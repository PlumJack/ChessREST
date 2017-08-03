package com.capgemini.chess.dataaccess.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.capgemini.chess.dataaccess.MatchDao;
import com.capgemini.chess.dataaccess.entities.MatchEntity;
import com.capgemini.chess.dataaccess.mappers.MatchMapper;
import com.capgemini.chess.exception.MatchExistsInDatabaseException;
import com.capgemini.chess.service.to.MatchTO;

@Repository
public class MatchDaoImpl implements MatchDao {

	private final Map<Long, MatchEntity> matches = new HashMap<>();
	
	@Override
	public MatchTO save(MatchTO matchToSave) throws MatchExistsInDatabaseException {
		if(matchToSave.getId() != null){
			throw new MatchExistsInDatabaseException("Match already exists.");
		}
		MatchEntity match = MatchMapper.map(matchToSave);
		Long id = generateId();
		match.setId(id);
		matches.put(id, match);
		return MatchMapper.map(match);
	}

	@Override
	public MatchTO findById(Long id) {
		MatchEntity user = matches.values().stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
		return MatchMapper.map(user);
	}
	
	private Long generateId() {
		return matches.keySet().stream().max((i1, i2) -> i1.compareTo(i2)).orElse(0L) + 1;
	}

}
