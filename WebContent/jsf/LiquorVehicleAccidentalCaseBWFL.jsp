<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich"
	xmlns:h="http://java.sun.com/jsf/html">

   
  
   <h:form>
    <f:view>
   <style>
    .table1 TD{ width:200px;}
    
    .dropdown-menu {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
	
}
    
    
    textArea{ border-radius: 3px;
	border-style: none;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
	height:40px;
     }
     .main{ margin: 20px;
     background-color:#f2f2f2;
     padding:20px;
     border-radius:10px;
     shadow: 5px #888888;
     box-shadow: 5px 4px 10px grey;}
   
   .inputtext {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
}
   </style>
   
   
   
     <div class="row">
       <rich:spacer height="30"></rich:spacer>
       
     </div>
     
      <h:messages errorStyle="color:red" layout="table"
														id="messages" infoStyle="color:green">
													</h:messages>
       <rich:spacer  height="30"></rich:spacer>
       <h:inputHidden value="#{liquorVehicleAccidentalCaseBWFLAction.hidden}" />
                            <div align="center"><h:outputText
										value="Liquor Vehicle Accidental Case"
										
										style="TEXT-DECORATION: underline; FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;"></h:outputText>
										</div>
							
							  <rich:spacer  height="30"></rich:spacer>
							 <rich:separator lineType="dashed"></rich:separator>
							 
							 
							 <rich:spacer  height="30"></rich:spacer>
							
							 <h:panelGroup rendered="#{liquorVehicleAccidentalCaseBWFLAction.viewflag == false}">
							 
							  <div align="center">
							 <h:selectOneRadio value="#{liquorVehicleAccidentalCaseBWFLAction.radio}"
							 onchange="this.form.submit();" valueChangeListener="#{liquorVehicleAccidentalCaseBWFLAction.listener}">
							 
							 <f:selectItem itemLabel="New" itemValue="N"/>
							 <f:selectItem itemLabel="Saved" itemValue="S"/>
							 </h:selectOneRadio>
							 </div>
							 <rich:spacer  height="30"></rich:spacer>
							 <div align="center">
					<rich:dataTable id="table22" rows="15" var="list"
						value="#{liquorVehicleAccidentalCaseBWFLAction.display_list}" styleClass="table table-hover"
						width="100%" headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2" >

						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Sr.No."
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.srno}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Request Id "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.req_id}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Request Date "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.req_dt}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Gatepass Type "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.gatepass_type}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Gatepass no. "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.gatepass_no}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Gatepass Date "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.gatepass_date}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="  BWFL Name"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.distillery_name}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center" rendered="#{liquorVehicleAccidentalCaseBWFLAction.radio eq 'N'}">
							<f:facet name="header">
								<h:outputText value="Action"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:commandButton value="Action" actionListener="#{liquorVehicleAccidentalCaseBWFLAction.view}"
							styleClass="btn btn-primary btn-sm"></h:commandButton>
							
						</rich:column>
						<rich:column align="center" rendered="#{liquorVehicleAccidentalCaseBWFLAction.radio eq 'S'}">
							<f:facet name="header">
								<h:outputText value="Inspection Report"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							
							<h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/WholeSale/pdf//#{list.inpection_pdf}"
												target="_blank">
												<h:outputText styleClass="outputText" id="text25"
													value="View Report"
													style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
											</h:outputLink>
							
						</rich:column>
						<rich:column align="center" rendered="#{liquorVehicleAccidentalCaseBWFLAction.radio eq 'S'}">
							<f:facet name="header">
								<h:outputText value="Print"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:commandButton value="Scan and upload" actionListener="#{liquorVehicleAccidentalCaseBWFLAction.scanUpload}"
							rendered="#{list.verify_flg and list.vch_finalize eq 'N'}" styleClass="btn btn-success btn-sm"></h:commandButton>
							<h:commandButton value="Print Forward Gatepass" actionListener="#{liquorVehicleAccidentalCaseBWFLAction.print}"
							rendered="#{list.verify_flg and list.vch_finalize eq 'F'}" styleClass="btn btn-primary btn-sm"></h:commandButton>
							<h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/WholeSale/pdf//#{list.pdfname}"
												target="_blank">
												<h:outputText styleClass="outputText" id="text225"
													value="View Report" rendered="#{list.printFlag}"
													style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
											</h:outputLink>
							
						</rich:column>
						
						<rich:column align="center" rendered="#{liquorVehicleAccidentalCaseBWFLAction.radio eq 'S'}">
							<f:facet name="header">
								<h:outputText value="Print"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:commandButton value="Return gatepass" actionListener="#{liquorVehicleAccidentalCaseBWFLAction.printRetrnGatepass}"
							rendered="#{list.verify_flg}" styleClass="btn btn-primary btn-sm"></h:commandButton>
							
							
						</rich:column>
						
						
					   <f:facet name="footer">
							<rich:datascroller for="table22"></rich:datascroller>
						</f:facet> 

					</rich:dataTable>
					</div>
					</h:panelGroup>
						<h:panelGroup rendered="#{liquorVehicleAccidentalCaseBWFLAction.viewflag}">	
							 <rich:spacer  height="30"></rich:spacer>
							 <div class="main">
							 <div >
							   <TABLE align="center" class="table1">
							    <TR style="margin: 5px;">
							     <TD style="text-align: right"><h:outputText value="Gatepass type :"></h:outputText></TD>
							     <TD><h:inputText value="#{liquorVehicleAccidentalCaseBWFLAction.gatepass_type}" styleClass="inputtext" 
							     readonly="true"></h:inputText> </TD>
							     <TD style="text-align: right"><h:outputText value="Gatepass No.:"></h:outputText></TD>
							      <TD><h:inputTextarea value="#{liquorVehicleAccidentalCaseBWFLAction.gatepass_no}"  readonly="true"></h:inputTextarea></TD>
							      <TD style="text-align: right"><h:outputText value="Gatepass Date :"></h:outputText></TD>
							      <TD style="text-align: left;"><h:inputText value="#{liquorVehicleAccidentalCaseBWFLAction.gatepass_date}" styleClass="inputtext"  readonly="true"></h:inputText> </TD>
							    </TR>
							    <TR style="height: 20px;"></TR>
							    
							    <TR style="margin-top: 10px;">
							     <TD style="text-align: right"><h:outputText value="Accident Location Address :"></h:outputText></TD>
							     <TD><h:inputTextarea value="#{liquorVehicleAccidentalCaseBWFLAction.accident_address}"  readonly="true"></h:inputTextarea> </TD>
							     <TD style="text-align: right"><h:outputText value="Accident Location District:"></h:outputText></TD>
							      <TD><h:inputText value="#{liquorVehicleAccidentalCaseBWFLAction.accident_district_name}" styleClass="inputtext"  readonly="true">
						       
							     </h:inputText> </TD>
							      <TD style="text-align: right"><h:outputText value="Accident  Date :"></h:outputText></TD>
							      <TD><h:inputText value="#{liquorVehicleAccidentalCaseBWFLAction.accident_date}" styleClass="inputtext"  readonly="true"></h:inputText > </TD>
							    </TR>
							    
							   </TABLE>
							 </div>
				 <div class="row">   
				  <rich:spacer height="30"></rich:spacer>
                 </div>
							
							 
							 </div>
							 
							 <rich:spacer height="10px"></rich:spacer>
							 
							 <div class="main">
							  <div >
							   <TABLE align="center" class="table1">
							    <TR style="margin: 5px;">
							     <TD style="text-align: right"><h:outputText value="License No. :"></h:outputText></TD>
							     <TD><h:inputText value="#{liquorVehicleAccidentalCaseBWFLAction.license_no}" styleClass="inputtext"></h:inputText> </TD>
							     <TD style="text-align: right"><h:outputText value="Licensee Name:"></h:outputText></TD>
							      <TD><h:inputTextarea value="#{liquorVehicleAccidentalCaseBWFLAction.licensee_name}"></h:inputTextarea></TD>
							      <TD style="text-align: right"><h:outputText value="Licensee Address :"></h:outputText></TD>
							      <TD style="text-align: left;"><h:inputTextarea value="#{liquorVehicleAccidentalCaseBWFLAction.licensee_add}"></h:inputTextarea> </TD>
							    </TR>
							    <TR style="height: 20px;"></TR>
							    
							    <TR style="margin-top: 10px;">
							     <TD style="text-align: right"><h:outputText value="Route Detail :"></h:outputText></TD>
							     <TD><h:inputTextarea value="#{liquorVehicleAccidentalCaseBWFLAction.route_detail}"></h:inputTextarea> </TD>
							     <TD style="text-align: right"><h:outputText value="Vehicle No:"></h:outputText></TD>
							      <TD><h:inputText value="#{liquorVehicleAccidentalCaseBWFLAction.vehicle_no}" styleClass="inputtext">
						       
							     </h:inputText> </TD>
							      <TD style="text-align: right"><h:outputText value="Driver Name :"></h:outputText></TD>
							      <TD><h:inputText value="#{liquorVehicleAccidentalCaseBWFLAction.driver_name}" styleClass="inputtext"></h:inputText> </TD>
							    </TR>
							     <TR style="height: 20px;"></TR>
							    <TR>
							    <TD style="text-align: right"><h:outputText value="Vehicle Agency Name and Address :"></h:outputText></TD>
							      <TD class=""><h:inputTextarea value="#{liquorVehicleAccidentalCaseBWFLAction.agency_name}"></h:inputTextarea> </TD>
							       <TD style="text-align: right"><h:outputText value="New Validity Date :"></h:outputText>
							        <h:outputText value="*" style="color:red;"></h:outputText></TD>
							      <TD><div><rich:calendar value="#{liquorVehicleAccidentalCaseBWFLAction.validity_date}"></rich:calendar>
							      </div> </TD>
							      
							      
							      
							    </TR>
							    <TR style="height: 20px;"></TR>
							    <TR>
							     <TD><h:outputText value="Upload Inspection Report :"></h:outputText>
							      <h:outputText value="*" style="color:red;"></h:outputText> </TD>
							      <TD><div class="col-md-3" align="left">
																																																																																																																			<a4j:outputPanel rendered="true">
									<rich:fileUpload listHeight="30px" listWidth="225px"
										fileUploadListener="#{liquorVehicleAccidentalCaseBWFLAction.doc3uploadMethod}"
										maxFilesQuantity="1" clearControlLabel="Clear"
										clearAllControlLabel="Clear All"
										ontyperejected="if (!confirm('Only pdf files are accepted')) return false"
										sizeErrorLabel="" acceptedTypes="pdf"
										addControlLabel="Add PDF">								
											<a4j:support event="onuploadcomplete"
												reRender="renpdffalse , renpdftrue"></a4j:support>
									</rich:fileUpload>
								</a4j:outputPanel> <h:commandButton
									action="#{liquorVehicleAccidentalCaseBWFLAction.pdf31}"
									disabled="#{liquorVehicleAccidentalCaseBWFLAction.pdfUploaderFlag1 == true}"
									styleClass="btn btn-info btn-sm" value="confirm PDF" /> <rich:spacer
									width="10px;"></rich:spacer> <a4j:outputPanel id="renpdftrue">
									<h:outputLink
										rendered="#{liquorVehicleAccidentalCaseBWFLAction.doc3upload1}"
										target="_blank"
										value="/doc/ExciseUp/WholeSale/pdf/#{liquorVehicleAccidentalCaseBWFLAction.pdf_name}">
										<h:graphicImage value="/img/download.gif" alt="view document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</h:outputLink>
								</a4j:outputPanel> <a4j:outputPanel id="renpdffalse">
									<a4j:outputPanel
										rendered="#{!liquorVehicleAccidentalCaseBWFLAction.doc3upload1}">
										<h:graphicImage value="/img/nodoc.png" alt="no document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</a4j:outputPanel>
								</a4j:outputPanel>
						</div></TD>
							    </TR>
							   </TABLE>
							 </div>
							 </div>
							 
							 <rich:spacer height="20"></rich:spacer>
					<div align="center">
					<rich:dataTable id="table23"  var="list22"
						value="#{liquorVehicleAccidentalCaseBWFLAction.brand_list}" styleClass="table table-hover"
						width="100%" headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2"  style="width:95%;">

						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Sr.No."
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.sr2}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Brand Name "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.brand_name}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Size "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.size}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Box Size "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.box_size}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Actual Dipatched Box "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.dispatch_box}">
							</h:outputText>
						</rich:column>
						<!--<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Total No. of Bottles "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.no_of_bottles}">
							</h:outputText>
						</rich:column>-->
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Actual Dispatched Bottles "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.dispatch_bottle}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center" >
							<f:facet name="header">
								<h:outputText value=" Total Damage Box "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:inputText value="#{list22.damage_box}" style="width: 90px;">
							</h:inputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Total Damage Bottles "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:inputText value="#{list22.damage_bottle}" style="width: 90px;">
							</h:inputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Total return Box "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:inputText value="#{list22.return_box}" style="width: 90px;">
							</h:inputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Total return Bottles "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:inputText value="#{list22.return_bottle}" style="width: 90px;">
							</h:inputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Procced To Dispatch Box"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:inputText value="#{list22.prcced_dispatch_box}" style="width: 90px;">
							</h:inputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Procced To Dispatch Bottle"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:inputText value="#{list22.prcced_dispatch_bottle}" style="width: 90px;">
							</h:inputText>
						</rich:column>
						
						
						
					   <f:facet name="footer">
							<rich:datascroller for="table23"></rich:datascroller>
						</f:facet> 

					</rich:dataTable>
					</div>
					 <rich:spacer height="20px"></rich:spacer>
					 
					 
					
					<div align="center">
							   <h:commandButton value="Save" action="#{liquorVehicleAccidentalCaseBWFLAction.save}" style="margin:0px 5px;"
							   styleClass="btn btn-success btn-sm" rendered="#{liquorVehicleAccidentalCaseBWFLAction.radio eq 'N'}"></h:commandButton>
							   <h:commandButton value="Back" action="#{liquorVehicleAccidentalCaseBWFLAction.back}" style="margin:0px 5px;"
							   styleClass="btn btn-warning btn-sm"></h:commandButton>
							 </div>
							 </h:panelGroup>
							 
							 
							 <div class="row">
							 <rich:spacer height="20"></rich:spacer>
							 </div> 
							 <h:panelGroup rendered="#{liquorVehicleAccidentalCaseBWFLAction.retrun_flag and liquorVehicleAccidentalCaseBWFLAction.radio eq 'S'}">
							 <div class="main">
							  <div >
							   <TABLE align="center" class="table1">
							    
							    <TR style="height: 20px;"></TR>
							    
							    <TR style="margin-top: 10px;">
							     <TD style="text-align: right"><h:outputText value="Route Detail :"></h:outputText>
							     <h:outputText value="*" style="color:red;"></h:outputText></TD>
							     <TD><h:inputTextarea value="#{liquorVehicleAccidentalCaseBWFLAction.return_route_detail}"></h:inputTextarea> </TD>
							     <TD style="text-align: right"><h:outputText value="Vehicle No:">
							      <h:outputText value="*" style="color:red;"></h:outputText></h:outputText></TD>
							      <TD><h:inputText value="#{liquorVehicleAccidentalCaseBWFLAction.return_vehicle_no}" styleClass="inputtext">
						       
							     </h:inputText> </TD>
							      <TD style="text-align: right"><h:outputText value="Driver Name :"></h:outputText>
							       <h:outputText value="*" style="color:red;"></h:outputText></TD>
							      <TD><h:inputText value="#{liquorVehicleAccidentalCaseBWFLAction.return_driver_name}" styleClass="inputtext"></h:inputText> </TD>
							    </TR>
							     <TR style="height: 20px;"></TR>
							    <TR>
							    <TD style="text-align: right"><h:outputText value="Vehicle Agency Name and Address :"></h:outputText>
							     <h:outputText value="*" style="color:red;"></h:outputText></TD>
							      <TD><h:inputTextarea value="#{liquorVehicleAccidentalCaseBWFLAction.return_agency_name}"></h:inputTextarea> </TD>
							      
							      <TD style="text-align: right"><h:outputText value=" Validity Date :"></h:outputText>
							       <h:outputText value="*" style="color:red;"></h:outputText></TD>
							      <TD><rich:calendar value="#{liquorVehicleAccidentalCaseBWFLAction.return_validity_date}"></rich:calendar> </TD>
							      
							      
							    </TR>
							    <TR style="height: 20px;"></TR>
							     <TR style="text-align: center">
							     
							     <TD colspan="6"><h:commandButton value="Print" action="#{liquorVehicleAccidentalCaseBWFLAction.printReturn}"></h:commandButton>
							     <h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/WholeSale/pdf//#{liquorVehicleAccidentalCaseBWFLAction.printGatePassNo}"
												target="_blank">
												<h:outputText styleClass="outputText" id="text225"
													value="View Report" rendered="#{liquorVehicleAccidentalCaseBWFLAction.printFlag}"
													style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
											</h:outputLink>
							     <h:commandButton value="Close" action="#{liquorVehicleAccidentalCaseBWFLAction.returnBack}"></h:commandButton>
							     </TD>
							      </TR>
							   </TABLE>
							 </div>
							 </div>
							 </h:panelGroup>
							 <h:panelGroup rendered="#{liquorVehicleAccidentalCaseBWFLAction.scanFlag}">
							 <div>
							 
							  <table align="center">
						<tr>
							<td colspan="6" style="color: Green;" align="left"><h:outputText
									rendered="#{liquorVehicleAccidentalCaseBWFLAction.gatePassFlag}"
									value="** Please read the instructions carefully before uploading."
									style="color: #ce0000;font-style: italic;font-size: large;text-decoration:blink;" /></td>
						</tr>

						<tr align="left">
							<td style="FONT-WEIGHT: bold; color: Green; width: 348px;"
								align="left"><h:outputText
									rendered="#{liquorVehicleAccidentalCaseBWFLAction.gatePassFlag}"
									value="Upload csv for Gatepass Number(Same Pass No. should be entered in the first row of csv.):"
									style="FONT-SIZE: medium;" /></td>
							<td style="FONT-SIZE: large; color: Red"><h:outputText
									rendered="#{liquorVehicleAccidentalCaseBWFLAction.gatePassFlag}"
									value="#{liquorVehicleAccidentalCaseBWFLAction.scanGatePassNo}"
									style="COLOR: #0000ff;" /></td>
							<TD><h:outputLink value="/doc/ExciseUp/ExcelFormat.pdf"
									target="_blank">
									<h:graphicImage value="/img/i.png" style="width:40px;"
										rendered="#{liquorVehicleAccidentalCaseBWFLAction.gatePassFlag}"></h:graphicImage>
								</h:outputLink></TD>



							<td><rich:fileUpload addControlLabel="Add File" id="link34"
									fileUploadListener="#{liquorVehicleAccidentalCaseBWFLAction.uploadCsv}"
									rendered="#{liquorVehicleAccidentalCaseBWFLAction.gatePassFlag}"
									maxFilesQuantity="1" listHeight="40" listWidth="250"
									clearControlLabel="clear" stopControlLabel="Stop"
									ontyperejected="if (!confirm(' ONLY .csv type file ALLOWED !!!')) return false"
									acceptedTypes="csv" clearAllControlLabel="Clear">
								</rich:fileUpload></td>

							<td><h:commandButton value="Upload CSV"
									styleClass="btn btn-primary"
									rendered="#{liquorVehicleAccidentalCaseBWFLAction.gatePassFlag}"
									action="#{liquorVehicleAccidentalCaseBWFLAction.csvSubmit}">
								</h:commandButton></td>

						</tr>
					</table>
					<div class="row">
						<rich:spacer height="20px"></rich:spacer>
					</div>
					<div>
						<rich:dataTable align="center" id="table57" rows="10" width="100%"
							var="listk" rendered="#{liquorVehicleAccidentalCaseBWFLAction.tableFlag}"
							value="#{liquorVehicleAccidentalCaseBWFLAction.getVal}" headerClass="TableHead"
							footerClass="TableHead" rowClasses="TableRow1,TableRow2">

							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="GatePass.No" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.gatepass}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="CaseCode" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.casecode}" />
								</center>
							</rich:column>

							<rich:column filterBy="#{listk.cascodeMatching}"
								sortBy="#{listk.cascodeMatching}">
								<f:facet name="header">
									<h:outputLabel value="Status" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.cascodeMatching}" />
								</center>
							</rich:column>



							<rich:column>
								<f:facet name="header">

								</f:facet>
							</rich:column>

							<f:facet name="footer">
								<rich:datascroller for="table57" />
							</f:facet>
						</rich:dataTable>
					</div>
					<table align="center">
						<tr>
							<td><h:commandButton value="Finalise"
									styleClass="btn btn-primary"
									rendered="#{liquorVehicleAccidentalCaseBWFLAction.tableFlag}"
									disabled="#{liquorVehicleAccidentalCaseBWFLAction.bottlingNotDoneFlag}"
									action="#{liquorVehicleAccidentalCaseBWFLAction.finalSubmit}">

								</h:commandButton> <h:commandButton value="Cancel" styleClass="btn btn-danger"
									rendered="#{liquorVehicleAccidentalCaseBWFLAction.tableFlag}"
									action="#{liquorVehicleAccidentalCaseBWFLAction.delete}">
								</h:commandButton></td>

						</tr>
					</table>
							 </div>
							 
							 <rich:spacer height="20px"></rich:spacer>
					 
					 
					
					<div align="center">
							   
							   <h:commandButton value="Back" action="#{liquorVehicleAccidentalCaseBWFLAction.back}" style="margin:0px 5px;"
							   styleClass="btn btn-warning btn-sm"></h:commandButton>
							 </div>
							 </h:panelGroup>
							 <div class="row">
							 <rich:spacer height="20"></rich:spacer>
							 </div> 
  </f:view>
   </h:form>
   
</ui:composition>