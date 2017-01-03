package org.pkb.springlogin.authentication;

import java.util.Arrays;
import java.util.List;

import org.pkb.springlogin.dao.UserDAO;
import org.pkb.springlogin.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyDBAuthenticationService implements UserDetailsService {

	@Autowired
	private UserDAO userInfoDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		org.pkb.springlogin.model.User userInfo = userInfoDAO.findUserInfo(username);

		if (userInfo == null) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		List<Role> roles = Arrays.asList(userInfo.getUserRole());

		UserDetails userDetails = (UserDetails) new User(userInfo.getUserName(), //
				userInfo.getPassword(), roles);

		return userDetails;
	}

}