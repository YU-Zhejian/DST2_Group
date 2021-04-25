package com.example.dao;

import com.example.bean.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * See implementation for more details
 *
 * @author Zhejian YU
 */
public interface RegisteredUserDao extends JpaRepository<RegisteredUser, String> {}
