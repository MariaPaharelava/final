package by.epam.finalTask.hr.command.impl;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.controller.PageName;
import by.epam.finalTask.hr.entity.Hiring;
import by.epam.finalTask.hr.entity.User;
import by.epam.finalTask.hr.entity.Vacancy;
import by.epam.finalTask.hr.service.HiringService;
import by.epam.finalTask.hr.service.UserService;
import by.epam.finalTask.hr.service.VacancyService;
import by.epam.finalTask.hr.service.exception.LoginAlreadyNoExistsException;
import by.epam.finalTask.hr.util.HiringForShow;
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
    private static final String ENTER_LOGIN = "enterLogin";
    private static final String ENTER_PASSWORD = "enterPassword";
    private static final String VACANCIES = "vacancies";
    private static final String HIRING = "hirings";
    private static final String USER = "user";
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

        try {
            User user = null;
            try {
                user = userService.authorization(login, password);

                session.setAttribute(USER, user);
                if (user == null) {
                    throw new LoginAlreadyNoExistsException("Not Exist");
                }
                switch (user.getUserRole()) {
                    case HR:
                        setAllNecessaryAttribute(session);
                        request.getRequestDispatcher(PageName.HR_VACANCY_PAGE).forward(request, response);
                        break;
                    case ADMIN:
                        request.getRequestDispatcher(PageName.WORK_WITH_USER).forward(request, response);
                        break;
                    case CANDIDATE:
                        request.getRequestDispatcher(PageName.USER_VACANCY_PAGE).forward(request, response);
                        break;
                }
                return;
            } catch (LoginAlreadyNoExistsException e) {
                LOGGER.error("The login already no exists.");
                request.setAttribute(ERROR_MESSAGES, "The login already exists.");
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            request.setAttribute(ENTER_LOGIN, login);
            request.setAttribute(ENTER_PASSWORD, password);
            request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error(e.getMessage());
            throw new CommandException(e);
        }
    }

    private void setAllNecessaryAttribute(HttpSession session) throws ServiceException {
        List<Vacancy> vacancyList = vacancyService.getAllVacancies();
        session.setAttribute(VACANCIES, vacancyList);
        List<HiringForShow> hiringForShows = createHiringList();
        session.setAttribute(HIRING, hiringForShows);
    }

    private List<HiringForShow> createHiringList() throws ServiceException {
        List<Hiring> hiringList = hiringService.getAllHirings();
        List<HiringForShow> hiringForShows = new ArrayList<>();
        for (Hiring aHiringList : hiringList) {
            hiringForShows.add(createHiringForShow(aHiringList));
        }
        return hiringForShows;
    }

    private HiringForShow createHiringForShow(Hiring aHiringList) throws ServiceException {
        User candidate = userService.findById(aHiringList.getCandidateId());
        User hr = userService.findById(aHiringList.getHrId());
        Vacancy vacancy = vacancyService.findById(aHiringList.getVacancyId());
        return new HiringForShow(hr.getName(), hr.getSurname(), candidate.getName(),
                candidate.getSurname(), vacancy.getVacancyPosition(),aHiringList.getOfferEmount(),
                aHiringList.getComment(), aHiringList.getHiringStatus());
    }
}
