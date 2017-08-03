package com.capgemini.chess.service;

import com.capgemini.chess.service.to.UserStatsTO;

public interface UserStatsCalculationService {
	
	UserStatsTO addWin(UserStatsTO userStatsTO);
	UserStatsTO addDraw(UserStatsTO userStatsTO);
	UserStatsTO addLoss(UserStatsTO userStatsTO);
	
}
