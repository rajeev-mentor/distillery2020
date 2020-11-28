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
import com.mentor.Datatable.dist_exp_uni_reg_dt ;
import com.mentor.action.Dist_exp_uni_reg_action;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class dist_exp_uni_impl {
	
	
	public void getDetails(Dist_exp_uni_reg_action act){


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
     
			//////System.out.println();
			
			
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

	/*public ArrayList getIcd_list() {
		
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		try {

			String query = "SELECT id,name  FROM licence.icd_master order by id ";

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				item = new SelectItem();

				item.setValue(rs.getString("id"));
				item.setLabel(rs.getString("name"));

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

	}*/
	
public ArrayList display_detail(Dist_exp_uni_reg_action action ) {
		
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

			////System.out.println("-- brand details ===="+query);
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


public boolean savedetails(Dist_exp_uni_reg_action act){

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 0;
	String query = ""; 
   int maxid = this.seq_pk();
	
	boolean flg=false;
	 try {

		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());
		 
		
			
		query=
	
				"	INSERT INTO distillery.reg_of_distilleryasexpunit(  "+
				"	icd_id, seq_pk, imp_exp_code, imp_exp_cert_dt, imp_exp_cert_pdf," +
				"  rcmc_no, rcmc_cert_dt, rcmc_cert_pdf, star_exp_house_no, star_exp_house_cert_dt," +
				"  star_exp_house_cert_pdf, gstin_no, distillery_id, created_dt,user1_nm, status)  "+
				"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ? ) " ;
				
				
						
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1,0) ;
				pstmt.setInt(2, maxid);
				pstmt.setString(3, act.getImporter_exporter_code());
				pstmt.setDate(4, Utility.convertUtilDateToSQLDate(act.getIcd_certificate_issue_date()));
				pstmt.setString(5, act.getUpload_certificate_export_1());
				pstmt.setString(6, act.getRegis_cum_membership_no());
				pstmt.setDate(7, Utility.convertUtilDateToSQLDate (act.getMembership_certificate_issue_date()));
				pstmt.setString(8, act.getUpload_rcmc_2());
				pstmt.setString(9, act.getHouse_no());
				pstmt.setDate(10, Utility.convertUtilDateToSQLDate( act.getExport_certificate_issue_date()));
				pstmt.setString(11, act.getUpload_certi_export_3());
				pstmt.setString(12, act.getGstin_no());
				pstmt.setLong(13, act.getDistillery_id());
				pstmt.setDate(14, Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setString(15, "Excise-DL-"+act.getDistillery_id()+"");
				pstmt.setString(16, "Pending At Excise-DL Since "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+" ");
				
				saveStatus = pstmt.executeUpdate();
				
				//System.out.println("save status==1"+saveStatus);
			
			
		
		if (saveStatus > 0) {
			int maxid_bank = seq_pk_bank();
			
			query =
					"    INSERT INTO distillery.reg_of_distasexpunit_bank_det(  "+
					" 	seq_pk,  bank_nm, branch_nm, account_no, distillery_id,masterseq_fk) "+
					" 		VALUES (?, ?, ?, ?, ?,? ) " ;
				
			
			if(act.getAddRowdetailsData().size()>0){
				
				for(int i=0;i<act.getAddRowdetailsData().size();i++){
					dist_exp_uni_reg_dt dt = (dist_exp_uni_reg_dt) act.getAddRowdetailsData().get(i);
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, maxid_bank);
					pstmt.setString(2, dt.getBank_name());
					pstmt.setString(3, dt.getBranch());
					pstmt.setString(4, dt.getAccount_no());
					pstmt.setLong(5, act.getDistillery_id());
					pstmt.setLong(6, maxid);
					
					saveStatus = pstmt.executeUpdate();
					
					//System.out.println("--");
					maxid_bank++ ;
				 //System.out.println("save bank details status==2="+saveStatus);
					
				}
		}
		}       
		if (saveStatus > 0) {
			conn.setAutoCommit(true);
			flg=true;
			act.setSaveflag(true);
			act.setUpload_flag(true);
			act.flag_bank_detail = "false";  
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("SAVED SUCCESSFULLY !", "SAVED SUCCESSFULLY !"));
			act.reset();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("NOT SAVED !", "NOT SAVED !"));
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

//============================================max id =======================================
public int seq_pk() {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = " SELECT max(seq_pk) as id FROM distillery.reg_of_distilleryasexpunit";
	int maxid = 0;
	try {
		con = ConnectionToDataBase.getConnection();
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			maxid = rs.getInt("id");
		}
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e
						.getMessage(), e.getMessage()));

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
	
	
	
	return maxid + 1;

}


public int seq_pk_bank() {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = " SELECT max(seq_pk) as id FROM distillery.reg_of_distasexpunit_bank_det";
	int maxid = 0;
	try {
		con = ConnectionToDataBase.getConnection();
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			maxid = rs.getInt("id");
		}
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e
						.getMessage(), e.getMessage()));

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
	
	
	
	return maxid + 1;

}

