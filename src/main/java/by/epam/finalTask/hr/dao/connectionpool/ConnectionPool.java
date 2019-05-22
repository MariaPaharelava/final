package by.epam.finalTask.hr.dao.connectionpool;

import by.epam.finalTask.hr.dao.connectionpool.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool{
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private static Lock lock = new ReentrantLock();

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConnectionQueue;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    public static ConnectionPool getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instance.initPoolData();
                }
            } catch (ConnectionPoolException e) {
                LOGGER.error("Can not create pool" + e.getMessage());
            } catch (Throwable e) {
                LOGGER.info(e.getMessage());
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        this.url = dbResourceManager.getValue(DBParameter.DB_URL);
        this.user = dbResourceManager.getValue(DBParameter.DB_USER);
        this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
        try {
            this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POLL_SIZE));
        } catch (NumberFormatException e) {
            poolSize = 5;
        }
    }

    public void initPoolData() {
        try {
            Class.forName(driverName); //просто загружает класс, включая запуск его статических инициализаторов
            givenAwayConnectionQueue = new ArrayBlockingQueue<>(poolSize);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password); //создадим соединение к базе данных
                PooledConnection pooledConnection = new PooledConnection(connection, connectionQueue, givenAwayConnectionQueue);
                connectionQueue.add(pooledConnection);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new ConnectionPoolException("SQLException in ConnectionPool", e);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
            throw new ConnectionPoolException("ClassNotFoundException in ConnectionPool", e);
        }
    }

    public void dispose() {
        clearConnectionQueue();
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = connectionQueue.take();
            givenAwayConnectionQueue.add(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error connection to the data sources.", e);
        }
        return connection;
    }

    public void closeConnection(Connection con, Statement st, ResultSet rs) {
        try {
            con.close();
        } catch (SQLException e) {
            LOGGER.error("Error closing connection.", e);
        }
        try {
            rs.close();
        } catch (SQLException e) {
            LOGGER.error("Error closing connection.", e);
        }

        try {
            st.close();
        } catch (SQLException e) {
            LOGGER.error("Error closing connection.", e);
        }
    }

    public void closeConnection(Connection con, Statement st) {
        try {
            con.close();
        } catch (SQLException e) {
            LOGGER.error("Error closing connection.", e);
        }

        try {
            st.close();
        } catch (SQLException e) {
            LOGGER.error("Error closing connection.", e);

        }
    }

    public void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            LOGGER.error("Error closing connection.", e);
        }
    }

    private void clearConnectionQueue() {
        try {
            closeConnectionsQueue(givenAwayConnectionQueue);
            closeConnectionsQueue(connectionQueue);
        } catch (SQLException e) {
            LOGGER.error("Error clearing connection queue.", e);
        }
    }

    private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection;
        while ((connection = queue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            ((PooledConnection) connection).reallyClose();
        }
    }}
