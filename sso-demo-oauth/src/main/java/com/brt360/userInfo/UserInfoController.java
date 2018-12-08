package com.brt360.userInfo;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
	
	@RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
