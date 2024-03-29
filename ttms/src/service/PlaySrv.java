package service;

import domain.Play;
import idao.DAOFactory;
import idao.IPlayDAO;

import java.util.List;

public class PlaySrv {
    private IPlayDAO plaDAO=DAOFactory.creatPlayDAO();

    public int add(Play pla) { return plaDAO.insert(pla); }

    public int modify(Play pla){
        return plaDAO.update(pla);
    }

    public int delete(int ID){
        return plaDAO.delete(ID);
    }

    public List<Play> Fetch(String condt){
        return plaDAO.select(condt);
    }

    public List<Play> FetchAll(){
        return plaDAO.select("");
    }
}
