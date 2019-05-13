package by.epam.finalTask.hr.service;

import by.epam.finalTask.hr.entity.Vacancy;
import com.google.protobuf.ServiceException;

import java.util.List;
import java.util.Optional;

public interface VacancyService {
    Vacancy addVacancy(String name, String description, int hr_id) throws ServiceException;
    void removeVacancy(int id) throws ServiceException;
    List<Vacancy> getAllVacancies() throws ServiceException;
    Vacancy findById(Integer id) throws ServiceException;
}
