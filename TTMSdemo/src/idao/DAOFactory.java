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

	public static ISaleItemDAO creatSaleItemDAO(){
		return new SaleItemDAO();
	}

	public static ISaleDAO creatSaleDAO(){
		return new SaleDAO();
	}

	public static IPlayDAO creatPlayDAO(){
		return new PlayDAO();
	}

	public static IEmployeeDAO creatEmployeeDAO(){
		return new EmployeeDAO();
	}
}
