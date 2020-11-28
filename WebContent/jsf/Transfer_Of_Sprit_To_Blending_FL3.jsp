
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
					<h:outputText value="Transfer Of Spirit To Blending Vat"
						style="FONT-WEIGHT: bold; FONT-SIZE: 35px;font-family:Comic Sans MS;"></h:outputText>
					<h:inputHidden
						value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_hidden}"></h:inputHidden>

				</div>
			</div>

			<hr style="border-top: 7px #D0D3D4; border-top-style: dashed;"></hr>

			<div class="col-md-12 row">
				<rich:spacer height="10px" />
			</div>

			<div class="co-md-12 row">
				<div class="col-md-2" align="left">
					<h:outputText value="Transfer Date :"
						styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-3" align="right">
					<rich:calendar
						value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_currentDate}"
						disabled="true"></rich:calendar>

				</div>
			</div>
			<div class="col-md-12 row">
				<rich:spacer height="10px" />
			</div>
			<div class="co-md-12 row">

				<div class="col-md-2" align="left">
					<h:outputText value="From Spirit Vat :"
						styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-3" align="right">
					<h:selectOneMenu styleClass="form-control"
						onchange="this.form.submit();"
						valueChangeListener="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_spirit_vatListner}"
						value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_spirt_no}">
						<f:selectItems
							value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_spirt_noList}" />

					</h:selectOneMenu>

				</div>
				<div class="col-md-1" align="left">
					<h:outputText value="BL :" styleClass="generalHeaderOutputTable"
						style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-2" align="right">
					<h:inputText
						value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_spirt_bl}"
						styleClass="form-control" disabled="true">
					</h:inputText>
				</div>
				<div class="col-md-1" align="left">
					<h:outputText value="AL :" styleClass="generalHeaderOutputTable"
						style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-2" align="right">
					<h:inputText
						value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_spirt_al}"
						styleClass="form-control" disabled="true">
					</h:inputText>
				</div>



			</div>
			<div class="col-md-12 row">
				<rich:spacer height="10px" />
			</div>
			
			<div class="co-md-12 row">

				<div class="col-md-2" align="left">
					<h:outputText value="To Blending Vat :"
						styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-3" align="right">
					<h:selectOneMenu styleClass="form-control"
						onchange="this.form.submit();"
						valueChangeListener="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_blendinglistner}"
						value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_blend_vat_no}">
						<f:selectItems
							value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_blend_vat_noList}" />

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
						<h:outputText
							value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_blending_bl}"
							styleClass="form-control">
						</h:outputText>
					</div>

					<div class="col-md-1" align="left">
						<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
							style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<h:outputText
							value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_blending_al}"
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
							value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_blending_bl_bfr}"
							styleClass="form-control">
							<a4j:support event="onblur"
								reRender="dutyPay1,blending_bl_wast,blending_al_wast,blending_bl_trnfr,blending_al_trnfr"></a4j:support>

						</h:inputText>
					</div>
					<div class="col-md-1" align="left">
						<h:outputText value="Strength:"
							styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<h:inputText
							value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_blending_strngth_bfr}"
							styleClass="form-control">
							<a4j:support event="onkeyup" reRender="dutyPay1,blending_al_wast"></a4j:support>
						</h:inputText>
					</div>
					<div class="col-md-1" align="left">
						<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
							style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<a4j:outputPanel id="dutyPay1">
							<h:outputText
								value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_blending_al_bfr}"
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
							value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_blending_bl_aftr}"
							styleClass="form-control">

							<a4j:support event="onblur" reRender="dutyPay2,blending_bl_trnfr"></a4j:support>
						</h:inputText>
					</div>
					<div class="col-md-1" align="left">
						<h:outputText value="Strength:"
							styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<h:inputText
							value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_blending_strnght_aftr}"
							styleClass="form-control">
							<a4j:support event="onblur" reRender="dutyPay2,blending_al_trnfr"></a4j:support>
						</h:inputText>
					</div>
					<div class="col-md-1" align="left">
						<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
							style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<a4j:outputPanel id="dutyPay2">
							<h:outputText
								value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_blending_al_aftr}"
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
						<a4j:outputPanel id="blending_bl_wast">
							<h:outputText
								value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_blending_bl_wast}"
								styleClass="form-control">
							</h:outputText>
						</a4j:outputPanel>
					</div>
					<div class="col-md-1" align="left">
						<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
							style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<a4j:outputPanel id="blending_al_wast">
							<h:outputText
								value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_blending_al_wast}"
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
						<a4j:outputPanel id="blending_bl_trnfr">
							<h:outputText
								value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_blending_bl_trnfr}"
								styleClass="form-control">

							</h:outputText>
						</a4j:outputPanel>
					</div>

					<div class="col-md-1" align="left">
						<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
							style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<a4j:outputPanel id="blending_al_trnfr">
							<h:outputText
								value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_blending_al_trnfr}"
								styleClass="form-control">

							</h:outputText>
						</a4j:outputPanel>
					</div>

				</div>
				<div>
					<rich:spacer height="30px"></rich:spacer>
				</div>
			</div>

			<div class="col-md-12 row">
				<div align="center">


					<h:commandButton class="btn btn-primary"
						action="#{transfer_Of_Sprit_To_Blending_VatAction.saveMethodfl3}"
						value="Save"></h:commandButton>
					<rich:spacer width="10px"></rich:spacer>
					<h:commandButton class="btn btn-info"
						action="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_reset}"
						value="Reset"></h:commandButton>
				</div>

			</div>
			<div>
				<rich:spacer height="10px"></rich:spacer>
			</div>
			<div class="row" align="center">
				<rich:dataTable columnClasses="columnClass1" headerClass="TableHead"
					rowClasses="TableRow1,TableRow2" styleClass="DataTable" id="table3"
					rows="20" width="80%"
					value="#{transfer_Of_Sprit_To_Blending_VatAction.fl3_saveData}"
					var="list">
					<f:facet name="header">
						<h:outputText
							value="Transfer Of Spirit To Blending Vat Save Deatil"
							style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
						</h:outputText>
					</f:facet>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Transfer Date"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.trans_date_dt}"
							styleClass="generalHeaderStyleOutput">

						</h:outputText>


					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="From Spirit Vat"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.from_vat_dt}"
							styleClass="generalHeaderStyleOutput">

						</h:outputText>


					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="To Blending Vat"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.to_vat_dt}"
							styleClass="generalHeaderStyleOutput">

						</h:outputText>


					</rich:column>



					<rich:column>
						<f:facet name="header">
							<h:outputText value="Trnasfer AL"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.trans_bl}"
							styleClass="generalHeaderStyleOutput">

						</h:outputText>
					</rich:column>






					<rich:column>
						<f:facet name="header">
							<h:outputText value="Trnasfer BL"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.trans_al}"
							styleClass="generalHeaderStyleOutput">

						</h:outputText>


					</rich:column>


					<f:facet name="footer">
						<rich:datascroller for="table3" />
					</f:facet>
				</rich:dataTable>
			</div>

		</h:form>
	</f:view>
</ui:composition>