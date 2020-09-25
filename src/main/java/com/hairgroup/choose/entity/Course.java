package com.hairgroup.choose.entity;

import java.io.Serializable;

public class Course implements Serializable {
	private int c_id;
	private String c_name;
	private String c_info;
	private String t_name;
	private int number;
	private int maxNumber = 20;
	private int c_room;
	private int cou_id;
	private int c_num;
	private boolean flag;
	private boolean maxFlag;
	
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

	public Course(int c_id, String c_name, String c_info, String t_name, int number, int maxNumber, int c_room, int cou_id, int c_num) {
		this.c_id = c_id;
		this.c_name = c_name;
		this.c_info = c_info;
		this.t_name = t_name;
		this.number = number;
		this.maxNumber = maxNumber;
		this.c_room = c_room;
		this.cou_id = cou_id;
		this.c_num = c_num;
	}

	public Course(int c_id, String c_name, String c_info, String t_name, int c_room, int c_num) {
		this.c_id = c_id;
		this.c_name = c_name;
		this.c_info = c_info;
		this.t_name = t_name;
		this.c_room = c_room;
		this.c_num = c_num;
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

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		if (t_name == null) {
			this.t_name = "待指派";
			flag = false;
		} else {
			this.t_name = t_name;
			flag = true;
		}
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}

	public int getC_num() {
		return c_num;
	}

	public void setC_num(int c_num) {
		if (c_num >= 20) {
			maxFlag = true;
		} else {
			maxFlag = false;
		}
		this.c_num = c_num;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean getMaxFlag() {
		return maxFlag;
	}

	public void setMaxFlag(boolean maxFlag) {
		this.maxFlag = maxFlag;
	}

	@Override
	public String toString() {
		return "Course{" +
				"c_id=" + c_id +
				", c_name='" + c_name + '\'' +
				", c_info='" + c_info + '\'' +
				", t_name='" + t_name + '\'' +
				", number=" + number +
				", maxNumber=" + maxNumber +
				", c_room=" + c_room +
				", cou_id=" + cou_id +
				", c_num=" + c_num +
				", flag=" + flag +
				'}';
	}
}
