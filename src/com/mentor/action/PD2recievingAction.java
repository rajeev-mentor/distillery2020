package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.mentor.Datatable.PD2recievingDt;
import com.mentor.impl.GatepassForBottolingImpl;
import com.mentor.impl.PD2recievingImpl; 

public class PD2recievingAction {
	
	
	PD2recievingImpl impl =new PD2recievingImpl();

	ArrayList showData = new ArrayList();
	ArrayList saveData = new ArrayList();
	public ArrayList getSaveData() {
		
		this.saveData = impl.getsaveData(this);
		return saveData;
	}


	public void setSaveData(ArrayList saveData) {
		this.saveData = saveData;
	}

	private boolean listFlagForPrint=true;
	private String dissteleryName;
	private String hidden;
	private int dissleryId;
	private String disslryAdd;
	private Date current_date=new Date();
	private int dt_al;
	private int dt_bl;
	private int dt_strength;
	private boolean listFlagquant=true;
	private boolean listFlagFortovat=true;
	private boolean hiddenFlag=true;
	
	public boolean isHiddenFlag() {
		return hiddenFlag;
	}


	public void setHiddenFlag(boolean hiddenFlag) {
		this.hiddenFlag = hiddenFlag;
	}


	public boolean isListFlagquant() {
		return listFlagquant;
	}


	public void setListFlagquant(boolean listFlagquant) {
		this.listFlagquant = listFlagquant;
	}


	public boolean isListFlagFortovat() {
		return listFlagFortovat;
	}


	public void setListFlagFortovat(boolean listFlagFortovat) {
		this.listFlagFortovat = listFlagFortovat;
	}

	private String radio;
    public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public void radioVal(ValueChangeEvent ee) {
		
		String val = (String) ee.getNewValue();
		this.setRadio(val);		
		this.toVatList = new PD2recievingImpl()
		.getToVat(this,val);
	}
	
	public int getDt_al() {
		return dt_al;
	}


	public void setDt_al(int dt_al) {
		this.dt_al = dt_al;
	}


	public int getDt_bl() {
		return dt_bl;
	}


	public void setDt_bl(int dt_bl) {
		this.dt_bl = dt_bl;
	}


	public int getDt_strength() {
		return dt_strength;
	}


	public void setDt_strength(int dt_strength) {
		this.dt_strength = dt_strength;
	}


	public Date getCurrent_date() {
		return current_date;
	}


	public void setCurrent_date(Date current_date) {
		this.current_date = current_date;
	}


	public String getDissteleryName() {
		return dissteleryName;
	}


	public void setDissteleryName(String dissteleryName) {
		this.dissteleryName = dissteleryName;
	}


	public String getHidden() {
		if (isHiddenFlag()){
		System.out.println("========hidden======");
		new PD2recievingImpl().getSugarmill(this);
		}
		return hidden;
	}


	public void setHidden(String hidden) {
		this.hidden = hidden;
	}


	public int getDissleryId() {
		return dissleryId;
	}


	public void setDissleryId(int dissleryId) {
		this.dissleryId = dissleryId;
	}


	public String getDisslryAdd() {
		return disslryAdd;
	}


	public void setDisslryAdd(String disslryAdd) {
		this.disslryAdd = disslryAdd;
	}


	public boolean isListFlagForPrint() {
		return listFlagForPrint;
	}


	public void setListFlagForPrint(boolean listFlagForPrint) {
		this.listFlagForPrint = listFlagForPrint;
	}


	public ArrayList getShowData() {
	
		if (isListFlagForPrint()) 
		{
			this.showData = impl.getData(this);
			//this.listFlagForPrint = false;
		}
		return showData;
	}


	public void setShowData(ArrayList showData) {
		this.showData = showData;
	}
	
	private String toVatId;


	public String getToVatId() {
		return toVatId;
	}


	public void setToVatId(String toVatId) {
		this.toVatId = toVatId;
	}

	private ArrayList toVatList = new ArrayList();

	
	

	

/*
	public int getToVatId() {
		return toVatId;
	}


	public void setToVatId(int toVatId) {
		this.toVatId = toVatId;
	}*/


	public ArrayList getToVatList() {
		if(isListFlagFortovat()){
	
		}

		return toVatList;
	}


	public void setToVatList(ArrayList toVatList) {
		this.toVatList = toVatList;
	}


	public void tanklistnerMFSeconend(ValueChangeEvent ae) {
		try {

		 
			String val1 = (String) ae.getNewValue();
			this.toVatId = val1;
			 
			PD2recievingImpl impl = new PD2recievingImpl();
			impl.getQuantitySeconend(this,val1);
			 
			 

		} catch (Exception e) {

		}
	}
	
	private boolean viewflag;
	public boolean isViewflag() {
		return viewflag;
	}


	public void setViewflag(boolean viewflag) {
		this.viewflag = viewflag;
	}

    PD2recievingDt prt;
	public PD2recievingDt getPrt() {
		return prt;
	}


	public void setPrt(PD2recievingDt prt) {
		this.prt = prt;
	}


