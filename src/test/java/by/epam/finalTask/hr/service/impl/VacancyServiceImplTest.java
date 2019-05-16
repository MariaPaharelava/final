package by.epam.finalTask.hr.service.impl;

import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;
import by.epam.finalTask.hr.dao.impl.VacancyDAO;
import by.epam.finalTask.hr.entity.Vacancy;
import by.epam.finalTask.hr.service.VacancyService;
import com.google.protobuf.ServiceException;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class VacancyServiceImplTest {
    private static final VacancyDAO EMPTY_VACANCY_DAO_MOCK = mock(VacancyDAO.class);
    private final VacancyService vacancyServiceWithEmptyMocks = new VacancyServiceImpl(EMPTY_VACANCY_DAO_MOCK);
    private static final Vacancy CORRECT_VACANCY = new Vacancy(
            "position",
            "description",
            1
    );
    private static final Vacancy EMPTY_POSITION = new Vacancy(
            "",
            "description",
            1
    );
    private static final Vacancy LONG_DESCRIPTION = new Vacancy(
            "position",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            1
    );

    @Test
    public void addVacancyShouldSaveCorrectUser() throws ServiceException, DAOException {
        vacancyServiceWithEmptyMocks.addVacancy(CORRECT_VACANCY.getVacancyPosition(),
                CORRECT_VACANCY.getVacancyDescrintion(), CORRECT_VACANCY.getUserId());
        verify(EMPTY_VACANCY_DAO_MOCK).save(CORRECT_VACANCY);
        verifyNoMoreInteractions(EMPTY_VACANCY_DAO_MOCK);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void addVacancyShouldThrowServiceExceptionWhenFilmWithEmptyPositionSupplied() throws ServiceException, DAOException {
        vacancyServiceWithEmptyMocks.addVacancy(EMPTY_POSITION.getVacancyPosition(),
                EMPTY_POSITION.getVacancyDescrintion(), EMPTY_POSITION.getUserId());
        verify(EMPTY_VACANCY_DAO_MOCK).save(EMPTY_POSITION);
        verifyNoMoreInteractions(EMPTY_VACANCY_DAO_MOCK);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void addVacancyShouldThrowServiceExceptionWhenFilmWithLongDescriptionSupplied() throws ServiceException, DAOException {
        vacancyServiceWithEmptyMocks.addVacancy(LONG_DESCRIPTION.getVacancyPosition(),
                LONG_DESCRIPTION.getVacancyDescrintion(), LONG_DESCRIPTION.getUserId());
        verify(EMPTY_VACANCY_DAO_MOCK).save(LONG_DESCRIPTION);
        verifyNoMoreInteractions(EMPTY_VACANCY_DAO_MOCK);
    }
}