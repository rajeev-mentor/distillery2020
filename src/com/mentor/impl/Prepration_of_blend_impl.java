package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.model.SelectItem;
import com.mentor.Datatable.Prepration_of_blend_dt;

import com.mentor.action.Prepration_of_blend_act;

import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class Prepration_of_blend_impl {

	public String hidden(Prepration_of_blend_act act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = "SELECT int_app_id_f,vch_undertaking_name,vch_undertaking_address,vch_reg_add,vch_wrk_add  from  dis_mst_pd1_pd2_lic "
					+ " where vch_wrk_phon="
					+ ResourceUtil.getUserNameAllReq().trim();
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);
			// System.out.println("========login query======"+queryList);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				act.setDis_id_int(rs.getInt("int_app_id_f"));
				act.setDis_name_str(rs.getString("vch_undertaking_name"));
				act.setDis_add_str(rs.getString("vch_undertaking_address"));
				act.setLogin(rs.getString("vch_wrk_phon"));

			}

		} catch (SQLException se) {
			// se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
			//	se.printStackTrace();
			}
		}
		return "";

	}

	// ---------------------------------------

	public double getBlendingdata(int vat, Prepration_of_blend_act action) {
		// System.out.println("===============getBlendingdata=================");
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
					+ vat + " AND  int_distillery_id=" + action.getDis_id_int();

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			// /System.out.println("query--------------------"+query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				DecimalFormat formatter = new DecimalFormat("#.##");
				// System.out.println("query=="+query);

				// NumberFormat formatter = new DecimalFormat("#");

				action.setBlendingbl(rs.getDouble("bl"));
				action.setBlendingal(rs.getDouble("al"));
				if (((rs.getDouble("al") / rs.getDouble("bl")) * 100) < 0
						|| ((rs.getDouble("al") / rs.getDouble("bl")) * 100) > 100) {
					action.setBlendingstrength(42.8);
				} else {
					action.setBlendingstrength(Double.parseDouble(formatter
							.format((rs.getDouble("al") / rs.getDouble("bl")) * 100)));
				}
			}
		} catch (Exception e) {

	///		e.printStackTrace();
		} finally {
			try {

				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
			//	e.printStackTrace();
			}
		}
		return al;

	}

	public ArrayList getStorage(Prepration_of_blend_act action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		String sql = "SELECT  storage_id, recieve_bl, recieve_al, storage_desc "
				+ "FROM distillery.spirit_for_bottling where int_distillery_id="
				+ action.getDis_id_int() + " and verified='V'";
		
		
		SelectItem item = new SelectItem();
		item.setLabel("SELECT");
		item.setValue(0);
		list.add(item);
		// /System.out.println("----------sql------------------"+sql);
		try {
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

	public void save(Prepration_of_blend_act act) {

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = "";
		String query1 = "";
		int saveStatus = 0;
		int count = 0;
		saveStatus = 0;
		try {
			query = "insert into distillery.blend_preparation  "
					+ " (blend_vat_no,txn_date ,blend_vat_bl ,blend_vat_al ,bln_material_bl ,distillery_id ,produced_al ,produced_bl ,produced_strength,water_bl ,int_id ,login) "
					+ " values " + "('"
					+ act.getStorageId()
					+ "','"
					+ Utility.convertUtilDateToSQLDate(new Date())
					+ "','"
					+ act.getBlendingbl()
					+ "','"
					+ act.getBlendingal()
					+ "',"
					+ "'"
					+ act.getBl()
					+ "','"
					+ act.getDis_id_int()
					+ "','"
					+ act.getTot_al()
					+ "','"
					+ act.getTot_bl()
					+ "','"
					+ act.getTot_str()
					+ "','"
					+ act.getBlwater()
					+ "',"
					+ "'"
					+ this.maxId() + "','" + act.getLogin() + "')";
			con = ConnectionToDataBase.getConnection();
			// System.out.println("---insert------------------"+query);
			pst = con.prepareStatement(query);

			saveStatus = pst.executeUpdate();
			// /
			// System.out.println("-------------executeUpdate-----------------"+saveStatus);
			if (saveStatus > 0) {
				query1 = " UPDATE distillery.spirit_for_bottling "
						+ "SET   recieve_bl=COALESCE(recieve_bl,0)+"
						+ act.getTot_bl()
						+ " , recieve_al=COALESCE(recieve_al,0)+"
						+ act.getTot_al() + " WHERE int_distillery_id ='"
						+ act.getDis_id_int() + "'and storage_id="
						+ act.getStorageId();
				con = ConnectionToDataBase.getConnection();
				// System.out.println("---update------------------"+query1);
				// System.out.println("Storage id---------------0"+act.getStorageId());
				pst = con.prepareStatement(query1);

				saveStatus = pst.executeUpdate();
			}
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (Exception e2) {
				// e2.printStackTrace();
			}
		}
	}

	public int maxId() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " select max(int_id) as id   from distillery.blend_preparation";
		int maxid = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			// System.out.println("--------------------------"+query);
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

	public ArrayList displaylistImpl2(Prepration_of_blend_act act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		int i = 1;

		try {

			query = "SELECT   (select storage_desc from distillery.spirit_for_bottling where int_distillery_id=distillery_id) as "
					+ " storage_desc ,blend_vat_no ,blend_vat_bl ,blend_vat_al  ,produced_al ,produced_bl,produced_strength,water_bl  "
					+ " FROM distillery.blend_preparation  where distillery_id='"
					+ act.getDis_id_int() + "'";

			// / System.out.println("selQr1============get data=========="
			// +query);
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();
			while (rs.next()) {
				Prepration_of_blend_dt dt = new Prepration_of_blend_dt();
				dt.setSno(i);
				// dt.setBl_vat(rs.getDouble("blend_vat_no"));
				dt.setVat_name(rs.getString("storage_desc"));
				dt.setVat_al(rs.getDouble("blend_vat_al"));
				dt.setVat_bl(rs.getDouble("blend_vat_bl"));
				dt.setWater(rs.getDouble("water_bl"));
				dt.setPal(rs.getDouble("produced_al"));
				dt.setPbl(rs.getDouble("produced_bl"));

				list.add(dt);

				i++;

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

}