package by.company.Model;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

    public static boolean checkCapacity(String testString) {
        Pattern p = Pattern.compile("([0-9]{6,20})");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    public static boolean checkShipWeight(String testString) {
        Pattern p = Pattern.compile("([0-9]{4,10})");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    public static boolean checkpierSpeed(String testString){
        Pattern p = Pattern.compile("([0-9]{3,4})");
        Matcher m = p.matcher(testString);
        return m.matches();
    }
}
