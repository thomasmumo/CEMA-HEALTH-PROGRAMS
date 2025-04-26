package com.example.HealthPrograms.Services;


import com.example.HealthPrograms.Models.UserPrincipal;
import com.example.HealthPrograms.Models.Users;
import com.example.HealthPrograms.Repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepo.findByUserName(username);


        if(user == null) {
            System.out.println("user not found");
        }

        return new UserPrincipal(user);
    }
}
