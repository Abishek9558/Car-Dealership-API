package com.pluralsight.CarDealership.controller;

import com.pluralsight.CarDealership.dao.LeaseDao;
import com.pluralsight.CarDealership.model.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lease-contracts")
public class LeaseContractController {

    @Autowired
    private LeaseDao leaseDao;

    @PostMapping
    public void addContract(@RequestBody LeaseContract contract) {
        leaseDao.save(contract);
    }

    @GetMapping("/{id}")
    public LeaseContract getById(@PathVariable int id) {
        return leaseDao.getById(id);
    }
}
