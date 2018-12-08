package com.brt360.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()//禁用csrf
			.antMatcher("/**")//拦截所有请求
			.authorizeRequests()
			.antMatchers().permitAll()//要放开的请求
			.anyRequest().authenticated()//其余请求需要认证
			.and()
			.logout().logoutSuccessUrl("http://localhost:8000/oa/revokeClient").permitAll();//退出登录跳转到oa进行注销
	}

}
