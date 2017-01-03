package org.pkb.springlogin.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class JwtLogoutHandler extends SimpleUrlLogoutSuccessHandler {

	private String authParam;

	@Autowired
	private TokenRepository tokenRepository;

	public JwtLogoutHandler(String authParam) {
		this.authParam = authParam;
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String authToken = request.getParameter(authParam);
		if (!tokenRepository.isBlackList(authToken)) {
			System.out.println("Blacklisted token : " + authToken);
			tokenRepository.blackListToken(authToken);
		}
		super.onLogoutSuccess(request, response, authentication);
	}

}
