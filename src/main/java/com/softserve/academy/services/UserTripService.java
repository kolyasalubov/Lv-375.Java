package com.softserve.academy.services;

import java.util.ArrayList;
import java.util.List;

import com.softserve.academy.DAO.TripDao;
import com.softserve.academy.DAO.UserDao;
import com.softserve.academy.DTO.TripDto;
import com.softserve.academy.DTO.UserDto;
import com.softserve.academy.DTO.UserTripDto;
import com.softserve.academy.Entity.Trip;
import com.softserve.academy.Entity.User;

public class UserTripService {

	private UserDao userDao;
	private TripDao tripDao;

	public UserTripService(UserDao userDao, TripDao tripDao) {
		this.userDao = userDao;
		this.tripDao = tripDao;
	}

	//Get trips of one user
	public UserTripDto getUserTrips(UserDto userDto) {
		User user = userDao.getUserEntityByLogin(userDto.getLogin());
		UserTripDto userTripDto = new UserTripDto(user.getLogin());

		for (Trip trip : tripDao.getTripEntityByIdUser(user.getId())) {
			TripDto tripDto = new TripDto(trip.getId(), trip.getCountry(), trip.getTitle(), trip.getDate(),
					trip.getDescription(), trip.getAuthors_alias());
			userTripDto.addTripDto(tripDto);
		}
		return userTripDto;
	}

	//Get all trips
	public UserTripDto getAllTrips(UserDto userDto) {
		User user = userDao.getUserEntityByLogin(userDto.getLogin());
		UserTripDto userTripDto = new UserTripDto(user.getLogin());
		for (Trip trip : tripDao.getAll()) {
			TripDto tripDto = new TripDto(trip.getId(), trip.getCountry(), trip.getTitle(), trip.getDate(),
					trip.getDescription(), trip.getAuthors_alias());
			userTripDto.addTripDto(tripDto);
		}
		return userTripDto;
	}

}
