package com.capgemini.chess.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.dataaccess.dao.impl.UserDaoImpl;
import com.capgemini.chess.exception.UserProfileExistsInDatabaseException;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatsTO;
import com.capgemini.chess.service.to.UserUpdateTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserDaoImplTests {
	
	private static boolean setUpIsDone = false;
	
	@Autowired
	private UserDao userDao;

	@Configuration
	static class TestContextConfiguration {
		@Bean
		public UserDao userDao() {
			return new UserDaoImpl();
		}
	}
	
	@Before
    public void setUp() throws UserProfileExistsInDatabaseException {
		if (setUpIsDone) {
	        return;
	    }
		UserProfileTO user1 = createUserProfile(1);
		UserProfileTO user2 = createUserProfile(2);
		UserProfileTO user3 = createUserProfile(3);
		user1 = userDao.save(user1);
		user2 = userDao.save(user2);
		user3 = userDao.save(user3);

		setUpIsDone = true;
	}
	
	private UserProfileTO createUserProfile(int i){
		UserProfileTO userProfileTO = new UserProfileTO();
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

	
	@Test
	public void shouldGetProperStatsWithLogin() {
		//given
		//when
		UserStatsTO stats1 = userDao.getStats("login1");
		UserStatsTO stats3 = userDao.getStats("login3");
		//then
		assertEquals(1, stats1.getGamesWon());
		assertEquals(3, stats3.getGamesWon());
	}
	
	@Test
	public void shouldGetProperStatsWithId() {
		//given
		//when
		UserStatsTO stats1 = userDao.getStats(1L);
		UserStatsTO stats3 = userDao.getStats(3L);
		//then
		assertEquals(1, stats1.getGamesWon());
		assertEquals(3, stats3.getGamesWon());
	}
	
	@Test
	public void shouldUpdateUserProfile(){
		//given
		UserUpdateTO userUpdateTO = new UserUpdateTO();
		userUpdateTO.setLogin("login2");
		userUpdateTO.setLifeMotto("qwerty");
		//when
		userDao.updateUserProfile(userUpdateTO);
		//then
		UserProfileTO updatedUser = userDao.findByLogin("login2");	
		assertEquals("qwerty", updatedUser.getLifeMotto());
	}
	
	@Test
	public void shouldUpdateUserStats() {
		//given
		UserStatsTO newStats = new UserStatsTO();
		newStats.setLevel(0);
		newStats.setPosition(2);
		newStats.setPoints(0);
		newStats.setGamesPlayed(6);
		newStats.setGamesWon(2);
		newStats.setGamesDrawn(2);
		newStats.setGamesLost(2);
		
		//when
		userDao.updateUserStats(2L, newStats);
		
		//then
		UserProfileTO updatedUser = userDao.findById(2L);	
		assertEquals(2, updatedUser.getUserStatsTO().getGamesDrawn());
	}

}
