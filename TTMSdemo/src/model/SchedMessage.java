package model;

public class SchedMessage {
	private int id;
	private int room;
	private int ticket;
	private String start;
	
	public SchedMessage() {
		super();
	}
	public SchedMessage(int id, int room, int ticket, String start) {
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
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
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
		return "SchedMessage [id=" + id + ", room=" + room + ", ticket="
				+ ticket + ", start=" + start + "]";
	}
	
}
