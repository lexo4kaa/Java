package com.company.xml_parsing.factory;

import com.company.xml_parsing.builder.AbstractTouristVouchersBuilder;
import com.company.xml_parsing.builder.TouristVouchersDomBuilder;
import com.company.xml_parsing.builder.TouristVouchersSaxBuilder;
import com.company.xml_parsing.builder.TouristVouchersStaxBuilder;

public class TouristVoucherBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    public static AbstractTouristVouchersBuilder createTouristVoucherBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM -> {
                return new TouristVouchersDomBuilder();
            }
            case STAX -> {
                return new TouristVouchersStaxBuilder();
            }
            case SAX -> {
                return new TouristVouchersSaxBuilder();
            }
            default -> throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
