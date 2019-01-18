package com.it.academy.db;


public final class DataSourceRepository {

    private DataSourceRepository(){}

    public static DataSource getSource() {
        return getMySqlLocalHost();
    }

    private static DataSource getMySqlLocalHost() {
        PropertiesReader pr = new PropertiesReader();
        return new DataSource("jdbc:mysql://localhost:3306/room_booking?createDatabaseIfNotExist=true&serverTimezone=UTC",
                pr.getUsername(), pr.getPassword());
    }
}
