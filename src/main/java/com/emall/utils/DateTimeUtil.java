package com.emall.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by kimvra on 2018/12/27
 */
public class DateTimeUtil {
    public static final String STANDARD_FORMAT = "yyyy-MM-dd";

    public static Date str2Date(String dateTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        return dateTimeFormatter.parseDateTime(dateTimeStr).toDate();
    }


}
