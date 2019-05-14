package by.epam.finalTask.hr.factory;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.command.impl.*;
import by.epam.finalTask.hr.controller.helper.CommandName;
import by.epam.finalTask.hr.service.HiringService;
import by.epam.finalTask.hr.service.InterviewService;
import by.epam.finalTask.hr.service.UserService;
import by.epam.finalTask.hr.service.VacancyService;
import by.epam.finalTask.hr.util.Validator;

public class CommandFactory {
    private final ServiceFactory serviceFactory;
    private final LanguageFactory languageFactory;

    public CommandFactory(ServiceFactory serviceFactory, LanguageFactory languageFactory) {
        this.serviceFactory = serviceFactory;
        this.languageFactory = languageFactory;
    }

    public Command create(String commandString) throws CommandException {
        Validator validator = new Validator();
        CommandName commandName = CommandName.valueOf(validator.validateCommandString(commandString));
        Command command = null;
        UserService userService = serviceFactory.getUserService();
        VacancyService vacancyService = serviceFactory.getVacancyService();
        HiringService hiringService = serviceFactory.getHiringService();
        InterviewService interviewService = serviceFactory.getInterviewService();

        switch (commandName) {
            case AUTHORIZATION:{
                command = new AuthorizationCommand(userService, vacancyService, hiringService);
                break;
            }
            case REGISTRATION:{
                command = new RegistrationCommand(userService);
                break;
            }
            case CREATE_VACANCY:{
                command = new CreateVacancyCommand(vacancyService);
                break;
            }
            case DELETE_HIRING:{
                command = new DeleteHiringCommand(hiringService);
                break;
            }
            case EDIT_HIRING_BUTTON:{
                command = new EditHiringButtonCommand(hiringService);
                break;
            }
            case EDIT_HIRING:{
                command = new EditHiringCommand(userService, vacancyService, hiringService);
                break;
            }
            case CHANGE_LOCAL:{

                break;
            }
        }
        return command;
    }
}
