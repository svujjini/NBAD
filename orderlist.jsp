<%-- 
    Document   : orderlist
    Created on : Nov 27, 2014, 5:13:43 PM
    Author     : kbs
--%>
<%@page import="java.util.List"%>
<%@page import="model.OrderBean"%>

        <jsp:directive.include file="WEB-INF/jspf/user-navigation.jspf" />
        
        <jsp:directive.include file="WEB-INF/jspf/site-navigation.jspf" />
        
                <section id="content">
                    <table>
                        <tr>
                            <td>Order Number</td>
                            <td>Customer</td>
                            <td>Order Date</td>
                            <td>Total</td>
                        </tr>
                        <% List<OrderBean> ordersList = (List<OrderBean>)session.getAttribute("ordersList");
                        for(int i = 0 ;i<ordersList.size();i++)
                        {
                        
                        OrderBean orderBean = ordersList.get(i);
                        
                        %>
                        <tr>
                            <td>
                                <%=orderBean.getOrderNumber() %>
                            </td>
                            <td>
                                <%=orderBean.getUser()%>
                            </td>
                            <td>
                                <%=orderBean.getDate()%>
                            </td>
                            <td>
                                <%=orderBean.getTotal()%>
                            </td>
                        </tr>
                        <% } %>
                        </table>
                        <hr>
                </section>
            </section>
        </div>
