package by.epam.finalTask.hr.filter;

import javax.servlet.*;
import java.io.IOException;

public class LanguageFilter implements Filter {
    private static final String ENCODING_DEFAULT = "UTF-8";
    private final static String ENCODING = "encoding";
    private static final String FILTERABLE_CONTENT_TYPE="application/x-www-form-urlencoded";
    private String code;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter(ENCODING);
        if (code == null){
            code = ENCODING_DEFAULT;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        String contentType = request.getContentType();
        // установка кодировки из параметров фильтра, если не установлена
        if (code != null && !code.equalsIgnoreCase(codeRequest) && contentType != null && contentType.startsWith(FILTERABLE_CONTENT_TYPE)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
