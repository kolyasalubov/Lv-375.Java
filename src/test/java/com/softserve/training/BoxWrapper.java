package com.softserve.training;

// Decorator
public class BoxWrapper {

	private Box box;
	
	public BoxWrapper() {
		box = new Box();
	}
	
	public Integer getData() {
		return (Integer) box.getData();
	}

	public void setData(Integer data) {
		this.box.setData(data);
	}
}
