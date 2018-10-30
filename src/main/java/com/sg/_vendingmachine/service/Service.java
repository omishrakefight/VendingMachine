/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg._vendingmachine.service;

import com.sg._vendingmachine.dao.SnackDao;
import java.math.BigDecimal;

/**
 *
 * @author omish
 */
public interface Service extends SnackDao {
    
     public String getChange(BigDecimal money);
     public String buySnack(String id, BigDecimal bdMoney) throws OutOfStockException, ShortOnMoneyException;
     
     public BigDecimal minusCost(String id, BigDecimal bdMoney);
}
