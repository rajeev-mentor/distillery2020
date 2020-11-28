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

import com.mentor.Datatable.DistilleryExportUnitForwardingDT;
import com.mentor.Datatable.DistilleryExportUnitForwardingDT;
import com.mentor.Datatable.dist_exp_uni_reg_dt;
import com.mentor.action.DistilleryExportUnitForwardingAction;
import com.mentor.action.DistilleryExportUnitForwardingAction;
import com.mentor.action.DistilleryExportUnitForwardingAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class DistilleryExportUnitForwardingImpl {
	
	
	public ArrayList export_order_display_detail(DistilleryExportUnitForwardingAction action ) {
		
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	   int sr = 1 ; ;
	    String filter="";
	    if(ResourceUtil.getUserNameAllReq().substring(0, 9).equalsIgnoreCase("Excise-DL")){
	    	
	    	if(action.getRadio().equalsIgnoreCase("N")){
	    		
	    		filter= " user1_dt is null and user1_nm='"+ResourceUtil.getUserNameAllReq()+"' " +
	    				" and (objection_flag not in ('O','D','R') or objection_flag is null)";
	    		
	    	}else if(action.getRadio().equalsIgnoreCase("A")){
	    		
	    		filter= " user1_dt is not null and user1_nm='"+ResourceUtil.getUserNameAllReq()+"' " +
	    				" and (objection_flag not in ('O','D','R') or objection_flag is null)";
	    		
	    		
	    	}
	    	
	    }else if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-DEC")){
	    	

	    	if(action.getRadio().equalsIgnoreCase("N")){
	    		
	    		filter= " user2_dt is null and user1_dt is not null and user2_name='"+ResourceUtil.getUserNameAllReq()+"' " +
	    				" and (objection_flag not in ('O','D','R') or objection_flag is null)";
	    		
	    		
	    	}else if(action.getRadio().equalsIgnoreCase("A")){
	    		
	    		filter= " user2_dt is not null and user2_name='"+ResourceUtil.getUserNameAllReq()+"' " +
	    				" and (objection_flag not in ('O','D','R') or objection_flag is null)";
	    		
	    	}else if(action.getRadio().equalsIgnoreCase("O")){
	    		
	    		filter= " user2_dt is  null and user2_name='"+ResourceUtil.getUserNameAllReq()+"' " +
	    				" and (objection_flag in ('O','D'))";
	    		
	    	}else if(action.getRadio().equalsIgnoreCase("OR")){
	    		
	    		filter= " user2_dt is null and user2_name='"+ResourceUtil.getUserNameAllReq()+"' " +
	    				" and (objection_flag in ('R'))";
	    		
	    	}
	    }else if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-AC-License")){

	    	if(action.getRadio().equalsIgnoreCase("N")){
	    		
	    		filter= " user3_dt is null and user2_dt is not null and user3_name='"+ResourceUtil.getUserNameAllReq()+"' " +
	    				" and (objection_flag not in ('O','D','R') or objection_flag is null)";
	    		
	    	}else if(action.getRadio().equalsIgnoreCase("A")){
	    		
	    		filter= " user3_dt is not null and user3_name='"+ResourceUtil.getUserNameAllReq()+"' " +
	    				" and (objection_flag not in ('O','D','R') or objection_flag is null)";
	    		
	    	}
          else if(action.getRadio().equalsIgnoreCase("O")){
	    		
	    		filter= " user3_dt is  null and user3_name='"+ResourceUtil.getUserNameAllReq()+"' " +
	    				" and (objection_flag in ('O','D'))";
	    		
	    	}else if(action.getRadio().equalsIgnoreCase("OR")){
	    		
	    		filter= " user3_dt is null and user3_name='"+ResourceUtil.getUserNameAllReq()+"' " +
	    				" and (objection_flag in ('R'))";
	    		
	    	}
	    }else if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-Commissioner")){

	       if(action.getRadio().equalsIgnoreCase("N")){
	    		
	    		filter= " user4_dt is null and user3_dt is not null and user4_name='"+ResourceUtil.getUserNameAllReq()+"' " +
	    				" and (objection_flag not in ('O','D','R') or objection_flag is null)";
	    		
	    	}else if(action.getRadio().equalsIgnoreCase("A")){
	    		
	    		filter= " user4_dt is not null and user4_name='"+ResourceUtil.getUserNameAllReq()+"' " +
	    				" and (objection_flag not in ('O','D','R') or objection_flag is null)";
	    		
	    	}
	    }
	    	
		try {

			String query =
				
			" SELECT seq_pk,created_dt,star_exp_house_no, imp_exp_code ,imp_exp_cert_dt ,distillery_id,status, "+
            " (select vch_undertaking_name from public.dis_mst_pd1_pd2_lic where int_app_id_f=distillery_id) "+
            " as dist_name FROM distillery.reg_of_distilleryasexpunit where "+filter+"  ";
					
					
			 
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			
			SimpleDateFormat formatter= new SimpleDateFormat("dd MMMM yyyy");
		
			
			

			while (rs.next()) {
				
				
				
				DistilleryExportUnitForwardingDT  dt = new DistilleryExportUnitForwardingDT();
				
				dt.setSrno(sr);
				dt.setApp_no(rs.getInt("seq_pk"));
				dt.setApp_date(rs.getDate("created_dt")) ;
				dt.setExport_house_no(rs.getString("star_exp_house_no"));
				dt.setImp_exp_code(rs.getString("imp_exp_code"));
			    dt.setStatus(rs.getString("status"));
				dt.setDistillery_name(rs.getString("dist_name"))	;
				dt.setDistillery_id(rs.getInt("distillery_id"));
				sr++;
										
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
	public void getShowDetails(DistilleryExportUnitForwardingAction action) {
	
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;


	try {

		String query = 
				
	" SELECT icd_id, seq_pk, imp_exp_code, imp_exp_cert_dt, imp_exp_cert_pdf, rcmc_no, "+
	" rcmc_cert_dt, rcmc_cert_pdf, star_exp_house_no, star_exp_house_cert_dt, "+          
	" star_exp_house_cert_pdf, gstin_no, distillery_id, created_dt, user1_nm , user1_remark,user2_remark,user3_remark, user4_remark"+
	" FROM distillery.reg_of_distilleryasexpunit where" +
	" distillery_id = '"+action.getDistillery_id()+"' ";


    
		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();

		if (rs.next()) {
			
			action.setImporter_exporter_code(rs.getString("imp_exp_code"));
			action.setIcd_certificate_issue_date(rs.getDate("imp_exp_cert_dt"));
			action.setUpload1(rs.getString("imp_exp_cert_pdf"));
			action.setRegis_cum_membership_no(rs.getString("rcmc_no"));
			action.setMembership_certificate_issue_date(rs.getDate("rcmc_cert_dt"));
			action.setUpload_rcmc_2(rs.getString("rcmc_cert_pdf"));	
			action.setHouse_no(rs.getString("star_exp_house_no"));
			action.setExport_certificate_issue_date(rs.getDate("star_exp_house_cert_dt"));
			action.setUpload_certi_export_3(rs.getString("star_exp_house_cert_pdf"));
			action.setGstin_no(rs.getString("gstin_no"));	
			action.setUser1_remark(rs.getString("user1_remark"));
			action.setUser2_remark(rs.getString("user2_remark"));
			action.setUser3_remark(rs.getString("user3_remark"));
			action.setUser4_remark(rs.getString("user4_remark"));
			action.setDoc1upload(true);
			action.setDoc2upload(true);
		    action.setDoc3upload(true);
            
		}
		
		//System.out.println("-- getShowDetails===="+query);

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


}
public ArrayList Bank_detail(DistilleryExportUnitForwardingAction action ) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int bankmax_id = 0 ;
     int sr = 1 ; ;

	try {

		String query = " SELECT seq_pk, masterseq_fk, bank_nm, branch_nm, account_no, distillery_id "+
				       " FROM distillery.reg_of_distasexpunit_bank_det where distillery_id = '"+action.getDistillery_id()+"' " ;		
				

		//System.out.println("list daya=="+query);
		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			dist_exp_uni_reg_dt  dt = new dist_exp_uni_reg_dt();
			dt.setSno_r(sr);
			dt.setBank_name(rs.getString("bank_nm"));
			dt.setBranch(rs.getString("branch_nm")) ;
			dt.setAccount_no(rs.getString("account_no")) ;		
			
			sr++;
									
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

public ArrayList display_detail(DistilleryExportUnitForwardingAction action ) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
     int sr = 1 ; ;

	try {

		String query =
				
				" select a.brand_id,a.brand_name,b.package_name,b.package_id,b.code_generate_through as etin  "+
	                     " from distillery.brand_registration_20_21 a,distillery.packaging_details_20_21 b                                   "+
	                     " where a.brand_id=b.brand_id_fk and a.domain in ('Export(Other Country)'," +
	                     " 'EXP(Other Country)','EXP' ) and a.distillery_id= '"+action.getDistillery_id()+"'";

		//System.out.println("-- brand details ===="+query);
		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			dist_exp_uni_reg_dt  dt = new dist_exp_uni_reg_dt();
			dt.setDisplay_srNo(sr);
			dt.setBrand_name(rs.getString("brand_name"));
			dt.setSize(rs.getString("package_name")) ;
			dt.setEtin_no(rs.getLong("etin")) ;
			dt.setBrand_id(rs.getInt("brand_id"));
				
			sr++;
									
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

public boolean updateDetails(DistilleryExportUnitForwardingAction act){

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
		 
		 if(ResourceUtil.getUserNameAllReq().substring(0, 9).equalsIgnoreCase("Excise-DL")){
			
		       query   =    " UPDATE distillery.reg_of_distilleryasexpunit set user1_dt=?,user1_remark=?," +
		   		            " user2_name='Excise-DEC', " +
		   		            " status='Pending At Excise-DEC Since "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"' where seq_pk=?";
		
		 }
		 else if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-DEC")){
				
			   query   =    " UPDATE distillery.reg_of_distilleryasexpunit set user2_dt=?,user2_remark=?," +
			   		        " user3_name='Excise-AC-License', " +
			   		        " status='Pending At Excise-AC-License Since "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"' where seq_pk=?";
			
			 }
		 else if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-AC-License")){
				
			   query   =    " UPDATE distillery.reg_of_distilleryasexpunit set user3_dt=?,user3_remark=?," +
			   		        " user4_name='Excise-Commissioner', " +
			   		        " status='Pending At Excise-Commissioner Since "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"' where seq_pk=?";
			
			 }
		 
				pstmt = conn.prepareStatement(query);
			
				pstmt.setDate(1, Utility.convertUtilDateToSQLDate(new Date()));pstmt.setString(2, time);
				pstmt.setString(2, act.getRemark());
				pstmt.setInt(3, act.getApp_id());
								
				saveStatus = pstmt.executeUpdate();
				
				System.out.println("====save status= pi details="+saveStatus);
			
			
		
		
		
		if (saveStatus > 0) {
			conn.setAutoCommit(true);
			flg=true;
			act.setViewFlag(false);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("FORWARDED SUCCESSFULLY !", "FORWARDED SUCCESSFULLY !"));
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("NOT FORWARDED !", "NOT FORWARDED !"));
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

//==================approve============

public boolean approveimpl(DistilleryExportUnitForwardingAction act){

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
		 
		 if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-Commissioner")){
			
		       query   =    " UPDATE distillery.reg_of_distilleryasexpunit set user4_dt=?,user4_remark=?," +
		   		            " approve_flag='A', status='Application Approved By  Excise-Commissioner On Date "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+" '  " +
		   		           	" where seq_pk=?";
		
		 }
		
		 
				pstmt = conn.prepareStatement(query);
			
				pstmt.setDate(1, Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setString(2, act.getRemark());
				pstmt.setInt(3, act.getApp_id());
								
				saveStatus = pstmt.executeUpdate();
				
				System.out.println("====save status= pi details=1"+saveStatus);
			
			
		
		
		
		if (saveStatus > 0) {
			conn.setAutoCommit(true);
			flg=true;
			act.setViewFlag(false);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("APPROVED SUCCESSFULLY !", "APPROVED SUCCESSFULLY !"));
		
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
return flg;

}


public boolean rejectimpl(DistilleryExportUnitForwardingAction act){

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
		 
		 if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-Commissioner")){
			
		       query   =    " UPDATE distillery.reg_of_distilleryasexpunit set user4_dt=?,user4_remark=?," +
		   		           "  approve_flag='R', " +
		   		           " status='Application Rejcted By Excise-Commissioner On Date "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"' where seq_pk=?";
		
		 }
		
		 
				pstmt = conn.prepareStatement(query);
			
				pstmt.setDate(1, Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setString(2, act.getRemark());
				pstmt.setInt(3,act.getApp_id());
								
				saveStatus = pstmt.executeUpdate();
				
				System.out.println("====save status= pi details=1"+saveStatus);
			
			
		
		
		
		if (saveStatus > 0) {
			conn.setAutoCommit(true);
			flg=true;
			act.setViewFlag(false);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("REJECTED SUCCESSFULLY !", "REJECTED SUCCESSFULLY !"));
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("SOMETHING WENT WRONG !", "OMETHING WENT WRONG !"));
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

public String getReplies(DistilleryExportUnitForwardingAction act) {

	Connection con = null;
	PreparedStatement pstmt = null, ps2 = null;
	ResultSet rs = null;
	String path = null;
	try {
		con = ConnectionToDataBase.getConnection();

		String queryList = " SELECT  app_id, objection_id, objection_title, objection_description, "
				+ " reply_on_objection, uploaded_file, "
				+ " objection_title || '(Description:)' || objection_description as objected_for "
				+ " FROM distillery.dist_exp_order_objection  "
				+ " WHERE  app_id="
				+ act.getApp_id()
				+ "   "
				+ " AND objection_id=(SELECT max(objection_id) FROM distillery.dist_exp_order_objection "
				+ " WHERE app_id="
				+ act.getApp_id() + ")  ";

		System.out.println("reply queryy=="+queryList);
		pstmt = con.prepareStatement(queryList);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			// System.out.println("path---------------------------"+path);

			act.setPopup4ObjectedFor(rs.getString("objection_title"));
			act.setPopup4ActionTaken(rs.getString("reply_on_objection"));
			act.setPopup4objID(rs.getInt("objection_id"));
			if (rs.getString("uploaded_file") != null) {
				act.setPopup4ObjectedPdf(rs.getString("uploaded_file"));
				act.setViewpdfFlg(true);
			} else {
				act.setViewpdfFlg(false);
			}

		}

	} catch (SQLException se) {
		se.printStackTrace();
	} finally {
		try {
			if (pstmt != null)
				pstmt.close();
			if (ps2 != null)
				ps2.close();
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	return "";

}

public void agreeReplyImpl(DistilleryExportUnitForwardingAction act) {

	int saveStatus = 0;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String queryList = "";
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	String time = sdf.format(cal.getTime());

	try {
		
		
		con = ConnectionToDataBase.getConnection();
		con.setAutoCommit(false);

		queryList = " UPDATE distillery.reg_of_distilleryasexpunit SET objection_flag='A', status=? "+
					" WHERE seq_pk=?";

		pstmt = con.prepareStatement(queryList);

		saveStatus = 0;
       
        pstmt.setString(1, "Objection Viewed & ACCEPTED By "+ResourceUtil.getUserNameAllReq()+" On Date "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"");
        pstmt.setInt(2, act.getApp_id());

		// System.out.println("--------------"+queryList);

		saveStatus = pstmt.executeUpdate();

		if (saveStatus > 0) {
			
			con.commit();
			act.setViewFlag(false);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Objection Removed Successfully !", "Objection Removed Successfully !"));
			
		} else {

			
			con.rollback();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error !","Error !"));
		}
	} catch (Exception se) {
		se.printStackTrace();

	} finally {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		} catch (Exception se) {
			se.printStackTrace();
		}
	}

}

// =====================decline reply================================

public void declineReplyImpl(DistilleryExportUnitForwardingAction act) {

	int saveStatus = 0;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String queryList = "";
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	String time = sdf.format(cal.getTime());

	try {

		con = ConnectionToDataBase.getConnection();
		con.setAutoCommit(false);

		queryList = " UPDATE distillery.reg_of_distilleryasexpunit SET objection_flag='D', status=? "+
					" WHERE seq_pk=? ";

		pstmt = con.prepareStatement(queryList);

		saveStatus = 0;
         
        pstmt.setString(1, "Objection Viewed & DECLINED By "+ResourceUtil.getUserNameAllReq()+" On Date "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"");
        pstmt.setInt(2, act.getApp_id());

		// System.out.println("--------------"+queryList);

		saveStatus = pstmt.executeUpdate();
		
		
		if (saveStatus > 0) {
			saveStatus=0;
			String query=" UPDATE distillery.dist_exp_order_objection SET reply_date=null" +
					     " WHERE app_id="+act.getApp_id()+" and objected_by='"+ResourceUtil.getUserNameAllReq()+"'"; 
			
			pstmt = con.prepareStatement(query);
			saveStatus = pstmt.executeUpdate();
		} 

		if (saveStatus > 0) {
			
			con.commit();
			act.setViewFlag(false);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Objection Declined !", "Objection Declined !"));

		} else {

			
			con.rollback();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error !","Error !"));
		}
	} catch (Exception se) {
		se.printStackTrace();

	} finally {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		} catch (Exception se) {
			se.printStackTrace();
		}
	}

}

public void save_Objection(DistilleryExportUnitForwardingAction act) {

	int saveStatus = 0;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String queryList = "";

	

	try {

		con = ConnectionToDataBase.getConnection();
		con.setAutoCommit(false);
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());

		queryList = " INSERT INTO distillery.dist_exp_order_objection( "
				+ "  app_id, objection_id, objection_title, objection_description,"
				+ " objection_time, objection_date, objected_by)VALUES(?, (select coalesce(max(objection_id),0)+1  from distillery.dist_exp_order_objection), ?, ?, ?, ?, ?)";

		pstmt = con.prepareStatement(queryList);

		saveStatus = 0;

		pstmt.setInt(1, act.getApp_id());
		pstmt.setString(2, act.getObjection_for());
		pstmt.setString(3, act.getObj_Description());
		pstmt.setString(4, time);
		pstmt.setDate(5, Utility.convertUtilDateToSQLDate(new Date()));
		pstmt.setString(6, ResourceUtil.getUserNameAllReq().trim());

		// System.out.println("--------------"+queryList);

		saveStatus = pstmt.executeUpdate();

		if (saveStatus > 0) {

			saveStatus = 0;

			String queryUpdate = " UPDATE distillery.reg_of_distilleryasexpunit SET objection_flag='O', objection_dt='"+Utility.convertUtilDateToSQLDate(new Date())+"'," +
					             " objection_user='"+ResourceUtil.getUserNameAllReq()+"', status='OBJECTED BY "+ResourceUtil.getUserNameAllReq()+" !' "
					           + " WHERE seq_pk=?";

			pstmt = con.prepareStatement(queryUpdate);

			saveStatus = 0;

			pstmt.setInt(1,  act.getApp_id());

			// System.out.println("-----update-----"+queryUpdate);

			saveStatus = pstmt.executeUpdate();

		}

		if (saveStatus > 0) {
			
			con.commit();
			act.setViewFlag(false);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Objection Raised!", "Objection Raised !"));
		} else {
			

			con.rollback();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error !","Error !"));
		}
	} catch (Exception se) {
		se.printStackTrace();

	} finally {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		} catch (Exception se) {
			se.printStackTrace();
		}
	}

}

}
