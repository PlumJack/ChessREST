package com.capgemini.chess.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.exception.InvalidPasswordException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.impl.UserProfileUpdateServiceImpl;
import com.capgemini.chess.service.to.UserUpdateTO;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileUpdateServiceImplTests {
	
	@Mock
	private UserProfileValidationService userProfileValidationService = null;
	
	@Mock
	private PasswordValidationService passwordValidationService = null;
	
	@Mock
	private UserDao userDao = null;
	
	private UserProfileUpdateService userProfileUpdateService;
	
	private UserUpdateTO invalidLoginUserUpdate;
	private UserUpdateTO invalidPasswordUserUpdate;
	private UserUpdateTO validPasswordUserUpdate;
	
	
	@Before
    public void setUp() throws UserProfileValidationException, InvalidPasswordException {
		userProfileUpdateService = new UserProfileUpdateServiceImpl(userProfileValidationService,
				passwordValidationService, userDao);
		
		invalidLoginUserUpdate = createUserUpdateTO(7,5,5);
		invalidPasswordUserUpdate = createUserUpdateTO(7,2,3);
		validPasswordUserUpdate = createUserUpdateTO(7,2,2);
		
		Mockito.doThrow(UserProfileValidationException.class).when(userProfileValidationService).validateLogin(invalidLoginUserUpdate.getLogin());
		Mockito.doThrow(InvalidPasswordException.class).when(passwordValidationService).validatePassword(invalidPasswordUserUpdate);

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
	public void shouldThrowExceptionWhenInvalidLogin() throws InvalidPasswordException, UserProfileValidationException{
		//given
		UserUpdateTO userUpdateTO = invalidLoginUserUpdate;
		//when
		thrown.expect(UserProfileValidationException.class);
		userProfileUpdateService.updateUser(userUpdateTO);
		//then
				
	}
	
	@Test
	public void shouldThrowExceptionWhenInvalidPassword() throws InvalidPasswordException, UserProfileValidationException{
		//given
		UserUpdateTO userUpdateTO = invalidPasswordUserUpdate;
		//when
		thrown.expect(InvalidPasswordException.class);
		userProfileUpdateService.updateUser(userUpdateTO);
		//then
				
	}
	
	@Test
	public void shouldNotThrowExceptionWhenValidPassword() throws InvalidPasswordException, UserProfileValidationException{
		//given
		UserUpdateTO userUpdateTO = validPasswordUserUpdate;
		//when
		userProfileUpdateService.updateUser(userUpdateTO);
		//then
		
	}

}
