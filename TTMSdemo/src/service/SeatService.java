package service;

import model.Seat;
import model.Studio;
import dao.SeatDao; 
import dao.StudioDao;

public class SeatService {
	
	private SeatDao seatDao = new SeatDao();
	/**
	 * 座位状态，要创建演出厅的ID
	 * @param str
	 * @param id
	 * @return 成功返回正数，失败返回-1
	 */
	public int creatSeat(String str,int id){
		StudioDao studio = new StudioDao();
		Studio sd = studio.findByID(id);
		int row = sd.getStudio_row_count();
		int col = sd.getStudio_col_count();
		return seatDao.creatSeat(str, id,row,col);
	}
	/**
	 * 删除演出厅，
	 * @param studioID
	 * @return 成功返回正数，失败返回-1
	 */
	public int delSeat(int studioID){
		return seatDao.delSeat(studioID);
	}
	
	

}
