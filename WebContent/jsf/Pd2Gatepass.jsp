<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">


	<h:form>
		<f:view>
		 
						
			<div class="form-group">
			
			
				<div class="row " align="center">
				
					<div align="center" width="100%" class="pghead">
						

                            <rich:separator lineType="dashed" />
						<h1><h:inputHidden
									value="#{gatepassForBottolingAction.hidden}"></h:inputHidden>
							<h:outputText
										value="Pd25 Gatepass"
										style="FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;" />
						</h1>
						<h:outputText value="#{gatepassForBottolingAction.dist_name}" style="FONT-WEIGHT: bold;"></h:outputText>
						
					
						<rich:spacer height="10px"></rich:spacer>
						<rich:separator lineType="dashed" />
					</div>
				</div>
				
					<div class="col-md-12" align="center">
				<h:messages errorStyle="color:black;background-color: #feeeee;font-size: 14px;margin-top:7px;font-weight: bold;width: 100%;height:35px;border-style: solid;border-width: thin;border-color:red;" 
				 id="messages"
				infoStyle="color:black;background-color: #c9e4c9;font-size: 14px;margin-top:7px;font-weight: bold;width: 100%;height:35px;border-style: solid;border-width: thin;border-color:green;"
				 >
			</h:messages>
			</div> 
				
						<rich:spacer height="10px;"></rich:spacer>
