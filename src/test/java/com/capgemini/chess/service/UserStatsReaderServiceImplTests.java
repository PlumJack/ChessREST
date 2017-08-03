package com.capgemini.chess.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.impl.UserStatsReaderServiceImpl;
import com.capgemini.chess.service.to.UserStatsTO;

@RunWith(MockitoJUnitRunner.class)
public class UserStatsReaderServiceImplTests {

	@InjectMocks
	private UserStatsReaderServiceImpl userStatsReaderServiceImpl;
	
	@Mock
	private UserDao userDao;
	
	@Mock
	private UserProfileValidationService userProfileValidationService;
	
	
	@Before
	public void setup() throws UserProfileValidationException {
		UserStatsTO userStatsTo = new UserStatsTO();
		userStatsTo.setGamesWon(3);
		Mockito.when(userStatsReaderServiceImpl.getStats("login123")).thenReturn(userStatsTo);
		Mockito.when(userStatsReaderServiceImpl.getStats(123L)).thenReturn(userStatsTo);
	}
	
	@Test
	public void shouldGetStatsWithLogin() throws UserProfileValidationException {
		//given
		//when
		UserStatsTO returnedUserStatsTo = userStatsReaderServiceImpl.getStats("login123");
		//then
		assertEquals(3, returnedUserStatsTo.getGamesWon());
	}

	@Test
	public void shouldGetStatsWithId() throws UserProfileValidationException {
		//given
		//when
		UserStatsTO returnedUserStatsTo = userStatsReaderServiceImpl.getStats(123L);
		//then
		assertEquals(3, returnedUserStatsTo.getGamesWon());
	}
}
