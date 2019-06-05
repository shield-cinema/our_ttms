package dao;
import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;


import domain.Ticket;
import idao.ITicketDAO;
import util.DBUtil;
   /*
    private int id;
	private int seatId;
	private int scheduleId;
	private float price;
	private int status;
         */

public class TicketDAO implements ITicketDAO {
    @Override
    public int insert(Ticket stu)//把票的信息转成string,连接数据库，传入，插入数据库值
    {

        try {
            String sql = "insert into ticket(ticket_seatId, ticket_scheduleId, ticket_price, ticket_status )"
                    + " values('"
                    + stu.getSeatId()
                    + "', "
                    + stu.getScheduleId()
                    + ", " + stu.getPrice()
                    + ", '" + stu.getStatus()
                    + "' )";
            DBUtil db = new DBUtil(); //数据库连接，可以直接用
            ResultSet rst = db.getInsertObjectIDs(sql);
            if (rst!=null && rst.first()) {
                stu.setId(rst.getInt(1));//如果插入成功，就返回数据库里新影厅的编号
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    @Override
    public int update(Ticket stu)  //更新
    {
        /*
    private int id;
	private int seatId;
	private int scheduleId;
	private float price;
	private int status;
         */
        String sql = "update ticket set " + " ticket_seatId ='" + stu.getSeatId()
                + "', " + " ticket_scheduleId= " + stu.getScheduleId() + ", "
                + " ticket_price = " + stu.getPrice() + ", "
                + " ticket_status = '" +stu.getStatus() + "' ";

        sql += " where ticket_id = " + stu.getId();

        DBUtil db = new DBUtil();

        return db.execCommand(sql);  //直接执行
    }


    @Override
    public int delete(int ID)  //根据ID删除
    {
        String sql = "delete from  ticket ";
        sql += " where ticket_id = " + ID;
        DBUtil db = new DBUtil();
        return db.execCommand(sql);
    }
    @Override
    public List<Ticket> select(String condt)  //查找
    {
        List<Ticket> stuList = null;//定义结果集
        stuList=new LinkedList<Ticket>();
        try {
            String sql = "select ticket_id, ticket_seatId, ticket_scheduleId, ticket_price, ticket_status from ticket ";//查询语句
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
                    Ticket stu=new Ticket();
                    stu.setId(rst.getInt("ticket_id"));
                    stu.setSeatId(rst.getInt("ticket_seatId"));
                    stu.setScheduleId(rst.getInt("ticket_scheduleId"));
                    stu.setPrice(rst.getFloat("ticket_price"));
                    stu.setStatus(rst.getInt("ticket_status"));
                    stuList.add(stu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stuList;
    }

    public int lockTicket(int ID, String time) {
        int rtn=0;
        try {
            String sql = "update ticket set ticket_status=1, ticket_locked_time='" + time + "'";
            sql += " where ticket_id = " + ID;
            DBUtil db = new DBUtil();
           // db.openConnection();
            rtn =db.execCommand(sql);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }


    public int unlockTicket(int ID) {
        int rtn=0;
        try {
            String sql = "update ticket set ticket_status=0";
            sql += " where ticket_id = " + ID;
            DBUtil db = new DBUtil();
            // db.openConnection();
            rtn =db.execCommand(sql);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }

}
