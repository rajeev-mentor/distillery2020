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

import com.mentor.Datatable.PenalityForLateRecevingDT;
import com.mentor.action.PenalityForLateRecevingAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class PenalityForLateRecevingImpl {
public ArrayList gatepassListImpl(PenalityForLateRecevingAction act ) {
		
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	     int sr = 1 ; ;
      String filter="";
      if(ResourceUtil.getUserNameAllReq().substring(0, 9).equalsIgnoreCase("Excise-DL")){
	     if(act.getRadio().equalsIgnoreCase("P")){
	    	 filter=" and pnlty_approve_by is null and int_dist_id='"+ResourceUtil.getUserNameAllReq().substring(10)+"'";
	     }else if(act.getRadio().equalsIgnoreCase("A")){
	    	 filter=" and pnlty_approve_by is not null and int_dist_id='"+ResourceUtil.getUserNameAllReq().substring(10)+"'";
	     }
      }
      else {
    		     if(act.getRadio().equalsIgnoreCase("P")){
    		    	 filter=" and penality_date is null AND pnlty_approve_by='"+ResourceUtil.getUserNameAllReq()+"'";
    		     }else if(act.getRadio().equalsIgnoreCase("A")){
    		    	 filter=" and penality_date is not null AND pnlty_approve_by='"+ResourceUtil.getUserNameAllReq()+"'";
    		     }
    	      }
	   
		try {

			String query = 
					
		      " select penality_value,bond_value,curr_date , vch_gatepass_no ,lading_bill_date ,no_of_days from " +
		      " bwfl.gatepass_to_manufacturewholesale_20_21" +
		      " where time_expire_flag='T' "+filter+ "";
					
				
			System.out.println("--  details ===="+query);
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

			while (rs.next()) {
				String date= formatter.format(rs.getDate("curr_date"));
				String date1=formatter.format(rs.getDate("lading_bill_date"));
				PenalityForLateRecevingDT  dt = new PenalityForLateRecevingDT();
				dt.setSrno(sr);
				dt.setGatepass(rs.getString("vch_gatepass_no"));
				dt.setGatepass_date(date);
				dt.setDate_of_lading(date1);
				dt.setBond_value(rs.getDouble("bond_value"));
				dt.setNo_of_days(rs.getInt("no_of_days"))	;
				dt.setPenality_value(rs.getDouble("penality_value"));
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

public boolean forwrdImpl(PenalityForLateRecevingAction act){

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 1;
	String query = ""; 

	
	boolean flg=false;
	 try {

		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		 
		
		query   =    "  update  distillery.gatepass_to_manufacturewholesale_20_21 set penality_value="+act.getPenality()+", pnlty_approve_by=?"+
				    " where vch_gatepass_no='"+act.getGatepass_no()+"' ";
		
			
				
					
				pstmt = conn.prepareStatement(query);
				if(act.getNo_of_days()<=30 && act.getNo_of_days()>15){
			        pstmt.setString(1, "Excise-DEC-Licence");
				}else{
					pstmt.setString(1, "Excise-DEC-Distribution");
				}
								
				saveStatus = pstmt.executeUpdate();
				
				System.out.println("====save status= pi details=1"+saveStatus);
			
			
		
		
		
		if (saveStatus > 0) {
			conn.setAutoCommit(true);
			flg=true;
			act.setViewflag(false);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("FORWARDED SUCCESSFULLY !", "FORWARDED SUCCESSFULLY !"));
			
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


public boolean approveImpl(PenalityForLateRecevingAction act){

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 1;
	String query = ""; 

	
	boolean flg=false;
	 try {

		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		 
		query   =    "  update  distillery.gatepass_to_manufacturewholesale_20_21 set penality_value="+act.getPenality()+", " +
				     " penality_date='"+Utility.convertUtilDateToSQLDate(new Date())+"'"+
				    " where vch_gatepass_no='"+act.getGatepass_no()+"' ";
		
					
				pstmt = conn.prepareStatement(query);
			
								
				saveStatus = pstmt.executeUpdate();
				
				System.out.println("====save status= pi details=1"+saveStatus);
			
			
		
		
		
		if (saveStatus > 0) {
			conn.setAutoCommit(true);
			flg=true;
			act.setViewflag(false);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("APPROVED SUCCESSFULLY !", "APPROVED SUCCESSFULLY !"));
			
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
}
