package com.example.servlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/");
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/contact").setViewName("/contact");
		registry.addViewController("/dosingGuideline").setViewName("/dosingGuideline");
		registry.addViewController("/drug").setViewName("/drug");
		registry.addViewController("/index").setViewName("/index");
		registry.addViewController("/login").setViewName("/login");
		registry.addViewController("/navi").setViewName("/navi");
		registry.addViewController("/register").setViewName("/register");
		registry.addViewController("/result").setViewName("/result");
	}

	/**
	 * Support Multiple JSP
	 * https://blog.csdn.net/tyvbpq/article/details/83588508
	 *
	 * @return resolved JSP
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
