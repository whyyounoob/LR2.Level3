package by.company.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class used for regular expressions.
 *
 * @author Maxim Borodin
 */

public class RegEx {

    /**
     * This method verifies the correctness of the input capacity.
     *
     * @param testString test string
     * @return true if string matches pattern
     */

    public static boolean checkCapacity(final String testString) {
        Pattern p = Pattern.compile("([0-9]{6,20})");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    /**
     * This method verifies the correctness of the input capacity of sheep.
     *
     * @param testString test string
     * @return true if string matches pattern
     */

    public static boolean checkShipWeight(final String testString) {
        Pattern p = Pattern.compile("([0-9]{4,10})");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    /**
     * This method verifies the correctness of the input pier speed.
     *
     * @param testString test string
     * @return true if string matches pattern
     */

    public static boolean checkPierSpeed(final String testString) {
        Pattern p = Pattern.compile("([0-9]{3,4})");
        Matcher m = p.matcher(testString);
        return m.matches();
    }
}
