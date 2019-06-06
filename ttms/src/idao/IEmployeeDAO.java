package idao;

import domain.Employee;

import java.util.List;

public interface IEmployeeDAO {
    public int insert(Employee emp);
    public int update(Employee emp);
    public int delete(int ID);
    public List<Employee> select(String condt);
}
