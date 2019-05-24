package by.epam.finalTask.hr.command.impl.hr;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.controller.helper.PageName;
import by.epam.finalTask.hr.entity.Vacancy;
import by.epam.finalTask.hr.service.VacancyService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class EditVacancyCommand implements Command {
    private static final String ENTER_VACANCY_DESCRIPTION = "enterVacancyDescription";
    private static final String INDEX = "index";
    private static final String VACANCIES = "vacancies";
    private static final Logger LOGGER = LogManager.getLogger(CreateVacancyCommand.class);
    private VacancyService vacancyService;

    public EditVacancyCommand(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);

        Integer numberOfVacancy = (Integer) session.getAttribute(INDEX);
        String vacancyDescription = request.getParameter(ENTER_VACANCY_DESCRIPTION);

        List<Vacancy> vacancyList = (List<Vacancy>) session.getAttribute(VACANCIES);
        Vacancy vacancy = vacancyList.get(numberOfVacancy);
        vacancy.setVacancyDescrintion(vacancyDescription);
        vacancyList.set(numberOfVacancy, vacancy);
        vacancyService.changeVacancyById(vacancy.getID(),
                vacancy.getVacancyPosition(), vacancyDescription);
        return PageName.HR_VACANCY_SHOW_JSP;
    }

}
