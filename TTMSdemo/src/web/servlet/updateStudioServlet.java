package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudioDao;

import net.sf.json.JSONObject;

import model.Studio;

import service.StudioService;

public class updateStudioServlet extends HttpServlet {
	private StudioService studioService= new StudioService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String want = request.getParameter("want");
		
		if("addStudio".equals(want)){//增加演出厅
			String name = request.getParameter("name");
			int row = Integer.parseInt(request.getParameter("row"));
			int col = Integer.parseInt(request.getParameter("col"));
			String value = request.getParameter("val");
			Studio from = new Studio(name, row, col, value);
			int rs = studioService.addStudio(from);
			String str = "{\"res\":\"0\"}";
			if(rs!=0){//添加成功
				str = "{\"res\":1,\"id\":" + rs +  "}";
			}
	        response.getWriter().print(str);
	        response.getWriter().flush();
	        response.getWriter().close();
		}
		
		else if("changeRoom".equals(want)){//修改演出厅的行列
			int id = Integer.parseInt(request.getParameter("id"));
			String row = request.getParameter("row");
			String col = request.getParameter("col");
			int rs = 0;
			String str = "{\"res\":\"0\"}";
			if(row!=null){
				int row2 = Integer.parseInt(row);
				rs = studioService.updataRow(id, row2);
			}
			else if(col!=null){
				int col2 = Integer.parseInt(col);
				rs = studioService.updataCol(id, col2);
			}
			if(rs!=-1){//修改成功
				str = "{\"res\":1}";
			}
			response.getWriter().print(str);
	        response.getWriter().flush();
	        response.getWriter().close();
		}
		
		else if("removeRoom".equals(want)){//删除演出厅
			String str = "{\"res\":\"0\"}";
			int id = Integer.parseInt(request.getParameter("id"));
			int rs =studioService.delStudio(id);
			if(rs!=0){
				str = "{\"res\":1}";
			}
			response.getWriter().print(str);
	        response.getWriter().flush();
	        response.getWriter().close();
		}
		
		else if("studio".equals(want)){//查询所有演出厅
			String str = studioService.findAllStudio();
			 response.getWriter().print(str);
		     response.getWriter().flush();
		     response.getWriter().close();
		}
		
		else if("design".equals(want)){//演出厅座位设计
			int id = Integer.parseInt(request.getParameter("id"));
			int row = Integer.parseInt(request.getParameter("row"));
			int col =Integer.parseInt(request.getParameter("col"));
			String status = request.getParameter("list");
			Studio from = new Studio(id,row,col,status);
			int rs = studioService.designStudio(from);
			String str = "{\"res\":\"0\"}";
			if(rs!=0){//设计成功
				str = "{\"res\":1}";
			}
	        response.getWriter().print(str);
	        response.getWriter().flush();
	        response.getWriter().close();
		}

	}

}
