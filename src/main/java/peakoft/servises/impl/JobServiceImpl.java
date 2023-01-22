package peakoft.servises.impl;

import peakoft.config.DatabaseConnection;
import peakoft.models.Job;
import peakoft.servises.JobService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobServiceImpl implements JobService {

    private Connection connection;

    public JobServiceImpl(){
        this.connection = DatabaseConnection.getConnection();

    }
    @Override
    public String createJobTable() {
        String query = """
                create table if not exists job(
                id serial primary key,
                position varchar ,
                profession varchar, 
                description varchar ,
                experience int
                """;

        try (Statement statement = connection.createStatement()){
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return "Successfully created on database!";
    }

    @Override
    public void addJob(Job job) {
        String query = """
                insert into jobs(position, profession, description, experience)
                values(?, ?, ?, ?);
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2, job.getProfession());
            preparedStatement.setString(3, job.getDescription());
            preparedStatement.setInt(4, job.getExperience());
            System.out.println("Successfully added on database!");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Job getJobById(Long id) {
        String query = """
                select * from jobs where id = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery(query);

            Job job = new Job();

            while (resultSet.next()) {
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));

            }
            return job;

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> jobList = new ArrayList<>();

        switch (ascOrDesc){
            case "asc":

                String query = """
                        select * from jobs where order by experience asc;
                        """;
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                ResultSet resultSet = preparedStatement.executeQuery(query);

                 while (resultSet.next()){
                     Job job = new Job();

                     job.setId(resultSet.getLong("id"));
                     job.setExperience(resultSet.getInt("experience"));
                     job.setDescription(resultSet.getString("description"));
                     jobList.add(job);
                 }
                 resultSet.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            break;

            case "desc":

                String sqlQuery = """
                        select * from jobs where order by experience desc;
                        """;

                try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
                    ResultSet resultSet= preparedStatement.executeQuery(sqlQuery);

                    while (resultSet.next()){
                        Job job = new Job();

                        job.setId(resultSet.getLong("id"));
                        job.setExperience(resultSet.getInt("experience"));
                        job.setPosition(resultSet.getString("position"));

                        jobList.add(job);
                    }
                    resultSet.close();
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
        }
        return jobList;
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        String query = """
                select * from jobs join employees e on jobs.id = e.job_id;
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            ResultSet resultSet = preparedStatement.executeQuery(query);
            Job job = new Job();
            while (resultSet.next()){
                job.setId(resultSet.getLong("id"));

            }
            return job;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteDescriptionColumn() {
        String query = """
                alter table jobs drop column description;
                """;
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(query);

            System.out.println("The database column has been deleted!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
