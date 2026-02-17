import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        String url = "jdbc:postgresql://localhost:5432/promanage_db";
        String user = "postgres";
        String password = "tripti@123";

        return DriverManager.getConnection(url, user, password);
    }
}