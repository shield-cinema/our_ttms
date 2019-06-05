package dao;


import util.DBUtil;
import domain.Sale;
import idao.ISaleDAO;

import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
public class SaleDAO implements ISaleDAO {
    @Override
    public int insert(Sale sa)//��Ӱ����Ϣת��string,�������ݿ⣬���룬�������ݿ�ֵ
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
            DBUtil db = new DBUtil(); //���ݿ����ӣ�����ֱ����
            ResultSet rst = db.getInsertObjectIDs(sql);
            if (rst!=null && rst.first()) {
                sa.setId(rst.getInt(1));//�������ɹ����ͷ������ݿ�����Ӱ���ı��
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
    public int update(Sale sa)  //����
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

        return db.execCommand(sql);  //ֱ��ִ��
    }
    @Override
    public int delete(int ID)  //����IDɾ��
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
	   sale_type            smallint comment '���ȡֵ���壺
	            1�����۵�
	            -1���˿',
	   sale_status          smallint comment '���۵�״̬���£�
	            0��������
	            1���Ѹ���',*/
        List<Sale> saList = null;//��������
        saList=new LinkedList<Sale>();
        try {
            String sql = "select sale_ID, emp_id, sale_time, sale_payment, sale_change, sale_type, sale_status from sale ";//��ѯ���
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
