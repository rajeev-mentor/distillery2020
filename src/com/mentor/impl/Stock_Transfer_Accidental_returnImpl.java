package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


import com.mentor.Datatable.Stock_Transfer_Accidental_returnDT; 

import com.mentor.action.Stock_Transfer_Accidental_returnAction; 
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class Stock_Transfer_Accidental_returnImpl {
	//***************************************************Login Distillery*************************
	public void getDetails(Stock_Transfer_Accidental_returnAction act){


		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionToDataBase.getConnection();

			
           String selQr =   
		 
		  "  SELECT int_app_id_f , vch_undertaking_name FROM " +
		  "  public.dis_mst_pd1_pd2_lic where vch_wrk_phon = '"+ResourceUtil.getUserNameAllReq()+"' " ;
	 
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();
			
			 
			while (rs.next()) {
				
				act.setDistillery_id(rs.getLong("int_app_id_f"));
			}
     
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				
				if (rs != null)
					rs.close();
				
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	//************************************Display Data in Datatable1**************************  
	public ArrayList getDetails1(Stock_Transfer_Accidental_returnAction act){


		int i= 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list1=new ArrayList();
		
		try {
			conn = ConnectionToDataBase.getConnection();
             
			
		String select=

		    		" select distinct a.int_id, br.brand_name,a.dt_trans_main_stock,a.bottle_transto_main ,a.distillery_id,c.gatepass_type,d.size ,a.vch_gatepass_no,a.int_brand_id,a.int_pack_id,(a.return_bottle-a.breakage) as bottle,b.vch_from_lic_no as lic_no "+
		    		" from distillery.liquor_accidental_case a,distillery.gatepass_to_districtwholesale_20_21 b,distillery.request_for_accidental_case c ,"+
		    		" distillery.fl2_stock_trxn_20_21 d , distillery.brand_registration_20_21 br  where  a.vch_gatepass_no =d.vch_gatepass_no and  c.gatepass_no=a.vch_gatepass_no and a.int_pack_id=d.int_pckg_id  and "+
		    		" c.gatepass_type in ('FL1','FL1A') and a.vch_gatepass_no=b.vch_gatepass_no and  a.int_brand_id = br.brand_id and dt_trans_main_stock is null and a.distillery_id ='"+act.getDistillery_id()+"'";


		  
	      
		    System.out.println("=====select===="+select);
		    
	        
			
				pstmt=conn.prepareStatement(select);	
					
				rs = pstmt.executeQuery();
		   		while(rs.next())
		   		{
		   			Stock_Transfer_Accidental_returnDT dt = new Stock_Transfer_Accidental_returnDT();
		   			dt.setSrno(i);
		   			dt.setId(rs.getInt("int_id"));
		   			dt.setBrand_name(rs.getString("brand_name"));                             
		   			dt.setSize(rs.getInt("size"));
		   			dt.setBottle_no(rs.getLong("bottle"));
		   			dt.setFl1_fla(rs.getString("gatepass_type"));
		   			dt.setLicence_no(rs.getString("lic_no"));
		   			dt.setDate(Utility.convertUtilDateToSQLDate(new Date()));
		   			dt.setBrand_id(rs.getInt("int_brand_id"));
		   			dt.setPackge_id(rs.getInt("int_pack_id"));
		   			dt.setGatepass(rs.getString("vch_gatepass_no"));
		   			i++ ;
		   		
		   	
		   		 list1.add(dt);
		   		}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				
				if (rs != null)
					rs.close();
				
				if (conn != null)
					conn.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}return list1;
	}
	 //************************************ update process*****************************
	 public void getupdate(Stock_Transfer_Accidental_returnAction action,Stock_Transfer_Accidental_returnDT dt )
		{
			Connection conn = null;
			
			PreparedStatement pstmt1 = null;
			PreparedStatement pstmt2 = null;
			ResultSet rs=null;
			ArrayList list2=new ArrayList();
			
			int updateStatus = 1;
			int update1Status = 1;
			int i = 1;
		
			String update = "";
			 String update1 ="";
			try
			{
				    conn = ConnectionToDataBase.getConnection();
				    
				    conn.setAutoCommit(false);
				    
					    update =" update distillery.fl2_stock_20_21 set stock_bottles=stock_bottles+'"+dt.getTransfer_bottle()+"' where int_dist_id='"+action.getDistillery_id()+"' and licence_no='"+dt.getLicence_no()+"' and  " +
								" brand='"+dt.getBrand_id()+"' and package='"+dt.getPackge_id()+"' and size='"+dt.getSize()+"' ";
						
						pstmt1 = conn.prepareStatement(update);
						
						
						
						//System.out.println("----=============----updateerror Row----=============----"+update);

						updateStatus = pstmt1.executeUpdate();
						System.out.println("update statuse"+updateStatus);
			       
					update1 =    " update distillery.liquor_accidental_case set dt_trans_main_stock=?,bottle_transto_main='"+dt.getBottle_no()+"' " +
							     " where int_id='"+dt.getId()+"'  and vch_gatepass_no='"+dt.getGatepass()+"' and int_brand_id='"+dt.getBrand_id()+"' and int_pack_id='"+dt.getPackge_id()+"' and distillery_id='"+action.getDistillery_id()+"' ";
				    
					
					pstmt2 = conn.prepareStatement(update1);
					pstmt2.setDate(1, Utility.convertUtilDateToSQLDate(dt.getDate()));
					
					//System.out.println("----=============----update1 Row- status 1---=============----"+update1);
					
					update1Status = pstmt2.executeUpdate();
			      
		        if(update1Status >0){
		        	conn.commit();
						
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update Successfully","Update Successfully"));	
					    
		        }else{
						
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Not Update"));
						conn.rollback();
					}
			       
			        
			}catch(Exception e)
			{
				//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage() ,e.getMessage()));
				e.printStackTrace();
			}
			finally
			{
	      		try
	      		{	
	      			if (conn != null)
						conn.close();
					if (pstmt1 != null)
						pstmt1.close();
					if (pstmt2 != null)
						pstmt2.close();
	          		
	          	}
	      		catch(Exception e)
	      		{
	      			e.printStackTrace();
	      		}
	      	}
				
		
		}
	
	 //****************************************Display date in Datatable 2******************************************
	 
	 public ArrayList getShowData2(Stock_Transfer_Accidental_returnAction act)
	   {
	   	Connection conn = null;
	   	PreparedStatement ps = null;
	   	ResultSet rs = null;
	   	
	   	ArrayList list3 = new ArrayList();

	   	int i=1;
	   	try
	   	{
	   		String qury2 =
                           " select distinct br.brand_name,a.dt_trans_main_stock,a.bottle_transto_main ,d.size ,a.distillery_id,a.vch_gatepass_no,a.int_brand_id,a.int_pack_id,a.return_bottle-a.breakage as bottle,b.vch_from_lic_no as lic_no "+
                           " from distillery.liquor_accidental_case a,distillery.gatepass_to_districtwholesale_20_21 b,distillery.request_for_accidental_case c ,"+
                           " distillery.fl2_stock_trxn_20_21 d , distillery.brand_registration_20_21 br  where  a.vch_gatepass_no =d.vch_gatepass_no and a.int_pack_id=d.int_pckg_id and  c.gatepass_no=a.vch_gatepass_no and " +
                           " c.gatepass_type in ('FL1','FL1A') and a.vch_gatepass_no=b.vch_gatepass_no and  a.int_brand_id = br.brand_id and dt_trans_main_stock is  not null and a.distillery_id ='"+act.getDistillery_id()+"'";
	   		
	   		conn = ConnectionToDataBase.getConnection();
	   		ps = conn.prepareStatement(qury2);
	   	
	   		rs = ps.executeQuery();
	   		//System.out.println("====show===="+qury2);
	   		while(rs.next())
	   		{
	   			Stock_Transfer_Accidental_returnDT dataTable = new Stock_Transfer_Accidental_returnDT();
	   			
	   			dataTable.setSrno(i);
	   			dataTable.setBrand_name(rs.getString("brand_name"));
	   			dataTable.setSize(rs.getInt("size"));
	   			dataTable.setDate(Utility.convertUtilDateToSQLDate(new Date()));
	   			dataTable.setBottle_no(rs.getLong("bottle"));
	   			dataTable.setLicence_no(rs.getString("lic_no"));
	   			i++;
	   			
	   		 list3.add(dataTable);
	   		
	   		}
	   	}
	   	catch(Exception e)
	   	{
	   		e.printStackTrace();
	   	}
	   	finally
	   	{
	   		try
	   		{
	   			if(conn!=null)conn.close();
	   			if(ps!=null)ps.close();
	   			if(rs!=null)rs.close();
	   		}
	   		catch(Exception e)
	   		{
	   			e.printStackTrace();
	   		}
	   	}
	   	return list3;
	   }
}
