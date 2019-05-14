package by.epam.finalTask.hr.command.impl.hr;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.controller.PageName;
import by.epam.finalTask.hr.entity.Hiring;
import by.epam.finalTask.hr.entity.Interview;
import by.epam.finalTask.hr.service.HiringService;
import by.epam.finalTask.hr.service.InterviewService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TableHiringCommand implements Command {
    private HiringService hiringService;
    private InterviewService interviewService;
    private static final String NUMBER_OF_HIRING = "table";
    private static final String HIRINGS_INTERVIEW = "hiringsInterview";
    private static final String HIRING_ID = "hiringID";
    private static final Logger LOGGER = LogManager.getLogger(TableHiringCommand.class);


    public TableHiringCommand(HiringService hiringService, InterviewService interviewService) {
        this.hiringService = hiringService;
        this.interviewService = interviewService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(false);

        Integer numberOfHiring = Integer.parseInt(request.getParameter(NUMBER_OF_HIRING));
        try {
            try {
                List<Hiring> hiringList = hiringService.getAllHirings();
                Hiring hiring = hiringList.get(numberOfHiring);
                List<Interview> interviewList = interviewService.getAllInterviewByHiringId(hiring.getID());
                session.setAttribute(HIRINGS_INTERVIEW, interviewList);
                session.setAttribute(HIRING_ID, hiring.getID());
                request.getRequestDispatcher(PageName.WORK_WITH_INTERVIEW).include(request,response);
            } catch (ServiceException e) {
                request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
                LOGGER.error(e.getMessage());
                throw new CommandException(e);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}

