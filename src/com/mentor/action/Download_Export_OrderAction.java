package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.portlet.PortletSession;

import org.jboss.portletbridge.context.ServletSessionWrapper;
import org.richfaces.component.UIDataTable;

import com.mentor.Datatable.Download_Export_OrderDT;
import com.mentor.Datatable.Replytoobjection_eoi_dt;
import com.mentor.impl.Download_Export_OrderImpl;

public class Download_Export_OrderAction 
{
private String radio = "pfa" ;

public ArrayList dataTable = new ArrayList();
private String radio1;
private String containerno;
private String sealno;
private Date sealdate;
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


public String getRadio1() {
	return radio1;
}
public void setRadio1(String radio1) {
	this.radio1 = radio1;
}
public String getContainerno() {
	return containerno;
}
public void setContainerno(String containerno) {
	this.containerno = containerno;
}
public String getSealno() {
	return sealno;
}
public void setSealno(String sealno) {
	this.sealno = sealno;
}
public boolean isObjection_reply_button() {
	return objection_reply_button;
}
public void setObjection_reply_button(boolean objection_reply_button) {
	this.objection_reply_button = objection_reply_button;
}
public Date getSealdate() {
	return sealdate;
}
public void setSealdate(Date sealdate) {
	this.sealdate = sealdate;
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



Download_Export_OrderImpl impl=new Download_Export_OrderImpl();

Download_Export_OrderDT dt = new Download_Export_OrderDT();


public void submit(){
	
	 impl.update(this);	
	 flag=false;
	 this.setRadio("ms2");
	 
}
public Download_Export_OrderDT getDt() {
	return dt;
}
public void setDt(Download_Export_OrderDT dt) {
	this.dt = dt;
}
public void reset(){
	this.containerno=null;
	this.radio1=null;
	this.sealno=null;
	this.sealdate=null;
}
public void close(){
	flag=false;
}
public void reply(){
	
}

public String replyObjection()
{
	try{
		PortletSession psi = null;
		ServletSessionWrapper ssw = null;

		Object o = FacesContext.getCurrentInstance().getExternalContext()
				.getSession(true);

		if (o instanceof ServletSessionWrapper) {

			ssw = (ServletSessionWrapper) o;
			 
		ssw.setAttribute("app_id",dt.getAppNO());
			  
		} else if (o instanceof PortletSession) {

			psi = (PortletSession) o;
			 
		 psi.setAttribute("app_id",dt.getAppNO());
		 
			  
		}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
return "reply_obj_exp";
}


//=============    update==========
public void view(ActionEvent e)
{
	
	UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
	Download_Export_OrderDT dt = (Download_Export_OrderDT) this.dataTable.get(uiTable.getRowIndex());
	
	flag=true;
	if(this.radio.equalsIgnoreCase("mspc")){
		this.radio1="PC";
	}
	this.setApplication_no_action(dt.getAppNO());

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
