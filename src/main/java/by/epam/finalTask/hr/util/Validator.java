package by.epam.finalTask.hr.util;

import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.entity.Hiring;
import by.epam.finalTask.hr.entity.User;
import by.epam.finalTask.hr.entity.Vacancy;
import by.epam.finalTask.hr.service.UserService;
import by.epam.finalTask.hr.service.VacancyService;
import by.epam.finalTask.hr.service.exception.StringTooLongException;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Validator {
    private static final Logger LOGGER = LogManager.getLogger(Validator.class);
    private static final int MAX_LENGTH = 255;

    public String validateCommandString(String name) throws CommandException {
        if (name == null || name.isEmpty()) {
            LOGGER.error("Error in command name");
            throw new CommandException("Error in command name");
        }
        return validateFromLowerCaseToUpperCase(name);
    }

    public String validateFromLowerCaseToUpperCase(String string) {
        string = string.replace('-', '_');
        return string.toUpperCase();
    }

    public String validateFromUpperCaseToLowerCase(String string) {
        string = string.replace('_', '-');
        return string.toLowerCase();
    }

    public String validateFromUpperCaseToLowerCaseForDB(String string) {
        return string.toLowerCase();
    }

    public HiringForShow validateFromHiringToHiringForShow(Hiring aHiringList, VacancyService vacancyService,
                                                           UserService userService) throws ServiceException {
        User candidate = userService.findById(aHiringList.getCandidateId());
        User hr = userService.findById(aHiringList.getHrId());
        Vacancy vacancy = vacancyService.findById(aHiringList.getVacancyId());
        return new HiringForShow(hr.getName(), hr.getSurname(), candidate.getName(),
                candidate.getSurname(), vacancy.getVacancyPosition(), aHiringList.getOfferEmount(),
                aHiringList.getComment(), aHiringList.getHiringStatus());
    }

    public void integerInformationIsNotNull(Integer ... integerValues) throws StringTooLongException {
        for (Integer integerValue : integerValues) {
            if (integerValue == 0) {
                LOGGER.warn("One of string are empty");
                throw new StringTooLongException("One of string are empty");
            }
        }
    }

    public void stringInformationIsNotNullAndNotMuchMoreMaxLength(String ... strings) throws StringTooLongException {
        for (String string : strings) {
            if (string != null && string.length() > MAX_LENGTH) {
                LOGGER.warn("String too long");
                throw new StringTooLongException("String too long");
            }
        }
    }

    public void stringInformationIsBetweenNullAndNotMuchMoreMaxLength(String ... strings) throws StringTooLongException {
        for (String string : strings) {
            if (string.equals("")) {
                LOGGER.warn("One of string are empty");
                throw new StringTooLongException("One of string are empty");
            }
        }
        stringInformationIsNotNullAndNotMuchMoreMaxLength(strings);
    }
}
