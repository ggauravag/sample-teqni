package org.pkb.springlogin.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private TokenRepository tokenRepository;

	public static String tokenParam;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String authToken = request.getParameter(tokenParam);

		// authToken.startsWith("Bearer ")
		// String authToken = header.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(authToken);
		logger.info("Checking authentication for user " + username);

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			if (!tokenRepository.isBlackList(authToken)) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

				// For simple validation it is completely sufficient to just
				// check
				// the token integrity. You don't have to call
				// the database compellingly. Again it's up to you ;)
				if (jwtTokenUtil.validateToken(authToken, userDetails)) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					logger.info("Authenticated user " + username);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} else {
				System.out.println("Blacklisted token, unauthenticated user");
			}

			// It is not compelling necessary to load the use details from the
			// database. You could also store the information
			// in the token and read it from it. It's up to you ;)

		}

		chain.doFilter(request, response);
	}
}