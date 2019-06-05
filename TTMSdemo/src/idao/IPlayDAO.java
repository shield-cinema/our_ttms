package idao;
import domain.*;

import java.util.List;

import domain.Play;

public interface IPlayDAO {
    public int insert(Play pla);
    public int update(Play pla);
    public int delete(int ID);
    public List<Play> select(String condt);
}

