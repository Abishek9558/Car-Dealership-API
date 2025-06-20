package com.pluralsight.CarDealership.controller;

import com.pluralsight.CarDealership.dao.SalesDao;
import com.pluralsight.CarDealership.model.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales-contracts")
public class SalesContractController {

    @Autowired
    private SalesDao salesDao;

    @PostMapping
    public void addContract(@RequestBody SalesContract contract) {
        salesDao.save(contract);
    }

    @GetMapping("/{id}")
    public SalesContract getById(@PathVariable int id) {
        return salesDao.getById(id);
    }
}
