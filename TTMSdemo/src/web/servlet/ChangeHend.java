package web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

import service.UserManageService;

public class ChangeHend extends HttpServlet {
	private UserManageService ums = new UserManageService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession hs = request.getSession();
		String username = (String)hs.getAttribute("username");
		String str = "{\"res\":0}";
		// 创建工厂
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		// 使用工厂创建解析器对象
		ServletFileUpload fileUpload = new ServletFileUpload(dfif);
		try {
			// 使用解析器对象解析request，得到FileItem列表
			List<FileItem> list = fileUpload.parseRequest(request);
			// 遍历所有表单项
			for(FileItem fileItem : list) {
			
				//如果当前表单项不是普通表单项，说明就是文件字段
					String name = fileItem.getName();//获取上传文件的名称
					// 如果上传的文件名称为空，即没有指定上传文件
					if(name == null || name.isEmpty()) {
						continue;
					}
					// 获取真实路径，对应${项目目录}/uploads，当然，这个目录必须存在
					String savepath = this.getServletContext().getRealPath("/WEB-INF/image");
					File file = new File(savepath, name);
					fileItem.write(file);
					savepath = savepath + "/" +name;
					byte[] bytes = IOUtils.toByteArray(new FileInputStream(savepath));
					ums.updateUserIcon(bytes, username);
					response.getWriter().print(str);
					response.getWriter().flush();
					response.getWriter().close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	response.getWriter().print(str);
        response.getWriter().flush();
        response.getWriter().close();
	}

}
