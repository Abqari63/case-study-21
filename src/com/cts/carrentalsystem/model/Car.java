/**
 * The Car class represents a car in the car rental system.
 * It contains attributes such as carId, make, model, year, price per day, and availability for rent.
 * It also provides getter and setter methods to access and modify these attributes.
 *
 * @version 1.0.0
 * @author Abqari Abbas
 * @since 2024-09-03
 */

package com.cts.carrentalsystem.model;

public class Car {
    /**
     * The unique identifier for the car.
     */
    private int carId;

    /**
     * The make of the car.
     */
    private String make;

    /**
     * The model of the car.
     */
    private String model;

    /**
     * The manufacturing year of the car.
     */
    private int year;

    /**
     * The rental price per day for the car.
     */
    private double pricePerDay;

    /**
     * Indicates whether the car is available for rent.
     */
    private boolean availableForRent;

    /**
     * Sets the unique identifier for the car.
     *
     * @param carId the car ID
     */
    public void setCarId(int carId) {
        this.carId = carId;
    }

    /**
     * Gets the unique identifier for the car.
     *
     * @return the car ID
     */
    public int getCarId() {
        return carId;
    }

    /**
     * Sets the make of the car.
     *
     * @param make the make of the car
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Gets the make of the car.
     *
     * @return the make of the car
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets the model of the car.
     *
     * @param model the model of the car
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the model of the car.
     *
     * @return the model of the car
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the manufacturing year of the car.
     *
     * @param year the manufacturing year of the car
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets the manufacturing year of the car.
     *
     * @return the manufacturing year of the car
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the rental price per day for the car.
     *
     * @param pricePerDay the rental price per day
     */
    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    /**
     * Gets the rental price per day for the car.
     *
     * @return the rental price per day
     */
    public double getPricePerDay() {
        return pricePerDay;
    }

    /**
     * Sets the availability status of the car for rent.
     *
     * @param availableForRent the availability status
     */
    public void setAvailableForRent(boolean availableForRent) {
        this.availableForRent = availableForRent;
    }

    /**
     * Gets the availability status of the car for rent.
     *
     * @return the availability status
     */
    public boolean getAvailableForRent() {
        return availableForRent;
    }
}
