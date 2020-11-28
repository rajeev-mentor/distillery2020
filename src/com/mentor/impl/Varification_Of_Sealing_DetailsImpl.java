
package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mentor.Datatable.Varification_Of_Sealing_DetailsDT;
import com.mentor.action.Dist_exp_uni_reg_action;
import com.mentor.action.Download_Export_OrderAction;
import com.mentor.action.Update_InspectionAction;
import com.mentor.action.Varification_Of_Sealing_DetailsAction;

import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;



public class Varification_Of_Sealing_DetailsImpl {
	

	
	public String getDetails(Varification_Of_Sealing_DetailsAction ac) throws SQLException {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;

		try {
		
			con = ConnectionToDataBase.getConnection();
		
			if (ResourceUtil.getUserNameAllReq().trim().substring(0, 9)
					.equalsIgnoreCase("Excise-DL")) {
				
			
				ac.setDl_id(Integer.parseInt(ResourceUtil.getUserNameAllReq()
						.trim().substring(10)));
			
			} else {
				
				}

			
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (ps2 != null)
					ps2.close();
				if (rs != null)
					rs.close();
				if (rs2 != null)
					rs2.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return "";

	}
	
	
	
	
 public void reject(Varification_Of_Sealing_DetailsAction action){
	 PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		int status = 0;
		
		System.out.println("action.getApplication_no_action()"+action.getApplication_no_action());
		
		try {
			


			con = ConnectionToDataBase.getConnection();
			
			String query = " update distillery.eoi_app_for_export_order set "+
					            " reject_date= ? " +
				             " where app_id = '"+action.getApplication_no_action()+"' ";   
			
			//System.out.println("****update------***"+query);
					
			ps = con.prepareStatement(query);
			ps.setDate(1, Utility.convertUtilDateToSQLDate(new Date()));
			
		//	ps.setString(1, action.getRadio1());
		    status = ps.executeUpdate();
        
        if(status == 1){
				//action.setMsg("Saved Successfully");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Rejected","Rejected"));	
			    action.reset();
        }else{
				//action.setMsg("Not Saved");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Not Rejected","Not Rejected"));
				con.rollback();
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

 }
 public void Verify(Varification_Of_Sealing_DetailsAction action){
	 PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		int status = 0;
		
		 
		
		try {
			


			con = ConnectionToDataBase.getConnection();
			
			String query = " update distillery.eoi_app_for_export_order set "+
					            " verify_date= ? " +
				             " where app_id = '"+action.getApplication_no_action()+"' ";   
			
			//System.out.println("****update------***"+query);
					
			ps = con.prepareStatement(query);
			ps.setDate(1, Utility.convertUtilDateToSQLDate(new Date()));
		//	ps.setString(1, action.getRadio1());
		    status = ps.executeUpdate();
        
        if(status == 1){
				//action.setMsg("Saved Successfully");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Verified","Verified"));	
			    action.reset();
        }else{
				//action.setMsg("Not Saved");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Not Verified","Not Verified"));
				con.rollback();
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

 }
 
 
 public ArrayList getdata(Varification_Of_Sealing_DetailsAction act) {
		
		

     ArrayList list = new ArrayList();
    
		Connection con = null;
		PreparedStatement ps = null;
		int i = 1;
		String filter = "";
		try {
			
			if(act.getRadio().equalsIgnoreCase("P")){
				
				filter = " b.verify_date is null ";
				
			}else if(act.getRadio().equalsIgnoreCase("V")){
				filter = " b.verify_date  is not null  ";
				
			}else if(act.getRadio().equalsIgnoreCase("R")){
				filter = " b.reject_date  is not null  ";
				
			}
			
			
			
	 	
			con = ConnectionToDataBase.getConnection();			
					
			String show_query = 
					          " SELECT b.app_id, a.seq_pk, a.po_number, a.po_date,a.importing_unit_nm ,"+
                             " b.status, b.container_no,b.seal_no,b.sealing_date ,b.esign_date ,b.esign_pdf , b.verify_date,b.container ,b.reject_date"+
                             " from distillery.eoi_import_order_master a ,distillery.eoi_app_for_export_order b " +
                             " where  " + filter + " " +
                             		"and b.order_id= a.seq_pk  and a.int_dist_id='"+act.getDl_id()+"' order by a.seq_pk ";
					
					
					//System.out.println("*****Show**********"+show_query);
	
			
			 ps = con.prepareStatement(show_query);						 
										 
			 
			 
			
			  ResultSet rs = ps.executeQuery();

			while (rs.next())

			{
        
				 
				Varification_Of_Sealing_DetailsDT dt = new Varification_Of_Sealing_DetailsDT();

                dt.setSn(i);
                dt.setAppNO(rs.getInt("app_id"));
                dt.setOrderno(rs.getLong("po_number"));
                dt.setOrderdate(rs.getDate("po_date"));
                dt.setImpu(rs.getString("importing_unit_nm"));
                dt.setStatus(rs.getString("status"));
                dt.setContno(rs.getString("container_no"));
                dt.setSeal(rs.getString("seal_no"));
                dt.setConttype(rs.getString("container"));
                dt.setSealdate(rs.getDate("sealing_date"));
                dt.setPdfName(rs.getString("esign_pdf"));
               
                
                
    			
                i++;
				list.add(dt);
             
			}
			
			
			
		} catch (Exception e) {
       e.printStackTrace();
       e.getMessage();
		}

		finally {
			try {

				con.close();
				ps.close();

			} catch (Exception e) {
			 System.out.println(e);
				   e.printStackTrace();
			}
		}

		return list;
}
}
