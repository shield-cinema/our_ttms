package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ScheDao;
import model.Schedule;
import service.ScheService;

public class AddScheServlet extends HttpServlet {

	private ScheService scheService = new ScheService();
	private ScheDao scheDao = new ScheDao();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String play_id = request.getParameter("num");
		String studio_id = request.getParameter("room");
		studio_id = studio_id.replace("A", "");
		String time = request.getParameter("start");
		String price = request.getParameter("ticket");
	
		Schedule sche = new Schedule(Integer.parseInt(studio_id),Integer.parseInt(play_id)
				,time,Integer.parseInt(price));
		String str = "";
		int flag = scheService.add(sche);
		if(flag != 0){
			int id = scheDao.findId(sche);
			str = "{\"res\":\"1\",\"id\":\"A"+ id + "\"}"; 
		}
		else{
			str = "{\"res\":\"0\"}";
		}
		response.getWriter().print(str);
		response.getWriter().flush();
		response.getWriter().close();
	}
	

}
