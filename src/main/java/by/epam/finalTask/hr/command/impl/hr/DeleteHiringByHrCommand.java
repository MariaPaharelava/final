package by.epam.finalTask.hr.command.impl.hr;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.controller.helper.PageName;
import by.epam.finalTask.hr.entity.Hiring;
import by.epam.finalTask.hr.service.HiringService;
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

public class DeleteHiringByHrCommand implements Command {
    private static final String NUMBER_OF_HIRING = "index";
    private static final String HIRINGS = "hirings";
    private static final Logger LOGGER = LogManager.getLogger(DeleteHiringByHrCommand.class);

    private HiringService hiringService;

    public DeleteHiringByHrCommand(HiringService hiringService) {
        this.hiringService = hiringService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(false);

        Integer numberOfHiring = Integer.parseInt(request.getParameter(NUMBER_OF_HIRING));
        try {
            try {
                deleteFromDB(numberOfHiring);
                deleteFromSession(session, numberOfHiring);
                request.getRequestDispatcher(PageName.HRS_VACANCY).forward(request, response);
            } catch (ServiceException e) {
                request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
                LOGGER.error(e.getMessage());
                throw new CommandException(e);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFromDB(Integer numberOfHiring) throws ServiceException {
        List<Hiring> hiringList = hiringService.getAllHirings();
        Hiring hiring = hiringList.get(numberOfHiring);
        hiringService.deleteHiring(hiring.getID());
        LOGGER.info("Hiring with " + hiring.getID() + " id was delete");
    }

    private void deleteFromSession(HttpSession session, Integer numberOfHiring){
        List<HiringForShow> hiringForShowList = (ArrayList<HiringForShow>)session.getAttribute(HIRINGS);
        hiringForShowList.remove(numberOfHiring.intValue());
        session.setAttribute(HIRINGS, hiringForShowList);

    }
}
