package com.softserve.edu.dao;

//public final class UserDao implements IUserDao { // Mockito not working
public class UserDao implements IUserDao {

	public String getIPAddress() {
		System.out.println("***Running UserDao getIPAddress()");
		return "192.168.103.181";
	}

	public String getIPAddress(String text) {
		System.out.println("***Running UserDao getIPAddress(String text)");
		return "192.168.103.181" + text;
	}

}