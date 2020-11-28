package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.Datatable.HistoricalDataVerificationdt;
import com.mentor.impl.HistoricalDataVerificationImpl;

public class HistoricalDataVerificationAction {

	HistoricalDataVerificationImpl impl = new HistoricalDataVerificationImpl();

	private String hidden;
	private boolean viewdisflag = true;
	private String viewflg = "F";
	private String Dist_name;
	private int Dist_id;

	private boolean flag = false;
	private String radio = "S";
	private String vch_bond;
	private int current_balance;
	private int bond_utilization_value;
	private String distillery_adrs;
	private Date ship_date;
	private String ship_number;

	private Date brc_dt;
	private int brc_no;
	private int objection_id;
	private String obj_int_id;
	private Object fillComment;
	private int Int_id;
	private String Pdf_Nmae;
	private Date created_date;
	private boolean objviewflg;
	private boolean objviewflg1;
	private Date riceipt_date;
	private String getpass_no;
	private String RecordFile;
	private String RecordFile1;

	public String getRecordFile() {
		return RecordFile;
	}

	public void setRecordFile(String recordFile) {
		RecordFile = recordFile;
	}

	public String getRecordFile1() {
		return RecordFile1;
	}

	public void setRecordFile1(String recordFile1) {
		RecordFile1 = recordFile1;
	}

	private int brc_no_bottel;
	private int ship_no_of_bottel;

	public int getBrc_no_bottel() {
		return brc_no_bottel;
	}

	public void setBrc_no_bottel(int brc_no_bottel) {
		this.brc_no_bottel = brc_no_bottel;
	}


	public String getDistillery_adrs() {
		return distillery_adrs;
	}

	public void setDistillery_adrs(String distillery_adrs) {
		this.distillery_adrs = distillery_adrs;
	}

	
	public Date getShip_date() {
		return ship_date;
	}

	public void setShip_date(Date ship_date) {
		this.ship_date = ship_date;
	}

	public String getShip_number() {
		return ship_number;
	}

	public void setShip_number(String ship_number) {
		this.ship_number = ship_number;
	}

	public int getShip_no_of_bottel() {
		return ship_no_of_bottel;
	}

	public void setShip_no_of_bottel(int ship_no_of_bottel) {
		this.ship_no_of_bottel = ship_no_of_bottel;
	}

	public Date getBrc_dt() {
		return brc_dt;
	}

	public void setBrc_dt(Date brc_dt) {
		this.brc_dt = brc_dt;
	}

	public int getBrc_no() {
		return brc_no;
	}

	public void setBrc_no(int brc_no) {
		this.brc_no = brc_no;
	}

	public int getObjection_id() {
		return objection_id;
	}

	public void setObjection_id(int objection_id) {
		this.objection_id = objection_id;
	}

	public String getObj_int_id() {
		return obj_int_id;
	}

	public void setObj_int_id(String obj_int_id) {
		this.obj_int_id = obj_int_id;
	}

	public Object getFillComment() {
		return fillComment;
	}

	public void setFillComment(Object fillComment) {
		this.fillComment = fillComment;
	}

	public int getInt_id() {
		return Int_id;
	}

	public void setInt_id(int int_id) {
		Int_id = int_id;
	}

	public String getPdf_Nmae() {
		return Pdf_Nmae;
	}

	public void setPdf_Nmae(String pdf_Nmae) {
		Pdf_Nmae = pdf_Nmae;
	}

	public Date getRiceipt_date() {
		return riceipt_date;
	}

	public void setRiceipt_date(Date riceipt_date) {
		this.riceipt_date = riceipt_date;
	}

	public String getVch_bond() {
		return vch_bond;
	}

	public void setVch_bond(String vch_bond) {
		this.vch_bond = vch_bond;
	}

	public int getCurrent_balance() {
		return current_balance;
	}

	public void setCurrent_balance(int current_balance) {
		this.current_balance = current_balance;
	}

	public int getBond_utilization_value() {
		return bond_utilization_value;
	}

