package com.mentor.Datatable;

public class FL3_3A_VAT_DECLARATION_DT {
	
	private int srNo;
	private String radio;
	private String tankName;
	private double capacity=0.0;
	private int tankid;

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getTankName() {
		return tankName;
	}

	public void setTankName(String tankName) {
		this.tankName = tankName;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	
	
	
	
	public int getTankid() {
		return tankid;
	}

	public void setTankid(int i) {
		this.tankid = i;
	}

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

}
