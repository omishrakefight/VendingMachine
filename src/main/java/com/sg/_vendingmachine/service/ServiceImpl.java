/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg._vendingmachine.service;

import com.sg._vendingmachine.dao.FilePersistenceException;
import com.sg._vendingmachine.dao.SnackDao;
import com.sg._vendingmachine.dao.SnackDaoImpl;
import com.sg._vendingmachine.model.Snack;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author omish
 */
public class ServiceImpl implements Service, SnackDao {

    SnackDaoImpl dao;

    @Inject
    public ServiceImpl(SnackDaoImpl dao) {
        this.dao = dao;
    }

    @Override
    public void loadSnacks() throws FilePersistenceException {
        //shouldn't be being called.  Its in the interface (so cant be private) but should be?
        //need it in the interface for the dependency e=injection loose coupling to workthough right?
    }

    @Override
    public List<Snack> listOfSnacks() {
        return dao.listOfSnacks();
    }

    public String getChange(BigDecimal money) {
        String moneyReturns = "";
        int quarters = 25;
        int  dimes = 10;
        int nickels = 05;
        int pennies = 01;
        BigDecimal roundedMoney = money.setScale(2, RoundingMode.HALF_UP);
        
        // convert BigDecimal to int for easy calculations.
        BigDecimal convertToInt = new BigDecimal("100.00");
        roundedMoney = roundedMoney.multiply(convertToInt);
        int intChange = roundedMoney.intValue();
        
        // to be reused for stringify
        int change = 0;

        // this cycles money by sorting by max money values, until all of his 'change' is accounted for.
        if ((intChange / quarters) > 0) {
            change = intChange / quarters;
            intChange = intChange % quarters;
            moneyReturns += "quarters: ";
            moneyReturns += String.valueOf(change);
            moneyReturns += "  ";
        }
        if ((intChange / dimes) > 0) {
            change = intChange / dimes;
            intChange = intChange % dimes;
            moneyReturns += "dimes: ";
            moneyReturns += String.valueOf(change);
            moneyReturns += "  ";
        }
        if ((intChange / nickels) > 0) {
            change = intChange / nickels;
            intChange = intChange % nickels;
            moneyReturns += "nickels: ";
            moneyReturns += String.valueOf(change);
            moneyReturns += "  ";
        }
        if ((intChange / pennies) > 0) {
            change = intChange / pennies;
            moneyReturns += "pennies: ";
            moneyReturns += String.valueOf(change);
            moneyReturns += "  ";
        }
        if (moneyReturns.equals("")){
            moneyReturns = "No change.";
        }
        return moneyReturns;
    }

    @Override
    public String buySnack(String id, BigDecimal bdMoney) throws OutOfStockException, ShortOnMoneyException {
        Snack snack = getSnack(id);
        String message = "";
        // if it has stock AND i have equal to or greater money.
        if(snack.getStock() > 0 && bdMoney.compareTo(snack.getCost()) == 1 || bdMoney.compareTo(snack.getCost()) == 0){
            int currentStock = snack.getStock();
            snack.setStock(currentStock - 1);
            
            message = "Thank you for the purchse!";
        }    // print errors if any instead
        else if(snack.getStock() < 1) {
            throw new OutOfStockException("Out of stock sorry.");
        }
        else if(bdMoney.compareTo(snack.getCost()) == -1){
            BigDecimal amountShort = snack.getCost().subtract(bdMoney).setScale(2, RoundingMode.HALF_UP);
            throw new ShortOnMoneyException("Please insert an additional " + amountShort);
        }
        
        return message;
    }

    @Override
    public BigDecimal minusCost(String id, BigDecimal bdMoney) {
        Snack snack = getSnack(id);
        BigDecimal remainingMoney;
        remainingMoney = bdMoney.subtract(snack.getCost()).setScale(2, RoundingMode.HALF_UP);
        return remainingMoney;
    }
    

    @Override
    public Snack getSnack(String id) {
        return dao.getSnack(id);
    }
    
    
}
