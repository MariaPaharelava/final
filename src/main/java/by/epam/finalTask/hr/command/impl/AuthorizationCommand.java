package by.epam.finalTask.hr.command.impl;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.controller.helper.PageName;
import by.epam.finalTask.hr.entity.Hiring;
import by.epam.finalTask.hr.entity.User;
import by.epam.finalTask.hr.entity.Vacancy;
import by.epam.finalTask.hr.service.HiringService;
import by.epam.finalTask.hr.service.UserService;
import by.epam.finalTask.hr.service.VacancyService;
import by.epam.finalTask.hr.util.HiringForShow;
import by.epam.finalTask.hr.util.Validator;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationCommand implements Command {
    private static final String ENTER_LOGIN = "enterLogin";
    private static final String ENTER_PASSWORD = "enterPassword";
    private static final String VACANCIES = "vacancies";
    private static final String HIRINGS = "hirings";
    private static final String USER = "user";
    private static final String USERS = "users";
    private static final String ERROR_MESSAGES = "errorMessage";
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationCommand.class);

    private UserService userService;
    private VacancyService vacancyService;
    private HiringService hiringService;

    public AuthorizationCommand(UserService userService, VacancyService vacancyService, HiringService hiringService) {
        this.vacancyService = vacancyService;
        this.userService = userService;
        this.hiringService = hiringService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(true);

        String login = request.getParameter(ENTER_LOGIN);
        String password = request.getParameter(ENTER_PASSWORD);

        User user;
        user = userService.authorization(login, password);
        session.setAttribute(USER, user);

        String pageName;
        if (user.getBlocked()) {
            session.setAttribute(ERROR_MESSAGES, "User is Blocked");
            pageName = PageName.INDEX_PAGE;
            LOGGER.info("Blocked user try to login");
        } else {
            pageName = switchUser(user, session);
        }
        return pageName;
    }

    private String switchUser(User user, HttpSession session) throws ServiceException {
        String pageName;
        switch (user.getUserRole()) {
            case HR:
                List<Hiring> hiringListForHr = hiringService.getAllHiringsByHrId(user.getID());
                setAllNecessaryAttributeForUser(session, hiringListForHr);
                pageName = PageName.HR_VACANCY_PAGE;
                break;
            case ADMIN:
                setAllNecessaryAttributeForAdmin(session);
                pageName = PageName.WORK_WITH_USER;
                break;
            case CANDIDATE:
                List<Hiring> hiringListForCandidate = hiringService.getAllHiringsByUserId(user.getID());
                setAllNecessaryAttributeForUser(session, hiringListForCandidate);
                pageName = PageName.USER_VACANCY_PAGE;
                break;
            default:
                pageName = PageName.INDEX_PAGE;
        }
        return pageName;
    }

    private void setAllNecessaryAttributeForAdmin(HttpSession session) throws ServiceException {
        List<User> userList = userService.getAllUsers();
        session.setAttribute(USERS, userList);
    }

    private void setAllNecessaryAttributeForUser(HttpSession session, List<Hiring> hiringListForCandidate)
            throws ServiceException {
        List<Vacancy> vacancyList = vacancyService.getAllVacancies();
        session.setAttribute(VACANCIES, vacancyList);
        List<HiringForShow> hiringForShows = createHiringList(hiringListForCandidate);
        session.setAttribute(HIRINGS, hiringForShows);
    }

    private List<HiringForShow> createHiringList(List<Hiring> hiringList) throws ServiceException {
        List<HiringForShow> hiringForShows = new ArrayList<>();
        Validator validator = new Validator();
        for (Hiring aHiringList : hiringList) {
            HiringForShow hiringForShow = validator.validateFromHiringToHiringForShow(aHiringList,
                    vacancyService, userService);
            hiringForShows.add(hiringForShow);
        }
        return hiringForShows;
    }

}
