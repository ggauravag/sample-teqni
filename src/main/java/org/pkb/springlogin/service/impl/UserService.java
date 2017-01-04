package org.pkb.springlogin.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.pkb.springlogin.dao.UserDAO;
import org.pkb.springlogin.dto.UserDTO;
import org.pkb.springlogin.model.User;
import org.pkb.springlogin.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public void registerUser(UserDTO userDTO) throws ParseException {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);

		user.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(userDTO.getDateOfBirth()));

		user.setUserRole(userDAO.getRole(userDTO.getUserRole()));
		try {
			userDAO.createUser(user);
		} catch (Exception e) {
			throw new RuntimeException("Username is already associated with an existing user");
		}
	}

	@Override
	public boolean verifyUserAccount(String email) throws NotFoundException {
		User user = this.userDAO.getUserByEmail(email);
		if (user == null)
			throw new NotFoundException("No user exists with given email");
		if (user.getAccountVerified())
			return false;
		user.setAccountVerified(true);
		this.userDAO.updateUser(user);
		return true;
	}

}
