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
						<h1>
							<h:inputHidden value="#{pD2recievingAction.hidden}"></h:inputHidden>
							<h:outputText value="PD25 RECIEVING"
								style="FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;" />
						</h1>
					</div>
				</div>
				<rich:separator lineType="dashed" />
				<div class="col-md-12" align="center">
				<h:messages errorStyle="color:black;background-color: #feeeee;font-size: 14px;margin-top:7px;font-weight: bold;width: 100%;height:35px;border-style: solid;border-width: thin;border-color:red;" 
				 id="messages"
				infoStyle="color:black;background-color: #c9e4c9;font-size: 14px;margin-top:7px;font-weight: bold;width: 100%;height:35px;border-style: solid;border-width: thin;border-color:green;"
				 >
			</h:messages>
			</div>
				<h:panelGroup rendered="#{pD2recievingAction.viewflag == false}">
					<div class="row" align="center">
						<rich:dataTable columnClasses="columnClass1"
							headerClass="TableHead" rowClasses="TableRow1,TableRow2"
							styleClass="DataTable" id="table2" rows="20" width="80%"
							value="#{pD2recievingAction.showData}" var="list">
							<f:facet name="header">
									<h:outputText value="Pending Gatepasses"
										style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
									</h:outputText>
								</f:facet>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Sr.No"
										style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
									</h:outputText>
								</f:facet>
								<h:outputText value="#{list.srno}"
									styleClass="generalHeaderStyleOutput">

								</h:outputText>


							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Gatepass Date"
										style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
									</h:outputText>
								</f:facet>
								<h:outputText value="#{list.gate_dt}"
									styleClass="generalHeaderStyleOutput">
									<f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+5:30" />
								</h:outputText>
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Gatepass No"
										style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
									</h:outputText>
								</f:facet>
								<h:outputText value="#{list.gatepass_no}"
									styleClass="generalHeaderStyleOutput">

								</h:outputText>
							</rich:column>
							 

							<rich:column>
								<f:facet name="header">
									<h:outputText value="AL"
										style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
									</h:outputText>
								</f:facet>
								<h:outputText value="#{list.al}"
									styleClass="generalHeaderStyleOutput">

								</h:outputText>
							</rich:column>






							<rich:column>
								<f:facet name="header">
									<h:outputText value="BL"
										style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
									</h:outputText>
								</f:facet>
								<h:outputText value="#{list.bl}"
									styleClass="generalHeaderStyleOutput">

								</h:outputText>


							</rich:column>
							<rich:column>
								<f:facet name="header">

								</f:facet>
								<center>
									<h:commandButton class="btn btn-warning"
										action="#{pD2recievingAction.viewMethod}" value="View">
										<f:setPropertyActionListener
											target="#{pD2recievingAction.prt}" value="#{list}" />
									</h:commandButton>
								</center>
							</rich:column>
							<f:facet name="footer">
								<rich:datascroller for="table2" />
							</f:facet>
						</rich:dataTable>
					</div>
				</h:panelGroup>

				<div class="row">
					<rich:spacer height="20px"></rich:spacer>
				</div>
				<h:panelGroup  rendered="#{pD2recievingAction.viewflag == true }" >
					<rich:spacer height="10px;"></rich:spacer>

					<div class="col-md-12" align="center">
						<div class="col-md-2" align="right">
							<h:outputText value=" Gatapass Date :: "
								style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
						</div>
						<div class="col-md-4" align="left">
							<h:inputText value="#{pD2recievingAction.gatapass_v}"
								style="COLOR: #0000ff;" readonly="true"
								styleClass="form-control"></h:inputText>

						</div>
						<div class="col-md-2" align="right">
							<h:outputText value="Gatapass No :: "
								style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
						</div>
						<div class="col-md-4" align="left">
							<h:inputText value="#{pD2recievingAction.gatapass_no_v}"
								style="COLOR: #0000ff;" readonly="true"
								styleClass="form-control"></h:inputText>

						</div>
					</div>
					<div class="col-md-12" align="center">
						<div class="col-md-2" align="right">
							<h:outputText value=" Al :: "
								style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
						</div>
						<div class="col-md-4" align="left">
							<h:inputText value="#{pD2recievingAction.dt_al}"
								style="COLOR: #0000ff;" readonly="true"
								styleClass="form-control"></h:inputText>

						</div>
						<div class="col-md-2" align="right">
							<h:outputText value="Bl :: "
								style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
						</div>
						<div class="col-md-4" align="left">
							<h:inputText value="#{pD2recievingAction.dt_bl}"
								style="COLOR: #0000ff;" readonly="true"
								styleClass="form-control"></h:inputText>

						</div>
					</div>
					<div class="row">
						<rich:spacer height="20px"></rich:spacer>
					</div>
					<div class="col-md-12" align="center"
						style="BACKGROUND-COLOR: beige;">
						<h:selectOneRadio value="#{pD2recievingAction.radio}"  disabled="true">
