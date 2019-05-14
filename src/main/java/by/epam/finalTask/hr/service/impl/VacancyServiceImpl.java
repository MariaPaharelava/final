package by.epam.finalTask.hr.service.impl;

import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import by.epam.finalTask.hr.dao.impl.VacancyDAO;
import by.epam.finalTask.hr.entity.Vacancy;
import by.epam.finalTask.hr.service.VacancyService;
import com.google.protobuf.ServiceException;

import java.util.List;
import java.util.Optional;

public class VacancyServiceImpl implements VacancyService {
    private final VacancyDAO vacancyDAO;
    private Optional<Vacancy> vacancyOptional;

    public VacancyServiceImpl(VacancyDAO vacancyDAO){
        this.vacancyDAO = vacancyDAO;
    }

    @Override
    public Vacancy addVacancy(String name, String description, int hr_id) throws ServiceException {
        Vacancy vacancy = null;
        try {
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
            return  ((VacancyDAO) vacancyDAO).findAll();
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
