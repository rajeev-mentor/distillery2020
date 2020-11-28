package com.mentor.action;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.mentor.impl.TransferOfGIN_betweenStorageVatImpl;
import com.mentor.utility.Validate;

public class TransferOfGIN_betweenStorageVatAction {
      

	TransferOfGIN_betweenStorageVatImpl impl = new TransferOfGIN_betweenStorageVatImpl();
	
	
	private ArrayList dataTable = new ArrayList();
	private String dissteleryName;
	private String disslryAdd;
	private Date cueentDate = new Date();
	private String vatNo;
	private double quantityFinal;
	private double quantityFinalal;
	private double purchasePayble;
	private boolean validate;
	private ArrayList showDataTablelist = new ArrayList();
	private double produced_bl;
	private ArrayList tankNoList = new ArrayList();
	public boolean validateInput;

	private double wastageAl = 0.0;
	private double wastageBl = 0.0;
	private double netAl = 0.0;
	private double netBl = 0.0;

	private ArrayList toVatList = new ArrayList();
	private String toVatId;
	private double toVatBl = 0.0;
	private double toVatAl = 0.0;

	public ArrayList getToVatList() {
		this.toVatList = new TransferOfGIN_betweenStorageVatImpl().getToVat(this,
				dissleryId);
		new TransferOfGIN_betweenStorageVatImpl().getStock(this);
		return toVatList;
	}

	public void setToVatList(ArrayList toVatList) {
		this.toVatList = toVatList;
	}

	public String getToVatId() {
		return toVatId;
	}

	public void setToVatId(String toVatId) {
		this.toVatId = toVatId;
	}

	public double getToVatBl() {

		return toVatBl;
	}

	public void setToVatBl(double toVatBl) {
		this.toVatBl = toVatBl;
	}

	public double getToVatAl() {
		return toVatAl;
	}

	public void setToVatAl(double toVatAl) {
		this.toVatAl = toVatAl;
	}

	public double getWastageAl() {

		this.wastageAl = this.quntityForDinaturingstregth * this.wastageBl
				/ 100;

		return wastageAl;
	}

	public void setWastageAl(double wastageAl) {
		this.wastageAl = wastageAl;
	}

	public double getWastageBl() {
		return wastageBl;
	}

	public void setWastageBl(double wastageBl) {
		this.wastageBl = wastageBl;
	}

	public double getNetAl() {
		this.netAl = this.quntityForDinaturingal - this.wastageAl;
		return netAl;
	}

	public void setNetAl(double netAl) {
		this.netAl = netAl;
	}

	public double getNetBl() {
		this.netBl = this.quntityForDinaturingbl - this.wastageBl;
		return netBl;
	}

	public void setNetBl(double netBl) {
		this.netBl = netBl;
	}

