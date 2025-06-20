package com.pluralsight.CarDealership.controller;
import com.pluralsight.CarDealership.dao.VehicleDao;
import com.pluralsight.CarDealership.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleDao vehicleDao;

    @GetMapping
    public List<Vehicle> search(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer minMiles,
            @RequestParam(required = false) Integer maxMiles,
            @RequestParam(required = false) String type
    ) {
        if (minPrice != null && maxPrice != null)
            return vehicleDao.searchByPriceRange(minPrice, maxPrice);
        if (make != null && model != null)
            return vehicleDao.searchByMakeModel(make, model);
        if (minYear != null && maxYear != null)
            return vehicleDao.searchByYearRange(minYear, maxYear);
        if (color != null)
            return vehicleDao.searchByColor(color);
        if (minMiles != null && maxMiles != null)
            return vehicleDao.searchByMileageRange(minMiles, maxMiles);
        if (type != null)
            return vehicleDao.searchByType(type);

        return vehicleDao.getAllVehicles();
    }

    @PostMapping
    public void addVehicle(@RequestBody Vehicle vehicle) {
        vehicleDao.addVehicle(vehicle);
    }

    @DeleteMapping("/{vin}")
    public void deleteVehicle(@PathVariable int vin) {
        vehicleDao.removeVehicleByVin(vin);
    }
}
