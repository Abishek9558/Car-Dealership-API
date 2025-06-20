package com.pluralsight.CarDealership.dao;

import com.pluralsight.CarDealership.model.Vehicle;
import java.util.List;

public interface VehicleDao {
    List<Vehicle> getAllVehicles();
    List<Vehicle> searchByPriceRange(double min, double max);
    List<Vehicle> searchByMakeModel(String make, String model);
    List<Vehicle> searchByYearRange(int min, int max);
    List<Vehicle> searchByColor(String color);
    List<Vehicle> searchByMileageRange(int min, int max);
    List<Vehicle> searchByType(String type);
    void addVehicle(Vehicle vehicle);
    void removeVehicleByVin(int vin);
}
