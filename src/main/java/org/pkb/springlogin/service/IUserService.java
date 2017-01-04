package org.pkb.springlogin.service;

import java.text.ParseException;

import org.pkb.springlogin.dto.UserDTO;

import javassist.NotFoundException;

public interface IUserService {

	void registerUser(UserDTO userDTO) throws ParseException;

	boolean verifyUserAccount(String email) throws NotFoundException;

}
