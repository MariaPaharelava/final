package by.epam.finalTask.hr.dao.builder;

import by.epam.finalTask.hr.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuilderDAOUser implements BuilderDAO<User> {
    private static final String SQL_USERS_ID = "users_id";
    private static final String SQL_LOGIN = "login";
    private static final String SQL_PASSWORD = "users_password";
    private static final String SQL_FNAME = "fname";
    private static final String SQL_LNAME = "lname";
    private static final String SQL_ROLE = "users_role";
    private static final String SQL_BLOCKED = "is_blocked";


    @Override
    public User buildEntity(ResultSet resultSet) throws SQLException {
        User searchedUser = new User();
        searchedUser.setUserID(resultSet.getInt(SQL_USERS_ID));
        searchedUser.setLogin(resultSet.getString(SQL_LOGIN));
        searchedUser.setPassword(resultSet.getString(SQL_PASSWORD));
        searchedUser.setSurname(resultSet.getString(SQL_FNAME));
        searchedUser.setName(resultSet.getString(SQL_LNAME));
        searchedUser.setUserRole(resultSet.getString(SQL_ROLE));
        searchedUser.setBlocked(resultSet.getBoolean(SQL_BLOCKED));
        return searchedUser;
    }
}