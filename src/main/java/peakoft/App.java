package peakoft;

import peakoft.models.Employee;
import peakoft.models.Job;
import peakoft.servises.EmployeeService;
import peakoft.servises.JobService;
import peakoft.servises.impl.EmployeeServiceImpl;
import peakoft.servises.impl.JobServiceImpl;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    static Scanner scanner = new Scanner(System.in);
    static Scanner scannerN = new Scanner(System.in);

    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeServiceImpl();

        JobService jobService = new JobServiceImpl();


        while (true) {
            System.out.println("<<<COMMANDS>>>");
            int cmd1 = scannerN.nextInt();
            System.out.println("""
                    |1| button -> createEmployee
                    |2| button -> addEmployee
                    |3| button -> dropTable
                    |4| button -> cleanTable
                    |5| button -> updateEmployee
                    |6| button -> List<Employee> getAllEmployees
                    |7| button -> findByEmail
                    |8| button -> getEmployeeById
                    |9| button -> getEmployeeByPosition
                    |0| button -> Break
                    """);

            switch (cmd1) {
                case 1:
                    employeeService.createEmployee();
                    break;
                case 2:
                    employeeService.addEmployee(new Employee(
                            "Almakan", "Mamytova", 34, "almakan@gmail.com", 1));
                    break;
                case 3:
                    employeeService.dropTable();
                    break;
                case 4:
                    employeeService.cleanTable();
                    break;
                case 5:
                    employeeService.updateEmployee(1L, new Employee(
                            "Almakan", "Mamytova", 34, "almakan@gmail.com", 1));
                    break;
                case 6:
                    System.out.println(employeeService.getAllEmployees());
                    break;
                case 7:
                    String email = new Scanner(System.in).nextLine();
                    employeeService.findByEmail(email);
                    break;
                case 8:
                    Long id = new Scanner(System.in).nextLong();
                    employeeService.getEmployeeById(id);
                    break;
                case 9:
                    String position = new Scanner(System.in).nextLine();
                    employeeService.getEmployeeByPosition(position);
                    break;
                default:
                    System.out.println("THERE IS NO SUCH WAY!");

            }

            while (true) {
                int cmd2 = scannerN.nextInt();
                System.out.println("<<<COMMANDS>>>");
                System.out.println("""
                        |1| button -> createTable
                        |2| button -> addJob
                        |3| button -> getJobById
                        |4| button -> sortByExperience
                        |5| button -> getJobEmployeeId
                        |6| button -> deleteDescriptionColumn
                        |0| button -> Break
                        """);

                switch (cmd2) {
                    case 1:
                        jobService.createJobTable();
                        break;
                    case 2:
                        jobService.addJob(new Job("Mentor", "Java", "Backend developer", 1));
                        break;
                    case 3:
                        jobService.getJobByEmployeeId(1L);
                        break;
                    case 4:
                        System.out.println(jobService.sortByExperience("desc"));
                        break;
                    case 5:
                        System.out.println(jobService.getJobByEmployeeId(1L));
                        break;
                    case 6:
                        jobService.deleteDescriptionColumn();
                    default:
                        System.out.println("THERE IS NO SUCH WAY!");


                }
            }
        }
    }
}