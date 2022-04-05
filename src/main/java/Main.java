import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) throws SQLException {
        try {
            connect();

            List<Classmates> students = findAll();
            increaseScore(students, 50);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static List<Classmates> findAll() throws SQLException {
        List<Classmates> students = new ArrayList<>();
        try (ResultSet rs = statement.executeQuery("select * from classmates;")) {
            while (rs.next()) {
                Classmates student = new Classmates(rs.getInt(1), rs.getString(2), rs.getInt(3));
                student.info();
                System.out.println();
                students.add(student);
            }
        }

        return students;
    }

    private static void increaseScore(List<Classmates> students, int count) throws SQLException {
        connection.setAutoCommit(false);
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("update classmates set score = ? where id = ?")) {
            for (Classmates student : students) {
                preparedStatement.setInt(1, student.getScore() + count);
                preparedStatement.setInt(2, student.getId());
                preparedStatement.executeUpdate();
            }

            connection.commit();
        }
    }

    private static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ToChat", "postgres", "fK1Z)Ua-4L8");
        statement = connection.createStatement();
    }

    private static void insert(String name, int score) throws SQLException {
        statement.executeUpdate(String.format("INSERT INTO classmates(name, score) values('%s', %s);", name, score));
    }

    private static void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
