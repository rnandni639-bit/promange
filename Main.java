import java.sql.*;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static int getIntInput(String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input! Please enter numbers only.");
            sc.next();
            System.out.print(msg);
        }
        int num = sc.nextInt();
        sc.nextLine();
        return num;
    }
    public static void main(String[] args) {

        while (true) {

            System.out.println("\n==== ProManage Solutions ====");
            System.out.println("1. Add Project");
            System.out.println("2. View Projects");
            System.out.println("3. Generate Weekly Schedule");
            System.out.println("4. Exit");

            int choice = getIntInput("Enter choice: ");

            switch (choice) {

                case 1:
                    addProject();
                    break;

                case 2:
                    viewProjects();
                    break;

                case 3:
                    generateSchedule();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    public static void addProject() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Project Title: ");
            String title = sc.nextLine();

            int deadline = getIntInput("Enter Deadline (1-5 days): ");
            int revenue = getIntInput("Enter Revenue: ");

            if (deadline < 1 || deadline > 5) {
                System.out.println("❌ Deadline must be between 1 and 5!");
                return;
            }

            String sql =
                    "INSERT INTO projects(title, deadline, revenue) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setInt(2, deadline);
            ps.setInt(3, revenue);

            ps.executeUpdate();

            System.out.println("✅ Project Added Successfully!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void viewProjects() {

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM projects");

            System.out.println("\nID | Title | Deadline | Revenue");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("title") + " | " +
                                rs.getInt("deadline") + " | " +
                                rs.getInt("revenue")
                );
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void generateSchedule() {

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM projects");

            List<Project> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new Project(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("deadline"),
                        rs.getInt("revenue")
                ));
            }

            Scheduler.scheduleProjects(list);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

