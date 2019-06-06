package dao;


import domain.Sale;
import idao.ISaleDAO;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SaleDAO implements ISaleDAO {
    @Override
    public int insert(Sale sa)//把影厅信息转成string,连接数据库，传入，插入数据库值
    {

        try {
            String sql = "insert into sale(emp_id, sale_time, sale_payment, sale_change, sale_type, sale_status )"
                    + " values("
                    + sa.getEmpId() + ", "
                    + sa.getTime() + ", "
                    + sa.getPayment() + ", "
                    + sa.getChange()+", "
                    + sa.getType()+", "
                    + sa.getStatus()
                    + ")";
            DBUtil db = new DBUtil(); //数据库连接，可以直接用
            ResultSet rst = db.getInsertObjectIDs(sql);
            if (rst!=null && rst.first()) {
                sa.setId(rst.getInt(1));//如果插入成功，就返回数据库里新影厅的编号
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
    public int update(Sale sa)  //更新
    {

        String sql = "update sale set " +
                " emp_id =" + sa.getEmpId() + ", " +
                " sale_time = " + sa.getTime() + ", " +
                " sale_payment = " + sa.getPayment() + ", " +
                " sale_change = " + sa.getChange() + ", " +
                " sale_type = " + sa.getType() + ", " +
                " sale_status = " + sa.getStatus() + " ";

        sql += " where sale_ID = " + sa.getId();

        DBUtil db = new DBUtil();

        return db.execCommand(sql);  //直接执行
    }
    @Override
    public int delete(int ID)  //根据ID删除
    {
        String sql = "delete from  sale ";
        sql += " where sale_ID = " + ID;
        DBUtil db = new DBUtil();
        return db.execCommand(sql);
    }
    @Override
    public List<Sale> select(String condt)
    {
        /*
        sale_ID              bigint not null auto_increment,
	   emp_id               int,
	   sale_time            datetime,
	   sale_payment         decimal(10,2),
	   sale_change          numeric(10,2),
	   sale_type            smallint comment '类别取值含义：
	            1：销售单
	            -1：退款单',
	   sale_status          smallint comment '销售单状态如下：
	            0：代付款
	            1：已付款',*/
        List<Sale> saList = null;//定义结果集
        saList=new LinkedList<Sale>();
        try {
            String sql = "select sale_ID, emp_id, sale_time, sale_payment, sale_change, sale_type, sale_status from sale ";//查询语句
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
                    Sale sa=new Sale();
                    sa.setId(rst.getInt("sale_ID"));
                    sa.setEmpId(rst.getInt("emp_id"));
                    sa.setTime(rst.getDate("sale_time"));
                    sa.setPayment(rst.getFloat("sale_payment"));
                    sa.setChange(rst.getFloat("sale_change"));
                    sa.setType(rst.getInt("sale_type"));
                    sa.setStatus(rst.getInt("sale_status"));
                    saList.add(sa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return saList;
    }
}
