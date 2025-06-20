package com.pluralsight.CarDealership.dao;

import com.pluralsight.CarDealership.model.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class LeaseImpl implements LeaseDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public void save(LeaseContract contract) {
        String sql = "INSERT INTO lease_contracts (VIN, dealership_id, customer_name, lease_start, lease_end, monthly_payment) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contract.getVin());
            stmt.setInt(2, contract.getDealershipId());
            stmt.setString(3, contract.getCustomerName());
            stmt.setString(4, contract.getLeaseStart());
            stmt.setString(5, contract.getLeaseEnd());
            stmt.setDouble(6, contract.getMonthlyPayment());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving lease contract: " + e.getMessage());
        }
    }

    @Override
    public LeaseContract getById(int id) {
        String sql = "SELECT * FROM lease_contracts WHERE id = ?";
        LeaseContract contract = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                contract = new LeaseContract();
                contract.setId(rs.getInt("id"));
                contract.setVin(rs.getString("VIN"));
                contract.setDealershipId(rs.getInt("dealership_id"));
                contract.setCustomerName(rs.getString("customer_name"));
                contract.setLeaseStart(rs.getString("lease_start"));
                contract.setLeaseEnd(rs.getString("lease_end"));
                contract.setMonthlyPayment(rs.getDouble("monthly_payment"));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching lease contract: " + e.getMessage());
        }

        return contract;
    }
}
