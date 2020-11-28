 <ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>
		<h:form style="background-color:whitesmoke;">

			<div class="form-group">
				<div class="row">
					<a4j:outputPanel id="msg">
						<div class="row col-md-12 wow shake" style="margin-top: 10px;">
							<h:messages errorStyle="color:red"
								style="font-size: 14px;font-weight: bold"
								styleClass="generalExciseStyle" layout="table"
								infoStyle="color:green" />
						</div>
					</a4j:outputPanel>
				</div>
				</div>

				<hr style="border-top: 7px #D0D3D4; border-top-style: dashed;"></hr>
				<div class="row">
					<div align="center">
						<h:outputText value="Verification Of Sealing Details"
							style="COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: 35px;font-family:Comic Sans MS;"></h:outputText>
					</div>
				</div>
				<h:inputHidden value="#{varification_Of_Sealing_DetailsAction.hidden}"></h:inputHidden>
				<hr style="border-top: 7px #D0D3D4; border-top-style: dashed;"></hr>
				
				<div class="row " align="center">
					<rich:spacer height="10px"></rich:spacer>
				</div>

				<div class="row col-md-12" align="center"
					style="BACKGROUND-COLOR: #c3e1f4;">
					<div class="col-md-12" align="center">
						<h:selectOneRadio
							style="FONT-WEIGHT: bold;  width: 50%; "
							value="#{varification_Of_Sealing_DetailsAction.radio}"						
							onchange="this.form.submit();">
							<f:selectItem itemValue="P" itemLabel="Pending" />
							<f:selectItem itemValue="V" itemLabel="Verified" />
							<f:selectItem itemValue="R" itemLabel="Rejected" />
							
							
							
						</h:selectOneRadio>
					</div>
				</div>
				<div class="row">
					<rich:spacer height="30px"></rich:spacer>
				</div>
             <div class="col-md-12" align="center">
					
					
						
						
							<rich:dataTable id="tableunitH" row="10"
                               
								width="100%" var="list2"
								value="#{varification_Of_Sealing_DetailsAction.dataTable}"
								headerClass="TableHead" footerClass="TableHead" 
								rowClasses="TableRow1,TableRow2">


								<rich:column >
									<f:facet name="header">
										<h:outputText value="Sno"
											styleClass="generalHeaderOutputTable"
											style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>
									<h:outputText value="#{list2.sn}"></h:outputText>
								</rich:column>
								<rich:column >
									<f:facet name="header">
										<h:outputText value="App Id"
											styleClass="generalHeaderOutputTable"
											style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>
									<h:outputText value="#{list2.appNO}"></h:outputText>
								</rich:column>
								<rich:column >
									<f:facet name="header">
										<h:outputText value="Order Date"
											styleClass="generalHeaderOutputTable"
											style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>
									<h:outputText value="#{list2.orderdate}"></h:outputText>
								</rich:column>
								
								<rich:column >
									<f:facet name="header">
										<h:outputText value=" Order Number"
											styleClass="generalHeaderOutputTable"
											style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>
									<h:outputText value="#{list2.orderno}"></h:outputText>
								</rich:column>
								<rich:column  >
									<f:facet name="header">
										<h:outputText value="Importing Unit"
											styleClass="generalHeaderOutputTable"
											style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>
									<h:outputText value="#{list2.impu}"></h:outputText>
								</rich:column>

								<rich:column >
									<f:facet name="header">
										<h:outputText value="Status"
											styleClass="generalHeaderOutputTable"
											style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>
									<h:outputText value="#{list2.status}"></h:outputText>
								</rich:column>


								<rich:column >
									<f:facet name="header">
										<h:outputText value="Container Number"
											style="FONT-SIZE: small; FONT-WEIGHT: bold;white-space: normal;"></h:outputText>
									</f:facet>

									<h:outputText style="width: 280px"
										value="#{list2.contno}"></h:outputText>
								</rich:column>

                               <rich:column >
									<f:facet name="header">
										<h:outputText value="Container Type"
											style="FONT-SIZE: small; FONT-WEIGHT: bold;white-space: normal;"></h:outputText>
									</f:facet>

									<h:outputText style="width: 280px"
										value="#{list2.conttype}"></h:outputText>
								</rich:column>
								<rich:column >
									<f:facet name="header">
										<h:outputText value="Seal Number"
											styleClass="generalHeaderOutputTable"
											style="FONT-SIZE: small; FONT-WEIGHT: bold;white-space: normal;"></h:outputText>
									</f:facet>

									<h:outputText value="#{list2.seal}"></h:outputText>
								</rich:column>


                          <rich:column >
									<f:facet name="header">
										<h:outputText value="Seal Date"
											styleClass="generalHeaderOutputTable"
											style="FONT-SIZE: small; FONT-WEIGHT: bold;white-space: normal;"></h:outputText>
									</f:facet>

									<h:outputText value="#{list2.sealdate}"></h:outputText>
								</rich:column>
								
								
								<rich:column align="center" >
										<f:facet name="header">
											<h:outputText value="Download Permit">
											</h:outputText>
										  </f:facet>
		    
								<h:outputLink
							
							target="_blank"
							value="/doc/ExciseUp/ExportOutsideIndia/License#{list2.pdfName}">
							<h:graphicImage value="/img/download.gif" alt="view document"
								style="width : 60px; height : 35px;"></h:graphicImage>
						</h:outputLink>
									</rich:column>
									
									<rich:column rendered="#{varification_Of_Sealing_DetailsAction.radio eq 'P' }">
								<f:facet name="header">

								</f:facet>
								
								<h:commandButton value="Verify"	
								actionListener="#{varification_Of_Sealing_DetailsAction.view}"	
								onclick="return confirm('ALERT : You are verify the application. Please Confirm Your Details !!');" 								
													styleClass="btn btn-sm btn-success">													
												
							</h:commandButton>
							
							</rich:column>
							
							<rich:column rendered="#{varification_Of_Sealing_DetailsAction.radio eq 'P' }">
								<f:facet name="header">

								</f:facet>
								
								<h:commandButton value="Reject"	
								actionListener="#{varification_Of_Sealing_DetailsAction.reject}"	
								onclick="return confirm('ALERT : You are reject the application. Please Confirm Your Details !!');" 								
													styleClass="btn btn-sm btn-success">													
												
							</h:commandButton>
							
							</rich:column>
							
                          


								<f:facet name="footer">
                                 <rich:datascroller for="tableunitH"></rich:datascroller>
								</f:facet>

							</rich:dataTable>
							
					
						
						
						
						
				
					</div>		
				<div class="row">
					<rich:spacer height="20px"></rich:spacer>
				</div>
				
				
		
		 </h:form>
	</f:view>
</ui:composition>