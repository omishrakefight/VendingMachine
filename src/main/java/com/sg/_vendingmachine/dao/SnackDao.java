/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg._vendingmachine.dao;

import com.sg._vendingmachine.model.Snack;
import java.util.List;

/**
 *
 * @author omish
 */
public interface SnackDao {
    void loadSnacks() throws FilePersistenceException;
    
    public List<Snack> listOfSnacks();
    
    public Snack getSnack(String id);
}
