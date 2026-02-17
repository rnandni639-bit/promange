//package com.promanage.db;

import com.promanage.model.Project;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    public void addProject(Project project) throws SQLException {
        String sql = "INSERT INTO projects(title, deadline, revenue) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, project.getTitle());
            stmt.setInt(2, project.getDeadline());
            stmt.setDouble(3, project.getRevenue());
            stmt.executeUpdate();
        }
    }

    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM projects";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                projects.add(new Project(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("deadline"),
                        rs.getDouble("revenue")
                ));
            }
        }
        return projects;
    }
}