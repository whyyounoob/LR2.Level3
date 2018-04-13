package by.company.Tests;

import by.company.Model.RegEx;
import org.junit.Assert;
import org.junit.Test;

public class RegExTest {

    @Test
    public void checkCorrectCapacity() {
        String correctCapacity = "100000000000";

        boolean expected = true;
        boolean actual = RegEx.checkCapacity(correctCapacity);
        Assert.assertEquals("Wrong capacity", expected, actual);
    }

    @Test
    public void checkCorrectShipWeight() {
        String correctShipWeight = "100000";

        boolean expected = true;
        boolean actual = RegEx.checkCapacity(correctShipWeight);
        Assert.assertEquals("Wrong ship weight", expected, actual);

    }

    @Test
    public void checkPierSpeed() {
        String correctPierSpeed = "1000";

        boolean expected = true;
        boolean actual = RegEx.checkCapacity(correctPierSpeed);
        Assert.assertEquals("Wrong pier speed", expected, actual);

    }
}