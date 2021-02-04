package com.company.xml_parsing.builder;

import com.company.xml_parsing.entity.HotelCharacteristic;
import com.company.xml_parsing.entity.TouristVoucher;
import com.company.xml_parsing.exception.ParserException;
import com.company.xml_parsing.parsing.LocalDateParsing;
import com.company.xml_parsing.parsing.TouristVoucherType;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.company.xml_parsing.validation.TouristVoucherErrorHandler.logger;

public class TouristVouchersStaxBuilder {
    private Set<TouristVoucher> touristVouchers;
    private XMLInputFactory inputFactory;
    public TouristVouchersStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        touristVouchers = new HashSet<>();
    }
    public Set<TouristVoucher> getTouristVouchers() {
        return touristVouchers;
    }
    public void buildSetTouristVouchers(String filename) {
        XMLStreamReader reader;
        String name;
        try(FileInputStream inputStream = new FileInputStream(new File(filename))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            // StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(TouristVoucherType.TOURIST_VOUCHER.getValue())) {
                        TouristVoucher touristVoucher = buildTouristVoucher(reader);
                        touristVouchers.add(touristVoucher);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            logger.error("OOPS!");
        } catch (IOException e) {
            logger.error("OOPS!!");
        } catch (ParserException e) {
            logger.error("O!");
        }
    }
    private TouristVoucher buildTouristVoucher(XMLStreamReader reader) throws XMLStreamException, ParserException {
        TouristVoucher touristVoucher = new TouristVoucher();
        // null check
        String transport = reader.getAttributeValue(null, TouristVoucherType.TRANSPORT.getValue());
        String type = reader.getAttributeValue(null, TouristVoucherType.TYPE.getValue());
        touristVoucher.setTransport(transport);
        touristVoucher.setType(type);
        String name;
        while (reader.hasNext()) {
            int next = reader.next();
            switch (next) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName().replace('-','_');
                    switch (TouristVoucherType.valueOf(name.toUpperCase())) {
                        case VOUCHER_NUMBER -> touristVoucher.setVoucherNumber(getXMLText(reader));
                        case COUNTRY -> touristVoucher.setCountry(getXMLText(reader));
                        case NUMBER_OF_DAYS ->
                                touristVoucher.setNumberOfDays(Integer.parseInt(getXMLText(reader)));
                        case START_DATE -> {
                            try {
                                touristVoucher.setStartDate(LocalDateParsing.
                                        convertStringToLocalDate(getXMLText(reader)));
                            } catch (ParserException e) {
                                logger.info("Incorrect date. Setting current date as a start date");
                                touristVoucher.setStartDate(LocalDate.now());
                            }
                        }
                        case COST -> touristVoucher.setCost(Integer.parseInt(getXMLText(reader)));
                        case HOTEL_CHARACTERISTIC ->
                                touristVoucher.setHotelCharacteristic(getXMLHotelCharacteristic(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName().replace('-','_');
                    if (TouristVoucherType.valueOf(name.toUpperCase()) == TouristVoucherType.TOURIST_VOUCHER) {
                        return touristVoucher;
                    }
            }
        }
        throw new ParserException("Unknown element in tag <tourist-voucher>");
    }
    private HotelCharacteristic getXMLHotelCharacteristic(XMLStreamReader reader) throws XMLStreamException {
        HotelCharacteristic hotelCharacteristic = new HotelCharacteristic();
        String foodType = reader.getAttributeValue(null, TouristVoucherType.FOOD_TYPE.getValue());
        if(foodType == null) {
            foodType = "HB";
        }
        hotelCharacteristic.setFoodType(foodType);
        String name;
        while (reader.hasNext()) {
            int next = reader.next();
            switch (next) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName().replace('-','_');
                    switch (TouristVoucherType.valueOf(name.toUpperCase())) {
                        case ROOMS ->
                                hotelCharacteristic.setRooms(Integer.parseInt(getXMLText(reader)));
                        case STARS ->
                                hotelCharacteristic.setStars(Integer.parseInt(getXMLText(reader)));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName().replace('-','_');
                    if (TouristVoucherType.valueOf(name.toUpperCase()) == TouristVoucherType.HOTEL_CHARACTERISTIC) {
                        return hotelCharacteristic;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <hotel-characteristic>");
    }
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
    public static void main(String[] args) {
        TouristVouchersStaxBuilder staxBuilder = new TouristVouchersStaxBuilder();
        staxBuilder.buildSetTouristVouchers("files/TouristVouchers.xml");
        System.out.println(staxBuilder.getTouristVouchers());
    }
}