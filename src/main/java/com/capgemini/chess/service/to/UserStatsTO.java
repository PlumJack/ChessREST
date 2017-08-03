package com.capgemini.chess.service.to;

public class UserStatsTO {
	
	private int level;
	private int position;
	private int points;
	private int gamesPlayed;
	private int gamesWon;
	private int gamesDrawn;
	private int gamesLost;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	public int getGamesWon() {
		return gamesWon;
	}
	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}
	public int getGamesDrawn() {
		return gamesDrawn;
	}
	public void setGamesDrawn(int gamesDrawn) {
		this.gamesDrawn = gamesDrawn;
	}
	public int getGamesLost() {
		return gamesLost;
	}
	public void setGamesLost(int gamesLost) {
		this.gamesLost = gamesLost;
	}
}
