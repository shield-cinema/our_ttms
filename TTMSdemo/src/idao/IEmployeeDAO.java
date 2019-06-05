package idao;
import domain.*;

import java.util.List;

import domain.Employee;

public interface IEmployeeDAO {
    public int insert(Employee emp);
    public int update(Employee emp);
    public int delete(int ID);
    public List<Employee> select(String condt);
}
