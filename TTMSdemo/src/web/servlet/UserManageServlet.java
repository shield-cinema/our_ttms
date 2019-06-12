package web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import service.UserManageService;


public class UserManageServlet extends HttpServlet {

	UserManageService ums = new UserManageService();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession hs = request.getSession();
		String username = (String)hs.getAttribute("username");
		
//		ums.updateUser(want);
		// 创建工厂
//		DiskFileItemFactory dfif = new DiskFileItemFactory();
		// 使用工厂创建解析器对象
//		ServletFileUpload fileUpload = new ServletFileUpload(dfif);
//		String password,tel,sex;
//		try {
//			List<FileItem> list = fileUpload.parseRequest(request);
//			for(FileItem fileItem : list) {
//				String fieldName = fileItem.getFieldName();
//				if("password".equals(fieldName)) {
//					password = fileItem.getString("utf-8");
//					ums.updateUser("password",password, username);
//				}
//				else if("telphone".equals(fieldName)) {
//					tel = fileItem.getString("utf-8");
//					ums.updateUser("tel", tel, username);
//				}
//				else if("sex".equals(fieldName)) {
//					sex = fileItem.getString("utf-8");
//					ums.updateUser("sex", sex, username);
//				}
//				
//				String name = fileItem.getName();//获取上传文件的名称
//				// 如果上传的文件名称为空，即没有指定上传文件
//				if(name == null || name.isEmpty()) {
//					continue;
//				}
//				// 获取真实路径，对应${项目目录}/uploads，当然，这个目录必须存在
//				String savepath = this.getServletContext().getRealPath("/WEB-INF/image");
//				File file = new File(savepath, name);
//				fileItem.write(file);
//				savepath = savepath + "/" +name;
//				byte[] bytes = IOUtils.toByteArray(new FileInputStream(savepath));
//				ums.updateUserIcon(bytes, username);
		String want = request.getParameter("want");
		String str = "{\"res\":0}";
		int rs = -1;
			if(want.equals("password")){
				rs = ums.updateUser("password",request.getParameter("password"), username);
				str = "{\"res\":1}";
			}
			else if (want.equals("sex")){
				rs = ums.updateUser("sex", request.getParameter("sex"), username);
				str = "{\"res\":1}";
			}
			else if (want.equals("telephone")){
				rs = ums.updateUser("telephone", request.getParameter("telephone"), username);
				str = "{\"res\":1}";
			}
			response.getWriter().print(str);
	        response.getWriter().flush();
	        response.getWriter().close();
	}

}
