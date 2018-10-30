/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg._vendingmachine.service;

/**
 *
 * @author omish
 */
public class OutOfStockException extends Exception{

    public OutOfStockException(String msg) {
        super(msg);
    }

    public OutOfStockException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
