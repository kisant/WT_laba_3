package service;

import bean.Edition;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Edition comparator provider.
 */
public final class EditionComparatorProvider {

    private static final EditionComparatorProvider instance = new EditionComparatorProvider();

    private static final Comparator<Edition> SORT_EDITION_BY_ID = Comparator.comparing(Edition::getId);
    private static final Comparator<Edition> SORT_EDITION_BY_TITLE_LENGTH = Comparator.comparingInt(o -> o.getTitle().length());

    private final Map<EditionComparatorName, Comparator<Edition>> repository = new HashMap<>();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static EditionComparatorProvider getInstance() {
        return instance;
    }

    private EditionComparatorProvider() {
        repository.put(EditionComparatorName.SORT_EDITION_BY_ID, SORT_EDITION_BY_ID);
        repository.put(EditionComparatorName.SORT_EDITION_BY_TITLE_LENGTH, SORT_EDITION_BY_TITLE_LENGTH);
    }

    /**
     * Gets comparator.
     *
     * @param name the name
     * @return the comparator
     */
    public Comparator<Edition> getComparator(String name) {
        EditionComparatorName comparatorName;
        Comparator<Edition> comparator;
        try {
            comparatorName = EditionComparatorName.valueOf(name.toUpperCase());
            comparator = repository.get(comparatorName);
        } catch (IllegalArgumentException | NullPointerException e) {
            comparator = null;
        }
        return comparator;
    }

}