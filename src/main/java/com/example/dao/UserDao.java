package com.example.dao;

import com.example.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * See implementation for more details
 *
 * @author Zhejian YU
 */
public interface UserDao extends JpaRepository<User, String> {}
