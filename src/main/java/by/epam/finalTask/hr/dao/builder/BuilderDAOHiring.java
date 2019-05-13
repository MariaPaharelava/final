package by.epam.finalTask.hr.dao.builder;

import by.epam.finalTask.hr.entity.Hiring;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuilderDAOHiring implements BuilderDAO<Hiring> {
    private static final String SQL_HIRING_ID = "hiring_id";
    private static final String SQL_HR_ID = "hr_id";
    private static final String SQL_CANDIDATE_ID = "candidate_id";
    private static final String SQL_CREATION_DATE = "creation_date";
    private static final String SQL_HIRING_STATUS = "hiring_status";
    private static final String SQL_VACANCY_ID = "vacancy_id";
    private static final String SQL_OFFER_EMOUNT = "offer_emount";
    private static final String SQL_HIRING_COMMENT = "hiring_comment";


    @Override
    public Hiring buildEntity(ResultSet resultSet) throws SQLException {
        Hiring searchedHiring = new Hiring();
        searchedHiring.setHiringId(resultSet.getInt(SQL_HIRING_ID));
        searchedHiring.setHrId(resultSet.getInt(SQL_HR_ID));
        searchedHiring.setCandidateId(resultSet.getInt(SQL_CANDIDATE_ID));
        searchedHiring.setCreationDate(resultSet.getDate(SQL_CREATION_DATE));
        searchedHiring.setHiringStatus(resultSet.getString(SQL_HIRING_STATUS));
        searchedHiring.setVacancyId(resultSet.getInt(SQL_VACANCY_ID));
        searchedHiring.setOfferEmount(resultSet.getDouble(SQL_OFFER_EMOUNT));
        searchedHiring.setComment(resultSet.getString(SQL_HIRING_COMMENT));
        return searchedHiring;
    }
}
