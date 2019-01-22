package com.softserve.academy.DTO;

public class TripDto {

	private Long idTrip;    
	private String country;
	private String title;
	private String date;
	private String description;
	private String authors_alias;
	
	public TripDto(Long idTrip, String country, String title, String date, String description, String authors_alias) {
		this.idTrip = idTrip;
		this.country = country;
		this.title = title;
		this.date = date;
		this.description = description;
		this.authors_alias = authors_alias;
	}

	//getters & setters
	
	public Long getIdTrip() {
		return idTrip;
	}

	public void setIdTrip(Long idTrip) {
		this.idTrip = idTrip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthors_alias() {
		return authors_alias;
	}

	public void setAuthors_alias(String authors_alias) {
		this.authors_alias = authors_alias;
	}

	@Override
	public String toString() {
		return "TripDto [idTrip=" + idTrip + ", country=" + country + ", title=" + title + ", date=" + date
				+ ", description=" + description + ", authors_alias=" + authors_alias + "]";
	}





}