package com.capgemini.chess.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.service.impl.UserStatsCalculationServiceImpl;
import com.capgemini.chess.service.to.UserStatsTO;

@RunWith(MockitoJUnitRunner.class)
public class UserStatsCalculationServiceImplTests {

	private UserStatsCalculationService userStatsCalculationService;
		
	@Before
	public void setUp() {
		userStatsCalculationService = new UserStatsCalculationServiceImpl();

		//BDDMockito.given(userDao.findByLogin("login2")).willReturn(createUserProfile(2));

	}
	
	private UserStatsTO createUserStats(int i){
		UserStatsTO userStatsTO = new UserStatsTO();
		userStatsTO.setLevel(0);
		userStatsTO.setPosition(i);
		userStatsTO.setPoints(i*10-20);
		userStatsTO.setGamesPlayed(i+3);
		userStatsTO.setGamesWon(i);
		userStatsTO.setGamesDrawn(1);
		userStatsTO.setGamesLost(2);
		
		return userStatsTO;
	}
	
	
	@Test
	public void shouldProperlyAddWin() {
		//given
		UserStatsTO baseStats = createUserStats(2);
		//when
		UserStatsTO newStats = userStatsCalculationService.addWin(baseStats);
		//then
		assertEquals(baseStats.getGamesWon()+1, newStats.getGamesWon());
		assertEquals(baseStats.getGamesDrawn(), newStats.getGamesDrawn());
		assertEquals(baseStats.getGamesLost(), newStats.getGamesLost());
		assertEquals(baseStats.getPoints()+10, newStats.getPoints());
		assertEquals(5, newStats.getLevel());
	}
	
	@Test
	public void shouldProperlyAddDraw() {
		//given
		UserStatsTO baseStats = createUserStats(2);
		//when
		UserStatsTO newStats = userStatsCalculationService.addDraw(baseStats);
		//then
		assertEquals(baseStats.getGamesWon(), newStats.getGamesWon());
		assertEquals(baseStats.getGamesDrawn()+1, newStats.getGamesDrawn());
		assertEquals(baseStats.getGamesLost(), newStats.getGamesLost());
		assertEquals(baseStats.getPoints(), newStats.getPoints());
		assertEquals(5, newStats.getLevel());
	}
	
	@Test
	public void shouldProperlyAddLoss() {
		//given
		UserStatsTO baseStats = createUserStats(2);
		//when
		UserStatsTO newStats = userStatsCalculationService.addLoss(baseStats);
		//then
		assertEquals(baseStats.getGamesWon(), newStats.getGamesWon());
		assertEquals(baseStats.getGamesDrawn(), newStats.getGamesDrawn());
		assertEquals(baseStats.getGamesLost()+1, newStats.getGamesLost());
		assertEquals(baseStats.getPoints()-10, newStats.getPoints());
		assertEquals(4, newStats.getLevel());
	}

}
