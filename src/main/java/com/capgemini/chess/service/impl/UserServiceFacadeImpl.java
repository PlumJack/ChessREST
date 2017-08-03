package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.exception.InvalidPasswordException;
import com.capgemini.chess.exception.MatchExistsInDatabaseException;
import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.exception.UserProfileExistsInDatabaseException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.MatchRegistrationService;
import com.capgemini.chess.service.UserProfileUpdateService;
import com.capgemini.chess.service.UserServiceFacade;
import com.capgemini.chess.service.UserStatsReaderService;
import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.service.to.MatchTO;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatsTO;
import com.capgemini.chess.service.to.UserUpdateTO;

@Service
public class UserServiceFacadeImpl implements UserServiceFacade {
	
	
	private UserDao userDao = null;
	private UserStatsReaderService userStatsReaderService = null;
	private UserProfileUpdateService userProfileUpdateService = null;
	private MatchRegistrationService matchRegistrationService = null;

	@Autowired
	public UserServiceFacadeImpl(UserDao userDao, UserStatsReaderService userStatsReaderService,
			UserProfileUpdateService userProfileUpdateService, MatchRegistrationService matchRegistrationService) {
		this.userDao = userDao;
		this.userStatsReaderService = userStatsReaderService;
		this.userProfileUpdateService = userProfileUpdateService;
		this.matchRegistrationService = matchRegistrationService;
	}

	public UserProfileTO saveUserProfile(UserProfileTO userProfileTo) throws UserProfileExistsInDatabaseException{
		return userDao.save(userProfileTo);
	}
	
	public UserStatsTO getStats(String login) throws UserProfileValidationException {
		return userStatsReaderService.getStats(login);
	}

	@Override
	public void updateUserProfile(UserUpdateTO userUpdateTO) throws UserProfileValidationException, InvalidPasswordException {
		userProfileUpdateService.updateUser(userUpdateTO);
		
	}

	@Override
	public void registerNewMatch(MatchTO matchTO)
			throws UserProfileValidationException, MatchValidationException, MatchExistsInDatabaseException {
		matchRegistrationService.registerNewMatch(matchTO);
		
	}
	
}
