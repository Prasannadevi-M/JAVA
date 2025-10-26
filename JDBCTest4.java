
TC3:
package com.java.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class JDBCTest {
    public static void main(String[] args) {
        // You can switch between valid/invalid credentials for testing
        String url = "jdbc:mysql://localhost:3306/student?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";     // change to "wrong_user" for invalid test
        String password = "1234"; // change to "wrong_pass" for invalid test

        try {
            // 1. Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connect to database
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected successfully!");

            // 3. Create Statement
            Statement stmt = conn.createStatement();

            // 4. Execute query
            String query = ""; // intentionally empty query
            if (query.trim().isEmpty()) {
                System.out.println("No result");
            } else {
                ResultSet rs = stmt.executeQuery(query);

                boolean hasRows = false;
                while (rs.next()) {
                    hasRows = true;
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String department = rs.getString("department");

                    System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Department: " + department);
                }

                if (!hasRows) {
                    System.out.println("No result");
                }

                rs.close();
            }

            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
        } catch (SQLException e) {
            if (e.getMessage().contains("Access denied")) {
                System.out.println("Access denied: Invalid username or password!");
            } else {
                System.out.println("SQL Error: " + e.getMessage());
            }
        }
    }
}

TC4:
package com.java.practice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class JDBCTest {
    public static void main(String[] args) {
        // You can switch between valid/invalid credentials for testing
        String url = "jdbc:mysql://localhost:3306/student?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";     // change to "wrong_user" for invalid test
        String password = "12345"; // change to "wrong_pass" for invalid test

        try {
            // 1. Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connect to database
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("PRASANNADEVI M");
            System.out.println("2117240070228");
            System.out.println("Connected successfully!");

            // 3. Create Statement
            Statement stmt = conn.createStatement();

            // 4. Execute query
            String query = ""; // intentionally empty query
            if (query.trim().isEmpty()) {
                System.out.println("No result");
            } else {
                ResultSet rs = stmt.executeQuery(query);

                boolean hasRows = false;
                while (rs.next()) {
                    hasRows = true;
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String department = rs.getString("department");

                    System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Department: " + department);
                }

                if (!hasRows) {
                    System.out.println("No result");
                }

                rs.close();
            }

            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
        } catch (SQLException e) {
            if (e.getMessage().contains("Access denied")) {
                System.out.println("Access denied: Invalid username or password!");
            } else {
                System.out.println("SQL Error: " + e.getMessage());
            }
        }
    }
}
TC5:
package com.java.practice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class JDBCTest {
    public static void main(String[] args) {
        // Database credentials (use correct ones for connection)
        String url = "jdbc:mysql://localhost:3306/student?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";     
        String password = "12345"; 

        try {
            // 1. Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connect to database
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("PRASANNADEVI M");
            System.out.println("2117240070228");
            System.out.println("Connected successfully!");

            // 3. Create Statement
            Statement stmt = conn.createStatement();

            // ------------------------------
            // Test case input examples:
            // String query = "";  // Empty query → Output: No result
            // String query = "SELECT * FROM student"; // Valid query
            String query = "SELEC * FRM student"; // Malformed SQL → Output: SQLSyntaxErrorException
            // ------------------------------

            if (query.trim().isEmpty()) {
                System.out.println("No result");
            } else {
                try {
                    ResultSet rs = stmt.executeQuery(query);
                    boolean hasRows = false;
                    while (rs.next()) {
                        hasRows = true;
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        String department = rs.getString("department");

                        System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Department: " + department);
                    }
                    if (!hasRows) {
                        System.out.println("No result");
                    }
                    rs.close();
                } catch (SQLException e) {
                    // Specifically handle malformed SQL
                    if (e instanceof java.sql.SQLSyntaxErrorException) {
                        System.out.println("SQLSyntaxErrorException: Malformed SQL query!");
                    } else {
                        System.out.println("SQL Error: " + e.getMessage());
                    }
                }
            }

            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
        } catch (SQLException e) {
            if (e.getMessage().contains("Access denied")) {
                System.out.println("Access denied: Invalid username or password!");
            } else {
                System.out.println("SQL Error: " + e.getMessage());
            }
        }
    }
}
