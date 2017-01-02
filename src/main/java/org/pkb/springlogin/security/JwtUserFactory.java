package org.pkb.springlogin.security;

import org.springframework.security.core.userdetails.UserDetails;

public final class JwtUserFactory {

	private JwtUserFactory() {

	}

	public static JwtUser create(UserDetails user) {
		JwtUser jwtUser = new JwtUser();
		jwtUser.setUsername(user.getUsername());
		jwtUser.setPassword(user.getPassword());
		jwtUser.setAuthorities(user.getAuthorities());
		jwtUser.setEnabled(user.isEnabled());
		return jwtUser;
	}

}
