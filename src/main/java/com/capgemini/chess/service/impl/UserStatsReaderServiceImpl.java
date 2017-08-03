package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.UserProfileValidationService;
import com.capgemini.chess.service.UserStatsReaderService;
import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.service.to.UserStatsTO;

@Service
public class UserStatsReaderServiceImpl implements UserStatsReaderService {

	
	private UserDao userDao = null;
	private UserProfileValidationService userProfileValidationService = null;
	
	@Autowired
	public UserStatsReaderServiceImpl(UserDao userDao, UserProfileValidationService userProfileValidationService) {
		this.userDao = userDao;
		this.userProfileValidationService = userProfileValidationService;
	}
	
	public UserStatsTO getStats(String login) throws UserProfileValidationException {
		userProfileValidationService.validateLogin(login);
		
		return userDao.getStats(login);
	}
	
	public UserStatsTO getStats(Long id) throws UserProfileValidationException {
		userProfileValidationService.validateId(id);
		
		return userDao.getStats(id);
	}
	
}
