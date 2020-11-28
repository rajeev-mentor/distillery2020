package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mentor.Datatable.Stock_Recieving_from_FL3_3ADT;  
import com.mentor.action.Stock_Recieving_from_FL3_3AAction;  
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class Stock_Recieving_from_FL3_3AImpl {
	
	public String getDetails(Stock_Recieving_from_FL3_3AAction ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try { 
			con = ConnectionToDataBase.getConnection();
			String queryList = /*"	select  a.int_app_id_f as unitid,a.vch_undertaking_name, b.vch_gatepass_no,b.vehicle_driver_name,a.vch_wrk_add,  " +
					"b.vch_from, b.vch_to, b.vch_from,b.vch_from_lic_no, b.vch_to_lic_no,  b.curr_date, b.valid_till,   " +
					"  c.dispatchd_box as dispatchd_box,b.gross_weight, b.tier_weight, b.net_weight,   " +
					" b.valid_till, c.int_brand_id, c.size as size, c.dispatchd_bottl as dispatch_bottle,  c.int_pckg_id ,d.brand_name, d.strength, e.package_name" +
					" ,x.vch_licence_type,x.licen_no,x.loginuser from ( select vch_licence_type," +
					" case when vch_license_fl1a is null then vch_license_fl3a else vch_license_fl1a end as licen_no,loginuser  from licence.fl3a_fl1a" +
					" where vch_lic_unit_type='D'  union" +
					" select vch_licence_type,vch_licence_no as licen_no,loginuser from licence.licence_entery_fl3_fl1  where vch_lic_unit_type='D' )x," +
					" public.dis_mst_pd1_pd2_lic a, distillery.gatepass_to_manufacturewholesale_20_21 b, distillery.fl1_stock_trxn_20_21 c," +
					" distillery.packaging_details_20_21 e,distillery.brand_registration_20_21 d, distillery.box_size_details f" +
					" where a.int_app_id_f=b.int_dist_id and b.vch_gatepass_no=c.vch_gatepass_no and b.int_dist_id=c.int_dissleri_id " +
					" and c.int_brand_id=d.brand_id and  d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id 	AND e.box_id=f.box_id" +
					" and x.licen_no =b.vch_to_lic_no " +
					" and x.loginuser='"+ResourceUtil.getUserNameAllReq().trim()+"' ";*/
					
				"	select  a.int_app_id_f as unitid ,vch_undertaking_name " +
				"	,x.vch_licence_type,x.licen_no,x.loginuser from " +
				"	( select vch_licence_type,int_distillery_id,  " +
				"	case when vch_license_fl1a is null then vch_license_fl3a else vch_license_fl1a end as licen_no,loginuser  from licence.fl3a_fl1a  " +
				"	where vch_lic_unit_type='D' and loginuser='"+ResourceUtil.getUserNameAllReq().trim()+"' union  " +
				"	select vch_licence_type,int_distillery_id,vch_licence_no as licen_no,loginuser "
				+ "from licence.licence_entery_fl3_fl1  where vch_lic_unit_type='D' and loginuser='"+ResourceUtil.getUserNameAllReq().trim()+"')x,  " +
				"	public.dis_mst_pd1_pd2_lic a  " +
				"	where a.int_app_id_f=x.int_distillery_id  " +
				"	and x.loginuser='"+ResourceUtil.getUserNameAllReq().trim()+"'" ;

			pstmt = con.prepareStatement(queryList);
			
			
			//System.out.println("queryList======================"+queryList);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ac.setName(rs.getString("vch_undertaking_name"));
				ac.setDist_id(rs.getInt("unitid"));
				ac.setLoginType(rs.getString("vch_licence_type"));
				ac.setLicenseing(rs.getString("licen_no"));
				


			}

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
		return "";

	}

	
	public ArrayList gatePassDetail(Stock_Recieving_from_FL3_3AAction action) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		int i = 1,cases=0;

		try {
					selQr ="select  a.int_app_id_f as unitid,a.vch_undertaking_name, b.vch_gatepass_no,b.vehicle_driver_name,a.vch_wrk_add,  " +
							"b.vch_from, b.vch_to, b.vch_from,b.vch_from_lic_no, b.vch_to_lic_no,  b.curr_date, b.valid_till,   " +
							"  c.dispatchd_box as dispatchd_box,b.gross_weight, b.tier_weight, b.net_weight,   " +
							" b.valid_till, c.int_brand_id, c.size as size, c.dispatchd_bottl as dispatch_bottle,  " +
							" c.int_pckg_id ,d.brand_name, d.strength, e.package_name, e.quantity," +
							" x.vch_licence_type,x.licen_no,x.loginuser from ( select vch_licence_type," +
							" case when vch_license_fl1a is null then vch_license_fl3a else vch_license_fl1a end as licen_no,loginuser  from licence.fl3a_fl1a" +
							" where vch_lic_unit_type='D'  union" +
							" select vch_licence_type,vch_licence_no as licen_no,loginuser from licence.licence_entery_fl3_fl1  where vch_lic_unit_type='D' )x," +
							" public.dis_mst_pd1_pd2_lic a, distillery.gatepass_to_manufacturewholesale_20_21 b, distillery.fl1_stock_trxn_20_21 c," +
							" distillery.packaging_details_20_21 e,distillery.brand_registration_20_21 d, distillery.box_size_details f" +
							" where a.int_app_id_f=b.int_dist_id and b.vch_gatepass_no=c.vch_gatepass_no and b.int_dist_id=c.int_dissleri_id " +
							" and c.int_brand_id=d.brand_id and  d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id 	AND e.box_id=f.box_id" +
							" and x.licen_no =b.vch_to_lic_no  and b.rcvdby is null and b.rcvdt is null " +
							" and x.loginuser='"+ResourceUtil.getUserNameAllReq().trim()+"' " +
							" and b.dt_date='"+Utility.convertUtilDateToSQLDate(action.getCreatedDate())+"'   " +
							" and b.vch_gatepass_no='"+action.getGatePassNo().trim()+"' ";

			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(selQr);
			//System.out.println("-------"+selQr);
			rs = ps.executeQuery();
			while (rs.next()) {
				Stock_Recieving_from_FL3_3ADT dt = new Stock_Recieving_from_FL3_3ADT();
				
				dt.setInt_brand_id(rs.getInt("int_brand_id"));
				dt.setInt_pckg_id(rs.getInt("int_pckg_id"));
				dt.setVch_brand(rs.getString("brand_name"));
				dt.setVch_pakg_Name(rs.getString("package_name")); // dispatch_box
				dt.setBoxreciv(rs.getInt("dispatchd_box"));
				dt.setInt_package_ml(rs.getInt("quantity"));
				//dt.setBatchNo(rs.getString("vch_batch_no"));
				dt.setInt_bottle_reciv(rs.getInt("dispatch_bottle"));
				dt.setRecivTotalBottel(rs.getInt("dispatch_bottle"));
				dt.setSize(rs.getInt("size"));
				
				dt.setSlno(i);
				cases++;
				i++;
				list.add(dt);

			}if(i==1) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Requested gatepass might already have been received or it does not belongs to you !", "Requested gatepass might already have been received or it does not belongs to you !"));
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
	
	
	public ArrayList getDataTable(Stock_Recieving_from_FL3_3AAction action ) {
		ArrayList list = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;

		
			query = " SELECT a.gatepass_no, a.gatepass_dt, a.brand_id, a.size_ml, a.recv_box, a.recv_bottels," +
					" a.breakage_bottels, a.total_recv_bottels, a.pckg_id, a.created_date, a.box_size, " +
					" a.distilleryid, a.loginusr, b.brand_name " +
					" FROM distillery.recieving_fl1_1a_20_21 a , distillery.brand_registration_20_21 b " +
					" WHERE a.brand_id=b.brand_id and rcvdby is null and rcvdt is null AND a.loginusr='"+ResourceUtil.getUserNameAllReq().trim()+"' " +
					" ORDER BY a.created_date DESC ";
		

		try {
			list = new ArrayList();
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);

			 

			//ps.setDate(1, Utility.convertUtilDateToSQLDate(val));
			rs = ps.executeQuery();
			int i = 1;
			while (rs.next()) 
			{
				Stock_Recieving_from_FL3_3ADT dt = new Stock_Recieving_from_FL3_3ADT();
				dt.setSno_data_table(i);
				dt.setCreatDate_data_table(rs.getDate("created_date"));
				dt.setVch_brand_data_table(rs.getString("brand_name"));
				dt.setInt_bottle_reciv_data_table(rs.getInt("recv_bottels"));
				dt.setBreakage_data_table(rs.getInt("breakage_bottels"));
				dt.setRecivTotalBottel_data_table(rs.getInt("total_recv_bottels"));
				dt.setGatepasss(rs.getString("gatepass_no"));
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
	
	public String saveMethodImpl(Stock_Recieving_from_FL3_3AAction act) {
		Connection con = null;Connection con1 = null;
		PreparedStatement ps = null, ps2 = null, ps2D = null, ps3 = null, ps4 = null, ps5 = null;
		ResultSet rs = null;		
		String sql2 = "";		
		int tok1 = 0;

		try {
			int cases = 0;
			int totalBottles = 0;

			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(cal.getTime());
			
					sql2 = 	" update distillery.gatepass_to_manufacturewholesale_20_21 set   rcvdby='"+act.getDist_id()+"' , " +
							" rcvdttime ='"+time+"',rcvdt='"+Utility.convertUtilDateToSQLDate(new Date())+"'" +
							" where vch_gatepass_no='"+act.getGatePassNo()+"' and trim(vch_to_lic_no)='"+act.getLicenseing().trim()+"'  " +
							" and rcvdby is null and rcvdt is null"; 
				

			
			ps2 = con.prepareStatement(sql2);
			tok1 = ps2.executeUpdate();


			if (tok1 > 0) {

				tok1 = 0;

				for (int i = 0; i < act.gatePssDisplaylist.size(); i++) {

					Stock_Recieving_from_FL3_3ADT dt = (Stock_Recieving_from_FL3_3ADT) act
							.getGatePssDisplaylist().get(i);
					if (dt.getRecivTotalBottel() > 0) {
						tok1 = 0;

						sql2 =  "INSERT INTO distillery.recieving_fl1_1a_20_21(" +
								"	gatepass_no, gatepass_dt, brand_id, size_ml, recv_box, recv_bottels, breakage_bottels, " +
								" total_recv_bottels, pckg_id, created_date, box_size , distilleryid, loginusr)" +
								"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";
								
							

						ps2 = con.prepareStatement(sql2);

						
						ps2.setString(1, act.getGatePassNo());
						ps2.setDate(2, Utility.convertUtilDateToSQLDate(act.getCreatedDate()));
						ps2.setInt(3, dt.getInt_brand_id());
						ps2.setInt(4, dt.getInt_package_ml());
						//ps2.setString(6, dt.getBatchNo());
						ps2.setInt(5, dt.getBoxreciv());
						ps2.setInt(6, dt.getInt_bottle_reciv());
						ps2.setInt(7, dt.getBreakage());
						ps2.setInt(8, dt.getRecivTotalBottel());
						ps2.setInt(9, dt.getInt_pckg_id());
						ps2.setDate(10, Utility.convertUtilDateToSQLDate(new Date()));
						ps2.setInt(11, dt.getSize());
						ps2.setInt(12, act.getDist_id());
						ps2.setString(13, ResourceUtil.getUserNameAllReq().trim());
						//ps2.setString(18, act.getPermitNo());
						cases += dt.getBoxreciv();
						totalBottles += dt.getInt_bottle_reciv();
						tok1 = ps2.executeUpdate();



					} 
				}
			}	
			if (tok1 > 0) {
				tok1 = 0;
				for (int i = 0; i < act.gatePssDisplaylist.size(); i++) {

					Stock_Recieving_from_FL3_3ADT dt = (Stock_Recieving_from_FL3_3ADT) act.getGatePssDisplaylist().get(i);
					if (dt.getRecivTotalBottel() > 0) {
						
						

						String updtQr = "  select * from  distillery.fl2_stock_20_21 where int_dist_id="+ act.getDist_id()+" and " +
								" licence_no='"+act.getLicenseing().trim()+"' and brand="+dt.getInt_brand_id()+" and " +
								" package="+dt.getInt_pckg_id()+" and size ="+dt.getSize()+"" ;
						ps = con.prepareStatement(updtQr);

						rs = ps.executeQuery();	
						if (rs.next()) {

							updtQr = " UPDATE distillery.fl2_stock_20_21 SET stock_box=COALESCE(stock_box,0)+"+dt.getBoxreciv()+",  stock_bottles=COALESCE(stock_bottles,0)+"+ dt.getRecivTotalBottel()+ " " +
									" WHERE int_dist_id="+ act.getDist_id()+ " AND  brand="+ dt.getInt_brand_id()+" AND package="+ dt.getInt_pckg_id()+" " +
									" and  licence_no='"+act.getLicenseing().trim()+"' and size ="+dt.getSize()+"";



						} else {
							updtQr = "  insert into distillery.fl2_stock_20_21 (int_dist_id,licence_no,brand,package,stock_box,stock_bottles,size)" +
										" values ("+act.getDist_id()+",'"+act.getLicenseing().trim()+"',"+dt.getInt_brand_id()+","+dt.getInt_pckg_id()+","+dt.getBoxreciv()+","+dt.getRecivTotalBottel()+","+dt.getSize()+")" ;   


}
						
						
						
						
						
						
						
						
						//=================================================================================
						tok1 = 0;
					/*	String updtQr =

								"	INSERT INTO fl2d.fl2_2b_stock_20_21( "
										+ "			id, type, brand_id, pckg_id, recv_total_bottels,size)  "
										+ "			VALUES (?, ?, ?, ?, ? ,"+dt.getSize()+")  "
										+ "			ON CONFLICT ON CONSTRAINT fl2_2b_receiving_stock_master_manage_pkey_20_21 "
										+ "			do update set recv_total_bottels=  COALESCE(fl2d.fl2_2b_stock_20_21.recv_total_bottels,0.0) + "
										+ dt.getRecivTotalBottel() + " ";*/

						ps3 = con.prepareStatement(updtQr);

						

						tok1 = ps3.executeUpdate();


					}	}} 
 
			if (tok1 >0) {
				con.commit();	 
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,
						Constants.SAVED_SUCESSFULLY);

				act.reset();
			}

			else {
				con.rollback();	 
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Data Not Saved !! ", "Data Not Saved !!"));

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));

		} finally {
			try {
				if (con != null)
					con.close();if (con1 != null)
						con1.close();
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

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}

		return "";
	}
	

}
