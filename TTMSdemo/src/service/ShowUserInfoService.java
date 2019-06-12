package service;

import dao.ShowUserInfoDAO;


public class ShowUserInfoService {
		
	private ShowUserInfoDAO su = new ShowUserInfoDAO(); 
	public String show(String username) {
		
		return su.show(username);
	}
}	
