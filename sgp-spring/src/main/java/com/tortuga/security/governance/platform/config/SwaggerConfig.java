package com.tortuga.security.governance.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.collect.Lists;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;


@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {
	
	 @Bean
	    public Docket apiDocket() {
	       /* return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.any())
	                .paths(PathSelectors.any())
	                .build();*/
	        
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.any())
	                .paths(PathSelectors.any())
	                .apis(RequestHandlerSelectors.basePackage("com.tortuga.security.governance.platform.controllers"))
	                .build()
	                .securitySchemes(Lists.newArrayList(apiKey()))
	                .securityContexts(Lists.newArrayList(securityContext()));
	              
	    }
	 
	 @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	    .addResourceLocations("classpath:/META-INF/resources/");

	    registry.addResourceHandler("/webjars/**")
	    .addResourceLocations("classpath:/META-INF/resources/webjars/");
	  }
	
	 
	 @Bean
	 SecurityContext securityContext() {
	     return SecurityContext.builder()
	             .securityReferences(defaultAuth())
	             .forPaths(PathSelectors.any())
	             .build();
	 }

	 List<SecurityReference> defaultAuth() {
	     AuthorizationScope authorizationScope
	             = new AuthorizationScope("global", "accessEverything");
	     AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	     authorizationScopes[0] = authorizationScope;
	     return Lists.newArrayList(
	             new SecurityReference("JWT", authorizationScopes));
	 }

	 private ApiKey apiKey() {
	     return new ApiKey("JWT", "Authorization", "header");
	 }
	 
	 
	 
	/*
	 * @Bean public Docket api() { return new Docket(DocumentationType.SWAGGER_2)
	 * .select() .apis(RequestHandlerSelectors.basePackage(
	 * "com.tortuga.security.governance.platform")) .paths(PathSelectors.any())
	 * .build(); }
	 */
	
	/*
	 * @Bean public Docket api(ServletContext servletContext) { return new
	 * Docket(DocumentationType.SWAGGER_2) .securitySchemes(Arrays.asList(apiKey()))
	 * .securityContexts(Collections.singletonList(securityContext())); }
	 * 
	 * private SecurityContext securityContext() { return
	 * SecurityContext.builder().securityReferences(defaultAuth()).forPaths(
	 * PathSelectors.regex("/.*")).build(); }
	 * 
	 * private List<SecurityReference> defaultAuth() { final AuthorizationScope
	 * authorizationScope = new AuthorizationScope("global", "accessEverything");
	 * final AuthorizationScope[] authorizationScopes = new
	 * AuthorizationScope[]{authorizationScope}; return
	 * Collections.singletonList(new SecurityReference("Bearer",
	 * authorizationScopes)); }
	 * 
	 * private ApiKey apiKey() { return new ApiKey("Bearer", "Authorization",
	 * "header"); }
	 */
	
      
      
      
	/*
	 * @Bean public Docket productApi() { return new
	 * Docket(DocumentationType.SWAGGER_2) .select()
	 * .apis(RequestHandlerSelectors.basePackage(
	 * "com.tortuga.security.governance.platform")) .paths(regex("/api.*"))
	 * .build(); } private ApiInfo metaData() { ApiInfo apiInfo = new ApiInfo(
	 * "Tortuga Security Governance Platform Api Documentation",
	 * "Tortuga Security Governance Platform Api Documentation", "1.0",
	 * "Terms of service", new Contact("John Thompson",
	 * "https://springframework.guru/about/", "john@springfrmework.guru"),
	 * "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0",
	 * null); return apiInfo; }
	 */
}
