package listener;

import jmx.Premier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import servlets.Index;
import servlets.Inscription;

import javax.management.*;
import javax.servlet.ServletContextEvent;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StartUpListerner {

    private static final Logger logger = LogManager.getLogger(Index.class);

    /**
     * Default constructor.
     */
    public StartUpListerner() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("D�marrage");
        logger.debug("D�marrage");

        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
            logger.info("Connexion ok sur localhost");
            con.close();
        } catch (ClassNotFoundException e) {
            logger.error("Driver not available", e);
        } catch (SQLException e) {
            logger.error("Error while connecting to DB", e);
        }

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = null;

        try {
            name = new ObjectName("fr.epsi.jmx:type=PremierMBean");
            Premier mbean = new Premier();

            mbs.registerMBean(mbean, name);

        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub

    }

}
