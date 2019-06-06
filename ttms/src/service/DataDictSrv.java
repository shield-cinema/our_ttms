package service;

import domain.DataDict;
import idao.DAOFactory;
import idao.IDataDictDAO;

import java.util.List;

public class DataDictSrv {
    //创建实体实例
    private IDataDictDAO dataDictDAO = DAOFactory.createDataDictDAO();

    public int add(DataDict dataDict){
        return dataDictDAO.insert(dataDict);
    }

    public int update(DataDict dataDict){
        return dataDictDAO.update(dataDict);
    }

    public int delete(int ID){
        return dataDictDAO.delete(ID);
    }

    //根据条件进行部分查找
    public List<DataDict> Fetch(String condition){
        return dataDictDAO.select(condition);
    }

    //全部查找，相当于select *;
    public List<DataDict> FetchAll(){
        return dataDictDAO.select("");
    }

}
