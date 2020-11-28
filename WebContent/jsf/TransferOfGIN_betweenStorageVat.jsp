<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>
		<h:form>
			<div class="panel panel-default">
				<div class="panel-body">
					<div>
						<rich:spacer height="20px;"></rich:spacer>
					</div>
					<div class="row">
						<div class="col-md-12 wow shake" align="center">
							<h:messages errorStyle="color:red" layout="TABLE" id="messages1"
								infoStyle="color:green"
								style="font-size:20px; background-color:#e1fcdf; font-weight: bold">
							</h:messages>

						</div>
					</div>
					<div class="row">
						<div align="center">
							<h:outputText
								value=" * Update your Vat's Capacity from the Vat Entry screen inside Distillery Information."
								style=" color: red;"></h:outputText>


						</div>
					</div>
					<div class="row">
						<div align="center">
							<h:outputText value="TRANSFER OF GIN BETWEEN STORAGE VATS "
								style="FONT-WEIGHT: bold; FONT-SIZE: 20px;font-family:Comic Sans MS;color: darkgreen;"></h:outputText>


						</div>
					</div>
					<div class="row" style="height: 2px; background-color: black;"></div>

					<div align="center">
						<h:outputLabel
							value="#{transferOfGIN_betweenStorageVatAction.dissteleryName}"
							style="COLOR: #000000; FONT-SIZE:  large;"></h:outputLabel>
					</div>

					<div align="center">
						<h:outputLabel
							value="#{transferOfGIN_betweenStorageVatAction.disslryAdd}"
							style="COLOR: #000000; FONT-SIZE: medium;"></h:outputLabel>
					</div>

					<div class="row" style="height: 2px; background-color: black;"></div>
					<div class="co-md-12 row">

						<div class="col-md-2" align="right">
							<h:outputText value="Date :"
								styleClass="generalHeaderOutputTable"></h:outputText>
						</div>
						<div class="col-md-3" align="left">
							<rich:calendar
								value="#{transferOfGIN_betweenStorageVatAction.cueentDate}"
								inputStyle="height:25px" datePattern="dd/MM/yyyy"
								disabled="true"></rich:calendar>

						</div>
						


					</div>
					<div align="left">
						<h:outputText
							value="Book Value ( Quantity Available As Per Portal Records )"
							style="margin-left:15px;FONT-WEIGHT: bold; FONT-SIZE: 15px; text-decoration: underline;color: darkgreen;"></h:outputText>

					</div>
					<div>
						<rich:spacer height="30px"></rich:spacer>
					</div>

					<div class="co-md-12 row">

						<div class="col-md-4" align="right">
							<h:outputText value="GIN Reciever Vat From Which Transfer Is Being Done :"
								styleClass="generalHeaderOutputTable"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<h:selectOneMenu styleClass="form-control"
								onchange="this.form.submit();"
								valueChangeListener="#{transferOfGIN_betweenStorageVatAction.tanklistnerMF4 }"
								value="#{transferOfGIN_betweenStorageVatAction.vatNo }">
								<f:selectItems
									value="#{transferOfGIN_betweenStorageVatAction.vatNoList }" />

							</h:selectOneMenu>

						</div>



						<div class="col-md-1" align="right">
							<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"></h:outputText>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText style="color:blue;"
								value="#{transferOfGIN_betweenStorageVatAction.quantityFinal}">
								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />
							</h:outputText>
						</div>

						<div class="col-md-1" align="right">
							<h:outputText value="Al:" styleClass="generalHeaderOutputTable"></h:outputText>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText style="color:blue;"
								value="#{transferOfGIN_betweenStorageVatAction.quantityFinalal}">
								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />
							</h:outputText>
						</div>
						<div class="col-md-1" align="right">
							<h:outputText value="Strength:"
								styleClass="generalHeaderOutputTable"></h:outputText>
						</div>
						<div class="col-md-1" align="left" style="color: blue;">
							<h:outputText
								value="#{transferOfGIN_betweenStorageVatAction.quantityStrength}">
								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />
							</h:outputText>
						</div>

					</div>
					<div>
						<rich:spacer height="10px"></rich:spacer>
					</div>
					<div class="co-md-12 row">

						<div class="col-md-4" align="right">
							<h:outputText value="Sprit Vat In Which Transfer Is Being Done :"
								styleClass="generalHeaderOutputTable"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<h:selectOneMenu styleClass="form-control"
								onchange="this.form.submit();"
								valueChangeListener="#{transferOfGIN_betweenStorageVatAction.tanklistnerMFSeconend }"
								value="#{transferOfGIN_betweenStorageVatAction.toVatId}">
								<f:selectItems
									value="#{transferOfGIN_betweenStorageVatAction.toVatList}" />

							</h:selectOneMenu>

						</div>


						<div class="col-md-1" align="right">
							<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"></h:outputText>
						</div>
						<div class="col-md-1" align="left" style="color: blue;">
							<h:outputText value="#{transferOfGIN_betweenStorageVatAction.toVatBl}">
								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />
							</h:outputText>
						</div>

						<div class="col-md-1" align="right">
							<h:outputText value="Al:" styleClass="generalHeaderOutputTable"></h:outputText>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText style="color:blue;"
								value="#{transferOfGIN_betweenStorageVatAction.toVatAl}">
								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />
							</h:outputText>
						</div>
						<div class="col-md-1" align="right">
							<h:outputText value="Strength:"
								styleClass="generalHeaderOutputTable"></h:outputText>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText style="color:blue;"
								value="#{transferOfGIN_betweenStorageVatAction.toVatAlStrength}">
								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />
							</h:outputText>
						</div>

					</div>
					<div>
						<rich:spacer height="30px"></rich:spacer>
					</div>

					<div align="left">
						<h:outputText
							value="Quantity As Per Actual Dip Value After Transfer"
							style="margin-left:15px;FONT-WEIGHT: bold; color: darkgreen;FONT-SIZE: 15px;  text-decoration: underline;"></h:outputText>

					</div>

					<div class=" row newsdiv">


						<rich:spacer height="10px;"></rich:spacer>

						<div class="col-md-12 row" align="center">

							<div class="col-md-2"></div>
							<div class="col-md-3" align="left">
								<h:outputText value="Vat From Which Transfer Is Being Done :"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</div>
							<div class="col-md-1" align="left">
								<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"></h:outputText>
							</div>
							<div class="col-md-2" align="left">
								<h:inputText onblur="this.form.submit();"
									value="#{transferOfGIN_betweenStorageVatAction.from_dip_bl}"
									styleClass="form-control">
									<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
									<a4j:support event="onblur" reRender="tranfr_quntity_bl"></a4j:support>

								</h:inputText>
							</div>
							<div class="col-md-1" align="left">
								<h:outputText value="Al:" styleClass="generalHeaderOutputTable"></h:outputText>
							</div>
							<div class="col-md-2" align="left">
								<h:inputText   onblur="this.form.submit();"
									value="#{transferOfGIN_betweenStorageVatAction.from_dip_al}"
									styleClass="form-control">
									<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
									<a4j:support event="onkeyup" reRender="tranfr_quntity_al"></a4j:support>
								</h:inputText>
							</div>



						</div>
						<div>
							<rich:spacer height="20px"></rich:spacer>
						</div>
						<div class="col-md-12 row">

							<div class="col-md-2"></div>
							<div class="col-md-3" align="left">
								<h:outputText value="Vat In Which Transfer Is Being Done :"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</div>
							<div class="col-md-1" align="left">
								<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"></h:outputText>
							</div>
							<div class="col-md-2" align="left">
								<h:inputText  onblur="this.form.submit();"
									value="#{transferOfGIN_betweenStorageVatAction.to_dip_bl}"
									styleClass="form-control">
									<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
									<a4j:support event="onblur"
										reRender="recv_quntity_bl,trns_wst_bl"></a4j:support>
								</h:inputText>
							</div>
							<div class="col-md-1" align="left">
								<h:outputText value="Al:" styleClass="generalHeaderOutputTable"></h:outputText>
							</div>
							<div class="col-md-2" align="left">
								<h:inputText  onblur="this.form.submit();"
									value="#{transferOfGIN_betweenStorageVatAction.to_dip_al}"
									styleClass="form-control">
									<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
									<a4j:support event="onblur"
										reRender="recv_quntity_al,trns_wst_al"></a4j:support>
								</h:inputText>
							</div>

						</div>
						<div>
							<rich:spacer height="20px"></rich:spacer>
						</div>
						<div class="col-md-12 row">

							<div class="col-md-2"></div>
							<div class="col-md-3" align="left">
								<h:outputText value="Quantity Transfered :"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</div>
							<div class="col-md-1" align="left">
								<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"></h:outputText>
							</div>
							<div class="col-md-2" align="left">
								<a4j:outputPanel id="tranfr_quntity_bl">
									<h:outputText
										value="#{transferOfGIN_betweenStorageVatAction.tranfr_quntity_bl}"
										styleClass="form-control">
										<f:convertNumber maxFractionDigits="2"
											pattern="#############0.00" />
									</h:outputText>
								</a4j:outputPanel>
							</div>
							<div class="col-md-1" align="left">
								<h:outputText value="Al:" styleClass="generalHeaderOutputTable"></h:outputText>
							</div>
							<div class="col-md-2" align="left">
								<a4j:outputPanel id="tranfr_quntity_al">
									<h:outputText
										value="#{transferOfGIN_betweenStorageVatAction.tranfr_quntity_al}"
										styleClass="form-control">
										<f:convertNumber maxFractionDigits="2"
											pattern="#############0.00" />
									</h:outputText>
								</a4j:outputPanel>
							</div>

						</div>
						<div>
							<rich:spacer height="20px"></rich:spacer>
						</div>
						<div class="col-md-12 row">

							<div class="col-md-2"></div>
							<div class="col-md-3" align="left">
								<h:outputText value="Quantity Recieved :"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</div>
							<div class="col-md-1" align="left">
								<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"></h:outputText>
							</div>
							<div class="col-md-2" align="left">
								<a4j:outputPanel id="recv_quntity_bl">
									<h:outputText
										value="#{transferOfGIN_betweenStorageVatAction.recv_quntity_bl}"
										styleClass="form-control">
										<a4j:support event="onkeyup" reRender="trns_wst_bl">
										</a4j:support>
										<f:convertNumber maxFractionDigits="2"
											pattern="#############0.00" />
									</h:outputText>
								</a4j:outputPanel>
							</div>

							<div class="col-md-1" align="left">
								<h:outputText value="Al:" styleClass="generalHeaderOutputTable"></h:outputText>
							</div>
							<div class="col-md-2" align="left">
								<a4j:outputPanel id="recv_quntity_al">
									<h:outputText
										value="#{transferOfGIN_betweenStorageVatAction.recv_quntity_al}"
										styleClass="form-control">
										<a4j:support event="onkeyup" reRender="trns_wst_al">
										</a4j:support>
										<f:convertNumber maxFractionDigits="2"
											pattern="#############0.00" />
									</h:outputText>
								</a4j:outputPanel>
							</div>

						</div>

						<div>
							<rich:spacer height="20px"></rich:spacer>
						</div>
						<div class="col-md-12 row">

							<div class="col-md-2"></div>
							<div class="col-md-3" align="left">
								<h:outputText value="Transfer Wastage :"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</div>
							<div class="col-md-1" align="left">
								<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"></h:outputText>
							</div>
							<div class="col-md-2" align="left">
								<a4j:outputPanel id="trns_wst_bl">
									<h:outputText
										value="#{transferOfGIN_betweenStorageVatAction.trns_wst_bl}"
										styleClass="form-control">
										<f:convertNumber maxFractionDigits="2"
											pattern="#############0.00" />
									</h:outputText>
								</a4j:outputPanel>
							</div>

							<div class="col-md-1" align="left">
								<h:outputText value="Al:" styleClass="generalHeaderOutputTable"></h:outputText>
							</div>
							<div class="col-md-2" align="left">
								<a4j:outputPanel id="trns_wst_al">
									<h:outputText
										value="#{transferOfGIN_betweenStorageVatAction.trns_wst_al}"
										styleClass="form-control">
										<f:convertNumber maxFractionDigits="2"
											pattern="#############0.00" />
									</h:outputText>
								</a4j:outputPanel>
							</div>

						</div>
						<div>
							<rich:spacer height="30px"></rich:spacer>
						</div>
					</div>
					<div class="panel-footer clearfix">
						<div align="center">
						<h:commandButton class="btn-sm btn-primary"
						action="#{transferOfGIN_betweenStorageVatAction.saveMethod}" value="Save"></h:commandButton>
							<rich:spacer width="10px"></rich:spacer>
							<h:commandButton class="btn-sm btn-primary"
								action="#{transferOfGIN_betweenStorageVatAction.reset}" value="Reset"></h:commandButton>
						</div>
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
					</div>
					<div>
						<rich:spacer height="30px"></rich:spacer>
					</div>

					<div>
						<rich:dataTable id="table2" rows="10" width="100%" var="list"
							value="#{transferOfGIN_betweenStorageVatAction.showDataTablelist}"
							headerClass="TableHead" footerClass="TableHead"
							rowClasses="TableRow1,TableRow2">
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Sr.No."
										styleClass="generalHeaderOutputTable"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.srNoTabel}"></h:outputText>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Date "
										styleClass="generalHeaderOutputTable"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.dateTable}">
									<f:convertDateTime pattern="dd-M-yyyy" timeZone="GMT+05:30" />
								</h:outputText>
							</rich:column>



							<rich:column>
								<f:facet name="header">
									<h:outputText value="To Vat "
										styleClass="generalHeaderOutputTable"></h:outputText>
								</f:facet>
								<a4j:outputPanel>
									<h:outputText value="#{list.toVatTable}"
										styleClass="form-control"></h:outputText>
								</a4j:outputPanel>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Type "
										styleClass="generalHeaderOutputTable"></h:outputText>
								</f:facet>
								<a4j:outputPanel>
									<h:outputText value="#{list.vType}" styleClass="form-control"></h:outputText>
								</a4j:outputPanel>
							</rich:column>



							<rich:column>
								<f:facet name="header">
									<h:outputText value="Transfer(BL) "
										styleClass="generalHeaderOutputTable"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.quantityToBeTransferBLTable}"
									styleClass="form-control"></h:outputText>
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Transfer(AL) "
										styleClass="generalHeaderOutputTable"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.quantityToBeTransferALTable}"
									styleClass="form-control"></h:outputText>
							</rich:column>


							<rich:column>
								<f:facet name="header">
									<h:outputText value="Transfer Wastage (BL)"
										styleClass="generalHeaderOutputTable"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.wastageTable}"></h:outputText>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value=" Transfer Wastage (AL)"
										styleClass="generalHeaderOutputTable"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.wastageAlTable}"></h:outputText>
							</rich:column>



							<rich:column>
								<f:facet name="header">
									<h:outputText value="Recieved(BL)"
										styleClass="generalHeaderOutputTable"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.netBLTable}"></h:outputText>
							</rich:column>


							<rich:column>
								<f:facet name="header">
									<h:outputText value="Recieved(AL)"
										styleClass="generalHeaderOutputTable"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.netALTable}"></h:outputText>
							</rich:column>


							<f:facet name="footer">
								<rich:datascroller for="table2"></rich:datascroller>
							</f:facet>

						</rich:dataTable>

					</div>
				</div>
				</div>
				</h:form>
				</f:view>
</ui:composition>