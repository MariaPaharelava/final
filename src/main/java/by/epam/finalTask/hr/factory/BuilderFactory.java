package by.epam.finalTask.hr.factory;


import by.epam.finalTask.hr.dao.builder.*;
import by.epam.finalTask.hr.entity.Hiring;
import by.epam.finalTask.hr.entity.Interview;
import by.epam.finalTask.hr.entity.User;
import by.epam.finalTask.hr.entity.Vacancy;

public class BuilderFactory {

    public BuilderDAO<Hiring> getBuilderDAOHiring() {
        return new BuilderDAOHiring();
    }

    public BuilderDAO<Vacancy> getBuilderDAOVacancy() {
        return new BuilderDAOVacancy();
    }


    public BuilderDAO<User> getBuilderDAOUser() {
        return new BuilderDAOUser();
    }


    public BuilderDAO<Interview> getBuilderDAOInterview() {
        return new BuilderDAOInterview();
    }

}
