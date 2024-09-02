/**
 * Car rental system is a menu based console application made using Core Java, JDBC and MySQL database,
 * CarRentalSystem is the entry point class and use classes CarService and RentalService for the functionalities
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
package com.cts.carrentalsystem.main;

import com.cts.carrentalsystem.model.Car;
import com.cts.carrentalsystem.model.Rental;
import com.cts.carrentalsystem.service.CarService;
import com.cts.carrentalsystem.service.RentalService;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CarRentalSystem {

    /**
     * Static final instance of CarService for car operations.
     */
    private static final CarService carService = new CarService();

    /**
     * Static final instance of RentalService for car rental operations.
     */
    private static final RentalService rentalService = new RentalService();

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
            scanner.nextLine(); // consume newline

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

        do {
            displayCarManagementMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addNewCar(scanner);
                    break;
                case 2:
                    viewCarDetails(scanner);
                    break;
                case 3:
                    updateCarInfo(scanner);
                    break;
                case 4:
                    deleteCar(scanner);
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
     * Adds a new car as per the information provided by the user.
     *
     * @param scanner  used for taking the input fields details as per database schema for new car from the user.
     */
    private static void addNewCar(Scanner scanner) {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        System.out.print("Enter price per day: ");
        double pricePerDay = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Car car = new Car();
        car.setMake(make);
        car.setModel(model);
        car.setYear(year);
        car.setPricePerDay(pricePerDay);
        car.setAvailableForRent(true);

        carService.addCar(car);
        System.out.println("Car added successfully.");
    }

    /**
     * Display details of a particular car as per car ID provided by the user.
     *
     * @param scanner taking input the car ID which is to be requested by the user.
     */
    private static void viewCarDetails(Scanner scanner) {
        System.out.print("Enter car ID: ");
        int carId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Car car = carService.getCar(carId);

        if (car != null) {
            System.out.println("Car ID: " + car.getCarId());
            System.out.println("Make: " + car.getMake());
            System.out.println("Model: " + car.getModel());
            System.out.println("Year: " + car.getYear());
            System.out.println("Price per day: " + car.getPricePerDay());
            System.out.println("Available for rent: " + (car.getAvailableForRent() ? "Yes" : "No"));
        } else {
            System.out.println("Car not found.");
        }
    }

    /**
     * Update the car information as per the new information provided by the user as input.
     *
     * @param scanner used to take new car information input from the user to update.
     */
    private static void updateCarInfo(Scanner scanner) {
        System.out.print("Enter car ID: ");
        int carId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Car car = carService.getCar(carId);

        if (car != null) {
            displayFieldsToBeUpdated();

            System.out.print("write the field to be update from above choice: ");
            String fieldToUpdate = scanner.next();

            String make = car.getMake();
            String model = car.getModel();
            int year = car.getYear();
            double pricePerDay = car.getPricePerDay();
            boolean availableForRent = car.getAvailableForRent();

            switch(fieldToUpdate) {
                case "make":
                    System.out.print("Enter new make: ");
                    make = scanner.nextLine();
                    break;

                case "model":
                    System.out.print("Enter new model: ");
                    model = scanner.nextLine();
                    break;

                case "year":
                    System.out.print("Enter new year: ");
                    year = scanner.nextInt();
                    break;

                case "pricePerDay":
                    System.out.print("Enter new price per day: ");
                    pricePerDay = scanner.nextDouble();
                    break;

                case "allFields":
                    System.out.print("Enter new make: ");
                    make = scanner.nextLine();
                    scanner.nextLine();
                    System.out.print("Enter new model: ");
                    model = scanner.nextLine();
                    System.out.print("Enter new year: ");
                    year = scanner.nextInt();
                    System.out.print("Enter new price per day: ");
                    pricePerDay = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    break;

                default:
                    System.out.println("No Field chosen, Exiting update operation...!");
            }

            car.setMake(make);
            car.setModel(model);
            car.setYear(year);
            car.setPricePerDay(pricePerDay);
            car.setAvailableForRent(availableForRent);

            carService.updateCar(car);
            System.out.println("Car information updated successfully.");
        } else {
            System.out.println("Car not found.");
        }
    }

    /**
     * Display the Field choices to be updated
     */
    private static void displayFieldsToBeUpdated() {
        System.out.println("make");
        System.out.println("model");
        System.out.println("year");
        System.out.println("pricePerDay");
        System.out.println("allFields");
    }


    /**
     * Deletes the particular car as per the car ID provided by the user as input.
     *
     * @param scanner used to take input car ID from the user.
     */
    private static void deleteCar(Scanner scanner) {
        System.out.print("Enter car ID: ");
        int carId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        carService.deleteCar(carId);
        System.out.println("Car deleted successfully.");
    }

    /**
     * Handles the car Rental operations chosen by the user from the menu.
     *
     * @param scanner used for taking input as operation choices for car rental operations.
     */
    private static void handleCarRental(Scanner scanner) {
        int choice;

        do {
            displayCarRentalMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    displayAvailableCars();
                    break;
                case 2:
                    displayRentedCars();
                    break;
                case 3:
                    rentCar(scanner);
                    break;
                case 4:
                    returnCar(scanner);
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

    /**
     * Display the list of available cars.
     */
    private static void displayAvailableCars() {
        List<Car> cars = carService.getAllCars();
        boolean hasAvailableCars = false;

        for (Car car : cars) {
            if (car.getAvailableForRent()) {
                System.out.println("Car ID: " + car.getCarId() + ", Make: " + car.getMake() + ", Model: " + car.getModel() + ", Year: " + car.getYear() + ", Price per day: " + car.getPricePerDay());
                hasAvailableCars = true;
            }
        }

        if (!hasAvailableCars) {
            System.out.println("No available cars.");
        }
    }

    /**
     * Display the Cars which are rented by the customers.
     */
    private static void displayRentedCars() {
        List<Rental> rentals = rentalService.getRentedCars();

        for (Rental rental : rentals) {
            System.out.println("Rental ID: " + rental.getRentalId() + ", Car ID: " + rental.getCarId() + ", Customer Name: " + rental.getCustomerName() + ", Rental Date: " + rental.getRentalDate());
        }

        if (rentals.isEmpty()) {
            System.out.println("No rented cars.");
        }
    }

    /**
     * Rent a car with the car ID provided by the user as input and validating the phone number to be IN(+91).
     *
     * @param scanner used to take input car ID for to rent a particular car.
     */
    private static void rentCar(Scanner scanner) {
        System.out.print("Enter car ID: ");
        int carId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Car car = carService.getCar(carId);

        if (car != null && car.getAvailableForRent()) {
            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine();

            String phoneReg = "^\\+91[6-9]\\d{9}$";
            Pattern pattern = Pattern.compile(phoneReg);

            while (true) {
                System.out.print("Enter customer phone: ");
                String customerPhone = scanner.nextLine();
                Matcher match = pattern.matcher(customerPhone);

                if (match.matches()) {
                    Rental rental = new Rental();
                    rental.setCarId(carId);
                    rental.setCustomerName(customerName);
                    rental.setCustomerPhone(customerPhone);
                    rental.setRentalDate(new java.util.Date());

                    rentalService.rentCar(rental);
                    System.out.println("Car rented successfully.");
                    break;
                }
                else {
                    System.out.println("Invalid phone number...!, Please enter correct phone number");
                }
            }

        } else {
            System.out.println("Car not available for rent.");
        }
    }

    /**
     * Handles the process of returning a rented car.
     * Prompts the user to enter the rental ID of the car to be returned,
     * and updates the rental record and car availability status accordingly.
     *
     * @param scanner The Scanner object used to read user input.
     */
    private static void returnCar(Scanner scanner) {
        System.out.print("Enter rental ID: ");
        int rentalId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        rentalService.returnCar(rentalId);
    }
}

