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
import com.mentor.Datatable.ImportOrderEntryEOIDt;
import com.mentor.action.ImportOrderEntryEOIAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class ImportOrderEntryEOIImpl {
	
	public String getDistDetails(ImportOrderEntryEOIAction act) {

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
        
		System.out.println("==getDistDetails====" + queryList);
			
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				act.setDistId(rs.getInt("int_app_id_f"));
				act.setDistNm(rs.getString("vch_undertaking_name"));
				act.setDistAdd(rs.getString("vch_wrk_add"));

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


	public ArrayList getCountryList() {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("NA");
		list.add(item);
		try {

			String query = "SELECT int_country_id, vch_country_name FROM public.country_mst order by vch_country_name";
			
			
			System.out.println("==getCountryList====" + query);

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				item = new SelectItem();

				item.setValue(rs.getString("int_country_id"));
				item.setLabel(rs.getString("vch_country_name"));

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
	//---------------------------------Brand List-------------------------------
	
	public ArrayList ShowBrandList(ImportOrderEntryEOIAction act) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList al = new ArrayList();
		int i = 1;
		conn = ConnectionToDataBase.getConnection();

		

		String sql =" select a.brand_id,a.brand_name,b.package_name,b.package_id,coalesce(b.code_generate_through,'NA') as etin   "+
				    " from distillery.brand_registration_20_21 a,distillery.packaging_details_20_21 b  "+
				    " where a.brand_id=b.brand_id_fk and a.domain in ('Export(Other Country)','EXP(Other Country)','EXP') "+
				    " and a.distillery_id='"+act.getDistId()+"' ";
 
		 System.out.println("===vShowBrandList====" + sql);
		try {

			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			// action.setFlag("T");
			while (rs.next()) {

				ImportOrderEntryEOIDt dt = new ImportOrderEntryEOIDt();

				dt.setBrandNm(rs.getString("brand_name"));
				dt.setBrandId(rs.getInt("brand_id"));
				dt.setPackageId(rs.getInt("package_id"));
				dt.setPackageNm(rs.getString("package_name"));
				dt.setEtin(rs.getString("etin")); 
				act.setFlag(true);

				// System.out.println("===		dt.getEtin()====" + 		dt.getEtin());
		
				dt.setSr_n(i);
				al.add(dt);

				i++;

			}

			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();

			if (rs != null)
				conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;

	}
	
	// ============================= save Data=======================

			public String saveData(ImportOrderEntryEOIAction act) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				int saveStatus = 0;
				int saveStatus1 = 0;
                act.setSeqId(getMaxId());
        		int seq1 = getMaxId1();
                //System.out.println("====update========"+action.getUnit_id());
				try {

					String query =" INSERT INTO distillery.eoi_import_order_master(  "+
							      " seq_pk, int_dist_id, country_id, importing_unit_nm, po_date, po_number, po_pdf, created_date)  "+
							      " VALUES (?, ?, ?, ?, ?, ?, ?, ?)  ";
					
					
					conn = ConnectionToDataBase.getConnection();
					conn.setAutoCommit(false);
					pstmt = conn.prepareStatement(query);
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	                 
					pstmt.setInt(1, act.getSeqId());
					pstmt.setInt(2, act.getDistId());
					pstmt.setInt(3, Integer.parseInt(act.getCountry_id()));
					pstmt.setString(4, act.getUnit_nm());
					pstmt.setDate(5, Utility.convertUtilDateToSQLDate(act.getValidtyDt()));
					pstmt.setInt(6, act.getPurchase_ordrno());
					pstmt.setString(7, act.getRecordFile());
					pstmt.setDate(8, Utility.convertUtilDateToSQLDate(act.getDate()));
					saveStatus = pstmt.executeUpdate();
					if(saveStatus > 0){
						for (int i = 0; i < act.getBrandList().size(); i++) {
							ImportOrderEntryEOIDt dt = (ImportOrderEntryEOIDt) act.getBrandList().get(i); 
							 // System.out.println("8457589");
							//  System.out.println("8457589"+dt.isSelect() );
							 // System.out.println("8457589"+dt.getQuantity());
						if (dt.getQuantity() >0) {
						 query =" INSERT INTO distillery.eoi_import_order_master_brand( " +
								"	seq_pk, masterseq_fk, brand_id, package_id, etin, distillery_id, no_of_bottles, size, no_of_box) " +
								"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";

							System.out.println("====updateappDetail========" + query);
					
							pstmt = conn.prepareStatement(query);
							pstmt.setInt(1, seq1);
							pstmt.setInt(2, act.getSeqId());
							pstmt.setInt(3, dt.getBrandId());
							pstmt.setInt(4, dt.getPackageId());
							pstmt.setString(5, dt.getEtin());
							pstmt.setInt(6, act.getDistId());
							pstmt.setInt(7, (int)dt.getQuantity());
							pstmt.setInt(8, (int)dt.getSize());
							pstmt.setInt(9,  dt.getNo_of_box());
							// pstmt.setInt(3, dt.getApp_id1());
						
							saveStatus1 = pstmt.executeUpdate();
							seq1 = seq1 + 1;
			              
						}
					
					
				
					}
					}
					if (saveStatus1 > 0) {

						conn.commit();
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"   Data Saved successfully!!! ",
										"  Data Saved successfully!!! "));
			/*			FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"  Registration done successfully. Your Registration ID is "+act.getUnit_id()+" !!! ",
										"  Registration done successfully. Your Registration ID is "+act.getUnit_id()+" !!!"));*/
						act.reset();
					     
					
					} else {

						// action.reset();
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"  Error message 'Data not saved'  All  Fields are Mandatory!!!. Error message 'Data not saved' All  Fields are Mandatory!!! ",
										"  Error message 'Data not saved'  All  Fields are Mandatory!!!. Error message 'Data not saved' All  Fields are Mandatory!!!  "));

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
			
			//------------------------------checkkk
			

	        public boolean checkDate(ImportOrderEntryEOIAction act) {

				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				boolean pass = false;
				String query = "";

				try {

					query ="SELECT  int_dist_id FROM distillery.eoi_import_order_master where int_dist_id='"+act.getDistId()+"'";
					
					System.out.println("checkDate==="+query);
					conn = ConnectionToDataBase.getConnection();
					pstmt = conn.prepareStatement(query);

					rs = pstmt.executeQuery();
					if (rs.next()) {

						// act.setCheckReg(rs.getInt("registration_id"));
						act.setDistilleryId(rs.getInt("int_dist_id"));
						pass = true;

					}
					else {

						pass = false;
					}

				} catch (Exception e) {

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
				return pass;

			}

				
	
			//------------------------------sumbit Orede purchase
			//---------------------------------Brand List-------------------------------
			
			public ArrayList ShowsaveDate(ImportOrderEntryEOIAction act) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ArrayList al = new ArrayList();
				int i = 1;
				conn = ConnectionToDataBase.getConnection();

				

				String sql =
					"	SELECT distinct a.seq_pk, a.int_dist_id, a.country_id,c.vch_country_name, a.importing_unit_nm, a.po_date, a.po_number, a.po_pdf, " +
					" a.created_date,sum(b.no_of_bottles) as no_of_bottles ,sum(b.size) as size ,sum(b.no_of_box) as no_of_box  "+
					"	FROM distillery.eoi_import_order_master a , distillery.eoi_import_order_master_brand b "+
					"	, public.country_mst c  "+
					"	where   a.seq_pk=b.masterseq_fk  and  a.country_id=c.int_country_id  and a.int_dist_id='"+act.getDistId()+"' " +
					" group by a.seq_pk, a.int_dist_id, a.country_id,c.vch_country_name, a.importing_unit_nm, a.po_date, a.po_number,a.po_pdf, a.created_date "+
							"order by a.seq_pk desc  ";
		 
				 System.out.println("===vShowBrandList====" + sql);
				try {

					pstmt = conn.prepareStatement(sql);

					ResultSet rs = pstmt.executeQuery();

					// action.setFlag("T");
					while (rs.next()) {

						ImportOrderEntryEOIDt dt = new ImportOrderEntryEOIDt();

						dt.setCountryNm(rs.getString("vch_country_name"));
						dt.setUnitNM(rs.getString("importing_unit_nm"));
						dt.setPurchaseNo(rs.getString("po_number"));
						dt.setCrDate(rs.getDate("created_date"));
						dt.setOrderCopy(rs.getString("po_pdf"));
						dt.setValidDate(rs.getDate("po_date"));
						dt.setNoOfbottles(rs.getInt("no_of_bottles"));
						dt.setShow_size(rs.getInt("size"));
						dt.setShow_no_of_box(rs.getInt("no_of_box"));
						
						dt.setSr_n1(i);
						al.add(dt);

						i++;

					}

					if (conn != null)
						conn.close();
					if (pstmt != null)
						pstmt.close();

					if (rs != null)
						conn.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

				return al;

			}
			
			
			
			// ==============get MAX Id====================

						public int getMaxId() {
							Connection conn = null;
							PreparedStatement pstmt = null;
							ResultSet rs = null;
							int maxid = 0;

							try {
								String query = "SELECT max(seq_pk) maxid from distillery.eoi_import_order_master ";

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
						
						
						// ==============get MAX Id====================

						
						public int getMaxId1() {
							Connection conn = null;
							PreparedStatement pstmt = null;
							ResultSet rs = null;
							int maxid = 0;

							try {
								String query = "SELECT max(seq_pk) maxid from distillery.eoi_import_order_master_brand ";

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
							return maxid + 1;
						}
						
						// ////////------------------------------------validtion-----------------------//////////
						 public boolean checkbrewery(ImportOrderEntryEOIAction ac) {

								Connection conn = null;
								PreparedStatement pstmt = null;
								ResultSet rs = null;
								boolean pass = false;
								String query = "";

								try {

									query ="select approve_flag from distillery.reg_of_distilleryasexpunit where distillery_id="+ac.getDistId()+" ";
									
									System.out.println("checkDate==="+query);
									conn = ConnectionToDataBase.getConnection();
									pstmt = conn.prepareStatement(query);

									rs = pstmt.executeQuery();
									if (rs.next()) {
										pass = true;

									}
									else {

										pass = false;
									}

								} catch (Exception e) {

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
								return pass;

							}


}
