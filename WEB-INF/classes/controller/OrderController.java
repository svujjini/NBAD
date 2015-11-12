/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CartBean;
import model.OrderBean;
import model.OrderItemBean;
import model.ProductBean;
import model.UserBean;
import pdata.UserDB;

/**
 *
 * @author kbs
 */
@WebServlet(name = "OrderController", loadOnStartup = 1, urlPatterns = {"/OrderController", "/orders", "/cart", "/payment", "/orderlist"})
public class OrderController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        private static final long serialVersionUID = 1L;
	private ArrayList<ProductBean> productList;
	private CartBean shoppingCart;
	private static int orderNo = 10000;
	private static double TAX_PERCENT = 0.12;
    
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
         
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter())
        {
            System.out.println("hello world! I'm do get");
            String userPath = request.getServletPath();
            productList = (ArrayList<ProductBean>) request.getSession().getAttribute("productList");
            if(userPath.equalsIgnoreCase("/cart")){
                shoppingCart = (CartBean) request.getSession().getAttribute("theShoppingCart");
                if(request.getParameter("removeProduct") != null){
                    for(ProductBean product : productList){
                        if(Integer.parseInt(request.getParameter("removeProduct")) == product.getProductID()){
                            shoppingCart.removeItem(product);
                            break;
                        }
                    }
                    request.getSession().setAttribute("theShoppingCart", shoppingCart);
                    response.sendRedirect("cart");
                } else {
                    request.getRequestDispatcher(userPath + ".jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher(userPath + ".jsp").forward(request, response);
            }
        
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
        @SuppressWarnings("empty-statement")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()){
            if(request.getParameter("action").equalsIgnoreCase("updateCart")){
               String[] productID = request.getParameterValues("productID");
               System.out.println("hello world! i'm do post");
               String[] productQuantity = request.getParameterValues("productQuantity");
               ArrayList<OrderItemBean> orderItems = new ArrayList<OrderItemBean>();
               shoppingCart = (CartBean) request.getSession().getAttribute("theShoppingCart");
               for(int i=0;i<productID.length;i++){
                   for(OrderItemBean orderItem : shoppingCart.getOrderItemsList()){
                       if(orderItem.getProduct().getProductID() == Integer.parseInt(productID[i])){
                           shoppingCart.removeItem(orderItem.getProduct());
                           if(Integer.parseInt(productQuantity[i]) > 0){
                               OrderItemBean item = new OrderItemBean();
                               item.setProduct(orderItem.getProduct());
                               item.setQuantity(Integer.parseInt(productQuantity[i]));
                               orderItems.add(item);
                           
                           }
                       }
                   }
                   
               } 
               shoppingCart.setOrderItemsList(orderItems);
               request.getSession().setAttribute("theShoppingCart", shoppingCart);
               response.sendRedirect("cart");
            } else if(request.getParameter("action").equalsIgnoreCase("checkout")) {
                UserDB userDB = new UserDB();
                //userDB.LoadData();
                UserBean user = userDB.getAllUsers().get(0);
                request.getSession().setAttribute("theUser", user);
                OrderBean order = new OrderBean();
                orderNo += 1;;
                order.setOrderNumber(orderNo);
                order.setDate(new Date());
                order.setUser(user);
                order.setOrderItems(shoppingCart.getOrderItemsList());
                double totalCost = 0.0;
                for (OrderItemBean orderItem : shoppingCart.getOrderItemsList()){
                    totalCost += orderItem.getTotal();
                }
                double taxRate = totalCost * TAX_PERCENT;
                taxRate = Math.rint(taxRate);
                totalCost += taxRate;
                order.setTaxRate(taxRate);
                order.setTotal(totalCost);
                order.setPaid(false);
                request.getSession().setAttribute("currentOrder", order);
                response.sendRedirect("orders");
            } else if(request.getParameter("action").equalsIgnoreCase("confirmOrder")) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet PaymentServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> Payment processed Successfully </h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
