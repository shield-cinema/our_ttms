package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PlayService;

import dao.PlayDao;

public class CountServlet extends HttpServlet {
	private PlayService playService = new PlayService();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
	     response.setContentType("text/html;charset=UTF-8");
	     String str = playService.CountTicket();
	     response.getWriter().print(str);
	     response.getWriter().flush();
	     response.getWriter().close();
	}

}
