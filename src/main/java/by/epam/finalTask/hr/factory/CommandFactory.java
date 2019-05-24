package by.epam.finalTask.hr.factory;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.command.impl.AuthorizationCommand;
import by.epam.finalTask.hr.command.impl.RegistrationCommand;
import by.epam.finalTask.hr.command.impl.SetLocalCommand;
import by.epam.finalTask.hr.command.impl.admin.AddUserCommand;
import by.epam.finalTask.hr.command.impl.admin.BlockedUserCommand;
import by.epam.finalTask.hr.command.impl.admin.DeleteUserCommand;
import by.epam.finalTask.hr.command.impl.hr.*;
import by.epam.finalTask.hr.command.impl.user.AddHiringCommand;
import by.epam.finalTask.hr.command.impl.user.DeleteHiringByUserCommand;
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
            case AUTHORIZATION: {
                command = new AuthorizationCommand(userService, vacancyService, hiringService);
                break;
            }
            case REGISTRATION: {
                command = new RegistrationCommand(userService);
                break;
            }
            case CREATE_VACANCY: {
                command = new CreateVacancyCommand(vacancyService);
                break;
            }
            case DELETE_HIRING_BY_HR: {
                command = new DeleteHiringByHrCommand(hiringService);
                break;
            }
            case EDIT_HIRING_BUTTON: {
                command = new EditHiringButtonCommand(hiringService);
                break;
            }
            case EDIT_HIRING: {
                command = new EditHiringCommand(userService, vacancyService, hiringService);
                break;
            }
            case TABLE_HIRING: {
                command = new TableHiringCommand(hiringService, interviewService);
                break;
            }
            case DELETE_INTERVIEW: {
                command = new DeleteInterviewCommand(interviewService);
                break;
            }
            case ADD_INTERVIEW: {
                command = new AddInterviewCommand(interviewService);
                break;
            }
            case ADD_USER: {
                command = new AddUserCommand(userService);
                break;
            }
            case DELETE_USER: {
                command = new DeleteUserCommand(userService);
                break;
            }
            case ADD_HIRING: {
                command = new AddHiringCommand(hiringService, vacancyService, userService);
                break;
            }
            case DELETE_HIRING_BY_USER: {
                command = new DeleteHiringByUserCommand(hiringService);
                break;
            }
            case CHANGE_LOCAL: {
                command = new SetLocalCommand(languageFactory);
                break;
            }
            case BLOCKED_USER:{
                command = new BlockedUserCommand(userService);
                break;
            }
            case EDIT_VACANCY:{
                command = new EditVacancyCommand(vacancyService);
                break;
            }
            case EDIT_VACANCY_BUTTON: {
                command = new EditVacancyButtonCommand();
                break;
            }
        }
        return command;
    }
}
