package com.mentor.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import com.mentor.Datatable.LiquorVehicleAccidentalCaseBWFLDT;
import com.mentor.action.LiquorVehicleAccidentalCaseBWFLAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class LiquorVehicleAccidentalCaseBWFLImpl {
	
	
	public void  getDetails(LiquorVehicleAccidentalCaseBWFLAction act) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " select districtid, description from district d where deo='"+ResourceUtil.getUserNameAllReq()+"' ";
		int maxid = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				act.setDistrict_id(rs.getInt("districtid"));
				act.setAccident_district_name(rs.getString("description"));
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
		

	}
	
	public boolean SaveImpl(LiquorVehicleAccidentalCaseBWFLAction act){

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 1;
	String query = ""; 
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	String time = sdf.format(cal.getTime());
	int max  = this.maxId();
	boolean flg=false;
	 try {

		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		
		
		String insert   =" INSERT INTO bwfl_license.liquor_accidental_case_mst "+
                        " (int_id, vch_gatepass_no, int_bwfl_id, district_id, created_date, vch_to_lic_no, licensee_name, licensee_adrs, "+
                        " vch_root_details, vch_vehicle_no, vehicle_driver_name, vehicle_agency_name_adrs, valid_up_to, inpection_pdf,  req_id, vch_from)"+
                         " VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (select vch_from from bwfl_license.gatepass_to_districtwholesale_bwfl_20_21 where vch_gatepass_no='"+act.getGatepass_no()+"'))";




		pstmt = conn.prepareStatement(insert);
	
	    pstmt.setInt(1, max);
	    pstmt.setString(2, act.getGatepass_no());
	    pstmt.setInt(3, act.getDistillery_id());
	    pstmt.setInt(4,act.getDistrict_id());
	    pstmt.setDate(5,Utility.convertUtilDateToSQLDate(new Date()));
	    pstmt.setString(6, act.getLicense_no());
	    pstmt.setString(7, act.getLicensee_name());
	    pstmt.setString(8, act.getLicensee_add());
	    pstmt.setString(9, act.getRoute_detail());
	    pstmt.setString(10, act.getVehicle_no());
	    pstmt.setString(11, act.getDriver_name());
	    pstmt.setString(12, act.getAgency_name());
	    pstmt.setDate(13, Utility.convertUtilDateToSQLDate(act.getValidity_date()));
	    pstmt.setString(14, act.getPdf_name());
	    pstmt.setInt(15, act.getReq_id());
						
		saveStatus = pstmt.executeUpdate();
		
		if(saveStatus>0){
			saveStatus = 0;
		for (int i = 0; i < act.getBrand_list().size(); i++) {
			

			LiquorVehicleAccidentalCaseBWFLDT dt = (LiquorVehicleAccidentalCaseBWFLDT) act
					.getBrand_list().get(i);
			
		       query   =    " INSERT INTO bwfl_license.liquor_accidental_case "+
                            " (int_id,  int_bwfl_id, vch_gatepass_no, curr_dt, district_id, int_brand_id, int_pack_id, damage_box, damage_bottle, " +
                            " return_box, return_bottle, prcced_dispatch_box, prcced_dispatch_bottle, vch_batch_no, size, vch_lic_no) "+
                            " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
		
		
		
		 
				pstmt = conn.prepareStatement(query);
			
			    pstmt.setInt(1, max);
			    pstmt.setInt(2, act.getDistillery_id());
			    pstmt.setString(3,act.getGatepass_no());
			    pstmt.setDate(4,Utility.convertUtilDateToSQLDate(new Date()));
			    pstmt.setInt(5, act.getDistrict_id());
			    pstmt.setInt(6, dt.getInt_brand_id());
			    pstmt.setInt(7, dt.getInt_pack_id());
			    pstmt.setInt(8, dt.getDamage_box());
			    pstmt.setInt(9, dt.getDamage_bottle());
			    pstmt.setInt(10, dt.getReturn_box());
			    pstmt.setInt(11, dt.getReturn_bottle());
			    pstmt.setInt(12, dt.getPrcced_dispatch_box());
			    pstmt.setInt(13, dt.getPrcced_dispatch_bottle());
			    pstmt.setString(14, dt.getBatch_no());
			    pstmt.setInt(15, dt.getBox_size());
			    pstmt.setString(16, dt.getVch_lic_no());
			    
								
				saveStatus = pstmt.executeUpdate();
				//System.out.println("====save status= pi details=1"+saveStatus);
			
		}
		
		if(saveStatus > 0){
			saveStatus = 0;
			
			String update="Update bwfl_license.request_for_accidental_case set save_flag='A' where gatepass_no='"+act.getGatepass_no()+"'";
			
			pstmt = conn.prepareStatement(update);
			saveStatus = pstmt.executeUpdate();
		}
		
		}
		
		if (saveStatus > 0) {
			conn.setAutoCommit(true);
			flg=true;
		    act.setViewflag(false);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("SAVED SUCCESSFULLY !", "SAVED SUCCESSFULLY !"));
			
		} else {
			 act.setViewflag(true);
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
	
public ArrayList display_detail(LiquorVehicleAccidentalCaseBWFLAction act) {
		
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	     int sr = 1 ; 
	     String query ="";
	     String filter="";
   if(act.getRadio().equalsIgnoreCase("N")){
	   filter=" and a.save_flag is null ";
   }else  if(act.getRadio().equalsIgnoreCase("S")){
	   filter=" and a.save_flag is not null ";
   }
		try {
			if(act.getRadio().equalsIgnoreCase("N")){
				 query = 
							
						   " SELECT a.req_id, gatepass_type, gatepass_no, 'N' as vch_finalize,gatepass_date, accident_address,b.vch_firm_name, " +
						   " '' as vch_from, accident_district, accident_date, a.created_date, a.bwfl_id as int_bwfl_id,'NA' as  verify_flg, '' as  inpection_pdf"+
		                   " FROM bwfl_license.request_for_accidental_case a,bwfl.registration_of_bwfl_lic_holder_20_21 b " +
		                   " where  a.bwfl_id = b.int_id and "+
		                   " accident_district = "+act.getDistrict_id()+"   "+filter+"";
				 
			 }else if(act.getRadio().equalsIgnoreCase("S")){
			 query = 
					
				   " SELECT a.req_id, gatepass_type, gatepass_no, gatepass_date,coalesce(b.vch_finalize,'N') as vch_finalize, accident_address," +
				   " accident_district, accident_date, a.created_date, a.bwfl_id,coalesce(b.verify_flg,'NA') as  verify_flg, b.inpection_pdf,"+
                  "  c.vch_firm_name, b.int_bwfl_id, b.vch_from " +
                  "  FROM bwfl_license.request_for_accidental_case a, " +
                  " bwfl_license.liquor_accidental_case_mst b, bwfl.registration_of_bwfl_lic_holder_20_21 c where "+
                  " accident_district = "+act.getDistrict_id()+" and  b.int_bwfl_id = c.int_id and gatepass_no=b.vch_gatepass_no "+filter+"";
					
			 }
			System.out.println("-- dell details ===="+query);
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				LiquorVehicleAccidentalCaseBWFLDT  dt = new LiquorVehicleAccidentalCaseBWFLDT();
				dt.setReq_id(rs.getInt("req_id"));
				dt.setGatepass_type(rs.getString("gatepass_type"));
				dt.setSrno(sr);
				dt.setGatepass_no(rs.getString("gatepass_no"));
				dt.setGatepass_date(rs.getDate("gatepass_date"));
				dt.setAccident_address(rs.getString("accident_address"));
				dt.setAccident_date(rs.getDate("accident_date"));
				dt.setReq_dt(rs.getDate("created_date"));	
				dt.setDistillery_name(rs.getString("vch_firm_name"));
				dt.setDistillery_id(rs.getInt("int_bwfl_id"));
				if(rs.getString("verify_flg").equalsIgnoreCase("V")){
					dt.setVerify_flg(true);
				}else{
					dt.setVerify_flg(false);
				}
				
				dt.setInpection_pdf(rs.getString("inpection_pdf"));
				dt.setVch_finalize(rs.getString("vch_finalize"));
				dt.setVch_from(rs.getString("vch_from"));
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


public ArrayList gatepass_detail(LiquorVehicleAccidentalCaseBWFLAction act) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
     int sr = 1 ; ;
     String query = "";

	try {
		 query =  "select vch_gatepass_no ,curr_date ,vch_to , vch_to_lic_no,licensee_name, licensee_adrs, vch_route_detail, vch_vehicle_no , " +
		 		  "  vehicle_driver_name ,vehicle_agency_name_adrs as agency from bwfl_license.gatepass_to_districtwholesale_bwfl_20_21 where vch_gatepass_no='"+act.getGatepass_no()+"'";
		
			
		System.out.println("-- brand details ===="+query);
		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();

		if (rs.next()) {
			
			act.setLicense_no(rs.getString("vch_to_lic_no"));
			act.setLicensee_name(rs.getString("licensee_name"));
			act.setLicensee_add(rs.getString("licensee_adrs"));
			act.setRoute_detail(rs.getString("vch_route_detail"));
			act.setVehicle_no(rs.getString("vch_vehicle_no"));
			act.setDriver_name(rs.getString("vehicle_driver_name"));
			act.setAgency_name(rs.getString("agency"));
			

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


public ArrayList brand_detail(LiquorVehicleAccidentalCaseBWFLAction act) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
     int sr = 1 ; 
     String query ="";

	try {
        	  query = " select br.brand_name , a.vch_lic_no,pk.package_name , a.size, a.dispatch_box,a.dispatch_bottle,a.vch_batch_no," +
        	  		  " int_brand_id , int_pckg_id  from bwfl_license.fl2_stock_trxn_bwfl_20_21 a, distillery.brand_registration_20_21 br, "+
                      " distillery.packaging_details_20_21 pk where a.int_brand_id=br.brand_id and pk.brand_id_fk = br.brand_id and " +
                      " a.int_pckg_id = pk.package_id and a.vch_gatepass_no='"+act.getGatepass_no()+"'";
      
          
		
				
			
		System.out.println("-- brand details ===="+query);
		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			
			LiquorVehicleAccidentalCaseBWFLDT  dt = new LiquorVehicleAccidentalCaseBWFLDT();
            dt.setSr2(sr);
			dt.setBrand_name(rs.getString("brand_name"));
			dt.setSize(rs.getString("package_name"));
			dt.setBox_size(rs.getInt("size"));
			dt.setDispatch_box(rs.getInt("dispatch_box"));
			dt.setDispatch_bottle(rs.getInt("dispatch_bottle"));
			dt.setInt_brand_id(rs.getInt("int_brand_id"));
			dt.setInt_pack_id(rs.getInt("int_pckg_id"));
			dt.setBatch_no(rs.getString("vch_batch_no"));
			dt.setVch_lic_no(rs.getString("vch_lic_no"));
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

public boolean printReturnReport(LiquorVehicleAccidentalCaseBWFLAction action) {
	String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
	String relativePath = mypath + File.separator + "ExciseUp"
			+ File.separator + "WholeSale" + File.separator + "jasper";
	String relativePathpdf = mypath + File.separator + "ExciseUp"
			+ File.separator + "WholeSale" + File.separator + "pdf";
	JasperReport jasperReport = null;
	JasperPrint jasperPrint = null;
	PreparedStatement pst = null;
	Connection con = null;
	ResultSet rs = null;
	String reportQuery = null;
	boolean printFlag = false;
	String filter="";
	
	
	try {
		con = ConnectionToDataBase.getConnection();
		
		
		reportQuery="select a.return_route_detail as route_details, a.return_vehicle_no as vehicle_no ," +
				"  a.return_driver_name as driver_name, a.return_agency_name as agency, " +
				" return_validity_date as  valid_upto," +
				" return_created_date as curr_date,a.vch_gatepass_no, br.brand_name ,pk.package_name,pk.quantity ,b.return_box , b.return_bottle  ,b.vch_batch_no ,br.strength ,"+
                " (((b.return_bottle)*pk.quantity)/1000) as bl, ((((b.return_bottle*pk.quantity)/1000)*br.strength)/100) as al," +
                " (select vch_firm_name from bwfl.registration_of_bwfl_lic_holder_20_21  where int_id = a.int_bwfl_id) as dist_name "+
                " from bwfl_license.liquor_accidental_case_mst a,bwfl_license.liquor_accidental_case b, distillery.brand_registration_20_21 br ,distillery.packaging_details_20_21 pk "+
                " where b.int_brand_id = br.brand_id and b.int_pack_id = pk.package_id and br.brand_id = pk.brand_id_fk and and a.vch_gatepass_no =b.vch_gatepass_no  " +
                " and a.vch_gatepass_no='"+action.getGatepass_no()+"'";
	  
		System.out.println("report query==="+reportQuery);
		pst = con.prepareStatement(reportQuery);
	

		rs = pst.executeQuery();
		if (rs.next()) {
			rs = pst.executeQuery();

			Map parameters = new HashMap();
			parameters.put("REPORT_CONNECTION", con);
			parameters.put("SUBREPORT_DIR", relativePath + File.separator);
			parameters.put("image", relativePath + File.separator);

			JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
			
			jasperReport = (JasperReport) JRLoader.loadObject(relativePath
					+ File.separator
					+ "GatepassDistrictWholesaleBWFL_return.jasper");
			
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					parameters, jrRs);
			Random rand = new Random();
			int n = rand.nextInt(250) + 1;

			JasperExportManager.exportReportToPdfFile(print, relativePathpdf+ File.separator + action.getGatepass_no()  +"_return.pdf");
			action.setPrintGatePassNo(action.getGatepass_no() + "_return.pdf");
			action.setPrintFlag(true);
			printFlag = true;
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("No Data Found", "No Data Found"));
			action.setPrintFlag(false);
			printFlag = false;
		}

	} catch (JRException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	return printFlag;
}

public boolean printReport(LiquorVehicleAccidentalCaseBWFLAction action, LiquorVehicleAccidentalCaseBWFLDT dt) {
	String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
	String relativePath = mypath + File.separator + "ExciseUp"
			+ File.separator + "WholeSale" + File.separator + "jasper";
	String relativePathpdf = mypath + File.separator + "ExciseUp"
			+ File.separator + "WholeSale" + File.separator + "pdf";
	JasperReport jasperReport = null;
	JasperPrint jasperPrint = null;
	PreparedStatement pst = null;
	Connection con = null;
	ResultSet rs = null;
	String reportQuery = null;
	boolean printFlag = false;
	String filter="";
	
	
	try {
		con = ConnectionToDataBase.getConnection();
		
		reportQuery="select distinct a.vch_gatepass_no ,a.created_date ,a.licensee_name ,a.licensee_adrs ,a.valid_up_to ,a.vch_root_details ,a.vch_to_lic_no ,a.vehicle_agency_name_adrs , "+
                  " a.vehicle_driver_name,a.vch_vehicle_no ,b.prcced_dispatch_box , b.prcced_dispatch_bottle,b.vch_batch_no ,                                                              "+
                  " (((b.prcced_dispatch_bottle)*pk.quantity)/1000) as bl, ((((b.prcced_dispatch_bottle*pk.quantity)/1000)*br.strength)/100) as al, br.strength ,                          "+
                  " (select vch_firm_name from bwfl.registration_of_bwfl_lic_holder_20_21  where int_id = a.int_bwfl_id) as vch_undertaking_name,                                          "+     
                  " pk.quantity as ml, br.brand_name ,a.vch_from                                                                                                                           "+
                  " from                                                                                                                                                                   "+
                  " bwfl_license.liquor_accidental_case_mst a, bwfl_license.liquor_accidental_case b,                                                                                      "+
                  " distillery.brand_registration_20_21 br, distillery.packaging_details_20_21 pk                                                                                          "+
                  " where a.vch_gatepass_no = b.vch_gatepass_no                                                                                                                            "+
                  " and b.int_brand_id = br.brand_id and b.int_pack_id = pk.package_id and br.brand_id = pk.brand_id_fk and a.vch_gatepass_no ='"+action.getGatepass_no()+"'  ";

	  
		System.out.println("report query==="+reportQuery);
		pst = con.prepareStatement(reportQuery);
	

		rs = pst.executeQuery();
		if (rs.next()) {
			rs = pst.executeQuery();

			Map parameters = new HashMap();
			parameters.put("REPORT_CONNECTION", con);
			parameters.put("SUBREPORT_DIR", relativePath + File.separator);
			parameters.put("image", relativePath + File.separator);
			parameters.put("loginName",action.getName());

			JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
			
			jasperReport = (JasperReport) JRLoader.loadObject(relativePath
					+ File.separator
					+ "GatepassDistrictWholesaleBWFL_new.jasper");
			
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					parameters, jrRs);
			Random rand = new Random();
			int n = rand.nextInt(250) + 1;

			JasperExportManager.exportReportToPdfFile(print, relativePathpdf+ File.separator + dt.getGatepass_no() +"_new.pdf");
			dt.setPdfname(dt.getGatepass_no() + "_new.pdf");
			action.setPrintFlag(true);
			printFlag = true;
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("No Data Found", "No Data Found"));
			action.setPrintFlag(false);
			printFlag = false;
		}

	} catch (JRException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	return printFlag;
}
public int maxId() {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = " SELECT coalesce(max(int_id),0) as id FROM bwfl_license.liquor_accidental_case ";
	int maxid = 0;
	try {
		con = ConnectionToDataBase.getConnection();
		pstmt = con.prepareStatement(query);
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
	return maxid + 1;

}


public boolean UpadteReturnImpl(LiquorVehicleAccidentalCaseBWFLAction act){

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 1;
	String query = ""; 
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	String time = sdf.format(cal.getTime());
	int max  = this.maxId();
	boolean flg=false;
	 try {

		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		
		
		String insert   =" update bwfl_license.liquor_accidental_case_mst set "+
                        " return_route_detail=?, return_vehicle_no=?,return_driver_name=?, return_agency_name=?, return_validity_date=?," +
                        " return_created_date=? where vch_gatepass_no=?";




		       pstmt = conn.prepareStatement(insert);
		       pstmt.setString(1, act.getReturn_route_detail());
		       pstmt.setString(2, act.getReturn_vehicle_no());
		       pstmt.setString(3, act.getReturn_driver_name());
		       pstmt.setString(4, act.getReturn_agency_name());
		       pstmt.setDate(5, Utility.convertUtilDateToSQLDate(act.getReturn_validity_date()));
		       pstmt.setDate(6, Utility.convertUtilDateToSQLDate(new Date()));
			   pstmt.setString(7, act.getGatepass_no());			
		       saveStatus = pstmt.executeUpdate();
		
		
		
		if (saveStatus > 0) {
			conn.setAutoCommit(true);
			flg=true;
		    
			
			
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
public void updateData(LiquorVehicleAccidentalCaseBWFLAction action) {
	Connection con = null;
	PreparedStatement stmt = null;
   String query="";
	
	
		query = " UPDATE bottling_unmapped.bwfl SET fl36_date=null WHERE fl36gatepass=? ";
		

	     //System.out.println("delete query-----------"+query);

	 int saveStatus=0;
	try {
		con = ConnectionToDataBase.getConnection20_21();
		stmt = con.prepareStatement(query);
		stmt.setString(1, action.getGatepass_no());
		saveStatus=stmt.executeUpdate();
		System.out.println("saveStatus-----------"+saveStatus);
	} catch (Exception e) {
		e.printStackTrace();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(e.getMessage(), e.getMessage()));
	}

	finally {
		try {
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		}
	}

}


public void saveCSV(LiquorVehicleAccidentalCaseBWFLAction action) throws IOException {
	Connection con = null;
	PreparedStatement stmt = null;
	String query = "";
    
	 query = " INSERT INTO bwfl_license.fl36_dispatch_casecode_fl2d_20_21(vch_gatepass_no, vch_casecode)VALUES (?, ?) ";
	

	String gatepass = "";
	int status = 0, flag = 0;
	BufferedReader bReader = new BufferedReader(new FileReader(action.getCsvFilepath()));
	// String line = "";
	try {
		con = ConnectionToDataBase.getConnection();
		stmt = con.prepareStatement(query);
		// ArrayList ar = new ArrayList();

		String line = "";
		StringTokenizer st = null;
		int lineNumber = 0;
		int tokenNumber = 0;
		while ((line = bReader.readLine()) != null) {
			lineNumber++;
			String casecode = "";
			st = new StringTokenizer(line, " ");
			while (st.hasMoreTokens()) {
				String sd = st.nextToken() + "  ";

				if (sd != null) {
					if (lineNumber == 1) {

						gatepass = sd.trim();
					}

					else
					{  
						if (gatepass.trim().equalsIgnoreCase(action.getGatepass_no().trim()+"-Accidental")) {
							casecode = sd;

							if(this.etin(casecode.trim().substring(0, 12),casecode.trim().substring(23, 26), action)==true){
								FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
										" Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!" ," Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!"));	
							}else{ 

								stmt.setString(1, gatepass.trim());
								//stmt.setString(2, casecode.trim());

								if(casecode.trim().length()==35)
								{
									stmt.setString(2, casecode.trim());
								}else
								{
									FacesContext.getCurrentInstance().addMessage(null,
											new FacesMessage("Invalid Length Of Casecode"+casecode.trim(), "Invalid Length Of Casecode"+casecode.trim()));
									break;

								}

								stmt.addBatch();
							}//status = stmt.executeUpdate();
						} else {
							flag = 1;
						}
					}

				}

				tokenNumber = 0;
			}
		}

		if (flag == 0) {
			status=stmt.executeBatch().length;
			if (status > 0) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"File Uploaded Successfully ","File Uploaded Successfully "));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"File Not Uploaded!!", "File Not Uploaded!!"));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Kindly Enter Same Gatepass Number !! ","Kindly Enter Same Gatepass Number !! "));
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (stmt != null)
				stmt.close();

			if (con != null)
				con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}



}

public boolean  etin(String casecode,String size, LiquorVehicleAccidentalCaseBWFLAction act) {

	Connection con = null;
	PreparedStatement pstmt = null, ps2 = null;
	ResultSet rs = null, rs2 = null;
	boolean s = false;
	try {
		con = ConnectionToDataBase.getConnection();

			String query = " SELECT distinct b.code_generate_through FROM bwfl_license.liquor_accidental_case a, distillery.packaging_details_20_21 b "
					       + " WHERE b.package_id = a.int_pack_id  and a.vch_gatepass_no='"+ act.getGatepass_no()+ "' and b.code_generate_through='"+casecode+"'";
      
		pstmt = con.prepareStatement(query);

		System.out.println("query-----etin------"+query);

		rs = pstmt.executeQuery();
		if (rs.next()) {


		}else{

			s=true;
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
			if (rs2 != null)
				rs2.close();
			if (con != null)
				con.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	return s;

}


public ArrayList getExcelData(LiquorVehicleAccidentalCaseBWFLAction action) {
	ArrayList list = new ArrayList();

	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	int boxingCount=0;
	int listSize=0;
	String query ="";
	
	 query = 	" SELECT vch_gatepass_no, vch_casecode FROM bwfl_license.fl36_dispatch_casecode_fl2d_20_21 "
				+ " WHERE vch_gatepass_no='"
				+ action.getScanGatePassNo()
				+ "' ";
	

	System.out.println("========query========"+query);
	try {
		con = ConnectionToDataBase.getConnection();
		stmt = con.prepareStatement(query);
		rs = stmt.executeQuery();
		// Date d=Utility.convertSqlDateToUtilDate(date_created);
		while (rs.next()) {
			LiquorVehicleAccidentalCaseBWFLDT dt = new LiquorVehicleAccidentalCaseBWFLDT();

			String datenew=rs.getString("vch_casecode").substring(16,22).trim();

			datenew="20"+datenew;
			datenew	=datenew.substring(0,4)+"-"+datenew.substring(4,6)+"-"+datenew.substring(6);

			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(datenew);

			action.setValFlag(true);
			dt.setGatepass(rs.getString("vch_gatepass_no"));
			dt.setCasecode(rs.getString("vch_casecode"));
			//boolean flag=getCascodeMatch(rs.getString("casecode").substring(29,rs.getString("casecode").length()),rs.getString("casecode").substring(2, 15));

			boolean flag=getCascodeMatch(date1,rs.getString("vch_casecode").substring(26),rs.getString("vch_casecode").substring(0, 12),action);
			if(flag)
			{
				++listSize;
				dt.setCascodeMatching("Valid");
			}else{
				++boxingCount;
				action.setBottlingNotDoneFlag(true);
				dt.setCascodeMatching("Invalid");
			}

			list.add(dt);

		}
		if(boxingCount!=0 || listSize<=0 )
		{
			action.setBottlingNotDoneFlag(true);
		}else
		{
			action.setBottlingNotDoneFlag(false);
		}
		
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(e.getMessage(), e.getMessage()));
	}

	finally {
		try {
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		}
	}

	return list;
}


public boolean getCascodeMatch(Date date1, String casecode,String etin, LiquorVehicleAccidentalCaseBWFLAction action)
{
	System.out.println("boxing-------"+action.getVch_from());
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	boolean flag=false;
	
	String sql= "";
	sql="select * from bottling_unmapped.bwfl WHERE casecode='"+casecode+"' and etin='"+etin+"' and fl11_date is not null and " +
			" date_plan='"+Utility.convertUtilDateToSQLDate(date1)+"' " +
					//"and boxing_seq is not null" +
					" and fl36_date is  null ";
	System.out.println("sql-----boxing--------"+sql);

	try{
		conn=ConnectionToDataBase.getConnection20_21();
		pstmt=conn.prepareStatement(sql);


		//pstmt.setString(1, casecode.trim());
		//pstmt.setString(2, etin.trim());
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			flag=true;
		}

	}
	catch(Exception e)
	{
		e.printStackTrace();	
	}
	finally{
		try{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();



		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	return flag;
}


public int excelCases(LiquorVehicleAccidentalCaseBWFLAction act) {
	int id = 0;
	Connection con = null;
	PreparedStatement pstmt = null, ps2 = null;
	ResultSet rs = null, rs2 = null;
	String query = "";
	try {
		con = ConnectionToDataBase.getConnection();

	
		
			 query = 	" SELECT count(*) as dispatchd_box FROM bwfl_license.fl36_dispatch_casecode_fl2d_20_21 "
						+ " WHERE vch_gatepass_no='"
						+ act.getScanGatePassNo().trim() + "'";

			
			 System.out.println("excel box=="+query);
		pstmt = con.prepareStatement(query);

		rs = pstmt.executeQuery();

		while (rs.next()) {

			id = (rs.getInt("dispatchd_box"));

		}

	} catch (SQLException se) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(se.getMessage(), se.getMessage()));
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
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(se.getMessage(), se.getMessage()));
		}
	}
	return id;

}

public int recieveCases(LiquorVehicleAccidentalCaseBWFLAction act) {
	int id = 0;
	Connection con = null;
	PreparedStatement pstmt = null, ps2 = null;
	ResultSet rs = null, rs2 = null;
	String query = "";

	try {
		con = ConnectionToDataBase.getConnection();

		
			 query = "  SELECT SUM(prcced_dispatch_box) as dispatchd_box FROM bwfl_license.liquor_accidental_case "
						+ " WHERE vch_gatepass_no='"
						+ act.getGatepass_no() + "'  ";
	     

			 System.out.println("no of box=="+query);
		pstmt = con.prepareStatement(query);

		rs = pstmt.executeQuery();

		while (rs.next()) {

			id = (rs.getInt("dispatchd_box"));

		}

	} catch (SQLException se) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(se.getMessage(), se.getMessage()));
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
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(se.getMessage(), se.getMessage()));
		}
	}
	return id;

}

