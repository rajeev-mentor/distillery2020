package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.mentor.Datatable.ReceivefromPlantGinDT;
import com.mentor.Datatable.ReceivefromPlantGinDT;
import com.mentor.action.ReceivefromPlantGinAction;
import com.mentor.action.ReceivefromPlantGinAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class ReceivefromPlantGinImpl {

	public String getSugarmill(ReceivefromPlantGinAction ac) {

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
				ac.setDistillery_nm(rs.getString("vch_undertaking_name"));
				ac.setDistillery_id(rs.getInt("int_app_id_f"));
				ac.setDistillery_adrs(rs.getString("vch_wrk_add"));

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

	// ----------------------- plant BL and AL ------------------

	public String getPlantTotalblAndAL(ReceivefromPlantGinAction ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList =

			"		  select (coalesce(received_bl,0)- coalesce(consumed_bl,0)) as bl , "
					+ "	       (coalesce(recieved_al,0)- coalesce(consumed_al,0)) as  al , received_strength  "
					+ "	    FROM distillery.transfer_of_gin_to_plant_master_stock  "
					+ "	    where int_dist_id=" + ac.getDistillery_id();

			System.out.println("select query=="+queryList);
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				ac.setPlant_Total_Balance_BL(rs.getDouble("bl"));
				ac.setPlant_Total_balance_AL(rs.getDouble("al"));

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

	// ///////////////////////////////// spirit vat
	// ////////////////////////////////////////

	public ArrayList gettankdet(ReceivefromPlantGinAction ac, String radio) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		try {
			String query = 
					
					" SELECT int_id, int_distilleri_id, vch_tank_name, double_tank_capacity, openingal, openingbl, strength "
					+ "	FROM distillery.gin_store_detail_distillery  where int_distilleri_id= "
					+ ac.getDistillery_id()+ " order by vch_tank_name";
					
					/*" select * from (SELECT int_id, int_distilleri_id, vch_tank_name, double_tank_capacity, openingal, openingbl, strength "
					+ "	FROM distillery.gin_store_detail_distillery  where int_distilleri_id= "
					+ ac.getDistillery_id()
					+ " and  vat_def='R'   "
					+ " union "
					+ "	SELECT int_id, int_distilleri_id, vch_tank_name, double_tank_capacity, openingal, openingbl, strength "
					+ "	FROM distillery.gin_store_detail_distillery  where int_distilleri_id= "
					+ ac.getDistillery_id()
					+ "  and b_heavy_flag='"
					+ radio
					+ "' and  vat_def='S')f order by vch_tank_name ";*/

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

	// ----------------------------- save --------------------------------

	public void saveDetail(ReceivefromPlantGinAction ac) {
		int status = 0;
		Connection con = null;
		PreparedStatement ps = null;
		int seq = selectMax();
		// int seq_detail=selectMax_detail();

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());
		System.out.println("===String time===" + time);
		
		String SQL =

		"		INSERT INTO distillery.received_from_plant_gin_master ( "
				+ "				seq, int_distillery_id, plant_total_bl, plant_total_al, received_frm_plant_bl, "
				+ "			    received_frm_plant_al, vat_id, quantity_bl, quantity_strength, quantity_al, created_date,dt_time) "
				+ "				VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,'"+time+"') ";

		try {
			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(SQL);
			ps.setInt(1, seq);
			ps.setInt(2, ac.getDistillery_id());
			ps.setDouble(3, ac.getPlant_Total_Balance_BL());
			ps.setDouble(4, ac.getPlant_Total_balance_AL());
			ps.setDouble(5, ac.getReceived_Frm_Pant_Balance_BL());
			ps.setDouble(6, ac.getReceived_Frm_Pant_balance_AL());
			ps.setInt(7, Integer.parseInt(ac.getVat_id()));
			ps.setDouble(8, ac.getQuantity_Balance_BL());
			ps.setDouble(9, ac.getQuantity_Strength());
			ps.setDouble(10, ac.getQuantity_Balance_AL());
			ps.setDate(11, Utility.convertUtilDateToSQLDate(ac.getDtDate()));

			status = ps.executeUpdate();

			if (status > 0) {

				status = 0;

				/*
				 * String updateSpirtVat=
				 * "INSERT INTO distillery.spirit_vat(	recieved_al, received_strength,received_bl,consumed_bl,  int_dist_id, vat_id, int_season_id, "
				 * +
				 * "consumed_al, consumed_strength)	VALUES (0,0,0,  ?, ?,?, (SELECT   sesn_id::int  FROM public.mst_season where  active = 'a'),   ?, ?) ON CONFLICT ON CONSTRAINT spirit_vat_pkey "
				 * +
				 * " do update set consumed_al= COALESCE(distillery.spirit_vat.consumed_al,0.0) + "
				 * + ac.getTransfer_AL()+
				 * ", consumed_strength= COALESCE(distillery.spirit_vat.consumed_strength,0.0) + '0',"
				 * +
				 * " consumed_bl= COALESCE(distillery.spirit_vat.consumed_bl,0.0) + "
				 * + ac.getTransfer_BL()+" ";
				 */

				String updateSpirtVat = "update distillery.gin_store_detail_distillery set recieved_al= COALESCE(recieved_al,0.0) + "
						+ ac.getQuantity_Balance_AL()
						+ ", recieved_strength= COALESCE(recieved_strength,0.0) + '0',"
						+ " recieved_bl= COALESCE(recieved_bl,0.0) + "
						+ ac.getQuantity_Balance_BL() + " where int_distilleri_id="+ac.getDistillery_id()+" and int_id='"+ac.getVat_id()+"'";

				System.out.println("----- updateSpirtVat------"
						+ updateSpirtVat);

				ps = con.prepareStatement(updateSpirtVat);
				int j = 1;

				/*ps.setDouble(1, ac.getQuantity_Balance_BL());
				ps.setDouble(4, ac.getQuantity_Balance_AL());

				ps.setDouble(5, ac.getQuantity_Strength());
				ps.setInt(2, ac.getDistillery_id());
				ps.setInt(3, Integer.parseInt(ac.getVat_id()));

				System.out.println("----- getQuantity_Balance_BL------"
						+ ac.getQuantity_Balance_BL());
				System.out.println("----- getQuantity_Balance_AL------"
						+ ac.getQuantity_Balance_AL());
				System.out.println("----- getDistillery_id------"
						+ ac.getDistillery_id());
				System.out.println("----- getVat_id------" + ac.getVat_id());*/
				status = ps.executeUpdate();

			}

			if (status > 0) {

				status = 0;

				String updateSpirtVat = " update distillery.transfer_of_gin_to_plant_master_stock set consumed_al= COALESCE(consumed_al,0.0) + "
						+ ac.getReceived_Frm_Pant_balance_AL()
						+ ", consumed_strength= COALESCE(consumed_strength,0.0) + '0',"
						+ " consumed_bl= COALESCE(consumed_bl,0.0) + "
						+ ac.getReceived_Frm_Pant_Balance_BL() + " where int_dist_id="+ac.getDistillery_id()+" ";

				/*
				 * String updateSpirtVat=
				 * "INSERT INTO distillery.re_distillation_of_spirit_master_stock(	consumed_al, consumed_strength,consumed_bl,received_bl,  int_dist_id, vat_id, int_season_id, "
				 * +
				 * "recieved_al, received_strength)	VALUES (0,0,0,  ?, ?,?, (SELECT   sesn_id::int  FROM public.mst_season where  active = 'a'),   ?, ?) ON CONFLICT ON CONSTRAINT re_distillation_of_spirit_master_stock_pkey "
				 * +
				 * " do update set recieved_al= COALESCE(distillery.re_distillation_of_spirit_master_stock.recieved_al,0.0) + "
				 * + ac.getTransfer_AL()+
				 * ", received_strength= COALESCE(distillery.re_distillation_of_spirit_master_stock.received_strength,0.0) + '0',"
				 * +
				 * " received_bl= COALESCE(distillery.re_distillation_of_spirit_master_stock.received_bl,0.0) + "
				 * + ac.getTransfer_BL()+" ";
				 */

				ps = con.prepareStatement(updateSpirtVat);
				int j = 1;

				/*ps.setDouble(1, ac.getReceived_Frm_Pant_Balance_BL());
				ps.setDouble(4, ac.getReceived_Frm_Pant_balance_AL());

				ps.setDouble(5, 0);
				ps.setInt(2, ac.getDistillery_id());
				ps.setInt(3, 0); // Integer.parseInt(ac.getVat_id())
*/				status = ps.executeUpdate();

			}

			if (status > 0) {
				con.commit();
				ac.reset();
				ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY,
						Constants.SAVED_SUCESSFULLY);
			} else {
				con.rollback();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);

			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	// -------------------------------------- max id Master Table
	// ----------------------

	public int selectMax() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int maxid = 0;

		try {
			String query = "SELECT coalesce(max(seq),0) from distillery.received_from_plant_gin_master ";
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
		return maxid + 1;
	}

	public ArrayList showData(ReceivefromPlantGinAction action) {
		ArrayList list = new ArrayList();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql =

		"		SELECT a.seq, a.int_distillery_id, a.plant_total_bl, a.plant_total_al, "
				+ "		a.received_frm_plant_bl, a.received_frm_plant_al, a.vat_id, a.quantity_bl, a.quantity_strength,  "
				+ "		a.quantity_al, a.created_date, "
				+ "			b.int_id, b.vch_tank_name  "
				+ "			FROM distillery.received_from_plant_gin_master a , distillery.gin_store_detail_distillery b  "
				+ "		   where a.vat_id=b.int_id  "
				+ "		   and  a.int_distillery_id= " + action.getDistillery_id()
				+ "  ";

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				ReceivefromPlantGinDT dt = new ReceivefromPlantGinDT();

				dt.setDtDate_Data_Table(rs.getDate("created_date"));
				dt.setReceived_Frm_Pant_Balance_BL_Data_Table(rs
						.getDouble("received_frm_plant_bl"));
				dt.setReceived_Frm_Pant_balance_AL_Data_Table(rs
						.getDouble("received_frm_plant_al"));
				dt.setVat_Name_Data_Table(rs.getString("vch_tank_name"));
				dt.setQuantity_Balance_BL_Data_Table(rs
						.getDouble("quantity_bl"));
				dt.setQuantity_Balance_AL_Data_Table(rs
						.getDouble("quantity_al"));

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
//---------------------------------
	

	//---------------------------------------------------------------------------
		public String getStock(ReceivefromPlantGinAction act) {

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String path = null;

			try {
				con = ConnectionToDataBase.getConnection();
			//	storage_id,storage_desc ,int_distillery_id
				//int_id, int_distilleri_id, vch_tank_name,
				String queryList =" select vch_tank_name as from_vat  "+
						          //" (select storage_desc from distillery.bottling_vat_cl " +
						         // "  where int_distillery_id='"+act.getDissleriId()+"' and storage_id='"+act.getStorageId()+"')  as to_vat "+
						          " FROM  distillery.gin_store_detail_distillery where int_distilleri_id='"+act.getDistillery_id()+"' and int_id='"+act.getVat_id()+"' ";

			System.out.println("getObjectionReplies==="+queryList);
				pstmt = con.prepareStatement(queryList);

				rs = pstmt.executeQuery();

				while (rs.next()) {

				
					// System.out.println("path---------------------------"+path);

					act.setVat_f(rs.getString("from_vat"));
					//act.setVat_to(rs.getString("to_vat"));
		
		

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
