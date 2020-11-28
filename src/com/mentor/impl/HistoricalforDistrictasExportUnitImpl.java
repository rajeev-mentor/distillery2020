package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem; 



import com.mentor.Datatable.HistoricalforDistrictasExportUnitdt;

import com.mentor.action.HistoricalforDistrictasExportUnitAction;


import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class HistoricalforDistrictasExportUnitImpl {

	
	
	
	
	public String getdetails(HistoricalforDistrictasExportUnitAction ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = "SELECT int_app_id_f,vch_undertaking_name,vch_wrk_add  from  " +
					"dis_mst_pd1_pd2_lic where vch_wrk_phon='"+ ResourceUtil.getUserNameAllReq().trim()+"'";
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

	
	//---------------------------------show data---------------------*/
	
		public ArrayList showData(HistoricalforDistrictasExportUnitAction act)  {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ArrayList al = new ArrayList();
			int i = 1;
			String sql=null;
			conn = ConnectionToDataBase.getConnection();
			
			if(act.getRadio().equalsIgnoreCase("S"))
			
			{	
					sql = " select distinct  b.save_flg,coalesce(b.getpass_no,'NA') as getpass_no,     "+
						" (select vch_country_name FROM public.country_mst where int_country_id::text=a.export_district)as country,      "+
					    " a.int_dist_id,a.vch_gatepass_no,a.dt_date,a.vch_from,a.vch_to                                       "+
					    " from distillery.gatepass_to_manufacturewholesale_20_21 a left outer join                                       "+
					    " distillery.historical_for_distillery_export_as_unit b on b.getpass_no=a.vch_gatepass_no                        "+
					    " where  vch_to='EOI' and int_dist_id='"+act.getDistillery_id()+"' and b.save_flg is null "
					    		+ "and b.leo_date is null and b.brc_date is null  and b.leo_no is null and dt_date<='2020-10-10' " +
					    " order by a.dt_date desc ";                            
					
				}
			else if(act.getRadio().equalsIgnoreCase("BRC"))
			   {
				      sql = " select distinct b.save_flg,coalesce(b.getpass_no,'NA') as getpass_no,     "+
							" (select vch_country_name FROM public.country_mst where int_country_id::text=a.export_district)as country,      "+
						    " a.int_dist_id,a.vch_gatepass_no,a.dt_date,a.vch_from,a.vch_to                                       "+
						    " from distillery.gatepass_to_manufacturewholesale_20_21 a left outer join                                       "+
						    " distillery.historical_for_distillery_export_as_unit b on b.getpass_no=a.vch_gatepass_no                        "+
						    " where  vch_to='EOI' and int_dist_id='"+act.getDistillery_id()+"' and b.leo_date is not null and b.brc_date is null and dt_date<='2020-10-10' " +
						    " order by a.dt_date desc ";   
				
				
				
			}
			else if(act.getRadio().equalsIgnoreCase("A"))
			{
				
				  sql = 
						            " select distinct b.save_flg,coalesce(b.getpass_no,'NA') as getpass_no,    "+
									" (select vch_country_name FROM public.country_mst where int_country_id::text=a.export_district)as country,      "+
									" a.int_dist_id,a.vch_gatepass_no,a.dt_date,a.vch_from,a.vch_to                                       "+
									" from distillery.gatepass_to_manufacturewholesale_20_21 a left outer join                                       "+
									" distillery.historical_for_distillery_export_as_unit b on b.getpass_no=a.vch_gatepass_no                        "+
									" where  vch_to='EOI' and int_dist_id='"+act.getDistillery_id()+"' and b.save_flg='T' and   " +
									" b.leo_date is not null  and b.brc_date is not null order by a.dt_date desc ";
										 
			}
			else
			{
				  sql =" ";
				
				
			}
			System.out.println("===showData====" + sql);

			try {
				
				pstmt = conn.prepareStatement(sql);

				ResultSet rs = pstmt.executeQuery();

				// action.setFlag("T");
				while (rs.next()) {
					HistoricalforDistrictasExportUnitdt dt = new HistoricalforDistrictasExportUnitdt();

					
				dt.setGatapass_no(rs.getString("vch_gatepass_no"));
				dt.setGatepass_dt(rs.getDate("dt_date"));
					dt.setFrom(rs.getString("vch_from"));
					dt.setTo(rs.getString("country"));
				
					//System.out.println("------------getpass_no-------"+dt.getGatapas_chk());
					if(rs.getString("vch_gatepass_no").equalsIgnoreCase("NA"))
					{
						dt.setSave_flg("T");
						//act.setSave_flg("T");
						//System.out.println("------------getpass_no-------"+"T");
					}else{ 
						//System.out.println("------------getpass_no-------"+"F");
						dt.setSave_flg("F");
						//act.setSave_flg("F");
					}
					act.setFlag(true);
					dt.setSr_no(i);
				
					i++;
					al.add(dt);

					

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
		
		//---------------------------------View Detail---------------------*/
		
		public ArrayList viewdetail(HistoricalforDistrictasExportUnitAction act,HistoricalforDistrictasExportUnitdt dt)  {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ArrayList al = new ArrayList();
			int i = 1;
			conn = ConnectionToDataBase.getConnection();
			
			String sql =" select b.brand_name, int_brand_id,size,dispatchd_bottl,dispatchd_box    " +
					" from distillery.fl1_stock_trxn_20_21 a,distillery.brand_registration_20_21 b   " +
					"  where int_dissleri_id='"+act.getDistillery_id()+"'   " +
					"   and vch_gatepass_no='"+dt.getGatapass_no()+"' and a.int_brand_id=b.brand_id   ";

							//" order by Description";
			System.out.println("===viewDetail====" + sql);

			try {
				
				pstmt = conn.prepareStatement(sql);

				ResultSet rs = pstmt.executeQuery();

				// action.setFlag("T");
				while (rs.next()) {
					HistoricalforDistrictasExportUnitdt dt1 = new HistoricalforDistrictasExportUnitdt();

					
				dt1.setBrnad_id(rs.getInt("int_brand_id"));
				dt1.setBrand(rs.getString("brand_name"));
				dt1.setSize(rs.getInt("size"));
				dt1.setBottel(rs.getInt("dispatchd_bottl"));
				dt1.setBox(rs.getInt("dispatchd_box"));
				act.setGatapassno(dt.getGatapass_no());
				
			
					dt1.setSr_no1(i);
				
					i++;
					al.add(dt1);

					act.setViewflg("T");

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

	
			//-----------------------Save Data----------------------------------*/

			public String SaveData(HistoricalforDistrictasExportUnitAction act) {
				
				Connection conn = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				PreparedStatement pstmt1 = null;
				ResultSet rs = null;
				int saveStatus = 0;
				int saveStatus1 = 0;
				
				try {

					conn = ConnectionToDataBase.getConnection();
					SimpleDateFormat showDate = new SimpleDateFormat("dd-MM-yyyy");
					String displayDate = showDate.format(Utility.convertUtilDateToSQLDate(new Date()));

					String selQr =      " Select * from distillery.historical_for_distillery_export_as_unit where getpass_no='"+act.getGatapassno()+"' ";
					
					
					pstmt = conn.prepareStatement(selQr);
					rs=pstmt.executeQuery();
					if(rs.next())
					
					{
					
						String update = " update distillery.historical_for_distillery_export_as_unit " +
								       " set leo_date=?, leo_no=?,leo_no_of_bottel=?,created_dt=?,leo_pdf=?,getpass_no=?," +
								       " distillery_id=?,save_flg=?,currency=?,type=? where getpass_no='"+act.getGatapassno()+"'  ";
			
			
			String update2 = " update distillery.historical_for_distillery_export_as_unit " +
					" set riceipt_date=?, brc_no=?, "+
		       		" brc_no_of_bottel=?, created_dt=?,brc_date=?,brc_pdf=?,getpass_no=?,distillery_id=?," +
		       		" save_flg=?,currency=?,amount=?,type=? where getpass_no='"+act.getGatapassno()+"' ";
		       			
		
			 
			 

			    if(act.getRadio().equalsIgnoreCase("S"))
			 {
				pstmt = conn.prepareStatement(update);
				pstmt.setDate(1, Utility.convertUtilDateToSQLDate(act.getLeo_date()));
				pstmt.setString(2, act.getLeo_number());
				pstmt.setInt(3, act.getLeo_no_of_bottel());
				pstmt.setDate(4,Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setString(5,act.getRecordFile());
				pstmt.setString(6, act.getGatapassno());
				pstmt.setInt(7, act.getDistillery_id());
				pstmt.setString(8, "T");
				pstmt.setInt(9, act.getCurrency_id());
				pstmt.setString(10, act.getRadio());
				saveStatus = pstmt.executeUpdate();
				pstmt=null;
				
				if (saveStatus > 0) 
				{
					
					String query1 = " update distillery.gatepass_to_manufacturewholesale_20_21 set leo_date=? " +
									" where vch_gatepass_no=? and int_dist_id=?  ";
						
			 
		        pstmt = conn.prepareStatement(query1);
				
		        pstmt.setDate(1,Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setString(2, act.getGatapassno());
				pstmt.setInt(3, act.getDistillery_id());
				
				
				saveStatus1 = pstmt.executeUpdate();
					
				}
			}
			else if(act.getRadio().equalsIgnoreCase("BRC"))
			{
				
				
				pstmt = conn.prepareStatement(update2);
				
				pstmt.setDate(1,Utility.convertUtilDateToSQLDate( act.getRiceipt_date()));
				pstmt.setInt(2, act.getBrc_no());
			    pstmt.setInt(3,act.getBrc_no_bottel());
				pstmt.setDate(4,Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setDate(5,Utility.convertUtilDateToSQLDate(act.getBrc_dt()));
			    pstmt.setString(6,act.getRecordFile1());
				pstmt.setString(7, act.getGatapassno());
			    pstmt.setInt(8, act.getDistillery_id());
				pstmt.setString(9, "T");
				pstmt.setInt(10, act.getCurrency_id());
			    pstmt.setInt(11, act.getAmount());
				pstmt.setString(12, act.getRadio());
				
				saveStatus = pstmt.executeUpdate();
				pstmt=null;
				if (saveStatus > 0) 
				{
					
					String query1 = " update distillery.gatepass_to_manufacturewholesale_20_21 set brc_date=? " +
									" where vch_gatepass_no=? and int_dist_id=?  ";
						
			 
				 pstmt = conn.prepareStatement(query1);
				
				pstmt.setDate(1,Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setString(2, act.getGatapassno());
				pstmt.setInt(3, act.getDistillery_id());
				
				
				saveStatus1 = pstmt.executeUpdate();
						
						
						
						
					}
			}
					}		else
					
					{
					
					String query =      " INSERT INTO distillery.historical_for_distillery_export_as_unit (" +
							       		" leo_date, leo_no ," +
							       		" leo_no_of_bottel,created_dt,leo_pdf,getpass_no,distillery_id,save_flg,currency,type)" +
							       		" VALUES (?, ?,?, ?, ?, ?, ?, ?, ?, ?)";
					
					
					String query2 = " INSERT INTO distillery.historical_for_distillery_export_as_unit (" +
				       		" riceipt_date, brc_no,   " +
				       		" brc_no_of_bottel, created_dt,brc_date,brc_pdf,getpass_no,distillery_id,save_flg,currency,amount,type)" +
				       		" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";		
				
					 
					 conn = ConnectionToDataBase.getConnection();

					    if(act.getRadio().equalsIgnoreCase("S"))
					 {
						pstmt = conn.prepareStatement(query);
						pstmt.setDate(1, Utility.convertUtilDateToSQLDate(act.getLeo_date()));
						pstmt.setString(2, act.getLeo_number());
						pstmt.setInt(3, act.getLeo_no_of_bottel());
						pstmt.setDate(4,Utility.convertUtilDateToSQLDate(new Date()));
						pstmt.setString(5,act.getRecordFile());
						pstmt.setString(6, act.getGatapassno());
						pstmt.setInt(7, act.getDistillery_id());
						pstmt.setString(8, "T");
						pstmt.setInt(9, act.getCurrency_id());
						pstmt.setString(10, act.getRadio());
						saveStatus = pstmt.executeUpdate();
						pstmt=null;
						
						if (saveStatus > 0) 
						{
							
							String query1 = " update distillery.gatepass_to_manufacturewholesale_20_21 set leo_date=? " +
											" where vch_gatepass_no=? and int_dist_id=?  ";
								
					 
				        pstmt = conn.prepareStatement(query1);
						
				        pstmt.setDate(1,Utility.convertUtilDateToSQLDate(new Date()));
						pstmt.setString(2, act.getGatapassno());
						pstmt.setInt(3, act.getDistillery_id());
						
						
						saveStatus1 = pstmt.executeUpdate();
							
						}
					}
					else if(act.getRadio().equalsIgnoreCase("BRC"))
					{
						
						
						pstmt = conn.prepareStatement(query2);
						
						pstmt.setDate(1,Utility.convertUtilDateToSQLDate( act.getRiceipt_date()));
						pstmt.setInt(2, act.getBrc_no());
					    pstmt.setInt(3,act.getBrc_no_bottel());
						pstmt.setDate(4,Utility.convertUtilDateToSQLDate(new Date()));
						pstmt.setDate(5,Utility.convertUtilDateToSQLDate(act.getBrc_dt()));
					    pstmt.setString(6,act.getRecordFile1());
						pstmt.setString(7, act.getGatapassno());
					    pstmt.setInt(8, act.getDistillery_id());
						pstmt.setString(9, "T");
						pstmt.setInt(10, act.getCurrency_id());
					    pstmt.setInt(11, act.getAmount());
						pstmt.setString(12, act.getRadio());
						
						saveStatus = pstmt.executeUpdate();
						pstmt=null;
						if (saveStatus > 0) 
						{
							
							String query1 = " update distillery.gatepass_to_manufacturewholesale_20_21 set brc_date=? " +
											" where vch_gatepass_no=? and int_dist_id=?  ";
								
					 
						 pstmt = conn.prepareStatement(query1);
						
						pstmt.setDate(1,Utility.convertUtilDateToSQLDate(new Date()));
						pstmt.setString(2, act.getGatapassno());
						pstmt.setInt(3, act.getDistillery_id());
						
						
						saveStatus1 = pstmt.executeUpdate();
							
						}
					}
					}
					
					
					
					 
					
			
					if (saveStatus1 > 0) {
						
						
		              
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_INFO,
										" Data Saved Successfully !! ",
										"Data Saved Successfully !!"));
						
						act.close();
						
						
					} else {
						
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_INFO,
										" Data Not Saved!! ", "Data Not Saved !!"));

					}

				}
					

				catch (Exception e) {
					e.printStackTrace();

				}

				finally {
					try {

						if (conn != null)
							conn.close();
						if (con != null)
							con.close();
						if (pstmt != null)
							pstmt.close();
						if (pstmt1 != null)
							pstmt1.close();
						if (rs != null)
							rs.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return "";

			}

			//---------------------------------max ID---------------------------------//
			
			public int getStorageMax() {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				int maxid = 0;
				
				try {
					String query = " SELECT coalesce(max(int_id),0)+1  as maxid  from distillery.historical_for_distillery_export_as_unit ";

					conn = ConnectionToDataBase.getConnection();
					pstmt = conn.prepareStatement(query);

					rs = pstmt.executeQuery();
					if (rs.next()) {
						maxid = rs.getInt("maxid");
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
				return maxid+1;
			}
				
			public String updateFile(HistoricalforDistrictasExportUnitAction act,String url)
			{

				Connection con = null;
				PreparedStatement ps = null;
				PreparedStatement ps2 = null;
				int stat = 0;
				try {
					con = ConnectionToDataBase.getConnection();
					con.setAutoCommit(false);
				
					int i=0;
					 
						 System.out.println("112211");
							i=0;
							String updtQr = "";
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (con != null)
							con.close();
						if (ps != null)
							ps.close();
						if (ps2 != null)
							ps2.close();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				return "";
			}
			
			////=-------------------------GetpassNo Show-----------////////////////
			
	public String GetpassNo(HistoricalforDistrictasExportUnitAction act,
					HistoricalforDistrictasExportUnitdt dt) {

				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					String query = " select vch_gatepass_no from distillery.gatepass_to_manufacturewholesale_20_21" +
								   " where vch_gatepass_no='"+act.getGatapassno()+"'";
						
			
					
					con = ConnectionToDataBase.getConnection();
					pstmt = con.prepareStatement(query);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						
						act.setGetpass_no(rs.getString("vch_gatepass_no"));
						

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

//////////------------------------------------chkgetpass-------------------------//////////

		public String chkgetpass(HistoricalforDistrictasExportUnitAction act,
					HistoricalforDistrictasExportUnitdt dt) {

				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					String query =
						"	select b.getpass_no,b.leo_date, b.leo_no, b.riceipt_date, b.brc_no, b.leo_no_of_bottel, b.brc_no_of_bottel,       "+
						"	b.brc_date,b.leo_pdf, b.brc_pdf ,                                                                                 "+
						"	coalesce((select currency_nm  FROM public.currency_list where curency_id=b.currency),'NA') as   currency_nm,      "+
						"	coalesce(b.amount,0) as amount from                                                                               "+
						"	distillery.historical_for_distillery_export_as_unit b where b.getpass_no='"+dt.getGatapass_no()+"'                   "+
						"	and b.distillery_id='"+act.getDistillery_id()+"' ";
							
						/*	
							" select leo_date, leo_no, riceipt_date, brc_no, leo_no_of_bottel, brc_no_of_bottel, " +
									" brc_date,leo_pdf, brc_pdf ," +
									"coalesce((select currency_nm  FROM public.currency_list where curency_id=b.currency),'NA') as   currency_nm," +
									"coalesce(amount,0) as amount from  distillery.historical_for_distillery_export_as_unit b" +
								   " where getpass_no='"+dt.getGatapass_no()+"' and distillery_id='"+act.getDistillery_id()+"' ";
						*/
					System.out.println("chkgetpass===="+query);
					
					con = ConnectionToDataBase.getConnection();
					pstmt = con.prepareStatement(query);
					
		 
					 
					rs = pstmt.executeQuery();
					if (rs.next()) {
						
						 
					act.setLeo_date(rs.getDate("leo_date"))	;
					act.setLeo_number(rs.getString("leo_no"))	;
					act.setRiceipt_date(rs.getDate("riceipt_date"))	;
					act.setBrc_no(rs.getInt("brc_no"))	;
					act.setLeo_no_of_bottel(rs.getInt("leo_no_of_bottel"))	;
					act.setBrc_no_bottel(rs.getInt("brc_no_of_bottel"))	;
					act.setBrc_dt(rs.getDate("leo_date"))	;
					act.setRecordFile(rs.getString("leo_pdf"))	;
					act.setRecordFile1(rs.getString("brc_pdf"))	;
					act.setCurrency_name(rs.getString("currency_nm"));
					act.setAmount(rs.getInt("amount"));

					}  else {
						
						act.setLeo_date(null)	;
						act.setLeo_number("")	;
						act.setRiceipt_date(null)	;
						act.setBrc_no(0)	;
						act.setLeo_no_of_bottel(0)	;
						act.setBrc_no_bottel(0)	;
						act.setBrc_dt(null)	;
						act.setRecordFile("")	;
						act.setRecordFile1("")	;
						act.setCurrency_name("NA");
						act.setAmount(0);
						
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
//---------------------Rajeev

		public ArrayList getCurrencyList() {
			ArrayList list = new ArrayList();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			SelectItem item = new SelectItem();
			item.setLabel("--select--");
			item.setValue("NA");
			list.add(item);
			try {

				String query = "SELECT curency_id, currency_nm FROM public.currency_list";
				
				
				System.out.println("==getCountryList====" + query);

				conn = ConnectionToDataBase.getConnection();
				pstmt = conn.prepareStatement(query);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					item = new SelectItem();

					item.setValue(rs.getString("curency_id"));
					item.setLabel(rs.getString("currency_nm"));

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



}
