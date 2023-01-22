package peakoft.servises.impl;

import peakoft.config.DatabaseConnection;
import peakoft.models.Employee;
import peakoft.models.Job;
import peakoft.servises.EmployeeService;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {

    private Connection connection;

    public EmployeeServiceImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public String createEmployee() {
        String query = """
                create table if not exists employee(
                id serial primary key,
                first_name varchar,
                last_name varchar,
                age smallint not null,
                email varchar unique,
                experience job_id int reference job(id);
                """;

        try (Statement statement = connection.createStatement()){
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return "Successfully created on database!";
    }

    @Override
    public void addEmployee(Employee employee) {
        String query = """
                insert into employees(first_name, last_name, age, email, job_id)
                values(?, ?, ?, ?, ?);
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setInt(5, employee.getJobId());
            System.out.println("Successfully added on database!");

        }catch (SQLException e){
            System.out.println(e.getMessage());

        }


    }

    @Override
    public void dropTable() {
        String query = """
                drop table jobs;
                """;
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println("Table deleted on database!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void cleanTable() {
        String query = """
                truncate table jobs;
                """;
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println("Table is truncate on database!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        String query = """
                update employees
                set first_name, age
                where id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setInt(2, employee.getAge());
            preparedStatement.setLong(3, id);
            System.out.println("Successfully updated!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Employee> getAllEmployees() {
        String query = "select * from employees;";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            List<Employee> employeeList = new ArrayList<>();
            while (resultSet.next()){
                employeeList.add(new Employee(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("id")
                ));
            }
            return employeeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee findByEmail(String email) {
        String query = """
                select * from employee where email = ?;
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            ResultSet resultSet = preparedStatement.executeQuery(query);

            Employee employee = new Employee();
            while (resultSet.next()){
                employee.setID(resultSet.getLong("id"));
                employee.setEmail(resultSet.getString("email"));

            }
            return employee;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
            Map<Employee, Job> map = new HashMap<>();
        String query = """
                select * from employees join jobs j on j.id = employees.job_id;
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()){
                map.put(new Employee(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("id")),
                        new Job(
                                resultSet.getString("position"),
                                resultSet.getString("profession"),
                                resultSet.getString("description"),
                                resultSet.getInt("experience")
                        ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee> employeeList = new ArrayList<>();
        String query = """
                select position from jobs join employees e on jobs.id = e.job_id;
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, position);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return employeeList;
    }
}
