package idao;

public interface iTicketSaleDAO {

	public int lock(int row, int column, int play_id, int sched_id, String user);
	public int unlock(int row, int column, int play_id, int sched_id, String user);
	public int buy(int row, int column, int play_id, int sched_id, String user);
}
