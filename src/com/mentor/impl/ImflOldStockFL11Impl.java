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

import com.mentor.Datatable.ImflOldStockFL11Datatable;
import com.mentor.action.ImflOldStockFl11Action;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;


 

public class ImflOldStockFL11Impl {

	// ===================get distillery details=================================

	public String getDetails(ImflOldStockFl11Action act) {
		
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;

		try {
			con = ConnectionToDataBase.getConnection();
			
			String queryList=	"  select  vch_app_id_f , brewery_nm , vch_reg_address ," +
								"  vch_reg_mobile FROM public.bre_mst_b1_lic" +
								"  where vch_reg_mobile='"+ResourceUtil.getUserNameAllReq()+"' ";
			

			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				 
				act.setBreId(rs.getInt("vch_app_id_f"));
				

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
	
	
	//============================get from license list===============================
	
	
	public ArrayList fromLicListImpl(ImflOldStockFl11Action act)
	{

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		try {
			String query = 	" SELECT DISTINCT licence_no  FROM bwfl.mst_bottling_plan_of_oldstock " +
							" WHERE int_brewery_id ='"+act.getBreId()+"'  " +
							" AND vch_license_type = '"+act.getVch_from()+"' ";

			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);
			
			//System.out.println("query------from license--------"+query);
			
			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				item.setValue(rs.getString("licence_no"));
				item.setLabel(rs.getString("licence_no"));

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
	
	public ArrayList toliclistImpl1a(ImflOldStockFl11Action act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("NA");
		list.add(item);
		try {
			String query = "SELECT vch_license_fl1a FROM licence.fl3a_fl1a " +
					" WHERE int_brewery_id='"+act.getBreId()+"' " +
					" AND vch_licence_type='"+act.getVch_to()+"' and vch_lic_unit_type='B'";

			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				// item.setValue(rs.getInt("int_state_id"));
				item.setValue(rs.getString("vch_license_fl1a"));
				item.setLabel(rs.getString("vch_license_fl1a"));

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
	 
	
	public ArrayList displaylistImpl(ImflOldStockFl11Action act, String yr)
	{

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;		
		int i = 1;

		try {
			
if(yr.equalsIgnoreCase("2019")) {
	selQr=	" SELECT z.qnt_ml_detail,z.flg, z.brand_name, z.package_name, z.vch_lic_type, z.vch_lic_no, z.box_size, "+
			" z.brnd_id, z.pckg_id, z.avlbottle, z.avlbox  "+
			" FROM  "+
			" (  "+
			" SELECT d.qnt_ml_detail,1 as flg, a.package_name, a.package_id as pckg_id,c.vch_lic_no,c.vch_lic_type, a.duty, a.adduty, " +
			" c.int_brand_id as brnd_id, b.brand_name, "+
			" c.int_pckg_id, c.int_stock-c.int_dispatched as avlbottle, d.box_size, "+
			" ROUND(((c.int_stock-c.int_dispatched)/d.box_size)) as avlbox  "+
			" FROM distillery.packaging_details_19_20 a, distillery.brand_registration_19_20 b,  "+
			" bwfl.boxing_stock_19_20 c, bwfl.box_size_details d  "+
			" WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.int_brand_id  "+
			" AND a.package_id=c.int_pckg_id AND b.brand_id=c.int_brand_id  "+
			" AND c.int_dissleri_id='"+ act.getBreId()+ "'   "+
			" AND c.vch_lic_type in ('FL3','FL3A') AND c.int_stock-c.int_dispatched >0  "+
			" AND a.quantity=d.qnt_ml_detail)z  "+
			" ORDER BY z.brand_name, z.package_name ";
	
}else {
	selQr=	" SELECT z.qnt_ml_detail,z.flg, z.brand_name, z.package_name, z.vch_lic_type, z.vch_lic_no, z.box_size, "+
			" z.brnd_id, z.pckg_id, z.avlbottle, z.avlbox  "+
			" FROM  "+
			" (  "+
			" SELECT d.qnt_ml_detail,1 as flg, a.package_name, a.package_id as pckg_id,c.vch_lic_no,c.vch_lic_type, a.duty, a.adduty, " +
			" c.int_brand_id as brnd_id, b.brand_name, "+
			" c.int_pckg_id, c.int_stock-c.int_dispatched as avlbottle, d.box_size, "+
			" ROUND(((c.int_stock-c.int_dispatched)/d.box_size)) as avlbox  "+
			" FROM distillery.packaging_details_20_21 a, distillery.brand_registration_20_21 b,  "+
			" bwfl.boxing_stock_20_21 c, bwfl.box_size_details d  "+
			" WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.int_brand_id  "+
			" AND a.package_id=c.int_pckg_id AND b.brand_id=c.int_brand_id  "+
			" AND c.int_dissleri_id='"+ act.getBreId()+ "'   "+
			" AND c.vch_lic_type in ('FL3','FL3A') AND c.int_stock-c.int_dispatched >0  "+
			" AND a.quantity=d.qnt_ml_detail)z  "+
			" ORDER BY z.brand_name, z.package_name ";
}
			 


			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);
			
			//System.out.println("selQr----------display list-------"+selQr);

			rs = ps.executeQuery();
			while (rs.next()) 
			{
				ImflOldStockFL11Datatable dt = new ImflOldStockFL11Datatable();
				
				dt.setInt_brand_id(rs.getInt("brand_id"));
				dt.setInt_pckg_id(rs.getInt("pack_id"));
				dt.setVch_brand(rs.getString("brand_name"));
				dt.setInt_bottle_avaliable(rs.getInt("avlbottle"));
				dt.setPckg_name(rs.getString("package_name"));
				dt.setDb_duty(rs.getString("vch_lic_type"));
				dt.setDb_add_duty(rs.getString("vch_lic_no"));
				dt.setSize(rs.getInt("box_size"));
				dt.setBoxAvailable(rs.getInt("avlbox"));
				
				dt.setSlno(i);

				
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
	
	 
	
	//=============================save data====================================
	
	
	
	public String saveMethodImpl(ImflOldStockFl11Action act)
	{
		Connection con = null;
		PreparedStatement ps = null, ps2 = null, ps2D = null, ps3 = null, ps4 = null, ps5 = null;
		 
	 String insQr = "";
		int status = 0 ;
	  try {

			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);

 
			
			

			for (int i = 0; i < act.displaylist.size(); i++) {
				ImflOldStockFL11Datatable dt = (ImflOldStockFL11Datatable) act.getDisplaylist().get(i);	
				 
					 
						status = 0;
					 
						insQr=	" INSERT INTO bwfl.fl1_stock_trxn_verification( " +
								" int_brewery_id, vch_lic_type, int_brand_id, int_pckg_id,vch_lic_no, " +
								" avl_bottl, avl_box , size ,created_dt,rad ) " +
								" VALUES (?, ?, ?, ?, ?, ?, ?, ?,?) ";
						 
						ps5 = con.prepareStatement(insQr);

						ps5.setInt(1, act.getBreId());
						ps5.setString(2, dt.getDb_duty());
						ps5.setString(5, dt.getDb_add_duty());
						ps5.setInt(3, (dt.getInt_brand_id()));
						ps5.setInt(4, (dt.getInt_pckg_id()));
						 						
						ps5.setInt(6, dt.getInt_bottle_avaliable());
						ps5.setInt(7, dt.getBoxAvailable());
						ps5.setString(10, dt.getVch_from());
						ps5.setInt(8, dt.getSize()); 
						ps5.setDate(9,Utility.convertUtilDateToSQLDate(new Date()));
						status = ps5.executeUpdate();
					 
						 
			}
			
			 
			if (status > 0) {
				con.commit();
				 
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,Constants.SAVED_SUCESSFULLY);
				
			}

			else {
				con.rollback();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,Constants.NOT_SAVED);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally 
		{
			try 
			{
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

			} catch (Exception e) 
			{
				e.printStackTrace();			}
		}

		return "";
	}

	
	//================================get details in second datatable=========================
	
	
	
	public ArrayList getWholeSaleManufactureList(ImflOldStockFl11Action act)
	{

		ArrayList list = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

	/*	String query2 = " SELECT distinct a.int_dist_id, a.vch_distillary_name, a.vch_distillary_address, " +
						" a.vch_gatepass_no, c.code_generate_through, a.dt_date, a.vch_from, a.vch_to, " +
						" a.vch_from_lic_no, a.vch_to_lic_no, a.vch_bond, a.curr_date, a.int_max_id, " +
						" a.db_total_duty, a.db_total_additional_duty, b.dispatchd_box, b.dispatchd_bottl, " +
						" COALESCE(a.vch_finalize,'N') as vch_finalize  " +
						" FROM distillery.gatepass_to_manufacturewholesale_imfl_old_stock a, " +
						" distillery.fl1_stock_trxn_imfl_old_stock b, distillery.packaging_details c  " +
						" WHERE a.int_dist_id=b.int_dissleri_id  AND a.vch_gatepass_no=b.vch_gatepass_no  " +
						" AND  b.int_pckg_id=c.package_id AND b.int_brand_id=c.brand_id_fk " +
						" AND a.int_dist_id='"+act.getBreId()+"'  " +
						" ORDER BY a.dt_date desc";*/
		
		String query=" SELECT distinct a.bre_id, a.vch_bre_name, a.vch_bre_address, "+
					 " a.vch_gatepass_no, c.code_generate_through, a.dt_date, a.vch_from, a.vch_to, "+
					 " a.vch_from_lic_no, a.vch_to_lic_no, a.vch_bond, a.curr_date, a.int_max_id, "+
					 " a.db_total_duty, a.db_total_additional_duty, b.dispatchd_box, b.dispatchd_bottl,"+ 
					 " COALESCE(a.vch_finalize,'N') as vch_finalize  "+
	
					 " FROM bwfl.gatepass_to_districtwholesale_2017_18_old_stock a, 	 "+				
					 " bwfl.fl1_stock_trxn_old_stock_17_18 b, "+
					 " distillery.packaging_details c  "+
	

					" WHERE	a.bre_id=b.int_brewery_id  "+
					" AND a.vch_gatepass_no=b.vch_gatepass_no  "+
					" AND  b.int_pckg_id=c.package_id "+
					" AND b.int_brand_id=c.brand_id_fk "+
					" AND a.bre_id='"+act.getBreId()+"'  "+
					" ORDER BY a.dt_date desc ";

		
		
		try {
			list = new ArrayList();
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);

			//System.out.println("query-----second datatable---------"+query);
			rs = ps.executeQuery();
			int i = 1;
			while (rs.next()) {
				ImflOldStockFL11Datatable dt = new ImflOldStockFL11Datatable();
				dt.setSno(i);
				dt.setBreId(rs.getInt("bre_id"));
				dt.setVch_bre_name(rs.getString("vch_bre_name"));
				dt.setVch_bre_address(rs.getString("vch_bre_address"));
				dt.setVch_gatepass_no(rs.getString("vch_gatepass_no"));
				dt.setDt_date(rs.getDate("dt_date"));
				dt.setVch_from(rs.getString("vch_from"));
				dt.setVch_to(rs.getString("vch_to"));
				dt.setVch_from_lic_no(rs.getString("vch_from_lic_no"));
				dt.setVch_to_lic_no(rs.getString("vch_to_lic_no"));				
				dt.setVch_bond(rs.getString("vch_bond"));
				dt.setCurr_date(rs.getDate("curr_date"));
				dt.setInt_max_id(rs.getInt("int_max_id"));
				dt.setDb_total_duty(rs.getDouble("db_total_duty"));
				dt.setDb_total_additional_duty(rs.getDouble("db_total_additional_duty"));
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
	
	
	//===========================print draft report==============================
	
	
	
}
