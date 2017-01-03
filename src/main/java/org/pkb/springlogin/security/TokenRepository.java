package org.pkb.springlogin.security;

import java.util.HashSet;
import java.util.Set;

public class TokenRepository {

	private Set<String> blackListedTokens;

	private JwtTokenUtil jwtTokenUtil;

	public TokenRepository(JwtTokenUtil jwtTokenUtil) {
		this.blackListedTokens = new HashSet<>();
		this.jwtTokenUtil = jwtTokenUtil;
	}

	public void blackListToken(String token) {
		boolean tokenExpired = this.jwtTokenUtil.isTokenExpired(token);
		if (!tokenExpired)
			blackListedTokens.add(token);
	}

	public boolean isBlackList(String token) {
		boolean tokenExpired = this.jwtTokenUtil.isTokenExpired(token);
		if (tokenExpired) {
			blackListedTokens.remove(token);
			return false;
		} else {
			return this.blackListedTokens.contains(token);
		}
	}

}
