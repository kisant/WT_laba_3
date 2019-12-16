package controller.command.impl;

import bean.*;
import bean.enums.*;
import controller.command.Command;

/**
 * The type Edition creator.
 */
class EditionCreator {

    /**
     * Value of edition.
     *
     * @param s the s
     * @return the edition
     * @throws IllegalArgumentException the illegal argument exception
     */
    static Edition valueOf(String s) throws IllegalArgumentException {
        int indexStart = s.indexOf(Command.paramDelimiter) + 1;
        int indexEnd = s.indexOf(Command.paramDelimiter, indexStart);
        EditionType type = EditionType.valueOf(s.substring(indexStart, indexEnd).toUpperCase());

        indexStart = indexEnd + 1;
        indexEnd = s.indexOf(Command.paramDelimiter, indexStart);
        String id = s.substring(indexStart, indexEnd);

        indexStart = indexEnd + 1;
        indexEnd = s.indexOf(Command.paramDelimiter, indexStart);
        String title = s.substring(indexStart, indexEnd);

        indexStart = indexEnd + 1;
        indexEnd = s.indexOf(Command.paramDelimiter, indexStart);
        ListFormat listFormat = ListFormat.valueOf(s.substring(indexStart, indexEnd).toUpperCase());

        indexStart = indexEnd + 1;
        indexEnd = s.indexOf(Command.paramDelimiter, indexStart);
        int listCount = Integer.valueOf(s.substring(indexStart, indexEnd));

        indexStart = indexEnd + 1;
        indexEnd = s.indexOf(Command.paramDelimiter, indexStart);
        int publicationYear = Integer.valueOf(s.substring(indexStart, indexEnd));

        Edition edition;
        switch (type) {
            case PRINT_EDITION:
                edition = new PrintEdition(type, id, title, listFormat, listCount, publicationYear);
                break;
            case BOOK:
                Genre genre = Genre.valueOf(s.substring(indexEnd + 1).toUpperCase());
                edition = new Book(type, id, title, listFormat, listCount, publicationYear, genre);
                break;
            case NEWSPAPER:
                NewspaperType newspaperType = NewspaperType.valueOf(s.substring(indexEnd + 1).toUpperCase());
                edition = new Newspaper(type, id, title, listFormat, listCount, publicationYear, newspaperType);
                break;
            case ENCYCLOPEDIA:
                EncyclopediaType encyclopediaType = EncyclopediaType.valueOf(s.substring(indexEnd + 1).toUpperCase());
                edition = new Encyclopedia(type, id, title, listFormat, listCount, publicationYear, encyclopediaType);
                break;
            default:
                edition = null;
        }

        return edition;
    }

}