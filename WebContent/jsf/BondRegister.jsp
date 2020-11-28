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
				<div align="center" style="background-color: #253f8a;">
					<h:outputText value="Bond Register"
							style="FONT-SIZE: xx-large; FONT-FAMILY: 'Agency FB'; COLOR: #ffffff; TEXT-DECORATION: underline;"></h:outputText>
					<h:inputHidden value="#{bondRegisterAction.hidden}"></h:inputHidden>

				</div>
			</div>

			<hr style="border-top: 7px #D0D3D4; border-top-style: dashed;"></hr>

			<rich:spacer height="10px;"></rich:spacer>
			<div class="co-md-12 row">
				<div class="col-md-2" align="right">
					<h:outputText value="Bond Type:"
						styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
			<div class="col-md-2" 
				>
				<h:selectOneRadio 
				value="#{bondRegisterAction.radio_type}"
					valueChangeListener="#{bondRegisterAction.radioVal}"
					onchange="this.form.submit();">
                   	<f:selectItem itemValue="FL3" itemLabel="FL3" />
					<f:selectItem itemValue="FL3A" itemLabel="FL3A" />
				</h:selectOneRadio>
			</div>

				<div class="col-md-2" align="right">
					<h:outputText value="Bond Name  :"
						styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-2" align="left">
				<h:selectOneMenu styleClass="form-control" 	style="border-radius: 6px;padding: 5px 5px;height: 30px;width: 30%;box-shadow: 1px 1px 15px lightsteelblue;border: 1px solid #669999;"
						onchange="this.form.submit();"
						valueChangeListener="#{bondRegisterAction.bondlistner}"
						value="#{bondRegisterAction.bondId}">
						<f:selectItems value="#{bondRegisterAction.bondList}" />

					</h:selectOneMenu>
				

				</div>
			</div>
			<rich:spacer height="10px;"></rich:spacer>
			<div class="co-md-12 row">

				<div class="col-md-2" align="right">
					<h:outputText value="Register Name  :"
						styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-2" align="left">
					<h:selectOneMenu
						value="#{bondRegisterAction.regisId}"
						styleClass="form-control" 	style="border-radius: 6px;padding: 5px 5px;height: 30px;width: 30%;box-shadow: 1px 1px 15px lightsteelblue;border: 1px solid #669999;" 

						onchange="this.form.submit();">
						<f:selectItems value="#{bondRegisterAction.registerNm_List}" />
					</h:selectOneMenu>

				</div>

				<div class="col-md-2" align="right">
					<a4j:commandButton value="add"
						oncomplete="#{rich:component('popup')}.show()"
						styleClass="btn-sm btn-primary" />
				</div>



			</div>
			<rich:spacer height="10px;"></rich:spacer>
			<div class="co-md-12 row">

				<div class="col-md-2" align="right">
					<h:outputText value="Bond Submission  :"
						styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
				</div>
				<div class="col-md-2" align="left">
			<h:inputText value="#{bondRegisterAction.bond_sbmisn}"
						style="border-radius: 6px;padding: 5px 5px;height: 30px;width: 300px;box-shadow: 1px 1px 4px 4px gray;border: 1px solid #669999;">
			
					</h:inputText>
				</div>
			</div>
			<rich:spacer height="30px"></rich:spacer>
				<div class="row" align="center">
				<h:commandButton action="#{bondRegisterAction.save}" value="Save"  styleClass="btn-sm btn-success"/>
				<rich:spacer width="20px"></rich:spacer>
				<h:commandButton action="#{bondRegisterAction.reset}" value="Reset"  styleClass="btn-sm btn-warning"/>
				</div>
				
				
					<div class="row">
				<rich:spacer height="20px"></rich:spacer>
			</div>
			<div>
				<rich:dataTable id="table2" rows="8" width="100%" var="list"
					value="#{bondRegisterAction.datalist}"
					headerClass="TableHead" footerClass="TableHead"
					rowClasses="TableRow1,TableRow2">


					<rich:column>
						<f:facet name="header">
							<h:outputText value="Sr.No"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.sr}" styleClass="form-control"></h:outputText>
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
							<h:outputText value="Bond Type"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.type}"
							styleClass="form-control"></h:outputText>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Register Name"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.regNm}"
							styleClass="form-control"></h:outputText>
					</rich:column>



					<rich:column>
						<f:facet name="header">
							<h:outputText value="Bond Submission"
								styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<a4j:outputPanel>
							<h:outputText value="#{list.bondSub}"
								styleClass="form-control"></h:outputText>
						</a4j:outputPanel>
					</rich:column>

					
					
					



					<f:facet name="footer">
						<rich:datascroller for="table2"></rich:datascroller>
					</f:facet>

				</rich:dataTable>

			</div>
	

		</h:form>

	</f:view>
		<rich:modalPanel id="popup" minWidth="600" autosized="true">
		<f:facet name="header">
			<h:outputText value="AddRegister" />
		</f:facet>
		<h:form>
			<rich:messages styleClass="generalExciseStyle" id="messages1"
				errorStyle="color:#CC0000" layout="table" infoStyle="color:#00B300"
				style="FONT-WEIGHT: bold; font-size: 15px;" />
			<table width="90%" align="center">
				<tr>
					
					<td><h:outputText value="Register Name"
							style="FONT-SIZE: small; FONT-WEIGHT: bold;" /></td>
					<td><h:inputText
							value="#{bondRegisterAction.regDescription}"
							id="bb" styleClass="generalHeaderOutputTable"></h:inputText></td>
				</tr>
				<tr>
					 
					<td><h:outputText value="Opening Value"
							style="FONT-SIZE: small; FONT-WEIGHT: bold;" /></td>
					<td><h:inputText
							value="#{bondRegisterAction.openValue}"
							 styleClass="generalHeaderOutputTable">
							 </h:inputText></td>
						
			
				</tr>
			</table>
			<div>
			<rich:spacer height="20px"></rich:spacer>
			</div>
			<table align="right" width="70%">
		
				<tr>
					<td>
						<h:commandButton value="Save" styleClass="btn-sm btn-success"
							
							action="#{bondRegisterAction.saveStorage}" />
<rich:spacer width="5px"></rich:spacer>
								<h:commandButton value="Reset" 	styleClass="btn-sm btn-danger"
							    action="#{bondRegisterAction.reset1}" />
					<rich:spacer width="5px"></rich:spacer>
						<a4j:commandButton value="Close" styleClass="btn-sm btn-danger"
							oncomplete="#{rich:component('popup')}.hide()" /></td>
				</tr>
			</table>
		</h:form>
	</rich:modalPanel>
</ui:composition>