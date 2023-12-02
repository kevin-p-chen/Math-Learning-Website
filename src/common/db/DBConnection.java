package common.db;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author baoxu
 */
@WebListener
public class DBConnection implements ServletContextListener {
    public static Connection conn = null;
    public static Properties properties = new Properties();

    public static Connection getConnection(){
        return conn;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        InputStream in = getClass().getResourceAsStream("/jdbc.properties");
        try {
            properties.load(in);
            String userName = properties.getProperty("user");
            String userPwd = properties.getProperty("password");
            String dbURL = properties.getProperty("jdbcUrl");
            String driver = properties.getProperty("driver");

            Class.forName(driver);
            conn = DriverManager.getConnection(dbURL, userName, userPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}


