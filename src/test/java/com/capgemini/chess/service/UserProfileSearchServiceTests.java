package com.capgemini.chess.service;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.service.impl.UserProfileSearchServiceImpl;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatsTO;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileSearchServiceTests {

	@Mock
	private UserDao userDao = null;
	
	private UserProfileSearchService userProfileSearchService;
	
	List<UserProfileTO> users;
	
	@Before
	public void setUp(){
		userProfileSearchService = new UserProfileSearchServiceImpl(userDao);
		users = new ArrayList<UserProfileTO>();
		users.add(createUserProfile(1));
		users.add(createUserProfile(2));
		users.add(createUserProfile(3));
		users.get(0).setName("name2");
	}

	@Test
	public void shouldSearchByName() throws Exception {
		//given
		Mockito.when(userDao.getAll()).thenReturn(users);
		List<UserProfileTO> searchedUsers;
		//when
		searchedUsers = userProfileSearchService.getUserProfilesByName("name2");
		//then
		assertTrue(searchedUsers.size()==2);
		assertEquals("name2", searchedUsers.get(0).getName());
		assertEquals("name2", searchedUsers.get(1).getName());
	}
	
	@Test
	public void shouldSearchBySurname() throws Exception {
		//given
		Mockito.when(userDao.getAll()).thenReturn(users);
		List<UserProfileTO> searchedUsers;
		//when
		searchedUsers = userProfileSearchService.getUserProfilesBySurname("surname2");
		//then
		assertTrue(searchedUsers.size()==1);
		assertEquals("surname2", searchedUsers.get(0).getSurname());
	}
	
	@Test
	public void shouldSearchByNameAndSurname() throws Exception {
		//given
		Mockito.when(userDao.getAll()).thenReturn(users);
		List<UserProfileTO> searchedUsers;
		//when
		searchedUsers = userProfileSearchService.getUserProfilesByNameAndSurname("name2","surname2");
		//then
		assertTrue(searchedUsers.size()==1);
		assertEquals("name2", searchedUsers.get(0).getName());
		assertEquals("surname2", searchedUsers.get(0).getSurname());
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
}
