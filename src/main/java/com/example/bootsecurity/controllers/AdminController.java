package com.example.bootsecurity.controllers;

import com.example.bootsecurity.entity.User;
import com.example.bootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;


    @GetMapping(value = "/admin")
    public String findAll(Model model){

        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping(value = "/admin/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        List<User> users = new ArrayList<>();
        users.add(user);
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/admin/create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/admin/create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/admin/update")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }

}
