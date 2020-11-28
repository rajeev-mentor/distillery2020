package com.mentor.impl;

import java.io.File;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.mentor.Datatable.EoiExportOrderFrorwardingDT;
import com.mentor.action.EoiExportOrderFrorwardingAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class EoiExportOrderFrorwardingImpl {
	
	//=-==================================    login  ================================================
	
	public void getDetails(EoiExportOrderFrorwardingAction action){


		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionToDataBase.getConnection();

			
           String selQr =   
		 
		  "  SELECT int_app_id_f , vch_undertaking_name , importing_unit_nm  FROM " +
		  "  public.dis_mst_pd1_pd2_lic , distillery.eoi_import_order_master   where vch_wrk_phon = '"+ResourceUtil.getUserNameAllReq()+"' " ;
	 
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();
			
			 
			while (rs.next()) {
				
				action.setDistillery_id(rs.getInt("int_app_id_f"));
				action.setName_of_importing_unit(rs.getString("importing_unit_nm"));
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
public ArrayList getIcd_list(EoiExportOrderFrorwardingAction action) {
	
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

public ArrayList brand_display_detail(EoiExportOrderFrorwardingAction action,int app_id) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
     int sr = 1 ; ;

	try {

		String query = 
				
			   " SELECT distinct br.brand_name, pckg.package_name, b.etin, a.app_id, a.recv_date, a.brc_number, a.leo_number,a.created_date , b.reqstd_bottles"+
               " FROM distillery.eoi_app_for_export_order a , distillery.eoi_app_for_export_order_brand b ,  "+
               " distillery.brand_registration_20_21 br , distillery.packaging_details_20_21 pckg ,          "+
               " distillery.eoi_import_order_master_brand mst                                                                                                 "+
               " where a.int_dist_id = '"+action.getDistillery_id()+"' and mst.package_id = pckg.package_id and  mst.brand_id = br.brand_id and                                           "+
               " a.app_id = '"+app_id+"' and a.app_id= b.app_id_fk and mst.etin = b.etin ";
				
			
		System.out.println("-- brand details ===="+query);
		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			
			EoiExportOrderFrorwardingDT  dt = new EoiExportOrderFrorwardingDT();
			dt.setSrno_brand(sr);
			dt.setBrand_name(rs.getString("brand_name"));
			dt.setPackage_name(rs.getString("package_name")) ;
			dt.setEtin(rs.getString("etin")) ;
			//dt.setBalance_qty(rs.getLong("no_of_bottles"));
			dt.setRequested_qty(rs.getInt("reqstd_bottles"));
			
				
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

public ArrayList export_order_display_detail(EoiExportOrderFrorwardingAction action ) {
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
   int sr = 1 ; ;
    String filter="";
    if(ResourceUtil.getUserNameAllReq().substring(0, 9).equalsIgnoreCase("Excise-DL")){
    	
    	if(action.getRadio().equalsIgnoreCase("N")){
    		
    		filter= " a.user1_dt is null and a.user1_name='"+ResourceUtil.getUserNameAllReq()+"' " +
    				" and (a.objection_flag not in ('O','D','R') or a.objection_flag is null)";
    		
    	}else if(action.getRadio().equalsIgnoreCase("A")){
    		
    		filter= " a.user1_dt is not null  and esign_date is   null and a.user1_name='"+ResourceUtil.getUserNameAllReq()+"' " +
    				" and (a.objection_flag not in ('O','D','R') or a.objection_flag is null)";
    		
    	 }else if(action.getRadio().equalsIgnoreCase("DP")){
     		
     		filter= "  a.approve_flag='A' and esign_date is not null";
     		
     	}
    	
    }else if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-DEC")){
    	

    	if(action.getRadio().equalsIgnoreCase("N")){
    		
    		filter= " a.user2_dt is null and a.user1_dt is not null and a.user2_name='"+ResourceUtil.getUserNameAllReq()+"' " +
    				" and (a.objection_flag not in ('O','D','R') or a.objection_flag is null)";
    		
    		
    	}else if(action.getRadio().equalsIgnoreCase("A")){
    		
    		filter= " a.user2_dt is not null and esign_date is   null and a.user2_name='"+ResourceUtil.getUserNameAllReq()+"' " +
    				" and (a.objection_flag not in ('O','D','R') or a.objection_flag is null)";
    		
    	}else if(action.getRadio().equalsIgnoreCase("O")){
    		
    		filter= " a.user2_dt is  null and a.user2_name='"+ResourceUtil.getUserNameAllReq()+"' " +
    				" and (a.objection_flag in ('O'))";
    		
    	}else if(action.getRadio().equalsIgnoreCase("OR")){
    		
    		filter= " a.user2_dt is null and a.user2_name='"+ResourceUtil.getUserNameAllReq()+"' " +
    				" and (a.objection_flag in ('R','D'))";
    		
    	}else if(action.getRadio().equalsIgnoreCase("D")){
    		
    		filter= " a.user4_dt is not null and a.user2_name='"+ResourceUtil.getUserNameAllReq()+"' " +
    				" and a.approve_flag='A'  and esign_date is null";
    		
    	}else if(action.getRadio().equalsIgnoreCase("DP")){
    		
    		filter= "  a.approve_flag='A' and esign_date is not null";
    		
    	}
    }else if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-AC-License")){

    	if(action.getRadio().equalsIgnoreCase("N")){
    		
    		filter= " a.user3_dt is null and a.user2_dt is not null and a.user3_name='"+ResourceUtil.getUserNameAllReq()+"' " +
    				" and (a.objection_flag not in ('O','D','R') or a.objection_flag is null)";
    		
    	}else if(action.getRadio().equalsIgnoreCase("A")){
    		
    		filter= " a.user3_dt is not null  and esign_date is   null and a.user3_name='"+ResourceUtil.getUserNameAllReq()+"' " +
    				" and (a.objection_flag not in ('O','D','R') or a.objection_flag is null)";
    		
    	}else if(action.getRadio().equalsIgnoreCase("DP")){
    		
    		filter= "  a.approve_flag='A' and esign_date is not null";
    		
    	}
    }else if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-Commissioner")){

       if(action.getRadio().equalsIgnoreCase("N")){
    		
    		filter= " a.user4_dt is null and a.user3_dt is not null and a.user4_name='"+ResourceUtil.getUserNameAllReq()+"' " +
    				" and (a.objection_flag not in ('O','D','R') or a.objection_flag is null)";
    		
    	}else if(action.getRadio().equalsIgnoreCase("A")){
    		
    		filter= " a.user4_dt is not null  and esign_date is   null and a.user4_name='"+ResourceUtil.getUserNameAllReq()+"' " +
    				" and (a.objection_flag not in ('O','D','R') or a.objection_flag is null)";
    		
    	}else if(action.getRadio().equalsIgnoreCase("DP")){
    		
    		filter= "  a.approve_flag='A' and esign_date is not null";
    		
    	}
    }
    	
	try {

		String query =
			
		"SELECT coalesce(a.permit_no,'NA') as permit_no,a.nm_req_id,a.nm_service_id,a.nm_unit_id,a.nm_control_id,  a.app_id, a.recv_date,a.order_id,coalesce(a.approve_flag,'NA') as approve_flag,a.esign_date,a.esign_pdf,a.esign_flag, a.brc_number,a.int_dist_id, a.leo_number,a.created_date , sum(b.reqstd_bottles) as reqstd_bottles,coalesce(a.status,'NA') as status ,  "+
		"(SELECT vch_undertaking_name from public.dis_mst_pd1_pd2_lic where int_app_id_f=a.int_dist_id) as distillery_name," +
		" puc_certificate,puc_no, a.print_permit_pdf FROM distillery.eoi_app_for_export_order a , distillery.eoi_app_for_export_order_brand b  ,distillery.eoi_import_order_master c                                     "+
		"where a.app_id= b.app_id_fk  and a.order_id=c.seq_pk and "+filter+" group by                                                                                                 "+
		"a.app_id, a.recv_date, a.brc_number, a.leo_number,a.created_date,a.order_id ,a.status ,a.int_dist_id                                                  ";
				
		 conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();
		
		SimpleDateFormat formatter= new SimpleDateFormat("dd MMMM yyyy");
		String icd_date="";
		
		

		while (rs.next()) {
			
			String reg_date =formatter.format(rs.getDate("created_date"));
			if(rs.getDate("recv_date")!=null){
			 icd_date = formatter.format(rs.getDate("recv_date"));
			}else{
				icd_date="";
			}
			
			EoiExportOrderFrorwardingDT  dt = new EoiExportOrderFrorwardingDT();
			//ankur 29-09-2020
			if(rs.getString("esign_pdf")!=null)
			{
			dt.setCheck_pdf(true);	
			}	
			else
			{
			 dt.setCheck_pdf(false);	
			}
			dt.setSrno_export(sr);
			dt.setApplication_no(rs.getInt("app_id"));
			dt.setApplication_date(reg_date) ;
			dt.setNo_of_bottles(rs.getLong("reqstd_bottles")) ;
			dt.setCeo_no(rs.getInt("leo_number"));
			dt.setBrc_no(rs.getInt("brc_number"));
			dt.setReciept_at_icd(icd_date);
			dt.setStatus(rs.getString("status"));
			dt.setDistillery_name(rs.getString("distillery_name"))	;
			dt.setDistillery_id(rs.getString("int_dist_id"));
			dt.setRequestId(rs.getString("nm_req_id"));
			dt.setMaincntrlId(rs.getString("nm_control_id"));
			dt.setMainunitId(rs.getString("nm_unit_id"));
			dt.setMainServiceId(rs.getString("nm_service_id"));
			dt.setPermit_no(rs.getString("permit_no"));
			dt.setPuc_no(rs.getString("puc_no"));
			dt.setPuc_pdf(rs.getString("puc_certificate"));
			if(rs.getString("print_permit_pdf")!= null){
				dt.setPrintFlag(true);
				dt.setPdfname(rs.getString("print_permit_pdf"));
			}else{
				dt.setPrintFlag(false);
				dt.setPdfname("");
			}
			
			/*if(rs.getString("print_permit_pdf")!=null){
				dt.setPrintFlag(true);
			}else{
				dt.setPrintFlag(false);
			}*/
			if(rs.getDate("esign_date")!=null){
				dt.setEsign_flag(true);
			}else{
				dt.setEsign_flag(false);
			}
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

public ArrayList import_list(EoiExportOrderFrorwardingAction action ) {
	
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

public boolean updateDetails(EoiExportOrderFrorwardingAction act){

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 1;
	String query = ""; String filter1 = ""; 
 
    Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	String time = sdf.format(cal.getTime());
	
	boolean flg=false;
	 try {

		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		 
		 if(ResourceUtil.getUserNameAllReq().substring(0, 9).equalsIgnoreCase("Excise-DL")){
			 filter1="Pending At c Since "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"";
		       query   =    " UPDATE distillery.eoi_app_for_export_order set user1_dt=?,user1_remark=?," +
		   		            " user2_name='Excise-DEC', " +
		   		            " status='Pending At Excise-DEC Since "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"' where app_id=?";
		
		 }
		 else if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-DEC")){
			 filter1="Pending At Excise-AC-License Since "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"";
			   query   =    " UPDATE distillery.eoi_app_for_export_order set user2_dt=?,user2_remark=?," +
			   		        " user3_name='Excise-AC-License', " +
			   		        " status='Pending At Excise-AC-License Since "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"' where app_id=?";
			
			 }
		 else if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-AC-License")){
			 filter1="Pending At Excise-Commissioner Since "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"";
			   query   =    " UPDATE distillery.eoi_app_for_export_order set user3_dt=?,user3_remark=?," +
			   		        " user4_name='Excise-Commissioner', " +
			   		        " status='Pending At Excise-Commissioner Since "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"' where app_id=?";
			
			 }
		 
				pstmt = conn.prepareStatement(query);
			
				pstmt.setDate(1, Utility.convertUtilDateToSQLDate(new Date()));pstmt.setString(2, time);
				pstmt.setString(2, act.getRemark());
				pstmt.setInt(3, act.getApp_id());
								
				saveStatus = pstmt.executeUpdate();
				
				 
		
		
		
		if (saveStatus > 0) {
			if(act.getMaincntrlId()!=null && act.getMaincntrlId().length()>0 && act.getMainServiceId()!=null && act.getMainServiceId().length()>0 )
			{
			String status =this.returnParameter(act.getMaincntrlId(), act.getMainunitId(),
					 act.getMainServiceId(), act.getApp_id()+"", act.getApp_id()+"",
					 "05","  Forwarded By : "+ResourceUtil.getUserNameAllReq()+" ."+filter1+" marking :"+act.getRemark(),act.getRequestId(),filter1,"");
					
					if(status.equalsIgnoreCase("SUCCESS"))
					{
			conn.setAutoCommit(true);
			flg=true;
			act.setViewFlag(false);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("FORWARDED SUCCESSFULLY !", "FORWARDED SUCCESSFULLY !"));
			act.reset();}else{
				conn.rollback();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Api Status Returned Failed", "Api Status Returned Failed"));
			}
			}else {

				conn.setAutoCommit(true);
				flg=true;
				act.setViewFlag(false);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("FORWARDED SUCCESSFULLY !", "FORWARDED SUCCESSFULLY !"));
				act.reset();
			}
					
					
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("NOT FORWARDED !", "NOT FORWARDED !"));
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

//==================approve============

public boolean approveimpl(EoiExportOrderFrorwardingAction act){

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 1;
	String query = ""; 
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	String time = sdf.format(cal.getTime());
	
	boolean flg=false;
	 try {

		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		 
		 if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-Commissioner")){
			
		       query   =    " UPDATE distillery.eoi_app_for_export_order set user4_dt=?,user4_remark=?," +
		   		            " approve_flag='A', status='Application Approved By  Excise-Commissioner On Date "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+" " +
		   		            		" And Pending For Digital Sign At Excise-DEC'  " +
		   		           	" where app_id=?";
		
		 }
		
		 
				pstmt = conn.prepareStatement(query);
			
				pstmt.setDate(1, Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setString(2, act.getRemark());
				pstmt.setInt(3, act.getApp_id());
								
				saveStatus = pstmt.executeUpdate();
				
				 
		
		
		if (saveStatus > 0) {
			if(act.getMaincntrlId()!=null && act.getMaincntrlId().length()>0 && act.getMainServiceId()!=null && act.getMainServiceId().length()>0 )
			{String status =this.returnParameter(act.getMaincntrlId(), act.getMainunitId(),
					act.getMainServiceId(), act.getApp_id()+"", act.getApp_id()+"",
					 "06","  Approved By : "+ResourceUtil.getUserNameAllReq()+" Remarks Is "+act.getRemark(),act.getRequestId(),"Pendency Level at: Excise-DEC  For Digital Sign","");
			if(status.equalsIgnoreCase("SUCCESS"))
			{conn.setAutoCommit(true);
			flg=true;
			act.setViewFlag(false);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("APPROVED SUCCESSFULLY !", "APPROVED SUCCESSFULLY !"));
			act.reset();  
			
			}else{
				conn.rollback();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Api Status Returned Failed", "Api Status Returned Failed"));
			
				}
			
			}	else {
				conn.setAutoCommit(true);
				flg=true;
				act.setViewFlag(false);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("APPROVED SUCCESSFULLY !", "APPROVED SUCCESSFULLY !"));
				act.reset();  
				
				
			}
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("SOMETHING WENT WRONG !", "SOMETHING WENT WRONG !"));
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


public boolean rejectimpl(EoiExportOrderFrorwardingAction act){

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 1;
	String query = ""; 
	String updatequery = "";
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	String time = sdf.format(cal.getTime());
	
	boolean flg=false;
	 try {

		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		 
		 if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-Commissioner")){
			
		       query   =    " UPDATE distillery.eoi_app_for_export_order set user4_dt=?,user4_remark=?," +
		   		           "  approve_flag='R', " +
		   		           " status='Application Rejcted By Excise-Commissioner On Date "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"' where app_id=?";
		       
		    updatequery   = " update  distillery.eoi_import_order_master_brand set used_bottles = used_bottles - ? where masterseq_fk = ? and  etin=? and distillery_id = ?";
		
		 }
		
		 
				pstmt = conn.prepareStatement(query);
			
				pstmt.setDate(1, Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setString(2, act.getRemark());
				pstmt.setInt(3,act.getApp_id());
								
				saveStatus = pstmt.executeUpdate();
				
				if(saveStatus > 0){
					pstmt = conn.prepareStatement(updatequery);
					
					for(int i=0; i<act.getBrand_detaiil_list().size(); i++){
						EoiExportOrderFrorwardingDT dt = (EoiExportOrderFrorwardingDT) act.getBrand_detaiil_list().get(i);
						
					pstmt.setDouble(1, dt.getRequested_qty());
					pstmt.setInt(2, act.getSeq_pk());
					pstmt.setString(3,dt.getEtin());
					pstmt.setInt(4, act.getDistillery_id());
									
					saveStatus = pstmt.executeUpdate();
					
					}
				}
				
				 
		if (saveStatus > 0) {
			if(act.getMaincntrlId()!=null && act.getMaincntrlId().length()>0 && act.getMainServiceId()!=null && act.getMainServiceId().length()>0 )
			{	String status= this.returnParameter(act.getMaincntrlId(), act.getMainunitId(),
					 act.getMainServiceId(), act.getApp_id()+"", act.getApp_id()+"",
					 "07","  Rejected By : "+ResourceUtil.getUserNameAllReq()+" . Remarks is: "+act.getRemark()+".",act.getRequestId(),"Application Rejcted By Excise-Commissioner On Date "+Utility.convertUtilDateToSQLDate(new Date())+" - "+time,"");
			if(status.equalsIgnoreCase("SUCCESS"))
			{
			conn.setAutoCommit(true);
			flg=true;
			act.setViewFlag(false);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("REJECTED SUCCESSFULLY !", "REJECTED SUCCESSFULLY !"));
			act.reset();
			}else{
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Not Saved","Not Saved"));
				conn.rollback();
			
				}
		} else{
			conn.setAutoCommit(true);
			flg=true;
			act.setViewFlag(false);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("REJECTED SUCCESSFULLY !", "REJECTED SUCCESSFULLY !"));
			act.reset();
		}
			}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("SOMETHING WENT WRONG !", "OMETHING WENT WRONG !"));
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
//===================Revert back method=================

public boolean revertBack(EoiExportOrderFrorwardingAction act){

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 1;
	String query = ""; 
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	String time = sdf.format(cal.getTime());
	
	boolean flg=false;
	 try {

		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		 
		 if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-DEC")){
			
		       query   =    " UPDATE distillery.eoi_app_for_export_order set user1_dt=null,user2_dt=null," +
		   		            " status='Application Reverted Back To Excise-DL On Date "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"'," +
		   		            " revert_date=?,revert_remark=? where app_id=?";
		
		 }
		 else if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-AC-License")){
				
			   query   =    " UPDATE distillery.eoi_app_for_export_order set user2_dt=null,user3_dt=null," +
			   		        " status='Application Reverted Back To Excise-DEC On Date "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"' ," +
			   		        " revert_date=?,revert_remark=? where app_id=?";
			
			 }
		 else if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-Commissioner")){
				
			   query   =    " UPDATE distillery.eoi_app_for_export_order set user3_dt=null,user4_dt=null," +
			   		        " status='Application Reverted Back To Excise-AC-License On Date "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"'," +
			   		        " revert_date=?,revert_remark=? where app_id=?";
			
			 }
		 
				pstmt = conn.prepareStatement(query);
			
				pstmt.setDate(1, Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setString(2, act.getRvrtRemark());
				pstmt.setInt(3, act.getApp_id());
								
				saveStatus = pstmt.executeUpdate();
				
				System.out.println("====save status= pi details=1"+saveStatus);
			
			
		
		
		
		if (saveStatus > 0) {
			conn.setAutoCommit(true);
			flg=true;
			act.setViewFlag(false);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("REVERTED BACK SUCCESSFULLY !", "REVERTED BACK SUCCESSFULLY !"));
			act.reset();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("ERROR !", "ERROR !"));
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


public void viewImpl(EoiExportOrderFrorwardingAction act, int app_id){
	

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
     int sr = 1 ; ;

	try {

		String query = " SELECT b.seq_pk, po_number,importing_unit_nm,created_date as  import_order_date,pi_date as validity_date,pi_number as performa_invoice_no,c.name,"+
                       " a.user1_remark,a.user2_remark,a.user3_remark,a.user4_remark,coalesce(a.revert_remark,'NA') as  revert_remark,a.pi_pdf, b.po_pdf from distillery.eoi_app_for_export_order a," +
                       " distillery.eoi_import_order_master b,licence.icd_master c" +
                       " where b.seq_pk=a.order_id and a.icd_id=c.id and a.app_id="+app_id+" ";
				
			
		 
		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();
		SimpleDateFormat formatter= new SimpleDateFormat("dd MMMM yyyy");
		
		if (rs.next()) {
			String validity_date = formatter.format(rs.getDate("validity_date"));
			String import_date = formatter.format(rs.getDate("import_order_date"));
			act.setImport_order_no(rs.getString("po_number"));
			act.setName_of_importing_unit(rs.getString("importing_unit_nm"));
			act.setImport_order_date(import_date);
			act.setValidity_demanded(validity_date);
			act.setPerforma_invoice_no(rs.getString("performa_invoice_no"));
			act.setIcd_name(rs.getString("name"));
			act.setUser1_remark(rs.getString("user1_remark"));
			act.setUser2_remark(rs.getString("user2_remark"));
			act.setUser3_remark(rs.getString("user3_remark"));
			act.setUser4_remark(rs.getString("user4_remark"));
			if(rs.getString("revert_remark").equalsIgnoreCase("NA")){
				act.setUser_revert_flag(false);
				act.setUser_revert_remark("");
			}else{
				act.setUser_revert_flag(true);
				act.setUser_revert_remark(rs.getString("revert_remark"));
			}
			
			act.setPi_pdf(rs.getString("pi_pdf"));
			act.setPurcahse_order_pdf(rs.getString("po_pdf"));
			act.setPurcahse_order_no(rs.getString("po_number"));
			act.setSeq_pk(rs.getInt("seq_pk"));
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


}


public void viewPDF(EoiExportOrderFrorwardingAction act){
	

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
     int sr = 1 ; ;

	try {

		String query = " select imp_exp_code,imp_exp_cert_pdf,rcmc_no,rcmc_cert_pdf,star_exp_house_no,star_exp_house_cert_pdf from " +
				      " distillery.reg_of_distilleryasexpunit  where distillery_id ="+act.getDistillery_id()+"";
				
			
		System.out.println("-- pdf details ===="+query);
		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();
		SimpleDateFormat formatter= new SimpleDateFormat("dd MMMM yyyy");
		
		if (rs.next()) {
			act.setImp_exp_no(rs.getString("imp_exp_code"));
			act.setImp_exp_pdf(rs.getString("imp_exp_cert_pdf"));
			act.setRcm_no(rs.getString("rcmc_no"));
			act.setRcm_pdf(rs.getString("rcmc_cert_pdf"));
			act.setHouse_no(rs.getString("star_exp_house_no"));
			act.setHouse_pdf(rs.getString("star_exp_house_cert_pdf"));
			
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


}
public String getReplies(EoiExportOrderFrorwardingAction act) {

	Connection con = null;
	PreparedStatement pstmt = null, ps2 = null;
	ResultSet rs = null;
	String path = null;
	try {
		con = ConnectionToDataBase.getConnection();

		String queryList = " SELECT  app_id, objection_id, objection_title, objection_description, "
				+ " reply_on_objection, uploaded_file, "
				+ " objection_title || '(Description:)' || objection_description as objected_for "
				+ " FROM distillery.eoi_import_order_objection  "
				+ " WHERE  app_id="
				+ act.getApp_id()
				+ "   "
				+ " AND objection_id=(SELECT max(objection_id) FROM distillery.eoi_import_order_objection "
				+ " WHERE app_id="
				+ act.getApp_id() + ")  ";

		System.out.println("reply queryy=="+queryList);
		pstmt = con.prepareStatement(queryList);

		rs = pstmt.executeQuery();

		while (rs.next()) {

			path = "/ExciseUp/MIS/ExportOutsideIndia/eoiObjection/"+rs.getString("uploaded_file");

			// System.out.println("path---------------------------"+path);

			act.setPopup4ObjectedFor(rs.getString("objection_title"));
			act.setPopup4ActionTaken(rs.getString("reply_on_objection"));
			act.setPopup4objID(rs.getInt("objection_id"));
			if (rs.getString("uploaded_file") != null) {
				act.setPopup4ObjectedPdf(path);
				act.setViewpdfFlg(true);
			} else {
				act.setViewpdfFlg(false);
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
			if (con != null)
				con.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	return "";

}

public void agreeReplyImpl(EoiExportOrderFrorwardingAction act) {

	int saveStatus = 0;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String queryList = "";
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	String time = sdf.format(cal.getTime());

	try {
		
		
		con = ConnectionToDataBase.getConnection();
		con.setAutoCommit(false);

		queryList = " UPDATE distillery.eoi_app_for_export_order SET objection_flag='A', status=? "+
					" WHERE app_id=?";

		pstmt = con.prepareStatement(queryList);

		saveStatus = 0;
       
        pstmt.setString(1, "Objection Viewed & ACCEPTED By "+ResourceUtil.getUserNameAllReq()+" On Date "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"");
        pstmt.setInt(2, act.getApp_id());

		// System.out.println("--------------"+queryList);

		saveStatus = pstmt.executeUpdate();

		if (saveStatus > 0) {
			
			con.commit();
			act.setViewFlag(false);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Objection Removed Successfully !", "Objection Removed Successfully !"));
			
		} else {

			
			con.rollback();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error !","Error !"));
		}
	} catch (Exception se) {
		se.printStackTrace();

	} finally {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		} catch (Exception se) {
			se.printStackTrace();
		}
	}

}

// =====================decline reply================================

public void declineReplyImpl(EoiExportOrderFrorwardingAction act) {

	int saveStatus = 0;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String queryList = "";
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	String time = sdf.format(cal.getTime());

	try {

		con = ConnectionToDataBase.getConnection();
		con.setAutoCommit(false);

		queryList = " UPDATE distillery.eoi_app_for_export_order SET objection_flag='D', status=? "+
					" WHERE app_id=? ";

		pstmt = con.prepareStatement(queryList);

		saveStatus = 0;
         
        pstmt.setString(1, "Objection Viewed & DECLINED By "+ResourceUtil.getUserNameAllReq()+" On Date "+Utility.convertUtilDateToSQLDate(new Date())+" "+time+"");
        pstmt.setInt(2, act.getApp_id());

		// System.out.println("--------------"+queryList);

		saveStatus = pstmt.executeUpdate();
		
		
		if (saveStatus > 0) {
			saveStatus=0;
			String query=" UPDATE distillery.eoi_import_order_objection SET reply_date=null" +
					     " WHERE app_id="+act.getApp_id()+" and objected_by='"+ResourceUtil.getUserNameAllReq()+"'"; 
			
			pstmt = con.prepareStatement(query);
			saveStatus = pstmt.executeUpdate();
		} 

		if (saveStatus > 0) {
			
			con.commit();
			act.setViewFlag(false);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Objection Declined !", "Objection Declined !"));

		} else {

			
			con.rollback();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error !","Error !"));
		}
	} catch (Exception se) {
		se.printStackTrace();

	} finally {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		} catch (Exception se) {
			se.printStackTrace();
		}
	}

}

public void save_Objection(EoiExportOrderFrorwardingAction act) {

	int saveStatus = 0;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String queryList = "";

	

	try {

		con = ConnectionToDataBase.getConnection();
		con.setAutoCommit(false);
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());

		queryList = " INSERT INTO distillery.eoi_import_order_objection( "
				+ "  app_id, objection_id, objection_title, objection_description,"
				+ " objection_time, objection_date, objected_by)VALUES(?, (select coalesce(max(objection_id),0)+1  from distillery.eoi_import_order_objection), ?, ?, ?, ?, ?)";

		pstmt = con.prepareStatement(queryList);

		saveStatus = 0;

		pstmt.setInt(1, act.getApp_id());
		pstmt.setString(2, act.getObjection_for());
		pstmt.setString(3, act.getObj_Description());
		pstmt.setString(4, time);
		pstmt.setDate(5, Utility.convertUtilDateToSQLDate(new Date()));
		pstmt.setString(6, ResourceUtil.getUserNameAllReq().trim());

		// System.out.println("--------------"+queryList);

		saveStatus = pstmt.executeUpdate();

		if (saveStatus > 0) {

			saveStatus = 0;

			String queryUpdate = " UPDATE distillery.eoi_app_for_export_order SET objection_flag='O', " +
					" status='OBJECTED BY "+ResourceUtil.getUserNameAllReq()+" !' "
					+ " WHERE app_id=?";

			pstmt = con.prepareStatement(queryUpdate);

			saveStatus = 0;

			pstmt.setInt(1,  act.getApp_id());

			// System.out.println("-----update-----"+queryUpdate);

			saveStatus = pstmt.executeUpdate();

		}

		if (saveStatus > 0) {
			
			con.commit();
			act.setViewFlag(false);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Objection Raised!", "Objection Raised !"));
		} else {
			

			con.rollback();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error !","Error !"));
		}
	} catch (Exception se) {
		se.printStackTrace();

	} finally {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		} catch (Exception se) {
			se.printStackTrace();
		}
	}

}

public String returnParameter(String ControlID, String UnitID,
		String ServiceID, String ProcessIndustryID, String ApplicationID,
		String Status_Code,String REMARKS,String reqId,String pendencylevel,String objectionRejectionCode) {
	
	String apiOutput="";
	try {
		org.apache.http.client.HttpClient client = new DefaultHttpClient();
		
		
		HttpPost post = new HttpPost("http://72.167.225.87/testing_nmswp/upswp_niveshmitraservices.asmx/WReturn_CUSID_STATUS");
		
		final List nameValuePairs = new ArrayList(28);
        nameValuePairs.add(new BasicNameValuePair("ControlID", ControlID));
        nameValuePairs.add(new BasicNameValuePair("UnitID", UnitID));
        nameValuePairs.add(new BasicNameValuePair("ServiceID", ServiceID));
        nameValuePairs.add(new BasicNameValuePair("ProcessIndustryID", ProcessIndustryID));
        nameValuePairs.add(new BasicNameValuePair("ApplicationID", ApplicationID));
        nameValuePairs.add(new BasicNameValuePair("Status_Code", Status_Code));
        nameValuePairs.add(new BasicNameValuePair("Remarks", REMARKS));
        nameValuePairs.add(new BasicNameValuePair("Fee_Amount", ""));
        nameValuePairs.add(new BasicNameValuePair("Fee_Status", ""));
        nameValuePairs.add(new BasicNameValuePair("Transaction_ID", ""));
        nameValuePairs.add(new BasicNameValuePair("Transaction_Date", ""));
        nameValuePairs.add(new BasicNameValuePair("Transaction_Date_Time", ""));
        nameValuePairs.add(new BasicNameValuePair("NOC_Certificate_Number", ""));
        nameValuePairs.add(new BasicNameValuePair("NOC_URL", ""));
        nameValuePairs.add(new BasicNameValuePair("ISNOC_URL_ActiveYesNO", ""));
        nameValuePairs.add(new BasicNameValuePair("passsalt","gttbt3b8a5c915ba4ftr63267c5baz8kp"));
        
        nameValuePairs.add(new BasicNameValuePair("Request_ID", reqId));
        
        nameValuePairs.add(new BasicNameValuePair("Objection_Rejection_Code", objectionRejectionCode));
        nameValuePairs.add(new BasicNameValuePair("Is_Certificate_Valid_Life_Time", ""));
        nameValuePairs.add(new BasicNameValuePair("Certificate_EXP_Date_DDMMYYYY",""));
        nameValuePairs.add(new BasicNameValuePair("Pendancy_level", pendencylevel));
        nameValuePairs.add(new BasicNameValuePair("D1", ""));
        nameValuePairs.add(new BasicNameValuePair("D2", ""));
        nameValuePairs.add(new BasicNameValuePair("D3", ""));
        nameValuePairs.add(new BasicNameValuePair("D4", ""));
        nameValuePairs.add(new BasicNameValuePair("D5", ""));
        nameValuePairs.add(new BasicNameValuePair("D6", ""));
        nameValuePairs.add(new BasicNameValuePair("D7", "")); 
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		 
		org.apache.http.HttpResponse respons = client.execute(post);
		 
		
		
		String xml=EntityUtils.toString(respons.getEntity());
		   
		   XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
		   InputSource source = new InputSource(new StringReader(xml));
		    
		        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder builder;
		        InputSource is;   
		       
		        builder = factory.newDocumentBuilder();
		        is = new InputSource(new StringReader(xml)); 
		        Document   doc = builder.parse(is);
		        String xml1 = doc.getDocumentElement().getTextContent(); 
		        NodeList nodes = doc.getElementsByTagName("string");
		        apiOutput= nodes.item(0).getTextContent();  
System.out.println("ControlID========"+ControlID);
System.out.println("ServiceID========"+ServiceID);
System.out.println("UnitID========"+UnitID);
System.out.println("ApplicationID========"+ApplicationID);System.out.println("ProcessIndustryID========"+ProcessIndustryID);
System.out.println("apiOutput========"+apiOutput);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return apiOutput;
}


//ankur 01-10-2020

public void printReport(EoiExportOrderFrorwardingAction act, EoiExportOrderFrorwardingDT dt)	
{

	String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;

	String relativePath = mypath + File.separator + "ExciseUp"+ File.separator + "Distillery" + File.separator + "jasper";
	String relativePathpdf = mypath + File.separator + "ExciseUp"+ File.separator + "ExportOutsideIndia" + File.separator + "License";
	JasperReport jasperReport = null;
	PreparedStatement pst = null;
	Connection con = null;
	ResultSet rs = null;
	String reportQuery = null;
	String reportQuery1 = null;
	
	

	try {
		
		 
		con = ConnectionToDataBase.getConnection();
					
		this.updatePermit(act, dt.getApplication_no()); 
		
		reportQuery1 =
				/*" SELECT distinct e.po_date,e.po_number,f.vch_country_name,e.country_id,e.importing_unit_nm,br.strength," +
				" dist.vch_undertaking_name,dist.vch_wrk_add,br.brand_name, pckg.package_name, b.etin, a.app_id, a.recv_date, a.brc_number, " +
				" a.leo_number,a.created_date , b.reqstd_bottles FROM distillery.eoi_app_for_export_order a ," +
				" distillery.eoi_app_for_export_order_brand b ,distillery.brand_registration_20_21 br ," +
				" distillery.packaging_details_20_21 pckg , distillery.eoi_import_order_master_brand mst ," +
				" public.dis_mst_pd1_pd2_lic dist,distillery.eoi_import_order_master e," +
				" public.country_mst f where a.int_dist_id = '"+dt.getDistillery_id()+"' and mst.package_id = pckg.package_id and" +
				" mst.brand_id = br.brand_id and a.app_id = '"+dt.getApplication_no()+"'and a.app_id= b.app_id_fk and mst.etin = b.etin " +
				" and  a.int_dist_id=dist.int_app_id_f " +
				" and mst.masterseq_fk=e.seq_pk and e.country_id=f.int_country_id";
		
		
		*/ 
				
			 
           
		
		
		"  SELECT distinct (select  string_agg(g.name, ',') from licence.icd_master g  )  as icd,to_char(e.created_date, 'DD/MM/YYYY') as po_date,e.po_number,f.vch_country_name,e.country_id,e.importing_unit_nm,br.strength,                            "+
		"  dist.vch_undertaking_name,dist.vch_wrk_add,br.brand_name, pckg.package_name, b.etin, a.app_id, a.recv_date, a.brc_number,         "+
		"  a.leo_number,to_char(a.print_permit_dt, 'DD/MM/YYYY') as created_date , b.reqstd_bottles,b.req_box                                                                                    "+
		"  FROM distillery.eoi_app_for_export_order a , distillery.eoi_app_for_export_order_brand b ,                                        "+
		"  distillery.brand_registration_20_21 br , distillery.packaging_details_20_21 pckg ,                                                "+
		"  public.dis_mst_pd1_pd2_lic dist,distillery.eoi_import_order_master e, public.country_mst f                                       "+
		"  where a.int_dist_id = '"+dt.getDistillery_id()+"' and a.app_id= b.app_id_fk and a.app_id = '"+dt.getApplication_no()+"'                                                           "+
		"  and b.etin=pckg.code_generate_through and br.brand_id=pckg.brand_id_fk                                                            "+
		"  and  a.int_dist_id=dist.int_app_id_f    and b.reqstd_bottles >0                                                                                             "+
		"  and a.order_id=e.seq_pk and e.country_id=f.int_country_id   group by e.po_date,e.po_number,f.vch_country_name,e.country_id,e.importing_unit_nm,br.strength,                           " + 
		"	 	  dist.vch_undertaking_name,dist.vch_wrk_add,br.brand_name, pckg.package_name, b.etin, a.app_id, a.recv_date, a.brc_number,       " + 
		"	   a.leo_number, a.print_permit_dt   ,b.reqstd_bottles    ,b.req_box        ";
 pst = con.prepareStatement(reportQuery1);
		 		rs = pst.executeQuery();

		if (rs.next()) {

			rs = pst.executeQuery();
			Map parameters = new HashMap();
			parameters.put("REPORT_CONNECTION", con);
			parameters.put("SUBREPORT_DIR", relativePath + File.separator);
			parameters.put("image", relativePath + File.separator);
			//parameters.put("distnm", act.getName());
			parameters.put("grpnm", act.getRadio());
			
			JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

			
			jasperReport = (JasperReport) JRLoader.loadObject(relativePath + File.separator+ "EoiExportOrder.jasper");
			 

			JasperPrint print = JasperFillManager.fillReport(jasperReport,parameters, jrRs);
			Random rand = new Random();
			int n = rand.nextInt(250) + 1;
			JasperExportManager.exportReportToPdfFile(print,relativePathpdf + File.separator+ "EoiExportOrder" + "-" + dt.getApplication_no() + "-2020.pdf");
			dt.setPdfname("EoiExportOrder" + "-" + dt.getApplication_no() + "-2020.pdf");
			
		
			
			
		dt.setPrintFlag(true);
		 
		} else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"No Data Found!!", "No Data Found!!"));
			dt.setPrintFlag(false);
		}
	} catch (JRException e) {
		 
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
				e.getMessage(), e.getMessage()));
	} catch (Exception e) {
		 
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
				e.getMessage(), e.getMessage()));
	} finally {
		try {
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			 
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					e.getMessage(), e.getMessage()));
		}
	}

}

public String updatePermit(EoiExportOrderFrorwardingAction act, int appId){


	int saveStatus = 0;
	
	Connection con = null;
	PreparedStatement pstmt = null;			
	String updtQr = "";
	

	
	try {
		con = ConnectionToDataBase.getConnection();
		con.setAutoCommit(false);
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());

			updtQr = 	" UPDATE distillery.eoi_app_for_export_order" +
					    "   set print_permit_time=?,print_permit_dt=?,print_permit_pdf=?,permit_no= ? "+
					 	" WHERE app_id="+appId+"  ";
								

			pstmt = con.prepareStatement(updtQr);

		 pstmt.setString(1, time);
			pstmt.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));	
			pstmt.setString(3, "EoiExportOrder" + "-" + appId + "-2020.pdf");
			pstmt.setString(4, "EoiExportOrder" + "-" + appId + "-2020");
			

			saveStatus = pstmt.executeUpdate();
				


		if (saveStatus > 0) {
			con.commit();
			//act.closeApplication();
			
			

		} else {
			con.rollback();

		}
	} catch (Exception se) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(se.getMessage(), se.getMessage()));
		se.printStackTrace();

	} finally {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		} catch (Exception se) {
			se.printStackTrace();
		}
	}
	return "";


}



}
