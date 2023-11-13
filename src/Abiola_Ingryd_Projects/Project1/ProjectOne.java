package Abiola_Ingryd_Projects.Project1;

import java.sql.*;
import java.util.Scanner;

public class ProjectOne {
    private static final String url = "jdbc:mysql://localhost:3306/project_one";
    private static final String user = "root";
    private static final String password = "B0@nerge29";

    public static void main(String[] args) {
        createTable();
        System.out.printf("Number of employees inserted: %d \n", populateTable());
        System.out.println("Table populated successfully!");
    }

    public static void createTable() {

        try (Connection connection = DriverManager.getConnection(url, user, password); Statement statement = connection.createStatement()) {
            
            String tableCreationQuery = "CREATE TABLE IF NOT EXISTS employees (name Text, email Text, age Integer, location Text, designation Text)";
            statement.execute(tableCreationQuery);
            System.out.println("Table created successfully");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int populateTable() {

        int count = 0;

        try(Connection connection = DriverManager.getConnection(url, user, password); Scanner scanner = new Scanner(System.in)) {

            PreparedStatement insertStatement =
                    connection.prepareStatement("INSERT INTO employees (name, email, age, location, designation) VALUES(?, ?, ?, ?, ?)");

            System.out.println("Please populate the table with 10 employees");

            for (int i = 0; i < 10; i++) {
                System.out.println("Please enter your name: ");
                String name = scanner.nextLine();
                System.out.println("Please enter your email: ");
                String email = scanner.nextLine();
                System.out.println("Please enter your age as a number: ");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.println("Please enter your location: ");
                String location = scanner.nextLine();
                System.out.println("Please enter your designation: ");
                String designation = scanner.nextLine();

                insertStatement.setString(1, name);
                insertStatement.setString(2, email);
                insertStatement.setInt(3, age);
                insertStatement.setString(4, location);
                insertStatement.setString(5, designation);

                insertStatement.execute();
                count++;
                System.out.printf("Employee #%d inserted successfully! \n", count);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return count;
    }
}
