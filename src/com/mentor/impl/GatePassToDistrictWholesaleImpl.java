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

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import com.mentor.Datatable.CLGatepassDT;
import com.mentor.Datatable.GatePassToDistrictWholesaleDatatable;

import com.mentor.action.GatePassToDistrictWholesaleAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class GatePassToDistrictWholesaleImpl {

	public boolean insertorupdateflg = false;

	public boolean isInsertorupdateflg() {
		return insertorupdateflg;
	}

	public void setInsertorupdateflg(boolean insertorupdateflg) {
		this.insertorupdateflg = insertorupdateflg;
	}

	public String fetchDataImpl(int i1, int i2, int i3, String s, int dis,
			double duty, double addduty, int bottle_avl, int box_avl,
			int dispatchbox) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		String query = "";

		try {
			con = ConnectionToDataBase.getConnection();
			sql = "SELECT int_package_ml FROM distillery.fl1_stock_19_20 WHERE  int_package_ml="
					+ i1
					+ " AND  int_dist_id="
					+ i2
					+ "   AND  int_brand_id="
					+ i3 + "   AND vch_to_fl1_fl1a='" + s + "'";

			ps = con.prepareStatement(sql);
			// System.out.println("fetchDataImpl"+sql);
			rs = ps.executeQuery();
			if (rs.next()) {

				query = "UPDATE distillery.fl1_stock_19_20 SET  int_dispatch=int_dispatch+"
						+ dis
						+ ", db_duty=db_duty+"
						+ duty
						+ ", db_add_duty=db_add_duty+"
						+ addduty
						+ ", int_bottle_avaliable="
						+ (bottle_avl - dis)
						+ ", no_boxes_avaliable="
						+ (box_avl - dispatchbox)
						+ " WHERE    int_package_ml="
						+ i1
						+ " AND  int_dist_id="
						+ i2
						+ "   AND  int_brand_id="
						+ i3 + "   AND vch_to_fl1_fl1a='" + s + "'";
				this.insertorupdateflg = true;
				// System.out.println("update above============"+query);
			} else {
				query = " INSERT INTO distillery.fl1_stock_19_20(int_seq, int_dist_id, int_brand_id, vch_to_fl1_fl1a, "
						+ " int_package_ml, int_bottle_avaliable, int_dispatch, db_duty, db_add_duty, dt_date, "
						+ " box_size, no_boxes_avaliable, no_boxes_dispatch)VALUES "
						+ " ((select nextval('seq')),?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				this.insertorupdateflg = false;
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

		return query;
	}
	public boolean  etin(String casecode,GatePassToDistrictWholesaleAction act) {
		 
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		boolean s = false;
		try {
			con = ConnectionToDataBase.getConnection();
 
		
			String query = " SELECT distinct b.code_generate_through FROM distillery.fl2_stock_trxn_19_20 a, distillery.packaging_details_19_20 b "
					+ " WHERE b.package_id = a.int_pckg_id  and a.vch_gatepass_no='"+ act.getScanGatePassNo().trim()+ "' and b.code_generate_through='"+casecode+"'";

			
			//System.out.println("faizal----"+query);
			pstmt = con.prepareStatement(query);
			 
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
	public String saveMethodImpl(GatePassToDistrictWholesaleAction act) {
		Connection con = null;
		PreparedStatement ps = null, ps2 = null, ps2D = null, ps3 = null, ps4 = null, ps5 = null;
		ResultSet rs = null;
		String sql = "";
		String sql2 = "";
		String sql3 = "";
		int tok1 = 0, tok2 = 0, tok3 = 0, tok5 = 0;
		double duty = 0;
		double addduty = 0;
		String sql5 = "";int indentbox = 0;
		int seq = this.maxId(this);
		String gatepass = String.valueOf(act.getDist_id()) + "-2019-20-FL36-"
				+ seq;
		try {
			int cases = 0;
			int totalBottles = 0;

			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);

			sql = " INSERT INTO distillery.gatepass_to_districtwholesale_19_20("
					+ " int_dist_id,  dt_date, vch_from, vch_to, vch_from_lic_no, vch_to_lic_no, curr_date,"
					+ " int_max_id, licencee_id,vch_gatepass_no, vch_route_detail, vch_vehicle_no,vehicle_driver_name, "
					+ " vehicle_agency_name_adrs, licensee_name, licensee_adrs, district_1, district_2, district_3,valid_till,gross_weight, tier_weight, net_weight,licencee_district,tnt)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?, ?,?,?,?)";

			ps = con.prepareStatement(sql);

			ps.setInt(1, act.getDist_id());
			ps.setDate(2, Utility.convertUtilDateToSQLDate(act.getDt_date()));
			ps.setString(3, act.getVch_from());
			ps.setString(4, act.getVch_to());
			ps.setString(5, act.getVch_from_lic_no().trim());
			ps.setString(6, act.getVch_to_lic_no().trim());
			ps.setDate(7, Utility.convertUtilDateToSQLDate(new Date()));
			ps.setInt(8, seq);
			ps.setDouble(9, act.getLicenceeid());
			ps.setString(10, gatepass);
			ps.setString(11, act.getRouteDtl());
			ps.setString(12, act.getVehicleNo());
			ps.setString(13, act.getVehicleDrvrName());
			ps.setString(14, act.getVehicleAgencyNmAdrs());
			ps.setString(15, act.getLicenceenm());
			ps.setString(16, act.getLicenceeadd());
			ps.setInt(17, act.getDistrict1());
			ps.setInt(18, act.getDistrict2());
			ps.setInt(19, act.getDistrict3());
			ps.setDate(20,
					Utility.convertUtilDateToSQLDate(act.getValidtilldt_date()));
			ps.setDouble(21, act.getGrossWeight());
			ps.setDouble(22, act.getTierWeight());
			ps.setDouble(23, act.getNetWeight());
			ps.setInt(24, act.getDistrictLic());
			ps.setString(25, act.getTntflg());
			tok1 = ps.executeUpdate();

			// System.out.println("first status----------"+tok1);
			if (tok1 > 0) {

				for (int i = 0; i < act.displaylist.size(); i++) {

					GatePassToDistrictWholesaleDatatable dt = (GatePassToDistrictWholesaleDatatable) act
							.getDisplaylist().get(i);
					if (dt.getDispatchbottls() > 0 && dt.getDispatchbox() > 0 && dt.getIndentbox()>0) {
						tok1 = 0;
						indentbox=dt.getDispatchbox();
						sql2 = " INSERT INTO distillery.fl2_stock_trxn_19_20("
								+ " int_dissleri_id, vch_lic_no, dt, int_brand_id, int_pckg_id, avl_bottl, avl_box, breakage, balance, "
								+ " vch_gatepass_no, size,vch_batch_no, mst_seq,dispatch_box,dispatch_bottle,seq_fl1)	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,"
								+ "(select nextval('seq')),?,?,?)";

						ps2 = con.prepareStatement(sql2);

						ps2.setInt(1, act.getDist_id());
						ps2.setString(2, act.getVch_to());
						ps2.setDate(3, Utility.convertUtilDateToSQLDate(act
								.getDt_date()));
						ps2.setInt(4, dt.getInt_brand_id());
						ps2.setInt(5, dt.getInt_pckg_id());
						ps2.setInt(6, dt.getInt_bottle_avaliable());
						ps2.setInt(7, dt.getBoxAvailable());
						ps2.setInt(8, dt.getBreakage());
						ps2.setDouble(9, dt.getBalance());
						ps2.setString(10, gatepass);
						// ps2.setInt(11, seq);
						ps2.setInt(11, dt.getSize());
						ps2.setString(12, dt.getBatchNo());
						ps2.setInt(13, dt.getDispatchbox());
						ps2.setInt(14, dt.getDispatchbottls());
						ps2.setInt(15, dt.getSeq());
						cases += dt.getDispatchbox();
						totalBottles += dt.getDispatchbottls();
						tok1 = ps2.executeUpdate();

						/*if (tok1 > 0) {
							tok1 = 0;

							String updtQr = " UPDATE distillery.fl1_stock_trxn_19_20 SET "
									+ " dispatch_36=COALESCE(dispatch_36,0)+'"
									+ dt.getDispatchbottls()
									+ "',seq_fl1="
									+ dt.getSeq()
									+ " "
									+ " WHERE int_dissleri_id='"
									+ act.getDist_id()
									+ "' AND "
									+ " int_brand_id='"
									+ dt.getInt_brand_id()
									+ "' AND int_pckg_id='"
									+ dt.getInt_pckg_id()
									+ "' and  seq="
									+ dt.getSeq();

							ps3 = con.prepareStatement(updtQr);

							// System.out.println("update query----------------"+updtQr);

							tok1 = ps3.executeUpdate();
							// System.out.println("third status-------insert-------"+tok1);
						}
					}
				}
			}*/
			if (tok1 > 0) {
				tok1 = 0;

				String updtQr = " UPDATE distillery.fl2_stock_19_20 SET dispatch_box=COALESCE(dispatch_box,0)+"+dt.getDispatchbox()+",  dispatch_bottles=COALESCE(dispatch_bottles,0)+"+ dt.getDispatchbottls()+ " " +
						" WHERE int_dist_id="+ act.getDist_id()+ " AND  brand="+ dt.getInt_brand_id()+" AND package="+ dt.getInt_pckg_id()+" " +
								" and  licence_no='"+ act.getVch_from_lic_no().trim()+"' and size="+ dt.getSize()+"";

				ps3 = con.prepareStatement(updtQr);

				 

				tok1 = ps3.executeUpdate();
				// System.out.println("third status-------insert-------"+tok1);
			}
			
			
			
			if(tok1>0)
			{ 
			tok1=0;
			for (int ii = 0; ii < act.getDisplaylist1().size(); ii++) 
			{

				GatePassToDistrictWholesaleDatatable dt1 = 
						(GatePassToDistrictWholesaleDatatable) act.getDisplaylist1().get(ii);

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

						tok1 += ps2.executeUpdate();
						qury = " INSERT INTO distillery.wholesale_indent_gatepass( " +
								"  int_distillery_id, vch_gatepass, " +
								" gatepass_dt, indent_no, brand_id, package_id,boxes) " +
								" VALUES ("+act.getDist_id()+ ", '"+gatepass+"', '"+Utility.convertUtilDateToSQLDate(act.getDt_date())+"', '"+dt1.getIndentNo()+"' , "+dt.getInt_brand_id()+", "+dt.getInt_pckg_id()+","+indentbox+") ";


						ps4 = con.prepareStatement(qury);
						 
						tok1 = ps4.executeUpdate();
						}else{
							
						 
								String qury="UPDATE fl2d.indent_for_wholesale_trxn "+
									" SET  finalize_indent= coalesce(finalize_indent,0)+"+dt1.getDispatchboxIndent()+" "+
									" where  int_pckg_id="+dt.getInt_pckg_id()+" and  int_brand_id="+dt.getInt_brand_id()+" and "+
									" indent_no='"+dt1.getIndentNo()+"'  ";


							ps2 = con.prepareStatement(qury);

							tok1 += ps2.executeUpdate();
						 
							qury = " INSERT INTO distillery.wholesale_indent_gatepass( " +
									"  int_distillery_id, vch_gatepass, " +
									" gatepass_dt, indent_no, brand_id, package_id,boxes) " +
									" VALUES ("+act.getDist_id()+ ", '"+gatepass+"', '"+Utility.convertUtilDateToSQLDate(act.getDt_date())+"', '"+dt1.getIndentNo()+"' , "+dt.getInt_brand_id()+", "+dt.getInt_pckg_id()+","+dt1.getDispatchboxIndent()+") ";


							ps4 = con.prepareStatement(qury);
							 
							tok1 = ps4.executeUpdate();
							} 
					
					indentbox=indentbox-dt1.getDispatchboxIndent();
					}
				}
			}}
			
			
		}
	}
}
			if (tok1 > 0) {
				con.setAutoCommit(true);
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,
						Constants.SAVED_SUCESSFULLY);
				/*
				 * String to = act.getOfficrEmail(); String Name =
				 * act.getName();
				 * 
				 * String sub = " "; String msg = " " + gatepass + " For " +
				 * cases + " Cases Containing " + totalBottles +
				 * " Bottles Has Been Dispatched To Your District ";
				 * 
				 * //System.out.println("email msg-----------"+msg); String from
				 * = NewCommomImpl.getEmailUser(); String password =
				 * NewCommomImpl.getEmailUserPassword();
				 * NewCommomImpl.sendEmail(to, sub, msg, from,password);
				 */act.clearAll();
				act.setSaveflg(false);
				act.setSavecss("position: absolute; top: 230px; right: 0px; left: 0px; display: none; z-index: 1502; animation-name: sh-entry; animation-duration: 0.3s;");
				act.setShOverlayStyle("position: fixed; display: none; width: 100%; height: 100%; top: 0; left: 0; right: 0; bottom: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1500; cursor: pointer; animation-name: sh-entry; animation-duration: 0.3s;");

			}

			else {
				con.rollback();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);

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
				if (ps2D != null)
					ps2D.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}

		return "";
	}

	/*
	 * public double fetchDuty(int int_brand_id, String vch_package_ml){
	 * Connection conn = null; PreparedStatement ps = null; ResultSet rs=null;
	 * String query = null; double d=0; try{
	 * 
	 * 
	 * query =
	 * "SELECT   db_duty FROM distillery.brand_registration_19_20 WHERE brand_id="
	 * +int_brand_id+" AND  int_package_ml="+vch_package_ml;
	 * 
	 * 
	 * conn = ConnectionToDataBase.getConnection();
	 * ps=conn.prepareStatement(query);
	 * 
	 * System.out.println("666666666666"+query); rs=ps.executeQuery();
	 * while(rs.next()) { d=(rs.getDouble("db_duty")); } } catch(Exception e) {
	 * e.printStackTrace(); } finally { try {
	 * 
	 * if(conn!=null)conn.close(); if(ps!=null)ps.close();
	 * if(rs!=null)rs.close();
	 * 
	 * 
	 * } catch(Exception e) { e.printStackTrace(); } }
	 * 
	 * return d;
	 * 
	 * }
	 */

	public ArrayList displaylistImpl(GatePassToDistrictWholesaleAction act,
			String val) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		int i = 1;

		try {
			 
			selQr = " SELECT distinct  a.package_name, a.package_id,  c.brand, b.brand_name,"
					+ " c.package, c.stock_bottles-coalesce(c.dispatch_bottles,0)  as avlbottle,(c.stock_box-c.dispatch_box) as dispatchd_box, d.box_size  "
					+ " FROM distillery.packaging_details_19_20 a, distillery.brand_registration_19_20 b, "
					+ " distillery.fl2_stock_19_20 c, distillery.box_size_details d "
					+ " WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.brand "
					+ " AND a.package_id=c.package AND b.brand_id=c.brand "
					+ " AND c.int_dist_id='"
					+ act.getDist_id()
					+ "' "
					+ "  AND c.licence_no='"+val.trim()+"' AND c.stock_bottles-COALESCE(c.dispatch_bottles,0)>0 "
					+ " AND a.box_id=d.box_id AND a.quantity=d.qnt_ml_detail AND b.license_category='LAB' "
					+ "   ";

			String selQr1 = " SELECT distinct  a.package_name, a.package_id,  c.brand, b.brand_name, "
					+ " c.package, c.stock_bottles-coalesce(c.dispatch_bottles,0)  as avlbottle,(c.stock_box-c.dispatch_box) as dispatchd_box, d.box_size  "
					+ " FROM distillery.packaging_details_19_20 a, distillery.brand_registration_19_20 b, "
					+ " distillery.fl2_stock_19_20 c, distillery.box_size_details d "
					+ " WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.brand "
					+ " AND a.package_id=c.package AND b.brand_id=c.brand "
					+ " AND c.int_dist_id='"
					+ act.getDist_id()
					+ "'  AND c.licence_no='"+val.trim()+"' AND c.stock_bottles-COALESCE(c.dispatch_bottles,0)>0  "
					+ " AND a.box_id=d.box_id AND a.quantity=d.qnt_ml_detail AND b.license_category!='LAB' "
					+ "  ";

			 
			conn = ConnectionToDataBase.getConnection();
			
			

			if (act.getVch_to().equalsIgnoreCase("FL2B")) {
				System.out.println("FL2B-data for dt:-"+selQr);
				ps = conn.prepareStatement(selQr);
				 

			} else if (act.getVch_to().equalsIgnoreCase("FL2")) {
				System.out.println("FL2-data for dt:-"+selQr1);
				ps = conn.prepareStatement(selQr1);
				  

			}

			rs = ps.executeQuery();
			while (rs.next()) {
				GatePassToDistrictWholesaleDatatable dt = new GatePassToDistrictWholesaleDatatable();
				dt.setInt_brand_id(rs.getInt("brand"));
				dt.setInt_pckg_id(rs.getInt("package"));
				dt.setVch_brand(rs.getString("brand_name"));
				dt.setInt_bottle_avaliable(rs.getInt("avlbottle"));
				dt.setDesc(rs.getString("package_name"));
				dt.setSeq(i);
				dt.setSize(rs.getInt("box_size"));
				dt.setBoxAvailable(rs.getInt("dispatchd_box"));
				dt.setSlno(i);
				 
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

	// -----------list
	public ArrayList fromliclistImpl(GatePassToDistrictWholesaleAction act,
			String val) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);
		try {
			String query = " SELECT distinct vch_lic_type, trim(vch_lic_no) as vch_lic_no FROM distillery.fl1_stock_trxn_19_20 "
					+ " WHERE vch_lic_type='"
					+ val
					+ "' AND int_dissleri_id='"
					+ act.getDist_id() + "' ";
			 

			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);

	 

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				item.setValue(rs.getString("vch_lic_no"));
				item.setLabel(rs.getString("vch_lic_no"));

				list.add(item);

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

	public ArrayList toliclistImpl(GatePassToDistrictWholesaleAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);
		try {
			String query = "SELECT vch_licence_no FROM licence.licence_entery_fl3_fl1 WHERE int_distillery_id="
					+ act.getDist_id()
					+ " AND vch_licence_type='"
					+ act.getVch_to() + "' and vch_lic_unit_type='D'";
			;

			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				// item.setValue(rs.getInt("int_state_id"));
				item.setValue(rs.getString("vch_licence_no"));
				item.setLabel(rs.getString("vch_licence_no"));

				list.add(item);

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

	public String licenceedet(GatePassToDistrictWholesaleAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT distinct int_app_id,vch_firm_name,vch_applicant_name,vch_core_address FROM licence.fl2_2b_2d_19_20 WHERE vch_license_type='FL2'  and vch_licence_no='"
					+ act.getVch_to_lic_no().trim() + "'";
			;

			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();
			// System.out.println("query----"+query);
			if (rs.next()) {
				act.setLicenceeid(rs.getInt("int_app_id"));
				act.setLicenceeadd(rs.getString("vch_core_address"));
				act.setLicenceenm(rs.getString("vch_firm_name"));
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
		return "";
	}

	public ArrayList toliclistImpl2a(GatePassToDistrictWholesaleAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);
		try {
			String query = " SELECT distinct vch_licence_no FROM licence.fl2_2b_2d_19_20 WHERE vch_license_type='FL2'  "
					+ " AND vch_approval='A' and core_district_id="
					+ Integer.parseInt(act.getDistrictcode());

			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(query);

			// System.out.println("get license nmbr query==============="+query);
			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				// item.setValue(rs.getInt("int_state_id"));
				item.setValue(rs.getString("vch_licence_no"));
				item.setLabel(rs.getString("vch_licence_no"));

				list.add(item);

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
	
	
	
	
	
	
	
	
	
	
	

	public String getDetails(GatePassToDistrictWholesaleAction ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String queryList = "";
		try {
			con = ConnectionToDataBase.getConnection();
			
			/*String queryList = 	" SELECT int_app_id_f, vch_undertaking_name,vch_wrk_add  " +
								" FROM  dis_mst_pd1_pd2_lic WHERE vch_wrk_phon="+ ResourceUtil.getUserNameAllReq().trim();*/
			
			
			
			queryList = 		" SELECT x.app_id, x.distillery_id, x.license_district, x.license_no, x.vch_licence_type,               "+
								" x.distilery_district, x.unit_name, x.unit_adrs,                      "+
								" CASE WHEN x.license_district::text=x.distilery_district THEN 'SAME' else 'NOTSAME' end as status      "+
								" FROM                                                                                                  "+
								" (SELECT a.int_app_id as app_id, a.int_distillery_id as distillery_id, a.district as license_district, "+
								" a.vch_license_fl1a as license_no, a.vch_licence_type, b.vch_unit_dist as distilery_district,          "+
								" a.vch_unit_name as unit_name, a.vch_unit_addrs as unit_adrs "+
								" FROM licence.fl3a_fl1a a, public.dis_mst_pd1_pd2_lic b                              "+
								" WHERE a.int_distillery_id=b.int_app_id_f AND a.vch_lic_unit_type='D'                                  "+
								" AND a.vch_licence_type='FL1A' AND a.loginuser='"+ResourceUtil.getUserNameAllReq().trim() +"'          "+
								" UNION                                                                                                 "+
								" SELECT a.int_lic_id as app_id, a.int_distillery_id as distillery_id, a.district as license_district,  "+
								" a.vch_licence_no as license_no, a.vch_licence_type, b.vch_unit_dist as distilery_district,            "+
								" a.vch_unit_name as unit_name, a.vch_unit_addrs as unit_adrs "+
								" FROM licence.licence_entery_fl3_fl1 a, public.dis_mst_pd1_pd2_lic b                 "+
								" WHERE a.int_distillery_id=b.int_app_id_f AND a.vch_lic_unit_type='D'                                  "+
								" AND a.vch_licence_type='FL1' AND a.loginuser='"+ResourceUtil.getUserNameAllReq().trim() +"' )x ";

			pstmt = con.prepareStatement(queryList);
			
		//	System.out.println("hidden query================"+queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ac.setName(rs.getString("unit_name"));
				ac.setDist_id(rs.getInt("distillery_id"));
				ac.setAddress(rs.getString("unit_adrs"));
				ac.setVch_from(rs.getString("vch_licence_type"));
				ac.setVch_from_lic_no(rs.getString("license_no"));

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

	// ===================select season id====================

	public ArrayList getSeasonDetails(GatePassToDistrictWholesaleAction ac) {
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
			e.printStackTrace();
		}

		finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return list;
	}

	// =====================get max id sequence==============================

	public int maxId(GatePassToDistrictWholesaleImpl impl) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(int_max_id) as id FROM distillery.gatepass_to_districtwholesale_19_20 ";
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

	public boolean printReport(GatePassToDistrictWholesaleAction action) {
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
		try {
			con = ConnectionToDataBase.getConnection();
			
		/*	reportQuery="select vch_undertaking_name, vch_gatepass_no,vehicle_driver_name,vch_wrk_add, vch_from," +
					" vch_to, vch_from_lic_no, vch_to_lic_no,  curr_date, licencee_id, vch_route_detail, " +
					"vch_vehicle_no, valid_till, vehicle_agency_name_adrs,  licensee_name, licensee_adrs  ," +
					"dispatch_box ,gross_weight, tier_weight, net_weight, int_brand_id,box_size,no_bottl,brand_name," +
					" strength, package_name, box_id,ml,vch_firm_name, vch_batch_no,dt_date,bl,al  " +
					"from distillery.GatepassToDistrictWholesalePrint_19_20("+action.getDist_id()+", '"+action.getPrintGatePassNo().trim()+"' )";
			 */
			reportQuery="select time_dt,indent_no, vch_undertaking_name, vch_gatepass_no,vehicle_driver_name,vch_wrk_add, vch_from," +
					" vch_to, vch_from_lic_no, vch_to_lic_no,  curr_date, licencee_id, vch_route_detail, " +
					"vch_vehicle_no, valid_till, vehicle_agency_name_adrs,  licensee_name, licensee_adrs  ," +
					"dispatch_box ,gross_weight, tier_weight, net_weight, int_brand_id,box_size,no_bottl,brand_name," +
					" strength, package_name, box_id,ml,vch_firm_name, vch_batch_no,dt_date,bl,al  " +
					"from distillery.GatepassToDistrictWholesalePrint_19_20("+action.getDist_id()+", '"+action.getPrintGatePassNo().trim()+"' )";
			pst = con.prepareStatement(reportQuery);
			// pst.setInt(1, action.getDist_id());
			// pst.setDate(2,
			// Utility.convertUtilDateToSQLDate(action.getDt_date()));
			// pst.setDate(2,
			// Utility.convertUtilDateToSQLDate(action.getPrintDate()));

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
						+ "GatepassDistrictWholesaleprint.jasper");

				JasperPrint print = JasperFillManager.fillReport(jasperReport,
						parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				JasperExportManager.exportReportToPdfFile(print, relativePathpdf+ File.separator + action.getPrintGatePassNo() + ".pdf");
				action.setPdfname(action.getPrintGatePassNo() + ".pdf");
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

	// ------------------------------ PRINT
	// ---------------------------------------

	public boolean printDraftReport(GatePassToDistrictWholesaleAction action) {
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

		try {
			con = ConnectionToDataBase.getConnection();

			reportQuery="select indent_no,vch_undertaking_name, vch_gatepass_no,vehicle_driver_name,vch_wrk_add, vch_from," +
					" vch_to, vch_from_lic_no, vch_to_lic_no,  curr_date, licencee_id, vch_route_detail, " +
					"vch_vehicle_no, valid_till, vehicle_agency_name_adrs,  licensee_name, licensee_adrs  ," +
					"dispatch_box ,gross_weight, tier_weight, net_weight, int_brand_id,box_size,no_bottl,brand_name," +
					" strength, package_name, box_id,ml,vch_firm_name, vch_batch_no,dt_date,bl,al  " +
					"from distillery.GatepassToDistrictWholesalePrint_19_20("+action.getDist_id()+", '"+action.getPrintGatePassNo().trim()+"' )";


			/*reportQuery="select vch_undertaking_name, vch_gatepass_no,vehicle_driver_name,vch_wrk_add, vch_from," +
					" vch_to, vch_from_lic_no, vch_to_lic_no,  curr_date, licencee_id, vch_route_detail, " +
					"vch_vehicle_no, valid_till, vehicle_agency_name_adrs,  licensee_name, licensee_adrs  ," +
					"dispatch_box ,gross_weight, tier_weight, net_weight, int_brand_id,box_size,no_bottl,brand_name," +
					" strength, package_name, box_id,ml,vch_firm_name, vch_batch_no,dt_date,bl,al  " +
					"from distillery.GatepassToDistrictWholesalePrint_19_20("+action.getDist_id()+", '"+action.getPrintGatePassNo().trim()+"' )";

			*///System.out.println("reportQuery00="+reportQuery);
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
						+ "GatepassDistrictWholesaleDraft.jasper");

				JasperPrint print = JasperFillManager.fillReport(jasperReport,
						parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				JasperExportManager.exportReportToPdfFile(print, relativePathpdf+ File.separator+ "GatepassDistrictWholesaleDraft-" + action.getPrintGatePassNo() + ".pdf");

				GatePassToDistrictWholesaleDatatable dt = new GatePassToDistrictWholesaleDatatable();
				
				dt.setPdfDraft("GatepassDistrictWholesaleDraft-" + action.getPrintGatePassNo() + ".pdf");				
				action.setPdfDraft("GatepassDistrictWholesaleDraft-" + action.getPrintGatePassNo() + ".pdf");
				// dt.setPrintFlag(true);
				dt.setPrintDraftFlagDt(true);
				printFlag = true;
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Data Found", "No Data Found"));
				action.setPrintFlag(false);
				// printFlag=false;

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

	// ---------------------------------------------------------------

	public ArrayList getGatePassWholeSaleListManufacture(
			GatePassToDistrictWholesaleAction action) {
		ArrayList list = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = " SELECT int_dist_id, coalesce(seq_fl1,0) as seqfl11,   "
				+ " dt_date, vch_from, vch_to, vch_from_lic_no, vch_to_lic_no,  curr_date, "
				+ " int_max_id, vch_gatepass_no ,COALESCE(vch_finalize,'N') as vch_finalize ,coalesce(tnt,'X') as tnt FROM "
				+ " distillery.gatepass_to_districtwholesale_19_20 where int_dist_id="+ action.getDist_id() + " " +
				" AND vch_from_lic_no='"+action.getVch_from_lic_no()+"' " +
				" order by int_max_id desc ";
		try {
			list = new ArrayList();
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();
			int i = 1;
			while (rs.next()) {
				GatePassToDistrictWholesaleDatatable dt = new GatePassToDistrictWholesaleDatatable();
				dt.setSno(i);
				dt.setInt_dist_id(rs.getInt("int_dist_id"));
				dt.setDt_date(rs.getDate("dt_date"));
				dt.setVch_from(rs.getString("vch_from"));
				dt.setVch_to(rs.getString("vch_to"));
				dt.setVch_from_lic_no(rs.getString("vch_from_lic_no"));
				dt.setVch_to_lic_no(rs.getString("vch_to_lic_no"));
				dt.setGatepassNo(rs.getString("vch_gatepass_no"));
				dt.setCurr_date(rs.getDate("curr_date"));
				dt.setInt_max_id(rs.getInt("int_max_id"));
				dt.setVch_gatepass_no(rs.getString("vch_gatepass_no"));
				/*
				 * action.setPrintDraft(true); action.setFinalPrint(false);
				 */

			/*	if (rs.getString("tnt").equalsIgnoreCase("O")) {
					dt.setFinalizePrint(true);
					dt.setPrintDraft(false);
				} else */if (rs.getString("vch_finalize").equalsIgnoreCase("F")) {
					dt.setFinalizePrint(true);
					dt.setPrintDraft(false);
				} else {
					dt.setFinalizePrint(false);
					dt.setPrintDraft(true);
				}
				list.add(dt);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getDistList() {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);
		try {
			String query = "SELECT DistrictID, Description FROM district order by Description";

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			// pstmt.setInt(1,id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				item = new SelectItem();

				item.setValue(rs.getString("DistrictID"));
				item.setLabel(rs.getString("Description"));

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

	public String getEmailDetails(GatePassToDistrictWholesaleAction ac) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String queryList = " SELECT a.officer_email "
					+ " FROM public.district a, distillery.gatepass_to_districtwholesale_19_20 b "
					+ " WHERE a.districtid=b.licence_district ";

			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ac.setOfficrEmail(rs.getString("officer_email"));
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

	/*
	 * public boolean printDraftReport(GatePassToDistrictWholesaleAction act,
	 * GatePassToDistrictWholesaleDatatable dt) {
	 * 
	 * String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
	 * String relativePath = mypath + File.separator + "ExciseUp"+
	 * File.separator + "WholeSale" + File.separator + "jasper"; String
	 * relativePathpdf = mypath + File.separator + "ExciseUp"+ File.separator +
	 * "WholeSale" + File.separator + "pdf"; JasperReport jasperReport = null;
	 * JasperPrint jasperPrint = null; PreparedStatement pst = null; Connection
	 * con = null; ResultSet rs = null; String reportQuery = null; boolean
	 * printFlag = false;
	 * 
	 * try { con = ConnectionToDataBase.getConnection();
	 * 
	 * reportQuery =
	 * " SELECT DISTINCT a.int_fl2_fl2b_id, a.vch_gatepass_no, a.dt_date, a.vch_to, "
	 * + " a.vch_to_lic_no, a.curr_date, a.vch_route_detail, a.vch_vehicle_no, "
	 * + " a.vehicle_driver_name, a.vehicle_agency_name_adrs, a.licensee_name, "
	 * +
	 * " a.licensee_adrs, a.valid_till, a.licence_district, a.gross_weight, a.tier_weight, "
	 * +
	 * " a.net_weight, a.vch_from,b.vch_firm_name, c.dispatch_box, c.int_brand_id, "
	 * + " c.size as box_size, c.dispatch_bottle as no_bottl, c.vch_batch_no, "
	 * +
	 * " d.brand_name, d.strength, e.package_name,e.box_id, f.qnt_ml_detail as ml, "
	 * + " (((c.dispatch_bottle)*f.qnt_ml_detail)/1000) as bl, " +
	 * " ((((c.dispatch_bottle*f.qnt_ml_detail)/1000)*d.strength)/100) as al " +
	 * " FROM fl2d.gatepass_to_districtwholesale_fl2_fl2b a, licence.fl2_2b_2d_19_20 b, "
	 * + " fl2d.fl2_stock_trxn_fl2_fl2b c, distillery.brand_registration_19_20 d, " +
	 * " distillery.packaging_details_19_20 e, distillery.box_size_details f " +
	 * " WHERE a.int_fl2_fl2b_id=b.int_app_id AND a.int_fl2_fl2b_id=c.int_fl2_fl2b_id "
	 * + " AND a.vch_gatepass_no=c.vch_gatepass_no and a.dt_date=c.dt " +
	 * " AND c.int_brand_id=d.brand_id AND d.brand_id=e.brand_id_fk " +
	 * " AND c.int_pckg_id=e.package_id AND e.box_id=f.box_id AND f.qnt_ml_detail=e.quantity "
	 * +
	 * " AND a.int_fl2_fl2b_id="+act.getFl2_fl2bId()+" AND a.vch_gatepass_no='"
	 * +act.getPrintGatePassNo()+"' ";
	 * 
	 * System.out.println("reportQuery---------------------"+reportQuery);
	 * 
	 * pst = con.prepareStatement(reportQuery); rs = pst.executeQuery();
	 * 
	 * if (rs.next()) { rs = pst.executeQuery();
	 * 
	 * Map parameters = new HashMap(); parameters.put("REPORT_CONNECTION", con);
	 * // parameters.put("SUBREPORT_DIR", relativePath+File.separator);
	 * parameters.put("image", relativePath + File.separator);
	 * 
	 * JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
	 * 
	 * jasperReport = (JasperReport) JRLoader.loadObject(relativePath+
	 * File.separator+ "GatePassToRetailerFL2_FL2BDraft1.jasper");
	 * 
	 * JasperPrint print = JasperFillManager.fillReport(jasperReport,parameters,
	 * jrRs); Random rand = new Random(); int n = rand.nextInt(250) + 1;
	 * 
	 * JasperExportManager.exportReportToPdfFile(print,relativePathpdf +
	 * File.separator+ "GatePassToRetailerFL2_FL2BDraft"+
	 * act.getPrintGatePassNo() + "-" + n+ ".pdf");
	 * 
	 * System.out.println("------------ pdf ---------");
	 * JasperExportManager.exportReportToPdfFile(print,relativePathpdf +
	 * File.separator+ "GatePassToRetailerFL2_FL2BDraft1"+n+".pdf"); //
	 * act.getPrintGatePassNo() + "-" + n+ ".pdf");
	 * 
	 * 
	 * 
	 * 
	 * dt.setPdfDraft("GatePassToRetailerFL2_FL2BDraft1"+ n + ".pdf");
	 * //dt.setPdfDraft("GatePassToRetailerFL2_FL2BDraft1"+ n + ".pdf");
	 * 
	 * act.setPdfDraft("GatePassToRetailerFL2_FL2BDraft1"+ n + ".pdf");
	 * dt.setPrintDraftFlagDt(true);
	 * 
	 * printFlag = true; } else {
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage("No Data Found", "No Data Found"));
	 * 
	 * printFlag = false; dt.setPrintDraftFlagDt(false); } } catch (JRException
	 * e) { e.printStackTrace(); } catch (Exception e) { e.printStackTrace(); }
	 * finally { try { if (rs != null) rs.close(); if (con != null) con.close();
	 * } catch (SQLException e) { e.printStackTrace(); } } return printFlag;
	 * 
	 * }
	 */
	public void saveExcelData(GatePassToDistrictWholesaleAction action) {
		String gatepass = "";
		int status = 0, flag = 1, excelcase = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		FileInputStream fileInputStream = null;
		XSSFWorkbook workbook = null;
		try {
			
			String query = "DELETE FROM distillery.distillery_gatepass_casecode where gatespass ='"+ action.getScanGatePassNo().trim()+ "' ";
			 
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt=null;
			String sql = "INSERT INTO distillery.distillery_gatepass_casecode(gatespass, casecode)VALUES (?, ?)";

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
						if (action.getScanGatePassNo().equalsIgnoreCase(
								gatepass)) {
							// cellA2 = row2.getCell((short) 0);
							// casecode=getCellValue(cellA2);
							
							// pstmt.setString(1, gatepass);
							// pstmt.setString(2, casecode);
							// status= pstmt.executeUpdate();
							// excelcase++;
							i = 1;
						} else {

							flag = 0;
						}
					} else {

						cellA1 = row1.getCell((short) 0);
						casecode = getCellValue(cellA1);
						if(this.etin(casecode.trim().substring(0, 12), action)==true){
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								" Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!" ," Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!"));	
					}else{
						pstmt.setString(1, gatepass);
						pstmt.setString(2, casecode);

					 pstmt.addBatch();
						excelcase++;
						action.setExcelCases(excelcase);
						i = 1;
					}}
				}

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
				status=pstmt.executeBatch().length;
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
			 	
			if (  status > 0  ) {
				
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

	public void deleteData(GatePassToDistrictWholesaleAction action) {
		Connection con = null;
		PreparedStatement stmt = null;

		String query = "DELETE FROM distillery.distillery_gatepass_casecode where gatespass ='"
				+ action.getScanGatePassNo() + "' ";
		// '" + action.getScanGatePassNo() + "' ";
		int status = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			stmt = con.prepareStatement(query);
			status = stmt.executeUpdate();
			if (status > 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Buffer Cleared!!", "Buffer Cleared!!"));
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public ArrayList getExcelData(GatePassToDistrictWholesaleAction action) {
		ArrayList list = new ArrayList();

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		 int boxingCount=0;
         int listSize=0;
		/*String query = "SELECT gatespass,casecode FROM distillery.distillery_gatepass_casecode where gatespass ='"
				+ action.getScanGatePassNo() + "'";*/

		
		String query =/* "SELECT distinct  b.code_generate_through ,c.brand_name,b.package_name,d.gatespass,d.casecode " +
				" FROM distillery.fl2_stock_trxn_19_20 a, " +
				" distillery.packaging_details_19_20 b," +
				" distillery.brand_registration_19_20 c," +
				" distillery.distillery_gatepass_casecode d" +
				" WHERE b.package_id = a.int_pckg_id and c.brand_id=a.int_brand_id" +
				" and a.vch_gatepass_no=d.gatespass and d.gatespass='"
				+ action.getScanGatePassNo() + "'";*/
				"SELECT distinct  d.gatespass,d.casecode " +
				" FROM  distillery.distillery_gatepass_casecode d " +
				" WHERE   d.gatespass='"+ action.getScanGatePassNo() + "'";
		//System.out.println("faizal------------"+query);
		try {
			con = ConnectionToDataBase.getConnection();
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			// Date d=Utility.convertSqlDateToUtilDate(date_created);
			while (rs.next()) {
				GatePassToDistrictWholesaleDatatable dt = new GatePassToDistrictWholesaleDatatable();

				action.setValFlag(true);
				dt.setGatepass(rs.getString("gatespass"));
				dt.setCasecode(rs.getString("casecode"));
				dt.setBrand_name(this.brand_pack(rs.getString("casecode").substring(0, 12)));
				//dt.setBrand_name(rs.getString("brand_name")+"-"+rs.getString("package_name"));
				//dt.setPack_name(rs.getString("package_name"));
				
				String datenew=rs.getString("casecode").trim().substring(16, 22);
				
				datenew="20"+datenew;
				datenew	=datenew.substring(0,4)+"-"+datenew.substring(4,6)+"-"+datenew.substring(6);
				
				Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(datenew);
				 
				
				boolean flag=getCascodeMatch(rs.getString("casecode").substring(26,
						rs.getString("casecode").length()),rs.getString("casecode").substring(0, 12),action,date1);
				
			//	System.out.println("--"+rs.getString("casecode").substring(26,rs.getString("casecode").length())+"--"+rs.getString("casecode").substring(0, 12)+"=="+Utility.convertUtilDateToSQLDate(date1)+"----"+datenew);
				dt.setPlan_date(Utility.convertUtilDateToSQLDate(date1));
				if(flag)
				{
					++listSize;
		        dt.setCascodeMatching("BoxingDone");
				}else{
					++boxingCount;
					 dt.setCascodeMatching("BoxingNotDone");
				}
				
				if(boxingCount!=0 || listSize<=0 )
				{
					action.setBottlingNotDoneFlag(true);
				}
				list.add(dt);

			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	public String brand_pack(String etn){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String brand_pack="";
		String sql="";
		
		
			sql="select brand_name,package_name from distillery.packaging_details_19_20 a,distillery.brand_registration_19_20 b" +
					" where a.brand_id_fk=b.brand_id and code_generate_through='"+etn+"'";
	
		
		
		
	try{
		System.out.println("------ "+sql);
		conn=ConnectionToDataBase.getConnection();
		pstmt=conn.prepareStatement(sql);
		
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			brand_pack=rs.getString("brand_name")+"-"+rs.getString("package_name");
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
		
		
		return brand_pack;
	}

	/*
	 * public boolean updateFL3(GatePassToDistrictWholesaleAction act) { int
	 * save=0; boolean val=false; PreparedStatement ps=null; Connection
	 * con1=null; Connection con2=null; String query="";
	 * 
	 * if(act.getVch_from().equalsIgnoreCase("FL1")) { query=
	 * "UPDATE bottling_unmapped.disliry_unmap_fl3 SET  fl36gatepass=?, fl36_date=? WHERE casecode=? and fl36_date=null and fl11_date is not null  "
	 * ; } else if(act.getVch_from().equalsIgnoreCase("FL1A")) { query=
	 * "UPDATE bottling_unmapped.disliry_unmap_fl3a SET  fl36gatepass=?, fl36_date=? WHERE casecode=? and fl36_date=null and fl11_date is not null  "
	 * ; }
	 * 
	 * 
	 * try { con1=ConnectionToDataBase.getConnection19_20();
	 * con1.setAutoCommit(false); con2.setAutoCommit(false); for (int i = 0; i <
	 * act.getGetVal().size(); i++) { GatePassToDistrictWholesaleDatatable dt =
	 * (GatePassToDistrictWholesaleDatatable) act .getGetVal().get(i);
	 * ps=con1.prepareStatement(query);
	 * 
	 * 
	 * ps.setString(1,dt.getGatepass());
	 * 
	 * ps.setDate(2,Utility.convertUtilDateToSQLDate(new Date()));
	 * //ps.setString(3,dt.getCasecode());
	 * 
	 * 
	 * ps.setString(3,dt.getCasecode().substring(29,dt.getCasecode().length()));
	 * 
	 * save=ps.executeUpdate(); if(save>0) { try {
	 * con2=ConnectionToDataBase.getConnection(); con2.setAutoCommit(false);
	 * query=
	 * "UPDATE distillery.gatepass_to_districtwholesale_19_20	SET vch_finalize='F' where vch_gatepass_no='"
	 * +act.getScanGatePassNo()+"' "; ps=con2.prepareStatement(query);
	 * ps.executeUpdate(); }catch(Exception ex) { ex.printStackTrace(); }
	 * finally { try{ if(ps!=null)ps.close();
	 * 
	 * if(con2!=null)con2.close(); } catch(Exception ex) { ex.printStackTrace();
	 * } }
	 * 
	 * } } if(save>0) { val=true;
	 * 
	 * } else { val=false; }
	 * 
	 * 
	 * }catch(Exception ex) { ex.printStackTrace(); } finally{ try{
	 * if(ps!=null)ps.close();
	 * 
	 * if(con1!=null)con1.close(); }catch(Exception ex) { ex.printStackTrace();
	 * } } return val;
	 * 
	 * 
	 * }
	 */
	public boolean updateFL3(GatePassToDistrictWholesaleAction act) {
		int save = 0;
		int save1 = 0;
		boolean val = false;
		PreparedStatement ps = null;
		Connection con = null;
		Connection con1 = null;
		String query = "";
		String datenew="";
		Date date1=null;
		if (act.getGetvch().equalsIgnoreCase("FL1")) {
			query = "UPDATE bottling_unmapped.disliry_unmap_fl3 SET  fl36gatepass=?, fl36_date=? WHERE casecode=? and fl11_date is not null and fl36_date is null and etin=? and date_plan=?";
		} else if (act.getGetvch().equalsIgnoreCase("FL1A")) {
			query = "UPDATE bottling_unmapped.disliry_unmap_fl3a SET  fl36gatepass=?, fl36_date=? WHERE casecode=? and fl11_date is not null and fl36_date is null and etin=? and date_plan=?";
		}

		try {
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			int j[] = null;
			con = ConnectionToDataBase.getConnection();
			con1 = ConnectionToDataBase.getConnection19_20();
			con.setAutoCommit(false);
			con1.setAutoCommit(false);
			ps = con1.prepareStatement(query);
			for (int i = 0; i < act.getGetVal().size(); i++) {
				GatePassToDistrictWholesaleDatatable dt = (GatePassToDistrictWholesaleDatatable) act
						.getGetVal().get(i);

				datenew=dt.getCasecode().substring(16,22).trim();
				
				datenew="20"+datenew;
				datenew	=datenew.substring(0,4)+"-"+datenew.substring(4,6)+"-"+datenew.substring(6);
				 date1=new SimpleDateFormat("yyyy-MM-dd").parse(datenew);
				

				ps.setString(1, dt.getGatepass());
				ps.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));

				ps.setString(
						3,
						dt.getCasecode().substring(26,
								dt.getCasecode().length()));
				
				ps.setString(4, dt.getCasecode().substring(0,12));
				 ps.setDate(5, Utility.convertUtilDateToSQLDate(date1));
				ps.addBatch();
				 

			}j = ps.executeBatch();
			save = j.length;
			 
			if (act.getGetVal().size() == save && save>0) {
				save = 0;
				query = " UPDATE distillery.gatepass_to_districtwholesale_19_20	SET vch_finalize='F', finalize_dt_time=? " +
						" where vch_gatepass_no='"+ act.getScanGatePassNo() + "' ";

				ps = con.prepareStatement(query);
				ps.setString(1, dateFormat.format(new Date()));
				save = ps.executeUpdate();

				query = "DELETE FROM distillery.distillery_gatepass_casecode where gatespass ='"
						+ act.getScanGatePassNo() + "' ";
				ps =con.prepareStatement(query);
				ps.executeUpdate();
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

	
	
	public boolean updateFL3FL11(GatePassToDistrictWholesaleAction act) {
		int save = 0;
		int save1 = 0;
		boolean val = false;
		PreparedStatement ps = null;
		Connection con = null;
		Connection con1 = null;
		String query = "";

		if (act.getGetvch().equalsIgnoreCase("FL1")) {
			query = "UPDATE bottling_unmapped.disliry_unmap_fl3 SET  fl36gatepass=?, fl36_date=? WHERE fl36gatepass='"+act.getFl11gtps()+"'";
		} else if (act.getGetvch().equalsIgnoreCase("FL1A")) {
			query = "UPDATE bottling_unmapped.disliry_unmap_fl3a SET  fl36gatepass=?, fl36_date=? WHERE fl36gatepass='"+act.getFl11gtps()+"'";
		}

		try {
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			con = ConnectionToDataBase.getConnection();
			con1 = ConnectionToDataBase.getConnection19_20();
			con.setAutoCommit(false);
			con1.setAutoCommit(false);
			ps = con1.prepareStatement(query);
			ps.setString(1, act.getScanGatePassNo());
				ps.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));
		save=ps.executeUpdate();

			 
			if (save>0) {
				save = 0;
				query = " UPDATE distillery.gatepass_to_districtwholesale_19_20 SET vch_finalize='F', finalize_dt_time=?  " +
						" WHERE vch_gatepass_no='"+ act.getScanGatePassNo() + "' ";

				ps = con.prepareStatement(query);
				ps.setString(1, dateFormat.format(new Date()));
				save = ps.executeUpdate();

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

	
	
	public boolean getCascodeMatch(String casecode,String etin ,GatePassToDistrictWholesaleAction act,Date date)
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean flag=false;
		String sql="";
		
		
			if (act.getGetvch().equalsIgnoreCase("FL1")) {
				sql = "select * from bottling_unmapped.disliry_unmap_fl3  WHERE casecode=? and etin=? and fl11_date is not null and fl36_date is  null and date_plan='"+Utility.convertUtilDateToSQLDate(date)+"'";
		//	System.out.println("--"+sql+"--"+casecode+"--"+etin);
			} else if (act.getGetvch().equalsIgnoreCase("FL1A")) {
				sql = "select * from bottling_unmapped.disliry_unmap_fl3a  WHERE casecode=? and etin=? and fl11_date is not null  and fl36_date is  null and date_plan='"+Utility.convertUtilDateToSQLDate(date)+"'";
			//	System.out.println("--"+sql+"--"+casecode+"--"+etin);
			}
	
		
		
		
	try{
		conn=ConnectionToDataBase.getConnection19_20();
		pstmt=conn.prepareStatement(sql);
		 pstmt.setString(1, casecode);
		pstmt.setString(2, etin);
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
	
	
	
	
	
	
	
	
	
	
	
	public void savCSV(GatePassToDistrictWholesaleAction action)
			throws IOException {
		Connection con = null;
		PreparedStatement stmt = null;
		String query = "INSERT INTO bwfl.csv_gatepass_casedcode(gatepass, casecode)VALUES (?, ?)";
		String gatepass = "";
		int status = 0;
		BufferedReader bReader = new BufferedReader(new FileReader(
				action.getCsvFilepath()));
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
							 
							gatepass = sd;
						} else {
							 
							casecode = sd;
							stmt.setString(1, gatepass);
							stmt.setString(2, casecode);

							status = stmt.executeUpdate();
						}
					}

					tokenNumber = 0;
				}
			}

			/*
			 * while ((line = bReader.readLine()) != null) { int i=0; String
			 * casecode="";
			 * 
			 * 
			 * 
			 * 
			 * if (line != null) {
			 * 
			 * if(i==0) { gatepass=line; i=1; } else { casecode=line;
			 * 
			 * stmt.setString(1,gatepass); stmt.setString(2,casecode);
			 * 
			 * status=stmt.executeUpdate(); i=1; }
			 * 
			 * }
			 */

			 

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

	public int excelCases(GatePassToDistrictWholesaleAction act) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String query = " SELECT count(*) as dispatchd_box FROM distillery.distillery_gatepass_casecode "
					+ " WHERE gatespass='" + act.getScanGatePassNo() + "'";

			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				id = (rs.getInt("dispatchd_box"));

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
		return id;

	}

	public int recieveCases(GatePassToDistrictWholesaleAction act) {
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
			String query = "SELECT SUM(dispatch_box) as dispatch_box FROM distillery.fl2_stock_trxn_19_20 "
					+ "WHERE vch_gatepass_no= '"
					+ act.getScanGatePassNo()
					+ "' ";

			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				id = (rs.getInt("dispatch_box"));

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
		return id;

	}
	public int fl11Cases(GatePassToDistrictWholesaleAction act) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection19_20();
			 
			if (act.getGetvch().equalsIgnoreCase("FL1")) {
				s = "select coalesce(count(*),0) as dispatchd_box from  bottling_unmapped.disliry_unmap_fl3 where   fl11gatepass='"+act.getFl11gtps()+"'  and fl11_date is not null  ";
			} else if (act.getGetvch().equalsIgnoreCase("FL1A")) {
				s = "select coalesce(count(*),0) as dispatchd_box from  bottling_unmapped.disliry_unmap_fl3a where   fl11gatepass='"+act.getFl11gtps()+"'  and fl11_date is not null  ";
			}

			pstmt = con.prepareStatement(s);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				id = (rs.getInt("dispatchd_box"));
				if(id==0){

					FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									"FLB11 Barcodes Not Found ! ",
									"FLB11 Barcodes Not Found !"));
				
				}
			}else{
				FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								"FLB11 Barcodes Not Found ! ",
								"FLB11 Barcodes Not Found !"));
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
		return id;

	}
	public Date maxdt(int act) {
		Date id = null;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;

		try {
			con = ConnectionToDataBase.getConnection();

			String query = "SELECT coalesce(max(dt_date),'2018-01-01') FROM distillery.gatepass_to_manufacturewholesale_19_20 "
					+ "WHERE seq=" + act + "";

			pstmt = con.prepareStatement(query);
			 
			rs = pstmt.executeQuery();

			while (rs.next()) {

				id = (rs.getDate(1));

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
		return id;

	}

	// ----------------------------code for cancelgatepass------------------------------

	// =====================get max id sequence==============================

	public int seqCancel() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(seq) as id FROM distillery.gatepass_to_manufacturewholesale_19_20 ";
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

	// -------------------------cancel
	// gatepass----------------------------------

	public String cancelGatepassImpl(GatePassToDistrictWholesaleAction act) {

		Connection con = null;
		PreparedStatement ps = null, ps2 = null, ps3 = null, ps4 = null, ps5 = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		ResultSet rs1 = null;
		String sql = "";
		String sql2 = "";
		String sql3 = "";
		int tok1 = 0;
		int tok2 = 0;

		 

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);

			String gatepass = "";
			gatepass = act.getPrintGatePassNo().trim();

			String selQr = " SELECT a.int_dist_id, a.dt_date, a.vch_gatepass_no,b.dispatch_box, b.seq_fl1, "
					+ " b.int_brand_id, b.int_pckg_id, b.dispatch_bottle ,a.vch_from_lic_no ,b.size "
					+ " FROM distillery.gatepass_to_districtwholesale_19_20 a, distillery.fl2_stock_trxn_19_20 b "
					+ " where a.int_dist_id=b.int_dissleri_id AND a.vch_gatepass_no=b.vch_gatepass_no and a.vch_finalize is null "
					+ " AND a.dt_date=b.dt AND a.int_dist_id='"
					+ act.getDist_id()
					+ "' AND "
					+ " a.vch_gatepass_no='"
					+ gatepass.trim() + "' ";

			pstmt = con.prepareStatement(selQr);
 
			rs = pstmt.executeQuery();

			while (rs.next()) {
				  
				GatePassToDistrictWholesaleDatatable dt = new GatePassToDistrictWholesaleDatatable();

				dt.setSeqDt(rs.getInt("seq_fl1"));
				dt.setBrandIdDt(rs.getInt("int_brand_id"));
				dt.setPckgIdDt(rs.getInt("int_pckg_id"));
				dt.setDispatcBotlsDt(rs.getInt("dispatch_bottle"));
				int box=rs.getInt("dispatch_box");
				int size=rs.getInt("size");
				String vch_from_lic_no=rs.getString("vch_from_lic_no");
				 
					tok1 = 0;

					String  updtQr = " UPDATE distillery.fl2_stock_19_20 SET dispatch_box=COALESCE(dispatch_box,0)-"+box+",  dispatch_bottles=COALESCE(dispatch_bottles,0)-"+ dt.getDispatcBotlsDt()+ " " +
							" WHERE int_dist_id="+ act.getDist_id()+ " AND  brand="+ dt.getBrandIdDt()+" AND package="+ dt.getPckgIdDt()+" " +
									" and licence_no='"+vch_from_lic_no.trim()+"' and size="+size+""; 
					ps3 = con.prepareStatement(updtQr);

					 

					tok1 = ps3.executeUpdate();
					   
					 
					if (tok1 > 0) {
						 
						tok1 = 0;
						   updtQr = " UPDATE  fl2d.indent_for_wholesale_trxn a SET finalize_indent=COALESCE(finalize_indent,0)-b.boxes  from distillery.wholesale_indent_gatepass b " +
								" WHERE b.vch_gatepass ='"+gatepass.trim()+"' and  a. indent_no=b.indent_no and a.int_brand_id=b.brand_id and  a.int_pckg_id=b.package_id  and b.package_id="+dt.getPckgIdDt()+" and b.brand_id="+ dt.getBrandIdDt()+" and b.boxes>0 ";
						   ps3 = con.prepareStatement(updtQr);
						tok1 = ps3.executeUpdate();
						 
					 

					}	
					if (tok1 > 0) {
						 
						tok1 = 0;
						updtQr = " delete from distillery.wholesale_indent_gatepass "+
						        " where    int_distillery_id="+act.getDist_id()+" and  "+
						        "  vch_gatepass ='"+gatepass.trim()+"' and    "+
								" package_id="+dt.getPckgIdDt()+" and "+
								" brand_id="+ dt.getBrandIdDt()+" " ;
						 ps3 = con.prepareStatement(updtQr);
						tok1 = ps3.executeUpdate();
						 
					}	
					
				}

			 
			 
			if (tok1 > 0) {
				tok1 = 0;

				sql2 = " DELETE FROM distillery.gatepass_to_districtwholesale_19_20 "
						+ " WHERE vch_gatepass_no='" +gatepass
						+ "' AND int_dist_id='" + act.getDist_id() + "' AND vch_finalize IS NULL ";

				ps2 = con.prepareStatement(sql2);

				tok1 = ps2.executeUpdate();
			 
			}

			if (tok1 > 0) {
				tok1 = 0;

				sql3 = " DELETE FROM distillery.fl2_stock_trxn_19_20 "
						+ " WHERE vch_gatepass_no='" +gatepass
						+ "' AND int_dissleri_id='" + act.getDist_id() + "' ";

				ps3 = con.prepareStatement(sql3);
				tok1 = ps3.executeUpdate();

				 
			}
			
			if (tok1 > 0) {
				con.setAutoCommit(true);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Gatepass Cancelled Successfully !!! ",
								"Gatepass Cancelled Successfully !!!"));
				act.clearAll();
			}

			else {
				con.rollback();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Gatepass Not Cancelled !!! ",
								"Gatepass Not Cancelled !!!"));

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
	
	public void getLicenseDetail(GatePassToDistrictWholesaleAction action)
	{
		
		Connection con=null;
		PreparedStatement stmt=null;
		int status=0;
		ResultSet rs=null;
		ArrayList list=new ArrayList();
		String query1=" select vch_core_address,vch_firm_name from licence.fl2_2b_2d_19_20 " +
				"  where vch_licence_no='" + action.getVch_to_lic_no().trim() + "' and vch_license_type='FL2' ";
	
		
		String query2=" select vch_core_address,vch_firm_name from licence.fl2_2b_2d_19_20 " +
				"  where vch_licence_no='" + action.getVch_to_lic_no().trim() + "' and vch_license_type='FL2B' ";
		try
		{
			
		 
				con=ConnectionToDataBase.getConnection();
				if(action.getVch_to().equalsIgnoreCase("FL2"))
				{
					
					stmt=con.prepareStatement(query1);
					rs=stmt.executeQuery();
				}
				else if(action.getVch_to().equalsIgnoreCase("FL2B"))
				{
					
					stmt=con.prepareStatement(query2);
					rs=stmt.executeQuery();
				}
					
				while(rs.next())
				{
					status=1;
					action.setLicenceenm(rs.getString("vch_firm_name"));
					action.setLicenceeadd(rs.getString("vch_core_address"));
					action.setLic_disable_flag(true);
				
				}
				if(status==0)
	        	{
					action.setLic_disable_flag(false);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"License code does not exist!!",
									"License code does not exist!!"));
					
	        	}
				
		}
		catch (Exception ex) 
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
	}
	

	// ===========================code for csv============================

	public void saveCSV(GatePassToDistrictWholesaleAction action) throws IOException {
		Connection con = null;
		PreparedStatement stmt = null;
		
		
		String query = " INSERT INTO distillery.distillery_gatepass_casecode(gatespass, casecode)VALUES (?, ?) ";

		String gatepass = "";
		int status = 0, flag = 0;
		BufferedReader bReader = new BufferedReader(new FileReader(action.getCsvFilepath()));
		
		try {
			con = ConnectionToDataBase.getConnection();
			stmt = con.prepareStatement(query);
			

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
					//	System.out.println("---"+lineNumber);
						if (lineNumber == 1) {							
							gatepass = sd.trim();
						}
						else// line number >1
						{
							
							 
							//dt.setTemp_Date(rs.getDate("date"));
							
							
							if (gatepass.equalsIgnoreCase(action.getScanGatePassNo().trim())) {	
								
								casecode = sd;
								
							 	if(this.etin(casecode.trim().substring(0, 12), action)==true){
							 		
									FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
											" Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!" ," Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!"));	
								
							 	}else{ 
							 		
							 		
								stmt.setString(1, gatepass.trim());
								stmt.setString(2, casecode.trim());
								stmt.addBatch();
								
							 	
							 	
							 	}
								//status = stmt.executeUpdate();
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
	
	
	
	
	//------------------------------------------------------------
	public ArrayList indentDetail(GatePassToDistrictWholesaleAction act,String val ) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try { 
			String query = " SELECT   b.user_id,d.brand_name, c.package_name, b.indent_no, b.cr_date, b.int_brand_id,  " +
						   " b.int_pckg_id,  (b.no_of_box-coalesce(b.finalize_indent,0)) as no_of_box  "+
						   " from  fl2d.indent_for_wholesale a,fl2d.indent_for_wholesale_trxn b,"+
                           " distillery.packaging_details_19_20  c,distillery.brand_registration_19_20 d "+
                           " where a.indent_no=b.indent_no and "+
						   " c.package_id=b.int_pckg_id and  a.vch_action_taken='A'  and a.vch_licence_no='"+val+"' and "+// and a.vch_licence_no='"+val+"'
						   " d.brand_id=b.int_brand_id and a.unit_id='"+act.getDist_id()+"'  and  b.no_of_box-coalesce(b.finalize_indent,0) >0  order by b.cr_date ";

			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();
		 
		
			int i=1;
			while (rs.next()) {
				GatePassToDistrictWholesaleDatatable dt = new GatePassToDistrictWholesaleDatatable();
				
				dt.setSnoIndent(i);
				dt.setIndentNo(rs.getString("indent_no"));
				dt.setIndentDate( (rs.getDate("cr_date")));
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
	
	
	
	
	public ArrayList displaylistImpl2(GatePassToDistrictWholesaleAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		String brand = "";
		String packag = "";
		String indentno = "";
		int i = 1;

		try {
			
			 
		int indentedBox=0;	
boolean flag=false;
		
			 
			conn = ConnectionToDataBase.getConnection();
			
			
			int j=0;

			for (int ii = 0; ii < act.getDisplaylist1().size(); ii++) 
			{

				GatePassToDistrictWholesaleDatatable dt = 
						(GatePassToDistrictWholesaleDatatable) act.getDisplaylist1().get(ii);
				
				if(dt.isSelected())
				{
					packag=packag+dt.getPackageIdIndent()+",";
					brand=brand+dt.getBrandIdIndent()+",";
					if(j==0){
					indentno = " select sum(x.no_of_box) from ( SELECT  (m.no_of_box-coalesce(m.finalize_indent,0))no_of_box from  fl2d.indent_for_wholesale_trxn m where "+
 					" m.indent_no in ('"+dt.getIndentNo()+"')  and m.int_pckg_id="+dt.getPackageIdIndent()+"  and m.int_pckg_id=c.package  and m.int_brand_id=c.brand ";
					j++;
					}else{
						indentno=indentno+"  union all SELECT  (m.no_of_box-coalesce(m.finalize_indent,0))no_of_box from  fl2d.indent_for_wholesale_trxn m where "+
 					" m.indent_no in ('"+dt.getIndentNo()+"') and m.int_pckg_id="+dt.getPackageIdIndent()+" and m.int_pckg_id=c.package  and m.int_brand_id=c.brand ";
					}
					 
				}
			}

		
		/*if (act.getVch_to().equalsIgnoreCase("FL2B")) 
		{
			
			
			selQr = " SELECT distinct  a.package_name, a.package_id,  c.brand, b.brand_name,"
					+ " c.package, c.stock_bottles-coalesce(c.dispatch_bottles,0)  as avlbottle,c.size as box_size," +
					"(c.stock_box-c.dispatch_box) as dispatchd_box,("+indentno+" )x) as  indentno  "
					
					+ " FROM distillery.packaging_details_19_20 a, distillery.brand_registration_19_20 b, "
					+ " distillery.fl2_stock_19_20 c "
					+ " WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.brand "
					+ " AND a.package_id=c.package AND b.brand_id=c.brand "
					+ " AND c.int_dist_id='"
					+ act.getDist_id()
					+ "'   and  c.licence_no= '"+act.getVch_from_lic_no().trim()+"'  "
					+ "   AND c.stock_bottles-COALESCE(c.dispatch_bottles,0)>0 "
					+ "   AND b.license_category='LAB' "
					+ " and     b.brand_id  in ("+brand +"0)  and a.package_id in ("+packag +"0) "+
					"group by   a.package_name, "+
						" b.brand_name, a.package_id ,c.brand,c.package, c.stock_bottles,c.dispatch_bottles, "+
						" c.stock_box,c.dispatch_box, c.size   ,c.int_dist_id  ";
			
			 
			ps = conn.prepareStatement(selQr);
			
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				GatePassToDistrictWholesaleDatatable dt1 = new GatePassToDistrictWholesaleDatatable();
				dt1.setInt_brand_id(rs.getInt("brand"));
				dt1.setInt_pckg_id(rs.getInt("package"));
				dt1.setVch_brand(rs.getString("brand_name"));
				dt1.setInt_bottle_avaliable(rs.getInt("avlbottle"));
				dt1.setDesc(rs.getString("package_name"));
				dt1.setSeq(i);
				dt1.setSize(rs.getInt("box_size"));
				dt1.setBoxAvailable(rs.getInt("dispatchd_box"));
				dt1.setSlno(i);
				
				dt1.setIndentbox(rs.getInt("indentedbox"));
			 
				i++;
				
				list.add(dt1);
				}

			}
			 

	
		
		else if (act.getVch_to().equalsIgnoreCase("FL2")) 
		{
		
		*/
		
		
			String selQr1 = " SELECT distinct  a.package_name, a.package_id,  c.brand, b.brand_name, "
					+ " c.package, c.stock_bottles-coalesce(c.dispatch_bottles,0)  as avlbottle,(c.stock_box-c.dispatch_box) as dispatchd_box," +
					" c.size as box_size,("+indentno+" )x) as  indentno  "
					+ " FROM distillery.packaging_details_19_20 a, distillery.brand_registration_19_20 b, "
					+ " distillery.fl2_stock_19_20 c  "
					+ " WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.brand "
					+ " AND a.package_id=c.package AND b.brand_id=c.brand "
					+ " AND c.int_dist_id='"
					+ act.getDist_id()
					+ "'  and  c.licence_no= '"+act.getVch_from_lic_no().trim()+"' AND c.stock_bottles-COALESCE(c.dispatch_bottles,0)>0  "
					+ "   " //AND b.license_category!='LAB'
					+ " and      b.brand_id  in ("+brand +"0)  and a.package_id in ("+packag +"0) " +
							"group by   a.package_name, "+
						" b.brand_name, a.package_id ,c.brand,c.package, c.stock_bottles,c.dispatch_bottles, "+
						" c.stock_box,c.dispatch_box, c.size ,c.int_dist_id  ";


			 
			ps = conn.prepareStatement(selQr1);
			
			
	
			
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				GatePassToDistrictWholesaleDatatable dt2 = new GatePassToDistrictWholesaleDatatable();
				dt2.setInt_brand_id(rs.getInt("brand"));
				dt2.setInt_pckg_id(rs.getInt("package"));
				dt2.setVch_brand(rs.getString("brand_name"));
				dt2.setInt_bottle_avaliable(rs.getInt("avlbottle"));
				dt2.setDesc(rs.getString("package_name"));
				dt2.setSeq(i);
				dt2.setSize(rs.getInt("box_size"));
				dt2.setBoxAvailable(rs.getInt("dispatchd_box"));
				dt2.setIndentbox(rs.getInt("indentno"));
				dt2.setSlno(i);
				list.add(dt2);
			
				 
				i++;
				
			
			}
	//	}
		
		
		

		

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
