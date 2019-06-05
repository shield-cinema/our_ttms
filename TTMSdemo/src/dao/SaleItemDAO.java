package dao;


import util.DBUtil;
import domain.SaleItem;
import idao.ISaleItemDAO;

import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
public class SaleItemDAO implements ISaleItemDAO{

    @Override
    public int insert(SaleItem sal)//��Ʊ��Ϣת��string,�������ݿ⣬���룬�������ݿ�ֵ
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
            DBUtil db = new DBUtil(); //���ݿ����ӣ�����ֱ����
            ResultSet rst = db.getInsertObjectIDs(sql);
            if (rst!=null && rst.first()) {
                sal.setId(rst.getInt(1));//�������ɹ����ͷ������ݿ�����Ӱ���ı��
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
    public int update(SaleItem sal)  //����
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

        return db.execCommand(sql);  //ֱ��ִ��
    }
    @Override
    public int delete(int ID)  //����IDɾ��
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
        List<SaleItem> salList = null;//��������
        salList=new LinkedList<SaleItem>();
        try {
            String sql = "select sale_item_id, ticket_id, sale_ID, sale_item_price, from sale_item ";//��ѯ���
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
