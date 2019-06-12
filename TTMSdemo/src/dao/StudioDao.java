package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Play;
import model.Studio;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import service.SeatService;

import cn.itcast.jdbc.TxQueryRunner;
/**
 * 演出厅管理
 * @author Administrator
 *
 */
public class StudioDao {
	private QueryRunner qr = new TxQueryRunner();
	private SeatService seatService = new SeatService();
	/**
	 * 查询所有演出厅
	 * @return ArrayList<Studio>
	 */
	public ArrayList<Studio> findStudio(){
		try {
			String sql = "select * from studio";
			return (ArrayList<Studio>) qr.query(sql, new BeanListHandler<Studio>(Studio.class));
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 按ID查询演出厅
	 * @param id
	 * @return Studio
	 */
	public Studio findByID(int id){
		try{
			String sql = "select * from studio where studio_id=?";
			return qr.query(sql, new BeanHandler<Studio>(Studio.class), id);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 按ID删除演出厅
	 * @param id
	 * @return 删除成功返回1，失败返回0
	 */
	public int deleteStudio(int id){//truncate table 你的表名		删除全部数据，自增长从零开始，可能清空外键约束
		int s=0;
		try {
			String sql = "delete from studio where studio_id=?";
			return s = qr.update(sql, id);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 按名字查询演出厅返回一个演出厅的ID
	 * @param name
	 * @return	一个int型的演出厅的ID失败返回-1
	 */
	public int findID(String name){
		int s = -1;
		try{
			String sql = "select * from studio where studio_name=?";
			Studio studio = qr.query(sql, new BeanHandler<Studio>(Studio.class),name);
			if(studio!=null){
				s=studio.getStudio_id();
			}
			return s;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 添加演出厅,
	 * @param from
	 * @return 返回添加的演出厅的ID，如果添加失败返回-1
	 */
	public int addStudio(Studio from) {
		int s = -1;
		try{
			String sql = "insert into studio(" +
					"studio_name,studio_row_count," +
					"studio_col_count,studio_introduction,studio_status)"
					 + "values(?,?,?,?,?)";
			Object[] params = {
					from.getStudio_name(), from.getStudio_row_count(), from.getStudio_col_count(),
					from.getStudio_introduction(),defaultseat(from)
					};
			qr.update(sql, params);
			s = findID(from.getStudio_name());
			Studio sd = findByID(s);
			String str = sd.getStudio_status();
			int rs = seatService.creatSeat(str,s);//生成座位
			if(rs!=-1){
				return s;
			}
			return -1;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//生成默认的座位的状态
	private String defaultseat(Studio from){
		int row = from.getStudio_row_count();
		int col = from.getStudio_col_count();
		String seat = "0";
		for(int i=0;i<row*col-1;i++){
			seat +=",0";
		}
		return seat;
	}
	/**
	 * 修改演出厅的行数row
	 * @param id
	 * @param row
	 * @return 修改成功返回影响的行数，失败返回
	 */
	public int updataRow(int id,int row){
		int rs = 0;
		try{
			Studio stu = findByID(id);
			stu.setStudio_row_count(row);
			String status = defaultseat(stu);
			String sql = "update studio set " +
					"studio_row_count=?,studio_status=? where studio_id=?";
			Object[] params = {row,status,id};
			rs = qr.update(sql, params);
			return rs;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 修改演出厅的列数col
	 * @param id
	 * @param row
	 * @return 修改成功返回影响的行数，失败返回0
	 */
	public int updataCol(int id,int col){
		int rs = 0;
		try{
			Studio stu = findByID(id);
			stu.setStudio_row_count(col);
			String status = defaultseat(stu);//修改以后的座位界面和数据库的位置不对应了
			String sql = "update studio set " +
					"studio_col_count=?,studio_status=? where studio_id=?";
			Object[] params = {col,status,id};
			rs = qr.update(sql, params);
			return rs;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 设计座位
	 * @param from
	 * @return 成功返回1，失败返回0
	 */
	public int resignSeat(Studio from){
		try{
			Studio resign = findByID(from.getStudio_id());
			resign.setStudio_col_count(from.getStudio_col_count());
			resign.setStudio_row_count(from.getStudio_row_count());
			updataRow(resign.getStudio_id(), resign.getStudio_row_count());
			String sql = "update studio set studio_status=? where studio_id=?";
			Object[] param = {from.getStudio_status(),from.getStudio_id()};
			return qr.update(sql, param);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}














