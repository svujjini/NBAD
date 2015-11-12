<%@page import="model.CategoryBean"%>
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
			</ul>
        </div>    
        <jsp:directive.include file="WEB-INF/jspf/user-navigation.jspf" />
        
        <jsp:directive.include file="WEB-INF/jspf/site-navigation.jspf" />
        
                <section id="content">
                    <%
                        ArrayList<ProductBean> pList = (ArrayList<ProductBean>) session.getAttribute("productList");
                        ArrayList<CategoryBean> cList = (ArrayList<CategoryBean>) session.getAttribute("catList");
                        for (int i=0;i<cList.size();i++)
                        {
                            String catName = cList.get(i).getCatName();
                            int catID = cList.get(i).getCatID();
                            %> <div id="item_category"><%=catID%> <%=catName%></div>
                            <ul>
                                <%
                                for(int j=0;j<pList.size();j++)
                                {
                                    String pCategory = pList.get(j).getCategory();
                                    if(catName == pCategory){
                                        String productName = pList.get(j).getProductName();
                                        int productCode = pList.get(j).getProductID();
                                        %><li><a href="item?productCode=<%=productCode%>"> <%=productCode%>  , <%=productName%></a></li><%    
                                    }
                                    
                                }    
                                %>
                            </ul>
                        <% } %>
                    
          <!--          <div class="item_category">paintings</div>
                    <div class="item_list">
                        <ul>
                            <li><a href="item.html">Sree Ganesh</a></li>
                            <li><a href="item.html">Women in Blue</a></li>
                            <li><a href="item.html">Swinging Swan</a></li>
                        </ul>
                    </div>
                    <div class="item_category">Jewellery</div>
                    <div class="item_list">
                        <ul>
                            <li><a href="item.html">The Black Pearl</a></li>
                            <li><a href="item.html">Joker</a></li>
                            <li><a href="item.html">Dark Knight</a></li>
                        </ul>  -->
                    </div>
                </section>
            </section>
        </div>
