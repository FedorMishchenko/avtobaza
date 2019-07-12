package db;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *  Wrap over BasicDataSource
 *  sets initialization parameters:
 *       set pool connections ,
 *       set idle/max pool size ,
 *       set init pool size ,
 *       set validation query SELECT = min 1 row
 *  jdbc properties located in /resources/jdbc.properties
 */
public class DataSource {
    private static BasicDataSource dataSource;

    private DataSource(){}

    public static synchronized BasicDataSource getInstance() {
        if (dataSource == null) {
            dataSource =  new BasicDataSource();
        }
        init();
        return dataSource;
    }

    private static void init() {
        final Properties properties = new Properties();
        try (InputStream resourceAsStream = DataSource.class
                .getResourceAsStream("/jdbc.properties")) {
            properties.load(resourceAsStream);
            dataSource.setDriverClassName(properties.getProperty("driverName"));
            dataSource.setUsername(properties.getProperty("userName"));
            dataSource.setPassword(properties.getProperty("password"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setMaxActive(10);
            dataSource.setMaxIdle(5);
            dataSource.setInitialSize(5);
            dataSource.setValidationQuery("SELECT 1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
