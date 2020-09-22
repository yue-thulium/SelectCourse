package com.hairgroup.choose.entity;

import java.io.Serializable;

public class Student implements Serializable {
	private int s_id;
	private String s_name;
	private String s_gender;
	private int s_age;
	private int u_id;
	
	public Student() {
		super();
	}

	public Student(int s_id, String s_name, String s_gender, int s_age, int u_id) {
		super();
		this.s_id = s_id;
		this.s_name = s_name;
		this.s_gender = s_gender;
		this.s_age = s_age;
		this.u_id = u_id;
	}

	public Student(String s_name, String s_gender, int s_age) {
		this.s_name = s_name;
		this.s_gender = s_gender;
		this.s_age = s_age;
	}

	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_gender() {
		return s_gender;
	}

	public void setS_gender(String s_gender) {
		this.s_gender = s_gender;
	}

	public int getS_age() {
		return s_age;
	}

	public void setS_age(int s_age) {
		this.s_age = s_age;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	@Override
	public String toString() {
		return "Student [s_id=" + s_id + ", s_name=" + s_name + ", s_gender=" + s_gender + ", s_age=" + s_age
				+ ", u_id=" + u_id + "]";
	}
	
}
