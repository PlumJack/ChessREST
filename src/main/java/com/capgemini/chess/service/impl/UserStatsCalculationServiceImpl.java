package com.capgemini.chess.service.impl;

import org.springframework.stereotype.Service;

import com.capgemini.chess.service.UserStatsCalculationService;
import com.capgemini.chess.service.to.UserStatsTO;

@Service
public class UserStatsCalculationServiceImpl implements UserStatsCalculationService {

	@Override
	public UserStatsTO addWin(UserStatsTO userStatsTO) {
		UserStatsTO newStats = copyStats(userStatsTO);
		newStats.setGamesPlayed(userStatsTO.getGamesPlayed()+1);
		newStats.setGamesWon(userStatsTO.getGamesWon()+1);
		newStats.setPoints(userStatsTO.getPoints()+10);
		newStats.setLevel(calculateLevel(newStats));
		return newStats;
	}

	@Override
	public UserStatsTO addDraw(UserStatsTO userStatsTO) {
		UserStatsTO newStats = copyStats(userStatsTO);
		newStats.setGamesPlayed(userStatsTO.getGamesPlayed()+1);
		newStats.setGamesDrawn(userStatsTO.getGamesDrawn()+1);
		newStats.setLevel(calculateLevel(newStats));
		return newStats;
	}

	@Override
	public UserStatsTO addLoss(UserStatsTO userStatsTO) {
		UserStatsTO newStats = copyStats(userStatsTO);
		newStats.setGamesPlayed(userStatsTO.getGamesPlayed()+1);
		newStats.setGamesLost(userStatsTO.getGamesLost()+1);
		newStats.setPoints(userStatsTO.getPoints()-10);
		newStats.setLevel(calculateLevel(newStats));
		return newStats;
	}
	
	private int calculateLevel(UserStatsTO u){
		double nominator = 10 * u.getGamesWon() + 5 * u.getGamesDrawn();
		double denominator = u.getGamesPlayed();
		return (int)(nominator/denominator);
	}
	
	private UserStatsTO copyStats(UserStatsTO statsToCopy){
		UserStatsTO copy = new UserStatsTO();
		copy.setLevel(statsToCopy.getLevel());
		copy.setPosition(statsToCopy.getPosition());
		copy.setPoints(statsToCopy.getPoints());
		copy.setGamesPlayed(statsToCopy.getGamesPlayed());
		copy.setGamesWon(statsToCopy.getGamesWon());
		copy.setGamesDrawn(statsToCopy.getGamesDrawn());
		copy.setGamesLost(statsToCopy.getGamesLost());
		return copy;
	}

}
