package com.capgemini.chess.dataaccess;

import com.capgemini.chess.exception.MatchExistsInDatabaseException;
import com.capgemini.chess.service.to.MatchTO;

public interface MatchDao {
	
	MatchTO save(MatchTO matchToSave) throws MatchExistsInDatabaseException;
	MatchTO findById(Long id);

}
