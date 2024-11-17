package com.api.api_game.controller;
  
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.api_game.model.User;
import com.api.api_game.service.UserService;
   
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
    
    
 
    
    
}
