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
import by.epam.finalTask.hr.service.exception.LoginAlreadyNoExistsException;
import by.epam.finalTask.hr.util.HiringForShow;
import by.epam.finalTask.hr.util.Validator;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationCommand implements Command {
    private static final String SERVER_ERROR = "500";
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
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(true);

        String login = request.getParameter(ENTER_LOGIN);
        String password = request.getParameter(ENTER_PASSWORD);
        User user;

        try {
            try {
                user = userService.authorization(login, password);

                session.setAttribute(USER, user);

                switch (user.getUserRole()) {
                    case HR:
                        List<Hiring> hiringListForHr = hiringService.getAllHiringsByHrId(user.getID());
                        setAllNecessaryAttributeForUser(session, hiringListForHr);
                        response.sendRedirect(PageName.HR_VACANCY_PAGE);
                        break;
                    case ADMIN:
                        setAllNecessaryAttributeForAdmin(session);
                        response.sendRedirect(PageName.WORK_WITH_USER);
                        break;
                    case CANDIDATE:
                        List<Hiring> hiringListForCandidate = hiringService.getAllHiringsByUserId(user.getID());
                        setAllNecessaryAttributeForUser(session, hiringListForCandidate);
                        response.sendRedirect(PageName.USER_VACANCY_PAGE);
                        break;
                }
                return;
            } catch (ServiceException e) {
                LOGGER.error(e.getMessage());
                throw new CommandException(e);
            }
            //response.sendRedirect(PageName.INDEX_PAGE);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new CommandException(e);
        }
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
