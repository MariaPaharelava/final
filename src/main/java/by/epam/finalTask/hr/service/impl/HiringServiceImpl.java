package by.epam.finalTask.hr.service.impl;

import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import by.epam.finalTask.hr.dao.impl.HiringDAO;
import by.epam.finalTask.hr.entity.Hiring;
import by.epam.finalTask.hr.service.HiringService;
import by.epam.finalTask.hr.service.exception.StringTooLongException;
import by.epam.finalTask.hr.util.Validator;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class HiringServiceImpl implements HiringService {
    private static final Logger LOGGER = LogManager.getLogger(HiringServiceImpl.class);
    private final HiringDAO hiringDAO;
    private Hiring hiring;
    private Validator validator = new Validator();

    public HiringServiceImpl(HiringDAO hiringDAO) {
        this.hiringDAO = hiringDAO;
    }

    @Override
    public void addHiring(Integer candidateId, Integer hrId, Integer vacancyId) throws ServiceException {
        try {
            validator.integerInformationIsNotNull(candidateId, hrId, vacancyId);
            hiring = new Hiring(hrId, candidateId, vacancyId);
            hiringDAO.save(hiring);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteHiring(Integer id) throws ServiceException {
        try {
            hiringDAO.delete(id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Hiring changeHiring(Integer hiringId, double salary, String status, String comment) throws ServiceException {
        try {
            validator.stringInformationIsNotNullAndNotMuchMoreMaxLength(status, comment);
            Optional<Hiring> hiringOptional = hiringDAO.findEntityById(hiringId);
            hiring = hiringOptional.get();
            hiring.setHiringStatus(status);
            hiring.setComment(comment);
            hiring.setOfferEmount(salary);
            hiringDAO.save(hiring);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return hiring;
    }

    @Override
    public List<Hiring> getAllHirings() throws ServiceException {
        try {
            return hiringDAO.findAll();
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Hiring> getAllHiringsByHrId(Integer id) throws ServiceException {
        try {
            return hiringDAO.findAllByHrId(id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Hiring> getAllHiringsByUserId(Integer id) throws ServiceException {
        try {
            return hiringDAO.findAllBYUserId(id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }
}
