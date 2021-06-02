package com.gmail.timatiblackstar666.SpringMVC.services;

import com.gmail.timatiblackstar666.SpringMVC.dao.UserRepository;
import com.gmail.timatiblackstar666.SpringMVC.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(user);
    }
}
