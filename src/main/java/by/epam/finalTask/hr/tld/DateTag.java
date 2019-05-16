package by.epam.finalTask.hr.tld;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(DateTag.class);

    private static final String RU_PATTERN = "dd.MM.yyyy";
    private Date date;

    public DateTag() {
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int doStartTag() throws JspException {
        String dateString = new SimpleDateFormat(RU_PATTERN).format(date);
        JspWriter out = pageContext.getOut();
        try {
            out.write(dateString);
        } catch (IOException e) {
            LOGGER.error(e);
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
