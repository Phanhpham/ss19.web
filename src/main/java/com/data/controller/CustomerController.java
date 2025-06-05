package com.data.controller;

import com.data.model.Customer;
import com.data.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("customer")
    public String getAllCustomer(Model model){
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customer",customers);
                return "customer";
    }

    @GetMapping("addCustomer")
    public String showFormAdd(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer");
        return "customer";
    }
}
