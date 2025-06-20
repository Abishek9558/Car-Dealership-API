package com.pluralsight.CarDealership.dao;

import com.pluralsight.CarDealership.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleImpl implements VehicleDao {

    @Autowired
    private DataSource dataSource;

    private Vehicle mapRowToVehicle(ResultSet rs) throws SQLException {
        return new Vehicle(
                rs.getInt("vin"),
                rs.getInt("year"),
                rs.getString("make"),
                rs.getString("model"),
                rs.getString("type"),
                rs.getString("color"),
                rs.getInt("mileage"),
                rs.getDouble("price")
        );
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return queryVehicles("SELECT * FROM vehicles", stmt -> {});
    }

    @Override
    public List<Vehicle> searchByPriceRange(double min, double max) {
        return queryVehicles("SELECT * FROM vehicles WHERE price BETWEEN ? AND ?", stmt -> {
            stmt.setDouble(1, min);
            stmt.setDouble(2, max);
        });
    }

    @Override
    public List<Vehicle> searchByMakeModel(String make, String model) {
        return queryVehicles("SELECT * FROM vehicles WHERE make = ? AND model = ?", stmt -> {
            stmt.setString(1, make);
            stmt.setString(2, model);
        });
    }

    @Override
    public List<Vehicle> searchByYearRange(int min, int max) {
        return queryVehicles("SELECT * FROM vehicles WHERE year BETWEEN ? AND ?", stmt -> {
            stmt.setInt(1, min);
            stmt.setInt(2, max);
        });
    }

    @Override
    public List<Vehicle> searchByColor(String color) {
        return queryVehicles("SELECT * FROM vehicles WHERE color = ?", stmt -> stmt.setString(1, color));
    }

    @Override
    public List<Vehicle> searchByMileageRange(int min, int max) {
        return queryVehicles("SELECT * FROM vehicles WHERE mileage BETWEEN ? AND ?", stmt -> {
            stmt.setInt(1, min);
            stmt.setInt(2, max);
        });
    }

    @Override
    public List<Vehicle> searchByType(String type) {
        return queryVehicles("SELECT * FROM vehicles WHERE type = ?", stmt -> stmt.setString(1, type));
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (vin, year, make, model, type, color, mileage, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vehicle.getVin());
            stmt.setInt(2, vehicle.getYear());
            stmt.setString(3, vehicle.getMake());
            stmt.setString(4, vehicle.getModel());
            stmt.setString(5, vehicle.getType());
            stmt.setString(6, vehicle.getColor());
            stmt.setInt(7, vehicle.getMileage());
            stmt.setDouble(8, vehicle.getPrice());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error adding vehicle: " + e.getMessage());
        }
    }

    @Override
    public void removeVehicleByVin(int vin) {
        String sql = "DELETE FROM vehicles WHERE vin = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vin);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error deleting vehicle: " + e.getMessage());
        }
    }

    private List<Vehicle> queryVehicles(String sql, SQLConsumer<PreparedStatement> setter) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setter.accept(stmt);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicles.add(mapRowToVehicle(rs));
            }

        } catch (SQLException e) {
            System.err.println("Query error: " + e.getMessage());
        }
        return vehicles;
    }

    @FunctionalInterface
    private interface SQLConsumer<T> {
        void accept(T t) throws SQLException;
    }
}
