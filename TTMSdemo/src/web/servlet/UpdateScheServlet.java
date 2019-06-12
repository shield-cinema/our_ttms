package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Schedule;
import service.ScheService;

public class UpdateScheServlet extends HttpServlet {
	
	private ScheService scheService = new ScheService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String id = request.getParameter("id");
		String studio = request.getParameter("room");

		String time = request.getParameter("start");
		System.out.println(studio);
		
		Schedule sche = new Schedule(Integer.parseInt(id),Integer.parseInt(studio),time);
		
		String str = "";
		int flag = scheService.update(sche);
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
