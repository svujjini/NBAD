<%@page import="model.ProductBean"%>
<%@page import="model.CartBean"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
        <div class="breadCrumb">
			<ul class="breadCrumb">
				<li><a href="index">Home</a></li>
				<li class="seperator">></li>
				<li><a href="catalog">Catalog</a></li>
				<li class="seperator">></li>
				<li><a href="cart">Cart</a></li>
			</ul>
		</div>
        <jsp:directive.include file="WEB-INF/jspf/user-navigation.jspf" />
        
        <jsp:directive.include file="WEB-INF/jspf/site-navigation.jspf" />
        
        <%
            if(request.getSession().getAttribute("theShoppingCart") != null){
                CartBean shoppingCart = (CartBean) request.getSession().getAttribute("theShoppingCart");
                if(shoppingCart.getOrderItemsList() != null && shoppingCart.getOrderItemsList().size() > 0){
        %>
                <section id="content">
                    
                    <form method="post" name="cartForm" action="orders">
                    <h1>Your Cart</h1>
                    <table>
                        <tr>
                            <th> Item </th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                        <%
                            double subTotalPrice = 0.0;
                                for(int i=0;i<shoppingCart.getOrderItemsList().size();i++){
                                    ProductBean cartItem = shoppingCart.getOrderItemsList().get(i).getProduct();
                                
                        %>
                        <tr>
                        <input type="hidden" name="productID" value="<%=cartItem.getProductID()%>" />
                        <td><a href="cart?removeProduct=<%=cartItem.getProductID() %> style="border: 1px dashed; color: red;">X</a></td>
                        <td><a href="item?productID=<%=cartItem.getProductID()%>"><%=cartItem.getProductName()%></a></td>
                        <td>$ <%=cartItem.getPrice()%></td>
			<td><input name="productQuantity" type="text" value="<%=shoppingCart.getOrderItemsList().get(i).getQuantity()%>" /></td>
			<td>$ <%=(shoppingCart.getOrderItemsList().get(i).getTotal())%></td>
			</tr>
                        </table>
                        <table>
                        <%
			        subTotalPrice = subTotalPrice + (shoppingCart.getOrderItemsList().get(i).getTotal());
							}
                        %>
			<tr>
			<td></td>
			<td></td>
			<td></td>
			<td>Sub Total</td>
			<td>$ <%=subTotalPrice%></td>
			</tr>
                        </table>
                    <input type="submit" class="button" value="Checkout" onclick="document.cartForm.action='cart?action=checkout'" /> 
                    <input class="button" type="submit" value="Update Cart" onclick="document.cartForm.action='cart?action=updateCart'" />
                    </form>
                    <%
			} else {
		    %>
		    <p>No items in your cart</p>
		    <%
			}
			} else {
		    %>
		    <p>No items in your cart</p>

		    <%
			}
		    %>
                </section>
            </section>
        </div>
