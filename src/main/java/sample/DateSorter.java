package sample;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;


/**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 * <p>
 * <p>
 * package sample;
 * <p>
 * import java.time.LocalDate;
 * import java.util.Collection;
 * import java.util.List;
 * <p>
 * /**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 * <p>
 * Implement in single class. Don't chane constructor and signature method.
 */
public class DateSorter {
    public static final String R = "r";

    // TEST DATA
    private static final List<LocalDate> LIST;

    static {
        LIST = new ArrayList<>();
        LIST.add(LocalDate.of(2004, 7, 1));
        LIST.add(LocalDate.of(2005, 1, 2));
        LIST.add(LocalDate.of(2007, 1, 1));
        LIST.add(LocalDate.of(2022, 2, 3));
        LIST.add(LocalDate.of(2012, 3, 3));
        LIST.add(LocalDate.of(2002, 4, 3));
        LIST.add(LocalDate.of(2072, 5, 3));
        LIST.add(LocalDate.of(2002, 6, 3));
        LIST.add(LocalDate.of(2009, 8, 3));
        LIST.add(LocalDate.of(2009, 9, 3));
        LIST.add(LocalDate.of(2009, 10, 3));
        LIST.add(LocalDate.of(2009, 11, 3));
        LIST.add(LocalDate.of(2009, 12, 3));
        LIST.add(LocalDate.of(2004, 7, 1));
        LIST.add(LocalDate.of(2005, 1, 2));
        LIST.add(LocalDate.of(2007, 1, 1));
        LIST.add(LocalDate.of(2022, 2, 3));
        LIST.add(LocalDate.of(2012, 3, 3));
        LIST.add(LocalDate.of(2045, 8, 3));
        LIST.add(LocalDate.of(2072, 5, 3));
        LIST.add(LocalDate.of(2002, 9, 3));
        LIST.add(LocalDate.of(2009, 8, 3));
        LIST.add(LocalDate.of(2079, 9, 3));
        LIST.add(LocalDate.of(2049, 1, 3));
        LIST.add(LocalDate.of(2019, 5, 3));
        LIST.add(LocalDate.of(2001, 12, 3));

    }

    public boolean ifContainsMonthCharacter(LocalDate sourceDate, String targetCharacter) {
        return sourceDate.getMonth().getDisplayName(TextStyle.FULL, Locale.US).contains(targetCharacter);
    }

    public List<LocalDate> getDatesIfMonthsContainsTargetCharacter(List<LocalDate> sourceList, String targetCharacter) {
        List<LocalDate> listDatesWithTargetCharacter = new ArrayList<>();
        for (LocalDate localDate : sourceList) {
            if (ifContainsMonthCharacter(localDate, targetCharacter)) {
                listDatesWithTargetCharacter.add(localDate);
            }
        }
        return listDatesWithTargetCharacter;
    }


    public List<LocalDate> getDatesIfMonthsDoesNotContainsTargetCharacter(List<LocalDate> sourceList, String targetCharacter) {
        List<LocalDate> listDatesWithoutTargetCharacter = new ArrayList<>();
        for (LocalDate localDate : sourceList) {
            if (!ifContainsMonthCharacter(localDate, targetCharacter)) {
                listDatesWithoutTargetCharacter.add(localDate);
            }
        }
        return listDatesWithoutTargetCharacter;
    }

    public List<LocalDate> sortLocalDateDescByMonth(List<LocalDate> list) {
        list.sort(Comparator.comparingInt(LocalDate::getMonthValue).reversed());
        return list;
    }

    public List<LocalDate> sortLocalDateAscByMonth(List<LocalDate> list) {
        list.sort(Comparator.comparingInt(LocalDate::getMonthValue));
        return list;
    }


    /**
     * The implementation of this method should sort dates.
     * The output should be in the following order:
     * Dates with an 'r' in the month,
     * sorted ascending (first to last),
     * then dates without an 'r' in the month,
     * sorted descending (last to first).
     * For example, October dates would come before May dates,
     * because October has an 'r' in it.
     * thus: (2004-07-01, 2005-01-02, 2007-01-01, 2032-05-03)
     * would sort to
     * (2005-01-02, 2007-01-01, 2032-05-03, 2004-07-01)
     *
     * @param unsortedDates - an unsorted list of dates
     * @return the collection of dates now sorted as per the spec
     */
    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        List<LocalDate> result = new ArrayList<>();
        List<LocalDate> ifMonthsContainsLetters = getDatesIfMonthsContainsTargetCharacter(unsortedDates, R);
        List<LocalDate> monthsDoesNotContainsLetters = getDatesIfMonthsDoesNotContainsTargetCharacter(unsortedDates, R);
        ifMonthsContainsLetters = sortLocalDateAscByMonth(ifMonthsContainsLetters);
        monthsDoesNotContainsLetters = sortLocalDateDescByMonth(monthsDoesNotContainsLetters);
        result.addAll(ifMonthsContainsLetters);
        result.addAll(monthsDoesNotContainsLetters);
        for (LocalDate localDate : result) {
            System.out.print(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + " ==== ");
            System.out.println(localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.US));
        }
        return result;
        // your solution here
    }

    public static void main(String[] args) {
        DateSorter dateSorter = new DateSorter();
        dateSorter.sortDates(LIST);
    }

}