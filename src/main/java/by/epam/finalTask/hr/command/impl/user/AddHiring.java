package by.epam.finalTask.hr.command.impl.user;

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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AddHiring implements Command {
    private static final String NUMBER_OF_HIRING = "index";
    private static final String VACANCIES = "vacancies";
    private static final String USER = "user";
    private static final String HIRINGS = "hirings";
    private static final Logger LOGGER = LogManager.getLogger(AddHiring.class);
    private HiringService hiringService;
    private VacancyService vacancyService;
    private UserService userService;

    public AddHiring(HiringService hiringService, VacancyService vacancyService, UserService userService) {
        this.hiringService = hiringService;
        this.vacancyService = vacancyService;
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(false);

        Integer numberOfHiring = Integer.parseInt(request.getParameter(NUMBER_OF_HIRING));

        try {
            try {
                Hiring hiring = createObjectOfHiring(session, numberOfHiring);
                addHiringToDB(numberOfHiring, hiring);
                addHiringToSession(session, hiring);
                request.getRequestDispatcher(PageName.USER_VACANCY_PAGE).forward(request, response);
            } catch (ServiceException e) {
                request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
                LOGGER.error(e.getMessage());
                throw new CommandException(e);
            }
        } catch (ServletException | IOException e) {
            LOGGER.error(e.getMessage());
            throw new CommandException(e);
        }
    }

    private Hiring createObjectOfHiring(HttpSession session, Integer numberOfHiring){
        List<Vacancy> vacancyList = (List<Vacancy>) session.getAttribute(VACANCIES);
        User user = (User) session.getAttribute(USER);
        Vacancy vacancy = vacancyList.get(numberOfHiring);
        Hiring hiring = new Hiring(vacancy.getUserId(), user.getID(), vacancy.getID());
        hiring.setHiringStatus("IN_ANTICIPATION");
        return hiring;
    }

    private void addHiringToSession(HttpSession session, Hiring hiring) throws ServiceException {
        List<HiringForShow> hiringForShowList = (List<HiringForShow>) session.getAttribute(HIRINGS);
        Validator validator = new Validator();
        HiringForShow hiringForShow = validator.validateFromHiringToHiringForShow(hiring, vacancyService, userService);
        hiringForShowList.add(hiringForShow);
        session.setAttribute(HIRINGS, hiringForShowList);
    }

    private void addHiringToDB(Integer numberOfHiring, Hiring hiring) throws ServiceException {
        hiringService.addHiring(hiring.getCandidateId(),hiring.getHrId(),hiring.getVacancyId());
    }
}
