package com.lin.model;

public class Course {
	/*�γ̰��� ��ţ��γ�����ѧʱ��ѧ�֣��γ�״̬(ͨ�����������Ƿ��ѡ�����Ѿ��Ͽ�)
	 * 
	 * */
	
	private int id;
	private String name;
	private int time;
	private int credit;
	private int state;
	private String teacherName;
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
