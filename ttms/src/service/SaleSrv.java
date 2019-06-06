package service;

import domain.Sale;
import idao.DAOFactory;
import idao.ISaleDAO;

import java.util.List;

public class SaleSrv {
    private ISaleDAO saDAO=DAOFactory.creatSaleDAO();

    public int add(Sale sa) { return saDAO.insert(sa); }

    public int modify(Sale sa){
        return saDAO.update(sa);
    }

    public int delete(int ID){
        return saDAO.delete(ID);
    }

    public List<Sale> Fetch(String condt){
        return saDAO.select(condt);
    }

    public List<Sale> FetchAll(){
        return saDAO.select("");
    }
}
