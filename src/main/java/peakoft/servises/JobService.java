package peakoft.servises;

import peakoft.models.Job;

import java.util.List;

public interface JobService {
    String createJobTable();
    void addJob(Job job);
    Job getJobById(Long id);
    List<Job> sortByExperience(String ascOrDesc);
    Job getJobByEmployeeId(Long employeeId);
    void deleteDescriptionColumn();
}
