package by.epam.finalTask.hr.command.impl.user;

import by.epam.finalTask.hr.command.Command;
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
import java.util.List;

public class AddHiringCommand implements Command {
    private static final String NUMBER_OF_HIRING = "index";
    private static final String VACANCIES = "vacancies";
    private static final String USER = "user";
    private static final String HIRINGS = "hirings";
    private static final Logger LOGGER = LogManager.getLogger(AddHiringCommand.class);
    private static final String MESSAGES = "message";
    private HiringService hiringService;
    private VacancyService vacancyService;
    private UserService userService;

    public AddHiringCommand(HiringService hiringService, VacancyService vacancyService, UserService userService) {
        this.hiringService = hiringService;
        this.vacancyService = vacancyService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        Integer numberOfHiring = Integer.parseInt(request.getParameter(NUMBER_OF_HIRING));
        List<HiringForShow> hiringForShowList = (List<HiringForShow>) session.getAttribute(HIRINGS);
        Hiring hiring = createObjectOfHiring(session, numberOfHiring);

        if (isThisHiringExist(hiringForShowList, hiring)) {
            session.setAttribute(MESSAGES, "This hiring is exist");
            LOGGER.info("This hiring is exist");
            return PageName.USER_VACANCY_SHOW_JSP;
        }

        addHiringToDB(numberOfHiring, hiring);
        addHiringToSession(session, hiring, hiringForShowList);
        session.setAttribute(MESSAGES, "This vacancy is added");
        LOGGER.info("Vacancy is added");
        return PageName.USER_VACANCY_SHOW_JSP;
    }

    private boolean isThisHiringExist(List<HiringForShow> hiringForShowList, Hiring hiring) throws ServiceException {
        boolean result = false;
        Validator validator = new Validator();
        for (HiringForShow aHiringForShowList : hiringForShowList) {
            HiringForShow hiringForShow = validator.validateFromHiringToHiringForShow(hiring, vacancyService, userService);
            if (aHiringForShowList.getVacancyName().equals(hiringForShow.getVacancyName())&&
                    aHiringForShowList.getHrSurname().equals(hiringForShow.getHrSurname())) {
                result = true;
            }
        }
        return result;
    }

    private Hiring createObjectOfHiring(HttpSession session, Integer numberOfHiring) {
        List<Vacancy> vacancyList = (List<Vacancy>) session.getAttribute(VACANCIES);
        User user = (User) session.getAttribute(USER);
        Vacancy vacancy = vacancyList.get(numberOfHiring);
        Hiring hiring = new Hiring(vacancy.getUserId(), user.getID(), vacancy.getID());
        hiring.setHiringStatus("IN_ANTICIPATION");
        return hiring;
    }

    private void addHiringToSession(HttpSession session, Hiring hiring,
                                    List<HiringForShow> hiringForShowList) throws ServiceException {
        Validator validator = new Validator();
        HiringForShow hiringForShow = validator.validateFromHiringToHiringForShow(hiring, vacancyService, userService);
        hiringForShowList.add(hiringForShow);
        session.setAttribute(HIRINGS, hiringForShowList);
    }

    private void addHiringToDB(Integer numberOfHiring, Hiring hiring) throws ServiceException {
        hiringService.addHiring(hiring.getCandidateId(), hiring.getHrId(), hiring.getVacancyId());
    }

}
