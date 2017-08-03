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
import com.capgemini.chess.exception.InvalidPasswordException;
import com.capgemini.chess.service.impl.PasswordValidationServiceImpl;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatsTO;
import com.capgemini.chess.service.to.UserUpdateTO;

@RunWith(MockitoJUnitRunner.class)
public class PasswordValidationServiceImplTests {

	private PasswordValidationService passwordValidationService;
	
	@Mock
	private UserDao userDao;
	
	@Before
    public void setUp() {
		passwordValidationService = new PasswordValidationServiceImpl(userDao);

		BDDMockito.given(userDao.findByLogin("login2")).willReturn(createUserProfile(2));
		//BDDMockito.given(userDao.findByLogin("login5")).willReturn(createUserProfile(5));

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

private UserUpdateTO createUserUpdateTO(int i, int iLogin, int iOldPassword){
	UserUpdateTO userUpdateTO = new UserUpdateTO();
	
	userUpdateTO.setLogin("login" + iLogin);
	userUpdateTO.setOldPassword("password" + iOldPassword);
	userUpdateTO.setNewPassword("password" + i);
	userUpdateTO.setName("name" + i);
	userUpdateTO.setSurname("surname" + i);
	userUpdateTO.setEmail("email" + i + "@email.pl");
	userUpdateTO.setAboutMe("aboutMe" + i);
	userUpdateTO.setLifeMotto("lifeMotto" + i);
	
	return userUpdateTO;
}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void shouldNotThrowExceptionWhenValidPassword() throws InvalidPasswordException{
		//given
		UserUpdateTO userUpdateTO = createUserUpdateTO(7,2,2);
		//when
		passwordValidationService.validatePassword(userUpdateTO);
		//then
		
	}
	
	@Test
	public void shouldThrowExceptionWhenInvalidPassword() throws InvalidPasswordException{
		//given
		UserUpdateTO userUpdateTO = createUserUpdateTO(7,2,4);
		//when
		thrown.expect(InvalidPasswordException.class);
		passwordValidationService.validatePassword(userUpdateTO);
		//then
				
	}

}