<f:selectItem itemValue="BL" itemLabel="Blending" />
							 
							<f:selectItem itemValue="BT" itemLabel="Bottling" />


						</h:selectOneRadio>
					</div>
					<div class="row">
						<rich:spacer height="20px"></rich:spacer>
					</div>
					<div class="col-md-12 row">
						<div class="col-md-2">
							<h:outputText value="To Vat :"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>

						</div>
						<div class="col-md-4">
							<h:selectOneMenu styleClass="form-control"
								onchange="this.form.submit();"
								valueChangeListener="#{pD2recievingAction.tanklistnerMFSeconend }"
								value="#{pD2recievingAction.toVatId }">
								<f:selectItems value="#{pD2recievingAction.toVatList }" />

							</h:selectOneMenu>

						</div>
					</div>
					<div class="col-md-12 row">
						<div class="col-md-3" align="left">
							<h:outputText value="Book Value  :"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"
								style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<h:outputText value="#{pD2recievingAction.toVatBl}"
								styleClass="form-control">
								 

							</h:outputText>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText value="Strength:"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<h:outputText value="#{pD2recievingAction.toVatSrength}"
								styleClass="form-control">
								 

							</h:outputText>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
								style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							 
								<h:outputText value="#{pD2recievingAction.toVatAl}"
									styleClass="form-control"  >

								</h:outputText>
							 
						</div>

					</div>
					<div>
						<rich:spacer height="20px"></rich:spacer>
					</div>

					<div class="col-md-12 row">
						<div class="col-md-3" align="left">
							<h:outputText value="Reading Before Transfer  :"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"
								style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<h:inputText value="#{pD2recievingAction.toVatBl_bfr_trns}"
								styleClass="form-control">
								<a4j:support event="onblur"
									reRender="dutyPay1,toVatBl_dly_wst,toVatSrength_dly_wst,toVatAl_dly_wst,toVatBl_trnfr_wst,toVatAl_trnfr_wst"></a4j:support>

							</h:inputText>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText value="Strength:"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<h:inputText value="#{pD2recievingAction.toVatSrength_bfr_trns}"
								styleClass="form-control">
								<a4j:support event="onblur" reRender="dutyPay1,toVatAl_trnfr_wst"></a4j:support>
							</h:inputText>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
								style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<a4j:outputPanel id="dutyPay1">
								<h:outputText value="#{pD2recievingAction.toVatAl_bfr_trns}"
									styleClass="form-control" readonly="true">

								</h:outputText>
							</a4j:outputPanel>
						</div>

					</div>
					<div>
						<rich:spacer height="20px"></rich:spacer>
					</div>
					<div class="col-md-12 row">
						<div class="col-md-3" align="left">
							<h:outputText value="Reading After Transfer  :"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"
								style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<h:inputText value="#{pD2recievingAction.toVatBl_aftr_trns}"
								styleClass="form-control">
								<a4j:support event="onblur"
									reRender="dutyPay2,toVatBl_trnfr,toVatBl_trnfr_wst,toVatSrength_trnfr,toVatAl_trnfr,toVatAl_trnfr_wst"></a4j:support>

							</h:inputText>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText value="Strength:"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<h:inputText value="#{pD2recievingAction.toVatSrength_aftr_trns}"
								styleClass="form-control">
								<a4j:support event="onblur" reRender="dutyPay2,toVatAl_trnfr,toVatSrength_trnfr,toVatAl_trnfr_wst"></a4j:support>
							</h:inputText>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
								style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<a4j:outputPanel id="dutyPay2">
								<h:outputText value="#{pD2recievingAction.toVatAl_aftr_trns}"
									styleClass="form-control" readonly="true">

								</h:outputText>
							</a4j:outputPanel>
						</div>

					</div>
					<div>
						<rich:spacer height="20px"></rich:spacer>
					</div>
					<div class="col-md-12 row">
						<div class="col-md-3" align="left">
							<h:outputText value="Daily Wastage  :"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"
								style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<a4j:outputPanel id="toVatBl_dly_wst">
								<h:outputText value="#{pD2recievingAction.toVatBl_dly_wst}"
									styleClass="form-control">

								</h:outputText>
							</a4j:outputPanel>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText value="Strength:"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<a4j:outputPanel id="toVatSrength_dly_wst">
								<h:outputText value="#{pD2recievingAction.toVatSrength_dly_wst}"
									styleClass="form-control">

								</h:outputText>
							</a4j:outputPanel>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
								style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<a4j:outputPanel id="toVatAl_dly_wst">
								<h:outputText value="#{pD2recievingAction.toVatAl_dly_wst}"
									styleClass="form-control">

								</h:outputText>
							</a4j:outputPanel>
						</div>

					</div>
					<div>
						<rich:spacer height="20px"></rich:spacer>
					</div>
					<div class="col-md-12 row">
						<div class="col-md-3" align="left">
							<h:outputText value="Quantity to be transfered :"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"
								style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<a4j:outputPanel id="toVatBl_trnfr">
								<h:outputText value="#{pD2recievingAction.toVatBl_trnfr}"
									styleClass="form-control">
									<a4j:support event="onkeyup"
										reRender="toVatBl_trnfr_wst,toVatSrength_trnfr_wst,toVatAl_trnfr_wst">
									</a4j:support>
								</h:outputText>
							</a4j:outputPanel>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText value="Strength:"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<a4j:outputPanel id="toVatSrength_trnfr">
								<h:outputText value="#{pD2recievingAction.toVatSrength_trnfr}"
									styleClass="form-control">

								</h:outputText>
							</a4j:outputPanel>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
								style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<a4j:outputPanel id="toVatAl_trnfr">
								<h:outputText value="#{pD2recievingAction.toVatAl_trnfr}"
									styleClass="form-control">

								</h:outputText>
							</a4j:outputPanel>
						</div>

					</div>
					<div>
						<rich:spacer height="20px"></rich:spacer>
					</div>
					<div class="col-md-12 row">
						<div class="col-md-3" align="left">
							<h:outputText value="Transfer Wastage :"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-1" align="left">
							<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"
								style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<a4j:outputPanel id="toVatBl_trnfr_wst">
								<h:outputText value="#{pD2recievingAction.toVatBl_trnfr_wst}"
									styleClass="form-control">

								</h:outputText>
							</a4j:outputPanel>
						</div>
						 
						<div class="col-md-1" align="left">
							<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
								style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-2" align="left">
							<a4j:outputPanel id="toVatAl_trnfr_wst">
								<h:outputText value="#{pD2recievingAction.toVatAl_trnfr_wst}"
									styleClass="form-control">

								</h:outputText>
							</a4j:outputPanel>
						</div>

					</div>

					<div class="panel-footer clearfix">
						<div align="center">


							<h:commandButton class="btn btn-primary"
								action="#{pD2recievingAction.saveMethod}" value="Save"></h:commandButton>

							<rich:spacer width="10px"></rich:spacer>

							<h:commandButton class="btn btn-info"
								action="#{pD2recievingAction.close}" value="Close"></h:commandButton>
							<rich:spacer width="10px"></rich:spacer>
							<h:commandButton class="btn btn-info"
								action="#{pD2recievingAction.reset}" value="Reset"></h:commandButton>
						</div>
					</div>
					<div class="row">
						<rich:spacer height="20px"></rich:spacer>

					</div>
					


				</h:panelGroup>


