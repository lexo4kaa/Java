package com.company.composite_chain.reader;

import com.company.composite_chain.exception.CustomerException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CustomerFileReaderTest {
    @Test(dataProvider = "read")
    public void readTextTest(String fileName, String expected) throws CustomerException {
        String actual = CustomerFileReader.fileReading(fileName);
        Assert.assertEquals(actual, expected);
    }
    @DataProvider(name = "read")
    public Object[][] fileReadingData() {
        String text = "Это утро было особенным. Солнце запуталось в тучах за серебряной рекой. Над водой клубится прозрачный туман. Осенний лес окурен едким дымом.\n" +
                "Утро, а грустно. День ничего не обещает, и лицо у него печальное.\n" +
                "Солнце чувствовало себя виновато. Оно залежалось на тучах и опоздало осветить землю. Всякое случается.\n" +
                "Сад облился тёплыми лучами и вздохнул ароматом созревающих плодов.\n";
        return new Object[][]{
                {"data/text.txt", text}
        };
    }
}