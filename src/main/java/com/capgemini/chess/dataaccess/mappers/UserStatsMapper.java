package com.capgemini.chess.dataaccess.mappers;

import com.capgemini.chess.dataaccess.entities.UserStatsEntity;
import com.capgemini.chess.service.to.UserStatsTO;

public class UserStatsMapper {

	public static UserStatsEntity map(UserStatsTO to) {
		if(to != null){
			UserStatsEntity entity = new UserStatsEntity();
			entity.setLevel(to.getLevel());
			entity.setPosition(to.getPosition());
			entity.setPoints(to.getPoints());
			entity.setGamesPlayed(to.getGamesPlayed());
			entity.setGamesWon(to.getGamesWon());
			entity.setGamesDrawn(to.getGamesDrawn());
			entity.setGamesLost(to.getGamesLost());
			return entity;	
		}
		return null;
	}

	public static UserStatsTO map(UserStatsEntity entity) {
		if(entity != null){
			UserStatsTO to = new UserStatsTO();
			to.setLevel(entity.getLevel());
			to.setPosition(entity.getPosition());
			to.setPoints(entity.getPoints());
			to.setGamesPlayed(entity.getGamesPlayed());
			to.setGamesWon(entity.getGamesWon());
			to.setGamesDrawn(entity.getGamesDrawn());
			to.setGamesLost(entity.getGamesLost());
			return to;
		}
		return null;
	}

}