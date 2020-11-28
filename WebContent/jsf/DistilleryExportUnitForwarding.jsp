 <ui:composition
       xmlns="http://www.w3.org/1999/xhtml"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:rich="http://richfaces.org/rich"
      xmlns:a4j="http://richfaces.org/a4j"
      xmlns:h="http://java.sun.com/jsf/html">

<f:view>
  <h:form>
  	<head>
			
 <link  href ="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.0.0/animate.min.css"  rel="stylesheet" type="text/css"> </link>
			

			</head>

			<rich:spacer height="20"></rich:spacer>
	<div align="center" class="heading1">				
			 <div>
					<h:outputText
						value="Approval For Distillery Export Unit For OutSide India  "
						style="FONT-FAMILY: 'Futura-Bold'; LETTER-SPACE : 6px; FONT-WEIGHT: bold; COLOR: #0000a0; FONT-SIZE: 30px;">
					</h:outputText>
			</div>	
		</div>	
			
			<h:inputHidden value="#{distilleryExportUnitForwardingAction.hidden}"></h:inputHidden>

					
					
					<div class="row" align="right">
						<a href="/portal/Home/Home"> <h:outputText
								styleClass="btn btn-info btn-sm" value=" Back To Home"
								style="COLOR: #ffffff; BACKGROUND-COLOR: #0080c0; ;font-size: 1em;"></h:outputText>

						</a>
					</div>
					<div class="row">
						<div class="col-md-12 wow shake" align="center">
							<h:messages errorStyle="color:red" layout="TABLE" id="messages"
								infoStyle="color:green"
								style="FONT-FAMILY: 'Yu Gothic UI Semibold'; background-color: #eee6ff; font-size:16px; font-weight: bold ;decoration:none;">
							</h:messages>

						</div>
					</div>
					<hr></hr>
					<h:panelGroup rendered="#{distilleryExportUnitForwardingAction.viewFlag==false}">
   <div align="center">

				<h:selectOneRadio onchange="this.form.submit();"
					value="#{distilleryExportUnitForwardingAction.radio}"
					valueChangeListener="#{distilleryExportUnitForwardingAction.clickRadio}">

					<f:selectItem itemValue="N" itemLabel="New Application" />
					<f:selectItem itemValue="A" itemLabel="Approval History" />
					<f:selectItem itemValue="O" itemLabel="Objection" itemDisabled="#{distilleryExportUnitForwardingAction.raise_flg==false}"/>
					<f:selectItem itemValue="OR" itemLabel="Objection Replied" itemDisabled="#{distilleryExportUnitForwardingAction.raise_flg==false}"/>
				</h:selectOneRadio>
			</div>
			
			<rich:spacer height="10px"></rich:spacer>
					<div align="center">
					<rich:dataTable id="table22" rows="15" var="list22"
						value="#{distilleryExportUnitForwardingAction.export_order_list}" styleClass="table table-hover"
						width="100%" headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2">

						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Sr.No."
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.srno}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="App No. "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.app_no}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" App Date "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.app_date}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Distillery Name "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.distillery_name}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Star Export House No."
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.export_house_no}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Import Export Code "
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.imp_exp_code}">
							</h:outputText>
						</rich:column>
						
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Status "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.status}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="View"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:commandButton value="View Details" actionListener="#{distilleryExportUnitForwardingAction.view}" 
							styleClass="btn btn-primary btn-sm"/>
						</rich:column>
						
						
						
						
					   <f:facet name="footer">
							<rich:datascroller for="table22"></rich:datascroller>
						</f:facet> 

					</rich:dataTable>

				</div>
				</h:panelGroup>
				<h:panelGroup rendered="#{distilleryExportUnitForwardingAction.viewFlag}">
				<div class="col-md-12">
					<div class="col-md-3" align="right">
							<h:outputText value="Application No. : "
								style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
							
						</div>
						<div class="col-md-3" align="left">
							<h:inputText value="#{distilleryExportUnitForwardingAction.app_id}"
								converterMessage="Please enter only digits." maxlength="12" readonly="true"
								style="COLOR: #0000ff;" styleClass="form-control"></h:inputText>
						</div>
						
						
						<div class="col-md-3" align="right">
							<h:outputText value=" Application Date : "
								style="FONT-SIZE: 15px; FONT-WEIGHT: bold;FONT-FAMILY: 'Candara Light';"></h:outputText>
							
						</div>

						<div class="col-md-3" align="left">
							<rich:calendar value = "#{distilleryExportUnitForwardingAction.app_date}" readonly="true">
								
							</rich:calendar>
						</div>
						
					</div>
					<rich:spacer height="10"></rich:spacer>
				<div class="col-md-12">
					<div class="col-md-3" align="right">
							<h:outputText value="Importer Exporter Code : "
								style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
							
						</div>
						<div class="col-md-3" align="left">
							<h:inputText value="#{distilleryExportUnitForwardingAction.importer_exporter_code}"
								converterMessage="Please enter only digits." maxlength="12" readonly="true"
								style="COLOR: #0000ff;" styleClass="form-control"></h:inputText>
						</div>
						
						
						<div class="col-md-3" align="right">
							<h:outputText value=" Certificate Issue Date : "
								style="FONT-SIZE: 15px; FONT-WEIGHT: bold;FONT-FAMILY: 'Candara Light';"></h:outputText>
							
						</div>

						<div class="col-md-3" align="left">
							<rich:calendar value = "#{distilleryExportUnitForwardingAction.icd_certificate_issue_date}" readonly="true">
								
							</rich:calendar>
						</div>
						
					</div>

                 <rich:spacer height="10px" />
					<div class="col-md-12">
						
						<div class="col-md-3" align="right">
							<h:outputText value="Uploaded Importer Exporter Ceritificate : "
								style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
							
						</div>
						<div class="col-md-3" align="left">
							
							
							<rich:spacer width="10px;"></rich:spacer>
							<a4j:outputPanel id="renpdftrue1">


								<h:outputLink
									rendered="#{distilleryExportUnitForwardingAction.doc1upload}"
									target="_blank"
									value="/doc/ExciseUp/ExportOutsideIndia/ICD/#{distilleryExportUnitForwardingAction.upload1}">
									<h:graphicImage value="/img/download.gif" alt="view document"
										style="width : 60px; height : 35px;"></h:graphicImage>
								</h:outputLink>
							</a4j:outputPanel>
							<a4j:outputPanel id="renpdffalse1">
								<a4j:outputPanel
									rendered="#{!distilleryExportUnitForwardingAction.doc1upload}">
									<h:graphicImage value="/img/nodoc.png" alt="no document"
										style="width : 60px; height : 35px;"></h:graphicImage>

								</a4j:outputPanel>
							</a4j:outputPanel>
						</div>
								<div class ="row">					
								<rich:spacer height="20px" />
								</div>

						<div class="col-md-12">
							<div class="col-md-3" align="right">
								<h:outputText value=" Registration Cum Membership No. : "
									style="FONT-SIZE: 15px; FONT-WEIGHT: bold;FONT-FAMILY: 'Candara Light';margin-top: 20px;"></h:outputText>
								
							</div>

							<div class="col-md-3" align="left">
							<h:inputText value = "#{distilleryExportUnitForwardingAction.regis_cum_membership_no}"
							style="COLOR: #0000ff;" readonly="true"  styleClass="form-control"/>	
							</div>
							<div class="col-md-3" align="right">
								<h:outputText value="RCMC Certificate Issue Date : "
									style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
								
							</div>
							<div class="col-md-3" align="left">
								<rich:calendar value = "#{distilleryExportUnitForwardingAction.membership_certificate_issue_date}" readonly="true">
								
							</rich:calendar>
							</div>
						</div>
		<div class= "row">				
	   <rich:spacer height="20px"/>
	</div>
						<div class="row">
							<div class="col-md-3" align="right">
								<h:outputText value="Uploaded RCMC Certificate: "
									style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
								
							</div>
							<div class="col-md-3" align="left">
								
										
								 <rich:spacer
										width="10px;"></rich:spacer> <a4j:outputPanel id="renpdftrue2">
										<h:outputLink
											rendered="#{distilleryExportUnitForwardingAction.doc2upload}"
											target="_blank"
											value="/doc/ExciseUp/ExportOutsideIndia/RCM/#{distilleryExportUnitForwardingAction.upload_rcmc_2}">
											<h:graphicImage value="/img/download.gif" alt="view document"
												style="width : 60px; height : 35px;"></h:graphicImage>
										</h:outputLink>
									</a4j:outputPanel> <a4j:outputPanel id="renpdffalse2">
										<a4j:outputPanel
											rendered="#{!distilleryExportUnitForwardingAction.doc2upload}">
											<h:graphicImage value="/img/nodoc.png" alt="no document"
												style="width : 60px; height : 35px;"></h:graphicImage>
										</a4j:outputPanel>
									</a4j:outputPanel>
							</div>
						</div>

						<div class="row" align="center">
							<h1>
								<h:outputText value="Banks Details "
								style="FONT-WEIGHT: bold; FONT-SIZE: x-large;  COLOR: #0000a0;  FONT-FAMILY: 'Candara Light';" />
							</h1>
						</div>

						<div style="overflow-y: scroll; width: 100%;">
							<rich:dataTable align="center" id="table4" rows="10" width="100%"
							
								var="list"
								value="#{distilleryExportUnitForwardingAction.bank_detail}"
								headerClass="TableHead" footerClass="TableHead"
								rowClasses="TableRow1,TableRow2">

								<rich:column>
									<f:facet name="header">
										<h:outputText value=" Sr.No. "
											styleClass="generalHeaderOutputTable"
											style="FONT-SIZE: small; FONT-WEIGHT: bold; FONT-FAMILY: 'Candara Light';" />
									</f:facet>
									<h:outputText value="#{list.sno_r}" styleClass="form-control" />
								</rich:column>

								<rich:column width="400px" >
									<f:facet name="header">
										<h:outputText value="Bank Name "
											styleClass="generalHeaderOutputTable"
											style="FONT-SIZE: small; FONT-WEIGHT: bold; FONT-FAMILY: 'Candara Light';"></h:outputText>
									</f:facet>

									<h:inputText value="#{list.bank_name}"
							          readonly="true"
										style="COLOR: #0000ff;" styleClass="form-control"></h:inputText>

								</rich:column>

								<rich:column width="300px" align="center">
									<f:facet name="header">
										<h:outputText value=" Branch "
											styleClass="generalHeaderOutputTable"
											style="FONT-SIZE: small; FONT-WEIGHT: bold; FONT-FAMILY: 'Candara Light';" />
									</f:facet>

									<h:inputText value="#{list.branch}"
										converterMessage="Please enter only digits." maxlength="12" readonly="true"
										style="COLOR: #0000ff;" styleClass="form-control"></h:inputText>

								</rich:column>


								<rich:column  width="200px">
									<f:facet name="header">
										<h:outputText value=" Account No."
											style="FONT-SIZE: small; FONT-WEIGHT: bold; FONT-FAMILY: 'Candara Light';" />
									</f:facet>

									<h:inputText value="#{list.account_no}"
										converterMessage="Please enter only digits." maxlength="12" readonly="true"
										style="COLOR: #0000ff;" styleClass="form-control"></h:inputText>

								</rich:column>



								
								<f:facet name="footer">
									<rich:datascroller for="table4" />
								</f:facet>
							</rich:dataTable>
						</div>
	<rich:spacer height="20px"></rich:spacer>
	

	
						<div class="col-md-12">
							<div class="col-md-3" align="right">
								<h:outputText value=" Star Export House No. : "
									style="FONT-SIZE: 15px; FONT-WEIGHT: bold;FONT-FAMILY: 'Candara Light';"></h:outputText>
								
							</div>

							<div class="col-md-3" align="left">
								<h:inputText value = "#{distilleryExportUnitForwardingAction.house_no}"
							style="COLOR: #0000ff;" styleClass="form-control" readonly="true" >
							</h:inputText>
							</div>
							<div class="col-md-3" align="right">
								<h:outputText value="House Certificate Issue Date : "
									style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
								
							</div>
							<div class="col-md-3" align="left">
             <rich:calendar value = "#{distilleryExportUnitForwardingAction.export_certificate_issue_date}" readonly="true">
						
							</rich:calendar>
							</div>
						</div>






					</div>
					<div class= "row">				
						<rich:spacer height="20px"/>
					</div>

					<div class="row">
					<div class="col-md-3" align="right">
							<h:outputText value=" GSTIN No. : "
								style="FONT-SIZE: 15px; FONT-WEIGHT: bold;FONT-FAMILY: 'Candara Light';"></h:outputText>
							
						</div>

						<div class="col-md-3" align="left">
							<h:inputText value = "#{distilleryExportUnitForwardingAction.gstin_no}"
							style="COLOR: #0000ff;" styleClass="form-control" readonly="true">
							</h:inputText>
						</div>
						<div class="col-md-3" align="right">
							<h:outputText value="House Uploaded Certificate : "
								style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
							
						</div>
						<div class="col-md-3" align="left">
							<a4j:outputPanel rendered="true">
									
								</a4j:outputPanel> <rich:spacer
									width="10px;"></rich:spacer> <a4j:outputPanel id="renpdftrue3">
									<h:outputLink
										rendered="#{distilleryExportUnitForwardingAction.doc3upload}"
										target="_blank"
										value="/doc/ExciseUp/ExportOutsideIndia/SEH/#{distilleryExportUnitForwardingAction.upload_certi_export_3}">
										<h:graphicImage value="/img/download.gif" alt="view document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</h:outputLink>
								</a4j:outputPanel> <a4j:outputPanel id="renpdffalse3">
									<a4j:outputPanel
										rendered="#{!distilleryExportUnitForwardingAction.doc3upload}">
										<h:graphicImage value="/img/nodoc.png" alt="no document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</a4j:outputPanel>
								</a4j:outputPanel>
						</div>
					</div>
					
					
					<div class= "row"> 
					<div align="center" >
									<h:outputText value=" Brand  Details "
										style="FONT-WEIGHT: bold; FONT-SIZE: x-large;  COLOR: #0000a0;  FONT-FAMILY: 'Candara Light';"></h:outputText>
										
								</div>
						<rich:spacer height="10"></rich:spacer>		
								
				</div>
				<div align="center">
					<rich:dataTable id="table55" rows="15" var="list33"
						value="#{distilleryExportUnitForwardingAction.display_detail}" styleClass="table table-hover"
						width="100%" headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2">

						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Sr.No."
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.display_srNo}">
							</h:outputText>
						</rich:column>
						<rich:column align="left">
							<f:facet name="header">
								<h:outputText value="Brand Name "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.brand_name}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Size (ML) "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.size}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" ETIN No."
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.etin_no}">
							</h:outputText>
						</rich:column>
						
						
					   <f:facet name="footer">
							<rich:datascroller for="table55"></rich:datascroller>
						</f:facet> 

					</rich:dataTable>

				</div>	
