package by.epam.finalTask.hr.tld;

import by.epam.finalTask.hr.entity.enums.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(DateTag.class);

    private static final String LANGUAGE_ATTRIBUTE = "local";
    private static final String EN_PATTERN = "MM/dd/yyyy";
    private static final String RU_PATTERN = "dd.MM.yyyy";

    private Date date;
    private String lang;

    public DateTag() {
        System.out.println("werthi");
    }

    public void setDate(Date date) {
        System.out.println("werthi");
        this.date = date;
    }

    public void setLang(String lang) {
        System.out.println("werthi2");
        this.lang = lang;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        System.out.println(lang);
        Language language = Language.valueOf(lang.toUpperCase());
        Locale locale = Locale.forLanguageTag(language.name());
        System.out.println(language);
        String pattern = null;
        switch (language) {
            case RU: {
                pattern = RU_PATTERN;
                break;
            }
            case EN: {
                pattern = EN_PATTERN;
                break;
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(pattern)
                .withLocale(locale);

        //String formattedDate = date.format(formatter);
        JspWriter out = pageContext.getOut();
        try {
            out.write("formattedDate");
        } catch (IOException e) {
            LOGGER.error(e);
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}

