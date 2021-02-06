package com.company.xml_parsing.builder;

import com.company.xml_parsing.entity.TouristVoucher;
import com.company.xml_parsing.exception.ParserException;
import com.company.xml_parsing.factory.TouristVoucherBuilderFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

public class TouristVouchersBuilderTest {

    private static final String filePath = "files/TouristVoucher.xml";

    TouristVoucherBuilderFactory factory;
    AbstractTouristVouchersBuilder saxBuilder;
    AbstractTouristVouchersBuilder staxBuilder;
    AbstractTouristVouchersBuilder domBuilder;
    Set<TouristVoucher> expectedTouristVouchersSet;

    @BeforeClass
    public void beforeClass() throws ParserException {
        factory = new TouristVoucherBuilderFactory();
        saxBuilder = factory.createTouristVoucherBuilder("sax");
        staxBuilder = factory.createTouristVoucherBuilder("stax");
        domBuilder = factory.createTouristVoucherBuilder("dom");
        expectedTouristVouchersSet = new HashSet<TouristVoucher>();
        /*expectedTouristVouchersSet.add(new RestVoucher("air","A999999998","BRAZIL",10,10,200, LocalDateTime.parse("2021-01-31T22:43:00"),List.of(new HotelCharacteristic("HB",3,true,true,true,1),new HotelCharacteristic("AI",5,true,true,true,3))));
        expectedTouristVouchersSet.add(new ExcursionVoucher("auto","A999999999","BELARUS",1,1,100,LocalDateTime.parse("2021-01-30T23:43:00"),"MINSK","Victory Square", List.of(new HotelCharacteristic("HB",3,true,true,true,1))));
        expectedTouristVouchersSet.add(new ExcursionVoucher("air","A999999997","JAPAN",10,10,10000, LocalDateTime.parse("2021-03-31T19:43:00"),"Yamanashi", "Fujiyama",List.of(new HotelCharacteristic("HB",3,true,true,true,1))));
        expectedTouristVouchersSet.add(new PiligrimageVoucher("air","A999999996","ISRAEL",10,10,5000, LocalDateTime.parse("2021-04-28T17:43:00"), "JERUSALEM"));
        expectedTouristVouchersSet.add(new RestVoucher("auto","A999999995","TURKEY",365,365,10000, LocalDateTime.parse("2021-04-28T17:43:00"), List.of(new HotelCharacteristic("HB",1,false,false,false,1))));
        expectedTouristVouchersSet.add(new RestVoucher("air","A999999994","USA",10,10,9999999, LocalDateTime.parse("2025-04-28T18:43:00"), List.of(new HotelCharacteristic("AI",4,true,true,true,3))));
        expectedTouristVouchersSet.add(new RestVoucher("air","A999999993","SWEDEN",20,20,100000, LocalDateTime.parse("2026-01-28T06:00:00"), List.of( new HotelCharacteristic("HB",5,false,false,true,1), new HotelCharacteristic("BB",4,true,true,true,2), new HotelCharacteristic("AI",5,true,true,true,3))));
        expectedTouristVouchersSet.add(new ExcursionVoucher("railway","A999999992","SINGAPORE",20,20,1000000, LocalDateTime.parse("2023-04-28T07:00:00"),"UKRAINE","Kiev Pechersk Lavra",List.of( new HotelCharacteristic("BB",1,false,false,false,2))));
        expectedTouristVouchersSet.add(new PiligrimageVoucher("auto","A999999991","ITALY",5,5,20000, LocalDateTime.parse("2022-06-28T00:00:00"),"Holy Land"));
        expectedTouristVouchersSet.add(new ExcursionVoucher("air","A999999990","INDIA",20,20,999999,  LocalDateTime.parse("2026-07-28T04:00:00"), "Agra","Taj Mahal", List.of(new HotelCharacteristic("AI",4,true,true,true,2))));
        expectedTouristVouchersSet.add(new ExcursionVoucher("air","A999999989","CHINA",10,10,10000, LocalDateTime.parse("2025-12-31T13:00:00"),"Huairou District","Great Wall of China", List.of(new HotelCharacteristic("HB",1,false,false,false,3))));
        expectedTouristVouchersSet.add(new RestVoucher("air","A999999988","GREECE",20,20,20, LocalDateTime.parse("2030-11-30T11:00:00"), List.of( new HotelCharacteristic("HB",1,false,false,false,2))));
        expectedTouristVouchersSet.add(new RestVoucher("auto","A999999987","FRANCE",15,15,10000, LocalDateTime.parse("2028-06-30T06:00:00"), List.of( new HotelCharacteristic("HB",1,false,false,false,2))));
        expectedTouristVouchersSet.add(new ExcursionVoucher("air","A999999986","USA",20,20,10000, LocalDateTime.parse("2025-04-30T00:00:00"),"New York","Statue of Liberty", List.of( new HotelCharacteristic("AI",5,true,true,true,3))));
        expectedTouristVouchersSet.add(new ExcursionVoucher("auto","A999999985","FRANCE",999,999,5000,  LocalDateTime.parse("2100-01-01T00:00:00"),"Paris","tour Eiffel", List.of( new HotelCharacteristic("HB",5,true,true,true,3))));
        expectedTouristVouchersSet.add(new RestVoucher("railway","A999999984","FINLAND",5,5,9000, LocalDateTime.parse("2027-05-23T06:00:00"), List.of(new HotelCharacteristic("BB",5,true,true,true,3))));
    */}


    @Test()
    public void testTouristVoucherSaxBuilder() throws ParserException {
        saxBuilder.buildSetTouristVouchers(filePath);
        Set<TouristVoucher> actualTouristVouchersSet = saxBuilder.getTouristVouchers();
        assertEquals(actualTouristVouchersSet, expectedTouristVouchersSet);
    }

    @Test()
    public void testTouristVoucherStaxBuilder() throws ParserException {
        staxBuilder.buildSetTouristVouchers(filePath);
        Set<TouristVoucher> actualTouristVouchersSet = staxBuilder.getTouristVouchers();
        assertEquals(actualTouristVouchersSet, expectedTouristVouchersSet);
    }

    @Test()
    public void testTouristVoucherDomBuilder() throws ParserException {
        domBuilder.buildSetTouristVouchers(filePath);
        Set<TouristVoucher> actualTouristVouchersSet = domBuilder.getTouristVouchers();
        assertEquals(actualTouristVouchersSet, expectedTouristVouchersSet);
    }

    @AfterClass
    public void afterClass() {
        factory = null;
        saxBuilder = null;
        staxBuilder = null;
        staxBuilder = null;
        expectedTouristVouchersSet = null;
    }
}
