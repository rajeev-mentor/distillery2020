<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>

		<h:form>
			<div class="container" style="width: 100%; 	">
				<div class="row">
					<div>

						<a4j:outputPanel id="msg">
							<div class="col-md-12 wow shake" align="center">
								<h3>
									<h:messages errorStyle="color:red" layout="table" id="messages"
										infoStyle="color:green">
									</h:messages>
								</h3>
							</div>
						</a4j:outputPanel>
					</div>

					<div class="row" align="center">
						<div class="col-md-12">
							<h:outputLabel value="Prepration Of Blend  "
								style="text-align:center;font-family:Calibri;font-weight:bold;font-size:35px;COLOR: #0000a0;" />
							<h:inputHidden value="#{prepration_of_blend_act.hidden}"></h:inputHidden>
						</div>
					</div>

					<div>
						<rich:separator lineType="dashed"></rich:separator>
					</div>
				</div>
				<rich:spacer height="10px" />
				<div class="col-md-12 row">
					<div class="col-md-3" align="left">
						<h:outputText value="Blending Vat ::"
							styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-3" align="left">
						<h:selectOneMenu value="#{prepration_of_blend_act.storageId}"
							valueChangeListener="#{prepration_of_blend_act.blntanklistnerMF4 }"
							onchange="this.form.submit();" styleClass="form-control">
							<f:selectItems value="#{prepration_of_blend_act.storage}" />
							<a4j:support event="onchange"
								actionListener="#{prepration_of_blend_act.calculateBillAmt}">
							</a4j:support>
						</h:selectOneMenu>
					</div>
					<div class="col-md-1" align="left">
						<h:outputText value="BL ::" styleClass="generalHeaderOutputTable"
							style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left" >
						<h:inputText value="#{prepration_of_blend_act.blendingbl}" disabled="true">
							<a4j:support event="onblur" reRender=" bl"></a4j:support>
						</h:inputText>
					</div>
					<div class="col-md-1" align="left">
						<h:outputText value="AL ::" styleClass="generalHeaderOutputTable"
							style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<h:inputText value="#{prepration_of_blend_act.blendingal}" disabled="true">
							<a4j:support event="onblur" reRender=" bl"></a4j:support>

						</h:inputText>
					</div>



				</div>


				<rich:spacer height="5px"></rich:spacer>
				<div class="col-md-12 row">
					<div class="col-md-3" align="left">
						<h:outputText value="Add Blending Material ::"
							styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-3" align="right">
						<h:inputText value="#{prepration_of_blend_act.bl }">
							<a4j:support event="onblur" reRender=" bl"></a4j:support>
						</h:inputText>

					</div>
				</div>
				<div>
				<rich:spacer height="5px"></rich:spacer>
				</div>
				<div class="col-md-12 row">
					<div class="col-md-3" align="left">
						<h:outputText value="Add Water  ::"
							styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-3" align="right">
						<h:inputText value="#{prepration_of_blend_act.blwater }">
							<a4j:support event="onblur" reRender=" bl"></a4j:support>
						</h:inputText>

					</div>
				</div>

					<div>
				<rich:spacer height="5px"></rich:spacer>
				</div>





				<div class="col-md-12 row">
					<div class="col-md-2" align="left">
						<h:outputText value="Produced Blend::"
							styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
					</div>

				</div>

				<div class="col-md-12 row">
					<div class="col-md-1" align="left">
						<h:outputText value="BL ::" styleClass="generalHeaderOutputTable"
							style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="right">
						<a4j:outputPanel id="bl">
							<h:inputText value="#{prepration_of_blend_act.tot_bl}" disabled="true">
							
								<a4j:support event="onblur" reRender=" abl"></a4j:support>
								<f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
							</h:inputText>
						</a4j:outputPanel>
					</div>
					<div class="col-md-1" align="left">
						<h:outputText value="Strength::"
							styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="right">
						<a4j:outputPanel id="tot_bl">
							<h:inputText value="#{prepration_of_blend_act.tot_str}">
								<a4j:support event="onblur" reRender=" abl"></a4j:support>
							</h:inputText>
						</a4j:outputPanel>
					</div>
					<div class="col-md-1" align="left">
						<h:outputText value="AL::" styleClass="generalHeaderOutputTable"
							style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-2" align="right">
						<a4j:outputPanel id="abl">
							<h:inputText value="#{prepration_of_blend_act.tot_al}" disabled="true">
								<a4j:support event="onblur" reRender=" tot_bl"></a4j:support>
								<f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
							</h:inputText >
						</a4j:outputPanel>
					</div>

				</div>


			</div>

			<rich:spacer height="45px"></rich:spacer>
			<div style="width: 100%; ">
				<div align="center">
					<h:commandButton value="Save" styleClass="btn btn-primary"
						action="#{prepration_of_blend_act.save }">
					</h:commandButton>
					<rich:spacer width="10px"></rich:spacer>
					<h:commandButton styleClass="btn btn-primary"
						action="#{prepration_of_blend_act.reset}" value="Reset"></h:commandButton>
				</div>

			</div>
			<rich:spacer height="15px" />
			<div class="row" style="width: 100%;" align="center">
				<div class="col-md-12">
					<rich:dataTable columnClasses="columnClass1"
						headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2" styleClass="DataTable"
						id="table3" rows="10" width="90%"
						value="#{prepration_of_blend_act.displaylist}" var="list">
						<rich:column>
							<f:facet name="header">
								<h:outputText value="Sr.No">
								</h:outputText>
							</f:facet>
							<h:outputText value="#{list.sno}"
								styleClass="generalHeaderStyleOutput">
							</h:outputText>
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Blending Vat">
								</h:outputText>
							</f:facet>
							<h:outputText value="#{list.vat_name}"
								styleClass="generalHeaderStyleOutput">
							</h:outputText>
						</rich:column>



						<rich:column>
							<f:facet name="header">
								<h:outputText value="Vat AL">
								</h:outputText>
							</f:facet>
							<h:outputText value="#{list.vat_al}"
								styleClass="generalHeaderStyleOutput">
							</h:outputText>
						</rich:column>


						<rich:column>
							<f:facet name="header">
								<h:outputText value="Vat BL">
								</h:outputText>
							</f:facet>
							<h:outputText value="#{list.vat_bl}"
								styleClass="generalHeaderStyleOutput">
							</h:outputText>
						</rich:column>





						<rich:column>
							<f:facet name="header">
								<h:outputText value="Water">
								</h:outputText>
							</f:facet>
							<h:outputText value="#{list.water}"
								styleClass="generalHeaderStyleOutput">
							</h:outputText>
						</rich:column>


						<rich:column>
							<f:facet name="header">
								<h:outputText value="Produced BL ">
								</h:outputText>
							</f:facet>
							<h:outputText value="#{list.pbl}"
								styleClass="generalHeaderStyleOutput">
							</h:outputText>
						</rich:column>



						<rich:column>
							<f:facet name="header">
								<h:outputText value="Produces AL">
								</h:outputText>
							</f:facet>
							<h:outputText value="#{list.pal}"
								styleClass="generalHeaderStyleOutput">
							</h:outputText>
						</rich:column>



						<f:facet name="footer">
							<rich:datascroller for="table3"></rich:datascroller>
						</f:facet>
					</rich:dataTable>
				</div>
			</div>

		</h:form>

	</f:view>

</ui:composition>