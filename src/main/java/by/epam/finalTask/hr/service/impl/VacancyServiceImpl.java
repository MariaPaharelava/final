package by.epam.finalTask.hr.service.impl;

import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import by.epam.finalTask.hr.dao.impl.VacancyDAO;
import by.epam.finalTask.hr.entity.Vacancy;
import by.epam.finalTask.hr.service.VacancyService;
import by.epam.finalTask.hr.service.exception.StringTooLongException;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class VacancyServiceImpl implements VacancyService {
    private static final int MAX_LENGTH = 255;
    private static final Logger LOGGER = LogManager.getLogger(VacancyServiceImpl.class);
    private final VacancyDAO vacancyDAO;
    private Optional<Vacancy> vacancyOptional;

    public VacancyServiceImpl(VacancyDAO vacancyDAO) {
        this.vacancyDAO = vacancyDAO;
    }

    @Override
    public Vacancy addVacancy(String name, String description, int hr_id) throws ServiceException {
        Vacancy vacancy = null;
        try {
            if (description.equals("") || name.equals("") || hr_id == 0) {
                LOGGER.warn("One of string are empty");
                throw new StringTooLongException("One of string are empty");
            }
            if (description.length() > MAX_LENGTH ||
                    name.length() > MAX_LENGTH || hr_id > MAX_LENGTH) {
                LOGGER.warn("String too long");
                throw new StringTooLongException("String too long");
            }
            vacancy = new Vacancy(description, name, hr_id);
            ((VacancyDAO) vacancyDAO).save(vacancy);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return vacancy;
    }

    @Override
    public void removeVacancy(int id) throws ServiceException {
        try {
            ((VacancyDAO) vacancyDAO).delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vacancy> getAllVacancies() throws ServiceException {
        try {
            return ((VacancyDAO) vacancyDAO).findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Vacancy findById(Integer id) throws ServiceException {
        Vacancy vacancy = null;
        try {
            vacancyOptional = ((VacancyDAO) vacancyDAO).findEntityById(id);
            if (vacancyOptional.isPresent()) {
                vacancy = vacancyOptional.get();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return vacancy;
    }

    @Override
    public Vacancy findByPositionAndDescription(String position,
                                                String description) throws ServiceException {
        Vacancy vacancy = null;
        try {
            vacancyOptional = ((VacancyDAO) vacancyDAO).findEntityByPositionAndDescription(position, description);
            if (vacancyOptional.isPresent()) {
                vacancy = vacancyOptional.get();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return vacancy;
    }
}
