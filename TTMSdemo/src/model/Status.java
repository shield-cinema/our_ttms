package model;

public class Status {
	private int res;
	private int row;
	private int col;
	private String list;
	
	public Status() {
		super();
	}
	
	public Status(int res, int row, int col, String list) {
		super();
		this.res = res;
		this.row = row;
		this.col = col;
		this.list = list;
	}
	public int getRes() {
		return res;
	}
	public void setRes(int res) {
		this.res = res;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "Status [res=" + res + ", row=" + row + ", col=" + col
				+ ", list=" + list + "]";
	}
	
}
