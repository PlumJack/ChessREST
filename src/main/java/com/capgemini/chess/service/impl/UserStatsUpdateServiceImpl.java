package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.UserStatsCalculationService;
import com.capgemini.chess.service.UserStatsReaderService;
import com.capgemini.chess.service.UserStatsUpdateService;
import com.capgemini.chess.service.to.MatchTO;
import com.capgemini.chess.service.to.UserStatsTO;

@Service
public class UserStatsUpdateServiceImpl implements UserStatsUpdateService {

	private UserStatsReaderService userStatsReaderService = null;
	private UserStatsCalculationService userStatsCalculationService = null;
	private UserDao userDao = null;
		
	@Autowired
	public UserStatsUpdateServiceImpl(UserStatsReaderService userStatsReaderService,
			UserStatsCalculationService userStatsCalculationService, UserDao userDao) {
		this.userStatsReaderService = userStatsReaderService;
		this.userStatsCalculationService = userStatsCalculationService;
		this.userDao = userDao;
	}

	@Override
	public void updateStats(MatchTO matchto) throws UserProfileValidationException {
		UserStatsTO hostStats = userStatsReaderService.getStats(matchto.getHostPlayerId());
		UserStatsTO guestStats = userStatsReaderService.getStats(matchto.getGuestPlayerId());
		
		switch(matchto.getGameResult()){
		case HOST_WON:
			hostStats = userStatsCalculationService.addWin(hostStats);
			guestStats = userStatsCalculationService.addLoss(guestStats);
		case GUEST_WON:
			hostStats = userStatsCalculationService.addLoss(hostStats);
			guestStats = userStatsCalculationService.addWin(guestStats);
		case DRAW:
			hostStats = userStatsCalculationService.addDraw(hostStats);
			guestStats = userStatsCalculationService.addDraw(guestStats);
		}
		
		userDao.updateUserStats(matchto.getHostPlayerId(), hostStats);
		userDao.updateUserStats(matchto.getGuestPlayerId(), guestStats);
		userDao.updatePositions();
	}

}
