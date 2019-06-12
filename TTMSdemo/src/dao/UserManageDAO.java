package dao;

import idao.iUserManageDAO;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.jdbc.TxQueryRunner;

public class UserManageDAO implements iUserManageDAO {
	
	QueryRunner qr = new TxQueryRunner();
	
	public int updateUser(String want,String info,String username) {
		
		int rs = 0;
		String sql;
		if("sex".equals(want)) {
			
			try {
				sql = "UPDATE un SET '" + want +  "'='" + info + "' WHERE " +
						"username='" + username + "'";
				rs = qr.update(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		else if("telphone".equals(want)) {
			
			try {
				sql = "UPDATE un SET ?=? WHERE username=?";
				rs = qr.update(sql,want,info,username);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if("password".equals(want)) {
			
			try {
				sql = "UPDATE un SET ?=? WHERE username=?";
				rs = qr.update(sql,want,info,username);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rs;
	}

	public int updateUserIcon(byte[] bytes,String username) {
		
		String sql;
		int rs = 0;
		try {
			sql = "UPDATE un SET icon=? WHERE username=?";
			rs = qr.update(sql,new SerialBlob(bytes),username);
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
}
