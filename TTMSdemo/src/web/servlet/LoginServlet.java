package web.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.User;

import service.AdminService;
import service.UserService;

public class LoginServlet extends HttpServlet {
	private UserService userService = new UserService();
	private AdminService adminService = new AdminService();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String str = "{\"res\":0}";
        HttpSession session = request.getSession(); 
        String sessionID = null;
        String user = request.getParameter("formdata2");
        String username= request.getParameter("username");
        String password = request.getParameter("possword");
        String id = request.getParameter("id");
        
        if(id.equals("user")){//用户登录
        	User form = new User(username,password);
        	int rs = userService.login(form);
        	if(rs == 1){
        		str = "{\"res\":1}";
        		session.setAttribute("username", username);
        		String sessionId = session.getId();
        	}
        }
        else if(id.equals("manger")){//管理员登录
        	Admin form = new Admin(username,password);
        	int rs = adminService.login(form);
        	if(rs == 1){
        		str = "{\"res\":2}";
        		session.setAttribute("username", username);
        		String sessionId = session.getId();
        	}
        }
        response.getWriter().print(str);
        response.getWriter().flush();
        response.getWriter().close();
	}
}
