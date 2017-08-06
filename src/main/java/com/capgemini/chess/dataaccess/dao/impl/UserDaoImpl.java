package com.capgemini.chess.dataaccess.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.capgemini.chess.dataaccess.entities.UserProfileEntity;
import com.capgemini.chess.dataaccess.mappers.UserProfileMapper;
import com.capgemini.chess.exception.UserProfileExistsInDatabaseException;
import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatsTO;
import com.capgemini.chess.service.to.UserUpdateTO;


@Repository
public class UserDaoImpl implements UserDao {

	private final Map<Long, UserProfileEntity> users = new HashMap<>();
	
	public UserDaoImpl() {
		initData();
	}

	@Override
	public UserProfileTO save(UserProfileTO to) throws UserProfileExistsInDatabaseException {
		if(to.getId() != null){
			throw new UserProfileExistsInDatabaseException("Match already exists.");
		}
		UserProfileEntity user = UserProfileMapper.map(to);
		Long id = generateId();
		user.setId(id);
		users.put(id, user);
		return UserProfileMapper.map(user);
	}

	public UserProfileTO findById(Long id) {
		UserProfileEntity user = users.values().stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
		return UserProfileMapper.map(user);
	}
	
	
	public UserProfileTO findByLogin(String login) {
		UserProfileEntity user = users.values().stream().filter(u -> u.getLogin().equals(login)).findFirst().orElse(null);
		return UserProfileMapper.map(user);
	}


	private Long generateId() {
		return users.keySet().stream().max((i1, i2) -> i1.compareTo(i2)).orElse(0L) + 1L;
	}

	public UserStatsTO getStats(String login){
		
		UserProfileTO user = findByLogin(login);
		return user.getUserStatsTO();
		
	}
	
	public UserStatsTO getStats(Long id){
		
		UserProfileTO user = findById(id);
		return user.getUserStatsTO();
		
	}

	@Override
	public void updateUserProfile(UserUpdateTO userUpdateTO) {
		UserProfileTO userProfileTO = findByLogin(userUpdateTO.getLogin());
		userProfileTO.setPassword(userUpdateTO.getNewPassword());
		userProfileTO.setName(userUpdateTO.getName());
		userProfileTO.setSurname(userUpdateTO.getSurname());
		userProfileTO.setEmail(userUpdateTO.getEmail());
		userProfileTO.setAboutMe(userUpdateTO.getAboutMe());
		userProfileTO.setLifeMotto(userUpdateTO.getLifeMotto());
		
		saveUpdatedUserProfile(userProfileTO);
		
	}
	
	private void saveUpdatedUserProfile(UserProfileTO userProfileTO){
		UserProfileEntity userProfileEntity = UserProfileMapper.map(userProfileTO);
		users.put(userProfileEntity.getId(), userProfileEntity);
	}

	@Override
	public void updateUserStats(Long userProfileId, UserStatsTO newStats) {
		UserProfileTO userProfileTO = findById(userProfileId);
		userProfileTO.setUserStatsTO(newStats);
		saveUpdatedUserProfile(userProfileTO);
	}
	
	public void updatePositions(){
		ArrayList<UserProfileEntity> usersList = new ArrayList<UserProfileEntity>();
		for(UserProfileEntity user: users.values()){
			usersList.add(user);
		}

		Collections.sort(usersList, (UserProfileEntity u1, UserProfileEntity u2) -> 
		Integer.compare(u1.getUserStatsEntity().getPoints(), u2.getUserStatsEntity().getPoints()));	
		
		for(int i=0; i<usersList.size(); i++){
			usersList.get(0).getUserStatsEntity().setPosition(i+1);
		}
				
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

	@Override
	public void initData() {
		UserProfileTO user1 = createUserProfile(1);
		UserProfileTO user2 = createUserProfile(2);
		UserProfileTO user3 = createUserProfile(3);
		//UserProfileTO user4 = createUserProfile(4);
		//UserProfileTO user5 = createUserProfile(5);
		try {
			user1 = save(user1);
			user2 = save(user2);
			user3 = save(user3);
			//user4 = save(user4);
			//user5 = save(user5);
		} catch (UserProfileExistsInDatabaseException e) {
			e.printStackTrace();
		}		
		
	}

	@Override
	public List<UserProfileTO> getAll() {
		List<UserProfileEntity> usersList = new ArrayList<UserProfileEntity>();
		for(UserProfileEntity user: users.values()){
			usersList.add(user);
		}
		return UserProfileMapper.map2TOs(usersList);
	}
	
	
	
}
