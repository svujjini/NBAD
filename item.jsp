<%@page import="java.util.ArrayList"%>
<%@page import="model.ProductBean"%>
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
				<li><a href="item">Item</a></li>
			</ul>
</div>
        <jsp:directive.include file="WEB-INF/jspf/user-navigation.jspf" />
        
        <jsp:directive.include file="WEB-INF/jspf/site-navigation.jspf" />
        
                <section id="content">
                    <article>
                        <%
                        ArrayList<ProductBean> pList = (ArrayList<ProductBean>) session.getAttribute("productList"); 
                        String proCode = request.getParameter("productCode");
                        ProductBean pr = new ProductBean();
                        for(int i=0;i<pList.size();i++)
                        {
                            if(pList.get(i).getProductID() == Integer.parseInt(proCode)){
                                pr = pList.get(i);
                                break;
                            }
                        }
                        %>
                        <img src=" <%= pr.getImgURL()  %>" alt="item" width='100px' height='100px'>
                        <h1><%= pr.getProductName() %></h1>
                        <h2><%= pr.getCategory() %></h2>
                        <h2><%= pr.getPrice() %></h2>
                        <div class='item_description'> <% pr.getDescription(); %></div>       
                        <form name="addProduct" method="post">
                        <input value="<%=pr.getProductID()%>" type="hidden" name="productToAdd"/>
                        <input type="button" value="Add To Cart" onclick=" document.addProduct.submit()" />
                        <input type="button" value="Back" onClick="history.go(-1);">
                        </form>
                        
                        
                    </article>
                </section> 
            </section>
        </div>
