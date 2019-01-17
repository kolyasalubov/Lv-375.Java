package myArticles.edu.db;

/**
 * In this Class we realised Repository pattern to simply get DataSource object
 */
public final class DataSourceRepository {
    private static final String FAILED_JDBC_DRIVER = "Failed to Create JDBC Driver";

    private DataSourceRepository() {
    }

    public static DataSource getDefault() {
        return getMySqlLocalHost();
    }

    public static DataSource getMySqlLocalHost() {
        return new DataSource( "jdbc:mysql://localhost:3306/ProjectDB?createDatabaseIfNotExist=true", "root", "1111");
    }
}