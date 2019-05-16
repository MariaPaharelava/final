package by.epam.finalTask.hr.service.impl;

import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import by.epam.finalTask.hr.dao.impl.InterviewDAO;
import by.epam.finalTask.hr.entity.Interview;
import by.epam.finalTask.hr.service.InterviewService;
import by.epam.finalTask.hr.service.exception.StringTooLongException;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

public class InterviewServiceImpl implements InterviewService {
    private static final int MAX_LENGTH = 255;
    private static final Logger LOGGER = LogManager.getLogger(InterviewServiceImpl.class);
    private final InterviewDAO interviewDAO;

    public InterviewServiceImpl(InterviewDAO interviewDAO) {
        this.interviewDAO = interviewDAO;
    }


    @Override
    public void addInterview(String comment, String type, String result, int hiring_id) throws ServiceException {
        try {
            if (result != null && result.length() > MAX_LENGTH) {
                LOGGER.warn("String too long");
                throw new StringTooLongException("String too long");
            }
            if (comment != null && comment.length() > MAX_LENGTH) {
                LOGGER.warn("String too long");
                throw new StringTooLongException("String too long");
            }
            Interview interview = new Interview(hiring_id, type, comment, result);
            ((InterviewDAO) interviewDAO).save(interview);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void getAllInterview(Date date, String comment, String type, String result, int hiring_id) throws ServiceException {
        try {
            ((InterviewDAO) interviewDAO).findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public List<Interview> getAllInterviewByHiringId(Integer id) throws ServiceException {
        try {
            return ((InterviewDAO) interviewDAO).findEntityByHiringId(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteInterview(Integer id) throws ServiceException {
        try {
            ((InterviewDAO) interviewDAO).delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


}
