package com.example.victo.virtualstore.HELPER;

import java.text.NumberFormat;

/**
 * Created by munirwanis on 07/12/17.
 */

public class CurrencyHelper {
   public static String parseDoubleToCurrency(Double value) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(value);
    }
}
