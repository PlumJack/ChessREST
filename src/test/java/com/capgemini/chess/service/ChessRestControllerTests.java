package com.capgemini.chess.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.chess.controller.ChessRestController;
import com.capgemini.chess.service.to.UserStatsTO;

@RunWith(MockitoJUnitRunner.class)
public class ChessRestControllerTests {
	
	@Mock
	private UserServiceFacade userServiceFacade;
	
	@InjectMocks
	private ChessRestController chessRestController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(chessRestController).build();
	}
	
	@Test
	public void shouldGetUserStatsByLogin() throws Exception {
		//given
		UserStatsTO userStatsTO = createUserStats(5);
		Mockito.when(userServiceFacade.getStats(Mockito.anyString())).thenReturn(userStatsTO);
		//when
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/chess/stats?login=login5"));
		//then
		response.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("level").value(userStatsTO.getLevel()))
				.andExpect(MockMvcResultMatchers.jsonPath("position").value(userStatsTO.getPosition()))
				.andExpect(MockMvcResultMatchers.jsonPath("points").value(userStatsTO.getPoints()))
				.andExpect(MockMvcResultMatchers.jsonPath("gamesPlayed").value(userStatsTO.getGamesPlayed()))
				.andExpect(MockMvcResultMatchers.jsonPath("gamesWon").value(userStatsTO.getGamesWon()))
				.andExpect(MockMvcResultMatchers.jsonPath("gamesDrawn").value(userStatsTO.getGamesDrawn()))
				.andExpect(MockMvcResultMatchers.jsonPath("gamesLost").value(userStatsTO.getGamesLost()));
	}
	
	@Test
	public void shouldGetStatus200WhenRegisterMatch() throws Exception {
		// given
		String json = "{\"id\":null,\"hostPlayerId\":1,\"guestPlayerId\":2,\"gameResult\":\"HOST_WON\"}";
		// when
		ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.post("/chess/registerMatch").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json.getBytes()));
		// then
		response.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void shouldGetStatus200WhenUpdateUserStats() throws Exception {
		// given
		String json = "{\"login\":\"login1\",\"oldPassword\":\"password1\",\"newPassword\":\"password4\","
				+ "\"name\":\"name4\",\"surname\":\"surname4\",\"email\":\"email4@email.pl\","
				+ "\"aboutMe\":\"aboutMe4\",\"lifeMotto\":\"lifeMotto4\"}";
		// when
		ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.post("/chess/update").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json.getBytes()));
		// then
		response.andExpect(MockMvcResultMatchers.status().isOk());
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
