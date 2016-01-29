package com.sharpinu.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.persist.repositories.UserRoleRepo;
import com.sharpinu.security.bean.SharpInUUserDetails;

@Component
public class SharpInUUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserRoleRepo userRoleRepo;

	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		User user = userRepo.findByUserEmail(userEmail);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("Cannot find user by userEmail [%s]", userEmail));
		}

		String password = user.getUserPassword();
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		return new SharpInUUserDetails(userEmail, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(user.getUserId()), user);
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Integer userId) {
		List<String> roles = userRoleRepo.findRoleNameByUserId(userId);
		List<GrantedAuthority> authList = getGrantedAuthorities(roles);
		return authList;
	}

	private static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}

		return authorities;
	}
}
