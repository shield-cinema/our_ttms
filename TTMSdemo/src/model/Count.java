package model;

public class Count {
	private String name;
	private int money;
	
	public Count() {
		super();
	}
	public Count(String name, int money) {
		super();
		this.name = name;
		this.money = money;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "Count [name=" + name + ", money=" + money + "]";
	}
	
}
