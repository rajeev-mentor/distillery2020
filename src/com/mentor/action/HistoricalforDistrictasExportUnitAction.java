package com.mentor.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.Datatable.HistoricalforDistrictasExportUnitdt;

import com.mentor.impl.HistoricalforDistrictasExportUnitImpl;
import com.mentor.utility.Constants;
import com.mentor.utility.Validate;






public class HistoricalforDistrictasExportUnitAction {

HistoricalforDistrictasExportUnitImpl impl = new HistoricalforDistrictasExportUnitImpl();

	private String hidden;
	private boolean viewdisflag=true;
	private String viewflg="F";
	private String distillery_nm;
	private int distillery_id;
	private String distillery_adrs;
	private Date leo_date;
	private String leo_number;

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
	private boolean flag=false;
	private String radio;
	
	
	
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


	public void GrtpassNo()
{
	impl.GetpassNo(this, prt);
	}

	public String getGetpass_no() {
		return getpass_no;
	}

	public void setGetpass_no(String getpass_no) {
		this.getpass_no = getpass_no;
	}

	public Date getRiceipt_date() {
		return riceipt_date;
	}

	public void setRiceipt_date(Date riceipt_date) {
		this.riceipt_date = riceipt_date;
	}

	public Date getLeo_date() {
		return leo_date;
	}

	public void setLeo_date(Date leo_date) {
		this.leo_date = leo_date;
	}

	


	public String getLeo_number() {
		return leo_number;
	}
	public void setLeo_number(String leo_number) {
		this.leo_number = leo_number;
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

public String getDistillery_nm() {
		return distillery_nm;
	}

	public void setDistillery_nm(String distillery_nm) {
		this.distillery_nm = distillery_nm;
	}

	public int getDistillery_id() {
		return distillery_id;
	}

	public void setDistillery_id(int distillery_id) {
		this.distillery_id = distillery_id;
	}

	public String getDistillery_adrs() {
		return distillery_adrs;
	}

	public void setDistillery_adrs(String distillery_adrs) {
		this.distillery_adrs = distillery_adrs;
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
		//-----------------------------doc1uploadMethod First---------------------------/////
	
	public void doc1uploadMethod(UploadEvent event) throws Exception {

		this.fileName = "";
		InputStream inFile = null;
		UploadItem item = event.getUploadItem();
		String FullfileName = item.getFileName();
		String FullfileExt = null;

		String arr[] = FullfileName.split(".pdf");
		this.fileName = arr[0];

		if (FullfileName != null && FullfileName.length() > 4) {
			FullfileExt = FullfileName.substring(FullfileName.lastIndexOf(".pdf"));
		}

	 String recordFile = this.getGatapassno()+"_Leo_" +".pdf";
	 
	
		//recordFile = impl.getStorageMax() + "_wholesalebyDeo.pdf";

		String path = item.getFile().getAbsolutePath();
		filePathphoto = item.getFile().getPath();
		if (filePathphoto != null && (FullfileExt.equalsIgnoreCase(".pdf"))) {

		inFile = new FileInputStream(path);
	boolean success = false;

		try {
				String mypath = Constants.JBOSS_SERVER_PATH
						+ Constants.JBOSS_LINX_PATH + File.separator
						+ "ExciseUp" + File.separator + "HistoricalExport"+ File.separator + "pdf";
				mypathphoto = mypath + File.separator + recordFile;
				if (!(new File(mypath).exists())) {
					File file = new File(mypath);
					success = file.mkdirs();
				}
				inFile = new FileInputStream(path);
				apidoc1 = new BufferedInputStream(inFile);
				if (apidoc1.available() > 0) {

					FileOutputStream out = new FileOutputStream(mypath
							+ File.separator + recordFile);
					BufferedOutputStream outb = new BufferedOutputStream(out);
					int z = 0;
					while ((z = apidoc1.read()) != -1) {
						outb.write(z);
						doc1upload = true;
						this.setRecordFile(recordFile);
					}
					outb.flush();
					outb.close();

				} else {
					img11 = true;
					doc1upload = false;
				}
				 System.out.print("doc1 uploaded successfully");
				 this.setCount(this.getCount()+1);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		else {
		System.out.print("1 NO FILE READ STARTED.");
		doc1upload = false;
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							" MObile  no Required !!!!!!",
							"  MObile Required   !!!!!!"));
		}

	}

	private void setCount(int i) {
		
		
	}

	private int getCount() {
	
		return 0;
	}

	public void resetpage() {

		// System.out.println("------file name----------"+this.getFileName());
		if (this.fileName != null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Pdf Uploaded  !!!!!", "Pdf Uploaded  !!!!!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							" Upload Pdf first !!!!!!",
							"Upload Pdf first  !!!!!!"));
		}
	}
	private String recordFile = "NA";

