package com.gamedoora.backend.userservices.dto;

import java.util.Date;

public class UserDTO {

	String email;

	String firstName;

	String lastName;

	long providerId;

	String password;

	int signInCount;

	String sessionToken;

	String resetPasswordToken;

	String photoUrl;

	String phoneNumber;

	Date lastSignIn;

	String lastSignIp;

	String currentSignIp;

	Date currentSignIn;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getProviderId() {
		return providerId;
	}

	public void setProviderId(long providerId) {
		this.providerId = providerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSignInCount() {
		return signInCount;
	}

	public void setSignInCount(int signInCount) {
		this.signInCount = signInCount;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getLastSignIn() {
		return lastSignIn;
	}

	public void setLastSignIn(Date lastSignIn) {
		this.lastSignIn = lastSignIn;
	}

	public String getLastSignIp() {
		return lastSignIp;
	}

	public void setLastSignIp(String lastSignIp) {
		this.lastSignIp = lastSignIp;
	}

	public String getCurrentSignIp() {
		return currentSignIp;
	}

	public void setCurrentSignIp(String currentSignIp) {
		this.currentSignIp = currentSignIp;
	}

	public Date getCurrentSignIn() {
		return currentSignIn;
	}

	public void setCurrentSignIn(Date currentSignIn) {
		this.currentSignIn = currentSignIn;
	}

}
