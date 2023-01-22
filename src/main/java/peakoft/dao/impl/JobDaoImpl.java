package peakoft.dao.impl;

import peakoft.config.DatabaseConnection;
import peakoft.dao.JobDao;
import peakoft.models.Job;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JobDaoImpl implements JobDao {

    JobDao jobDao = new JobDaoImpl();

    public JobDaoImpl() {
    }

    @Override
    public String createJobTable() {
        jobDao.createJobTable();
       return "Successfully created!";
}

    @Override
    public void addJob(Job job) {
        jobDao.addJob(job);

    }

    @Override
    public Job getJobById(Long id) {
        return jobDao.getJobById(id);
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        return jobDao.sortByExperience(ascOrDesc);
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        return jobDao.getJobByEmployeeId(employeeId);
    }

    @Override
    public void deleteDescriptionColumn() {
        jobDao.deleteDescriptionColumn();

    }
}
