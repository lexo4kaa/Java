package com.company.xml_parsing.builder;

import com.company.xml_parsing.entity.*;
import com.company.xml_parsing.exception.ParserException;
import com.company.xml_parsing.parsing.LocalDateParsing;
import com.company.xml_parsing.handler.TouristVoucherType;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.company.xml_parsing.handler.TouristVoucherErrorHandler.logger;

public class TouristVouchersStaxBuilder extends AbstractTouristVouchersBuilder {
    private XMLInputFactory inputFactory;
    public TouristVouchersStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        touristVouchers = new HashSet<>();
    }
    @Override
    public Set<TouristVoucher> getTouristVouchers() {
        return touristVouchers;
    }
    public void buildSetTouristVouchers(String filename) {
        XMLStreamReader reader;
        String name;
        try(FileInputStream inputStream = new FileInputStream(new File(filename))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int next = reader.next();
                if (next == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    TouristVoucherType type = TouristVoucherType.valueOf(name.toUpperCase().replace('-','_'));
                    switch (type) {
                        case REST_VOUCHER -> {
                            TouristVoucher touristVoucher = new RestVoucher();
                            buildTouristVoucher(reader, touristVoucher);
                            touristVouchers.add(touristVoucher);
                        }
                        case SIGHTSEEING_VOUCHER -> {
                            TouristVoucher touristVoucher = new SightseeingVoucher();
                            buildTouristVoucher(reader, touristVoucher);
                            touristVouchers.add(touristVoucher);
                        }
                        case WEEKEND_VOUCHER -> {
                            TouristVoucher touristVoucher = new WeekendVoucher();
                            buildTouristVoucher(reader, touristVoucher);
                            touristVouchers.add(touristVoucher);
                        }
                    }
                }
            }
        } catch (XMLStreamException e) {
            logger.error("XML stream exception", e);
        } catch (IOException e) {
            logger.error("I/O exception", e);
        }
    }
    private TouristVoucher buildTouristVoucher(XMLStreamReader reader, TouristVoucher touristVoucher) throws XMLStreamException {
        String transport = reader.getAttributeValue(null, TouristVoucherType.TRANSPORT.getValue());
        touristVoucher.setTransport(transport);
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

                        case CITY -> ((RestVoucher)touristVoucher).setCity(getXMLText(reader));
                        case NUMBER_OF_PLACES ->
                                ((SightseeingVoucher)touristVoucher).setNumberOfPlaces(Integer
                                                                                        .parseInt(getXMLText(reader)));
                        case NUMBER_OF_NIGHTS ->
                                ((WeekendVoucher)touristVoucher).setNumberOfNights(Integer
                                                                                        .parseInt(getXMLText(reader)));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName().replace('-','_');
                    if (TouristVoucherType.valueOf(name.toUpperCase()) == TouristVoucherType.REST_VOUCHER ||
                            TouristVoucherType.valueOf(name.toUpperCase()) == TouristVoucherType.SIGHTSEEING_VOUCHER ||
                            TouristVoucherType.valueOf(name.toUpperCase()) == TouristVoucherType.WEEKEND_VOUCHER) {
                        return touristVoucher;
                    }
            }
        }
        throw new XMLStreamException("Unknown element " + reader);
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

    /*
    public static void main(String[] args) {
        TouristVouchersStaxBuilder staxBuilder = new TouristVouchersStaxBuilder();
        staxBuilder.buildSetTouristVouchers("files/TouristVouchers.xml");
        System.out.println(staxBuilder.getTouristVouchers());
    }
    */
}