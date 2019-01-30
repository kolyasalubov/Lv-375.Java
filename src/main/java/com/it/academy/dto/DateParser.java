package com.it.academy.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Class DateParser change Date format from DB to nice displayed format and vice versa
 */
public class DateParser {

    /**
     * Changes data from displayed to DB format
     */
    public String parseToDb(String dateFromDisplay) {
        DateFormat toDisplay = getToDisplay();
        DateFormat toDb = getToDb();
        return dateParser(dateFromDisplay, toDisplay, toDb);
    }

    /**
     * Changes data from DB to DB nice displayed format divided into date and time
     */
    public Map<String, String> parseToDisplay(String dateFromDb) {
        Map<String, String> dateTime = new HashMap<>();
        DateFormat dateToDisplay = getDateToDisplay();
        DateFormat timeToDisplay = getTimeToDisplay();
        DateFormat toDb = getToDb();

        dateTime.put("date", dateParser(dateFromDb, toDb, dateToDisplay));
        dateTime.put("time", dateParser(dateFromDb, toDb, timeToDisplay));
        return dateTime;
    }

    /**
     * Get formatter to display date and time
     */
    private DateFormat getToDisplay(){
        return new SimpleDateFormat("MMMM dd, yyyy HH:mm", Locale.ENGLISH);
    }

    /**
     * Get formatter to display date
     */
    private DateFormat getDateToDisplay(){
        return new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
    }

    /**
     * Get formatter to display time
     */
    private DateFormat getTimeToDisplay(){
        return new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    }

    /**
     * Get formatter to save date to DB
     */
    private DateFormat getToDb(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    }

    /**
     * Get parser to parse one format of date to another
     */
    private String dateParser(String date, DateFormat toParse, DateFormat toFormat) {
        String dateToDB = null;
        try {
            Date dateMain = toParse.parse(date);
            dateToDB = toFormat.format(dateMain);
        } catch (ParseException e) {
            System.out.println("FALSE DATE!");
        }
        return dateToDB;
    }
}
