package com.capgemini.chess.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.dataaccess.MatchDao;
import com.capgemini.chess.dataaccess.enums.GameResult;
import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.service.impl.MatchValidationServiceImpl;
import com.capgemini.chess.service.to.MatchTO;

@RunWith(MockitoJUnitRunner.class)
public class MatchValidationServiceImplTests {

	@Mock
	private MatchDao matchDao = null;
	
	private MatchValidationService matchValidationService;
	
	private MatchTO match1;
	
	@Before
	    public void setUp() {
			matchValidationService = new MatchValidationServiceImpl(matchDao);
			match1 = new MatchTO();
			match1.setHostPlayerId(1L);
			match1.setGuestPlayerId(2L);
			match1.setGameResult(GameResult.HOST_WON);
			BDDMockito.given(matchDao.findById(1L)).willReturn(match1);
			BDDMockito.given(matchDao.findById(2L)).willReturn(null);
		}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void shouldNotThrowExceptionWhenMatchExists() throws MatchValidationException {
		//given
		Long matchId = 1L;
		//when
		matchValidationService.validateMatch(matchId);
		//then
		
	}
	
	@Test
	public void shouldThrowExceptionWhenMatchDoesNotExist() throws MatchValidationException {
		//given
		Long matchId = 2L;
		//when
		thrown.expect(MatchValidationException.class);
		matchValidationService.validateMatch(matchId);
		//then
				
	}

}
