package com.capgemini.chess.service;

import java.util.List;

import com.capgemini.chess.service.to.UserProfileTO;

public interface UserProfileSearchService {

	List<UserProfileTO> getUserProfilesByName(String name);
	List<UserProfileTO> getUserProfilesBySurname(String surname);
	List<UserProfileTO> getUserProfilesByNameAndSurname(String name, String surname);
}
