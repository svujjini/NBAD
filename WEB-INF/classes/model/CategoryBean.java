/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;

/**
 *
 * @author kbs
 */
public class CategoryBean implements Serializable {
    private int catID;
    private String catName;
    
    public CategoryBean(){}
    
    public CategoryBean(int catID, String catName){
        this.catID = catID;
        this.catName = catName;
    }

    /**
     * @return the catID
     */
    public int getCatID() {
        return catID;
    }

    /**
     * @param catID the catID to set
     */
    public void setCatID(int catID) {
        this.catID = catID;
    }

    /**
     * @return the catName
     */
    public String getCatName() {
        return catName;
    }

    /**
     * @param catName the catName to set
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }
}
