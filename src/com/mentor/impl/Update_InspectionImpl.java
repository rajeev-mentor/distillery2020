package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.mentor.Datatable.Update_InspectionDt;
import com.mentor.action.Update_InspectionAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class Update_InspectionImpl {

	public String getDetails(Update_InspectionAction ac) {
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
				ac.setDist_name(ResourceUtil.getUserNameAllReq().trim());
				ac.setDist_id(Integer.parseInt(ResourceUtil.getUserNameAllReq()
						.trim().substring(10)));
				// ac.setAddress("");

				// //System.out.println("1--"+ResourceUtil.getUserNameAllReq().trim().substring(0,9)+"-");
			} else {
				queryList = " SELECT int_app_id_f as app_id,vch_undertaking_name as name ,vch_wrk_add as address,'D' as login_type"
						+ " FROM dis_mst_pd1_pd2_lic WHERE vch_wrk_phon='"
						+ ResourceUtil.getUserNameAllReq().trim() + "'";

				pstmt = con.prepareStatement(queryList);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					ac.setDist_name(rs.getString("name"));
					ac.setDist_id(rs.getInt("app_id"));
					// ac.setAddress(rs.getString("address"));

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

	// -----------------------------------vat list----------------------------

	public ArrayList vatlistImpl(Update_InspectionAction act) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		SelectItem item = new SelectItem();
		item.setValue("");
		item.setLabel("--Select--");
		list.add(item);
		String query = "";
		String queryS = "";

		conn = ConnectionToDataBase.getConnection();
		try {
			if (act.getRadio_type() != null) {

				if (act.getRadio_type().equalsIgnoreCase("BLENDFL")) {

					query = "SELECT  storage_id, recieve_bl, recieve_al, storage_desc "
							+ "FROM distillery.spirit_for_bottling where int_distillery_id="
							+ act.getDist_id()+"  order by storage_desc  ";
					System.out.println("=BLENDFL==" + query);
					pstmt = conn.prepareStatement(query);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						item = new SelectItem();
						item.setValue(rs.getString("storage_id"));
						item.setLabel(rs.getString("storage_desc"));
						list.add(item);
					}

				} else if (act.getRadio_type().equalsIgnoreCase("BLENDCL")) {

					query = "Select storage_id,storage_desc ,recieve_bl ,recieve_al ,consumed_bl , "
							+ " consumed_al   "
							+ "from  distillery.spirit_for_bottling_cl where int_distillery_id='"
							+ act.getDist_id() + "'  order by storage_desc  ";

					System.out.println("=BLENDFL==" + query);
					pstmt = conn.prepareStatement(query);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						item = new SelectItem();
						item.setValue(rs.getString("storage_id"));
						item.setLabel(rs.getString("storage_desc"));
						list.add(item);
					}

				} else if (act.getRadio_type().equalsIgnoreCase("DV")) {

					query = " SELECT b.int_id ,b.vch_tank_name ,a.received_bl  ,a.recieved_al, "
							+ "	 a.consumed_bl ,a.consumed_al               "
							+ " from distillery.distillery_denatures_spirit_store_detail b                    "
							+ " left outer join  distillery.denatured_spirit_vat a on b.int_id=a.den_vat_id      "
							+ " and a.int_dist_id=b.int_distilleri_id                       "
							+ "  where  b.int_distilleri_id='"
							+ act.getDist_id() + "'    order by b.vch_tank_name      ";

					System.out.println("=DV==" + query);
					pstmt = conn.prepareStatement(query);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						item = new SelectItem();
						item.setValue(rs.getString("int_id"));
						item.setLabel(rs.getString("vch_tank_name"));
						list.add(item);
					}

				}

				else if (act.getRadio_type().equalsIgnoreCase("S")) {

					query = " SELECT b.int_id ,b.vch_tank_name , a.received_bl ,a.recieved_al ,  "
							+ " a.consumed_bl ,a.consumed_al   from distillery.distillery_spirit_store_detail b   "
							+ " left outer join  distillery.spirit_vat a on b.int_id=a.vat_id     and a.int_dist_id=b.int_distilleri_id   "
							+ " where  b.int_distilleri_id='"
							+ act.getDist_id() + "'   order by b.vch_tank_name    ";

					/*
					 * " select distinct a.vch_tank_name,coalesce(a.vat_type ,'NA') as vat_type ,a.int_id  FROM distillery.distillery_spirit_store_detail a where a.int_distilleri_id="
					 * + act.getDist_id() + " order by a.vch_tank_name";
					 */

					System.out.println("=S==" + query);
					pstmt = conn.prepareStatement(query);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						item = new SelectItem();
						item.setValue(rs.getString("int_id"));
						item.setLabel(rs.getString("vch_tank_name"));
						list.add(item);
					}

				} else if (act.getRadio_type().equalsIgnoreCase("BOT-CL")) {

					query = " select a.storage_id, a.storage_desc FROM distillery.bottling_vat_cl a where a.int_distillery_id="
							+ act.getDist_id() + " order by a.storage_desc";
					
					System.out.println("=BOT--CL==" + query);
					pstmt = conn.prepareStatement(query);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						item = new SelectItem();
						item.setValue(rs.getString("storage_id"));
						item.setLabel(rs.getString("storage_desc"));
						list.add(item);
					}

				} else if (act.getRadio_type().equalsIgnoreCase("BOT-FL")) {

					query = " select a.storage_id, a.storage_desc FROM distillery.bottling_vat a where a.int_distillery_id="
							+ act.getDist_id() + " order by a.storage_desc";
					System.out.println("=BOT--FL==" + query);
					pstmt = conn.prepareStatement(query);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						item = new SelectItem();
						item.setValue(rs.getString("storage_id"));
						item.setLabel(rs.getString("storage_desc"));
						list.add(item);
					}

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
		return list;
	}

	// ------------------------------------------------------------------qunatity----------------

	public double getQuantity(Update_InspectionAction ac, String val) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		ArrayList list = new ArrayList();

		double quantity = 0.0;
		try {

			if (ac.getRadio_type().equalsIgnoreCase("S")) {
				query = " SELECT a.received_bl ,a.consumed_bl,a.consumed_al,a.recieved_al  "
						+ " from distillery.distillery_spirit_store_detail b left outer join  "
						+ " distillery.spirit_vat a on b.int_id=a.vat_id and a.int_dist_id=b.int_distilleri_id where "
						+ " b.int_id="
						+ Integer.parseInt(val)
						+ " and b.int_distilleri_id=" + ac.getDist_id();

				System.out.println("---S---" + query);
			} else if (ac.getRadio_type().equalsIgnoreCase("DV")) {

				query = " SELECT b.int_id as storage_id,b.vch_tank_name as tank_nm,a.received_bl ,a.recieved_al , "
						+ " a.consumed_bl ,a.consumed_al                   "
						+ " from distillery.distillery_denatures_spirit_store_detail b                    "
						+ " left outer join  distillery.denatured_spirit_vat a on b.int_id=a.den_vat_id            "
						+ " and a.int_dist_id=b.int_distilleri_id                       "
						+ "  where  b.int_id='"
						+ Integer.parseInt(val)
						+ "' and b.int_distilleri_id='"
						+ ac.getDist_id()
						+ "'    order by tank_nm   ";

			

				System.out.println("---DV---" + query);

			} else if (ac.getRadio_type().equalsIgnoreCase("BLENDCL")) {

				query = "Select storage_id,storage_desc as tank_nm,recieve_bl  as received_bl,recieve_al as recieved_al,consumed_bl as consumed_bl, "
						+ " consumed_al as consumed_al   "
						+ "from  distillery.spirit_for_bottling_cl where storage_id="
						+ Integer.parseInt(val)
						+ " and int_distillery_id='"
						+ ac.getDist_id() + "'  order by tank_nm  ";

				System.out.println("---BLENDCL---" + query);
			} else if (ac.getRadio_type().equalsIgnoreCase("BLENDFL")) {

				query = "SELECT recieve_bl as received_bl,consumed_bl as consumed_bl,consumed_al as consumed_al,recieve_al as recieved_al "
						+ "from distillery.spirit_for_bottling where storage_id="
						+ Integer.parseInt(val)
						+ " and int_distillery_id="
						+ ac.getDist_id();

				System.out.println("---BLENDFL---" + query);

			} else if (ac.getRadio_type().equalsIgnoreCase("BOT-FL")) {

				query = "SELECT storage_id, recieve_bl as received_bl, recieve_al as recieved_al,  int_distillery_id, consumed_bl as consumed_bl, consumed_al as consumed_al FROM distillery.bottling_vat where storage_id='"
						+ Integer.parseInt(val)
						+ "'  and int_distillery_id="
						+ ac.getDist_id();

				System.out.println("---BOT-FL--" + query);
			} else if (ac.getRadio_type().equalsIgnoreCase("BOT-CL")) {

				query = "SELECT storage_id, recieve_bl as received_bl, recieve_al as recieved_al, storage_desc, int_distillery_id, consumed_bl as consumed_bl, consumed_al as consumed_al FROM  distillery.bottling_vat_cl  where storage_id='"
						+ Integer.parseInt(val)
						+ "' and int_distillery_id="
						+ ac.getDist_id();

				System.out.println("---BOT-CL---" + query);
			}

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				ac.setBook_bl(rs.getDouble("received_bl")
						- rs.getInt("consumed_bl"));
				ac.setBook_al(rs.getDouble("recieved_al")
						- rs.getInt("consumed_al"));

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

	// ----------------------------------------------------save method

	public String saveData(Update_InspectionAction action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;

		try {

			String query = " INSERT INTO distillery.inspection_master( "
					+ "		int_id,vat_id, vat_book_bl, vat_book_al, ins_read_bl, ins_read_al, ins_read_strength, "
					+ "    ins_wst_bl, ins_wst_al, created_date,vat_type, login_user) "
					+ "		VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			// System.out.println("====saveData1======" + query);
			// System.out.println("getMaxId" + this.getMaxId());
			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(query);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			pstmt.setInt(1, this.getMaxId() + 1);
			pstmt.setInt(2, Integer.parseInt(action.getVatno()));
			pstmt.setDouble(3, action.getBook_bl());
			pstmt.setDouble(4, action.getBook_al());
			pstmt.setDouble(5, action.getIns_read_bl());
			pstmt.setDouble(6, action.getIns_read_al());
			pstmt.setDouble(7, action.getIns_read_strength());
			pstmt.setDouble(8, action.getIns_wast_bl());
			pstmt.setDouble(9, action.getIns_wast_al());
			pstmt.setDate(10, Utility.convertUtilDateToSQLDate(new Date()));
			pstmt.setString(11, action.getRadio_type());
			pstmt.setString(12, ResourceUtil.getUserNameAllReq());

			saveStatus = pstmt.executeUpdate();
			if (saveStatus > 0) {
				saveStatus = 0;

				query = " INSERT INTO distillery.vat_wastage(vat_no, vat_book_val_bl, vat_book_val_al, vat_wastage_bl, "
						+ "vat_wastage_al,"
						+ " txn_date, vat_type,unit_id,ref_id, type,  vat_des)VALUES  "
						+ " ('"
						+ Integer.parseInt(action.getVatno())
						+ "', '"
						+ action.getBook_bl()
						+ "', "
						+ "'"
						+ action.getBook_al()
						+ "', '"
						+ action.getIns_wast_al()
						+ "', "
						+ "'"
						+ action.getIns_wast_bl()
						+ "', '"
						+ Utility.convertUtilDateToSQLDate(new Date())
						+ "',"
						+ " ?, '"
						+ action.getDist_id()
						+ "', "
						+ " ?, 'INSPECTION', 'I')";

				// System.out.println("====saveDatavat======" + query);
				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, action.getRadio_type());
				pstmt.setInt(2, this.getMaxId() + 1);
				saveStatus = pstmt.executeUpdate();
			}
			if (saveStatus > 0) {
				saveStatus = 0;

				if (action.getRadio_type().equalsIgnoreCase("S")) {
					query = "  update  distillery.spirit_vat  set  consumed_bl=consumed_bl+"
							+ action.getIns_wast_bl()
							+ ","
							+ "consumed_al=consumed_al+"
							+ action.getIns_wast_al()
							+ " where  "
							+ " vat_id="
							+ Integer.parseInt(action.getVatno())
							+ " and int_dist_id=" + action.getDist_id();
				} else if (action.getRadio_type().equalsIgnoreCase("DV")) {

					query = "  update  distillery.denatured_spirit_vat  set  consumed_bl=consumed_bl+"
							+ action.getIns_wast_bl()
							+ ","
							+ "consumed_al=consumed_al+"
							+ action.getIns_wast_al()
							+ " where  "
							+ " den_vat_id="
							+ Integer.parseInt(action.getVatno())
							+ " and int_dist_id=" + action.getDist_id();
				}
				else if (action.getRadio_type().equalsIgnoreCase("BLENDCL")) {

					query = "update distillery.spirit_for_bottling_cl set consumed_bl=consumed_bl+"
							+ action.getIns_wast_bl()
							+ ",consumed_al=consumed_al+"
							+ action.getIns_wast_al()
							+ "  where storage_id="
							+ Integer.parseInt(action.getVatno())
							+ " and int_distillery_id=" + action.getDist_id();
				}

				else if (action.getRadio_type().equalsIgnoreCase("BLENDFL")) {

					query = "update distillery.spirit_for_bottling set consumed_bl=consumed_bl+"
							+ action.getIns_wast_bl()
							+ ",consumed_al=consumed_al+"
							+ action.getIns_wast_al()
							+ "  where storage_id="
							+ Integer.parseInt(action.getVatno())
							+ " and int_distillery_id=" + action.getDist_id();
				} else if (action.getRadio_type().equalsIgnoreCase("BOT-FL")) {

					query = "update  distillery.bottling_vat set consumed_bl=consumed_bl+"
							+ action.getIns_wast_bl()
							+ ",consumed_al=consumed_al+"
							+ action.getIns_wast_al()
							+ " where storage_id='"
							+ Integer.parseInt(action.getVatno())
							+ "'  and int_distillery_id=" + action.getDist_id();

				} else if (action.getRadio_type().equalsIgnoreCase("BOT-CL")) {

					query = "update distillery.bottling_vat_cl set consumed_bl=consumed_bl+"
							+ action.getIns_wast_bl()
							+ ",consumed_al=consumed_al+"
							+ action.getIns_wast_al()
							+ " where storage_id='"
							+ Integer.parseInt(action.getVatno())
							+ "' and int_distillery_id=" + action.getDist_id();

				}
				
				System.out.println("===="+action.getRadio_type()+"=="+query);
				pstmt = conn.prepareStatement(query);

				saveStatus = pstmt.executeUpdate();
			}
			if (saveStatus > 0) {
				action.reset();
				conn.commit();
				ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY,
						Constants.SAVED_SUCESSFULLY);
				action.reset();

			} else {

				// action.reset();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));

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
			String query = "SELECT max(int_id) maxid from distillery.inspection_master ";

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

	// DATA TABLE CODE FETCHING RECORD FROM MASTER INSPECTION

	public ArrayList getDataList(Update_InspectionAction action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list2 = new ArrayList();

		String selQr =
				
	  "    SELECT a.created_date,a.vat_id, a.vat_book_bl, a.vat_book_al, a.ins_read_bl, a.ins_read_al, a.ins_wst_bl, a.ins_wst_al,  a.vat_type , 	        "+
	  "    case when a.vat_type in ('BLENDFL','BL') then (select b.storage_desc from distillery.spirit_for_bottling b where b.storage_id=a.vat_id)                    "+
	  "    when a.vat_type='BLENDCL' then (select b.storage_desc from distillery.spirit_for_bottling_cl b where b.storage_id=a.vat_id)                      "+
	  "    when a.vat_type='DV' then (select b.vch_tank_name from distillery.distillery_denatures_spirit_store_detail b where b.int_id=a.vat_id)            "+
	  "    when a.vat_type='S' then (select b.vch_tank_name from distillery.distillery_spirit_store_detail b where b.int_id=a.vat_id)                       "+
	  "    when a.vat_type='BOT-FL' then (select b.storage_desc from distillery.bottling_vat b where b.storage_id=a.vat_id)                                 "+
	  "    when a.vat_type='BOT-CL' then (select b.storage_desc from distillery.bottling_vat_cl b where b.storage_id=a.vat_id)  else 'NA' end as tank_nm    "+
	  "    FROM distillery.inspection_master a where   a.login_user='" + ResourceUtil.getUserNameAllReq() + "'  order by  a.created_date desc         ";
				
					
			/*	
				
				"	SELECT a.vat_id, a.vat_book_bl, a.vat_book_al, a.ins_read_bl, a.ins_read_al,"
				+ " a.ins_wst_bl, a.ins_wst_al, a.created_date, a.vat_type , "
				+ "	case when a.vat_type='BL' then (select b.storage_desc from distillery.spirit_for_bottling b where b.storage_id=a.vat_id) "
				+ " when a.vat_type='S' then (select b.vch_tank_name from distillery.distillery_spirit_store_detail b where b.int_id=a.vat_id) "
				+ " when a.vat_type='BOT-FL' then (select b.storage_desc from distillery.bottling_vat b where b.storage_id=a.vat_id) "
				+ " when a.vat_type='BOT-CL' then (select b.storage_desc from distillery.bottling_vat_cl b where b.storage_id=a.vat_id) "
				+ " else 'NA' end as tank_nm FROM distillery.inspection_master a where  "
				+ " a.login_user='" + ResourceUtil.getUserNameAllReq() + "' ";
*/		
		
		
		
		try {
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(selQr);
		     System.out.println("datatable query-------------"+selQr);
			// pstmt.setInt(1, action.getDissleriId());
			rs = pstmt.executeQuery();
			while (rs.next()) {

				Update_InspectionDt dt = new Update_InspectionDt();

				dt.setVat_nm(rs.getString("tank_nm"));
				dt.setBk_al(rs.getString("vat_book_al"));
				dt.setBk_bl(rs.getString("vat_book_bl"));
				dt.setRead_al(rs.getString("ins_read_al"));
				dt.setRead_bl(rs.getString("ins_read_bl"));
				dt.setWst_al(rs.getString("ins_wst_al"));
				dt.setWst_bl(rs.getString("ins_wst_bl"));
				dt.setDate(rs.getDate("created_date"));
				if (rs.getString("vat_type").equalsIgnoreCase("BLENDFL")) {
					dt.setType("BLEND FL");
				}
				else if (rs.getString("vat_type").equalsIgnoreCase("BLENDCL")) {
					dt.setType("BLEND CL");
				} 
				else if (rs.getString("vat_type").equalsIgnoreCase("DV")) {
					dt.setType("Denature");
				} 
				else if (rs.getString("vat_type").equalsIgnoreCase("S")) {
					dt.setType("Sprit");
				} else if (rs.getString("vat_type").equalsIgnoreCase("BOT-FL")) {
					dt.setType("Bottling FL");
				} else if (rs.getString("vat_type").equalsIgnoreCase("BOT-CL")) {
					dt.setType("Bottling CL");
				}
				list2.add(dt);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return list2;

	}

}
