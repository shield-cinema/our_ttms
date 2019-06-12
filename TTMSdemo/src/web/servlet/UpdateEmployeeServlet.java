package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmployeeService;



public class UpdateEmployeeServlet extends HttpServlet {
	
	private EmployeeService employeeService = new EmployeeService();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String res = "{\"res\":\"0\"}";
		int rs = 0;
		String emp_no = request.getParameter("id");//要更新人员的工号
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		try {
		
		rs = employeeService.update(emp_no,tel,email,address);
		if(rs != 0) {
			
			res = "{\"res\":\"1\"}";
		}
		response.getWriter().print(res);
		response.getWriter().flush();
		response.getWriter().close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
