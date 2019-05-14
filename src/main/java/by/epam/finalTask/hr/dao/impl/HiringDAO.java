package by.epam.finalTask.hr.dao.impl;

import by.epam.finalTask.hr.dao.builder.BuilderDAO;
import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import by.epam.finalTask.hr.entity.Hiring;
import by.epam.finalTask.hr.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class HiringDAO extends AbstractDAO<Hiring>{
    private static final String SQL_SEARCH_ALL_HIRING = "SELECT `hiring_id`, `hr_id`, `candidate_id`, `creation_date`, `hiring_status`, `vacancy_id`, `offer_emount`, `hiring_comment` FROM `hiring`;";
    private static final String SQL_SEARCH_HIRING_BY_ID = "SELECT `hiring_id`, `hr_id`, `candidate_id`, `creation_date`, `hiring_status`, `vacancy_id`, `offer_emount`, `hiring_comment` FROM `hiring` WHERE `hiring_id` = ?;";
    private static final String SQL_DELETE_HIRING_BY_ID = "DELETE FROM `hiring` WHERE `hiring_id` = ?;";
    private static final String SQL_ADD_HIRING = "INSERT INTO `hiring` (`hiring_id`, `hr_id`, `candidate_id`, `creation_date`, `hiring_status`, `vacancy_id`, `offer_emount`, `hiring_comment`) VALUES (? ,?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE_HIRING_BY_ID = "UPDATE `hiring` SET `hiring_id` = ?, `hr_id` = ?, `candidate_id` = ?, `creation_date` = ?, `hiring_status` = ?, `vacancy_id` = ? , `offer_emount` = ?, `hiring_comment` = ? WHERE  `hiring_id` = ?;";

    public HiringDAO(Connection connection, BuilderDAO<Hiring> hiringBuilderDAO) {
        super(connection, hiringBuilderDAO);
    }

    @Override
    public List<Hiring> findAll() throws DAOException {
        return executeQuery(SQL_SEARCH_ALL_HIRING);
    }

    @Override
    public Optional<Hiring> findEntityById(int id) throws DAOException {
        return executeQueryForSingleResult(SQL_SEARCH_HIRING_BY_ID, id);
    }

    @Override
    public void delete(int id) throws DAOException {
        executeUpdate(SQL_DELETE_HIRING_BY_ID, id);
    }

    @Override
    public void save(Hiring entity) throws DAOException {
        Validator validator = new Validator();
        String status = validator.validateFromUpperCaseToLowerCase(entity.getHiringStatus().toString());
        if (entity.getID() == null) {
            executeUpdate(SQL_ADD_HIRING, entity.getHrId(), entity.getCandidateId(), entity.getCreationDate(),
                    entity.getHiringStatus(), entity.getVacancyId(), entity.getOfferEmount(), entity.getComment());
        } else {
            executeUpdate(SQL_UPDATE_HIRING_BY_ID,entity.getID(), entity.getHrId(), entity.getCandidateId(),
                    entity.getCreationDate(), status, entity.getVacancyId(),
                    entity.getOfferEmount(), entity.getComment(), entity.getID());
        }
    }
}
