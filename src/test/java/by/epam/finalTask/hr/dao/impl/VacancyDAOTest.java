package by.epam.finalTask.hr.dao.impl;


import by.epam.finalTask.hr.dao.DAO;
import by.epam.finalTask.hr.dao.builder.BuilderDAO;
import by.epam.finalTask.hr.dao.connectionpool.ConnectionPool;
import by.epam.finalTask.hr.entity.Vacancy;
import by.epam.finalTask.hr.factory.BuilderFactory;
import by.epam.finalTask.hr.factory.DAOFactory;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class VacancyDAOTest {

    @Test
    public void test(){
        BuilderFactory builderFactory = new BuilderFactory();
        Connection connection = ConnectionPool.getInstance().getConnection();
        DAOFactory daoFactory = new DAOFactory(connection,builderFactory);
        BuilderDAO<Vacancy> vacancyBuilderDAO = builderFactory.getBuilderDAOVacancy();
        DAO<Vacancy> vacancyDAO = new VacancyDAO(connection,  vacancyBuilderDAO);
        List<Vacancy> vacancies = vacancyDAO.findAll();
        System.out.println(vacancies.get(0).getID() + " " + vacancies.get(0).getVacancyDescrintion());
    }

}