package com.dalexa.business.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

public class LocalDatetimeAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final DateTimeFormatter OLD_LOCAL_DATE_TIME = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(ISO_LOCAL_DATE)
                .appendLiteral(' ')
                .append(ISO_LOCAL_TIME)
                .toFormatter();

    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        if (v == null || "".equals(v.trim())) {
            return null;
        }
        if (v.contains("T")) {
            return LocalDateTime.parse(v, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        else {
            return LocalDateTime.parse(v, OLD_LOCAL_DATE_TIME);
        }
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return v.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}