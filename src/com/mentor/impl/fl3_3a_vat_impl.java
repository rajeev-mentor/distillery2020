package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem; 

import com.mentor.Datatable.fl3_3a_vat_dt;
import com.mentor.action.fl3_3a_vat_action;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;

public class fl3_3a_vat_impl {
		//----------------------------------------User Detais-----------------------------------------------Created by Arvind-------------Verma-----
	public String getUserDetails(fl3_3a_vat_action act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

		s=	" SELECT x.app_id, x.distillery_id, x.license_district, x.license_no,           "+
			" x.distilery_district, x.unit_name, x.unit_adrs      "+
			" FROM                                                                                                  "+
			" (SELECT a.int_app_id as app_id, a.int_distillery_id as distillery_id, a.district as license_district, "+
			" a.vch_license_fl1a as license_no, a.vch_licence_type as licence_type, b.vch_unit_dist as distilery_district,          "+
			" a.vch_unit_name as unit_name, a.vch_unit_addrs as unit_adrs "+
			" FROM licence.fl3a_fl1a a, public.dis_mst_pd1_pd2_lic b                              "+
			" WHERE a.int_distillery_id=b.int_app_id_f AND a.vch_lic_unit_type='D'                                  "+
			" AND a.vch_licence_type='FL3A' AND a.loginuser='"+ResourceUtil.getUserNameAllReq().trim() +"'          "+
			" UNION                                                                                                 "+
			" SELECT a.int_lic_id as app_id, a.int_distillery_id as distillery_id, a.district as license_district,  "+
			" a.vch_licence_no as license_no, a.vch_licence_type as licence_type, b.vch_unit_dist as distilery_district,            "+
			" a.vch_unit_name as unit_name, a.vch_unit_addrs as unit_adrs "+
			" FROM licence.licence_entery_fl3_fl1 a, public.dis_mst_pd1_pd2_lic b                 "+
			" WHERE a.int_distillery_id=b.int_app_id_f AND a.vch_lic_unit_type='D'                                  "+
			" AND a.vch_licence_type='FL3' AND a.loginuser='"+ResourceUtil.getUserNameAllReq().trim() +"' )x ";

			pstmt = con.prepareStatement(s);
           //   System.out.println("======================hidden===="+s);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				act.setInt_app_id(rs.getInt("app_id"));
				act.setInt_dist_id(rs.getInt("distillery_id"));
				act.setLic_no(rs.getString("license_no"));
				act.setUnit_name(rs.getString("unit_name"));
				act.setUnit_address(rs.getString("unit_adrs"));
				
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


	
//----------------------------------sprit_storage---------------------------------------------------------Created by Arvind-------------Verma------------
	public ArrayList getPackagingName(fl3_3a_vat_dt dt) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String SQl ="SELECT int_spirit_type_id, vch_spirit_name  FROM distillery.mst_spirit_type";
	//	System.out.println("pckg=" + SQl);
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_spirit_name"));
				item.setValue(rs.getString("int_spirit_type_id"));
				list.add(item);
			}
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));

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
		return list;
	}
