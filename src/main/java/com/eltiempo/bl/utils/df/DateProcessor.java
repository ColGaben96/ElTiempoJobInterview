package com.eltiempo.bl.utils.df;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Gabriel Blanco
 * @version 1.0
 */
public class DateProcessor {
    /**
     * This method will return a java.util.Date, where you can set it with the format you need.
     * @return
     * @throws ParseException
     */
    public Date parse2Date(String pattern) throws ParseException {
        return new SimpleDateFormat().parse(pattern);
    }

    /**
     * This method will return a String, where you input the date and outputs a date with this format
     * <pre>Wednesday, october 10, 2021 at 1:50:01 PM</pre>
     * No format needed before.
     * @param date
     * @return
     */
    public String parse2String(Date date) {
        return new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm:ss aaa").format(date);
    }
    /**
     * This method will return a String, where you in put the date and outputs a date with this format
     * <pre>oct-10-2021 1:50:01 PM</pre>
     * No format needed before.
     * @param date
     * @return
     */
    public String parse2BasicDateTime(Date date) {
        return new SimpleDateFormat("MMM-d-yyyy h-mm-ss aaa").format(date);
    }
    /**
     * This method will return a String, where you in put the date and outputs a date with this format
     * <pre>2021-oct-10</pre>
     * No format needed before.
     * @param date
     * @return
     */
    public String parse2BasicDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
