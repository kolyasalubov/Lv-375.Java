package com.softserve.training;

public class Box {

	private Object data;

	public Box() {
		this.data = null;
	}
	
	public Box(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
