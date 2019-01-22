package com.softserve.edu.items.dto;

public class MovieDto {

	private long id;
	private String title; 
	private String imdbMovieID; 	
	private String information;
	private boolean isFavourite;
	private String posterUrl;
	
	public MovieDto() {
	}
	
	public MovieDto(String title, String imdbMovieID, String information, String posterUrl) {
		this.title = title;
		this.imdbMovieID = imdbMovieID;
		this.information = information;
		this.posterUrl = posterUrl;
	}

	public MovieDto(long id, String title, String imdbMovieID, String information, boolean isFavourite, String posterUrl) {
		this.id = id;
		this.title = title;
		this.imdbMovieID = imdbMovieID;
		this.information = information;
		this.isFavourite = isFavourite;
		this.posterUrl = posterUrl;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImdbMovieID() {
		return imdbMovieID;
	}

	public void setImdbMovieID(String imdbMovieID) {
		this.imdbMovieID = imdbMovieID;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public boolean getIsFavourite() {
		return isFavourite;
	}

	public void setFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	@Override
	public String toString() {
		return "MovieDto [id=" + id + ", title=" + title + ", imdbMovieID=" + imdbMovieID + ", information="
				+ information + ", isFavourite=" + isFavourite + ", posterUrl=" + posterUrl + "]";
	}
	
}
