package by.epam.finalTask.hr.command.impl.user;

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

public class DeleteHiringByUserCommand implements Command {
    private static final String NUMBER_OF_HIRING = "index";
    private static final String HIRINGS = "hirings";
    private static final Logger LOGGER = LogManager.getLogger(DeleteHiringByUserCommand.class);
    private HiringService hiringService;

    public DeleteHiringByUserCommand(HiringService hiringService) {
        this.hiringService = hiringService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        Integer numberOfHiring = Integer.parseInt(request.getParameter(NUMBER_OF_HIRING));
        deleteHiringfromDB(numberOfHiring);
        deleteHiringfromSession(session, numberOfHiring);
        return PageName.USERS_VACANCY;
    }

    private void deleteHiringfromSession(HttpSession session, Integer numberOfHiring) {
        List<HiringForShow> hiringForShowArrayList = (ArrayList<HiringForShow>) session.getAttribute(HIRINGS);
        hiringForShowArrayList.remove(numberOfHiring.intValue());
        session.setAttribute(HIRINGS, hiringForShowArrayList);
    }

    private void deleteHiringfromDB(Integer numberOfHiring) throws ServiceException {
        List<Hiring> hiringList = hiringService.getAllHirings();
        Hiring hiring = hiringList.get(numberOfHiring);
        hiringService.deleteHiring(hiring.getID());
    }
}
