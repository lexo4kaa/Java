package com.company.customer_array.test;

import com.company.customer_array.action.ArrayParser;
import com.company.customer_array.entity.CustomerArray;
import com.company.customer_array.exception.ArrayException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ArrayParserTest {
    ArrayParser obj;
    CustomerArray customerArray;
    @BeforeClass
    public void setUp() {
        obj = new ArrayParser();
    }
    @Test(dataProvider = "parser")
    public void testParser(List<String> list, int[] expected ) throws ArrayException {
        customerArray = obj.arrayParser(list);
        int[] actual = customerArray.getArray();
        assertEquals(actual, expected);
    }
    @DataProvider(name = "parser")
    public Object[] createDataParser() {
        return new Object[][] { { Arrays.asList("2z-5 4 1o", "0 1 3 5q", "1 2 3 4"), new int[] {1, 2, 3, 4} },
                { Arrays.asList("2,33,4,-5,1", "1,2,33,174q", "-1,2,3,4"), new int[] {2, 33, 4, -5, 1} },
                { Arrays.asList("2@3@4q@-p5@1", "1@2@3@174", "-1@2@3@4"), new int[] {1, 2, 3, 174} },
                { Arrays.asList("2q;3;4;-5;1", "1;2;33;174q", "-1;2;3;4"), new int[] {-1, 2, 3, 4} },
                { Arrays.asList("1;2;3;4;5", "1;2;3;4", "-1;-2;-3"), new int[] {1, 2, 3, 4, 5} },
                { Arrays.asList("2q 3,4 -5;1", "1@2;33,174q", "-1@2;3,4"), new int[] {-1, 2, 3, 4} } };
    }
    @AfterClass
    public void tierDown() {
        obj = null;
        customerArray = null;
    }
}