public void deleteData(LiquorVehicleAccidentalCaseBWFLAction action) {
	Connection con = null;
	PreparedStatement stmt = null;
   String delQr="";
	
	
			delQr = " DELETE FROM bwfl_license.fl36_dispatch_casecode_fl2d_20_21 WHERE vch_gatepass_no ='"
					+ action.getScanGatePassNo() + "' ";
        


	//System.out.println("delete query-----------"+query);

	int status = 0;
	try {
		con = ConnectionToDataBase.getConnection();
		stmt = con.prepareStatement(delQr);
		status = stmt.executeUpdate();
		if (status > 0) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cancelled Successfully","Cancelled Successfully"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Not Cancelled","Not Cancelled"));
		}
	} catch (Exception e) {
		e.printStackTrace();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(e.getMessage(), e.getMessage()));
	}

	finally {
		try {
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		}
	}

}

public boolean updateFL3(LiquorVehicleAccidentalCaseBWFLAction act) {

	int save = 0;
	//int j = 0;
	boolean val = false;
	PreparedStatement ps = null;
	Connection con = null;
	Connection con1 = null;
	String query = "";		
	/*query = " INSERT INTO bottling_unmapped.bwfl (fl36gatepass, plan_id, date_plan,etin,fl36_date,casecode) " +
			" values (?,?,?,?,?,?) "+
			" ON CONFLICT (etin, casecode) DO UPDATE SET fl36gatepass=?, fl36_date=?  where bottling_unmapped.bwfl.fl36_date is null ";
*/	
	query = " UPDATE bottling_unmapped.bwfl SET  fl36gatepass=?, "
			+ " fl36_date=? WHERE casecode=? and fl36_date is null and fl11_date is not   null and etin=? " +
			"and  date_plan=?";
	Date date1=null;
	try {	
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");			
		int j[] = null;
		con = ConnectionToDataBase.getConnection();
		con1 = ConnectionToDataBase.getConnection20_21();
		con.setAutoCommit(false);
		con1.setAutoCommit(false);
		ps = con1.prepareStatement(query);
		for (int i = 0; i < act.getGetVal().size(); i++) {
			LiquorVehicleAccidentalCaseBWFLDT dt = (LiquorVehicleAccidentalCaseBWFLDT) act.getGetVal().get(i);
			
			String datePlan=dt.getCasecode().trim().substring(16,22).trim();
		 datePlan="20"+datePlan;
				datePlan=datePlan.substring(0,4)+"-"+datePlan.substring(4,6)+"-"+datePlan.substring(6);
				 date1=new SimpleDateFormat("yyyy-MM-dd").parse(datePlan);
				//////System.out.println(datePlan+"="+date_plan);
				
				ps.setString(1, dt.getGatepass());
				ps.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));
				ps.setString(3,dt.getCasecode().trim().substring(26,dt.getCasecode().length()));
			
				ps.setString(4,dt.getCasecode().trim().substring(0,12));
				ps.setDate(5,Utility.convertUtilDateToSQLDate(date1));
			
			/* ps.setString(1, dt.getGatepass().trim());
			 ps.setInt(2, 0);
			 ps.setDate(3, Utility.convertUtilDateToSQLDate(new Date()));
			 ps.setString(4,(dt.getCasecode().substring(0, 12)));
			 ps.setDate(5, Utility.convertUtilDateToSQLDate(new Date()));
			 ps.setString(6,dt.getCasecode().substring(26,dt.getCasecode().length()));
			 ps.setString(7, dt.getGatepass().trim());
			 ps.setDate(8, Utility.convertUtilDateToSQLDate(new Date()));*/

			ps.addBatch();
			
			//////System.out.println("save-------"+save +"dfgdfghd------------"+query);
			
		}
		j = ps.executeBatch();
		 
		save = j.length;
		
	
		if (act.getGetVal().size() == save && save>0) {
			save = 0;

			String updtQr = " UPDATE bwfl_license.liquor_accidental_case_mst SET vch_finalize='F', finalize_dt_time=? "
					+ " WHERE vch_gatepass_no='"
					+ act.getGatepass_no()
					+ "' ";

			ps = con.prepareStatement(updtQr);
			ps.setString(1, dateFormat.format(new Date()));

			 //////System.out.println("updtQr------------" + updtQr);
			save = ps.executeUpdate();

			 //////System.out.println("second status------------" + save);

			query = " DELETE FROM bwfl_license.fl36_dispatch_casecode_fl2d_20_21 WHERE vch_gatepass_no ='"
					+ act.getScanGatePassNo() + "' ";
			ps = con.prepareStatement(query);
			ps.executeUpdate();

			 //////System.out.println("----   delete query   ---------"+query);

		} else {
			save = 0;
		}
		if (save > 0) {
			val = true;
			con.commit();
			con1.commit();
		} else {
			val = false;
			con.rollback();
			con1.rollback();
		}

	} catch (Exception ex) {
		ex.printStackTrace();
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
	} finally {
		try {
			if (ps != null)
				ps.close();

			if (con != null)
				con.close();
			con1.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return val;

}

public boolean etin_check(String g, LiquorVehicleAccidentalCaseBWFLAction act) {
	
	Connection c1 = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String q1="",q2="";
	
	Map<String, String> m1 = new HashMap<String, String>();
	Map<String, String> m2 = new HashMap<String, String>();

	
	if(act.getGatepass_type().equalsIgnoreCase("CL")){
	 q1 = "select count(a.etin), a.etin from" +
			" (select substring(casecode,1,12) as etin from distillery.distillery_gatepass_casecode_cl" +
			" where gatespass='"+g+"')a group by a.etin order by a.etin;";
	
	 q2 = "select b.f*count(b.code_generate_through) as count, b.code_generate_through as etin from" +
			" (select a.f, (select code_generate_through from distillery.packaging_details_20_21 where package_id=a.p) from" +
			" (select dispatchd_box as f,int_pckg_id as p  from distillery.cl2_stock_trxn_20_21" +
			" where vch_gatepass_no = '"+g+"') a)b group by b.code_generate_through, b.f order by etin";
	
	}else if(act.getGatepass_type().equalsIgnoreCase("FL3/3A")){
		q1 = "select count(a.etin), a.etin from" +
				" (select substring(vch_casecode,1,12) as etin from distillery.fl11_dislery_casecode_20_21" +
				" where vch_gatepass_no='"+g+"')a group by a.etin order by a.etin;";
		
		 q2 = "select b.f*count(b.code_generate_through) as count, b.code_generate_through as etin from" +
				" (select a.f, (select code_generate_through from distillery.packaging_details_20_21 where package_id=a.p) from" +
				" (select dispatchd_box as f,int_pckg_id as p  from distillery.fl1_stock_trxn_20_21" +
				" where vch_gatepass_no = '"+g+"') a)b group by b.code_generate_through, b.f order by etin";
	}else if(act.getGatepass_type().equalsIgnoreCase("FL1/1A")){
		q1 = "select count(a.etin), a.etin from" +
				" (select substring(casecode,1,12) as etin from distillery.distillery_gatepass_casecode" +
				" where gatespass='"+g+"')a group by a.etin order by a.etin;";
		
		 q2 = "select b.f*count(b.code_generate_through) as count, b.code_generate_through as etin from" +
				" (select a.f, (select code_generate_through from distillery.packaging_details_20_21 where package_id=a.p) from" +
				" (select dispatchd_box as f,int_pckg_id as p  from distillery.fl2_stock_trxn_20_21" +
				" where vch_gatepass_no = '"+g+"') a)b group by b.code_generate_through, b.f order by etin";
	}
	try {
		c1 = ConnectionToDataBase.getConnection();
		
		ps = c1.prepareStatement(q1);
		rs = ps.executeQuery();		
		while (rs.next()) {
			m1.put(rs.getString("etin"), rs.getString("count"));
		//	System.out.println(rs.getString("etin")+","+ rs.getString("count"));
			
		}
		
		ps = c1.prepareStatement(q2);
		rs = ps.executeQuery();		
		while (rs.next()) {
			m2.put(rs.getString("etin"), rs.getString("count"));
		//	System.out.println(rs.getString("etin")+","+ rs.getString("count"));
			
		}
		
	
		
		if(m1.equals(m2)){
			//System.out.println("Result: "+ m1.equals(m2));
			return true;
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (c1 != null)
				c1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return false;

}

}