	public boolean isValidateInput() {

		this.validateInput = true;
		if (!(Validate.validateDate("date", this.cueentDate)))
			this.validateInput = false;
		// else if(!(Validate.validateStrReq("season",
		// this.session)))this.validate=false;
		else if (!(Validate.validateStrReq("fromvat", this.vatNo)))
			this.validateInput = false;
		else if (!(Validate.validateDoubleValue("quantitybl",
				this.quantityFinal)))
			this.validateInput = false;
		else if (!(Validate.validateDoubleValue("quantityal",
				this.quantityFinalal)))
			this.validateInput = false;

		else if (!(Validate.validateStrReq("tovat", this.toVatId)))
			this.validateInput = false;
		else if (this.to_dip_bl == 0.0 && this.to_dip_al == 0.0) {
			this.validateInput = false;
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							" Vat From Dip Al & Bl  cannot be Zero !",
							"Dip Al & Bl  cannot be Zero !"));

		}
		/*
		 * else if (this.from_dip_bl == 0.0 && this.from_dip_al == 0.0 ) {
		 * this.validateInput = false;
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(FacesMessage
		 * .SEVERITY_ERROR,"To Vat Dip Al & Bl  cannot be Zero !",
		 * "Dip Al & Bl  cannot be Zero !"));
		 * 
		 * }
		 */
		// else if(!(Validate.validateDoubleValue("quantitybl",
		// this.toVatBl)))this.validateInput=false;
		// else if(!(Validate.validateDoubleValue("quantityal",
		// this.toVatAl)))this.validateInput=false;
		/*
		 * else if (!(Validate.validateDoubleValue("QuantityTobeTransferBL",
		 * this.quntityForDinaturingbl))) this.validateInput = false; else if
		 * (!(Validate.validateDoubleValue("Strength",
		 * this.quntityForDinaturingstregth))) this.validateInput = false; else
		 * if (!(Validate.validateDoubleValue("QuantityTobeTransferAL",
		 * this.quntityForDinaturingal))) this.validateInput = false;
		 * 
		 * // else if(!(Validate.validateDoubleValue("denaturingfee", //
		 * this.dutyPayble)))this.validateInput=false; // else
		 * if(!(Validate.validateStrReq("vatfordenaturingspirit", //
		 * this.tankNo)))this.validateInput=false; // else
		 * if(!(Validate.validateDoubleValue("producedbl", //
		 * this.produced)))this.validateInput=false; // else
		 * if(!(Validate.validateDouble("strength", //
		 * this.strength)))this.validateInput=false;
		 * 
		 * else if (!(Validate.validateDouble("wastage_bl",
		 * this.getWastageBl()))) validateInput = false; else if
		 * (!(Validate.validateDouble("wastage_al", this.getWastageAl())))
		 * validateInput = false; else if (!(Validate.validateDouble("net_bl",
		 * this.getNetBl()))) validateInput = false; else if
		 * (!(Validate.validateDouble("net_al", this.getNetAl()))) validateInput
		 * = false;
		 */

		return this.validateInput;
	}

	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}

	public double getQuantityFinalal() {
		return quantityFinalal;
	}

	public void setQuantityFinalal(double quantityFinalal) {
		this.quantityFinalal = quantityFinalal;
	}

	public ArrayList getTankNoList() {
		TransferOfGIN_betweenStorageVatImpl impl = new TransferOfGIN_betweenStorageVatImpl();
		tankNoList = impl.getDenatureSpritList(this, dissleryId);
		return tankNoList;
	}

	public void setTankNoList(ArrayList tankNoList) {
		this.tankNoList = tankNoList;
	}

	public double getProduced_bl() {
		return produced_bl;
	}

	public void setProduced_bl(double produced_bl) {
		this.produced_bl = produced_bl;
	}

	public ArrayList getShowDataTablelist() {
		this.showDataTablelist = new TransferOfGIN_betweenStorageVatImpl()
				.getShowData(this);
		return showDataTablelist;
	}

	public void setShowDataTablelist(ArrayList showDataTablelist) {
		this.showDataTablelist = showDataTablelist;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public double getPurchasePayble() {
		return purchasePayble;
	}

	public void setPurchasePayble(double purchasePayble) {
		this.purchasePayble = purchasePayble;
	}

	public double getQuantityFinal() {
		return quantityFinal;
	}

	public void setQuantityFinal(double quantityFinal) {
		this.quantityFinal = quantityFinal;
	}

	public ArrayList getVatNoList() {
		vatNoList = new TransferOfGIN_betweenStorageVatImpl().getVatList(this,
				dissleryId);
		return vatNoList;
	}

	public void tanklistnerMF4(ValueChangeEvent ae) {
		try {
			String val = (String) ae.getNewValue();
			this.setToVatId(val);
			System.out.println("------getToVatId------"+this.getToVatId());
			TransferOfGIN_betweenStorageVatImpl impl = new TransferOfGIN_betweenStorageVatImpl();
			impl.getQuantity(this, val);

		} catch (Exception e) {

		}
	}

	public void tanklistnerMFSeconend(ValueChangeEvent ae) {
		try {

			String val1 = (String) ae.getNewValue();
			TransferOfGIN_betweenStorageVatImpl impl = new TransferOfGIN_betweenStorageVatImpl();
			impl.getQuantitySeconend(this, val1);
			this.dataflag = true;

		} catch (Exception e) {

		}
	}

	public String onchanegeMethod(ValueChangeEvent event) {
		this.flag = false;
		Object obj = event.getNewValue();
		Date date = (Date) obj;
		// this.vatNoList=new
		// RemovalSipiritDorDinatureImpl().getVatList(dissleryId);
		return "";
	}

	public void setVatNoList(ArrayList vatNoList) {
		this.vatNoList = vatNoList;
	}

	private int dissleryId;
	private ArrayList vatNoList = new ArrayList();

	public int getDissleryId() {
		return dissleryId;
	}

	public void setDissleryId(int dissleryId) {
		this.dissleryId = dissleryId;
	}

	public String getDissteleryName() {
		new TransferOfGIN_betweenStorageVatImpl().getSugarmill(this);
		return dissteleryName;
	}

	public void setDissteleryName(String dissteleryName) {
		this.dissteleryName = dissteleryName;
	}

	public String getDisslryAdd() {
		return disslryAdd;
	}

	public Date getCueentDate() {
		return cueentDate;
	}

	public void setDisslryAdd(String disslryAdd) {
		this.disslryAdd = disslryAdd;
	}

	public void setCueentDate(Date cueentDate) {
		this.cueentDate = cueentDate;
	}

	public String getVatNo() {
		return vatNo;
	}

	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getDutyPayble() {
		return dutyPayble;
	}

	public void setDutyPayble(double dutyPayble) {
		this.dutyPayble = dutyPayble;
	}

	private double quantity;
	private double quntityForDinaturingal;
	private double quntityForDinaturingbl;
	private double quntityForDinaturingstregth;
	private double dutyPayble;
	private boolean flag = true;

	public double getQuntityForDinaturingal() {
		try {

			NumberFormat formatter = new DecimalFormat("#0.00");

			// quntityForDinaturingal = (quntityForDinaturingbl *
			// quntityForDinaturingstregth) / 100;
			quntityForDinaturingal = Double
					.parseDouble(formatter
							.format((quntityForDinaturingbl * quntityForDinaturingstregth) / 100));

		} catch (Exception e) {
			// TODO: handle exception
		}
		return quntityForDinaturingal;
	}

	public void setQuntityForDinaturingal(double quntityForDinaturingal) {
		this.quntityForDinaturingal = quntityForDinaturingal;
	}

	public double getQuntityForDinaturingbl() {
		return quntityForDinaturingbl;
	}

	public void setQuntityForDinaturingbl(double quntityForDinaturingbl) {
		this.quntityForDinaturingbl = quntityForDinaturingbl;
	}

	public double getQuntityForDinaturingstregth() {
		return quntityForDinaturingstregth;
	}

	public void setQuntityForDinaturingstregth(
			double quntityForDinaturingstregth) {
		this.quntityForDinaturingstregth = quntityForDinaturingstregth;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setDataTable(ArrayList dataTable) {
		this.dataTable = dataTable;
	}

	public String saveMethod() {

		if (this.recv_quntity_bl <= this.tank_capacity) {
			new TransferOfGIN_betweenStorageVatImpl().saveData(this);
		} else {

			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									"Recieved Quantity  Should be Less Than or Equal To ToVat Capacity",
									"Recieved Quantity  Should be Less Than or Equal To ToVat Capacity"));
		}

		/*
		 * if (this.isValidateInput()) { if ((this.tranfr_quntity_al <=
		 * this.quantityFinalal) && (this.tranfr_quntity_bl <=
		 * this.quantityFinal)) { if(this.tranfr_quntity_bl>this.trns_wst_bl){
		 * new TransferOfRemovalSpritVatImpl().saveData(this);
		 * 
		 * }else{ FacesContext.getCurrentInstance().addMessage( null,new
		 * FacesMessage( "Wastage BL Should be Less Than Transfer BL",
		 * "Wastage BL Should be Less Than Transfer BL")); }
		 * 
		 * } else { FacesContext.getCurrentInstance().addMessage(null,new
		 * FacesMessage(
		 * "Quantity To be Transfer B.L & A.L Less Than From Vat Quantity B.L & A.L "
		 * ,
		 * "Quantity To be Transfer B.L & A.L Less Than From Vat Quantity B.L & A.L"
		 * ));
		 * 
		 * } }
		 */
		return "";
	}

	public String reset() {
		this.vatNoList.clear();
		this.toVatList.clear();
		this.cueentDate = new Date();
		this.vatNo = "";
		// this.dataTable.clear();
		this.dutyPayble = 0.0;
		this.purchasePayble = 0.0;
		this.quantity = 0.0;
		this.quantityFinal = 0.0;
		this.quntityForDinaturingal = 0.0;
		this.quntityForDinaturingbl = 0.0;
		this.quntityForDinaturingstregth = 0.0;
		this.produced = 0.0;
		this.strength = 0.0;
		this.tankNo = "";
		this.quntityAl = 0.0;
		this.quantityFinalal = 0.0;

		this.wastageAl = 0.0;
		this.wastageBl = 0.0;
		this.netAl = 0.0;
		this.netBl = 0.0;

		this.toVatId = "";
		this.toVatBl = 0.0;
		this.toVatAl = 0.0;
		this.from_dip_bl = 0.0;
		this.from_dip_al = 0.0;
		this.to_dip_bl = 0.0;
		this.to_dip_al = 0.0;
		this.tranfr_quntity_bl = 0.0;
		this.tranfr_quntity_al = 0.0;
		this.recv_quntity_al = 0.0;
		this.recv_quntity_bl = 0.0;
		this.trns_wst_bl = 0.0;
		this.trns_wst_al = 0.0;
		this.toVatAlStrength = 0.0;
		this.quantityStrength = 0.0;
		// this. wastage=0.0;
		// this. netBl1=0.0;

		return "";
	}

	private double strength = 0.0;
	private double produced = 0.0;
	private double quntityAl = 0.0;
	private String tankNo;

	public double getStrength() {
		return strength;
	}

	public void setStrength(double strength) {
		this.strength = strength;
	}

	public double getProduced() {
		return produced;
	}

	public void setProduced(double produced) {
		this.produced = produced;
	}

	public double getQuntityAl() {
		try {
			quntityAl = (produced * strength) / 100;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return quntityAl;
	}

	public void setQuntityAl(double quntityAl) {
		this.quntityAl = quntityAl;
	}

	public String getTankNo() {
		return tankNo;
	}

	public void setTankNo(String tankNo) {
		this.tankNo = tankNo;
	}

	// -------------------------------------------

	private String radio = "C";

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public void radioVal(ValueChangeEvent ee) {

		String val = (String) ee.getNewValue();
		this.setRadio(val);

	}

	private double from_dip_bl;
	private double from_dip_al;
	private double to_dip_bl;
	private double to_dip_al;
	private double tranfr_quntity_bl;
	private double tranfr_quntity_al;
	private double recv_quntity_al;
	private double recv_quntity_bl;
	private double trns_wst_bl;
	private double trns_wst_al;
	private double toVatAlStrength = 0.0;
	private double quantityStrength = 0.0;

	public double getFrom_dip_bl() {
		return from_dip_bl;
	}

	public void setFrom_dip_bl(double from_dip_bl) {
		this.from_dip_bl = from_dip_bl;
	}

	public double getFrom_dip_al() {
		return from_dip_al;
	}

	public void setFrom_dip_al(double from_dip_al) {
		this.from_dip_al = from_dip_al;
	}

	public double getTo_dip_bl() {
		return to_dip_bl;
	}

	public void setTo_dip_bl(double to_dip_bl) {
		this.to_dip_bl = to_dip_bl;
	}

	public double getTo_dip_al() {
		return to_dip_al;
	}

	public void setTo_dip_al(double to_dip_al) {
		this.to_dip_al = to_dip_al;
	}

	public double getTranfr_quntity_bl() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		this.tranfr_quntity_bl = Double.parseDouble(formatter
				.format(this.quantityFinal - this.from_dip_bl));
		System.out.println("this.tranfr_quntity_bl " + this.tranfr_quntity_bl);
		this.setCosum_al(this.tranfr_quntity_al);
		return tranfr_quntity_bl;
	}

	public void setTranfr_quntity_bl(double tranfr_quntity_bl) {
		this.tranfr_quntity_bl = tranfr_quntity_bl;
	}

	public double getTranfr_quntity_al() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		this.tranfr_quntity_al = Double.parseDouble(formatter
				.format(this.quantityFinalal - this.from_dip_al));
		return tranfr_quntity_al;
	}

	public void setTranfr_quntity_al(double tranfr_quntity_al) {
		this.tranfr_quntity_al = tranfr_quntity_al;
	}

	public double getRecv_quntity_al() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		this.recv_quntity_al = Double.parseDouble(formatter
				.format(this.to_dip_al - this.toVatAl));
		return recv_quntity_al;
	}

	public void setRecv_quntity_al(double recv_quntity_al) {
		this.recv_quntity_al = recv_quntity_al;
	}

	public double getRecv_quntity_bl() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		this.recv_quntity_bl = Double.parseDouble(formatter
				.format(this.to_dip_bl - this.toVatBl));
		return recv_quntity_bl;
	}

	public void setRecv_quntity_bl(double recv_quntity_bl) {
		this.recv_quntity_bl = recv_quntity_bl;
	}

	public double getTrns_wst_bl() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		this.trns_wst_bl = Double.parseDouble(formatter
				.format(this.tranfr_quntity_bl - this.recv_quntity_bl));
		return trns_wst_bl;
	}

	public void setTrns_wst_bl(double trns_wst_bl) {
		this.trns_wst_bl = trns_wst_bl;
	}

	public double getTrns_wst_al() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		this.trns_wst_al = Double.parseDouble(formatter
				.format(this.tranfr_quntity_al - this.recv_quntity_al));
		return trns_wst_al;
	}

	public void setTrns_wst_al(double trns_wst_al) {
		this.trns_wst_al = trns_wst_al;
	}

	public double getToVatAlStrength() {
		try {
			NumberFormat formatter = new DecimalFormat("#0.00");

			// quntityForDinaturingal = (quntityForDinaturingbl *
			// quntityForDinaturingstregth) / 100;

			toVatAlStrength = Double.parseDouble(formatter
					.format((toVatAl * 100) / toVatBl));

		} catch (Exception e) {
			// TODO: handle exception
		}
		return toVatAlStrength;
	}

	public void setToVatAlStrength(double toVatAlStrength) {
		this.toVatAlStrength = toVatAlStrength;
	}

	public double getQuantityStrength() {
		try {
			NumberFormat formatter = new DecimalFormat("#0.00");

			// quntityForDinaturingal = (quntityForDinaturingbl *
			// quntityForDinaturingstregth) / 100;

			quantityStrength = Double.parseDouble(formatter
					.format((quantityFinalal * 100) / quantityFinal));

		} catch (Exception e) {
			// TODO: handle exception
		}
		return quantityStrength;
	}

	public void setQuantityStrength(double quantityStrength) {
		this.quantityStrength = quantityStrength;
	}

	// ----------------
	private double tank_capacity;

	public double getTank_capacity() {
		return tank_capacity;
	}

	public void setTank_capacity(double tank_capacity) {
		this.tank_capacity = tank_capacity;
	}

	private String popup4Hidden;
	private String vat_f;
	private String vat_to;

	public String getPopup4Hidden() {
		try {
			if (isDataflag()) {

				// /this.setCosum_al(this.tranfr_quntity_al);
				this.setCosum_bl(this.tranfr_quntity_bl);
				this.setRecv_al(this.recv_quntity_al);
				this.setRecv_bl(this.recv_quntity_bl);
				this.setWst_al(this.wastageAl);
				this.setWst_bl(this.wastageBl);
				System.out.println("====tranfr_quntity_bl=="
						+ this.getCosum_al());
				System.out.println("===tranfr_quntity_al==="
						+ this.tranfr_quntity_al);
				System.out.println("==recv_quntity_al===="
						+ this.recv_quntity_al);
				System.out.println("=recv_quntity_bl====="
						+ this.recv_quntity_bl);
				System.out.println("=wastageAl====" + this.wastageAl);
				System.out.println("==wastageBl===" + this.wastageBl);
				/*
				 * this.cosum_bl=this.tranfr_quntity_bl;
				 * this.recv_al=this.recv_quntity_al;
				 * this.recv_bl=this.recv_quntity_bl;
				 * this.wst_al=this.wastageAl; this.wst_bl=this.wastageBl;
				 */
				new TransferOfGIN_betweenStorageVatImpl().getStock(this);
				this.dataflag = false;
			}

		} catch (Exception e) {

		}
		return popup4Hidden;
	}

	public void setPopup4Hidden(String popup4Hidden) {
		this.popup4Hidden = popup4Hidden;
	}

	public String getVat_f() {
		/*
		 * if(isDataflag()){ this.cosum_al=this.tranfr_quntity_al;
		 * this.cosum_bl=this.tranfr_quntity_bl;
		 * this.recv_al=this.recv_quntity_al; this.recv_bl=this.recv_quntity_bl;
		 * this.wst_al=this.wastageAl; this.wst_bl=this.wastageBl; }
		 */
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

	private double cosum_al;
	private double cosum_bl;
	private double recv_al;
	private double recv_bl;
	private double wst_al;
	private double wst_bl;

	public double getCosum_al() {

		// this.cosum_al=this.quantityFinalal-this.from_dip_al;

		return cosum_al;
	}

	public void setCosum_al(double cosum_al) {
		this.cosum_al = cosum_al;
	}

	public double getCosum_bl() {
		// this.cosum_bl=this.quantityFinal-this.from_dip_bl;
		return cosum_bl;
	}

	public void setCosum_bl(double cosum_bl) {
		this.cosum_bl = cosum_bl;
	}

	public double getRecv_al() {
		// this.recv_al=this.to_dip_al-this.toVatAl;
		return recv_al;
	}

	public void setRecv_al(double recv_al) {
		this.recv_al = recv_al;
	}

	public double getRecv_bl() {
		// this.recv_bl=this.to_dip_bl-this.toVatBl;
		return recv_bl;
	}

	public void setRecv_bl(double recv_bl) {
		this.recv_bl = recv_bl;
	}

	public double getWst_al() {
		// this.wst_al = this.quntityForDinaturingstregth * this.wastageBl/ 100;
		return wst_al;
	}

	public void setWst_al(double wst_al) {
		this.wst_al = wst_al;
	}

	public double getWst_bl() {
		// this.wst_bl=this.wastageBl;
		return wst_bl;
	}

	public void setWst_bl(double wst_bl) {
		this.wst_bl = wst_bl;
	}

	public ArrayList getDataTable() {
		return dataTable;
	}

	private boolean dataflag = false;

	public boolean isDataflag() {
		return dataflag;
	}

	public void setDataflag(boolean dataflag) {
		this.dataflag = dataflag;
	}

}
