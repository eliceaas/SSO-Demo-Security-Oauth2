package com.brt360.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RevokeClientController {
	
	@RequestMapping("/revokeClient")
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication, Principal principal) {
		try {
			SecurityContextLogoutHandler handler = new SecurityContextLogoutHandler();
			
			handler.logout(request, response, authentication);
			handler.setClearAuthentication(true);
			handler.setInvalidateHttpSession(true);
			
			/*String username = principal.getName();
			Collection<OAuth2AccessToken> accessTokens = tokenStore.findTokensByClientIdAndUserName("client1", username);
			accessTokens.forEach(a -> ConsumerTokenServices.revokeToken(a.getValue()));*/
			
			//跳回客户端
			response.sendRedirect(request.getHeader("referer"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
