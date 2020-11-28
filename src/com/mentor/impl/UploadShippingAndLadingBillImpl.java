package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.mentor.Datatable.UploadShippingAndLadingBillDT;

import com.mentor.action.UploadShippingAndLadingBillAction;

import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class UploadShippingAndLadingBillImpl {

	public void getDetails(UploadShippingAndLadingBillAction act){


		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionToDataBase.getConnection();

			
           String selQr =   
		 
		  "  SELECT int_app_id_f , vch_undertaking_name FROM " +
		  "  public.dis_mst_pd1_pd2_lic   where vch_wrk_phon = '"+ResourceUtil.getUserNameAllReq()+"' " ;
	 
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();
			
			 
			while (rs.next()) {
				
				act.setInt_distillery_id(rs.getInt("int_app_id_f"));
				act.setDistillery_name(rs.getString("vch_undertaking_name"));
				
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
	
public ArrayList gatepassListImpl(UploadShippingAndLadingBillAction act ) {
		
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	     int sr = 1 ; ;
      String filter="";
      
      if(act.getSealradio().equalsIgnoreCase("P")){
	    	 filter=" and app.container='P' and gtm.sealing_date is null and lading_bill_date is null ";
	     }
      else if(act.getSealradio().equalsIgnoreCase("M")){
    	  filter=" and app.container='P' and gtm.sealing_date is not null and lading_bill_date is null ";
	     }
      
      else if(act.getRadio().equalsIgnoreCase("SB")){
	    	 filter=" and shipping_bill_date is null and lading_bill_date is null";
	     }else if(act.getRadio().equalsIgnoreCase("LB")){
	    	 filter=" and shipping_bill_date is not null and lading_bill_date is null";
	     }else if(act.getRadio().equalsIgnoreCase("B")){
	    	 filter=" and lading_bill_date is not null  and  gtm.brc_date is null ";
	     }else if(act.getRadio().equalsIgnoreCase("C")){
	    	 filter=" and shipping_bill_date is not null and lading_bill_date is not null and gtm.brc_date is not null";
	     }
	     
	     
		try {

			String query = 
					
		      " select   gtm.container,gtm.container_no,gtm.seal_no,gtm.sealing_date, gtm.vch_gatepass_no ,gtm.int_dist_id ,gtm.curr_date, sum(fst.dispatchd_box) as dispatchd_box ,sum(fst.dispatchd_bottl) as             "+
              " dispatchd_bottl,shipping_bill_pdf,lading_bill_pdf,gtm.uploade_certificate_forgien_exchange_pdf from distillery.gatepass_to_manufacturewholesale_20_21 gtm,                                                                  "+
              " distillery.fl1_stock_trxn_20_21 fst,distillery.eoi_app_for_export_order app  where gtm.vch_gatepass_no = fst.vch_gatepass_no and gtm.vch_to ='EOI' and gtm.int_dist_id ="+act.getInt_distillery_id()+" " +
              " AND vch_finalize = 'F'  and app.order_id::text=gtm.importorderno and  gtm.int_dist_id=app.int_dist_id "+filter+" group by "+  
              " gtm.vch_gatepass_no ,gtm.int_dist_id ,gtm.curr_date order by gtm.curr_date ";
					
			System.out.println("== gatepass list impl  =="+query);
		 conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
		
		

			while (rs.next()) {
				
				UploadShippingAndLadingBillDT  dt = new UploadShippingAndLadingBillDT();
				dt.setSno(sr);
				dt.setVch_gatepass_no(rs.getString("vch_gatepass_no"));
				dt.setDt_date(rs.getDate("curr_date"));
				dt.setTotal_dis_box(rs.getInt("dispatchd_box"));
				dt.setTotal_dis_bottles(rs.getInt("dispatchd_bottl"));
				dt.setShipping_pdf(rs.getString("shipping_bill_pdf"));
				dt.setLading_pdf(rs.getString("lading_bill_pdf"));
				dt.setBrc_pdf(rs.getString("uploade_certificate_forgien_exchange_pdf"));
					dt.setContainer_no(rs.getString("container_no"));
					dt.setSeal_dt(rs.getDate("sealing_date"));
					dt.setSeal_no(rs.getString("seal_no"));
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
	public ArrayList brand_display_detail(UploadShippingAndLadingBillAction act ) {
		
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	     int sr = 1 ; ;

		try {

			String query = 
					
		      " select a.dispatchd_bottl ,a.dispatchd_box ,a.int_brand_id ,a.int_pckg_id ,                                                                           "+
              " a.int_dissleri_id ,a.vch_gatepass_no,br.brand_name ,pk.package_name  from distillery.fl1_stock_trxn_20_21 a,distillery.brand_registration_20_21 br , "+
              " distillery.packaging_details_20_21 pk where br.brand_id = pk.brand_id_fk and br.brand_id = a.int_brand_id and pk.package_id = a.int_pckg_id and      "+
              " a.vch_gatepass_no ='"+act.getGatepass_no()+"' order by br.brand_name";
					
				
			System.out.println("-- brand details ===="+query);
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				UploadShippingAndLadingBillDT  dt = new UploadShippingAndLadingBillDT();
				dt.setSrno(sr);
				dt.setBrand_name(rs.getString("brand_name"));
				dt.setPackage_name(rs.getString("package_name"));
				dt.setDispatch_box(rs.getInt("dispatchd_box"));
				dt.setDispatch_bottle(rs.getInt("dispatchd_bottl"));
				
					
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
	
	public boolean savedetails(UploadShippingAndLadingBillAction act){

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 1;
		String query = ""; 
	
		
		boolean flg=false;
		 try {

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			 
			
				if(act.getRadio().equalsIgnoreCase("SB")){
			query   =    "  update  distillery.gatepass_to_manufacturewholesale_20_21 set shipping_bill_pdf='"+act.getShipping_bil_pdf()+"'," +
					    " shipping_bill_no='"+act.getShipping_bill_no()+"',shipping_bill_date='"+Utility.convertUtilDateToSQLDate(act.getShipping_bill_date())+"'" +
					    " where vch_gatepass_no='"+act.getGatepass_no()+"' ";
			
				}else if(act.getRadio().equalsIgnoreCase("LB")){
					
					query   =    "  update  distillery.gatepass_to_manufacturewholesale_20_21 set lading_bill_pdf='"+act.getLading_bil_pdf()+"'," +
						    " lading_bill_no='"+act.getLading_bill_no()+"',lading_bill_date='"+Utility.convertUtilDateToSQLDate(act.getLading_bill_date())+"'" +
						    " where vch_gatepass_no='"+act.getGatepass_no()+"' ";
				}		
		 else if(act.getRadio().equalsIgnoreCase("B")){
				
				query   =    " update distillery.gatepass_to_manufacturewholesale_20_21 set " +
						" currency_id='"+act.getCurrency_id()+"', brc_value='"+act.getBrc_value()+"', " +
						" value_in_inr='"+act.getValue_inr()+"' , uploade_certificate_forgien_exchange_pdf='"+act.getUploade_certificate_foriegn_exchange_pdf()+"' ," +
						" brc_date='"+Utility.convertUtilDateToSQLDate(act.getBrc_date())+"' where vch_gatepass_no='"+act.getGatepass_no()+"'  ";
			}	
				
				System.out.println("====save status= pi details=1111"+query);
					pstmt = conn.prepareStatement(query);
				
									
					saveStatus = pstmt.executeUpdate();
					
					System.out.println("====save status= pi details=122"+saveStatus);
				
			
			
			if (saveStatus > 0) {
				conn.setAutoCommit(true);
				flg=true;
				act.setView_flag(false);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("SAVED SUCCESSFULLY !", "SAVED SUCCESSFULLY !"));
				
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
	public boolean savedetails1(UploadShippingAndLadingBillAction act){

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 1;
		String query = ""; 
	
		
		boolean flg=false;
		 try {

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			 
			
				 
			query   =    "  update  distillery.gatepass_to_manufacturewholesale_20_21 set  " +
					    " seal_no='"+act.getSeal_no()+"',sealing_date='"+Utility.convertUtilDateToSQLDate(act.getSeal_dt())+"'" +
					    " where vch_gatepass_no='"+act.getGatepass_no()+"' ";
			
			System.out.println("---savedetails---"+query);
			
				 	pstmt = conn.prepareStatement(query);
				
									
					saveStatus = pstmt.executeUpdate();
					 
			
			
			if (saveStatus > 0) {
				conn.setAutoCommit(true);
				flg=true;
				act.setView_flag(false);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("SAVED SUCCESSFULLY !", "SAVED SUCCESSFULLY !"));
				act.setSealradio("M");
				
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
	 //----------------------------------------Currencey----------------------------------------
	public ArrayList selectcurrency()
	{

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);
		try {
			String query = " SELECT curency_id, currency_nm FROM public.currency_list order by curency_id ";
			
		
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
