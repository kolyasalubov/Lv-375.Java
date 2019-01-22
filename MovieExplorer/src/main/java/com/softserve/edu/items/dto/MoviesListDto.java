package com.softserve.edu.items.dto;

import java.util.List;

public class MoviesListDto {
	//
	private List<MovieDto> movies;
	private int currentPage;
	private int pageCount;
	private int pageOffset;

	public MoviesListDto(List<MovieDto> movies, int currentPage, int pageCount, int pageOffset) {
		this.movies = movies;
		this.currentPage = currentPage;
		this.pageCount = pageCount;
		this.pageOffset = pageOffset;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setMovies(List<MovieDto> movies) {
		this.movies = movies;
	}

	// setters
	public void setItems(List<MovieDto> movies) {
		this.movies = movies;
	}

	public void addItemDto(MovieDto movie) {
		movies.add(movie);
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

	// getters
	public List<MovieDto> getMovies() {
		return movies;
	}

	public int getPageCount() {
		return  pageCount;
	}

	public int getPageOffset() {
		return  pageOffset;
	}

	@Override
	public String toString() {
		return "("
				+ " items=" + movies.toString() 
				+ " pageCount=" + pageCount
				+ " pageOffset=" + pageOffset
				+ ")";
	}
}