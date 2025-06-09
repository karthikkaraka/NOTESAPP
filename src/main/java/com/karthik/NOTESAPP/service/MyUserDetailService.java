package com.karthik.NOTESAPP.service;

import com.karthik.NOTESAPP.model.User;
import com.karthik.NOTESAPP.model.UserPrinciple;
import com.karthik.NOTESAPP.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUserName(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("user not found!!1");
        }
        else{
            return new UserPrinciple(user);
        }
    }
}
