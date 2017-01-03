package org.pkb.springlogin.service;

import java.text.ParseException;

import org.pkb.springlogin.dto.UserDTO;

public interface IUserService {

	void registerUser(UserDTO userDTO) throws ParseException;

}
