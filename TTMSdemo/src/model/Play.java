package model;

import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 剧目的实体类
 * @author Administrator
 *
 */
public class Play {
	private int play_id;
	private String play_type;
	private String play_language;
	private String play_name;
	private String play_introduction;
	private byte[] play_image;
	private int play_length;
	private int play_status;  //0：待安排演出	1：已安排演出	-1：下线
	private int ticketCount;  //票房
	
	
	public int getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}

	public Play(String play_type, String play_language,
			String play_name, String play_introduction, byte[] play_image,
			int play_length, int play_status) {
		this.play_type = play_type;
		this.play_language = play_language;
		this.play_name = play_name;
		this.play_introduction = play_introduction;
		this.play_image = play_image;
		this.play_length = play_length;
		this.play_status = play_status;
	}
	
	public Play(){
		
	}

	public Play(int play_id, String play_type, String play_language,
			String play_name, String play_introduction, byte[] play_image,
			int play_length, int play_status) {
		super();
		this.play_id = play_id;
		this.play_type = play_type;
		this.play_language = play_language;
		this.play_name = play_name;
		this.play_introduction = play_introduction;
		this.play_image = play_image;
		this.play_length = play_length;
		this.play_status = play_status;
	}
	public int getPlay_id() {
		return play_id;
	}
	public void setPlay_id(int play_id) {
		this.play_id = play_id;
	}
	
	
	public String getPlay_type() {
		return play_type;
	}
	public void setPlay_type(String play_type) {
		this.play_type = play_type;
	}
	public String getPlay_language() {
		return play_language;
	}
	public void setPlay_language(String play_language) {
		this.play_language = play_language;
	}
	public String getPlay_name() {
		return play_name;
	}
	public void setPlay_name(String play_name) {
		this.play_name = play_name;
	}
	public String getPlay_introduction() {
		return play_introduction;
	}
	public void setPlay_introduction(String play_introduction) {
		this.play_introduction = play_introduction;
	}
	public byte[] getPlay_image() {
		return play_image;
	}
	public void setPlay_image(byte[] play_image) {
		this.play_image = play_image;
	}
	public int getPlay_length() {
		return play_length;
	}
	public void setPlay_length(int play_length) {
		this.play_length = play_length;
	}
	public int getPlay_status() {
		return play_status;
	}
	public void setPlay_status(int play_status) {
		this.play_status = play_status;
	}
	@Override
	public String toString() {
		String path = "http://localhost:8080/ttms/image/" + play_id +".jpg";
		String path1 = "D:\\JavaWorkspaceIDEA\\New_TTMS\\web\\image\\"+ play_id +".jpg";
		try {
			FileOutputStream fos = new FileOutputStream(path1);
			fos.write(play_image);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			return "{\"num\":\"" + play_id + "\", \"type\":\"" + play_type
					+ "\",\"language\":\"" + play_language + "\",\"name\":\"" + play_name + "\",\"director\":\""
					+ play_introduction + "\",\"img\":\"" + path + "\", \"time\":\"" + play_length 
					+ "\",\"status\":\""
					+ play_status + "\",";
	}
	
}
