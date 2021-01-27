package com.as24.fullstack.helper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class AnalysisHelper {
    /**
     * Output refinement / formatting methods of
     * Data type format, Currency format, Mileage format, Unix timestamp
     * @return formatted string
     */

    // formatting for data types: task 5
    public String aliasType(String type) {
        // remaining conditions are available in
        // https://spark.apache.org/docs/3.0.0-preview2/sql-ref-datatypes.html

        if (type.equals("IntegerType") || type.equals("FloatType")) {
            return "numeric";
        } else if (type.equals("StringType")) {
            return "alphanumeric";
        } else if (type.equals("TimestampType")) {
            return "UTC Timestamp(ms)";
        } else {
            return type;
        }
    }

    // set the format in given pattern of GERMANY number structure
    public String formatCurrency(String amount) {
        String pattern = "#,##0.";
        DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(Locale.GERMANY);
        format.applyPattern(pattern);
        return  format.format(Double.valueOf(amount));
    }

    // set the format of GERMANY number structure
    public String formatMileage(String mileage) {
        DecimalFormat format = (DecimalFormat)NumberFormat.getNumberInstance(Locale.GERMANY);
        return format.format(Integer.valueOf(mileage));
    }

    // unix timestamp conversion and extraction of month in number and year concating with '.'.
    public String unxDateConv(long date) {
        final DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MM.yyyy");

        // millisecond division then convert
        final String formattedDtm = Instant.ofEpochSecond(date/1000)
                .atZone(ZoneId.of("GMT+1"))
                .format(formatter);

        return formattedDtm;
    }


}
