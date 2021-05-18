package com.example.init;

import com.example.util.RegisteredUserUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * The run method of this class is executed after initializing Java Beans.
 *
 * @author 黑夜刺客勋
 * @author Zhejian YU
 */
@Component
public class InitApp implements ApplicationRunner {

    // https://blog.csdn.net/m0_37642745/article/details/107106496 Accessed 26/04/2021
    @Override
    public void run(ApplicationArguments args) throws Exception {
        RegisteredUserUtils registeredUserUtils = new RegisteredUserUtils();
        registeredUserUtils.registerPreservedUser();
    }
}
