package com.tortuga.security.governance.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tortuga.security.governance.platform"})
@EnableSwagger2
@EnableScheduling
public class SpringBootSecurityJwtMongodbApplication extends SpringBootServletInitializer {

	
	  @Override
	    protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
		return builder.sources(SpringBootSecurityJwtMongodbApplication.class);
          }
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtMongodbApplication.class, args);
	}

}
