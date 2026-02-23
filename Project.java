package model;

public class Project {

    private int id;
    private String title;
    private int deadline;
    private double revenue;
    private double predictedRevenue;
    private String status;

    // 🔹 Default Constructor
    public Project() {
    }

    // 🔹 Constructor without predictedRevenue & status
    public Project(int id, String title, int deadline, double revenue) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.revenue = revenue;
        this.status = "PENDING";
    }

    // 🔹 Full Constructor
    public Project(int id, String title, int deadline,
                   double revenue, double predictedRevenue, String status) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.revenue = revenue;
        this.predictedRevenue = predictedRevenue;
        this.status = status;
    }

    // ==========================
    // Getters
    // ==========================

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDeadline() {
        return deadline;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getPredictedRevenue() {
        return predictedRevenue;
    }

    public String getStatus() {
        return status;
    }

    // ==========================
    // Setters
    // ==========================

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public void setPredictedRevenue(double predictedRevenue) {
        this.predictedRevenue = predictedRevenue;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ==========================
    // toString() Method
    // ==========================

    @Override
    public String toString() {
        return "Project {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", deadline=" + deadline +
                ", revenue=" + revenue +
                ", predictedRevenue=" + predictedRevenue +
                ", status='" + status + '\'' +
                '}';
    }
}
