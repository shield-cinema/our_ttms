package test;

import java.util.ArrayList;
import java.util.Arrays;

import model.Seat;

import org.junit.Test;

import dao.SeatDao;

public class TestSeat {
	@Test
	public void TestFindSeat(){
		SeatDao seatDao = new SeatDao();
		int[] seatID = new int[300];
		seatDao.findSeat(11);
		ArrayList<Seat> list = seatDao.findSeat(11);
		for(int i=0;i<list.size();i++){
			seatID[i] = list.get(i).getSeat_id();
		}
		Arrays.toString(seatID);
		System.out.println(seatID.toString());
		System.out.println(seatID[1]);
		System.out.println(seatID);
	}

}
