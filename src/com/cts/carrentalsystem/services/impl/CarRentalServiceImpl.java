package com.cts.carrentalsystem.services.impl;

import com.cts.carrentalsystem.dao.impl.CarDAOImpl;
import com.cts.carrentalsystem.dao.impl.RentalDAOImpl;
import com.cts.carrentalsystem.exception.CarNotFoundException;
import com.cts.carrentalsystem.model.Car;
import com.cts.carrentalsystem.model.Rental;
import com.cts.carrentalsystem.services.CarRentalService;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.cts.carrentalsystem.exception.InvalidCustomerInformationException;

/**
 * CarRentalServiceImpl implements the CarRentalService interface
 * to handle car rental operations.
 *
 * @version 1.0.0
 * @since 2024-09-03
 */
public class CarRentalServiceImpl implements CarRentalService {

    private final RentalDAOImpl rentalDAO = new RentalDAOImpl();
    private final CarDAOImpl carDAO = new CarDAOImpl();

    @Override
    public void rentCar(Scanner scanner) {
        System.out.print("Enter car ID: ");
        int carId = scanner.nextInt();
        scanner.nextLine();

        Car car = carDAO.getCar(carId);
        try {
            if (car != null && car.getAvailableForRent()) {
                System.out.print("Enter customer name: ");
                String customerName = scanner.nextLine();

                String phoneReg = "^\\+91[6-9]\\d{9}$";
                Pattern pattern = Pattern.compile(phoneReg);

                while (true) {
                    System.out.print("Enter customer phone: ");
                    String customerPhone = scanner.nextLine();
                    Matcher match = pattern.matcher(customerPhone);

                    try {
                        if (match.matches()) {
                            Rental rental = new Rental();
                            rental.setCarId(carId);
                            rental.setCustomerName(customerName);
                            rental.setCustomerPhone(customerPhone);
                            rental.setRentalDate(new java.util.Date());

                            rentalDAO.rentCar(rental);
                            System.out.println("Car rented successfully.");
                            break;
                        }
                        else {
                            throw new InvalidCustomerInformationException("Invalid Phone number. Please Try again...!");
                        }
                    }

                    catch (InvalidCustomerInformationException err) {
                        System.err.println("ERROR: " +err.getMessage());
                    }
                }
            }
            else throw new CarNotFoundException("Car not available for rent...!");
        } catch(CarNotFoundException err) {
            System.out.println("ERROR: " + err.getMessage());
        }
    }

    @Override
    public void returnCar(Scanner scanner) {
        System.out.print("Enter rental ID: ");
        int rentalId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        rentalDAO.returnCar(rentalId);
    }

    @Override
    public void getRentedCars() {
        List<Rental> rentals = rentalDAO.getRentedCars();

        for (Rental rental : rentals) {
            System.out.println("Rental ID: " + rental.getRentalId() + ", Car ID: " + rental.getCarId() + ", Customer Name: " + rental.getCustomerName() + ", Rental Date: " + rental.getRentalDate());
        }

        try {
            if (rentals.isEmpty()) {
                throw new CarNotFoundException("No Rented Cars");
            }
        }
        catch(CarNotFoundException err) {
            System.err.println("Error: " + err.getMessage());
        }
    }
}
