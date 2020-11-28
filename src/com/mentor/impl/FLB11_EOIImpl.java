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

import com.mentor.Datatable.FLB11_EOIDt; 
import com.mentor.action.FLB11_EOIAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class FLB11_EOIImpl {

	public String getDetails(FLB11_EOIAction act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();
			String queryList = " SELECT int_app_id_f, vch_undertaking_name, vch_wrk_add  "
					+ " FROM  dis_mst_pd1_pd2_lic WHERE vch_wrk_phon='"
					+ ResourceUtil.getUserNameAllReq().trim() + "'  ";

			 
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				act.setDissteleryId(rs.getInt("int_app_id_f"));
				act.setDissteleryName(rs.getString("vch_undertaking_name"));
				act.setDisslryAdd(rs.getString("vch_wrk_add"));

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

	public ArrayList getSeasonDetails(FLB11_EOIAction ac) {
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

	// -----------list
	public ArrayList fromliclistImpl(FLB11_EOIAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		try {// @rvind
			
			String query = "SELECT distinct licence_no   from  "
					+ " distillery.mst_bottling_plan_20_21 where   int_distillery_id='"
					+ act.getDissteleryId() + "'  and vch_license_type= '"
					+ act.getVch_from() + "'";
			
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);
			// ps.setDate(1,
			// Utility.convertUtilDateToSQLDate(act.getDt_date()));
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
 //------------------------------
	
	public ArrayList toliclistImpl1a(FLB11_EOIAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);
		try {
			String query = /*"select app_id,concat(app_id,'/',order_id) as id from distillery.eoi_app_for_export_order " +
					" where approve_flag is not null and esign_date is not null and int_dist_id='"+act.getDissteleryId()+"'";
			*/
			
			 " select app_id,concat(app_id,'/Order-',order_id) as id  from distillery.eoi_app_for_export_order  where approve_flag is not null and esign_date is not null  " +
			"		   and int_dist_id='"+act.getDissteleryId()+"'"  ;

			  
			  
			   
			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				// item.setValue(rs.getInt("int_state_id"));
				item.setValue(rs.getString("app_id"));
				item.setLabel(rs.getString("id"));

				list.add(item);

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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

	
	//--------------------
	

	public ArrayList displaylistImpl(FLB11_EOIAction act,String vat) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		// int i1=0,i2=0;
		int i = 1;
		// String arr[] = vat.split("/Order-");
         String app_id  = vat;
       //  String order_id  = arr[1];


		try {

		

			 		
					
			selQr = " SELECT d.esign_date,d.app_id,liquor_type,quantity as qnt_ml_detail,a.cesh,a.code_generate_through,b.domain,a.package_name, a.package_id, a.duty, a.adduty, c.int_brand_id, b.brand_name,coalesce(c.tnt,'X') as tt , c.tnt ,"
					+ " c.int_pckg_id, c.int_stock-c.int_dispatched as stockbottle,(e.reqstd_bottles-coalesce(e.dispatched_bottles,0))  as avlbottle, c.size as box_size,"
					+ " ROUND(((e.reqstd_bottles-e.dispatched_bottles )/c.size)+.5) as avlbox,a.code_generate_through "
					+ " FROM distillery.packaging_details_20_21 a  , distillery.brand_registration_20_21 b, "
					+ " distillery.boxing_stock_20_21 c  ,distillery.eoi_app_for_export_order d , distillery.eoi_app_for_export_order_brand e    "
					+ " WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.int_brand_id "
					+ " AND a.package_id=c.int_pckg_id AND b.brand_id=c.int_brand_id and d.app_id= e.app_id_fk  and a.code_generate_through=e.etin  and  d.app_id = "+Integer.parseInt(app_id)+"  "
					+ " AND c.int_dissleri_id='"+ act.getDissteleryId()+"' " +
					"   AND c.int_stock-c.int_dispatched >0    and (e.reqstd_bottles-coalesce(e.dispatched_bottles,0)) >0 "
					+ " order by b.brand_name,a.package_name,c.tnt";	 
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);
			
			 

			rs = ps.executeQuery();
			while (rs.next()) {
				FLB11_EOIDt dt = new FLB11_EOIDt();
				dt.setSlno(i);
				dt.setInt_brand_id(rs.getInt("int_brand_id"));
				dt.setInt_pckg_id(rs.getInt("int_pckg_id"));
				dt.setVch_brand(rs.getString("brand_name"));
				dt.setInt_bottle_avaliable(rs.getInt("avlbottle"));
				dt.setDesc(rs.getString("package_name"));
				dt.setDb_duty(rs.getDouble("duty"));
				dt.setDb_add_duty(rs.getDouble("adduty"));
				dt.setSize(rs.getInt("box_size"));
				 dt.setStkbtl(rs.getInt("stockbottle"));
				dt.setDomain(rs.getString("domain"));
				dt.setCode_generate_through(rs.getString("code_generate_through"));
				dt.setQty(rs.getInt("qnt_ml_detail"));
				dt.setLic_type(rs.getString("liquor_type"));
				dt.setCesh(rs.getDouble("cesh"));
				dt.setApp_id(rs.getInt("app_id"));
				
			//	dt.setInt_dispatch(rs.getInt("avlbottle"));
			//	dt.setDispatchBoxes(rs.getInt("avlbox"));
				list.add(dt);
				i++;

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
	
	//////----------------------------------------------------display----------------------------------------
	
	public String displayImpl(FLB11_EOIAction act,String vat) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//String arr[] = vat.split("/Order-");
	       //  String app_id  = arr[0];
	       //  String order_id  = arr[1];

			String query = 
					/*" SELECT a.seq_pk, a.int_dist_id, a.country_id, a.importing_unit_nm, a.po_date, a.po_number, a.po_pdf, a.created_date, "+
					"	 c.vch_country_name 	  "+
					"	 FROM distillery.eoi_import_order_master a ,public.country_mst c where seq_pk=(select order_id from distillery.eoi_app_for_export_order where app_id="+Integer.parseInt(vat)+")  and   a.country_id=c.int_country_id   ";                                                                         
				 */
			" SELECT a.seq_pk,d.esign_date, a.int_dist_id, a.country_id, a.importing_unit_nm, a.po_date, a.po_number, a.po_pdf, a.created_date,  "+
			" c.vch_country_name 	    "+
			" FROM distillery.eoi_import_order_master a ,public.country_mst c ,distillery.eoi_app_for_export_order d  "+
			" where a.seq_pk=d.order_id  and app_id="+Integer.parseInt(vat)+"  and   a.country_id=c.int_country_id "  ;    
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				act.setDt_date(Utility.convertUtilDateToSQLDate(new Date()));
				act.setPermit_date(rs.getDate("esign_date"));
				act.setUnit_nm(rs.getString("importing_unit_nm"));
				act.setCountry(rs.getString("vch_country_name"));
				act.setPurchaseOrderNo(rs.getInt("po_number"));
				act.setUnitNo(rs.getInt("seq_pk"));
				act.setCountryId(rs.getInt("country_id"));
			}
			else{
				act.setUnit_nm("");
			    
				act.setCountry("");
				act.setPurchaseOrderNo(0);
				act.setUnitNo(0);
				act.setCountryId(0);
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

	
	public String saveMethodImpl(FLB11_EOIAction act) {
		Connection con = null;
		PreparedStatement ps = null, ps2 = null, ps2D = null, ps3 = null, ps4 = null, ps5 = null;
		ResultSet rs = null;
		String sql = "";
		String sql2 = "";
		String sql3 = "";
		int tok1 = 0, tok2 = 0, tok3 = 0, tok5 = 0;
		double duty = 0;
		double addduty = 0;
		String sql5 = "";
		int dispatch = 0;
		int seq = this.maxId(this);
		String gatepass = String.valueOf(act.getDissteleryId()) + "-2020-21-"
				+ act.getVch_from() + "-" + seq;

		java.sql.Timestamp date1 = new java.sql.Timestamp(
				new java.util.Date().getTime());

		try {

			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false); 
			sql = " INSERT INTO distillery.gatepass_to_manufacturewholesale_20_21(int_dist_id, vch_distillary_name, "
					+ " vch_distillary_address, vch_gatepass_no, dt_date, vch_from, vch_to, vch_from_lic_no, "
					+ " vch_to_lic_no, vch_bond, curr_date, int_max_id, db_total_duty, db_total_additional_duty, "
					+ " vch_root_details, vch_vehicle_no, vch_auth_name,"
					+ " export_lic_no, mode_of_transport, bond_value,"
					+ " export_district, vehicle_driver_name, vehicle_agency_and_address,valid_till,gross_weight, "
					+ " tier_weight, net_weight, permit_dt, vch_permit_no,exp_fee,purchaseOrderNo,importOrderNo,container,  "
							+ "sealing_date ,  seal_no ,   container_no ,icd) VALUES "
					+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?, ?, ?,?,'"+act.getPurchaseOrderNo()+"',"
							+ "'"+act.getUnitNo()+"','"+act.getRadio1()+"',?, '"+act.getSealno()+"','"+act.getContainerno()+"','"+act.getIcd()+"')";
			
			
			
			 ps = con.prepareStatement(sql);
			// ps.setDate(1, Utility.convertUtilDateToSQLDate(date));
			ps.setInt(1, act.getDissteleryId());
			ps.setString(2, act.getDissteleryName());
			ps.setString(3, act.getDisslryAdd());

			ps.setString(4, gatepass);
			ps.setDate(5, Utility.convertUtilDateToSQLDate(act.getDt_date()));
			ps.setString(6, act.getVch_from());
			ps.setString(7, act.getVch_to());
			ps.setString(8, act.getVch_from_lic_no().trim());
			 ps.setString(18, act.getVch_to_lic_no()+"/(9)Alcohol / Export Outside India");
				ps.setString(19, "");
				ps.setDouble(20, act.getBondValue());
				ps.setString(21, String.valueOf(act.getCountryId()));
				ps.setDouble(30,0);
				ps.setString(17, act.getUnit_nm());
				ps.setString(9, act.getVch_to_lic_no());
				 
				ps.setDouble(13, 0);
			 
		 ps.setString(10, "UnderBond");
			  
			ps.setDate(11, Utility.convertUtilDateToSQLDate(date1));
			ps.setInt(12, seq);
			
			ps.setDouble(14, act.getDb_total_add_value());
			ps.setString(15, act.getRouteDtl());
			ps.setString(16, act.getVehicleNo());

			ps.setString(22, act.getVehicleDrvrName());
			ps.setString(23, act.getVehicleAgencyNmAdrs());

			ps.setDate(24,
					Utility.convertUtilDateToSQLDate(act.getValidtilldt_date()));
			ps.setDouble(25, act.getGrossWeight());
			ps.setDouble(26, act.getTierWeight());
			ps.setDouble(27, act.getNetWeight());
			ps.setDate(28, Utility.convertUtilDateToSQLDate(act.getPermit_date()));
			ps.setString(29, "");
			if(act.getSealdate()!=null) {
			ps.setDate(31, Utility.convertUtilDateToSQLDate(act.getSealdate()));
			}else {
				ps.setDate(31,null);
			}
			tok1 = ps.executeUpdate();
			
		 for (int i = 0; i < act.displaylist.size(); i++) {
				FLB11_EOIDt dt = (FLB11_EOIDt) act.getDisplaylist().get(i);
				
				if (dt.getInt_dispatch() > 0 && dt.getDispatchBoxes() > 0) {
					sql2 = this.fetchDataImpl(
							dt.getInt_pckg_id(),// //////////////////////
							act.getDissteleryId(), dt.getInt_brand_id(),
							act.getVch_to(), dt.getInt_dispatch(),
							dt.getCalculated_duty(),
							dt.getCalculated_add_duty(),
							dt.getInt_bottle_avaliable(), dt.getBoxAvailable(),
							dt.getDispatchBoxes(), act.getVch_from_lic_no());
					 ps2 = con.prepareStatement(sql2);

					if (this.insertorupdateflg == false) {
						ps2.setInt(1, act.getDissteleryId());
						ps2.setInt(2, dt.getInt_brand_id());
						ps2.setString(3, act.getVch_to());
						ps2.setInt(4, dt.getInt_pckg_id());// ////////////////////////////////////////////
						ps2.setInt(5, dt.getInt_bottle_avaliable());

						ps2.setDouble(6, dt.getCalculated_duty());
						ps2.setDouble(7, dt.getCalculated_add_duty());
						ps2.setDate(8, Utility.convertUtilDateToSQLDate(act
								.getDt_date()));
						// ps2.setInt(10, dt.getSize());
						ps2.setInt(9, dt.getBoxAvailable());

						duty = duty
								+ (((dt.getDb_duty() * dt.getInt_dispatch())) * 100.0)
								/ 100.0;
						addduty = addduty
								+ (((dt.getDb_add_duty() * dt.getInt_dispatch())) * 100.0)
								/ 100.0;
						tok2 = ps2.executeUpdate();
						
						  
					}

					else if (this.insertorupdateflg == true) {
						//System.out.println("iii");
						tok2 = ps2.executeUpdate();
						 

					}

					if (tok2 > 0) {

						tok2 = 0;

						String query1 = " INSERT INTO distillery.fl1_stock_trxn_20_21(int_dissleri_id, vch_lic_type, int_brand_id, int_pckg_id, vch_lic_no,"
								+ "avl_bottl,avl_box,dispatchd_bottl,dispatchd_box,size,duty,addduty,vch_gatepass_no,seq,batch_no,tnt,code_generate_through)	"
								+ "VALUES (?, ?, ?,?, ?, ?,?, ?, ?,?, ?, ?,?,(select max(coalesce(seq,0))+1 from distillery.fl1_stock_trxn_20_21),?,?,?)";
						
						 ps5 = con.prepareStatement(query1);

						ps5.setInt(1, act.getDissteleryId());

						ps5.setString(2, act.getVch_to());
						ps5.setInt(3, (dt.getInt_brand_id()));
						ps5.setInt(4, (dt.getInt_pckg_id()));// /////////////////////////////////////
						 ps5.setString(5, "");
						 
						ps5.setInt(6, dt.getInt_bottle_avaliable());
						ps5.setInt(7, dt.getBoxAvailable());
						ps5.setInt(8, dt.getInt_dispatch());
						ps5.setInt(9, dt.getDispatchBoxes());
						ps5.setInt(10, dt.getSize());
						dispatch += dt.getInt_dispatch();

						ps5.setDouble(11, dt.getCalculated_duty());
						ps5.setDouble(12, dt.getCalculated_add_duty());
						ps5.setString(13, gatepass);
						ps5.setString(14, dt.getBatchNo());
						ps5.setString(15, dt.getTntflg());
						ps5.setString(16, dt.getCode_generate_through());
						tok2 = ps5.executeUpdate();
						 

					}
				
		     if(tok2 > 0){
						
						tok2=0;
												
						sql5 = " UPDATE distillery.boxing_stock_20_21 SET int_dispatched=COALESCE(int_dispatched,0)+"
								+ dt.getInt_dispatch()
								+ ", "
								+ " int_dispatched_cases=COALESCE(int_dispatched_cases,0) + "
								+ dt.getDispatchBoxes()
								+ " "
								+ " WHERE int_dissleri_id="
								+ act.getDissteleryId()
								+ " "
								+ "AND int_pckg_id="
								+ dt.getInt_pckg_id()
								+ " AND vch_lic_no='"
								+ act.getVch_from_lic_no()
								+ "' and    vch_lic_type='"
								+ act.getVch_from()
								+ "' AND int_brand_id="
								+ dt.getInt_brand_id()
								+ " and tnt='" + dt.getTntflg() + "' and size ="+dt.getSize()+"";
						 ps5 = con.prepareStatement(sql5);

					 
						tok2 = ps5.executeUpdate();
						
						 
						
					}
				if(tok2 > 0){
					
					tok2=0;
											
					sql5 = " update distillery.eoi_app_for_export_order_brand set   "+
						   "	dispatched_bottles=coalesce(dispatched_bottles,0)+'"+dt.getInt_dispatch()+"'   "+
						   "	where app_id_fk='"+dt.getApp_id()+"'    "+
						   "	and etin='"+dt.getCode_generate_through()+"'    "+
						   "	and distillery_id='"+act.getDissteleryId()+"'    ";
							
							
							
					 
				 
					ps5 = con.prepareStatement(sql5);

				 
					tok2 = ps5.executeUpdate();
					
					 
					
				}


				}
			}
		 
		 sql3 = "INSERT INTO distillery.bond_register_20_21( "
						+ "int_id, int_distillery_id, date_crr_date, vch_duty_type, int_quantity, bond_value, vch_description,gatepass) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
				   
				ps3 = con.prepareStatement(sql3);

				ps3.setInt(1, getMaxIdBond() + 1);
				ps3.setInt(2, act.getDissteleryId());
				ps3.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				ps3.setString(4, "EXP_BOND");
				ps3.setDouble(5, dispatch);
				ps3.setDouble(6, act.getBondValue());
				ps3.setString(7, "Export Duty for FL11B for Gatepass "+ gatepass);
				ps3.setString(8,  gatepass);
				tok3 = ps3.executeUpdate();
				
				if(tok3>0)
				{
					tok3=0;
					
					sql3 = "INSERT INTO distillery.duty_register_19_20( "
							+ "int_id, int_distillery_id, date_crr_date, vch_duty_type, int_quantity, int_value, vch_description,  gatepass) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
					
					
					 ps3 = con.prepareStatement(sql3);

					ps3.setInt(1, getMaxIdDuty() + 1);
					ps3.setInt(2, act.getDissteleryId());
					ps3.setDate(3, new java.sql.Date(System.currentTimeMillis()));
					ps3.setString(4, "EXP_FEE_FL");
					ps3.setDouble(5, dispatch);
					ps3.setDouble(6, act.getDb_total_value_exp());
					ps3.setString(7, "Export Fee for FL11");
					ps3.setString(8, gatepass);
					
					tok3 = ps3.executeUpdate();
				} 
				  
 
			
			if (tok1 > 0 && tok2 > 0 && tok3 > 0) {
				con.commit();
				act.clearAll();
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,
						Constants.SAVED_SUCESSFULLY);
				act.setSaveflg(false);
				
			}

			else {
				con.rollback();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
				e.printStackTrace();
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
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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

	// =====================get max id sequence==============================

		public int maxId(FLB11_EOIImpl  impl) {

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = " SELECT max(int_max_id) as id FROM distillery.gatepass_to_manufacturewholesale_20_21 ";
			int maxid = 0;
			try {
				con = ConnectionToDataBase.getConnection();
				pstmt = con.prepareStatement(query);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					maxid = rs.getInt("id");
				}
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
//-----------------------------------------
		
		public int getMaxIdBond() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int maxid = 0;

			try {
				String query = "SELECT max(int_id) from distillery.bond_register_20_21 ";
				conn = ConnectionToDataBase.getConnection();
				pstmt = conn.prepareStatement(query);

				rs = pstmt.executeQuery();
				if (rs.next()) {
					maxid = rs.getInt(1);
				}

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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

		
		//-----------------------------------------------------
		
		public boolean insertorupdateflg = false;
		boolean printFlag = false;

		public boolean isInsertorupdateflg() {
			return insertorupdateflg;
		}

		public void setInsertorupdateflg(boolean insertorupdateflg) {
			this.insertorupdateflg = insertorupdateflg;
		}

		public String fetchDataImpl(int i1, int i2, int i3, String s, int dis,
				double duty, double addduty, int bottle_avl, int box_avl,
				int dispatchbox, String licno) {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "";
			String query = "";

			try {
				con = ConnectionToDataBase.getConnection();
				sql = "SELECT int_package_ml FROM distillery.fl1_stock_20_21 WHERE  int_package_ml="
						+ i1
						+ " AND  int_dist_id="
						+ i2
						+ "   AND  int_brand_id="
						+ i3 + "    and lic_no='" + licno + "'  ";
				
				System.out.println("===fetchDataImpl==="+sql);

				ps = con.prepareStatement(sql);

				rs = ps.executeQuery();
				if (rs.next()) {

					query = "UPDATE distillery.fl1_stock_20_21 SET int_dispatch=int_dispatch+"
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
							+ i3 + "     and lic_no='" + licno + "'";
					
					
				 
					this.insertorupdateflg = true;
					
					 

				} else {
					query = " INSERT INTO distillery.fl1_stock_20_21(int_seq, int_dist_id, int_brand_id, vch_to_fl1_fl1a, "
							+ " int_package_ml, int_bottle_avaliable, db_duty, db_add_duty, dt_date, "
							+ "  no_boxes_avaliable,lic_no)VALUES "
							+ " ((select nextval('seq')),?, ?, ?, ?, ?, ?, ?, ?, ?,   '"
							+ licno + "')";
					this.insertorupdateflg = false;
					
					 
				}

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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

		
		
		// ---------------------------------------------------------------

		public ArrayList getGatePassWholeSaleListManufacture(
				FLB11_EOIAction action, Date ev) {
			ArrayList list = null;
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			String query = " SELECT distinct int_dist_id, vch_distillary_name, vch_distillary_address, a.vch_gatepass_no, c.code_generate_through,a.export_lic_no,"
					+ " dt_date, vch_from, vch_to, vch_from_lic_no, vch_to_lic_no, vch_bond, curr_date, "
					+ " int_max_id, db_total_duty, db_total_additional_duty,b.dispatchd_box,b.dispatchd_bottl,a.int_max_id,coalesce(b.tnt,'X') as tnt,"
					+ " COALESCE(vch_finalize,'N') as vch_finalize  FROM "
					+ " distillery.gatepass_to_manufacturewholesale_20_21 a ,distillery.fl1_stock_trxn_20_21 b, distillery.packaging_details_20_21 c "
					+ "   where vch_to in ('EOI')  and  a.int_dist_id="
					+ action.getDissteleryId()
					+ " and a.int_dist_id=b.int_dissleri_id and dt_date='"+Utility.convertUtilDateToSQLDate(ev)+"' and "
					+ "   b.vch_gatepass_no=a.vch_gatepass_no and  c.package_id=b.int_pckg_id  and c.brand_id_fk=b.int_brand_id "
					+ " order by a.int_max_id desc";
			
			
			try {
				list = new ArrayList();
				con = ConnectionToDataBase.getConnection();
				ps = con.prepareStatement(query);

			 			rs = ps.executeQuery();
				int i = 1;
				while (rs.next()) {
					FLB11_EOIDt dt = new FLB11_EOIDt();
					dt.setSno(i);
					dt.setInt_dist_id(rs.getInt("int_dist_id"));
					dt.setVch_distillary_name(rs.getString("vch_distillary_name"));
					dt.setVch_distillary_address(rs
							.getString("vch_distillary_address"));
					dt.setVch_gatepass_no(rs.getString("vch_gatepass_no"));
					dt.setDt_date(rs.getDate("dt_date"));
					dt.setVch_from(rs.getString("vch_from"));
					dt.setVch_to(rs.getString("vch_to"));
					dt.setVch_from_lic_no(rs.getString("vch_from_lic_no"));
					if (rs.getString("vch_to").equalsIgnoreCase("EXPORT")
							|| rs.getString("vch_to").equalsIgnoreCase("EOI")) {
						dt.setVch_to_lic_no(rs.getString("vch_to_lic_no"));
						
					} else {
						dt.setVch_to_lic_no(rs.getString("export_lic_no"));
					}
					dt.setVch_bond(rs.getString("vch_bond"));
					dt.setCurr_date(rs.getDate("curr_date"));
					dt.setInt_max_id(rs.getInt("int_max_id"));
					dt.setDb_total_duty_2(rs.getDouble("db_total_duty"));
					dt.setDb_total_additional_duty(rs
							.getDouble("db_total_additional_duty"));
					dt.setFindispatchedbox(rs.getInt("dispatchd_box"));
					dt.setFindispatchedbttl(rs.getInt("dispatchd_bottl"));
					dt.setGtinno(rs.getString("code_generate_through"));

					 if (rs.getString("vch_finalize").equalsIgnoreCase("F")) {
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
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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

		
		//
		// ============================print draft report for
		// export==================================

		public boolean printReportexp1Draft(FLB11_EOIAction action,
				FLB11_EOIDt dt) {

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
			String reportQuery1 = null;
			boolean flagImplDraft = false;

			try {
				con = ConnectionToDataBase.getConnection();
				reportQuery = " select int_dist_id, vch_distillary_name,dispatchd_box, vch_distillary_address, vch_auth_name, export_lic_no,"+
						" mode_of_transport, bond_value,export_district, vch_to, vch_from_lic_no,vch_from, vch_gatepass_no,"+
						" dt_date,  db_total_duty, db_total_additional_duty, vch_root_details,vch_vehicle_no,int_dissleri_id,"+
						" int_brand_id,no_bottl, box_size,brand_id,brand_name,strength,"+
						" valid_till, batch_no,package_name,box_id, ml, bl ,al, vch_unit_name,container_no ,sealing_date ,seal_no,icd "+
						" from distillery.fl11gatepass_draftexp_20_21eoi(" + action.getDissteleryId() + ", '"+
						Utility.convertUtilDateToSQLDate(action.getPrintDate())+"','"+action.getPrintGatePassNo().trim()+"')";
				
				/*reportQuery = " SELECT a.int_dist_id, a.vch_distillary_name,b.dispatchd_box, a.vch_distillary_address, a.vch_auth_name, a.export_lic_no, a.mode_of_transport, a.bond_value, a.export_district,"
						+ " a.vch_to, a.vch_from_lic_no,a.vch_from, a.vch_gatepass_no,a.dt_date, "
						+ " a.db_total_duty,a. db_total_additional_duty, a.vch_root_details,a.vch_vehicle_no, "
						+ " b.int_dissleri_id, b.int_brand_id,b.dispatchd_bottl as no_bottl, "
						+ " b.size::int as box_size, c.brand_id, c.brand_name, c.strength,a.valid_till, b.batch_no, "
						+ " d.package_name,d.box_id, e.qnt_ml_detail as ml, "
						+ " (((b.dispatchd_box*b.size::int)*e.qnt_ml_detail)/1000) as bl , "
						+ " (((((b.dispatchd_box*b.size::int)*e.qnt_ml_detail)/1000)*c.strength)/100) as al, "
						+ " a.vch_distillary_name as vch_unit_name "
						+ " FROM distillery.gatepass_to_manufacturewholesale a , distillery.fl1_stock_trxn b , "
						+ " distillery.brand_registration c,  distillery.packaging_details d, "
						+ " distillery.box_size_details e  "
						+ " where a.int_dist_id=b.int_dissleri_id  and a.vch_gatepass_no=b.vch_gatepass_no   "
						+ " and b.int_dissleri_id=a.int_dist_id    "
						+ " and c.brand_id=d.brand_id_fk   and b.int_brand_id=c.brand_id and c.brand_id=d.brand_id_fk "
						+ " and b.int_brand_id=d.brand_id_fk  and b.int_pckg_id=d.package_id  "
						+ " and d.box_id=e.box_id and e.box_size=b.size::int  and e.qnt_ml_detail=d.quantity  "
						+ "   "
						+ " AND a.int_dist_id='"
						+ action.getDist_id()
						+ "' "
						+ " AND a.dt_date='"
						+ Utility.convertUtilDateToSQLDate(action.getPrintDate())
						+ "' "
						+ " AND a.vch_gatepass_no='"
						+ action.getPrintGatePassNo().trim() + "' ";*/
				//System.out.println("reportQuery11 : printReportexp1Draft="+reportQuery);
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
							+ File.separator + "MadeForeignLiquorexpDrafteoi.jasper");

					JasperPrint print = JasperFillManager.fillReport(jasperReport,
							parameters, jrRs);
					Random rand = new Random();
					int n = rand.nextInt(999) + 1;

					JasperExportManager.exportReportToPdfFile(print, relativePathpdf + File.separator+ "MadeForeignLiquorexpDraft-"+ action.getPrintGatePassNo() + ".pdf");

					
					dt.setPdfNameDt("MadeForeignLiquorexpDraft-"+ action.getPrintGatePassNo() + ".pdf");
					action.setPdfDraft("MadeForeignLiquorexpDraft-"+ action.getPrintGatePassNo() + ".pdf");
					dt.setDraftprintFlag(true);
					flagImplDraft = true;
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("No Data Found", "No Data Found"));

					flagImplDraft = false;
					dt.setDraftprintFlag(false);

				}

			} catch (JRException e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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

			return flagImplDraft;

		}
		

		// ====================print report 1 for draft==========================

		public boolean printReport1Draft(FLB11_EOIAction action,
				FLB11_EOIDt dt) {
		 

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
			String reportQuery1 = null;
			boolean flagImplDraft = false;

			try {
				con = ConnectionToDataBase.getConnection();

				reportQuery="select code_generate_through ,liquor_type,int_dissleri_id , vch_lic_no , dt , int_brand_id,container_no ,sealing_date ,seal_no," +
						" int_pckg_id, dispatchd_bottl, dispatchd_box ,  brand_name, nm , balance, vch_gatepass_no, seq," +
						" size , batch_no, vch_lic_type , duty , int_app_id_f,  dt_date , vch_from ,  vch_to, " +
						"vch_from_lic_no ,  vch_to_lic_no, int_dist_id , vch_bond , curr_date ,  int_max_id , " +
						" db_total_duty , vch_auth_name ,  vch_root_details , vch_vehicle_no , export_lic_no ," +
						"  mode_of_transport ,   export_district  ,bl , al , vehicle_driver_name  , " +
						" vehicle_agency_and_address , bond_value  , valid_till , gross_weight , tier_weight , net_weight ," +
						" strength  from distillery.fl11gatepass_draftexp1_20_21("+dt.getInt_dist_id()+", '"+
						dt.getVch_gatepass_no()+"')";
				
				/*reportQuery = "   SELECT distinct c.code_generate_through, e.license_category as liquor_type,a.int_dissleri_id, a.vch_lic_no,b.dt_date as dt, a.int_brand_id, a.int_pckg_id, "
						+ "	  a.dispatchd_bottl, a.dispatchd_box, e.brand_name,  d.vch_undertaking_name as nm,                                                                            "
						+ "	  a.dispatchd_bottl as balance, a.vch_gatepass_no, a.seq, a.size, a.batch_no,   a.vch_lic_type, a.duty,               "
						+ "	 d.int_app_id_f,  b.vch_gatepass_no,  b.dt_date,  b.vch_from,  b.vch_to,    b.vch_from_lic_no,  b.vch_to_lic_no,                  "
						+ "	    b.int_dist_id, b.vch_bond,   b.curr_date,  b.int_max_id,  b.db_total_duty, b.vch_auth_name, b.vch_root_details,                             "
						+ "	     b.vch_vehicle_no,   b.export_lic_no,    b.mode_of_transport,   b.export_district, (((a.dispatchd_box*a.size::int)*f.qnt_ml_detail)/1000) as bl , "
						+ " (((((a.dispatchd_box*a.size::int)*f.qnt_ml_detail)/1000)*e.strength)/100) as al,                                           "
						+ "	b.vehicle_driver_name,  b.vehicle_agency_and_address,  b.bond_value , "
						+ " b.valid_till,b.gross_weight, b.tier_weight, b.net_weight, e.strength                           "
						+ "	 FROM                                                                                                                            "
						+ "	distillery.gatepass_to_manufacturewholesale b , distillery.fl1_stock_trxn a,distillery.packaging_details c  ,public.dis_mst_pd1_pd2_lic d ,     "
						+ "	 distillery.brand_registration e      ,distillery.box_size_details f                                                                                           "
						+ "		  where a.int_dissleri_id="
						+ dt.getInt_dist_id()
						+ " and f.qnt_ml_detail=c.quantity and d.int_app_id_f=a.int_dissleri_id and a.int_dissleri_id=b.int_dist_id and  e.brand_id=c.brand_id_fk and                           "
						+ "	  b.vch_gatepass_no=a.vch_gatepass_no and c.brand_id_fk=a.int_brand_id and c.package_id=a.int_pckg_id and b.vch_gatepass_no='"
						+ dt.getVch_gatepass_no() + "' ";*/
				
			//	System.out.println("exp-draft1 reportQuery : printReport1Draft= "+reportQuery );
				pst = con.prepareStatement(reportQuery);
			 
				rs = pst.executeQuery();

				if (rs.next()) {

					rs = pst.executeQuery();

					Map parameters = new HashMap();
					parameters.put("REPORT_CONNECTION", con);
					parameters.put("SUBREPORT_DIR", relativePath + File.separator);
					parameters.put("image", relativePath + File.separator);

					JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

					jasperReport = (JasperReport) JRLoader.loadObject(relativePath+ File.separator + "PD_26_Report_Draft.jasper");

					JasperPrint print = JasperFillManager.fillReport(jasperReport,
							parameters, jrRs);
					Random rand = new Random();
					int n = rand.nextInt(999) + 1;

					JasperExportManager.exportReportToPdfFile(print,relativePathpdf + File.separator + "PD_26_Draft-"+ action.getPrintGatePassNo() + ".pdf");

					// FLB11_EOIDt  dt=new
					// FLB11_EOIDt ();
					dt.setPdfDraft1("PD_26_Draft-"+ action.getPrintGatePassNo() + ".pdf");

					dt.setDraftprintFlag1(true);
					flagImplDraft = true;
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("No Data Found", "No Data Found"));

					flagImplDraft = false;
					dt.setDraftprintFlag1(false);

				}

			} catch (JRException e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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

			return flagImplDraft;

		}
		

		public boolean printDraft(FLB11_EOIAction action,
				FLB11_EOIDt dt) {

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
			String reportQuery1 = null;
			String reportQuery2 = null;
			boolean flagImplDraft = false;

			try {
				con = ConnectionToDataBase.getConnection();

				reportQuery = "select int_dist_id, vch_distillary_name, dispatchd_box, vch_distillary_address, permit_dt, vch_permit_no,"+
						" vehicle_agency_and_address, vch_to, vch_from_lic_no, vch_from, vch_gatepass_no, dt_date, db_total_duty,"+
						" db_total_additional_duty, vch_root_details, vch_vehicle_no, int_dissleri_id, int_brand_id, no_bottl,"+
						" box_size, brand_id, brand_name, strength, valid_till, batch_no, package_name, box_id, ml, bl, al, vch_unit_name"+
						" from distillery.fl11gatepass_draftfl1_20_21("+action.getDissteleryId()+",'"+Utility.convertUtilDateToSQLDate(action.getPrintDate())
						+"','"+action.getPrintGatePassNo().trim()+"')";
				reportQuery1 = "select int_dist_id, vch_distillary_name, dispatchd_box, vch_distillary_address, permit_dt, vch_permit_no,"+
						" vehicle_agency_and_address, vch_to, vch_from_lic_no, vch_from, vch_gatepass_no, dt_date, db_total_duty,"+
						" db_total_additional_duty, vch_root_details, vch_vehicle_no, int_dissleri_id, int_brand_id, no_bottl,"+
						" box_size, brand_id, brand_name, strength, valid_till, batch_no, package_name, box_id, ml, bl, al, vch_unit_name"+
						" from distillery.fl11gatepass_draftfl1a_20_21("+action.getDissteleryId()+",'"+Utility.convertUtilDateToSQLDate(action.getPrintDate())
						+"','"+action.getPrintGatePassNo().trim()+"')";
				reportQuery2 = "select int_dist_id, vch_distillary_name, dispatchd_box, vch_distillary_address, permit_dt, vch_permit_no,"+
						" vehicle_agency_and_address, vch_to, vch_from_lic_no, vch_from, vch_gatepass_no, dt_date, db_total_duty,"+
						" db_total_additional_duty, vch_root_details, vch_vehicle_no, int_dissleri_id, int_brand_id, no_bottl,"+
						" box_size, brand_id, brand_name, strength, valid_till, batch_no, package_name, box_id, ml, bl, al, vch_unit_name"+
						" from distillery.fl11gatepass_draftfl2a_20_21("+action.getDissteleryId()+",'"+Utility.convertUtilDateToSQLDate(action.getPrintDate())
						+"','"+action.getPrintGatePassNo().trim()+"')";
				
				
		/*		reportQuery = " SELECT a.int_dist_id, a.vch_distillary_name,b.dispatchd_box, a.vch_distillary_address, a.permit_dt, a.vch_permit_no,a.vehicle_agency_and_address,"
						+ " a.vch_to, a.vch_from_lic_no,a.vch_from, a.vch_gatepass_no,a.dt_date, "
						+ " a.db_total_duty,a. db_total_additional_duty, a.vch_root_details,a.vch_vehicle_no, "
						+ " b.int_dissleri_id, b.int_brand_id,b.dispatchd_bottl as no_bottl, "
						+ " b.size::int as box_size, c.brand_id, c.brand_name, c.strength,a.valid_till,b.batch_no, "
						+ " d.package_name,d.box_id, e.qnt_ml_detail as ml, "
						+ " (((b.dispatchd_box*b.size::int)*e.qnt_ml_detail)/1000) as bl , "
						+ " (((((b.dispatchd_box*b.size::int)*e.qnt_ml_detail)/1000)*c.strength)/100) as al, "
						+ " a.vch_distillary_name as vch_unit_name "
						+ " FROM distillery.gatepass_to_manufacturewholesale a , distillery.fl1_stock_trxn b , "
						+ " distillery.brand_registration c,  distillery.packaging_details d, "
						+ " distillery.box_size_details e  "
						+ " where a.int_dist_id=b.int_dissleri_id  and a.vch_gatepass_no=b.vch_gatepass_no   "
						+ " and b.int_dissleri_id=a.int_dist_id and b.vch_lic_no=a.vch_to_lic_no  "
						+ " and c.brand_id=d.brand_id_fk   and b.int_brand_id=c.brand_id and c.brand_id=d.brand_id_fk "
						+ " and b.int_brand_id=d.brand_id_fk  and b.int_pckg_id=d.package_id  "
						+ " and d.box_id=e.box_id and e.box_size=b.size::int  and e.qnt_ml_detail=d.quantity  "
						+ "   "
						+ " AND a.int_dist_id='"
						+ action.getDist_id()
						+ "' "
						+ " AND a.dt_date='"
						+ Utility.convertUtilDateToSQLDate(action.getPrintDate())
						+ "' "
						+ " AND a.vch_gatepass_no='"
						+ action.getPrintGatePassNo().trim() + "' ";*/

				/*reportQuery1 = " SELECT a.int_dist_id, a.vch_distillary_name,b.dispatchd_box, a.vch_distillary_address,a.permit_dt, a.vch_permit_no,a.vehicle_agency_and_address,"
						+ " a.vch_to, a.vch_from_lic_no,a.vch_from, a.vch_gatepass_no,a.dt_date, "
						+ " a.db_total_duty,a. db_total_additional_duty, a.vch_root_details,a.vch_vehicle_no, "
						+ " b.int_dissleri_id, b.int_brand_id,b.dispatchd_bottl as no_bottl, "
						+ " b.size::int as box_size, c.brand_id, c.brand_name, c.strength,a.valid_till,b.batch_no,"
						+ " d.package_name,d.box_id, e.qnt_ml_detail as ml, "
						+ " (((b.dispatchd_box*b.size::int)*e.qnt_ml_detail)/1000) as bl , "
						+ " (((((b.dispatchd_box*b.size::int)*e.qnt_ml_detail)/1000)*c.strength)/100) as al, "
						+ " f.vch_unit_name "
						+ " FROM distillery.gatepass_to_manufacturewholesale a , distillery.fl1_stock_trxn b , "
						+ " distillery.brand_registration c,  distillery.packaging_details d, "
						+ " distillery.box_size_details e, licence.fl3a_fl1a f "
						+ " where a.int_dist_id=b.int_dissleri_id  and a.vch_gatepass_no=b.vch_gatepass_no   "
						+ " and b.int_dissleri_id=a.int_dist_id and b.vch_lic_no=a.vch_to_lic_no  "
						+ " and c.brand_id=d.brand_id_fk   and b.int_brand_id=c.brand_id and c.brand_id=d.brand_id_fk "
						+ " and b.int_brand_id=d.brand_id_fk  and b.int_pckg_id=d.package_id  "
						+ " and d.box_id=e.box_id and e.box_size=b.size::int  and e.qnt_ml_detail=d.quantity  "
						+ " and a.vch_to_lic_no=f.vch_license_fl1a AND  a.int_dist_id=f.int_distillery_id "
						+ " AND a.int_dist_id='"
						+ action.getDist_id()
						+ "' "
						+ " AND a.dt_date='"
						+ Utility.convertUtilDateToSQLDate(action.getPrintDate())
						+ "' "
						+ " AND a.vch_gatepass_no='"
						+ action.getPrintGatePassNo().trim() + "' ";*/

				/*reportQuery2 = " SELECT a.int_dist_id, a.vch_distillary_name,b.dispatchd_box, a.vch_distillary_address,a.permit_dt, a.vch_permit_no,a.vehicle_agency_and_address,"
						+ " a.vch_to, a.vch_from_lic_no,a.vch_from, a.vch_gatepass_no,a.dt_date, "
						+ " a.db_total_duty,a. db_total_additional_duty, a.vch_root_details,a.vch_vehicle_no, "
						+ " b.int_dissleri_id, b.int_brand_id,b.dispatchd_bottl as no_bottl, "
						+ " b.size::int as box_size, c.brand_id, c.brand_name, c.strength,a.valid_till,b.batch_no,"
						+ " d.package_name,d.box_id, e.qnt_ml_detail as ml, "
						+ " (((b.dispatchd_box*b.size::int)*e.qnt_ml_detail)/1000) as bl , "
						+ " (((((b.dispatchd_box*b.size::int)*e.qnt_ml_detail)/1000)*c.strength)/100) as al, "
						+ " f.vch_firm_name as vch_unit_name "
						+ " FROM distillery.gatepass_to_manufacturewholesale a , distillery.fl1_stock_trxn b , "
						+ " distillery.brand_registration c,  distillery.packaging_details d, "
						+ " distillery.box_size_details e, licence.fl2_2b_2d f "
						+ " where a.int_dist_id=b.int_dissleri_id  and a.vch_gatepass_no=b.vch_gatepass_no   "
						+ " and b.int_dissleri_id=a.int_dist_id and b.vch_lic_no=a.vch_to_lic_no  "
						+ " and c.brand_id=d.brand_id_fk   and b.int_brand_id=c.brand_id and c.brand_id=d.brand_id_fk "
						+ " and b.int_brand_id=d.brand_id_fk  and b.int_pckg_id=d.package_id  "
						+ " and d.box_id=e.box_id and e.box_size=b.size::int  and e.qnt_ml_detail=d.quantity  "
						+ " and a.vch_to_lic_no=f.vch_licence_no and f.vch_license_type='FL2A' "
						+ " AND a.int_dist_id='"
						+ action.getDist_id()
						+ "' "
						+ " AND a.dt_date='"
						+ Utility.convertUtilDateToSQLDate(action.getPrintDate())
						+ "' "
						+ " AND a.vch_gatepass_no='"
						+ action.getPrintGatePassNo().trim() + "' ";*/
						
						
						
				if (dt.getVch_to().equalsIgnoreCase("FL1")) {
					pst = con.prepareStatement(reportQuery);
					//System.out.println("reportQuery="+reportQuery);
				} else if (dt.getVch_to().equalsIgnoreCase("FL1A")) {
					pst = con.prepareStatement(reportQuery1);
					//System.out.println("reportQuery1="+reportQuery1);
				} else if (dt.getVch_to().equalsIgnoreCase("FL2A")) {
					pst = con.prepareStatement(reportQuery2);
					//System.out.println("reportQuery2="+reportQuery2);
				}

				rs = pst.executeQuery();
				if (rs.next()) {

					rs = pst.executeQuery();

					Map parameters = new HashMap();
					parameters.put("REPORT_CONNECTION", con);
					parameters.put("SUBREPORT_DIR", relativePath + File.separator);
					parameters.put("image", relativePath + File.separator);

					JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

					jasperReport = (JasperReport) JRLoader.loadObject(relativePath
							+ File.separator + "MadeForeignLiquorDraft.jasper");

					JasperPrint print = JasperFillManager.fillReport(jasperReport,
							parameters, jrRs);
					Random rand = new Random();
					int n = rand.nextInt(999) + 1;

					JasperExportManager.exportReportToPdfFile(print,relativePathpdf + File.separator+ "MadeForeignLiquorDraft-"+ action.getPrintGatePassNo() +".pdf");

					// FLB11_EOIDt  dt=new
					// FLB11_EOIDt ();
					dt.setPdfNameDt("MadeForeignLiquorDraft-"+ action.getPrintGatePassNo() +".pdf");
					action.setPdfDraft("MadeForeignLiquorDraft-"+ action.getPrintGatePassNo() +".pdf");
					dt.setDraftprintFlag(true);
					flagImplDraft = true;
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("No Data Found", "No Data Found"));

					flagImplDraft = false;
					dt.setDraftprintFlag(false);

				}

			} catch (JRException e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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

			return flagImplDraft;

		}
		
		

		public boolean printReportexp1(FLB11_EOIAction  action,FLB11_EOIDt dt) {

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
			String reportQuery1 = null;
			boolean flagImpl = false;

			try {
				con = ConnectionToDataBase.getConnection();
				reportQuery = " select int_dist_id, vch_distillary_name,dispatchd_box, vch_distillary_address, vch_auth_name, export_lic_no,"+
						" mode_of_transport, bond_value,export_district, vch_to, vch_from_lic_no,vch_from, vch_gatepass_no,"+
						" dt_date,  db_total_duty, db_total_additional_duty, vch_root_details,vch_vehicle_no,int_dissleri_id,"+
						" int_brand_id,no_bottl, box_size,brand_id,brand_name,strength,"+
						" valid_till, batch_no,package_name,box_id, ml, bl ,al, vch_unit_name,container_no ,sealing_date ,seal_no,icd "+
						" from distillery.fl11gatepass_draftexp_20_21eoi(" + action.getDissteleryId() + ", '"+
						Utility.convertUtilDateToSQLDate(action.getPrintDate())+"','"+action.getPrintGatePassNo().trim()+"')";
				/*reportQuery = " SELECT a.int_dist_id, a.vch_distillary_name,b.dispatchd_box, a.vch_distillary_address, a.vch_auth_name, a.export_lic_no, a.mode_of_transport, a.bond_value, a.export_district,"
						+ " a.vch_to, a.vch_from_lic_no,a.vch_from, a.vch_gatepass_no,a.dt_date, "
						+ " a.db_total_duty,a. db_total_additional_duty, a.vch_root_details,a.vch_vehicle_no, "
						+ " b.int_dissleri_id, b.int_brand_id,b.dispatchd_bottl as no_bottl, "
						+ " b.size::int as box_size, c.brand_id, c.brand_name, c.strength,a.valid_till, b.batch_no, "
						+ " d.package_name,d.box_id, e.qnt_ml_detail as ml, "
						+ " (((b.dispatchd_box*b.size::int)*e.qnt_ml_detail)/1000) as bl , "
						+ " (((((b.dispatchd_box*b.size::int)*e.qnt_ml_detail)/1000)*c.strength)/100) as al, "
						+ " a.vch_distillary_name as vch_unit_name "
						+ " FROM distillery.gatepass_to_manufacturewholesale a , distillery.fl1_stock_trxn b , "
						+ " distillery.brand_registration c,  distillery.packaging_details d, "
						+ " distillery.box_size_details e  "
						+ " where a.int_dist_id=b.int_dissleri_id  and a.vch_gatepass_no=b.vch_gatepass_no   "
						+ " and b.int_dissleri_id=a.int_dist_id    "
						+ " and c.brand_id=d.brand_id_fk   and b.int_brand_id=c.brand_id and c.brand_id=d.brand_id_fk "
						+ " and b.int_brand_id=d.brand_id_fk  and b.int_pckg_id=d.package_id  "
						+ " and d.box_id=e.box_id and e.box_size=b.size::int  and e.qnt_ml_detail=d.quantity  "
						+ "   "
						+ " AND a.int_dist_id='"
						+ action.getDist_id()
						+ "' "
						+ " AND a.dt_date='"
						+ Utility.convertUtilDateToSQLDate(action.getPrintDate())
						+ "' "
						+ " AND a.vch_gatepass_no='"
						+ action.getPrintGatePassNo().trim() + "' ";*/
	//System.out.println("reportQuery : printReportexp1="+reportQuery);
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
							+ File.separator + "MadeForeignLiquorexpeoi.jasper");

					JasperPrint print = JasperFillManager.fillReport(jasperReport,
							parameters, jrRs);
					Random rand = new Random();
					int n = rand.nextInt(999) + 1;

					JasperExportManager.exportReportToPdfFile(print,relativePathpdf + File.separator+ "MadeForeignLiquorexp-" + action.getPrintGatePassNo()+ ".pdf");

					// FLB11_EOIDt  dt=new
					// FLB11_EOIDt ();
					dt.setPdfNameDt("MadeForeignLiquorexp-" + action.getPrintGatePassNo()+ ".pdf");
					action.setPdfName("MadeForeignLiquorexp-" + action.getPrintGatePassNo()+ ".pdf");
					dt.setMyFlagDt(true);
					flagImpl = true;
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("No Data Found", "No Data Found"));

					flagImpl = false;
					dt.setMyFlagDt(false);

				}

			} catch (JRException e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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

			return flagImpl;

		}

		
		public boolean printReport1(FLB11_EOIAction action,
				FLB11_EOIDt dt) {

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
			String reportQuery1 = null;
			boolean flagImpl = false;

			try {
				con = ConnectionToDataBase.getConnection();

				reportQuery="select code_generate_through ,liquor_type,int_dissleri_id , vch_lic_no , dt , int_brand_id,container_no ,sealing_date ,seal_no," +
						" int_pckg_id, dispatchd_bottl, dispatchd_box ,  brand_name, nm , balance, vch_gatepass_no, seq," +
						" size , batch_no, vch_lic_type , duty , int_app_id_f,  dt_date , vch_from ,  vch_to, " +
						"vch_from_lic_no ,  vch_to_lic_no, int_dist_id , vch_bond , curr_date ,  int_max_id , " +
						" db_total_duty , vch_auth_name ,  vch_root_details , vch_vehicle_no , export_lic_no ," +
						"  mode_of_transport ,   export_district  ,bl , al , vehicle_driver_name  , " +
						" vehicle_agency_and_address , bond_value  , valid_till , gross_weight , tier_weight , net_weight ," +
						" strength  from distillery.fl11gatepass_draftexp1_20_21("+dt.getInt_dist_id()+", '"+
						dt.getVch_gatepass_no()+"')";
				 
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
							+ File.separator + "PD_26_Report.jasper");

					JasperPrint print = JasperFillManager.fillReport(jasperReport,
							parameters, jrRs);
					Random rand = new Random();
					int n = rand.nextInt(999) + 1;

					JasperExportManager.exportReportToPdfFile(print,relativePathpdf + File.separator + "PD_26-"+ action.getPrintGatePassNo()+ ".pdf");

					// FLB11_EOIDt  dt=new
					// FLB11_EOIDt ();
					dt.setPdfNameDt1("PD_26-"+ action.getPrintGatePassNo()+ ".pdf");

					dt.setMyFlagDt1(true);
					flagImpl = true;
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("No Data Found", "No Data Found"));

					flagImpl = false;
					dt.setMyFlagDt1(false);

				}

			} catch (JRException e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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

			return flagImpl;

		}
		
		// ------------------------------ PRINT
		// newly---------------------------------------

		public boolean printReport(FLB11_EOIAction action,
				FLB11_EOIDt dt) {

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
			String reportQuery1 = null;
			String reportQuery2 = null;
			boolean flagImpl = false;

			try {
				con = ConnectionToDataBase.getConnection();
				reportQuery = "select int_dist_id, vch_distillary_name, dispatchd_box, vch_distillary_address, permit_dt, vch_permit_no,"+
						" vehicle_agency_and_address, vch_to, vch_from_lic_no, vch_from, vch_gatepass_no, dt_date, db_total_duty,"+
						" db_total_additional_duty, vch_root_details, vch_vehicle_no, int_dissleri_id, int_brand_id, no_bottl,"+
						" box_size, brand_id, brand_name, strength, valid_till, batch_no, package_name, box_id, ml, bl, al, vch_unit_name"+
						" from distillery.fl11gatepass_draftfl1_20_21("+action.getDissteleryId()+",'"+Utility.convertUtilDateToSQLDate(action.getPrintDate())
						+"','"+action.getPrintGatePassNo().trim()+"')";
				reportQuery1 = "select int_dist_id, vch_distillary_name, dispatchd_box, vch_distillary_address, permit_dt, vch_permit_no,"+
						" vehicle_agency_and_address, vch_to, vch_from_lic_no, vch_from, vch_gatepass_no, dt_date, db_total_duty,"+
						" db_total_additional_duty, vch_root_details, vch_vehicle_no, int_dissleri_id, int_brand_id, no_bottl,"+
						" box_size, brand_id, brand_name, strength, valid_till, batch_no, package_name, box_id, ml, bl, al, vch_unit_name"+
						" from distillery.fl11gatepass_draftfl1a_20_21("+action.getDissteleryId()+",'"+Utility.convertUtilDateToSQLDate(action.getPrintDate())
						+"','"+action.getPrintGatePassNo().trim()+"')";
				reportQuery2 = "select int_dist_id, vch_distillary_name, dispatchd_box, vch_distillary_address, permit_dt, vch_permit_no,"+
						" vehicle_agency_and_address, vch_to, vch_from_lic_no, vch_from, vch_gatepass_no, dt_date, db_total_duty,"+
						" db_total_additional_duty, vch_root_details, vch_vehicle_no, int_dissleri_id, int_brand_id, no_bottl,"+
						" box_size, brand_id, brand_name, strength, valid_till, batch_no, package_name, box_id, ml, bl, al, vch_unit_name"+
						" from distillery.fl11gatepass_draftfl2a_20_21("+action.getDissteleryId()+",'"+Utility.convertUtilDateToSQLDate(action.getPrintDate())
						+"','"+action.getPrintGatePassNo().trim()+"')";
				 

				if (dt.getVch_to().equalsIgnoreCase("FL1")) { 
					pst = con.prepareStatement(reportQuery);
				} else if (dt.getVch_to().equalsIgnoreCase("FL1A")) { 
					pst = con.prepareStatement(reportQuery1);

				} else if (dt.getVch_to().equalsIgnoreCase("FL2A")) { 
					pst = con.prepareStatement(reportQuery2);

				}

				rs = pst.executeQuery();
				if (rs.next()) {

					rs = pst.executeQuery();

					Map parameters = new HashMap();
					parameters.put("REPORT_CONNECTION", con);
					parameters.put("SUBREPORT_DIR", relativePath + File.separator);
					parameters.put("image", relativePath + File.separator);

					JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

					jasperReport = (JasperReport) JRLoader.loadObject(relativePath+ File.separator + "MadeForeignLiquor.jasper");

					JasperPrint print = JasperFillManager.fillReport(jasperReport,
							parameters, jrRs);
					Random rand = new Random();
					int n = rand.nextInt(999) + 1;

					JasperExportManager.exportReportToPdfFile(print,relativePathpdf + File.separator + action.getPrintGatePassNo() +".pdf");

					// FLB11_EOIDt  dt=new
					// FLB11_EOIDt ();
					dt.setPdfNameDt(action.getPrintGatePassNo() +".pdf");
					action.setPdfName(action.getPrintGatePassNo() +".pdf");
					dt.setMyFlagDt(true);
					flagImpl = true;
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("No Data Found", "No Data Found"));

					flagImpl = false;
					dt.setMyFlagDt(false);

				}

			} catch (JRException e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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

			return flagImpl;

		}
		
		// -----------------------------------------------------

		public ArrayList getExcelData(FLB11_EOIAction action) {
			ArrayList list = new ArrayList();

			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
	         int boxingCount=0;
	         int listSize=0;
			String query = "SELECT vch_gatepass_no,vch_casecode FROM distillery.fl11_dislery_casecode_20_21 where vch_gatepass_no='"
					+ action.getScanGatePassNo().trim() + "' order by vch_casecode";
			//System.out.println("getExcelData="+query);
			try {
				con = ConnectionToDataBase.getConnection();
				stmt = con.prepareStatement(query);
				rs = stmt.executeQuery();
				// Date d=Utility.convertSqlDateToUtilDate(date_created);
				while (rs.next()) {
					FLB11_EOIDt dt = new FLB11_EOIDt();

					action.setValFlag(true);
					dt.setGatepass(rs.getString("vch_gatepass_no"));
					dt.setCasecode(rs.getString("vch_casecode"));
					String datePlan=rs.getString("vch_casecode").substring(16,22).trim();
					//System.out.println("dt casecode="+rs.getString("vch_casecode").substring(26,rs.getString("vch_casecode").length()));
					//System.out.println("dt etin="+rs.getString("vch_casecode").substring(0, 12));
					//datePlan="20"+datePlan;
					//System.out.println("11="+datePlan.substring(0, 2));
					//System.out.println("22="+datePlan.substring(2, 4));
					//System.out.println("33="+datePlan.substring(4,6));
					String datePlan1="";
					datePlan1="20"+datePlan.substring(0, 2)+"/"+datePlan.substring(2, 4)+"/"+datePlan.substring(4,6);
					
					Date date_plan=new SimpleDateFormat("yyyy/MM/dd").parse(datePlan1);
					
					date_plan=Utility.convertUtilDateToSQLDate(date_plan);
					System.out.println(datePlan1+"\t"+date_plan);
					boolean flag=getCascodeMatch(rs.getString("vch_casecode").substring(26,rs.getString("vch_casecode").length()),rs.getString("vch_casecode").substring(0, 12),date_plan,action);
					//boolean flag2=getSize(rs.getString("vch_casecode").substring(23,26),rs.getString("vch_casecode").substring(0, 12),action);
				String brand_name=getCascodeBrand(rs.getString("vch_casecode").substring(0, 12));
				dt.setBrand_name(brand_name);
				//System.out.println("brand name="+dt.getBrand_name());
				dt.setCasecoseBrandSize(Integer.parseInt(rs.getString("vch_casecode").substring(23, 26))); 
				dt.setDate_plan(date_plan);
				
					if(flag)
					{++listSize;
					dt.setCascodeMatching("BoxingDone");
					/*	System.out.println("11");
						if(flag2){
							System.out.println("00");
						++listSize;
						dt.setCascodeMatching("BoxingDone");
						}else{
							dt.setCascodeMatching("Size not Match");
						}*/
					}else{
						
						//System.out.println("boxingCount"+boxingCount);
						++boxingCount;
						 dt.setCascodeMatching("BoxingNotDone");
					}


					list.add(dt);

				}
				
				 
				if(boxingCount!=0 || listSize<=0 )
				{
					 
					action.setBottlingNotDoneFlag(true);
				}
				
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
		
		
		// -----------------------cancel gatepass------------------------------

		 public String cancel_gatepassImpl(FLB11_EOIAction act,String vch, String vch_to_lic)
		{

			Connection con = null;
			PreparedStatement ps = null, ps2 = null, ps3 = null, ps4 = null, ps5 = null;
			PreparedStatement pstmt = null;

			ResultSet rs = null;
			ResultSet rs1 = null;
			String sql = "";
			String sql2 = "";
			String sql3 = "";String level = "";
			int tok1 = 0;
			int tok2 = 0;

			int seq = this.maxIdSeq(this);

			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

				con = ConnectionToDataBase.getConnection();
				con.setAutoCommit(false);

				String gatepass = "";
				gatepass = act.getPrintGatePassNo().trim();

				String selQr = 	" SELECT a.int_dist_id, a.dt_date, a.vch_gatepass_no, a.vch_from_lic_no, a.vch_to,a.vch_to_lic_no,c.code_generate_through, " +
								" a.vch_from, b.duty, b.addduty, b.int_brand_id, b.int_pckg_id, b.dispatchd_bottl, " +
								" b.avl_bottl, b.avl_box, b.dispatchd_box, b.tnt,b.size  " +
								" FROM distillery.gatepass_to_manufacturewholesale_20_21 a, distillery.fl1_stock_trxn_20_21 b ,distillery.packaging_details_20_21 c " +
								" WHERE a.int_dist_id=b.int_dissleri_id AND a.vch_gatepass_no=b.vch_gatepass_no and a.vch_finalize is null and c.package_id=b.int_pckg_id  " +
								" AND a.int_dist_id='"+act.getDissteleryId()+"' AND a.vch_gatepass_no='"+gatepass+"' ";
				
				pstmt = con.prepareStatement(selQr);
				rs = pstmt.executeQuery();

				while (rs.next()) 
				{
					FLB11_EOIDt  dt = new FLB11_EOIDt ();
					dt.setBrandIdDt(rs.getInt("int_brand_id"));
					dt.setPckgIdDt(rs.getInt("int_pckg_id"));
					dt.setDispatcBotlsDt(rs.getInt("dispatchd_bottl"));
					dt.setLicenseNoDt(rs.getString("vch_from_lic_no"));
					dt.setLicenseTypeDt(rs.getString("vch_from"));
					dt.setAvlBottleDt(rs.getInt("avl_bottl"));
					dt.setAvlBoxDt(rs.getInt("avl_box"));
					dt.setDispatchBoxDt(rs.getInt("dispatchd_box"));
					dt.setDutyDt(rs.getDouble("duty"));
					dt.setAdddutyDt(rs.getDouble("addduty"));
					dt.setTntflgDt(rs.getString("tnt"));
					dt.setSize(rs.getInt("size"));
					dt.setCode_generate_through(rs.getString("code_generate_through"));
					dt.setVch_to(rs.getString("vch_to_lic_no"));
				String updtQr = "UPDATE distillery.fl1_stock_20_21 SET int_dispatch=COALESCE(int_dispatch,0)-"
							+ dt.getDispatcBotlsDt()
							+ ", db_duty=COALESCE(db_duty,0)-"	
							+ dt.getDutyDt()
							+ ", db_add_duty=COALESCE(db_add_duty,0)-"
							+ dt.getAdddutyDt()
							+ ", int_bottle_avaliable="
							+ (dt.getAvlBottleDt() + dt.getDispatcBotlsDt())
							+ ", no_boxes_avaliable="
							+ (dt.getAvlBoxDt() + dt.getDispatchBoxDt())
							+ " WHERE int_package_ml="
							+ dt.getPckgIdDt()
							+ " AND  int_dist_id="
							+ act.getDissteleryId()
							+ "   AND  int_brand_id="
							+ dt.getBrandIdDt()+ "  and lic_no='"+dt.getLicenseNoDt()+"' ";
					
					
			String updtQr1 =" UPDATE distillery.boxing_stock_20_21 " +
							" SET int_dispatched=COALESCE(int_dispatched,0)-"+dt.getDispatcBotlsDt()+", " +
							" int_dispatched_cases=COALESCE(int_dispatched_cases,0)-"+dt.getDispatchBoxDt()+" "+
							" WHERE int_dissleri_id="
							+ act.getDissteleryId()+" "
							+ "AND int_pckg_id="
							+ dt.getPckgIdDt()
							+ " AND vch_lic_no='"
							+ dt.getLicenseNoDt()
							+ "' and    vch_lic_type='"
							+ dt.getLicenseTypeDt()
							+ "' AND int_brand_id="
							+ dt.getBrandIdDt()
							+ " and tnt='" + dt.getTntflgDt()+"'  and size="+ dt.getSize()+"";
					
					
					ps4 = con.prepareStatement(updtQr);
					tok1 = ps4.executeUpdate();
					level="Failed at level 1";
				
					
					if(tok1 > 0)
					{
						tok1 = 0;
						ps5 = con.prepareStatement(updtQr1);
						tok1 = ps5.executeUpdate();
						level="Failed at level 3";
					}
					
					
					if(tok1 > 0){
						ps5=null;
						tok1=0;
												
						sql3 = " update distillery.eoi_app_for_export_order_brand set   "+
							   "	dispatched_bottles=coalesce(dispatched_bottles,0)-'"+dt.getDispatcBotlsDt()+"'   "+
							   "	where app_id_fk='"+dt.getVch_to()+"'    "+
							   "	and etin='"+dt.getCode_generate_through()+"'    "+
							   "	and distillery_id='"+act.getDissteleryId()+"'    ";
								
								
								
						 
					 
						ps5 = con.prepareStatement(sql3);

					 
						tok1 = ps5.executeUpdate();
						level="Failed at level 3+"+dt.getVch_to()+"/"+dt.getCode_generate_through();
						 
						
					}
				}
				if (tok1 > 0) 
				{
					tok1 = 0;
					sql = 	" INSERT INTO distillery.duty_cancellation_20_21( "
							+ " seq, vch_gatepass_no, gatepass_dt, duty_cancellation_dt_tm, db_duty_amount, vch_type, db_add_duty_amount, " +
							" vch_duty_type, distillery_id) "
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
					ps = con.prepareStatement(sql);
					ps.setInt(1, seq);
					ps.setString(2, act.getPrintGatePassNo());
					ps.setDate(3,Utility.convertUtilDateToSQLDate(act.getPrintDate()));
					ps.setString(4, dateFormat.format(new Date()));
					ps.setDouble(5, act.getCancelDuty());
					ps.setString(6, "FL11-Dist");
					ps.setDouble(7, act.getCancelAddDuty());
					if (vch.equals("FL1") || vch.equals("FL1A")) {
						ps.setString(8, "DUTY_FL");					
					} else if (vch.equals("FL2A")) {
						ps.setString(8, "DUTY_FL_CSD");					
					}
					else if(vch.equalsIgnoreCase("EXPORT") || vch.equalsIgnoreCase("EOI")){
						ps.setString(8, "EXP_FEE_FL");
					}
					
					ps.setInt(9, act.getDissteleryId());	
					
					tok1 = ps.executeUpdate();
					level="Failed at level 4";
				}

				if (tok1 > 0) 
				{
					tok1 = 0;
					sql2 = 	" DELETE FROM distillery.gatepass_to_manufacturewholesale_20_21 "+
							" WHERE vch_gatepass_no='"+gatepass+"' " +
							" AND int_dist_id='"+act.getDissteleryId()+"' AND vch_finalize IS NULL ";
					ps2 = con.prepareStatement(sql2);
					tok1 = ps2.executeUpdate();
					level="Failed at level 5";
				}
				if (tok1 > 0) 
				{
					tok1 = 0;
					sql3 = 	" DELETE FROM distillery.fl1_stock_trxn_20_21 "+
							" WHERE vch_gatepass_no='"+gatepass+"' " +
							" AND int_dissleri_id='" + act.getDissteleryId() + "' ";
					ps3 = con.prepareStatement(sql3);
					tok1 = ps3.executeUpdate();
					level="Failed at level 6";
				}
				
				if (tok1 > 0)
				{
				
					tok1 = 0;
					sql3 = " DELETE FROM distillery.duty_register_19_20  WHERE  gatepass='"+gatepass+"'";
					  
						 
					  
					ps3 = con.prepareStatement(sql3);
					tok1 = ps3.executeUpdate();
					level="Failed at level 2";
				}
				 
				
				if (tok1 > 0)
				{
				String gt="Export Duty for FL11B for Gatepass "+gatepass;
					tok1 = 0;
					sql3 = " DELETE FROM distillery.bond_register_20_21  WHERE  gatepass='"+gatepass+"'";
					  
						 
					  
					ps3 = con.prepareStatement(sql3);
					tok1 = ps3.executeUpdate();
					level="Failed at level 2+";
				}
				if (tok1 > 0) 
				{
					con.setAutoCommit(true);
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Gatepass Cancelled Successfully !!! ","Gatepass Cancelled Successfully !!!"));
					act.clearAll();
				}

				else 
				{
					con.rollback();
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Gatepass Not Cancelled !!! "+level,"Gatepass Not Cancelled !!!"+level));

				}

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
		
	 
		
		// -----------------code for cancel gatepass--------------------------

		// ----------------------get max id ---------------------------

		public int maxIdSeq(FLB11_EOIImpl impl) {

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = " SELECT max(seq) as id FROM distillery.duty_cancellation_20_21 ";
			int maxid = 0;
			try {
				con = ConnectionToDataBase.getConnection();
				pstmt = con.prepareStatement(query);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					maxid = rs.getInt("id");
				}
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
		
		

		
		public boolean getCascodeMatch(String casecode,String etin,Date date_plan,FLB11_EOIAction action)
		{
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			boolean flag=false;
			String sql="";
			
			if(action.getScanVchFrom().equalsIgnoreCase("FL3"))
			{
			sql="select * from bottling_unmapped.disliry_unmap_fl3  WHERE casecode=? and etin=? and fl11_date is   null and " +
					" date_plan='"+Utility.convertUtilDateToSQLDate(date_plan)+"' and " +
					" fl36_date is  null and boxing_seq is not null  ";
			}if(action.getScanVchFrom().equalsIgnoreCase("FL3A"))
			{
				sql="select * from bottling_unmapped.disliry_unmap_fl3a  WHERE casecode=? and etin=? and fl11_date is   null and " +
						" date_plan='"+Utility.convertUtilDateToSQLDate(date_plan)+"' and " +
						" fl36_date is  null and boxing_seq is not null ";	
			}
			//System.out.println("select="+sql);
			try{
			conn=ConnectionToDataBase.getConnection20_21();
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
		{FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
		
		
		public String getCascodeBrand(String etin)
		{
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String brand_name="";
			String sql="";
			
			sql="select a.brand_name from distillery.brand_registration_20_21 a,distillery.packaging_details_20_21 b where " +
				"b.brand_id_fk=a.brand_id and code_generate_through='"+etin+"'";
			//System.out.println("brand name casecode="+sql);
			try{
			conn=ConnectionToDataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			
		 
			//pstmt.setString(1, casecode);
			//pstmt.setString(2, etin);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				brand_name=rs.getString("brand_name");
			}else{
				brand_name="Invalid Brand";
			}
			
		}
		catch(Exception e)
		{FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
		return brand_name;
		}
		
	//-----------------------------------------------------------------------------------
		
		public void saveCSV(FLB11_EOIAction action) throws IOException {
			Connection con = null;
			PreparedStatement stmt = null;
			String query = "INSERT INTO distillery.fl11_dislery_casecode_20_21(vch_gatepass_no, vch_casecode)VALUES (?, ?)";

			String gatepass = "";
			int status = 0, flag = 0;
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
								 
								gatepass = sd.trim();
							}

							else
							{ //System.out.println("gatepass="+gatepass.trim());
							   //System.out.println("GatePass="+action.getScanGatePassNo().trim());
								if (gatepass.trim().equalsIgnoreCase(action.getScanGatePassNo().trim())) {
									casecode = sd;
									 //System.out.println("casecode="+casecode.trim().substring(0, 12)+" | size="+casecode.trim().substring(23, 26));
									if(this.etin(casecode.trim().substring(0, 12),casecode.trim().substring(23, 26), action)==true){
										FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
												" Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!" ," Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!"));	
									}else{ 
									
									stmt.setString(1, gatepass.trim());
									stmt.setString(2, casecode.trim());
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
						FacesContext.getCurrentInstance()
								.addMessage(
										null,
										new FacesMessage(FacesMessage.SEVERITY_ERROR,
												"File Uploaded Successfully ",
												"File Uploaded Successfully "));
					} else {
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"File Not Uploaded!!", "File Not Uploaded!!"));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Kindly Enter Same Gatepass Number !! ",
									"Kindly Enter Same Gatepass Number !! "));
				}
			} catch (Exception ex) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(ex.getMessage(), ex.getMessage()));
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
		
		
		public boolean  etin(String casecode,String size,FLB11_EOIAction act) {
			 
			Connection con = null;
			PreparedStatement pstmt = null, ps2 = null;
			ResultSet rs = null, rs2 = null;
			boolean s = false;
			try {
				con = ConnectionToDataBase.getConnection();
	 
			
				String query = " SELECT distinct b.code_generate_through FROM distillery.fl1_stock_trxn_20_21 a, distillery.packaging_details_20_21 b "
						+ " WHERE b.package_id = a.int_pckg_id  and a.vch_gatepass_no='"+ act.getScanGatePassNo().trim()+ "' and" +
								" b.code_generate_through='"+casecode+"' and a.size::numeric="+size+"";
				System.out.println("chk etin="+query);
				pstmt = con.prepareStatement(query);
				 
				rs = pstmt.executeQuery();
				if (rs.next()) {
					System.out.println("chk efalse=");
				 
				}else{
					System.out.println("chk true="+s);
					s=true;
				}

				 

			} catch (SQLException se) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(se.getMessage(), se.getMessage()));
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
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(se.getMessage(), se.getMessage()));
					se.printStackTrace();
				}catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
					e.printStackTrace();
				}
			}
			return s;

		}

		public int excelCases(FLB11_EOIAction act) {
			int id = 0;
			Connection con = null;
			PreparedStatement pstmt = null, ps2 = null;
			ResultSet rs = null, rs2 = null;
			String s = "";
			try {
				con = ConnectionToDataBase.getConnection();

				String query = " SELECT count(*) as dispatchd_box FROM distillery.fl11_dislery_casecode_20_21 "
						+ " WHERE vch_gatepass_no='"
						+ act.getScanGatePassNo().trim() + "'";

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
		
		// =============select cases from databases===================

		public int recieveCases(FLB11_EOIAction act) {
			int id = 0;
			Connection con = null;
			PreparedStatement pstmt = null, ps2 = null;
			ResultSet rs = null, rs2 = null;
			String s = "";
			try {
				con = ConnectionToDataBase.getConnection();

				String query = " SELECT SUM(dispatchd_box) as dispatchd_box FROM distillery.fl1_stock_trxn_20_21 "
						+ " WHERE vch_gatepass_no='"
						+ act.getScanGatePassNo().trim() + "'  ";

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
      
		


		public boolean updateFL3(FLB11_EOIAction act) {
			int save = 0;
			int j[] = null;
			boolean val = false;
			PreparedStatement ps = null;
			Connection con = null;
			Connection con1 = null;
			String query = "";
			
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

			if (act.getScanVchFrom().equalsIgnoreCase("FL3")) {
				query = " UPDATE bottling_unmapped.disliry_unmap_fl3 SET  fl11gatepass=?, "
						+ " fl11_date=? WHERE casecode=? and fl36_date is null and fl11_date is   null and etin=? " +
						" and date_plan=?";

			} else if (act.getScanVchFrom().equalsIgnoreCase("FL3A")) {
				query = " UPDATE bottling_unmapped.disliry_unmap_fl3a SET  fl11gatepass=?, "
						+ " fl11_date=? WHERE casecode=? and fl36_date is null and fl11_date is   null and etin=? " +
						"and  date_plan=?";
			}

			Date date1=null;
			try {
				con = ConnectionToDataBase.getConnection();
				con1 = ConnectionToDataBase.getConnection20_21();
				con.setAutoCommit(false);
				con1.setAutoCommit(false);
				ps = con1.prepareStatement(query);
				for (int i = 0; i < act.getGetVal().size(); i++) {
					FLB11_EOIDt  dt = (FLB11_EOIDt ) act
							.getGetVal().get(i);
					
					String datePlan=dt.getCasecode().trim().substring(16,22).trim();
					
				//	datePlan="20"+datePlan;
					 	 
					datePlan="20"+datePlan;
					datePlan=datePlan.substring(0,4)+"-"+datePlan.substring(4,6)+"-"+datePlan.substring(6);
					 date1=new SimpleDateFormat("yyyy-MM-dd").parse(datePlan);
					//System.out.println(datePlan+"="+date_plan);
					
					ps.setString(1, dt.getGatepass());
					ps.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));
					ps.setString(3,dt.getCasecode().trim().substring(26,dt.getCasecode().length()));
				
					ps.setString(4,dt.getCasecode().trim().substring(0,12));
					ps.setDate(5,Utility.convertUtilDateToSQLDate(date1));
					 ps.addBatch();
					/*if (j == 0) {
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(dt.getCasecode().substring(29,
										dt.getCasecode().length())
										+ " Casecode not found! ", dt.getCasecode()
										.substring(29, dt.getCasecode().length())
										+ " Casecode not found! "));
						// System.out.println("dt.getCasecode().substring(29,dt.getCasecode().length())==="+dt.getCasecode().substring(29,dt.getCasecode().length()));
					}*/
				 

				} 
				j = ps.executeBatch();
				 
				save = j.length;
				
				 //System.out.println("save size->"+save+"="+act.getGetVal().size()); 
				if (act.getGetVal().size() == save && save>0) {
					save = 0;
					String updtQr = " UPDATE distillery.gatepass_to_manufacturewholesale_20_21 SET vch_finalize='F' "
							+ " WHERE vch_gatepass_no='"
							+ act.getScanGatePassNo().trim()
							+ "' ";

					ps = con.prepareStatement(updtQr);
					save = ps.executeUpdate();

					query = "DELETE FROM distillery.fl11_dislery_casecode_20_21 where vch_gatepass_no ='"
							+ act.getScanGatePassNo().trim() + "' ";
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

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
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


		public boolean etin_check(String g) {

			Connection c1 = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			Map<String, String> m1 = new HashMap<String, String>();
			Map<String, String> m2 = new HashMap<String, String>();
			

			String q1 = "select count(a.etin), a.etin from"
					+ " (select substring(vch_casecode,1,12) as etin from distillery.fl11_dislery_casecode_20_21"
					+ " where vch_gatepass_no='" + g
					+ "')a group by a.etin order by a.etin;";

			String q2 = "select b.f*count(b.code_generate_through) as count, b.code_generate_through as etin from"
					+ " (select a.f, (select code_generate_through from distillery.packaging_details_20_21 where package_id=a.p) from"
					+ " (select dispatchd_box as f,int_pckg_id as p  from distillery.fl1_stock_trxn_20_21"
					+ " where vch_gatepass_no = '"
					+ g
					+ "') a)b group by b.code_generate_through, b.f order by etin";

			// System.out.println("second datatable==============="+query);
			try {
				c1 = ConnectionToDataBase.getConnection();

				ps = c1.prepareStatement(q1);
				rs = ps.executeQuery();
				while (rs.next()) {
					m1.put(rs.getString("etin"), rs.getString("count"));
					// System.out.println(rs.getString("etin")+","+
					// rs.getString("count"));

				}

				ps = c1.prepareStatement(q2);
				rs = ps.executeQuery();
				while (rs.next()) {
					m2.put(rs.getString("etin"), rs.getString("count"));
					// System.out.println(rs.getString("etin")+","+
					// rs.getString("count"));

				}

				

				if (m1.equals(m2)) {
					// System.out.println("Result: "+ m1.equals(m2));
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
					c1.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return false;

		}
		
		
		// delete query of table...............

		public void deleteData(FLB11_EOIAction act) {

			Connection con = null;
			PreparedStatement stmt = null;

			String query = " DELETE FROM distillery.fl11_dislery_casecode_20_21 WHERE vch_gatepass_no='"
					+ act.getScanGatePassNo().trim() + "'  ";
			int status = 0;
			try {
				con = ConnectionToDataBase.getConnection();
				stmt = con.prepareStatement(query);

				status = stmt.executeUpdate();
				if (status > 0) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Cancelled Successfully ",
									"Cancelled Successfully "));
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Not Cancelled ", "Not Cancelled "));
				}
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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


		
		
		
		
		
		

}
