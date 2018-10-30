/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg._vendingmachine.dao;

import com.sg._vendingmachine.model.Snack;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author omish
 */
public class SnackDaoDbImpl implements SnackDao{

    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //also get a list of snacks.
    private final static String SQL_LOAD_SNACKS = "";
    
    // get a list of snacks
    private final static String SQL_LIST_SNACKS = "";
    
    // delete old snack and add a new one with -1 stock.
    private final static String SQL_GET_SNACK = "";
    
    @Override
    public void loadSnacks() throws FilePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Snack> listOfSnacks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Snack getSnack(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
