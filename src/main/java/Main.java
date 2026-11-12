import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/student_db";
        String user = "postgres";
        String password = System.getenv("DB_PASSWORD");

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            System.out.println("Connected to PostgreSQL!");
            //call CRUD function to interact with students table
            //getAllStudents(connection);
            //addStudent(connection, "Alice", "Brown", "alice.brown@example.com", "2024-01-15");
            //updateStudentEmail(connection, 4, "alice.new@example.com");
            //deleteStudent(connection, 4);

            connection.close();
        }
        catch (Exception e) {
            System.out.println(e); // show execution errors
        }
    }

    // retrieves and displays all students from database
    public static void getAllStudents(Connection connection) {
        String sql = "SELECT * FROM students";

        try {
            // execute SELECT query
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // loop through results and display student info
            while (result.next()) {
                System.out.println(
                        result.getInt("student_id") + " | " +
                        result.getString("first_name") + " | " +
                        result.getString("last_name") + " | " +
                        result.getString("email") + " | " +
                        result.getDate("enrollment_date")
                );
            }
            // close result and staetment
            result.close();
            statement.close();
        }
        catch (Exception e) {
            System.out.println(e); // show errors
        }
    }

    // adds new student to database using provided info
    public static void addStudent(Connection connection, String first_name, String last_name, String email, String enrollment_date) {
        // sql query with placeholders for student info
        String sql = "INSERT INTO students " +
                "(first_name, last_name, email, enrollment_date) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            // set values for each placeholder in sql query
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, email);
            statement.setDate(4, java.sql.Date.valueOf(enrollment_date));

            // execute query
            statement.executeUpdate();

            System.out.println("Student added successfully!");
        }
        catch (Exception e) {
            // show errors
            System.out.println(e);
        }
    }

    // update student email address using  student id
    public static void updateStudentEmail(Connection connection, int student_id, String new_email) {
        // sql UPDATE query for specific student
        String sql = "UPDATE students SET email = ? WHERE student_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // set new email and id of student to update
            statement.setString(1, new_email);
            statement.setInt(2, student_id);

            // execute UPDATE query and get number of affects rows
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Student email updated successfully!");
            }
            else {
                System.out.println("Student not found!");
            }
        }
        catch (Exception e) {
            // display errors while updating email
            System.out.println(e);
        }
    }

    // deletes student from database using student id
    public static void deleteStudent(Connection connection, int student_id) {
        // sql DELETE query for specific student
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // set id of student to delete
            statement.setInt(1, student_id);

            // execute DELETE query and get # of affected rows
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Student deleted successfully!");
            }
            else {
                System.out.println("Student not found!");
            }
        }
        // display errors
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
