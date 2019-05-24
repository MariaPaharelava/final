package by.epam.finalTask.hr.command.impl.hr;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.controller.helper.PageName;
import by.epam.finalTask.hr.service.HiringService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditVacancyButtonCommand implements Command {
    private static final String NUMBER_OF_VACANCY = "index";
    private static final String MESSAGES = "message";
    private static final Logger LOGGER = LogManager.getLogger(EditVacancyButtonCommand.class);


    public EditVacancyButtonCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        Integer numberOfVacancy = Integer.parseInt(request.getParameter(NUMBER_OF_VACANCY));
        session.setAttribute(NUMBER_OF_VACANCY, numberOfVacancy);
        session.setAttribute(MESSAGES,"Set vacancy on session");
        return PageName.HR_VACANCY_SHOW_JSP;
    }
}

