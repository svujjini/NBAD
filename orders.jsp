<%-- 
    Document   : orders
    Created on : Sep 24, 2014, 12:28:27 AM
    Author     : kbs
--%>

<%@page import="model.ProductBean"%>
<%@page import="model.OrderBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="breadCrumb">
			<ul class="breadCrumb">
				<li><a href="index">Home</a></li>
				<li class="seperator">></li>
				<li><a href="catalog">Catalog</a></li>
				<li class="seperator">></li>
				<li><a href="orders">Order</a></li>
			</ul>
</div>
        <jsp:directive.include file="WEB-INF/jspf/user-navigation.jspf" />
        
        <jsp:directive.include file="WEB-INF/jspf/site-navigation.jspf" />
        
                <section id="content">
                    <%
			OrderBean currentOrder = (OrderBean) request.getSession().getAttribute("currentOrder");
		    %>
                    <h3>INVOICE :: OrderNumber : <%=currentOrder.getOrderNumber() %></h3>
                    <!--<p>Date:Sep.16,2014</p>
                    <p><b><u>Ship to/Bill to</u><br></p>
                    <p>University City Blvd<br>
                        Charlotte<br>
                        North Carolina</p>-->
                    <p>
			Ship To/Bill To: <br />
			<%=currentOrder.getUser().getFirstName()%>
			<%=currentOrder.getUser().getLastName()%><br />
			<%=currentOrder.getUser().getAddress1()%><br />
			<%=currentOrder.getUser().getAddress2()%><br />
			<%=currentOrder.getUser().getCity()%>,
			<%=currentOrder.getUser().getState()%>
			<br />
			<%=currentOrder.getUser().getPostCode()%>,
			<%=currentOrder.getUser().getCountry()%>
		    </p>
                    <table>
                        <tr>
                            <td> Item </td>
                            <td>Price</td>
                            <td>Quantity</td>
                            <td>Total</td>
                        </tr>
                       <!-- <hr>
                        <tr>
                            <td> Girl in Blue Roses </td>
                            <td>$150.00</td>
                            <td>1</td>
                            <td>$150</td>
                        </tr>
                        <tr>
                            <td> Dark Knight </td>
                            <td>$15.00</td>
                            <td>3</td>
                            <td>$15</td>
                        </tr>-->
                       <%
				double subTotalPrice = 0.0;
				for (int i = 0; i < currentOrder.getOrderItems().size(); i++) {
					ProductBean cartItem = currentOrder.getOrderItems().get(i)
							.getProduct();
			%>
                       <tr>
				<td><a href="item?productCode=<%=cartItem.getProductID()%>"><%=cartItem.getProductName()%></a></td>
				<td>$ <%=cartItem.getPrice()%></td>
				<td><input type="text" value="<%=currentOrder.getOrderItems().get(i).getQuantity()%>" disabled="disabled" /></td>
				<td>$ <%=(currentOrder.getOrderItems().get(i).getTotal())%></td>
			</tr>
                        <%
				subTotalPrice += currentOrder.getOrderItems().get(i).getTotal();
				}
			%>
                    </table>
                    
                    <table><td></td><td></td><td>Sub Total:</td><td>$ <%=subTotalPrice%></td></table> 
                    <table><td></td><td></td><td>Tax:</td><td>$ <%=currentOrder.getTaxRate()%></td></table> 
                    <table><td></td><td></td><td>Total:</td><td>$ <%=currentOrder.getTotal()%></td></table> 
                    
                    <input class="button" type="button" value="Purchase" onclick="location.href='payment.jsp'" />
                    <input type="button" class="button" value="Back To Cart" onclick="location.href='cart.jsp'" />
                    
                </section>