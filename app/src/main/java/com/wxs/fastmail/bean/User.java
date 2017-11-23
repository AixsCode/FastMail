package com.wxs.fastmail.bean;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userName;
	private String password;
	private String token;
	private Float creditScore;
	private String sex;
	private String job;
	private String nickName;
	private String hobby;
	private String school;
	private String company;
	private String mailBox;
	private String homePlace;
	private String age;
	private String imageUrl;
	private String address;
	private String state;
	private String introduction;
	private String idProve;//身份证号
	private String realName;//姓名
	private boolean despost;//押金
	private Integer delivery;//0普通 1申请 2拒绝 3快递员
	private boolean matching;
	
	
	public Integer getDelivery() {
		return delivery;
	}
	public void setDelivery(Integer delivery) {
		this.delivery = delivery;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Float getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(Float creditScore) {
		this.creditScore = creditScore;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getMailBox() {
		return mailBox;
	}
	public void setMailBox(String mailBox) {
		this.mailBox = mailBox;
	}
	public String getHomePlace() {
		return homePlace;
	}
	public void setHomePlace(String homePlace) {
		this.homePlace = homePlace;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getIdProve() {
		return idProve;
	}
	public void setIdProve(String idProve) {
		this.idProve = idProve;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public boolean isDespost() {
		return despost;
	}
	public void setDespost(boolean despost) {
		this.despost = despost;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", token=" + token
				+ ", creditScore=" + creditScore + ", sex=" + sex + ", job=" + job + ", nickName=" + nickName
				+ ", hobby=" + hobby + ", school=" + school + ", company=" + company + ", mailBox=" + mailBox
				+ ", homePlace=" + homePlace + ", age=" + age + ", imageUrl=" + imageUrl + ", address=" + address
				+ ", state=" + state + ", introduction=" + introduction + ", idProve=" + idProve + ", realName="
				+ realName + ", despost=" + despost + ", delivery=" + delivery + "]";
	}
	public User() {
		super();
	}
	public User(String userName, String password, String token, Float creditScore) {
		super();
		this.userName = userName;
		this.password = password;
		this.token = token;
		this.creditScore = creditScore;
	}
	public boolean isMatching() {
		return matching;
	}
	public void setMatching(boolean matching) {
		this.matching = matching;
	}
	
}
