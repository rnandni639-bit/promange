public class Project {

    private String title;
    private double revenue;
    private String deadline;

    // Constructor
    public Project(String title, double revenue, String deadline) {
        this.title = title;
        this.revenue = revenue;
        this.deadline = deadline;
    }
    public String getTitle() {
        return title;
    }

    public double getRevenue() {
        return revenue;
    }

    public String getDeadline() {
        return deadline;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    // Display Method
    public void displayProject() {
        System.out.println("Title: " + title);
        System.out.println("Revenue: " + revenue);
        System.out.println("Deadline: " + deadline);
    }
}