public void getShowDetails(Dist_exp_uni_reg_action action) {
	
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;


	try {

		String query = 
				
	" SELECT coalesce(objection_flag,'NA') as objection_flag ,coalesce(status,'Pending at AEC') as status,icd_id, seq_pk, imp_exp_code, imp_exp_cert_dt, imp_exp_cert_pdf, rcmc_no, "+
	" rcmc_cert_dt, rcmc_cert_pdf, star_exp_house_no, star_exp_house_cert_dt, "+          
	" star_exp_house_cert_pdf, gstin_no, distillery_id, created_dt, user1_nm "+
	" FROM distillery.reg_of_distilleryasexpunit where" +
	" distillery_id = '"+action.getDistillery_id()+"' ";



		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();

		if (rs.next()) {
			
			
			if(rs.getString("status")!=null)
			{
				action.setStatus(rs.getString("status"));
				
			}else{
				action.setStatus("");
			}
			
			if(rs.getString("objection_flag").equals("O"))
			{
				action.setObjection_reply_button(true);
			}else{
				action.setObjection_reply_button(false);
			}
			
			
			action.setApp_id(rs.getInt("seq_pk"));
			action.setImporter_exporter_code(rs.getString("imp_exp_code"));
			action.setIcd_certificate_issue_date(rs.getDate("imp_exp_cert_dt"));
			action.setUpload_certificate_export_1(rs.getString("imp_exp_cert_pdf"));
			action.setRegis_cum_membership_no(rs.getString("rcmc_no"));
			action.setMembership_certificate_issue_date(rs.getDate("rcmc_cert_dt"));
			action.setUpload_rcmc_2(rs.getString("rcmc_cert_pdf"));	
			action.setHouse_no(rs.getString("star_exp_house_no"));
			action.setExport_certificate_issue_date(rs.getDate("star_exp_house_cert_dt"));
			action.setUpload_certi_export_3(rs.getString("star_exp_house_cert_pdf"));
			action.setGstin_no(rs.getString("gstin_no"));	
			action.setDoc1upload(true);
			action.setDoc2upload(true);
			action.setDoc3upload(true);
			
         
			if(rs.getString("objection_flag").equals("O"))
			{
				action.setSaveflag(true);
				action.setUpdate_flag(true);
				action.setUpload_flag(false);
			
			}else if(rs.getString("objection_flag").equals("R")){
				action.setSaveflag(true);
				action.setUpdate_flag(false);
				action.setUpload_flag(true);
				 
				

			}else{
				
				action.setSaveflag(true);
				action.setUpload_flag(true);
			}
			
			
			
			
		}
		
		////System.out.println("-- getShowDetails===="+query);

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




public ArrayList Bank_detail(Dist_exp_uni_reg_action action ) {
	
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
			dt.setBank_pk(rs.getInt("seq_pk"));
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

//===========================update ==============================================

public boolean update(Dist_exp_uni_reg_action act){

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 0;
	String query = ""; 
 
	
	boolean flg=false;
	 try {

		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());
		 
		
		query=
	
				"   update distillery.reg_of_distilleryasexpunit set   "+
                "    rcmc_no = ?, rcmc_cert_dt = ?, rcmc_cert_pdf = ?, "+
                 "   star_exp_house_no = ?, star_exp_house_cert_dt = ?, "+
                "    star_exp_house_cert_pdf = ?, gstin_no = ? ,        "+
                "    imp_exp_code = ? , imp_exp_cert_dt = ? , imp_exp_cert_pdf = ?  "+
               "     where  distillery_id = ?  " ;

					
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, act.getRegis_cum_membership_no());
				pstmt.setDate(2, Utility.convertUtilDateToSQLDate (act.getMembership_certificate_issue_date()));
				pstmt.setString(3, act.getUpload_rcmc_2());
				pstmt.setString(4, act.getHouse_no());
				pstmt.setDate(5, Utility.convertUtilDateToSQLDate( act.getExport_certificate_issue_date()));
				pstmt.setString(6, act.getUpload_certi_export_3());
				pstmt.setString(7, act.getGstin_no());
				pstmt.setString(8, act.getImporter_exporter_code());
				pstmt.setDate(9, Utility.convertUtilDateToSQLDate(act.getIcd_certificate_issue_date()));
				pstmt.setString(10, act.getUpload_certificate_export_1());

				pstmt.setLong(11, act.getDistillery_id());				
				
				
				saveStatus = pstmt.executeUpdate();
				
				//System.out.println("save status=  update =1"+saveStatus);
			
			
		
		if (saveStatus > 0) {
		
			
			query =
					" update distillery.reg_of_distasexpunit_bank_det "+
					" set  bank_nm = ? , branch_nm = ?,   "+
					" account_no = ? where distillery_id = ? and seq_pk = ? " ;
				
			
			if(act.getAddRowdetailsData().size()>0){
				
				for(int i=0;i<act.getAddRowdetailsData().size();i++){
					dist_exp_uni_reg_dt dt = (dist_exp_uni_reg_dt) act.getAddRowdetailsData().get(i);
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, dt.getBank_name());
					pstmt.setString(2, dt.getBranch());
					pstmt.setString(3, dt.getAccount_no());
					pstmt.setLong(4, act.getDistillery_id());
					pstmt.setInt(5, dt.getBank_pk());
					
					
					saveStatus = pstmt.executeUpdate();
//System.out.println("- -dt.getBank_pk()---"+dt.getBank_pk()+"--"+dt.getBank_name()+"--"+dt.getBranch()+dt.getAccount_no());
			
			
			//System.out.println("---UPDATED BANK-----"+saveStatus);
					
				}
		}
		}       
		if (saveStatus > 0) {
			conn.setAutoCommit(true);
			flg=true;
			act.setSaveflag(true);
			act.flag_bank_detail = "false" ;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("UPDATED SUCCESSFULLY !", "UPDATED SUCCESSFULLY !"));
			act.reset();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("NOT UPDATED !", "NOT UPDATED !"));
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
