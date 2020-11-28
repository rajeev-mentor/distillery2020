package com.mentor.action;


import java.util.ArrayList;

import javax.faces.event.ActionEvent;

import org.richfaces.component.UIDataTable;



import com.mentor.Datatable.Stock_Transfer_Accidental_returnDT;
import com.mentor.impl.Stock_Transfer_Accidental_returnImpl;


public class Stock_Transfer_Accidental_returnAction {
	
	private String hidden;
	private long Distillery_id;
	private boolean flag=false;
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public long getDistillery_id() {
		return Distillery_id;
	}

	public void setDistillery_id(long distillery_id) {
		Distillery_id = distillery_id;
	}

	public String getHidden() {
		impl.getDetails(this);
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	private ArrayList update_list= new ArrayList();
	private ArrayList select_list= new ArrayList();

	public ArrayList getSelect_list() {
		this.select_list=impl.getShowData2(this);
		return select_list;
	}

	public void setSelect_list(ArrayList select_list) {
		this.select_list = select_list;
	}

	public ArrayList getUpdate_list() {
		//flag=true;
		this.update_list=impl.getDetails1(this);
		
		return update_list;
	}

	public void setUpdate_list(ArrayList update_list) {
		this.update_list = update_list;
	}

	
	//*******************************update to the transfer**************************
	Stock_Transfer_Accidental_returnImpl impl=new Stock_Transfer_Accidental_returnImpl();
	
	
	public void update(ActionEvent e){
		
		UIDataTable uiTable = (UIDataTable)e.getComponent().getParent().getParent();
		Stock_Transfer_Accidental_returnDT dt = (Stock_Transfer_Accidental_returnDT) this.update_list.get(uiTable.getRowIndex());
		try {
			
			impl.getupdate(this,dt);
			flag=true;
			impl.getShowData2(this);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
