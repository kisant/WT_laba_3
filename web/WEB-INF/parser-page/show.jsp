<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    PrintWriter w = response.getWriter();
    StringBuilder builder = new StringBuilder();
    builder.append("<div><button onclick=\"location.href='/library_war_exploded'\">Back</button>");
    builder.append("<table class=\"striped\">");
    builder.append("<thead>");
    builder.append("<tr>");
    builder.append("<table class=\"striped\">");
    builder.append("<th>ID</th>");
    builder.append("<th>TITLE</th>");
    builder.append("<th>EDITION-TYPE</th>");
    builder.append("<th>LIST-FORMAT</th>");
    builder.append("<th>LIST-COUNT</th>");
    builder.append("<th>PUBLICATION-YEAR</th>");
    builder.append("<th>NEWSPAPER-TYPE</th>");
    builder.append("<th>ENCYCLOPEDIA-TYPE</th>");
    builder.append("<th>GENRE</th>");
    builder.append("</tr>");
    builder.append("</thead>");
    builder.append("<tbody>");

    List<Edition> editions = (List<Edition>) request.getAttribute("editions");
    for (Edition edition : editions) {
        builder.append("<tr>");
        builder.append("<td>");
        builder.append(edition.getId());
        builder.append("</td>");

        builder.append("<td>");
        builder.append(edition.getTitle());
        builder.append("</td>");

        builder.append("<td>");
        builder.append(edition.getEditionType());
        builder.append("</td>");

        if (edition instanceof PrintEdition) {
            PrintEdition printEdition = (PrintEdition) edition;

            builder.append("<td>");
            builder.append(printEdition.getListFormat());
            builder.append("</td>");

            builder.append("<td>");
            builder.append(printEdition.getListCount());
            builder.append("</td>");

            builder.append("<td>");
            builder.append(printEdition.getPublicationYear());
            builder.append("</td>");
        }

        switch (edition.getEditionType()) {
            case PRINT_EDITION:
                break;
            case ENCYCLOPEDIA:
                if (edition instanceof Encyclopedia) {
                    Encyclopedia encyclopedia = (Encyclopedia) edition;
                    builder.append("<td>");
                    builder.append(encyclopedia.getEncyclopediaType());
                    builder.append("</td>");
                }

                break;
            case NEWSPAPER:
                if (edition instanceof Newspaper) {
                    Newspaper newspaper = (Newspaper) edition;
                    builder.append("<td>");
                    builder.append(newspaper.getNewspaperType());
                    builder.append("</td>");
                }
                break;
            case BOOK:
                if (edition instanceof Book) {
                    Book book = (Book) edition;
                    builder.append("<td>");
                    builder.append(book.getGenre());
                    builder.append("</td>");
                }
                break;
        }
        builder.append("<tr>");
    }
    builder.append("</tbody>");
    builder.append("</table>");
    w.println(builder.toString());
%>
</body>
</html>
