package by.epam.finalTask.hr.filter;

import javax.servlet.*;
import java.io.IOException;

public class LanguageFilter implements Filter {
    private final static String ENCODING = "encoding";
    private String code;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter(ENCODING);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        // установка кодировки из параметров фильтра, если не установлена
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
