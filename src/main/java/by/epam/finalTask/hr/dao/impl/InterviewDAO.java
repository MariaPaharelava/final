package by.epam.finalTask.hr.dao.impl;

import by.epam.finalTask.hr.dao.builder.BuilderDAO;
import by.epam.finalTask.hr.dao.connectionpool.PooledConnection;
import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import by.epam.finalTask.hr.entity.Interview;
import by.epam.finalTask.hr.util.Validator;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class InterviewDAO extends AbstractDAO<Interview> {
    private static final String SQL_SEARCH_ALL_INTERVIEW = "SELECT `interview_id`, `hiring_id`, `interview_date`, `interview_type`, `result`, `interview_comment` FROM `interview`;";
    private static final String SQL_SEARCH_INTERVIEW_BY_ID = "SELECT `interview_id`, `hiring_id`, `interview_date`, `interview_type`, `result`, `interview_comment` FROM `interview` WHERE `interview_id` = ?;";
    private static final String SQL_SEARCH_INTERVIEW_BY_HIRING_ID = "SELECT `interview_id`, `hiring_id`, `interview_date`, `interview_type`, `result`, `interview_comment` FROM `interview` WHERE `hiring_id` = ?;";
    private static final String SQL_DELETE_INTERVIEW_BY_ID = "DELETE FROM `interview` WHERE `interview_id` = ?;";
    private static final String SQL_ADD_INTERVIEW = "INSERT INTO `interview` (`hiring_id`,`interview_type`, `result`, `interview_comment`) VALUES (?, ?, ?, ?);";
    private static final String SQL_UPDATE_INTERVIEW_BY_ID = "UPDATE `interview` SET `interview_id` = ?, `hiring_id` = ?, `interview_type` = ?, `result` = ?, `interview_comment` = ? WHERE  `interview_id` = ?;";

    public InterviewDAO(Connection connection, BuilderDAO<Interview> interviewBuilderDAO) {
        super(connection, interviewBuilderDAO);
    }

    @Override
    public List<Interview> findAll() throws DAOException {
        return executeQuery(SQL_SEARCH_ALL_INTERVIEW);
    }

    @Override
    public Optional<Interview> findEntityById(int id) throws DAOException {
        return executeQueryForSingleResult(SQL_SEARCH_INTERVIEW_BY_ID, id);
    }

    public List<Interview> findEntityByHiringId(int id) throws DAOException {
        return executeQuery(SQL_SEARCH_INTERVIEW_BY_HIRING_ID, id);
    }

    @Override
    public void delete(int id) throws DAOException {
        executeUpdate(SQL_DELETE_INTERVIEW_BY_ID, id);
    }

    @Override
    public void save(Interview entity) throws DAOException {
        Validator validator = new Validator();
        String type = validator.validateFromUpperCaseToLowerCaseForDB(entity.getInterviewType().toString());
        if (entity.getID() == null) {
            executeUpdate(SQL_ADD_INTERVIEW, entity.getHiringId(),
                    type, entity.getResult(), entity.getComment());
        } else {
            executeUpdate(SQL_UPDATE_INTERVIEW_BY_ID, entity.getInterviewID(), entity.getHiringId(),
                    type, entity.getResult(), entity.getComment(), entity.getInterviewID());
        }
    }
}
