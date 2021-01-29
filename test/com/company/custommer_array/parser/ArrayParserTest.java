package com.company.custommer_array.parser;

import com.company.customer_array.entity.CustomerArray;
import com.company.customer_array.exception.ArrayException;
import com.company.customer_array.parser.ArrayParser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ArrayParserTest {
    CustomerArray customerArray;
    @BeforeClass
    public void setUp() {

    }
    @Test(dataProvider = "parser")
    public void testParser(List<String> list, int[] expected ) throws ArrayException {
        customerArray = ArrayParser.arrayParser(list);
        int[] actual = customerArray.getArray();
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "exception", expectedExceptions = ArrayException.class)
    public void testParserOnExceptions(List<String> list) throws ArrayException {
        customerArray = ArrayParser.arrayParser(list);
    }
    @DataProvider(name = "parser")
    public Object[] createGoodDataParser() {
        return new Object[][] { { Arrays.asList("2z-5 4 1o", "0 1 3 5q", "1 2 3 4"), new int[] {1, 2, 3, 4} },
                { Arrays.asList("2,33,4,-5,1", "1,2,33,174q", "-1,2,3,4"), new int[] {2, 33, 4, -5, 1} },
                { Arrays.asList("2@3@4q@-p5@1", "1@2@3@174", "-1@2@3@4"), new int[] {1, 2, 3, 174} },
                { Arrays.asList("2q;3;4;-5;1", "1;2;33;174q", "-1;2;3;4"), new int[] {-1, 2, 3, 4} },
                { Arrays.asList("1;2;3;4;5", "1;2;3;4", "-1;-2;-3"), new int[] {1, 2, 3, 4, 5} },
                { Arrays.asList("2q 3,4 -5;1", "1@2;33,174q", "-1@2;3,4"), new int[] {-1, 2, 3, 4} } };
    }
    @DataProvider(name = "exception")
    public Object[] createBadDataParser() {
        return new Object[][] { { null }, // null
                { Arrays.asList("2 k o", "0 1z 3x 5q", "0 1 2 -y") }  // no integer array
        };
    }
    @AfterClass
    public void tierDown() {
        customerArray = null;
    }
}