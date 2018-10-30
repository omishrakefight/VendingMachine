/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg._vendingmachine;

import com.sg._vendingmachine.dao.SnackDao;
import com.sg._vendingmachine.dao.SnackDaoImpl;
import com.sg._vendingmachine.model.Snack;
import com.sg._vendingmachine.service.OutOfStockException;
import com.sg._vendingmachine.service.Service;
import com.sg._vendingmachine.service.ServiceImpl;
import com.sg._vendingmachine.service.ShortOnMoneyException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author omish
 */
@Controller
public class VendingController {

    ServiceImpl service;
    private BigDecimal money = BigDecimal.ZERO;
    private List<Snack> snacks;
    private String returnChange;
    private String id;
    private String msg;

    @Inject
    public VendingController(ServiceImpl service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSnacks(Model model) {

        snacks = service.listOfSnacks();

        return "redirect:sendAllModels";
    }

    @RequestMapping(value = "/addMoney", method = RequestMethod.GET)
    public String addMoney(HttpServletRequest request, Model model) {

        BigDecimal addedMoney = new BigDecimal(request.getParameter("money"));
        money = money.add(addedMoney).setScale(2, RoundingMode.HALF_UP);

        //redirect so that way I dont lose contacts when adding money.
        return "redirect:sendAllModels";
    }

    @RequestMapping(value = "/sendAllModels", method = RequestMethod.GET)
    public String sendAllModels(Model model) {

        //make sure everything is returned.
        model.addAttribute("snacks", snacks);
        model.addAttribute("currentMoney", money);
        model.addAttribute("returnChange", returnChange);
        model.addAttribute("id", id);
        model.addAttribute("msg", msg);

        return "VendingMachine";
    }

    @RequestMapping(value = "/getChange", method = RequestMethod.GET)
    public String getChange(HttpServletRequest request, Model model) {
        BigDecimal moneyForChange = new BigDecimal(request.getParameter("money"));

        returnChange = service.getChange(moneyForChange);
        // set money to 0 after calculating change
        money = BigDecimal.ZERO;
        msg = "";
        
        return "redirect:sendAllModels";
    }

    @RequestMapping(value = "/getBtnId", method = RequestMethod.GET)
    public String getBtnId(HttpServletRequest request, Model model) {
        id = request.getParameter("id");

        return "redirect:sendAllModels";
    }

    @RequestMapping(value = "/buySnack", method = RequestMethod.GET)
    public String buySnack(HttpServletRequest request, Model model) {
        // this checks snackId to make sure an item was selected.
        String snackId = request.getParameter("snackId");
        if (snackId != null && !snackId.isEmpty()) {
            String depositedMoney = request.getParameter("depositedMoney");
            BigDecimal bdMoney = new BigDecimal(depositedMoney);
            BigDecimal leftOverMoney;

            //after we have valid input, do all the stuff to buy a snack
            try {
                msg = service.buySnack(snackId, bdMoney);
                leftOverMoney = service.minusCost(snackId, bdMoney).setScale(2, RoundingMode.HALF_UP);
                returnChange = service.getChange(leftOverMoney);
                //zero out fields.
                money = BigDecimal.ZERO;
                id = "";
                snacks = service.listOfSnacks();
            } catch (OutOfStockException | ShortOnMoneyException e) {
                msg = e.getMessage();
            }
            // if no item was selected spit out an error.
        } else {
            msg = "ERROR - select a product.";
        }

        return "redirect:sendAllModels";
    }
}
