package com.mentor.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem; 

import com.mentor.Datatable.TransferOfGIN_betweenStorageVatDt;
import com.mentor.action.TransferOfGIN_betweenStorageVatAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class TransferOfGIN_betweenStorageVatImpl {

	public String getSugarmill(TransferOfGIN_betweenStorageVatAction ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = "SELECT int_app_id_f,vch_undertaking_name,vch_wrk_add  from  dis_mst_pd1_pd2_lic where vch_wrk_phon="
					+ ResourceUtil.getUserNameAllReq().trim();
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ac.setDissteleryName((rs.getString("vch_undertaking_name")));
				ac.setDissleryId(rs.getInt("int_app_id_f"));
				ac.setDisslryAdd(rs.getString("vch_wrk_add"));

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

	// ======================= get Vat No. List ===============

	public ArrayList getVatList(TransferOfGIN_betweenStorageVatAction action, int date) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		SelectItem item = new SelectItem();
		item.setValue("");
		item.setLabel("--Select--");
		list.add(item);
		String queryB = "";
		String queryC = "";
		try {
			queryB =" SELECT int_id, vch_tank_name "+
					" FROM distillery.gin_store_detail_distillery where int_distilleri_id ="+date+"";

			queryC = " SELECT int_id, vch_tank_name "+
					" FROM distillery.gin_store_detail_distillery where int_distilleri_id ="+date+"";
			
			/*queryB = "SELECT   b.int_id ,b.vch_tank_name  FROM  distillery.distillery_spirit_store_detail b"
					+ " where   b.int_distilleri_id = "
					+ date
					+ " and b.b_heavy_flag = 'T' and vat_def='S'  "
					+ " union SELECT   b.int_id ,b.vch_tank_name  FROM  distillery.distillery_spirit_store_detail b"
					+ " where   b.int_distilleri_id = "
					+ date
					+ " and vat_def='R' ";

			queryC = "SELECT   b.int_id ,b.vch_tank_name  FROM  distillery.distillery_spirit_store_detail b"
					+ " where b.int_distilleri_id= "
					+ date
					+ " and (b.b_heavy_flag != 'T' or b.b_heavy_flag is null) and vat_def='S' "
					+ " union SELECT   b.int_id ,b.vch_tank_name  FROM  distillery.distillery_spirit_store_detail b"
					+ " where b.int_distilleri_id= "
					+ date
					+ "   and vat_def='R'  ";
*/
			conn = ConnectionToDataBase.getConnection();

			if (action.getRadio().equalsIgnoreCase("B")) {

				pstmt = conn.prepareStatement(queryB);
				rs = pstmt.executeQuery();

			} else if (action.getRadio().equalsIgnoreCase("C")) {

				pstmt = conn.prepareStatement(queryC);
				rs = pstmt.executeQuery();

			}

			while (rs.next()) {
				item = new SelectItem();
				item.setValue(rs.getString("int_id"));
				item.setLabel(rs.getString("vch_tank_name"));
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

	// ======================= get Vat No. List ===============

	public ArrayList getToVat(TransferOfGIN_betweenStorageVatAction action, int date) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		SelectItem item = new SelectItem();
		item.setValue("");
		item.setLabel("--Select--");
		list.add(item);
		String queryB = "";
		String queryC = "";
		try {
			queryB = "SELECT   b.int_id ,b.vch_tank_name  FROM  distillery.distillery_spirit_store_detail b"
					+ " where b.int_distilleri_id= "
					+ date
					+ " and b.b_heavy_flag = 'T' and vat_def='S' union "
					+ "SELECT   b.int_id ,b.vch_tank_name  FROM  distillery.distillery_spirit_store_detail b"
					+ " where b.int_distilleri_id= "
					+ date
					+ "  and vat_def in ('I')  ";

			queryC = "SELECT   b.int_id ,b.vch_tank_name  FROM  distillery.distillery_spirit_store_detail b"
					+ " where b.int_distilleri_id= "
					+ date
					+ " and (b.b_heavy_flag != 'T' or b.b_heavy_flag is null)   and vat_def in ('S') "
					+ " union SELECT   b.int_id ,b.vch_tank_name  FROM  distillery.distillery_spirit_store_detail b"
					+ " where b.int_distilleri_id= "
					+ date
					+ "   and vat_def in ('I') ";
			System.out.println("in vatlist======" + queryC);

			conn = ConnectionToDataBase.getConnection();

			if (action.getRadio().equalsIgnoreCase("B")) {

				pstmt = conn.prepareStatement(queryB);
				rs = pstmt.executeQuery();

			} else if (action.getRadio().equalsIgnoreCase("C")) {

				pstmt = conn.prepareStatement(queryC);
				rs = pstmt.executeQuery();

			}

			while (rs.next()) {
				item = new SelectItem();
				item.setValue(rs.getString("int_id"));
				item.setLabel(rs.getString("vch_tank_name"));
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

	// =================================== denature Spirit ================

	public ArrayList getDenatureSpritList(
			TransferOfGIN_betweenStorageVatAction action, int date) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		SelectItem item = new SelectItem();
		item.setValue("");
		item.setLabel("--Select--");
		list.add(item);
		String query = "";
		try {

			query =

			"	select int_id, int_distilleri_id, vch_tank_name, double_tank_capacity from "
					+ "      distillery.distillery_denatures_spirit_store_detail where int_distilleri_id ="
					+ date;

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setValue(rs.getString("int_id"));
				item.setLabel(rs.getString("vch_tank_name"));
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

	// =========================== close denature Spirit
	// ==========================

	// =================== Get Quantity =======================

	public double getQuantity(TransferOfGIN_betweenStorageVatAction ac, String val) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		ArrayList list = new ArrayList();

		double quantity = 0.0;
		try {

		query =" SELECT int_id, int_distilleri_id, vch_tank_name,  coalesce(openingal,0) as openingal,  coalesce(openingbl,0) as openingbl, consumed_strength, "+ 
			   " coalesce(recieved_bl,0) as recieved_bl, coalesce(recieved_al,0) as recieved_al, coalesce(consumed_al,0) as consumed_al,  "+
			   " coalesce(consumed_bl,0) as consumed_bl FROM distillery.gin_store_detail_distillery " +
			   "where int_id="+Integer.parseInt(val)+" and int_distilleri_id ="+ ac.getDissleryId();
					
					
					
				/*	"SELECT b.openingbl,b.strength,b.openingal,a.received_bl ,a.consumed_bl,a.consumed_al,a.consumed_strength,a.recieved_al "
					+ " from distillery.distillery_spirit_store_detail b"
					+ " left outer join  distillery.spirit_vat a on b.int_id=a.vat_id "
					+ "and a.int_dist_id=b.int_distilleri_id "
					+ "where  b.int_id="
					+ Integer.parseInt(val)
					+ " and b.int_distilleri_id=" + ac.getDissleryId();*/

			System.out.println("======finalal====" + query);

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			if (rs.next()) {

				NumberFormat formatter = new DecimalFormat("#0.00");

				ac.setQuantityFinal(Double.parseDouble(formatter.format((rs.getDouble("recieved_bl") - rs.getDouble("consumed_bl") + rs.getDouble("openingbl")))));
				ac.setQuantityFinalal(Double.parseDouble(formatter.format((rs.getDouble("recieved_al") - rs.getDouble("consumed_al") + rs.getDouble("openingbl")))));
				ac.setQuantityStrength((Double.parseDouble(formatter.format((rs.getDouble("recieved_al") - rs.getDouble("consumed_al") + rs.getDouble("openingbl")))) * 100)
						/ Double.parseDouble(formatter.format((rs.getDouble("recieved_bl") - rs.getDouble("consumed_bl")))));

				// ac.setQuantityFinal(rs.getDouble("received_bl")-
				// rs.getDouble("consumed_bl"));
				// ac.setQuantityFinalal(rs.getDouble("recieved_al")-
				// rs.getDouble("consumed_al"));

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
		return quantity;
	}

	// =================== Get Quantity =======================

	public double getQuantitySeconend(TransferOfGIN_betweenStorageVatAction ac,
			String val) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		ArrayList list = new ArrayList();

		double quantity = 0.0;
		try {

			query = " SELECT coalesce(b.double_tank_capacity,0) as double_tank_capacity,b.openingbl,b.strength,b.openingal,a.received_bl ,a.consumed_bl,a.consumed_al,a.consumed_strength,a.recieved_al "
					+ " from distillery.distillery_spirit_store_detail b"
					+ " left outer join  distillery.spirit_vat a on b.int_id=a.vat_id "
					+ "and a.int_dist_id=b.int_distilleri_id "
					+ "where  b.int_id="
					+ Integer.parseInt(val)
					+ " and b.int_distilleri_id=" + ac.getDissleryId();

			System.out.println("===getQuantitySeconend=" + query);
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				ac.setTank_capacity(rs.getDouble("double_tank_capacity"));
				ac.setToVatBl(rs.getDouble("received_bl")
						- rs.getDouble("consumed_bl"));
				if ((rs.getDouble("received_bl") - rs.getDouble("consumed_bl")) == 0) {
					ac.setToVatAl(0);
					ac.setToVatAlStrength(0);

				} else {
					ac.setTank_capacity(rs.getDouble("double_tank_capacity"));
					ac.setToVatAl(rs.getDouble("recieved_al")
							- rs.getDouble("consumed_al"));
					ac.setToVatAlStrength(((rs.getDouble("recieved_al") - rs
							.getDouble("consumed_al")) * 100)
							/ (rs.getDouble("received_bl") - rs
									.getDouble("consumed_bl")));
				}
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
		return quantity;
	}

	// ===================== get duty payble Charges ==================

	public double getDutyPayble() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";

		double duty = 0.0;
		try {
			query = "SELECT int_charge from distillery.spirit_removable_fee where  int_fee_id=1 ";
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				duty = rs.getDouble("int_charge");
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
		return duty;
	}

	// ===================== get Payble payble Charges ==================

	public double getPurchasePayble() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";

		double purchase = 0.0;
		try {
			query = "SELECT int_charge from distillery.spirit_removable_fee where  int_fee_id=2 ";
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				purchase = rs.getDouble("int_charge");
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
		return purchase;
	}

	// ============================= save Data=======================

	public String saveData(TransferOfGIN_betweenStorageVatAction action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		int seq =this.getMaxId()+1;
		System.out.println("===String time===" + seq);

		try {

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(cal.getTime());
			System.out.println("===String time===" + time);

			String query =

			" INSERT INTO distillery.transfer_of_gin_from_one_vat_to_other( "
			+ "	int_transfer_id, int_from_vat_id, dob_from_bl, dob_from_al, "
			+ "   int_to_vat_id,dob_to_vat_bl,dob_to_vat_al, dob_qunty_transfer_bl, dob_qunty_transfer_al, "
			+ "     wastage_bl, date_created, int_distillery_id,net_bl,net_al,wastage_al,b_heavy_flag,"
			+ "to_qty_as_pr_dpt_al,to_qty_as_pr_dpt_bl,from_qty_as_pr_dpt_al,from_qty_as_pr_dpt_bl,dob_from_strength,dob_to_vat_strength,dt_time) "
			+ "	VALUES ('"+seq+"', '"+action.getVatNo()+"', '"+action.getQuantityFinal()+"', " +
			"'"+action.getQuantityFinalal()+"'," +
			" '"+action.getToVatId()+"', '"+action.getToVatBl()+"', '"+action.getToVatAl()+"', " +
			"'"+action.getTranfr_quntity_bl()+"', '"+action.getTranfr_quntity_al()+"', '"+action.getTrns_wst_bl()+"', " +
		   "'"+Utility.convertUtilDateToSQLDate(action.getCueentDate())+"', '"+action.getDissleryId()+"', " +
			"'"+action.getRecv_quntity_bl()+"'," +
			" '"+action.getRecv_quntity_al()+"', '"+action.getTrns_wst_al()+"', ?, " +
			"'"+action.getTo_dip_al()+"','"+action.getTo_dip_bl()+"', '"+action.getFrom_dip_al()+"', " +
			"'"+action.getFrom_dip_bl()+"', '"+action.getQuantityStrength()+"', '"+action.getToVatAlStrength()+"','"
					+ time + "')";
			
			System.out.println("====saveData1======"+query);
			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(query);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			if (action.getRadio().equalsIgnoreCase("B")) {

				pstmt.setString(1, "T");

			} else if (action.getRadio().equalsIgnoreCase("C")) {

				pstmt.setString(1, "F");

			}
			/*pstmt.setInt(1, seq);
			pstmt.setInt(2, Integer.parseInt(action.getVatNo()));
			pstmt.setDouble(3, action.getQuantityFinal());
			pstmt.setDouble(4, action.getQuantityFinalal());
			pstmt.setInt(5, Integer.parseInt(action.getToVatId()));
			pstmt.setDouble(6, action.getToVatBl());
			pstmt.setDouble(7, action.getToVatAl());

			// pstmt.setDouble(8, action.getQuntityForDinaturingbl());
			pstmt.setDouble(8, action.getTranfr_quntity_bl());
			pstmt.setDouble(9, action.getTranfr_quntity_al());
			// pstmt.setDouble(9, action.getQuntityForDinaturingal());
			// pstmt.setDouble(10, action.getQuntityForDinaturingstregth());
			pstmt.setDouble(10, action.getTrns_wst_bl());
			pstmt.setDate(11,
					Utility.convertUtilDateToSQLDate(action.getCueentDate()));
			pstmt.setInt(12, action.getDissleryId());
			pstmt.setDouble(13, action.getRecv_quntity_bl());

			pstmt.setDouble(14, action.getRecv_quntity_al());
			pstmt.setDouble(15, action.getTrns_wst_al());

			if (action.getRadio().equalsIgnoreCase("B")) {

				pstmt.setString(16, "T");

			} else if (action.getRadio().equalsIgnoreCase("C")) {

				pstmt.setString(16, "F");

			}
			pstmt.setDouble(17, action.getTo_dip_al());
			pstmt.setDouble(18, action.getTo_dip_bl());
			pstmt.setDouble(19, action.getFrom_dip_al());
			pstmt.setDouble(20, action.getFrom_dip_bl());
			pstmt.setDouble(21, action.getQuantityStrength());
			pstmt.setDouble(22, action.getToVatAlStrength());*/

			// pstmt.setString(11,ResourceUtil.getUserNameAllReq());
			// pstmt.setString(12, dateFormat.format(new Date()) );

			saveStatus = pstmt.executeUpdate();

			if (saveStatus > 0) {
				saveStatus = 0;

				query =
			
			/*			SELECT int_id, int_distilleri_id, vch_tank_name, double_tank_capacity, openingal, openingbl, strength, recieved_bl, 
                       recieved_al, consumed_al, consumed_bl
	                      FROM distillery.gin_store_detail_distillery;
	                      
	                      *
	                      		"INSERT INTO distillery.spirit_vat(	recieved_al, received_strength,received_bl,consumed_bl,  int_dist_id, vat_id, int_season_id, "
						+ "consumed_al, consumed_strength)	VALUES (0,0,0,  ?, ?,?, (SELECT   sesn_id::int  FROM public.mst_season where  active = 'a'),   ?, ?) ON CONFLICT ON CONSTRAINT spirit_vat_pkey "
						+ " do update set consumed_al= COALESCE(distillery.spirit_vat.consumed_al,0.0) + "
						+ action.getTranfr_quntity_al()
						+ ", consumed_strength= COALESCE(distillery.spirit_vat.consumed_strength,0.0) +"
						+ action.getStrength()
						+ ","
						+ " consumed_bl= COALESCE(distillery.spirit_vat.consumed_bl,0.0) +"
						+ action.getTranfr_quntity_bl() + " ";*/

				"INSERT INTO distillery.gin_store_detail_distillery(	recieved_al, recieved_strength,recieved_bl,consumed_bl,  int_distilleri_id, int_id,  "
						+ "consumed_al, consumed_strength)	VALUES (0,0,0,'"+action.getTranfr_quntity_bl()+"', '"+action.getDissleryId()+"','"+action.getVatNo()+"',   '"+action.getTranfr_quntity_al()+"', '"+action.getStrength()+"') ON CONFLICT ON CONSTRAINT gin_store_detail_distillery_pkey "
						+ " do update set consumed_al= COALESCE(distillery.gin_store_detail_distillery.consumed_al,0.0) + "
						+ action.getTranfr_quntity_al()
						+ ", consumed_strength= COALESCE(distillery.gin_store_detail_distillery.consumed_strength,0.0) +"
						+ action.getStrength()
						+ ","
						+ " consumed_bl= COALESCE(distillery.gin_store_detail_distillery.consumed_bl,0.0) +"
						+ action.getTranfr_quntity_bl() + " ";
				
			System.out.println("====saveData2======"+query);
				pstmt = conn.prepareStatement(query);

		/*		pstmt.setDouble(1, action.getTranfr_quntity_bl());
				pstmt.setDouble(4, action.getTranfr_quntity_al());
				pstmt.setDouble(5, action.getStrength());
				pstmt.setInt(2, action.getDissleryId());
				pstmt.setInt(3, Integer.parseInt(action.getVatNo()));

				System.out.println("====getTranfr_quntity_bl======"
						+ action.getTranfr_quntity_bl());
				System.out.println("====getTranfr_quntity_al======"
						+ action.getTranfr_quntity_al());
				System.out.println("====getDissleryId======"
						+ action.getDissleryId());
				System.out.println("====getVatNo======" + action.getVatNo());*/
				// System.out.println("====saveData2======"+query);

				// pstmt.setInt(1,action.getDissleryId());
				// pstmt.setInt(2,Integer.parseInt(action.getVatNo()));
				saveStatus = pstmt.executeUpdate();
			}

			if (saveStatus > 0) {
				saveStatus = 0;

				query =

				/*
				 * "update  distillery.spirit_vat set recieved_al=recieved_al+"+
				 * action.getNetAl() +" " +
				 * ", received_bl=received_bl+"+action.getNetBl()+" " +
				 * " where int_dist_id=? and vat_id=? and int_season_id= (SELECT   sesn_id::int  FROM public.mst_season where  active = 'a')"
				 * ;
				 */

				"INSERT INTO distillery.spirit_vat(	consumed_al, consumed_strength,consumed_bl,received_bl,  int_dist_id, vat_id, int_season_id, "
						+ "recieved_al, received_strength)	VALUES (0,0,0,  ?, ?,?, (SELECT   sesn_id::int  FROM public.mst_season where  active = 'a'),   ?, ?) ON CONFLICT ON CONSTRAINT spirit_vat_pkey "
						+ " do update set recieved_al= COALESCE(distillery.spirit_vat.recieved_al,0.0) + "
						+ action.getRecv_quntity_al()
						+ ", consumed_strength= COALESCE(distillery.spirit_vat.consumed_strength,0.0) +"
						+ action.getStrength()
						+ ","
						+ " received_bl= COALESCE(distillery.spirit_vat.received_bl,0.0) +"
						+ action.getRecv_quntity_bl() + " ";
			System.out.println("====saveData3======"+query);
				pstmt = conn.prepareStatement(query);

				pstmt.setDouble(1, action.getRecv_quntity_bl());
				pstmt.setDouble(4, action.getRecv_quntity_al());
				pstmt.setDouble(5, action.getStrength());
				pstmt.setInt(2, action.getDissleryId());
				pstmt.setInt(3, Integer.parseInt(action.getToVatId()));

				System.out.println("====getRecv_quntity_bl======"
						+ action.getRecv_quntity_bl());
				System.out.println("====getRecv_quntity_al======"
						+ action.getRecv_quntity_al());
				System.out.println("====getDissleryId======"
						+ action.getDissleryId());
				System.out
						.println("====getToVatId======" + action.getToVatId());
				// pstmt.setInt(1,action.getDissleryId());
				// pstmt.setInt(2,Integer.parseInt(action.getToVatId()));

				saveStatus = pstmt.executeUpdate();
			}
			if (saveStatus > 0) {
				saveStatus = 0;

				query = " INSERT INTO distillery.vat_wastage(vat_no, vat_book_val_bl, vat_book_val_al, vat_wastage_bl, "
						+ "vat_wastage_al,"
						+ " txn_date, vat_type,unit_id,ref_id, type,  vat_des)VALUES  "
						+ " ('"
						+ Integer.parseInt(action.getToVatId())
						+ "', '"
						+ action.getToVatBl()
						+ "', "
						+ "'"
						+ action.getToVatAl()
						+ "', '"
						+ action.getTrns_wst_bl()
						+ "', "
						+ "'"
						+ action.getTrns_wst_al()
						+ "', '"
						+ Utility.convertUtilDateToSQLDate(action
								.getCueentDate())
						+ "',"
						+ " ?, '"
						+ action.getDissleryId()
						+ "', "
						+ " ?, 'GIN_TRANSFER_WASTAGE', 'F')";

				System.out.println("====getTrns_wst_bl======"
						+ action.getTrns_wst_bl());
				System.out.println("====getTrns_wst_al======"
						+ action.getTrns_wst_al());
				System.out.println("====getDissleryId======"
						+ action.getDissleryId());
				System.out
						.println("====getToVatId======" + action.getToVatId());

				pstmt = conn.prepareStatement(query);
				if (action.getRadio().equalsIgnoreCase("B")) {

					pstmt.setString(1, "T");

				} else if (action.getRadio().equalsIgnoreCase("C")) {

					pstmt.setString(1, "F");

				}
				pstmt.setInt(2, this.getMaxId() + 1);
				saveStatus = pstmt.executeUpdate();
			}
			if (saveStatus > 0) {

				conn.commit();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Saved Data", "Saved Data"));

				action.reset();

			} else {

				// action.reset();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								" Data NOT_SAVED", "Data NOT_SAVED"));
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);

			}
		} catch (Exception e) {
			ResourceUtil.addErrorMessage(e.getMessage(), e.getMessage());
			// e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();

				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		return "";
	}

public double fetchopening(TransferOfGIN_betweenStorageVatAction act) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		double val = 0.0;

		try {

			con = ConnectionToDataBase.getConnection();
			String query = "SELECT int_open_stock -int_issue as opng FROM distillery.removal_spirit_distillery"
					+ " WHERE  date_crr_date=(select MAX(date_crr_date)from distillery.removal_spirit_distillery where int_distillery_code="
					+ act.getDissleryId()
					+ " AND vch_vat_no="
					+ Integer.parseInt(act.getTankNo())
					+ ") AND int_distillery_code="
					+ act.getDissleryId()
					+ " AND vch_vat_no="
					+ Integer.parseInt(act.getTankNo())
					+ "";

			ps = con.prepareStatement(query);

			rs = ps.executeQuery();
			if (rs.next()) {

				val = rs.getDouble("opng");

			}

		} catch (Exception e) {
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
		}
		return val;

	}

	// ==============get MAX Challan Id====================

	public int getMaxId() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int maxid = 0;

		try {
			String query = "SELECT max(int_transfer_id) maxid from distillery.transfer_of_GIN_from_one_vat_to_other ";

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxid = rs.getInt("maxid");
			}

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			try {

				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		return maxid;
	}

	// ========= show DataTable =============

	public ArrayList getShowData(TransferOfGIN_betweenStorageVatAction action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		int j = 1;
		try {
			String query =

			"     SELECT a.int_transfer_id, a.int_from_vat_id, a.dob_from_bl, a.dob_from_al, "
					+ "     a.int_to_vat_id,b.vch_tank_name, a.dob_qunty_transfer_bl, a.dob_qunty_transfer_al,  "
					+ "    a.dob_qunty_transfer_strength, a.wastage_bl, a.date_created,  "

					+ "  case when a.b_heavy_flag='T' then 'B-Heavy' when a.b_heavy_flag='F' then 'C-Heavy' "
					+ " when a.b_heavy_flag is null then 'C-Heavy' end as status,"

					+ "    a.int_distillery_id, a.dob_to_vat_bl, a.dob_to_vat_al, a.net_bl,a.wastage_al,a.net_al "
					+ "    	FROM distillery.transfer_of_gin_from_one_vat_to_other a ,  "
					+ "        distillery.distillery_spirit_store_detail b "
					+ "        where a.int_to_vat_id=b.int_id  "
					+ "       and  a.int_distillery_id = "
					+ action.getDissleryId()
					+ " and a.date_created >= '2020-4-01' order by  a.date_created  desc";

		System.out.println("------- show data table   -------"+query);

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TransferOfGIN_betweenStorageVatDt dataTable = new TransferOfGIN_betweenStorageVatDt();
				dataTable.setSrNoTabel(j++);

				dataTable.setDateTable(rs.getDate("date_created"));
				dataTable.setDisisileriNameTabel(action.getDissteleryName());

				dataTable.setToVatTable(rs.getString("vch_tank_name"));
				dataTable.setQuantityToBeTransferBLTable(rs
						.getDouble("dob_qunty_transfer_bl"));
				dataTable.setQuantityToBeTransferALTable(rs
						.getDouble("dob_qunty_transfer_al"));
				dataTable.setWastageTable(rs.getDouble("wastage_bl"));
				dataTable.setWastageAlTable(rs.getDouble("wastage_al"));
				dataTable.setNetBLTable(rs.getDouble("net_bl"));
				dataTable.setNetALTable(rs.getDouble("net_al"));
				dataTable.setvType(rs.getString("status"));

				list.add(dataTable);
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

	public int getMaxIdDuty() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int maxid = 0;

		try {
			String query = "SELECT max(int_transfer_id) from distillery.transfer_of_spirit_from_one_vat_to_other ";
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

	// ---------------------------------------------------------------------------
	public String getStock(TransferOfGIN_betweenStorageVatAction act) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String path = null;

		try {
			con = ConnectionToDataBase.getConnection();

			String queryList = " select vch_tank_name as from_vat , "
					+ " (select vch_tank_name from distillery.distillery_spirit_store_detail "
					+ "  where int_distilleri_id='"
					+ act.getDissleryId()
					+ "' and int_id="
					+ act.getToVatId()
					+ ")  as to_vat "
					+ " FROM  distillery.distillery_spirit_store_detail where int_distilleri_id='"
					+ act.getDissleryId() + "' and int_id=" + act.getVatNo()
					+ " ";

			System.out.println("getObjectionReplies===" + queryList);
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				// System.out.println("path---------------------------"+path);

				act.setVat_f(rs.getString("from_vat"));
				act.setVat_to(rs.getString("to_vat"));
				// act.setCosum_al(act.getTranfr_quntity_al());
				// act.setCosum_bl(act.getTranfr_quntity_bl());
				// act.setRecv_al(act.getRecv_quntity_al());
				// act.setRecv_bl(act.getRecv_quntity_bl());
				// act.setWst_al(act.getWastageAl());
				// act.setWst_bl(act.getWastageBl());

			}

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
		return "";

	}

}
