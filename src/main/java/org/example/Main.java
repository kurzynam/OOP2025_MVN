package org.example;


import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String dbPath = "university.db";
        DatabaseConnection dbConn = new DatabaseConnection();

        dbConn.connect(dbPath);
        Connection conn = dbConn.getConnection();

        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                // Tworzenie tabeli students
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS students (
                        student_id INTEGER PRIMARY KEY,
                        name TEXT NOT NULL,
                        surname TEXT NOT NULL
                    );
                    """);

                // Wstawianie przykładowych danych
                stmt.execute("INSERT INTO students (name, surname) VALUES ('Janusz', 'Kowalski');");
                stmt.execute("INSERT INTO students (name, surname) VALUES ('Karolina', 'Nowak');");

                // Odczyt danych
                ResultSet rs = stmt.executeQuery("SELECT * FROM students;");
                System.out.println("Lista studentów:");
                while (rs.next()) {
                    int id = rs.getInt("student_id");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    System.out.println(id + ": " + name + " " + surname);
                }
            } catch (SQLException e) {
                System.err.println("Błąd SQL: " + e.getMessage());
            } finally {
                dbConn.disconnect();
            }
        }
    }
}
