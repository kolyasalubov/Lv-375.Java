package com.it.academy.entity;

/**
 * Class Room contains queries for operation with Rooms
 */
public class Room implements IEntity {

    public static enum RoomQueries {

        CREATE_TABLE(QueryNames.CREATE_TABLE, "CREATE TABLE IF NOT EXISTS rooms(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "number INT NOT NULL," +
                "type VARCHAR(40)," +
                "UNIQUE(number)" +
                ")"),

        INSERT(QueryNames.INSERT, "INSERT INTO rooms(number, type) VALUES ('%s', '%s');"),

        GET_BY_ID(QueryNames.GET_BY_ID, "SELECT * FROM rooms WHERE id = %s;"),
        GET_BY_FIELD(QueryNames.GET_BY_FIELD, "SELECT * FROM rooms WHERE %s = %s;"),
        GET_ALL(QueryNames.GET_ALL, "SELECT * FROM rooms ORDER BY number;"),

        UPDATE_ROW_BY_ID(QueryNames.UPDATE_ROW_BY_ID, "UPDATE rooms SET number = '%s', type = '%s' " +
                "WHERE id = %s;"),
        UPDATE_ROW_BY_FIELD(QueryNames.UPDATE_ROW_BY_FIELD, "UPDATE rooms SET number = '%s', type = '%s' " +
                "WHERE %s = '%s';"),

        DELETE_BY_ID(QueryNames.DELETE_BY_ID, "DELETE FROM rooms WHERE id = %s;"),
        DELETE_BY_FIELD(QueryNames.DELETE_BY_FIELD, "DELETE FROM rooms WHERE %s = '%s';");


        private QueryNames queryName;
        private String query;

        private RoomQueries(QueryNames queryName, String query) {
            this.queryName = queryName;
            this.query = query;
        }

        public QueryNames getQueryName() {
            return queryName;
        }

        public String getQuery() {
            return query;
        }
    }

    private long id;
    private int number;
    private String type;

    public Room(){}

    public Room(long id, int number, String type) {
        this.id = id;
        this.number = number;
        this.type = type;
    }

    public Room(int number, String type) {
        this.number = number;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", type='" + type + '\'' +
                '}';
    }
}
