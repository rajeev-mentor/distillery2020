
package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.portlet.PortletSession;

import oracle.net.aso.e;


import org.richfaces.component.UIDataTable;

import com.mentor.Datatable.Varification_Of_Sealing_DetailsDT;

import com.mentor.impl.Varification_Of_Sealing_DetailsImpl;

public class Varification_Of_Sealing_DetailsAction 
{
private String radio = "P" ;

public ArrayList dataTable = new ArrayList();

private boolean flag;
private boolean objection_reply_button;
public boolean isFlag() {
	return flag;
}
public void setFlag(boolean flag) {
	this.flag = flag;
}
public String getRadio() {
	return radio;
}
public void setRadio(String radio) {
	this.radio = radio;
}
public ArrayList getDataTable() {
	if(this.getRadio()!=null && this.getRadio().length()>0)
	{
	this.dataTable=impl.getdata(this);
	
	}return dataTable;
}
public void setDataTable(ArrayList dataTable) {
	this.dataTable = dataTable;
}


private Date calender;

public Date getCalender() {
	return calender;
}
public void setCalender(Date calender) {
	this.calender = calender;
}


private int application_no_action ;

public int getApplication_no_action() {
	return application_no_action;
}
public void setApplication_no_action(int application_no_action) {
	this.application_no_action = application_no_action;
}


public void edit(){
	try {
		
		flag=true;
			
		this.application_no_action = dt.getAppNO();
	
		
} catch (Exception ex) {
	ex.printStackTrace();
}
}



Varification_Of_Sealing_DetailsImpl impl=new Varification_Of_Sealing_DetailsImpl();

Varification_Of_Sealing_DetailsDT dt = new Varification_Of_Sealing_DetailsDT();

/*
public void submit(){
	
	 impl.update(this);	
	 flag=false;
	 this.setRadio("V");
	 
}*/
public Varification_Of_Sealing_DetailsDT getDt() {
	return dt;
}
public void setDt(Varification_Of_Sealing_DetailsDT dt) {
	this.dt = dt;
}
public void reset(){
	
	
}
public void close(){
	flag=false;
}



//=============    update==========
public void view(ActionEvent e)
{
	
	UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
	Varification_Of_Sealing_DetailsDT dt = (Varification_Of_Sealing_DetailsDT) this.dataTable.get(uiTable.getRowIndex());
	
	//flag=true;
	
	this.setApplication_no_action(dt.getAppNO());
	impl.Verify(this);
	this.dataTable=impl.getdata(this);
	this.setRadio("V");
}
public void reject(ActionEvent e){
	
	UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
	Varification_Of_Sealing_DetailsDT dt = (Varification_Of_Sealing_DetailsDT) this.dataTable.get(uiTable.getRowIndex());
	
	//flag=true;
	
	this.setApplication_no_action(dt.getAppNO());
	impl.reject(this);
	this.dataTable=impl.getdata(this);
	this.setRadio("R");
 
}

private String hidden;



public void setHidden(String hidden) {
	this.hidden = hidden;
}
public String getHidden() {
	try {
		impl.getDetails(this);
		// impl.getSeasonDetails(this);

	} catch (Exception e) {
	}
	return hidden;
}


private int dl_id ;

public int getDl_id() {
	return dl_id;
}
public void setDl_id(int dl_id) {
	this.dl_id = dl_id;
}



}
