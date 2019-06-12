package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ShowUserInfoService;


public class ShowUserInfoServlet extends HttpServlet {

	private ShowUserInfoService su = new ShowUserInfoService();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession hs = request.getSession();
		String username = (String)hs.getAttribute("username");
		String info;
		info = su.show(username);
		response.getWriter().print(info);
		response.getWriter().flush();
		response.getWriter().close();
		
		
	}

}
