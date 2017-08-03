package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.exception.InvalidPasswordException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.PasswordValidationService;
import com.capgemini.chess.service.UserProfileUpdateService;
import com.capgemini.chess.service.UserProfileValidationService;
import com.capgemini.chess.service.to.UserUpdateTO;

@Service
public class UserProfileUpdateServiceImpl implements UserProfileUpdateService {
	
	private UserProfileValidationService userProfileValidationService = null;
	private PasswordValidationService passwordValidationService = null;
	private UserDao userDao = null;
	
	@Autowired
	public UserProfileUpdateServiceImpl(UserProfileValidationService userProfileValidationService,
			PasswordValidationService passwordValidationService, UserDao userDao) {
		this.userProfileValidationService = userProfileValidationService;
		this.passwordValidationService = passwordValidationService;
		this.userDao = userDao;
	}

	@Override
	public void updateUser(UserUpdateTO userUpdateTO) throws UserProfileValidationException, InvalidPasswordException {
		userProfileValidationService.validateLogin(userUpdateTO.getLogin());
		passwordValidationService.validatePassword(userUpdateTO);
		userDao.updateUserProfile(userUpdateTO);
	}

}
