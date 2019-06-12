package service;

import model.Admin;
import dao.AdminDao;


public class AdminService {
	private AdminDao adminDao = new AdminDao();
	//登录功能
	public int login(Admin form){
		//校验用户
		Admin admin = adminDao.findByUsername(form.getUsername());
		if(admin==null){
			return 0;
		}
		if(!form.getPassword().equals(admin.getPassword()))	return 0;
			return 1;
	}
}
