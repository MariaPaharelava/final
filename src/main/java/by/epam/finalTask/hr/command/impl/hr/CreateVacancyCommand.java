package by.epam.finalTask.hr.command.impl.hr;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.controller.helper.PageName;
import by.epam.finalTask.hr.entity.User;
import by.epam.finalTask.hr.entity.Vacancy;
import by.epam.finalTask.hr.service.VacancyService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);

        String vacancyName = request.getParameter(ENTER_VACANCY_NAME);
        String vacancyDescription = request.getParameter(ENTER_VACANCY_DESCRIPTION);
        User user = (User) session.getAttribute(USER);
        List<Vacancy> vacancyList = (List<Vacancy>) session.getAttribute(VACANCIES);

        Vacancy vacancy = new Vacancy(vacancyName, vacancyDescription, user.getUserID());
        vacancyService.addVacancy(vacancyName, vacancyDescription, user.getUserID());

        List<Vacancy> vacancyListNew = vacancyService.getAllVacancies();
        session.removeAttribute(VACANCIES);
        session.setAttribute(VACANCIES, vacancyListNew);
        return PageName.CREATING_VACANCY;
    }
}
