package com.company.xml_parsing.parsing;

import com.company.xml_parsing.exception.ParserException;

import java.time.LocalDate;

public class LocalDateParsing {
    public static LocalDate convertStringToLocalDate(String string) throws ParserException {
        if(string.length() != 10) {
            throw new ParserException(string + " is not a date");
        }
        int year;
        int month;
        int day;
        try {
            year = Integer.parseInt(string.substring(0, 4));
            month = Integer.parseInt(string.substring(5, 7));
            day = Integer.parseInt(string.substring(8, 10));
        } catch (NumberFormatException e) {
            throw new ParserException("input string " + string + " is incorrect", e);
        }
        return LocalDate.of(year, month, day);
    }
}
