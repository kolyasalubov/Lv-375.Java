package com.softserve.edu;

public class Greeting {
	private final long id;
	private final String content;

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
	
    public void swap(int arg0, int arg1) {
        int arg = arg0;
        arg0 = arg1;
        arg1 = arg;
    }

}
