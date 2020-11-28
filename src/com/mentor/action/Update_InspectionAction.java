package com.mentor.action;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
 
import com.mentor.impl.Update_InspectionImpl;

public class Update_InspectionAction {
	Update_InspectionImpl impl = new Update_InspectionImpl();

	public String dist_name;
	private int dist_id;
	private String hidden;
	private String radio_type = "BL";
	private String vatno;
	private boolean dataflg=false;

	public boolean isDataflg() {
		return dataflg;
	}

	public void setDataflg(boolean dataflg) {
		this.dataflg = dataflg;
	}

	public String getDist_name() {
		return dist_name;
	}

	public void setDist_name(String dist_name) {
		this.dist_name = dist_name;
	}

	public int getDist_id() {
		return dist_id;
	}

	public void setDist_id(int dist_id) {
		this.dist_id = dist_id;
	}

	public String getHidden() {
		try {
			impl.getDetails(this);
			// impl.getSeasonDetails(this);

		} catch (Exception e) {
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getRadio_type() {
		return radio_type;
	}

	public void setRadio_type(String radio_type) {
		this.radio_type = radio_type;
	}

	public String getVatno() {
		return vatno;
	}

	public void setVatno(String vatno) {
		this.vatno = vatno;
	}

	public ArrayList getVatList() {
		try{
			if(this.radio_type != null){
		    this.vatList = impl.vatlistImpl(this);
			}
		}
		catch(Exception e){
			
		}
		return vatList;
	}

	public void setVatList(ArrayList vatList) {
		this.vatList = vatList;
	}

	public double getBook_al() {
		return book_al;
	}

	public void setBook_al(double book_al) {
		this.book_al = book_al;
	}

	public double getBook_bl() {
		return book_bl;
	}

	public void setBook_bl(double book_bl) {
		this.book_bl = book_bl;
	}

	public double getIns_read_bl() {
		return ins_read_bl;
	}

	public void setIns_read_bl(double ins_read_bl) {
		this.ins_read_bl = ins_read_bl;
	}

	public double getIns_read_al() {
		ins_read_al = ((ins_read_bl * ins_read_strength) / 100);
		return ins_read_al;
	}

	public void setIns_read_al(double ins_read_al) {
		this.ins_read_al = ins_read_al;
	}

	public double getIns_read_strength() {
		return ins_read_strength;
	}

	public void setIns_read_strength(double ins_read_strength) {
		this.ins_read_strength = ins_read_strength;
	}

	public double getIns_wast_bl() {
		this.ins_wast_bl = this.book_bl - this.ins_read_bl;
		return ins_wast_bl;
	}

	public void setIns_wast_bl(double ins_wast_bl) {
		this.ins_wast_bl = ins_wast_bl;
	}

	public double getIns_wast_al() {
		this.ins_wast_al = this.book_al - this.ins_read_al;
		return ins_wast_al;
	}

	public void setIns_wast_al(double ins_wast_al) {
		this.ins_wast_al = ins_wast_al;
	}

	private ArrayList vatList = new ArrayList();
	private double book_al;
	private double book_bl;
	private double ins_read_bl;
	private double ins_read_al;
	private double ins_read_strength;
	private double ins_wast_bl;
	private double ins_wast_al;

	public void radioVal(ValueChangeEvent ee) {

		String val = (String) ee.getNewValue();
		this.setRadio_type(val);

	}

	public void vatListner(ValueChangeEvent ae) {
		try {
			if(this.vatno != null){
			String val = (String) ae.getNewValue();
			// GatepassForBottolingImpl impl = new GatepassForBottolingImpl();
			impl.getQuantity(this, val);
			}

		} catch (Exception e) {

		}
	}

	private boolean validate;

	public boolean isValidate() {
		this.validate = true;
		  if (this.vatno == null && this.vatno.length() == 0) {
				this.validate = false;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								" Please Select Blend Vat !",
								"Please Select Blend Vat !"));
			} 

		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	/*public String saveMethod() {

		if (this.isValidate()) {
			impl.saveData(this);
		}

		return "";
	}*/
    
	
	public void saveMethod()
	{
	
		
		if(isValidate())
		{
		try
		{
			if(this.ins_read_strength >0 && this.ins_read_bl >0)
			{
			impl.saveData(this);
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								" Inspection Reading  (BL,AL) Should Be Greater than ZERO !",
								"Inspection Reading  (BL,AL) Should Be Greater than ZERO !"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	}
	public String reset() {

		this.vatno = "";
		this.radio_type = "BL";
		this.book_al = 0.0;
		this.book_bl = 0.0;
		this.ins_read_al = 0.0;
		this.ins_read_bl = 0.0;
		this.ins_read_strength = 0.0;
		this.ins_wast_al = 0.0;
		this.ins_wast_bl = 0.0;
		return "";
	}
	
	private ArrayList datalist = new ArrayList();

	public ArrayList getDatalist() {
		try {
			this.datalist = impl.getDataList(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return datalist;
	}

	public void setDatalist(ArrayList datalist) {
		this.datalist = datalist;
	}
	
}
