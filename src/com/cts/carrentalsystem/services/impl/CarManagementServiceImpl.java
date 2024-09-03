package com.cts.carrentalsystem.services.impl;

import com.cts.carrentalsystem.dao.impl.CarDAOImpl;
import com.cts.carrentalsystem.exception.CarNotFoundException;
import com.cts.carrentalsystem.model.Car;
import com.cts.carrentalsystem.services.CarManagementService;

import java.util.List;
import java.util.Scanner;

/**
 * CarManagementServiceImpl implements the CarManagementService interface
 * to handle car management operations.
 *
 * @version 1.0.0
 * @since 2024-09-03
 */
public class CarManagementServiceImpl implements CarManagementService {

    private final CarDAOImpl carDAO = new CarDAOImpl();


    /**
     * Adds a new car as per the information provided by the user.
     *
     * @param scanner  used for taking the input fields details as per database schema for new car from the user.
     */
    @Override
    public void addNewCar(Scanner scanner) {
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

        carDAO.addCar(car);
        System.out.println("Car added successfully.");
    }

    @Override
    public void viewCarDetails(Scanner scanner) {
        System.out.print("Enter car ID: ");
        int carId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Car car = carDAO.getCar(carId);

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

    @Override
    public void getAllCars() {
        List<Car> cars = carDAO.getAllCars();
        boolean hasAvailableCars = false;

        for (Car car : cars) {
            if (car.getAvailableForRent()) {
                System.out.println("Car ID: " + car.getCarId() + ", Make: " + car.getMake() + ", Model: " + car.getModel() + ", Year: " + car.getYear() + ", Price per day: " + car.getPricePerDay());
                hasAvailableCars = true;
            }
        }

        try {
            if (!hasAvailableCars) {
                throw new CarNotFoundException("No Available Cars");
            }
        }
        catch(CarNotFoundException err) {
            System.err.println("ERROR: " + err.getMessage());
        }
    }

    @Override
    public void updateCarInfo(Scanner scanner) {
        System.out.print("Enter car ID: ");
        int carId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Car car = carDAO.getCar(carId);

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

            carDAO.updateCar(car);
            System.out.println("Car information updated successfully.");
        } else {
            System.out.println("Car not found.");
        }
    }

    private static void displayFieldsToBeUpdated() {
        System.out.println("make");
        System.out.println("model");
        System.out.println("year");
        System.out.println("pricePerDay");
        System.out.println("allFields");
    }

    @Override
    public void deleteCar(Scanner scanner) {
        System.out.print("Enter car ID: ");
        int carId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        carDAO.deleteCar(carId);
        System.out.println("Car deleted successfully.");
    }
}
