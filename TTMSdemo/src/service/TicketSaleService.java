package service;

import dao.PlayDao;
import dao.TicketSaleDAO;


public class TicketSaleService {
	
	private TicketSaleDAO tsd = new TicketSaleDAO();
	private PlayDao pd = new PlayDao();
	
	public int lock(int row ,int column,int play_id,int sched_id,String user) {
		
		return tsd.lock(row,column,play_id,sched_id,user);
	}
	
	public int unlock(int row ,int column,int play_id,int sched_id,String user) {
		
		return tsd.unlock(row, column, play_id, sched_id, user);
	}
	
	public int buy(int row ,int column,int play_id,int sched_id,String user) {
		
		return tsd.buy(row, column, play_id, sched_id, user);
	}

	public long getTicket_ID(int ro, int co,int play_id,int shced_id) {
		
		return tsd.getTicket_ID(ro,co,play_id,shced_id);
	}

	public Object addCount(int play_id) {
		
		return pd.addCount(play_id);
	}
	
}
