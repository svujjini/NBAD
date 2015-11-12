/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;

/**
 *
 * @author Sneha
 */
public class OrderItemBean implements Serializable {
  private ProductBean product;
  private int quantity;
  private int total;
  
    public OrderItemBean(){}
    
    public OrderItemBean(ProductBean product, int quantity, int total){
        this.product=product;
        this.quantity=quantity;
        this.total=total;
    }

    /**
     * @return the product
     */
    public ProductBean getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(ProductBean product) {
        this.product = product;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return this.quantity * this.product.getPrice();
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }
}

  