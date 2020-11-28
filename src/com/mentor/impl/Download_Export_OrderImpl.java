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

import com.mentor.Datatable.Download_Export_OrderDT;
import com.mentor.action.Dist_exp_uni_reg_action;
import com.mentor.action.Download_Export_OrderAction;
import com.mentor.action.Update_InspectionAction;

import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;



public class Download_Export_OrderImpl {
	
	public void getDetails(Download_Export_OrderAction act){


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
				
				act.setDl_id(rs.getInt("int_app_id_f"));
			}
     
			////System.out.println();
			
			
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
	
	/*public String getDetails(Download_Export_OrderAction ac) throws SQLException {
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
	*/
	
	
	
 public void update(Download_Export_OrderAction action){
	 PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		int status = 0;
		
		System.out.println("action.getApplication_no_action()"+action.getApplication_no_action());
		
		try {
			


			con = ConnectionToDataBase.getConnection();
			
			String query = " update distillery.eoi_app_for_export_order set "+
					" container= '"+action.getRadio1()+"',  sealing_date = ?, "+
					" seal_no = ?, " +
					"  container_no =? " +
					" where app_id = '"+action.getApplication_no_action()+"' ";   
			
			//System.out.println("****update------***"+query);
					
			ps = con.prepareStatement(query);
			if(action.getRadio1().equalsIgnoreCase("FC") || action.getRadio().equalsIgnoreCase("mspc")){
			ps.setDate(1, Utility.convertUtilDateToSQLDate(action.getSealdate()));
			ps.setString(2, action.getSealno());
			ps.setString(3, action.getContainerno());
			}else{
				ps.setDate(1, null);
				ps.setString(2, null);
				ps.setString(3, null);
			}
		    status = ps.executeUpdate();
        
        if(status == 1){
				//action.setMsg("Saved Successfully");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update Successfully","Update Successfully"));	
			    action.reset();
        }else{
				//action.setMsg("Not Saved");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Not Update","Not Update"));
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
 
 public ArrayList getdata(Download_Export_OrderAction act) {
		
		

     ArrayList list = new ArrayList();
    
		Connection con = null;
		PreparedStatement ps = null;
		int i = 1;
		String filter = "";
		try {
			
			if(act.getRadio().equalsIgnoreCase("pfa")){
				
				filter = " b.esign_date is null ";
				
			}else if(act.getRadio().equalsIgnoreCase("ms1")){
				filter = " b.esign_date  is not null and b.container is null ";
				
			}else if (act.getRadio().equalsIgnoreCase("ms2")){
				
				filter = " b.esign_date is not null and b.sealing_date is not null";
			}
             else if (act.getRadio().equalsIgnoreCase("DP")){
				
				filter = " b.esign_date is not null  ";
			}
           else if (act.getRadio().equalsIgnoreCase("mspc")){
	
	        filter = " b.sealing_date is null and  b.container='PC'";
          }
			
			
			
	 	
			con = ConnectionToDataBase.getConnection();			
					
			String show_query = 
					         " SELECT b.app_id, a.seq_pk,b.created_date, a.po_number, a.po_date,a.importing_unit_nm ,"+
                             " b.approve_flag,b.verify_date,b.reject_date,coalesce(b.container,'NA') as container,b.status,coalesce(b.approve_flag,'NA') as approve_flag, " +
                             " b.container_no,b.seal_no,b.sealing_date ,b.esign_date ,b.esign_pdf ,coalesce(b.objection_flag,'NA') as objection_flag1 "+
                             " from distillery.eoi_import_order_master a ,distillery.eoi_app_for_export_order b " +
                             " where  " + filter + " " +
                             " and b.order_id= a.seq_pk  and a.int_dist_id='"+act.getDl_id()+"' order by a.seq_pk ";
					
					 
			 ps = con.prepareStatement(show_query);						 
										 
			 
			 
			
			  ResultSet rs = ps.executeQuery();
			  SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

			while (rs.next())

			{
        
				 
				Download_Export_OrderDT dt = new Download_Export_OrderDT();
                 
				
				
                dt.setSn(i);
                dt.setAppNO(rs.getInt("app_id"));
                dt.setOrderno(rs.getLong("po_number"));
                dt.setOrderdate(rs.getDate("created_date"));
                dt.setImpu(rs.getString("importing_unit_nm"));
                dt.setStatus(rs.getString("status"));
                dt.setContno(rs.getString("container_no"));
                dt.setSeal(rs.getString("seal_no"));
                if(rs.getDate("verify_date")!=null && rs.getString("container").equalsIgnoreCase("FC")){
                	String date = formatter.format(rs.getDate("verify_date"));
                	
                	 dt.setSealstatus("Approved on date "+date);
                }
                else if(rs.getDate("reject_date")!=null && rs.getString("container").equalsIgnoreCase("FC")){
                	String date1 = formatter.format(rs.getDate("reject_date"));
                	 dt.setSealstatus("Rejected on date "+date1);
                }
                else{
                	dt.setSealstatus("NA");
                }
                dt.setSealdate(rs.getDate("sealing_date"));
                dt.setPdfName(rs.getString("esign_pdf"));
                if(rs.getString("container").equalsIgnoreCase("FC")){
                	
                   dt.setConttype("Full Container");
                }
                else if(rs.getString("container").equalsIgnoreCase("PC")){
                    dt.setConttype("Part Container");
                    
                }
                if(rs.getString("objection_flag1").equals("O"))
    			{
                	dt.setObjection_reply_button(true);
    				
    			}else{
    				dt.setObjection_reply_button(false);
    				
    			}
                
    			
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
