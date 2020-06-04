import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class DBConnection
{
    private static Connection connection;

    private static final String dbName = "learn";
    private static final String dbUser = "root";
    private static final String dbPass = "1234";

    private static StringBuilder insertQuery = new StringBuilder();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private static AtomicInteger countThread = new AtomicInteger(0);

    public static Connection getConnection() {

        if(connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?user=" + dbUser + "&password=" + dbPass +
                                "&serverTimezone=UTC");
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() {

        countThread.incrementAndGet();
        String query = insertQuery.toString();
        executorService.execute(new Thread(() -> {
            String sql =
                    "INSERT INTO voter_count(name, birthDate, `count`) " +
                            "VALUES " + query +
                            "ON DUPLICATE KEY UPDATE `count`=`count` + 1";
            try {
                DBConnection.getConnection().createStatement().execute(sql);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            countThread.decrementAndGet();
        }));

    }

    public static void countVoter(String name, String birthDay) {

        birthDay = birthDay.replace('.', '-');

        insertQuery
                .append(insertQuery.length() == 0 ? "" : ",")
                .append("('")
                .append(name)
                .append("', '")
                .append(birthDay)
                .append("', 1)");

        if (insertQuery.length() >= 40_000_000) {
            executeMultiInsert();
            insertQuery = new StringBuilder();
        }
    }

    public static void printVoterCounts() throws SQLException
    {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while(rs.next())
        {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }

    public static AtomicInteger getCountThread() {
        return countThread;
    }
}
