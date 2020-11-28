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
					<h:outputText value="Bond Release"
						style="FONT-SIZE: xx-large; FONT-FAMILY: 'Agency FB'; COLOR: #ffffff; TEXT-DECORATION: underline;"></h:outputText>


				</div>
			</div>

			<hr style="border-top: 7px #D0D3D4; border-top-style: dashed;"></hr>


			<div class="row" align="center">
				<div class="col-md-3"></div>
				<div class="col-md-3">
					<h:outputText value="Select FLB11 Gatepass"></h:outputText>
				</div>
				<div class="col-md-3">
					<h:selectOneMenu value="#{bondReleaseAction.vch_gatepass_no}" styleClass="form-control" onchange="this.form.submit();" valueChangeListener="#{bondReleaseAction.valueChangeEvent }">
						<f:selectItems value="#{bondReleaseAction.gatepass_list}" />
					</h:selectOneMenu>
				</div>
				<div class="col-md-3"></div>
			</div>
			
			
			<rich:spacer height="8px;"></rich:spacer>
			
			<div class="row" align="center">
			<div class="col-md-2"></div>
				<div class="col-md-2">
				<h:outputText value="Gatepass Date"></h:outputText>
				
				</div>
                   <div class="col-md-2">
				<h:outputText value="#{bondReleaseAction.gatepass_date}">
				<f:convertDateTime  pattern="dd-MM-yyyy" timeZone="GMT+05:30" />
				
				</h:outputText>
				
				</div>

				<div class="col-md-2">
				<h:outputText value="Bond Value"></h:outputText>
				
				</div>
                   <div class="col-md-2">
				<h:outputText value="#{bondReleaseAction.bondValue}"></h:outputText>
				
				</div>

				<div class="col-md-2"></div>
			</div>
				<rich:spacer height="3px;"></rich:spacer>
			
			
			<div class="row" align="center">
			<div class="col-md-2"></div>
			<div class="col-md-2">
			<h:outputText value="Bill Of Lading Date"></h:outputText>
			</div>
			<div class="col-md-2">
			<h:outputText value="#{bondReleaseAction.lading_dt}">
			<f:convertDateTime  pattern="dd-MM-yyyy" timeZone="GMT+05:30"/>
			</h:outputText>
			</div>
			<div class="col-md-2"></div>
			<div class="col-md-2">
					<h:commandButton value="Print Gatepass"
						styleClass="btn btn-primary" action="#{bondReleaseAction.print}"></h:commandButton>
						
						
						<h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/WholeSale/pdf//#{bondReleaseAction.pdfName}"
												target="_blank">
												<h:outputText styleClass="outputText" id="text223"
													value="View FLB11" rendered="#{bondReleaseAction.pdf_flag}"
													style="color: blue; font-family: serif; font-size: 12pt" />
													</h:outputLink>
				</div>
			<div class="col-md-2"></div>
			</div>
			
			
			
<rich:spacer height="8px;"></rich:spacer>
		
			<div class="row" align="center">
				<h:selectOneRadio value="#{bondReleaseAction.with_in_time}" style=" pointer-events:none;opacity:8;">
					<f:selectItem itemLabel="WithInTime" itemValue="W" />
					<f:selectItem itemLabel="Time Expired" itemValue="T" />
				</h:selectOneRadio>
			</div>


<rich:spacer height="10px;"></rich:spacer>
			
	<h:panelGroup rendered="#{bondReleaseAction.with_in_time eq 'T' }">		
			
			
<div class="row" align="center">
<div class="col-md-3"></div>
<div class="col-md-3">
<h:outputText value="Adjust Duty From"></h:outputText>
</div>

<div class="col-md-3">
<h:selectOneMenu value="#{bondReleaseAction.head}" styleClass="form-control">
<f:selectItems value="#{bondReleaseAction.duty_head}"/>

</h:selectOneMenu>
</div>
<div class="col-md-3"></div>
</div>
</h:panelGroup>
<rich:spacer height="5px;"></rich:spacer>
			
<div class="row" align="center">
<div class="col-md-4">
</div>

<div class="col-md-4">
<h:commandButton value="Release Bond" action="#{bondReleaseAction.save}" styleClass="btn btn-primary"></h:commandButton>
<h:commandButton value="Reset" action="#{bondReleaseAction.reset1}" styleClass="btn btn-primary"></h:commandButton>
</div>

<div class="col-md-4">
</div>

</div>


		</h:form>

	</f:view>
</ui:composition>