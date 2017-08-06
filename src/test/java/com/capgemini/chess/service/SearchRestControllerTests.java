package com.capgemini.chess.service;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.chess.controller.SearchRestController;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatsTO;

@RunWith(MockitoJUnitRunner.class)
public class SearchRestControllerTests {

	@Mock
	private UserProfileSearchService userProfileSearchService;
	
	@InjectMocks
	private SearchRestController searchRestController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(searchRestController).build();
	}
	
	@Test
	public void shouldSearchByName() throws Exception {
		//given
		ArrayList<UserProfileTO> list = new ArrayList<UserProfileTO>();
		list.add(createUserProfile(2));
		Mockito.when(userProfileSearchService.getUserProfilesByName("name2")).thenReturn(list);
		//when
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/search/getByName?name=name2"));
		//then
		response.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("[0].name").value("name2"));
	}
	
	@Test
	public void shouldSearchBySurname() throws Exception {
		//given
		ArrayList<UserProfileTO> list = new ArrayList<UserProfileTO>();
		list.add(createUserProfile(2));
		Mockito.when(userProfileSearchService.getUserProfilesBySurname("surname2")).thenReturn(list);
		//when
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/search/getBySurname?surname=surname2"));
		//then
		response.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("[0].surname").value("surname2"));
	}
	
	@Test
	public void shouldSearchByNameAndSurname() throws Exception {
		//given
		ArrayList<UserProfileTO> list = new ArrayList<UserProfileTO>();
		list.add(createUserProfile(2));
		Mockito.when(userProfileSearchService.getUserProfilesByNameAndSurname("name2", "surname2")).thenReturn(list);
		//when
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/search/getByNameSurname?name=name2&surname=surname2"));
		//then
		response.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("[0].name").value("name2"))
				.andExpect(MockMvcResultMatchers.jsonPath("[0].surname").value("surname2"));
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
