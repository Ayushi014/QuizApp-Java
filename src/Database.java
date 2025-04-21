import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:db/quiz.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            String createUsers = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT UNIQUE," +
                    "password TEXT)";

            String createQuestions = "CREATE TABLE IF NOT EXISTS questions (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "question TEXT," +
                    "optionA TEXT," +
                    "optionB TEXT," +
                    "optionC TEXT," +
                    "optionD TEXT," +
                    "correct TEXT)";

            stmt.execute(createUsers);
            stmt.execute(createQuestions);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

