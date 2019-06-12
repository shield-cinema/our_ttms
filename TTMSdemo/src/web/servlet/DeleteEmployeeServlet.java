package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmployeeService;

import net.sf.json.JSONObject;
public class DeleteEmployeeServlet extends HttpServlet {
	
	private EmployeeService employeeService = new EmployeeService();
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String emp_no = req.getParameter("id");
		int rs = 0;
		String res = "{\"res\":\"0\"}";
		try {
		rs = employeeService.delete(emp_no);
		if(rs != 0) {
			res = "{\"res\":\"1\"}";
		}
		resp.getWriter().print(res);
		resp.getWriter().flush();
		resp.getWriter().close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
