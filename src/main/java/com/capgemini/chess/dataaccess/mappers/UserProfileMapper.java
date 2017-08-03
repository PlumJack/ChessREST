package com.capgemini.chess.dataaccess.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.chess.dataaccess.entities.UserProfileEntity;
import com.capgemini.chess.service.to.UserProfileTO;

public class UserProfileMapper {

	public static UserProfileTO map(UserProfileEntity userEntity) {
		if (userEntity != null) {
			UserProfileTO userTO = new UserProfileTO();
			userTO.setAboutMe(userEntity.getAboutMe());
			userTO.setEmail(userEntity.getEmail());
			userTO.setId(userEntity.getId());
			userTO.setLifeMotto(userEntity.getLifeMotto());
			userTO.setLogin(userEntity.getLogin());
			userTO.setName(userEntity.getName());
			userTO.setPassword(userEntity.getPassword());
			userTO.setSurname(userEntity.getSurname());
			userTO.setUserStatsTO(UserStatsMapper.map(userEntity.getUserStatsEntity()));
			return userTO;
		}
		return null;
	}

	public static UserProfileEntity map(UserProfileTO userTO) {
		if (userTO != null) {
			UserProfileEntity userEntity = new UserProfileEntity();
			userEntity.setAboutMe(userTO.getAboutMe());
			userEntity.setEmail(userTO.getEmail());
			userEntity.setId(userTO.getId());
			userEntity.setLifeMotto(userTO.getLifeMotto());
			userEntity.setLogin(userTO.getLogin());
			userEntity.setName(userTO.getName());
			userEntity.setPassword(userTO.getPassword());
			userEntity.setSurname(userTO.getSurname());
			userEntity.setUserStatsEntity(UserStatsMapper.map(userTO.getUserStatsTO()));
			return userEntity;
		}
		return null;
	}

	public static UserProfileEntity update(UserProfileEntity userEntity, UserProfileTO userTO) {
		if (userTO != null && userEntity != null) {
			userEntity.setAboutMe(userTO.getAboutMe());
			userEntity.setEmail(userTO.getEmail());
			userEntity.setId(userTO.getId());
			userEntity.setLifeMotto(userTO.getLifeMotto());
			userEntity.setName(userTO.getName());
			userEntity.setPassword(userTO.getPassword());
			userEntity.setSurname(userTO.getSurname());
		}
		return userEntity;
	}

	public static List<UserProfileTO> map2TOs(List<UserProfileEntity> userEntities) {
		return userEntities.stream().map(UserProfileMapper::map).collect(Collectors.toList());
	}

	public static List<UserProfileEntity> map2Entities(List<UserProfileTO> userTOs) {
		return userTOs.stream().map(UserProfileMapper::map).collect(Collectors.toList());
	}
}
