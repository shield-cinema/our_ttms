package idao;

public interface iUserManageDAO {
	
	public int updateUser(String want, String info, String username);
	public int updateUserIcon(byte[] bytes, String username);
}
