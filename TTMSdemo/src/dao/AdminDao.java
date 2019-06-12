package dao;

import java.sql.SQLException;


import model.Admin;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.TxQueryRunner;

/**
 * 依赖数据文件
 * @author Administrator
 *
 */

public class AdminDao {
	private QueryRunner qr = new TxQueryRunner();
	//按用户名查询
	public Admin findByUsername(String user){
		try {
			String sql = "select * from admin where username=?";
			return qr.query(sql,new BeanHandler<Admin>(Admin.class),user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
