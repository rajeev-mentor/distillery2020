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
						<h:outputText value="Download Export Order"
							style="COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: 35px;font-family:Comic Sans MS;"></h:outputText>
					</div>
				</div>
				<h:inputHidden value="#{download_Export_OrderAction.hidden}"></h:inputHidden>
				<hr style="border-top: 7px #D0D3D4; border-top-style: dashed;"></hr>
				
				<div class="row " align="center">
					<rich:spacer height="10px"></rich:spacer>
				</div>

				<div class="row col-md-12" align="center"
					style="BACKGROUND-COLOR: #c3e1f4;">
					<div class="col-md-12" align="center">
						<h:selectOneRadio
							   
							value="#{download_Export_OrderAction.radio}"						
							onchange="this.form.submit();">
							<f:selectItem itemValue="pfa" itemLabel="Pending For Approval" />
							<f:selectItem itemValue="DP" itemLabel="Download Permit" />
							
							
							
						</h:selectOneRadio>
					</div>
				</div>
				<div class="row">
					<rich:spacer height="30px"></rich:spacer>
				</div>
             <div class="col-md-12" align="center">
					
					     <rich:dataTable id="tableunit"
						
							width="100%" var="list" rows="10"
							value="#{download_Export_OrderAction.dataTable}"
							rendered="#{download_Export_OrderAction.radio eq 'pfa'}"
							headerClass="TableHead" footerClass="TableHead" 
							rowClasses="TableRow1,TableRow2">


							<rich:column>
								<f:facet name="header">
									<h:outputText value="Sno."
										styleClass="generalHeaderOutputTable"
										style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.sn}"></h:outputText>
							</rich:column>
							<rich:column >
									<f:facet name="header">
										<h:outputText value="App Id"
											styleClass="generalHeaderOutputTable"
											style="FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>
									<h:outputText value="#{list.appNO}"></h:outputText>
								</rich:column>
							<rich:column >
								<f:facet name="header">
									<h:outputText value="App. Date"
										styleClass="generalHeaderOutputTable"
										style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.orderdate}"></h:outputText>
							</rich:column>
							<rich:column >
								<f:facet name="header">
									<h:outputText value="Order Number"
										styleClass="generalHeaderOutputTable"
										style="FONT-SIZE: small; FONT-WEIGHT: bold;white-space: normal;"></h:outputText>
								</f:facet>

								<h:outputText value="#{list.orderno}"></h:outputText>
							</rich:column>
							<rich:column >
									<f:facet name="header">
										<h:outputText value="Importing Unit"
											styleClass="generalHeaderOutputTable"
											style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>
									<h:outputText value="#{list.impu}"></h:outputText>
								</rich:column>
							
							<rich:column >
								<f:facet name="header">
									<h:outputText value=" Status "
										styleClass="generalHeaderOutputTable"
										style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.status}"></h:outputText>
							</rich:column>
							
							<rich:column >
								<f:facet name="header">
								<h:outputText value="" />
								</f:facet>
								<h:commandButton value="Objection Reply"
											action="#{download_Export_OrderAction.replyObjection}"
											rendered="#{list.objection_reply_button}" 
											styleClass = "btn btn-sm btn-primary btn-sm" >
											<f:setPropertyActionListener value="#{list}"
														target="#{download_Export_OrderAction.dt}" />
			                    </h:commandButton>
							
							</rich:column>
							
							<f:facet name="footer">
                                 <rich:datascroller for="tableunit"></rich:datascroller>
								</f:facet>
							

						</rich:dataTable>
					
						
						
							<rich:dataTable id="tableunitH" rows="10"
                               
								width="100%" var="list2"
								value="#{download_Export_OrderAction.dataTable}"
								headerClass="TableHead" footerClass="TableHead" 
								rowClasses="TableRow1,TableRow2">


								<rich:column rendered="#{download_Export_OrderAction.radio ne 'pfa'}">
									<f:facet name="header">
										<h:outputText value="Sr No."
											styleClass="generalHeaderOutputTable"
											style="FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>
									<h:outputText value="#{list2.sn}"></h:outputText>
								</rich:column>
								<rich:column rendered="#{download_Export_OrderAction.radio ne 'pfa'}">
									<f:facet name="header">
										<h:outputText value="App Id"
											styleClass="generalHeaderOutputTable"
											style="FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>
									<h:outputText value="#{list2.appNO}"></h:outputText>
								</rich:column>
								<rich:column rendered="#{download_Export_OrderAction.radio ne 'pfa'}">
									<f:facet name="header">
										<h:outputText value="App. Date"
											styleClass="generalHeaderOutputTable"
											style="FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>
									<h:outputText value="#{list2.orderdate}"></h:outputText>
								</rich:column>
								
								<rich:column rendered="#{download_Export_OrderAction.radio ne 'pfa'}">
									<f:facet name="header">
										<h:outputText value=" Order Number"
											styleClass="generalHeaderOutputTable"
											style="FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>
									<h:outputText value="#{list2.orderno}"></h:outputText>
								</rich:column>
								<rich:column rendered="#{download_Export_OrderAction.radio ne 'pfa'}" >
									<f:facet name="header">
										<h:outputText value="Importing Unit"
											styleClass="generalHeaderOutputTable"
											style="FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>
									<h:outputText value="#{list2.impu}"></h:outputText>
								</rich:column>

								<rich:column rendered="#{download_Export_OrderAction.radio eq 'DP'}">
									<f:facet name="header">
										<h:outputText value="Status"
											styleClass="generalHeaderOutputTable"
											style="FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>
									<h:outputText value="#{list2.status}"></h:outputText>
								</rich:column>


								<rich:column rendered="#{download_Export_OrderAction.radio eq 'ms2'}">
									<f:facet name="header">
										<h:outputText value="Container Number"
											style="FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>

									<h:outputText style="width: 280px"
										value="#{list2.contno}"></h:outputText>
								</rich:column>
								<rich:column rendered="#{download_Export_OrderAction.radio eq 'ms2'}">
									<f:facet name="header">
										<h:outputText value="Container Type"
											style="FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>

									<h:outputText style="width: 280px"
										value="#{list2.conttype}"></h:outputText>
								</rich:column>

								<rich:column rendered="#{download_Export_OrderAction.radio eq 'ms2' }">
									<f:facet name="header">
										<h:outputText value="Seal Number"
											styleClass="generalHeaderOutputTable"
											style="FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>

									<h:outputText value="#{list2.seal}"></h:outputText>
								</rich:column>


                          <rich:column rendered="#{download_Export_OrderAction.radio eq 'ms2' }">
									<f:facet name="header">
										<h:outputText value="Seal Date"
											styleClass="generalHeaderOutputTable"
											style="FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>

									<h:outputText value="#{list2.sealdate}"></h:outputText>
								</rich:column>
								
								<rich:column rendered="#{download_Export_OrderAction.radio eq 'ms2'}">
									<f:facet name="header">
										<h:outputText value="Seal Approval Status"
											styleClass="generalHeaderOutputTable"
											style="FONT-WEIGHT: bold;"></h:outputText>
									</f:facet>

									<h:outputText value="#{list2.sealstatus}"></h:outputText>
								</rich:column>
								<rich:column align="center" rendered="#{download_Export_OrderAction.radio eq 'DP'}">
										<f:facet name="header">
											<h:outputText value="Download Permit" style="FONT-WEIGHT: bold;">
											</h:outputText>
										  </f:facet>
		    
								<h:outputLink
							
							target="_blank"
							value="/doc/ExciseUp/ExportOutsideIndia/License/#{list2.pdfName}">
							<h:graphicImage value="/img/download.gif" alt="view document"
								style="width : 60px; height : 35px;"></h:graphicImage>
						</h:outputLink>
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