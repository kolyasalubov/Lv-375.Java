package com.it.academy.entity;

/**
 * Enum QueryNames contains names of all queries
 */
public enum QueryNames {

    CREATE_TABLE,

	INSERT,

	GET_BY_ID,
	GET_BY_FIELD,
	GET_ALL,
    GET_PAST_BY_FIELD,     // in BookingDao only
    GET_FUTURE_BY_FIELD,   // in BookingDao only

	UPDATE_ROW_BY_ID,
	UPDATE_FIELD_BY_ID,
    UPDATE_ROW_BY_FIELD,     // in UserDao only
    UPDATE_FIELD_BY_FIELD,   // in UserDao only

	DELETE_BY_ID,
	DELETE_BY_FIELD,

    IS_EXIST,    // in UserDao and BookingDao only
}
