package service;

import domain.Employee;
import idao.DAOFactory;
import idao.IEmployeeDAO;

import java.util.List;

public class EmployeeSrv {
    private IEmployeeDAO empDAO= DAOFactory.creatEmployeeDAO();

    public int add(Employee emp) { return empDAO.insert(emp); }

    public int modify(Employee emp){
        return empDAO.update(emp);
    }

    public int delete(int ID){
        return empDAO.delete(ID);
    }

    public List<Employee> Fetch(String condt){
        return empDAO.select(condt);
    }

    public List<Employee> FetchAll(){
        return empDAO.select("");
    }
}
