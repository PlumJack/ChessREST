package com.capgemini.chess.dataaccess;

import java.util.List;
import com.capgemini.chess.exception.UserProfileExistsInDatabaseException;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatsTO;
import com.capgemini.chess.service.to.UserUpdateTO;

public interface UserDao {

	UserProfileTO save(UserProfileTO to) throws UserProfileExistsInDatabaseException;
	UserProfileTO findById(Long id);
	UserProfileTO findByLogin(String login);
	UserStatsTO getStats(String login);
	UserStatsTO getStats(Long id);
	
	void updateUserProfile(UserUpdateTO userUpdateTO);
	void updateUserStats(Long userProfileId, UserStatsTO newStats);
	void updatePositions();
	void initData() throws UserProfileExistsInDatabaseException;
	
	List<UserProfileTO> getAll();
}