<div style="background-color:#ABC2C2;">
				<div class= "row">				
						<rich:spacer height="10px"/>
					</div>
				  <div class="col-md-12">
				    <div class="col-md-2" align="right" >
				     <h:outputText value=" Excise-DL Remark :"></h:outputText>
				     
				    </div>
				    
				     <div class="col-md-8" align="left" >
				       <h:inputTextarea value="#{distilleryExportUnitForwardingAction.user1_remark}"
				       styleClass="form-control" style="height:40px;COLOR: #000000;" readonly="true"></h:inputTextarea>
				     </div>
				</div>
				<div class="col-md-12">
				    <div class="col-md-2" align="right" >
				     <h:outputText value=" Excise-DEC Remark :"></h:outputText>
				     
				    </div>
				    
				     <div class="col-md-8" align="left">
				       <h:inputTextarea value="#{distilleryExportUnitForwardingAction.user2_remark}"
				       styleClass="form-control" style="height:40px;COLOR: #000000;" readonly="true"></h:inputTextarea>
				     </div>
				</div>
				<div class="col-md-12">
				    <div class="col-md-2" align="right" >
				     <h:outputText value=" Excise-AC-License Remark :"></h:outputText>
				     
				    </div>
				    
				     <div class="col-md-8" align="left" >
				       <h:inputTextarea value="#{distilleryExportUnitForwardingAction.user3_remark}"
				       styleClass="form-control" style="height:40px;COLOR: #000000;" readonly="true"></h:inputTextarea>
				     </div>
				</div>
				<div class="col-md-12">
				    <div class="col-md-2" align="right" >
				     <h:outputText value=" Excise-Commissioner Remark :"></h:outputText>
				     
				    </div>
				    
				     <div class="col-md-8" align="left" >
				       <h:inputTextarea value="#{distilleryExportUnitForwardingAction.user4_remark}"
				       styleClass="form-control" style="height:40px;COLOR: #000000;" readonly="true"></h:inputTextarea>
				     </div>
				</div>
				
				
				<div class= "row">				
						<rich:spacer height="10px"/>
					</div>
				</div>
				<rich:spacer height="10"></rich:spacer>
				<div class="col-md-12">
				    <div class="col-md-2" align="right">
				     <h:outputText value="Remark :" rendered="#{distilleryExportUnitForwardingAction.radio eq 'N'}"></h:outputText>
				     <h:outputText value="*" style="COLOR: #ff0000;" rendered="#{distilleryExportUnitForwardingAction.radio eq 'N'}"></h:outputText>
				    </div>
				    
				     <div class="col-md-8" align="left">
				       <h:inputTextarea value="#{distilleryExportUnitForwardingAction.remark}"
				       styleClass="form-control" style="height:40px;COLOR: #000000;" rendered="#{distilleryExportUnitForwardingAction.radio eq 'N'}">
				       </h:inputTextarea>
				     </div>
				</div>
				
					<div class="col-md-12" align="center">
								<h:commandButton
									rendered="#{distilleryExportUnitForwardingAction.radio eq 'N' and !distilleryExportUnitForwardingAction.user4_flag}"
									action="#{distilleryExportUnitForwardingAction.forward}"
									value=" Forward " 
									styleClass="btn btn-info btn-sm" style="FONT-SIZE: small;">

								</h:commandButton>
								<rich:spacer width="10px"></rich:spacer>


								<h:commandButton
									rendered="#{distilleryExportUnitForwardingAction.radio eq 'N' and distilleryExportUnitForwardingAction.user4_flag}"
									onclick="return confirm('ALERT : The application will be approved. Do you consent?');"
									action="#{distilleryExportUnitForwardingAction.approve}"
									value=" Approve " 
									styleClass="btn btn-success btn-sm">

								</h:commandButton>
								<rich:spacer width="10px"></rich:spacer>
								<h:commandButton
									rendered="#{distilleryExportUnitForwardingAction.radio eq 'N' and distilleryExportUnitForwardingAction.user4_flag}"
									onclick="return confirm('ALERT : The application will be Rejected. Do you consent ?');"
									action="#{distilleryExportUnitForwardingAction.reject}"
									value=" Reject " 
									styleClass="btn btn-danger btn-sm">

								</h:commandButton>
								<rich:spacer width="10px"></rich:spacer>
								<a4j:commandButton value="Raise Objection"
									rendered="#{distilleryExportUnitForwardingAction.radio eq 'N' and distilleryExportUnitForwardingAction.raise_flg}"
									oncomplete="#{rich:component('popup')}.show()"
									styleClass="btn btn-primary btn-sm" />
									<rich:spacer width="10px"></rich:spacer>
                               <a4j:commandButton value="View Objection Reply"
							rendered="#{distilleryExportUnitForwardingAction.radio eq 'OR' and distilleryExportUnitForwardingAction.raise_flg}"
							oncomplete="#{rich:component('popup4')}.show()"
							styleClass="btn btn-primary btn-sm" />
								<rich:spacer width="10px"></rich:spacer>
								<h:commandButton
									action="#{distilleryExportUnitForwardingAction.close}"
									value=" Back " style="width : 64px;"
									styleClass="btn btn-warning btn-sm">

								</h:commandButton>
							</div>
							
							
					</h:panelGroup>		
							
					
	
		</h:form>
		


