package com.vaish.SpringBootRestService.controller;

import org.springframework.stereotype.Component;

@Component
public class Greeting {
	
	private long id;
	private String content;
	
	public long getId() {
		return id;
	}
	public void setId(long l) {
		this.id = l;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
