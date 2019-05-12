package by.epam.finalTask.hr.factory;

import by.epam.finalTask.hr.dao.builder.BuilderDAO;
import by.epam.finalTask.hr.dao.impl.HiringDAO;
import by.epam.finalTask.hr.dao.impl.InterviewDAO;
import by.epam.finalTask.hr.dao.impl.UserDAO;
import by.epam.finalTask.hr.dao.impl.VacancyDAO;
import by.epam.finalTask.hr.entity.Hiring;
import by.epam.finalTask.hr.entity.Interview;
import by.epam.finalTask.hr.entity.User;
import by.epam.finalTask.hr.entity.Vacancy;

import java.sql.Connection;

public class DAOFactory {
    private Connection connection;
    private BuilderFactory builderFactory;

    public DAOFactory(Connection connection, BuilderFactory builderFactory){
        this.connection = connection;
        this.builderFactory = builderFactory;
    }


    public HiringDAO getHiringDAO() {
        BuilderDAO<Hiring> hiringBuilderDAO = builderFactory.getBuilderDAOHiring();
        return new HiringDAO(connection, hiringBuilderDAO);
    }

    public UserDAO getUserDAO() {
        BuilderDAO<User> userBuilderDAO = builderFactory.getBuilderDAOUser();
        return new UserDAO(connection, userBuilderDAO);
    }

    public VacancyDAO getVacancyDAO() {
        BuilderDAO<Vacancy> vacancyBuilderDAO = builderFactory.getBuilderDAOVacancy();
        return new VacancyDAO(connection, vacancyBuilderDAO);
    }


    public InterviewDAO getInterviewDAO() {
        BuilderDAO<Interview> interviewBuilderDAO = builderFactory.getBuilderDAOInterview();
        return new InterviewDAO(connection, interviewBuilderDAO);
    }
}
