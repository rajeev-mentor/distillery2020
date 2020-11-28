package com.mentor.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

import com.mentor.Datatable.GatepassToWholesale_20_21_active_dt;
import com.mentor.action.BondReleaseAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class BondReleaseImpl {
	
	
	
	public ArrayList getGatepassList(BondReleaseAction action)
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		
		ArrayList list=new ArrayList();
		SelectItem item;
		
		item=new SelectItem();
		item.setLabel("Select");
		item.setValue("0");
		list.add(item);
		String sql= 
		"select vch_gatepass_no from distillery.gatepass_to_manufacturewholesale_20_21 a,public.unit_login b, distillery.eoi_app_for_export_order c "+
		"where lading_bill_date  is not null and bond_release_date is null  and c.verify_date is not null  "+
		"and vch_to ='EOI' and c.app_id=a.vch_to_lic_no::numeric "+
		"and  a.int_dist_id=b.int_mill_id and vch_unit_type='DL'  and vch_rollname='"+ResourceUtil.getUserNameAllReq()+"'";
		
		try{
			conn=ConnectionToDataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				item=new SelectItem();
				item.setLabel(rs.getString("vch_gatepass_no"));
				item.setValue(rs.getString("vch_gatepass_no"));
				list.add(item);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			
			try{
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
				if(rs!=null)rs.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
	
	
	
	public ArrayList getHeadList(BondReleaseAction action)
	{
		
		ArrayList list=new ArrayList();
		
		SelectItem item=null;
		try{
			item=new SelectItem();
			item.setLabel("003900105010000 à¤­à¤Ÿà¥�à¤Ÿà¥€ à¤ªà¤° à¤¦à¥‡à¤¶à¥€ à¤¸à¥�à¤¥à¤¾à¤¨à¥€à¤¯ à¤¨à¤¿à¤°à¥�à¤®à¤¿à¤¤ à¤®à¤¦à¥�à¤¯ à¤”à¤° à¤ªà¥�à¤°à¤¾à¤¸à¤µà¥‹à¤‚ à¤•à¤¾ à¤¶à¥�à¤²à¥�à¤•  DUTY_FL");
			item.setValue("003900105010000|11");
			list.add(item);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public boolean printReportexp1(BondReleaseAction action) {

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
					" valid_till, batch_no,package_name,box_id, ml, bl ,al, vch_unit_name"+
					" from distillery.fl11gatepass_draftexp_20_21(" + action.getInt_dist_id() + ", '"+
					Utility.convertUtilDateToSQLDate(action.getDt_date())+"','"+action.getVch_gatepass_no().trim()+"')";
			
System.out.println("reportQuery : printReportexp1="+reportQuery);
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
						+ File.separator + "MadeForeignLiquorexp.jasper");

				JasperPrint print = JasperFillManager.fillReport(jasperReport,
						parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(999) + 1;

				JasperExportManager.exportReportToPdfFile(print,relativePathpdf + File.separator+ "MadeForeignLiquorexp-" + action.getVch_gatepass_no()+ ".pdf");

				// GatepassToWholesale_20_21_active_dt dt=new
				// GatepassToWholesale_20_21_active_dt();
				//dt.setPdfNameDt("MadeForeignLiquorexp-" + action.getVch_gatepass_no()+ ".pdf");
				action.setPdfName("MadeForeignLiquorexp-" + action.getVch_gatepass_no()+ ".pdf");
				//dt.setMyFlagDt(true);
				//flagImpl = true;
				action.setPdf_flag(true);
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Data Found", "No Data Found"));
				action.setPdf_flag(false);
				//flagImpl = false;
				//dt.setMyFlagDt(false);

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
	
	
	public void getValue(BondReleaseAction action)
	{
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		String sql="select int_dist_id,dt_date,vch_gatepass_no,lading_bill_date,bond_value  from distillery.gatepass_to_manufacturewholesale_20_21 where vch_gatepass_no=?";
		try{
			conn=ConnectionToDataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, action.getVch_gatepass_no());
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				action.setDt_date(Utility.convertSqlDateToUtilDate(rs.getDate("dt_date")));
				action.setInt_dist_id(rs.getInt("int_dist_id"));
				action.setLading_dt(Utility.convertSqlDateToUtilDate(rs.getDate("lading_bill_date")));
				action.setGatepass_date(Utility.convertSqlDateToUtilDate(rs.getDate("dt_date")));
				action.setBondValue(rs.getDouble("bond_value"));
				  long difference_In_Time =Utility.convertSqlDateToUtilDate(rs.getDate("lading_bill_date")).getTime()-Utility.convertSqlDateToUtilDate(rs.getDate("dt_date")).getTime();
				  long difference_In_Days = TimeUnit .MILLISECONDS .toDays(difference_In_Time) % 365; 
				  
				  
				  action.setDays_diff(new Double(difference_In_Days).intValue());
				  if(difference_In_Days<=15)
				  {
					  
					  System.out.println("with in time " +difference_In_Days);
					action.setWith_in_time("W")  ;
				  }else{
					  System.out.println("out in time "+difference_In_Days);
					  action.setWith_in_time("T")  ;
				  }
			}else {
				action.reset();
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	
	}
	
	
	
	public void save(BondReleaseAction action)
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		int status=0;
	String 	withintime = "INSERT INTO distillery.bond_register_20_21( "
				+ "int_id, int_distillery_id, date_crr_date, vch_duty_type, int_quantity, bond_value, vch_description) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		
	String 	outTime = "INSERT INTO distillery.duty_register_19_20( "
				+ "int_id, int_distillery_id, date_crr_date, vch_duty_type, int_quantity, int_value, vch_description,  gatepass) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
	
	String update ="update distillery.gatepass_to_manufacturewholesale_20_21 set bond_release_date=?,no_of_days=?,time_expire_flag=? where vch_gatepass_no=?";
		
		try{
			conn=ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(update);
			pstmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setInt(2, action.getDays_diff());
			pstmt.setString(3, action.getWith_in_time());
			
			pstmt.setString(4,action.getVch_gatepass_no());
			 status=pstmt.executeUpdate();
			
			if(status>0&&action.getWith_in_time().equals("W"))
			{
				status=0;
				pstmt=conn.prepareStatement(withintime);
				pstmt.setInt(1, getMaxIdBond() + 1);
				pstmt.setInt(2, action.getInt_dist_id());
				pstmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(4, "EXP_BOND");
				pstmt.setDouble(5, getdispatchValue(action.getVch_gatepass_no()));
				pstmt.setDouble(6, -action.getBondValue());
				pstmt.setString(7, "Export Duty for FL11B for Gatepass "+ action.getVch_gatepass_no());
				status = pstmt.executeUpdate();			
				
			}else if(status>0&&action.getWith_in_time().equals("T"))
			{
				status=0;
				
				pstmt=conn.prepareStatement(outTime);
				
				pstmt.setInt(1, getMaxIdDuty() + 1);
				pstmt.setInt(2, action.getInt_dist_id());
				pstmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(4, "EXP_FEE_FL");
				pstmt.setDouble(5, getdispatchValue(action.getVch_gatepass_no()));
				pstmt.setDouble(6, action.getBondValue());
				pstmt.setString(7, "Against "+action.getVch_gatepass_no()+"Bond");
				pstmt.setString(8, action.getVch_gatepass_no());
					
					status = pstmt.executeUpdate();
				
				
				
			}
			
			if(status>0)
			{
				
				
				action.reset1();
				conn.commit();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Saved Successfully","Saved Successfully"));
			}else{
				conn.rollback();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Not Saved ","Not Saved"));
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			
			try{
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
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
					
					
					
					
					
					
					
					
					
					
					
					
					public double getdispatchValue(String gatepass) {
						Connection conn = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						double maxid = 0;

						try {
							String query = "select int_quantity from distillery.duty_register_19_20 where gatepass='"+gatepass+"'";
							conn = ConnectionToDataBase.getConnection();
							pstmt = conn.prepareStatement(query);

							rs = pstmt.executeQuery();
							if (rs.next()) {
								maxid = rs.getDouble(1);
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
					
	
	

}
