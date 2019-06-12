package service;

import java.util.ArrayList;

import model.Studio;
import dao.StudioDao;

public class StudioService {
	private StudioDao studioDao = new StudioDao();
	private SeatService seatService = new SeatService(); 
	/**
	 * 添加演出厅
	 * @param from
	 * @return 返回添加演出厅的ID,如果添加失败返回0
	 */
	public int addStudio(Studio from){
		int flag = 0;
		int rs = studioDao.findID(from.getStudio_name());
		if(rs==-1){
			flag = studioDao.addStudio(from);
		}
		return flag;
	}
	/**
	 * 修改演出厅的row
	 * @param id
	 * @param row
	 * @return 如果修改成功返回1，修改失败返回-1；
	 */
	public int updataRow(int id,int row){
		int rs = studioDao.updataRow(id, row);
		if(rs!=0){
			rs = seatService.delSeat(id);
			if(rs!=0){
				rs = seatService.creatSeat(studioDao.findByID(id).getStudio_status(), id);
			}
		}	
		return rs;
	}
	/**
	 * 修改演出厅的col
	 * @param id
	 * @param col
	 * @return 如果修改成功，返回1，失败返回-1
	 */
	public int updataCol(int id,int col){
		int rs = -1;
		rs = studioDao.updataCol(id, col);
		if(rs!=0){
			rs = seatService.delSeat(id);
			if(rs!=0){
				rs = seatService.creatSeat(studioDao.findByID(id).getStudio_status(), id);
			}
		}	
		return rs;
	}
	/**
	 * 删除演出厅
	 * @param id
	 * @return 删除成功返回1，失败返回0
	 */
	public int delStudio(int id){
		int s=0;
		s = seatService.delSeat(id);
		System.out.println(s);
		if(s!=-1){
			s = studioDao.deleteStudio(id);
		}
		return s;
	}
	/**
	 * 查询所有演出厅
	 * @return 如果查到就返回结果，没有数据 就返回一个空字符串
	 */
	public String findAllStudio(){
		ArrayList<Studio> al = studioDao.findStudio();
		String s ="";
		if(al!=null){
			s ="[";
			for(int i=0;i<al.size();i++){
				s += al.get(i);
				if(i<al.size()-1)
				s += ",";
			}
			s += "]";
		}
		return s;
	}
	/**
	 * 设计演出厅
	 * @param from
	 * @return 成功返回1，失败0
	 */
	public int designStudio(Studio from) {
		
		return studioDao.resignSeat(from);
	}
}
