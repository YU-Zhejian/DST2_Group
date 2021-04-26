package com.example.dao;

import com.example.bean.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * See implementation for more details
 *
 * @author Zhejian YU
 */
public interface RegisteredUserDao extends JpaRepository<RegisteredUser, String> {
    @Query("SELECT ru FROM RegisteredUser ru WHERE ru.userName = ?1")
    RegisteredUser findRegisteredUserByUserName(String userName);
}
