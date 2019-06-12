package dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import model.User;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class ShowUserInfoDAO{
	
	QueryRunner qr = new TxQueryRunner();
	public String show(String username) {
		
		String path1 = "E:/apache-tomcat-7." +
				"0.42/apache-tomcat-7.0.42/webapps/ttms/image/"+ username +".png";
		byte[] bytes = null;
		try {
			FileOutputStream fos = new FileOutputStream(path1);
			bytes =  findImg(username);
			if(bytes != null){
				fos.write(bytes);
				fos.flush();
				fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String sql1,sql2;
		String str = null;
		int index;
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		if(bytes!=null){
			sb.append("\"img\":\"http://localhost:8080/" +
					"ttms/image/" + username +".png\",");
		}else{
			sb.append("\"img\":\"http://localhost:8080/" +
					"ttms/image/zxc.png\",");
		}
		sb.append("\"img\":\"http://localhost:8080/" +
				"ttms/image/" + username +".png\",");
		sb.append("\"sex\":");
		try {
			sql1 = "SELECT sex,telphone FROM un WHERE username=?";
			Map m1 = qr.query(sql1, new MapHandler(),username);
			Map m2;
			String movie_name,movie_language,movie_type,movie_start;
			int movie_time;
			BigDecimal bd;
			long ticket_id;
			double ticket_price;
			int order_status;
			sb.append("\""+m1.get("sex")+"\",");
			sb.append("\"tel\":");
			sb.append("\""+m1.get("telphone")+"\",");
			sb.append("\"history\":[");
			sql2 = "SELECT movie_name,movie_language,movie_type,movie_time,ticket_price,"+
			"movie_start,ticket_id,order_status FROM customer_order WHERE username=?";
			List<Map<String, Object>> list = qr.query(sql2, new MapListHandler(),username);
			Iterator it = list.iterator();
			
			while(it.hasNext()) {
				m2 = (Map)it.next();
				movie_name = (String)m2.get("movie_name");
				movie_language = (String)m2.get("movie_language");
				movie_type = (String)m2.get("movie_type");
				movie_time = (Integer)m2.get("movie_time");
				bd = (BigDecimal)m2.get("ticket_price");
				ticket_price = bd.doubleValue();
				movie_start = m2.get("movie_start").toString();
				ticket_id = (Long)m2.get("ticket_id");
				order_status = (Integer)m2.get("order_status");
				sb.append("{\"name\":\""+movie_name+"\",");
				sb.append("\"language\":\""+movie_language+"\",");
				sb.append("\"type\":\""+movie_type+"\",");
				sb.append("\"time\":\""+movie_time+"\",");
				sb.append("\"start\":\""+movie_start+"\",");
				sb.append("\"ticket\":\""+ticket_price+"\",");
				sb.append("\"ticketId\":\""+ticket_id+"\",");
				sb.append("\"status\":\""+order_status+"\"");
				sb.append("},");
			}
			index = sb.lastIndexOf(",");
			str = sb.substring(0, index);
			str += "]}";
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return str;
	}
	public byte[] findImg(String username){
		byte[] bytes = null;
		try{
			String sql = "select * from un where username='" + username  +"'";
			User us = qr.query(sql, new BeanHandler<User>(User.class));
			bytes = us.getIcon();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

}
