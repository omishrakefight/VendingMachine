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
public class ShortOnMoneyException extends Exception{
    public ShortOnMoneyException(String msg) {
        super(msg);
    }
    public ShortOnMoneyException(String msg, Throwable cause){
        super(msg, cause);
    }
}
