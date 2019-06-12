package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Count;
import model.Play;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import dao.PlayDao;
/**
 * 处理剧目的增删改查
 * @author Administrator
 *
 */
public class PlayService {
	private PlayDao playDao = new PlayDao();
	private ScheService scheSrv = new ScheService();
	/*
	 * 修改剧目状态
	 * */
	public int update(int id){
		return playDao.update(id);
	}
	
	
	/*
	 * 添加剧目
	 * */
	public int add(Play pla) {
			return playDao.insert(pla);
	}
	
	public String findAll(){
		ArrayList<Play> al = playDao.findAll();
		String s = "";
		if(al != null){
			s = "{\"res\":\"1\",\"obj\":[";
			for(int i=0;i<al.size();i++){
				s += al.get(i);
				int id = al.get(i).getPlay_id();
				s += scheSrv.findAll(id);
				if(i<al.size()-1)
				s += "},";
			}
			s += "}]}";
			return s;
		}else
			return s;
	}
	/**
	 * 统计电影数量
	 * @return
	 */
	public long countPlay(){
		return playDao.countPlay();
	}
	
	
	public String CountTicket(){
		List<Map<String, Object>> list = playDao.getCount2();
		List<Count> st = new ArrayList<Count>();
		for(Map<String, Object> li :list){
			String name = (String)li.get("play_name");
			int money = (Integer)li.get("ticketCount");
			Count count = new Count(name,money);
			st.add(count);
		}
		String s = JSONArray.fromObject(st).toString();
		return s;

	}
	
}
