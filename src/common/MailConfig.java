package common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.InputStream;
import java.util.Properties;

@WebListener
public class MailConfig implements ServletContextListener {
    public static Properties properties = new Properties();
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        InputStream in = getClass().getResourceAsStream("/mail.properties");
        try {
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
