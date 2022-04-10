package com.example.bootsecurity.controllers;

import com.example.bootsecurity.entity.User;
import com.example.bootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public String getUser(Principal principal, Model model){
        List<User> users = new ArrayList<>();
        User user = userService.findByName(principal.getName());
        users.add(user);
        model.addAttribute("users", users);
        return "user";
    }
}
