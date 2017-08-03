package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.MatchDao;
import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.service.MatchValidationService;
import com.capgemini.chess.service.to.MatchTO;

@Service
public class MatchValidationServiceImpl implements MatchValidationService {

	private MatchDao matchDao = null;
	
	@Autowired
	public MatchValidationServiceImpl(MatchDao matchDao) {
		super();
		this.matchDao = matchDao;
	}

	@Override
	public void validateMatch(Long id) throws MatchValidationException {
		MatchTO matchTO = matchDao.findById(id);
		if(matchTO == null){
			throw new MatchValidationException("There is no match with that Id.");
		}
	}

}
