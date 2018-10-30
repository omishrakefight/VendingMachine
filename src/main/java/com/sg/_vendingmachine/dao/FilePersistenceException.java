/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg._vendingmachine.dao;

/**
 *
 * @author omish
 */
public class FilePersistenceException extends Exception{
    public FilePersistenceException (String msg) {
        super(msg);
    }
    public FilePersistenceException (String msg, Throwable cause) {
        super(msg, cause);
    }
}
