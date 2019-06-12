package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Seat;
import model.Studio;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;

/**
 * 座位管理
 * @author Administrator
 *
 */
public class SeatDao {
	private QueryRunner qr = new TxQueryRunner();
//	private static final int ROW = 50;
//	private static final int COL = 50;
	
	/**
	 * 按演出厅ID查找座位
	 * @param studio_id
	 * @return 返回座位对象
	 */
	public ArrayList<Seat> findSeat(int studio_id){
		try {
			String sql = "select * from seat where studio_id=? ";
			return (ArrayList<Seat>) qr.query(sql, new BeanListHandler<Seat>(Seat.class), studio_id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 生成座位
	 * @param str 座位状态信息
	 * @param id  影厅ID
	 * @return 成功返回正数，失败返回-1
	 */
	public int creatSeat(String status,int id,int row,int col){
		int rs = -1;
		if(id!=-1){//不为-1才可以生成座位
			try{
				String sql = "insert into seat(studio_id,seat_row,seat_column)"
					 + "values(?,?,?)";
				String [] arr = status.split(",");
//				int [][] stt = new int[ROW][COL];
				int num = -1;
//				Object[][] params = new Object[11][3];
				for(int i=0;i<row;i++) 
					for(int j=0;j<col;j++){
						num++;
//						stt[i][j] = Integer.parseInt(arr[num]);
						Object[] params = {id,i+1,j+1};
//						params[1][0] = 1;
						rs = qr.update(sql, params);
					}
				
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return rs;
	}
	/**
	 * 删除演出厅对应的所有座位
	 * @param studioID
	 * @return 成功返回一个正数，失败返回-1
	 */
	public int delSeat(int studioID){
		int rs = -1;
		try{
			String sql = "delete from seat where studio_id='" + studioID + "'";
			rs = qr.update(sql);
			return rs;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void findSeatIDs(){
		
	}
	
}
