package com.example.util;

import com.example.bean.RegisteredUser;
import com.example.dao.RegisteredUserDao;
import com.example.service.RegisteredUserService;
import com.example.service.impl.RegisteredUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * To download dosing guideline information by general-purposed crawler
 *
 * @author Zhejian YU
 */
@Component
public class RegisteredUserUtils {

	private final Logger log = LoggerFactory.getLogger(com.example.util.RegisteredUserUtils.class);

	@Autowired
	private RegisteredUserService registeredUserService;

	/**
	 * To add default registered users
	 *
	 * @throws Exception TODO
	 */
	public void registerPreservedUser() throws Exception {
		log.info("Start adding users");
		RegisteredUser rootUser = new RegisteredUser();
		rootUser.setUserName("root");
		if (this.registeredUserService.findAll(rootUser) != null) {
			log.info("Finished adding users -- done previously");
			return;
		}
		rootUser.setId(0L);
		this.registeredUserService.save(rootUser);
		for (long userId = 0L; userId < 1000; userId++) {
			RegisteredUser tmpUser = new RegisteredUser();
			tmpUser.setUserName("PRESERVED_" + userId);
			tmpUser.setId(userId);
			this.registeredUserService.save(tmpUser);
		}
		log.info("Finished adding users");
	}
}
