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
                    + " values("
                    + sea.getStudioId() + ", "
                    + sea.getRow() + ", "
                    + sea.getColumn() + ")";
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

        String sql = "update seat set " + " studio_id =" + sea.getStudioId()
                + ", " + " seat_row = " + sea.getRow() + ", "
                + " seat_column = " + sea.getColumn()+ " " ;

        sql += " where studio_id = " + sea.getId();

        DBUtil db = new DBUtil();

        return db.execCommand(sql);  //ֱ��ִ��

    }

    @Override
    public int delete(int ID)  //����IDɾ��
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
        List<Seat> seaList = null;//��������
        seaList=new LinkedList<Seat>();
        try {
            String sql = "select seat_id, studio_id, seat_row, seat_column from seat ";//��ѯ���
            condt.trim();//ȥ��ǰ��ո񣬿���ֱ����
            if(!condt.isEmpty())  //������ǿ�
                sql+= " where " + condt;//����where���
            DBUtil db = new DBUtil();
            ResultSet rst = db.execQuery(sql);  //��ѯ���õ������
            //System.out.print("sql:"+sql);
            if (rst!=null)   //�ҵ��Ľ�������´�������Ľ������ȥ
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
