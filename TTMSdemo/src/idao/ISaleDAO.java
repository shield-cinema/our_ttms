package idao;

import domain.*;

import java.util.List;

import domain.Sale;
public interface ISaleDAO {
    public int insert(Sale sa);
    public int update(Sale sa);
    public int delete(int ID);
    public List<Sale> select(String condt);
}

