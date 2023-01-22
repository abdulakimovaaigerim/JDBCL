package peakoft.dao.impl;

import peakoft.config.DatabaseConnection;
import peakoft.dao.EmployeeDao;
import peakoft.models.Employee;
import peakoft.models.Job;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao {

  EmployeeDao employeeDao = new EmployeeDaoImpl();

  public EmployeeDaoImpl() {

  }

  @Override
    public String createEmployee() {
    employeeDao.createEmployee();
        return "Successfully created!";
    }

    @Override
    public void addEmployee(Employee employee) {
    employeeDao.addEmployee(employee);

    }

    @Override
    public void dropTable() {
    employeeDao.dropTable();

    }

    @Override
    public void cleanTable() {
    employeeDao.cleanTable();

    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
    employeeDao.updateEmployee(id, employee);

    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeDao.findByEmail(email);
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        return employeeDao.getEmployeeByPosition(position);
    }
}
