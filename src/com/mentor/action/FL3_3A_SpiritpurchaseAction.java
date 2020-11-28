package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.mentor.impl.FL3_3A_SpiritpurchaseImpl;
import com.mentor.impl.GatepassForBottolingImpl;

public class FL3_3A_SpiritpurchaseAction {
	
	FL3_3A_SpiritpurchaseImpl impl =new FL3_3A_SpiritpurchaseImpl();
	
	private String hidden;
	private String radio="UP";
	private String vch_from;
	private String vch_from_lic_no;
	private String distilleri_nm;
	private int distilleri_id;
	private int dist_id;
	ArrayList dist_list =new ArrayList();
	private int state_id;
	ArrayList state_list =new ArrayList();

	private int contri_id;
	ArrayList contri_list =new ArrayList();
	private boolean dist_flg=true;
	private boolean state_flg;
	private boolean contri_flg;
	private boolean lic_disable_flag2;
	public String spritTypeId;
	public String vatid;
	ArrayList vatList = new ArrayList();
	ArrayList spritType = new ArrayList();
	
	private boolean  fromflag=false;
	private boolean hiddenFlag=true;
	private String vattype=" ";
	
	
	
	
	
	

	public String getVattype() {
		return vattype;
	}

	public void setVattype(String vattype) {
		this.vattype = vattype;
	}

	public boolean isHiddenFlag() {
		return hiddenFlag;
	}

	public void setHiddenFlag(boolean hiddenFlag) {
		this.hiddenFlag = hiddenFlag;
	}

	public boolean isFromflag() {
		return fromflag;
	}

	public void setFromflag(boolean fromflag) {
		this.fromflag = fromflag;
	}

	public String getSpritTypeId() {
		return spritTypeId;
	}

	public void setSpritTypeId(String spritTypeId) {
		this.spritTypeId = spritTypeId;
	}

	public String getVatid() {
		return vatid;
	}

	public void setVatid(String vatid) {
		this.vatid = vatid;
	}

	public ArrayList getVatList() {
		this.vatList=impl.getSpiritVat(this);
		return vatList;
	}

	public void setVatList(ArrayList vatList) {
		this.vatList = vatList;
	}

	public ArrayList getSpritType() {
		this.spritType=impl.getSpritType(); 
		return spritType;
	}

	public void setSpritType(ArrayList spritType) {
		this.spritType = spritType;
	}

	public boolean isLic_disable_flag2() {
		return lic_disable_flag2;
	}

	public void setLic_disable_flag2(boolean lic_disable_flag2) {
		this.lic_disable_flag2 = lic_disable_flag2;
	}

	public int getDist_id() {
		return dist_id;
	}

	public void setDist_id(int dist_id) {
		this.dist_id = dist_id;
	}

	public ArrayList getDist_list() {
	   if(isDist_flg())
	   {
		this.dist_list=impl.getState(this);
	   }
		return dist_list;
	}

	public void setDist_list(ArrayList dist_list) {
		this.dist_list = dist_list;
	}

	public int getState_id() {
		return state_id;
	}

	public void setState_id(int state_id) {
		this.state_id = state_id;
	}

	public ArrayList getState_list() {
		if(isState_flg()){
		this.state_list=impl.getoutsideState(this);
		}
		return state_list;
	}

	public void setState_list(ArrayList state_list) {
		this.state_list = state_list;
	}

	public int getContri_id() {
		return contri_id;
	}

	public void setContri_id(int contri_id) {
		this.contri_id = contri_id;
	}

	public ArrayList getContri_list() {
		if(isContri_flg()){
		this.contri_list=impl.getcountrilist(this);
		}
		return contri_list;
	}

	public void setContri_list(ArrayList contri_list) {
		this.contri_list = contri_list;
	}



	public String getDistilleri_nm() {
		return distilleri_nm;
	}

	public void setDistilleri_nm(String distilleri_nm) {
		this.distilleri_nm = distilleri_nm;
	}

	public int getDistilleri_id() {
		return distilleri_id;
	}

	public void setDistilleri_id(int distilleri_id) {
		this.distilleri_id = distilleri_id;
	}

