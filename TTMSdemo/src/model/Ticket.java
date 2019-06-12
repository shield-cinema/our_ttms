package model;

import java.util.Date;

public class Ticket {
	private long ticet_id;
	private int seat_id;
	private int sched_id;//{row:,col:,ticket_status:0}
	private double ticket_price;
	private short ticket_status;//0:待销售	1：锁定	9：卖出	
	private Date locked_time;
//	将Date类型的值通过SimpleDateFormat类转换成”yyyy-MM-dd HH:mm:ss”这样的字符串
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	Date now = new Date();
//	String abcValue = sdf.format(now);
//	String sql = "update table set abc='"+abcValue+"' where ......";
//	再执行SQL就OK了
	public long getTicet_id() {
		return ticet_id;
	}
	public void setTicet_id(long ticet_id) {
		this.ticet_id = ticet_id;
	}
	public int getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}
	public int getSched_id() { 
		return sched_id;
	}
	public void setSched_id(int sched_id) {
		this.sched_id = sched_id;
	}
	public double getTicket_price() {
		return ticket_price;
	}
	public void setTicket_price(double ticket_price) {
		this.ticket_price = ticket_price;
	}
	public short getTicket_status() {
		return ticket_status;
	}
	public void setTicket_status(short ticket_status) {
		this.ticket_status = ticket_status;
	}
	public Date getLocked_time() {
		return locked_time;
	}
	public void setLocked_time(Date locked_time) {
		this.locked_time = locked_time;
	}
	@Override
	public String toString() {
		return "Ticket [ticet_id=" + ticet_id + ", seat_id=" + seat_id
				+ ", sched_id=" + sched_id + ", ticket_price=" + ticket_price
				+ ", ticket_status=" + ticket_status + ", locked_time="
				+ locked_time + "]";
	}

}
