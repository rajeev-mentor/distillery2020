package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.mentor.Datatable.fl3a_3a_licence_dt;
import com.mentor.action.fl3a_3a_licence_action;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;
public class fl3a_3a_licence_impl {
//---------------------------------------------------Hidden -----------------------------------------------------------Created by Arvind-------------Verma-------------------
	public String  hidden(fl3a_3a_licence_action act) {
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement pst=null;
		String query="";
		
		try {
			

			query="select brewery_id , lic_no  ,login from  bwfl.fl3_3a_licence where lic_type='FL3A'" +
				   " and  login='"+ResourceUtil.getUserNameAllReq().trim() +"' ";
			con=ConnectionToDataBase.getConnection();
			pst=con.prepareStatement(query);
			rs=pst.executeQuery();
			while (rs.next()) {
				act.setBrewery_id(rs.getInt("brewery_id"));
				act.setLic_no(rs.getString("lic_no"));
				act.setLic_type(rs.getString("lic_no"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (pst!=null) {
					pst.close();
				}
				if (rs!=null) {
					rs.close();
					
				}
				if (con!=null) {
					con.close();
					
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		return "";
		
	}
	
	//-------------------------------------------------------------------------Displaylist Details--------------------------------Created by Arvind-------------Verma-----
	public ArrayList displaylistImpl(String radio ){
		ArrayList list=new ArrayList();
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String query="";
		int i=1;
		try {
			query =" SELECT a.storage_id as storage_id,a.int_spirit_type_id as int_spirit_type_id , " +
					"a.storage_desc as storage_desc , a.vat_type as vat_type,"+
                    " a.capacity as capacity , a.strength as strength , a.int_app_id as int_app_id "+
                    " FROM bwfl.fl3_3a_vat_master  a  where vat_type='"+radio+"'  ";
            
	//		System.out.println("=----------------------------bwfl-------------------"+query);
	//		System.out.println("----------------------radio----"+radio);
			con=ConnectionToDataBase.getConnection();
			pst=con.prepareStatement(query);
			rs=pst.executeQuery();
			while (rs.next()) {
				fl3a_3a_licence_dt dt=new fl3a_3a_licence_dt();
				dt.setSno(i);
				dt.setVat_name(rs.getString("storage_desc"));
				dt.setCap(rs.getDouble("capacity"));
				dt.setStorage_id(rs.getInt("storage_id"));
				list.add(dt);
				i++;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			try {
			if (con!=null) {
				con.close();
			}	
			if (pst!=null) {
				pst.close();
			
			}
			if (rs!=null) {
				rs.close();
			}
			} catch (Exception e1) {
		e1.printStackTrace();
		}
		}
		
		
		return list;
		
	}
	//------------------------------Select ----------------------------------------------------------------------------------Created by Arvind-------------Verma------
	public boolean select(fl3a_3a_licence_dt dt){
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null; 
		boolean s=false;
		String query="";
		try {
			query="select  storage_id,vat_type,lic_no,storage_desc ,capacity,int_app_id from " +
					"  bwfl.fl3_3a_vat_master where storage_id='"+dt.getStorage_id()+"'";
		//	System.out.println("-------------------------------"+query);
			con=ConnectionToDataBase.getConnection();
			pst=con.prepareStatement(query);
			rs=pst.executeQuery();
			if (rs.next()) {
				s=true;
			}else {
				s=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (con!=null) {
					con.close();
				}
				if (rs!=null) {
					rs.close();
				}if (pst!=null) {
					pst.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return s;
	}

	//--------------------------------Update-----------------------------------------------------------------------------Created by Arvind-------------Verma-------
	public int  update(fl3a_3a_licence_dt dt ,fl3a_3a_licence_action act){
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String query="";
		int status=0;
		int count=0;
		ArrayList list=new ArrayList();
		try {
			
			query=
			" UPDATE  bwfl.fl3_3a_vat_master  " +
			 " SET  storage_desc='"+dt.getVat_name()+"' ," +
			 " capacity="+dt.getCap() + " " + 
			 "  where  storage_id='"+dt.getStorage_id()+"' " ;

			con=ConnectionToDataBase.getConnection();
			
				pst=con.prepareStatement(query);
				status=pst.executeUpdate();
	           
			
			if (status>0) {
				count++;
			}
			else {
				
			}
			
			} 
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (con!=null) {
					con.close();
				}if (pst!=null) {
					pst.close();
				}if (rs!=null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return count;
		//return list;
	}

//--------------------------------------------------------------------Save Methods----------------------------------------------------Created by Arvind-------------Verma------
	public int  save(fl3a_3a_licence_action act,fl3a_3a_licence_dt dt){
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList list=new ArrayList();
		String query="";
		int count=0;
		int status=0;
		try {
			query="insert into bwfl.fl3_3a_vat_master ( storage_id,vat_type ,lic_no ,storage_desc ,capacity ,int_app_id,brewery_id) " +
				  " values('"+this.maxId()+"' ,'"+act.getRadio()+"' ,'"+act.getLic_no()+"' ,?" +
				  " , ?,'"+act.getBrewery_id()+"' ,'"+act.getBrewery_id()+"')";
		
			
	//		System.out.println("=========vat name==============="+dt.getVat_name());
		//	System.out.println("==========insert -----------------"+query);
			con=ConnectionToDataBase.getConnection();
	     
			
			pst=con.prepareStatement(query);
			
			if (dt.getVat_name()!=null) {
				pst.setString(1,dt.getVat_name());
			}
			if (dt.getCap()!=0.0) {
				pst.setDouble(2,dt.getCap());
			}
			
			status=pst.executeUpdate();
		
			if (status>0) {
				count++;
			}else {
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
			if (con!=null) {
				con.close();
			}if (rs!=null) {
				rs.close();
				
			}
			if (pst!=null) {
				pst.close();
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return count;
	}

	//---------------------------------------------Max Id--Methods---------------------------------Created by Arvind-------------Verma-------------------------------
	public int maxId()
	{

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " select max(storage_id) as id   from bwfl.fl3_3a_vat_master";
		int maxid = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
		//	System.out.println("--------------------------"+query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxid = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return maxid+1;
	
	}

	
	
	
}
