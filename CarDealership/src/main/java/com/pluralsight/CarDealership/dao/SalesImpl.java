package com.pluralsight.CarDealership.dao;

import com.pluralsight.CarDealership.model.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class SalesImpl implements SalesDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public void save(SalesContract contract) {
        String sql = "INSERT INTO sales_contracts (VIN, dealership_id, customer_name, sale_date, sale_price) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contract.getVin());
            stmt.setInt(2, contract.getDealershipId());
            stmt.setString(3, contract.getCustomerName());
            stmt.setString(4, contract.getSaleDate());
            stmt.setDouble(5, contract.getSalePrice());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving sales contract: " + e.getMessage());
        }
    }

    @Override
    public SalesContract getById(int id) {
        String sql = "SELECT * FROM sales_contracts WHERE id = ?";
        SalesContract contract = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                contract = new SalesContract();
                contract.setId(rs.getInt("id"));
                contract.setVin(rs.getString("VIN"));
                contract.setDealershipId(rs.getInt("dealership_id"));
                contract.setCustomerName(rs.getString("customer_name"));
                contract.setSaleDate(rs.getString("sale_date"));
                contract.setSalePrice(rs.getDouble("sale_price"));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching sales contract: " + e.getMessage());
        }

        return contract;
    }
}
