package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import com.mentor.Datatable.FL3_3A_VAT_DECLARATION_DT;
import com.mentor.action.FL3_3A_VAT_DECLARATION_ACTION;


import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;

public class FL3_3A_DECLARATION_IMPL {
	
	public String getSugarmill(FL3_3A_VAT_DECLARATION_ACTION ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		 String queryList = 		 	" SELECT x.app_id, x.distillery_id, x.license_district, x.license_no, x.vch_licence_type,               "+
					" x.distilery_district, x.unit_name, x.unit_adrs      "+
					" FROM                                                                                                  "+
					" (SELECT a.int_app_id as app_id, a.int_distillery_id as distillery_id, a.district as license_district, "+
					" a.vch_license_fl1a as license_no, a.vch_licence_type, b.vch_unit_dist as distilery_district,          "+
					" a.vch_unit_name as unit_name, a.vch_unit_addrs as unit_adrs "+
					" FROM licence.fl3a_fl1a a, public.dis_mst_pd1_pd2_lic b                              "+
					" WHERE a.int_distillery_id=b.int_app_id_f AND a.vch_lic_unit_type='D'                                  "+
					" AND a.vch_licence_type='FL3A' AND a.loginuser='"+ResourceUtil.getUserNameAllReq().trim() +"'          "+
					" UNION                                                                                                 "+
					" SELECT a.int_lic_id as app_id, a.int_distillery_id as distillery_id, a.district as license_district,  "+
					" a.vch_licence_no as license_no, a.vch_licence_type, b.vch_unit_dist as distilery_district,            "+
					" a.vch_unit_name as unit_name, a.vch_unit_addrs as unit_adrs "+
					" FROM licence.licence_entery_fl3_fl1 a, public.dis_mst_pd1_pd2_lic b                 "+
					" WHERE a.int_distillery_id=b.int_app_id_f AND a.vch_lic_unit_type='D'                                  "+
					" AND a.vch_licence_type='FL3' AND a.loginuser='"+ResourceUtil.getUserNameAllReq().trim() +"' )x ";
		 con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();
             
			
			
			while (rs.next()) {
				ac.setDissteleryName((rs.getString("license_no")));
				ac.setDissleryId(rs.getInt("distillery_id"));
				ac.setHiddenFlag(false);
				
				//ac.setDisslryAdd(rs.getString("vch_wrk_add"));

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return "";

	}
	
	
	
	
	public ArrayList spiritDetails(FL3_3A_VAT_DECLARATION_ACTION action) {

		ArrayList list = new ArrayList();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String filter = null;
		String selQuery = null;

	 if(action.getRadio().equalsIgnoreCase("BOT"))
		{
			
			selQuery = " SELECT storage_id, recieve_bl, recieve_al, storage_desc, lic_no, consumed_bl, consumed_al, openingal, openingbl," +
				" vat_type, capacity, strength, int_app_id, distillery_id "+
                " FROM distillery.fl3_3a_vat_master where  lic_no='"+action.getDissteleryName()+"'  and  distillery_id='"+action.getDissleryId()+"'  and vat_type='BOT'  ";

		}
		else if (action.getRadio().equalsIgnoreCase("BLN"))
		{
			selQuery = " SELECT storage_id, recieve_bl, recieve_al, storage_desc, lic_no, consumed_bl, consumed_al, openingal, openingbl," +
					" vat_type, capacity, strength, int_app_id, distillery_id "+
	                " FROM distillery.fl3_3a_vat_master where  lic_no='"+action.getDissteleryName()+"'  and  distillery_id='"+action.getDissleryId()+"' and vat_type='BLN'  ";

		}
		
		 
				
				 
				
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(selQuery);

			rs = ps.executeQuery();
			int i = 1;
			while (rs.next()) {

				FL3_3A_VAT_DECLARATION_DT dt = new FL3_3A_VAT_DECLARATION_DT();

				dt.setSrNo(i);
				
				dt.setTankName(rs.getString("storage_desc"));
				dt.setCapacity(rs.getDouble("capacity"));
				dt.setTankid(rs.getInt("storage_id"));
				dt.setSrNo(i);
				
				
				list.add(dt);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;

	}
	
	public boolean updateData(FL3_3A_VAT_DECLARATION_ACTION ac) {

		int usr = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		boolean flg = false;
		int saveStatus = 0;
		ResultSet rs = null;
		try {
			
			conn= ConnectionToDataBase.getConnection();
			
			
			for (int i = 0; i < ac.getAddRowBranding().size(); i++) {
				FL3_3A_VAT_DECLARATION_DT dt = (FL3_3A_VAT_DECLARATION_DT) ac.getAddRowBranding().get(i);
				
				 	String queryUP = "insert into  distillery.fl3_3a_vat_master " +
							          "(storage_id,storage_desc,vat_type,capacity)"+
							          " values ( '"+dt.getTankid()+"','"+dt.getTankName().trim()+"','"+ac.getRadio()+"','"+dt.getCapacity()+"') " +
						              " ON CONFLICT(storage_id) " +
						              " DO update set" +
							          " storage_desc = '"+dt.getTankName().trim()+"' , capacity='"+dt.getCapacity()+"' "; 
				/* 
				
				String queryUP = "update  distillery.fl3_3a_vat_master  set" +
				          " storage_desc = '"+dt.getTankName()+"' , capacity='"+dt.getCapacity()+"' where  storage_id='"+dt.getTankid()+"'" +
				          		" and distillery_id='"+ac.getDissleryId()+"' and vat_type='"+ac.getRadio()+"' and lic_no='"+ac.getDissteleryName()+"'";
					*/
				 
					pstmt = conn.prepareStatement(queryUP);
					saveStatus = pstmt.executeUpdate();
				
					
				
			}

			if (saveStatus > 0) {
				FacesContext.getCurrentInstance().addMessage(	null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR," Vat Saved Successfully ! ","Vat Saved Successfully !"));
								
								
			
				
				flg = true;

			} else {
			
				flg = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								" Invalid Request !!! "," Invalid Request !!!"));
					
			}

		}

		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage() ,e.getMessage()));		
			e.printStackTrace();
		}

		finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return flg;
	}

	public int max() {
FL3_3A_VAT_DECLARATION_DT dt=new FL3_3A_VAT_DECLARATION_DT();
		int usr = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		boolean flg = false;
		int saveStatus = 0;
		ResultSet rs = null;
		int id=0;
		try {
			
			int j=0;
			conn = ConnectionToDataBase.getConnection();

			conn.setAutoCommit(false);
			String query = "  select max(storage_id)+1  from distillery.fl3_3a_vat_master  ";
			pstmt = conn.prepareStatement(query);
			 rs = pstmt.executeQuery();
			 
			if (rs.next()) {

				id=rs.getInt(1);
				
				
			}
			
			
		
	}catch(Exception e){
		
	}
		finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		
		return id;

}

	
	
	// ===========  add data==================
	
	
	public ArrayList addrow(FL3_3A_VAT_DECLARATION_ACTION action)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		ArrayList list=new ArrayList();
		int j=0;
		try
		{
			
	     
	     
	    		// select max(chamber_id)+1 from distillery.pamr3_chamber_detail where int_seq_fk='"+dt1.getAppid()+"'
			String query2=" INSERT INTO distillery.fl3_3a_vat_master( storage_id,distillery_id,vat_type,lic_no) " +
     		              " VALUES ((select max(storage_id)+1  from distillery.fl3_3a_vat_master), '"+action.getDissleryId()+"', " +
     		              		" '"+action.getRadio()+"', '"+action.getDissteleryName()+"')";
	    
	     
	   System.out.println("--------addrow--------"+query2);
		conn = ConnectionToDataBase.getConnection();
		
		pstmt=conn.prepareStatement(query2);
		//conn.setAutoCommit(false);
		
		
		//pstmt.setInt(1,challanId);
		
		j=pstmt.executeUpdate();
		
		
		if(j>0)
		{
			//conn.commit();
		//ResourceUtil.addErrorMessage(Constants.DELETED_SUCESSFULLY, Constants.DELETED_SUCESSFULLY)	;
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							" Row Added Sucessfully !!!", " Row Added Sucessfully !!!"));
		}
		else
		{
			//ResourceUtil.addErrorMessage(Constants.NOT_DELETED, Constants.NOT_DELETED)	;	
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
      		try
      		{	
      			
          		if(conn!=null)conn.close();
          		if(pstmt!=null)pstmt.close();
          		if(rs!=null)rs.close();
          		
          		
      		}
      		catch(Exception e)
      		{
      			e.printStackTrace();
      		}
      	}
	return list;	
	}

	
	// ===========  delete data==================
	
	
				public ArrayList deleterow(FL3_3A_VAT_DECLARATION_ACTION action,int tankid)
				{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs=null;
					ArrayList list=new ArrayList();
					int j=0;
					try
					{
						
				     
				     String query2="DELETE FROM  distillery.fl3_3a_vat_master where  storage_id="+tankid+" " +
				          		" and distillery_id='"+action.getDissleryId()+"' and vat_type='"+action.getRadio()+"' and lic_no='"+action.getDissteleryName()+"'";
				     
				    
				     
				   //System.out.println("--------deleterow--------"+query2);
					conn = ConnectionToDataBase.getConnection();
					
					pstmt=conn.prepareStatement(query2);
					//conn.setAutoCommit(false);
					
					
					//pstmt.setInt(1,challanId);
					
					j=pstmt.executeUpdate();
					
					
					if(j>0)
					{
						//conn.commit();
					ResourceUtil.addErrorMessage(Constants.DELETED_SUCESSFULLY, Constants.DELETED_SUCESSFULLY)	;
					}
					else
					{
						ResourceUtil.addErrorMessage(Constants.NOT_DELETED, Constants.NOT_DELETED)	;	
						
					}
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					finally
					{
			      		try
			      		{	
			      			
			          		if(conn!=null)conn.close();
			          		if(pstmt!=null)pstmt.close();
			          		if(rs!=null)rs.close();
			          		
			          		
			      		}
			      		catch(Exception e)
			      		{
			      			e.printStackTrace();
			      		}
			      	}
				return list;	
				}
	
	

}
