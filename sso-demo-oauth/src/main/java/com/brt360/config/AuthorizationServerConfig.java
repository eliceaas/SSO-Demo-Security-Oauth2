package com.brt360.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
	        .inMemory()
	        .withClient("client1")
	        .secret(passwordEncoder.encode("secret1"))
	        .scopes("read", "write")
	        .redirectUris("http://localhost:8081/clt1/login")
	        .authorizedGrantTypes("authorization_code", "refresh_token")
	        .autoApprove(true)
	        .and()
	        .withClient("client2")
	        .secret(passwordEncoder.encode("secret2"))
	        .scopes("read", "write")
	        .redirectUris("http://localhost:8082/clt2/login")
	        .authorizedGrantTypes("authorization_code", "refresh_token")
	        .autoApprove(true);
	}
	
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    	endpoints.tokenStore(tokenStore);
    }

}
