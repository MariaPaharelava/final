package by.epam.finalTask.hr.util;

import by.epam.finalTask.hr.command.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Validator {
    private static final Logger LOGGER = LogManager.getLogger(Validator.class);

    public String validateCommandString(String name) throws CommandException {
        if (name == null || name.isEmpty()) {
            LOGGER.error("Error in command name");
            throw new CommandException("Error in command name");
        }
        name = name.replace('-', '_');
        return name.toUpperCase();
    }
}
