/**
 * @auther Rakesh
 * @time Nov 12, 2016
 */

package com.rkumbhare.restapi.vo;

public class User {

	private int id;
	private String fname;
	private String lname;
	private int age;
	private String gender;

	public User() {
	}

	public User(int id, String fname, String lname, int age, String gender) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
