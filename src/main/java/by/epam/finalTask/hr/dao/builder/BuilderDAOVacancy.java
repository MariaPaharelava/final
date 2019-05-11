package by.epam.finalTask.hr.dao.builder;

import by.epam.finalTask.hr.entity.Vacancy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuilderDAOVacancy implements BuilderDAO<Vacancy> {
    private static final String SQL_POSITION_ID         = "vacancy_position_id";
    private static final String SQL_POSITION            = "vacancy_position";
    private static final String SQL_VACANCY_DESCRIPTION = "vacancy_description";
    private static final String SQL_USER_ID             = "hr_id";

    @Override
    public Vacancy buildEntity(ResultSet resultSet) throws SQLException {
        Vacancy searchedVacancy = new Vacancy();
        searchedVacancy.setVacancyID(resultSet.getInt(SQL_POSITION_ID));
        searchedVacancy.setVacancyPosition(resultSet.getString(SQL_POSITION));
        searchedVacancy.setVacancyDescrintion(resultSet.getString(SQL_VACANCY_DESCRIPTION));
        searchedVacancy.setUserId(resultSet.getInt(SQL_USER_ID));
        return searchedVacancy;
    }
}
