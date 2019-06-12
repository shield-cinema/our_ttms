package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ScheService;

public class TicketStatusServlet extends HttpServlet {
	private ScheService scheService = new ScheService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        
        int schedule_id = Integer.parseInt(request.getParameter("id"));
        String str = scheService.findStatus(schedule_id);
        response.getWriter().print(str);
		response.getWriter().flush();
		response.getWriter().close();
	}

}
