package by.epam.finalTask.hr.command.impl.hr;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.command.impl.hr.DeleteHiringCommand;
import by.epam.finalTask.hr.controller.PageName;
import by.epam.finalTask.hr.entity.Interview;
import by.epam.finalTask.hr.service.InterviewService;
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

public class DeleteInterviewCommand implements Command {
    private static final String NUMBER_OF_INTERVIEW = "index";
    private static final String HIRINGS_INTERVIEW = "hiringsInterview";
    private static final String HIRING_ID = "hiringID";
    private static final Logger LOGGER = LogManager.getLogger(DeleteHiringCommand.class);
    private InterviewService interviewService;

    public DeleteInterviewCommand(InterviewService interviewService) {
        this.interviewService = interviewService;
    }


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(false);

        Integer numberOfHiring = (Integer)session.getAttribute(HIRING_ID);
        Integer numberOfInterview = Integer.parseInt(request.getParameter(NUMBER_OF_INTERVIEW));
        try {
            try {
                deleteFromDB(numberOfInterview, numberOfHiring);
                deleteFromSession(session, numberOfInterview);
                request.getRequestDispatcher(PageName.WORK_WITH_INTERVIEW).forward(request, response);
                session.removeAttribute(HIRING_ID);
            } catch (ServiceException e) {
                request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
                LOGGER.error(e.getMessage());
                throw new CommandException(e);
            }
        } catch (ServletException | IOException e) {
            LOGGER.error(e.getMessage());
            throw new CommandException(e);
        }
    }

    private void deleteFromDB(Integer numberOfInterview, Integer numberOfHiring) throws ServiceException {
        List<Interview> interviewListList = interviewService.getAllInterviewByHiringId(numberOfHiring);
        Interview interview = interviewListList.get(numberOfInterview);
        interviewService.deleteInterview(interview.getID());
        LOGGER.info("Interview with " + interview.getID() + " id was delete");
    }

    private void deleteFromSession(HttpSession session, Integer numberOfInterview){
        List<Interview> interviewArrayList = (ArrayList<Interview>)session.getAttribute(HIRINGS_INTERVIEW);
        interviewArrayList.remove(numberOfInterview.intValue());
        session.setAttribute(HIRINGS_INTERVIEW, interviewArrayList);
    }
}
