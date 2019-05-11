package by.epam.finalTask.hr.dao.builder;

import by.epam.finalTask.hr.entity.Interview;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuilderDAOInterview implements BuilderDAO<Interview> {
    private static final String SQL_INTERVIEW_ID = "interview_id";
    private static final String SQL_HIRING_ID = "hiring_id";
    private static final String SQL_INTERVIEW_DATE = "interview_date";
    private static final String SQL_INTERVIEW_TYPE = "interview_type";
    private static final String SQL_RESULT = "result";
    private static final String SQL_INTERVIEW_COMMENT = "interview_comment";


    @Override
    public Interview buildEntity(ResultSet resultSet) throws SQLException {
        Interview searchedInterview = new Interview();
        searchedInterview.setInterviewID(resultSet.getInt(SQL_INTERVIEW_ID));
        searchedInterview.setHiringId(resultSet.getInt(SQL_HIRING_ID));
        searchedInterview.setInterviewDate(resultSet.getDate(SQL_INTERVIEW_DATE));
        searchedInterview.setInterviewType(resultSet.getString(SQL_INTERVIEW_TYPE));
        searchedInterview.setResult(resultSet.getString(SQL_RESULT));
        searchedInterview.setComment(resultSet.getString(SQL_INTERVIEW_COMMENT));
        return searchedInterview;
    }
}
