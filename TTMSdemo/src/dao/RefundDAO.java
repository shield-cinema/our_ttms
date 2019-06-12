package dao;

import idao.iRefundDAO;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class RefundDAO implements iRefundDAO {

	QueryRunner qr = new TxQueryRunner();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	更改票状态，生成退单，删除sale_item表中的记录
	public int refund(long ticket_id,String username) {
		
		long currentMills = System.currentTimeMillis();
		String sql1,sql2,sql3,sql;
		String time = sdf.format(new Date(currentMills)).toString();
		double refund_money;
		Map m;
		BigDecimal bd;
		int rs = 0;
//		refund_money = getByIdPrice(ticket_id);
		try {
			sql = "SELECT ticket_price FROM ticket WHERE ticket_id=?";
			m = qr.query(sql, new MapHandler(),ticket_id);
			bd = (BigDecimal)m.get("ticket_price");
			refund_money = bd.doubleValue();
			sql1 = "CALL ticket_refund("+ticket_id+")";
			sql2 = "CALL insert_chargeback('"+username+"','"+time+"',"+refund_money+")";
			sql3 = "CALL delete_item("+ticket_id+")";
			rs = qr.update(sql1);
			rs = qr.update(sql2);
			rs = qr.update(sql3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

}
