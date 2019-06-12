package dao;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import model.Studio;
import model.Ticket;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.itcast.jdbc.TxQueryRunner;


public class TicketDao {
	private QueryRunner qr = new TxQueryRunner();
	/**
	 * 根据演出计划ID获取票价
	 * @param id
	 * @return 返回票价
	 */
	public double getTicketPrice(int id){
		try{
			String sql = "select * from ticket where sched_id=?";
			Ticket ticket = qr.query(sql, new BeanHandler<Ticket>(Ticket.class), id);
			double price = ticket.getTicket_price();
			return price;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据座位ID获取票价
	 * @param id
	 * @return 返回票价
	 */
	public double getByseatPrice(int id){
		try{
			String sql = "select * from ticket where seat_id=?";
			Ticket ticket = qr.query(sql, new BeanHandler<Ticket>(Ticket.class), id);
			double price = ticket.getTicket_price();
			return price;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据票ID获取票的状态
	 * @param id
	 * @return 0：待销售  1：锁定  9：卖出
	 */
	public int getTicketStatus(int id){
		try{
			String sql = "select * from ticket where ticket_id=?";
			return qr.query(sql, new BeanHandler<Ticket>(Ticket.class), id)
					.getTicket_status();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据演出计划获取票的状态
	 * @param sched_id
	 * @return 票价链表
	 */
	public ArrayList<Ticket> getPrice(int sched_id){
		try{
			String sql = "select * from ticket where sched_id=?";
			return (ArrayList<Ticket>) qr.query(sql, 
					new BeanListHandler<Ticket>(Ticket.class), sched_id);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
/*	/**
	 * 根据票的ID修改锁时间
	 * @param time
	 * @param id
	 *//*
	public void updateTime(Date time,int id){
		try{
			String sql = "UPDATE ticket SET " +
					"ticket_locked_time = ? WHERE ticket_id = ?";
			Object[] params = {time,id};
			qr.update(sql, params);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}*/
	/**
	 * 生成票
	 * @param sched_id
	 * @param seatID
	 * @param ticket_price
	 * @return 成功返回1，失败返回0
	 */
	public int creatTicket(int sched_id,int[] seatID,double ticket_price,int length){
		int rs = 0;
		try{
			String sql = "insert into ticket(seat_id,sched_id,ticket_price," +
					"ticket_status) values(?,?,?,?)";
			for(int i=0;i<length;i++){
				Object[] params = {seatID[i],sched_id,ticket_price,0};
				rs = qr.update(sql, params);
			}	
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return rs;
	}
	/**
	 * 修改票价
	 * @param sched_id
	 * @param ticket_price
	 * @return 失败返回0，成功返回正数
	 */
	public int updatePrice(int sched_id,double ticket_price){
		try{
			String sql = "update ticket set ticket_price=? where sched_id=?";
			Object[] params = {ticket_price,sched_id};
			return qr.update(sql, params);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 修改票的状态
	 * @param ticket_id
	 * @param ticket_status
	 * @return 成功返回1，失败返回0。
	 */
	public int updateStatus(int ticket_id,int ticket_status){
		try{
			String sql = "update ticket set ticket_status=? where ticket_id=?";
			Object[] params = {ticket_status,ticket_id};
			return qr.update(sql, params);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查询票的锁的时间
	 * @param ticket_id
	 * @return 返回SQL时间
	 */
	public Date findLockTime(int ticket_id){
		Date s=null;
		try{
			String sql = "select locked_time from ticket where ticket_id=?";
			Ticket ticket = qr.query(sql, new BeanHandler<Ticket>(Ticket.class),ticket_id);
			if(ticket!=null){
				s=ticket.getLocked_time();
			}
			return s;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 修改锁的时间
	 * @param ticket_id
	 * @param locked_time
	 * @return
	 */
	public int udateLockTime(int ticket_id,Date locked_time){
		try{
			String sql = "update ticket set locked_time=? where ticket_id=?";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String timeLock = sdf.format(locked_time);
			Object[] params = {timeLock,ticket_id};
			return qr.update(sql, params);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 删除票
	 * @param sched_id
	 * @return 失败返回0，成功返回正数
	 */
	public int delTicket(int sched_id){
		try{
			String sql = "delete from ticket where sched_id='" + sched_id +"'";
			return qr.update(sql);
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查询票的状态
	 * @param sched_id
	 * @return
	 */
	public String findTicketStatus(int sched_id){
		String str = "";
		try{
			String sql = "SELECT ticket_status FROM " +
					"ticket WHERE sched_id='" + sched_id +"'";
			List<Map<String, Object>> list = qr.query(sql, new MapListHandler());
			int[] status = new int[500];
			int count = 0;
			int i = 0;
			for(Map<String, Object> li :list){
				status[i] =  (Integer)li.get("ticket_status");
				i++;
				count++;
			}
			str = "" + status[0];
			for(int j = 1; j < count; j++){
				str = str + "," + status[j];
			}
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return str;
	}
}
