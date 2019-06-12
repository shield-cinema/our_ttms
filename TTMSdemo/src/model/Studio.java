package model;


public class Studio {
	private int studio_id;
	private String studio_name;
	private int studio_row_count;
	private int studio_col_count;
	private String studio_introduction;
	private String studio_status;//一个数组，保存座位的信息
	
	public Studio() {
		super();
	}
	
	public Studio(int studio_id, int studio_row_count, int studio_col_count,
			String studio_status) {
		super();
		this.studio_id = studio_id;
		this.studio_row_count = studio_row_count;
		this.studio_col_count = studio_col_count;
		this.studio_status = studio_status;
	}

	public Studio(String studio_name, int studio_row_count,
			int studio_col_count, String studio_introduction) {
		super();
		this.studio_name = studio_name;
		this.studio_row_count = studio_row_count;
		this.studio_col_count = studio_col_count;
		this.studio_introduction = studio_introduction;
	}

	public Studio(int studio_id, String studio_name, int studio_row_count,
			int studio_col_count, String studio_introduction,
			String studio_status) {
		super();
		this.studio_id = studio_id;
		this.studio_name = studio_name;
		this.studio_row_count = studio_row_count;
		this.studio_col_count = studio_col_count;
		this.studio_introduction = studio_introduction;
		this.studio_status = studio_status;
	}
	public String getStudio_status() {
		return studio_status;
	}
	public void setStudio_status(String studio_status) {
		this.studio_status = studio_status;
	}
	public int getStudio_id() {
		return studio_id;
	}
	public void setStudio_id(int studio_id) {
		this.studio_id = studio_id;
	}
	public String getStudio_name() {
		return studio_name;
	}
	public void setStudio_name(String studio_name) {
		this.studio_name = studio_name;
	}
	public int getStudio_row_count() {
		return studio_row_count;
	}
	public void setStudio_row_count(int studio_row_count) {
		this.studio_row_count = studio_row_count;
	}
	public int getStudio_col_count() {
		return studio_col_count;
	}
	public void setStudio_col_count(int studio_col_count) {
		this.studio_col_count = studio_col_count;
	}
	public String getStudio_introduction() {
		return studio_introduction;
	}
	public void setStudio_introduction(String studio_introduction) {
		this.studio_introduction = studio_introduction;
	}
	@Override
	public String toString() {
		/*return "Studio [studio_id=" + studio_id + ", studio_name="
				+ studio_name + ", studio_row_count=" + studio_row_count
				+ ", studio_col_count=" + studio_col_count
				+ ", studio_introduction=" + studio_introduction
				+ ", studio_status=" + studio_status + "]";
		*/
		return "{\"id\":\"" + studio_id + "\",\"name\":\"" +
				studio_name + "\",\"row\":\"" + studio_row_count + "\"," +
				"\"col\":\"" + studio_col_count + "\"," +
				"\"val\":" + "\"" + studio_introduction + "\"" + "}";
	}
	
}






