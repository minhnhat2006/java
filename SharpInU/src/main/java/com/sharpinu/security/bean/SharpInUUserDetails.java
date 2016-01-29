package com.sharpinu.security.bean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SharpInUUserDetails extends User {
	private static final long serialVersionUID = -3614724270308267823L;

	private com.sharpinu.persist.domain.User sharpInUUser;

	public SharpInUUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, com.sharpinu.persist.domain.User sharpInUUser) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.sharpInUUser = sharpInUUser;
	}

	public com.sharpinu.persist.domain.User getSharpInUUser() {
		return sharpInUUser;
	}

	public void setSharpInUUser(com.sharpinu.persist.domain.User siuUser) {
		this.sharpInUUser = siuUser;
	}

}
