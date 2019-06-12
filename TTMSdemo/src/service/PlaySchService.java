package service;

import java.util.ArrayList;

import model.Play;
import model.PlaySch;
import model.Schedule;
import dao.PlayDao;
import dao.ScheDao;

public class PlaySchService {
	private PlayDao playDao = new PlayDao();
	private PlaySch playSch = new PlaySch();
	private ScheDao scheDao = new ScheDao();
	
	
	public PlaySch find(int play_id){
		Play play = playDao.findByID(play_id);
		Schedule sch = scheDao.findById(play_id);
		String date = sch.getSched_time();
		int num = play.getPlay_id();
		String name = play.getPlay_name();
		String img = "http://localhost:8080/ttms/image/" + play_id +".jpg";
		String director = play.getPlay_introduction();
		String language = play.getPlay_language();
		String type = play.getPlay_type();
		int money = play.getTicketCount();
		PlaySch ps = new PlaySch(num, name, img, 
				director, language, type, money, date);
		return ps;
	}
}