	public void radiomethod(ValueChangeEvent ee) {
		
		String val = (String) ee.getNewValue();
		this.setRadio(val);	
		System.out.println("=getRadio==="+this.getRadio());
		if(this.getRadio().equalsIgnoreCase("UP")){
			this.setContri_flg(false);
			this.setState_flg(false);
			this.setDist_flg(true);
			
		}
		else if(this.getRadio().equalsIgnoreCase("OUP")){
			this.setContri_flg(false);
			this.setState_flg(true);
			this.setDist_flg(false);
			
		}
		else if(this.getRadio().equalsIgnoreCase("OC")){
			this.setContri_flg(true);
			this.setState_flg(false);
			this.setDist_flg(false);
			
		}
		
	}
	
	public boolean isDist_flg() {
		return dist_flg;
	}

	public void setDist_flg(boolean dist_flg) {
		this.dist_flg = dist_flg;
	}

	public boolean isState_flg() {
		return state_flg;
	}

	public void setState_flg(boolean state_flg) {
		this.state_flg = state_flg;
	}

	public boolean isContri_flg() {
		return contri_flg;
	}

	public void setContri_flg(boolean contri_flg) {
		this.contri_flg = contri_flg;
	}

 
   
	public String listMethod(ValueChangeEvent lce) {
		String val = (String) lce.getNewValue();
		//this.displaylist = impl.displaylistImpl(this, val);
		this.vch_from_lic_no = val;

		if (this.vch_from_lic_no==null || this.vch_from_lic_no.equalsIgnoreCase(""))
			this.lic_disable_flag2 = false;
		else
			this.lic_disable_flag2 = true;
		
		return "";
	}
	
	
	
	public void spritvatlistner(ValueChangeEvent ae) {
		try {
			String val = (String) ae.getNewValue();
			FL3_3A_SpiritpurchaseImpl impl = new FL3_3A_SpiritpurchaseImpl();
			impl.getQuantity(this, val);

		} catch (Exception e) {

		}
	}
	
