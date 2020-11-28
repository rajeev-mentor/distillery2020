package com.mentor.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
import com.mentor.Datatable.GatepassForBottolingDatatable;
import com.mentor.action.GatepassForBottolingAction; 
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class GatepassForBottolingImpl {
	
	
	
	//======================get hidden method distillery lg================
	
	public String getSugarmill(GatepassForBottolingAction ac) {

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

				ac.setDist_name(rs.getString("vch_undertaking_name"));
				ac.setDistillery_id(rs.getInt("int_app_id_f"));
				ac.setAddress(rs.getString("vch_wrk_add"));
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
	
   //=====================list 

	public ArrayList fromliclistImpl(GatepassForBottolingAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		act.setFromflag(true);
		try {//@rvind
			String query = "SELECT distinct licence_no   from  "
					+ " distillery.mst_bottling_plan_19_20 where   int_distillery_id='"
					+ act.getDistillery_id() + "'  and vch_license_type= '"
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
	
	// =================== Get Quantity =======================

		public double getQuantity(GatepassForBottolingAction ac, String val) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "";
			ArrayList list = new ArrayList();

			double quantity = 0.0;
			try {

			/*	query = "SELECT b.openingbl,b.strength,b.openingal,a.received_bl ,a.consumed_bl,a.consumed_al,a.consumed_strength,a.recieved_al "
						+ " from distillery.distillery_spirit_store_detail b"
						+ " left outer join  distillery.spirit_vat a on b.int_id=a.vat_id "
						+ "and a.int_dist_id=b.int_distilleri_id "
						+ "where  b.int_id="
						+ Integer.parseInt(val)
						+ " and b.int_distilleri_id=" + ac.getDistillery_id();*/
				if(ac.getRadio().equalsIgnoreCase("S")){
				query =" SELECT a.received_bl ,a.consumed_bl,a.consumed_al,a.recieved_al  " +
					   " from distillery.distillery_spirit_store_detail b left outer join  " +
					   " distillery.spirit_vat a on b.int_id=a.vat_id and a.int_dist_id=b.int_distilleri_id where " +
					   " b.int_id="+Integer.parseInt(val)+" and b.int_distilleri_id=" + ac.getDistillery_id();
				}
				else if(ac.getRadio().equalsIgnoreCase("BL")){
					
				query ="SELECT recieve_bl as received_bl,consumed_bl as consumed_bl,consumed_al as consumed_al,recieve_al as recieved_al " +
						"from distillery.spirit_for_bottling where storage_id="+Integer.parseInt(val)+" and int_distillery_id=" + ac.getDistillery_id();
				}

                    
				conn = ConnectionToDataBase.getConnection();
				pstmt = conn.prepareStatement(query); 
				rs = pstmt.executeQuery();
				if (rs.next()) {
					
				 		ac.setQuantityFinal(rs.getInt("received_bl")- rs.getInt("consumed_bl"));
						ac.setQuantityFinalal(rs.getInt("recieved_al")- rs.getInt("consumed_al"));
						 	
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
		 // ======================= get Vat No. List ===============

		public ArrayList getVatList(GatepassForBottolingAction action,int date) {
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
			    if(action.getRadio()!=null){
			 	
	          if(action.getRadio().equalsIgnoreCase("BL")){
					
	        		query =" SELECT   b.storage_id as int_id,b.storage_desc as vch_tank_name  FROM " +
							" distillery.spirit_for_bottling b where " +
							" b.int_distillery_id =" + date+" ";
	        		
						pstmt = conn.prepareStatement(query); 
						rs = pstmt.executeQuery();
					while (rs.next()) {
						item = new SelectItem();
						item.setValue(rs.getString("int_id"));
						item.setLabel(rs.getString("vch_tank_name"));
						list.add(item);
					}
					
				}else if(action.getRadio().equalsIgnoreCase("S")){
					
					query = "SELECT   b.int_id ,b.vch_tank_name  FROM  distillery.distillery_spirit_store_detail b"
							+ " where b.int_distilleri_id= " + date
							+ " and (b.b_heavy_flag != 'T' or b.b_heavy_flag is null) and vat_def='S' "+
							 " union SELECT   b.int_id ,b.vch_tank_name  FROM  distillery.distillery_spirit_store_detail b"
							+ " where b.int_distilleri_id= " + date
							+ "   and vat_def='R'  ";
					 
						pstmt = conn.prepareStatement(query); 
						rs = pstmt.executeQuery();
					while (rs.next()) {
						item = new SelectItem();
						item.setValue(rs.getString("int_id"));
						item.setLabel(rs.getString("vch_tank_name"));
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
		
	 // ============================= save Data=======================

		public String saveData(GatepassForBottolingAction action) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int saveStatus = 0;
			int seq= this.getMaxId() + 1;
			try {

				String query =
						" INSERT INTO distillery.pd2_gatepass( "+
						" from_vat_no, transfer_bl, transfer_al,"+
						" vat_type, lic_type, lic_no, created_date, gt_date, int_id, distillery_id,qty_strength_befr,qty_strength_aftr,gt_no) "+
						" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
                  
				 
				conn = ConnectionToDataBase.getConnection();
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(query);
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                 
				
				pstmt.setInt(1, Integer.parseInt(action.getVatNo()));
				pstmt.setInt(2, action.getQuantityFinal_trnfr());
				pstmt.setInt(3, action.getQuantityFinalal_trnfr());
				 
				if(action.getRadio().equalsIgnoreCase("BL")){
					pstmt.setString(4, "BL");
				}
				else if(action.getRadio().equalsIgnoreCase("S")){
					pstmt.setString(4, "S");
				}
				if(action.getVch_from().equalsIgnoreCase("FL3")){
					pstmt.setString(5, "FL3");
				}
				else if(action.getVch_from().equalsIgnoreCase("FL3A")){
					pstmt.setString(5, "FL3A");
				}
				pstmt.setString(6, action.getVch_from_lic_no());
				pstmt.setDate(7,
						Utility.convertUtilDateToSQLDate(action.getCurrent_date()));
				pstmt.setDate(8,
						Utility.convertUtilDateToSQLDate(action.getGatepass_date()));
				
				pstmt.setInt(9, seq);
				pstmt.setInt(10, action.getDistillery_id());
				pstmt.setInt(11, action.getQty_strength_befr());
				pstmt.setInt(12, action.getQty_strength_aftr());
				pstmt.setString(13,action.getDistillery_id()+"PD25-2020-21-"+seq);
				 saveStatus = pstmt.executeUpdate();

				if (saveStatus > 0) {
					saveStatus = 0;

					query =" INSERT INTO distillery.vat_wastage( "+
						   " vat_no, vat_book_val_bl, vat_book_val_al,   vat_bef_transfer_bl,"+
						   " vat_bef_transfer_al, vat_bef_transfer_strength, vat_wastage_bl, vat_wastage_al ,"+
						   " txn_date, vat_type, unit_id,licno,ref_id,type)"+
						   " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?, ?,'PD25_WASTAGE')";
                  
					pstmt = conn.prepareStatement(query);

					pstmt.setInt(1, Integer.parseInt(action.getVatNo()));
					pstmt.setInt(2, action.getQuantityFinal());
					pstmt.setInt(3, action.getQuantityFinalal());
					 
					pstmt.setInt(4, action.getQuantityFinal_befr());
					pstmt.setInt(5, action.getQuantityFinalal_befr());
					pstmt.setInt(6, action.getQty_strength_befr());
					pstmt.setInt(7, action.getQuantityFinal_wast());
					pstmt.setInt(8, action.getQuantityFinalal_wast());
					 
					pstmt.setDate(9,Utility.convertUtilDateToSQLDate(action.getCurrent_date()));
					if(action.getRadio().equalsIgnoreCase("BL")){
						pstmt.setString(10, "BL");
					}
					else if(action.getRadio().equalsIgnoreCase("S")){
						pstmt.setString(10, "S");
					}
					pstmt.setInt(11, action.getDistillery_id());
					pstmt.setString(12, action.getVch_from_lic_no());
					pstmt.setInt(13, seq);
					 saveStatus = pstmt.executeUpdate();
				}

				if (saveStatus > 0) {
					saveStatus = 0;

					query =
				 
					"INSERT INTO distillery.spirit_vat(	recieved_al, received_bl,consumed_bl,  int_dist_id, vat_id, int_season_id, "
							+ "consumed_al)	VALUES (0,0,0,  ?, ?, (SELECT   sesn_id::int  FROM public.mst_season where  active = 'a'),   0) " +
							"ON CONFLICT ON CONSTRAINT spirit_vat_pkey "
							+ " do update set consumed_al= COALESCE(distillery.spirit_vat.consumed_al,0.0) + "
							+ action.getQuantityFinalal_trnfr()+action.getQuantityFinalal_wast()
						
							+ ","
							+ " consumed_bl= COALESCE(distillery.spirit_vat.consumed_bl,0.0) +"
							+ action.getQuantityFinal_trnfr()+action.getQuantityFinal_wast()+"";
                       
					
					
					pstmt = conn.prepareStatement(query);

					pstmt.setDouble(1, action.getDistillery_id());
					pstmt.setInt(2, Integer.parseInt(action.getVatNo()));
					//pstmt.setDouble(3, action.getQuantityFinalal_trnfr());
					//pstmt.setDouble(5, action.getStrength());
					//pstmt.setInt(2, action.getDistillery_id());
					//pstmt.setInt(3, Integer.parseInt(action.getVatNo()));

					// pstmt.setInt(1,action.getDissleryId());
					// pstmt.setInt(2,Integer.parseInt(action.getVatNo()));
					saveStatus = pstmt.executeUpdate();
				}
				if (saveStatus > 0) {

					conn.commit();
					ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,
							Constants.SAVED_SUCESSFULLY);
					action.reset();

				} else {

					// action.reset();
					ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
							Constants.NOT_SAVED);

				}
			} catch (Exception e) {
				ResourceUtil.addErrorMessage(e.getMessage(),e.getMessage());
				 
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
				String query = "SELECT max(int_id) maxid from distillery.pd2_gatepass ";

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
				String query = "SELECT max(unit_id) maxid from distillery.vat_wastage ";

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

		  //=============Aman========created date 20/01/2020========from line 481 to 623=========== //
		  
		//=======================Display Pd2 challan in new datatable===============================//
		
		  public ArrayList pd2_challan(GatepassForBottolingAction act)
		  {
			    act.setFlag(false);
				ArrayList list=null;
				Connection con=null;
				PreparedStatement pstmt=null;
				ResultSet rs = null;
				
				String getQr = "	SELECT from_vat_no,gt_no, transfer_bl, transfer_al, lic_no, gt_date,int_id FROM distillery.pd2_gatepass where distillery_id='"+act.getDistillery_id()+"'";
				try
				{
					list = new ArrayList();
					con = ConnectionToDataBase.getConnection();
					
					pstmt= con.prepareStatement(getQr);
					rs= pstmt.executeQuery();
					int i =1;
					
					while(rs.next())
					{
						GatepassForBottolingDatatable dt = new GatepassForBottolingDatatable();
						dt.setSrNo(i);
						dt.setGatepass_date(rs.getDate("gt_date"));
						dt.setVch_from_lic_no(rs.getString("lic_no"));
						dt.setVatNoList(rs.getString("from_vat_no"));
						dt.setQuantityFinal_trnfr(rs.getDouble("transfer_bl"));
						dt.setQuantityFinalal_trnfr(rs.getDouble("transfer_al"));
						dt.setInt_id(rs.getDouble("int_id"));
						dt.setGtno(rs.getString("gt_no"));
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

		  
		//=======================print report===============================//
		  
		public boolean printReport(GatepassForBottolingAction action, GatepassForBottolingDatatable dt) {
		{
			String mypath=Constants.JBOSS_SERVER_PATH+Constants.JBOSS_LINX_PATH;
			String relativePath=mypath+File.separator+"ExciseUp"+File.separator+"Distillery"+File.separator+"jasper";
			String relativePathImage=mypath+File.separator+"ExciseUp"+File.separator+"image";
			String relativePathpdf=mypath+File.separator+"ExciseUp"+File.separator+"Distillery"+File.separator+"pdf";
			JasperReport jasperReport = null;
			JasperPrint jasperPrint = null;
			PreparedStatement pst=null;
			Connection con=null;
			ResultSet rs=null;
			String reportQuery=null;
			boolean printFlag = false;
			
			try {
				con =ConnectionToDataBase.getConnection();
				reportQuery=" select b.vch_undertaking_name,a.transfer_bl,a.transfer_al,a.lic_no,a.gt_date,a.int_id,a.qty_strength_befr,a.qty_strength_aftr," +
				            "d.vat_book_val_al,d.vat_book_val_bl,d.vat_wastage_al,d.vat_wastage_bl,"+
						    " case when a.vat_type='BL' then 'Blend' else 'Spirit' end as vat_type," +
						    " case when a.vat_type='BL' then (select storage_desc from " +
						    " distillery.spirit_for_bottling where a.from_vat_no=storage_id ) " +
						    " else  (select vch_tank_name from distillery.distillery_spirit_store_detail where a.from_vat_no=int_id )  end as vat_ " +
						    " from distillery.pd2_gatepass a,dis_mst_pd1_pd2_lic b,distillery.vat_wastage d " +
						    " where a.distillery_id=b.int_app_id_f and a.int_id=d.unit_id::int  "+
						    " and a.int_id='"+dt.getInt_id()+"' ";
			
						
						
						/*
						"	SELECT from_vat_no, transfer_bl, transfer_al, lic_no, gt_date,int_id FROM distillery.pd2_gatepass distillery_id='"+action.getDistillery_id()+"' " +
								" AND from_vat_no='"+dt.getVatNoList()+"' AND lic_no='"+dt.getVch_from_lic_no()+"'";*/
				
				  pst=con.prepareStatement(reportQuery);
					
					 
				rs=pst.executeQuery();
				if(rs.next()){
					
					rs=pst.executeQuery();
					
				Map parameters = new HashMap();
				parameters.put("REPORT_CONNECTION", con);
				parameters.put("SUBREPORT_DIR", relativePath+File.separator);
				parameters.put("image", relativePathImage+File.separator);
				 
				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

				jasperReport = (JasperReport) JRLoader.loadObject(relativePath+File.separator+"Pd2_Gatepass.jasper");

				JasperPrint print = JasperFillManager.fillReport(jasperReport,parameters, jrRs);	
		 

				JasperExportManager.exportReportToPdfFile(print,relativePathpdf+File.separator+"Pd2_Gatepass"+dt.getInt_id()+".pdf");
				
				 dt.setPdfName("Pd2_Gatepass"+dt.getInt_id()+".pdf");
				action.setPdfName("Pd2_Gatepass"+dt.getInt_id()+".pdf");
				 dt.setPrintFlag(true);
			//	action.setPrintFlag(true);
				 
				 printFlag=true;
				}else{FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("No Data Found In Pd2_Gatepass", "No Data Found In Pd2_Gatepass")); 
				
			//	action.setPrintFlag(false);
				 printFlag=false;
				 dt.setPrintFlag(false);
				
				}
				 
				
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(rs!=null)rs.close();
					if(con!=null)con.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}

	     return printFlag;
		}
		}
		
}

		  
		  
		  

