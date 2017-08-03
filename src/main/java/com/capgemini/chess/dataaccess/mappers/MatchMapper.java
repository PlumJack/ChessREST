package com.capgemini.chess.dataaccess.mappers;

import com.capgemini.chess.dataaccess.entities.MatchEntity;
import com.capgemini.chess.service.to.MatchTO;

public class MatchMapper {
	
	public static MatchTO map(MatchEntity matchEntity) {
		if (matchEntity != null) {
			MatchTO matchTO = new MatchTO();
			matchTO.setId(matchEntity.getId());
			matchTO.setHostPlayerId(matchEntity.getHostPlayerId());
			matchTO.setGuestPlayerId(matchEntity.getGuestPlayerId());
			matchTO.setGameResult(matchEntity.getGameResult());
			return matchTO;
		}
		return null;
	}

	public static MatchEntity map(MatchTO matchTO) {
		if (matchTO != null) {
			MatchEntity matchEntity = new MatchEntity();
			matchEntity.setId(matchTO.getId());
			matchEntity.setHostPlayerId(matchTO.getHostPlayerId());
			matchEntity.setGuestPlayerId(matchTO.getGuestPlayerId());
			matchEntity.setGameResult(matchTO.getGameResult());
			return matchEntity;
		}
		return null;
	}

}
