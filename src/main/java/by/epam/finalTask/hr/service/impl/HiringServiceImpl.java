package by.epam.finalTask.hr.service.impl;

import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import by.epam.finalTask.hr.dao.impl.HiringDAO;
import by.epam.finalTask.hr.entity.Hiring;
import by.epam.finalTask.hr.service.HiringService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class HiringServiceImpl implements HiringService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private final HiringDAO hiringDAO;
    private Hiring hiring;

    public HiringServiceImpl(HiringDAO hiringDAO) {
        this.hiringDAO = hiringDAO;
    }

    @Override
    public void addHiring(Integer candidateId, Integer hrId, Integer vacancyId) throws ServiceException {
        try {
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
