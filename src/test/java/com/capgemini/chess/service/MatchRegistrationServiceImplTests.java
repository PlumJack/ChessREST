package com.capgemini.chess.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.dataaccess.MatchDao;
import com.capgemini.chess.dataaccess.enums.GameResult;
import com.capgemini.chess.exception.MatchExistsInDatabaseException;
import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.impl.MatchRegistrationServiceImpl;
import com.capgemini.chess.service.to.MatchTO;

@RunWith(MockitoJUnitRunner.class)
public class MatchRegistrationServiceImplTests {

	@Mock
	private MatchDao matchDao = null;
	
	@Mock
	private MatchValidationService matchValidationService = null;
	
	@Mock
	private UserProfileValidationService userProfileValidationService = null;
	
	@Mock
	private UserStatsUpdateService userStatsUpdateService = null;
	
	private MatchRegistrationService matchRegistrationService;
	
	MatchTO okMatch;
	MatchTO invalidHostId;
	MatchTO invalidGuestId;
	MatchTO okMatchWithId;
	
	@Before
	public void setUp() throws UserProfileValidationException, MatchExistsInDatabaseException {
		matchRegistrationService = new MatchRegistrationServiceImpl(matchDao, matchValidationService,
				userProfileValidationService, userStatsUpdateService);

		okMatch = createMatchTO(1L,2L,GameResult.HOST_WON);
		invalidHostId = createMatchTO(5L,2L,GameResult.HOST_WON);
		invalidGuestId = createMatchTO(1L,5L,GameResult.HOST_WON);
		okMatchWithId = createMatchTO(1L,2L,GameResult.HOST_WON);
		okMatchWithId.setId(1L);

		Mockito.doThrow(UserProfileValidationException.class).when(userProfileValidationService).validateId(5L);
		Mockito.when(matchDao.save(okMatch)).thenReturn(okMatchWithId);
	}
	
	private MatchTO createMatchTO(Long hostId, Long guestId, GameResult result){
		MatchTO match = new MatchTO();
		match.setHostPlayerId(hostId);
		match.setGuestPlayerId(guestId);
		match.setGameResult(result);
		return match;
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void shouldNotThrowExceptionWhenValidIds() throws UserProfileValidationException, MatchValidationException, MatchExistsInDatabaseException {
		//given
		MatchTO matchToRegister = okMatch;
		//when
		matchRegistrationService.registerNewMatch(matchToRegister);
		//then		
	}
	
	@Test
	public void shouldThrowExceptionWhenInvalidHostId() throws UserProfileValidationException, MatchValidationException, MatchExistsInDatabaseException {
		//given
		MatchTO matchToRegister = invalidHostId;
		//when
		thrown.expect(UserProfileValidationException.class);
		matchRegistrationService.registerNewMatch(matchToRegister);
		//then			
	}
	
	@Test
	public void shouldThrowExceptionWhenInvalidGuestId() throws UserProfileValidationException, MatchValidationException, MatchExistsInDatabaseException {
		//given
		MatchTO matchToRegister = invalidGuestId;
		//when
		thrown.expect(UserProfileValidationException.class);
		matchRegistrationService.registerNewMatch(matchToRegister);
		//then			
	}

	
}
