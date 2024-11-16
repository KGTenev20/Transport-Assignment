package utils;
import java.sql.*;

public class DBConnection {

    private static Connection connectionInstance = null;
    public static Connection getInstance() {
        if (connectionInstance == null) {
            try {
                String connectionURL = "jdbc:sqlserver://localhost:1433;"
                        + "trustServerCertificate=true;"
                        + "integratedSecurity=true;"
                        + "database=PublicTransportBatteryManagementSystem";

                //transportproject.database.windows.net
                String azureDatabase = "jdbc:sqlserver://transportproject.database.windows.net:1433;" +
                        "database=TransportManagementSystem;" +
                        "user=DbUser;" +
                        "password=Password123;" +
                        "encrypt=true;" +
                        "trustServerCertificate=false;" +
                        "hostNameInCertificate=*.database.windows.net;" +
                        "loginTimeout=30;";

                connectionInstance = DriverManager.getConnection(azureDatabase);
                return connectionInstance;
            } catch (SQLException exception) {
                throw new RuntimeException(exception);
            }
        }
        else {
            return connectionInstance;
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = getInstance();
        Statement statement = connection.createStatement();
    }
}
