package com.example.service.impl;

import com.example.bean.RegisteredUser;
import com.example.dao.RegisteredUserDao;
import com.example.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Query or update user database. See also: {@link DrugLabelServiceImpl}
 *
 * @author Zhejian YU
 */
@Service
public class RegisteredRegisteredUserServiceImpl implements RegisteredUserService {

    @Autowired
    private RegisteredUserDao registeredUserDao;

    /**
     * Update drug database
     *
     * @param registeredUser Entry that needs to br inserted
     */
    @Override
    @Transactional
    public void save(RegisteredUser registeredUser) {
        RegisteredUser param = new RegisteredUser();
        param.setId(registeredUser.getId());
        List<RegisteredUser> list = this.findAll(registeredUser);
        if (list.size() == 0) {
            this.registeredUserDao.save(registeredUser);
            this.registeredUserDao.flush();
        }
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
}
