package com.mentor.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.mentor.Datatable.Icd_masterdt;
import com.mentor.action.Icd_masterAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Utility;

public class Icd_masterimpl {
	public void Icdmaster(Icd_masterAction action){
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		int status = 0;
		try {

			con = ConnectionToDataBase.getConnection();
			



			String query = " INSERT INTO licence.icd_master(address, id, name, state, ut, creation_date) "+
	                       " VALUES (?, (select coalesce(max(id),0)+1  from licence.icd_master), "+
					       " ?, ?, ?, ?)";      
	
					

					
					System.out.println("---wholesale data----"+ query);



			ps = con.prepareStatement(query);
			ps.setString(1, action.getAddress());
			ps.setString(2, action.getName());
			if(action.getRedio().equalsIgnoreCase("1")){
			ps.setInt(3, Integer.parseInt(action.getState()));
			ps.setString(4, "NA");
			}else if(action.getRedio().equalsIgnoreCase("2")){
				ps.setInt(3, 0);
				ps.setString(4, action.getUt());
			}
			ps.setDate(5, Utility.convertUtilDateToSQLDate(new Date()));
			
			
			
           status = ps.executeUpdate();
           
           if(status == 1){
				//action.setMsg("Saved Successfully");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Saved Successfully","Saved Successfully"));	
			    action.reset();
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

	
	public ArrayList getShowdata(Icd_masterAction act)
	{

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		try {
			String query = " SELECT int_state_id, vch_state_name FROM public.state_ind order by  vch_state_name";

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			
			//System.out.println("query-------------"+query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				item = new SelectItem();

				item.setValue(rs.getString("int_state_id"));
				item.setLabel(rs.getString("vch_state_name"));

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
	
public ArrayList getdata(Icd_masterAction act) {
		
		

        ArrayList list = new ArrayList();
       
		Connection con = null;
		PreparedStatement ps = null;
		int i = 1;
		String filter = "";
		try {
			
	 	
			con = ConnectionToDataBase.getConnection();			
					
			String show_query = " select distinct a.state,a.address , a.name , a.creation_date ,a.ut , "+
                                " (select vch_state_name from public.state_ind b where a.state= b.int_state_id )" +
                                " as vch_state_name  from licence.icd_master a order by a.name  ";
	
			
			 ps = con.prepareStatement(show_query);						 
										 
			 System.out.println("---ICD master----"+ show_query);
			 
			 System.out.println("========radiovalue========"+act.getRedio());
			  ResultSet rs = ps.executeQuery();

			while (rs.next())

			{
           
				 
				Icd_masterdt dt = new Icd_masterdt();
				
                dt.setSno(i);
                dt.setNicd(rs.getString("name"));
                dt.setAddressicd(rs.getString("address"));
                if(rs.getInt("state")>0)
                {
                  dt.setStateicd(rs.getString("vch_state_name"));
                }
                else
                {
                   dt.setStateicd(rs.getString("ut"));
                	
                }
                
               // dt.setUticd(rs.getString("ut"));
                dt.setCdate(rs.getDate("creation_date"));
                i++;
				list.add(dt);

			}
			
			
			
		} catch (Exception e) {
          e.printStackTrace();
          e.getMessage();
		}

		finally {
			try {

				con.close();
				ps.close();

			} catch (Exception e) {
			 System.out.println(e);
				   e.printStackTrace();
			}
		}

		return list;
}
}
