package by.epam.finalTask.hr.command.impl.hr;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.controller.helper.PageName;
import by.epam.finalTask.hr.entity.Interview;
import by.epam.finalTask.hr.service.InterviewService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AddInterviewCommand implements Command {
    private static final String ENTER_TYPE = "enterType";
    private static final String ENTER_RESULT = "enterResult";
    private static final String ENTER_COMMENT = "enterComment";
    private static final String HIRINGS_INTERVIEW = "hiringsInterview";
    private static final String HIRING_ID = "hiringID";
    private static final Logger LOGGER = LogManager.getLogger(AddInterviewCommand.class);
    private InterviewService interviewService;

    public AddInterviewCommand(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);

        String type = request.getParameter(ENTER_TYPE);
        String result = request.getParameter(ENTER_RESULT);
        String comment = request.getParameter(ENTER_COMMENT);
        Integer numberOfHiring = (Integer) session.getAttribute(HIRING_ID);
        session.removeAttribute(HIRING_ID);

        Interview interview = new Interview(numberOfHiring, type, result, comment);
        addInterviewToDB(numberOfHiring, interview);
        addInterviewToSession(session, interview);
        return PageName.WORK_WITH_INTERVIEW;

    }

    private void addInterviewToSession(HttpSession session, Interview interview) {
        List<Interview> interviewArrayList = (ArrayList<Interview>) session.getAttribute(HIRINGS_INTERVIEW);
        Date data = new Date(System.currentTimeMillis());
        interview.setInterviewDate(data);
        interviewArrayList.add(interview);
        session.removeAttribute(HIRINGS_INTERVIEW);
        session.setAttribute(HIRINGS_INTERVIEW, interviewArrayList);
    }

    private void addInterviewToDB(Integer numberOfHiring, Interview interview) throws ServiceException {
        interviewService.addInterview(interview.getComment(), interview.getInterviewType().toString(),
                interview.getResult(), numberOfHiring);
    }
}
