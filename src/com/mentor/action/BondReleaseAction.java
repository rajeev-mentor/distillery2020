package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.mentor.impl.BondReleaseImpl;

public class BondReleaseAction {

	private String vch_gatepass_no="";
	private Date lading_dt;
	private Date gatepass_date;
	private String with_in_time;
	private String head;
	private Date dt_date;
	private int int_dist_id;
	private double bondValue;
	private String pdfName;
	private boolean pdf_flag;
	private int days_diff;
	public int getDays_diff() {
		return days_diff;
	}
	public void setDays_diff(int days_diff) {
		this.days_diff = days_diff;
	}
	public boolean isPdf_flag() {
		return pdf_flag;
	}
	public void setPdf_flag(boolean pdf_flag) {
		this.pdf_flag = pdf_flag;
	}
	public String getPdfName() {
		return pdfName;
	}
	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}
	public double getBondValue() {
		return bondValue;
	}
	public void setBondValue(double bondValue) {
		this.bondValue = bondValue;
	}
	public Date getDt_date() {
		return dt_date;
	}
	public void setDt_date(Date dt_date) {
		this.dt_date = dt_date;
	}
	public int getInt_dist_id() {
		return int_dist_id;
	}
	public void setInt_dist_id(int int_dist_id) {
		this.int_dist_id = int_dist_id;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	private ArrayList gatepass_list = new ArrayList();
	private ArrayList duty_head=new ArrayList();
	public String getVch_gatepass_no() {
		return vch_gatepass_no;
	}
	public void setVch_gatepass_no(String vch_gatepass_no) {
		this.vch_gatepass_no = vch_gatepass_no;
	}
	public Date getLading_dt() {
		return lading_dt;
	}
	public void setLading_dt(Date lading_dt) {
		this.lading_dt = lading_dt;
	}
	public Date getGatepass_date() {
		return gatepass_date;
	}
	public void setGatepass_date(Date gatepass_date) {
		this.gatepass_date = gatepass_date;
	}
	public String getWith_in_time() {
		return with_in_time;
	}
	public void setWith_in_time(String with_in_time) {
		this.with_in_time = with_in_time;
	}
	public ArrayList getGatepass_list() {
		
		
		try{
			this.gatepass_list=new BondReleaseImpl().getGatepassList(this);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try{
			if(!this.getVch_gatepass_no().equals("0")&& !this.getVch_gatepass_no().equals(""))
			{
				
			
		
			new BondReleaseImpl().getValue(this);
			}else{
				this.reset();
				this.setVch_gatepass_no("0");
				//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Select One Gatepass","Please Select One Gatepass"));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}

		
		
		
		
		
		return gatepass_list;
	}
	public void setGatepass_list(ArrayList gatepass_list) {
		this.gatepass_list = gatepass_list;
	}
	public ArrayList getDuty_head() {
		
		
		try{
			duty_head=new BondReleaseImpl().getHeadList(this);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return duty_head;
	}
	public void setDuty_head(ArrayList duty_head) {
		this.duty_head = duty_head;
	}
	
public void valueChangeEvent(ValueChangeEvent event)
{
	try{
		if(!this.getVch_gatepass_no().equals("0")&& !this.getVch_gatepass_no().equals(""))
		{
			
			this.pdf_flag=false;
		this.vch_gatepass_no=(String)event.getNewValue();
		new BondReleaseImpl().getValue(this);
		}else{
			this.reset();
			this.setVch_gatepass_no("0");
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Select One Gatepass","Please Select One Gatepass"));
		}
	}catch(Exception e)
	{
		e.printStackTrace();
		
	}


}
public void print()
{
try{
	new BondReleaseImpl().printReportexp1(this);
}	catch(Exception e)
{
	e.printStackTrace();
	
}
}


public void save()
{try{
	new BondReleaseImpl().save(this);
}	catch(Exception e)
{
	e.printStackTrace();
	
}
	}

public void reset()
{
	this.setDt_date(null);
	this.setInt_dist_id(0);
	this.setLading_dt(null);
	this.setGatepass_date(null);
	this.setBondValue(0.0);
	this.setWith_in_time("");
	this.days_diff=0;

}
public void reset1()
{
	this.setDt_date(null);
	this.setInt_dist_id(0);
	this.setLading_dt(null);
	this.setGatepass_date(null);
	this.setBondValue(0.0);
	this.setWith_in_time("");
	this.days_diff=0;
	this.vch_gatepass_no="0";

}
}
