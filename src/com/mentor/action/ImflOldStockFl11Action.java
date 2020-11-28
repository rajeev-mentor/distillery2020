package com.mentor.action;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;

import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.impl.ImflOldStockFL11Impl;




 


public class ImflOldStockFl11Action {

	ImflOldStockFL11Impl impl = new ImflOldStockFL11Impl();

	private String hidden;
	private int breId;
	private String breName;
	private String breAddress;
	private Date dt_date = new Date();
	private Date validtill_date;
	private String vch_from;
	private String vch_to;
	private String vch_from_lic_no;
	private String vch_to_lic_no;
	private String routeDtl;
	private String vehicleNo;
	private String vehicleDrvrName;
	private String vehicleAgencyNmAdrs;
	private double grossWeight = 0;
	private double tierWeight = 0;
	private double netWeight = 0;
	public String vch_bond = "1";
	public ArrayList displaylist = new ArrayList();
	public ArrayList fromLicList = new ArrayList();
	public ArrayList toLicList = new ArrayList();

	public String getVch_bond() {
		return vch_bond;
	}

	public void setVch_bond(String vch_bond) {
		this.vch_bond = vch_bond;
	}

	public String getHidden() {
		try {
			impl.getDetails(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	




	public int getBreId() {
		return breId;
	}

	public void setBreId(int breId) {
		this.breId = breId;
	}

	public String getBreName() {
		return breName;
	}

	public void setBreName(String breName) {
		this.breName = breName;
	}

	

	public String getBreAddress() {
		return breAddress;
	}

	public void setBreAddress(String breAddress) {
		this.breAddress = breAddress;
	}

	public Date getDt_date() {
		return dt_date;
	}

	public void setDt_date(Date dt_date) {
		this.dt_date = dt_date;
	}

	public Date getValidtill_date() {
		return validtill_date;
	}

	public void setValidtill_date(Date validtill_date) {
		this.validtill_date = validtill_date;
	}

	public String getVch_from() {
		return vch_from;
	}

	public void setVch_from(String vch_from) {
		this.vch_from = vch_from;
	}

	public String getVch_to() {
		return vch_to;
	}

	public void setVch_to(String vch_to) {
		this.vch_to = vch_to;
	}

	 

	public ArrayList getDisplaylist() {
		return displaylist;
	}

	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
	}

	 

 


	public ArrayList getFromLicList() {
		return fromLicList;
	}

	public void setFromLicList(ArrayList fromLicList) {
		this.fromLicList = fromLicList;
	}

	public ArrayList getToLicList() {
		return toLicList;
	}

	public void setToLicList(ArrayList toLicList) {
		this.toLicList = toLicList;
	}

	public String fromListMethod(ValueChangeEvent vce) {

		Object obj = vce.getNewValue();
		String s = (String) obj;

		this.displaylist = impl.displaylistImpl(this, s);

		return "";
	}

 
 

	public void saveMethod() {
		try {
			 
				impl.saveMethodImpl(this);
			 

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

 

	
}
