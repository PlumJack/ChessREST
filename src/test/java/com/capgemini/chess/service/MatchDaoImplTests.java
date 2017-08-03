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

import com.capgemini.chess.dataaccess.MatchDao;
import com.capgemini.chess.dataaccess.dao.impl.MatchDaoImpl;
import com.capgemini.chess.dataaccess.enums.GameResult;
import com.capgemini.chess.exception.MatchExistsInDatabaseException;
import com.capgemini.chess.service.to.MatchTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MatchDaoImplTests {
	
	private static boolean setUpIsDone = false;

	@Autowired
	private MatchDao matchDao;

	@Configuration
	static class TestContextConfiguration {
		@Bean
		public MatchDao matchDao() {
			return new MatchDaoImpl();
		}
	}
	
	@Before
    public void setUp() throws MatchExistsInDatabaseException {
		if (setUpIsDone) {
	        return;
	    }
		MatchTO match1 = new MatchTO();
		match1.setHostPlayerId(1L);
		match1.setGuestPlayerId(2L);
		match1.setGameResult(GameResult.HOST_WON);

		matchDao.save(match1);
		
		setUpIsDone = true;
	}
	
	
	@Test
	public void shouldSaveMatch() throws MatchExistsInDatabaseException {
		//given
		MatchTO match2 = new MatchTO();
		match2.setHostPlayerId(3L);
		match2.setGuestPlayerId(4L);
		match2.setGameResult(GameResult.DRAW);
		//when
		MatchTO savedMatch = matchDao.save(match2);
		//then
		assertEquals(new Long(2), savedMatch.getId());
	}

}
