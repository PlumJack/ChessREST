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
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.impl.UserProfileValidationServiceImpl;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatsTO;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileValidationServiceImplTests {
		
	private UserProfileValidationService userProfileValidationService;
	
	@Mock
	private UserDao userDao;
	
		
	@Before
	public void setUp() {
		userProfileValidationService = new UserProfileValidationServiceImpl(userDao);
	
		BDDMockito.given(userDao.findById(2L)).willReturn(createUserProfile(2));
		BDDMockito.given(userDao.findById(5L)).willReturn(null);
		BDDMockito.given(userDao.findByLogin("login2")).willReturn(createUserProfile(2));
		BDDMockito.given(userDao.findByLogin("login5")).willReturn(null);
	}
	
	
	private UserProfileTO createUserProfile(int i){
		UserProfileTO userProfileTO = new UserProfileTO();
		userProfileTO.setId(new Long(i));
		userProfileTO.setLogin("login" + i);
		userProfileTO.setPassword("password" + i);
		userProfileTO.setName("name" + i);
		userProfileTO.setSurname("surname" + i);
		userProfileTO.setEmail("email" + i + "@email.pl");
		userProfileTO.setAboutMe("aboutMe" + i);
		userProfileTO.setLifeMotto("lifeMotto" + i);
		
		userProfileTO.setUserStatsTO(createUserStats(i));
		
		return userProfileTO;
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
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void shouldThrowExceptionWhenInvalidId() throws UserProfileValidationException {
		//given
		Long id = 5L;
		
		//when
		thrown.expect(UserProfileValidationException.class);
		userProfileValidationService.validateId(id);
		
		//then	
		
	}
	
	@Test
	public void shouldThrowExceptionWhenInvalidLogin() throws UserProfileValidationException {
		
		//given
		String login = "login5";
		
		//when
		thrown.expect(UserProfileValidationException.class);
		userProfileValidationService.validateLogin(login);
		
		//then
		
	}

	@Test
	public void shouldNotThrowExceptionWhenValidId() throws UserProfileValidationException {
		
		//given
		Long id = 2L;
				
		//when
		userProfileValidationService.validateId(id);
				
		//then		
		
	}

	@Test
	public void shouldNotThrowExceptionWhenValidLogin() throws UserProfileValidationException {
		
		//given
		String login = "login2";
				
		//when
		userProfileValidationService.validateLogin(login);
				
		//then
					
	}	

}