	ArrayList fromliclist = new ArrayList();
	public String getHidden() {
		try{
			if(isHiddenFlag()){
			impl.getDetails(this);
			//this.setDist_flg(true);
			}
		}
		catch(Exception e){
			
		}
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public String getVch_from() {
		return vch_from;
	}
	public void setVch_from(String vch_from) {
		this.vch_from = vch_from;
	}
	public String getVch_from_lic_no() {
		vch_from_lic_no=distilleri_nm;
		return vch_from_lic_no;
	}
	public void setVch_from_lic_no(String vch_from_lic_no) {
		this.vch_from_lic_no = vch_from_lic_no;
	}
	public ArrayList getFromliclist() {
		if(fromflag==false){
		 
			}
			
		return fromliclist;
	}
	public void setFromliclist(ArrayList fromliclist) {
		this.fromliclist = fromliclist;
	}
	
	
	//-----------------------bl al value-----------------------
	private int quntity_bl_b;
	private int quntity_al_b;
	private int quantity_bl_befr;
	private int qty_strength_befr;
	private int quantity_al_befr;
	private int quantity_bl_after;
	public int getQuantity_bl_befr() {
		return quantity_bl_befr;
	}

	public void setQuantity_bl_befr(int quantity_bl_befr) {
		this.quantity_bl_befr = quantity_bl_befr;
	}

	public int getQty_strength_befr() {
		return qty_strength_befr;
	}

	public void setQty_strength_befr(int qty_strength_befr) {
		this.qty_strength_befr = qty_strength_befr;
	}

	public int getQuantity_al_befr() {
		 try {

			 quantity_al_befr=((quantity_bl_befr * qty_strength_befr) / 100);     

			} catch (Exception e) {
				// TODO: handle exception
			}
		//quantity_al_befr=quantity_bl_befr
		return quantity_al_befr;
	}

	public void setQuantity_al_befr(int quantity_al_befr) {
		this.quantity_al_befr = quantity_al_befr;
	}

	public int getQuantity_bl_after() {
		return quantity_bl_after;
	}

	public void setQuantity_bl_after(int quantity_bl_after) {
		this.quantity_bl_after = quantity_bl_after;
	}

	public int getQty_strength_aftr() {
		return qty_strength_aftr;
	}

	public void setQty_strength_aftr(int qty_strength_aftr) {
		this.qty_strength_aftr = qty_strength_aftr;
	}

	public int getQuantity_al_after() {
		 try {

			 quantity_al_after=((quantity_bl_after * qty_strength_aftr) / 100);     

			} catch (Exception e) {
				// TODO: handle exception
			}
		return quantity_al_after;
	}

	public void setQuantity_al_after(int quantity_al_after) {
		this.quantity_al_after = quantity_al_after;
	}

	public int getQuantity_bl_wast() {
		this.quantity_bl_wast=this.quntity_bl_b-this.quantity_bl_befr;
		return quantity_bl_wast;
	}

	public void setQuantity_bl_wast(int quantity_bl_wast) {
		this.quantity_bl_wast = quantity_bl_wast;
	}

	public int getQuantity_al_wast() {
		this.quantity_al_wast=this.quntity_al_b-this.quantity_al_befr;
		return quantity_al_wast;
	}

	public void setQuantity_al_wast(int quantity_al_wast) {
		this.quantity_al_wast = quantity_al_wast;
	}

	public int getQuantity_bl_recv() {
		this.quantity_bl_recv=this.quantity_bl_after-this.quantity_bl_befr;
		return quantity_bl_recv;
	}

	public void setQuantity_bl_recv(int quantity_bl_recv) {
		this.quantity_bl_recv = quantity_bl_recv;
	}

	public int getQuantity_al_recv() {
		this.quantity_al_recv=this.quantity_al_after-this.quantity_al_befr;
		return quantity_al_recv;
	}

	public void setQuantity_al_recv(int quantity_al_recv) {
		this.quantity_al_recv = quantity_al_recv;
	}

	private int qty_strength_aftr;
	private int quantity_al_after;
	private int quantity_bl_wast;
	private int quantity_al_wast;
	private int quantity_bl_recv;
	private int quantity_al_recv;








	public int getQuntity_bl_b() {
		return quntity_bl_b;
	}

	public void setQuntity_bl_b(int quntity_bl_b) {
		this.quntity_bl_b = quntity_bl_b;
	}

	public int getQuntity_al_b() {
		return quntity_al_b;
	}

	public void setQuntity_al_b(int quntity_al_b) {
		this.quntity_al_b = quntity_al_b;
	}
	
	

	public String saveMethod()
	{
		try{
	         if(this.quantity_bl_befr>0 && this.qty_strength_befr>0 && this.quantity_al_befr>0 && this.quantity_bl_after>0 &&
	        		 this.qty_strength_aftr>0 && this.quantity_al_after>0 && this.quantity_bl_wast>0 && 
	        		 this.quantity_al_wast>0 && this.quantity_bl_recv>0 && this.quantity_al_recv>0){
	     impl.saveData(this);
	         }
	     	else{
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("above All Details are required please save carefully !", "above All Details are required please save carefully !"));
			}
		}
		catch(Exception e)
		{
			
		}
	   
		return "";
	}
	
	public void reset(){
		impl.getDetails(this);
	    this.state_id=0;
	    this.dist_id=0;
	    this.contri_id=0;
	    this.spritTypeId=null;
	    this.vatid=null;
		this.dist_list.clear();
		this.state_list.clear();
		this.contri_list.clear();
		this.vatList.clear();
		this.spritType.clear();
		this.radio="UP";
	
		 
		this.qty_strength_befr=0;
		this.qty_strength_aftr=0;
		this.quntity_al_b=0;
		this.quntity_bl_b=0;
		this.quantity_al_befr=0;
		this.quantity_al_after=0;
		this.quantity_al_recv=0;
		this.quantity_al_wast=0;
		this.quantity_bl_after=0;
		this.quantity_bl_befr=0;
		this.quantity_bl_recv=0;
		this.quantity_bl_wast=0;
	
		vch_from_lic_no=distilleri_nm;
		
	}
	private int vat_max_id;


	private int wast_max_id;





	public int getWast_max_id() {
		return wast_max_id;
	}

	public void setWast_max_id(int wast_max_id) {
		this.wast_max_id = wast_max_id;
	}

	public int getVat_max_id() {
		return vat_max_id;
	}

	public void setVat_max_id(int vat_max_id) {
		this.vat_max_id = vat_max_id;
	}
	
	ArrayList showData =new ArrayList();


	public ArrayList getShowData() {
		
	this.showData=impl.fl3_fl3asavedata(this);
		return showData;
	}

	public void setShowData(ArrayList showData) {
		this.showData = showData;
	}
	
}
