package dao;


import java.sql.SQLException;

import model.User;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.TxQueryRunner;

/**
 * 依赖数据文件
 * @author Administrator
 *
 */

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	//按用户名查询
	public User findByUsername(String user){
		try {
			String sql = "select * from un where username=?";
			return qr.query(sql,new BeanHandler<User>(User.class),user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//按电话号码查询
	public User findTelphone(String telphone){
		try {
			String sql = "select * from un where telphone=?";
			return qr.query(sql,new BeanHandler<User>(User.class),telphone);
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
	}
	
	//添加数据
	public void addUserName(User username){
		try {
			String sql = "insert into un(username,password,telphone) values (?,?,?)";
			Object[] params = {username.getUsername(),
					username.getPassword(),username.getTelphone()};
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
