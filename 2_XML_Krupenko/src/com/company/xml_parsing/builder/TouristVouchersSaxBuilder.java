package com.company.xml_parsing.builder;

import com.company.xml_parsing.entity.TouristVoucher;
import com.company.xml_parsing.handler.TouristVoucherErrorHandler;
import com.company.xml_parsing.handler.TouristVoucherHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

import static com.company.xml_parsing.handler.TouristVoucherErrorHandler.logger;

public class TouristVouchersSaxBuilder extends AbstractTouristVouchersBuilder {
    private TouristVoucherHandler handler = new TouristVoucherHandler();
    private XMLReader reader;
    public TouristVouchersSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException e) {
            logger.error("Parser configuration exception", e);
        } catch (SAXException e) {
            logger.error("SAX parser exception", e);
        }
        reader.setErrorHandler(new TouristVoucherErrorHandler());
        reader.setContentHandler(handler);
    }
    @Override
    public Set<TouristVoucher> getTouristVouchers() {
        return touristVouchers;
    }
    public void buildSetTouristVouchers(String filename) {
        try {
            reader.parse(filename);
        } catch (SAXException e) {
            logger.error("SAX parser exception in " + filename, e);
        } catch (IOException e) {
            logger.error("I/O exception", e);
        }
        touristVouchers = handler.getTouristVouchers();
    }
}
