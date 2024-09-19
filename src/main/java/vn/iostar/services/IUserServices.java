package vn.iostar.services;

import vn.iostar.models.UserModel;

public interface IUserServices {

	UserModel login (String username, String password);
	
	
	UserModel FindByUserName(String username);
	
	
	void insert(UserModel user);
	
	boolean register(String email, String password, String username, String fullname, String phone);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	boolean checkExistPhone(String phone);

}
