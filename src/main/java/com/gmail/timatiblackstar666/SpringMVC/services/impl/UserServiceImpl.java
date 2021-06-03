package com.gmail.timatiblackstar666.SpringMVC.services.impl;

import com.gmail.timatiblackstar666.SpringMVC.dao.UserRepository;
import com.gmail.timatiblackstar666.SpringMVC.models.User;
import com.gmail.timatiblackstar666.SpringMVC.services.IUserService;
import com.gmail.timatiblackstar666.SpringMVC.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void postConstructor(){
        saveUser(new User("Timati Admin", "admin@mail.com", "admin123", Constants.ROLE_ADMIN, true));
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByEmail(login);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public List<User> findUsersByName(String name) {
        return null;
    }
}
