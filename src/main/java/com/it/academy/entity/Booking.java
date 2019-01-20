package com.it.academy.entity;

public class Booking {

    public static enum BookingQueries {

        CREATE_TABLE(QueryNames.CREATE_TABLE, "CREATE TABLE IF NOT EXISTS bookings(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "room_id BIGINT," +
                "user_id BIGINT," +
                "start_date DATETIME NOT NULL," +
                "end_date DATETIME NOT NULL," +
                "purpose VARCHAR(100)," +
                "FOREIGN KEY(room_id) REFERENCES rooms(id) ON DELETE CASCADE," +
                "FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE" +
                ")"),

        // TODO complicated query with FOREIGN KEYS
        INSERT(QueryNames.INSERT, "INSERT INTO bookings(room_id, user_id, start_date, end_date, purpose) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s');"),

        GET_BY_ID(QueryNames.GET_BY_ID, "SELECT * FROM bookings WHERE id = %s;"),
        GET_BY_FIELD(QueryNames.GET_BY_FIELD, "SELECT * FROM bookings WHERE %s = '%s';"),
        GET_FUTURE_BY_FIELD(QueryNames.GET_FUTURE_BY_FIELD, "SELECT * FROM bookings " +
                "WHERE %s = '%s' AND start_date >= '%s' " +      // '%s' --> current date
                "ORDER BY start_date;"),
        GET_PAST_BY_FIELD(QueryNames.GET_PAST_BY_FIELD, "SELECT * FROM bookings " +
                "WHERE %s = '%s' AND start_date <= '%s' " +      // '%s' --> current date
                "ORDER BY start_date DESC;"),

        // TODO complicated query with FOREIGN KEYS
        UPDATE_ROW_BY_ID(QueryNames.UPDATE_ROW_BY_ID, "UPDATE bookings " +
                "SET room_id = '%s', user_id = '%s', start_date = '%s', end_date = '%s', purpose = '%s' " +
                "WHERE id = %s;"),
        UPDATE_ROW_BY_FIELD(QueryNames.UPDATE_ROW_BY_FIELD, "UPDATE bookings " +
                "SET room_id = '%s', user_id = '%s', start_date = '%s', end_date = '%s', purpose = '%s' " +
                "WHERE %s = '%s';"),

        DELETE_BY_ID(QueryNames.DELETE_BY_ID, "DELETE FROM bookings WHERE id = %s;"),
        DELETE_BY_FIELD(QueryNames.DELETE_BY_FIELD, "DELETE FROM bookings WHERE %s = '%s';"),

        IS_EXIST(QueryNames.IS_EXIST, "SELECT id FROM bookings " +
                "WHERE ((start_date <= '%s' AND end_date >= '%s') " +  // current start_date
                "OR (start_date BETWEEN '%s' AND '%s')) AND (room_id = '%s');");    // current start_date AND current end_date


        private QueryNames queryName;
        private String query;

        private BookingQueries(QueryNames queryName, String query) {
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
    private long roomId;
    private long userId;
    private String startDate;
    private String endDate;
    private String purpose;

    public Booking(){}

    public Booking(long id, long roomId, long userId, String startDate, String endDate, String purpose) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.purpose = purpose;
    }

    public Booking(long roomId, long userId, String startDate, String endDate, String purpose) {
        this.roomId = roomId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.purpose = purpose;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", userId=" + userId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", purpose='" + purpose + '\'' +
                '}';
    }
}
