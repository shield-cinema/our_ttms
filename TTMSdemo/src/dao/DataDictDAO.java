package dao;

import idao.IDataDictDAO;
import domain.DataDict;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DataDictDAO implements IDataDictDAO {

    @Override
    public int insert(DataDict dd) {

        try{
            //获取SQL语句执行内容
            //向表中添加数据，添加的是实参对象中的数据
            String sql = "insert into DataDict(id,superId,index,name,value)"
                    + "values("
                    + dd.getSuperId()
                    + ","
                    +dd.getIndex()
                    + ",'"
                    + dd.getName()
                    + "','"
                    + dd.getValue()
                    + "')";

            DBUtil db = new DBUtil();   //获取数据库操作类的实例
            ResultSet rs = db.getInsertObjectIDs(sql);  //调用插入方法执行sql语句

            //插入信息成功
            //将光标移动到此结果集的第一行
            if(rs != null && rs.first()){
                //将结果集中生成的索引标识作为一条记录的主键
                dd.setId(rs.getInt(1));
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    //根据ID删除记录
    @Override
    public int delete(int ID) {
        //返回是否成功删除数据
        int flag = 0;
        try{
            //初始化执行的sql语句
            String sql = "delete from DataDict";    //根据ID从数据库表中删除数据
            sql += "where id = " + ID;

            DBUtil db = new DBUtil();
            flag = db.execCommand(sql);
        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int update(DataDict dd) {
        //更新成功的标志
        int flag = 0;
        try{
            //更新语句
            String sql = "update DataDict set id = " + dd.getId()
                    + ",set superId = " + dd.getSuperId()
                    + ",set index = " + dd.getIndex()
                    + ",set name  = '" + dd.getName()
                    + "',set value = '" + dd.getValue()
                    + "'";
            //ID作为唯一标志，表的主键
            sql  += "where id = " + dd.getId();
            DBUtil db = new DBUtil();

            flag =  db.execCommand(sql);
            return flag;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<DataDict> select(String condition) {
        //存储查询结果集
        List<DataDict> DataDictlist = null;
        DataDictlist = new LinkedList<DataDict>();

        try{
            //查询语句
            //未加sql语句，避免无条件的情况
            String sql = "select id,superId,index,name,value";
            condition.trim(); //去除前后的空白字符（空格、tab、回车）

            //查询条件不为空
            if(!condition.isEmpty()){
                sql += " where " + condition;
            }

            //获取数据库操作实例
            DBUtil db = new DBUtil();
            ResultSet rs = db.execQuery(sql);

            //创建DataDict对象，将结果集中的结果赋值给该对象
            if(rs != null){
                while(rs.next()){
                    DataDict datadict = new DataDict();
                    //根据列名插入数据
                    datadict.setId(rs.getInt("id"));
                    datadict.setSuperId(rs.getInt("superId"));
                    datadict.setIndex(rs.getInt("index"));
                    datadict.setName(rs.getString("name"));
                    datadict.setValue(rs.getString("datadict_value"));
                    //
                    // 将对象记录
                    DataDictlist.add(datadict);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return DataDictlist;
    }
}
