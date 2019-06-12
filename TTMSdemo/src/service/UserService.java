package service;

import model.User;
import dao.UserDao;


/**
 * User的业务层
 * @author Administrator
 *
 */
public class UserService {
	
	private UserDao userDao = new UserDao();
	//注册功能
	public int regist(User form){
		//校验用户名
		User user = userDao.findByUsername(form.getUsername());
		if(user!=null){
			return 0;
		}
		//校验电话号码
		user = userDao.findTelphone(form.getTelphone());
		if(user!=null){
			return 0;
		}
		//插入数据
		userDao.addUserName(form);
		return 1;
	}
	//登录功能
	public int login(User form){
		//校验用户
		User user = userDao.findByUsername(form.getUsername());
		if(user==null){
			return 0;
		}
		if(!form.getPassword().equals(user.getPassword()))	return 0;
		return 1;
	}
	
}