	public void setBond_utilization_value(int bond_utilization_value) {
		this.bond_utilization_value = bond_utilization_value;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void GrtpassNo() {
		// impl.GetpassNo(this, prt);
	}

	public String getGetpass_no() {
		return getpass_no;
	}

	public void setGetpass_no(String getpass_no) {
		this.getpass_no = getpass_no;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public boolean isObjviewflg() {
		return objviewflg;
	}

	public void setObjviewflg(boolean objviewflg) {
		this.objviewflg = objviewflg;
	}

	public boolean isObjviewflg1() {
		return objviewflg1;
	}

	public void setObjviewflg1(boolean objviewflg1) {
		this.objviewflg1 = objviewflg1;
	}

	public String getViewflg() {
		return viewflg;
	}

	public void setViewflg(String viewflg) {
		this.viewflg = viewflg;
	}

	public String getDist_name() {
		return Dist_name;
	}

	public void setDist_name(String dist_name) {
		Dist_name = dist_name;
	}

	public int getDist_id() {
		return Dist_id;
	}

	public void setDist_id(int dist_id) {
		Dist_id = dist_id;
	}

	public String getHidden() {

		try {
			impl.getdetails(this);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public boolean isViewdisflag() {
		return viewdisflag;
	}

	public void setViewdisflag(boolean viewdisflag) {
		this.viewdisflag = viewdisflag;

	}

	private ArrayList viewdetail1;

	public void viewdetail(ActionEvent ae) {

		UIDataTable uiTable = (UIDataTable) ae.getComponent().getParent()
				.getParent();
		HistoricalDataVerificationdt dt = (HistoricalDataVerificationdt) this
				.getShowData().get(uiTable.getRowIndex());
		try {

			this.setGatapassno(dt.getGetpass_no());
			impl.chkgetpass(this, prt);
			viewdetail1 = impl.viewdetail(this, dt);
			impl.GetpassDetailShow(this, dt);

			// impl.GetpassNo(this,prt);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * public void viewdetail(){
	 * 
	 * try{ this.setSave_flg(null);
	 * 
	 * impl.chkgetpass(this, prt); viewdetail1=impl.viewdetail(this,prt);
	 * impl.GetpassNo(this,prt); } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */
	public ArrayList getViewdetail1() {
		return viewdetail1;
	}

	public void setViewdetail1(ArrayList viewdetail1) {
		this.viewdetail1 = viewdetail1;
	}

	private ArrayList showData;

	public ArrayList getShowData() {
		if (this.flag == false) {
			try {
				impl.chkgetpass(this, prt);
				this.showData = impl.showData(this);

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		return showData;
	}

	HistoricalDataVerificationdt prt;

	public HistoricalDataVerificationdt getPrt() {
		return prt;
	}

	public void setPrt(HistoricalDataVerificationdt prt) {
		this.prt = prt;
	}

	public void setShowData(ArrayList showData) {
		this.showData = showData;
	}

	private String Gatapassno;

	public String getGatapassno() {
		return Gatapassno;
	}

	public void setGatapassno(String gatapassno) {
		Gatapassno = gatapassno;
	}

	public void close() {
		this.viewflg = "F";
		this.created_date = null;
		this.bond_utilization_value = 0;
		this.vch_bond = null;
		this.current_balance = 0;
	}

	private boolean objsave_flg;

	public boolean isObjsave_flg() {
		return objsave_flg;
	}

	public void setObjsave_flg(boolean objsave_flg) {
		this.objsave_flg = objsave_flg;
	}

	private String save_flg;

	public String getSave_flg() {
		return save_flg;
	}

	public void setSave_flg(String save_flg) {
		this.save_flg = save_flg;
	}

	public void radioListener1(ValueChangeEvent e) {

		try {
			String val = (String) e.getNewValue();

			this.viewflg = "F";
			this.setRadio(val);
			this.showData = impl.showData(this);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	// /--------------------------------------
	// Save------------------------------------////

	public void save() {
		HistoricalDataVerificationdt dt = new HistoricalDataVerificationdt();
		try {
			if (this.vch_bond != null && this.current_balance != 0
					&& this.bond_utilization_value != 0) {
				impl.VerifyData(this);
				this.showData = impl.showData(this);
				this.viewflg = "F";
				

			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"All Fields Are Mandotry!!",
								"All Fields Are Mandotry!!"));

			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	private String currency;


	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	private String amount;

}
