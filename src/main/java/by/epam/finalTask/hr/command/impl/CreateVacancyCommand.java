package by.epam.finalTask.hr.command.impl;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.controller.PageName;
import by.epam.finalTask.hr.entity.User;
import by.epam.finalTask.hr.entity.Vacancy;
import by.epam.finalTask.hr.service.VacancyService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CreateVacancyCommand implements Command {
    private static final String ENTER_VACANCY_NAME = "enterVacancyName";
    private static final String ENTER_VACANCY_DESCRIPTION = "enterVacancyDescription";
    private static final String USER = "user";
    private static final String VACANCIES = "vacancies";
    private static final Logger LOGGER = LogManager.getLogger(CreateVacancyCommand.class);
    private VacancyService vacancyService;

    public CreateVacancyCommand(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(true);

        String vacancyName = request.getParameter(ENTER_VACANCY_NAME);
        String vacancyDescription = request.getParameter(ENTER_VACANCY_DESCRIPTION);
        try {
            User user = (User) session.getAttribute(USER);
            List<Vacancy> vacancyList = (List<Vacancy>) session.getAttribute(VACANCIES);

            Vacancy vacancy = new Vacancy(ENTER_VACANCY_DESCRIPTION, ENTER_VACANCY_NAME, user.getUserID());
            vacancyService.addVacancy(ENTER_VACANCY_NAME, ENTER_VACANCY_DESCRIPTION, user.getUserID());

            LOGGER.info(ENTER_VACANCY_DESCRIPTION + " " + ENTER_VACANCY_NAME + " " + user.getUserID());

            List<Vacancy> vacancyListNew = vacancyService.getAllVacancies();
            session.setAttribute(VACANCIES, vacancyListNew);

            request.getRequestDispatcher(PageName.CREATING_VACANCY).forward(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.info(e.getMessage());
            throw new CommandException(e);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage());
            throw new CommandException(e);
        }

    }
}
