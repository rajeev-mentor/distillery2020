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


import com.mentor.Datatable.Approved_application_for_eoi_export_dt;
import com.mentor.action.Approved_application_for_eoi_export_Action;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class Approved_application_for_eoi_export_impl {
	

	
	//=-==================================    login  ================================================
	
	public void getDetails(Approved_application_for_eoi_export_Action action){


		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionToDataBase.getConnection();

			
           String selQr =   
		 
		  "  SELECT int_app_id_f , vch_undertaking_name   FROM " +
		  "  public.dis_mst_pd1_pd2_lic     where vch_wrk_phon = '"+ResourceUtil.getUserNameAllReq()+"' " ;
	 
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();
			
			 
			while (rs.next()) {
				
				action.setDistillery_id(rs.getInt("int_app_id_f"));
				//action.setName_of_importing_unit(rs.getString("importing_unit_nm"));
				//action.setImport_order_date(Utility.convertSqlDateToUtilDate( rs.getDate("po_date")));
			}
     
			//System.out.println();
			
			
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
	}
	
	
	
//======================================================icd list  ==============================================	

	public void imorting_name_order_date(Approved_application_for_eoi_export_Action action){


		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionToDataBase.getConnection();

			
           String selQr =   
		 
		  "  SELECT  importing_unit_nm , po_date FROM " +
		  "   distillery.eoi_import_order_master   where seq_pk = "+action.getImport_order_no()+" " ;
	 
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();
			
			 
			while (rs.next()) {
				
				 
				action.setName_of_importing_unit(rs.getString("importing_unit_nm"));
				action.setImport_order_date(Utility.convertSqlDateToUtilDate( rs.getDate("po_date")));
			}
     
			//System.out.println();
			
			
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
	}
	
	
	public ArrayList getIcd_list(Approved_application_for_eoi_export_Action action) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	SelectItem item = new SelectItem();
	item.setLabel("--select--");
	item.setValue("");
	list.add(item);
	try {

		String query = "SELECT id,name  FROM licence.icd_master  order by id ";
		
		//System.out.println(" --getIcd_list--- "+query);

		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			item = new SelectItem();

			item.setValue(rs.getString("id"));
			item.setLabel(rs.getString("name"));

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


	
//================================= show brand detail ===========================================

public ArrayList brand_display_detail(Approved_application_for_eoi_export_Action action ) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
     int sr = 1 ; ;

	try {

		String query = 
				
	"	select  b.brand_name, c.package_name, a.etin,(a.no_of_bottles-coalesce(a.used_bottles,0)) as btl  from distillery.eoi_import_order_master_brand a,  "+
	"	distillery.brand_registration_20_21 b , distillery.packaging_details_20_21 c where a.brand_id = b.brand_id and a.package_id = c.package_id  " +
	"  and a.distillery_id = '"+action.getDistillery_id()+"' and masterseq_fk = '"+action.getImport_order_no()+"' order by b.brand_name  ";
				
			
		 conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			
			Approved_application_for_eoi_export_dt  dt = new Approved_application_for_eoi_export_dt();
			dt.setSrno_brand(sr);
			dt.setBrand_name(rs.getString("brand_name"));
			dt.setPackage_name(rs.getString("package_name")) ;
			dt.setEtin(rs.getString("etin")) ;
			dt.setBalance_qty(rs.getLong("btl"));
			
				
			sr++ ;
									
			list.add(dt);

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


//================================= export order detail  ===========================================

public ArrayList export_order_display_detail(Approved_application_for_eoi_export_Action action ) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
   int sr = 1 ; ;

	try {

		String query =
				
				         "	SELECT a.app_id, a.recv_date, a.brc_number, a.leo_number,a.created_date , " +
						"    sum(b.reqstd_bottles) as reqstd_bottles                                   " +
						"	FROM distillery.eoi_app_for_export_order a , distillery.eoi_app_for_export_order_brand b  " + 
						"    where a.int_dist_id = '"+action.getDistillery_id()+"'  and       " +
					    "    a.order_id = '"+action.getImport_order_no()+"' and a.app_id= b.app_id_fk group by          " +
						"    a.app_id, a.recv_date, a.brc_number, a.leo_number,a.created_date order by  a.created_date desc " ;

				
				
		//System.out.println("--export order===="+query);
		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();
		
		SimpleDateFormat formatter= new SimpleDateFormat("dd MMMM yyyy");
	
		
		

		while (rs.next()) {
			
			String reg_date =formatter.format(rs.getDate("created_date"));
			
			
			Approved_application_for_eoi_export_dt  dt = new Approved_application_for_eoi_export_dt();
			
			dt.setSrno_export(sr);
			dt.setApplication_no(rs.getInt("app_id"));
			dt.setApplication_date(reg_date) ;
			dt.setNo_of_bottles(rs.getLong("reqstd_bottles")) ;
			dt.setCeo_no(rs.getInt("leo_number"));
			dt.setBrc_no(rs.getInt("brc_number"));
			dt.setReciept_at_icd(rs.getDate("recv_date"));
				
			sr++;
									
			list.add(dt);

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


//==================================max id  eoi_app_for_export_order ====================================================


public int seq_pk_eoi_app_export() {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = " SELECT max(app_id) as id FROM distillery.eoi_app_for_export_order";
	int maxid = 0;
	try {
		con = ConnectionToDataBase.getConnection();
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			maxid = rs.getInt("id");
		}
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e
						.getMessage(), e.getMessage()));

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




//================================= import select item  list===========================================

public ArrayList import_list(Approved_application_for_eoi_export_Action action ) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	SelectItem item = new SelectItem();
	item.setLabel("--select--");
	item.setValue("");
	list.add(item);
	try {

		String query = "SELECT po_number , seq_pk  FROM " +
				" distillery.eoi_import_order_master where int_dist_id = '"+action.getDistillery_id()+"' order by seq_pk ";

		
		 
		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			item = new SelectItem();

			item.setValue(rs.getString("seq_pk"));
			item.setLabel(rs.getString("po_number"));

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

//========================================== save details  ==========================================

public boolean savedetails(Approved_application_for_eoi_export_Action act){

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 1;
	String query = ""; 
 int maxid = this.seq_pk_eoi_app_export();
	
	boolean flg=false;
	 try {

		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		 
		
			
		query   =    "  INSERT INTO distillery.eoi_app_for_export_order( "+
                   "  app_id,  pi_date, pi_number, pi_pdf, created_date, int_dist_id, order_id,user1_name,icd_id,puc_certificate , puc_no, esign_pdf ,esign_date,permit_no) "+
	                "  VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?);  ";
		
					
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1,maxid) ;
				pstmt.setDate(2, Utility.convertUtilDateToSQLDate(act.getValidity_demanded()));
				pstmt.setString(3, act.getPerforma_invoice_no());
				pstmt.setString(4, act.getUpload_performa_invoice());
				pstmt.setDate(5, Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setInt(6, act.getDistillery_id());
				pstmt.setInt(7, Integer.parseInt(act.getImport_order_no()));
				pstmt.setString(8, "Excise-DL-"+act.getDistillery_id());	
				pstmt.setInt(9, Integer.parseInt(act.getIcd_id()));
				pstmt.setString(10, act.getPuc_pdf());
				pstmt.setString(11, act.getPuc_no());
				pstmt.setString(12, act.getApproval_pdf());
				pstmt.setDate(13, Utility.convertUtilDateToSQLDate(act.getApproval_date()));
				pstmt.setString(14, act.getApproval_no());
				
				
				saveStatus = pstmt.executeUpdate();
				
				//System.out.println("====save status= pi details=1"+saveStatus);
			
			
		
		if (saveStatus > 0) {
				
			
			if(act.getBrand_detaiil_list().size()>0){
				 query =
							    "	UPDATE distillery.eoi_import_order_master_brand  " +
								" SET  used_bottles=? " +
								"  WHERE  distillery_id = ? and  etin = ? and masterseq_fk = ? "  ;
				
				for(int i=0;i<act.getBrand_detaiil_list().size();i++){
		
					Approved_application_for_eoi_export_dt dt = (Approved_application_for_eoi_export_dt) act.getBrand_detaiil_list().get(i);
					
					pstmt = conn.prepareStatement(query);
					
					 
					pstmt.setLong(1, dt.getRequested_qty());
					pstmt.setInt(2, act.getDistillery_id());
					pstmt.setString(3,dt.getEtin());
					pstmt.setInt(4, Integer.parseInt(act.getImport_order_no()));
								
					saveStatus = pstmt.executeUpdate();
					
					 
					
				}
		}
		}  
		if (saveStatus > 0) {
					
					
					if(act.getBrand_detaiil_list().size()>0){
						
				 query =	" INSERT INTO distillery.eoi_app_for_export_order_brand( "+
							" app_id_fk, etin, distillery_id, reqstd_bottles)         "+
							" VALUES (?, ?, ?, ?)  " ;
						        
						for(int i=0;i<act.getBrand_detaiil_list().size();i++){
									
							Approved_application_for_eoi_export_dt dt = (Approved_application_for_eoi_export_dt) act.getBrand_detaiil_list().get(i);
												
							pstmt = conn.prepareStatement(query);
							
							pstmt.setLong(1, maxid);
							pstmt.setString(2, dt.getEtin());
							pstmt.setInt(3, act.getDistillery_id());
							pstmt.setLong(4,dt.getRequested_qty());
										
							saveStatus = pstmt.executeUpdate();
							
							//System.out.println("save status==2="+saveStatus);	
							
					
						}
				}
				}
		
		if (saveStatus > 0) {
			conn.setAutoCommit(true);
			flg=true;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("SAVED SUCCESSFULLY !", "SAVED SUCCESSFULLY !"));
			act.reset();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("NOT SAVED !", "NOT SAVED !"));
			conn.rollback();

		}
	 }


	catch (SQLException e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(e.getMessage(), e.getMessage()));
		e.printStackTrace();
	}catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(e.getMessage(), e.getMessage()));
		e.printStackTrace();
	}

	finally {
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
return flg;

}






}
