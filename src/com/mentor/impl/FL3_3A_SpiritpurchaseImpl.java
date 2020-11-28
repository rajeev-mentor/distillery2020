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

import com.mentor.Datatable.FL3_3A_SpiritpurchaseDt;
import com.mentor.Datatable.GatepassForBottolingDatatable;
import com.mentor.action.FL3_3A_SpiritpurchaseAction;
import com.mentor.action.GatepassForBottolingAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class FL3_3A_SpiritpurchaseImpl {
	
	//------------------------------------hedden method----------------------

	public String getDetails(FL3_3A_SpiritpurchaseAction ac) {

		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;

		try {
			con = ConnectionToDataBase.getConnection();

			String queryList = " SELECT x.app_id, x.distillery_id, x.license_district, x.license_no, x.vch_licence_type,               "+
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

		  
			pstmt = con.prepareStatement(queryList);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ac.setDistilleri_nm(rs.getString("license_no"));
				ac.setDistilleri_id(rs.getInt("distillery_id"));
				ac.setHiddenFlag(false);
				if(rs.getString("vch_licence_type").equalsIgnoreCase("FL3")){
					ac.setVch_from("FL3");
				}
				else if(rs.getString("vch_licence_type").equalsIgnoreCase("FL3A")){
					ac.setVch_from("FL3A");
				}

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
	
///------------------
  
	

	//-----------------------------dist list
	
	public ArrayList getState(FL3_3A_SpiritpurchaseAction act) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String SQl =" select  districtid, description FROM public.district order by  description";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("description"));
				item.setValue(rs.getString("districtid"));
				//act.setDist_flg(false);
				list.add(item);
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
	
	//-----------------------------outside state

	public ArrayList getoutsideState(FL3_3A_SpiritpurchaseAction act) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String SQl =" select  int_state_id, vch_state_name FROM public.state_ind order by  vch_state_name";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_state_name"));
				item.setValue(rs.getString("int_state_id"));
				//act.setState_flg(false);
				list.add(item);
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
	
	//---------------------------country list
	
	

	public ArrayList getcountrilist(FL3_3A_SpiritpurchaseAction act) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String SQl =" select  int_country_id, vch_country_name FROM public.country_mst order by  vch_country_name";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_country_name"));
				item.setValue(rs.getString("int_country_id"));
			//	act.setContri_flg(false);
				list.add(item);
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
	
	
	//-------------------------------------FL3_3A List
	
	 //=====================list 

	 
		//---------------------------------------------get spirit vat
		
		public ArrayList getSpiritVat(FL3_3A_SpiritpurchaseAction action ) {
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
				 	 
					
					query ="select storage_id,recieve_bl,recieve_al,storage_desc,consumed_bl,consumed_al,strength " +
							"from distillery.fl3_3a_vat_master where  distillery_id='"+action.getDistilleri_id()+"' " +
							" and lic_no='"+action.getDistilleri_nm()+"' and vat_type='S' and  verified is true";
					 
				System.out.println("====getSpiritVat=="+query);
				 
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
		
		
		//-------------------------spirir type
		
		
		public ArrayList getSpritType() {
			ArrayList list = new ArrayList();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			SelectItem item = new SelectItem();
			item.setLabel("--select--");
			item.setValue("");
			list.add(item);
			try {
				String query = " SELECT int_spirit_type_id, vch_spirit_name  FROM distillery.mst_spirit_type  order by vch_spirit_name ";

				conn = ConnectionToDataBase.getConnection();
				pstmt = conn.prepareStatement(query);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					item = new SelectItem();
					item.setValue(rs.getString(1));
					item.setLabel(rs.getString(2));
					list.add(item);
				}

			} catch (Exception e) {
				e.printStackTrace();

				try {
					if (conn != null)
						conn.close();
					if (pstmt != null)
						pstmt.close();
					if (rs != null) {
						rs.close();
					}

				} catch (Exception e44) {
					e44.printStackTrace();
				}
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (pstmt != null)
						pstmt.close();
					if (rs != null) {
						rs.close();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return list;
		}
		


		public double getQuantity(FL3_3A_SpiritpurchaseAction ac, String val) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "";
			ArrayList list = new ArrayList();

			double quantity = 0.0;
			try {

		
					
				query =
						"SELECT (coalesce(recieve_bl,0)-coalesce(consumed_bl,0)) as quant_bl, "
								+ " (coalesce(recieve_al,0)-coalesce(consumed_al,0)) as al,strength "
								+ " from distillery.fl3_3a_vat_master where distillery_id='"+ac.getDistilleri_id()+"' and lic_no='"+ac.getDistilleri_nm()+"' and storage_id="+val;

              System.out.println("=getQuantity====="+query);
				conn = ConnectionToDataBase.getConnection();
				pstmt = conn.prepareStatement(query); 
				rs = pstmt.executeQuery();
				if (rs.next()) {
					
				 		ac.setQuntity_bl_b(rs.getInt("quant_bl"));
						ac.setQuntity_al_b(rs.getInt("al"));
						 	
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
		
		
		
		//---------------------------save method-------------------------------
		

		public String saveData(FL3_3A_SpiritpurchaseAction action) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int saveStatus = 0;
			action.setVat_max_id(getMaxId());
			 

            //System.out.println("====update========"+action.getUnit_id());
			try {

				String query =" INSERT INTO distillery.fl3_3a_spiritpurchase( "+
						"		from_vat_no, transfer_bl, transfer_al, vat_type, lic_type, lic_no, created_date, int_id, distillery_id,"+
						"		 qty_strength_befr, qty_strength_aftr, distict_id, state_id, country_id, spirit_type) "+
						"		VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				//System.out.println("quer=================="+query);
				conn = ConnectionToDataBase.getConnection();
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(query);
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                 
				
				pstmt.setInt(1, Integer.parseInt(action.getVatid()));
				pstmt.setInt(2, action.getQuantity_bl_recv());
				pstmt.setInt(3, action.getQuantity_al_recv());
				//System.out.println("vat no  ==============="+action.getVatNo());
				if(action.getRadio().equalsIgnoreCase("UP")){
					pstmt.setString(4, "UP");
					pstmt.setInt(12, action.getDist_id());
					pstmt.setInt(13,0);
					pstmt.setInt(14, 0);
				}
				else if(action.getRadio().equalsIgnoreCase("OUP")){
					pstmt.setString(4, "OUP");
					pstmt.setInt(12, 0);
					pstmt.setInt(13, action.getState_id());
					pstmt.setInt(14, 0);
				}
				else if(action.getRadio().equalsIgnoreCase("OC")){
					pstmt.setString(4, "OC");
					pstmt.setInt(12, 0);
					pstmt.setInt(13, 0);
					pstmt.setInt(14, action.getContri_id());
				}
				if(action.getVch_from().equalsIgnoreCase("FL3")){
					pstmt.setString(5, "FL3");
				}
				else if(action.getVch_from().equalsIgnoreCase("FL3A")){
					pstmt.setString(5, "FL3A");
				}
				pstmt.setString(6, action.getVch_from_lic_no());
				pstmt.setDate(7,Utility.convertUtilDateToSQLDate(new Date()));
				//pstmt.setDate(8,Utility.convertUtilDateToSQLDate(action.getGatepass_date()));
				
			    pstmt.setInt(8, action.getVat_max_id());
				pstmt.setInt(9, action.getDistilleri_id());
				pstmt.setInt(10, action.getQty_strength_befr());
				pstmt.setInt(11, action.getQty_strength_aftr());
				pstmt.setInt(15, Integer.parseInt(action.getSpritTypeId()));

				saveStatus = pstmt.executeUpdate();
				if (saveStatus > 0) {
					saveStatus = 0;

					query =" INSERT INTO distillery.vat_wastage( "+
						   " vat_no, vat_book_val_bl, vat_book_val_al, vat_bef_transfer_bl, vat_bef_transfer_al, vat_bef_transfer_strength, "+
						   "  vat_wastage_bl, vat_wastage_al, txn_date, vat_type, unit_id,licno,ref_id,type) "+
						   "  VALUES ('"+Integer.parseInt(action.getVatid())+"', '"+action.getQuntity_bl_b()+"', '"+action.getQuntity_al_b()+"'," +
						   		" '"+action.getQuantity_bl_befr()+"', '"+action.getQuantity_al_befr()+"', '"+action.getQty_strength_befr()+"'," +
						   				" '"+action.getQuantity_bl_wast()+"', '"+action.getQuantity_al_wast()+"', '"+Utility.convertUtilDateToSQLDate(new Date())+"', " +
						   						" '"+action.getRadio()+"','"+action.getDistilleri_id()+"', '"+action.getDistilleri_nm()+"','"+action.getWast_max_id()+"','PURCHASE_WASTAGE')";
						
                  
					//System.out.println("vat_wastage_fl3_3a====="+query);
					pstmt = conn.prepareStatement(query);

			 saveStatus = pstmt.executeUpdate();
				}
				
				if (saveStatus > 0) {
				String queryspiritvat = "update  distillery.fl3_3a_vat_master set recieve_bl=COALESCE(recieve_bl,0)+"
						+ action.getQuantity_bl_recv()
						+ "  ,recieve_al=COALESCE(recieve_al,0)+"
						+ action.getQuantity_al_recv()
						+ "  where distillery_id ='"	+ action.getDistilleri_id()	+ "' and lic_no='"+action.getDistilleri_nm()+"' and storage_id="
						+ Integer.parseInt(action.getVatid());

				 

				pstmt = conn.prepareStatement(queryspiritvat);

				saveStatus = pstmt.executeUpdate();
				}
				if (saveStatus > 0) {

					conn.commit();
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"  Record saved successfully !!! ",
									"  Record saved successfully !!!"));
					action.reset();
				     //action.setFinalflg(true);
				
				} else {

					// action.reset();
					ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
							Constants.NOT_SAVED);

				}
			} catch (Exception e) {
				FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
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
				String query = "SELECT max(int_id) maxid from distillery.fl3_3a_spiritpurchase ";

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
			return maxid+1;
		}
				
				public int getMaxId1() {
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					int maxid = 0;

					try {
						String query = "SELECT max(unit_id) maxid from distillery.vat_wastage_fl3_3a ";

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
					return maxid+1;
				}
				
				
				
	//---------------------------------show save data------------------------
				

				  public ArrayList fl3_fl3asavedata(FL3_3A_SpiritpurchaseAction act)
				  {
					    //act.setFlag(false);
						ArrayList list=null;
						Connection con=null;
						PreparedStatement pstmt=null;
						ResultSet rs = null;
						
						String getQr = " SELECT from_vat_no, transfer_bl, transfer_al, vat_type, lic_type, lic_no, " +
								       " created_date,  int_id, qty_strength_befr, qty_strength_aftr" +
								       "	FROM distillery.fl3_3a_spiritpurchase where lic_no='"+act.getDistilleri_nm()+"'";
						try
						{
							list = new ArrayList();
							con = ConnectionToDataBase.getConnection();
							
							pstmt= con.prepareStatement(getQr);
							System.out.println(getQr);
							rs= pstmt.executeQuery();
							int i =1;
							
							while(rs.next())
							{
								FL3_3A_SpiritpurchaseDt dt = new FL3_3A_SpiritpurchaseDt();
						        dt.setSrno(i);
						        dt.setInt_id(rs.getInt("int_id"));
						        dt.setLic_no(rs.getString("lic_no"));
						        dt.setLic_type(rs.getString("lic_type"));
						        dt.setVat_type(rs.getString("vat_type"));
						        dt.setTranfr_bl(rs.getString("transfer_bl"));
						        dt.setTrnfr_al(rs.getString("transfer_al"));
						       
								list.add(dt);
							    i++;
								
							}
							
						}
						catch(Exception e){
							e.printStackTrace();
						}
						
						finally
						{
							try
							{
								if(pstmt!=null) pstmt.close();
								if(con!=null) con.close();
								if(rs!=null) rs.close();
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
						return list;
					  
				  }

}
