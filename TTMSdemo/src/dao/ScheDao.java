package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Play;
import model.Schedule;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class ScheDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/*
	 * 新建演出计划
	 * 
	 * */
	public int insert(Schedule sche){
		int s = 0;
		try {
			String sql = "insert into schedule(sched_id,studio_id,play_id,sched_time,sched_ticket_price)"
					+ " values(?,?,?,?,?)";
			
			Object[] pa = {
					sche.getSched_id(),sche.getStudio_id(),sche.getPlay_id(),
					sche.getSched_time(),sche.getSched_ticket_price()
					};
			s = qr.update(sql, pa);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/*
	 * 修改演出计划 
	 * 
	 * */
	public int update(Schedule sche){
		int s = 0;
		
		try {
			String sql = "update schedule set studio_id=?,sched_time=?" +
						" where sched_id=?";
			Object[] pa = {
					sche.getStudio_id(),sche.getSched_time(),sche.getSched_id()
					};
			s = qr.update(sql, pa);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/*
	 * 删除演出计划
	 * 
	 * */
	public int delete(int id){
		int s = 0;
		try {
			String sql = "delete from schedule where sched_id='" + id +"'";
			s = qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/*
	 * 通过影片ID返回相应的演出计划
	 * 
	 * */
	public ArrayList<Schedule> findAll(int id){
		try {
			String sql ="select * from schedule where play_id=" + id;
			
			return (ArrayList<Schedule>) qr.query(sql, new BeanListHandler<Schedule>(Schedule.class));
		} catch (SQLException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 返回相应的演出计划的ID
	 * 
	 * */
	public int findId(Schedule sche){
		int s = 0;
		try {
			String sql = "select * from schedule where play_id=? and studio_id=? and sched_time=? and sched_ticket_price=?";
			Schedule li =qr.query(sql, new BeanHandler<Schedule>(Schedule.class),sche.getPlay_id(),sche.getStudio_id(),sche.getSched_time(),sche.getSched_ticket_price());
			s = li.getSched_id();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return s;
	}
	
	/*
	 * 通过传入的演出厅ID返回对应厅内演出计划的开始时间
	 * 
	 * */
	public ArrayList<Schedule> findTime(int id){
		try {
			String sql = "select * from schedule where studio_id=" + id;
			return (ArrayList<Schedule>) qr.query(sql, new BeanListHandler<Schedule>(Schedule.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 返回所有的演出计划信息
	 * 
	 * */
	public ArrayList<Schedule> find(){
		try {
			String sql = "select * from schedule";
			return (ArrayList<Schedule>) qr.query(sql, new BeanListHandler<Schedule>(Schedule.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Schedule findById(int id){//SchDao
		try {
			String sql = "SELECT *,MIN(Sched_time) FROM"
					+ " SCHEDULE GROUP BY play_id HAVING play_id=?";
			return qr.query(sql,
					new BeanHandler<Schedule>(Schedule.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map findStatus(int schedule_id){
		Map str = null;
		try{
			String sql = "SELECT studio_row_count," +
					"studio_col_count,studio_status " +
					"FROM studio,SCHEDULE WHERE" +
					" studio.studio_id = SCHEDULE.studio_id" +
					" AND SCHEDULE.sched_id='" + schedule_id + "'";
			str = qr.query(sql, new MapHandler());
		}
		 catch (SQLException e) {
				e.printStackTrace();
			}
		return str;
	}
	
	public List<Map<String,Object>> findSchedule(int play_id){
		List<Map<String,Object>>  list = null;
		try {
			String sql ="SELECT sched_id,studio_id," +
					"sched_ticket_price,sched_time" +
					" FROM SCHEDULE WHERE play_id =?";
			list = qr.query(sql,new MapListHandler(),play_id);
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int min(){
		
		try {
			String sql ="SELECT MIN(play_id) FROM SCHEDULE";
			return (Integer)qr.query(sql,new ScalarHandler());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int max(){
		
		try {
			String sql ="SELECT MAX(play_id) FROM SCHEDULE";
			return (Integer)qr.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
