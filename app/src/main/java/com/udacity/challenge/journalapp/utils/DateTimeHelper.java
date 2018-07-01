/*
 *
 * Developer :   Paul Msegeya
 *
 * Email : paul.msegeya@gmail.com
 *
 * Cell  : +255756734448
 *
 * Country:  Tanzania
 *
 * Track :  Android Developer Track
 *
 *
 * Copyright (c) 2018
 */

package com.udacity.challenge.journalapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeHelper {

    public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
