package com.karthik.NOTESAPP.service;

import com.karthik.NOTESAPP.model.User;
import com.karthik.NOTESAPP.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public User saveUser(User user) {
    user.setPassword(encoder.encode(user.getPassword()));
       return repo.save(user);
    }

    public User findUser(String userName) {
        return repo.findByUserName(userName);
    }
}
