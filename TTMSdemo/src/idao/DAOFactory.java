package idao;
import dao.*;
public class DAOFactory {
	
	public static IStudioDAO creatStudioDAO(){
		return new StudioDAO();
	}

	public static ITicketDAO creatTicketDAO(){
		return new TicketDAO();
	}

	public static ISeatDAO creatSeatDAO(){
		return new SeatDAO();
	}
}
