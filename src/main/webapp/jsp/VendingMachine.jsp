<%-- 
    Document   : VendingMachine
    Created on : Sep 29, 2018, 12:38:11 PM
    Author     : omish
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <header>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Vending Machine</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </header>
    <body>
        <h1></h1>

        <div>
            <div class="col-sm-1"></div>
            <div class='col-md-8'>
                <c:forEach items="${snacks}" var="snack">  <!--width: 100px-->
                    <a href="${pageContext.request.contextPath}/getBtnId?id=${snack.getId()}" class="button">
                        <button class="col-sm-3 btn-sq-lg btn" type="button" style='margin: 15px 10px 5px 10px' 
                                onclick="" 
                                id="${snack.getId()}">
                            <p style='text-align: justify'> ${snack.getId()} </p>
                            ${snack.getName()} <br> <br>
                            <output class='cost'> $${snack.getCost()} </output> </br></br>
                            <output class='quantity'> Quantity Left: ${snack.getStock()}</output>
                        </button>
                    </a>
                </c:forEach>
            </div>


            <!--  This is right hand side of screen -->
            <form>
                <div class='col-md-3'>
                    <div id ='totalCash' class='col-md-12' style = "text-align: center;">
                        <p> <h2 style='text-align: center'> Total $ deposited </h2> </p>
                        <div id =output align='center'>
                            <output id='currentMoney' class='output form-control' style="border-style: solid; text-align: justify;"> 
                                <c:out value="$ ${currentMoney}" /> 
                            </output>

                        </div>
                        </br>
                        <div >
                            <a href="${pageContext.request.contextPath}/addMoney?money=1.00" class="button">
                                <button type='button' id='addDollar'
                                        class='btn btn-default' onclick="">
                                    Add Dollar
                                </button>
                            </a>
                            <a href="${pageContext.request.contextPath}/addMoney?money=.25" class="button">
                                <button type='button' id='addQuarter'
                                        class='btn btn-default' onclick="">
                                    Add Quarter
                                </button>
                            </a>
                        </div>
                        </br>

                        <div>
                            <a href="${pageContext.request.contextPath}/addMoney?money=.10" class="button">
                                <button type='button' id='addDime'
                                        class='btn btn-default' onclick="">
                                    Add Dime
                                </button>
                            </a>

                            <a href="${pageContext.request.contextPath}/addMoney?money=.05" class="button">
                                <button type='button' id='addNickel'
                                        class='btn btn-default' onclick="">
                                    Add Nickel
                                </button>
                            </a>
                            <hr/>
                        </div>
                    </div>
            </form>

            <form>
                <div class='col-md-12' align='center'>
                    <h2 style="text-align: center"> Messages </h2>
                    <div id =output>
                        <output id = 'Message' style="border-style: solid; text-align: justify;" class='output form-control'> <c:out value="${msg}" /> </output>
                    </div>
                    <div id = 'item'>
                        <h3 style='display:flex'> Item:
                            <output  id ='itemNumber' style="border-style: solid; text-align: justify; display:inline" class='output form-control'> ${id}</output>
                        </h3>
                    </div>
                    <br/>
                    <a href="${pageContext.request.contextPath}/buySnack?snackId=${id}&depositedMoney=${currentMoney}" class="button">
                        <button type="button" id='Purchase' class="btn btn-default"> Make Purchase </button>
                        <hr/>
                    </a>
                </div>
            </form>

            <form>
                <div class='col-md-12' align='center'>
                    <h2> Change </h2>
                    <output id = 'Change' style="border-style: solid;" class='output form-control'> 
                        <!--  add in a string pull sent in from service.  1 whole passed in and printed  -->
                        <c:out value="${returnChange}" />  </output>
                    <br/>
                    <a href = "${pageContext.request.contextPath}/getChange?money=${currentMoney}"  
                       <button type="button"  id='returnChange' class='btn btn-default'> Change Return
                        </button>
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
