package com.hairgroup.choose.entity;

public class Teacher {
	private int t_id;
	private String t_name;
	private String t_gender;
	private int t_age;
	private int u_id;
	
	public Teacher() {
		super();
	}

	public Teacher(int t_id, String t_name, String t_gender, int t_age, int u_id) {
		super();
		this.t_id = t_id;
		this.t_name = t_name;
		this.t_gender = t_gender;
		this.t_age = t_age;
		this.u_id = u_id;
	}

	public Teacher(String t_name, String t_gender, int t_age) {
		this.t_name = t_name;
		this.t_gender = t_gender;
		this.t_age = t_age;
	}

	public int getT_id() {
		return t_id;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public String getT_gender() {
		return t_gender;
	}

	public void setT_gender(String t_gender) {
		this.t_gender = t_gender;
	}

	public int getT_age() {
		return t_age;
	}

	public void setT_age(int t_age) {
		this.t_age = t_age;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	@Override
	public String toString() {
		return "Teacher [t_id=" + t_id + ", t_name=" + t_name + ", t_gender=" + t_gender + ", t_age=" + t_age
				+ ", u_id=" + u_id + "]";
	}
	
}
