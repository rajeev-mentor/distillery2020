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
					<h:outputText value="Update Inspection"
						style="FONT-WEIGHT: bold; FONT-SIZE: 35px;font-family:Comic Sans MS;"></h:outputText>
					<h:inputHidden value="#{update_InspectionAction.hidden}"></h:inputHidden>

				</div>
			</div>

			<hr style="border-top: 7px #D0D3D4; border-top-style: dashed;"></hr>

			<rich:spacer height="10px;"></rich:spacer>
			<div class="col-md-12" align="center"
				style="BACKGROUND-COLOR: beige;">
				<h:selectOneRadio value="#{update_InspectionAction.radio_type}"
					valueChangeListener="#{update_InspectionAction.radioVal}"
					onchange="this.form.submit();">
                   	<f:selectItem itemValue="S" itemLabel="Spirit" />
					<f:selectItem itemValue="DV" itemLabel="Denatured Spirit" />
					<f:selectItem itemValue="BLENDCL" itemLabel="Blending CL" />
					<f:selectItem itemValue="BLENDFL" itemLabel="Blending FL" />
					<f:selectItem itemValue="BOT-FL" itemLabel="Bottling FL" />
					<f:selectItem itemValue="BOT-CL" itemLabel="Bottling CL" />


				</h:selectOneRadio>
			</div>
			<div class="row">
				<rich:spacer height="20px"></rich:spacer>
			</div>
			<div class="co-md-12 row">

				<div class="col-md-3" align="right">
					<h:outputText value="From Spirit Vat :"
						styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-3" align="left">
					<h:selectOneMenu styleClass="form-control"
						onchange="this.form.submit();"
						valueChangeListener="#{update_InspectionAction.vatListner}"
						value="#{update_InspectionAction.vatno}">
						<f:selectItems value="#{update_InspectionAction.vatList}" />

					</h:selectOneMenu>

				</div>
			</div>

			<div class="col-md-12 row">
				<div class="col-md-3" align="right">
					<h:outputText value="Book Value :"
						styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-1" align="right">
					<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"
						style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-2" align="left">
					<h:outputText value="#{update_InspectionAction.book_bl}"
						styleClass="form-control">
					</h:outputText>
				</div>

				<div class="col-md-1" align="right">
					<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
						style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-2" align="left">
					<h:outputText value="#{update_InspectionAction.book_al}"
						styleClass="form-control">

					</h:outputText>
				</div>

			</div>

			<div class="co-md-12 row">

				<div class="col-md-3" align="right">
					<h:outputText value="Inspection Reading :"
						styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
				</div>

				<div class="col-md-1" align="right">
					<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"
						style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-2" align="left">
					<h:inputText value="#{update_InspectionAction.ins_read_bl}"
						styleClass="form-control">
						<a4j:support event="onblur" reRender="ins_wast_bl"></a4j:support>
						<f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
					</h:inputText>
				</div>

				<div class="col-md-1" align="right">
					<h:outputText value="Strength:"
						styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-2" align="left">
					<h:inputText value="#{update_InspectionAction.ins_read_strength}"
						styleClass="form-control">
						<f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
						<a4j:support event="onblur" reRender="dutyPay22,ins_wast_al"></a4j:support>
					</h:inputText>

				</div>
				<div class="col-md-1" align="right">
					<h:outputText value="AL:" styleClass="generalHeaderOutputTable"
						style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-2" align="left" style="color: blue;">
					<a4j:outputPanel id="dutyPay22">
						<h:outputText value="#{update_InspectionAction.ins_read_al}">
							<a4j:support event="onblur" reRender="ins_wast_al"></a4j:support>
							<f:convertNumber maxFractionDigits="2"
								pattern="#############0.00" />
						</h:outputText>
					</a4j:outputPanel>
				</div>

			</div>

			<div class="col-md-12 row">
				<div class="col-md-3" align="right">
					<h:outputText value="Inspection Wastage :"
						styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-1" align="right">
					<h:outputText value="Bl:" styleClass="generalHeaderOutputTable"
						style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-2" align="left">
					<a4j:outputPanel id="ins_wast_bl">
						<h:outputText value="#{update_InspectionAction.ins_wast_bl}"
							styleClass="form-control">
											<f:convertNumber maxFractionDigits="2"
											pattern="#############0.00" />
						</h:outputText>
					</a4j:outputPanel>
				</div>

				<div class="col-md-1" align="right">
					<h:outputText value="Al:" styleClass="generalHeaderOutputTable"
						style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-2" align="left">
					<a4j:outputPanel id="ins_wast_al">
						<h:outputText value="#{update_InspectionAction.ins_wast_al}"
							styleClass="form-control">
		<f:convertNumber maxFractionDigits="2"
											pattern="#############0.00" />
						</h:outputText>
					</a4j:outputPanel>
				</div>

			</div>

			<div class="row">
				<rich:spacer height="20px"></rich:spacer>
			</div>
			<div class="panel-footer clearfix">
				<div align="center">

					<h:commandButton class="btn btn-primary"
						action="#{update_InspectionAction.saveMethod}" value="Save"></h:commandButton>

					<rich:spacer width="10px"></rich:spacer>
					<h:commandButton class="btn btn-primary"
						action="#{update_InspectionAction.reset}" value="Reset"></h:commandButton>
				</div>
			</div>

			<div class="row">
				<rich:spacer height="20px"></rich:spacer>
			</div>
			<div>
				<rich:dataTable id="table2" rows="8" width="100%" var="list"
					value="#{update_InspectionAction.datalist}"
					headerClass="TableHead" footerClass="TableHead"
					rowClasses="TableRow1,TableRow2">


					<rich:column>
						<f:facet name="header">
							<h:outputText value="Vats"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.vat_nm}" styleClass="form-control"></h:outputText>
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Date" styleClass="generalHeaderOutputTable"
								style="FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.date}" styleClass="form-control"></h:outputText>
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Vat Type"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.type}"
							styleClass="form-control"></h:outputText>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="BooK AL"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.bk_al}"
							styleClass="form-control"></h:outputText>
					</rich:column>



					<rich:column>
						<f:facet name="header">
							<h:outputText value="BooK BL"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<a4j:outputPanel>
							<h:outputText value="#{list.bk_bl}"
								styleClass="form-control"></h:outputText>
						</a4j:outputPanel>
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Read AL"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<a4j:outputPanel>
							<h:outputText value="#{list.read_al}"
								styleClass="form-control"></h:outputText>
						</a4j:outputPanel>
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Read BL"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.read_bl}"
							styleClass="form-control"></h:outputText>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Wast AL"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.wst_al}"
							styleClass="form-control"></h:outputText>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Wast BL"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.wst_bl}"
							styleClass="form-control"></h:outputText>
					</rich:column>
					



					<f:facet name="footer">
						<rich:datascroller for="table2"></rich:datascroller>
					</f:facet>

				</rich:dataTable>

			</div>

		</h:form>
	</f:view>
</ui:composition>