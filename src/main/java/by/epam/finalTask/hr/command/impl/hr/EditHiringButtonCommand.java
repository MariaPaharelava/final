package by.epam.finalTask.hr.command.impl.hr;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.controller.helper.PageName;
import by.epam.finalTask.hr.entity.Hiring;
import by.epam.finalTask.hr.service.HiringService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EditHiringButtonCommand implements Command {
    private HiringService hiringService;
    private static final String NUMBER_OF_HIRING = "index";
    private static final String HIRING_ID = "hiringId";
    private static final Logger LOGGER = LogManager.getLogger(EditHiringButtonCommand.class);


    public EditHiringButtonCommand(HiringService hiringService) {
        this.hiringService = hiringService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServiceException {
        HttpSession session = request.getSession(false);

        Integer numberOfHiring = Integer.parseInt(request.getParameter(NUMBER_OF_HIRING));

        try {
            List<Hiring> hiringList = hiringService.getAllHirings();
            Hiring hiring = hiringList.get(numberOfHiring);
            session.setAttribute(HIRING_ID, hiring.getID());
            session.setAttribute(NUMBER_OF_HIRING, numberOfHiring);
            response.sendRedirect(PageName.EDIT_VACANCY);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new CommandException(e);
        }
    }
}

