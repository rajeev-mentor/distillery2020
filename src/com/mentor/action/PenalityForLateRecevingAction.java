package com.mentor.action;
import java.util.*;

import javax.faces.event.ActionEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.Datatable.PenalityForLateRecevingDT;
import com.mentor.impl.PenalityForLateRecevingImpl;
import com.mentor.utility.ResourceUtil;

public class PenalityForLateRecevingAction {
	
	PenalityForLateRecevingImpl impl= new PenalityForLateRecevingImpl();
	private String gatepass_no;
	private String laading_date;
	private double bond_value;
	private double penality;
	private String radio="P";
	private boolean viewflag;
	private int no_of_days;
    private boolean btnflg;
	
	public boolean isBtnflg() {
		if(ResourceUtil.getUserNameAllReq().substring(0, 9).equalsIgnoreCase("Excise-DL")){
			this.btnflg=true;
		}else{
			this.btnflg=false;
		}
		return btnflg;
	}
	public void setBtnflg(boolean btnflg) {
		this.btnflg = btnflg;
	}
	public int getNo_of_days() {
		return no_of_days;
	}
	public void setNo_of_days(int no_of_days) {
		this.no_of_days = no_of_days;
	}
	public boolean isViewflag() {
		return viewflag;
	}
	public void setViewflag(boolean viewflag) {
		this.viewflag = viewflag;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	private ArrayList datalist = new ArrayList();
	public String getGatepass_no() {
		return gatepass_no;
	}
	public void setGatepass_no(String gatepass_no) {
		this.gatepass_no = gatepass_no;
	}
	public String getLaading_date() {
		return laading_date;
	}
	public void setLaading_date(String laading_date) {
		this.laading_date = laading_date;
	}
	public double getBond_value() {
		return bond_value;
	}
	public void setBond_value(double bond_value) {
		this.bond_value = bond_value;
	}
	public double getPenality() {
		return penality;
	}
	public void setPenality(double penality) {
		this.penality = penality;
	}
	public ArrayList getDatalist() {
		this.datalist=impl.gatepassListImpl(this);
		return datalist;
	}
	public void setDatalist(ArrayList datalist) {
		this.datalist = datalist;
	}
	

	public void view(ActionEvent e){
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		PenalityForLateRecevingDT dt = (PenalityForLateRecevingDT) this.getDatalist()
				.get(uiTable.getRowIndex());
		
		this.setGatepass_no(dt.getGatepass());
		this.laading_date=dt.getDate_of_lading();
		this.bond_value=dt.getBond_value();
		this.no_of_days=dt.getNo_of_days();
		this.penality=dt.getPenality_value();
		this.viewflag=true;
			
}
	public void forward(){
		impl.forwrdImpl(this);
	}
	
    public void approve(){
		impl.approveImpl(this);
	}
    public void back(){
		this.viewflag=false;
	}
}