<div class="row" align="center">
						<rich:dataTable columnClasses="columnClass1"
							headerClass="TableHead" rowClasses="TableRow1,TableRow2"
							styleClass="DataTable" id="table3" rows="20" width="80%"
							value="#{pD2recievingAction.saveData}" var="list">
						<f:facet name="header">
									<h:outputText value="Recieved Gatepasses"
										style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
									</h:outputText>
								</f:facet>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Sr.No"
										style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
									</h:outputText>
								</f:facet>
								<h:outputText value="#{list.srno_s}"
									styleClass="generalHeaderStyleOutput">

								</h:outputText>


							</rich:column>
<rich:column>
								<f:facet name="header">
									<h:outputText value="Gatepass"
										style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
									</h:outputText>
								</f:facet>
								<h:outputText value="#{list.gatepass_no}"
									styleClass="generalHeaderStyleOutput">

								</h:outputText>


							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Recieved On"
										style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
									</h:outputText>
								</f:facet>
								<h:outputText value="#{list.recv_dt}"
									styleClass="generalHeaderStyleOutput">

								</h:outputText>


							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Vat Name"
										style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
									</h:outputText>
								</f:facet>
								<h:outputText value="#{list.vat_name_s}"
									styleClass="generalHeaderStyleOutput">

								</h:outputText>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="AL"
										style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
									</h:outputText>
								</f:facet>
								<h:outputText value="#{list.al_s}"
									styleClass="generalHeaderStyleOutput">

								</h:outputText>
							</rich:column>






							<rich:column>
								<f:facet name="header">
									<h:outputText value="BL"
										style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
									</h:outputText>
								</f:facet>
								<h:outputText value="#{list.bl_s}"
									styleClass="generalHeaderStyleOutput">

								</h:outputText>


							</rich:column>


							<f:facet name="footer">
								<rich:datascroller for="table3" />
							</f:facet>
						</rich:dataTable>
					</div>

			</div>
		</f:view>
	</h:form>

</ui:composition>