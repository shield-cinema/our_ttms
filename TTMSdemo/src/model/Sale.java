package model;

import java.util.Date;

public class Sale {
	private long sale_ID;
	private int emp_id;
	private Date sale_time;
	private double sale_payment;
	private double sale_change;
	private int sale_type;//类别取值含义：  1：销售单  -1：退款单
	private int sale_status;//销售单状态如下：  0：代付款  1：已付款
	public long getSale_ID() {
		return sale_ID;
	}
	public void setSale_ID(long sale_ID) {
		this.sale_ID = sale_ID;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public Date getSale_time() {
		return sale_time;
	}
	public void setSale_time(Date sale_time) {
		this.sale_time = sale_time;
	}
	public double getSale_payment() {
		return sale_payment;
	}
	public void setSale_payment(double sale_payment) {
		this.sale_payment = sale_payment;
	}
	public double getSale_change() {
		return sale_change;
	}
	public void setSale_change(double sale_change) {
		this.sale_change = sale_change;
	}
	public int getSale_type() {
		return sale_type;
	}
	public void setSale_type(int sale_type) {
		this.sale_type = sale_type;
	}
	public int getSale_status() {
		return sale_status;
	}
	public void setSale_status(int sale_status) {
		this.sale_status = sale_status;
	}
	@Override
	public String toString() {
		return "Sale [sale_ID=" + sale_ID + ", emp_id=" + emp_id
				+ ", sale_time=" + sale_time + ", sale_payment=" + sale_payment
				+ ", sale_change=" + sale_change + ", sale_type=" + sale_type
				+ ", sale_status=" + sale_status + "]";
	}
	
	
}
