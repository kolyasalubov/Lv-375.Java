package myArticles.edu.Services;

import myArticles.edu.container.IocContainer;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataBaseConnectionServiceTest {

    @Test
    public void createDataTable() {
        DataBaseConnectionService dataBaseConnectionService = IocContainer.get().getDataBaseConnectionService();
        dataBaseConnectionService.createDataTable();
    }
}