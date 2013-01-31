package com.devnexus;

public class Speaker {

	public String bio;
	public String createdDate;
	public String firstName;
	public String goodlePlusId;
	public Long id;
	public String lastName;
	public String twitterId;
	public String updatedDate;
	public int version;
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
	
}
