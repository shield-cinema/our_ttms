package dao;
import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;


import util.DBUtil;
import domain.Seat;
import idao.ISeatDAO;

public class SeatDAO implements  ISeatDAO {
    @Override
    public int insert(Seat sea)//把座位信息转成string,连接数据库，传入，插入数据库值
    {
     /*   seat_id              int not null auto_increment,
            studio_id            int,
        seat_row             int,
        seat_column          int,
      */
        try {
            String sql = "insert into seat(studio_id, seat_row, seat_column )"
                    + " values('"
                    + sea.getStudioId()
                    + "', "
                    + sea.getRow()
                    + ", " + sea.getColumn()
                    + "' )";
            DBUtil db = new DBUtil(); //数据库连接，可以直接用
            ResultSet rst = db.getInsertObjectIDs(sql);
            if (rst!=null && rst.first()) {
                sea.setId(rst.getInt(1));//如果插入成功，就返回数据库里新影厅的编号
                db.closeDB(rst);
                db.close();
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int update(Seat sea)  //更新
    {
        /*   seat_id              int not null auto_increment,
            studio_id            int,
        seat_row             int,
        seat_column          int,
      */
        String sql = "update seat set " + " studio_id ='" + sea.getName()
                + "', " + " seat_row = " + sea.getRowCount() + ", "
                + " seat_column = " + sea.getColCount() + ", "
                + "' ";

        sql += " where studio_id = " + stu.getID();

        DBUtil db = new DBUtil();

        return db.execCommand(sql);  //直接执行
        return 0;
    }

    @Override
    public int delete(int ID)  //根据ID删除
    {
        return 0;
    }


    @Override
    public List<Seat> select(String condt)
    {
        List<Seat> stuList = null;//定义结果集
        stuList=new LinkedList<Seat>();

        return stuList;
    }
}
