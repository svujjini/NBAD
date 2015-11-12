/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sneha
 */
public class OrderBean implements Serializable {
  private int orderNumber;
  private Date date;
  private UserBean user;
  private ArrayList<OrderItemBean> orderItems;
  private double taxRate;
  private double total;
  private boolean paid;  
  
  public OrderBean(){};
  
  public OrderBean(int orderNumber, Date date, UserBean user, ArrayList<OrderItemBean> orderItems, double taxRate, double total, boolean paid){
      this.orderNumber = orderNumber;
      this.date=date;
      this.user=user;
      this.orderItems=orderItems;
      this.taxRate=taxRate;
      this.total=total;
      this.paid=paid;
  }

    /**
     * @return the orderNumber
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the user
     */
    public UserBean getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserBean user) {
        this.user = user;
    }

    /**
     * @return the orderItems
     */
    public ArrayList<OrderItemBean> getOrderItems() {
        return orderItems;
    }

    /**
     * @param orderItems the orderItems to set
     */
    public void setOrderItems(ArrayList<OrderItemBean> orderItems) {
        this.orderItems = orderItems;
    }

    /**
     * @return the taxRate
     */
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate the taxRate to set
     */
    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the paid
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * @param paid the paid to set
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

}