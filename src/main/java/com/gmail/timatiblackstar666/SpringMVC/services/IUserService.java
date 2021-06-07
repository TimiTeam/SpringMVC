package com.gmail.timatiblackstar666.SpringMVC.services;

import com.gmail.timatiblackstar666.SpringMVC.exceptions.UserNotFoundException;
import com.gmail.timatiblackstar666.SpringMVC.models.User;

import java.util.List;

public interface IUserService {
    User findUserById(String id) throws UserNotFoundException;
    User saveUser(User user);
    User findUserByLogin(String login);
    User updateUser(User user);
    List<User> findUsersByName(String name);
    List<User> findAllUsers();
    User getCurrentUser();
}
