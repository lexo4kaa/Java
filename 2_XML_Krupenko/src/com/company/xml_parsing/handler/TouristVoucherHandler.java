package com.company.xml_parsing.handler;

import com.company.xml_parsing.builder.TouristVouchersSaxBuilder;
import com.company.xml_parsing.entity.TouristVoucher;
import com.company.xml_parsing.entity.RestVoucher;
import com.company.xml_parsing.entity.SightseeingVoucher;
import com.company.xml_parsing.entity.WeekendVoucher;
import com.company.xml_parsing.entity.HotelCharacteristic;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class TouristVoucherHandler extends DefaultHandler {
    private Set<TouristVoucher> touristVouchers;
    private TouristVoucher current;
    private TouristVoucherType currentXmlTag;
    private EnumSet<TouristVoucherType> withText;
    public TouristVoucherHandler() {
        touristVouchers = new HashSet<>();
        withText = EnumSet.range(TouristVoucherType.VOUCHER_NUMBER, TouristVoucherType.CITY);
    }
    public Set<TouristVoucher> getTouristVouchers() {
        return touristVouchers;
    }
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        TouristVoucherType type = TouristVoucherType.valueOf(qName.toUpperCase().replace('-','_'));
        switch (type) {
            case REST_VOUCHER -> {
                current = new RestVoucher();
                current.setTransport(attrs.getValue(0));
            }
            case SIGHTSEEING_VOUCHER -> {
                current = new SightseeingVoucher();
                current.setTransport(attrs.getValue(0));
            }
            case WEEKEND_VOUCHER -> {
                current = new WeekendVoucher();
                current.setTransport(attrs.getValue(0));
            }
            case HOTEL_CHARACTERISTIC -> {
                HotelCharacteristic hotelCharacteristic = current.getHotelCharacteristic();
                String foodType = attrs.getValue(0);
                if(foodType == null)
                {
                    foodType = "HB";
                }
                hotelCharacteristic.setFoodType(foodType);
            }
        }
        TouristVoucherType temp = TouristVoucherType.valueOf(qName.toUpperCase().replace('-','_'));
        if (withText.contains(temp)) {
            currentXmlTag = temp;
        }
    }
    public void endElement(String uri, String localName, String qName) {
        TouristVoucherType tag = TouristVoucherType.valueOf(qName.toUpperCase().replace('-','_'));
        if (tag.equals(TouristVoucherType.REST_VOUCHER) || tag.equals(TouristVoucherType.SIGHTSEEING_VOUCHER)
                || tag.equals(TouristVoucherType.WEEKEND_VOUCHER)) {
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
                case START_DATE -> current.setStartDate(LocalDate.parse(data));
                case COST -> current.setCost(Integer.parseInt(data));
                case STARS -> hotelCharacteristic.setStars(Integer.parseInt(data));
                case ROOMS -> hotelCharacteristic.setRooms(Integer.parseInt(data));
                case CITY -> ((RestVoucher)current).setCity(data);
                case NUMBER_OF_PLACES -> ((SightseeingVoucher)current).setNumberOfPlaces(Integer.parseInt(data));
                case NUMBER_OF_NIGHTS -> ((WeekendVoucher)current).setNumberOfNights(Integer.parseInt(data));
                default -> throw new EnumConstantNotPresentException(
                        currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }
}
