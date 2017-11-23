package com.wxs.fastmail.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderUserThree implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User userSend;//发件人
	private User userGet;//收件人（下单者）
	private User userDelivery;
	private Integer state;
	private Timestamp userGetSubmitTime;//下单时间
	private Timestamp userSendAcceptTime;//发件人接单时间
	private Timestamp userDeliveryAcceptTime;//快递员接单时间
	private Timestamp userSendDeliveryTransferTime;
	private Timestamp userDeliveryGetTransferTime;
	private String userGetAdress;//收货地址
	private String userSendAdress;//发货地址
	private String userGetNotes;
	private String userSendNotes;
	private Float userSendMoney;
	private Float userDeliveryReward;
	private String userGetTel;
	private String userSendTel;
	private String userDeliveryTel;
	private Timestamp userGetExceptTime;
	private String position;//就近购买位置="0"  物品位置
	private String tag;
	private boolean getInsurance;
	private boolean deliveryInsurance;
	private String imageUrl;
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isGetInsurance() {
		return getInsurance;
	}

	public void setGetInsurance(boolean getInsurance) {
		this.getInsurance = getInsurance;
	}

	public boolean isDeliveryInsurance() {
		return deliveryInsurance;
	}

	public void setDeliveryInsurance(boolean deliveryInsurance) {
		this.deliveryInsurance = deliveryInsurance;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public OrderUserThree() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserSend() {
		return userSend;
	}

	public void setUserSend(User userSend) {
		this.userSend = userSend;
	}

	public User getUserGet() {
		return userGet;
	}

	public void setUserGet(User userGet) {
		this.userGet = userGet;
	}

	public User getUserDelivery() {
		return userDelivery;
	}

	public void setUserDelivery(User userDelivery) {
		this.userDelivery = userDelivery;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	public void setUserDeliveryGetTransferTime(Timestamp userDeliveryGetTransferTime) {
		this.userDeliveryGetTransferTime = userDeliveryGetTransferTime;
	}

	public String getUserGetAdress() {
		return userGetAdress;
	}

	public void setUserGetAdress(String userGetAdress) {
		this.userGetAdress = userGetAdress;
	}

	public String getUserSendAdress() {
		return userSendAdress;
	}

	public void setUserSendAdress(String userSendAdress) {
		this.userSendAdress = userSendAdress;
	}

	public String getUserGetNotes() {
		return userGetNotes;
	}

	public void setUserGetNotes(String userGetNotes) {
		this.userGetNotes = userGetNotes;
	}

	public String getUserSendNotes() {
		return userSendNotes;
	}

	public void setUserSendNotes(String userSendNotes) {
		this.userSendNotes = userSendNotes;
	}

	public Float getUserSendMoney() {
		return userSendMoney;
	}

	public void setUserSendMoney(Float userSendMoney) {
		this.userSendMoney = userSendMoney;
	}

	public Float getUserDeliveryReward() {
		return userDeliveryReward;
	}

	public void setUserDeliveryReward(Float userDeliveryReward) {
		this.userDeliveryReward = userDeliveryReward;
	}

	public String getUserGetTel() {
		return userGetTel;
	}

	public void setUserGetTel(String userGetTel) {
		this.userGetTel = userGetTel;
	}

	public String getUserSendTel() {
		return userSendTel;
	}

	public void setUserSendTel(String userSendTel) {
		this.userSendTel = userSendTel;
	}

	public String getUserDeliveryTel() {
		return userDeliveryTel;
	}

	public void setUserDeliveryTel(String userDeliveryTel) {
		this.userDeliveryTel = userDeliveryTel;
	}

	public Timestamp getUserGetExceptTime() {
		return userGetExceptTime;
	}

	public void setUserGetExceptTime(Timestamp userGetExceptTime) {
		this.userGetExceptTime = userGetExceptTime;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "OrderUserThree [id=" + id + ", userSend=" + userSend + ", userGet=" + userGet + ", userDelivery="
				+ userDelivery + ", state=" + state + ", userGetSubmitTime=" + userGetSubmitTime
				+ ", userSendAcceptTime=" + userSendAcceptTime + ", userDeliveryAcceptTime=" + userDeliveryAcceptTime
				+ ", userSendDeliveryTransferTime=" + userSendDeliveryTransferTime + ", userDeliveryGetTransferTime="
				+ userDeliveryGetTransferTime + ", userGetAdress=" + userGetAdress + ", userSendAdress="
				+ userSendAdress + ", userGetNotes=" + userGetNotes + ", userSendNotes=" + userSendNotes
				+ ", userSendMoney=" + userSendMoney + ", userDeliveryReward=" + userDeliveryReward + ", userGetTel="
				+ userGetTel + ", userSendTel=" + userSendTel + ", userDeliveryTel=" + userDeliveryTel
				+ ", userGetExceptTime=" + userGetExceptTime + ", position=" + position + "]";
	}
}
