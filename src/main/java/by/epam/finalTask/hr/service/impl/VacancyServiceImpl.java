package by.epam.finalTask.hr.service.impl;

import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import by.epam.finalTask.hr.dao.impl.VacancyDAO;
import by.epam.finalTask.hr.entity.Vacancy;
import by.epam.finalTask.hr.service.VacancyService;
import by.epam.finalTask.hr.service.exception.VacancyAlreadyExistsException;
import com.google.protobuf.ServiceException;

import java.util.List;
import java.util.Optional;

public class VacancyServiceImpl implements VacancyService {
    private final VacancyDAO vacancyDAO;
    private Optional<Vacancy> vacancyOptional;

    VacancyServiceImpl(VacancyDAO vacancyDAO){
        this.vacancyDAO = vacancyDAO;
    }

    @Override
    public Vacancy addVacancy(String name, String description, int hr_id) throws ServiceException {
        Vacancy vacancy = null;
        try {
            vacancyOptional = ((VacancyDAO) vacancyDAO).findEntityByEntity( name, description, hr_id);
            if(vacancyOptional.isPresent()){
                throw new VacancyAlreadyExistsException("This vacancy is exist");
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
            return  ((VacancyDAO) vacancyDAO).findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
