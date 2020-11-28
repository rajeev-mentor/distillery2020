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

import com.mentor.Datatable.Replytoobjection_eoi_dt;
import com.mentor.action.Replytoobjection_eoi_Action;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Utility;



public class Replytoobjection_eoi_impl {

	
	
	

	public ArrayList getobjectionlist( Replytoobjection_eoi_Action ac ,int id)
	{
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Date rplydate = null;
		int j = 1;
		String selQr="";
		
		selQr = "  SELECT a.app_id, a.objection_title, a.reply_on_objection, a.objection_id, a.objection_description, " +
				"  a.objected_by,  a.objection_date, b.objection_flag, a.reply_date, "+
				 " (SELECT max(objection_id) from distillery.eoi_import_order_objection WHERE  app_id=b.seq_pk) as maxId  "+ 
				 " FROM distillery.eoi_import_order_objection a,distillery.eoi_import_order_master b    "+    
				 " where a.app_id=b.seq_pk and a.app_id='"+id+"' ";
				 		
		try
		{
			 System.out.println("========selQr===="+selQr);
			 
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(selQr);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Replytoobjection_eoi_dt dt=new Replytoobjection_eoi_dt();
				dt.setSrNo(j);
				dt.setAppid(rs.getInt("app_id"));
				dt.setDescription_dt(rs.getString("objection_description"));
				dt.setTitle(rs.getString("objection_title"));
				dt.setObjectionRaisedBy_dt(rs.getString("objected_by"));
				dt.setObjectionID_dt(rs.getInt("objection_id"));
				rplydate = rs.getDate("reply_date");
				if(rs.getInt("objection_id")==rs.getInt("maxId") && rplydate == null)
				{
					dt.setDisableFlg(false);
					
				}
				else{
					dt.setDisableFlg(true);
					
				}
				
				if(rs.getString("objection_flag").equalsIgnoreCase("A")){

					dt.setStatus("Objection Accept");
				}else if(rs.getString("objection_flag").equalsIgnoreCase("D")){
					dt.setStatus("Objection Decline");
				}else if(rs.getString("objection_flag").equalsIgnoreCase("O")){
					dt.setStatus("Objection Raised");
				}else if(rs.getString("objection_flag").equalsIgnoreCase("R")){
					dt.setStatus("Objection Replied");
				}else{
					
				}
				
				list.add(dt);
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
				
	    		if(con!=null)con.close();
	    		if(ps!=null)ps.close();
	    		if(rs!=null)rs.close();
	    		
	    		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return list;
	}


	public String submitDataImpl(Replytoobjection_eoi_Action act)
	{


		Connection conn = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		int saveStatus = 0;
		//boolean isValid = false;

		try {
		
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

			String query = 	" UPDATE distillery.eoi_import_order_objection " +
							" SET reply_on_objection=?, uploaded_file=?, reply_date=?, reply_time=? " +
							" WHERE app_id="+act.getAppID()+" AND objection_id="+act.getObjectionID()+" ";
			
								

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, act.getFillComment());
			pstmt.setString(2, act.getViewFile());
			pstmt.setDate(3, Utility.convertUtilDateToSQLDate(new Date()));
			pstmt.setString(4,dateFormat.format(Utility.convertUtilDateToSQLDate(new Date())));
			
			saveStatus = pstmt.executeUpdate();

				String updtQr=	" UPDATE distillery.eoi_import_order_master SET objection_flag='R' " +
   		              			" WHERE  seq_pk="+act.getAppID()+" ";
				
				con = ConnectionToDataBase.getConnection();
				pstmt1 = con.prepareStatement(updtQr);
				
				
				saveStatus = pstmt1.executeUpdate();
				if (saveStatus > 0) {
					conn.commit();
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
					" Data Saved Successfully !! ", "Data Saved Successfully !!"));
					act.reset();
					
			}

			 else {
							
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
				" Data Not Saved!! ", "Data Not Saved !!"));

			}
			

		}
		
		catch (Exception e) {
			e.printStackTrace();

		}

		finally {
			try {

				if (conn != null)
					conn.close();
				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
				if (pstmt1 != null)
					pstmt1.close();
				
				if (rs != null)
					rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "";

	
	
	}
	
	public String getvalue(Replytoobjection_eoi_Action ac)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String userId="";
		
		
		try
		{
			
		String query = 	 " SELECT a.app_id, a.objection_title, a.reply_on_objection, a.objection_id, a.objection_description, "+         
			    "a.objected_by, a.objection_date, b.objection_flag ,   "+                                              
			    " (SELECT max(objection_id) from distillery.eoi_import_order_objection   "+                 
			    " WHERE  app_id=b.seq_pk) as maxId     "+                                                            
			    " FROM distillery.eoi_import_order_objection a, distillery.eoi_import_order_master b  "+
			    " where a.app_id=b.seq_pk and a.objection_id="+ac.getObjectionID()+" ";	
					

  

		conn = ConnectionToDataBase.getConnection();
		pstmt=conn.prepareStatement(query);
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			ac.setTitle(rs.getString("objection_title"));
			ac.setObj_date(rs.getDate("objection_date"));
							
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
          		if(pstmt!=null)pstmt.close();
          		if(rs!=null)rs.close();
          		
          		
      		}
      		catch(Exception e)
      		{
      			e.printStackTrace();
      		}
      	}
	return userId;	
	}






	}
	
	



