package com.company.xml_parsing.builder;

import com.company.xml_parsing.entity.TouristVoucher;
import com.company.xml_parsing.validation.TouristVoucherErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

import static com.company.xml_parsing.validation.TouristVoucherErrorHandler.logger;

public class TouristVouchersSaxBuilder {
    private Set<TouristVoucher> touristVouchers;
    private TouristVoucherHandler handler = new TouristVoucherHandler();
    private XMLReader reader;
    public TouristVouchersSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            logger.error(e);
        }
        reader.setErrorHandler(new TouristVoucherErrorHandler());
        reader.setContentHandler(handler);
    }
    public Set<TouristVoucher> getTouristVouchers() {
        return touristVouchers;
    }
    public void buildSetTouristVouchers(String filename) {
        try {
            reader.parse(filename);
        } catch (SAXException e) {
            logger.error("SAX parser exception: " + e);
        } catch (IOException e) {
            logger.error("I/O problems: " + e);
        }
        touristVouchers = handler.getTouristVouchers();
    }

    public static void main(String[] args) {
        TouristVouchersSaxBuilder saxBuilder = new TouristVouchersSaxBuilder();
        saxBuilder.buildSetTouristVouchers("files/TouristVouchers.xml");
        System.out.println(saxBuilder.getTouristVouchers());
    }
}
