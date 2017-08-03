package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.exception.InvalidPasswordException;
import com.capgemini.chess.service.PasswordValidationService;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserUpdateTO;

@Service
public class PasswordValidationServiceImpl implements PasswordValidationService {

	private UserDao userDao = null;
	
	@Autowired
	public PasswordValidationServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void validatePassword(UserUpdateTO userUpdateTO) throws InvalidPasswordException {
		
		UserProfileTO userProfileTo =  userDao.findByLogin(userUpdateTO.getLogin());
		if(!userUpdateTO.getOldPassword().equals(userProfileTo.getPassword())){
			throw new InvalidPasswordException("Wrong password.");
		}
	}

}
