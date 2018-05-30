package com.packt.naturebesttouch.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.packt.naturebesttouch.domain.User;
import com.packt.naturebesttouch.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;

// issue : if a new user is added then it will not be counted or added in this session to the AuthenticationManagerBuilder -- 
//	-- it doesn't refresh because it's autowired
//  ... maybe I can added when adding a new user to the AuthenticationManagerBuilder auth ... who knows? 
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		List<User> users = userService.getAllUsers();
		
		for (User user : users) {
			if (user.isIs_admin()) {
				auth.inMemoryAuthentication().withUser(user.getUsername()).password(user.getPassword()).roles("USER", "ADMIN");
			}
			else {
				auth.inMemoryAuthentication().withUser(user.getUsername()).password(user.getPassword()).roles("USER");
			}
		}
		
//		auth.inMemoryAuthentication().withUser("john").password("pa55word").roles("USER");
//		auth.inMemoryAuthentication().withUser("admin").password("root123").roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.formLogin().loginPage("/login").usernameParameter("userId").passwordParameter("password");
		httpSecurity.formLogin().defaultSuccessUrl("/market/products/add").failureUrl("/login?error");
		httpSecurity.logout().logoutSuccessUrl("/login?logout");
		httpSecurity.exceptionHandling().accessDeniedPage("/login?accessDenied");
		httpSecurity.authorizeRequests().antMatchers("/").permitAll().antMatchers("/**/add").access("hasRole('ADMIN')")
				.antMatchers("/**/market/**").access("hasRole('USER')");
		httpSecurity.csrf().disable();
	}

}
