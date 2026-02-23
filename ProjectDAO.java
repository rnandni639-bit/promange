
     package dao;

import model.Project;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    private Connection connection;

    // ✅ ADD THIS CONSTRUCTOR
    public ProjectDAO(Connection connection) {
        this.connection = connection;
    }

    // Add Project
    public void addProject(Project project) throws SQLException {

        String sql = "INSERT INTO projects (title, deadline, revenue, predicted_revenue, status) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, project.getTitle());
        stmt.setInt(2, project.getDeadline());
        stmt.setDouble(3, project.getRevenue());
        stmt.setDouble(4, project.getPredictedRevenue());
        stmt.setString(5, project.getStatus());

        stmt.executeUpdate();
    }

    // Get All Projects
    public List<Project> getAllProjects() throws SQLException {

        List<Project> projects = new ArrayList<>();

        String sql = "SELECT * FROM projects";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {

            Project p = new Project();
            p.setId(rs.getInt("id"));
            p.setTitle(rs.getString("title"));
            p.setDeadline(rs.getInt("deadline"));
            p.setRevenue(rs.getDouble("revenue"));
            p.setPredictedRevenue(rs.getDouble("predicted_revenue"));
            p.setStatus(rs.getString("status"));

            projects.add(p);
        }

        return projects;
    }

    // Get Only Pending Projects
    public List<Project> getPendingProjects() throws SQLException {

        List<Project> projects = new ArrayList<>();

        String sql = "SELECT * FROM projects WHERE status = 'PENDING'";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {

            Project p = new Project();
            p.setId(rs.getInt("id"));
            p.setTitle(rs.getString("title"));
            p.setDeadline(rs.getInt("deadline"));
            p.setRevenue(rs.getDouble("revenue"));
            p.setPredictedRevenue(rs.getDouble("predicted_revenue"));
            p.setStatus(rs.getString("status"));

            projects.add(p);
        }

        return projects;
    }

    // Update Status
    public void updateProjectStatus(int id, String status) throws SQLException {

        String sql = "UPDATE projects SET status = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, status);
        stmt.setInt(2, id);

        stmt.executeUpdate();
    }
}
