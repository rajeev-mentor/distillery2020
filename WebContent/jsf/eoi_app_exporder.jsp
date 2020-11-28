
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich"
	xmlns:h="http://java.sun.com/jsf/html">

	<h:form>
		<f:view>
			<head>
			
 <link  href ="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.0.0/animate.min.css"  rel="stylesheet" type="text/css"> </link>
			
<style>
.heading1{
 background-color: #ccccff;
 padding-top: 20px;

}
.TableHead{
 background-color: #ccccff;
}
#table55{
background-color: #ccccff;
}
#table22{
background-color: #ccccff;
}
#table23{
background-color: #ccccff;
}


.inputtext {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
}

.dropdown-menu {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 30%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
	color: maroon;
}

.dropdown-menu1 {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 75%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
	COLOR: maroon;
	BACKGROUND-COLOR: #0080c0;
}

.textarea1 {
	border-radius: 3px;
	border-style: none;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
}

.blinking {
	animation: mymove 2s infinite alternate;
}

@
-webkit-keyframes mymove {
	from {opacity: 0;
}

to {
	opacity: 1;
}
}
</style>
			</head>

			<div class="panel panel-default">

				<div class="panel-body">
	<div align="center" style="background-color: #253f8a;">				
			  
					<h:outputText
						value="APPLICATION FOR EXPORT ORDER BY DISTILLERY "
						style="FONT-SIZE: xx-large; FONT-FAMILY: 'Agency FB'; COLOR: #ffffff; TEXT-DECORATION: underline;" >
					</h:outputText>
			 
		</div>		
			<h:inputHidden value="#{eoi_app_exporder_action.hidden}"></h:inputHidden>

					
					
					<div class="row" align="right">
						 
					</div>
					<div class="row">
						<div class="col-md-12 wow shake" align="center">
							<h:messages errorStyle="color:red" layout="TABLE" id="messages"
								infoStyle="color:green"
								style="FONT-FAMILY: 'Yu Gothic UI Semibold'; background-color: #eee6ff; font-size:20px; font-weight: bold ;decoration:none;">
							</h:messages>

						</div>
					</div>
					<div class="blinking" align="left">
								<h1>


									<h:outputText value="All "
										style="FONT-FAMILY: 'Futura-Medium'; FONT-SIZE: medium;  color:CornflowerBlue;margin-left:30px;font-weight: bold;" />
										<h:outputLabel value="*"
										style="color:red;font-size:medium">
										<h:outputText value=" Fields are Neccessary :"
										style="FONT-FAMILY: 'Microsoft JhengHei UI Light'; FONT-SIZE: medium; color:CornflowerBlue;font-weight: bold;" />
										</h:outputLabel>
										
								</h1>
							</div>
					<div class="row">		
							<div class="col-md-5" align="right">
								<h:outputText value=" Import Order No. : "
									style="FONT-SIZE: 15px; FONT-WEIGHT: bold;FONT-FAMILY: 'Candara Light';"></h:outputText>
								<h:outputLabel value="*" style="color: red;" />
							</div>

							<div class="col-md-4" align="left">
									<h:selectOneMenu onchange="this.form.submit();"
										styleClass="form-control" style="color: #0055ff;width:300px"
										value="#{eoi_app_exporder_action.import_order_no}"
										valueChangeListener="#{eoi_app_exporder_action.change_import_order_list}">
										<f:selectItems
											value="#{eoi_app_exporder_action.import_order_no_list}"></f:selectItems>
									</h:selectOneMenu>
								</div>
								</div>
								
				<div class="row">
					<rich:spacer height="30px" />
					</div>
								
				<div class="col-md-12">
				<div class="col-md-3" align="right">
									<h:outputText value="Name Of Importing Unit :"
										style="FONT-FAMILY: 'Candara Light'; FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
										
								</div>
								
								<div class="col-md-3" align="left">
								        <h:inputTextarea styleClass="form-control" style="COLOR: #0055ff; height:30px; margin-right: 100px;" readonly = "true"
								         		value="#{eoi_app_exporder_action.name_of_importing_unit}">
										
									</h:inputTextarea>
								</div>			
							
							
							<div class="col-md-3" align="right">
								<h:outputText value="Import Order Date : "
									style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
								
							</div>
							<div class="col-md-3" align="left" style = "width:300px;color:#0055ff">
             <rich:calendar disabled = "true" value = "#{eoi_app_exporder_action.import_order_date}">
						
							</rich:calendar>
							</div>
						</div>	
					<div class= "row">				
						<rich:spacer height="20px"/>
					</div>	
						
						<div class="col-md-12">
							 
							<div class="col-md-3" align="right">
							<h:outputText value=" Validity Demanded : "
								style="FONT-SIZE: 15px; FONT-WEIGHT: bold;FONT-FAMILY: 'Candara Light';"></h:outputText>
							<h:outputLabel value="*" style="color: red;" />
						</div>

						<div class="col-md-3" align="left" style = "width:300px;color:#0055ff;">
								<rich:calendar value = "#{eoi_app_exporder_action.validity_demanded}">
							</rich:calendar>
						</div>
							
						</div>	
						
						<div class ="row">					
								<rich:spacer height="20px" />
								</div>
								
						<div class="row">
						<div class="col-md-3" align="right">
								<h:outputText value="Performa Invoice No. : "
									style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
								<h:outputLabel value="*" style="color: red;" />
							</div>
							<div class="col-md-3" align="left">
                            <h:inputText value = "#{eoi_app_exporder_action.performa_invoice_no}"
							style="color:#0055ff;;" styleClass="form-control"/>								
							</div>
							
					
						<div class="col-md-3" align="right">
							<h:outputText value="Upload Performa invoice Certificate : "
								style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
							<h:outputLabel value="*" style="color: red;" />
						</div>
						<div class="col-md-3" align="left">
							<a4j:outputPanel rendered="true">
									<rich:fileUpload listHeight="30px" listWidth="225px"
										fileUploadListener="#{eoi_app_exporder_action.doc3uploadMethod}"
										maxFilesQuantity="1" clearControlLabel="Clear"
										clearAllControlLabel="Clear All"
										ontyperejected="if (!confirm('Only pdf files are accepted')) return false"
										sizeErrorLabel="" acceptedTypes="pdf"
										addControlLabel="Add PDF">								
											<a4j:support event="onuploadcomplete"
												reRender="renpdffalse3 , renpdftrue3"></a4j:support>
									</rich:fileUpload>
								</a4j:outputPanel> <h:commandButton
									action="#{eoi_app_exporder_action.pdf3}"
									disabled="#{eoi_app_exporder_action.pdfUploaderFlag == true}"
									styleClass="btn btn-info btn-sm" value="confirm PDF" /> <rich:spacer
									width="10px;"></rich:spacer> <a4j:outputPanel id="renpdftrue3">
									<h:outputLink
										rendered="#{eoi_app_exporder_action.doc3upload}"
										target="_blank"
										value="/doc/ExciseUp/ExportOutsideIndia/SEH/#{eoi_app_exporder_action.upload_performa_invoice}">
										<h:graphicImage value="/img/download.gif" alt="view document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</h:outputLink>
								</a4j:outputPanel> <a4j:outputPanel id="renpdffalse3">
									<a4j:outputPanel
										rendered="#{!eoi_app_exporder_action.doc3upload}">
										<h:graphicImage value="/img/nodoc.png" alt="no document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</a4j:outputPanel>
								</a4j:outputPanel>
						</div>
					</div>
						
			
					<div class="row">
					<rich:spacer height="10px" />
					</div>
					<div class="row">
						<div class="col-md-3" align="right">
								<h:outputText value="PUC NO. : "
									style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
								<h:outputLabel value="*" style="color: red;" />
							</div>
							<div class="col-md-3" align="left">
                            <h:inputText value = "#{eoi_app_exporder_action.puc_no}"
							style="color:#0055ff;;" styleClass="form-control"/>								
							</div>
							
					
						<div class="col-md-3" align="right">
							<h:outputText value="Upload PUC Certificate : "
								style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
							<h:outputLabel value="*" style="color: red;" />
						</div>
						<div class="col-md-3" align="left">
							<a4j:outputPanel rendered="true">
									<rich:fileUpload listHeight="30px" listWidth="225px"
										fileUploadListener="#{eoi_app_exporder_action.doc4uploadMethod}"
										maxFilesQuantity="1" clearControlLabel="Clear"
										clearAllControlLabel="Clear All"
										ontyperejected="if (!confirm('Only pdf files are accepted')) return false"
										sizeErrorLabel="" acceptedTypes="pdf"
										addControlLabel="Add PDF">								
											<a4j:support event="onuploadcomplete"
												reRender="renpdffalse4 , renpdftrue4"></a4j:support>
									</rich:fileUpload>
								</a4j:outputPanel> <h:commandButton
									action="#{eoi_app_exporder_action.pdf4}"
									disabled="#{eoi_app_exporder_action.pdfUploaderFlag4 == true}"
									styleClass="btn btn-info btn-sm" value="confirm PDF" /> <rich:spacer
									width="10px;"></rich:spacer> <a4j:outputPanel id="renpdftrue4">
									<h:outputLink
										rendered="#{eoi_app_exporder_action.doc4upload}"
										target="_blank"
										value="/doc/ExciseUp/ExportOutsideIndia/SEH/#{eoi_app_exporder_action.upload_puc_certificate}">
										<h:graphicImage value="/img/download.gif" alt="view document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</h:outputLink>
								</a4j:outputPanel> <a4j:outputPanel id="renpdffalse4">
									<a4j:outputPanel
										rendered="#{!eoi_app_exporder_action.doc4upload}">
										<h:graphicImage value="/img/nodoc.png" alt="no document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</a4j:outputPanel>
								</a4j:outputPanel>
						</div>
					</div>
					
					<div class="row">
					<rich:spacer height="10px" />
					</div>
			<h:panelGroup rendered = "#{eoi_app_exporder_action.flag_brand_table}" >	
			<div class="animate__animated animate__bounceInUp animate__slower">		
					<div class= "row" align="center">
				<h:outputText
							value=" Brand Details  "
						style="FONT-FAMILY: 'PMingLiU-ExtB'; COLOR: #5c98da; FONT-WEIGHT: bold; FONT-SIZE: 20px;">
			   </h:outputText>
				</div>	
				<div class="row">
					<rich:spacer height="10px" />
					</div>
					<div align="center" class="animate__animated animate__bounceInUp ">
					<rich:dataTable id="table55"   var="list33"
						value="#{eoi_app_exporder_action.brand_detaiil_list}" styleClass="table table-hover"
						width="100%" headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2">

						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Sr.No."
									style="FONT-SIZE: xx-small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.srno_brand}">
							
							</h:outputText>
						</rich:column>
						<rich:column align="left">
							<f:facet name="header">
								<h:outputText value="Brand Name "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.brand_name}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Package "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.package_name}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" ETIN "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.etin}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Balance Qty. "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.balance_qty}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="No. Of Bottles(per case) "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.bottles_in_box}">
							 <a4j:support event = "onblur" reRender="box1">  </a4j:support>
							 <f:convertNumber maxFractionDigits="0"
									pattern="#############0" />
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" No. Of Box "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.no_of_box}">
							
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Requested Qty(In Bottles)"
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:inputText value="#{list33.requested_qty}" style= "color: #0044cc">
							<a4j:support event = "onblur" reRender="box1">  </a4j:support>
							<f:convertNumber maxFractionDigits="0"
									pattern="#############0" />
							</h:inputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Requested Box "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText id = "box1" value="#{list33.requested_box}">
							</h:outputText>
						</rich:column>
						
						
					   

					</rich:dataTable>

				</div>	
				</div>
				</h:panelGroup>	
				
				<div class= "row">				
						<rich:spacer height="30px"/>
					</div>
					
		<h:panelGroup rendered = "#{eoi_app_exporder_action.flag_brand_table}" >	
				<div class="animate__animated animate__bounceInUp animate__slower">
				<div class= "row" align="center">
				<h:outputText
							value="Export Order Issued In Final Year Against Selected Import Order "
						style="COLOR: #5c98da;  FONT-FAMILY: 'PMingLiU-ExtB'; FONT-WEIGHT: bold; FONT-SIZE: 20px;">
			   </h:outputText>
				</div>	
				<div class= "row">				
						<rich:spacer height="10px"/>
					</div>
				
				<div align="center">
					<rich:dataTable id="table22" rows="15" var="list22"
						value="#{eoi_app_exporder_action.export_order_list}" styleClass="table table-hover"
						width="100%" headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2">

						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Sr.No."
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.srno_export}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="App No. "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.application_no}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" App Date "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.application_date}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" No. of Bottles "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.no_of_bottles}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" LEO No. "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.ceo_no}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="BRC No. "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.brc_no}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Reciept at ICD "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.reciept_at_icd}">
							</h:outputText>
						</rich:column>
						<f:facet name="footer">
							<rich:datascroller for="table22"></rich:datascroller>
						</f:facet> 

					</rich:dataTable>

				</div>	
				</div>
				</h:panelGroup>	
				
				
					<div align="center">
							<rich:spacer height="20px" />
							<h:commandButton
							
							style = "border-radius:5px;"
								action="#{eoi_app_exporder_action.save}"
								onclick="return confirm('ALERT : You are submitting the application. Please Confirm Your Details !!');" 							
								value="Save" styleClass="btn btn-success" />
							<rich:spacer width="15px;"></rich:spacer>
							<h:commandButton
						
								action="#{eoi_app_exporder_action.reset}"
									style = "border-radius:5px;"
								value="Reset" styleClass="btn btn-info  active" />
							<rich:spacer width="15px;"></rich:spacer>
							

						</div>	
							
		<rich:spacer height="10px;"></rich:spacer>
		
		<div class= "row" align="center">
				<h:outputText
							value="Applications For Export Order"
						style="COLOR: #5c98da;  FONT-FAMILY: 'PMingLiU-ExtB'; FONT-WEIGHT: bold; FONT-SIZE: 20px;">
			   </h:outputText>
				</div>
				
		<rich:spacer height="10px;"></rich:spacer>
		
		<div align="center">
					<rich:dataTable id="table23" rows="15" var="list23"
						value="#{eoi_app_exporder_action.detail_list}" styleClass="table table-hover"
						width="100%" headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2">

						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Sr.No."
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list23.srno}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="App No. "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list23.application}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" App Date "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list23.application_dt}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" No. of Bottles "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list23.no_of_bottl}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Import Order Number "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list23.import_no}">
							</h:outputText>
						</rich:column>
						
						 <rich:column>
							<f:facet name="header">
								
							</f:facet>
							<h:commandButton
							style = "border-radius:5px;"
							actionListener="#{eoi_app_exporder_action.delete}"
							disabled="#{list23.flg==false}"
							value="Delete" styleClass="btn btn-danger" />
						</rich:column>
						
						<rich:column>
							<f:facet name="header">
								
							</f:facet>
							<h:commandButton
							style = "border-radius:5px;"
							actionListener="#{eoi_app_exporder_action.finalize}"
							disabled="#{list23.flg ==false}"
							value="Finalize" styleClass="btn btn-success" />
						</rich:column>
						
						
					   <f:facet name="footer">
							<rich:datascroller for="table23"></rich:datascroller>
						</f:facet> 

					</rich:dataTable>

				</div>					
							
							
							
		</div>												
		</div>					
	</f:view>
	</h:form>
</ui:composition>							