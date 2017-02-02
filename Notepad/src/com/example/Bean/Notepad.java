package com.example.Bean;
/*
 * 用来暂时存储数据
 */
	public class Notepad {
		private String title;//标题
		private int id;//编号
		private String content;//内容
		private String time;//时间
		
	public Notepad(String title,int id,String content ,String time){
		this.id=id;
		this.title=title;
		this.content=content;
		this.time=time;
	}
	
	public Notepad(String title,String content,String time){
		this.title=title;
		this.content=content;
		this.time=time;
	}
	public Notepad(int id,String title,String time){
		this.id=id;
		this.title=title;
		this.time=time;
	}
	public Notepad(String title,String content){
		this.title=title;
		this.content=content;
	}
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getTimes() {
		return time;
	}
 
	}
