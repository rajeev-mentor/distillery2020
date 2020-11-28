package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mentor.Datatable.ReplyForObjectionExportUnitDataTable;
import com.mentor.action.ReplyForObjectionExportUnitAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Utility;

public class ReplyForObjectionExportUnitImpl {
	
	
	// ------------------display data in datatable----------------------

		public ArrayList displayObjectionImpl(ReplyForObjectionExportUnitAction act) {

			ArrayList list = new ArrayList();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int j = 1;
			String selQr = null;

			try {
				con = ConnectionToDataBase.getConnection();
				SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");

				

				selQr = "  SELECT app_id, objection_id,objection_title,  objection_description, objection_time, objection_date, "+
						    "   reply_on_objection, uploaded_file, reply_date, objected_by , "
						  + " (SELECT max(objection_id) FROM distillery.dist_exp_order_objection  "
						  + " WHERE app_id="
						 + act.getAppID()
						 + ") as maxId   "
						+ " FROM distillery.dist_exp_order_objection "
						+ " WHERE app_id="
						+ act.getAppID() + " ;";
				
				System.out.println("selQr --=" + selQr);
				ps = con.prepareStatement(selQr);

				rs = ps.executeQuery();

				while (rs.next()) {

					ReplyForObjectionExportUnitDataTable dt = new ReplyForObjectionExportUnitDataTable();

					dt.setSrNo(j);
					dt.setAppID_dt(rs.getInt("app_id"));
					dt.setObjectionID_dt(rs.getInt("objection_id"));
					dt.setObjectionRaisedBy_dt(rs.getString("objected_by"));
					dt.setObjectionFor_dt(rs.getString("objection_title"));
					dt.setDescription_dt(rs.getString("objection_description"));

					dt.setShowApplicationID_dt(rs.getString("app_id"));

					String appDate = date
							.format(Utility.convertSqlDateToUtilDate(rs
									.getDate("objection_date")));
					dt.setObjectionDate_dt(appDate);

					if (rs.getInt("objection_id") == rs.getInt("maxId")
							&& rs.getString("reply_date") == null) {
						dt.setDisableFlg(false);
					} else {
						dt.setDisableFlg(true);
					}

					j++;
					list.add(dt);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (rs != null)
						rs.close();
					if (con != null)
						con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return list;

		}

		// ------------------------save data-------------------------

		public String submitDataImpl(ReplyForObjectionExportUnitAction act) {

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int saveStatus = 0;
			// boolean isValid = false;

			try {

				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				// @RVIND NEED TO MOVE THIS METHOD FROM HERE
				String query = " UPDATE distillery.dist_exp_order_objection "
						           + " SET reply_on_objection=?, uploaded_file=?, reply_date='"+Utility.convertUtilDateToSQLDate(new Date())+"' "
						        	+ " Where app_id="
						            + act.getAppID()
						            + " AND objection_id="
						           + act.getObjectionID() + " ";

				conn = ConnectionToDataBase.getConnection();
				conn.setAutoCommit(false);

				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, act.getFillComment());
				pstmt.setString(2, act.getValForDb());

				saveStatus = pstmt.executeUpdate();

				if (saveStatus > 0) {
					
					String updtQr = " UPDATE distillery.reg_of_distilleryasexpunit SET objection_flag='R' ,status='Objection Replied !'"
							+ " Where seq_pk="
							+ act.getAppID() + " ";

					pstmt = conn.prepareStatement(updtQr);

					saveStatus = pstmt.executeUpdate();
				}

				if (saveStatus > 0) {
					conn.commit();
					act.closeApplication();
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									" Data Saved Successfully !! ",
									"Data Saved Successfully !!"));

				} else {
					conn.rollback();
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
					if (pstmt != null)
						pstmt.close();
					if (rs != null)
						rs.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return "";
		}

		
		public String checkPDF(ReplyForObjectionExportUnitAction act) {
	 
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int j = 1;
			String selQr = null;

			try {
				con = ConnectionToDataBase.getConnection();
				SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
	 
				selQr = " select uploaded_file from distillery.dist_exp_order_objection where  AND app_id="+act.getAppID()+" " +
						     " AND objection_id="+act.getObjectionID()+"";
				
				System.out.println("selQr --=" + selQr);
				ps = con.prepareStatement(selQr);

				rs = ps.executeQuery();
				act.setUploadedPdf(null);
				if (rs.next()) {
					act.setUploadedPdf(rs.getString("uploaded_file"));
					act.setPdfDone(true);
				}else{
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage (FacesMessage.SEVERITY_ERROR,"PDF File Not Uploaded" ,"PDF File Not Uploaded"));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (rs != null)
						rs.close();
					if (con != null)
						con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return act.getUploadedPdf();

		}

}
