package org.pkb.springlogin.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class JwtAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	JwtTokenUtil tokenUtil;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		ReflectionToStringBuilder.toString(authentication.getPrincipal(), ToStringStyle.MULTI_LINE_STYLE);

		String token = tokenUtil.generateToken((UserDetails) authentication.getPrincipal());
		if (token != null)
			getRedirectStrategy().sendRedirect(request, response,
					"/user?" + JwtAuthenticationTokenFilter.tokenParam + "=" + token);
		else
			super.onAuthenticationSuccess(request, response, authentication);
	}

}
