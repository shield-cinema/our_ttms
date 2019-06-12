package model;

public class saleItem {
	private long sale_item_id;
	private long ticket_id;
	private long sale_ID;
	private double sale_item_price;
	public long getSale_item_id() {
		return sale_item_id;
	}
	public void setSale_item_id(long sale_item_id) {
		this.sale_item_id = sale_item_id;
	}
	public long getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(long ticket_id) {
		this.ticket_id = ticket_id;
	}
	public long getSale_ID() {
		return sale_ID;
	}
	public void setSale_ID(long sale_ID) {
		this.sale_ID = sale_ID;
	}
	public double getSale_item_price() {
		return sale_item_price;
	}
	public void setSale_item_price(double sale_item_price) {
		this.sale_item_price = sale_item_price;
	}
	@Override
	public String toString() {
		return "saleItem [sale_item_id=" + sale_item_id + ", ticket_id="
				+ ticket_id + ", sale_ID=" + sale_ID + ", sale_item_price="
				+ sale_item_price + "]";
	}
	
}
