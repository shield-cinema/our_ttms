package idao;

import java.util.ArrayList;

import model.Employee;



public interface iEmployeeDAO {
	public int insert(Employee emp);
	public int delete(String emp_no);
	public int update(String emp_no, String tel, String email, String adress);
}
