package com.capgemini.chess.service.to;

import com.capgemini.chess.dataaccess.enums.GameResult;

public class MatchTO {
	
	private Long id;
	private Long hostPlayerId;
	private Long guestPlayerId;
	private GameResult gameResult;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getHostPlayerId() {
		return hostPlayerId;
	}
	public void setHostPlayerId(Long hostPlayerId) {
		this.hostPlayerId = hostPlayerId;
	}
	public Long getGuestPlayerId() {
		return guestPlayerId;
	}
	public void setGuestPlayerId(Long guestPlayerId) {
		this.guestPlayerId = guestPlayerId;
	}
	public GameResult getGameResult() {
		return gameResult;
	}
	public void setGameResult(GameResult gameResult) {
		this.gameResult = gameResult;
	}
	
}
