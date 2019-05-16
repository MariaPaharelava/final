package by.epam.finalTask.hr.service.impl;

import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import by.epam.finalTask.hr.dao.impl.HiringDAO;
import by.epam.finalTask.hr.entity.Hiring;
import by.epam.finalTask.hr.service.HiringService;
import by.epam.finalTask.hr.service.exception.StringTooLongException;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class HiringServiceImpl implements HiringService {
    private static final int MAX_LENGTH = 255;
    private static final Logger LOGGER = LogManager.getLogger(HiringServiceImpl.class);
    private final HiringDAO hiringDAO;
    private Hiring hiring;

    public HiringServiceImpl(HiringDAO hiringDAO) {
        this.hiringDAO = hiringDAO;
    }

    @Override
    public void addHiring(Integer candidateId, Integer hrId, Integer vacancyId) throws ServiceException {
        try {
            if (candidateId == 0 || hrId == 0 || vacancyId == 0) {
                LOGGER.warn("One of string are empty");
                throw new StringTooLongException("One of string are empty");
            }

            hiring = new Hiring(hrId, candidateId, vacancyId);
            ((HiringDAO) hiringDAO).save(hiring);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteHiring(Integer id) throws ServiceException {
        try {
            hiringDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Hiring changeHiring(Integer hiringId, double salary, String status, String comment) throws ServiceException {
        try {
            if (status != null && status.length() > MAX_LENGTH) {
                LOGGER.warn("String too long");
                throw new StringTooLongException("String too long");
            }
            if (comment != null && comment.length() > MAX_LENGTH) {
                LOGGER.warn("String too long");
                throw new StringTooLongException("String too long");
            }
            Optional<Hiring> hiringOptional = ((HiringDAO) hiringDAO).findEntityById(hiringId);
            hiring = hiringOptional.get();
            hiring.setHiringStatus(status);
            hiring.setComment(comment);
            hiring.setOfferEmount(salary);
            ((HiringDAO) hiringDAO).save(hiring);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return hiring;
    }

    @Override
    public List<Hiring> getAllHirings() throws ServiceException {
        try {
            return ((HiringDAO) hiringDAO).findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Hiring> getAllHiringsByHrId(Integer id) throws ServiceException {
        try {
            return ((HiringDAO) hiringDAO).findAllBYUserId(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Hiring> getAllHiringsByUserId(Integer id) throws ServiceException {
        try {
            return ((HiringDAO) hiringDAO).findAllByHrId(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
