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

import com.mentor.Datatable.HistoricalDataVerificationdt;

import com.mentor.action.HistoricalDataVerificationAction;

import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class HistoricalDataVerificationImpl {

	public String getdetails(HistoricalDataVerificationAction act) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;

		try {
			String queryList = "";
			con = ConnectionToDataBase.getConnection();
			// try {
			if (ResourceUtil.getUserNameAllReq().trim().substring(0, 9)
					.equalsIgnoreCase("Excise-DL")) {
				act.setDist_name(ResourceUtil.getUserNameAllReq().trim());
				act.setDist_id(Integer.parseInt(ResourceUtil
						.getUserNameAllReq().trim().substring(10)));
				// ac.setAddress("");

				// //System.out.println("1--"+ResourceUtil.getUserNameAllReq().trim().substring(0,9)+"-");
			} else {
				queryList = " SELECT int_app_id_f as app_id,vch_undertaking_name as name ,vch_wrk_add as address,'D' as login_type"
						+ " FROM dis_mst_pd1_pd2_lic WHERE vch_wrk_phon='"
						+ ResourceUtil.getUserNameAllReq().trim() + "'";

				pstmt = con.prepareStatement(queryList);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					act.setDist_name(rs.getString("name"));
					act.setDist_id(rs.getInt("app_id"));
					// act.set(rs.getString("address"));

				}

			}
		} catch (SQLException se) {
			se.printStackTrace();
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
				se.printStackTrace();
			}
		}
		return "";

	}

	// ---------------------------------show data---------------------*/

	public ArrayList showData(HistoricalDataVerificationAction act) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList al = new ArrayList();
		int i = 1;
		String sql = null;

		if (act.getRadio().equalsIgnoreCase("S")) {

			sql = " select  (select vch_country_name FROM public.country_mst where int_country_id::text=a.export_district)as country, a.int_dist_id,a.vch_gatepass_no, a.dt_date,a.vch_from ,a.vch_to from  "
					+ " distillery.gatepass_to_manufacturewholesale_20_21 a,"
					+ " distillery.historical_for_distillery_export_as_unit  b "
					+ " where b.getpass_no=a.vch_gatepass_no  and b.vrification_created_date_ship is null  "
					+ " and  a.vch_to='EOI' and b.brc_date is not null and a.int_dist_id='"
					+ act.getDist_id() + "'  order by vrification_created_date_ship ASC ";

		} else if (act.getRadio().equalsIgnoreCase("B")) {

			sql = " select  (select vch_country_name FROM public.country_mst where int_country_id::text=a.export_district)as country, a.int_dist_id,a.vch_gatepass_no, a.dt_date,a.vch_from ,a.vch_to from  "
					+ " distillery.gatepass_to_manufacturewholesale_20_21 a,"
					+ " distillery.historical_for_distillery_export_as_unit  b "
					+ " where b.getpass_no=a.vch_gatepass_no  and b.leo_date is not null and b.vrification_created_date_brc is null  "
					+ " and  a.vch_to='EOI' and a.int_dist_id='"
					+ act.getDist_id() + "'   order by vrification_created_date_brc ASC";

		}
			else if (act.getRadio().equalsIgnoreCase("V")) {

			sql = " select (select vch_country_name FROM public.country_mst where int_country_id::text=a.export_district)as country,a.int_dist_id,a.vch_gatepass_no, a.dt_date,a.vch_from ,a.vch_to  from  "
					+ " distillery.gatepass_to_manufacturewholesale_20_21 a,"
					+ " distillery.historical_for_distillery_export_as_unit  b "
					+ " where b.getpass_no=a.vch_gatepass_no and b.vrification_created_date_ship is not null and b.vrification_created_date_brc is not null "
					+ " and  a.vch_to='EOI' and a.int_dist_id='"
					+ act.getDist_id() + "'  order by vrification_created_date_ship ASC ";

		} else {
			sql = " ";

		}
		
		
		System.out.println("===showData====" + sql);

		try {
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			// action.setFlag("T");
			while (rs.next()) {
				HistoricalDataVerificationdt dt = new HistoricalDataVerificationdt();

				dt.setGetpass_no(rs.getString("vch_gatepass_no"));
				dt.setGatepass_dt(rs.getDate("dt_date"));
				dt.setFrom(rs.getString("vch_from"));
				dt.setTo(rs.getString("country"));
		

				// System.out.println("------------getpass_no-------"+dt.getGatapas_chk());
				// if(rs.getString("vch_gatepass_no").equalsIgnoreCase("NA"))
				// {
				dt.setSave_flg("T");
				// act.setSave_flg("T");
				System.out.println("------------getpass_no-------" + "T");
				// }else{
				System.out.println("------------getpass_no-------" + "F");
				dt.setSave_flg("F");
				// act.setSave_flg("F");

				act.setFlag(true);
				dt.setSr_no(i);
				
				i++;
				al.add(dt);

				// /}
			}
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();

			if (rs != null)
				conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;

	}

	// ---------------------------------View Detail---------------------*/

	public ArrayList viewdetail(HistoricalDataVerificationAction act,
			HistoricalDataVerificationdt dt) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList al = new ArrayList();
		int i = 1;
		conn = ConnectionToDataBase.getConnection();

		String sql = "select b.brand_name, int_brand_id,size,dispatchd_bottl,dispatchd_box    "
				+ " from distillery.fl1_stock_trxn_20_21 a,distillery.brand_registration_20_21 b   "
				+ "  where int_dissleri_id='"
				+ act.getDist_id()
				+ "'   "
				+ "   and vch_gatepass_no='"
				+ dt.getGetpass_no()
				+ "' and a.int_brand_id=b.brand_id   ";

		System.out.println("===viewDetail====" + sql);
		try {
			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			// action.setFlag("T");
			while (rs.next()) {
				HistoricalDataVerificationdt dt1 = new HistoricalDataVerificationdt();

				dt1.setBrnad_id(rs.getInt("int_brand_id"));
				dt1.setBrand(rs.getString("brand_name"));
				dt1.setSize(rs.getInt("size"));
				dt1.setBottel(rs.getInt("dispatchd_bottl"));
				dt1.setBox(rs.getInt("dispatchd_box"));
				// act.setGatapassno(dt.getGatapass_no());

				dt1.setSr_no1(i);

				i++;
				al.add(dt1);

				act.setViewflg("T");

			}

			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();

			if (rs != null)
				conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;

	}

	// -----------------------Save Data----------------------------------*/

	public void VerifyData(HistoricalDataVerificationAction act) {

		int saveStatus = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String queryList = "";

		try {
			
			if(act.getRadio().equalsIgnoreCase("S"))
				
			{

			queryList = "update  distillery.historical_for_distillery_export_as_unit set "
					+ "vch_bond='"
					+ act.getVch_bond()
					+ "' , bond_utilization_value='"
					+ act.getBond_utilization_value()
					+ "',"
					+ " vrification_created_date_ship='"
					+ Utility.convertUtilDateToSQLDate(new Date())
					+ "',"
					+ " current_balnc='"
					+ act.getCurrent_balance()
					+ "'"
					+ "  where distillery_id='"
					+ act.getDist_id()
					+ "' and   getpass_no='" + act.getGatapassno() + "'";
			
			act.setRadio("B");
			
			}
			
			else if(act.getRadio().equalsIgnoreCase("B"))
				
			{

			queryList = "update  distillery.historical_for_distillery_export_as_unit set "
					+ "vch_bond='"
					+ act.getVch_bond()
					+ "' , bond_utilization_value='"
					+ act.getBond_utilization_value()
					+ "',"
					+ " vrification_created_date_brc='"
					+ Utility.convertUtilDateToSQLDate(new Date())
					+ "',"
					+ " current_balnc='"
					+ act.getCurrent_balance()
					+ "'"
					+ "  where distillery_id='"
					+ act.getDist_id()
					+ "' and   getpass_no='" + act.getGatapassno() + "'";
			
			
			act.setRadio("V");
			
			}
			
			
			
			//act.setRadio("V");

			
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			saveStatus = pstmt.executeUpdate();

			if (saveStatus > 0) {
				
				act.close();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Saved  Successfully !!! ",
								" Saved Successfully !!!"));

			} else {
				
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Not Saved  !!! ", "Not Saved !!!"));

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

	/*
	 * ///-----------------------------------------------//////////////////
	 * 
	 * public String SaveData(HistoricalDataVerificationAction act) {
	 * 
	 * Connection conn = null; Connection con = null; PreparedStatement pstmt =
	 * null; PreparedStatement pstmt1 = null; ResultSet rs = null; int
	 * saveStatus = 0; int saveStatus1 = 0; conn =
	 * ConnectionToDataBase.getConnection();
	 * 
	 * try {
	 * 
	 * 
	 * SimpleDateFormat showDate = new SimpleDateFormat("dd-MM-yyyy"); String
	 * displayDate = showDate.format(Utility.convertUtilDateToSQLDate(new
	 * Date()));
	 * 
	 * 
	 * String query =
	 * "update  distillery.historical_for_distillery_export_as_unit set " +
	 * "vch_bond='"+act.getVch_bond()+"' , bond_utilization_value='"+act.
	 * getBond_utilization_value()+"'," +
	 * " vrification_created_date='"+Utility.convertUtilDateToSQLDate(new
	 * Date())+"'," + " current_balnc='"+act.getCurrent_balance()+"'" +
	 * "  where distillery_id='"
	 * +act.getDist_id()+"' and   getpass_no='"+act.getGatapassno()+"'" ;
	 * 
	 * 
	 * 
	 * System.out.println("-----getStorageMax-"+query);
	 * 
	 * 
	 * pstmt = conn.prepareStatement(query);
	 * pstmt.setString(1,act.getVch_bond()); pstmt.setInt(2,
	 * act.getBond_utilization_value());
	 * pstmt.setDate(3,Utility.convertUtilDateToSQLDate(new Date()));
	 * pstmt.setInt(4, act.getCurrent_balance());
	 * 
	 * 
	 * 
	 * saveStatus = pstmt.executeUpdate();
	 * 
	 * 
	 * 
	 * 
	 * if (saveStatus1 > 0) {
	 * 
	 * 
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(FacesMessage.SEVERITY_INFO, " Data Saved Successfully !! ",
	 * "Data Saved Successfully !!")); } else { act.reset(); conn.rollback();
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(FacesMessage.SEVERITY_INFO, " Data Not Saved!! ",
	 * "Data Not Saved !!"));
	 * 
	 * }
	 * 
	 * }
	 * 
	 * catch (Exception e) { e.printStackTrace();
	 * 
	 * }
	 * 
	 * finally { try {
	 * 
	 * if (conn != null) conn.close(); if (con != null) con.close(); if (pstmt
	 * != null) pstmt.close(); if (pstmt1 != null) pstmt1.close(); if (rs !=
	 * null) rs.close();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } } return "";
	 * 
	 * }
	 */
	// ---------------------------------max
	// ID---------------------------------//

	public int getStorageMax() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int maxid = 0;

		try {
			String query = " SELECT coalesce(max(int_id),0)+1  as maxid  from distillery.historical_for_distillery_export_as_unit ";

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxid = rs.getInt("maxid");
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
		return maxid + 1;
	}

	public String updateFile(HistoricalDataVerificationAction act, String url) {

		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		int stat = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);

			int i = 0;

			System.out.println("112211");
			i = 0;
			String updtQr = "";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (ps2 != null)
					ps2.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "";
	}

	// //=-------------------------GetpassNo Show-----------////////////////

	public String GetpassNo(HistoricalDataVerificationAction act,
			HistoricalDataVerificationdt dt) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String query = " select vch_gatepass_no from distillery.gatepass_to_manufacturewholesale_20_21"
					+ " where vch_gatepass_no='" + act.getGatapassno() + "' ";

			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				act.setGetpass_no(rs.getString("vch_gatepass_no"));

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return "";

	}

	// ////////------------------------------------chkgetpass-------------------------//////////

	public String chkgetpass(HistoricalDataVerificationAction act,
			HistoricalDataVerificationdt dt) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String query = " select getpass_no from  distillery.historical_for_distillery_export_as_unit"
					+ " where getpass_no='"
					+ act.getGatapassno()
					+ "' and distillery_id='" + act.getDist_id() + "' ";

			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				act.setSave_flg("T");

			} else {
				act.setSave_flg("F");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return "";

	}

	// //-----------------------------------------getpass detail
	// show--------------------------////
	public String GetpassDetailShow(HistoricalDataVerificationAction act,
			HistoricalDataVerificationdt dt) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = " select   coalesce((select currency_nm  FROM public.currency_list where curency_id=b.currency),'NA') as   currency_nm, coalesce(b.amount,0) as amount ," +
				"a.int_dist_id,a.vch_gatepass_no, a.dt_date,a.vch_from,a.vch_to,"
				+ " b.leo_date, b.leo_no, b.riceipt_date, b.brc_no, b.leo_no_of_bottel, b.brc_no_of_bottel, "
				+ " b.created_dt, b.brc_date, b.leo_pdf, "
				+ " b.brc_pdf, b.getpass_no, b.distillery_id, b.save_flg ,b.vch_bond, b.bond_utilization_value, b.vrification_created_date_ship,b.vrification_created_date_brc ,b.current_balnc "
				+ " from  "
				+ " distillery.gatepass_to_manufacturewholesale_20_21 a,"
				+ " distillery.historical_for_distillery_export_as_unit  b "
				+ " where b.getpass_no=a.vch_gatepass_no  "
				+
				// "and b.vrification_created_date is null  " +
				" and  a.vch_to='EOI' and a.int_dist_id='"
				+ act.getDist_id()
				+ "' and a.vch_gatepass_no='" + dt.getGetpass_no() + "' ";

		/*
		 * " select vch_gatepass_no from distillery.gatepass_to_manufacturewholesale_20_21"
		 * + " where vch_gatepass_no='"+act.getGatapassno()+"' ";
		 */

		System.out.println("===========GetpassDetailShow=====" + query);
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				System.out.println("===========hdfhjf=====" + act.getRadio());

				act.setShip_date(rs.getDate("leo_date"));
				act.setShip_number(rs.getString("leo_no"));
				act.setRiceipt_date(rs.getDate("riceipt_date"));
				act.setBrc_no(rs.getInt("brc_no"));
				act.setShip_no_of_bottel(rs.getInt("leo_no_of_bottel"));
				act.setBrc_no_bottel(rs.getInt("brc_no_of_bottel"));
				act.setCreated_date(rs.getDate("created_dt"));
				act.setBrc_dt(rs.getDate("brc_date"));
				act.setRecordFile(rs.getString("leo_pdf"));
				act.setRecordFile1(rs.getString("brc_pdf"));
				act.setGetpass_no(rs.getString("vch_gatepass_no"));
				act.setCurrency(rs.getString("currency_nm"));
				act.setAmount(rs.getString("amount"));

				if (act.getRadio().equalsIgnoreCase("V")) {
					act.setVch_bond(rs.getString("vch_bond"));
					System.out.println("===========hdfhjf====="
							+ act.getVch_bond());
					act.setBond_utilization_value(rs
							.getInt("bond_utilization_value"));
					if (act.getRadio().equalsIgnoreCase("S")){
					act.setCreated_date(rs.getDate("vrification_created_date_ship"));}
					if(act.getRadio().equalsIgnoreCase("B")){
						act.setCreated_date(rs.getDate("vrification_created_date_brc"));
					}
					
					System.out.println("===========hdfhjf====="
							+ act.getVch_bond());
					act.setCurrent_balance(rs.getInt("current_balnc"));
				}
			

				/*
				 * System.out.println("===========hdfhjf====="+act.getRadio());
				 * 
				 * act.setLeo_date(rs.getDate("leo_date"));
				 * act.setLeo_number(rs.getString("leo_no"));
				 * act.setRiceipt_date(rs.getDate("riceipt_date"));
				 * act.setBrc_no(rs.getInt("brc_no"));
				 * act.setLeo_no_of_bottel(rs.getInt("leo_no_of_bottel"));
				 * act.setBrc_no_bottel(rs.getInt("brc_no_of_bottel"));
				 * act.setCreated_date(rs.getDate("created_dt"));
				 * act.setBrc_dt(rs.getDate("brc_date"));
				 * act.setRecordFile(rs.getString("leo_pdf"));
				 * act.setRecordFile1(rs.getString("brc_pdf"));
				 * act.setGetpass_no(rs.getString("vch_gatepass_no"));
				 * 
				 * 
				 * }
				 */

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return "";

	}
}
