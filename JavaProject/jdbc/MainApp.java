package jdbc;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Employee employee = new Employee();
            System.out.println("==== Employee Management System ====");

            while (true) {
                System.out.println("\nMain Menu:");
                System.out.println("1. Insert Employee");
                System.out.println("2. Update Employee Details");
                System.out.println("3. Delete Employee");
                System.out.println("4. Display Employees");
                System.out.println("5. Search Employee");
                System.out.println("6. Show Highest Salary Employee");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                switch (choice) {
                    case 1: {
                        System.out.println("\n1. Enter single employee");
                        System.out.println("2. Enter group of employees");
                        int subChoice = sc.nextInt();
                        switch (subChoice) {
                            case 1:
                                employee.insertSingleEmployeeData();
                                break;
                            case 2:
                                employee.insertMultipleEmployeeData();
                                break;
                            default:
                                System.out.println("Invalid input");
                        }
                        break;
                    }
                    case 2: {
                        System.out.println("\nUpdate Menu:");
                        System.out.println("1. Update Name");
                        System.out.println("2. Update Department");
                        System.out.println("3. Update Salary");
                        int updateChoice = sc.nextInt();
                        switch (updateChoice) {
                            case 1:
                                employee.updateEmployeeName();
                                break;
                            case 2:
                                employee.updateEmployeeDept();
                                break;
                            case 3:
                                employee.updateEmployeeSalary();
                                break;
                            default:
                                System.out.println("Invalid input");
                        }
                        break;
                    }
                    case 3:
                        employee.deleteEmployee();
                        break;
                    case 4: {
                        System.out.println("\nDisplay Options:");
                        System.out.println("1. Show employee by ID");
                        System.out.println("2. Show department-wise employees");
                        System.out.println("3. Show gender-wise employees");
                        System.out.println("4. Show employees born after a specific year");
                        System.out.println("5. Show all employees");
                        int displayChoice = sc.nextInt();
                        switch (displayChoice) {
                            case 1:
                                employee.showSingleEmployee();
                                break;
                            case 2:
                                employee.showDepartmentWiseEmployee();
                                break;
                            case 3:
                                employee.showGenderWiseEmployee();
                                break;
                            case 4:
                                employee.showYearWiseEmployee();
                                break;
                            case 5:
                                employee.showAllEmployees();
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("\nSearch Options:");
                        System.out.println("1. Search by Name");
                        System.out.println("2. Search by ID");
                        int searchChoice = sc.nextInt();
                        switch (searchChoice) {
                            case 1:
                                employee.searchEmployeeByName();
                                break;
                            case 2:
                                employee.searchEmployeeByID();
                                break;
                            default:
                                System.out.println("Invalid search option.");
                        }
                        break;
                    }
                    case 6:
                        employee.showEmployeeWithMaxSalary();
                        break;
                    case 7:
                        System.out.println("Thank you for using the application.");
                        JDBCConnection.closeConnection();
                        return; // cleaner than System.exit(0)
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred in the application.");
            e.printStackTrace();
        }
    }
}
