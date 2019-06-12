package service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.SchedMessage;
import model.Schedule;
import model.Status;
import net.sf.json.JSONObject;
import dao.PlayDao;
import dao.ScheDao;
import dao.TicketDao;

public class ScheService {
	private PlayDao playDao = new PlayDao();
	private ScheDao scheDao = new ScheDao();
	private TicketService ticSrv = new TicketService();
	private TicketDao ticketDao = new TicketDao();
	public int add(Schedule sche){
		int a = 0;
		String s = sche.getSched_time();
		s = s.replace("T", " ");
		s += ":00.0";
		Timestamp ts1 = Timestamp.valueOf(s);
		long l1 = ts1.getTime();
		
		int minute = playDao.findLength(sche.getPlay_id());
		long l2 = minute * 60000;
		
		ArrayList<Schedule> list = scheDao.find();
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		for(Schedule sche1:list){
			int id = sche1.getStudio_id();
			list1.add(id);
		}
		int stuid = sche.getStudio_id();
		if(list1.contains(stuid)){
			int i=0;
			ArrayList<Schedule> li = scheDao.findTime(stuid);
			for(Schedule sche2:li){
				String s1 = sche2.getSched_time();
				Timestamp ts = Timestamp.valueOf(s1);
				long l = ts.getTime();
				if(l + l2 < l1 || l - l2 > l1){
					i++;
					continue;
				}
				else {
					break;
				}
			}
			if(i == li.size()){
				a = scheDao.insert(sche);
				ticSrv.CreatTicket(scheDao.findId(sche),
						sche.getStudio_id(),sche.getSched_ticket_price());

			}
			else{
				a = 0;
			}
			
		}else{
			a = scheDao.insert(sche);
			ticSrv.CreatTicket(scheDao.findId(sche),sche.getStudio_id(),sche.getSched_ticket_price());

		}

		return a;
	}
	
	public int update(Schedule sche){
		int sched_id = sche.getSched_id();
		int studio_id = sche.getStudio_id();
		int ticket_price = sche.getSched_ticket_price();
		ticSrv.delTicket(sched_id);
		ticSrv.CreatTicket(sched_id, studio_id, ticket_price);		
		return scheDao.update(sche);
	}
	
	public int delete(int id){
		int rs = 0;
		rs = ticSrv.delTicket(id);
		if(rs!=0){
			rs = scheDao.delete(id);
		}
		return rs;
	}
	
	public String findAll(int id){
		ArrayList<Schedule> al = scheDao.findAll(id);
		String s = "";
		if(al != null){
			s = "\"list\":[";
			for(int i=0;i<al.size();i++){
				s += al.get(i);
				if(i<al.size()-1) 
				s += ",";
			}
			s += "]";
			return s;
		}else
			return s;
	}

	public String findStatus(int schedule_id){
		Map map = scheDao.findStatus(schedule_id);
		int row = (Integer)map.get("studio_row_count");
		int col = (Integer)map.get("studio_col_count");
		String list = ticketDao.findTicketStatus(schedule_id);
		Status ls = new Status(1,row,col,list);
		return JSONObject.fromObject(ls).toString();
	}
	
	public List<SchedMessage> findSchedule(int play_id){
		List<Map<String,Object>> list = scheDao.findSchedule(play_id);
		List<SchedMessage> sms = new ArrayList<SchedMessage>();
		for(Map<String, Object> li :list){
			int id = (Integer)li.get("sched_id");
			int room = (Integer)li.get("studio_id");
			double ticket1 = ((BigDecimal) li.get("sched_ticket_price")).doubleValue();
			int ticket = (int)ticket1;
			String start = li.get("sched_time").toString();
			SchedMessage sm = new SchedMessage(id,room,ticket,start);
			sms.add(sm);
		}
		return sms;
	}
}
