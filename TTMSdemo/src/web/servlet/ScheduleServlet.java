package web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import model.PlaySch;
import model.SchedMessage;
import service.PlaySchService;
import service.ScheService;

public class ScheduleServlet extends HttpServlet {
	private PlaySchService pss = new PlaySchService();
	private ScheService ss = new ScheService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String str = null;
		
		int play_id = Integer.parseInt(request.getParameter("id"));
		
		String scHedule = JSONArray.fromObject(
				ss.findSchedule(play_id)).toString();
		PlaySch playSch = pss.find(play_id);
		JSONObject map = new JSONObject();
		map.put("num", playSch.getNum());
		map.put("name", playSch.getName());
		map.put("img", playSch.getImg());
		map.put("director", playSch.getDirector());
		map.put("language", playSch.getLanguage());
		map.put("type", playSch.getType());
		map.put("data", playSch.getDate());
		map.put("list", scHedule);
		str = map.toString();
		response.getWriter().print(str);
		response.getWriter().flush();
		response.getWriter().close();
	}

}
