package com.it.academy.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DateParser {

    public String parseToDb(String dateFromDisplay) {
        DateFormat toDisplay = getToDisplay();
        DateFormat toDb = getToDb();
        return dateParser(dateFromDisplay, toDisplay, toDb);
    }

    public Map<String, String> parseToDisplay(String dateFromDb) {
        Map<String, String> dateTime = new HashMap<>();
        DateFormat dateToDisplay = getDateToDisplay();
        DateFormat timeToDisplay = getTimeToDisplay();
        DateFormat toDb = getToDb();

        dateTime.put("date", dateParser(dateFromDb, toDb, dateToDisplay));
        dateTime.put("time", dateParser(dateFromDb, toDb, timeToDisplay));
        return dateTime;
    }

    private DateFormat getToDisplay(){
        return new SimpleDateFormat("MMMM dd, yyyy HH:mm", Locale.ENGLISH);
    }

    private DateFormat getDateToDisplay(){
        return new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
    }
    private DateFormat getTimeToDisplay(){
        return new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    }

    private DateFormat getToDb(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    }

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
