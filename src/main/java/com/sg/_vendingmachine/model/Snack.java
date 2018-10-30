/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg._vendingmachine.model;

import java.math.BigDecimal;

/**
 *
 * @author omish
 */
public class Snack {

    private String name = "";
    private int stock = 0;
    private String id = "";
    private BigDecimal cost = BigDecimal.ZERO;

    public Snack(String name, String stock, String id, String cost) {
        this.name = name;
        this.stock = Integer.parseInt(stock);
        this.id = id;
        this.cost = new BigDecimal(cost);
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    
    
}
