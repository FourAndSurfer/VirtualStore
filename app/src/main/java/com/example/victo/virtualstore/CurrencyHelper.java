package com.example.victo.virtualstore;

import java.text.NumberFormat;

/**
 * Created by munirwanis on 07/12/17.
 */

class CurrencyHelper {
    static String parseDoubleToCurrency(Double value) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(value);
    }
}
