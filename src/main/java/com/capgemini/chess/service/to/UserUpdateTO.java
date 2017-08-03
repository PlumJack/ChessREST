package com.capgemini.chess.service.to;

public class UserUpdateTO {
	
	private String login;
	private String oldPassword;
	private String newPassword;
	private String name;
	private String surname;
	private String email;
	private String aboutMe;
	private String lifeMotto;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public String getLifeMotto() {
		return lifeMotto;
	}
	public void setLifeMotto(String lifeMotto) {
		this.lifeMotto = lifeMotto;
	}	

}
