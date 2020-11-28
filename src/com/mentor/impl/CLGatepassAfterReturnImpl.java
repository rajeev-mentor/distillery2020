package com.mentor.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mentor.Datatable.CLGatepassAfterReturnDT;
import com.mentor.action.CLGatepassAfterReturnAction;
import com.mentor.action.CLGatepassAfterReturnAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class CLGatepassAfterReturnImpl {
	


	// =================================get distillery
	// details===========================

	public String getDetails(CLGatepassAfterReturnAction ac) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String queryList = " SELECT int_app_id_f,vch_undertaking_name,vch_wrk_add  "
					+ " FROM  dis_mst_pd1_pd2_lic "
					+ " WHERE vch_wrk_phon='"
					+ ResourceUtil.getUserNameAllReq().trim() + "' ";

			pstmt = con.prepareStatement(queryList);

			// //System.out.println("get details query----------------"+queryList);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ac.setDistName(rs.getString("vch_undertaking_name"));
				ac.setDistId(rs.getInt("int_app_id_f"));
				ac.setDistAddress(rs.getString("vch_wrk_add"));

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
		return "";

	}

	// ===================select season id====================

	public ArrayList getSeasonDetails(CLGatepassAfterReturnAction ac) {

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();

		String selQr = "";
		try {

			selQr = "SELECT * FROM public.mst_season where active='a'";
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(selQr);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setValue(rs.getString(1));
				item.setLabel(rs.getString(2) + "-" + rs.getString(3));
				ac.setSession(rs.getString(2) + "-" + rs.getString(3));
				list.add(item);
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		}

		finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(se.getMessage(), se.getMessage()));
			}
		}
		return list;

	}

	// ==============================get CL2 license
	// number============================

	public ArrayList licListFL2Impl(CLGatepassAfterReturnAction act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);
		try {
			String query = " SELECT licence_no, licence_type  "
					+ " FROM licence.cltype1__licence "
					+ " WHERE licence_type='" + act.getVch_to() + "' ";

			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(query);
			// //System.out.println("get CL2 license query----------------"+query);

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				item.setValue(rs.getString("licence_no"));
				item.setLabel(rs.getString("licence_no"));

				list.add(item);

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		} finally {
			try {

				if (conn != null)
					conn.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
			}
		}
		return list;

	}

	// ============================display data in first
	// datatable=============================

	public ArrayList displaylistImpl(CLGatepassAfterReturnAction act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		// int i1=0,i2=0;

		try {

			selQr = " SELECT a.package_name, a.package_id,c.vch_lic_no, a.duty, a.adduty, c.int_brand_id, b.brand_name,coalesce(c.tnt,'X') as tt , c.tnt ,"
					+ " c.int_pckg_id, c.int_stock-c.int_dispatched as avlbottle, d.box_size, "
					+ " ROUND(((c.int_stock-c.int_dispatched)/d.box_size)) as avlbox "
					+ " FROM distillery.packaging_details_20_21 a, distillery.brand_registration_20_21 b, "
					+ " distillery.boxing_stock_20_21 c, distillery.box_size_details d  "
					+ " WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.int_brand_id "
					+ " AND a.package_id=c.int_pckg_id AND b.brand_id=c.int_brand_id  "
					+ " AND c.int_dissleri_id='"
					+ act.getDistId()
					+ "' "
					+ " AND c.vch_lic_type='CL' AND ROUND(((c.int_stock-c.int_dispatched)/d.box_size)) >0 "
					+ " AND a.box_id=d.box_id AND a.quantity=d.qnt_ml_detail";

			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);

			rs = ps.executeQuery();
			while (rs.next()) {
				CLGatepassAfterReturnDT dt = new CLGatepassAfterReturnDT();
				dt.setInt_brand_id(rs.getInt("int_brand_id"));
				dt.setInt_pckg_id(rs.getInt("int_pckg_id"));
				dt.setVch_brand(rs.getString("brand_name"));
				dt.setInt_bottle_avaliable(rs.getInt("avlbottle"));
				dt.setDesc(rs.getString("package_name"));
				dt.setDb_duty(rs.getDouble("duty"));
				dt.setDb_add_duty(rs.getDouble("adduty"));
				dt.setSize(rs.getInt("box_size"));
				dt.setBoxAvailable(rs.getInt("avlbox"));
				dt.setLicnobottling(rs.getString("vch_lic_no"));
				dt.setTntflg(rs.getString("tt"));
				list.add(dt);

			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		} finally {
			try {

				if (conn != null)
					conn.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
			}
		}
		return list;

	}

	// =================================get licensee
	// detail=================================

	public String licenceeDetail(CLGatepassAfterReturnAction act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String selQr = " SELECT licence_no, applicant_name, applicant_add  "
					+ " FROM licence.cltype1__licence WHERE licence_type='CL2' AND licence_no='"
					+ act.getVch_to_lic_no() + "' ";

			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(selQr);

			rs = ps.executeQuery();
			// //System.out.println("licensee detail query-------------------"+selQr);
			if (rs.next()) {

				act.setLicenceeId(rs.getInt("licence_no"));
				act.setLicenceeAdrs(rs.getString("applicant_add"));
				act.setLicenceeName(rs.getString("applicant_name"));
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		} finally {
			try {

				if (conn != null)
					conn.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
			}
		}
		return "";

	}
	public boolean  etin(String casecode,CLGatepassAfterReturnAction act) {

		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		boolean s = false;
		try {
			con = ConnectionToDataBase.getConnection();


			String query = " SELECT distinct b.code_generate_through FROM distillery.cl2_stock_trxn_20_21 a, distillery.packaging_details_20_21 b "
					+ " WHERE b.package_id = a.int_pckg_id  and a.vch_gatepass_no='"+ act.getScanGatePassNo().trim()+ "' and b.code_generate_through='"+casecode+"'";

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
	// ============================save
	// data=======================================

	public String saveMethodImpl(CLGatepassAfterReturnAction act) {

		Connection con = null;
		PreparedStatement ps = null, ps2 = null, ps3 = null,ps4= null;
		ResultSet rs = null;
		String insQr = "";
		String insQr1 = "";
		int tok1 = 0;
		int seq = this.maxId(this);
		String gatepass = String.valueOf(act.getDistId()) + "-2020-21-"+ act.getVch_from() + "-" + seq;
		int cases = 0;
		int totalBottles = 0;int indentbox = 0;
		double dutyAddDuty=0.0,ceshsum=0;

		try {

			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);

			insQr = " INSERT INTO distillery.gatepass_to_manufacturewholesale_cl_20_21( "
					+ " int_dist_id, vch_distillary_name, vch_distillary_address, vch_gatepass_no, dt_date, "
					+ " vch_from, vch_to, vch_to_lic_no, curr_date, int_max_id, db_total_duty, "
					+ " db_total_additional_duty, vch_root_details, vch_vehicle_no, "
					+ " vehicle_driver_name, vehicle_agency_name_adrs, licensee_name, licensee_adrs, "
					+ " district_1, district_2, district_3,licence_district,validtill," +
					" gross_weight, tier_weight, net_weight,tnt,return_flag) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,'R') ";

			ps = con.prepareStatement(insQr);

			ps.setInt(1, act.getDistId());
			ps.setString(2, act.getDistName());
			ps.setString(3, act.getDistAddress());
			ps.setString(4, gatepass);
			ps.setDate(5, Utility.convertUtilDateToSQLDate(act.getDt_date()));
			ps.setString(6, act.getVch_from());
			ps.setString(7, act.getVch_to());
			ps.setString(8, act.getVch_to_lic_no());
			ps.setDate(9, Utility.convertUtilDateToSQLDate(new Date()));
			ps.setInt(10, seq);
			ps.setDouble(11, act.getDb_total_value());
			ps.setDouble(12, act.getDb_total_add_value());
			ps.setString(13, act.getRouteDtl());
			ps.setString(14, act.getVehicleNo());
			ps.setString(15, act.getVehicleDrvrName());
			ps.setString(16, act.getVehicleAgencyNmAdrs());
			ps.setString(17, act.getLicenceeName());
			ps.setString(18, act.getLicenceeAdrs());
			ps.setInt(19, act.getDistrict1());
			ps.setInt(20, act.getDistrict2());
			ps.setInt(21, act.getDistrict3());
			ps.setInt(22, act.getDistrictLic());
			ps.setDate(23, Utility.convertUtilDateToSQLDate(act.getValidtill()));
			ps.setDouble(24, act.getGrossWeight());
			ps.setDouble(25, act.getTierWeight());
			ps.setDouble(26, act.getNetWeight());
			ps.setString(27, act.getTntflg());

			tok1 = ps.executeUpdate();

			//System.out.println("first status----------------"+tok1);

			if (tok1 > 0) {
				tok1 = 0;
				for (int i = 0; i < act.displaylist.size(); i++) {
					CLGatepassAfterReturnDT dt = (CLGatepassAfterReturnDT) act.getDisplaylist().get(i);

					if (dt.getDispatchBoxes() > 0 && dt.getInt_dispatch() > 0 && dt.getIndentbox()>0) {
						indentbox=dt.getDispatchBoxes();
						insQr1 = " INSERT INTO distillery.cl2_stock_trxn_20_21( "
								+ " int_dissleri_id, vch_lic_no, vch_lic_type, int_brand_id, int_pckg_id, avl_bottl, "
								+ " avl_box, dispatchd_bottl, dispatchd_box, size, duty, addduty," +
								"  vch_gatepass_no, dt_date,mst_seq,vch_batch_no) "
								+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (select nextval('seq')),?)";

						ps2 = con.prepareStatement(insQr1);

						ps2.setInt(1, act.getDistId());
						ps2.setString(2, act.getVch_to_lic_no());
						ps2.setString(3, act.getVch_to());
						ps2.setInt(4, dt.getInt_brand_id());
						ps2.setInt(5, dt.getInt_pckg_id());
						ps2.setInt(6, dt.getInt_bottle_avaliable());
						ps2.setInt(7, dt.getBoxAvailable());
						ps2.setInt(8, dt.getInt_dispatch());
						ps2.setDouble(9, dt.getDispatchBoxes());
						ps2.setInt(10, dt.getSize());
						ps2.setDouble(11, dt.getCalculated_duty());
						ps2.setDouble(12, dt.getCalculated_add_duty());
						ps2.setString(13, gatepass);
						ps2.setDate(14, Utility.convertUtilDateToSQLDate(act.getDt_date()));
						ps2.setString(15, dt.getBatchNo());
						ceshsum+=(dt.getInt_dispatch()*dt.getCesh());

						cases += dt.getDispatchBoxes();
						totalBottles += dt.getInt_dispatch();
						dutyAddDuty = dutyAddDuty+(dt.getCalculated_duty()+dt.getCalculated_add_duty());

						tok1 = ps2.executeUpdate();
						//System.out.println("second status----------------"+tok1);

						if (tok1 > 0) {
							tok1 = 0;

							String updtQr = " UPDATE distillery.liquor_accidental_case SET " +
									" dispatch_bottle=coalesce(dispatch_bottle,0)+'"+ dt.getInt_dispatch()+ "'," +
									" dispatch_box=coalesce(dispatch_box,0)+"+dt.getDispatchBoxes()+" " +
									" WHERE distillery_id='"+ act.getDistId()+ "'  " +
									" AND int_brand_id='"+ dt.getInt_brand_id()+ "'  " +
									" AND int_pack_id='"+ dt.getInt_pckg_id()+ "'   ";
 
							ps3 = con.prepareStatement(updtQr);


							tok1 = ps3.executeUpdate();



 

						}



						if(tok1>0)
						{ 
						tok1=0;
						for (int ii = 0; ii < act.getDisplaylist1().size(); ii++) 
						{

							CLGatepassAfterReturnDT dt1 = 
									(CLGatepassAfterReturnDT) act.getDisplaylist1().get(ii);

							if(dt1.isSelected())
							{ 
								if(dt.getInt_pckg_id()==dt1.getPackageIdIndent() && dt.getInt_brand_id()==dt1.getBrandIdIndent()  && indentbox>0)
								{
								if(dt1.getDispatchboxIndent()>indentbox){
									 
										String qury="UPDATE fl2d.indent_for_wholesale_trxn "+
											" SET  finalize_indent=coalesce(finalize_indent,0)+"+indentbox+"  "+
											" where  int_pckg_id="+dt.getInt_pckg_id()+" and  int_brand_id="+dt.getInt_brand_id()+" and "+
											" indent_no='"+dt1.getIndentNo()+"'  ";


									ps2 = con.prepareStatement(qury);

									tok1= ps2.executeUpdate();
									qury = " INSERT INTO distillery.wholesale_indent_gatepass( " +
											"  int_distillery_id, vch_gatepass, " +
											" gatepass_dt, indent_no, brand_id, package_id,boxes) " +
											" VALUES ("+act.getDistId()+ ", '"+gatepass+"', '"+Utility.convertUtilDateToSQLDate(act.getDt_date())+"', '"+dt1.getIndentNo()+"' , "+dt.getInt_brand_id()+", "+dt.getInt_pckg_id()+","+indentbox+") ";


									ps4 = con.prepareStatement(qury);
									 
									tok1 = ps4.executeUpdate();
									}else{
										
									 
											String qury="UPDATE fl2d.indent_for_wholesale_trxn "+
												" SET  finalize_indent= coalesce(finalize_indent,0)+"+dt1.getDispatchboxIndent()+" "+
												" where  int_pckg_id="+dt.getInt_pckg_id()+" and  int_brand_id="+dt.getInt_brand_id()+" and "+
												" indent_no='"+dt1.getIndentNo()+"'  ";


										ps2 = con.prepareStatement(qury);

										tok1 = ps2.executeUpdate();
									 if(tok1>0){
										 tok1=0;
										qury = " INSERT INTO distillery.wholesale_indent_gatepass( " +
												"  int_distillery_id, vch_gatepass, " +
												" gatepass_dt, indent_no, brand_id, package_id,boxes) " +
												" VALUES ("+act.getDistId()+ ", '"+gatepass+"', '"+Utility.convertUtilDateToSQLDate(act.getDt_date())+"', '"+dt1.getIndentNo()+"' , "+dt.getInt_brand_id()+", "+dt.getInt_pckg_id()+","+dt1.getDispatchboxIndent()+") ";


										ps4 = con.prepareStatement(qury);
										 
										tok1 = ps4.executeUpdate();
										}} 
								
								indentbox=indentbox-dt1.getDispatchboxIndent();
								}
							}
						}}
					}
				}

				
			 

			}

			if (tok1 > 0) {
				con.setAutoCommit(true);
				act.clearAll();
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,Constants.SAVED_SUCESSFULLY);
				act.setSaveflg(false);
				act.setSavecss("position: absolute; top: 230px; right: 0px; left: 0px; display: none; z-index: 1502; animation-name: sh-entry; animation-duration: 0.3s;");
				act.setShOverlayStyle("position: fixed; display: none; width: 100%; height: 100%; top: 0; left: 0; right: 0; bottom: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1500; cursor: pointer; animation-name: sh-entry; animation-duration: 0.3s;");

				/*	String to = act.getOfficrEmail();
				String Name = act.getDistName();
				String uName = act.getDistMob();
				String sub = "Dispatch";
				String msg = " Gatepass No." + gatepass + " For " + cases
						+ " Cases Containing " + totalBottles
						+ " Bottles Has Been Dispatched To Your District. ";

				String from = NewCommomImpl.getEmailUser();
				String password = NewCommomImpl.getEmailUserPassword();
				NewCommomImpl.sendEmail(to, sub, msg, from, password);*/
			}

			else {
				con.rollback();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (ps2 != null)
					ps2.close();

				if (ps4 != null)
					ps4.close();

			} catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
			}
		}

		return "";

	}


	public int getMaxIdDuty() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int maxid = 0;

		try {
			String query = "SELECT max(int_id) from distillery.duty_register_19_20 ";
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxid = rs.getInt(1);
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
		return maxid;
	}



	public String getEmailDetails(CLGatepassAfterReturnAction ac) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String queryList = 	" SELECT a.officer_email " +
					" FROM public.district a, distillery.gatepass_to_manufacturewholesale_cl_20_21 b " +
					" WHERE a.districtid=b.licence_district ";

			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ac.setOfficrEmail(rs.getString("officer_email"));

			}

		} catch (SQLException se) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(se.getMessage(), se.getMessage()));
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();

				if (rs != null)
					rs.close();

				if (con != null)
					con.close();

			} catch (SQLException se) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(se.getMessage(), se.getMessage()));
			}
		}
		return "";

	}

	// =====================get max id sequence==============================

	public int maxId(CLGatepassAfterReturnImpl impl) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(int_max_id) as id FROM distillery.gatepass_to_manufacturewholesale_cl_20_21 ";
		int maxid = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxid = rs.getInt("id");
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
			}
		}
		return maxid + 1;

	}

	// ==========================get second datatable
	// data=============================================

	public ArrayList getGatePassWholeSaleListCL(CLGatepassAfterReturnAction act) {

		ArrayList list = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = " SELECT a.int_dist_id, a.vch_distillary_name, a.vch_distillary_address, a.vch_gatepass_no,coalesce(a.tnt,'X') as tnt, "
				+ " a.dt_date, a.vch_from, a.vch_to, a.vch_to_lic_no, a.curr_date, "
				+ " a.int_max_id, a.db_total_duty, a.db_total_additional_duty, COALESCE(a.vch_finalize,'N') as vch_finalize FROM "
				+ " distillery.gatepass_to_manufacturewholesale_cl_20_21 a  " +
				" WHERE a.int_dist_id="+ act.getDistId() + " and a.return_flag='R' ORDER BY a.int_max_id DESC";
		try {
			list = new ArrayList();
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			int i = 1;
			while (rs.next()) {
				CLGatepassAfterReturnDT dt = new CLGatepassAfterReturnDT();
				dt.setSno(i);
				dt.setInt_dist_id(rs.getInt("int_dist_id"));
				dt.setVch_distillary_name(rs.getString("vch_distillary_name"));
				dt.setVch_distillary_address(rs.getString("vch_distillary_address"));
				dt.setVch_gatepass_no(rs.getString("vch_gatepass_no"));
				dt.setDt_date(rs.getDate("dt_date"));
				dt.setVch_from(rs.getString("vch_from"));
				dt.setVch_to(rs.getString("vch_to"));
				dt.setVch_to_lic_no(rs.getString("vch_to_lic_no"));
				dt.setCurr_date(rs.getDate("curr_date"));
				dt.setInt_max_id(rs.getInt("int_max_id"));
				dt.setDb_total_duty_2(rs.getDouble("db_total_duty"));
				dt.setDb_total_additional_duty(rs.getDouble("db_total_additional_duty"));
				if (rs.getString("vch_finalize").equalsIgnoreCase("F")) {
					dt.setFinalizePrint(true);
					dt.setPrintDraft(false);
				} else {
					dt.setFinalizePrint(false);
					dt.setPrintDraft(true);
				}
				act.setScanUploadFlag(true);
				list.add(dt);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
			}
		}
		return list;

	}

	// ===============================get district
	// list=====================================

	public ArrayList districtListImpl(CLGatepassAfterReturnAction act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);
		try {
			String query = " SELECT districtid, description "
					+ " FROM public.district where coalesce(allowed_dispatch,'D')='A'  ORDER BY districtid ";


			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(query);
			// //System.out.println("get district query----------------"+query);

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				item.setValue(rs.getString("districtid"));
				item.setLabel(rs.getString("description"));

				list.add(item);

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		} finally {
			try {

				if (conn != null)
					conn.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
			}
		}
		return list;

	}

	// ------------------------------ PRINT
	// ---------------------------------------

	public boolean printReport(CLGatepassAfterReturnAction action) 
	{
		String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
		String relativePath = mypath + File.separator + "ExciseUp"+ File.separator + "WholeSale" + File.separator + "jasper";
		String relativePathpdf = mypath + File.separator + "ExciseUp"+ File.separator + "WholeSale" + File.separator + "pdf";
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		String reportQuery = null;
		boolean printFlag = false;

		try {
			con = ConnectionToDataBase.getConnection();


			/*	"	SELECT DISTINCT a.int_dist_id, a.validtill,a.vch_distillary_name, a.gross_weight, a.tier_weight, a.net_weight, "
					+ "	a.vch_gatepass_no, a.dt_date, a.vch_from, a.vch_to,a.vch_root_details, a.vch_vehicle_no,  "
					+ "	a.vehicle_driver_name, a.vehicle_agency_name_adrs, a.licensee_name, a.licensee_adrs, "
					+ "	b.vch_lic_no, b.vch_lic_type, b.int_brand_id, b.int_pckg_id, "
					+ "	 b.dispatchd_bottl, b.dispatchd_box, b.size, b.duty, b.addduty, "
					+ " (SELECT f.description FROM public.district f where a.licence_district=f.districtid ) as licdistrct, "
					+ "	b.vch_gatepass_no, b.dt_date, "
					+ "	c.brand_id, c.brand_name, c.strength, "
					+ "	d.package_name,d.box_id, "
					+ "	e.qnt_ml_detail as ml , "
					+ "	(((b.size::int*b.dispatchd_box)*e.qnt_ml_detail)/1000) as bl , "
					+ "	case when  "
					+ "	a.district_1 is not null then (select f.description FROM public.district f where a.district_1=f.districtid ) "
					+ "	 else '---' end as district_1 , "
					+ "	case when  "
					+ "	a.district_2 is not null then (select f.description FROM public.district f where a.district_2=f.districtid )  "
					+ "	else '---' end as district_2  , "
					+ "	case when  "
					+ "	a.district_3 is not null then (select f.description FROM public.district f where a.district_3=f.districtid )  "
					+ "	else '---' end as district_3 "
					+ "	FROM distillery.gatepass_to_manufacturewholesale_cl_20_21 a,  "
					+ "	distillery.cl2_stock_trxn_20_21 b , "
					+ "	distillery.brand_registration_20_21 c , "
					+ "	 distillery.packaging_details_20_21 d, "
					+ "	  distillery.box_size_details e "
					+ "	  where a.vch_gatepass_no=b.vch_gatepass_no "
					+ "	  and a.dt_date=b.dt_date "
					+ "	  and b.int_brand_id=c.brand_id "
					+ "	  and c.brand_id=d.brand_id_fk "
					+ "	  and d.box_id=e.box_id "
					+ "	  and a.int_dist_id=? "
					+ "	  and a.vch_gatepass_no= ?  ";*/

			reportQuery = 	" SELECT  indent_no,vch_batch_no,int_dist_id, validtill,vch_distillary_name, gross_weight, tier_weight, " +
					" net_weight, vch_gatepass_no,dt_date, vch_from, vch_to,vch_root_details, vch_vehicle_no," +
					" vehicle_driver_name, vehicle_agency_name_adrs, licensee_name, licensee_adrs,vch_lic_no," +
					" vch_lic_type, int_brand_id, int_pckg_id,dispatchd_bottl, dispatchd_box,size, duty," +
					" addduty, licdistrct, brand_id, brand_name, strength, package_name,box_id,ml ,bl ," +
					" district_1 , district_2,district_3  " +
					" FROM  distillery.CLGatepass_20_21("+action.getDistId()+",'"+action.getPrintGatePassNo().trim()+"' )";

			pst = con.prepareStatement(reportQuery);


			rs = pst.executeQuery();
			if (rs.next()) {

				rs = pst.executeQuery();

				Map parameters = new HashMap();
				parameters.put("REPORT_CONNECTION", con);
				parameters.put("SUBREPORT_DIR", relativePath + File.separator);
				parameters.put("image", relativePath + File.separator);

				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

				jasperReport = (JasperReport) JRLoader.loadObject(relativePath+ File.separator + "CLGatePass.jasper");

				JasperPrint print = JasperFillManager.fillReport(jasperReport,parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(999) + 1;

				JasperExportManager.exportReportToPdfFile(print,relativePathpdf + File.separator + action.getPrintGatePassNo() +".pdf");

				CLGatepassAfterReturnDT dt = new CLGatepassAfterReturnDT();
				dt.setPdfName(action.getPrintGatePassNo() +".pdf");
				action.setPdfname(action.getPrintGatePassNo() +".pdf");
				dt.setPrintFlag(true);
				// action.setPrintFlag(true);

				printFlag = true;
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("No Data Found", "No Data Found"));
				CLGatepassAfterReturnDT dt1 = new CLGatepassAfterReturnDT();
				// action.setPrintFlag(false);
				printFlag = false;
				dt1.setPrintFlag(false);

			}

		} catch (JRException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
			}
		}

		return printFlag;
	}

	// ------------------------------------------------
	public void saveExcelData(CLGatepassAfterReturnAction action) {
		String gatepass = "";
		int status = 0;
		int	flag = 1, excelcase = 0;int k[]=null;
		//int status=0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		FileInputStream fileInputStream = null;
		XSSFWorkbook workbook = null;
		try {

			String query = "DELETE FROM distillery.distillery_gatepass_casecode_cl where gatespass ='"+ action.getScanGatePassNo().trim()+ "' ";

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt=null;
			String sql = "INSERT INTO distillery.distillery_gatepass_casecode_cl(gatespass, casecode)VALUES (?, ?)";

			conn = ConnectionToDataBase.getConnection();

			pstmt = conn.prepareStatement(sql);
			fileInputStream = new FileInputStream(action.getExcelFilepath());

			workbook = new XSSFWorkbook(fileInputStream);

			XSSFSheet worksheet = workbook.getSheet("Sheet1");
			int i = 0, j = 0;
			do {

				String casecode = "";
				XSSFRow row1 = worksheet.getRow(j);
				// XSSFRow row2 = worksheet.getRow(j+1);

				XSSFCell cellA1 = row1.getCell((short) 0);
				// XSSFCell cellA2 = row2.getCell((short) 0);

				String cellval = getCellValue(cellA1);
				// String cellval2=getCellValue(cellA2);

				////System.out.println("break.............break" + cellval);
				if ((cellval == null /* && cellval2==null */)
						|| (cellval.length() == 0 /* && cellval2.length()==0 */)
						|| (cellval.equals("0.0")) /*
						 * &&
						 * cellval2.equals("0.0"))
						 */) {

					break;
				} else {

					if (j == 0) {
						cellA1 = row1.getCell((short) 0);
						gatepass = getCellValue(cellA1);
						if (action.getScanGatePassNo().equalsIgnoreCase(gatepass)) {

							i = 1;
						} else {

							flag = 0;
						}
					} else {

						cellA1 = row1.getCell((short) 0);
						casecode = getCellValue(cellA1);
						if(this.etin(casecode.trim().substring(2, 15), action)==true){
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
									" Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!" ," Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!"));	
						}else{
							pstmt.setString(1, gatepass);
							pstmt.setString(2, casecode);

							//status = pstmt.executeUpdate();

							pstmt.addBatch();

							excelcase++;
							action.setExcelCases(excelcase);
							i = 1;
						}
					}}

				j++;
			} while (i == 1);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));
		}


		if (flag == 1) {
			try {
				k=pstmt.executeBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e
								.getMessage(), e.getMessage()));
			}finally {
				try {
					if (pstmt != null)
						pstmt.close();

					if (conn != null)
						conn.close();
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, e
									.getMessage(), e.getMessage()));
				}
			}

			if ( k!=null && k.length > 0  ) {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Upload successfully!!",
								"Upload successfully!!"));

			} else {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Uploading Failed!!", "Uploading Failed!!"));
			}
		} else {
			// action.setKidnlyUploadFlag(true);
			// action.setGatePassFlag(true);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Kindly enter the same gatepass number!!",
							"Kindly enter the same gatepass number!!"));
		}

	}

	private String getCellValue(XSSFCell cell) {
		try {

			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_STRING:

				return cell.getStringCellValue().toString();

			case XSSFCell.CELL_TYPE_BOOLEAN:
				return Boolean.toString(cell.getBooleanCellValue());

			case XSSFCell.CELL_TYPE_NUMERIC:
				String val = Double.toString(cell.getNumericCellValue());
				val = val.substring(0, val.lastIndexOf("."));

				return val;

			case XSSFCell.CELL_TYPE_BLANK:

				return null;

			}

			return null;
		} catch (Exception e) {
			return null;
		}

	}

	public int excelCases(CLGatepassAfterReturnAction act) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String query = 	" SELECT count(*) as dispatchd_box FROM distillery.distillery_gatepass_casecode_cl " +
					" WHERE gatespass='"+ act.getScanGatePassNo().trim()+ "'";

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

	public int recieveCases(CLGatepassAfterReturnAction act) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";

		try {
			con = ConnectionToDataBase.getConnection();

			/*
			 * String query =
			 * " SELECT SUM(dispatchd_box) as dispatchd_box FROM bwfl.fl2_stock_trxn "
			 * + " WHERE vch_gatepass_no='"+act.getScanGatePassNo()+
			 * "' AND int_brewery_id='"+act.getBreweryId()+"' ";
			 */
			String query = 	" SELECT SUM(dispatchd_box) as dispatch_box FROM distillery.cl2_stock_trxn_20_21 " +
					" WHERE vch_gatepass_no= '"+ act.getScanGatePassNo().trim() + "' ";

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

	public void deleteData(CLGatepassAfterReturnAction action) {
		Connection con = null;
		PreparedStatement stmt = null;

		String query = 	" DELETE FROM distillery.distillery_gatepass_casecode_cl " +
				" WHERE gatespass ='"+ action.getScanGatePassNo().trim() + "' ";


		//System.out.println("delete query-----------"+query);

		int status = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			stmt = con.prepareStatement(query);
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
	
public boolean etin_check(String g) {
		
		Connection c1 = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Map<String, String> m1 = new HashMap<String, String>();
		Map<String, String> m2 = new HashMap<String, String>();

		
		
		String q1 = "select count(a.etin), a.etin from" +
				" (select substring(casecode,1,12) as etin from distillery.distillery_gatepass_casecode_cl" +
				" where gatespass='"+g+"')a group by a.etin order by a.etin;";
		
		String q2 = "select b.f*count(b.code_generate_through) as count, b.code_generate_through as etin from" +
				" (select a.f, (select code_generate_through from distillery.packaging_details_20_21 where package_id=a.p) from" +
				" (select dispatchd_box as f,int_pckg_id as p  from distillery.cl2_stock_trxn_20_21" +
				" where vch_gatepass_no = '"+g+"') a)b group by b.code_generate_through, b.f order by etin";
		
		

		// System.out.println("second datatable==============="+query);
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
	
	




	public boolean updateFL3(CLGatepassAfterReturnAction act) {
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
		
		if (!etin_check(act.getScanGatePassNo())) {
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

		query = " UPDATE bottling_unmapped.disliry_unmap_cl SET  fl36gatepass=?, fl36_date=?  WHERE casecode=? " +
				" AND etin=? AND date_plan=? AND fl36_date IS NULL AND boxing_seq IS NOT NULL  ";

		try {
			con = ConnectionToDataBase.getConnection();
			con1 = ConnectionToDataBase.getConnection20_21();
			con.setAutoCommit(false);
			con1.setAutoCommit(false);
			int j[] = null;
			ps = con1.prepareStatement(query);
			for (int i = 0; i < act.getGetVal().size(); i++) {
				CLGatepassAfterReturnDT dt = (CLGatepassAfterReturnDT) act.getGetVal().get(i);

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
			if (act.getGetVal().size() == save && save>0) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

				query = " UPDATE distillery.gatepass_to_manufacturewholesale_cl_20_21 " +
						" SET vch_finalize='F', finalize_dt_time=?  " +
						" WHERE vch_gatepass_no='"+ act.getScanGatePassNo().trim() + "' ";

				ps = con.prepareStatement(query);
				ps.setString(1, dateFormat.format(new Date()));
				save1 = ps.executeUpdate();

				delQr = " DELETE FROM distillery.distillery_gatepass_casecode_cl " +
						" WHERE gatespass='"+ act.getScanGatePassNo().trim() + "' ";


				ps = con.prepareStatement(delQr);
				ps.executeUpdate();
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


	public ArrayList getExcelData(CLGatepassAfterReturnAction action) {
		ArrayList list = new ArrayList();

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int boxingCount=0;
		int listSize=0;
		String query = 	" SELECT gatespass,casecode FROM distillery.distillery_gatepass_casecode_cl " +
				" WHERE gatespass ='"+ action.getScanGatePassNo().trim() + "'";

		try {
			con = ConnectionToDataBase.getConnection();
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			// Date d=Utility.convertSqlDateToUtilDate(date_created);
			while (rs.next()) {
				CLGatepassAfterReturnDT dt = new CLGatepassAfterReturnDT();

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


	public boolean getCascodeMatch(Date date1, String casecode,String etin, CLGatepassAfterReturnAction action)
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean flag=false;

		String sql= " SELECT * from bottling_unmapped.disliry_unmap_cl  " +
				" WHERE casecode='"+casecode.trim()+"' AND etin='"+etin.trim()+"' AND date_plan='"+Utility.convertUtilDateToSQLDate(date1)+"'  " +
				" AND fl36_date IS NULL AND boxing_seq IS NOT NULL ";


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








	// ------------------------------ PRINT darft
	// report---------------------------------------

	public boolean printDraftReport(CLGatepassAfterReturnAction action) {

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
		boolean printFlagDraft = false;

		try {
			con = ConnectionToDataBase.getConnection();
			/*	reportQuery =


			"	SELECT DISTINCT a.int_dist_id, a.validtill,a.vch_distillary_name, a.gross_weight, a.tier_weight, a.net_weight, "
					+ "	a.vch_gatepass_no, a.dt_date, a.vch_from, a.vch_to,a.vch_root_details, a.vch_vehicle_no,  "
					+ "	a.vehicle_driver_name, a.vehicle_agency_name_adrs, a.licensee_name, a.licensee_adrs, "
					+ "	b.vch_lic_no, b.vch_lic_type, b.int_brand_id, b.int_pckg_id, "
					+ "	 b.dispatchd_bottl, b.dispatchd_box, b.size, b.duty, b.addduty, "
					+ " (SELECT f.description FROM public.district f where a.licence_district=f.districtid ) as licdistrct, "
					+ "	b.vch_gatepass_no, b.dt_date, "
					+ "	c.brand_id, c.brand_name, c.strength, "
					+ "	d.package_name,d.box_id, "
					+ "	e.qnt_ml_detail as ml , "
					+ "	(((b.size::int*b.dispatchd_box)*e.qnt_ml_detail)/1000) as bl , "
					+ "	case when  "
					+ "	a.district_1 is not null then (select f.description FROM public.district f where a.district_1=f.districtid ) "
					+ "	 else '---' end as district_1 , "
					+ "	case when  "
					+ "	a.district_2 is not null then (select f.description FROM public.district f where a.district_2=f.districtid )  "
					+ "	else '---' end as district_2  , "
					+ "	case when  "
					+ "	a.district_3 is not null then (select f.description FROM public.district f where a.district_3=f.districtid )  "
					+ "	else '---' end as district_3 "
					+ "	FROM distillery.gatepass_to_manufacturewholesale_cl_20_21 a,  "
					+ "	distillery.cl2_stock_trxn_20_21 b , "
					+ "	distillery.brand_registration_20_21 c , "
					+ "	 distillery.packaging_details_20_21 d, "
					+ "	  distillery.box_size_details e "
					+ "	  where a.vch_gatepass_no=b.vch_gatepass_no "
					+ "	  and a.dt_date=b.dt_date "
					+ "	  and b.int_brand_id=c.brand_id "
					+ "	  and c.brand_id=d.brand_id_fk "
					+ "	  and d.box_id=e.box_id "
					+ "	  and a.int_dist_id=? "
					+ "	  and a.vch_gatepass_no= ?  ";*/
			reportQuery = 	" SELECT  indent_no,vch_batch_no,int_dist_id, validtill,vch_distillary_name, gross_weight, tier_weight, " +
					" net_weight, vch_gatepass_no,dt_date, vch_from, vch_to,vch_root_details, vch_vehicle_no," +
					" vehicle_driver_name, vehicle_agency_name_adrs, licensee_name, licensee_adrs,vch_lic_no," +
					" vch_lic_type, int_brand_id, int_pckg_id,dispatchd_bottl, dispatchd_box,size, duty," +
					" addduty,licdistrct, 	 brand_id, brand_name, strength, package_name,box_id,ml ,bl ," +
					" district_1 , district_2,district_3  " +
					" FROM  distillery.CLGatepass_20_21("+action.getDistId()+",'"+action.getPrintGatePassNo().trim()+"' )";

			pst = con.prepareStatement(reportQuery);


			rs = pst.executeQuery();
			if (rs.next()) {

				rs = pst.executeQuery();

				Map parameters = new HashMap();
				parameters.put("REPORT_CONNECTION", con);
				parameters.put("SUBREPORT_DIR", relativePath + File.separator);
				parameters.put("image", relativePath + File.separator);

				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

				jasperReport = (JasperReport) JRLoader.loadObject(relativePath+ File.separator + "CLGatePassDraft.jasper");

				JasperPrint print = JasperFillManager.fillReport(jasperReport,parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(999) + 1;

				JasperExportManager.exportReportToPdfFile(print,relativePathpdf + File.separator + "CLGatePassDraft-"+ action.getPrintGatePassNo() +".pdf");

				CLGatepassAfterReturnDT dt = new CLGatepassAfterReturnDT();
				dt.setPdfDraftName("CLGatePassDraft-"+ action.getPrintGatePassNo() +".pdf");
				action.setPdfDraftname("CLGatePassDraft-"+ action.getPrintGatePassNo() +".pdf");
				dt.setDraftprintFlag(true);
				// action.setPrintFlag(true);

				printFlagDraft = true;
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Data Found", "No Data Found"));
				CLGatepassAfterReturnDT dt1 = new CLGatepassAfterReturnDT();
				// action.setPrintFlag(false);
				printFlagDraft = false;
				dt1.setDraftprintFlag(false);

			}

		} catch (JRException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
			}
		}

		return printFlagDraft;

	}

	// -----------------------code for cancel gatepass--------------------------------


	//----------------get max id -------------------------------------


	public int maxIdSeq(CLGatepassAfterReturnImpl impl) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(seq) as id FROM distillery.duty_cancellation_19_20 ";
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

	//-------------------cancel gatepass--------------------------------


	/*public String cancel_gatepassImpl(CLGatepassAfterReturnAction act)
	{


		Connection con = null;
		PreparedStatement ps = null, ps2 = null, ps3 = null, ps4 = null, ps5 = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		ResultSet rs1 = null;
		String sql = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		int tok1 = 0;
		int tok2 = 0;
		int updateindent = 0;
		int delindent = 0;
		int seq = this.maxIdSeq(this);

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
int dispatchBox=0;
String description="";
			String gatepass = "";
			gatepass = act.getPrintGatePassNo().trim();

		

			String selQr = 	" SELECT DISTINCT d.vch_description,a.int_dist_id,b.duty,b.addduty,b.dispatchd_box,(duty+addduty) as amount, " +
					" a.dt_date, a.vch_gatepass_no, a.vch_from, a.tnt,  " +
					" b.int_brand_id, b.int_pckg_id, b.dispatchd_bottl, c.vch_lic_no  " +
					" FROM distillery.gatepass_to_manufacturewholesale_cl_20_21 a,  " +
					" distillery.cl2_stock_trxn_20_21 b, distillery.boxing_stock_20_21 c   ,distillery.duty_register_19_20 d " +
					" WHERE a.int_dist_id=b.int_dissleri_id  AND a.vch_gatepass_no=b.vch_gatepass_no  " +
					" AND a.dt_date=b.dt_date AND b.int_brand_id = c.int_brand_id  " +
					" AND b.int_pckg_id = c.int_pckg_id  " +
					" AND b.int_dissleri_id=c.int_dissleri_id  AND a.vch_from = c.vch_lic_type  " +
					" AND c.vch_lic_type='CL' AND a.int_dist_id='"+act.getDistId()+"' " +
					 " and a.int_dist_id=d.int_distillery_id and a.vch_gatepass_no=d.gatepass  "+
					" AND a.vch_gatepass_no='"+gatepass+"' AND vch_finalize IS NULL  ";

			pstmt = con.prepareStatement(selQr);

			System.out.println("selQr------------" + selQr);

			rs = pstmt.executeQuery();

			while (rs.next()) {

			
				CLGatepassAfterReturnDT dt = new CLGatepassAfterReturnDT();
				
				description=rs.getString("vch_description");
				
				dispatchBox=rs.getInt("dispatchd_box");
				
				 
				 System.out.println(dispatchBox+"==========box+description=============="+description);

				 
				dt.setBrandIdDt(rs.getInt("int_brand_id"));
				dt.setPckgIdDt(rs.getInt("int_pckg_id"));
				dt.setDispatcBotlsDt(rs.getInt("dispatchd_bottl"));
				dt.setLicenseNoDt(rs.getString("vch_lic_no"));
				dt.setTntflgDt(rs.getString("tnt"));

				String updtQr = " UPDATE distillery.boxing_stock_20_21 SET "
						+ " int_dispatched=COALESCE(int_dispatched,0)-'"
						+ dt.getDispatcBotlsDt()
						+ "' "
						+ " WHERE int_dissleri_id='"
						+ act.getDistId()
						+ "' AND int_brand_id='"
						+ dt.getBrandIdDt()
						+ "' "
						+ " AND int_pckg_id='"
						+ dt.getPckgIdDt()
						+ "' and vch_lic_no='"
						+ dt.getLicenseNoDt()
						+ "' ";

				ps5 = con.prepareStatement(updtQr);
				////System.out.println("updtQr------ggg----" + updtQr);
				tok1 = ps5.executeUpdate();

				if (tok1 > 0) {

					tok1 = 0;
					   updtQr = " UPDATE  fl2d.indent_for_wholesale_trxn a SET finalize_indent=COALESCE(finalize_indent,0)-b.boxes  from distillery.wholesale_indent_gatepass b " +
							" WHERE b.vch_gatepass ='"+gatepass.trim()+"' and  a. indent_no=b.indent_no and a.int_brand_id=b.brand_id and  a.int_pckg_id=b.package_id  and b.package_id="+dt.getPckgIdDt()+" and b.brand_id="+ dt.getBrandIdDt()+" and b.boxes>0 ";
					   ps5 = con.prepareStatement(updtQr);
					tok1 = ps5.executeUpdate();
					if (tok1 > 0) {
					
					tok1=0;
					updtQr = " UPDATE  distillery.wholesale_indent_gatepass  set boxes=0  " +
							" WHERE  vch_gatepass ='"+gatepass.trim()+"' and    package_id="+dt.getPckgIdDt()+" and  brand_id="+ dt.getBrandIdDt()+" ";
					   ps5 = con.prepareStatement(updtQr);
					tok1 = ps5.executeUpdate();
					}

				}	

			}
			if (tok1 > 0) {

				tok1 = 0;
				sql = 	" INSERT INTO distillery.duty_cancellation_20_21( "
						+ " seq, vch_gatepass_no, gatepass_dt, " +
						" duty_cancellation_dt_tm, db_duty_amount, " +
						" vch_type, db_add_duty_amount,dispatched_box,description) "
						+ " VALUES (?, ?, ?, ?, ?, ?, ?,?,?) ";

				ps = con.prepareStatement(sql);

				ps.setInt(1, seq);
				ps.setString(2, act.getPrintGatePassNo());
				ps.setDate(3,Utility.convertUtilDateToSQLDate(act.getPrintDate()));
				ps.setString(4, dateFormat.format(new Date()));
				ps.setDouble(5, -act.getCancelDuty());
				ps.setString(6, "CL");
				ps.setDouble(7, -act.getCancelAddDuty());
				ps.setDouble(8, -dispatchBox);
				ps.setString(9, " Cancellation "+description);
				tok1 = ps.executeUpdate();

				////System.out.println("second status----------" + tok1);

			}

			if (tok1 > 0) {
				tok1 = 0;

				sql2 = 	" DELETE FROM distillery.gatepass_to_manufacturewholesale_cl_20_21 "+
						" WHERE vch_gatepass_no='"+gatepass+"' " +
						" AND int_dist_id='"+act.getDistId()+"' AND vch_finalize IS NULL ";

				ps2 = con.prepareStatement(sql2);

				////System.out.println("sql2------ggg----" + sql2);
				tok1 = ps2.executeUpdate();
				////System.out.println("third status----------" + tok1);
			}

			if (tok1 > 0) {
				tok1 = 0;

				sql3 = 	" DELETE FROM distillery.cl2_stock_trxn_20_21 "+
						" WHERE vch_gatepass_no='"+gatepass+"' " +
						" AND int_dissleri_id='" + act.getDistId() + "' ";

				ps3 = con.prepareStatement(sql3);
				tok1 = ps3.executeUpdate();

				////System.out.println("fourth status----------" + tok1);
			}

			if (tok1 > 0) {

				sql4 = 	" DELETE FROM distillery.distillery_gatepass_casecode_cl where gatespass='"
						+ gatepass + "' ";

				ps3 = con.prepareStatement(sql4);
				ps3.executeUpdate();

				////System.out.println("fifth status----------" + tok1);
			}

			if (tok1 > 0) {
				con.setAutoCommit(true);
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Gatepass Cancelled Successfully !!! ","Gatepass Cancelled Successfully !!!"));
				act.clearAll();
			}

			else {
				con.rollback();
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Gatepass Not Cancelled !!! ","Gatepass Not Cancelled !!!"));
				
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Indent Updated - "+updateindent+" & Indent Reverted - "+delindent+" .","Indent Updated - "+updateindent+" & Indent Reverted - "+delindent+" ."));
				
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (ps2 != null)
					ps2.close();
				if (ps5 != null)
					ps5.close();
				if (ps3 != null)
					ps3.close();
				if (ps4 != null)
					ps4.close();

			} catch (SQLException ex) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(ex.getMessage(), ex.getMessage()));
				ex.printStackTrace();
			}
		}

		return "";


	}*/
	
	
	public String cancel_gatepassImpl(CLGatepassAfterReturnAction act)
	{


		Connection con = null;
		PreparedStatement ps = null, ps2 = null, ps3 = null, ps4 = null, ps5 = null, ps6=null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		ResultSet rs1 = null;
		String sql = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		int tok1 = 0;
		int tok2 = 0;
		int updateindent = 0;
		int delindent = 0;
		int seq = this.maxIdSeq(this);
		
		//System.out.println("Seq no======"+seq);

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
int dispatchBox=0;
String description="";
			String gatepass = "";
			gatepass = act.getPrintGatePassNo().trim();

		

			String selQr = 	" SELECT DISTINCT d.vch_description,a.int_dist_id,b.duty,b.addduty,b.dispatchd_box,(duty+addduty) as amount, " +
					" a.dt_date, a.vch_gatepass_no, a.vch_from, a.tnt,  " +
					" b.int_brand_id, b.int_pckg_id, b.dispatchd_bottl, c.vch_lic_no  " +
					" FROM distillery.gatepass_to_manufacturewholesale_cl_20_21 a,  " +
					" distillery.cl2_stock_trxn_20_21 b, distillery.boxing_stock_20_21 c   ,distillery.duty_register_19_20 d " +
					" WHERE a.int_dist_id=b.int_dissleri_id  AND a.vch_gatepass_no=b.vch_gatepass_no  " +
					" AND a.dt_date=b.dt_date AND b.int_brand_id = c.int_brand_id  " +
					" AND b.int_pckg_id = c.int_pckg_id and d.vch_duty_type='DUTY_CL' " +
					" AND b.int_dissleri_id=c.int_dissleri_id  AND a.vch_from = c.vch_lic_type  " +
					" AND c.vch_lic_type='CL' AND a.int_dist_id='"+act.getDistId()+"' " +
					 " and a.int_dist_id=d.int_distillery_id and a.vch_gatepass_no=d.gatepass  "+
					" AND a.vch_gatepass_no='"+gatepass+"' AND vch_finalize IS NULL  ";

			pstmt = con.prepareStatement(selQr);

			//System.out.println("selQr------------" + selQr);

			rs = pstmt.executeQuery();

			while (rs.next()) {

			
				CLGatepassAfterReturnDT dt = new CLGatepassAfterReturnDT();
				
				description=rs.getString("vch_description");
				
				dispatchBox=rs.getInt("dispatchd_box");
				
				 
				// System.out.println(dispatchBox+"==========box+description=============="+description);

				 
				dt.setBrandIdDt(rs.getInt("int_brand_id"));
				dt.setPckgIdDt(rs.getInt("int_pckg_id"));
				dt.setDispatcBotlsDt(rs.getInt("dispatchd_bottl"));
				dt.setLicenseNoDt(rs.getString("vch_lic_no"));
				dt.setTntflgDt(rs.getString("tnt"));

				String updtQr = " UPDATE distillery.liquor_accidental_case SET dispatch_bottle=COALESCE(dispatch_bottle,0)-"
						+ dt.getDispatcBotlsDt()
						+ ", "
						+ " dispatch_box=COALESCE(dispatch_box,0) - "
						+ dispatchBox
						+ " "
						+ " WHERE distillery_id="
						+ act.getDistId()
						+ " "
						+ "AND int_pack_id="
						+ dt.getPckgIdDt()
						+ " AND int_brand_id="
						+ dt.getBrandIdDt()+"";

				ps5 = con.prepareStatement(updtQr);
				 System.out.println("updtQr------ggg----" + updtQr);
				tok1 = ps5.executeUpdate();

				if (tok1 > 0) {

					tok1 = 0;
					   updtQr = " UPDATE  fl2d.indent_for_wholesale_trxn a SET finalize_indent=COALESCE(finalize_indent,0)-b.boxes  from distillery.wholesale_indent_gatepass b " +
							" WHERE b.vch_gatepass ='"+gatepass.trim()+"' and  a. indent_no=b.indent_no and a.int_brand_id=b.brand_id and  a.int_pckg_id=b.package_id  and b.package_id="+dt.getPckgIdDt()+" and b.brand_id="+ dt.getBrandIdDt()+" and b.boxes>0 ";
					   ps5 = con.prepareStatement(updtQr);
					tok1 = ps5.executeUpdate();
					if (tok1 > 0) {
					
					tok1=0;
					updtQr = " UPDATE  distillery.wholesale_indent_gatepass  set boxes=0  " +
							" WHERE  vch_gatepass ='"+gatepass.trim()+"' and    package_id="+dt.getPckgIdDt()+" and  brand_id="+ dt.getBrandIdDt()+" ";
					   ps5 = con.prepareStatement(updtQr);
					tok1 = ps5.executeUpdate();
					}

				}	

			}
			if (tok1 > 0) {

				tok1 = 0;
				sql = 	" INSERT INTO distillery.duty_cancellation_19_20( "
						+ " seq, vch_gatepass_no, gatepass_dt, " +
						" duty_cancellation_dt_tm, db_duty_amount, " +
						" vch_type, db_add_duty_amount,dispatched_box,description) "
						+ " VALUES (?, ?, ?, ?, ?, ?, ?,?,?) ";

				ps = con.prepareStatement(sql);

				ps.setInt(1, seq);
				ps.setString(2, act.getPrintGatePassNo());
				ps.setDate(3,Utility.convertUtilDateToSQLDate(act.getPrintDate()));
				ps.setString(4, dateFormat.format(new Date()));
				ps.setDouble(5, -act.getCancelDuty());
				ps.setString(6, "CL");
				ps.setDouble(7, -act.getCancelAddDuty());
				ps.setDouble(8, -dispatchBox);
				ps.setString(9, " Cancellation "+description);
				tok1 = ps.executeUpdate();

				 System.out.println("tok1----------" + tok1);

			}

			if (tok1 > 0) {
				tok1 = 0;

				sql2 = 	" DELETE FROM distillery.gatepass_to_manufacturewholesale_cl_20_21 "+
						" WHERE vch_gatepass_no='"+gatepass+"' " +
						" AND int_dist_id='"+act.getDistId()+"' AND vch_finalize IS NULL ";

				ps2 = con.prepareStatement(sql2);

				//System.out.println("sql2------ggg----" + sql2);
				tok1 = ps2.executeUpdate();
				 System.out.println("third status----------" + tok1);
			}
			
			
			 
			if (tok1 > 0) {
				

				sql3 = 	" DELETE FROM distillery.cl2_stock_trxn_20_21 "+
						" WHERE vch_gatepass_no='"+gatepass+"' " +
						" AND int_dissleri_id='" + act.getDistId() + "' ";

				ps3 = con.prepareStatement(sql3);
			 ps3.executeUpdate();

			 System.out.println("tok3----------" + tok1);
			}

			if (tok1 > 0) {
                  
				sql4 = 	" DELETE FROM distillery.distillery_gatepass_casecode_cl where gatespass='"
						+ gatepass + "' ";

				ps3 = con.prepareStatement(sql4);
				  ps3.executeUpdate();

				 System.out.println("fifth status----------" + tok1);
			}
		

			if (tok1 > 0) {
				con.setAutoCommit(true);
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Gatepass Cancelled Successfully !!! ","Gatepass Cancelled Successfully !!!"));
				act.clearAll();
			}

			else {
				con.rollback();
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Gatepass Not Cancelled !!! ","Gatepass Not Cancelled !!!"));
				
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Indent Updated - "+updateindent+" & Indent Reverted - "+delindent+" .","Indent Updated - "+updateindent+" & Indent Reverted - "+delindent+" ."));
				
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (ps2 != null)
					ps2.close();
				if (ps5 != null)
					ps5.close();
				if (ps3 != null)
					ps3.close();
				if (ps4 != null)
					ps4.close();

			} catch (SQLException ex) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(ex.getMessage(), ex.getMessage()));
				ex.printStackTrace();
			}
		}

		return "";


	}
	
	public boolean getLicenseDetail(CLGatepassAfterReturnAction action)
	{

		Connection con=null;
		PreparedStatement stmt=null;
		int status=0;
		ResultSet rs=null;boolean flg=false;
		ArrayList list=new ArrayList();

		String xx=action.getVch_to_lic_no().trim(), temp="";
		int len = xx.indexOf('-');
		String s2=xx.substring(0,len+1);

		if(s2.equalsIgnoreCase("F-"))
		{
			temp=xx.substring(len+1);
		}else
		{
			temp=action.getVch_to_lic_no().trim();
		}


		String query=" select vch_core_address,vch_firm_name,coalesce(b.allowed_dispatch,'D')allowed_dispatch ,b.description  from licence.fl2_2b_2d_20_21 ,public.district b  " +
				"  where b.districtid=core_district_id and    vch_licence_no='" + temp + "' and vch_license_type='CL2' ";
		try
		{
			con=ConnectionToDataBase.getConnection();
			stmt=con.prepareStatement(query);
			rs=stmt.executeQuery();
			 
			if(rs.next())
			{ 
				if(rs.getString("allowed_dispatch").equalsIgnoreCase("A"))
				{
					status=1;
				action.setLicenceeName(rs.getString("vch_firm_name"));
				action.setLicenceeAdrs(rs.getString("vch_core_address"));				
				action.setLicence_disable_flag(true);
				flg=true;
				}else {status=0;
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Dispatches are not allowed in selected licensee district !!","Dispatches are not allowed in selected licensee district!!"));
				}
			}
			else  
			{
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"License code does not exist!!","License code does not exist!!"));
			}

		}catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally 
		{
			try 
			{
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return flg;
	}

	// ===========================code for csv============================

	public void saveCSV(CLGatepassAfterReturnAction action) throws IOException {
		Connection con = null;
		PreparedStatement stmt = null;

		String query = "INSERT INTO distillery.distillery_gatepass_casecode_cl(gatespass, casecode)VALUES (?, ?)";

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
							if (gatepass.trim().equalsIgnoreCase(action.getScanGatePassNo().trim())) {
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
	//-------------------------------------------------------------------------------------
	public ArrayList indentDetail(CLGatepassAfterReturnAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = " SELECT   b.user_id,d.brand_name, c.package_name, b.indent_no, b.cr_date, b.int_brand_id,  " +
					" b.int_pckg_id, (b.no_of_box-coalesce(b.finalize_indent,0)) as no_of_box "+
					" from  fl2d.indent_for_wholesale a,fl2d.indent_for_wholesale_trxn b,"+
					" distillery.packaging_details_20_21  c,distillery.brand_registration_20_21 d "+
					" where a.indent_no=b.indent_no and  a.vch_action_taken='A' and"+
					" c.package_id=b.int_pckg_id and a.vch_licence_no='"+act.getVch_to_lic_no()+"' and "+
					" d.brand_id=b.int_brand_id and a.unit_id='"+act.getDistId()+"' and  b.no_of_box-coalesce(b.finalize_indent,0) >0  order by  b.cr_date ";

			
			System.out.println("query indent_no===="+query);
			
			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();
 

			int i=1;
			while (rs.next()) {
				CLGatepassAfterReturnDT dt = new CLGatepassAfterReturnDT();

				dt.setSnoIndent(i);
				dt.setIndentNo(rs.getString("indent_no"));
				dt.setIndentDate(rs.getString("cr_date"));
				dt.setBrandName(rs.getString("brand_name"));
				dt.setPackageSize(rs.getString("package_name"));
				dt.setDispatchboxIndent(rs.getInt("no_of_box"));
				dt.setBrandIdIndent(rs.getInt("int_brand_id"));
				dt.setPackageIdIndent(rs.getInt("int_pckg_id"));



				i++;
				list.add(dt);

			}



		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (conn != null)
					conn.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public ArrayList displaylistImpl2(CLGatepassAfterReturnAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		String brand = "";
		String packag = "";
		String indentno = "";
		int i = 1;

		try 
		{ 
			conn = ConnectionToDataBase.getConnection();
			int j=0;

			for (int ii = 0; ii < act.getDisplaylist1().size(); ii++) 
			{

				CLGatepassAfterReturnDT dt = 
						(CLGatepassAfterReturnDT) act.getDisplaylist1().get(ii);
				
				if(dt.isSelected())
				{
					packag=packag+dt.getPackageIdIndent()+",";
					brand=brand+dt.getBrandIdIndent()+",";
					if(j==0){
					indentno = " select sum(x.no_of_box) from ( SELECT  (m.no_of_box-coalesce(m.finalize_indent,0))no_of_box from  fl2d.indent_for_wholesale_trxn m where "+
 					" m.indent_no in ('"+dt.getIndentNo()+"')  and m.int_pckg_id="+dt.getPackageIdIndent()+" and m.int_pckg_id=a.int_pack_id  and m.int_brand_id=pk.brand_id_fk ";
					j++;
					}else{
						indentno=indentno+"  union all SELECT  (m.no_of_box-coalesce(m.finalize_indent,0))no_of_box from  fl2d.indent_for_wholesale_trxn m where "+
 					" m.indent_no in ('"+dt.getIndentNo()+"') and m.int_pckg_id="+dt.getPackageIdIndent()+" and m.int_pckg_id=c.int_pack_id  and m.int_brand_id=pk.brand_id_fk ";
					}
					 
				}
			}

		

			selQr ="select a.return_bottle-coalesce (a.dispatch_bottle ,0) as avlbottle, a.return_box-coalesce (a.dispatch_box,0)  as avlbox,br.brand_name ," +
					"a.int_brand_id ,a.int_pack_id,pk.package_name , ("+indentno+" )x) as  indentno,                                                                                                        "+
                   " (select size from distillery.cl2_stock_trxn_20_21 x where x.int_brand_id=a.int_brand_id and x.int_pckg_id=a.int_pack_id and                             "+
                   " x.vch_gatepass_no=a.vch_gatepass_no and x.int_dissleri_id=a.distillery_id) as box_size  from                                                            "+
                   " distillery.liquor_accidental_case a,                                                                                                                    "+
                   " distillery.brand_registration_20_21 br , distillery.packaging_details_20_21 pk where a.int_brand_id = br.brand_id and a.int_pack_id = pk.package_id and "+
                   " br.brand_id = pk.brand_id_fk and br.vch_license_type = 'CL' and a.distillery_id='"+act.getDistId()+"'" +
                   " and a.int_brand_id in ("+brand +"0) and a.int_pack_id in ("+packag +"0) and a.return_bottle-coalesce (a.dispatch_bottle ,0)>0";
					
					
					
					
					/*" SELECT a.package_name, a.cesh, a.package_id,c.vch_lic_no, a.duty, a.adduty, c.int_brand_id," +
					" (b.brand_name||'-'||b.strength)brand_name,coalesce(c.tnt,'X') as tt , c.tnt ,"
					+ " c.int_pckg_id, c.int_stock-c.int_dispatched as avlbottle, d.box_size, "
					+ " ROUND(((c.int_stock-c.int_dispatched)/d.box_size)) as avlbox,("+indentno+" )x) as  indentno "
					+ " FROM distillery.packaging_details_20_21 a, distillery.brand_registration_20_21 b, "
					+ " distillery.boxing_stock_20_21 c, distillery.box_size_details d  "
					+ " WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.int_brand_id  "
					+ " AND a.package_id=c.int_pckg_id AND b.brand_id=c.int_brand_id  "
					+ " AND c.int_dissleri_id='"
					+ act.getDistId()
					+ "' "
					+ " AND c.vch_lic_type='CL' AND ROUND(((c.int_stock-c.int_dispatched)/d.box_size)) >0 "
					+ " AND a.box_id=d.box_id AND a.quantity=d.qnt_ml_detail" +
					" and   b.brand_id  in ("+brand +"0)  and a.package_id in ("+packag +"0) ";*/

              System.out.println("displaylistImpl2Qr==="+selQr);
 

			ps = conn.prepareStatement(selQr);

			rs = ps.executeQuery();

			while (rs.next())
			{
				CLGatepassAfterReturnDT dt = new CLGatepassAfterReturnDT();
				dt.setInt_brand_id(rs.getInt("int_brand_id"));
				dt.setInt_pckg_id(rs.getInt("int_pack_id"));
				dt.setVch_brand(rs.getString("brand_name"));
				dt.setInt_bottle_avaliable(rs.getInt("avlbottle"));
				dt.setDesc(rs.getString("package_name"));
				//dt.setDb_duty(rs.getDouble("duty"));
				//dt.setDb_add_duty(rs.getDouble("adduty"));
				dt.setSize(rs.getInt("box_size"));
				dt.setBoxAvailable(rs.getInt("avlbox"));
				//dt.setLicnobottling(rs.getString("vch_lic_no"));
				//dt.setCesh(rs.getDouble("cesh"));

				dt.setIndentbox(rs.getInt("indentno"));

				list.add(dt);

			}







		} catch (Exception e) 
		{
			e.printStackTrace();
		} finally {
			try {

				if (conn != null)
					conn.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

//-----------------------------------------------------------------------------------
	

    public boolean checkDate(CLGatepassAfterReturnAction act) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean pass = false;
		String query = "";

		try {
			
	

			query =" select  vch_finalize,dt_date from distillery.gatepass_to_manufacturewholesale_cl_20_21 "+
				   " where vch_finalize is  null    and "+
				   " int_dist_id='"+act.getDistId()+"'   and  dt_date <'"+Utility.convertUtilDateToSQLDate(new Date())+"'  order by dt_date desc ";
					
					
					
					
		/*			" select  vch_finalize from distillery.gatepass_to_manufacturewholesale_cl_20_21 "+
				   " where vch_finalize is not null     "+
				   " and int_dist_id='"+act.getDistId()+"'   and  dt_date='"+Utility.convertUtilDateToSQLDate(m1)+"'  ";*/
					
					
					
			 
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next())
			{
				pass = true;

			}
		

		} catch (Exception e) {

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
		return pass;

	}
	
}
