package idao;

import domain.Play;

import java.util.List;

public interface IPlayDAO {
    public int insert(Play pla);
    public int update(Play pla);
    public int delete(int ID);
    public List<Play> select(String condt);
}

