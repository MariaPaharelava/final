package by.epam.finalTask.hr.factory;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.command.impl.AuthorizationCommand;
import by.epam.finalTask.hr.command.impl.RegistrationCommand;
import by.epam.finalTask.hr.controller.helper.CommandName;
import by.epam.finalTask.hr.service.UserService;
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
        switch (commandName) {
            case AUTHORIZATION:{
                UserService userService = serviceFactory.getUserService();
                command = new AuthorizationCommand(userService);
                break;
            }
            case REGISTRATION:{
                UserService userService = serviceFactory.getUserService();
                command = new RegistrationCommand(userService);
                break;
            }
            case CHANGE_LOCAL:{
                break;
            }
        }
        return command;
    }
}
