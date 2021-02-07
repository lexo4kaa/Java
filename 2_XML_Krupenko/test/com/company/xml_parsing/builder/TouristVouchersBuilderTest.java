package com.company.xml_parsing.builder;

import com.company.xml_parsing.entity.*;
import com.company.xml_parsing.exception.ParserException;
import com.company.xml_parsing.factory.TouristVoucherBuilderFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TouristVouchersBuilderTest {

    private static final String filePath = "files/TouristVouchers.xml";

    AbstractTouristVouchersBuilder saxBuilder;
    AbstractTouristVouchersBuilder staxBuilder;
    AbstractTouristVouchersBuilder domBuilder;
    Set<TouristVoucher> expected;

    @BeforeClass
    public void beforeClass() {
        saxBuilder = TouristVoucherBuilderFactory.createTouristVoucherBuilder("sax");
        staxBuilder = TouristVoucherBuilderFactory.createTouristVoucherBuilder("stax");
        domBuilder = TouristVoucherBuilderFactory.createTouristVoucherBuilder("dom");
        expected = new HashSet<>();
        expected.add(new WeekendVoucher("air","SWE0000000001","Sweden",3,3, LocalDate.parse("2021-03-03"), 500, new HotelCharacteristic("OB",4,2)));
        expected.add(new SightseeingVoucher("air","CHI0000000001","China", 5, 7, LocalDate.parse("2022-06-15"), 1000, new HotelCharacteristic("HB",4,2)));
        expected.add(new RestVoucher("auto","SPA0000000001","Spain","Madrid",14, LocalDate.parse("2021-09-04"),2500, new HotelCharacteristic("AI",4,1)));
        expected.add(new WeekendVoucher("ferry","SWE0000000002","Sweden",2,1, LocalDate.parse("2021-07-22"), 300, new HotelCharacteristic("HB",4,3)));
        expected.add(new SightseeingVoucher("auto","RUS0000000001","Russia", 4, 10, LocalDate.parse("2021-10-25"), 750, new HotelCharacteristic("HB",4,2)));
        expected.add(new RestVoucher("air","EGY0000000001","Egypt","Cairo",30, LocalDate.parse("2021-07-01"),7500, new HotelCharacteristic("UAI",5,2)));
        expected.add(new WeekendVoucher("ferry","SWE0000000003","Sweden",2,1, LocalDate.parse("2021-12-12"), 300, new HotelCharacteristic("HB",4,3)));
        expected.add(new SightseeingVoucher("railway","RUS0000000002","Russia", 7, 15, LocalDate.parse("2021-08-08"), 1000, new HotelCharacteristic("HB",3,3)));
        expected.add(new RestVoucher("air","EGY0000000002","Egypt","Cairo",10, LocalDate.parse("2021-03-03"),2500, new HotelCharacteristic("AI",4,2)));
        expected.add(new WeekendVoucher("ferry","SWE0000000004","Sweden",2,2, LocalDate.parse("2021-11-02"), 400, new HotelCharacteristic("HB",3,3)));
        expected.add(new SightseeingVoucher("auto","POL0000000001","Poland", 4, 5, LocalDate.parse("2021-05-25"), 660, new HotelCharacteristic("HB",3,2)));
        expected.add(new RestVoucher("air","ITA0000000001","Italy","Rome",7, LocalDate.parse("2021-03-03"),1500, new HotelCharacteristic("AI",4,2)));
        expected.add(new WeekendVoucher("ferry","FIN0000000001","Finland",1,2, LocalDate.parse("2021-04-15"), 200, new HotelCharacteristic("HB",3,1)));
        expected.add(new SightseeingVoucher("auto","POL0000000002","Poland", 3, 7, LocalDate.parse("2021-06-05"), 770, new HotelCharacteristic("HB",4,2)));
        expected.add(new RestVoucher("air","ITA0000000002","Italy","Rome",7, LocalDate.parse("2021-09-13"),1300, new HotelCharacteristic("FB",4,2)));
        expected.add(new RestVoucher("railway","UKR0000000001","Ukraine","Kiev",10, LocalDate.parse("2021-07-15"),1111, new HotelCharacteristic("BB",3,4)));
    }

    @Test()
    public void testTouristVoucherSaxBuilder() {
        saxBuilder.buildSetTouristVouchers(filePath);
        Set<TouristVoucher> actual = saxBuilder.getTouristVouchers();
        assertEquals(actual, expected);
    }

    @Test()
    public void testTouristVoucherStaxBuilder() {
        staxBuilder.buildSetTouristVouchers(filePath);
        Set<TouristVoucher> actual = staxBuilder.getTouristVouchers();
        assertEquals(actual, expected);
    }

    @Test()
    public void testTouristVoucherDomBuilder() {
        domBuilder.buildSetTouristVouchers(filePath);
        Set<TouristVoucher> actual = domBuilder.getTouristVouchers();
        assertEquals(actual, expected);
    }

    @AfterClass
    public void afterClass() {
        saxBuilder = null;
        staxBuilder = null;
        domBuilder = null;
        expected = null;
    }
}
