package model;

public class Schedule {
	private int sched_id;
	private int studio_id;
	private int play_id;
	private String sched_time;
	private int sched_ticket_price;
	
	public Schedule(){
		
	}
	
	public Schedule(int sched_id, int studio_id, int play_id,
			String sched_time, int sched_ticket_price) {
		super();
		this.sched_id = sched_id;
		this.studio_id = studio_id;
		this.play_id = play_id;
		this.sched_time = sched_time;
		this.sched_ticket_price = sched_ticket_price;
	}

	public Schedule(int studio_id, int play_id,
			String sched_time, int sched_ticket_price) {
		this.studio_id = studio_id;
		this.play_id = play_id;
		this.sched_time = sched_time;
		this.sched_ticket_price = sched_ticket_price;
	}
	
	
	public Schedule(int sched_id, int studio_id, String sched_time) {
		this.sched_id = sched_id;
		this.studio_id = studio_id;
		this.sched_time = sched_time;
	}


	@Override
	public String toString() {
		return "{\"id\":\"A" + sched_id + "\", \"room\":\"A" + studio_id
				+ "\", \"start\":\"" + sched_time
				+ "\", \"ticket\":\"" + sched_ticket_price + "\"}";
	}
	public int getSched_id() {
		return sched_id;
	}
	public void setSched_id(int sched_id) {
		this.sched_id = sched_id;
	}
	public int getStudio_id() {
		return studio_id;
	}
	public void setStudio_id(int studio_id) {
		this.studio_id = studio_id;
	}
	public int getPlay_id() {
		return play_id;
	}
	public void setPlay_id(int play_id) {
		this.play_id = play_id;
	}
	public String getSched_time() {
		return sched_time;
	}
	public void setSched_time(String sched_time) {
		this.sched_time = sched_time;
	}
	public int getSched_ticket_price() {
		return sched_ticket_price;
	}
	public void setSched_ticket_price(int sched_ticket_price) {
		this.sched_ticket_price = sched_ticket_price;
	}
	
}
