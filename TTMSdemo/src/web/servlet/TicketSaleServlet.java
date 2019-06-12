package web.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TicketSaleService;

public class TicketSaleServlet extends HttpServlet {

	private TicketSaleService sts = new TicketSaleService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String status = request.getParameter("status");
		System.out.println("status:"+status);
		StringBuilder sb = new StringBuilder();
//		int[] ro = {0,0,0,0,0};
//		int[] co = {0,0,0,0,0};
//		String[] str_ro = {null,null,null,null,null}; 
//		String[] str_co = {null,null,null,null,null};
		long ticket_id = 0;
		int row = 0;
		int column = 0;
		String play_Id = request.getParameter("playId");
		String sched_Id = request.getParameter("scheduleId");
		System.out.println(request.getParameter("scheduleId"));
		int sched_id = Integer.parseInt(sched_Id);
		int play_id = Integer.parseInt(play_Id);
		String user = request.getParameter("user");
		int rs = 0;
		int i = 0;
		if("lock".equals(status)) {
			
			row = Integer.parseInt(request.getParameter("row"));
			column = Integer.parseInt(request.getParameter("col"));
			rs = sts.lock(row,column,play_id,sched_id,user);
			
		}
		else if("unlock".equals(status)) {
			row = Integer.parseInt(request.getParameter("row"));
			column = Integer.parseInt(request.getParameter("col"));
			rs = sts.unlock(row,column,play_id,sched_id,user);
		}
		else {
			
//				str_ro = request.getParameterValues("row");
//				str_co = request.getParameterValues("col");
//				
//				for(int k = 0;k < str_ro.length;k++) {
//					ro[k] = Integer.parseInt(str_ro[k]);
//					co[k] = Integer.parseInt(str_co[k]);
//				}
				
			
				
			row = Integer.parseInt(request.getParameter("row"));
			column = Integer.parseInt(request.getParameter("col"));
				rs = sts.buy(row,column,play_id,sched_id,user);
				if(rs == -1) {
					response.getWriter().print("{\"res\":\"-1\"}");
					response.getWriter().flush();
					response.getWriter().close();
					
				}
				else {
					
					sts.addCount(play_id);
					ticket_id = sts.getTicket_ID(row,column,play_id,sched_id);
					sb.append("{\"res\":\"1\"");
					sb.append(",\"ticketId\":[");
//					for(int j = 0;i < 5 && ticket_id[j] != 0;j++) {
						sb.append("\""+ticket_id+"\"");
//					}
//					int index = sb.lastIndexOf(",");
//					String str = sb.substring(0, index);
					String str;
					str = sb.toString();
					str += "]}";
					
//					response.getWriter().println("{\"res\":\"1\"}");
					response.getWriter().println(str);
					
					response.getWriter().flush();
					response.getWriter().close();
	//				request.setAttribute("ticket_id", ticket_id);
	//				request.getRequestDispatcher("").forward(request, response);
				}
		}
		
	}

}
