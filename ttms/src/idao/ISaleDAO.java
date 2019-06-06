package idao;

import domain.Sale;

import java.util.List;

public interface ISaleDAO {
    public int insert(Sale sa);
    public int update(Sale sa);
    public int delete(int ID);
    public List<Sale> select(String condt);
}

