package com.as24.fullstack.controller;

import com.as24.fullstack.serviceimpl.SparkDataAnalysisImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class AnalysisController {

    @Autowired
    SparkDataAnalysisImpl analysis;

    // Definition of the CSV files
    @GetMapping("schema")
    public ResponseEntity<HashMap<String, List>> getSchema() {
        return ResponseEntity.status(HttpStatus.OK).body(analysis.getSchema());
    }

    // Average Listing Selling Price per Seller Type
    @GetMapping("avgseller")
    public ResponseEntity<List<HashMap>> getAvgPriceBySeller() {
        return ResponseEntity.status(HttpStatus.OK).body(analysis.getAvgPriceBySeller());
    }

    // Percentile distribution of available cars by Make
    @GetMapping("dist")
    public ResponseEntity<List<HashMap>> getDistributionBYMake() {
        return ResponseEntity.status(HttpStatus.OK).body(analysis.getDistByMake());
    }

    // The average price of the 30% most contacted listings
    @GetMapping("mostof")
    public ResponseEntity<HashMap<String, String>> MostOfAvgPrice() {
        return ResponseEntity.status(HttpStatus.OK).body(analysis.getMostAvGPrice());
    }

    // The Top 5 most contacted listings per Month
    @GetMapping("top")
    public ResponseEntity<List<LinkedHashMap<String, List>>> getTopResults() {
        return ResponseEntity.status(HttpStatus.OK).body(analysis.getTopResultByMonth());
    }

}
