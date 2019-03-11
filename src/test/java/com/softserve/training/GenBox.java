package com.softserve.training;

public class GenBox<T> {

	private T data;

	public GenBox() {
		this.data = null;
	}
	
	public GenBox(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
