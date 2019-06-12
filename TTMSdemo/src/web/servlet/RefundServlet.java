package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.RefundService;

public class RefundServlet extends HttpServlet {

	private RefundService rs = new RefundService();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int rtn = 0;
		String res ="{\"res\":\"0\"}";
		String ticketId = request.getParameter("ticket_id");
		long ticket_id = Long.parseLong(ticketId);
		HttpSession hs = request.getSession();
		String username = (String)hs.getAttribute("username");
		rtn = rs.refund(ticket_id,username);
		if(rtn != 0) {
			res = "{\"res\":\"1\"}";
		}
		response.getWriter().print(res);
		response.getWriter().flush();
		response.getWriter().close();
		
	}

}
