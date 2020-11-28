<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>
		<head>
<style type="text/css">
.reseet {
	border-radius: 3px;
	border-style: none;
	height: 28px;
	width: 75px;
	background-color: #8a8a5c;
	color: white;
	font-weight: bold;
}

.savee {
	border: none;
	border-radius: 3px;
	border-style: none;
	height: 28px;
	width: 75px;
	padding: 8px, 16px;
	background-color: #2d8659;
	color: white;
	font-weight: bold;
	border-radius: 3px;
	background-color: #2d8659;
}

.savee:hover {
	text-decoration: blink;
	background-color: #7ec473;
}

.reseet:hover {
	text-decoration: blink;
	background-color: #837c83;
}

.textt {
	border-radius: 3px;
	background-color: #E8E8E8;
	border: 1px dotted #669999;
	height: 25px;
	box-shadow: 1px 1px 15px lightsteelblue;
	padding: 5px 5px;
	height: 25px;
	width: 10%;
}

.combo {
	border-radius: 3px;
	background-color: #E8E8E8;
	padding-top: 2px;
	padding-bottom: 2px;
	height: 25px;
	width: 75%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px dotted #669999;
	height: 25px;
}

.inputtext {
	border-radius: 3px;
	background-color: #E8E8E8;
	padding: 5px 5px;
	height: 25px;
	width: 75%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px dotted #669999;
}

.textarea1 {
	border-radius: 3px;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px dotted #669999;
	background-color: #E8E8E8;
	width: 100%;
	border: 1px dotted #669999;
}


</style>
		</head>
		<h:form>
			<div class="container">
			   <div class="row">
					<div>
          
                          <a4j:outputPanel id="msg">
			                   	<div class="col-md-12 wow shake" align="center">
				                  	<h3>
				                	    <h:messages errorStyle="color:red"
							     		layout="table" id="messages"
							     		infoStyle="color:green">
						</h:messages>
					</h3>
				</div>
               </a4j:outputPanel>
              </div>
              
              <div class="row" align="center">
					<div class="col-md-12">
						<h:outputLabel value="Enter Only Those Vat Which Are Signed  Under FL3/3A Excise Department"
							style="text-align:center;font-family:Calibri;font-weight:bold;font-size:35px;COLOR: #0000a0;" />
                        <h:inputHidden
									value="#{fl3a_3a_licence_action.hidden}"></h:inputHidden>
					
					</div>
				</div>
                 <div>
                      <rich:spacer height="30px"></rich:spacer>
                 </div>
                 
                 <div>
                      <rich:separator lineType="dashed"></rich:separator>
                 </div>
                 
                 <div>
                     <rich:spacer height="30px"></rich:spacer>
                 
                 </div>
                 
                 <div class="row col-md-12" align="center"
					style="BACKGROUND-COLOR: #dee0e2;">
					<div class="col-md-12" align="center">
						<h:selectOneRadio value="#{fl3a_3a_licence_action.radio }"
							valueChangeListener="#{fl3a_3a_licence_action.radiomethods}"
							onchange="this.form.submit();">

							
							<f:selectItem itemValue="BT" itemLabel="Bottling Vat" />
							<f:selectItem itemValue="BL" itemLabel="Bleeding Vat" />
							<f:selectItem itemValue="UT" itemLabel="Uni Tank" />
						</h:selectOneRadio>
					</div>

				</div>
                 
                 
                 
                 
                 <div class="row">
					<div class="col-md-12">
					<rich:dataTable columnClasses="columnClass1" headerClass="TableHead"
				        footerClass="TableHead" rowClasses="TableRow1,TableRow2"
				        styleClass="DataTable" id="table3" rows="10" width="100%"
				         value="#{fl3a_3a_licence_action.displaylist}" var="list">
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
								<h:outputText value="Vat Name"
									styleClass="generalHeaderOutputTable" />
							</f:facet>
							<h:inputText value="#{list.vat_name}" styleClass="form-control" 
								style="COLOR: #000040; FONT-SIZE: medium;" />
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Capacity"
									styleClass="generalHeaderOutputTable" />
							</f:facet>
							<h:inputText value="#{list.cap}" 
								style="COLOR: #000040; FONT-SIZE: medium;"
								styleClass="form-control" />
						</rich:column>
				        
				       
						<rich:column>
							<f:facet name="header">
								<h:commandButton class="img"
									action="#{fl3a_3a_licence_action.addRowMethod}" value=" ADD"
									image="/img/add.png" style=" width : 43px;"/>
							</f:facet>
							<h:commandButton class="img"
								actionListener="#{fl3a_3a_licence_action.deleteRowMethod}"
								style="background: transparent;height:30px ; width : 36px;"
								image="/img/del.png" />
						</rich:column>
				       
				        
				   </rich:dataTable>
				   <div>
				   <f:facet name="footer"></f:facet>
				      <rich:datascroller for="table3"></rich:datascroller>
				   </div>
				  </div>
				 </div>
				 <rich:spacer height="20px"></rich:spacer>
				 <div class="row " align="center">
			<div class="col-md-12" align="center">
					<h:commandButton styleClass="btn btn-primary" value="Save/Update "
						action="#{fl3a_3a_licence_action.save}" />
				</div>
			
			</div>
				 
				      </div>
              </div>
            
            
            
            </h:form>
            
          </f:view>
        </ui:composition>
                
  