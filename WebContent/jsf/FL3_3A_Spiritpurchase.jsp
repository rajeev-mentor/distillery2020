<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:rich="http://richfaces.org/rich"
	xmlns:a4j="http://richfaces.org/a4j">

	<f:view>
		<h:form>
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
					<h:outputText value="Spirit Purchase /Import"
						style="FONT-WEIGHT: bold; FONT-SIZE: 35px;font-family:Comic Sans MS;"></h:outputText>
					<h:inputHidden value="#{fL3_3A_SpiritpurchaseAction.hidden}"></h:inputHidden>

				</div>
			</div>

			<hr style="border-top: 7px #D0D3D4; border-top-style: dashed;"></hr>

			<div class="col-md-12 row">
				<rich:spacer height="10px" />
			</div>
			<div class="row col-md-12" align="center"
				style="BACKGROUND-COLOR: #dee0e2;">
				<div class="col-md-12" align="center">
					<h:selectOneRadio value="#{fL3_3A_SpiritpurchaseAction.radio}"
						valueChangeListener="#{fL3_3A_SpiritpurchaseAction.radiomethod}"
						onchange="this.form.submit();">

						<f:selectItem itemValue="UP" itemLabel="With in State" />
						<f:selectItem itemValue="OUP" itemLabel="Outside State" />
						<f:selectItem itemValue="OC" itemLabel="Outside Country" />
					</h:selectOneRadio>
				</div>

			</div>
			<div class="col-md-12 row">
				<rich:spacer height="20px" />
			</div>
			<div class="col-md-12">
				<div class="col-md-3" align="right">
					<h:outputText value="Import/Purchase From:"
						style="FONT-SIZE: 14px; FONT-WEIGHT: bold;"></h:outputText>
				</div>

				<div class="col-md-3" align="left">
					<h:selectOneMenu styleClass="form-control" style="COLOR: #0000ff;"
						value="#{fL3_3A_SpiritpurchaseAction.dist_id}"
						rendered="#{fL3_3A_SpiritpurchaseAction.dist_flg}">
						<f:selectItems value="#{fL3_3A_SpiritpurchaseAction.dist_list}"></f:selectItems>
					</h:selectOneMenu>
					<h:selectOneMenu styleClass="form-control" style="COLOR: #0000ff;"
						value="#{fL3_3A_SpiritpurchaseAction.state_id}"
						rendered="#{fL3_3A_SpiritpurchaseAction.state_flg}">
						<f:selectItems value="#{fL3_3A_SpiritpurchaseAction.state_list}"></f:selectItems>
					</h:selectOneMenu>
					<h:selectOneMenu styleClass="form-control" style="COLOR: #0000ff;"
						value="#{fL3_3A_SpiritpurchaseAction.contri_id}"
						rendered="#{fL3_3A_SpiritpurchaseAction.contri_flg}">
						<f:selectItems value="#{fL3_3A_SpiritpurchaseAction.contri_list}"></f:selectItems>
					</h:selectOneMenu>

				</div>
			</div>





			<div class="col-md-12 row">
				<rich:spacer height="5px" />
			</div>
			<div class="col-md-12 row">
				<div class="col-md-3" align="right">
					<h:outputText value="Licence Type:"
						style="FONT-SIZE: 14px; FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-3" align="left">
					<h:selectOneRadio value="#{fL3_3A_SpiritpurchaseAction.vch_from}"
						 disabled="true"
						 >
						<f:selectItem itemValue="FL3" itemLabel="FL3" />
						<f:selectItem itemValue="FL3A" itemLabel="FL3A" />

					</h:selectOneRadio>
				</div>

				<div class="col-md-3" align="right">
					<h:outputText value="License No. "
						style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-3" align="left">
       <h:outputText value="#{fL3_3A_SpiritpurchaseAction.vch_from_lic_no}"
					 ></h:outputText>

					 

				</div>
			</div>

			<div class="col-md-12 row">
				<div class="col-md-3" align="right">
					<h:outputText value="To Vat :"
						style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
					 
				</div>

				<div class="col-md-3" align="left">
					<h:selectOneMenu styleClass="form-control" style="COLOR: #0000ff;"
						value="#{fL3_3A_SpiritpurchaseAction.vatid}"
						valueChangeListener="#{fL3_3A_SpiritpurchaseAction.spritvatlistner}"
						onchange="this.form.submit();">
						<f:selectItems value="#{fL3_3A_SpiritpurchaseAction.vatList}" />
					</h:selectOneMenu>

				</div>
				<div class="col-md-3" align="right">
					<h:outputText value="Spirit Type"
						style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-3" align="left">
					<h:selectOneMenu styleClass="form-control" style="COLOR: #0000ff;"
						value="#{fL3_3A_SpiritpurchaseAction.spritTypeId}">
						<f:selectItems value="#{fL3_3A_SpiritpurchaseAction.spritType }" />
					</h:selectOneMenu>
				</div>
			</div>
			<div>
				<rich:spacer height="30px"></rich:spacer>
			</div>
			<div class="newsdiv">
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
						<h:outputText value="#{fL3_3A_SpiritpurchaseAction.quntity_bl_b}"
							styleClass="form-control">
							<a4j:support event="onblur" reRender="dutyPay"></a4j:support>
						</h:outputText>
					</div>

					<div class="col-md-1" align="left">
						<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
							style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<h:outputText value="#{fL3_3A_SpiritpurchaseAction.quntity_al_b}"
							styleClass="form-control">

						</h:outputText>
					</div>

				</div>
				<div>
					<rich:spacer height="20px"></rich:spacer>
				</div>
				<rich:spacer height="10px;"></rich:spacer>

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
						<h:inputText
							value="#{fL3_3A_SpiritpurchaseAction.quantity_bl_befr}"
							styleClass="form-control">
							<a4j:support event="onblur"
								reRender="quantityFinal_wast,quantityFinal_trnfr,quantityFinalal_wast,dutyPay1"></a4j:support>

						</h:inputText>
					</div>
					<div class="col-md-1" align="left">
						<h:outputText value="Strength:"
							styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<h:inputText
							value="#{fL3_3A_SpiritpurchaseAction.qty_strength_befr}"
							styleClass="form-control">
							<a4j:support event="onkeyup"
								reRender="dutyPay1,quantityFinalal_wast,quantityFinalal_trnfr"></a4j:support>
						</h:inputText>
					</div>
					<div class="col-md-1" align="left">
						<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
							style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<a4j:outputPanel id="dutyPay1">
							<h:outputText
								value="#{fL3_3A_SpiritpurchaseAction.quantity_al_befr}"
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
						<h:inputText
							value="#{fL3_3A_SpiritpurchaseAction.quantity_bl_after}"
							styleClass="form-control">
							<a4j:support event="onblur"
								reRender="dutyPay2,quantityFinal_trnfr,quantityFinalal_trnfr"></a4j:support>

						</h:inputText>
					</div>
					<div class="col-md-1" align="left">
						<h:outputText value="Strength:"
							styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<h:inputText
							value="#{fL3_3A_SpiritpurchaseAction.qty_strength_aftr}"
							styleClass="form-control">
							<a4j:support event="onkeyup"
								reRender="dutyPay2,quantityFinalal_trnfr"></a4j:support>
						</h:inputText>
					</div>
					<div class="col-md-1" align="left">
						<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
							style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<a4j:outputPanel id="dutyPay2">
							<h:outputText
								value="#{fL3_3A_SpiritpurchaseAction.quantity_al_after}"
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
						<h:outputText value="Wastage  :"
							styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-1" align="left">
						<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"
							style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<a4j:outputPanel id="quantityFinal_wast">
							<h:outputText
								value="#{fL3_3A_SpiritpurchaseAction.quantity_bl_wast}"
								styleClass="form-control">
							</h:outputText>
						</a4j:outputPanel>
					</div>
					<div class="col-md-1" align="left">
						<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
							style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<a4j:outputPanel id="quantityFinalal_wast">
							<h:outputText
								value="#{fL3_3A_SpiritpurchaseAction.quantity_al_wast}"
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
						<h:outputText value="Quabtity to be Recive :"
							styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-1" align="left">
						<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"
							style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<a4j:outputPanel id="quantityFinal_trnfr">
							<h:outputText
								value="#{fL3_3A_SpiritpurchaseAction.quantity_bl_recv}"
								styleClass="form-control">

							</h:outputText>
						</a4j:outputPanel>
					</div>

					<div class="col-md-1" align="left">
						<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
							style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<a4j:outputPanel id="quantityFinalal_trnfr">
							<h:outputText
								value="#{fL3_3A_SpiritpurchaseAction.quantity_al_recv}"
								styleClass="form-control">

							</h:outputText>
						</a4j:outputPanel>
					</div>

				</div>
				<div>
					<rich:spacer height="30px"></rich:spacer>
				</div>
			</div>
			<div>
				<rich:spacer height="10px"></rich:spacer>
			</div>
			<div class="panel-footer clearfix">
				<div align="center">

					<h:commandButton styleClass="btn btn-success btn-sm" 
						action="#{fL3_3A_SpiritpurchaseAction.saveMethod}" value="Save"></h:commandButton>

					<rich:spacer width="10px"></rich:spacer>
					<h:commandButton styleClass="btn btn-warning btn-sm" 
						action="#{fL3_3A_SpiritpurchaseAction.reset}" value="Reset"></h:commandButton>
				</div>
			</div>


			<div class="col-md-12 row">
				<rich:spacer height="5px" />
			</div>
			<div align="center">
				<h:outputText value="Save Detail for FL3/FL3A"
					styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>


			</div>

			<div class="col-md-12 row">
				<rich:spacer height="5px" />
			</div>
			<div class="row" align="center">
				<rich:dataTable columnClasses="columnClass1" headerClass="TableHead"
					rowClasses="TableRow1,TableRow2" styleClass="DataTable" id="table2"
					rows="20" width="80%"
					value="#{fL3_3A_SpiritpurchaseAction.showData}" var="list">
					<rich:column>

						<f:facet name="header">
							<h:outputText value="S.No"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.srno}"
							styleClass="generalHeaderStyleOutput">
						</h:outputText>
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputText value="License No"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.lic_no}"
							styleClass="generalHeaderStyleOutput">

						</h:outputText>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="VAT Type"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.lic_type}"
							styleClass="generalHeaderStyleOutput">

						</h:outputText>


					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Transfer BL"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.tranfr_bl}"
							styleClass="generalHeaderStyleOutput">

						</h:outputText>
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Transfer AL"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.trnfr_al}"
							styleClass="generalHeaderStyleOutput">

						</h:outputText>
					</rich:column>






					<rich:column>
						<f:facet name="header">
							<h:outputText value="Gatepass"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.int_id}"
							styleClass="generalHeaderStyleOutput">

						</h:outputText>


					</rich:column>



				</rich:dataTable>
			</div>
			<div>
				<rich:spacer height="30px"></rich:spacer>

			</div>


		</h:form>
	</f:view>

</ui:composition>