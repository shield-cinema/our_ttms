package dao;

import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtil;
import domain.Studio;
import idao.IStudioDAO;

public class StudioDAO implements IStudioDAO {
	@Override
	public int insert(Studio stu)//把影厅信息转成string,连接数据库，传入，插入数据库值
	{
		try {
			String sql = "insert into studio(studio_name, studio_row_count, studio_col_count, studio_introduction )"
					+ " values('"
					+ stu.getName()
					+ "', "
					+ stu.getRowCount()
					+ ", " + stu.getColCount()
					+ ", '" + stu.getIntroduction()
					+ "' )";
			DBUtil db = new DBUtil(); //数据库连接，可以直接用
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst!=null && rst.first()) {
				stu.setID(rst.getInt(1));//如果插入成功，就返回数据库里新影厅的编号
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int update(Studio stu)  //更新
	{

		String sql = "update studio set " + " studio_name ='" + stu.getName()
				+ "', " + " studio_row_count = " + stu.getRowCount() + ", "
				+ " studio_col_count = " + stu.getColCount() + ", "
				+ " studio_introduction = '" + stu.getIntroduction() + "' ";

		sql += " where studio_id = " + stu.getID();

		DBUtil db = new DBUtil();

		return db.execCommand(sql);  //直接执行

	}

	@Override
	public int delete(int ID)  //根据ID删除
	{
		String sql = "delete from  studio ";
		sql += " where studio_id = " + ID;
		DBUtil db = new DBUtil();
		return db.execCommand(sql);
	}

	@Override
	public List<Studio> select(String condt)
	{
		List<Studio> stuList = null;//定义结果集
		stuList=new LinkedList<Studio>();
		try {
			String sql = "select studio_id, studio_name, studio_row_count, studio_col_count, studio_introduction from studio ";//查询语句
			condt.trim();//去除前后空格，可以直接用
			if(!condt.isEmpty())  //如果不是空
				sql+= " where " + condt;//加上where语句
			DBUtil db = new DBUtil();
			ResultSet rst = db.execQuery(sql);  //查询并得到结果集
			//System.out.print("sql:"+sql);
			if (rst!=null)   //找到的结果集重新传到定义的结果集里去
			{
				while(rst.next())
				{
					Studio stu=new Studio();
					stu.setID(rst.getInt("studio_id"));
					stu.setName(rst.getString("studio_name"));
					stu.setRowCount(rst.getInt("studio_row_count"));
					stu.setColCount(rst.getInt("studio_col_count"));
					stu.setIntroduction(rst.getString("studio_introduction"));
					stuList.add(stu);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stuList;
	}
}
