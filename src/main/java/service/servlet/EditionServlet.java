package service.servlet;

import dao.Editions;
import dao.parser.XmlParser;
import dao.parser.exception.ParserException;
import dao.parser.factory.ParserFactory;
import dao.parser.validator.impl.XmlValidatorByXsd;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditionServlet extends HttpServlet {

    private static final String PARSER_TYPE_PARAMETER_NAME = "parser";
    private static final String JSP_FILE_PATH = "/WEB-INF/parser-page/show.jsp";
    private static final String EDITION_XSD_PATH = "D:\\library\\src\\main\\resources\\editions.xsd";
    private static final String EDITION_XML_PATH = "D:\\library\\src\\main\\resources\\editions.xml";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parserName = req.getParameter(PARSER_TYPE_PARAMETER_NAME);

        ParserFactory parserFactory = ParserFactory.getInstance();

        try {
            XmlParser<Editions> parser = parserFactory.newParser(parserName, new XmlValidatorByXsd(EDITION_XSD_PATH));
            Editions editions = parser.parse(EDITION_XML_PATH);
            List<String> a = new ArrayList<>();
            a.add("a");
            a.add("b");
            a.add("c");
            req.setAttribute("editions", editions.getEditions());
            req.setAttribute("a", a);
        } catch (ParserException e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(JSP_FILE_PATH);
        requestDispatcher.forward(req, resp);
    }

}