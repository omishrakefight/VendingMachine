/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg._vendingmachine.dao;

import com.sg._vendingmachine.model.Snack;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author omish
 */
public class SnackDaoImpl implements SnackDao {

    public static final String STOCK_FILE = "Snacks_Pls.txt";
    public static final String DELIMITER = "::";

    private List<Snack> snacks = new ArrayList<>();
    private Map<String, Snack> snacksMap = new HashMap<>();

    public SnackDaoImpl() {
        try {
            loadSnacks();
        } catch (FilePersistenceException e) {
            System.out.println("Could not load candy");
        }

    }

    @Override
    public void loadSnacks() throws FilePersistenceException {
        //     Snack(String name, String stock, String id, String cost)
        Snack snack1 = new Snack("Snicker", "10", "A1", "1.50");
        Snack snack2 = new Snack("Mtn Dew", "10", "B1", "2.00");
        Snack snack3 = new Snack("Twix", "1", "A2", "1.75");
        Snack snack4 = new Snack("Doritoes", "10", "A3", "2.15");
        Snack snack5 = new Snack("Poptart", "10", "A4", "2.50");
        Snack snack6 = new Snack("Dr. Pepper", "6", "B2", "2.00");
        Snack snack7 = new Snack("Root Beer", "10", "B3", "2.00");
        Snack snack8 = new Snack("Melted Icecream", "10", "A5", "1.00");
        Snack snack9 = new Snack("Nuclear Waste", "10", "A6", ".50");

        snacks.add(snack1);
        snacks.add(snack2);
        snacks.add(snack3);
        snacks.add(snack4);
        snacks.add(snack5);
        snacks.add(snack6);
        snacks.add(snack7);
        snacks.add(snack8);
        snacks.add(snack9);

        for (Snack snack : snacks) {
            snacksMap.put(snack.getId(), snack);
        }
    }

    @Override
    public Snack getSnack(String id) {
        return snacksMap.get(id);
    }

    @Override
    public List<Snack> listOfSnacks() {
        return snacks;
    }

}
