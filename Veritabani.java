import java.sql.*;
public class Veritabani {
    public static Connection vbBaglan() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/okul",
                    "root", ""
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
