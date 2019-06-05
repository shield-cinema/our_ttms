package dao;

import util.DBUtil;
import domain.Employee;
import idao.IEmployeeDAO;

import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
public class EmployeeDAO implements IEmployeeDAO{
    @Override
    public int insert(Employee emp)//��Ӱ����Ϣת��string,�������ݿ⣬���룬�������ݿ�ֵ
    {

        try {
            String sql = "insert into employee(emp_no, emp_name, emp_tel_num, emp_addr, emp_email )"
                    + " values('" + emp.getNo() + "', '"
                    + emp.getName() + "', '"
                    + emp.getTelNum() + "', '"
                    + emp.getAddress() + "', '"
                    + emp.getEmail() + "' )";
            DBUtil db = new DBUtil(); //���ݿ����ӣ�����ֱ����
            ResultSet rst = db.getInsertObjectIDs(sql);
            if (rst!=null && rst.first()) {
                emp.setId(rst.getInt(1));//�������ɹ����ͷ������ݿ�����Ӱ���ı��
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
    public int update(Employee emp)  //����
    {

        String sql = "update employee set " +
                " emp_no ='" + emp.getNo() + "', " +
                " emp_name ='" + emp.getName() + "', "+
                " emp_tel_num ='" + emp.getTelNum() + "', "+
                " emp_addr ='" + emp.getAddress() + "', "+
                " emp_email ='" + emp.getEmail() + "' ";

        sql += " where emp_id = " + emp.getId();

        DBUtil db = new DBUtil();

        return db.execCommand(sql);  //ֱ��ִ��

    }
    @Override
    public int delete(int ID)  //����IDɾ��
    {
        String sql = "delete from  employee ";
        sql += " where emp_id = " + ID;
        DBUtil db = new DBUtil();
        return db.execCommand(sql);

    }
    @Override
    public List<Employee> select(String condt)
    {
        List<Employee> plaList = null;//��������
        plaList=new LinkedList<Employee>();
        try {
            String sql = "select emp_id, emp_no, emp_name, emp_tel_num, emp_addr, emp_email from employee ";//��ѯ���
            condt.trim();//ȥ��ǰ��ո񣬿���ֱ����
            if(!condt.isEmpty())  //������ǿ�
                sql+= " where " + condt;//����where���
            DBUtil db = new DBUtil();
            ResultSet rst = db.execQuery(sql);  //��ѯ���õ������
            //System.out.print("sql:"+sql);
            if (rst!=null)   //�ҵ��Ľ�������´�������Ľ������ȥ
            {
                /*
        emp_id               int not null auto_increment,
        emp_no               char(20) not null,
        emp_name             varchar(100) not null,
        emp_tel_num          char(20),
        emp_addr             varchar(200),
        emp_email            varchar(100),
         */
                while(rst.next())
                {
                    Employee emp=new Employee();
                    emp.setId(rst.getInt("emp_id"));
                    emp.setNo(rst.getString("emp_no"));
                    emp.setName(rst.getString("emp_name"));
                    emp.setTelNum(rst.getString("emp_tel_num"));
                    emp.setAddress(rst.getString("emp_addr"));
                    emp.setEmail(rst.getString("emp_email"));
                    plaList.add(emp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plaList;
    }
}
