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
    public int insert(Seat sea)//����λ��Ϣת��string,�������ݿ⣬���룬�������ݿ�ֵ
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
            DBUtil db = new DBUtil(); //���ݿ����ӣ�����ֱ����
            ResultSet rst = db.getInsertObjectIDs(sql);
            if (rst!=null && rst.first()) {
                sea.setId(rst.getInt(1));//�������ɹ����ͷ������ݿ�����Ӱ���ı��
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
    public int update(Seat sea)  //����
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

        return db.execCommand(sql);  //ֱ��ִ��
        return 0;
    }

    @Override
    public int delete(int ID)  //����IDɾ��
    {
        return 0;
    }


    @Override
    public List<Seat> select(String condt)
    {
        List<Seat> stuList = null;//��������
        stuList=new LinkedList<Seat>();

        return stuList;
    }
}
