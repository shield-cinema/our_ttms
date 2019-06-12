package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ScheService;

public class DeleteScheServlet extends HttpServlet {
	private ScheService scheService = new ScheService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String id = request.getParameter("id");
		id = id.replace("A", "");
		String str = "";
		int flag = scheService.delete(Integer.parseInt(id));
		if(flag != 0){
			str = "{\"res\":\"1\"}"; 
		}
		else{
			str = "{\"res\":\"0\"}";
		}
		response.getWriter().print(str);
		response.getWriter().flush();
		response.getWriter().close();

	}

}
