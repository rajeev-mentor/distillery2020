package com.mentor.action;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.Datatable.GatepassForBottolingDatatable;
import com.mentor.impl.GatepassForBottolingImpl; 
public class GatepassForBottolingAction {
	
	GatepassForBottolingImpl impl = new GatepassForBottolingImpl();
	
	public String dist_name;
	private int distillery_id;
	public String address;
	private String hidden;
	public String vch_from_lic_no;
	private boolean lic_disable_flag2;
	ArrayList fromliclist = new ArrayList();
	public String vch_from;
	private boolean  fromflag=false;
	private Date gatepass_date;
	private Date current_date=new Date();
	private String pdfName;
	private boolean flag=true;
	
	

	
	
	
	
	
	
	
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getPdfName() {
		return pdfName;
	}
	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}
	public Date getGatepass_date() {
		return gatepass_date;
	}
	public void setGatepass_date(Date gatepass_date) {
		this.gatepass_date = gatepass_date;
	}
	public Date getCurrent_date() {
		return current_date;
	}
	public void setCurrent_date(Date current_date) {
		this.current_date = current_date;
	}
	public boolean isFromflag() {
		return fromflag;
	}
	public void setFromflag(boolean fromflag) {
		this.fromflag = fromflag;
	}
	public String getVch_from_lic_no() {
		return vch_from_lic_no;
	}
	public void setVch_from_lic_no(String vch_from_lic_no) {
		this.vch_from_lic_no = vch_from_lic_no;
	}
	
	public String getVch_from() {
		return vch_from;
	}
	public void setVch_from(String vch_from) {
		this.vch_from = vch_from;
	}
	public ArrayList getFromliclist() {
		if(fromflag==false){
		fromliclist = impl.fromliclistImpl(this);
		}
		
		return fromliclist;
	}
	public void setFromliclist(ArrayList fromliclist) {
		this.fromliclist = fromliclist;
	}
	public String getHidden() {
		try {
			impl.getSugarmill(this);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return hidden;
	}
	public boolean isLic_disable_flag2() {
		return lic_disable_flag2;
	}
	public void setLic_disable_flag2(boolean lic_disable_flag2) {
		this.lic_disable_flag2 = lic_disable_flag2;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public String getDist_name() {
		return dist_name;
	}
	public void setDist_name(String dist_name) {
		this.dist_name = dist_name;
	}
	public int getDistillery_id() {
		return distillery_id;
	}
	public void setDistillery_id(int distillery_id) {
		this.distillery_id = distillery_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	//=====================
	
	private String radio="X";
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
	
	
	public String listMethod(ValueChangeEvent vce) {
		String val = (String) vce.getNewValue();
		//this.displaylist = impl.displaylistImpl(this, val);
		this.vch_from_lic_no = val;

		if (this.vch_from_lic_no==null || this.vch_from_lic_no.equalsIgnoreCase(""))
			this.lic_disable_flag2 = false;
		else
			this.lic_disable_flag2 = true;
		return "";
	}
	
	public String fromListMethod(ValueChangeEvent vce) {
		try {
		
			Object obj = vce.getNewValue();
			String s = (String) obj;

			this.lic_disable_flag2 = false;

			if (s.equalsIgnoreCase("FL3A")) {
				this.vch_from = "FL3A";
				this.fromliclist = impl.fromliclistImpl(this);
				//this.displaylist = impl.displaylistImpl(this, vch_from_lic_no);
			}

			else if (s.equalsIgnoreCase("FL3")) {
				this.vch_from = "FL3";
				this.fromliclist = impl.fromliclistImpl(this);

				//this.displaylist = impl.displaylistImpl(this, vch_from_lic_no);
				// System.out.println("ijhfejrfeijfei");

			}

			else {
			}

		} catch (Exception e) {

		}

		return "";
	}
   
	private String vatNo;
	private ArrayList vatNoList = new ArrayList();
	private int quantityFinal;
	private int quantityFinalal;
	private int qty_strength;

	
	
	public int getQty_strength() {
		
		return qty_strength;
	}
	public void setQty_strength(int qty_strength) {
		this.qty_strength = qty_strength;
	}
	public int getQuantityFinal() {
		
		return quantityFinal;
	}
	public void setQuantityFinal(int quantityFinal) {
		this.quantityFinal = quantityFinal;
	}
	public int getQuantityFinalal() {
    
		return quantityFinalal;
	}
	public void setQuantityFinalal(int quantityFinalal) {
		this.quantityFinalal = quantityFinalal;
	}


	public String getVatNo() {
		return vatNo;
	}
	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
	}
	public ArrayList getVatNoList() {
		 try {
				
			 vatNoList = new GatepassForBottolingImpl().getVatList(this,distillery_id);
			 
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		return vatNoList;
	}
	public void setVatNoList(ArrayList vatNoList) {
		this.vatNoList = vatNoList;
	}
	public void tanklistnerMF4(ValueChangeEvent ae) {
		try {
			String val = (String) ae.getNewValue();
			GatepassForBottolingImpl impl = new GatepassForBottolingImpl();
			impl.getQuantity(this, val);

		} catch (Exception e) {

		}
	}
	
	private int quantityFinal_befr;
	private int quantityFinalal_befr;
	private int qty_strength_befr;
	
	private int quantityFinal_aftr;
	private int quantityFinalal_aftr;
	private int qty_strength_aftr;
	
	private int quantityFinal_wast;
	private int quantityFinalal_wast;
	private int qty_strength_wast;
	
	private int quantityFinal_trnfr;
	private int quantityFinalal_trnfr;
	private int qty_strength_trnfr;









	public int getQuantityFinal_befr() {
		return quantityFinal_befr;
	}
	public void setQuantityFinal_befr(int quantityFinal_befr) {
		this.quantityFinal_befr = quantityFinal_befr;
	}
	public int getQuantityFinalal_befr() {
		
		 quantityFinalal_befr=((quantityFinal_befr * qty_strength_befr) / 100);  
			 
	     return quantityFinalal_befr;
	}
	
	public void setQuantityFinalal_befr(int quantityFinalal_befr) {
		this.quantityFinalal_befr = quantityFinalal_befr;
	}
	public int getQty_strength_befr() {
		return qty_strength_befr;
	}
	public void setQty_strength_befr(int qty_strength_befr) {
		this.qty_strength_befr = qty_strength_befr;
	}
	public int getQuantityFinal_aftr() {
		return quantityFinal_aftr;
	}
	public void setQuantityFinal_aftr(int quantityFinal_aftr) {
		this.quantityFinal_aftr = quantityFinal_aftr;
	}
	public int getQuantityFinalal_aftr() {
		
		 quantityFinalal_aftr=((quantityFinal_aftr * qty_strength_aftr) / 100);  
		
		 return quantityFinalal_aftr;
	}
	
	public void setQuantityFinalal_aftr(int quantityFinalal_aftr) {
		this.quantityFinalal_aftr = quantityFinalal_aftr;
	}
	public int getQty_strength_aftr() {
		return qty_strength_aftr;
	}
	public void setQty_strength_aftr(int qty_strength_aftr) {
		this.qty_strength_aftr = qty_strength_aftr;
	}
	public int getQuantityFinal_wast() {
		this.quantityFinal_wast=this.quantityFinal-this.quantityFinal_befr;
		return quantityFinal_wast;
	}
	public void setQuantityFinal_wast(int quantityFinal_wast) {
		this.quantityFinal_wast = quantityFinal_wast;
	}
	public int getQuantityFinalal_wast() {
		this.quantityFinalal_wast=this.quantityFinalal-this.quantityFinalal_befr;
		return quantityFinalal_wast;
	}
	public void setQuantityFinalal_wast(int quantityFinalal_wast) {
		this.quantityFinalal_wast = quantityFinalal_wast;
	}
	public int getQty_strength_wast() {
		this.qty_strength_wast=this.qty_strength-this.qty_strength_befr;
		return qty_strength_wast;
	}
	public void setQty_strength_wast(int qty_strength_wast) {
		this.qty_strength_wast = qty_strength_wast;
	}
	public int getQuantityFinal_trnfr() {
		
		this.quantityFinal_trnfr=this.quantityFinal_befr-this.quantityFinal_aftr;
		return quantityFinal_trnfr;
	}
	public void setQuantityFinal_trnfr(int quantityFinal_trnfr) {
		this.quantityFinal_trnfr = quantityFinal_trnfr;
	}
	public int getQuantityFinalal_trnfr() {
		this.quantityFinalal_trnfr=this.quantityFinalal_befr-this.quantityFinalal_aftr;
		return quantityFinalal_trnfr;
	}
	public void setQuantityFinalal_trnfr(int quantityFinalal_trnfr) {
		this.quantityFinalal_trnfr = quantityFinalal_trnfr;
	}
	public int getQty_strength_trnfr() {
		this.qty_strength_trnfr=this.qty_strength_befr-this.qty_strength_aftr;
		return qty_strength_trnfr;
	}
	public void setQty_strength_trnfr(int qty_strength_trnfr) {
		this.qty_strength_trnfr = qty_strength_trnfr;
	}
	
	
	
	
	public String saveMethod()
	{
		
		
		 
	   if(this.getQuantityFinal_befr()>0 && this.getQty_strength_befr()>0 && this.getQuantityFinal_aftr()>0 && this.getQty_strength_aftr()>0) 
	   {   
	 impl.saveData(this);
	   this.showData = impl.pd2_challan(this);	
	   }
	   else
	   {
       	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Quantity to be transfered should be greater than Zero !!", "Quantity to be transfered should be greater than Zero !!"));
       }
		return "";
	}

	public String reset() {
		this.current_date = new Date();
		this.gatepass_date=null;
		this.vatNo = "";
		this.radio=null;
		this.vch_from=null;
		this.fromliclist.clear();
		this.vatNoList.clear();
		this.quantityFinal=0;
		this.quantityFinalal=0;
		this.qty_strength=0;
		this.quantityFinal_befr=0;
		this.quantityFinalal_befr=0;
		this.qty_strength_befr=0;
		this.quantityFinal_trnfr=0;
		this.quantityFinalal_trnfr=0;
		this.qty_strength_trnfr=0;
		this.quantityFinalal=0;
		this.quantityFinalal_wast=0;
		this.qty_strength_wast=0;
		this.quantityFinal_trnfr=0;
		this.quantityFinalal_trnfr=0;
		this.qty_strength_trnfr=0;
		this.quantityFinal_aftr=0;
		this.quantityFinalal_aftr=0;
		this.qty_strength_aftr=0;
		
				
		
	

		return "";
	}
	//====================to reciving
	/*//============================to vat
	
	public void tanklistnerMFSeconend(ValueChangeEvent ae) {
		try {

			System.out.println("listener come seconed");
			String val1 = (String) ae.getNewValue();
			GatepassForBottolingImpl impl = new GatepassForBottolingImpl();
			impl.getQuantitySeconend(this, val1);

		} catch (Exception e) {

		}
	}
	
	
	private String toVatId;
	private ArrayList toVatList = new ArrayList();









	public String getToVatId() {
		return toVatId;
	}
	public void setToVatId(String toVatId) {
		this.toVatId = toVatId;
	}
	public ArrayList getToVatList() {
		this.toVatList = new GatepassForBottolingImpl()
		.getToVat(this,distillery_id);
		return toVatList;
	}
	public void setToVatList(ArrayList toVatList) {
		this.toVatList = toVatList;
	}
	private int quantityFinal_to;
	private int quantityFinalal_to;
	private int qty_strength_to;
	
	private int quantityFinal_befr_to;
	private int quantityFinalal_befr_to;
	private int qty_strength_befr_to;
	
	private int quantityFinal_aftr_to;
	private int quantityFinalal_aftr_to;
	private int qty_strength_aftr_to;
	
	private int quantityFinal_wast_to;
	private int quantityFinalal_wast_to;
	private int qty_strength_wast_to;
	
	private int quantityFinal_trnfr_to;
	private int quantityFinalal_trnfr_to;
	private int qty_strength_trnfr_to;









	public int getQuantityFinal_to() {
		return quantityFinal_to;
	}
	public void setQuantityFinal_to(int quantityFinal_to) {
		this.quantityFinal_to = quantityFinal_to;
	}
	public int getQuantityFinalal_to() {
		return quantityFinalal_to;
	}
	public void setQuantityFinalal_to(int quantityFinalal_to) {
		this.quantityFinalal_to = quantityFinalal_to;
	}
	public int getQty_strength_to() {
		return qty_strength_to;
	}
	public void setQty_strength_to(int qty_strength_to) {
		this.qty_strength_to = qty_strength_to;
	}
	public int getQuantityFinal_befr_to() {
		return quantityFinal_befr_to;
	}
	public void setQuantityFinal_befr_to(int quantityFinal_befr_to) {
		this.quantityFinal_befr_to = quantityFinal_befr_to;
	}
	public int getQuantityFinalal_befr_to() {
		return quantityFinalal_befr_to;
	}
	public void setQuantityFinalal_befr_to(int quantityFinalal_befr_to) {
		this.quantityFinalal_befr_to = quantityFinalal_befr_to;
	}
	public int getQty_strength_befr_to() {
		return qty_strength_befr_to;
	}
	public void setQty_strength_befr_to(int qty_strength_befr_to) {
		this.qty_strength_befr_to = qty_strength_befr_to;
	}
	public int getQuantityFinal_aftr_to() {
		return quantityFinal_aftr_to;
	}
	public void setQuantityFinal_aftr_to(int quantityFinal_aftr_to) {
		this.quantityFinal_aftr_to = quantityFinal_aftr_to;
	}
	public int getQuantityFinalal_aftr_to() {
		return quantityFinalal_aftr_to;
	}
	public void setQuantityFinalal_aftr_to(int quantityFinalal_aftr_to) {
		this.quantityFinalal_aftr_to = quantityFinalal_aftr_to;
	}
	public int getQty_strength_aftr_to() {
		return qty_strength_aftr_to;
	}
	public void setQty_strength_aftr_to(int qty_strength_aftr_to) {
		this.qty_strength_aftr_to = qty_strength_aftr_to;
	}
	public int getQuantityFinal_wast_to() {
		return quantityFinal_wast_to;
	}
	public void setQuantityFinal_wast_to(int quantityFinal_wast_to) {
		this.quantityFinal_wast_to = quantityFinal_wast_to;
	}
	public int getQuantityFinalal_wast_to() {
		return quantityFinalal_wast_to;
	}
	public void setQuantityFinalal_wast_to(int quantityFinalal_wast_to) {
		this.quantityFinalal_wast_to = quantityFinalal_wast_to;
	}
	public int getQty_strength_wast_to() {
		return qty_strength_wast_to;
	}
	public void setQty_strength_wast_to(int qty_strength_wast_to) {
		this.qty_strength_wast_to = qty_strength_wast_to;
	}
	public int getQuantityFinal_trnfr_to() {
		return quantityFinal_trnfr_to;
	}
	public void setQuantityFinal_trnfr_to(int quantityFinal_trnfr_to) {
		this.quantityFinal_trnfr_to = quantityFinal_trnfr_to;
	}
	public int getQuantityFinalal_trnfr_to() {
		return quantityFinalal_trnfr_to;
	}
	public void setQuantityFinalal_trnfr_to(int quantityFinalal_trnfr_to) {
		this.quantityFinalal_trnfr_to = quantityFinalal_trnfr_to;
	}
	public int getQty_strength_trnfr_to() {
		return qty_strength_trnfr_to;
	}
	public void setQty_strength_trnfr_to(int qty_strength_trnfr_to) {
		this.qty_strength_trnfr_to = qty_strength_trnfr_to;
	}
	*/
	
	// ====Aman ======created date 20/01/2020========from line 587 to 615===========//
	
	//=====To display data====//
	
	ArrayList showData = new ArrayList();
	public ArrayList getShowData() {
		if(this.flag==true){
	         this.showData = impl.pd2_challan(this);
		}
	         return showData;

	}

	public void setShowData(ArrayList showData) {
		this.showData = showData;
	}
	
	
//======To print report======//

public void printreport(ActionEvent e) {


	UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();

	GatepassForBottolingDatatable dt = (GatepassForBottolingDatatable) this.getShowData().get(uiTable.getRowIndex());



	impl.printReport(this,dt);
}

}


