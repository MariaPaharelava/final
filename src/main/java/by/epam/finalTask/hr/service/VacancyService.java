package by.epam.finalTask.hr.service;

import by.epam.finalTask.hr.entity.Vacancy;
import com.google.protobuf.ServiceException;

import java.util.List;

public interface VacancyService {
    Vacancy addVacancy(String name, String description, int hr_id) throws ServiceException;

    void removeVacancyById(int id) throws ServiceException;

    void changeVacancyById(int id, String position, String description) throws ServiceException;

    List<Vacancy> getAllVacancies() throws ServiceException;

    Vacancy findById(Integer id) throws ServiceException;

    Vacancy findByPositionAndDescription(String position,
                                         String description) throws ServiceException;
}