<div class="col-md-12" align="center" style="BACKGROUND-COLOR: beige;"> 
						<h:selectOneRadio
													value="#{gatepassForBottolingAction.radio}"
													valueChangeListener="#{gatepassForBottolingAction.radioVal}"
													onchange="this.form.submit();">

													<f:selectItem itemValue="BL" itemLabel="Blend" />
													<f:selectItem itemValue="S" itemLabel="Spirit" />


												</h:selectOneRadio>
					</div>
					<div class="row">
					<rich:spacer height="20px"></rich:spacer>
					</div>
				
				
					<div class="col-md-12 row">
              <div class="col-md-1" align="left">
             <h:outputText   value="FROM"   style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
												
              </div>	
                 <div class="col-md-3" align="left">
                 <h:selectOneRadio
																value="#{gatepassForBottolingAction.vch_from}"
																onchange="this.form.submit()"
																valueChangeListener="#{gatepassForBottolingAction.fromListMethod}">
																<f:selectItem itemValue="FL3" itemLabel="FL3" />
																<f:selectItem itemValue="FL3A" itemLabel="FL3A" />

															</h:selectOneRadio>
              </div>	
              
              <rich:spacer height="50px"></rich:spacer>
              
              
				<rich:spacer height="10px"></rich:spacer>
              <div class="col-md-1" align="left">
              <h:outputText
																value="License No."
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
              </div>
              
              	
              <div class="col-md-3" align="left">
              
              <h:selectOneMenu
																valueChangeListener="#{gatepassForBottolingAction.listMethod}"
																value="#{gatepassForBottolingAction.vch_from_lic_no}"
																onchange="this.form.submit()"
																disabled="#{gatepassForBottolingAction.lic_disable_flag2}"
																style="width: 250px; height: 28px;"
																>
																<f:selectItems
																	value="#{gatepassForBottolingAction.fromliclist}" />
															</h:selectOneMenu>
              </div>	
              <rich:spacer height="10"></rich:spacer>		
					<div class="col-md-3" align="left">GatePass Date:-<rich:calendar
													value="#{gatepassForBottolingAction.gatepass_date}"
													inputStyle="height:25px" datePattern="dd/MM/yyyy"
													></rich:calendar></div>
					
					
					</div>
					<rich:spacer height="10"></rich:spacer>
					<div class="col-md-12 row">
					<div class="col-md-1" align="left">
					<h:outputText value="From Vat  :"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
												<div class="col-md-3">
					<h:selectOneMenu styleClass="form-control" onchange="this.form.submit();" valueChangeListener="#{gatepassForBottolingAction.tanklistnerMF4 }" value="#{gatepassForBottolingAction.vatNo }">
												<f:selectItems value="#{gatepassForBottolingAction.vatNoList }" />

											</h:selectOneMenu>
					
					</div>
					
					
					</div>
					<rich:spacer height="20"></rich:spacer>
					
					
					<div><rich:spacer height="30px"></rich:spacer></div>
					<div class="newsdiv">
					<div class="col-md-12 row">
					<div class="col-md-3" align="left"><h:outputText value="Book Value  :"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
				<div class="col-md-1" align="left"><h:outputText value="Bl:"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
		     	<div class="col-md-2" align="left"><h:outputText
												value="#{gatepassForBottolingAction.quantityFinal}"
												styleClass="form-control">
													<a4j:support event="onblur"
													reRender="dutyPay"></a4j:support>
											</h:outputText></div>
				 
			<div class="col-md-1" align="left"><h:outputText value="Al:"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
		     	<div class="col-md-2" align="left">
		     	 <h:outputText
												value="#{gatepassForBottolingAction.quantityFinalal}"
												styleClass="form-control">
													 
											</h:outputText>
											 </div>
					
					</div>
					<div>
					<rich:spacer height="20px"></rich:spacer>
					</div>
					<rich:spacer height="10px;"></rich:spacer>
					
								<div class="col-md-12 row">
					<div class="col-md-3" align="left"><h:outputText value="Reading Before Transfer  :"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
				<div class="col-md-1" align="left"><h:outputText value="Bl:"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
		     	<div class="col-md-2" align="left"><h:inputText
												value="#{gatepassForBottolingAction.quantityFinal_befr}"
												styleClass="form-control">
												<a4j:support event="onblur"
													reRender="quantityFinal_wast,quantityFinal_trnfr,quantityFinalal_wast,dutyPay1"></a4j:support>
											
											</h:inputText></div>
				<div class="col-md-1" align="left"><h:outputText value="Strength:"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
		     	<div class="col-md-2" align="left"><h:inputText
												value="#{gatepassForBottolingAction.qty_strength_befr}"
												styleClass="form-control">
										<a4j:support event="onkeyup" reRender="dutyPay1,quantityFinalal_wast,quantityFinalal_trnfr"></a4j:support>
											</h:inputText></div>
			<div class="col-md-1" align="left"><h:outputText value="Al:"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
		     	<div class="col-md-2" align="left"><a4j:outputPanel id="dutyPay1"><h:outputText
												value="#{gatepassForBottolingAction.quantityFinalal_befr}"
												styleClass="form-control" readonly="true">
											
											</h:outputText></a4j:outputPanel></div>
					
					</div>
					<div>
					<rich:spacer height="20px"></rich:spacer>
					</div>
							<div class="col-md-12 row">
					<div class="col-md-3" align="left"><h:outputText value="Reading After Transfer  :"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
				<div class="col-md-1" align="left"><h:outputText value="Bl:"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
		     	<div class="col-md-2" align="left"><h:inputText
												value="#{gatepassForBottolingAction.quantityFinal_aftr}"
												styleClass="form-control">
												<a4j:support event="onblur"
													reRender="dutyPay2,quantityFinal_trnfr,quantityFinalal_trnfr"></a4j:support>
											
											</h:inputText></div>
				<div class="col-md-1" align="left"><h:outputText value="Strength:"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
		     	<div class="col-md-2" align="left"><h:inputText
												value="#{gatepassForBottolingAction.qty_strength_aftr}"
												styleClass="form-control">
											<a4j:support event="onkeyup" reRender="dutyPay2,quantityFinalal_trnfr"></a4j:support>
											</h:inputText></div>
			<div class="col-md-1" align="left"><h:outputText value="Al:"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
		     	<div class="col-md-2" align="left"><a4j:outputPanel id="dutyPay2"><h:outputText
												value="#{gatepassForBottolingAction.quantityFinalal_aftr}"
												styleClass="form-control" readonly="true">
											
											</h:outputText></a4j:outputPanel>
											</div>
					
					</div>
					<div>
					<rich:spacer height="20px"></rich:spacer>
					</div>
							<div class="col-md-12 row">
					<div class="col-md-3" align="left"><h:outputText value="Wastage  :"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
				<div class="col-md-1" align="left"><h:outputText value="Bl:"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
		     	<div class="col-md-2" align="left"><a4j:outputPanel id="quantityFinal_wast"><h:outputText
												value="#{gatepassForBottolingAction.quantityFinal_wast}"
												styleClass="form-control"> 
											</h:outputText></a4j:outputPanel></div>
			<div class="col-md-1" align="left"><h:outputText value="Al:"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
		     	<div class="col-md-2" align="left"><a4j:outputPanel id="quantityFinalal_wast"><h:outputText
												value="#{gatepassForBottolingAction.quantityFinalal_wast}"
												styleClass="form-control">
											
											</h:outputText></a4j:outputPanel></div>
					
					</div>
					<div>
					<rich:spacer height="20px"></rich:spacer>
					</div>
							<div class="col-md-12 row">
					<div class="col-md-3" align="left"><h:outputText value="Quantity to be transfered :"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
				<div class="col-md-1" align="left"><h:outputText value="Bl:"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
		     	<div class="col-md-2" align="left"><a4j:outputPanel id="quantityFinal_trnfr"><h:outputText
												value="#{gatepassForBottolingAction.quantityFinal_trnfr}"
												styleClass="form-control">
										
											</h:outputText></a4j:outputPanel></div>
				
			<div class="col-md-1" align="left"><h:outputText value="Al:"
												styleClass="generalHeaderOutputTable"
												style="FONT-WEIGHT: bold;"></h:outputText></div>
		     	<div class="col-md-2" align="left"><a4j:outputPanel id="quantityFinalal_trnfr"><h:outputText
												value="#{gatepassForBottolingAction.quantityFinalal_trnfr}"
												styleClass="form-control">
												
											</h:outputText></a4j:outputPanel></div>
					
					</div>
					<div><rich:spacer height="30px"></rich:spacer></div>
					</div>
					<div><rich:spacer height="10px"></rich:spacer></div>
				<div class="panel-footer clearfix">
												<div align="center">

													<h:commandButton class="btn btn-primary"
														action="#{gatepassForBottolingAction.saveMethod}"
														value="Save"></h:commandButton>

													<rich:spacer width="10px"></rich:spacer>
													<h:commandButton class="btn btn-primary"
														action="#{gatepassForBottolingAction.reset}"
														value="Reset"></h:commandButton>
												</div>
											</div>
			
					
				</div>
				
				
				<div height="100px"></div>
				<div align="center">
						<div >
							<div>
								<TABLE width="100%" align="center">
									<TBODY align="center">
									<div align="center"><div><h:outputText value="Saved Details Of Gatepass"
																styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText></div></div>
										
										<rich:spacer height="20px" />
										<div>
											<div><rich:spacer height="10px;"></rich:spacer>
			<div class="row">
				<rich:dataTable columnClasses="columnClass1" headerClass="TableHead"
					rowClasses="TableRow1,TableRow2" styleClass="DataTable" id="table2"
					rows="20" width="100%"
					value="#{gatepassForBottolingAction.showData}" var="list">
					<rich:column>
					
						<f:facet name="header">
							<h:outputText value="S.No"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.srNo}"
							styleClass="generalHeaderStyleOutput">
						</h:outputText>
					</rich:column>
					<rich:column>
					
						<f:facet name="header">
							<h:outputText value="Date"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.gatepass_date}"
							styleClass="generalHeaderStyleOutput">
							<f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+5:30" />
						</h:outputText>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="License No"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.vch_from_lic_no}"
							styleClass="generalHeaderStyleOutput">

						</h:outputText>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="VAT No"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.vatNoList}"
							styleClass="generalHeaderStyleOutput">

						</h:outputText>


					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Transfer BL"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.quantityFinal_trnfr}"
							styleClass="generalHeaderStyleOutput">

						</h:outputText>
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Transfer AL"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.quantityFinalal_trnfr}"
							styleClass="generalHeaderStyleOutput">

						</h:outputText>
					</rich:column>






					<rich:column>
						<f:facet name="header">
							<h:outputText value="Gatepass"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.gtno}"
							styleClass="generalHeaderStyleOutput">

						</h:outputText>


					</rich:column>
					
					<rich:column>
							<h:commandButton styleClass="btn btn-success" value="Print"
								actionListener="#{gatepassForBottolingAction.printreport}"></h:commandButton>
					
						<h:outputLink styleClass="outputLinkEx"
								value="/doc/ExciseUp/Distillery/pdf//#{gatepassForBottolingAction.pdfName}"
								target="_blank">
								<h:outputText styleClass="outputText" id="text223"
									value="View Pdf" rendered="#{list.printFlag}"
									style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
							</h:outputLink>
					</rich:column>
				
				</rich:dataTable>
			</div>
												</div>
										</div>

										<div align="center">
											<div colspan="4" align="center">
												
											</div>
										</div>
									</TBODY>
								</TABLE>
							</div>
						</div>
						
				</div>
				
				</f:view>
				
				
				
				</h:form>
</ui:composition>