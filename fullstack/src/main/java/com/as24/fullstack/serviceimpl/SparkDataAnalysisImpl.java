package com.as24.fullstack.serviceimpl;

import com.as24.fullstack.helper.AnalysisHelper;
import com.as24.fullstack.service.SparkDataAnalysis;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class SparkDataAnalysisImpl implements SparkDataAnalysis {
    /**
     * Filter the data using Apache spark engine
     * Spark SQL library is used to process queries
     *
     */

    @Autowired
    SparkSession sparkSession;

    // datasets holder for each
    public Dataset<Row> contactsDS;
    public Dataset<Row> listingsDS;

    // fixed location
    public final String contactsData = "data\\contacts.csv";
    public final String listingsData = "data\\listings.csv";

    // dataframe reader with header
    public DataFrameReader getSparkSession() {
        return sparkSession.read().option("header", "true");
    }

    // returns contacts dataframe after type casting and creating temp view
    public Dataset<Row> loadContactsCSV(String path) {
        contactsDS = getSparkSession().csv(path);
        contactsDS.createOrReplaceTempView("contacts");
        contactsDS = sparkSession.sql("SELECT CAST(listing_id as int)," +
                " CAST(from_unixtime(contact_date/1000) as TIMESTAMP) contact_date" +
                " FROM contacts");
        return contactsDS;
    }

    // returns listings dataframe after type casting and creating temp view
    public Dataset<Row> loadListingsCSV(String path) {
        listingsDS = getSparkSession().csv(path);
        listingsDS.createOrReplaceTempView("listings");
        listingsDS = sparkSession.sql("SELECT CAST(id as int) id, CAST(make as String) make," +
                " CAST(price as int) price, CAST(mileage as int) mileage," +
                " CAST(seller_type as String) seller_type FROM listings");
        return listingsDS;
    }

    @Override
    public HashMap<String, List> getSchema() {
        // Task 5. Definition of the CSV files
        HashMap<String, List> response = new HashMap<>();
        try {
            listingsDS = loadListingsCSV(listingsData);

            AnalysisHelper helper = new AnalysisHelper();
            // temporary data holder
            List<HashMap> contactsSchema = new ArrayList<>();

            // refines the return value using helper methods
            for (StructField field : contactsDS.schema().fields()) {
                HashMap<String, String> schemaDef = new HashMap<>();
                schemaDef.put("field", helper.aliasType(field.name()));
                schemaDef.put("type", helper.aliasType(field.dataType().toString()));
                schemaDef.put("required", String.valueOf(field.nullable()).equals("true") ? "yes" : "no");
                contactsSchema.add(schemaDef);
            }
            // adding result to response
            response.put("Contacts.csv", contactsSchema);

            List<HashMap> listingsSchema = new ArrayList<>();
            // refines the return value using helper methods
            for (StructField field : listingsDS.schema().fields()) {
                HashMap<String, String> schemaDef = new HashMap<>();
                schemaDef.put("field", helper.aliasType(field.name()));
                schemaDef.put("type", helper.aliasType(field.dataType().toString()));
                schemaDef.put("required", String.valueOf(field.nullable()).equals("true") ? "yes" : "no");
                listingsSchema.add(schemaDef);
            }
            // adding result to response
            response.put("listings.csv", listingsSchema);

            return response;
        } catch (Exception e) {
            return response;
        }
    }

    @Override
    public List<HashMap> getAvgPriceBySeller() {
        listingsDS = loadListingsCSV(listingsData);

        // Task 1. Average Listing Selling Price per Seller Type
        List<String> results = sparkSession.sql("select seller_type, " +
                "CAST(ROUND(AVG(price)) as int) as `Average in Euro` from listings" +
                " group by seller_type").toJSON().collectAsList();

        List<HashMap> response = new ArrayList();

        AnalysisHelper helper = new AnalysisHelper();
        ObjectMapper mapper = new ObjectMapper();

        // refining data as required
        for(String result : results){
            HashMap<String, String> prodHashMap = new HashMap<String, String>();
            try {
                prodHashMap = mapper.readValue(result, new TypeReference<HashMap<String, String>>(){});
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            prodHashMap.put("Average in Euro",
                    "\u20ac "+helper.formatCurrency(String.valueOf(prodHashMap.get("Average in Euro")))+"-");
            response.add(prodHashMap);
        }
        return response;
    }

    @Override
    public List<HashMap> getDistByMake() {
        // Task 2. Percentile distribution of available cars by Make
        listingsDS = loadListingsCSV(listingsData);
        // get total price from listing with that
        // divide to Makers and * 100 then
        // show group by make to show each result
        List<String> results = sparkSession.sql("SELECT make as Make," +
                " CAST(ROUND(sum(price)/(select sum(price) from listings)*100) as int) as Distribution" +
                " from listings" +
                " group by make").toJSON().collectAsList();

        List<HashMap> response = new ArrayList();

        ObjectMapper mapper = new ObjectMapper();
        for(String s : results){
            System.out.println(s);
            HashMap<String, String> prodHashMap = new HashMap<String, String>();
            try {
                prodHashMap = mapper.readValue(s, new TypeReference<HashMap<String, String>>(){});
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            // concating "%" as required
            prodHashMap.put("Distribution", prodHashMap.get("Distribution")+"%");
            response.add(prodHashMap);
        }

        return response;
    }

    @Override
    public HashMap<String, String> getMostAvGPrice() {
        // Task 3. The average price of the 30% most contacted listings

        contactsDS = loadContactsCSV(contactsData);
        listingsDS = loadListingsCSV(listingsData);
        contactsDS.printSchema();

        // 30% limit from all contacted list
        List<String> ofTotal = sparkSession.sql("SELECT DISTINCT" +
                " CAST(30*(SELECT count(*) from contacts)/100 as int) as tempTotal" +
                " FROM contacts").toJSON().collectAsList();

        // confuse may be by 30% of listing data
//        SELECT * FROM listings
//        INNER JOIN
//        (SELECT * from (
//                SELECT *
//                        FROM (
//                                select listing_id, count(listing_id) as c
//                                from contacts
//                                GROUP by listing_id
//                                ORDER by c DESC) as t
//                LIMIT round(30*(SELECT count(*) from contacts)/100) ) as tc
//
//        LIMIT (SELECT CAST(round(DISTINCT(count(id))*30/100) as int) as tempLimit from listings)) as top
//        on listings.id = top.listing_id

        ObjectMapper mapL = new ObjectMapper();
        HashMap<String, Object> prodHashMapL = new HashMap<String, Object>();
        try {
            prodHashMapL = mapL.readValue(ofTotal.get(0), new TypeReference<HashMap<String, Object>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        contactsDS.printSchema();

        int tempLimit = (int) prodHashMapL.get("tempTotal");

        // get the count from contacts in desc order
        // match the id of above output to listing with tempLimit (30%)
        // make average of price of tempLimit return id
        List<String> results = sparkSession.sql("SELECT cast(round(avg(listings.price)) as int) as `Average price`" +
                " from listings INNER JOIN"+
                " (SELECT DISTINCT contacts.listing_id, c from contacts INNER JOIN"+
                    " (select listing_id, count(listing_id) as c " +
                    " from contacts GROUP by listing_id" +
                    " ORDER by c DESC) as tmp on" +
                " contacts.listing_id = tmp.listing_id " +
                " ORDER by tmp.c DESC LIMIT "+tempLimit+ " ) as t" +
                " on t.listing_id = listings.id").toJSON().collectAsList();
//                " LIMIT length(CAST(30*(SELECT count(*) from contacts)/100 as INT))");
        contactsDS.printSchema();
        ObjectMapper mapper = new ObjectMapper();
        AnalysisHelper helper = new AnalysisHelper();
        HashMap<String, String> response = new HashMap<String, String>();
        try {
            response = mapper.readValue(results.get(0), new TypeReference<HashMap<String, String>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // customize return
        response.put("Average price",
                "\u20ac "+helper.formatCurrency(String.valueOf(response.get("Average price")))+"-");

        return response;
    }

    @Override
    public List<LinkedHashMap<String, List>> getTopResultByMonth() {
        // Task 4. The Top 5 most contacted listings per Month

        contactsDS = loadContactsCSV(contactsData);
        listingsDS = loadListingsCSV(listingsData);

        // factorizing types
        contactsDS.createOrReplaceTempView("contacts");
        Dataset<Row> contactsDSS = sparkSession
                .sql("SELECT CAST(listing_id as int) id," +
                        " CAST(date_format(CAST(contact_date as date), 'LL.y') as String) as date" +
                        " FROM contacts");
        contactsDSS.createOrReplaceTempView("contactss");

        List<LinkedHashMap<String, List>> response = new ArrayList<>();

        // take distinct date in list
        List<String> eachDate = sparkSession.sql("SELECT DISTINCT date FROM contactss" +
                " ORDER BY date").toJSON().collectAsList();

//        eachDate.forEach(System.out::println);

        // for each date run query
        for(String s : eachDate){
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String, String> tmpDate = new HashMap<String, String>();
            try {
                tmpDate = mapper.readValue(s, new TypeReference<HashMap<String, String>>(){});
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            // based on extracted date date get top 5 records
            // get top 5 count, id from contacts where date must come from loop
            // order the result and take only 5
            // match the extracted result id with listings id to select properties of that table
            // finally oder ascending by rank
            List<String> topRecords = sparkSession.sql("SELECT tmp.Ranking, listings.id as `Listing Id`," +
                    " make as Make, price as `Selling Price`, mileage `Mileage`, tmp.cnt `Total Amount of contacts`" +
                    " FROM listings" +
                    " INNER JOIN(" +
                    "    SELECT id, count(id) as cnt," +
                    " row_number() over (order by count(id) DESC) as Ranking" +
                    " from contactss" +
                    " WHERE date = '"+tmpDate.get("date")+"'" +
                    " GROUP by contactss.id" +
                    " ORDER by cnt DESC" +
                    " limit 5) as tmp" +
                    " ON listings.id = tmp.id" +
                    " ORDER BY tmp.Ranking").toJSON().collectAsList();

//            topRecords.forEach(System.out::println);

            // stores the temp result
            List<LinkedHashMap> tempResponse = new ArrayList<>();

            AnalysisHelper helper = new AnalysisHelper();
            ObjectMapper mapperTopRecords = new ObjectMapper();

            // formatting return by
            // putting '\u20ac' (€), '-', 'KM' in required results
            for(String topRecord : topRecords){
                System.out.println(topRecord);
                LinkedHashMap<String, Object> topRecordsHM = new LinkedHashMap<String, Object>();
                try {
                    topRecordsHM = mapperTopRecords.readValue(topRecord, new TypeReference<LinkedHashMap<String, Object>>(){});
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                topRecordsHM.put("Selling Price",
                        "\u20ac "+helper.formatCurrency(String.valueOf(topRecordsHM.get("Selling Price")))+"-");
                topRecordsHM.put("Mileage", helper.formatMileage(String.valueOf(topRecordsHM.get("Mileage")))+" KM");
                tempResponse.add(topRecordsHM);
            }

            // to store the result of each month
            LinkedHashMap<String, List> eachMonthRecords = new LinkedHashMap<>();
            eachMonthRecords.put(tmpDate.get("date"), tempResponse);

            // finally all data placed here
            response.add(eachMonthRecords);
        }

        return response;
    }
}
