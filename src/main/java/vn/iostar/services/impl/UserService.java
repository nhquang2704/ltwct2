package vn.iostar.services.impl;

import vn.iostar.dao.IUserDao;
import vn.iostar.dao.impl.UserDaoImpl;
import vn.iostar.models.UserModel;
import vn.iostar.services.IUserServices;

public class UserService implements IUserServices{
	//lay toan bo ham trong tang Dao cua user
	IUserDao userDao = new UserDaoImpl();
	
	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.FindByUserName(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
			}
			return null;
		}


	@Override
	public UserModel FindByUserName(String username) {
		return userDao.findByUserName(username);
	}
	public static void main (String[] args) {
		IUserServices userService = new UserService();
		System.out.println(userService.FindByUserName("nhatquang"));
	}
	
	@Override
	public boolean register(String username, String password, String email, String fullname, String phone ) {
		if (userDao.checkExistUsername(username)) {
			return false;
	}
	long millis=System.currentTimeMillis();
	java.sql.Date date=new java.sql.Date(millis);
    userDao.insert(new UserModel(99,username,password,email,fullname,null,phone,5,date));
	return true;
	}
	
	public boolean checkExistEmail(String email) {
		
		return userDao.checkExistEmail(email);
		
	}
	
	public boolean checkExistUsername(String username) {
		
		return userDao.checkExistUsername(username);
		
	}
	@Override
	public boolean checkExistPhone(String phone) {
		
		return userDao.checkExistPhone(phone);
		
	}
	@Override
	public void insert(UserModel user) {
		
		userDao.insert(user);
		
	}
}
