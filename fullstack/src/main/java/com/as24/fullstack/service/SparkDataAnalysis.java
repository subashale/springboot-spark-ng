package com.as24.fullstack.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface SparkDataAnalysis {

    public HashMap<String, List> getSchema();

    public List<HashMap> getAvgPriceBySeller() throws JsonProcessingException;

    public List<HashMap> getDistByMake();

    public HashMap<String, String> getMostAvGPrice();

    public List<LinkedHashMap<String, List>> getTopResultByMonth();
}
