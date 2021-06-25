package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The crawler wrapper. This is the root class of this application, and should be placed above other packages (Spring Boot Reference, v2.0..RELEASE).
 *
 * @author Jie Jin
 * @author Yaqi-SU
 */

@SpringBootApplication
@EnableJpaRepositories
// https://stackoverflow.com/questions/48019364/nested-exception-is-java-lang-illegalargumentexception-not-a-managed-type-clas Accessed 24/04/2021
@EntityScan
// https://stackoverflow.com/questions/48019364/nested-exception-is-java-lang-illegalargumentexception-not-a-managed-type-clas Accessed 24/04/2021
@EnableScheduling // To enable the task be executed as scheduled (fixDelay=1 week)
@ServletComponentScan // https://blog.csdn.net/u010508829/article/details/80594003
public class Dst2Application extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder
	configure(SpringApplicationBuilder application) {
		return application.sources(Dst2Application.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(Dst2Application.class, args);
	}
}
