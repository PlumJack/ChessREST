package com.capgemini.chess.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.service.UserProfileSearchService;
import com.capgemini.chess.service.to.UserProfileTO;

@Service
public class UserProfileSearchServiceImpl implements UserProfileSearchService {

	private UserDao userDao = null;

	@Autowired
	public UserProfileSearchServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<UserProfileTO> getUserProfilesByName(String name) {
		List<UserProfileTO> userProfiles = userDao.getAll();
		List<UserProfileTO> foundUserProfiles = new ArrayList<UserProfileTO>();
		for(UserProfileTO userProfile: userProfiles){
			if(userProfile.getName().equals(name)){
				foundUserProfiles.add(userProfile);
			}			
		}
		return foundUserProfiles;
	}

	@Override
	public List<UserProfileTO> getUserProfilesBySurname(String surname) {
		List<UserProfileTO> userProfiles = userDao.getAll();
		List<UserProfileTO> foundUserProfiles = new ArrayList<UserProfileTO>();
		for(UserProfileTO userProfile: userProfiles){
			if(userProfile.getSurname().equals(surname)){
				foundUserProfiles.add(userProfile);
			}			
		}
		return foundUserProfiles;
	}

	@Override
	public List<UserProfileTO> getUserProfilesByNameAndSurname(String name, String surname) {
		List<UserProfileTO> userProfiles = userDao.getAll();
		List<UserProfileTO> foundUserProfiles = new ArrayList<UserProfileTO>();
		for(UserProfileTO userProfile: userProfiles){
			if(userProfile.getName().equals(name) && userProfile.getSurname().equals(surname)){
				foundUserProfiles.add(userProfile);
			}			
		}
		return foundUserProfiles;
	}
	
}
