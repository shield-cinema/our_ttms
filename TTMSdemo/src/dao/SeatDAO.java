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
                    + " values("
                    + sea.getStudioId() + ", "
                    + sea.getRow() + ", "
                    + sea.getColumn() + ")";
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

        String sql = "update seat set " + " studio_id =" + sea.getStudioId()
                + ", " + " seat_row = " + sea.getRow() + ", "
                + " seat_column = " + sea.getColumn()+ " " ;

        sql += " where studio_id = " + sea.getId();

        DBUtil db = new DBUtil();

        return db.execCommand(sql);  //直接执行

    }

    @Override
    public int delete(int ID)  //根据ID删除
    {
        String sql = "delete from  seat ";
        sql += " where seat_id = " + ID;
        DBUtil db = new DBUtil();
        return db.execCommand(sql);
    }


    @Override
    public List<Seat> select(String condt)
    {
        /*   seat_id              int not null auto_increment,
            studio_id            int,
        seat_row             int,
        seat_column          int,
      */
        List<Seat> seaList = null;//定义结果集
        seaList=new LinkedList<Seat>();
        try {
            String sql = "select seat_id, studio_id, seat_row, seat_column from seat ";//查询语句
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
                    Seat sea=new Seat();
                    sea.setId(rst.getInt("seat_id"));
                    sea.setStudioId(rst.getInt("studio_id"));
                    sea.setRow(rst.getInt("seat_row"));
                    sea.setColumn(rst.getInt("seat_column"));

                    seaList.add(sea);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seaList;
    }
}
