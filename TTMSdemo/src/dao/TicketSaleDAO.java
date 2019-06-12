package dao;

import idao.iTicketSaleDAO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class TicketSaleDAO implements iTicketSaleDAO {

	private QueryRunner qr = new TxQueryRunner();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public int lock(int row ,int column,int play_id,int sched_id,String user) {
		int rs = 0;
		long ticket_id = getTicket_ID(row,column,play_id,sched_id);
		long currentMills = System.currentTimeMillis();
		long limitedMills = currentMills + 900000;//加锁时间(15分钟)
		String time = sdf.format(new Date(limitedMills)).toString();
		String sql1,sql2;
		String sql ="";
		try {
			sql1 = "UPDATE ticket SET ticket_status=1 WHERE ticket_id="+ticket_id+" AND sched_id="+
					sched_id;
			sql2 = "UPDATE ticket SET ticket_locked_time='"+time+"' WHERE ticket_id="+ticket_id+" AND sched_id="+
					sched_id;
			rs = qr.update(sql1);
			rs += qr.update(sql2);
//			sql = "CALL ticket_lock("+ticket_id+","+sched_id+","+time+")";
//					rs = qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	private int getCustomer_ID(String user) {
		int id = -1;
		String sql = "";
		try {
			sql = "SELECT id FROM un WHERE username=?";
			Map m = qr.query(sql, new MapHandler(),user);
			id = (Integer)m.get("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public long getTicket_ID(int row, int column,int play_id,int sched_id) {
		long ticket_id = 0;
		String sql,sql1,slq2,sql3;
		try {
			sql = "SELECT ticket_id FROM ticket  WHERE sched_id ="+sched_id+" AND seat_id IN("+
					"SELECT seat_id FROM seat WHERE studio_id IN("+
							"SELECT studio_id FROM SCHEDULE WHERE play_id="+play_id+") AND seat_row="+row+" AND seat_column="+column+")";
			
			
			Map m = qr.query(sql, new MapHandler());
			ticket_id = (Long)m.get("ticket_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticket_id;
	}
	
//	public long[] getTicket_ID(int[] row, int[] column) {
//		long[] ticket_id = {0,0,0,0,0};
//		String sql = "";
//		try {
//			
//			for (int i = 0; i < 5 && row[i] != 0 ; i++) {
//				
//				sql = "SELECT ticket_id FROM ticket WHERE seat_id="+
//						"(SELECT seat_id FROM seat WHERE seat_row=? AND seat_column=?)";
//				Map m = qr.query(sql, new MapHandler(),row[i],column[i]);
//				ticket_id[i] = (Long)m.get("ticket_id");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return ticket_id;
//	}
	
	
	public int unlock(int row ,int column,int play_id,int sched_id,String user) {
		
		int rs = 0;
		long ticket_id = getTicket_ID(row,column,play_id,sched_id);
		String sql1,sql2;
		String sql = "";
		try {
			sql1 = "UPDATE ticket SET ticket_status=0 WHERE ticket_id="+ticket_id+" AND sched_id="+
					sched_id;
			
			sql2 = "UPDATE ticket SET ticket_locked_time='2020-01-01 00:00:00' WHERE ticket_id="+ticket_id+" AND sched_id="+
					sched_id;
			rs = qr.update(sql1);
			rs += qr.update(sql2);
//			sql = "CALL ticket_unlock("+ticket_id+","+sched_id+")";
//			qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public int buy(int row , int column, int play_id, int sched_id, String user) {
	
		if(judgeIsOvertime(row, column,play_id,sched_id)) {
		
		int rs = 0;
		String sql1,sql2;
//		String sql = "";
		long ticket_id = getTicket_ID(row,column,play_id,sched_id);
		int customer_id = getCustomer_ID(user);
		long sale_ID = 0;
//		double price = 0;
		try {
//			for (int i = 0; i < 5 && ticket_id[i] != 0 ; i++) {
				
				sql1 = "UPDATE ticket SET ticket_status=9 WHERE ticket_id="
						+ ticket_id + " AND sched_id=" + sched_id;
				sql2 = "UPDATE ticket SET ticket_locked_time='1900-01-01 00:00:00' " 
						+ "WHERE ticket_id=" + ticket_id + " AND sched_id="
						+ sched_id;
				rs = qr.update(sql1);
				rs += qr.update(sql2);
//				sql = "CALL ticket_buy("+ticket_id+","+sched_id+")";
//				rs = qr.update(sql);
				
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
					
		insertSale(ticket_id,sched_id,customer_id);
		sale_ID = getSale_ID(customer_id);
		insertSale_Item(ticket_id,sale_ID);
		insertOrder(play_id, sched_id,ticket_id,user);
		return rs;
		}
		
		else {
			return -1;//表示超时
		}
	}
	
	private void insertOrder(int play_id, int sched_id, long ticket_id, String user) {
		
		String sql,sql1,sql2,sql3;
		try {
			sql1 = "SELECT play_name,play_language,play_type,play_length FROM play WHERE play_id=?";
			Map m1 = qr.query(sql1, new MapHandler(),play_id);
			String play_name = (String)m1.get("play_name");
			String play_language = (String)m1.get("play_language");
			String play_type = (String)m1.get("play_type");
			int movie_time = (Integer)m1.get("play_length");
			
			sql2 = "SELECT sched_time FROM SCHEDULE WHERE sched_id=?";
			Map m2 = qr.query(sql2, new MapHandler(),sched_id);
			String movie_start = m2.get("sched_time").toString();
			BigDecimal bd;
//			double ticket_price = bd.doubleValue();
			double ticket_price = 0;
			//每张的价格
			Map m3;
//			for (int i = 0; i < ticket_id.length && ticket_id[i] != 0; i++) {
				sql3 = "SELECT ticket_price FROM ticket WHERE ticket_id=?";
				m3 = qr.query(sql3, new MapHandler(),ticket_id);
				bd = (BigDecimal)m3.get("ticket_price");
				ticket_price = bd.doubleValue();
				Object[] params = {user,play_name,play_language,play_type,movie_time,ticket_price,movie_start,ticket_id,1};
				sql = "INSERT INTO customer_order(username,movie_name,movie_language,movie_type,movie_time,"+
						"ticket_price,movie_start,ticket_id,order_status) VALUES(?,?,?,?,?,?,?,?,?)";
				qr.update(sql, params);
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//判断是否超时，超时则不能支付
	private boolean judgeIsOvertime(int row ,int column,int play_id,int sched_id) {
		
		long ticket_id = getTicket_ID(row,column,play_id,sched_id);
		String sql = "";
		String ticket_locked_time;
		long currentMills = System.currentTimeMillis();
		long lockedMills = 0;
		try {
			sql = "SELECT ticket_locked_time FROM ticket WHERE ticket_id=?";
			Map m = qr.query(sql, new MapHandler(),ticket_id);
			ticket_locked_time = m.get("ticket_locked_time").toString();
			lockedMills = sdf.parse(ticket_locked_time).getTime();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(lockedMills > currentMills) {
			return true;
		}
		else 
			return false;
		
	}

	//向sale_item表中添加一条记录
	private void insertSale_Item(long ticket_id,long sale_ID) {

		String sql = "";
		double sale_item_price = getPriceArray(ticket_id);
		
		try {
//				for (int i = 0; i < 5 && ticket_id[i] != 0; i++) {
					sql = "INSERT INTO sale_item(ticket_id,sale_ID,sale_item_price) VALUES("
							+ ticket_id
							+ ","
							+ sale_ID
							+ ","
							+ sale_item_price
							+ ")";
					qr.update(sql);
//				}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		
	}
	
	private double getPriceArray(long ticket_id) {
		
		BigDecimal price;
		double ticket_price = 0;
		String sql = "";
		try {
//			for (int i = 0; i < 5 && ticket_id[i] != 0; i++) {
				sql = "SELECT ticket_price FROM ticket WHERE ticket_id=?";
				Map m = qr.query(sql, new MapHandler(), ticket_id);
				price= (BigDecimal) m.get("ticket_price");
				ticket_price = price.doubleValue();
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		for (int i = 0; i < 5 && ticket_id[i] != 0; i++) {
//			ticket_price[i] = price[i].doubleValue();
//		}
		
		return ticket_price;
	}
	
	private long getSale_ID(int customer_id) {
		
		String sql = "";
		long sale_ID = 0;
		sql = "SELECT sale_ID FROM sale WHERE id=?";
		try {
			Map m = qr.query(sql, new MapHandler(),customer_id);
			sale_ID = (Long)m.get("sale_ID");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sale_ID;
	}

	//向sale表中添加一条记录
	private void insertSale(long ticket_id, int sched_id,int customer_id) {
		
		double price = getPrice(ticket_id);	//获取票价
		String sql = "";
		String sale_time = sdf.format(new Date());
		double sale_change = 0;
		int sale_type = 1;	//1:销售单  -1:退款单
		int sale_status = 1;	//0:待付款  1:已付款

		try {
			sql = "INSERT INTO sale(id,sale_time,sale_payment,sale_change,sale_type,sale_status)"+
					"VALUES("+customer_id+",'"+sale_time+"',"+price+","+sale_change+","+sale_type+","+sale_status+")";
			qr.update(sql);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	private double getPrice(long ticket_id) {
		
		BigDecimal[] price = new BigDecimal[5];
		double[] ticket_price = new double[5];
		double price_sum = 0;
		String sql = "";
		try {
//			for (int i = 0; i < 5 && ticket_id[i] != 0; i++) {
				sql = "SELECT ticket_price FROM ticket WHERE ticket_id=?";
				Map m = qr.query(sql, new MapHandler(), ticket_id);
				price_sum= ((BigDecimal) m.get("ticket_price")).doubleValue();
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		for (int i = 0; i < 5 && ticket_id[i] != 0; i++) {
//			ticket_price[i] = price[i].doubleValue();
//			price_sum += ticket_price[i];
//		}
		
		return price_sum;
	}

	
}
