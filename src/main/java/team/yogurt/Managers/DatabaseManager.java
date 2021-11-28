package team.yogurt.Managers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private Connection connection;

    public DatabaseManager(String username, String password, String database, String host, int port, String extra){
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database+extra;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean isConnected(){
        return connection != null;
    }
    public void disconnect(){
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public Connection getConnection() {
        return this.connection;
    }

}
