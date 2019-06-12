package service;

import java.util.ArrayList;

import model.Employee;

import dao.EmployeeDAO;

public class EmployeeService {
	
	private EmployeeDAO empDAO = new EmployeeDAO();
	
	public int add(Employee form) {
		Employee emp = empDAO.findById(form.getEmp_no());
		if(emp != null) {
			return 0;
		}
		return empDAO.insert(form);
	}
	
	public int delete(String emp_no) {
		
		return empDAO.delete(emp_no);
	}

	/*
	 * data=[
                    {
                        identity:'xxx',
                        id:'3',
                        name:'xxxxx',
                        tel:'18392120131',
                        email:'123@qq.com',
                        address:'xxxxxxx'
                    },
                    {
                        identity:'xxx',
                        id:'4',
                        name:'xxxxx',
                        tel:'18392120131',
                        email:'123@qq.com',
                        address:'xxxxxxx'
                    }

                ]
	 */
	public String findAllEmployee() {
		
		String str = "";
		StringBuilder sb = new StringBuilder();
		ArrayList<Employee> list = empDAO.findAllEmployee();
		
		try {
			sb.append("[");
			for(int i = 0;i <list.size();i++ ) {
				sb.append("{");
				sb.append("\"identity\":\""+list.get(i).getEmp_identity()+"\",");
				sb.append("\"id\":\""+list.get(i).getEmp_no()+"\",");
				sb.append("\"name\":\""+list.get(i).getEmp_name()+"\",");
				sb.append("\"tel\":\""+list.get(i).getEmp_tel_num()+"\",");
				sb.append("\"email\":\""+list.get(i).getEmp_email()+"\",");
				sb.append("\"address\":\""+list.get(i).getEmp_addr()+"\"}");
				if(i != list.size() - 1) {
					sb.append(",");
				}
			}
			sb.append("]");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		str = sb.toString();
		return str;
	}

	public int update(String emp_no, String tel,String mail,String address) {
		return empDAO.update(emp_no,tel,mail,address);
	}
}
