package by.epam.finalTask.hr.dao.impl;

import by.epam.finalTask.hr.dao.builder.BuilderDAO;
import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import by.epam.finalTask.hr.entity.Vacancy;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

//TODO коннектион пул, использовтаь симафор
//TODO ActionType( default: runTime)
//TODO дао отличается от репозитория тем, что в дао есть конкретные методы, а в репозитории спецификации
//TODO функциональные требования(функциональные и нефункциональнаые прочитать)) защита транзакции.

//todo коннекшн можно обернуть в коннекшн прокси(книга блинчика)
// мультимодульный проект - нексколько пректов собираются в одиин и спомощью варки несколько джарок объединяются
// команд и сервис эксепщен один уровень(просто для сведения)

// todo посмотреть , чтобы ввод зарплаты был обязателен или обрабатывался(не пустое полу)
// todo валидатор
// todo джава скрипт обрабатывал ввод на клиенте и потом что-то еще обрабатывало на сервере


public class VacancyDAO extends AbstractDAO<Vacancy> {
    private static final String SQL_SEARCH_ALL_VACANCY = "SELECT `vacancy_position_id`, `vacancy_position`, `vacancy_description`, `hr_id`  FROM `vacancy`;";
    private static final String SQL_SEARCH_VACANCY_BY_ID = "SELECT `vacancy_position_id`, `vacancy_position`, `vacancy_description`, `hr_id` FROM `vacancy` WHERE `vacancy_position_id` = ?;";
    private static final String SQL_SEARCH_VACANCY_BY_VACANCY = "SELECT `vacancy_position_id`, `vacancy_position`, `vacancy_description`, `hr_id` FROM `vacancy` WHERE `vacancy_position` = ?, `vacancy_description` = ?,`hr_id` = ?;";
    private static final String SQL_DELETE_VACANCY_BY_ID = "DELETE FROM `vacancy` WHERE `vacancy_position_id` = ?;";
    private static final String SQL_ADD_VACANCY = "INSERT INTO `vacancy` (`vacancy_position`, `vacancy_description`, `hr_id`) VALUES (?, ?, ?);";
    private static final String SQL_UPDATE_VACANCY_BY_ID = "UPDATE `vacancy` SET `vacancy_position_id` =?, `vacancy_position`= ?, `vacancy_description`= ? , `hr_id` WHERE  `vacancy_position_id` = ?;";

    public VacancyDAO(Connection connection, BuilderDAO<Vacancy> vacancyBuilderDAO) {
        super(connection, vacancyBuilderDAO);
    }

    @Override
    public List<Vacancy> findAll() throws DAOException {
        return executeQuery(SQL_SEARCH_ALL_VACANCY);
    }

    @Override
    public Optional<Vacancy> findEntityById(int id) throws DAOException {
        return executeQueryForSingleResult(SQL_SEARCH_VACANCY_BY_ID, id);
    }

    public Optional<Vacancy> findEntityByPositionAndDescription(String position,
                                                                String description) throws DAOException {
        return executeQueryForSingleResult(SQL_SEARCH_VACANCY_BY_ID, position, description);
    }

    public Optional<Vacancy> findEntityByEntity(String name, String description, Integer hrId) throws DAOException {
        return executeQueryForSingleResult(SQL_SEARCH_VACANCY_BY_VACANCY, name, description, hrId);
    }

    @Override
    public void delete(int id) throws DAOException {
        executeUpdate(SQL_DELETE_VACANCY_BY_ID, id);
    }

    @Override
    public void save(Vacancy entity) throws DAOException {
        if (entity.getID() == null) {
            executeUpdate(SQL_ADD_VACANCY, entity.getVacancyPosition(), entity.getVacancyDescrintion(), entity.getUserId());
        } else {
            executeUpdate(SQL_UPDATE_VACANCY_BY_ID, entity.getID());
        }
    }
}
