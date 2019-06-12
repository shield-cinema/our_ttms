package web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Play;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import service.PlayService;
import dao.PlayDao;


public class AddPlayServlet extends HttpServlet {	
	private PlayService playService = new PlayService();
	private PlayDao playDao = new PlayDao();
	private Play pla = new Play();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	 
	// 因为要使用response打印，所以设置其编码
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
			
	// 创建工厂
	DiskFileItemFactory dfif = new DiskFileItemFactory();
	// 使用工厂创建解析器对象
	ServletFileUpload fileUpload = new ServletFileUpload(dfif);
	try {
		// 使用解析器对象解析request，得到FileItem列表
		List<FileItem> list = fileUpload.parseRequest(request);
		// 遍历所有表单项
		for(FileItem fileItem : list) {
			// 如果当前表单项为普通表单项
		
				// 获取当前表单项的字段名称
				String fieldName = fileItem.getFieldName();
				// 如果当前表单项的字段名为username
				if(fieldName.equals("time")) {
					String length = fileItem.getString("utf-8");
					pla.setPlay_length(Integer.parseInt(length));
				}
				if(fieldName.equals("name")) {
					String name1 = fileItem.getString("utf-8");
					pla.setPlay_name(name1);
				}
				if(fieldName.equals("director")) {
					String introduction = fileItem.getString("utf-8");
					pla.setPlay_introduction(introduction);
				}
				if(fieldName.equals("type")) {
					String type = fileItem.getString("utf-8");
					pla.setPlay_type(type);
				}
				if(fieldName.equals("language")) {
					String language = fileItem.getString("utf-8");
					pla.setPlay_language(language);
				}
		
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
				
				pla.setPlay_image(bytes);
				pla.setPlay_status(1);
				int flag = playService.add(pla);
				int id = playDao.findId(pla.getPlay_name());
				String str = "";
				if(flag != 0){
					str = "{\"res\":\"1\",\"num\":\""+ id + "\"}"; 
				}
				else{
					str = "{res:\"0\"}";
				}
				response.getWriter().print(str);
				response.getWriter().flush();
				response.getWriter().close();
		}
	} catch (Exception e) {
		//throw new ServletException(e);
		e.printStackTrace();
	} 
	
	}


}

