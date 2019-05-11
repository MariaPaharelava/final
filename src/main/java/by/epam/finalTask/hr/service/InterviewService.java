package by.epam.finalTask.hr.service;

import com.google.protobuf.ServiceException;

import java.util.Date;

public interface InterviewService {
    void deleteInterview(Integer id) throws ServiceException;
    void addInterview(Date date, String comment, String type, String result, int hiring_id) throws ServiceException;
    void getAllInterview(Date date, String comment, String type, String result, int hiring_id) throws ServiceException;
    void getAllInterviewByHiringId(Integer id) throws ServiceException;
}
