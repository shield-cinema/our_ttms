package web.servlet;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;


import service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class RegistServlet extends HttpServlet {

	private UserService userService = new UserService();
	//注册功能
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取前台data数据中的
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("formdata2");
        String username= request.getParameter("username");
        String password = request.getParameter("possword");
        String telphone = request.getParameter("telphone");
        User form = new User(password,username,telphone);
		int rs = userService.regist(form);
		String str = "{\"res\":\"0\"}";
		if(rs == 1){
			str = "{\"res\":\"1\"}";
		}else{
			str = "{\"res\":\"0\"}";

		}
        response.getWriter().print(str);
        response.getWriter().flush();
        response.getWriter().close();
    }
    
}
