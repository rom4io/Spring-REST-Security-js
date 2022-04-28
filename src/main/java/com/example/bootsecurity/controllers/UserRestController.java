package com.example.bootsecurity.controllers;

import com.example.bootsecurity.entity.User;
import com.example.bootsecurity.service.RoleService;
import com.example.bootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final RoleService roleService;

    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    @GetMapping("/user")
//    public ResponseEntity<User> userPrincipal(@AuthenticationPrincipal User user){
//        return user != null?
//                new ResponseEntity<>(user, HttpStatus.OK):
//                new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
    @GetMapping("/user")
    public User userPrincipal(@AuthenticationPrincipal User user){
        return user;
    }

    @GetMapping("/users")
    public List<User> showAllUsers(){
        List<User> allUsers = userService.findAll();
        return allUsers;
    }
    @GetMapping("/users/{id}")
    public User userById(@PathVariable("id") Long id){
        return userService.findById(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        User newUser = user;
        userService.saveUser(newUser);
    }

    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id")Long id){
        userService.deleteById(id);
    }


}
