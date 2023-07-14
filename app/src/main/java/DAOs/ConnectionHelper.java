package DAOs;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection con;
    private static final int PORT = 8888;
    private static final String DATABASE = "PRM392_FinalProject";
    private static final String JDBC_DRIVER = "net.sourceforge.jtds.jdbc.Driver";
    private static final String DB_URL = "jdbc:jtds:sqlserver://localhost:" + PORT + "/" + DATABASE;
    private static final String USER = "sa";
    private static final String PASSWORD = "sa123456";

    public static Connection getConnection(){
        Connection connection = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
