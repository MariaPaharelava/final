package by.epam.finalTask.hr.dao.impl;

import by.epam.finalTask.hr.dao.builder.BuilderDAO;
import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import by.epam.finalTask.hr.entity.Interview;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class InterviewDAO extends AbstractDAO<Interview> {
    private static final String SQL_SEARCH_ALL_INTERVIEW = "SELECT `interview_id`, `hiring_id`, `interview_date`, `interview_type`, `result`, `interview_comment` FROM `interview`;";
    private static final String SQL_SEARCH_INTERVIEW_BY_ID = "SELECT `interview_id`, `hiring_id`, `interview_date`, `interview_type`, `result`, `interview_comment` FROM `interview` WHERE `interview_id` = ?;";
    private static final String SQL_SEARCH_INTERVIEW_BY_HIRING_ID = "SELECT `interview_id`, `hiring_id`, `interview_date`, `interview_type`, `result`, `interview_comment` FROM `interview` WHERE `hiring_id` = ?;";
    private static final String SQL_DELETE_INTERVIEW_BY_ID = "DELETE FROM `interview` WHERE `interview_id` = ?;";
    private static final String SQL_ADD_INTERVIEW = "INSERT INTO `interview` (`interview_id`, `hiring_id`, `interview_date`, `interview_type`, `result`, `interview_comment`) VALUES (? ,?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE_INTERVIEW_BY_ID = "UPDATE `interview` SET `interview_id` = ?, `hiring_id` = ?, `interview_date` = ?, `interview_type` = ?, `result` = ?, `interview_comment` = ? WHERE  `interview_id` = ?;";

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
        if (entity.getID() == null) {
            executeUpdate(SQL_ADD_INTERVIEW, entity.getInterviewID(), entity.getHiringId(),
                    entity.getInterviewDate(), entity.getInterviewType(), entity.getResult(), entity.getComment());
        } else {
            executeUpdate(SQL_UPDATE_INTERVIEW_BY_ID, entity.getInterviewID(), entity.getHiringId(),
                    entity.getInterviewDate(), entity.getInterviewType(), entity.getResult(), entity.getComment());
        }
    }
}
