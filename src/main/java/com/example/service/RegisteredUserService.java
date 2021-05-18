package com.example.service;

import com.example.bean.RegisteredUser;
import org.springframework.data.jpa.repository.Query;

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

    RegisteredUser findRegisteredUserByUserName(String userName);
}
