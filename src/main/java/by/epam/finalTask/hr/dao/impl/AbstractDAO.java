package by.epam.finalTask.hr.dao.impl;

import by.epam.finalTask.hr.dao.DAO;
import by.epam.finalTask.hr.dao.builder.BuilderDAO;
import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T> implements DAO<T> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    private final Connection connection;
    private final BuilderDAO<T> builder;

    public AbstractDAO(Connection connection, BuilderDAO<T> builder) {
        this.connection = connection;
        this.builder = builder;
    }

    protected int executeUpdate(String query, Object... params) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareStatement(statement, params);

            LOGGER.debug("Prepared statement: {}", statement);
            System.out.println(connection.isClosed());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    protected List<T> executeQuery(String query, Object... params) throws DAOException {
        List<T> resultList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareStatement(statement, params);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    T builtObject = builder.buildEntity(resultSet);
                    resultList.add(builtObject);
                }
            }
            System.out.println(connection.isClosed());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return resultList;
    }

    protected Optional<T> executeQueryForSingleResult(String query, Object... params) throws DAOException {
        List<T> itemsList = executeQuery(query, params);
        Optional<T> result = Optional.empty();
        if (itemsList.size() == 1) {
            T firstItem = itemsList.get(0);
            result = Optional.of(firstItem);
        }

        return result;
    }

    private void prepareStatement(PreparedStatement statement, Object... params) throws SQLException {
        for (int i = 1; i < params.length + 1; i++) {
            statement.setObject(i, params[i - 1]);
        }
    }
}
