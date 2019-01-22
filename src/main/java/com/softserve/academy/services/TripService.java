package com.softserve.academy.services;

import com.softserve.academy.DAO.TripDao;
import com.softserve.academy.DTO.TripDto;
import com.softserve.academy.Entity.Trip;

public class TripService {

	private TripDao tripDao;


	public TripService(TripDao tripDao) {
		this.tripDao = tripDao;
	}
		
	//Update or insert trip
	public boolean setTripDto(TripDto tripDto, Long idUser) {
		boolean result = false;
		Trip trip = new Trip(tripDto.getIdTrip(), tripDto.getCountry(), tripDto.getTitle(), tripDto.getDate(), tripDto.getDescription(),tripDto.getAuthors_alias(), idUser);
		if (tripDto.getIdTrip() > 0) {
			if (isExistTrip(trip.getId())) {
				tripDao.updateByEntity(trip);
				result = true;
			
		} else {
			tripDao.insert(trip);
			result = true;
		}
		}
		return result;
	}

	
	public TripDto getTripDto(long id) {
		Trip trip = tripDao.getById(id);
		return new TripDto(trip.getId(), trip.getCountry(), trip.getTitle(), trip.getDate(), trip.getDescription(), trip.getAuthors_alias());
	}
	
	//Check if trip exist
	public boolean isExistTrip(long id) {
		boolean result = true;
		try {
			tripDao.getById(id);
		} catch (RuntimeException e) {
			System.out.println("Item not found, message: " + e.getMessage());
			result = false;
		}
		return result;
	}

	//Delete
	public boolean deleteTrip(long id) {
		boolean result = true;
		try {
			result = result && tripDao.deleteById(id);
		} catch (RuntimeException e) {
			System.out.println("Can't delete trip, message: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean initializeTrip() {
		return tripDao.createTable();
	}
}
