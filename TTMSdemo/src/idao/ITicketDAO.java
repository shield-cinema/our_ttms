package idao;
import domain.*;

import java.util.List;

import domain.Ticket;
public interface ITicketDAO
{
    public int insert(Ticket tic);
    public int update(Ticket tic);
    public int delete(int ID);
    public List<Ticket> select(String condt);
    public int lockTicket(int ID, String time);
    public int unlockTicket(int ID);
}
