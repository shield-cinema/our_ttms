package idao;

import domain.Seat;

import java.util.List;

public interface ISeatDAO {

    public int insert(Seat sea);
    public int update(Seat sea);
    public int delete(int ID);
    public List<Seat> select(String condt);

}
