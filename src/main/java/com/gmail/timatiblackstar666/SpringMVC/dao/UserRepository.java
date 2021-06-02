package com.gmail.timatiblackstar666.SpringMVC.dao;

import com.gmail.timatiblackstar666.SpringMVC.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);
}
