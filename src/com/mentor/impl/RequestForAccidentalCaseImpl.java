package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.mentor.Datatable.EoiExportOrderFrorwardingDT;
import com.mentor.Datatable.RequestForAccidentalCaseDT;
import com.mentor.action.EoiExportOrderFrorwardingAction;
import com.mentor.action.RequestForAccidentalCaseAction;
import com.mentor.resource.ConnectionToDataBase;

import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class RequestForAccidentalCaseImpl {
	public void getDetails(RequestForAccidentalCaseAction act){


		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionToDataBase.getConnection();

			
           String selQr =   
		 
		  "  SELECT int_app_id_f , vch_undertaking_name, vch_wrk_add  FROM " +
		  "  public.dis_mst_pd1_pd2_lic   where vch_wrk_phon = '"+ResourceUtil.getUserNameAllReq()+"' " ;
	 
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();
			
			 
			while (rs.next()) {
				act.setDistillery_id(rs.getInt("int_app_id_f"));
				act.setName(rs.getString("vch_undertaking_name"));
				act.setAddress(rs.getString("vch_wrk_add"));
			
			}
     
			//System.out.println();
			
			
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
	
	
	public ArrayList getDistrictList(RequestForAccidentalCaseAction act) {
		
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		try {

			String query = "select description , districtid from public.district order by description";
			
			System.out.println(" --getdistrict_list--- "+query);

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				item = new SelectItem();

				item.setValue(rs.getString("districtid"));
				item.setLabel(rs.getString("description"));

				list.add(item);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	/*public boolean SaveImpl(RequestForAccidentalCaseAction act){

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 1;
		String query = ""; 
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());
		
		boolean flg=false;
		 try {

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			 
			
				
			       query   =    " INSERT INTO distillery.request_for_accidental_case "+
                                " (req_id, gatepass_type, gatepass_no, gatepass_date, accident_address, accident_district, accident_date," +
                                " distillery_id, created_date) "+
                                 " VALUES((SELECT coalesce(max(req_id),0)+1 from distillery.request_for_accidental_case), ?, ?, ?, ?, ?, ?, ?, ?)";
			
			
			
			 
					pstmt = conn.prepareStatement(query);
				
				    pstmt.setString(1, act.getGatepass_type());
				    pstmt.setString(2, act.getGatepass_no());
				    pstmt.setDate(3, Utility.convertUtilDateToSQLDate(act.getGatepass_date()));
				    pstmt.setString(4, act.getAccident_address());
				    pstmt.setInt(5, Integer.parseInt(act.getAccident_district_id()));
				    pstmt.setDate(6, Utility.convertUtilDateToSQLDate(act.getAccident_date()));
				    pstmt.setInt(7, act.getDistillery_id());
				    pstmt.setDate(8, Utility.convertUtilDateToSQLDate(new Date()));
									
					saveStatus = pstmt.executeUpdate();
					
					System.out.println("====save status= pi details=1"+saveStatus);
				
				
			
			
			
			if (saveStatus > 0) {
				conn.setAutoCommit(true);
				flg=true;
				act.reset();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("SAVED SUCCESSFULLY !", "SAVED SUCCESSFULLY !"));
				act.reset();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("SOMETHING WENT WRONG !", "SOMETHING WENT WRONG !"));
				conn.rollback();

			}
		 }


		catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}

		finally {
			try {

				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	return flg;*/

	
	//===============================================fl3=====================
	
	public boolean SaveImplFl3insert(RequestForAccidentalCaseAction act){

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 1;
		String query = ""; 
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());
		
		boolean flg=false;
		 try {
			 
			 
			 String query1 =" select * from  distillery.gatepass_to_manufacturewholesale_20_21 " +
			 		        " where vch_gatepass_no like '"+act.getGatepass_no()+"'";

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(query1);
			
			 rs = pstmt.executeQuery();
			 
			   if (rs.next())
				
			   {
				   
				pstmt = null ;	   
				   
				
			       query   =    " INSERT INTO distillery.request_for_accidental_case "+
                           " (req_id, gatepass_type, gatepass_no, gatepass_date, accident_address, accident_district, accident_date," +
                           " distillery_id, created_date) "+
                            " VALUES((SELECT coalesce(max(req_id),0)+1 from distillery.request_for_accidental_case), ?, ?, ?, ?, ?, ?, ?, ?)";
			
			
			 
					pstmt = conn.prepareStatement(query);
				
				    pstmt.setString(1, act.getGatepass_type());
				    pstmt.setString(2, act.getGatepass_no());
				    pstmt.setDate(3, Utility.convertUtilDateToSQLDate(act.getGatepass_date()));
				    pstmt.setString(4, act.getAccident_address());
				    pstmt.setInt(5, Integer.parseInt(act.getAccident_district_id()));
				    pstmt.setDate(6, Utility.convertUtilDateToSQLDate(act.getAccident_date()));
				    pstmt.setInt(7, act.getDistillery_id());
				    pstmt.setDate(8, Utility.convertUtilDateToSQLDate(new Date()));
									
					saveStatus = pstmt.executeUpdate();
					
					System.out.println("====save status= pi details=1"+saveStatus);
				
				
			   }
			
			
			if (saveStatus > 0) {
				conn.setAutoCommit(true);
				flg=true;
				act.reset();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("SAVED SUCCESSFULLY !", "SAVED SUCCESSFULLY !"));
				act.reset();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Please Enter Vaild Gatepass Number !", "Please Enter Vaild Gatepass Number !"));
				conn.rollback();

			}
		 }

		
		catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}

		finally {
			try {

				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	return flg;

	}
	
	
	
	
	
	//===============================savefl1============================
	public boolean SaveImplFl1(RequestForAccidentalCaseAction act){

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 1;
		String query = ""; 
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());
		
		boolean flg=false;
		 try {
			 
			 
			 String query1 =" select * from distillery.gatepass_to_districtwholesale_20_21 " +
			 		        " where vch_gatepass_no like '"+act.getGatepass_no()+"'";

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			 
			pstmt = conn.prepareStatement(query1);
			
			 rs = pstmt.executeQuery();
				
			      
			 if (rs.next())
					
			   {
				   
				 flg=true;
				
				
			
			   }else{
				   
				   flg=false;
				   FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
						   "Please Enter Vaild Gatepass Number !", "Please Enter Vaild Gatepass Number !"));
					conn.rollback();
					 
			   }
			 		
			
		 }


		catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}

		finally {
			try {

				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	return flg;

	}
	
	
	
	
	
	//=================================insertimpl================================
	
	public void insert_impl(RequestForAccidentalCaseAction act){

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 1;
		String query = ""; 
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());
		
		boolean flg=false;
		 try {
			 
			

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			 
			
			
			       query   = " INSERT INTO distillery.request_for_accidental_case "+
                           " (req_id, gatepass_type, gatepass_no, gatepass_date, accident_address, accident_district, accident_date," +
                           " distillery_id, created_date) "+
                            " VALUES((SELECT coalesce(max(req_id),0)+1 from distillery.request_for_accidental_case), ?, ?, ?, ?, ?, ?, ?, ?)";	
			 
			       
			       
			       
					pstmt = conn.prepareStatement(query);
				
				    pstmt.setString(1, act.getGatepass_type());
				    pstmt.setString(2, act.getGatepass_no());
				    pstmt.setDate(3, Utility.convertUtilDateToSQLDate(act.getGatepass_date()));
				    pstmt.setString(4, act.getAccident_address());
				    pstmt.setInt(5, Integer.parseInt(act.getAccident_district_id()));
				    pstmt.setDate(6, Utility.convertUtilDateToSQLDate(act.getAccident_date()));
				    pstmt.setInt(7, act.getDistillery_id());
				    pstmt.setDate(8, Utility.convertUtilDateToSQLDate(new Date()));
									
					saveStatus = pstmt.executeUpdate();
					
					System.out.println("====save status= pi details=1"+saveStatus);
			
			if (saveStatus > 0) {
				conn.setAutoCommit(true);
				flg=true;
				act.reset();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("SAVED SUCCESSFULLY !", "SAVED SUCCESSFULLY !"));
				act.reset();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,        
				"Please Enter Vaild Gatepass Number !", "Please Enter Vaild Gatepass Number !"));
				conn.rollback();

			}
		 }


		catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}

		finally {
			try {

				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}

	
	
	
//======================================================
	
	public ArrayList display_detail(RequestForAccidentalCaseAction act) {
		
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	     int sr = 1 ; ;

		try {

			String query = 
					
				   " SELECT req_id, gatepass_type, gatepass_no, gatepass_date, accident_address, accident_district, accident_date, created_date, "+
                   " (select description from public.district where districtid=accident_district) as dist_name FROM distillery.request_for_accidental_case where distillery_id ="+act.getDistillery_id()+"";
					
				
			System.out.println("-- brand details ===="+query);
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				RequestForAccidentalCaseDT  dt = new RequestForAccidentalCaseDT();
				dt.setReq_id(rs.getInt("req_id"));
				if(rs.getString("gatepass_type").equalsIgnoreCase("FL3")){
					dt.setGatepass_type("FL3/3A");
				}
				else if(rs.getString("gatepass_type").equalsIgnoreCase("FL1")){
					dt.setGatepass_type("FL1/1A");
				}
				else if(rs.getString("gatepass_type").equalsIgnoreCase("CL")){
					dt.setGatepass_type("CL");
				}
				dt.setSrno(sr);
				dt.setGatepass_no(rs.getString("gatepass_no"));
				dt.setGatepass_date(rs.getDate("gatepass_date"));
				dt.setAccident_address(rs.getString("accident_address"));
				dt.setAccident_district_name(rs.getString("dist_name"));
				dt.setAccident_date(rs.getDate("accident_date"));
				dt.setReq_dt(rs.getDate("created_date"));	
				sr++ ;
										
				list.add(dt);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}
	




public boolean SaveImplFl3(RequestForAccidentalCaseAction act){

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 1;
	String query = ""; 
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	String time = sdf.format(cal.getTime());
	
	boolean flg=false;
	 try {
		 
		 
		 
		 String query1 =" select * from distillery.gatepass_to_manufacturewholesale_20_21 " +
		 		        " where vch_gatepass_no like '"+act.getGatepass_no()+"'";

		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		 
		pstmt = conn.prepareStatement(query1);
		
		 rs = pstmt.executeQuery();
			
		      
		 if (rs.next())
				
		   {
			   
			 flg=true;
			
			
		
		   }else{
			   
			   flg=false;
			   FacesContext.getCurrentInstance().addMessage(null,new FacesMessage( FacesMessage.SEVERITY_ERROR,
			"Please Enter Vaild Gatepass Number !", "Please Enter Vaild Gatepass Number !"));
				conn.rollback();
				 
		   }
		 		
		
	 }


	catch (SQLException e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(e.getMessage(), e.getMessage()));
		e.printStackTrace();
	}catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(e.getMessage(), e.getMessage()));
		e.printStackTrace();
	}

	finally {
		try {

			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
return flg;

}


//=================================================CL==============



public boolean SaveImplCL(RequestForAccidentalCaseAction act){

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 1;
	String query = ""; 
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	String time = sdf.format(cal.getTime());
	
	boolean flg=false;
	 try {
		 
		 
		 
		 String query1 =" select * from distillery.gatepass_to_manufacturewholesale_cl_20_21 " +
		 		        " where vch_gatepass_no like '"+act.getGatepass_no()+"'";

		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		 
		pstmt = conn.prepareStatement(query1);
		
		 rs = pstmt.executeQuery();
			
		      
		 if (rs.next())
				
		   {
			   
			 flg=true;
			
			
		
		   }else{
			   
			   flg=false;
			   FacesContext.getCurrentInstance().addMessage(null,new FacesMessage( FacesMessage.SEVERITY_ERROR,
			"Please Enter Vaild Gatepass Number !", "Please Enter Vaild Gatepass Number !"));
				conn.rollback();
				 
		   }
		 		
		
	 }


	catch (SQLException e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(e.getMessage(), e.getMessage()));
		e.printStackTrace();
	}catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(e.getMessage(), e.getMessage()));
		e.printStackTrace();
	}

	finally {
		try {

			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
return flg;

}

}



//=============================================end================================================


