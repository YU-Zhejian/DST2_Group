package com.example.dao;

import com.example.bean.DrugLabel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * See implementation for more details
 * @author Zhejian YU
 */
public interface UserDao extends JpaRepository<DrugLabel,String> {
}
