package com.karthik.NOTESAPP.controller;

import com.karthik.NOTESAPP.model.Loginresponse;
import com.karthik.NOTESAPP.model.User;
import com.karthik.NOTESAPP.repo.UserRepo;
import com.karthik.NOTESAPP.service.JwtService;
import com.karthik.NOTESAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private UserService userService;
   @Autowired
    private AuthenticationManager authenticationmanager;
   @Autowired
    private JwtService jwtservice;
   @Autowired
   private UserRepo userrepo;
    @PostMapping("register")
    public User register(@RequestBody User user)
    {
        System.out.println("entered into authentication succes");
        return userService.saveUser(user);
    }
    @PostMapping("login")
    public Loginresponse login(@RequestBody User user)
    {
        Authentication auth = authenticationmanager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
         User userr = userrepo.findByUserName(user.getUserName());
        if(auth.isAuthenticated())
        {
            Loginresponse loginresponse = new Loginresponse();
            loginresponse.setToken(jwtservice.getToken(user.getUserName()));
            loginresponse.setUserid(userr.getId());
            loginresponse.setUserName(userr.getUserName());
            return loginresponse;
        }
        else {
            return null;
        }
    }
}
