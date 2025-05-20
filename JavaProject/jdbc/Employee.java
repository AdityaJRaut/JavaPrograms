package jdbc;

import java.sql.*;
import java.util.Scanner;
import utils.ScannerUtil;

public class Employee {

    private int id;
    private String name;
    private String gender;
    private double salary;
    private String dept;
    private String dob;

    private String query;
    private Scanner sc = ScannerUtil.getScanner();
    private Connection conn = JDBCConnection.getDBConnection();
    private CallableStatement ps;
    private ResultSet rs;

    public void deleteEmployee() {
        System.out.println();
        try {
            System.out.println("Enter ID to delete employee:");
            this.id = sc.nextInt();

            query = "CALL selectidfromemployeeid(?)";
            ps = conn.prepareCall(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No employee found with id " + id + "!\n");
            } else {
                query = "CALL deleteemployeebyemployeeid(?)";
                ps = conn.prepareCall(query);
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("Employee with id " + id + " deleted successfully...\n");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertMultipleEmployeeData() {
        System.out.println("\nEnter no. of employees to enter:");
        int num = sc.nextInt();

        query = "INSERT INTO employees(name, salary, dept, gender, dob) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            for (int i = 1; i <= num; i++) {
                System.out.println("Enter details for Employee " + i);
                System.out.print("Name: ");
                name = sc.next();
                System.out.print("Salary: ");
                salary = sc.nextDouble();
                System.out.print("Department: ");
                dept = sc.next();
                System.out.print("Gender: ");
                gender = sc.next();
                System.out.print("DOB (YYYY-MM-DD): ");
                dob = sc.next();

                ps.setString(1, name);
                ps.setDouble(2, salary);
                ps.setString(3, dept);
                ps.setString(4, gender);
                ps.setString(5, dob);
                ps.addBatch();
            }

            ps.executeBatch();
            System.out.println(num + " employee records inserted successfully!\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSingleEmployeeData() {
        System.out.println("\nEnter details for single employee:");
        System.out.print("Name: ");
        name = sc.next();
        System.out.print("Salary: ");
        salary = sc.nextDouble();
        System.out.print("Department: ");
        dept = sc.next();
        System.out.print("Gender: ");
        gender = sc.next();
        System.out.print("DOB (YYYY-MM-DD): ");
        dob = sc.next();

        query = "INSERT INTO employees(name, salary, dept, gender, dob) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setDouble(2, salary);
            ps.setString(3, dept);
            ps.setString(4, gender);
            ps.setString(5, dob);
            ps.executeUpdate();
            System.out.println("Employee inserted successfully!\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showDepartmentWiseEmployee() {
        System.out.println("\nEnter department name:");
        dept = sc.next();

        query = "SELECT * FROM employees WHERE dept = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, dept);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No records found in department: " + dept);
            } else {
                displayTable(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showEmployeeWithMaxSalary() {
        System.out.println("\nShowing employee with max salary:");

        query = "SELECT * FROM employees ORDER BY salary DESC LIMIT 1";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            rs = ps.executeQuery();
            if (rs.next()) {
                rs.beforeFirst();
                displayTable(rs);
            } else {
                System.out.println("No record found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showGenderWiseEmployee() {
        System.out.println("\nEnter gender:");
        gender = sc.next();

        query = "SELECT * FROM employees WHERE gender = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, gender);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No records found for gender: " + gender);
            } else {
                displayTable(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showSingleEmployee() {
        System.out.println("\nEnter employee ID:");
        id = sc.nextInt();

        query = "SELECT * FROM employees WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No record found for ID: " + id);
            } else {
                displayTable(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showYearWiseEmployee() {
        System.out.println("\nEnter year (YYYY):");
        int year = sc.nextInt();

        query = "SELECT * FROM employees WHERE YEAR(dob) > ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, year);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No records found after year: " + year);
            } else {
                displayTable(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployeeDept() {
        System.out.println("\nEnter employee ID to update department:");
        id = sc.nextInt();

        query = "SELECT id FROM employees WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No employee found with ID: " + id);
                return;
            }
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        System.out.println("Enter new department:");
        dept = sc.next();

        query = "UPDATE employees SET dept = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, dept);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Department updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployeeName() {
        System.out.println("\nEnter employee ID to update name:");
        id = sc.nextInt();

        query = "SELECT id FROM employees WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No employee found with ID: " + id);
                return;
            }
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        System.out.println("Enter new name:");
        name = sc.next();

        query = "UPDATE employees SET name = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Name updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployeeSalary() {
        System.out.println("\nEnter employee ID to update salary:");
        id = sc.nextInt();

        query = "SELECT id FROM employees WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No employee found with ID: " + id);
                return;
            }
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        System.out.println("Enter new salary:");
        salary = sc.nextDouble();

        query = "UPDATE employees SET salary = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDouble(1, salary);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Salary updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchEmployeeByName() {
        System.out.println("\nEnter name to search:");
        name = sc.next();

        query = "SELECT * FROM employees WHERE name = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No employee found with name: " + name);
            } else {
                displayTable(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchEmployeeByID() {
        showSingleEmployee(); // Reusing method
    }

    public void showAllEmployees() {
        query = "SELECT * FROM employees";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No employees found.");
            } else {
                displayTable(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayTable(ResultSet rs) throws SQLException {
        System.out.printf("%-5s| %-20s| %-10s| %-10s| %-20s| %-10s|\n", "ID", "Name", "Gender", "Salary", "Department", "DOB");
        System.out.println("----------------------------------------------------------------------------------");
        while (rs.next()) {
            System.out.printf("%-5d| %-20s| %-10s| %-10.2f| %-20s| %-10s|\n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    rs.getDouble("salary"),
                    rs.getString("dept"),
                    rs.getString("dob"));
        }
    }
}
