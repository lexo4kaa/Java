package com.company.xml_parsing.builder;

import com.company.xml_parsing.entity.TouristVoucher;
import com.company.xml_parsing.exception.ParserException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractTouristVouchersBuilder {

    protected Set<TouristVoucher> touristVouchers;

    public AbstractTouristVouchersBuilder() {
        touristVouchers = new HashSet<TouristVoucher>();
    }

    public AbstractTouristVouchersBuilder(Set<TouristVoucher> touristVouchers) {
        this.touristVouchers = touristVouchers;
    }

    public Set<TouristVoucher> getTouristVouchers() {
        return touristVouchers;
    }

    abstract public void buildSetTouristVouchers(String fileName) throws ParserException;
}
