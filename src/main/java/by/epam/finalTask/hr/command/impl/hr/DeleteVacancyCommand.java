package by.epam.finalTask.hr.command.impl.hr;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.controller.helper.PageName;
import by.epam.finalTask.hr.entity.Hiring;
import by.epam.finalTask.hr.entity.Vacancy;
import by.epam.finalTask.hr.service.VacancyService;
import by.epam.finalTask.hr.util.HiringForShow;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class DeleteVacancyCommand implements Command {
    private static final String NUMBER_OF_VACANCY = "index";
    private static final String VACANCIES = "vacancies";
    private static final Logger LOGGER = LogManager.getLogger(DeleteVacancyCommand.class);

    private VacancyService vacancyService;

    public DeleteVacancyCommand(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);

        Integer numberOfVacancy = Integer.parseInt(request.getParameter(NUMBER_OF_VACANCY));
        deleteFromDB(numberOfVacancy);
        deleteFromSession(session, numberOfVacancy);
        return PageName.HR_VACANCY_SHOW_JSP;
    }

    private void deleteFromDB(Integer numberOfVacancy) throws ServiceException {
        List<Vacancy> vacancyList = vacancyService.getAllVacancies();
        Vacancy vacancy = vacancyList.get(numberOfVacancy);
        vacancyService.removeVacancyById(vacancy.getID());
        LOGGER.info("Vacancy with " + vacancy.getID() + " id was delete");
    }

    private void deleteFromSession(HttpSession session, Integer numberOfVacancy) {
        List<Vacancy> vacancies = (ArrayList<Vacancy>) session.getAttribute(VACANCIES);
        vacancies.remove(numberOfVacancy.intValue());
        session.setAttribute(VACANCIES, vacancies);

    }
}
