package service;

import dao.UserManageDAO;


public class UserManageService {
	
	UserManageDAO umd = new UserManageDAO();
	public int updateUser(String want,String info,String username) {
		
		return umd.updateUser(want,info,username);
	}
	public int updateUserIcon(byte[] bytes,String username) {
		
		return umd.updateUserIcon(bytes,username); 
	}
}
