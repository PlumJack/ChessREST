package com.capgemini.chess.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.impl.UserServiceFacadeImpl;
import com.capgemini.chess.service.impl.UserStatsReaderServiceImpl;
import com.capgemini.chess.service.to.UserStatsTO;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceFacadeImplTests {

	@InjectMocks
	private UserServiceFacadeImpl userServiceFacadeImpl;
	
	@Mock
	private UserStatsReaderServiceImpl userStatsReaderServiceImpl;
	
	@Test
	public void test() throws UserProfileValidationException {
		//given
		UserStatsTO userStatsTo = new UserStatsTO();
		userStatsTo.setGamesPlayed(5);
		userStatsTo.setGamesWon(3);
		userStatsTo.setGamesLost(2);
		Mockito.when(userStatsReaderServiceImpl.getStats("login123")).thenReturn(userStatsTo);
		//when
		UserStatsTO returnedUserStatsTo = userStatsReaderServiceImpl.getStats("login123");
		//then
		assertEquals(3, returnedUserStatsTo.getGamesWon());
	}
	
}
