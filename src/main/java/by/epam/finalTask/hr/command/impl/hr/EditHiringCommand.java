package by.epam.finalTask.hr.command.impl.hr;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.command.impl.AuthorizationCommand;
import by.epam.finalTask.hr.controller.helper.PageName;
import by.epam.finalTask.hr.entity.Hiring;
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
import java.util.ArrayList;
import java.util.List;

public class EditHiringCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationCommand.class);
    private static final String HIRINGS = "hirings";
    private static final String HIRING_ID = "hiringId";
    private static final String ENTER_SALARY = "salary";
    private static final String ENTER_STATUS = "status";
    private static final String ENTER_COMMENT = "comment";
    private static final String NUMBER_OF_HIRING = "index";
    private UserService userService;
    private VacancyService vacancyService;
    private HiringService hiringService;
    private Validator validator = new Validator();

    public EditHiringCommand(UserService userService, VacancyService vacancyService, HiringService hiringService) {
        this.vacancyService = vacancyService;
        this.userService = userService;
        this.hiringService = hiringService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(false);

        String salary = request.getParameter(ENTER_SALARY);
        String status = request.getParameter(ENTER_STATUS);
        String comment = request.getParameter(ENTER_COMMENT);

        status = validator.validateFromLowerCaseToUpperCase(status);
        try {
            try {
                Integer hiringID = (Integer) session.getAttribute(HIRING_ID);
                Integer numberOfHiring = (Integer) session.getAttribute(NUMBER_OF_HIRING);
                Hiring hiringNew = hiringService.changeHiring(hiringID,
                        Double.valueOf(salary), status, comment);
                changeHiringFromSession(session, numberOfHiring, hiringNew);
                session.removeAttribute(NUMBER_OF_HIRING);
                session.removeAttribute(HIRING_ID);
                request.getRequestDispatcher(PageName.HRS_VACANCY).forward(request, response);
            } catch (ServiceException e) {
                LOGGER.info(e.getMessage());
                request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
                throw new CommandException(e);
            }
        } catch (ServletException | IOException e) {
            LOGGER.error(e.getMessage());
            throw new CommandException(e);
        }
    }

    private void changeHiringFromSession(HttpSession session, Integer numberOfHiring,
                                         Hiring hiringNew) throws ServiceException {
        HiringForShow hiringForShow = validator.validateFromHiringToHiringForShow(hiringNew,
                vacancyService, userService);
        List<HiringForShow> hiringForShowList = (ArrayList<HiringForShow>) session.getAttribute(HIRINGS);
        hiringForShowList.set(numberOfHiring, hiringForShow);
        session.setAttribute(HIRINGS, hiringForShowList);
    }
}