	public void viewMethod(){
		impl.getViewdetaillist(this,prt);
		this.setViewflag(true);	
	}
	
	public String saveMethod()
	{
		try{
		if (isValidateInput()) {
			if(  this.toVatBl_trnfr>0 &&   this.toVatAl_trnfr>0 ){
	     
	   new PD2recievingImpl().saveData(this);
			}
			else{
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Quantity to be transfered cannot be Zero !", "Quantity to be transfered cannot be Zero !"));
			}
	     
		}
		}
		catch(Exception e)
		{
			
		}
				
		return "";
	}
	
	private boolean validateInput;
	
	
	public boolean isValidateInput() {
		validateInput = true;
		//FL41_Validation val = new FL41_Validation();
		
		
		if (validateInput) {
			if (this.toVatId.length()!=0 && this.toVatId == null) {
				this.validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please select Vat !", "Please select Vat !")); 
			}
			else if (this.radio == null) {
				this.validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please Select Radio !", "Please Select Radio !"));
				 
			}
			/*else if (this.toVatBl_trnfr != this.dt_bl && this.toVatAl_trnfr != this.dt_al ) {
				this.validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Quantity To Be transfered Al & Bl Should Be Equal to Gatapass AL & BL"));	
			}*/
		
		}
		return validateInput;
	}


	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}


	public void close(){
		  
		this.setViewflag(false);
		
		System.out.println("========="+this.viewflag);
	}
	
	public void reset(){
		System.out.println("======"+this.toVatSrength);
		this.radio=null;
		this.toVatBl=0;
		this.toVatAl=0;
		this.toVatSrength=0;
		this.toVatBl_bfr_trns=0;
		this.toVatAl_bfr_trns=0;
		this.toVatSrength_bfr_trns=0;
		this.toVatBl_aftr_trns=0;
		this.toVatAl_aftr_trns=0;
		this.toVatSrength_aftr_trns=0;
		this.toVatBl_dly_wst=0; 
		this.toVatAl_dly_wst=0;
		this.toVatSrength_dly_wst=0; 
		this.toVatBl_trnfr=0;
		this.toVatAl_trnfr=0;
		this.toVatSrength_trnfr=0; 
		this.toVatBl_trnfr_wst=0; 
		this.toVatAl_trnfr_wst=0;
		this.toVatSrength_trnfr_wst=0; 
		this.toVatList.clear();
		
		
		
	}
	
	private int toVatBl; 
	private int toVatAl;
	private int toVatSrength; 
	private int toVatBl_bfr_trns; 
	private int toVatAl_bfr_trns;
	private int toVatSrength_bfr_trns; 
	private int toVatBl_aftr_trns; 
	private int toVatAl_aftr_trns;
	private int toVatSrength_aftr_trns; 
	public int getToVatBl() {
		return toVatBl;
	}


	public void setToVatBl(int toVatBl) {
		this.toVatBl = toVatBl;
	}


	public int getToVatAl() {
		 try {
				
				
			// toVatAl=((toVatBl * toVatSrength) / 100);     
	             
		
			} catch (Exception e) {
				// TODO: handle exception
			}
		return toVatAl;
	}


	public void setToVatAl(int toVatAl) {
		this.toVatAl = toVatAl;
	}


	public int getToVatSrength() {
		return toVatSrength;
	}


	public void setToVatSrength(int toVatSrength) {
		this.toVatSrength = toVatSrength;
	}


	public int getToVatBl_bfr_trns() {
		return toVatBl_bfr_trns;
	}


	public void setToVatBl_bfr_trns(int toVatBl_bfr_trns) {
		this.toVatBl_bfr_trns = toVatBl_bfr_trns;
	}


	public int getToVatAl_bfr_trns() {
		 try {
				
				
			 toVatAl_bfr_trns=((toVatBl_bfr_trns * toVatSrength_bfr_trns) / 100);     
	              //System.out.println("====quantityFinalal======"+getQuantityFinalal());
		
			} catch (Exception e) {
				// TODO: handle exception
			}
		return toVatAl_bfr_trns;
	}


	public void setToVatAl_bfr_trns(int toVatAl_bfr_trns) {
		this.toVatAl_bfr_trns = toVatAl_bfr_trns;
	}


	public int getToVatSrength_bfr_trns() {
		return toVatSrength_bfr_trns;
	}


	public void setToVatSrength_bfr_trns(int toVatSrength_bfr_trns) {
		this.toVatSrength_bfr_trns = toVatSrength_bfr_trns;
	}


	public int getToVatBl_aftr_trns() {
		return toVatBl_aftr_trns;
	}


	public void setToVatBl_aftr_trns(int toVatBl_aftr_trns) {
		this.toVatBl_aftr_trns = toVatBl_aftr_trns;
	}


	public int getToVatAl_aftr_trns() {
		 try {
				
				
			 toVatAl_aftr_trns=((toVatBl_aftr_trns * toVatSrength_aftr_trns) / 100);     
	              //System.out.println("====quantityFinalal======"+getQuantityFinalal());
		
			} catch (Exception e) {
				// TODO: handle exception
			}
		return toVatAl_aftr_trns;
	}


	public void setToVatAl_aftr_trns(int toVatAl_aftr_trns) {
		this.toVatAl_aftr_trns = toVatAl_aftr_trns;
	}


	public int getToVatSrength_aftr_trns() {
		return toVatSrength_aftr_trns;
	}


	public void setToVatSrength_aftr_trns(int toVatSrength_aftr_trns) {
		this.toVatSrength_aftr_trns = toVatSrength_aftr_trns;
	}


	public int getToVatBl_dly_wst() {
		this.toVatBl_dly_wst=this.toVatBl-this.toVatBl_bfr_trns;
		return toVatBl_dly_wst;
	}


	public void setToVatBl_dly_wst(int toVatBl_dly_wst) {
		this.toVatBl_dly_wst = toVatBl_dly_wst;
	}


	public int getToVatAl_dly_wst() {
		this.toVatAl_dly_wst=this.toVatAl-this.toVatAl_bfr_trns;
		return toVatAl_dly_wst;
	}


	public void setToVatAl_dly_wst(int toVatAl_dly_wst) {
		this.toVatAl_dly_wst = toVatAl_dly_wst;
	}


	public int getToVatSrength_dly_wst() {
		this.toVatSrength_dly_wst=this.toVatSrength-this.toVatSrength_bfr_trns;
		return toVatSrength_dly_wst;
	}


	public void setToVatSrength_dly_wst(int toVatSrength_dly_wst) {
		this.toVatSrength_dly_wst = toVatSrength_dly_wst;
	}


	public int getToVatBl_trnfr() {
		this.toVatBl_trnfr=this.toVatBl_aftr_trns-this.toVatBl_bfr_trns;
		return toVatBl_trnfr;
	}


	public void setToVatBl_trnfr(int toVatBl_trnfr) {
		this.toVatBl_trnfr = toVatBl_trnfr;
	}


	public int getToVatAl_trnfr() {
		this.toVatAl_trnfr=this.toVatAl_aftr_trns-this.toVatAl_bfr_trns;
		return toVatAl_trnfr;
	}


	public void setToVatAl_trnfr(int toVatAl_trnfr) {
		this.toVatAl_trnfr = toVatAl_trnfr;
	}


	public int getToVatSrength_trnfr() {
		this.toVatSrength_trnfr=this.toVatSrength_aftr_trns-this.toVatSrength_bfr_trns;
		return toVatSrength_trnfr;
	}


	public void setToVatSrength_trnfr(int toVatSrength_trnfr) {
		this.toVatSrength_trnfr = toVatSrength_trnfr;
	}


	public int getToVatBl_trnfr_wst() {
		this.toVatBl_trnfr_wst=dt_bl-this.toVatBl_trnfr;
		
		return toVatBl_trnfr_wst;
	}


	public void setToVatBl_trnfr_wst(int toVatBl_trnfr_wst) {
		this.toVatBl_trnfr_wst = toVatBl_trnfr_wst;
	}


	public int getToVatAl_trnfr_wst() {
		this.toVatAl_trnfr_wst=this.dt_al-this.toVatAl_trnfr;
		return toVatAl_trnfr_wst;
	}


	public void setToVatAl_trnfr_wst(int toVatAl_trnfr_wst) {
		this.toVatAl_trnfr_wst = toVatAl_trnfr_wst;
	}


	public int getToVatSrength_trnfr_wst() {
		this.toVatSrength_trnfr_wst=this.toVatSrength_trnfr-this.dt_strength;
		return toVatSrength_trnfr_wst;
	}


	public void setToVatSrength_trnfr_wst(int toVatSrength_trnfr_wst) {
		this.toVatSrength_trnfr_wst = toVatSrength_trnfr_wst;
	}

	private int toVatBl_dly_wst; 
	private int toVatAl_dly_wst;
	private int toVatSrength_dly_wst; 
	private int toVatBl_trnfr; 
	private int toVatAl_trnfr;
	private int toVatSrength_trnfr; 
	private int toVatBl_trnfr_wst; 
	private int toVatAl_trnfr_wst;
	private int toVatSrength_trnfr_wst; 
	//
	
	//----------------------view data
	private String gatapass_v;
	private String gatapass_no_v;
	private String al_v;
	private String bl_v;
	public String getGatapass_v() {
		return gatapass_v;
	}


	public void setGatapass_v(String gatapass_v) {
		this.gatapass_v = gatapass_v;
	}


	public String getGatapass_no_v() {
		return gatapass_no_v;
	}


	public void setGatapass_no_v(String gatapass_no_v) {
		this.gatapass_no_v = gatapass_no_v;
	}


	public String getAl_v() {
		return al_v;
	}


	public void setAl_v(String al_v) {
		this.al_v = al_v;
	}


	public String getBl_v() {
		return bl_v;
	}


	public void setBl_v(String bl_v) {
		this.bl_v = bl_v;
	}

}
