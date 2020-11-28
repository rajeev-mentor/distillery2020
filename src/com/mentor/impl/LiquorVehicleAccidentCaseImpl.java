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

import com.mentor.Datatable.LiquorVehicleAccidentCaseDT;
import com.mentor.Datatable.LiquorVehicleAccidentCaseDT;
import com.mentor.action.ClGatepass_20_21_active_action;
import com.mentor.action.GatepassToDistrictWholesale_20_21_active_action;
import com.mentor.action.LiquorVehicleAccidentCaseAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class LiquorVehicleAccidentCaseImpl {
	
	
	public void  getDetails(LiquorVehicleAccidentCaseAction act) {

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
	
	public boolean SaveImpl(LiquorVehicleAccidentCaseAction act){

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
		
		
		String insert   =" INSERT INTO distillery.liquor_accidental_case_mst "+
                        " (int_id, vch_gatepass_no, distillery_id, district_id, created_date, vch_to_lic_no, licensee_name, licensee_adrs, "+
                        " vch_root_details, vch_vehicle_no, vehicle_driver_name, vehicle_agency_name_adrs, valid_up_to, inpection_pdf,  req_id)"+
                         " VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";




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
			

			LiquorVehicleAccidentCaseDT dt = (LiquorVehicleAccidentCaseDT) act
					.getBrand_list().get(i);
			
		       query   =    " INSERT INTO distillery.liquor_accidental_case "+
                            " (int_id,  distillery_id, vch_gatepass_no, curr_dt, district_id, int_brand_id, int_pack_id, damage_box, damage_bottle, " +
                            " return_box, return_bottle, prcced_dispatch_box, prcced_dispatch_bottle, vch_batch_no) "+
                            " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
		
		
		
		 
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
								
				saveStatus = pstmt.executeUpdate();
				//System.out.println("====save status= pi details=1"+saveStatus);
			
		}
		
		if(saveStatus > 0){
			saveStatus = 0;
			
			String update="Update distillery.request_for_accidental_case set save_flag='A' where gatepass_no='"+act.getGatepass_no()+"'";
			
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
	
public ArrayList display_detail(LiquorVehicleAccidentCaseAction act) {
		
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
							
						   " SELECT a.req_id, gatepass_type, gatepass_no, 'N' as vch_finalize,gatepass_date, accident_address, accident_district, accident_date, a.created_date, a.distillery_id,'NA' as  verify_flg, '' as  inpection_pdf,"+
		                   " (select vch_undertaking_name  from dis_mst_pd1_pd2_lic where int_app_id_f = a.distillery_id) as dis_name," +
		                   " case when gatepass_type='FL1' then                                                                                    "+
                           " (select vch_from  from distillery.gatepass_to_districtwholesale_20_21 where vch_gatepass_no=a.gatepass_no )      "+
                           " when  gatepass_type='FL3' then                                                                                       "+
                           " (select vch_from  from distillery.gatepass_to_manufacturewholesale_20_21 where vch_gatepass_no=a.gatepass_no )   "+
                           " when  gatepass_type='CL' then                                                                                       "+
                           " (select vch_from  from distillery.gatepass_to_manufacturewholesale_cl_20_21 where vch_gatepass_no=a.gatepass_no ) end as vch_from " +
                           " ,0 as total_bottle FROM distillery.request_for_accidental_case a " +
		                   " where "+
		                   " accident_district = "+act.getDistrict_id()+"   "+filter+"";
				 
			 }else if(act.getRadio().equalsIgnoreCase("S")){
			 query = 
					
				   " SELECT a.req_id, gatepass_type, gatepass_no, gatepass_date,coalesce(b.vch_finalize,'N') as vch_finalize, accident_address, accident_district, accident_date, a.created_date, a.distillery_id,coalesce(b.verify_flg,'NA') as  verify_flg, b.inpection_pdf,"+
                   " (select vch_undertaking_name  from dis_mst_pd1_pd2_lic where int_app_id_f = a.distillery_id) as dis_name ," +
                   " case when gatepass_type='FL1' then                                                                                    "+
                   " (select vch_from  from distillery.gatepass_to_districtwholesale_20_21 where vch_gatepass_no=b.vch_gatepass_no )      "+
                   " when  gatepass_type='FL3' then                                                                                       "+
                   " (select vch_from  from distillery.gatepass_to_manufacturewholesale_20_21 where vch_gatepass_no=b.vch_gatepass_no )   "+
                   "  when  gatepass_type='CL' then                                                                                       "+
                   " (select vch_from  from distillery.gatepass_to_manufacturewholesale_cl_20_21 where vch_gatepass_no=b.vch_gatepass_no ) end as vch_from," +
                   " (select sum(prcced_dispatch_bottle) from distillery.liquor_accidental_case where vch_gatepass_no = b.vch_gatepass_no ) as total_bottle " +
                   " FROM distillery.request_for_accidental_case a, " +
                   " distillery.liquor_accidental_case_mst b where "+
                   " accident_district = "+act.getDistrict_id()+" and  gatepass_no=b.vch_gatepass_no "+filter+"";
					
			 }
			System.out.println("-- dell details ===="+query);
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				LiquorVehicleAccidentCaseDT  dt = new LiquorVehicleAccidentCaseDT();
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
				dt.setAccident_date(rs.getDate("accident_date"));
				dt.setReq_dt(rs.getDate("created_date"));	
				dt.setDistillery_name(rs.getString("dis_name"));
				dt.setDistillery_id(rs.getInt("distillery_id"));
				if(rs.getString("verify_flg").equalsIgnoreCase("V")){
					dt.setVerify_flg(true);
				}else{
					dt.setVerify_flg(false);
				}
				
				dt.setInpection_pdf(rs.getString("inpection_pdf"));
				dt.setVch_finalize(rs.getString("vch_finalize"));
				dt.setVch_from(rs.getString("vch_from"));
				dt.setTotal_bottles(rs.getLong("total_bottle"));
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


public ArrayList gatepass_detail(LiquorVehicleAccidentCaseAction act) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
     int sr = 1 ; ;
     String query = "";

	try {
		if(act.getGatepass_type().equalsIgnoreCase("FL1/1A")){
		 query =  "select vch_gatepass_no ,curr_date ,vch_to , vch_to_lic_no,licensee_name, licensee_adrs, vch_route_detail, vch_vehicle_no , " +
		 		  "  vehicle_driver_name ,vehicle_agency_name_adrs as agency from distillery.gatepass_to_districtwholesale_20_21 where vch_gatepass_no='"+act.getGatepass_no()+"'";
		}else if(act.getGatepass_type().equalsIgnoreCase("FL3/3A")){
			query =  "select vch_gatepass_no ,curr_date ,vch_to , vch_to_lic_no, '' as licensee_name, '' as licensee_adrs, vch_root_details as" +
					" vch_route_detail , vch_vehicle_no , vehicle_driver_name ,vehicle_agency_and_address as agency from " +
					"distillery.gatepass_to_manufacturewholesale_20_21 where vch_gatepass_no='"+act.getGatepass_no()+"'";
		}else if(act.getGatepass_type().equalsIgnoreCase("CL")){
			query =  "select vch_gatepass_no ,curr_date ,vch_to , vch_to_lic_no,licensee_name, licensee_adrs, vch_root_details as vch_route_detail," +
					" vch_vehicle_no , vehicle_driver_name ,vehicle_agency_name_adrs as agency from distillery.gatepass_to_manufacturewholesale_cl_20_21" +
					" where vch_gatepass_no='"+act.getGatepass_no()+"'";
		}
			
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


public ArrayList brand_detail(LiquorVehicleAccidentCaseAction act) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
     int sr = 1 ; 
     String query ="";

	try {
          if(act.getGatepass_type().equalsIgnoreCase("FL1/1A")){
        	  query = " select br.brand_name , pk.package_name , a.size, a.dispatch_box,a.dispatch_bottle,a.vch_batch_no," +
        	  		  " int_brand_id , int_pckg_id  from distillery.fl2_stock_trxn_20_21 a, distillery.brand_registration_20_21 br, "+
                      " distillery.packaging_details_20_21 pk where a.int_brand_id=br.brand_id and pk.brand_id_fk = br.brand_id and " +
                      " a.int_pckg_id = pk.package_id and a.vch_gatepass_no='"+act.getGatepass_no()+"'";
          }else  if(act.getGatepass_type().equalsIgnoreCase("FL3/3A")){
        	  query = " select br.brand_name , pk.package_name , a.size, a.dispatchd_box as dispatch_box,a.dispatchd_bottl as dispatch_bottle," +
        	  		  " int_brand_id , int_pckg_id, batch_no as vch_batch_no from distillery.fl1_stock_trxn_20_21 a, distillery.brand_registration_20_21 br, "+
                      " distillery.packaging_details_20_21 pk where a.int_brand_id=br.brand_id and pk.brand_id_fk = br.brand_id and " +
                      " a.int_pckg_id = pk.package_id and a.vch_gatepass_no='"+act.getGatepass_no()+"'";
        	  
          }else  if(act.getGatepass_type().equalsIgnoreCase("CL")){
        	  query = " select br.brand_name , pk.package_name , a.size, a.dispatchd_box as dispatch_box,a.dispatchd_bottl as dispatch_bottle, " +
        	  		  " int_brand_id , int_pckg_id, vch_batch_no from distillery.cl2_stock_trxn_20_21 a, distillery.brand_registration_20_21 br, "+
                      " distillery.packaging_details_20_21 pk where a.int_brand_id=br.brand_id and pk.brand_id_fk = br.brand_id and " +
                      " a.int_pckg_id = pk.package_id and a.vch_gatepass_no='"+act.getGatepass_no()+"'";
        	  
          }
          
		
				
			
		System.out.println("-- brand details ===="+query);
		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			
			LiquorVehicleAccidentCaseDT  dt = new LiquorVehicleAccidentCaseDT();
            dt.setSr2(sr);
			dt.setBrand_name(rs.getString("brand_name"));
			dt.setSize(rs.getString("package_name"));
			dt.setBox_size(rs.getInt("size"));
			dt.setDispatch_box(rs.getInt("dispatch_box"));
			dt.setDispatch_bottle(rs.getInt("dispatch_bottle"));
			dt.setInt_brand_id(rs.getInt("int_brand_id"));
			dt.setInt_pack_id(rs.getInt("int_pckg_id"));
			dt.setBatch_no(rs.getString("vch_batch_no"));
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

public boolean printReturnReport(LiquorVehicleAccidentCaseAction action) {
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
		
		
		reportQuery="select '"+action.getReturn_route_detail()+"' as route_details, '"+action.getReturn_vehicle_no()+"' as vehicle_no ," +
				" '"+action.getReturn_driver_name()+"' as driver_name, '"+action.getReturn_agency_name()+"' as agency, " +
				" '"+Utility.convertUtilDateToSQLDate(action.getReturn_validity_date())+"'::date as valid_upto," +
				" '"+Utility.convertUtilDateToSQLDate(new Date())+"'::date as curr_date,a.vch_gatepass_no, br.brand_name ,pk.package_name,pk.quantity ,a.return_box , a.return_bottle  ,a.vch_batch_no ,br.strength ,"+
                " (((a.return_bottle)*pk.quantity)/1000) as bl, ((((a.return_bottle*pk.quantity)/1000)*br.strength)/100) as al," +
               "(select vch_undertaking_name from dis_mst_pd1_pd2_lic where int_app_id_f=a.distillery_id) as dist_name "+
             " from distillery.liquor_accidental_case a, distillery.brand_registration_20_21 br ,distillery.packaging_details_20_21 pk "+
              " where a.int_brand_id = br.brand_id and a.int_pack_id = pk.package_id and br.brand_id = pk.brand_id_fk and a.vch_gatepass_no='"+action.getGatepass_no()+"'";
	  
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
					+ "GatepassDistrictWholesaleprint_return.jasper");
			
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

public boolean printReport(LiquorVehicleAccidentCaseAction action, LiquorVehicleAccidentCaseDT dt) {
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
	
	if(dt.getGatepass_type().equalsIgnoreCase("FL1/1A")){
		filter = "(select vch_from from  distillery.gatepass_to_districtwholesale_20_21 where vch_gatepass_no=a.vch_gatepass_no ) as vch_from";
	}else if(dt.getGatepass_type().equalsIgnoreCase("CL")){
		filter = "(select vch_from from  distillery.gatepass_to_manufacturewholesale_cl_20_21 where vch_gatepass_no=a.vch_gatepass_no ) as vch_from";
	}
	try {
		con = ConnectionToDataBase.getConnection();
	   if(dt.getGatepass_type().equalsIgnoreCase("FL1/1A") || dt.getGatepass_type().equalsIgnoreCase("CL")){
		reportQuery="select distinct a.vch_gatepass_no ,a.created_date ,a.licensee_name ,a.licensee_adrs ,a.valid_up_to ,a.vch_root_details ,a.vch_to_lic_no ,a.vehicle_agency_name_adrs ,"+
          " a.vehicle_driver_name,a.vch_vehicle_no ,b.prcced_dispatch_box , b.prcced_dispatch_bottle,b.vch_batch_no , g.indent_no,                                               "+
          " (((b.prcced_dispatch_bottle)*f.qnt_ml_detail)/1000) as bl, ((((b.prcced_dispatch_bottle*f.qnt_ml_detail)/1000)*br.strength)/100) as al, br.strength ,                "+
          " (select vch_undertaking_name from dis_mst_pd1_pd2_lic where int_app_id_f = a.distillery_id) as vch_undertaking_name,                                                 "+
          " f.qnt_ml_detail as ml, br.brand_name ,"+filter+"    "+
          " from                                                                                                                                                                 "+
          " distillery.liquor_accidental_case_mst a, distillery.liquor_accidental_case b,                                                                                        "+
          " distillery.brand_registration_20_21 br, distillery.packaging_details_20_21 pk, distillery.box_size_details f,distillery.wholesale_indent_gatepass g                  "+
          " where a.vch_gatepass_no = b.vch_gatepass_no and a.vch_gatepass_no = g.vch_gatepass and f.qnt_ml_detail=pk.quantity and pk.box_id=f.box_id                            "+
          " and b.int_brand_id = br.brand_id and b.int_pack_id = pk.package_id and br.brand_id = pk.brand_id_fk and a.vch_gatepass_no ='"+dt.getGatepass_no()+"'                       "+
          " and a.int_id = b.int_id and b.vch_gatepass_no = g.vch_gatepass and b.int_brand_id = g.brand_id and b.int_pack_id = g.package_id";
	   }else{
		   
		   reportQuery="SELECT a.int_dist_id, a.vch_distillary_name,b.prcced_dispatch_box , a.vch_distillary_address,                                            "+
               " a.vch_to, a.vch_from_lic_no,a.vch_from,mst.valid_up_to ,a.vch_auth_name, a.export_lic_no, a.mode_of_transport, a.bond_value, a.export_district, "+
               " mst.vch_gatepass_no,mst.created_date ,a.db_total_duty,a. db_total_additional_duty,                                                              "+
               " mst.vch_root_details ,mst.vch_vehicle_no,                                                                                                       "+
               "  b.int_brand_id,b.prcced_dispatch_bottle as no_bottl,                                                                                           "+
               " c.brand_id, c.brand_name, c.strength,a.valid_till, b.vch_batch_no as batch_no,                                                                  "+
               " d.package_name,d.box_id,                                                                                                                        "+
               " e.qnt_ml_detail as ml,(select vch_undertaking_name from dis_mst_pd1_pd2_lic where int_app_id_f = mst.distillery_id) as vch_unit_name ,          "+
               " (((b.prcced_dispatch_bottle)*e.qnt_ml_detail)/1000) as bl, ((((b.prcced_dispatch_bottle*e.qnt_ml_detail)/1000)*c.strength)/100) as al           "+
               " FROM distillery.gatepass_to_manufacturewholesale_20_21 a ,                                                                                      "+
               " distillery.liquor_accidental_case b ,distillery.brand_registration_20_21  c,                                                                    "+
               " distillery.packaging_details_20_21 d,                                                                                                           "+
               " distillery.box_size_details e,  distillery.liquor_accidental_case_mst mst                                                                       "+
               " where a.int_dist_id=b.distillery_id   and a.vch_gatepass_no=b.vch_gatepass_no                                                                   "+
               " and c.brand_id=d.brand_id_fk   and b.int_brand_id=c.brand_id                                                                                    "+
               " and c.brand_id=d.brand_id_fk and b.int_brand_id=d.brand_id_fk   and b.int_pack_id =d.package_id                                                 "+
               " and d.box_id=e.box_id  and e.qnt_ml_detail=d.quantity           "+                                                                               
               " AND                                                      "+                                                                                      
               " mst.vch_gatepass_no =a.vch_gatepass_no and mst.vch_gatepass_no = '"+dt.getGatepass_no()+"'  ";                                                          

              }
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
			if(dt.getGatepass_type().equalsIgnoreCase("FL1/1A")){
			jasperReport = (JasperReport) JRLoader.loadObject(relativePath
					+ File.separator
					+ "GatepassDistrictWholesaleprint_fl36.jasper");
			}else if(dt.getGatepass_type().equalsIgnoreCase("FL3/3A")){
				jasperReport = (JasperReport) JRLoader.loadObject(relativePath
						+ File.separator
						+ "GatepassDistrictWholesaleprint_fl11.jasper");
			}else if(dt.getGatepass_type().equalsIgnoreCase("CL")){
					jasperReport = (JasperReport) JRLoader.loadObject(relativePath
							+ File.separator
							+ "GatepassDistrictWholesaleprint_cl.jasper");
			}
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
	String query = " SELECT coalesce(max(int_id),0) as id FROM distillery.liquor_accidental_case ";
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


public boolean UpadteReturnImpl(LiquorVehicleAccidentCaseAction act){

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
		
		
		String insert   =" update distillery.liquor_accidental_case_mst set "+
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
public void updateData(LiquorVehicleAccidentCaseAction action) {
	Connection con = null;
	PreparedStatement stmt = null;
   String query="";
	
	
   if (action.getVch_from().equalsIgnoreCase("CL")) {
		query = " UPDATE bottling_unmapped.disliry_unmap_cl SET fl36_date=null WHERE fl36gatepass=? ";
		}else
		if (action.getVch_from().equalsIgnoreCase("FL1")) {
			query = "UPDATE bottling_unmapped.disliry_unmap_fl3 SET fl36_date=null WHERE fl36gatepass=? ";
		} else if (action.getVch_from().equalsIgnoreCase("FL1A")) {
			
			query = "UPDATE bottling_unmapped.disliry_unmap_fl3a SET fl36_date=null WHERE fl36gatepass=? ";
		}
		
		if (action.getVch_from().equalsIgnoreCase("FL3")) {
			query = " UPDATE bottling_unmapped.disliry_unmap_fl3 SET  "
					+ " fl11_date=null WHERE fl11gatepass=? ";

		} else if (action.getVch_from().equalsIgnoreCase("FL3A")) {
			query = " UPDATE bottling_unmapped.disliry_unmap_fl3a SET "
					+ " fl11_date=null WHERE fl11gatepass=? ";
		}


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


public void saveCSV(LiquorVehicleAccidentCaseAction action) throws IOException {
	Connection con = null;
	PreparedStatement stmt = null;
	String query = "";
    
	if(action.getGatepass_type().equalsIgnoreCase("CL")){
	 query = "INSERT INTO distillery.distillery_gatepass_casecode_cl(gatespass, casecode)VALUES (?, ?)";
	}else if(action.getGatepass_type().equalsIgnoreCase("FL3/3A")){
	 query = "INSERT INTO distillery.fl11_dislery_casecode_20_21(vch_gatepass_no, vch_casecode)VALUES (?, ?)";
	}else if(action.getGatepass_type().equalsIgnoreCase("Fl1/1A")){
	 query = " INSERT INTO distillery.distillery_gatepass_casecode(gatespass, casecode)VALUES (?, ?) ";
	}

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
						if (gatepass.trim().equalsIgnoreCase(action.getScanGatePassNo())) {
							casecode = sd;

							if(this.etin(casecode.trim().substring(0, 12), action)==true){
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

public boolean  etin(String casecode,LiquorVehicleAccidentCaseAction act) {

	Connection con = null;
	PreparedStatement pstmt = null, ps2 = null;
	ResultSet rs = null, rs2 = null;
	boolean s = false;
	String query ="";
	try {
		con = ConnectionToDataBase.getConnection();

      if(act.getGatepass_type().equalsIgnoreCase("CL")){
		 query = " SELECT distinct b.code_generate_through FROM distillery.cl2_stock_trxn_20_21 a, distillery.packaging_details_20_21 b "
				+ " WHERE b.package_id = a.int_pckg_id  and a.vch_gatepass_no='"+ act.getScanGatePassNo().trim()+ "' and b.code_generate_through='"+casecode+"'";
      }else if(act.getGatepass_type().equalsIgnoreCase("FL3/3A")){
    	  query = " SELECT distinct b.code_generate_through FROM distillery.fl1_stock_trxn_20_21 a, distillery.packaging_details_20_21 b "
					+ " WHERE b.package_id = a.int_pckg_id  and a.vch_gatepass_no='"+ act.getScanGatePassNo().trim()+ "' and" +
							" b.code_generate_through='"+casecode+"' ";
      }else if(act.getGatepass_type().equalsIgnoreCase("FL1/1A")){
    	  query = " SELECT distinct b.code_generate_through FROM distillery.fl2_stock_trxn_20_21 a, distillery.packaging_details_20_21 b "
					+ " WHERE b.package_id = a.int_pckg_id  and a.vch_gatepass_no='"+ act.getScanGatePassNo().trim()+ "' and b.code_generate_through='"+casecode+"'";
      }
		pstmt = con.prepareStatement(query);

		//System.out.println("query-----etin------"+query);

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


public ArrayList getExcelData(LiquorVehicleAccidentCaseAction action) {
	ArrayList list = new ArrayList();

	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	int boxingCount=0;
	int listSize=0;
	String query ="";
	if(action.getGatepass_type().equalsIgnoreCase("CL")){
	 query = 	" SELECT gatespass,casecode FROM distillery.distillery_gatepass_casecode_cl " +
			" WHERE gatespass ='"+ action.getGatepass_no().trim() + "'";
	}else if(action.getGatepass_type().equalsIgnoreCase("FL3/3A")){
		query = "SELECT vch_gatepass_no as gatespass,vch_casecode as casecode FROM distillery.fl11_dislery_casecode_20_21 where vch_gatepass_no='"
				+ action.getScanGatePassNo().trim() + "' order by vch_casecode";
	}else if(action.getGatepass_type().equalsIgnoreCase("FL1/1A")){
		query = 	"SELECT distinct  d.gatespass,d.casecode " +
				" FROM  distillery.distillery_gatepass_casecode d " +
				" WHERE   d.gatespass='"+ action.getScanGatePassNo() + "'";
	}

	System.out.println("========query========"+query);
	try {
		con = ConnectionToDataBase.getConnection();
		stmt = con.prepareStatement(query);
		rs = stmt.executeQuery();
		// Date d=Utility.convertSqlDateToUtilDate(date_created);
		while (rs.next()) {
			LiquorVehicleAccidentCaseDT dt = new LiquorVehicleAccidentCaseDT();

			String datenew=rs.getString("casecode").substring(16,22).trim();

			datenew="20"+datenew;
			datenew	=datenew.substring(0,4)+"-"+datenew.substring(4,6)+"-"+datenew.substring(6);

			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(datenew);

			action.setValFlag(true);
			dt.setGatepass(rs.getString("gatespass"));
			dt.setCasecode(rs.getString("casecode"));
			//boolean flag=getCascodeMatch(rs.getString("casecode").substring(29,rs.getString("casecode").length()),rs.getString("casecode").substring(2, 15));

			boolean flag=getCascodeMatch(date1,rs.getString("casecode").substring(26),rs.getString("casecode").substring(0, 12),action);
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


public boolean getCascodeMatch(Date date1, String casecode,String etin, LiquorVehicleAccidentCaseAction action)
{
	System.out.println("boxing-------"+action.getVch_from());
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	boolean flag=false;
	
	String sql= "";
     if(action.getVch_from().equalsIgnoreCase("CL")){
	 sql= " SELECT * from bottling_unmapped.disliry_unmap_cl  " +
			" WHERE casecode='"+casecode.trim()+"' AND etin='"+etin.trim()+"' AND date_plan='"+Utility.convertUtilDateToSQLDate(date1)+"'  " +
			" AND fl36_date IS NULL AND boxing_seq IS NOT NULL ";
     }else if(action.getVch_from().equalsIgnoreCase("FL3")){
    	 sql="select * from bottling_unmapped.disliry_unmap_fl3  WHERE casecode=? and etin=? and fl11_date is   null and " +
 				" date_plan='"+Utility.convertUtilDateToSQLDate(date1)+"' and " +
 				" fl36_date is  null and boxing_seq is not null  ";
     }else if(action.getVch_from().equalsIgnoreCase("FL3A")){
    	   sql=     "select * from bottling_unmapped.disliry_unmap_fl3a  WHERE casecode=? and etin=? and fl11_date is   null and " +
		     	" date_plan='"+Utility.convertUtilDateToSQLDate(date1)+"' and " +
			    " fl36_date is  null and boxing_seq is not null ";
     }else if(action.getVch_from().equalsIgnoreCase("FL1")){
    	 sql = "select * from bottling_unmapped.disliry_unmap_fl3  WHERE casecode=? and etin=? and fl11_date is not null " +
    	 		" and fl36_date is  null and date_plan='"+Utility.convertUtilDateToSQLDate(date1)+"'" ;
     }else if(action.getVch_from().equalsIgnoreCase("FL1A")){
    	 sql= "select * from bottling_unmapped.disliry_unmap_fl3a  WHERE casecode=? and etin=? and fl11_date is not null " +
    	 		" and fl36_date is  null and date_plan='"+Utility.convertUtilDateToSQLDate(date1)+"'";
     }

	System.out.println("sql-----boxing--------"+sql);

	try{
		conn=ConnectionToDataBase.getConnection20_21();
		pstmt=conn.prepareStatement(sql);


		pstmt.setString(1, casecode.trim());
		pstmt.setString(2, etin.trim());
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


public int excelCases(LiquorVehicleAccidentCaseAction act) {
	int id = 0;
	Connection con = null;
	PreparedStatement pstmt = null, ps2 = null;
	ResultSet rs = null, rs2 = null;
	String query = "";
	try {
		con = ConnectionToDataBase.getConnection();

	
		
		if(act.getGatepass_type().equalsIgnoreCase("CL")){
			 query = 	" SELECT count(*) as dispatchd_box FROM distillery.distillery_gatepass_casecode_cl " +
					" WHERE gatespass ='"+ act.getGatepass_no().trim() + "'";
			}else if(act.getGatepass_type().equalsIgnoreCase("FL3/3A")){
				query = "SELECT count(*) as dispatchd_box FROM distillery.fl11_dislery_casecode_20_21 where vch_gatepass_no='"
						+ act.getScanGatePassNo().trim() + "' ";
			}else if(act.getGatepass_type().equalsIgnoreCase("FL1/1A")){
				query = 	"SELECT count(*) as dispatchd_box " +
						" FROM  distillery.distillery_gatepass_casecode d " +
						" WHERE   d.gatespass='"+ act.getScanGatePassNo() + "'";
			}

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

public int recieveCases(LiquorVehicleAccidentCaseAction act) {
	int id = 0;
	Connection con = null;
	PreparedStatement pstmt = null, ps2 = null;
	ResultSet rs = null, rs2 = null;
	String query = "";

	try {
		con = ConnectionToDataBase.getConnection();

		
		if(act.getGatepass_type().equalsIgnoreCase("CL")){
			 query = " SELECT SUM(dispatchd_box) as dispatch_box FROM distillery.cl2_stock_trxn_20_21 a, distillery.packaging_details_20_21 b "
					+ " WHERE b.package_id = a.int_pckg_id  and a.vch_gatepass_no='"+ act.getScanGatePassNo().trim()+ "'";
	      }else if(act.getGatepass_type().equalsIgnoreCase("FL3/3A")){
	    	  query = " SELECT SUM(dispatchd_box) as dispatch_box FROM distillery.fl1_stock_trxn_20_21 a, distillery.packaging_details_20_21 b "
						+ " WHERE b.package_id = a.int_pckg_id  and a.vch_gatepass_no='"+ act.getScanGatePassNo().trim()+ "'";
	      }else if(act.getGatepass_type().equalsIgnoreCase("FL1/1A")){
	    	  query = " SELECT SUM(dispatch_box) as dispatch_box FROM distillery.fl2_stock_trxn_20_21 a, distillery.packaging_details_20_21 b "
						+ " WHERE b.package_id = a.int_pckg_id  and a.vch_gatepass_no='"+ act.getScanGatePassNo().trim()+ "'";
	      }

		
		System.out.println("recieveCases===="+query);
		pstmt = con.prepareStatement(query);

		rs = pstmt.executeQuery();

		while (rs.next()) {

			id = (rs.getInt("dispatch_box"));

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

public void deleteData(LiquorVehicleAccidentCaseAction action) {
	Connection con = null;
	PreparedStatement stmt = null;
   String delQr="";
	
	
	 if(action.getGatepass_type().equalsIgnoreCase("CL")){
			delQr = " DELETE FROM distillery.distillery_gatepass_casecode_cl " +
					" WHERE gatespass='"+ action.getScanGatePassNo().trim() + "' ";
        }else if(action.getGatepass_type().equalsIgnoreCase("FL3/3A")){
			delQr = " DELETE FROM distillery.fl11_dislery_casecode_20_21 " +
					" WHERE vch_gatepass_no='"+ action.getScanGatePassNo().trim() + "' ";
        } else if(action.getGatepass_type().equalsIgnoreCase("FL1/1A")){
			delQr = " DELETE FROM distillery.distillery_gatepass_casecode " +
					" WHERE gatespass='"+ action.getScanGatePassNo().trim() + "' ";
        }


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

public boolean updateFL3(LiquorVehicleAccidentCaseAction act) {
	int save = 0;
	int save1 = 0;
	boolean val = false;
	PreparedStatement ps = null;
	Connection con = null;
	Connection con1 = null;
	String query = "";
	String datenew="";
	String delQr = "";
	Date date1=null;
	
	if (!etin_check(act.getScanGatePassNo(), act)) {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"Number of SKUs in gatepass and uploaded file do not match.",
								"Number of SKUs in gatepass and uploaded file do not match."));

		return false;
	}

	if (act.getVch_from().equalsIgnoreCase("CL")) {
	query = " UPDATE bottling_unmapped.disliry_unmap_cl SET  fl36gatepass=?, fl36_date=?  WHERE casecode=? " +
			" AND etin=? AND date_plan=? AND fl36_date IS NULL AND boxing_seq IS NOT NULL  ";
	}else
	if (act.getVch_from().equalsIgnoreCase("FL1")) {
		query = "UPDATE bottling_unmapped.disliry_unmap_fl3 SET  fl36gatepass=?, fl36_date=? WHERE casecode=? and fl11_date is not null and fl36_date is null and etin=? and date_plan=?";
	} else if (act.getVch_from().equalsIgnoreCase("FL1A")) {
		
		query = "UPDATE bottling_unmapped.disliry_unmap_fl3a SET  fl36gatepass=?, fl36_date=? WHERE casecode=? and fl11_date is not null and fl36_date is null and etin=? and date_plan=?";
	}
	
	if (act.getVch_from().equalsIgnoreCase("FL3")) {
		query = " UPDATE bottling_unmapped.disliry_unmap_fl3 SET  fl11gatepass=?, "
				+ " fl11_date=? WHERE casecode=? and fl36_date is null and fl11_date is   null and etin=? " +
				" and date_plan=?";

	} else if (act.getVch_from().equalsIgnoreCase("FL3A")) {
		query = " UPDATE bottling_unmapped.disliry_unmap_fl3a SET  fl11gatepass=?, "
				+ " fl11_date=? WHERE casecode=? and fl36_date is null and fl11_date is   null and etin=? " +
				"and  date_plan=?";
	}

	try {
		con = ConnectionToDataBase.getConnection();
		con1 = ConnectionToDataBase.getConnection20_21();
		con.setAutoCommit(false);
		con1.setAutoCommit(false);
		int j[] = null;
		ps = con1.prepareStatement(query);
		for (int i = 0; i < act.getGetVal().size(); i++) {
			LiquorVehicleAccidentCaseDT dt = (LiquorVehicleAccidentCaseDT) act.getGetVal().get(i);

			datenew=dt.getCasecode().substring(16,22).trim();

			datenew="20"+datenew;
			datenew	=datenew.substring(0,4)+"-"+datenew.substring(4,6)+"-"+datenew.substring(6);

			date1=new SimpleDateFormat("yyyy-MM-dd").parse(datenew);

			ps.setString(1, dt.getGatepass());
			ps.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));

			ps.setString(3,dt.getCasecode().trim().substring(26,dt.getCasecode().trim().length()));

			ps.setString(4, dt.getCasecode().trim().substring(0,12));
			ps.setDate(5, Utility.convertUtilDateToSQLDate(date1));

			ps.addBatch();

         
		}


		j = ps.executeBatch();
		save = j.length;
		
		System.out.println("save=1111="+save);
		if (act.getGetVal().size() == save && save>0) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			query = " UPDATE distillery.liquor_accidental_case_mst " +
					" SET vch_finalize='F', finalize_dt_time=?  " +
					" WHERE vch_gatepass_no='"+ act.getGatepass_no().trim()+ "' ";

			ps = con.prepareStatement(query);
			ps.setString(1, dateFormat.format(new Date()));
			save1 = ps.executeUpdate();
			System.out.println("save=22="+save1);
           if(act.getGatepass_type().equalsIgnoreCase("CL")){
			delQr = " DELETE FROM distillery.distillery_gatepass_casecode_cl " +
					" WHERE gatespass='"+ act.getScanGatePassNo().trim() + "' ";
           }else if(act.getGatepass_type().equalsIgnoreCase("FL3/3A")){
			delQr = " DELETE FROM distillery.fl11_dislery_casecode_20_21 " +
					" WHERE vch_gatepass_no='"+ act.getScanGatePassNo().trim() + "' ";
           } else if(act.getGatepass_type().equalsIgnoreCase("FL1/1A")){
			delQr = " DELETE FROM distillery.distillery_gatepass_casecode " +
					" WHERE gatespass='"+ act.getScanGatePassNo().trim() + "' ";
           }

			ps = con.prepareStatement(delQr);
			save1=ps.executeUpdate();
			System.out.println("save=333="+save1);
		}
		if (save1 > 0) {
			val = true;
			con.commit();
			con1.commit();
		} else {
			val = false;
			con.rollback();
			con1.rollback();
		}

	} catch (Exception ex) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(ex.getMessage(),ex.getMessage()));
		ex.printStackTrace();
	} finally {
		try {
			if (ps != null)
				ps.close();

			if (con != null)
				con.close();
			con1.close();
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(ex.getMessage(),ex.getMessage()));
			ex.printStackTrace();
		}
	}
	return val;

}

public boolean update(LiquorVehicleAccidentCaseAction act) {
	int save = 0;
	int save1 = 0;
	boolean val = false;
	PreparedStatement ps = null;
	Connection con = null;
	Connection con1 = null;
	String query = "";
	String datenew="";
	String delQr = "";
	Date date1=null;
	
	
	

	try {
		con = ConnectionToDataBase.getConnection();
		
		System.out.println("save=1111="+save);
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			query = " UPDATE distillery.liquor_accidental_case_mst " +
					" SET vch_finalize='F', finalize_dt_time=?  " +
					" WHERE vch_gatepass_no='"+ act.getGatepass_no().trim()+ "' ";

			ps = con.prepareStatement(query);
			ps.setString(1, dateFormat.format(new Date()));
			save1 = ps.executeUpdate();
			
		
		if (save1 > 0) {
			val = true;
			
		} else {
			val = false;
			
		}

	} catch (Exception ex) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(ex.getMessage(),ex.getMessage()));
		ex.printStackTrace();
	} finally {
		try {
			if (ps != null)
				ps.close();

			if (con != null)
				con.close();
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(ex.getMessage(),ex.getMessage()));
			ex.printStackTrace();
		}
	}
	return val;

}


public boolean etin_check(String g, LiquorVehicleAccidentCaseAction act) {
	
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
			" where vch_gatepass_no = '"+g+"' and dispatchd_box>0) a)b group by b.code_generate_through, b.f order by etin";
	
	}else if(act.getGatepass_type().equalsIgnoreCase("FL3/3A")){
		q1 = "select count(a.etin), a.etin from" +
				" (select substring(vch_casecode,1,12) as etin from distillery.fl11_dislery_casecode_20_21" +
				" where vch_gatepass_no='"+g+"')a group by a.etin order by a.etin;";
		
		 q2 = "select b.f*count(b.code_generate_through) as count, b.code_generate_through as etin from" +
				" (select a.f, (select code_generate_through from distillery.packaging_details_20_21 where package_id=a.p) from" +
				" (select dispatchd_box as f,int_pckg_id as p  from distillery.fl1_stock_trxn_20_21" +
				" where vch_gatepass_no = '"+g+"' and dispatchd_box>0) a )b group by b.code_generate_through, b.f order by etin";
	}else if(act.getGatepass_type().equalsIgnoreCase("FL1/1A")){
		q1 = "select count(a.etin), a.etin from" +
				" (select substring(casecode,1,12) as etin from distillery.distillery_gatepass_casecode" +
				" where gatespass='"+g+"')a group by a.etin order by a.etin;";
		
		 q2 = "select b.f*count(b.code_generate_through) as count, b.code_generate_through as etin from" +
				" (select a.f, (select code_generate_through from distillery.packaging_details_20_21 where package_id=a.p) from" +
				" (select dispatchd_box as f,int_pckg_id as p  from distillery.fl2_stock_trxn_20_21" +
				" where vch_gatepass_no = '"+g+"' and dispatchd_box>0) a)b group by b.code_generate_through, b.f order by etin";
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





