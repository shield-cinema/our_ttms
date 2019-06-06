package idao;

import domain.DataDict;

import java.util.List;

public interface IDataDictDAO {
    //对数据字典的增删改查操作（暂理解为历史记录）
    public int insert(DataDict dd);
    public int delete(int ID);
    public int update(DataDict dd);
    public List<DataDict> select(String condition);
}
