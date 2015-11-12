<%-- 
    Document   : payment
    Created on : Nov 27, 2014, 4:19:33 PM
    Author     : kbs
--%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page import="model.OrderBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <jsp:directive.include file="WEB-INF/jspf/user-navigation.jspf" />
        
        <jsp:directive.include file="WEB-INF/jspf/site-navigation.jspf" />
        
                <section id="content">
                    <h1> Enter your payment information </h1>
                    <hr>
                    <form name="confirmOrder" method="post">
                    <table>
                        <tr>
                            <td>Credit Card Type : </td>
                            <td><select name="cardType">
                                    <option>VISA</option>
                                    <option>Master Card</option>
                                    <option>Mastero</option>
                                </select> </td>
                        </tr>
                        <tr>
                            <td>Card Number : </td>
                            <td><input type = "text" name="cardNumber"></td>
                        </tr>
                        <tr>
                            <td>Expiry Date : </td>
                            <td><select name="cardMonth">
                                    <option>Jan</option>
                                    <option>Feb</option>
                                    <option>Mar</option>
                                    <option>Apr</option>
                                    <option>May</option>
                                    <option>Jun</option>
                                    <option>Jul</option>
                                    <option>Aug</option>
                                    <option>Sep</option>
                                    <option>Oct</option>
                                    <option>Nov</option>
                                    <option>Dec</option>
                                </select> </td>
                            <td><select name="cardYear">
                                    <option>2014</option>
                                    <option>2015</option>
                                    <option>2016</option>
                                    <option>2017</option>
                                    <option>2018</option>
                                    <option>2019</option>
                                    <option>2020</option>
                                    <option>2021</option>
                                    <option>2022</option>
                                    <option>2023</option>
                                    <option>2024</option>
                                    <option>2025</option>
                                </select> </td>
                        </tr>
                        <tr>
                            <td>CVV:</td>
                            <td><input type="text"name="cvv"></td>
                        </tr>
                    </table>
                    <hr>
                    <% OrderBean order = (OrderBean)session.getAttribute("currentOrder");                 
                    %>
                    <p>Your card will be charged a total of $<%=order.getTotal() %></p>
                    
                        <input value="<%=order.getOrderNumber() %>" type="hidden" name="orderNumber"/>
                        <input type="button" value="Confirm Payment" onclick=" document.confirmOrder.action='payment?action=confirmOrder'" />
                        <input type="button" value="Back" onClick="history.go(-1);">
                    </form>
                </section>
            </section>
        </div>
