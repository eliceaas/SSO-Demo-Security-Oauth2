package com.brt360.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.brt360.userDetailsService.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@Order(1)//SecurityConfig >> ResourceConfig
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()//禁用csrf
			.requestMatchers()
			.antMatchers("/loginPage", "/login**", "/registerPage", "/register", "/oauth/authorize", "/revokeClient")//只拦截特定的url
			.and()
			.authorizeRequests()
			.antMatchers("/registerPage", "/register").permitAll()//放开注册
			.anyRequest()
			.authenticated()
			.and()
			.formLogin().loginPage("/loginPage").loginProcessingUrl("/login").permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
	}
	
	//放开静态资源
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/docs/**", "/fonts/**", "/img/**", "/js/**", "/plugins/**");
    }
	
}
