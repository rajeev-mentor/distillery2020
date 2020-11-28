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

import com.mentor.Datatable.BondRegisterDt;
import com.mentor.action.BondRegisterAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class BondRegisterImpl {
	
//---------------AEC LOGIN --------------------------------------------------
	
	public String getDetails(BondRegisterAction ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;

		try {
			String queryList = "";
			con = ConnectionToDataBase.getConnection();
			// try {
			if (ResourceUtil.getUserNameAllReq().trim().substring(0, 9)
					.equalsIgnoreCase("Excise-DL")) {
				ac.setDist_name(ResourceUtil.getUserNameAllReq().trim());
				ac.setDist_id(Integer.parseInt(ResourceUtil.getUserNameAllReq()
						.trim().substring(10)));
				// ac.setAddress("");

			System.out.println("1--"+ResourceUtil.getUserNameAllReq().trim().substring(0,9)+"-"+ac.getDist_id());
			} else {
				queryList = " SELECT int_app_id_f as app_id,vch_undertaking_name as name ,vch_wrk_add as address,'D' as login_type"
						+ " FROM dis_mst_pd1_pd2_lic WHERE vch_wrk_phon='"
						+ ResourceUtil.getUserNameAllReq().trim() + "'";

				pstmt = con.prepareStatement(queryList);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					ac.setDist_name(rs.getString("name"));
					ac.setDist_id(rs.getInt("app_id"));
					// ac.setAddress(rs.getString("address"));

				}

			}
		} catch (SQLException se) {
			se.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(se.getMessage(), se.getMessage()));
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

//--------------------------------------bond Nmae
	
	public ArrayList getBondlist(BondRegisterAction ac) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		SelectItem item = new SelectItem();
		item.setValue(0);
		item.setLabel("--Select--");
		list.add(item);
		String query = "";
		try {
			
			
			
			if (ac.getRadio_type().equalsIgnoreCase("FL3")) {
				query = " SELECT a.int_app_id as app_id, a.int_distillery_id as distillery_id, a.district as license_district,           "+
						" a.vch_license_fl1a as license_no, a.vch_licence_type as licence_type, b.vch_unit_dist as distilery_district,    "+     
						" a.vch_unit_name as unit_name, a.vch_unit_addrs as unit_adrs                                                     "+
						" FROM licence.fl3a_fl1a a, public.dis_mst_pd1_pd2_lic b                                                          "+
						" WHERE a.int_distillery_id=b.int_app_id_f AND a.vch_lic_unit_type='D'                                            "+
						" AND a.vch_licence_type='FL3A' AND   a.int_distillery_id='"+ac.getDist_id()+"' ";

				System.out.println("--FL3---" + query);
			} else if (ac.getRadio_type().equalsIgnoreCase("FL3A")) {

				query = " SELECT a.int_lic_id as app_id, a.int_distillery_id as distillery_id, a.district as license_district,            "+
						" a.vch_licence_no as license_no, a.vch_licence_type as licence_type, b.vch_unit_dist as distilery_district,      "+     
						" a.vch_unit_name as unit_name, a.vch_unit_addrs as unit_adrs                                                     "+
						" FROM licence.licence_entery_fl3_fl1 a, public.dis_mst_pd1_pd2_lic b                                             "+
						" WHERE a.int_distillery_id=b.int_app_id_f AND a.vch_lic_unit_type='D'                                            "+
						" AND a.vch_licence_type='FL3' AND  a.int_distillery_id='"+ac.getDist_id()+"' ";

			

				System.out.println("---FL3--" + query);

			}

			/*query ="  SELECT x.app_id, x.distillery_id, x.license_district, x.license_no,                                            "+
				   	" x.distilery_district, x.unit_name, x.unit_adrs                                                                  "+
					" FROM                                                                                                            "+
					" (SELECT a.int_app_id as app_id, a.int_distillery_id as distillery_id, a.district as license_district,           "+
					" a.vch_license_fl1a as license_no, a.vch_licence_type as licence_type, b.vch_unit_dist as distilery_district,    "+     
					" a.vch_unit_name as unit_name, a.vch_unit_addrs as unit_adrs                                                     "+
					" FROM licence.fl3a_fl1a a, public.dis_mst_pd1_pd2_lic b                                                          "+
					" WHERE a.int_distillery_id=b.int_app_id_f AND a.vch_lic_unit_type='D'                                            "+
					" AND a.vch_licence_type='FL3A' AND   a.int_distillery_id='"+act.getDist_id()+"'                                                    "+
					" UNION                                                                                                           "+
					" SELECT a.int_lic_id as app_id, a.int_distillery_id as distillery_id, a.district as license_district,            "+
					" a.vch_licence_no as license_no, a.vch_licence_type as licence_type, b.vch_unit_dist as distilery_district,      "+     
					" a.vch_unit_name as unit_name, a.vch_unit_addrs as unit_adrs                                                     "+
					" FROM licence.licence_entery_fl3_fl1 a, public.dis_mst_pd1_pd2_lic b                                             "+
					" WHERE a.int_distillery_id=b.int_app_id_f AND a.vch_lic_unit_type='D'                                            "+
					" AND a.vch_licence_type='FL3' AND  a.int_distillery_id='"+act.getDist_id()+"' )x    ";
			*/
			System.out.println("---getBondlist----"+ query);

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				item = new SelectItem();

				item.setValue(rs.getInt("distillery_id"));
				item.setLabel(rs.getString("license_no"));
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
				// e.printStackTrace();
			}
		}
		return list;
	}
	
