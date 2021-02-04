package com.company.xml_parsing.builder;

import com.company.xml_parsing.entity.HotelCharacteristic;
import com.company.xml_parsing.entity.TouristVoucher;
import com.company.xml_parsing.exception.ParserException;
import com.company.xml_parsing.parsing.LocalDateParsing;
import com.company.xml_parsing.parsing.TouristVoucherType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.company.xml_parsing.validation.TouristVoucherErrorHandler.logger;

public class TouristVoucherHandler extends DefaultHandler {
    private Set<TouristVoucher> touristVouchers;
    private TouristVoucher current;
    private TouristVoucherType currentXmlTag;
    private EnumSet<TouristVoucherType> withText;
    private static final String ELEMENT_VOUCHER = "tourist-voucher";
    private static final String ELEMENT_HOTEL_CHARACTERISTIC = "hotel-characteristic";
    public TouristVoucherHandler() {
        touristVouchers = new HashSet<>();
        withText = EnumSet.range(TouristVoucherType.VOUCHER_NUMBER, TouristVoucherType.ROOMS);
    }
    public Set<TouristVoucher> getTouristVouchers() {
        return touristVouchers;
    }
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (ELEMENT_VOUCHER.equals(qName)) {
            current = new TouristVoucher();
            if(attrs.getLength() == 2) {
                if(Objects.equals(attrs.getQName(0), TouristVoucherType.TRANSPORT)) {
                    current.setTransport(attrs.getValue(0));
                    current.setType(attrs.getValue(1));
                } else {
                    current.setTransport(attrs.getValue(1));
                    current.setType(attrs.getValue(0));
                }
            }
        } else {
            if (ELEMENT_HOTEL_CHARACTERISTIC.equals(qName)) {
                HotelCharacteristic hotelCharacteristic = current.getHotelCharacteristic();
                String foodType = attrs.getValue(0);
                hotelCharacteristic.setFoodType(Objects.requireNonNullElse(foodType, "HB"));
            }
            TouristVoucherType temp = TouristVoucherType.valueOf(qName.toUpperCase().replace('-','_'));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }
    public void endElement(String uri, String localName, String qName) {
        if (ELEMENT_VOUCHER.equals(qName)) {
            touristVouchers.add(current);
        }
    }
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            HotelCharacteristic hotelCharacteristic = current.getHotelCharacteristic();
            switch (currentXmlTag) {
                case VOUCHER_NUMBER -> current.setVoucherNumber(data);
                case COUNTRY -> current.setCountry(data);
                case NUMBER_OF_DAYS -> current.setNumberOfDays(Integer.parseInt(data));
                case START_DATE -> {
                    try {
                        current.setStartDate(LocalDateParsing.convertStringToLocalDate(data));
                    } catch (ParserException e) {
                        logger.info("Incorrect date. Setting current date as a start date");
                        current.setStartDate(LocalDate.now());
                    }
                }
                case COST -> current.setCost(Integer.parseInt(data));
                case STARS -> hotelCharacteristic.setStars(Integer.parseInt(data));
                case ROOMS -> hotelCharacteristic.setRooms(Integer.parseInt(data));
                default -> throw new EnumConstantNotPresentException(
                        currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }
}
