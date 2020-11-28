package com.mentor.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mentor.Datatable.Stock_Recieving_from_FL3_3ADT; 
import com.mentor.impl.Stock_Recieving_from_FL3_3AImpl;
import com.mentor.utility.Validate;

public class Stock_Recieving_from_FL3_3AAction {
	Stock_Recieving_from_FL3_3AImpl impl=new Stock_Recieving_from_FL3_3AImpl();
	
	
	private String name;
	private String address;
	private String loginType;
	private String hidden;
	private boolean validateInput;
	private boolean validateInput1;
	public void setValidateInput1(boolean validateInput1) {
		this.validateInput1 = validateInput1;
	}
	private String gatePassNo;
	private Date createdDate;
	private String licenseing;
	public ArrayList gatePssDisplaylist = new ArrayList();
	private int dist_id;
	
	public int getDist_id() {
		return dist_id;
	}



	public void setDist_id(int dist_id) {
		this.dist_id = dist_id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getLoginType() {
		return loginType;
	}



	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}



	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	
	

	public ArrayList getGatePssDisplaylist() {
		return gatePssDisplaylist;
	}



	public void setGatePssDisplaylist(ArrayList gatePssDisplaylist) {
		this.gatePssDisplaylist = gatePssDisplaylist;
	}



	public String getGatePassNo() {
		return gatePassNo;
	}

	public void setGatePassNo(String gatePassNo) {
		this.gatePassNo = gatePassNo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	

	public String getLicenseing() {
		return licenseing;
	}

	public void setLicenseing(String licenseing) {
		this.licenseing = licenseing;
	}

	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}
	public ArrayList show_Data_table = new ArrayList();
	public ArrayList getShow_Data_table() {
		this.show_Data_table = impl.getDataTable(this);
		return show_Data_table;
	}

	public void setShow_Data_table(ArrayList show_Data_table) {
		this.show_Data_table = show_Data_table;
	}

	public boolean isValidateInput() {
		validateInput = true;

		if (!(Validate.validateStrReq("gatepass", this.getGatePassNo())))
			validateInput = false;
		else if (!(Validate.validateDate("gatepassdate", this.getCreatedDate())))
			validateInput = false;
		

		return validateInput;
	}
	
	public boolean isValidateInput1() {
		validateInput1 = true;

		if (!(Validate.validateStrReq("gatepassNo", this.getGatePassNo())))
			validateInput1 = false;

		else if (!(Validate.validateDate("date", this.getCreatedDate())))
			validateInput1 = false;

		for (int i = 0; i < this.gatePssDisplaylist.size(); i++) {
			Stock_Recieving_from_FL3_3ADT dt = (Stock_Recieving_from_FL3_3ADT) gatePssDisplaylist
					.get(i);

			if (dt.getRecivTotalBottel() > dt.getInt_bottle_reciv()) {
				validateInput1 = false;
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Total Recieve Bottles Should Be Less Than Available Bottles !!! ",
										"Total Recieve Bottles Should Be Less Than Available Bottles !!!"));

				break;
			}
		}

		return validateInput1;
	}
	
	public String getHidden() {
		try {
			impl.getDetails(this);
		} catch (Exception e) {
		}
		return hidden;
	}
	
	
	public void check() throws ParseException {

		
		if (isValidateInput()) {
			String st = "16/08/2019";
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(st);

			if (this.createdDate.before(date1)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Stock of FLB11 gatepasses generated on or before 15th August'19 has already been added to the corresponding FL1 / FL1A stock.  ",
								"Stock of FLB11 gatepasses generated on or before 15th August'19 has already been added to the corresponding FL1 / FL1A stock."));
					}else {
			this.gatePssDisplaylist = impl.gatePassDetail(this);}
		}


	}
	
	public void saveMethod(){
		int box = 0;
		if (isValidateInput1()) {
			for (int i = 0; i < this.gatePssDisplaylist.size(); i++) {
				Stock_Recieving_from_FL3_3ADT dt = (Stock_Recieving_from_FL3_3ADT) this
						.getGatePssDisplaylist().get(i);
				box += dt.getRecivTotalBottel();
			}
			if (box > 0) {
				impl.saveMethodImpl(this);
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Receive Bottles cannot be zero!",
								"Receive Bottles cannot be zero!"));
			}
		}

	}
	
	public void reset(){
		this.gatePassNo="";
		this.gatePssDisplaylist.clear();
		this.createdDate=new Date();
	}
	
}
