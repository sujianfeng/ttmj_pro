package com.len.kindle.service;

import com.len.kindle.entity.User;
import com.len.kindle.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sujianfeng
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User login(String username, String password) {

        return userRepo.findUserByUsernameAndPassword(username, password);
    }
}