	public String getRecordFile() {
		return recordFile;
	}

	public void setRecordFile(String recordFile) {
		this.recordFile = recordFile;
	}

	public boolean doc1upload = false;

	public boolean isDoc1upload() {
		return doc1upload;
	}

	public void setDoc1upload(boolean doc1upload) {
		this.doc1upload = doc1upload;
	}

	public boolean isImg1() {
		return img11;
	}

	public void setImg1(boolean img1) {
		this.img11 = img1;
	}

	public static BufferedInputStream getApidoc1() {
		return apidoc1;
	}

	public static void setApidoc1(BufferedInputStream apidoc1) {
		HistoricalforDistrictasExportUnitAction.apidoc1 = apidoc1;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private boolean img11 = false;
	private static BufferedInputStream apidoc1 = null;
	private String fileName;
	private String mypathphoto = "";

	private String filePathphoto;

	private String PdfPath;

	public String getPdfPath() {
		return getPdfPath();
	}

	public void setPdfPath(String pdfPath) {
		this.PdfPath = pdfPath;
	}

	public String getMypathphoto() {
		return mypathphoto;
	}

	public void setMypathphoto(String mypathphoto) {
		this.mypathphoto = mypathphoto;
	}

	public String getFilePathphoto() {
		return filePathphoto;
	}

	public void setFilePathphoto(String filePathphoto) {
		this.filePathphoto = filePathphoto;
	}





	
//----------------------------doc1uploadMethod1--------------------///ankur@///////---
	
	
	public void doc1uploadMethod1(UploadEvent event) throws Exception {

		this.fileName1 = "";
		InputStream inFile1 = null;
		UploadItem item1 = event.getUploadItem();
		String FullfileName1 = item1.getFileName();
		String FullfileExt1 = null;

		String arr[] = FullfileName1.split(".pdf");
		this.fileName1 = arr[0];

		if (FullfileName1 != null && FullfileName1.length() > 4) {
			FullfileExt1 = FullfileName1.substring(FullfileName1.lastIndexOf(".pdf"));
		}

	 String recordFile1 = this.getGatapassno()+ "_Brc_"+".pdf";
	
		//recordFile = impl.getStorageMax() + "_wholesalebyDeo.pdf";

		String path1 = item1.getFile().getAbsolutePath();
		filePathphoto1 = item1.getFile().getPath();
		if (filePathphoto1 != null && (FullfileExt1.equalsIgnoreCase(".pdf"))) {

		inFile1 = new FileInputStream(path1);
	boolean success = false;

		try {
				String mypath = Constants.JBOSS_SERVER_PATH
						+ Constants.JBOSS_LINX_PATH + File.separator
						+ "ExciseUp" + File.separator + "HistoricalExport"+ File.separator + "pdf";
				mypathphoto1 = mypath + File.separator + recordFile1;
				if (!(new File(mypath).exists())) {
					File file1 = new File(mypath);
					success = file1.mkdirs();
				}
				inFile1 = new FileInputStream(path1);
				apidoc2 = new BufferedInputStream(inFile1);
				if (apidoc2.available() > 0) {

					FileOutputStream out = new FileOutputStream(mypath
							+ File.separator + recordFile1);
					BufferedOutputStream outb = new BufferedOutputStream(out);
					int z = 0;
					while ((z = apidoc2.read()) != -1) {
						outb.write(z);
						doc1upload1 = true;
						this.setRecordFile1(recordFile1);
					}
					outb.flush();
					outb.close();

				} else {
					img12 = true;
					doc1upload1 = false;
				}
				 System.out.print("doc2 uploaded success fully");
				 this.setCount(this.getCount()+1);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		else {
		System.out.print("1 NO FILE READ STARTED.");
		doc1upload1 = false;
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							" MObile  no Required !!!!!!",
							"  MObile Required   !!!!!!"));
		}

	}

	private void setCount1(int i) {
		// TODO Auto-generated method stub
		
	}

	private int getCount1() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void resetpage1() {

		// System.out.println("------file name----------"+this.getFileName());
		if (this.fileName1 != null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Pdf Uploaded  !!!!!", "Pdf Uploaded  !!!!!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							" Upload Pdf Second !!!!!!",
							"Upload Pdf Second  !!!!!!"));
		}
	}
	private String recordFile1 = "NA";

	public String getRecordFile1() {
		return recordFile1;
	}

	public void setRecordFile1(String recordFile1) {
		this.recordFile1 = recordFile1;
	}

	public boolean doc1upload1 = false;

	public boolean isDoc1upload1() {
		return doc1upload1;
	}

	public void setDoc1upload1(boolean doc1upload) {
		this.doc1upload1 = doc1upload;
	}

	public boolean isImg11() {
		return img12;
	}

	public void setImg11(boolean img12) {
		this.img12 = img12;
	}

	public static BufferedInputStream getApidoc11() {
		return apidoc2;
	}

	public static void setApidoc11(BufferedInputStream apidoc1) {
		HistoricalforDistrictasExportUnitAction.apidoc2 = apidoc1;
	}

	public String getFileName1() {
		return fileName1;
	}

	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}

	private boolean img12 = false;
	private static BufferedInputStream apidoc2 = null;
	private String fileName1;
	private String mypathphoto1 = "";

	private String filePathphoto1;

	private String PdfPath1;

	public String getPdfPath1() {
		return getPdfPath1();
	}

	public void setPdfPath1(String pdfPath1) {
		this.PdfPath1 = pdfPath1;
	}

	public String getMypathphoto1() {
		return mypathphoto1;
	}

	public void setMypathphoto1(String mypathphoto1) {
		this.mypathphoto1 = mypathphoto1;
	}

	public String getFilePathphoto1() {
		return filePathphoto1;
	}

	public void setFilePathphoto1(String filePathphoto1) {
		this.filePathphoto = filePathphoto1;
	}

	///-------------------------------------- Save------------------------------------////
	
	public void save() {
		
		
		
			
			impl.SaveData(this);
			this.showData = impl.showData(this);
	
			this.viewflg="F";
		
			
	

			 
	
		
				
	}

	
	

	/*public void reset() {
		this.viewflg="T";
		
	}*/
	
	private ArrayList viewdetail1;
	public void viewdetail(){
		
		try{
		
			
	
			viewdetail1=impl.viewdetail(this,prt);
			impl.GetpassNo(this,prt);
			if(this.getRadio().equalsIgnoreCase("A")){
				impl.chkgetpass(this, prt);
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
	public ArrayList getViewdetail1() {
		return viewdetail1;
	}
	
	public void setViewdetail1(ArrayList viewdetail1) {
		this.viewdetail1 = viewdetail1;
	}
	
	private String currency_name;
	
	
	
	public String getCurrency_name() {
		return currency_name;
	}
	public void setCurrency_name(String currency_name) {
		this.currency_name = currency_name;
	}
	private ArrayList showData;
	
	
	public ArrayList getShowData() {
		if(this.flag==false){
		try {
		
	///this.showData = impl.showData(this);
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		}
		return showData;
	}
	HistoricalforDistrictasExportUnitdt prt;
	
	public HistoricalforDistrictasExportUnitdt getPrt() {
		return prt;
	}
	public void setPrt(HistoricalforDistrictasExportUnitdt prt) {
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
	private int brc_no_bottel;
	private int leo_no_of_bottel;
	
	public int getBrc_no_bottel() {
		return brc_no_bottel;
	}
	
	public void setBrc_no_bottel(int brc_no_bottel) {
		this.brc_no_bottel = brc_no_bottel;
	}
	
	public int getLeo_no_of_bottel() {
		return leo_no_of_bottel;
	}
	
	public void setLeo_no_of_bottel(int leo_no_of_bottel) {
		this.leo_no_of_bottel = leo_no_of_bottel;
	}
	
	public void close ()
	{
		this.viewflg="F";
		this.leo_date=null;
		this.leo_number=null;
		this.brc_dt=null;
		this.brc_no=0;
		this.Int_id=0;
		this.Pdf_Nmae=null;
		this. created_date=null;
		this.leo_no_of_bottel=0;
		this.brc_no_bottel=0;
		this.riceipt_date=null;
		this.recordFile =null;
		this.recordFile1 =null;
		this.fileName=null;
		this.fileName1=null;
		this. doc1upload1 = false;
		this. doc1upload = false;
	
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
			this.viewflg="F";
			this.setRadio(val);
			this.showData = impl.showData(this);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	} 
	
	private String chkGatapass;



	public String getChkGatapass() {
		return chkGatapass;
	}
	public void setChkGatapass(String chkGatapass) {
		this.chkGatapass = chkGatapass;
	}
	
	
	public int getCurrency_id() {
		return currency_id;
	}
	public void setCurrency_id(int currency_id) {
		this.currency_id = currency_id;
	}
	public ArrayList getCurrency_list() {
		this.currency_list=impl.getCurrencyList();
		return currency_list;
	}
	public void setCurrency_list(ArrayList currency_list) {
		this.currency_list = currency_list;
	}
	private int currency_id;
	private ArrayList currency_list = new ArrayList();
	private int amount;



	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	private String type;



	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}