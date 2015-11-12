                /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CartBean;
import model.OrderItemBean;
import model.ProductBean;
import pdata.ProductDB;

/**
 *
 * @author kbs
 */
@WebServlet(name = "ControllerServlet", loadOnStartup = 1, urlPatterns = {"/ControllerServlet", "/aboutus", "/contactus", "/catalog", "/item"})
public class CatalogController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
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
         /* Servlet Code */
            String userPath = request.getServletPath();
            ProductDB productDB = new ProductDB();
            productDB.LoadData();
            request.getSession().setAttribute("productList", productDB.getAllProducts());
            request.getSession().setAttribute("catList", productDB.getCategories());
            //if (userPath.equals("/catalog")){
                
           // }
           // if (userPath.equals("/item")){
           //     
           // }
            
            //Request Dispatcher to forward request
            String url = userPath +".jsp";
            //out.println(url);
            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception ex) {
            ex.printStackTrace();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderItemBean orderItem = new OrderItemBean();
        int productID = Integer.parseInt(request.getParameter("productToAdd"));
        ArrayList<ProductBean> productList = (ArrayList<ProductBean>)request.getSession().getAttribute("productList");
        for(ProductBean product : productList){
            if(product.getProductID() == productID){
                orderItem.setProduct(product);
                orderItem.setQuantity(1);
                break;
            }
        }
        CartBean shoppingCart;
        if(request.getSession().getAttribute("theShoppingCart")!=null){
            shoppingCart = (CartBean) request.getSession().getAttribute("theShoppingCart");
        } else {
            shoppingCart = new CartBean();
        }
        shoppingCart.addItem(orderItem.getProduct(), orderItem.getQuantity());
        request.getSession().setAttribute("theShoppingCart", shoppingCart);
        response.sendRedirect("cart.jsp");
                
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
