package com.pluralsight.CarDealership.model;

public class LeaseContract {
    private int id;
    private String vin;
    private int dealershipId;
    private String customerName;
    private String leaseStart;
    private String leaseEnd;
    private double monthlyPayment;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }

    public int getDealershipId() { return dealershipId; }
    public void setDealershipId(int dealershipId) { this.dealershipId = dealershipId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getLeaseStart() { return leaseStart; }
    public void setLeaseStart(String leaseStart) { this.leaseStart = leaseStart; }

    public String getLeaseEnd() { return leaseEnd; }
    public void setLeaseEnd(String leaseEnd) { this.leaseEnd = leaseEnd; }

    public double getMonthlyPayment() { return monthlyPayment; }
    public void setMonthlyPayment(double monthlyPayment) { this.monthlyPayment = monthlyPayment; }
}
