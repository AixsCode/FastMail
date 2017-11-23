package com.wxs.fastmail.bean;

import java.io.Serializable;
import java.sql.Timestamp;


public class Recruit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private Float price;
	private String imageUrl;//内容
	private String content;
	private Timestamp publishTime;//发布时间
	
	public Recruit(User user, Float price, String imageUrl, String content, Timestamp publishTime) {
		super();
		this.user = user;
		this.price = price;
		this.imageUrl = imageUrl;
		this.content = content;
		this.publishTime = publishTime;
	}
	public Recruit() {
		super();
	}
	@Override
	public String toString() {
		return "Recruit [id=" + id + ", user=" + user + ", price=" + price + ", imageUrl=" + imageUrl + ", content="
				+ content + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
}
