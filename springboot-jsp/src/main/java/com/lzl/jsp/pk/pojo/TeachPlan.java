package com.lzl.jsp.pk.pojo;

public class TeachPlan {

	 private int id;  //id
	 private String lesson_name; 
	 private String teacher_name;
	 private String clazz_name;
	 private int zks;
	 private String classroom_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLesson_name() {
		return lesson_name;
	}
	public void setLesson_name(String lesson_name) {
		this.lesson_name = lesson_name;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getClazz_name() {
		return clazz_name;
	}
	public void setClazz_name(String clazz_name) {
		this.clazz_name = clazz_name;
	}
	public int getZks() {
		return zks;
	}
	public void setZks(int zks) {
		this.zks = zks;
	}
	public String getClassroom_name() {
		return classroom_name;
	}
	public void setClassroom_name(String classroom_name) {
		this.classroom_name = classroom_name;
	}
	@Override
	public String toString() {
		return "TeachPlan [id=" + id + ", lesson_name=" + lesson_name
				+ ", teacher_name=" + teacher_name + ", clazz_name="
				+ clazz_name + ", zks=" + zks + ", classroom_name="
				+ classroom_name + "]";
	}
	
	 
	 
}
