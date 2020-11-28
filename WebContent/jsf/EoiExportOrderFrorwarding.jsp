
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich"
	xmlns:h="http://java.sun.com/jsf/html">

	
		<f:view>
		<h:form>
			<head>
			
 <link  href ="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.0.0/animate.min.css"  rel="stylesheet" type="text/css"> 
			
</link>
			</head>

			<div class="panel panel-default">

				<div class="panel-body">
				<div class="row">
					<rich:spacer height="20px" />
					</div>
	<div align="center" class="heading1">				
			 <div>
					<h:outputText
						value="APPROVAL FOR EXPORT ORDER BY DISTILLERY "
						style="FONT-FAMILY: 'Futura-Bold'; LETTER-SPACE : 6px; FONT-WEIGHT: bold; COLOR: #0000a0; FONT-SIZE: 30px;">
					</h:outputText>
			</div>	
		</div>		
			<h:inputHidden value="#{eoiExportOrderFrorwardingAction.hidden}"></h:inputHidden>

					
					
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
					<h:panelGroup rendered="#{!eoiExportOrderFrorwardingAction.viewFlag}">
					<div align="center">

				<h:selectOneRadio onchange="this.form.submit();"
					value="#{eoiExportOrderFrorwardingAction.radio}"
					valueChangeListener="#{eoiExportOrderFrorwardingAction.clickRadio}">

					<f:selectItem itemValue="N" itemLabel="New Application" />
					<f:selectItem itemValue="A" itemLabel="Forwarded" />
					<f:selectItem itemValue="O" itemLabel="Objection" itemDisabled="#{eoiExportOrderFrorwardingAction.raise_flg==false}"/>
					<f:selectItem itemValue="OR" itemLabel="Objection Replied" itemDisabled="#{eoiExportOrderFrorwardingAction.raise_flg==false}"/>
					<f:selectItem itemValue="D" itemLabel="Pending For DS" itemDisabled="#{eoiExportOrderFrorwardingAction.ds_flag==false}"/>
					<f:selectItem itemValue="DP" itemLabel="Approved"/>
					
				</h:selectOneRadio>
			</div>
			
			<rich:spacer height="10px"></rich:spacer>
					<div align="center">
					<rich:dataTable id="table22" rows="15" var="list22"
						value="#{eoiExportOrderFrorwardingAction.export_order_list}" styleClass="table table-hover"
						width="100%" headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2">

						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Sr.No."
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.srno_export}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="App No. "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.application_no}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" App Date "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.application_date}">
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
								<h:outputText value=" LEO No. "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.ceo_no}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="BRC No. "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.brc_no}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Reciept at ICD "
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.reciept_at_icd}">
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
							<h:commandButton value="View Details" actionListener="#{eoiExportOrderFrorwardingAction.view}" 
							styleClass="btn btn-primary btn-sm"/>
						</rich:column>
						
						
							<rich:column align="center"  
							rendered="#{eoiExportOrderFrorwardingAction.radio eq 'D'}">
							
							<f:facet name="header">
								
							</f:facet>
								
							<h:commandButton value="Print Report" styleClass="btn btn-success"
							rendered="#{list22.check_pdf==false and list22.printFlag==false}"
						actionListener="#{eoiExportOrderFrorwardingAction.print}">
						</h:commandButton>

						<h:outputLink styleClass="outputLinkEx"  
						
							value="/doc/ExciseUp/ExportOutsideIndia/License/#{list22.pdfname}"
							target="_blank">
							<h:outputText styleClass="outputText" id="text223"
								value="View Report"
								rendered="#{list22.printFlag==true}"
								style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
						</h:outputLink>

						</rich:column>
						
						
						<rich:column rendered="#{eoiExportOrderFrorwardingAction.radio eq 'D' or eoiExportOrderFrorwardingAction.radio eq 'DP'}">
									<f:facet name="header">
										<h:outputText value="Permit" />
									</f:facet>
									<center>

										<h:outputLink
											value="https://www.upexciseonline.in/DigitalSignature/EoiExportOrder.jsp">
											<f:param name="app_id" value="#{list22.application_no}"></f:param>
											<f:param name="distillery_id" value="#{list22.distillery_id}"></f:param>
											<f:param name="permit_nbr" value="#{list22.permit_no}"></f:param>
											<f:param name="domain" value="143"></f:param>
											<f:param name="controlid" value="#{list22.maincntrlId }"></f:param>
                            <f:param name="unitId" value="#{list22.mainunitId }"></f:param>
                            <f:param name="serviceId" value="#{list22.mainServiceId }"></f:param>
                            <f:param name="reqId" value="#{list22.requestId }"></f:param>
                             <f:param name="appid" value="#{list22.application_no }"></f:param>
                            <f:param name="processIndustryId" value="#{list22.application_no }"></f:param>
											<h:outputText value="Digital Sign"
												class="btn btn-sm btn-danger"
												rendered="#{list22.esign_flag==false and list22.printFlag==true}"></h:outputText>
										</h:outputLink>


										<h:outputLink styleClass="outputLinkEx"
											rendered="#{list22.esign_flag}"
											value="/doc/ExciseUp/ExportOutsideIndia/License//#{list22.permit_no}_esign.pdf"
											target="_blank">
											<h:outputText styleClass="outputText" id="textD1"
												value="View Digitally Sign Permit"
												style="color: blue; font-family: serif; font-size: 10pt"></h:outputText>
										</h:outputLink>
										
									</center>
								</rich:column>
						
						
						
						
					   <f:facet name="footer">
							<rich:datascroller for="table22"></rich:datascroller>
						</f:facet> 

					</rich:dataTable>

				</div>
				</h:panelGroup>
				<rich:spacer height="10"></rich:spacer>	
				<h:panelGroup rendered="#{eoiExportOrderFrorwardingAction.viewFlag}">
				<div align="center" style="background-color:#F4F3CC;">
				  <h:outputText value="Application No. :-#{eoiExportOrderFrorwardingAction.app_id}" 
				  style="color:blue;"> </h:outputText>
				   <h:outputText value=" Application Date :- #{eoiExportOrderFrorwardingAction.app_date}" 
				  style="color:blue;"> </h:outputText>
				</div>
				<rich:spacer height="10"></rich:spacer>	
					<div class="col-md-12">		
							<div class="col-md-3" align="right">
								<h:outputText value=" Import Order No. : "
									style="FONT-SIZE: 15px; FONT-WEIGHT: bold;FONT-FAMILY: 'Candara Light';"></h:outputText>
								
							</div>

							<div class="col-md-3" align="left" >
									<h:inputText value="#{eoiExportOrderFrorwardingAction.import_order_no}"
									styleClass="form-control" style="COLOR: #000000;" readonly = "true"/>
								</div>
								<div class="col-md-3" align="right">
								<h:outputText value=" Distillery Name. : "
									style="FONT-SIZE: 15px; FONT-WEIGHT: bold;FONT-FAMILY: 'Candara Light';"></h:outputText>
								
							</div>

							<div class="col-md-3" align="left">
									<h:inputTextarea value="#{eoiExportOrderFrorwardingAction.distillery_name}"
									 styleClass="form-control" style="height:30px;COLOR: #000000;" readonly = "true"/>
								</div>
								</div>
								
				<div class="row">
					<rich:spacer height="10px" />
					</div>
								
				<div class="col-md-12">
				<div class="col-md-3" align="right">
									<h:outputText value="Name Of Importing Unit :"
										style="FONT-FAMILY: 'Candara Light'; FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
										
								</div>
								
								<div class="col-md-3" align="left">
								        <h:inputTextarea styleClass="form-control" style="height:30px;COLOR: #000000; margin-right: 100px;" readonly = "true"
								         		value="#{eoiExportOrderFrorwardingAction.name_of_importing_unit}">
										
									</h:inputTextarea>
								</div>			
							
							
							<div class="col-md-3" align="right">
								<h:outputText value="Import Order Date : "
									style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
								
							</div>
							<div class="col-md-3" align="left" style = "color:#0055ff">
                            <h:inputText readonly = "true" value = "#{eoiExportOrderFrorwardingAction.import_order_date}"
                            styleClass="form-control" style="COLOR: #000000;">
						
							</h:inputText>
							</div>
						</div>
						<div class ="row">					
								<rich:spacer height="10px" />
								</div>	
						<div class="col-md-12">
				<div class="col-md-3" align="right">
									<h:outputText value="LEO No.:"
										style="FONT-FAMILY: 'Candara Light'; FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
										
								</div>
								
								<div class="col-md-3" align="left">
								        <h:inputText styleClass="form-control" style="COLOR: #000000;" readonly = "true"
								         		value="#{eoiExportOrderFrorwardingAction.leo_no}">
										
									</h:inputText>
								</div>			
							
							
							<div class="col-md-3" align="right">
								<h:outputText value="BRC No. : "
									style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
								
							</div>
							<div class="col-md-3" align="left" style = "width:300px;color:#0055ff">
                            <h:inputText  value = "#{eoiExportOrderFrorwardingAction.brc_no}"
                            styleClass="form-control" style="COLOR: #000000;" readonly = "true">
						
							</h:inputText>
							</div>
						</div>	
						<div class ="row">					
								<rich:spacer height="10px" />
								</div>
						<div class="col-md-12">
				<div class="col-md-3" align="right">
									<h:outputText value="Total No. Of Bottles :"
										style="FONT-FAMILY: 'Candara Light'; FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
										
								</div>
								
								<div class="col-md-3" align="left">
								        <h:inputText styleClass="form-control" style="COLOR: #000000;" readonly = "true"
								         		value="#{eoiExportOrderFrorwardingAction.total_bottles}">
										
									</h:inputText>
								</div>			
							
							
							<div class="col-md-3" align="right">
								<h:outputText value="Reciept At ICD : "
									style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
								
							</div>
							<div class="col-md-3" align="left" style = "width:300px;" >
                            <h:inputText readonly = "true" value = "#{eoiExportOrderFrorwardingAction.icd_reciept}"
                            styleClass="form-control" style="color: #000000">
						
							</h:inputText>
							</div>
						</div>	
						<div class ="row">					
								<rich:spacer height="10px" />
								</div>
						
						
						<div class="col-md-12">
							<div class="col-md-3" align="right">
								<h:outputText value="ICD : "
									style="FONT-SIZE: 15px; FONT-WEIGHT: bold;FONT-FAMILY: 'Candara Light';"></h:outputText>
								
							</div>

							<div class="col-md-3" align="left">
								<h:inputText value="#{eoiExportOrderFrorwardingAction.icd_name}"
								styleClass="form-control" style="COLOR: #000000;" readonly = "true"></h:inputText>
							</div>
							<div class="col-md-3" align="right">
							<h:outputText value=" Validity Demanded : "
								style="FONT-SIZE: 15px; FONT-WEIGHT: bold;FONT-FAMILY: 'Candara Light';"></h:outputText>
							
						</div>

						<div class="col-md-3" align="left" style = "width:300px;">
							<h:inputText value = "#{eoiExportOrderFrorwardingAction.validity_demanded}"
							styleClass="form-control"  style="color:#000000;" readonly = "true">
							</h:inputText>
						</div>
							
						</div>	
						
						<div class ="row">					
								<rich:spacer height="10px" />
								</div>
								
						
						
			
					<div class="row">
					<rich:spacer height="15px" />
					</div>
					<div class="col-md-12">
					<div class="col-md-2" align="right">
					 <h:outputText value="Uploaded Purcahse Order :"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
					<h:outputLink
										
						target="_blank"
						value="/doc/ExciseUp/ImportOrderEntry/#{eoiExportOrderFrorwardingAction.purcahse_order_pdf}">
						<h:outputText style="color:blue;" value="#{eoiExportOrderFrorwardingAction.purcahse_order_no}"></h:outputText>
					</h:outputLink>
					</div>
					<div class="col-md-2" align="right">
					 <h:outputText value="Uploaded Importer Exporter Ceritificate"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
					<h:outputLink
										
						target="_blank"
						value="/doc/ExciseUp/ExportOutsideIndia/ICD/#{eoiExportOrderFrorwardingAction.imp_exp_pdf}">
						<h:outputText style="color:blue;" value="#{eoiExportOrderFrorwardingAction.imp_exp_no}"></h:outputText>
					</h:outputLink>
					</div>
					<div class="col-md-2" align="right">
					 <h:outputText value="Uploaded House Certificate"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
					<h:outputLink
										
						target="_blank"
						value="/doc/ExciseUp/ImportOrderEntry/#{eoiExportOrderFrorwardingAction.house_pdf}">
						<h:outputText style="color:blue;" value="#{eoiExportOrderFrorwardingAction.house_no}"></h:outputText>
					</h:outputLink>
					</div>
					</div>
					<div class="row">
					<rich:spacer height="10px" />
					</div>
					<div class="col-md-12">
					<div class="col-md-2" align="right">
					 <h:outputText value="Uploaded RCM Ceritificate"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
					<h:outputLink
										
						target="_blank"
						value="/doc/ExciseUp/ExportOutsideIndia/RCM/#{eoiExportOrderFrorwardingAction.rcm_pdf}">
						<h:outputText style="color:blue;" value="#{eoiExportOrderFrorwardingAction.rcm_no}"></h:outputText>
					</h:outputLink>
					</div>
					<div class="col-md-2" align="right">
					 <h:outputText value="Performa Invoice Certificate"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
					<h:outputLink
										
						target="_blank"
						value="/doc/ExciseUp/ExportOutsideIndia/SEH/#{eoiExportOrderFrorwardingAction.pi_pdf}">
						<h:outputText style="color:blue;"  value = "#{eoiExportOrderFrorwardingAction.performa_invoice_no}"></h:outputText>
					</h:outputLink>
					</div>
					<div class="col-md-2" align="right">
					 <h:outputText value="PUC Certificate"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
					<h:outputLink
										
						target="_blank"
						value="/doc/ExciseUp/ExportOutsideIndia/SEH/#{eoiExportOrderFrorwardingAction.puc_pdf}">
						<h:outputText style="color:blue;" value="#{eoiExportOrderFrorwardingAction.puc_no}"></h:outputText>
					</h:outputLink>
					</div>
					</div>
					
					
					
					<div class="row">
					<rich:spacer height="10px" />
					</div>
			
			<div>		
					<div class= "row" align="center">
				<h:outputText
							value=" Brand Details  "
						style="FONT-FAMILY: 'PMingLiU-ExtB'; COLOR: #5c98da; FONT-WEIGHT: bold; FONT-SIZE: 20px;">
			   </h:outputText>
				</div>	
				<div class="row">
					<rich:spacer height="10px" />
					</div>
					<div align="center" >
					<rich:dataTable id="table55" rows="15" var="list33"
						value="#{eoiExportOrderFrorwardingAction.brand_detaiil_list}" styleClass="table table-hover"
						width="100%" headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2">

						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Sr.No."
								 styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.srno_brand}">
							
							</h:outputText>
						</rich:column>
						<rich:column align="left">
							<f:facet name="header">
								<h:outputText value="Brand Name "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.brand_name}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Package "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.package_name}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" ETIN "
								
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.etin}">
							</h:outputText>
						</rich:column>
						
						<!--<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Balance Qty. "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.balance_qty}">
							</h:outputText>
						</rich:column>-->
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Requested Qty."
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.requested_qty}">
							</h:outputText>
						</rich:column>
						
						
					   <f:facet name="footer">
							<rich:datascroller for="table55"></rich:datascroller>
						</f:facet> 

					</rich:dataTable>

				</div>	
				</div>
				
				
				<div class= "row">				
						<rich:spacer height="30px"/>
					</div>
					
			
				<div class="animate__animated animate__bounceInUp animate__slower">
				<div class= "row" align="center">
			
				</div>	
				<div class= "row">				
						<rich:spacer height="10px"/>
					</div>
				
				
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
				       <h:inputTextarea value="#{eoiExportOrderFrorwardingAction.user1_remark}"
				       styleClass="form-control" style="height:40px;COLOR: #000000;" readonly="true"></h:inputTextarea>
				     </div>
				</div>
				<div class="col-md-12">
				    <div class="col-md-2" align="right" >
				     <h:outputText value=" DEC Remark :"></h:outputText>
				     
				    </div>
				    
				     <div class="col-md-8" align="left">
				       <h:inputTextarea value="#{eoiExportOrderFrorwardingAction.user2_remark}"
				       styleClass="form-control" style="height:40px;COLOR: #000000;" readonly="true"></h:inputTextarea>
				     </div>
				</div>
				<div class="col-md-12">
				    <div class="col-md-2" align="right" >
				     <h:outputText value=" Excise-AC-License Remark :"></h:outputText>
				     
				    </div>
				    
				     <div class="col-md-8" align="left" >
				       <h:inputTextarea value="#{eoiExportOrderFrorwardingAction.user3_remark}"
				       styleClass="form-control" style="height:40px;COLOR: #000000;" readonly="true"></h:inputTextarea>
				     </div>
				</div>
				<div class="col-md-12">
				    <div class="col-md-2" align="right" >
				     <h:outputText value=" Excise-Commissioner Remark :"></h:outputText>
				     
				    </div>
				    
				     <div class="col-md-8" align="left" >
				       <h:inputTextarea value="#{eoiExportOrderFrorwardingAction.user4_remark}"
				       styleClass="form-control" style="height:40px;COLOR: #000000;" readonly="true"></h:inputTextarea>
				     </div>
				</div>
				<div class="col-md-12">
				    <div class="col-md-2" align="right" >
				     <h:outputText value=" Revert Remark :"
				     rendered="#{eoiExportOrderFrorwardingAction.user_revert_flag}"></h:outputText>
				     
				    </div>
				    
				     <div class="col-md-8" align="left" >
				       <h:inputTextarea value="#{eoiExportOrderFrorwardingAction.user_revert_remark}"
				       rendered="#{eoiExportOrderFrorwardingAction.user_revert_flag}"
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
				     <h:outputText value="Remark :" rendered="#{eoiExportOrderFrorwardingAction.radio eq 'N'}"></h:outputText>
				     <h:outputText value="*" style="COLOR: #ff0000;" rendered="#{eoiExportOrderFrorwardingAction.radio eq 'N'}"></h:outputText>
				    </div>
				    
				     <div class="col-md-8" align="left">
				       <h:inputTextarea value="#{eoiExportOrderFrorwardingAction.remark}"
				       styleClass="form-control" style="height:40px;COLOR: #000000;" rendered="#{eoiExportOrderFrorwardingAction.radio eq 'N'}">
				       </h:inputTextarea>
				     </div>
				</div>
				
					<div class="col-md-12" align="center">
								<h:commandButton
									rendered="#{eoiExportOrderFrorwardingAction.radio eq 'N' and !eoiExportOrderFrorwardingAction.user4_flag}"
									action="#{eoiExportOrderFrorwardingAction.forward}"
									value=" Forward " 
									styleClass="btn btn-info btn-sm" style="FONT-SIZE: small;">

								</h:commandButton>
								<rich:spacer width="10px"></rich:spacer>


								<h:commandButton
									rendered="#{eoiExportOrderFrorwardingAction.radio eq 'N' and eoiExportOrderFrorwardingAction.user4_flag}"
									onclick="return confirm('ALERT : The application will be approved. Do you consent?');"
									action="#{eoiExportOrderFrorwardingAction.approve}"
									value=" Approve " 
									styleClass="btn btn-success btn-sm">

								</h:commandButton>
								<rich:spacer width="10px"></rich:spacer>
								<h:commandButton
									rendered="#{eoiExportOrderFrorwardingAction.radio eq 'N' and eoiExportOrderFrorwardingAction.user4_flag}"
									onclick="return confirm('ALERT : The application will be Rejected. Do you consent ?');"
									action="#{eoiExportOrderFrorwardingAction.reject}"
									value=" Reject " 
									styleClass="btn btn-danger btn-sm">

								</h:commandButton>
								<rich:spacer width="10px"></rich:spacer>
								<a4j:commandButton value="Revert Back"
									styleClass="btn btn-warning btn-sm"
									rendered="#{eoiExportOrderFrorwardingAction.radio eq 'N' and eoiExportOrderFrorwardingAction.revertFlg}"
									oncomplete="#{rich:component('popupRvrt')}.show()" />
							
								<rich:spacer width="10px"></rich:spacer>
								<a4j:commandButton value="Raise Objection"
									rendered="#{eoiExportOrderFrorwardingAction.radio eq 'N' and eoiExportOrderFrorwardingAction.raise_flg}"
									oncomplete="#{rich:component('popup')}.show()"
									styleClass="btn btn-primary btn-sm" />
									<rich:spacer width="10px"></rich:spacer>
                               <a4j:commandButton value="View Objection Reply"
							rendered="#{eoiExportOrderFrorwardingAction.radio eq 'OR' and eoiExportOrderFrorwardingAction.raise_flg}"
							oncomplete="#{rich:component('popup4')}.show()"
							styleClass="btn btn-primary btn-sm" />
								<rich:spacer width="10px"></rich:spacer>
								<h:commandButton
									action="#{eoiExportOrderFrorwardingAction.close}"
									value=" Close " style="width : 64px;"
									styleClass="btn btn-warning btn-sm">

								</h:commandButton>
							</div>
							
							
							
							
				</h:panelGroup>			
		</div>												
		</div>	
		</h:form>
		<rich:modalPanel id="popupRvrt" minWidth="600" autosized="true">
			<f:facet name="header">
				<h:outputText value="Revert Back" />
			</f:facet>
			<h:form>

				<div class="col-md-12">
					<div class="col-md-3">
						<b>*Comment</b>
					</div>
					<div class="col-md-7">
						<h:inputTextarea
							value="#{eoiExportOrderFrorwardingAction.rvrtRemark}"
							styleClass="form-control" style="FONT-STYLE: italic;width: 100%;"></h:inputTextarea>
					</div>
				</div>

				<div class="col-md-12">
					<h:commandButton value="Save" styleClass="btn btn-success"
						onclick="return confirm('ALERT : The application will be reverted back. Do you wish to continue?');"
						action="#{eoiExportOrderFrorwardingAction.revert}"></h:commandButton>

					<a4j:commandButton value="Close" styleClass="btn btn-danger"
						oncomplete="#{rich:component('popupRvrt')}.hide()" />
				</div>

			</h:form>
		</rich:modalPanel>


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
							value="#{eoiExportOrderFrorwardingAction.objection_for}"
							styleClass="form-control" style="FONT-STYLE: italic;width: 100%;"></h:inputText>
					</div>
				</div>


				<div class="col-md-12">
					<div class="col-md-3">
						<b>Description</b>
					</div>
					<div class="col-md-7">
						<h:inputTextarea
							value="#{eoiExportOrderFrorwardingAction.obj_Description}"
							styleClass="form-control" style="FONT-STYLE: italic;width: 100%;"></h:inputTextarea>
					</div>
				</div>

				<div class="col-md-12">
					<h:commandButton value="Save" styleClass="btn btn-success"
						action="#{eoiExportOrderFrorwardingAction.objection}"></h:commandButton>

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
					value="#{eoiExportOrderFrorwardingAction.popup4Hidden}"></h:inputHidden>
				<div class="col-md-12">
					<div class="col-md-3">
						<b>Objected Title</b>
					</div>
					<div class="col-md-7">
						<h:inputTextarea disabled="true"
							value="#{eoiExportOrderFrorwardingAction.popup4ObjectedFor}"
							styleClass="form-control" style="FONT-STYLE: italic;width: 100%;"></h:inputTextarea>
					</div>
				</div>


				<div class="col-md-12">
					<div class="col-md-3">
						<b>Action Taken</b>
					</div>
					<div class="col-md-7">
						<h:inputTextarea disabled="true"
							value="#{eoiExportOrderFrorwardingAction.popup4ActionTaken}"
							styleClass="form-control" style="FONT-STYLE: italic;width: 100%;"></h:inputTextarea>
					</div>
				</div>


				<div class="col-md-12">
					<div class="col-md-3">
						<b>Uploaded pdf</b>
					</div>
					<div class="col-md-7">
						<h:outputLink styleClass="outputLinkEx"
							value="/doc#{eoiExportOrderFrorwardingAction.popup4ObjectedPdf}"
							target="_blank">
							<h:outputText styleClass="outputText" id="text224"
								value="View PDF"
								rendered="#{eoiExportOrderFrorwardingAction.viewpdfFlg}"
								style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
						</h:outputLink>
						<h:outputText styleClass="outputText" id="text22"
							value="No Pdf Uploaded"
							rendered="#{!eoiExportOrderFrorwardingAction.viewpdfFlg}"
							style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
					</div>
				</div>


				<div class="col-md-12">
					<h:commandButton value="Accept" styleClass="btn btn-success btn-sm"
						action="#{eoiExportOrderFrorwardingAction.agreeReply}"></h:commandButton>

					<h:commandButton value="Decline" styleClass="btn btn-danger btn-sm"
						action="#{eoiExportOrderFrorwardingAction.declineReply}"></h:commandButton>

					<a4j:commandButton value="Close" styleClass="btn btn-info btn-sm"
						oncomplete="#{rich:component('popup4')}.hide()" />
				</div>

			</h:form>
		</rich:modalPanel>				
	</f:view>
	
</ui:composition>							