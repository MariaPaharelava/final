package by.epam.finalTask.hr.command.impl;

import by.epam.finalTask.hr.command.Command;
import by.epam.finalTask.hr.command.exception.CommandException;
import by.epam.finalTask.hr.entity.enums.Language;
import by.epam.finalTask.hr.factory.LanguageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SetLocalCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SetLocalCommand.class);

    private static final String LANGUAGE_ATTRIBUTE = "local";
    private static final String PAGE_NAME = "pageName";
    private final LanguageFactory languageFactory;

    public SetLocalCommand(LanguageFactory languageFactory) {
        this.languageFactory = languageFactory;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String languageString = request.getParameter(LANGUAGE_ATTRIBUTE);
        Language language = languageFactory.getLanguage(languageString);
        try {
            HttpSession session = request.getSession();
            session.setAttribute(LANGUAGE_ATTRIBUTE, language.name().toLowerCase());
            String pageName = (String) session.getAttribute(PAGE_NAME);
            response.sendRedirect(pageName);
            LOGGER.info("Language was installed");
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            throw new CommandException(e);
        }
    }
}
