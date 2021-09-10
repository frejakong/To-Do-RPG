package com.example.to_dorpg.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class TodoTask {

	private int id;	
	private String title;
	private String content;
	private String flagCompleted;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFlagCompleted() {
		return flagCompleted;
	}
	public void setFlagCompleted(String flagCompleted) {
		this.flagCompleted = flagCompleted;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
