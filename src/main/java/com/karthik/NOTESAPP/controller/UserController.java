package com.karthik.NOTESAPP.controller;

import com.karthik.NOTESAPP.model.User;
import com.karthik.NOTESAPP.service.JwtService;
import com.karthik.NOTESAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
   @Autowired
    private AuthenticationManager authenticationmanager;
   @Autowired
    private JwtService jwtservice;
    @PostMapping("register")
    public User register(@RequestBody User user)
    {
        System.out.println("entered into authentication succes");
        return userService.saveUser(user);
    }
    @PostMapping("login")
    public String login(@RequestBody User user)
    {
        Authentication auth = authenticationmanager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));

        if(auth.isAuthenticated())
        {
            return jwtservice.getToken(user.getUserName());
        }
        else {
            return "Failure";
        }
    }
}
