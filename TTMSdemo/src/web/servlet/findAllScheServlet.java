package web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ScheDao;

import model.PlaySch;
import net.sf.json.JSONArray;
import service.PlaySchService;
import service.PlayService;

public class findAllScheServlet extends HttpServlet {
	
	private PlaySchService playSchService = new PlaySchService();
	private PlayService playService = new PlayService();
	private ScheDao scheDao = new ScheDao();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int play_id = Integer.parseInt(request.getParameter("id"));
		
//		产生一个1-50之间的随机数：int x=1+(int)(Math.random()*50)
		int min = scheDao.min();
		int max = scheDao.max();
		long s = playService.countPlay();
		int [] random = new int[4];
		for(int i=0;i<4;i++){
			random[i] = min+(int)(Math.random()*(max-min+1));
		}
		PlaySch playsch1= playSchService.find(play_id);
		PlaySch playsch2= playSchService.find(random[0]);
		PlaySch playsch3= playSchService.find(random[1]);
		PlaySch playsch4= playSchService.find(random[2]);
		PlaySch playsch5= playSchService.find(random[3]);
		List<PlaySch> list = new ArrayList<PlaySch>();
		list.add(playsch1);
		list.add(playsch2);
		list.add(playsch3);
		list.add(playsch4);
		list.add(playsch5);
		String str = JSONArray.fromObject(list).toString();
		
		response.getWriter().print(str);
		response.getWriter().flush();
		response.getWriter().close();
	}

}
