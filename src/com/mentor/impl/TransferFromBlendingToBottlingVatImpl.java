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

import com.mentor.Datatable.TransferFromBlendingToBottlingVatDt;
import com.mentor.Datatable.Transfer_Of_Sprit_To_Blending_VatDt;
import com.mentor.action.TransferFromBlendingToBottlingVatAction;
import com.mentor.action.Transfer_Of_Sprit_To_Blending_VatAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class TransferFromBlendingToBottlingVatImpl {

	public String getDetails(TransferFromBlendingToBottlingVatAction ac) {

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

	public ArrayList getblendingStoragelist(
			TransferFromBlendingToBottlingVatAction action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		String sql = "SELECT  storage_id, recieve_bl, recieve_al, storage_desc "
				+ "FROM distillery.spirit_for_bottling where int_distillery_id="
				+ action.getFl3_distillri_id();

		SelectItem item = new SelectItem();
		item.setLabel("SELECT");
		item.setValue(0);
		list.add(item);

		try {
			//System.out.println("=getblendingStoragelist====" + sql);
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
			TransferFromBlendingToBottlingVatAction action) {

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
					+ action.getFl3_distillri_id() + "'";

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				DecimalFormat formatter = new DecimalFormat("#.##");
				// System.out.println("query=="+query);

				// NumberFormat formatter = new DecimalFormat("#");

				action.setBlending_bl(rs.getDouble("bl"));
				action.setBlending_al(rs.getDouble("al"));

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

	public double getbottlingQuantityStrength(int vat,
			TransferFromBlendingToBottlingVatAction action) {

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
					+ "  where vat_type='BOT' and " + " a.distillery_id='"
					+ action.getFl3_distillri_id() + "' and a.lic_no='"
					+ action.getFl3_distillri_nm()
					+ "' and a.verified is true and a.storage_id=" + vat;
			
		//	System.out.println("getbottlingQuantityStrength==" + query);
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				action.setBottling_bl(rs.getDouble("bl"));
				action.setBottling_al(rs.getDouble("al"));
			} else {

				action.setBottling_bl(0.0);
				action.setBottling_al(0.0);
			}
		} catch (Exception e) {

			action.setBottling_bl(0.0);
			action.setBottling_al(0.0);

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

	public ArrayList getbottlingStoragelist(
			TransferFromBlendingToBottlingVatAction act) {
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
					+ "' and a.vat_type='BOT' and lic_no='"
					+ act.getFl3_distillri_nm() + "' order by a.storage_desc";
			
			
			
			//System.out.println("getbottlingStoragelist==" + query);
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

	// ============================= save Data=======================

	public String saveData(TransferFromBlendingToBottlingVatAction action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		int seq = this.getMaxId() + 1;
		// System.out.println("====update========"+seq);
		try {

			String query = " INSERT INTO distillery.transfer_from_blending_to_bottling( "
					+ " blend_vat_no, bottling_vat_no, blend_vat_bl, blend_vat_al, txn_date, bottling_vat_bl_bookval, "
					+ " bottling_vat_al_bookval, reading_bef_trxn, reading_aft_trxn, created_date, int_id, distillery_id, trxn_al,"
					+ " trxn_bl, login_user, unit_type) " + " VALUES ('"
					+ action.getBlend_vat_no()
					+ "', '"
					+ action.getBottling_no()
					+ "', '"
					+ action.getBlending_bl()
					+ "',"
					+ " '"
					+ action.getBlending_al()
					+ "', '"
					+ Utility.convertUtilDateToSQLDate(action.getCurrentDate())
					+ "', "
					+ "'"
					+ action.getBottling_bl()
					+ "', '"
					+ action.getBottling_al()
					+ "', '"
					+ action.getBottling_al_bfr()
					+ "', "
					+ "'"
					+ action.getBottling_al_aftr()
					+ "', '"
					+ Utility.convertUtilDateToSQLDate(new Date())
					+ "',"
					+ " '"
					+ seq
					+ "', '"
					+ action.getFl3_distillri_id()
					+ "', "
					+ "'"
					+ action.getBottling_al_trnfr()
					+ "', '"
					+ action.getBottling_bl_trnfr()
					+ "', '"
					+ ResourceUtil.getUserNameAllReq().trim() + "','D' )";
			
			

			//System.out.println("saveData=======" + query);

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(query);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			saveStatus = pstmt.executeUpdate();
			if (saveStatus > 0) {
				String querybottlingvat = " UPDATE distillery.fl3_3a_vat_master SET recieve_bl='"
						+ action.getBottling_bl_up()
						+ "', recieve_al='"
						+ action.getBottling_al_up()
						+ "' WHERE 	distillery_id='"
						+ action.getFl3_distillri_id()
						+ "' and storage_id='"
						+ action.getBottling_no() + "' ";

				/*
				 * UPDATE distillery.fl3_3a_vat_master SET recieve_bl=?,
				 * recieve_al=? WHERE distillery_id=? and storage_id=?
				 */
				//System.out.println("querybottlingvat=======" + querybottlingvat);
				
				pstmt = conn.prepareStatement(querybottlingvat);

				saveStatus = pstmt.executeUpdate();
			}
			if (saveStatus > 0) {
				String queryblendingvat = " UPDATE distillery.spirit_for_bottling SET consumed_bl='"
						+ action.getBlending_vat_bl_up()
						+ "', consumed_al='"
						+ action.getBlending_vat_al_up()
						+ "' WHERE 	int_distillery_id='"
						+ action.getFl3_distillri_id()
						+ "' and storage_id='"
						+ action.getBlend_vat_no() + "' ";
				/*
				 * UPDATE distillery.spirit_for_bottling SET consumed_bl=?,
				 * consumed_al=? WHERE storage_id=? and int_distillery_id=?
				 */

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
			String query = "SELECT max(int_id) maxid from distillery.transfer_from_blending_to_bottling ";

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
	public ArrayList getsaveData(TransferFromBlendingToBottlingVatAction ac) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 1;
		// ac.setListFlagForPrint(false);

		String sql = " SELECT c.storage_desc,b.storage_desc as to_vat, a.trxn_al, a.trxn_bl,a.txn_date "
				+ " FROM distillery.transfer_from_blending_to_bottling a,distillery.fl3_3a_vat_master b, "
				+ " distillery.spirit_for_bottling c where c.storage_id=a.blend_vat_no and b.storage_id=a.bottling_vat_no"
				+ " and unit_type='D' and"
				+ " a.distillery_id ='"
				+ ac.getFl3_distillri_id() + "' ";
     //	System.out.println("===getsaveData==" + sql);

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				TransferFromBlendingToBottlingVatDt dt = new TransferFromBlendingToBottlingVatDt();
				// dt.setSrno_s(i);
				dt.setTrans_date_dt(rs.getDate("txn_date"));
				dt.setFrom_vat_dt(rs.getString("storage_desc"));
				dt.setTo_vat_dt(rs.getString("to_vat"));
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
