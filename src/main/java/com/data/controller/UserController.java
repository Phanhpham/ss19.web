package com.data.controller;

import com.data.model.Customer;
import com.data.model.User;
import com.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/page")
    public String getAllCustomer(
            Model model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {

        List<User> users = userService.getUsersByPage(page, size);
        long totalUsers = userService.countTotalUsers();
        int totalPages = (int) Math.ceil((double) totalUsers / size);

        model.addAttribute("user", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "user";
    }

    @GetMapping("/users/block/{id}")
    public String blockUser(@PathVariable("id") int id) {
        userService.blockUserById(id);
        return "redirect:/page";
    }

    @GetMapping("/users/unblock/{id}")
    public String unblockUser(@PathVariable("id") int id) {
        userService.unblockUserById(id);
        return "redirect:/page";
    }

}