//----------------------------Register List
	public ArrayList getRegisterlist(BondRegisterAction act) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		SelectItem item = new SelectItem();
		item.setValue(0);
		item.setLabel("--Select--");
		list.add(item);
		String query = "";
		try {

			query =" select  seq_id, register_nm, distillery_id, opening_value, created_dt from distillery.register_list "+
				   " where distillery_id='"+act.getBond_id()+"' ";
			
			System.out.println("---getRegisterlist----"+ query);

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				item = new SelectItem();

				item.setValue(rs.getInt("seq_id"));
				item.setLabel(rs.getString("register_nm"));
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
				// e.printStackTrace();
			}
		}
		return list;
	}
//-------------------------------
	public void BondRegister(BondRegisterAction act){
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		int status = 0;
		try {

			con = ConnectionToDataBase.getConnection();
			



			String query = 
				  "	INSERT INTO distillery.bond_register( "+
				  "	 creation_dt,seq, distillery_id, bond_id, bond_type,bondvalue,reg_id)  "+
				  "	VALUES (?,(select coalesce(max(seq),0)+1  from distillery.bond_register),  ?, ?, ?,?,?) ";
	
					

					
					System.out.println("---wholesale data----"+ query);



			ps = con.prepareStatement(query);
			ps.setDate(1, Utility.convertUtilDateToSQLDate(new Date()));
			ps.setInt(2, act.getDist_id());
			ps.setInt(3, Integer.parseInt(act.getBondId()));
			ps.setString(4, act.getRadio_type());
			ps.setInt(5, act.getBond_sbmisn());
			ps.setInt(6, Integer.parseInt(act.getRegisId()));
	
           status = ps.executeUpdate();
           
           if(status == 1){
				//action.setMsg("Saved Successfully");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Saved Successfully","Saved Successfully"));	
			    act.reset();
           }else{
				//action.setMsg("Not Saved");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Not Saved","Not Saved"));
				con.rollback();
			}
           

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	
	}

	
	
	
//---------------------	
	
	public void saveStorage(BondRegisterAction act)
	{ 
		Connection conn=null;
		PreparedStatement pstmt=null;
		// (select coalesce(max(id),0)+1  from licence.icd_master)
		
		                                        
		try{
			
			String sql=" INSERT INTO distillery.register_list(                              "+
					   " register_nm,seq_id, distillery_id, opening_value, created_dt)     "+
					   " VALUES ('"+act.getRegDescription()+"',(select coalesce(max(seq_id),0)+1  from distillery.register_list),  " +
					   		"'"+act.getDist_id()+"', '"+act.getOpenValue()+"', '"+Utility.convertUtilDateToSQLDate(new Date())+"')";
			
			
			System.out.println("==saveStorage===="+sql);
			conn=ConnectionToDataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
		/*	
			pstmt.setString(1, act.getRegDescription());
			pstmt.setInt(2, act.getDist_id());
		    pstmt.setInt(3, act.getOpenValue());
		    pstmt.setDate(5,Utility.convertUtilDateToSQLDate(new Date()));*/
			
		   int i=pstmt.executeUpdate();
		   if(i>0)
		   {
			   act.reset1();
			   ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY, Constants.SAVED_SUCESSFULLY);
		   }else{
			   ResourceUtil.addErrorMessage(Constants.NOT_SAVED, Constants.NOT_SAVED);
		   }
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			
			try{
				if(conn!=null)conn.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	// DATA TABLE CODE FETCHING RECORD FROM MASTER INSPECTION

		public ArrayList getDataList(BondRegisterAction ac) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList list2 = new ArrayList();
			int i = 1;

			String selQr =
					" SELECT (select register_nm from distillery.register_list a where  reg_id=a.seq_id) as register_nm, "+
					" seq, creation_dt, distillery_id, bond_type, bond_id, bondvalue "+
					" FROM distillery.bond_register  where distillery_id='"+ac.getDist_id()+"' order by creation_dt desc";
			
			
			try {
				conn = ConnectionToDataBase.getConnection();
				pstmt = conn.prepareStatement(selQr);
			     System.out.println("datatable query-------------"+selQr);
				// pstmt.setInt(1, action.getDissleriId());
				rs = pstmt.executeQuery();
				while (rs.next()) {

					BondRegisterDt dt = new BondRegisterDt();
                    dt.setSr(i++);
					dt.setDate(rs.getDate("creation_dt"));
					dt.setType(rs.getString("bond_type"));
					dt.setBondSub(rs.getString("bondvalue"));
					dt.setRegNm(rs.getString("register_nm"));
					list2.add(dt);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			return list2;

		}

}
