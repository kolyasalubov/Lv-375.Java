package com.softserve.academy.DAO;

import java.util.ArrayList;
import java.util.List;

import com.softserve.academy.Entity.Trip;
import com.softserve.academy.Entity.Trip.TripEntityQueries;

public final class TripDao extends ADaoCRUD<Trip> {
    private final static String ID_USER_FIELDNAME = "idUser";

	public TripDao() {
		super();
		init();
	}

	protected void init() {
		for (TripEntityQueries tripEntityQueries : TripEntityQueries.values()) {     
			sqlQueries.put(tripEntityQueries.getSqlQuery(), tripEntityQueries);
		}
	}
	
	//Create a new trip
	protected Trip createInstance(List<String> tripParameters) {
		return new Trip(
				Long.parseLong(tripParameters.get(0) == null ? "0" : tripParameters.get(0)),
				tripParameters.get(1) == null ? new String() : tripParameters.get(1),
				tripParameters.get(2) == null ? new String() : tripParameters.get(2),
				tripParameters.get(3) == null ? new String() : tripParameters.get(3),		
				tripParameters.get(4) == null ? new String() : tripParameters.get(4),	
				tripParameters.get(5) == null ? new String() : tripParameters.get(5),			
				Long.parseLong(tripParameters.get(6) == null ? "0" : tripParameters.get(6)));
	}

	//Use in updateByEntity query
	protected List<String> getUpdateFields(Trip trip) {
		List<String> result = new ArrayList<>();
		List<String> allFields = getFields(trip);
		result.add(0, allFields.get(1)); // country
		result.add(1, allFields.get(2)); // title
		result.add(2, allFields.get(3)); // date
		result.add(3, allFields.get(4)); // description
		result.add(4, allFields.get(5)); // author_alias
		result.add(5, allFields.get(6)); // idUser
		result.add(6, allFields.get(0)); // id
		return result;
	}

	protected List<String> getFields(Trip trip) {
		List <String> fields = new ArrayList<>();
		fields.add(0, trip.getId().toString());
		fields.add(1, trip.getCountry());
		fields.add(2, trip.getTitle());
		fields.add(3, trip.getDate());
		fields.add(4, trip.getDescription());
		fields.add(5, trip.getAuthors_alias());
		fields.add(6, trip.getIdUser().toString());
		return fields;
	}

	public List<Trip> getTripEntityByIdUser(Long idUser) {
		return getByFieldName(ID_USER_FIELDNAME, idUser.toString());
		}
}