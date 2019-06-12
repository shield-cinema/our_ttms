package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmployeeService;
public class ShowServlet extends HttpServlet {

	private EmployeeService employeeService = new EmployeeService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String str = employeeService.findAllEmployee();
		response.getWriter().print(str);
		response.getWriter().flush();
		response.getWriter().close();
	}	
	

}
