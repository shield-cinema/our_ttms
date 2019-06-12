package service;

import java.util.ArrayList;

import model.Seat;
import dao.SeatDao;
import dao.TicketDao;

public class TicketService {
	private TicketDao ticketDao = new TicketDao();
	private SeatDao seatDao = new SeatDao();
	private static final int SEATID=500;
	/**
	 * 根据演出计划生成票
	 * @param sched_id
	 * @param studio_id
	 * @param ticket_price
	 * @return 成功返回1，失败返回0
	 */
	public int CreatTicket(int sched_id,int studio_id,double ticket_price){
		SeatDao seatDao = new SeatDao();
		int[] seatID = new int[SEATID];
		ArrayList<Seat> list = seatDao.findSeat(studio_id);//根据演出计划ID获取座位ID的列表
		for(int i=0;i<list.size();i++){
			seatID[i] = list.get(i).getSeat_id();//将座位ID的列表转换为数组
		}
		return ticketDao.creatTicket(sched_id,seatID,ticket_price,list.size());//调用dao层生成票
	}
	/**
	 * 删除票
	 * @param sched_id
	 * @return 失败返回0，成功返回正数
	 */
	public int delTicket(int sched_id){
		return ticketDao.delTicket(sched_id);
	}

}
