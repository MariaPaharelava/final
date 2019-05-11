package by.epam.finalTask.hr.dao.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BuilderDAO<T> {
    T buildEntity(ResultSet resultSet) throws SQLException;
}
