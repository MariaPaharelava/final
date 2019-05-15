package by.epam.finalTask.hr.controller.listener;

import by.epam.finalTask.hr.dao.connectionpool.ConnectionPool;
import by.epam.finalTask.hr.dao.connectionpool.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConnectionPoolListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolListener.class);


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ConnectionPool dbConnectionPool = ConnectionPool.getInstance();
            dbConnectionPool.initPoolData();
            LOGGER.info("Connection pool initialized successfully");
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolNotInitializedException("Connection cannot be initialized", e);
        } catch (Throwable e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool dbConnectionPool = ConnectionPool.getInstance();
        dbConnectionPool.dispose();
        LOGGER.info("Connection pool destroyed successfully");
    }
}