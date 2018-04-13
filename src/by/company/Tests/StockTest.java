package by.company.Tests;

import by.company.Model.Stock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StockTest {
    private Stock stock;

    @Before
    public void setUp() {
        stock = new Stock("Minerals", 1000000000);
        stock.setValueMap("Unloading", "Coal", 30000);
        stock.setValueMap("Unloading", "Gold", 30000);
        stock.setValueMap("Unloading", "Rock", 30000);
    }

    @After
    public void tearDown() throws Exception {
        stock = null;
    }

    @Test
    public void checkGoodTest() {
        String target = "Loading";
        String goods = "Coal";
        long weight = 10000;

        boolean expected = true;
        boolean actual = stock.checkGood(target, goods, weight);
        Assert.assertEquals("Broke function", expected, actual);
    }

}