package by.epam.finalTask.hr.factory;

import by.epam.finalTask.hr.dao.impl.HiringDAO;
import by.epam.finalTask.hr.dao.impl.InterviewDAO;
import by.epam.finalTask.hr.dao.impl.UserDAO;
import by.epam.finalTask.hr.dao.impl.VacancyDAO;
import by.epam.finalTask.hr.service.HiringService;
import by.epam.finalTask.hr.service.InterviewService;
import by.epam.finalTask.hr.service.UserService;
import by.epam.finalTask.hr.service.VacancyService;
import by.epam.finalTask.hr.service.impl.HiringServiceImpl;
import by.epam.finalTask.hr.service.impl.InterviewServiceImpl;
import by.epam.finalTask.hr.service.impl.UserServiceImpl;
import by.epam.finalTask.hr.service.impl.VacancyServiceImpl;
import by.epam.finalTask.hr.util.Validator;

public class ServiceFactory {
    private DAOFactory daoFactory;
    public ServiceFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    UserService getUserService() {
        UserDAO userDao = (UserDAO) daoFactory.getUserDAO();
        return new UserServiceImpl(userDao);
    }

    VacancyService getVacancyService() {
        VacancyDAO vacancyDao = (VacancyDAO) daoFactory.getVacancyDAO();
        return new VacancyServiceImpl(vacancyDao);
    }

    HiringService getHiringService() {
        HiringDAO hiringDao = (HiringDAO) daoFactory.getHiringDAO();
        return new HiringServiceImpl(hiringDao);
    }

    InterviewService getInterviewService() {
        InterviewDAO interviewDao = (InterviewDAO) daoFactory.getInterviewDAO();
        return new InterviewServiceImpl(interviewDao);
    }
}

