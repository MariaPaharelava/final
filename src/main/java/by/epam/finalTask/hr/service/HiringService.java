package by.epam.finalTask.hr.service;

import by.epam.finalTask.hr.entity.Hiring;
import com.google.protobuf.ServiceException;

import java.util.List;

public interface HiringService {
    void addHiring(Integer candidateId, Integer hrId, Integer vacancyId) throws ServiceException;
    void deleteHiring(Integer id) throws ServiceException;
    Hiring changeHiring(Integer hiringId, double salary, String status, String comment) throws ServiceException;
    List<Hiring> getAllHirings() throws ServiceException;
}
