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
    /**
     * A constant string representing "r".
     * This string is used as a parameter in the task.
     */
    public static final String R = "r";

    /**
     * Checks if the full display name of the month in the provided LocalDate contains the specified character.
     *
     * @param sourceDate      The LocalDate object representing the date to extract the month's full display name from.
     * @param targetCharacter The character to search for within the full month's display name.
     * @return True if the full month's display name contains the target character; otherwise, false.
     */
    private boolean ifContainsMonthCharacter(LocalDate sourceDate, String targetCharacter) {
        return sourceDate.getMonth().getDisplayName(TextStyle.FULL, Locale.US).contains(targetCharacter);
    }

    /**
     * Sorts a list of LocalDate objects in descending order based on their month values.
     *
     * @param list The list of LocalDate objects to be sorted.
     * @return A new list containing the LocalDate objects sorted in descending order by month.
     */
    private List<LocalDate> sortLocalDateDescByMonth(List<LocalDate> list) {
        list.sort(Comparator.comparingInt(LocalDate::getMonthValue).reversed());
        return list;
    }

    /**
     * Sorts a list of LocalDate objects in ascending order based on their month values.
     *
     * @param list The list of LocalDate objects to be sorted.
     * @return A new list containing the LocalDate objects sorted in ascending order by month.
     */
    private List<LocalDate> sortLocalDateAscByMonth(List<LocalDate> list) {
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
        List<LocalDate> datesWithTargetCharacterInMonth = new ArrayList<>();
        List<LocalDate> datesWithoutTargetCharacterInMonth = new ArrayList<>();
        List<LocalDate> result = new ArrayList<>();

        for (LocalDate date : unsortedDates) {
            if (ifContainsMonthCharacter(date, R)) {
                datesWithTargetCharacterInMonth.add(date);
            } else {
                datesWithoutTargetCharacterInMonth.add(date);
            }
        }

        sortLocalDateAscByMonth(datesWithTargetCharacterInMonth);
        sortLocalDateDescByMonth(datesWithoutTargetCharacterInMonth);

        result.addAll(datesWithTargetCharacterInMonth);
        result.addAll(datesWithoutTargetCharacterInMonth);

        return result;
    }
}