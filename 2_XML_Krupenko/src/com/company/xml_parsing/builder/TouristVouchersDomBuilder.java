package com.company.xml_parsing.builder;

import com.company.xml_parsing.entity.HotelCharacteristic;
import com.company.xml_parsing.entity.TouristVoucher;
import com.company.xml_parsing.exception.ParserException;
import com.company.xml_parsing.parsing.LocalDateParsing;
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

import static com.company.xml_parsing.validation.TouristVoucherErrorHandler.logger;

public class TouristVouchersDomBuilder {
    private Set<TouristVoucher> touristVouchers;
    private DocumentBuilder docBuilder;
    public TouristVouchersDomBuilder() {
        touristVouchers = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error(e);
        }
    }
    public Set<TouristVoucher> getTouristVouchers() {
        return touristVouchers;
    }
    public void buildSetTouristVouchers(String filename) {
        Document doc;
        try {
            doc = docBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            NodeList touristVouchersList = root.getElementsByTagName("tourist-voucher");
            for (int i = 0; i < touristVouchersList.getLength(); i++) {
                Element touristVouchersElement = (Element)touristVouchersList.item(i);
                TouristVoucher touristVoucher = buildSetTouristVouchers(touristVouchersElement);
                touristVouchers.add(touristVoucher);
            }
        } catch (IOException | SAXException | ParserException e) {
            logger.error(e);
        }
    }
    private TouristVoucher buildSetTouristVouchers(Element touristVoucherElement) throws ParserException {
        TouristVoucher touristVoucher = new TouristVoucher();
        touristVoucher.setType(touristVoucherElement.getAttribute("type"));
        touristVoucher.setTransport(touristVoucherElement.getAttribute("transport"));
        touristVoucher.setVoucherNumber(getElementTextContent(touristVoucherElement, "voucher-number"));
        touristVoucher.setCountry(getElementTextContent(touristVoucherElement, "country"));
        int numberOfDays = Integer
                .parseInt(getElementTextContent(touristVoucherElement, "number-of-days"));
        touristVoucher.setNumberOfDays(numberOfDays);
        LocalDate date = LocalDateParsing
                .convertStringToLocalDate(getElementTextContent(touristVoucherElement, "start-date"));
        touristVoucher.setStartDate(date);
        int cost = Integer
                .parseInt(getElementTextContent(touristVoucherElement, "cost"));
        touristVoucher.setCost(cost);
        HotelCharacteristic hotelCharacteristic = touristVoucher.getHotelCharacteristic();
        Element hotelCharacteristicElement =
                (Element)touristVoucherElement.getElementsByTagName("hotel-characteristic").item(0);
        String foodType = hotelCharacteristicElement.getAttribute("food-type");
        if(foodType.equals("")) {
            foodType = "HB";
        }
        hotelCharacteristic.setFoodType(foodType);
        int stars = Integer
                .parseInt(getElementTextContent(hotelCharacteristicElement, "stars"));
        hotelCharacteristic.setStars(stars);
        int rooms = Integer
                .parseInt(getElementTextContent(hotelCharacteristicElement, "rooms"));
        hotelCharacteristic.setRooms(rooms);
        return touristVoucher;
    }
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

    public static void main(String[] args) {
        TouristVouchersDomBuilder domBuilder = new TouristVouchersDomBuilder();
        domBuilder.buildSetTouristVouchers("files/TouristVouchers.xml");
        System.out.println(domBuilder.getTouristVouchers());
    }
}
