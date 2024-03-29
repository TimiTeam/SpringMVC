package com.gmail.timatiblackstar666.SpringMVC.services.impl;

import com.gmail.timatiblackstar666.SpringMVC.dao.UserRepository;
import com.gmail.timatiblackstar666.SpringMVC.exceptions.UserNotFoundException;
import com.gmail.timatiblackstar666.SpringMVC.models.User;
import com.gmail.timatiblackstar666.SpringMVC.services.IUserService;
import com.gmail.timatiblackstar666.SpringMVC.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
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
        saveUser(new User("Timati User", "timati@mail.com", "1234", Constants.ROLE_USER, true));
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

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(String id) throws UserNotFoundException {
        try {
            long userId = Long.parseLong(id);
            return userRepository.findById(userId).
                    orElseThrow(() -> new UserNotFoundException("User not found"));
        }catch (NumberFormatException e){
            throw new UserNotFoundException("Wrong id: "+id);
        }
    }

    @Override
    public User getCurrentUser(){
        User user = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            user = findUserByLogin(auth.getName());
        }
        return user;
    }
}
