package model;

public class PlaySch {
	private int num;
	private String name;
	private String img;
	private String director;
	private String language;
	private String type;
	private int money;
	private String date;
	
	public PlaySch() {
		super();
	}
	public PlaySch(int num, String name, String img, String director,
			String language, String type, int money, String date) {
		super();
		this.num = num;
		this.name = name;
		this.img = img;
		this.director = director;
		this.language = language;
		this.type = type;
		this.money = money;
		this.date = date;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "PlaySch [num=" + num + ", name=" + name + ", img=" + img
				+ ", director=" + director + ", language=" + language
				+ ", type=" + type + ", money=" + money + ", date=" + date
				+ "]";
	}
	
	
}
