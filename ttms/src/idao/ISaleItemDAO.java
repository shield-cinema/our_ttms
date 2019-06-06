package idao;

import domain.SaleItem;

import java.util.List;

public interface ISaleItemDAO {
    public int insert(SaleItem stu);
    public int update(SaleItem stu);
    public int delete(int ID);
    public List<SaleItem> select(String condt);
}
