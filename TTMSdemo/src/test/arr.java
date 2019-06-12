package test;

import model.Studio;

import org.junit.Test;

import service.ScheService;
import service.StudioService;
import dao.ScheDao;


public class arr {
	
	@Test
	public void TestfindAllStudio(){
		StudioService ss = new StudioService();
		String str = ss.findAllStudio();
		System.out.println(str);
	}
	
	@Test
	public void TestAddStudio(){
		StudioService ss = new StudioService();
		Studio sd = new Studio("pikaqiu5",2,2,"buhao");
		System.out.println(ss.addStudio(sd));
	}
	@Test
	public void TestdelStudio(){
		StudioService ss = new StudioService();
		System.out.println(ss.delStudio(10));
	}
	@Test
	public void TestUpRow(){
		StudioService ss = new StudioService();
		System.out.println(ss.updataCol(11, 3));
	}
	@Test
	public void Ts(){
		ScheService sc = new ScheService();
		System.out.println(sc.findStatus(1));
	}

}
