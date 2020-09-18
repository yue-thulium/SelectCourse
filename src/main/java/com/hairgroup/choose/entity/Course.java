package com.hairgroup.choose.entity;

public class Course {
	private int c_id;
	private String c_name;
	private String c_info;
	private int c_room;
	private int cou_id;
	
	public Course() {
		super();
	}

	public Course(int c_id, String c_name, String c_info, int c_room, int cou_id) {
		super();
		this.c_id = c_id;
		this.c_name = c_name;
		this.c_info = c_info;
		this.c_room = c_room;
		this.cou_id = cou_id;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_info() {
		return c_info;
	}

	public void setC_info(String c_info) {
		this.c_info = c_info;
	}

	public int getC_room() {
		return c_room;
	}

	public void setC_room(int c_room) {
		this.c_room = c_room;
	}

	public int getCou_id() {
		return cou_id;
	}

	public void setCou_id(int cou_id) {
		this.cou_id = cou_id;
	}

	@Override
	public String toString() {
		return "Course [c_id=" + c_id + ", c_name=" + c_name + ", c_info=" + c_info + ", c_room=" + c_room + ", cou_id="
				+ cou_id + "]";
	}
	
}
