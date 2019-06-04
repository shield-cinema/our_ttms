package idao;
import dao.*;
public class DAOFactory {
	
	public static IStudioDAO creatStudioDAO(){
		return new StudioDAO();
	}
}
