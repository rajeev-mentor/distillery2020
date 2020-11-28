package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import com.mentor.Datatable.Transfer_Of_Sprit_To_Blending_VatDt;
import com.mentor.action.FL3_3A_SpiritpurchaseAction;
import com.mentor.action.Transfer_Of_Sprit_To_Blending_VatAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class Transfer_Of_Sprit_To_Blending_VatImpl {

	// ======================get hidden method distillery lg================

	public String getSugarmill(Transfer_Of_Sprit_To_Blending_VatAction ac) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = "SELECT int_app_id_f,vch_undertaking_name,vch_wrk_add  from  dis_mst_pd1_pd2_lic where vch_wrk_phon="
					+ ResourceUtil.getUserNameAllReq().trim();

			 System.out.println("getSugarmill==="+queryList);
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ac.setDistillri_nm(rs.getString("vch_undertaking_name"));
				ac.setDistillri_id(rs.getInt("int_app_id_f"));
				ac.setDistillri_addrss(rs.getString("vch_wrk_add"));
			}

			// pstmt.executeUpdate();
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

	public ArrayList getblendingStoragelist(
			Transfer_Of_Sprit_To_Blending_VatAction action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		String sql = "SELECT  storage_id, recieve_bl, recieve_al, storage_desc "
				+ "FROM distillery.spirit_for_bottling where int_distillery_id="
				+ action.getDistillri_id();

		SelectItem item = new SelectItem();
		item.setLabel("SELECT");
		item.setValue(0);
		list.add(item);

		try {
			 System.out.println("=getblendingStoragelist===="+sql);
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setValue(rs.getInt("storage_id"));
				item.setLabel(rs.getString("storage_desc"));
				list.add(item);
			}

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return list;
	}

	public double getBlendingdata(int vat,
			Transfer_Of_Sprit_To_Blending_VatAction action) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		ArrayList list = new ArrayList();
		double al = 0.0;
		try {
			query = " SELECT (coalesce(recieve_bl,0)-coalesce(consumed_bl,0)) as bl,"
					+ " (coalesce(recieve_al,0)-coalesce(consumed_al,0)) as al, storage_id, storage_desc "
					+ " FROM distillery.spirit_for_bottling WHERE storage_id="
					+ vat
					+ " AND  int_distillery_id='"
					+ action.getDistillri_id() + "'";

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				DecimalFormat formatter = new DecimalFormat("#.##");
				// System.out.println("query=="+query);

				// NumberFormat formatter = new DecimalFormat("#");

				action.setBlending_bl(rs.getDouble("bl"));
				action.setBlending_al(rs.getDouble("al"));
				/*
				 * if (((rs.getDouble("al") / rs.getDouble("bl")) * 100) < 0 ||
				 * ((rs.getDouble("al") / rs.getDouble("bl")) * 100) > 100) {
				 * action.setBlend(42.8); } else {
				 * action.setBlendingstrength(Double.parseDouble(formatter
				 * .format((rs.getDouble("al") / rs.getDouble("bl")) * 100))); }
				 */
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
				// e.printStackTrace();
			}
		}
		return al;
	}

	public ArrayList getSpiritVatList(
			Transfer_Of_Sprit_To_Blending_VatAction act) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		SelectItem item = new SelectItem();
		item.setValue(0);
		item.setLabel("--Select--");
		list.add(item);
		String query = "";
		try {

			query = " select distinct a.vch_tank_name,coalesce(a.vat_type ,'NA') as vat_type ,a.int_id  FROM distillery.distillery_spirit_store_detail a where a.int_distilleri_id="
					+ act.getDistillri_id() + " order by a.vch_tank_name";

			 System.out.println("vat list"+query);

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				item = new SelectItem();

				item.setValue(rs.getInt("int_id"));
				item.setLabel(rs.getString("vch_tank_name") + " - "
						+ rs.getString("vat_type"));
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
				// e.printStackTrace();
			}
		}
		return list;
	}

	public double getQuantityStrength(int vat,
			Transfer_Of_Sprit_To_Blending_VatAction action) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		ArrayList list = new ArrayList();
		double al = 0.0;
		try {
			query =

			"SELECT (coalesce(a.received_bl,0)-coalesce(a.consumed_bl,0)) as quant, "
					+ " (coalesce(a.recieved_al,0)-coalesce(a.consumed_al,0)) as al,a.consumed_strength "
					+ " from distillery.distillery_spirit_store_detail b "
					+ "  left outer join distillery.spirit_vat a on    b.int_distilleri_id=a.int_dist_id and b.int_id=a.vat_id "
					+ "  where " + " b.int_distilleri_id="
					+ action.getDistillri_id() + " and b.int_id=" + vat;

			 System.out.println("==getQuantityStrength=="+query);

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				DecimalFormat formatter = new DecimalFormat("#.##");
				// //System.out.println("rs.getDouble quant "+rs.getDouble("quant")+" al "+rs.getDouble("al")+"strength  "+(rs.getDouble("al")/rs.getDouble("quant"))*100);

				// NumberFormat formatter = new DecimalFormat("#");
				// action.setStrength(Double.parseDouble(formatter.format((rs.getDouble("al")/rs.getDouble("quant"))*100)));
				action.setSpirt_bl(rs.getDouble("quant"));
				action.setSpirt_al(rs.getDouble("al"));
			} else {
				// action.setStrength(0.0);
				action.setSpirt_bl(0.0);
				action.setSpirt_bl(0.0);
			}
		} catch (Exception e) {

			// action.setStrength(0.0);
			action.setSpirt_bl(0.0);
			action.setSpirt_bl(0.0);
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
		return al;
	}

	// ============================= save Data=======================

	public String saveData(Transfer_Of_Sprit_To_Blending_VatAction action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		int seq = this.getMaxId() + 1;
		// System.out.println("====update========"+seq);
		try {

			String query = " INSERT INTO distillery.spirit_to_blending_vat( "
					+ " 		blend_vat_no, spirit_vat_no, spirit_vat_bl, spirit_vat_al, txn_date, blend_vat_bl_bookval, "
					+ " 		blend_vat_al_bookval, reading_bef_trxn, reading_aft_trxn, created_date, int_id, distillery_id, "
					+ " 		trxn_al, trxn_bl,login_user,unit_type) "
					+ " 		VALUES ('"
					+ action.getBlend_vat_no()
					+ "', '"
					+ action.getSpirt_no()
					+ "', '"
					+ action.getSpirt_bl()
					+ "', "
					+ "  '"
					+ action.getSpirt_al()
					+ "', '"
					+ Utility.convertUtilDateToSQLDate(action.getCurrentDate())
					+ "', "
					+ " '"
					+ action.getBlending_bl()
					+ "', '"
					+ action.getBlending_al()
					+ "', '"
					+ action.getBlending_al_bfr()
					+ "', "
					+ " '"
					+ action.getBlending_al_aftr()
					+ "', '"
					+ Utility.convertUtilDateToSQLDate(new Date())
					+ "', '"
					+ seq
					+ "', '"
					+ action.getDistillri_id()
					+ "', '"
					+ action.getBlending_al_trnfr()
					+ "', "
					+ "'"
					+ action.getBlending_bl_trnfr()
					+ "', '"
					+ ResourceUtil.getUserNameAllReq().trim() + "', 'D')";

			// System.out.println("saveData======="+query);

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(query);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			saveStatus = pstmt.executeUpdate();
			if (saveStatus > 0) {
				String queryspiritvat = " UPDATE distillery.spirit_vat SET consumed_bl='"
						+ action.getSpritvat_bl_up()
						+ "',  consumed_al='"
						+ action.getSpritvat_al_up()
						+ "'  WHERE int_dist_id='"
						+ action.getDistillri_id()
						+ "' and vat_id='"
						+ action.getSpirt_no() + "' ";

			//	System.out.println("queryspiritvat=======" + queryspiritvat);
				pstmt = conn.prepareStatement(queryspiritvat);

				saveStatus = pstmt.executeUpdate();
			}
			if (saveStatus > 0) {
				String queryblendingvat = " UPDATE distillery.spirit_for_bottling SET recieve_bl='"
						+ action.getBlending_vat_bl_up()
						+ "', recieve_al='"
						+ action.getBlending_vat_al_up()
						+ "' WHERE 	int_distillery_id='"
						+ action.getDistillri_id()
						+ "' and storage_id='"
						+ action.getBlend_vat_no() + "' ";

				//System.out.println("queryblendingvat=======" + queryblendingvat);
				pstmt = conn.prepareStatement(queryblendingvat);

				saveStatus = pstmt.executeUpdate();
			}
			if (saveStatus > 0) {

				conn.commit();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"  RECORD SAVED successfully.  !!! ",
								"  RECORD SAVED successfully.  !!!"));
				action.reset();
				// action.setFinalflg(true);

			} else {

				// action.reset();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							" RECORD NOT SAVED !!!!!!",
							"RECORD NOT SAVED !!!!!!"));
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
				// e.printStackTrace();
			}
		}
		return "";
	}

	// ==============get MAX Challan Id====================

	public int getMaxId() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int maxid = 0;

		try {
			String query = "SELECT max(int_id) maxid from distillery.spirit_to_blending_vat ";

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

	// ==========================save show data
	public ArrayList getsaveData(Transfer_Of_Sprit_To_Blending_VatAction ac) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 1;
		// ac.setListFlagForPrint(false);

		String sql = " SELECT c.storage_desc,b.vch_tank_name, a.trxn_al, a.trxn_bl,a.txn_date "
				+ " FROM distillery.spirit_to_blending_vat a,distillery.distillery_spirit_store_detail b, "
				+ " distillery.spirit_for_bottling c where c.storage_id=a.blend_vat_no and b.int_id=a.spirit_vat_no"
				+ " and unit_type='D' and"
				+ " a.distillery_id ='"
				+ ac.getDistillri_id() + "' ";

		/*
		 * " SELECT c.storage_desc,b.vch_tank_name, a.trxn_al, a.trxn_bl,a.txn_date "
		 * +
		 * " FROM distillery.spirit_to_blending_vat a,distillery.distillery_spirit_store_detail b, "
		 * +
		 * " distillery.spirit_for_bottling c where c.storage_id=a.blend_vat_no and b.int_id=a.spirit_vat_no and"
		 * + " a.distillery_id ='"+ac.getDistillri_id()+"' ";
		 */

		// System.out.println("===getsaveData=="+sql);

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				Transfer_Of_Sprit_To_Blending_VatDt dt = new Transfer_Of_Sprit_To_Blending_VatDt();
				// dt.setSrno_s(i);
				dt.setTrans_date_dt(rs.getDate("txn_date"));
				dt.setFrom_vat_dt(rs.getString("storage_desc"));
				dt.setTo_vat_dt(rs.getString("vch_tank_name"));
				dt.setTrans_al(rs.getString("trxn_al"));
				dt.setTrans_bl(rs.getString("trxn_bl"));

				i++;
				list.add(dt);
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
		return list;
	}

	// //////////////////////////////////////////fl3////////////////////////////////////
	public String getDetails(Transfer_Of_Sprit_To_Blending_VatAction ac) {

		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;

		try {
			con = ConnectionToDataBase.getConnection();

			String queryList = " SELECT x.app_id, x.distillery_id, x.license_district, x.license_no, x.vch_licence_type,               "
					+ " x.distilery_district, x.unit_name, x.unit_adrs      "
					+ " FROM                                                                                                  "
					+ " (SELECT a.int_app_id as app_id, a.int_distillery_id as distillery_id, a.district as license_district, "
					+ " a.vch_license_fl3a as license_no, a.vch_licence_type, b.vch_unit_dist as distilery_district,          "
					+ " a.vch_unit_name as unit_name, a.vch_unit_addrs as unit_adrs "
					+ " FROM licence.fl3a_fl1a a, public.dis_mst_pd1_pd2_lic b                              "
					+ " WHERE a.int_distillery_id=b.int_app_id_f AND a.vch_lic_unit_type='D'                                  "
					+ " AND a.vch_licence_type='FL3A' AND a.loginuser='"
					+ ResourceUtil.getUserNameAllReq().trim()
					+ "'          "
					+ " UNION                                                                                                 "
					+ " SELECT a.int_lic_id as app_id, a.int_distillery_id as distillery_id, a.district as license_district,  "
					+ " a.vch_licence_no as license_no, a.vch_licence_type, b.vch_unit_dist as distilery_district,            "
					+ " a.vch_unit_name as unit_name, a.vch_unit_addrs as unit_adrs "
					+ " FROM licence.licence_entery_fl3_fl1 a, public.dis_mst_pd1_pd2_lic b                 "
					+ " WHERE a.int_distillery_id=b.int_app_id_f AND a.vch_lic_unit_type='D'                                  "
					+ " AND a.vch_licence_type='FL3' AND a.loginuser='"
					+ ResourceUtil.getUserNameAllReq().trim() + "' )x ";

			pstmt = con.prepareStatement(queryList);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ac.setFl3_distillri_nm(rs.getString("license_no"));
				ac.setFl3_distillri_id(rs.getInt("distillery_id"));
				ac.setFl3_distillri_addrss(rs.getString("vch_licence_type"));

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
		return "";

	}

	public double getfl3_QuantityStrength(int vat,
			Transfer_Of_Sprit_To_Blending_VatAction action) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		ArrayList list = new ArrayList();
		double al = 0.0;
		try {
			query =

			"SELECT (coalesce(a.recieve_bl,0)-coalesce(a.consumed_bl,0)) as bl, "
					+ " (coalesce(a.recieve_al,0)-coalesce(a.consumed_al,0)) as al  "
					+ " from  distillery.fl3_3a_vat_master a "
					+ "  where vat_type='S' and " + " a.distillery_id='"
					+ action.getFl3_distillri_id() + "' and a.lic_no='"
					+ action.getFl3_distillri_nm()
					+ "' and a.verified is true and a.storage_id=" + vat;
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				action.setFl3_spirt_bl(rs.getString("bl"));
				action.setFl3_spirt_al(rs.getString("al"));
			} else {

				action.setFl3_spirt_bl("0");
				action.setFl3_spirt_bl("0");
			}
		} catch (Exception e) {

			action.setFl3_spirt_bl("0");
			action.setFl3_spirt_bl("0");

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
		return al;
	}

	public ArrayList getfl3_blendingStoragelist(
			Transfer_Of_Sprit_To_Blending_VatAction act) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		SelectItem item = new SelectItem();
		item.setValue(0);
		item.setLabel("--Select--");
		list.add(item);
		String query = "";
		try {

			query = " select distinct a.storage_desc,coalesce(a.vat_type ,'NA') as vat_type ,a.storage_id  FROM distillery.fl3_3a_vat_master a where a.distillery_id='"
					+ act.getFl3_distillri_id()
					+ "' and a.vat_type='BL' and lic_no='"
					+ act.getFl3_distillri_nm() + "' order by a.storage_desc";

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				item = new SelectItem();

				item.setValue(rs.getInt("storage_id"));
				item.setLabel(rs.getString("storage_desc"));
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
				// e.printStackTrace();
			}
		}
		return list;
	}

	public double getfl3_Blendingdata(int vat,
			Transfer_Of_Sprit_To_Blending_VatAction action) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		ArrayList list = new ArrayList();
		double al = 0.0;
		try {
			query = "SELECT (coalesce(a.recieve_bl,0)-coalesce(a.consumed_bl,0)) as bl, "
					+ " (coalesce(a.recieve_al,0)-coalesce(a.consumed_al,0)) as al  "
					+ " from  distillery.fl3_3a_vat_master a "
					+ "  where vat_type='BL' and "
					+ " a.distillery_id='"
					+ action.getFl3_distillri_id()
					+ "' and a.lic_no='"
					+ action.getFl3_distillri_nm()
					+ "' and a.verified is true and a.storage_id=" + vat;
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			System.out.println(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				action.setFl3_blending_bl(rs.getInt("bl"));
				action.setFl3_blending_al(rs.getInt("al"));
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
				// e.printStackTrace();
			}
		}
		return al;
	}

	public ArrayList getfl3_SpiritVatList(
			Transfer_Of_Sprit_To_Blending_VatAction act) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		SelectItem item = new SelectItem();
		item.setValue(0);
		item.setLabel("--Select--");
		list.add(item);
		String query = "";
		try {

			query = " select distinct a.storage_desc,coalesce(a.vat_type ,'NA') as vat_type ,a.storage_id  FROM distillery.fl3_3a_vat_master a where a.distillery_id='"
					+ act.getFl3_distillri_id()
					+ "' and a.vat_type='S' and lic_no='"
					+ act.getFl3_distillri_nm() + "' order by a.storage_desc";

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				item = new SelectItem();

				item.setValue(rs.getInt("storage_id"));
				item.setLabel(rs.getString("storage_desc") + " - "
						+ rs.getString("vat_type"));
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
				// e.printStackTrace();
			}
		}
		return list;
	}

	public String saveDatafl3(Transfer_Of_Sprit_To_Blending_VatAction action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		int seq = this.getMaxId() + 1;
		// System.out.println("====update========"+seq);
		try {

			String query = " INSERT INTO distillery.spirit_to_blending_vat( "
					+ " 		blend_vat_no, spirit_vat_no, spirit_vat_bl, spirit_vat_al, txn_date, blend_vat_bl_bookval, "
					+ " 		blend_vat_al_bookval, reading_bef_trxn, reading_aft_trxn, created_date, int_id, distillery_id, "
					+ " 		trxn_al, trxn_bl,login_user,unit_type) "
					+ " 		VALUES ('"
					+ action.getFl3_blend_vat_no()
					+ "', '"
					+ action.getFl3_spirt_no()
					+ "', '"
					+ action.getFl3_spirt_bl()
					+ "', "
					+ "  '"
					+ action.getFl3_spirt_al()
					+ "', '"
					+ Utility.convertUtilDateToSQLDate(action.getCurrentDate())
					+ "', "
					+ " '"
					+ action.getFl3_blending_bl()
					+ "', '"
					+ action.getFl3_blending_al()
					+ "', '"
					+ action.getFl3_blending_al_bfr()
					+ "', "
					+ " '"
					+ action.getFl3_blending_al_aftr()
					+ "', '"
					+ Utility.convertUtilDateToSQLDate(new Date())
					+ "', '"
					+ seq
					+ "', '"
					+ action.getFl3_distillri_id()
					+ "', '"
					+ action.getFl3_blending_al_trnfr()
					+ "', "
					+ "'"
					+ action.getFl3_blending_bl_trnfr()
					+ "', '"
					+ ResourceUtil.getUserNameAllReq().trim()
					+ "', '"
					+ action.getDistillri_addrss() + "')";

			// System.out.println("saveData======="+query);

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(query);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			saveStatus = pstmt.executeUpdate();
			if (saveStatus > 0) {

				conn.commit();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"  RECORD SAVED successfully.  !!! ",
								"  RECORD SAVED successfully.  !!!"));
				action.reset();
				// action.setFinalflg(true);

			} else {

				// action.reset();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							" RECORD NOT SAVED !!!!!!",
							"RECORD NOT SAVED !!!!!!"));
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
				// e.printStackTrace();
			}
		}
		return "";
	}

	public ArrayList getsaveDatafl3(Transfer_Of_Sprit_To_Blending_VatAction ac) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 1;
		// ac.setListFlagForPrint(false);

		String sql = " SELECT c.storage_desc as spirit_vat_no ,b.storage_desc, a.trxn_al, a.trxn_bl,a.txn_date  "
				+ " FROM distillery.spirit_to_blending_vat a left outer join distillery.fl3_3a_vat_master b on  b.storage_id=a.blend_vat_no"
				+ " left outer join distillery.fl3_3a_vat_master c on  c.storage_id=a.spirit_vat_no   "
				+ " where   unit_type not in ('D')   and"
				+ " a.login_user ='"
				+ ResourceUtil.getUserNameAllReq().trim() + "' ";

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				Transfer_Of_Sprit_To_Blending_VatDt dt = new Transfer_Of_Sprit_To_Blending_VatDt();
				// dt.setSrno_s(i);
				dt.setTrans_date_dt(rs.getDate("txn_date"));
				dt.setFrom_vat_dt(rs.getString("spirit_vat_no"));
				dt.setTo_vat_dt(rs.getString("storage_desc"));
				dt.setTrans_al(rs.getString("trxn_al"));
				dt.setTrans_bl(rs.getString("trxn_bl"));

				i++;
				list.add(dt);
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
		return list;
	}

}
