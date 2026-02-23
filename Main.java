import dao.ProjectDAO;
import model.Project;
import scheduler.GreedyScheduler;
import ml.MLModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String URL = "jdbc:postgresql://localhost:5432/project_scheduling_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "tripti@123";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            Scanner scanner = new Scanner(System.in);
            ProjectDAO projectDAO = new ProjectDAO(connection);
            GreedyScheduler scheduler = new GreedyScheduler();
            MLModel model = new MLModel();

            while (true) {

                System.out.println("\n==== Project Scheduling System ====");
                System.out.println("1. Add Project");
                System.out.println("2. View All Projects");
                System.out.println("3. Generate Weekly Schedule");
                System.out.println("4. Exit");
                System.out.print("Choose option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1:
                        System.out.print("Enter Project Title: ");
                        String title = scanner.nextLine();

                        System.out.print("Enter Deadline (1-5 days): ");
                        int deadline = scanner.nextInt();

                        System.out.print("Enter Revenue: ");
                        double revenue = scanner.nextDouble();

                        // Get past revenues for prediction
                        List<Project> allProjects = projectDAO.getAllProjects();
                        List<Double> pastRevenues = new ArrayList<>();

                        for (Project p : allProjects) {
                            pastRevenues.add(p.getRevenue());
                        }

                        double predictedRevenue = model.predictRevenue(pastRevenues);

                        Project project = new Project();
                        project.setTitle(title);
                        project.setDeadline(deadline);
                        project.setRevenue(revenue);
                        project.setPredictedRevenue(predictedRevenue);
                        project.setStatus("PENDING");

                        projectDAO.addProject(project);

                        System.out.println("Project Added Successfully!");
                        break;

                    case 2:
                        List<Project> projects = projectDAO.getAllProjects();

                        System.out.println("\n--- All Projects ---");
                        for (Project p : projects) {
                            System.out.println(
                                    "ID: " + p.getId() +
                                            ", Title: " + p.getTitle() +
                                            ", Deadline: " + p.getDeadline() +
                                            ", Revenue: " + p.getRevenue() +
                                            ", Predicted: " + p.getPredictedRevenue() +
                                            ", Status: " + p.getStatus()
                            );
                        }
                        break;

                    case 3:
                        List<Project> pendingProjects = projectDAO.getPendingProjects();

                        List<Project> scheduled = scheduler.scheduleProjects(pendingProjects);

                        System.out.println("\n--- Weekly Schedule ---");

                        int day = 1;
                        for (Project p : scheduled) {
                            System.out.println("Day " + day + ": " + p.getTitle());
                            p.setStatus("SCHEDULED");
                            projectDAO.updateProjectStatus(p.getId(), "SCHEDULED");
                            day++;
                        }

                        System.out.println("Scheduling Completed!");
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
