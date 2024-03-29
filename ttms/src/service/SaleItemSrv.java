package service;

import domain.SaleItem;
import idao.DAOFactory;
import idao.ISaleItemDAO;

import java.util.List;

public class SaleItemSrv {
    private ISaleItemDAO salDAO=DAOFactory.creatSaleItemDAO();

    public int add(SaleItem sal) { return salDAO.insert(sal); }

    public int modify(SaleItem sal){
        return salDAO.update(sal);
    }

    public int delete(int ID){
        return salDAO.delete(ID);
    }

    public List<SaleItem> Fetch(String condt){
        return salDAO.select(condt);
    }

    public List<SaleItem> FetchAll(){
        return salDAO.select("");
    }
}
