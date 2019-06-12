package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialBlob;

import model.Play;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;

/**
 * 依赖数据文件
 * @author Administrator
 *
 */
public class PlayDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/*
	 * 增加剧目
	 * 
	 * */
	public int insert(Play pla) {
		int s = 0;
		try {
			String sql = "insert into play(play_id,play_type,play_language,play_name,play_introduction,play_image,play_length,play_status)"
					 + "values(?,?,?,?,?,?,?,?)";
			Object[] params = {
					pla.getPlay_id(),pla.getPlay_type(),pla.getPlay_language(),pla.getPlay_name(),pla.getPlay_introduction(),new SerialBlob(pla.getPlay_image()),pla.getPlay_length(),
					pla.getPlay_status()
					};
			s = qr.update(sql, params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return s;
	}

	/*
	 * 修改剧目状态
	 * 
	 * */
	public int update(int id) {
		int s=0;
		try{
		String sql = "update play set play_status = -1";
		sql += " where play_id = " + id;
		s = qr.update(sql);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return s;
	}
	
	/*
	 * 以List返回所有的影片信息
	 * 
	 * */
	public ArrayList<Play> findAll(){
		try {
			String sql = "select * from play";
			return (ArrayList<Play>) qr.query(sql, new BeanListHandler<Play>(Play.class));
		} catch (SQLException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 通过影片名称得到影片id
	 * 
	 * */
	public int findId(String name){
		int s = 0;
		try {
		
			String sql = "select * from play where play_name =?"; 
			Play li =qr.query(sql, new BeanHandler<Play>(Play.class),name);
			s = li.getPlay_id();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
	/*
	 * 通过play_id得到影片时长
	 * 
	 * */
	public int findLength(int id){
		int s = 0;
		try {
			String sql = "select * from play where play_id=?";
			Play li = qr.query(sql, new BeanHandler<Play>(Play.class),id);
			s = li.getPlay_length();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/*
	 * 卖出票后，票房加1
	 * 
	 * */
	public int addCount(int id){
		int s = 0;
		try {
			String sql = "update play set ticketCount= ticketCount+1 where play_id=" + id;
			s = qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/*
	 * 退票后，票房减1
	 * 
	 * */
	public int subtractCount(int id){
		int s = 0;
		try {
			String sql = "update play set ticketCount= ticketCount-1 where play_id=" + id;
			s = qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/*
	 * 通过play_id得到票房数
	 * 
	 * */
	public int getCount(int id){
		int s = 0;
		try {
			String sql = "select * from play where play_id=?";
			Play li = qr.query(sql, new BeanHandler<Play>(Play.class),id);
			s = li.getTicketCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	public Play findByID(int play_id){//play
		try {
			String sql = "select * from play where play_id='" + play_id  +"'";
			return qr.query(sql, new BeanHandler<Play>(Play.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 统计电影数目
	 * @param play_id
	 * @return
	 */
	public long  countPlay(){//play
		long s = 0;
		try {
			String sql = "SELECT COUNT(*) FROM play";
			s= (Long)qr.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	public List<Map<String, Object>> getCount2(){
		List<Map<String, Object>> map = null;
		try {
			String sql = "select play_name,ticketCount from play";
			map = qr.query(sql, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
