package by.epam.finalTask.hr.service.impl;

import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import by.epam.finalTask.hr.dao.impl.VacancyDAO;
import by.epam.finalTask.hr.entity.Vacancy;
import by.epam.finalTask.hr.service.VacancyService;
import by.epam.finalTask.hr.util.Validator;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class VacancyServiceImpl implements VacancyService {
    private static final Logger LOGGER = LogManager.getLogger(VacancyServiceImpl.class);
    private final VacancyDAO vacancyDAO;
    private Optional<Vacancy> vacancyOptional;
    private Validator validator = new Validator();


    public VacancyServiceImpl(VacancyDAO vacancyDAO) {
        this.vacancyDAO = vacancyDAO;
    }

    @Override
    public Vacancy addVacancy(String name, String description, int hr_id) throws ServiceException {
        Vacancy vacancy = null;
        try {
            validator.stringInformationIsBetweenNullAndNotMuchMoreMaxLength(description, name);
            validator.integerInformationIsNotNull(hr_id);
            vacancy = new Vacancy(description, name, hr_id);
            vacancyDAO.save(vacancy);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return vacancy;
    }

    @Override
    public void removeVacancyById(int id) throws ServiceException {
        try {
            vacancyDAO.delete(id);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeVacancyById(int id, String position, String description) throws ServiceException {
        try {
            vacancyOptional = vacancyDAO.findEntityById(id);
            Vacancy vacancy = vacancyOptional.get();
            vacancy.setVacancyPosition(position);
            vacancy.setVacancyDescrintion(description);
            vacancyDAO.save(vacancy);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> getAllVacancies() throws ServiceException {
        try {
            return vacancyDAO.findAll();
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Vacancy findById(Integer id) throws ServiceException {
        Vacancy vacancy = null;
        try {
            vacancyOptional = (vacancyDAO).findEntityById(id);
            if (vacancyOptional.isPresent()) {
                vacancy = vacancyOptional.get();
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return vacancy;
    }

    @Override
    public Vacancy findByPositionAndDescription(String position,
                                                String description) throws ServiceException {
        Vacancy vacancy = null;
        try {
            vacancyOptional = vacancyDAO.findEntityByPositionAndDescription(position, description);
            if (vacancyOptional.isPresent()) {
                vacancy = vacancyOptional.get();
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
        return vacancy;
    }
}