<rich:modalPanel id="popup" minWidth="600" autosized="true">
			<f:facet name="header">
				<h:outputText value="Raise Objection" />
			</f:facet>
			<h:form>

				<div class="col-md-12">
					<div class="col-md-3">
						<b>Objection for</b>
					</div>
					<div class="col-md-7">
						<h:inputText
							value="#{distilleryExportUnitForwardingAction.objection_for}"
							styleClass="form-control" style="FONT-STYLE: italic;width: 100%;"></h:inputText>
					</div>
				</div>


				<div class="col-md-12">
					<div class="col-md-3">
						<b>Description</b>
					</div>
					<div class="col-md-7">
						<h:inputTextarea
							value="#{distilleryExportUnitForwardingAction.obj_Description}"
							styleClass="form-control" style="FONT-STYLE: italic;width: 100%;"></h:inputTextarea>
					</div>
				</div>

				<div class="col-md-12">
					<h:commandButton value="Save" styleClass="btn btn-success"
						action="#{distilleryExportUnitForwardingAction.objection}"></h:commandButton>

					<a4j:commandButton value="Close" styleClass="btn btn-danger"
						oncomplete="#{rich:component('popup')}.hide()" />
				</div>

			</h:form>
		</rich:modalPanel>
		
		<rich:modalPanel id="popup4" minWidth="600" autosized="true">
			<f:facet name="header">
				<h:outputText value="View Reply" />
			</f:facet>
			<h:form>
				<h:inputHidden
					value="#{distilleryExportUnitForwardingAction.popup4Hidden}"></h:inputHidden>
				<div class="col-md-12">
					<div class="col-md-3">
						<b>Objected Title</b>
					</div>
					<div class="col-md-7">
						<h:inputTextarea disabled="true"
							value="#{distilleryExportUnitForwardingAction.popup4ObjectedFor}"
							styleClass="form-control" style="FONT-STYLE: italic;width: 100%;"></h:inputTextarea>
					</div>
				</div>


				<div class="col-md-12">
					<div class="col-md-3">
						<b>Action Taken</b>
					</div>
					<div class="col-md-7">
						<h:inputTextarea disabled="true"
							value="#{distilleryExportUnitForwardingAction.popup4ActionTaken}"
							styleClass="form-control" style="FONT-STYLE: italic;width: 100%;"></h:inputTextarea>
					</div>
				</div>


				<div class="col-md-12">
					<div class="col-md-3">
						<b>Uploaded pdf</b>
					</div>
					<div class="col-md-7">
						<h:outputLink styleClass="outputLinkEx"
							value="#{distilleryExportUnitForwardingAction.popup4ObjectedPdf}"
							target="_blank">
							<h:outputText styleClass="outputText" id="text224"
								value="View PDF"
								rendered="#{distilleryExportUnitForwardingAction.viewpdfFlg}"
								style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
						</h:outputLink>
						<h:outputText styleClass="outputText" id="text22"
							value="No Pdf Uploaded"
							rendered="#{!distilleryExportUnitForwardingAction.viewpdfFlg}"
							style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
					</div>
				</div>


				<div class="col-md-12">
					<h:commandButton value="Accept" styleClass="btn btn-success btn-sm"
						action="#{distilleryExportUnitForwardingAction.agreeReply}"></h:commandButton>

					<h:commandButton value="Decline" styleClass="btn btn-danger btn-sm"
						action="#{distilleryExportUnitForwardingAction.declineReply}"></h:commandButton>

					<a4j:commandButton value="Close" styleClass="btn btn-info btn-sm"
						oncomplete="#{rich:component('popup4')}.hide()" />
				</div>

			</h:form>
		</rich:modalPanel>	
</f:view>
</ui:composition>