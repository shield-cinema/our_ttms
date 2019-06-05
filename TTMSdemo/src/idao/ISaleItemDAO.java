package idao;
import domain.*;

import java.util.List;

import domain.SaleItem;

public interface ISaleItemDAO {
    public int insert(SaleItem stu);
    public int update(SaleItem stu);
    public int delete(int ID);
    public List<SaleItem> select(String condt);
}
