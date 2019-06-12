package service;

import dao.RefundDAO;

	
public class RefundService {
	private RefundDAO rf = new RefundDAO();
	
	public int refund(long ticket_id,String username) {
		
		return rf.refund(ticket_id,username);
	}
	
}
