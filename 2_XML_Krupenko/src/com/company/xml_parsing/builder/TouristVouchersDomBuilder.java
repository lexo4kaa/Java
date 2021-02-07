package com.company.xml_parsing.builder;

import com.company.xml_parsing.entity.HotelCharacteristic;
import com.company.xml_parsing.entity.RestVoucher;
import com.company.xml_parsing.entity.SightseeingVoucher;
import com.company.xml_parsing.entity.TouristVoucher;
import com.company.xml_parsing.entity.WeekendVoucher;
import com.company.xml_parsing.exception.ParserException;
import com.company.xml_parsing.handler.TouristVoucherType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.company.xml_parsing.handler.TouristVoucherErrorHandler.logger;

public class TouristVouchersDomBuilder extends AbstractTouristVouchersBuilder {
    private DocumentBuilder docBuilder;
    public TouristVouchersDomBuilder() {
        touristVouchers = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Parser configuration exception", e);
        }
    }
    @Override
    public Set<TouristVoucher> getTouristVouchers() {
        return touristVouchers;
    }
    public void buildSetTouristVouchers(String filename) {
        Document doc;
        try {
            doc = docBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            fillTouristVouchersSet(root, TouristVoucherType.REST_VOUCHER);
            fillTouristVouchersSet(root, TouristVoucherType.SIGHTSEEING_VOUCHER);
            fillTouristVouchersSet(root, TouristVoucherType.WEEKEND_VOUCHER);
        } catch (IOException e) {
            logger.error("I/O exception in " + filename, e);
        } catch (SAXException e) {
            logger.error("SAX parser exception in " + filename, e);
        } catch (ParserException e) {
            logger.error("Parser exception in " + filename, e);
        }
    }
    private void fillTouristVouchersSet(Element root, TouristVoucherType type) throws ParserException {
        String value = type.getValue();
        NodeList touristVouchersList = root.getElementsByTagName(value);
        for (int i = 0; i < touristVouchersList.getLength(); i++) {
            Element touristVoucherElement = (Element)touristVouchersList.item(i);
            TouristVoucher touristVoucher = buildTouristVouchers(touristVoucherElement, type);
            touristVouchers.add(touristVoucher);
        }
    }
    private TouristVoucher buildTouristVouchers(Element touristVoucherElement, TouristVoucherType type) throws ParserException {
        TouristVoucher touristVoucher;
        switch (type) {
            case REST_VOUCHER -> touristVoucher = new RestVoucher();
            case SIGHTSEEING_VOUCHER -> touristVoucher = new SightseeingVoucher();
            case WEEKEND_VOUCHER -> touristVoucher = new WeekendVoucher();
            default -> throw new ParserException("Unexpected value: " + type);
        }
        touristVoucher.setTransport(touristVoucherElement.getAttribute(TouristVoucherType.TRANSPORT.getValue()));
        touristVoucher.setVoucherNumber(getElementTextContent(touristVoucherElement,
                                                              TouristVoucherType.VOUCHER_NUMBER.getValue()));
        touristVoucher.setCountry(getElementTextContent(touristVoucherElement,
                                                        TouristVoucherType.COUNTRY.getValue()));
        int numberOfDays = Integer.parseInt(getElementTextContent(touristVoucherElement,
                                                                  TouristVoucherType.NUMBER_OF_DAYS.getValue()));
        touristVoucher.setNumberOfDays(numberOfDays);
        LocalDate date = LocalDate.parse(getElementTextContent(touristVoucherElement,
                                                               TouristVoucherType.START_DATE.getValue()));
        touristVoucher.setStartDate(date);
        int cost = Integer.parseInt(getElementTextContent(touristVoucherElement,
                                                          TouristVoucherType.COST.getValue()));
        touristVoucher.setCost(cost);
        HotelCharacteristic hotelCharacteristic = touristVoucher.getHotelCharacteristic();
        Element hotelCharacteristicElement = (Element)touristVoucherElement
                                             .getElementsByTagName(TouristVoucherType.HOTEL_CHARACTERISTIC.getValue())
                                             .item(0);
        String foodType = hotelCharacteristicElement.getAttribute(TouristVoucherType.FOOD_TYPE.getValue());
        if(foodType.equals("")) {
            foodType = "HB";
        }
        hotelCharacteristic.setFoodType(foodType);
        int stars = Integer.parseInt(getElementTextContent(hotelCharacteristicElement,
                                                           TouristVoucherType.STARS.getValue()));
        hotelCharacteristic.setStars(stars);
        int rooms = Integer.parseInt(getElementTextContent(hotelCharacteristicElement,
                                                           TouristVoucherType.ROOMS.getValue()));
        hotelCharacteristic.setRooms(rooms);
        switch (type) {
            case REST_VOUCHER -> ((RestVoucher)touristVoucher)
                    .setCity(getElementTextContent(touristVoucherElement, TouristVoucherType.CITY.getValue()));
            case SIGHTSEEING_VOUCHER -> {
                int numberOfPlaces = Integer
                                     .parseInt(getElementTextContent(touristVoucherElement,
                                              TouristVoucherType.NUMBER_OF_PLACES.getValue()));
                ((SightseeingVoucher)touristVoucher).setNumberOfPlaces(numberOfPlaces);
            }
            case WEEKEND_VOUCHER -> {
                int numberOfNights = Integer
                                     .parseInt(getElementTextContent(touristVoucherElement,
                                              TouristVoucherType.NUMBER_OF_NIGHTS.getValue()));
                ((WeekendVoucher)touristVoucher).setNumberOfNights(numberOfNights);
            }
            default -> throw new ParserException("Unexpected value: " + type);
        }
        return touristVoucher;
    }
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
