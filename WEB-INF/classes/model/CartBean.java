/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author Sneha
 */
public class CartBean implements Serializable {
  private ArrayList<OrderItemBean> orderItemsList;

     

    /**
     * @return the orderItemsList
     */
    public ArrayList<OrderItemBean> getOrderItemsList() {
        return orderItemsList;
    }

    /**
     * @param orderItemsList
     */
    public void setOrderItemsList(ArrayList<OrderItemBean> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    /**
     * @return the items
     */
    public ArrayList<OrderItemBean> getItems() {
        return this.orderItemsList;
    }

    /**
     * @param product
     * @param quantity
    */
    public void addItem(ProductBean product, int quantity) {
	//v.addElement(name);
        OrderItemBean orderItem = new OrderItemBean();
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        ArrayList<OrderItemBean> orderItems = new ArrayList<OrderItemBean>();
        if(this.orderItemsList != null){
            orderItems.addAll(orderItemsList);
            orderItems.add(orderItem);
        } else {
            orderItems.add(orderItem);
        }
        this.orderItemsList = orderItems;
    }
    public void removeItem(ProductBean product)
    {
        ArrayList<OrderItemBean> retainedOrderItems = new ArrayList<OrderItemBean>();
        for(OrderItemBean orderItem : this.orderItemsList){
            if(orderItem.getProduct().getProductID() != product.getProductID()){
                retainedOrderItems.add(orderItem);
            }
        }
        this.orderItemsList = retainedOrderItems;
    }
    
    public void emptyCart(){
        this.orderItemsList = null;
    }

 
}
