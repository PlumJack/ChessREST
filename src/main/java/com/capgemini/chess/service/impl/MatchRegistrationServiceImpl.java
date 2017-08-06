package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.MatchDao;
import com.capgemini.chess.exception.MatchExistsInDatabaseException;
import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.MatchValidationService;
import com.capgemini.chess.service.MatchRegistrationService;
import com.capgemini.chess.service.UserProfileValidationService;
import com.capgemini.chess.service.UserStatsUpdateService;
import com.capgemini.chess.service.to.MatchTO;

@Service
public class MatchRegistrationServiceImpl implements MatchRegistrationService {

	private MatchDao matchDao = null;
	private MatchValidationService matchValidationService = null;
	private UserProfileValidationService userProfileValidationService = null;
	private UserStatsUpdateService userStatsUpdateService = null;
	
	@Autowired
	public MatchRegistrationServiceImpl(MatchDao matchDao, MatchValidationService matchValidationService,
			UserProfileValidationService userProfileValidationService, UserStatsUpdateService userStatsUpdateService) {
		this.matchDao = matchDao;
		this.matchValidationService = matchValidationService;
		this.userProfileValidationService = userProfileValidationService;
		this.userStatsUpdateService = userStatsUpdateService;
	}

	@Override
	public void registerNewMatch(MatchTO newMatch) throws UserProfileValidationException, MatchValidationException, MatchExistsInDatabaseException {
		
		userProfileValidationService.validateId(newMatch.getHostPlayerId());
		userProfileValidationService.validateId(newMatch.getGuestPlayerId());
		
		MatchTO matchTO = matchDao.save(newMatch);
		matchValidationService.validateMatch(matchTO.getId());
		
		//userStatsUpdateService.updateStats(newMatch);
		userStatsUpdateService.updateStats(matchTO);
	}

}
