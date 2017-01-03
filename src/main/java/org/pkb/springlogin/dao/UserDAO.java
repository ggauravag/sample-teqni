package org.pkb.springlogin.dao;

import org.pkb.springlogin.model.Role;
import org.pkb.springlogin.model.User;

public interface UserDAO {

	public User findUserInfo(String userName);

	public void createUser(User user);

	Role getRole(String name);
	
}