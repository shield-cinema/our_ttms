package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmployeeService;
import model.Employee;
import net.sf.json.JSONObject;
/**
 * 增加人员，依赖service层
 * @author Administrator
 *
 */

public class AddEmployeeServlet extends HttpServlet {
	private EmployeeService employeeService = new EmployeeService();
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		int rs = 0;
		String res = "";
        String emp_no = req.getParameter("id");
        String emp_name = req.getParameter("name");
        String emp_tel_num = req.getParameter("tel");
        String emp_addr = req.getParameter("address");
        String emp_email = req.getParameter("email");
        String emp_identity = req.getParameter("identity");
		Employee emp = new Employee(emp_no,emp_name,emp_tel_num,emp_addr,emp_email,emp_identity);
		try {
		rs = employeeService.add(emp);
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
