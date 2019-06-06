package dao;


import domain.SaleItem;
import idao.ISaleItemDAO;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SaleItemDAO implements ISaleItemDAO {

    @Override
    public int insert(SaleItem sal)//把票信息转成string,连接数据库，传入，插入数据库值
    {
        /*sale_item_id         bigint not null auto_increment,
            ticket_id            bigint,
            sale_ID              bigint,
            sale_item_price      numeric(10,2),

         */
        try {
            String sql = "insert into sale_item(ticket_id, sale_ID, sale_item_price )"
                    + " values("
                    + sal.getTicketId() + ", "
                    + sal.getSaleId() + ", "
                    + sal.getPrice()
                    + ")";
            DBUtil db = new DBUtil(); //数据库连接，可以直接用
            ResultSet rst = db.getInsertObjectIDs(sql);
            if (rst!=null && rst.first()) {
                sal.setId(rst.getInt(1));//如果插入成功，就返回数据库里新影厅的编号
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
    public int update(SaleItem sal)  //更新
    {
         /*sale_item_id         bigint not null auto_increment,
            ticket_id            bigint,
            sale_ID              bigint,
            sale_item_price      numeric(10,2),

         */
        String sql = "update sale_item set " + " ticket_id =" + sal.getTicketId()
                + ", " + " sale_ID = " + sal.getSaleId() + ", "
                + " sale_item_price = " + sal.getPrice()+" ";

        sql += " where sale_item_id = " + sal.getId();

        DBUtil db = new DBUtil();

        return db.execCommand(sql);  //直接执行
    }
    @Override
    public int delete(int ID)  //根据ID删除
    {
        String sql = "delete from  sale_item ";
        sql += " where sale_item_id = " + ID;
        DBUtil db = new DBUtil();
        return db.execCommand(sql);

    }
    @Override
    public List<SaleItem> select(String condt)
    {
        /*sale_item_id         bigint not null auto_increment,
            ticket_id            bigint,
            sale_ID              bigint,
            sale_item_price      numeric(10,2),

         */
        List<SaleItem> salList = null;//定义结果集
        salList=new LinkedList<SaleItem>();
        try {
            String sql = "select sale_item_id, ticket_id, sale_ID, sale_item_price, from sale_item ";//查询语句
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
                    SaleItem sal=new SaleItem();
                    sal.setId(rst.getInt("sale_item_id"));
                    sal.setTicketId(rst.getInt("ticket_id"));
                    sal.setSaleId(rst.getFloat("sale_ID"));
                    sal.setPrice(rst.getInt("sale_item_price"));

                    salList.add(sal);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salList;

    }
}