//-------------------------------------------Datatable--------Data-----------------------------------------------------------------------------Created by Arvind-------------Verma----------------------
	
	public ArrayList displaylistImpl2(String radio ,fl3_3a_vat_dt dt1) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		int i = 1;

		try {
	
                query =" SELECT a.storage_id as storage_id,a.int_spirit_type_id as int_spirit_type_id ,a.storage_desc as storage_desc , a.vat_type as vat_type,"+
                        " a.capacity as capacity , a.strength as strength , a.int_app_id as int_app_id "+
                        " FROM distillery.fl3_3a_vat_master  a  where vat_type='"+radio+"'  "+
                        "";
                
				

	     //	System.out.println("selQr1============get data==========" +query);
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();
			while (rs.next()) {
				fl3_3a_vat_dt dt=new fl3_3a_vat_dt();
				dt.setSno(i);
				dt.setVat_name(rs.getString("storage_desc"));
				dt.setStorage_id(rs.getInt("storage_id"));
				dt.setCap(rs.getDouble("capacity"));
				dt.setSprit_id(rs.getString("int_spirit_type_id"));
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

	
	
	
	//---------------------------Save-----------------------------Created by Arvind-------------Verma---------------------------------------------------------------

	public int save1(fl3_3a_vat_action act ,fl3_3a_vat_dt dt){
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "";
		String query1="";
		int saveStatus=0;
		PreparedStatement pstmt=null;
		int count=0;
		saveStatus = 0;
		try {
			
			query="INSERT INTO distillery.fl3_3a_vat_master( storage_id,distillery_id,vat_type,lic_no,storage_desc ,capacity,int_app_id ) " +
		             " VALUES ('"+this.maxId()+"','"+act.getInt_dist_id()+"','"+act.getRadio()+"','"+act.getLic_no()+"'" +
		             ",'"+dt.getVat_name()+"','"+dt.getCap()+"','"+act.getInt_app_id()+"')";
		
			query1="INSERT INTO distillery.fl3_3a_vat_master( storage_id,distillery_id,vat_type,lic_no,storage_desc ,capacity,int_app_id ,int_spirit_type_id) " +
		             " VALUES ('"+this.maxId()+"','"+act.getInt_dist_id()+"','"+act.getRadio()+"','"+act.getLic_no()+"'" +
		             ",'"+dt.getVat_name()+"','"+dt.getCap()+"','"+act.getInt_app_id()+"','"+dt.getSprit_id()+"')";
				
			con=ConnectionToDataBase.getConnection();
		//	System.out.println("------------------qu--"+query);
		//	System.out.println("");
             if (act.getRadio().equalsIgnoreCase("BL") || (act.getRadio().equalsIgnoreCase("BT"))) {
            	 pstmt = con.prepareStatement(query);
			}else {
				pstmt = con.prepareStatement(query1);
			}
			
			
			
					
				
                System.out.println("------------------"+query);
				/*	pstmt.setInt(1, this.maxId());
			
						pstmt.setString(2, dt.getVat_name());
					
					
					
						pstmt.setDouble(3, dt.getCap());
					
					
					pstmt.setInt(4, act.getInt_app_id());*/
                
                
                
					saveStatus = pstmt.executeUpdate();
					/* System.out.println("================================"+insQr);
                     System.out.println("--------lic no-------------"+act.getLic_no());
                     System.out.println("----radio----------------"+act.getRadio());
                     System.out.println("----------------------dis---"+act.getInt_dist_id());
                     System.out.println("------vat name------------------"+table.getVat_name());
                     System.out.println("--------------cap----"+table.getCap());
                     System.out.println("-------------int_app_id-----"+act.getInt_app_id());*/
					
                        
					/*	pstmt.setInt(1, act.getInt_dist_id());
						pstmt.setString(2,act.getRadio());
						*/
						
						System.out.println("-------lic no------------------------"+act.getLic_no());
					//	psmt.setString(3, act.getLic_no());*/
					//	System.out.println("------vat name-------------1111-----"+table.getVat_name());
					
					//	System.out.println("------vat name------------22221-----"+table.getVat_name());
					//	System.out.println("-------lic no---------------------1111---"+act.getLic_no());
						
						
				
					//	System.out.println("------------sequence----------------"+sequence);
					//	System.out.println("---------------------------save stat");
				
				
					if (saveStatus > 0) {
						  count++;

					} else {
						

					}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (con!=null) {
					con.close();
				}
				if (rs!=null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return count;
	}

	
	//------------------------------------------Select------------------------------------------------------------------Created by Arvind-------------Verma-----

	public boolean select( int stor_id) {

		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		boolean s = false;
		try {
			con = ConnectionToDataBase.getConnection();

			String query = 
					
					"select  storage_id,distillery_id,vat_type,lic_no,storage_desc ,capacity,int_app_id from " +
					"  distillery.fl3_3a_vat_master where storage_id='"+stor_id+"'";
				
			pstmt = con.prepareStatement(query);
               System.out.println("======================select-----------------"+query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				s = true;
				
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
		return s;

	}
	

//-------------------------------------------------Max_id--------------------------------------------------------------------Created by Arvind-------------Verma------------------------------------
	public int maxId()
	{

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " select max(storage_id) as id   from distillery.fl3_3a_vat_master";
		int maxid = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			System.out.println("--------------------------"+query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxid = rs.getInt("id");
			}
		} catch (Exception e) {
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
		return maxid+1;
	
	}


//----------------------------------Update Methods-----------------------------------------------------------------------------------------------------------------------
	public int update(int st ,fl3_3a_vat_action act , fl3_3a_vat_dt dt ) {
//rendered="#{fl3_3a_vat_action.radio eq 'S'}"
       int count=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " UPDATE  distillery.fl3_3a_vat_master  " +
				 " SET  storage_desc='"+dt.getVat_name()+"' , int_spirit_type_id='"+dt.getSprit_id()+"'," +
				 " capacity="+dt.getCap() + " " + 
				 "  where  storage_id='"+st+"' " ;
		
		 
		
		String query1 = " UPDATE  distillery.fl3_3a_vat_master  " +
				 " SET  storage_desc='"+dt.getVat_name()+"' , " +
				 " capacity="+dt.getCap() + " " + 
				 "  where  storage_id='"+st+"' " ;
		
		
		
		
		System.out.println("=============================save=====sataus--------"+query);
		System.out.println("===================="+query1);
		
		int saveStatus = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			if (act.getRadio().equalsIgnoreCase("S")) {
			pstmt = con.prepareStatement(query);
			}else {
				pstmt = con.prepareStatement(query1);
			}
			
			
			
			 saveStatus = pstmt.executeUpdate();
			System.out.println("=============================save=====sataus--------"+saveStatus);
			 if (saveStatus > 0) {
				count++;
				} else {
		
					ResourceUtil.addErrorMessage(Constants.NOT_SAVED,Constants.NOT_SAVED);

				}
		} catch (Exception e) {
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
		
	return count;
	
	}


}
	




