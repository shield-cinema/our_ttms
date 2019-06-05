package dao;


import util.DBUtil;
import domain.Play;
import idao.IPlayDAO;

import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayDAO implements IPlayDAO {
    @Override
    public int insert(Play pla)//把影厅信息转成string,连接数据库，传入，插入数据库值
    {

        try {
            String sql = "insert into play(play_type_id, play_lang_id, play_name, play_introduction, play_image, play_length, play_ticket_price, play_status )"
                    + " values("
                    + pla.getTypeId() + ", "
                    + pla.getLangId() + ", '"
                    + pla.getName() + "', '"
                    + pla.getIntroduction()+ "', '"
                    + pla.getImage()+ "', "
                    + pla.getTicketPrice() + ", "
                    + pla.getLength() + ", "
                    + pla.getStatus()
                    + " )";
            DBUtil db = new DBUtil(); //数据库连接，可以直接用
            ResultSet rst = db.getInsertObjectIDs(sql);
            if (rst!=null && rst.first()) {
                pla.setId(rst.getInt(1));//如果插入成功，就返回数据库里新影厅的编号
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
    public int update(Play pla)  //更新
    {

        String sql = "update play set " +
                " play_type_id = " + pla.getTypeId() + ", " +
                " play_lang_id = " + pla.getLangId() + ", " +
                " play_name = '" + pla.getName() + "', " +
                " play_introduction = '" + pla.getIntroduction() + "', "+
                " play_image = '" + pla.getImage() + "', "+
                " play_length = " + pla.getLength() + ", "+
                " play_ticket_price = " + pla.getTicketPrice() + ", "+
                " play_status = " + pla.getStatus() + " ";

        sql += " where play_id = " + pla.getId();

        DBUtil db = new DBUtil();

        return db.execCommand(sql);  //直接执行

    }
    @Override
    public int delete(int ID)  //根据ID删除
    {
        String sql = "delete from  play ";
        sql += " where play_id = " + ID;
        DBUtil db = new DBUtil();
        return db.execCommand(sql);
    }
    /*
play_id              int not null auto_increment,
play_type_id         int,
play_lang_id         int,
play_name            varchar(200),
play_introduction    varchar(2000),
play_image           longblob,
play_length          int,
play_ticket_price    numeric(10,2),
play_status          smallint comment '取值含义： */

    @Override
    public List<Play> select(String condt)
    {
        List<Play> plaList = null;//定义结果集
        plaList=new LinkedList<Play>();

        try {
            String sql = "select play_id, play_type_id, play_lang_id, play_name, play_introduction, play_image, play_length, play_ticket_price, play_status from play ";//查询语句
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

                    Play pla=new Play();
                    pla.setId(rst.getInt("play_id"));
                    pla.setTypeId(rst.getInt("play_type_id"));
                    pla.setLangId(rst.getInt("play_lang_id"));
                    pla.setName(rst.getString("play_name"));
                    pla.setIntroduction(rst.getString("play_introduction"));
                    pla.setImage(rst.getBytes("play_image"));
                    pla.setLength(rst.getInt("play_length"));
                    pla.setTicketPrice(rst.getFloat("play_ticket_price"));
                    pla.setStatus(rst.getInt("play_status"));
                    plaList.add(pla);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plaList;
    }

}
