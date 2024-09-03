/**
 * Car rental system is a menu based console application made using Core Java, JDBC and MySQL database,
 * CarRentalSystem is the entry point class and use classes CarDAOImpl and RentalDAOImpl for the functionalities
 * operations
 *
 * <p>
 *     This class handles user input display menus for the appropriate car management and car rental operations
 *     as per the switch-choice statement.
 * </p>
 *
 * @version 1.0.0
 * @author Abqari Abbas
 * @since 2024-09-03
 */
package com.cts.carrentalsystem.client;
import com.cts.carrentalsystem.services.impl.CarManagementServiceImpl;
import java.util.Scanner;
import com.cts.carrentalsystem.services.impl.CarRentalServiceImpl;

public class CarRentalSystem {

    /**
     * This is the entry level main function for this application, which take a choice from the menu as input from
     * user and do the appropriate operation.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleCarManagement(scanner);
                    break;
                case 2:
                    handleCarRental(scanner);
                    break;
                case 3:
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);

        scanner.close();
    }

    /**
     * Display the menu for our application according to requirements
     */
    private static void displayMainMenu() {
        System.out.println("Car Rental System");
        System.out.println("1. Car Management");
        System.out.println("2. Car Rental");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Handles the car management operations chosen by the user from the menu.
     *
     * @param scanner used for taking input as operation choices for car management operations.
     */
    private static void handleCarManagement(Scanner scanner) {
        int choice;
        CarManagementServiceImpl carManagementOperations = new CarManagementServiceImpl();

        do {
            displayCarManagementMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    carManagementOperations.addNewCar(scanner);
                    break;
                case 2:
                    carManagementOperations.viewCarDetails(scanner);
                    break;
                case 3:
                    carManagementOperations.updateCarInfo(scanner);
                    break;
                case 4:
                    carManagementOperations.deleteCar(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    /**
     * Display the sub-menu for the car management chosen from main menu.
     */
    private static void displayCarManagementMenu() {
        System.out.println("Car Management");
        System.out.println("1. Add New Car");
        System.out.println("2. View Car Details");
        System.out.println("3. Update Car Information");
        System.out.println("4. Delete Car");
        System.out.println("5. Return to Main Menu");
        System.out.print("Enter your choice: ");
    }

    /**
     * Handles the car Rental operations chosen by the user from the menu.
     *
     * @param scanner used for taking input as operation choices for car rental operations.
     */
    private static void handleCarRental(Scanner scanner) {
        int choice;
        CarRentalServiceImpl carRentalOperations = new CarRentalServiceImpl();
        CarManagementServiceImpl carManagementOperations = new CarManagementServiceImpl();
        do {
            displayCarRentalMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    carManagementOperations.getAllCars();
                    break;
                case 2:
                    carRentalOperations.getRentedCars();
                    break;
                case 3:
                    carRentalOperations.rentCar(scanner);
                    break;
                case 4:
                    carRentalOperations.returnCar(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    /**
     * Display the sub-menu for the car rental chosen from main menu.
     */
    private static void displayCarRentalMenu() {
        System.out.println("Car Rental");
        System.out.println("1. Display Available Cars");
        System.out.println("2. Display Rented Cars");
        System.out.println("3. Rent a Car");
        System.out.println("4. Return Car");
        System.out.println("5. Return to main menu");
        System.out.print("Enter your choice: ");
    }

}

