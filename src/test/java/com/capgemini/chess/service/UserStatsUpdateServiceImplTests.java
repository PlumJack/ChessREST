package com.capgemini.chess.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.dataaccess.enums.GameResult;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.impl.UserStatsUpdateServiceImpl;
import com.capgemini.chess.service.to.MatchTO;
import com.capgemini.chess.service.to.UserStatsTO;

@RunWith(MockitoJUnitRunner.class)
public class UserStatsUpdateServiceImplTests {
	
	@Mock
	private UserStatsReaderService userStatsReaderService = null;
	
	@Mock
	private UserStatsCalculationService userStatsCalculationService = null;
	
	@Mock
	private UserDao userDao = null;
	
	private UserStatsUpdateService userStatsUpdateService;
	
	@Before
	    public void setUp() throws UserProfileValidationException {
			userStatsUpdateService = new UserStatsUpdateServiceImpl(userStatsReaderService,
					userStatsCalculationService, userDao);
			
			BDDMockito.given(userStatsReaderService.getStats(1L)).willReturn(createUserStats(2));
			BDDMockito.given(userStatsReaderService.getStats(2L)).willReturn(createUserStats(2));
			BDDMockito.given(userStatsReaderService.getStats(5L)).willThrow(UserProfileValidationException.class);

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
	
	private MatchTO createMatchTO(Long hostId, Long guestId, GameResult result){
		MatchTO match = new MatchTO();
		match.setHostPlayerId(hostId);
		match.setGuestPlayerId(guestId);
		match.setGameResult(result);
		return match;
	}
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void shouldNotThrowExceptionWhenValidData() throws UserProfileValidationException{
		//given
		MatchTO validMatch = createMatchTO(1L,2L,GameResult.HOST_WON);
		//when
		userStatsUpdateService.updateStats(validMatch);
		//then
	}
	
	@Test
	public void shouldThrowExceptionWhenInValidData() throws UserProfileValidationException{
		//given
		MatchTO validMatch = createMatchTO(1L,5L,GameResult.HOST_WON);
		//when
		thrown.expect(UserProfileValidationException.class);
		userStatsUpdateService.updateStats(validMatch);
		//then
	}

}
