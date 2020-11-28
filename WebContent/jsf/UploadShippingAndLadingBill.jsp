 <ui:composition
       xmlns="http://www.w3.org/1999/xhtml"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

 <f:view>
   <h:form>
   <rich:spacer  height="20"></rich:spacer>
   <h:messages errorStyle="color:red" layout="table"
														id="messages" infoStyle="color:green">
													</h:messages>
       <rich:spacer  height="30"></rich:spacer>
       <h:inputHidden value="#{uploadShippingAndLadingBillAction.hidden}" />
                            <div align="center"><h:outputText
										value="Upload Shipping Bill And Lading Bill"
										 
										style="TEXT-DECORATION: underline; FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;"></h:outputText>
										</div>
							 <rich:spacer  height="30"></rich:spacer>
							 <rich:separator lineType="dashed"></rich:separator>
							 <rich:spacer  height="30"></rich:spacer>
							 
							 <div align="center" style="FONT-SIZE: large; FONT-WEIGHT: bold;">
							   <h:outputText value="#{uploadShippingAndLadingBillAction.distillery_name}"></h:outputText>
							 </div>	
							 <rich:spacer  height="30"></rich:spacer>
									<rich:separator lineType="dashed"></rich:separator>	
		<h:panelGroup rendered="#{!uploadShippingAndLadingBillAction.view_flag}">						
			 <rich:spacer  height="30"></rich:spacer>
       <div align="center">
         <h:selectOneRadio value="#{uploadShippingAndLadingBillAction.radio}"
         valueChangeListener="#{uploadShippingAndLadingBillAction.radioListener}"
         onchange="this.form.submit();">
          <f:selectItem itemLabel="Upload Shipping Bill" itemValue="SB"/>
           <f:selectItem itemLabel="Upload Lading Bill" itemValue="LB"/>
            <f:selectItem itemLabel="Upload BRC" itemValue="B"/>
            <f:selectItem itemLabel="Completed" itemValue="C"/>
         </h:selectOneRadio>
       </div>
        <rich:spacer  height="20"></rich:spacer>
       <div>
       <rich:dataTable align="center" id="table55" rows="20"
									width="100%" var="list"
									value="#{uploadShippingAndLadingBillAction.gatepass_list}"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2">

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Sr.No." />
										</f:facet>
										<center>
											<h:outputText value="#{list.sno}" />
										</center>
									</rich:column>


									<rich:column sortBy="#{list.vch_gatepass_no}"
										filterBy="#{list.vch_gatepass_no}">
										<f:facet name="header">
											<h:outputLabel value="Gate Pass No" />
										</f:facet>
										<center>
											<h:outputText value="#{list.vch_gatepass_no}" />
										</center>
									</rich:column>
									
									<rich:column sortBy="#{list.dt_date}">
										<f:facet name="header">
											<h:outputLabel value="Date " />
										</f:facet>
										<center>
											<h:outputText value="#{list.dt_date}" />
										</center>
									</rich:column>

									
									
									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Dispatched Boxes" />
										</f:facet>
										<center>
											<h:outputText
												value="#{list.total_dis_box}" />
										</center>
									</rich:column>

                                      <rich:column>
										<f:facet name="header">
											<h:outputLabel value="Dispatched Bottles" />
										</f:facet>
										<center>
											<h:outputText
												value="#{list.total_dis_bottles}" />
										</center>
									</rich:column>
									<rich:column rendered="#{uploadShippingAndLadingBillAction.radio ne 'SB'}">
										<f:facet name="header">
											<h:outputLabel value="Shipping Bill pdf" />
										</f:facet>
										<center>
										<h:outputLink value="/doc/ExciseUp/ExportOutsideIndia/SEH/#{list.shipping_pdf}"
										target="_blank">
											<h:outputText
												value="View pdf" />
												</h:outputLink>
											
										</center>
									</rich:column>
									<rich:column rendered="#{uploadShippingAndLadingBillAction.radio eq 'C' or uploadShippingAndLadingBillAction.radio eq 'B'}">
										<f:facet name="header">
											<h:outputLabel value="Ladding Bill pdf" />
										</f:facet>
										<center>
										<h:outputLink value="/doc/ExciseUp/ExportOutsideIndia/SEH/#{list.lading_pdf}"
										target="_blank">
											<h:outputText
												value="View pdf" />
												</h:outputLink>
										</center>
									</rich:column>
									
									<rich:column rendered="#{uploadShippingAndLadingBillAction.radio eq 'C'}">
										<f:facet name="header">
											<h:outputLabel value="BRC pdf" />
										</f:facet>
										<center>
										<h:outputLink value="/doc/ExciseUp/ExportOutsideIndia/SEH/#{list.brc_pdf}"
										target="_blank">
											<h:outputText
												value="View pdf" />
												</h:outputLink>
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Action" />
										</f:facet>
										<center>
											<h:commandButton styleClass="btn btn-primary btn-sm"
												actionListener="#{uploadShippingAndLadingBillAction.view}"
												value="View" />
										</center>
									</rich:column>


									<f:facet name="footer">
										<rich:datascroller for="table55" />
									</f:facet>
								</rich:dataTable>
       </div>
       </h:panelGroup>	
       <div class="row">
						<rich:spacer height="20"></rich:spacer>
						</div>
						
						
       <h:panelGroup rendered="#{uploadShippingAndLadingBillAction.view_flag}">
       <div>
						 <h:outputText value="Brand Details :-" style="COLOR: #0091d7; FONT-STYLE: italic; TEXT-DECORATION: underline; FONT-WEIGHT: bold;"></h:outputText>
						</div>
						  <div class="row">
						<rich:spacer height="10"></rich:spacer>
						</div>
       <div>
       <rich:dataTable align="center" id="table5" rows="20"
									width="100%" var="list11"
									value="#{uploadShippingAndLadingBillAction.brand_list}"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2">

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Sr.No." />
										</f:facet>
										<center>
											<h:outputText value="#{list11.srno}" />
										</center>
									</rich:column>


									<rich:column sortBy="#{list11.vch_gatepass_no}"
										filterBy="#{list11.vch_gatepass_no}">
										<f:facet name="header">
											<h:outputLabel value="Brand Name" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.brand_name}" />
										</center>
									</rich:column>
									
									<rich:column sortBy="#{list11.dt_date}">
										<f:facet name="header">
											<h:outputLabel value="Package " />
										</f:facet>
										<center>
											<h:outputText value="#{list11.package_name}" />
										</center>
									</rich:column>

									
									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Dispatched Boxes" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.dispatch_box}" />
										</center>
									</rich:column>

									

                                      <rich:column>
										<f:facet name="header">
											<h:outputLabel value="Dispatched Bottles" />
										</f:facet>
										<center>
											<h:outputText
												value="#{list11.dispatch_bottle}" />
										</center>
									</rich:column>
									
									

									

									<f:facet name="footer">
										<rich:datascroller for="table5" />
									</f:facet>
								</rich:dataTable>
       </div>
       <div class="row">
       <rich:spacer  height="20"></rich:spacer>
       </div>
       <div class="col-md-12" align="center">
      
       <div class="col-md-3" align="right">
        <h:outputText value="Gatepass No. :" style="FONT-WEIGHT: bold;"></h:outputText>
       </div>
       <div class="col-md-3" align="left">
							<h:outputText value="#{uploadShippingAndLadingBillAction.gatepass_no}"></h:outputText>
						</div>
						
						<div class="col-md-3" align="right">
						<h:outputText value="Gatepass Date" style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-3" align="left">
						<rich:calendar value="#{uploadShippingAndLadingBillAction.gatepass_date}" disabled="true"></rich:calendar>
						</div>
						</div>
        <h:panelGroup rendered="#{uploadShippingAndLadingBillAction.radio eq 'SB'}">
        <div class="row">
        <rich:spacer  height="20"></rich:spacer>
        </div>
        
						
       <div class="col-md-12" align="center">
      
       <div class="col-md-3" align="right">
        <h:outputText value="Upload Shipping Bill Received From CBIC :" style="FONT-WEIGHT: bold;"></h:outputText>
       </div>
       <div class="col-md-3" align="left">
							<a4j:outputPanel rendered="true">
									<rich:fileUpload listHeight="30px" listWidth="225px"
										fileUploadListener="#{uploadShippingAndLadingBillAction.doc3uploadMethod}"
										maxFilesQuantity="1" clearControlLabel="Clear"
										clearAllControlLabel="Clear All"
										ontyperejected="if (!confirm('Only pdf files are accepted')) return false"
										sizeErrorLabel="" acceptedTypes="pdf"
										addControlLabel="Add PDF">								
											<a4j:support event="onuploadcomplete"
												reRender="renpdffalse3 , renpdftrue3"></a4j:support>
									</rich:fileUpload>
								</a4j:outputPanel> <h:commandButton
									action="#{uploadShippingAndLadingBillAction.pdf3}"
									disabled="#{uploadShippingAndLadingBillAction.pdfUploaderFlag == true}"
									styleClass="btn btn-info btn-sm" value="confirm PDF" /> <rich:spacer
									width="10px;"></rich:spacer> <a4j:outputPanel id="renpdftrue4">
									<h:outputLink
										rendered="#{uploadShippingAndLadingBillAction.doc3upload}"
										target="_blank"
										value="/doc/ExciseUp/ExportOutsideIndia/SEH/#{uploadShippingAndLadingBillAction.shipping_bil_pdf}">
										<h:graphicImage value="/img/download.gif" alt="view document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</h:outputLink>
								</a4j:outputPanel> <a4j:outputPanel id="renpdffalse2">
									<a4j:outputPanel
										rendered="#{!uploadShippingAndLadingBillAction.doc3upload}">
										<h:graphicImage value="/img/nodoc.png" alt="no document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</a4j:outputPanel>
								</a4j:outputPanel>
						</div>
						
						<div class="col-md-3" align="right">
						<h:outputText value="Shipping Bill No." style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-3" align="left">
						<h:inputText value="#{uploadShippingAndLadingBillAction.shipping_bill_no}"></h:inputText>
						</div>
						</div>
						<div class="row">
						 <rich:spacer  height="20"></rich:spacer>
						 </div>
						<DIV class="col-md-12">
						<div class="col-md-3" align="right">
						<h:outputText value="Date Of Issue" style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						
						<div>
						  <div class="col-md-3" align="left">
						<rich:calendar value="#{uploadShippingAndLadingBillAction.shipping_bill_date}"></rich:calendar>
						</div>
						</div>
						</DIV>
		</h:panelGroup>
		<h:panelGroup rendered="#{uploadShippingAndLadingBillAction.radio eq 'LB'}">
				<div class="row">
				<rich:spacer height="20"></rich:spacer>		
				</div>
		<div class="col-md-12" align="center">
       
       <div class="col-md-3" align="right">
        <h:outputText value="Upload Lading Bill Received From CBIC :" style="FONT-WEIGHT: bold;"></h:outputText>
       </div>
       <div class="col-md-3" align="left">
							<a4j:outputPanel rendered="true">
									<rich:fileUpload listHeight="30px" listWidth="225px"
										fileUploadListener="#{uploadShippingAndLadingBillAction.doc3uploadMethod1}"
										maxFilesQuantity="1" clearControlLabel="Clear"
										clearAllControlLabel="Clear All"
										ontyperejected="if (!confirm('Only pdf files are accepted')) return false"
										sizeErrorLabel="" acceptedTypes="pdf"
										addControlLabel="Add PDF">								
											<a4j:support event="onuploadcomplete"
												reRender="renpdffalse , renpdftrue1"></a4j:support>
									</rich:fileUpload>
								</a4j:outputPanel> <h:commandButton
									action="#{uploadShippingAndLadingBillAction.pdf31}"
									disabled="#{uploadShippingAndLadingBillAction.pdfUploaderFlag1 == true}"
									styleClass="btn btn-info btn-sm" value="confirm PDF" /> <rich:spacer
									width="10px;"></rich:spacer> <a4j:outputPanel id="renpdftrue">
									<h:outputLink
										rendered="#{uploadShippingAndLadingBillAction.doc3upload1}"
										target="_blank"
										value="/doc/ExciseUp/ExportOutsideIndia/SEH/#{uploadShippingAndLadingBillAction.lading_bil_pdf}">
										<h:graphicImage value="/img/download.gif" alt="view document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</h:outputLink>
								</a4j:outputPanel> <a4j:outputPanel id="renpdffalse3">
									<a4j:outputPanel
										rendered="#{!uploadShippingAndLadingBillAction.doc3upload1}">
										<h:graphicImage value="/img/nodoc.png" alt="no document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</a4j:outputPanel>
								</a4j:outputPanel>
						</div>
						
						<div class="col-md-3" align="right">
						<h:outputText value="Lading Bill No." style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-3" align="left">
						<h:inputText value="#{uploadShippingAndLadingBillAction.lading_bill_no}"></h:inputText>
						</div>
						</div>
						<div class="row">
						 <rich:spacer  height="20"></rich:spacer>
						 </div>
						<DIV class="col-md-12">
						<div class="col-md-3" align="right">
						<h:outputText value="Date Of Issue" style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						
						<div>
						  <div class="col-md-3" align="left">
						<rich:calendar value="#{uploadShippingAndLadingBillAction.lading_bill_date}"></rich:calendar>
						</div>
						</div>
						</DIV>
						</h:panelGroup>
						
							<h:panelGroup rendered="#{uploadShippingAndLadingBillAction.radio eq 'B'}">
				<div class="row">
				<rich:spacer height="20"></rich:spacer>		
				</div>
				
							
		<div class="col-md-12" align="center">
		<div class="col-md-3" align="right">
							<h:outputText value="Currency::  "
									style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
							</div>
      	<div class="col-md-3" align="left">
							<h:selectOneMenu onchange="this.form.submit();"
										styleClass="form-control" 
										value="#{uploadShippingAndLadingBillAction.selectCurrency}"
										>
										<f:selectItems
											value="#{uploadShippingAndLadingBillAction.currencyList}"></f:selectItems>
									</h:selectOneMenu>
									<rich:spacer  height="10"></rich:spacer>
					</div>
						</div>
							 <rich:spacer  height="20"></rich:spacer>
							<div class="col-md-3" align="right">
						<h:outputText value="BRC Value." style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-3" align="left">
						<h:inputText value="#{uploadShippingAndLadingBillAction.brc_value}"></h:inputText>
						</div>
						<div class="row">
						<div class="col-md-3" align="right">
						<h:outputText value="Value in INR." style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-3" align="left">
						<h:inputText value="#{uploadShippingAndLadingBillAction.value_inr}"></h:inputText>
						</div>
						 <rich:spacer  height="20"></rich:spacer>
						 </div>
						<DIV class="col-md-12">
						
						
				 <rich:spacer  height="20"></rich:spacer>
					
       <div class="col-md-3" align="right">
        <h:outputText value="Upload Certificate from the earmarked Bank against the foriegn Exchange :" style="FONT-WEIGHT: bold;"></h:outputText>
       </div>
       <div class="col-md-3" align="left">
							<a4j:outputPanel rendered="true">
									<rich:fileUpload listHeight="30px" listWidth="225px"
										fileUploadListener="#{uploadShippingAndLadingBillAction.doc4uploadMethod2}"
										maxFilesQuantity="1" clearControlLabel="Clear"
										clearAllControlLabel="Clear All"
										ontyperejected="if (!confirm('Only pdf files are accepted')) return false"
										sizeErrorLabel="" acceptedTypes="pdf"
										addControlLabel="Add PDF">								
											<a4j:support event="onuploadcomplete"
												reRender="renpdffalse2, renpdftrue"></a4j:support>
									</rich:fileUpload>
								</a4j:outputPanel> <h:commandButton
									action="#{uploadShippingAndLadingBillAction.pdf32}"
									disabled="#{uploadShippingAndLadingBillAction.pdfUploaderFlag1 == true}"
									styleClass="btn btn-info btn-sm" value="confirm PDF" /> <rich:spacer
									width="10px;"></rich:spacer> <a4j:outputPanel id="renpdftrue5">
									<h:outputLink
										rendered="#{uploadShippingAndLadingBillAction.doc4upload2}"
										target="_blank"
										value="/doc/ExciseUp/ExportOutsideIndia/SEH/#{uploadShippingAndLadingBillAction.uploade_certificate_foriegn_exchange_pdf}">
										<h:graphicImage value="/img/download.gif" alt="view document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</h:outputLink>
								</a4j:outputPanel> <a4j:outputPanel id="renpdffalse6">
									<a4j:outputPanel
										rendered="#{!uploadShippingAndLadingBillAction.doc4upload2}">
										<h:graphicImage value="/img/nodoc.png" alt="no document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</a4j:outputPanel>
								</a4j:outputPanel>
						</div>
						</DIV>
						<div class="row">
						<rich:spacer  height="10"></rich:spacer>
						</div>
						<DIV>
					<div class="col-md-3" align="right">
						<h:outputText value="Date Of Issue" style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						
						
						  <div class="col-md-3" align="left">
						<rich:calendar value="#{uploadShippingAndLadingBillAction.brc_date}"></rich:calendar>
						</div>
						
						</DIV>
						</h:panelGroup>
						
						
						<div class="row">
						<rich:spacer height="10"></rich:spacer>
						</div>
						<div align="center">
						 <h:commandButton action="#{uploadShippingAndLadingBillAction.submit}" 
						 value="Submit" styleClass="btn btn-success btn-sm" 
						 rendered="#{uploadShippingAndLadingBillAction.radio ne 'C'}"/>
						 <rich:spacer width="5"></rich:spacer>
						 <h:commandButton action="#{uploadShippingAndLadingBillAction.back}" value="Back" styleClass="btn btn-warning btn-sm" />
						</div>
						</h:panelGroup>
   </h:form>
 
 </f:view>
</ui:composition>