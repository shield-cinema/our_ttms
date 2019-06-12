package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Play;
import net.sf.json.JSONObject;
import service.PlayService;

public class updatePlayServlet extends HttpServlet {
	private PlayService playservice = new PlayService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String num = request.getParameter("num");
		String str = "";
		
		int flag = playservice.update(Integer.parseInt(num));
			if(flag != 0){
				str = "{\"res\":\"1\"}"; 
			}
			else{
				str = "{res:\"0\"}";
			}
			response.getWriter().print(str);
			response.getWriter().flush();
			response.getWriter().close();
		}
}

