package service;

import domain.Seat;
import idao.DAOFactory;
import idao.ISeatDAO;

import java.util.List;

public class SeatSrv {
    private ISeatDAO seaDAO=DAOFactory.creatSeatDAO();

    public int add(Seat sea) { return seaDAO.insert(sea); }

    public int modify(Seat sea){
        return seaDAO.update(sea);
    }

    public int delete(int ID){
        return seaDAO.delete(ID);
    }

    public List<Seat> Fetch(String condt){
        return seaDAO.select(condt);
    }

    public List<Seat> FetchAll(){
        return seaDAO.select("");
    }
}
