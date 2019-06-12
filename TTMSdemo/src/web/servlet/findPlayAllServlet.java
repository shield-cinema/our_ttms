package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Play;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import service.PlayService;

//@WebServlet("/findPlayAllServlet")
public class findPlayAllServlet extends HttpServlet {
	private PlayService playService = new PlayService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		

		String s = playService.findAll();
	    response.getWriter().print(s);
	    response.getWriter().flush();
	    response.getWriter().close();
		}
}


