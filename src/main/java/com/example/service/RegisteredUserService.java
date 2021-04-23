package com.example.service;

import com.example.bean.RegisteredUser;

import java.util.List;

/**
 * See implementation for more details
 *
 * @author Zhejian YU
 *
 */
public interface RegisteredUserService {
    void save(RegisteredUser registeredUser);
    List<RegisteredUser> findAll(RegisteredUser registeredUser);
}
