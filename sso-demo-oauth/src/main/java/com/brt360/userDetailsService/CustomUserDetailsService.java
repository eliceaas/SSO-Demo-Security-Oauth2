package com.brt360.userDetailsService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.brt360.memoryDB.RoleMemoryMap;
import com.brt360.memoryDB.UserMemoryMap;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username == null) {
			username = new String();
		}
		//获取role
		List<String> roles = RoleMemoryMap.map.get(username);
		//组装auth
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		if (roles != null) {
			for(String role : roles) {
				SimpleGrantedAuthority auth = new SimpleGrantedAuthority(role);
				auths.add(auth);
			}
		}
		//获取password
		String password = UserMemoryMap.map.get(username);
		if (password == null) {
			password = new String();
		}
		//返回user
		return new User(username, passwordEncoder.encode(password), auths);
	}

}
