package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends Configs {

    private static final String URL = "jdbc:mysql://localhost:3306/genius?" +
            "useSSL=true&amp;autoReconnect=true&amp;serverTimezone=UTC";
    Connection dbConnection;

    public Connection getConnection(){
          try {
            dbConnection = DriverManager.getConnection(URL,dbuser,dbpass);
            if (!dbConnection.isClosed()){
                System.out.println("соединенеие с бд установлена");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

}
