package com.wxs.fastmail.bean;

import java.io.Serializable;
import java.sql.Timestamp;



public class OrderOnlySend implements Serializable{
	/**
	 * 
	 * 下单后 state 0
	 * 快递员接单后 state 1
	 * 快递员取件后 state 2
	 * 快递员送达 state 3
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User userSend;
	private User userDelivery;
	private String instruction;
	private Integer state;
	private String userSendAddress;
	private String sendToAddress;
	private String SendToName;
	private Float reward;
	private Timestamp userSendSubmitTime;//发单时间
	private Timestamp userDeliveryAcceptTime;//接单时间
	private Timestamp userSendDeliveryTransferTime;//快递员取货时间
	private Timestamp userDeliveryArriveTime;//快递员送达时间
	private Timestamp userGetExceptTime;//期望送达时间
	private Timestamp userExceptDeliveryTransferTime;//期望取件时间
	private String userSendTel;
	private String userDeliveryTel;
	private String SendToTel;
	private String itemType;
	private String weight;
	private String imageUrl;
	private boolean deliveryInsurance;
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public boolean isDeliveryInsurance() {
		return deliveryInsurance;
	}
	public void setDeliveryInsurance(boolean deliveryInsurance) {
		this.deliveryInsurance = deliveryInsurance;
	}
	public String getUserSendTel() {
		return userSendTel;
	}
	public void setUserSendTel(String userSendTel) {
		this.userSendTel = userSendTel;
	}
	public Timestamp getUserExceptDeliveryTransferTime() {
		return userExceptDeliveryTransferTime;
	}
	public void setUserExceptDeliveryTransferTime(Timestamp userExceptDeliveryTransferTime) {
		this.userExceptDeliveryTransferTime = userExceptDeliveryTransferTime;
	}
	public String getUserDeliveryTel() {
		return userDeliveryTel;
	}
	public void setUserDeliveryTel(String userDeliveryTel) {
		this.userDeliveryTel = userDeliveryTel;
	}
	public String getSendToTel() {
		return SendToTel;
	}
	public void setSendToTel(String sendToTel) {
		SendToTel = sendToTel;
	}
	public OrderOnlySend() {
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
	public User getUserDelivery() {
		return userDelivery;
	}
	public void setUserDelivery(User userDelivery) {
		this.userDelivery = userDelivery;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getUserSendAddress() {
		return userSendAddress;
	}
	public void setUserSendAddress(String userSendAddress) {
		this.userSendAddress = userSendAddress;
	}
	public String getSendToAddress() {
		return sendToAddress;
	}
	public void setSendToAddress(String sendToAddress) {
		this.sendToAddress = sendToAddress;
	}
	public String getSendToName() {
		return SendToName;
	}
	public void setSendToName(String sendToName) {
		SendToName = sendToName;
	}
	public Float getReward() {
		return reward;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public void setReward(Float reward) {
		this.reward = reward;
	}
	public void setUserSendSubmitTime(Timestamp userSendSubmitTime) {
		this.userSendSubmitTime = userSendSubmitTime;
	}
	public void setUserGetExceptTime(Timestamp userGetExceptTime) {
		this.userGetExceptTime = userGetExceptTime;
	}
	@Override
	public String toString()
	{
		return "OrderOnlySend [id=" + id + ", userSend=" + userSend + ", userDelivery=" + userDelivery
				+ ", instruction=" + instruction + ", state=" + state + ", userSendAddress=" + userSendAddress
				+ ", sendToAddress=" + sendToAddress + ", SendToName=" + SendToName + ", reward=" + reward
				+ ", userSendSubmitTime=" + userSendSubmitTime + ", userDeliveryAcceptTime=" + userDeliveryAcceptTime
				+ ", userSendDeliveryTransferTime=" + userSendDeliveryTransferTime + ", userDeliveryArriveTime="
				+ userDeliveryArriveTime + ", userGetExceptTime=" + userGetExceptTime + ", userSendTel=" + userSendTel
				+ ", userDeliveryTel=" + userDeliveryTel + ", SendToTel=" + SendToTel + "]";
	}

}
