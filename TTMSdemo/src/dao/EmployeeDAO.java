package dao;

import idao.iEmployeeDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import model.Employee;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import cn.itcast.jdbc.TxQueryRunner;


public class EmployeeDAO implements iEmployeeDAO{
	
	private QueryRunner qr = new TxQueryRunner();
	public int insert(Employee emp) {
		
		int s = 0;
		try {
			String sql = "insert into employee(emp_no,emp_name,emp_tel_num,emp_addr,emp_email,emp_identity)"
					 + "values(?,?,?,?,?,?)";
			
			Object[] params = {
					emp.getEmp_no(), emp.getEmp_name(), emp.getEmp_tel_num(),
					emp.getEmp_addr(), emp.getEmp_email(),emp.getEmp_identity()
					};
			s = qr.update(sql, params);
//			grant(emp.getEmp_identity(),emp.getEmp_name());
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return s;
}

//增加员工后授予权限
//	private void grant(String emp_identity,String emp_name) {
//		
//		String createUserSql = "";
//		String grantSql = "";
//		if("经理".equals(emp_identity)) {
//			
//			createUserSql = "create user '"+emp_name+"'@'localhost' identified by '123456'";
//			grantSql = " grant all privileges on `ttms`.* to '"+emp_name+"'@'localhost' identified by '123456'";
//			try {
//				qr.update(createUserSql);
//				qr.update(grantSql);
//			} catch (SQLException e) {
//				throw new RuntimeException(e);
//			}
//		}
//		
//	}


	public int delete(String emp_no) {
		
		int rtn = 0;
		try {
			String sql = "delete from employee where emp_no='"+emp_no+"'";
			rtn = qr.update(sql);
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return rtn;
}		
	


	public Employee findById(String emp_no) {
		
		try {
			String sql = "select * from employee where emp_no=?";
			Map<String,Object> map = qr.query(sql,new MapHandler(),emp_no);
			return (Employee)map;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//查询所有员工
		public ArrayList<Employee> findAllEmployee(){
			try {
				String sql = "select * from employee";
				return (ArrayList<Employee>) qr.query(sql, new BeanListHandler<Employee>(Employee.class));
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}


		
		//修改电话、邮箱和地址
		public int update(String emp_no,String tel,String email,String address) {
			int rs = 0;
			String sql = "";
			try {
				if(tel != null) {
					sql = "update employee set emp_tel_num='"+tel+"' where emp_no='"+emp_no+"'";
				}
				else if(email != null) {
					sql = "update employee set emp_email='"+email+"' where emp_no='"+emp_no+"'";
				}
				else {
					sql = "update employee set emp_addr='"+address+"' where emp_no='"+emp_no+"'";
				}
				
				rs = qr.update(sql);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
			return rs;
			 
		}
		
	
}
