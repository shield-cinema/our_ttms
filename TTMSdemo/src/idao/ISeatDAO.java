package idao;
import domain.*;

import java.util.List;

import domain.Seat;

public interface ISeatDAO {

    public int insert(Seat sea);
    public int update(Seat sea);
    public int delete(int ID);
    public List<Seat> select(String condt);

}
