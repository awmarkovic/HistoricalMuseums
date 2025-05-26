import com.mysql.cj.jdbc.Driver;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    // An own class to get access to the database (loading the properties-file)
    private static final String propertiesFile = "files/database.properties";
    private final Properties properties;
    private Connection connection;

    public Database() {
        this.properties = new Properties();

        // Loading database.properties file
        try (FileInputStream input = new FileInputStream(propertiesFile)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Error loading properties file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to connect to the database
    public void connect() {
        try {
            // Registering the driver
            DriverManager.registerDriver(new Driver());

            // Getting properties from my properties file
            String host = this.properties.getProperty("host");
            String port = this.properties.getProperty("port");
            String database = this.properties.getProperty("database");
            String username = this.properties.getProperty("username");
            String password = this.properties.getProperty("password");

            // Making my JDBC url
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;


            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.err.println("ERROR! Not connected to database" + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

}
