package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.mentor.impl.ReceivefromPlantGinImpl;
import com.mentor.utility.Validate;

public class ReceivefromPlantGinAction {

	ReceivefromPlantGinImpl impl = new ReceivefromPlantGinImpl();

	private String hidden;
	private String distillery_nm;
	private String distillery_adrs;
	private int Distillery_id;
	private Date dtDate = new Date();
	private double plant_Total_Balance_BL;
	private double plant_Total_balance_AL;
	private double received_Frm_Pant_Balance_BL;
	private double received_Frm_Pant_balance_AL;
	private String vat_id;
	private ArrayList vat_list = new ArrayList();
	private double quantity_Balance_BL;
	private double quantity_Balance_AL;
	private double quantity_Strength;
	private ArrayList showDataList = new ArrayList();

	public ArrayList getShowDataList() {
		this.showDataList = impl.showData(this);
		return showDataList;
	}

	public void setShowDataList(ArrayList showDataList) {
		this.showDataList = showDataList;
	}

	public String getHidden() {

		try {
			impl.getSugarmill(this);
			impl.getPlantTotalblAndAL(this);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getDistillery_nm() {
		return distillery_nm;
	}

	public void setDistillery_nm(String distillery_nm) {
		this.distillery_nm = distillery_nm;
	}

	public String getDistillery_adrs() {
		return distillery_adrs;
	}

	public void setDistillery_adrs(String distillery_adrs) {
		this.distillery_adrs = distillery_adrs;
	}

	public int getDistillery_id() {
		impl.getSugarmill(this);
		return Distillery_id;
	}

	public void setDistillery_id(int distillery_id) {
		Distillery_id = distillery_id;
	}

	public Date getDtDate() {
		return dtDate;
	}

	public void setDtDate(Date dtDate) {
		this.dtDate = dtDate;
	}

	public double getPlant_Total_Balance_BL() {
		return plant_Total_Balance_BL;
	}

	public void setPlant_Total_Balance_BL(double plant_Total_Balance_BL) {
		this.plant_Total_Balance_BL = plant_Total_Balance_BL;
	}

	public double getPlant_Total_balance_AL() {
		return plant_Total_balance_AL;
	}

	public void setPlant_Total_balance_AL(double plant_Total_balance_AL) {
		this.plant_Total_balance_AL = plant_Total_balance_AL;
	}

	public double getReceived_Frm_Pant_Balance_BL() {
		return received_Frm_Pant_Balance_BL;
	}

	public void setReceived_Frm_Pant_Balance_BL(
			double received_Frm_Pant_Balance_BL) {
		this.received_Frm_Pant_Balance_BL = received_Frm_Pant_Balance_BL;
	}

	public double getReceived_Frm_Pant_balance_AL() {
		return received_Frm_Pant_balance_AL;
	}

	public void setReceived_Frm_Pant_balance_AL(
			double received_Frm_Pant_balance_AL) {
		this.received_Frm_Pant_balance_AL = received_Frm_Pant_balance_AL;
	}

	public String getVat_id() {
		return vat_id;
	}

	public void setVat_id(String vat_id) {
		this.vat_id = vat_id;
	}

	public ArrayList getVat_list() {
		this.vat_list = impl.gettankdet(this,radio);
		if(this.vat_id!=null && this.vat_id.length()>0){
				impl.getStock(this);
		}
		return vat_list;
	}

	public void setVat_list(ArrayList vat_list) {
		this.vat_list = vat_list;
	}

	public double getQuantity_Balance_BL() {
		return quantity_Balance_BL;
	}

	public void setQuantity_Balance_BL(double quantity_Balance_BL) {
		this.quantity_Balance_BL = quantity_Balance_BL;
	}

	public double getQuantity_Balance_AL() {
		this.quantity_Balance_AL = this.quantity_Balance_BL
				* this.quantity_Strength / 100;
		return quantity_Balance_AL;
	}

	public void setQuantity_Balance_AL(double quantity_Balance_AL) {
		this.quantity_Balance_AL = quantity_Balance_AL;
	}

	public double getQuantity_Strength() {
		return quantity_Strength;
	}

	public void setQuantity_Strength(double quantity_Strength) {
		this.quantity_Strength = quantity_Strength;
	}

	// ----------- validation ----------------

	private boolean validateInput;

	public boolean isValidateInput() {
		validateInput = true;

		if (!(Validate.validateDate("mandatory", this.getDtDate())))
			validateInput = false;

		else if (!(Validate
				.validateStrReq("mandatory", this.getDistillery_nm())))
			validateInput = false;

		else if (!(Validate.validateDouble("PlantTotalBL",
				this.getPlant_Total_Balance_BL())))
			validateInput = false;

		else if (!(Validate.validateDouble("PlantTotalAL",
				this.getPlant_Total_balance_AL())))
			validateInput = false;

		else if (!(Validate.validateDouble("ReceivedFromPlantBL",
				this.getReceived_Frm_Pant_Balance_BL())))
			validateInput = false;

		else if (!(Validate.validateDouble("ReceivedFromPlantAL",
				this.getReceived_Frm_Pant_balance_AL())))
			validateInput = false;

		else if (!(Validate.validateStrReq("spiritvat", this.getVat_id())))
			validateInput = false;

		else if (!(Validate.validateDouble("QuantityBL",
				this.getQuantity_Balance_BL())))
			validateInput = false;

		else if (!(Validate.validateDouble("Quantitystrength",
				this.getQuantity_Strength())))
			validateInput = false;

		else if (!(Validate.validateDouble("QuantityAL",
				this.getQuantity_Balance_AL())))
			validateInput = false;

		else if (this.plant_Total_Balance_BL < this.received_Frm_Pant_Balance_BL
				|| this.plant_Total_balance_AL < this.received_Frm_Pant_balance_AL) {
			validateInput = false;
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Received Plant (BL) And Received Plant (AL) Less Than Or Equal Plant Total (BL) And (AL)  !!!",
									"Received Plant (BL) And Received Plant (AL) Less Than Or Equal Plant Total (BL) And (AL) !!!"));

		}

		/*
		 * else if (!(this.getValidDt().after(this.getDt()))) { validateInput =
		 * false; FacesContext.getCurrentInstance() .addMessage( null, new
		 * FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Valid Date !!!",
		 * "Invalid Valid Date !!!"));
		 */
		return validateInput;

	}

	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}

	// ----------------------------- save ---------------

	public void save() {
		if (isValidateInput()) {
			impl.saveDetail(this);
		}
	}

	public void reset() {

		this.dtDate = new Date();
		this.plant_Total_Balance_BL = 0.0;
		this.plant_Total_balance_AL = 0.0;
		this.received_Frm_Pant_Balance_BL = 0.0;
		this.received_Frm_Pant_balance_AL = 0.0;
		this.vat_id = null; radio="F";
		this.vat_list.clear();
		this.quantity_Balance_BL = 0.0;
		this.quantity_Balance_AL = 0.0;
		this.quantity_Strength = 0.0;

	}

	private String radio="F";

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public void switchProcess(ValueChangeEvent event) {
		Object s = event.getNewValue();
		String value = (String) s;
		
		this.vat_list = impl.gettankdet(this,radio);

	}

	

		private String vat_f;
		private String vat_to;





	public String getVat_f() {
		return vat_f;
	}

	public void setVat_f(String vat_f) {
		this.vat_f = vat_f;
	}

	public String getVat_to() {
		return vat_to;
	}

	public void setVat_to(String vat_to) {
		this.vat_to = vat_to;
	}
}
