import java.sql.*;

public class Main {
    public static void main(String[] args) {

//        String url = "jdbc:mysql://localhost:3306/skillbox";
        String url_2 = "jdbc:mysql://localhost:3306/skillbox"+
                "?verifyServerCertificate=false"+
//                "&allowPublicKeyRetrieval=true"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";

        String user = "root";
        String pass = "";

        try {
            Connection connection = DriverManager.getConnection(url_2, user, pass);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT name AS Courses, " +
                            "SUM(Grp.count) / COUNT(Grp.date) AS AVG_inMonth FROM Courses " +
                            "JOIN (SELECT course_name, MONTH(subscription_date) date, COUNT(*) count " +
                            "FROM PurchaseList GROUP BY course_name, date) Grp " +
                            "ON name = course_name " +
                            "GROUP BY name;");

            while (resultSet.next()) {

                System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
