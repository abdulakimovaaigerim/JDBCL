package peakoft.dao;

import peakoft.models.Employee;
import peakoft.models.Job;

import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;

public interface EmployeeDao {
    String createEmployee();
    void addEmployee(Employee employee);
    void dropTable();
    void cleanTable();
    void updateEmployee(Long id, Employee employee);
    List<Employee> getAllEmployees();
    Employee findByEmail(String email);
    Map<Employee, Job> getEmployeeById(Long employeeId);
    List<Employee> getEmployeeByPosition(String position);

}
