package com.tortuga.security.governance.platform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.tortuga.security.governance.platform.security.jwt.AuthEntryPointJwt;
import com.tortuga.security.governance.platform.security.jwt.AuthTokenFilter;
import com.tortuga.security.governance.platform.security.services.UserDetailsServiceImpl;

/**
 * Provides a convenient base class for creating a {@link WebSecurityConfigurerAdapter}
 * instance. The implementation allows customization by overriding methods.
 * 
 * @author Tortuga
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	/**
	 * Override this method to expose the {@link AuthenticationManager} from
	 * {@link #configure(AuthenticationManagerBuilder)} to be exposed as a Bean.
	 * @return the {@link AuthenticationManager}
	 * @throws Exception
	 */
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * PasswordEncoder that uses the BCrypt strong hashing function
	 * @return BCryptPasswordEncoder object 
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Override this method to configure the {@link HttpSecurity}. 
	 * <pre>
	 * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
	 * </pre>
	 *
	 * @param http the {@link HttpSecurity} to modify
	 * @throws Exception if an error occurs
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			.antMatchers("/api/auth/**").permitAll()
			.antMatchers("/api/user/**").permitAll()
			.antMatchers("/api/role/**").permitAll()
			.antMatchers("/api/pie-chart/**").permitAll()
			.antMatchers("/api/project/**").permitAll()
			.antMatchers("/api/sgpproject/**").permitAll()
			.anyRequest().authenticated();
		
		// http.authorizeRequests().anyRequest().permitAll();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("swagger-ui.html")
	                .addResourceLocations("classpath:/META-INF/resources/");
	        registry.addResourceHandler("/webjars/**")
		    .addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }
	 public void configure(WebSecurity web) throws Exception {
	        web.ignoring().mvcMatchers(HttpMethod.OPTIONS, "/**");
	        // ignore swagger 
	        web.ignoring().mvcMatchers("/swagger-ui.html/**", "/configuration/**", "/swagger-resources/**", "/v2/api-docs","/webjars/**");
	    }
	
	/*
	 * @Override public void configure(WebSecurity web) throws Exception {
	 * web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
	 * "/swagger-resources", "/configuration/security",
	 * "/swagger-ui.html","/swagger-ui", "/webjars/**"); }
	 */
}
