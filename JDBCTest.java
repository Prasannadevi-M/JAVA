TC1:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBCTest {
   public static void main(String[] args) {
       String url = "jdbc:mysql://localhost:3306/student?useSSL=false&serverTimezone=UTC";
       String user = "root";
       String password = "12345";
       System.out.println("prasannadevi M");
       System.out.println("2117240070228");
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection conn = DriverManager.getConnection(url, user, password);
           System.out.println("Connected");
           conn.close();
       } catch (ClassNotFoundException e) {
           System.out.println("Driver not found");
           e.printStackTrace();
       } catch (SQLException e) {
           System.out.println("Connection failed: " + e.getMessage());
       }
   }
}
TC2:
package com.java.practice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class JDBCTest {
public static void main(String[] args) {
String url = "jdbc:mysql://localhost:3306/student?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
String user = "root";
String password = "12345";

    try {
        // 1. Load MySQL driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. Connect to the database
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("PRASANNADEVI M");
        System.out.println("2117240070228");
        System.out.println("Connected successfully!");

        // 3. Create a statement
        Statement stmt = conn.createStatement();

        // 4. Execute SELECT query
        String query = "SELECT id, name, age, department FROM student";
        ResultSet rs = stmt.executeQuery(query);

        // 5. Process the result set
        System.out.println("\n--- Student Details ---");
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String department = rs.getString("department");

            System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Department: " + department);
        }

        // 6. Close connections
        rs.close();
        stmt.close();
        conn.close();

    } catch (ClassNotFoundException e) {
        System.out.println("MySQL Driver not found!");
        e.printStackTrace();
    } catch (SQLException e) {
        System.out.println("SQL Error: " + e.getMessage());
    }
}

}
