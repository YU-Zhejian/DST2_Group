package com.example.service.impl;

import com.example.bean.RegisteredUser;
import com.example.bean.Sample;
import com.example.dao.RegisteredUserDao;
import com.example.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Query or update user database. See also: {@link DrugLabelServiceImpl}
 *
 * @author Zhejian YU
 */
@Service
public class RegisteredUserServiceImpl implements RegisteredUserService {

    @Autowired
    private RegisteredUserDao registeredUserDao;

    /**
     * Update user database
     *
     * @param registeredUser Entry that needs to be inserted or updated. Remove id to make it insert instead of update.
     */
    @Override
    @Transactional
    public void save(RegisteredUser registeredUser) {
        this.registeredUserDao.save(registeredUser);
        this.registeredUserDao.flush();
    }

    /**
     * Query dosing guideline database
     *
     * @param registeredUser Entry that needs to be queried
     * @return List of found entries
     */
    @Override
    public List<RegisteredUser> findAll(RegisteredUser registeredUser) {
        Example<RegisteredUser> example = Example.of(registeredUser);
        return this.registeredUserDao.findAll(example);
    }

    /**
     * Find user with user name
     *
     * @param userName userName to find
     * @return User find
     */
    @Override
    public RegisteredUser findRegisteredUserByUserName(String userName){
        return registeredUserDao.findRegisteredUserByUserName(userName);
    }



}
