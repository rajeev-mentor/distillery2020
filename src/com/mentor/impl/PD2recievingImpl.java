package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.mentor.Datatable.PD2recievingDt;
import com.mentor.action.PD2recievingAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class PD2recievingImpl {
	
	public String getSugarmill(PD2recievingAction ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			System.out.println("========hidden==in====");
			String queryList =  " SELECT x.app_id, x.distillery_id, x.license_district, x.license_no, x.vch_licence_type,               "+
					" x.distilery_district, x.unit_name, x.unit_adrs      "+
					" FROM                                                                                                  "+
					" (SELECT a.int_app_id as app_id, a.int_distillery_id as distillery_id, a.district as license_district, "+
					" a.vch_license_fl3a as license_no, a.vch_licence_type, b.vch_unit_dist as distilery_district,          "+
					" a.vch_unit_name as unit_name, a.vch_unit_addrs as unit_adrs "+
					" FROM licence.fl3a_fl1a a, public.dis_mst_pd1_pd2_lic b                              "+
					" WHERE a.int_distillery_id=b.int_app_id_f AND a.vch_lic_unit_type='D'                                  "+
					" AND a.vch_licence_type='FL3A' AND a.loginuser='"+ResourceUtil.getUserNameAllReq().trim() +"'          "+
					" UNION                                                                                                 "+
					" SELECT a.int_lic_id as app_id, a.int_distillery_id as distillery_id, a.district as license_district,  "+
					" a.vch_licence_no as license_no, a.vch_licence_type, b.vch_unit_dist as distilery_district,            "+
					" a.vch_unit_name as unit_name, a.vch_unit_addrs as unit_adrs "+
					" FROM licence.licence_entery_fl3_fl1 a, public.dis_mst_pd1_pd2_lic b                 "+
					" WHERE a.int_distillery_id=b.int_app_id_f AND a.vch_lic_unit_type='D'                                  "+
					" AND a.vch_licence_type='FL3' AND a.loginuser='"+ResourceUtil.getUserNameAllReq().trim() +"' )x ";
					
					
					
				/*	
					"SELECT int_distillery_id,vch_licence_no from  distillery.licence_entery_fl3_fl3a where login='"+ResourceUtil.getUserNameAllReq().trim()+"'";
					//+ ResourceUtil.getUserNameAllReq().trim();
*/			
			System.out.println("=====log="+queryList);
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();
             
			
			
			while (rs.next()) {
				ac.setDissteleryName((rs.getString("license_no")));
				ac.setDissleryId(rs.getInt("distillery_id"));
				ac.setHiddenFlag(false);
				
				//ac.setDisslryAdd(rs.getString("vch_wrk_add"));

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
	//==========================show data
	public ArrayList getData(PD2recievingAction ac) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 1;
		ac.setListFlagForPrint(false);


		
		/*String sql =" select a.from_vat_transfer_bl, a.from_vat_transfer_al, a.lic_no, a.created_date, a.gt_date,b.vch_tank_name from distillery.pd2_gatepass a ,"+
				    " distillery.distillery_spirit_store_detail b "+
				    " where     a.from_vat_no=b.int_id ";*/

		
		String sql ="SELECT from_vat_no, transfer_bl,gt_no, transfer_al,"+
		            "lic_type, lic_no, created_date, gt_date"+
			        " FROM distillery.pd2_gatepass where recv_flg is null";
		
		 
		
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				PD2recievingDt dt = new PD2recievingDt();
				dt.setSrno(i);
			    dt.setGate_dt(rs.getDate("gt_date"));
			    dt.setGatepass_no(rs.getString("gt_no"));
			  
			    dt.setAl(rs.getString("transfer_al"));
			    dt.setBl(rs.getString("transfer_bl"));
		
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
	
	// =================== Get Quantity =======================

		public double getQuantitySeconend(PD2recievingAction ac,String val) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "";
			ArrayList list = new ArrayList();

			double quantity = 0.0;
			try {

			 if(ac.getRadio().equalsIgnoreCase("BL")){
				
				query="select storage_id,coalesce(recieve_bl,0)recieve_bl,coalesce(recieve_al,0)recieve_al,storage_desc,coalesce(consumed_bl,0)consumed_bl,coalesce(consumed_al,0)consumed_al,strength " +
						"from distillery.fl3_3a_vat_master where vat_type='"+ac.getRadio()+"' and distillery_id='"+ac.getDissleryId()+"' " +
						" and lic_no='"+ac.getDissteleryName()+"' and storage_id="+ val;
				
				}
				
				else if(ac.getRadio().equalsIgnoreCase("BT")){
					

					query="select storage_id,recieve_bl,recieve_al,storage_desc,consumed_bl,consumed_al,strength " +
							"from distillery.fl3_3a_vat_master where vat_type='"+ac.getRadio()+"' and distillery_id='"+ac.getDissleryId()+"' " +
							" and lic_no='"+ac.getDissteleryName()+"' and storage_id="+ val;
				}
                    
				conn = ConnectionToDataBase.getConnection();
				pstmt = conn.prepareStatement(query);
System.out.println(query);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					ac.setToVatBl(rs.getInt("recieve_bl")- rs.getInt("consumed_bl"));
					 ac.setToVatAl(rs.getInt("recieve_al")- rs.getInt("consumed_al"));
					ac.setToVatSrength(((rs.getInt("recieve_al")- rs.getInt("consumed_al"))*100)/(rs.getInt("recieve_bl")- rs.getInt("consumed_bl")) );
					 
					ac.setListFlagquant(false);
					 
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
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("NO Data Found"));
					e.printStackTrace();
				}
			}
			return quantity;
		}
    
		
		// ======================= get Vat No. List ===============

		public ArrayList getToVat(PD2recievingAction action,String val) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList list = new ArrayList();
			SelectItem item = new SelectItem();
			item.setValue("");
			item.setLabel("--Select--");
			list.add(item);
			String queryB = "";
			String query = "";
			action.setListFlagFortovat(false);
			try {
				 if(val.equalsIgnoreCase("BL")){
					
					query ="select storage_id,recieve_bl,recieve_al,storage_desc,consumed_bl,consumed_al,strength " +
							"from distillery.fl3_3a_vat_master where  distillery_id='"+action.getDissleryId()+"' and  verified is true " +
							" and lic_no='"+action.getDissteleryName()+"' and vat_type='"+val+"'";
					}
                else if(val.equalsIgnoreCase("BT")){
					

            	query ="select storage_id,recieve_bl,recieve_al,storage_desc,consumed_bl,consumed_al,strength " +
						"from distillery.fl3_3a_vat_master where  distillery_id='"+action.getDissleryId()+"' and  verified is true " +
						" and lic_no='"+action.getDissteleryName()+"' and vat_type='"+val+"'";
				}
				
				 
				conn = ConnectionToDataBase.getConnection();
					pstmt = conn.prepareStatement(query);
					rs = pstmt.executeQuery();

				while (rs.next()) {
					item = new SelectItem();
					item.setValue(rs.getString("storage_id"));
					item.setLabel(rs.getString("storage_desc"));
					list.add(item);
				}

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("NO Data Found"));
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
		
		//===============================
		

		public void getViewdetaillist(PD2recievingAction ac,PD2recievingDt dt)
		{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			ArrayList list = new ArrayList();
		
		
			try
			{
				String query2 =" SELECT lic_no, gt_date, vat_type,int_id, distillery_id,transfer_al,transfer_bl,recieving_date FROM distillery.pd2_gatepass where gt_no='"+dt.getGatepass_no()+"'  ";

	          
                
				conn = ConnectionToDataBase.getConnection() ;
				ps = conn.prepareStatement(query2);
			
				rs = ps.executeQuery();
				
				if(rs.next())
				{
					ac.setDt_al(rs.getInt("transfer_al"));
					ac.setDt_bl(rs.getInt("transfer_bl"));
					ac.setGatapass_no_v(rs.getString("lic_no"));
					ac.setGatapass_v(rs.getString("gt_date"));
					if(rs.getString("vat_type").equalsIgnoreCase("BL")){
						ac.setRadio("BT");
						ac.setToVatList(this.getToVat(ac, "BT"))  ;
					}else if(rs.getString("vat_type").equalsIgnoreCase("S")){
						ac.setRadio("BL");ac.setToVatList(this.getToVat(ac, "BL"))  ;
					}
					 
		
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(conn!=null)conn.close();
					if(ps!=null)ps.close();
					if(rs!=null)rs.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
		}
		
		// ============================= save Data=======================

		public String saveData(PD2recievingAction action) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int saveStatus = 0;
			int seq=this.getMaxId() + 1;
			try {

				String query =
						" INSERT INTO distillery.pd2_receiving( "+
	                    " to_vat_no, to_vat_transfer_bl, to_vat_transfer_al, to_vat_transfer_strength,  "+
	                    "   created_date, int_id, distillery_id,vat_type,licno,gtno) "+
	                    " VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)";
                 
				conn = ConnectionToDataBase.getConnection();
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(query);
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                 
				pstmt.setInt(1, Integer.parseInt(action.getToVatId()));
				//pstmt.setInt(1, Integer.parseInt(action.getv));
				pstmt.setInt(2, action.getToVatBl_trnfr());
				pstmt.setInt(3, action.getToVatAl_trnfr());
				pstmt.setInt(4, action.getToVatSrength_trnfr());
				pstmt.setDate(5, Utility.convertUtilDateToSQLDate(action.getCurrent_date()));
				pstmt.setInt(6, seq);
				pstmt.setInt(7, action.getDissleryId());
				if(action.getRadio().equalsIgnoreCase("BL"))
				{
					pstmt.setString(8, "BL");
				}
				else if(action.getRadio().equalsIgnoreCase("BT"))
				{
					pstmt.setString(8, "BT");
				}

				  pstmt.setString(9,action.getDissteleryName());
				  pstmt.setString(10, action.getGatapass_no_v()) ;

				saveStatus = pstmt.executeUpdate();

				if (saveStatus > 0) {
					saveStatus = 0;

					query =" INSERT INTO distillery.vat_wastage( "+
						   " vat_no, vat_book_val_bl, vat_book_val_al,  vat_bef_transfer_bl,"+
						   " vat_bef_transfer_al, vat_bef_transfer_strength, vat_wastage_bl, vat_wastage_al, "+
						   " txn_date, unit_id,licno,ref_id,type)"+
						   " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,'DAILY_WASTAGE')";
					 
					pstmt = conn.prepareStatement(query);

					pstmt.setInt(1, Integer.parseInt(action.getToVatId()));
					pstmt.setInt(2, action.getToVatBl());
					pstmt.setInt(3, action.getToVatAl());
				//	pstmt.setInt(4, action.getToVatSrength());
					pstmt.setInt(4, action.getToVatBl_bfr_trns());
					pstmt.setInt(5, action.getToVatAl_bfr_trns());
					pstmt.setInt(6, action.getToVatSrength_bfr_trns());
					pstmt.setInt(7, action.getToVatBl_dly_wst());
					pstmt.setInt(8, action.getToVatAl_dly_wst());
			//		pstmt.setInt(10, action.getToVatSrength_dly_wst());
					pstmt.setDate(9,Utility.convertUtilDateToSQLDate(action.getCurrent_date()));
					pstmt.setInt(10, action.getDissleryId());
					 pstmt.setString(11,action.getDissteleryName());
				  pstmt.setInt(12, seq);
				 saveStatus = pstmt.executeUpdate();
				}
				if (saveStatus > 0) {
					saveStatus = 0;

					query =" INSERT INTO distillery.vat_wastage( "+
						   " vat_no, vat_book_val_bl, vat_book_val_al,  vat_bef_transfer_bl,"+
						   " vat_bef_transfer_al, vat_bef_transfer_strength, vat_wastage_bl, vat_wastage_al,"+
						   " txn_date, unit_id,licno,ref_id,type)"+
						   " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?,?,'TRANSFER_WASTAGE')";
					 
					pstmt = conn.prepareStatement(query);

					pstmt.setInt(1, Integer.parseInt(action.getToVatId()));
					pstmt.setInt(2, action.getToVatBl());
					pstmt.setInt(3, action.getToVatAl());
				//	pstmt.setInt(4, action.getToVatSrength());
					pstmt.setInt(4, action.getToVatBl_bfr_trns());
					pstmt.setInt(5, action.getToVatAl_bfr_trns());
					pstmt.setInt(6, action.getToVatSrength_bfr_trns());
					pstmt.setInt(7, action.getToVatBl_trnfr_wst());
					pstmt.setInt(8, action.getToVatAl_trnfr_wst());
				//	pstmt.setInt(10, action.getToVatSrength_trnfr_wst());
					pstmt.setDate(9,
							Utility.convertUtilDateToSQLDate(action.getCurrent_date()));
				
					pstmt.setInt(10, action.getDissleryId());
					 pstmt.setString(11,action.getDissteleryName());
				  pstmt.setInt(12, seq);
					saveStatus = pstmt.executeUpdate();
				}
				if (saveStatus > 0) {
					String queryspiritvat = "update  distillery.fl3_3a_vat_master set recieve_bl=COALESCE(recieve_bl,0)+"
							+ action.getToVatBl_trnfr()
							+ "  ,recieve_al=COALESCE(recieve_al,0)+"
							+ action.getToVatAl_trnfr()
							+ "  where distillery_id ='"	+ action.getDissleryId()	+ "' and lic_no='"+action.getDissteleryName()+"' and storage_id="
							+ Integer.parseInt(action.getToVatId());

					 

					pstmt = conn.prepareStatement(queryspiritvat);

					saveStatus = pstmt.executeUpdate();
					}
				
				if (saveStatus > 0) {
					String queryspiritvat = "update  distillery.pd2_gatepass set recv_flg=true where distillery_id ='"	+ action.getDissleryId()	+ "' and gt_no='"+action.getGatapass_no_v()+"' ";

					 

					pstmt = conn.prepareStatement(queryspiritvat);

					saveStatus = pstmt.executeUpdate();
					}
				if (saveStatus > 0) {

					conn.commit();
					ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY,
							Constants.SAVED_SUCESSFULLY);
					action.reset();

				} else {

					  action.reset();
					ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
							Constants.NOT_SAVED);

				}
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Data Not saved"));
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
				String query = "SELECT max(int_id) maxid from distillery.pd2_receiving ";

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
		
		public int getMaxId1() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int maxid = 0;

			try {
				String query = "SELECT max(unit_id::int) maxid from distillery.vat_wastage ";

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
		//==========================save show data
		public ArrayList getsaveData(PD2recievingAction ac) {
			ArrayList list = new ArrayList();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int i = 1;
			//ac.setListFlagForPrint(false);


		/*	
			 select a.to_vat_transfer_al,a.to_vat_transfer_bl,b.vch_tank_name from distillery.pd2_receiving a ,"+
					    " distillery.distillery_spirit_store_detail b "+
					    " where      a.to_vat_no=b.int_id  ";
			 */
			 
			 String sql =" select a.to_vat_transfer_al,a.to_vat_transfer_bl,b.storage_desc ,created_date,gtno " +
			 		"from distillery.pd2_receiving a ,"+
			             " distillery.fl3_3a_vat_master b  where      a.to_vat_no=b.storage_id  ";

			
			
			
		 
			
			try {
				con = ConnectionToDataBase.getConnection();
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {

					PD2recievingDt dt = new PD2recievingDt();
					dt.setSrno_s(i);
				    dt.setVat_name_s(rs.getString("storage_desc"));
				    dt.setAl_s(rs.getString("to_vat_transfer_al"));
				    dt.setBl_s(rs.getString("to_vat_transfer_bl"));
				    dt.setRecv_dt(rs.getDate("created_date"));
				    dt.setGatepass_no(rs.getString("gtno"));
				    
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
