package by.epam.finalTask.hr.dao.impl;

import by.epam.finalTask.hr.dao.builder.BuilderDAO;
import by.epam.finalTask.hr.entity.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDAO extends AbstractDAO<User> {
    private static final String SQL_SEARCH_ALL_USER = "SELECT `users_id`, `login`, `users_password`, `fname`, `lname`, `users_role` FROM `users`;";
    private static final String SQL_SEARCH_USER_BY_ID = "SELECT `users_id`, `login`, `users_password`, `fname`, `lname`, `users_role` FROM `users` WHERE `users_id` = ?;";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM `users` WHERE `users_id` = ?;";
    private static final String SQL_ADD_USER = "INSERT INTO `users` (`login`, `users_password`, `fname`, `lname`, `users_role`) VALUES (?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE_USER_BY_ID = "UPDATE `users` SET `users_id` = ?, `login` = ?, `users_password` = ?, `fname` = ?, `lname` = ?, `users_role` = ? WHERE  `users_id` = ?;";
    private static final String SQL_SEARCH_USER_BY_LOGIN = "SELECT `users_id`, `login`, `users_password`, `fname`, `lname`, `users_role` FROM `users`  WHERE `login` = ?;";

    public UserDAO(Connection connection, BuilderDAO<User> userBuilderDAO) {
        super(connection, userBuilderDAO);
    }

    @Override
    public List<User> findAll() {
        return executeQuery(SQL_SEARCH_ALL_USER);
    }

    public Optional<User> findUserByLogin(String login) {
        return executeQueryForSingleResult(SQL_SEARCH_USER_BY_LOGIN, login);
    }

    @Override
    public Optional<User> findEntityById(int id) {
        return executeQueryForSingleResult(SQL_SEARCH_USER_BY_ID, id);
    }

    @Override
    public void delete(int id) {
        executeUpdate(SQL_DELETE_USER_BY_ID, id);
    }

    @Override
    public void save(User entity) {
        if (entity.getID() == null) {
            executeUpdate(SQL_ADD_USER, entity.getLogin(), entity.getPassword(),
                    entity.getSurname(), entity.getName(), entity.getUserRole());
        } else {
            executeUpdate(SQL_UPDATE_USER_BY_ID, entity.getLogin(), entity.getPassword(),
                    entity.getSurname(), entity.getName(), entity.getUserRole());
        }
    }
}
