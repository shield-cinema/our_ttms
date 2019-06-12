package model;

public class SchTime {
	private int id;
	private String room;
	private int ticket;
	private String start;

	public SchTime() {
		super();
	}
	public SchTime(int id, String room, int ticket, String start) {
		super();
		this.id = id;
		this.room = room;
		this.ticket = ticket;
		this.start = start;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	@Override
	public String toString() {
		return "SchTime [id=" + id + ", room=" + room + ", ticket=" + ticket
				+ ", start=" + start + "]";
	}
	

}
