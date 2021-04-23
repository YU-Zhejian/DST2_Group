package com.example.service;

import com.example.bean.User;

import java.util.List;

/**
 * See implementation for more details
 *
 * @author Zhejian YU
 *
 */
public interface UserService {
    void save(User user);
    List<User> findAll(User user);
}
