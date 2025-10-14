package gov.mf.dgb.rpp.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

//todo make enum formatters
class NumberFormatter {
    private static final DecimalFormat SPACE_GROUPING;
    private static final String ZERO_FORMAT = "-";
    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator(' ');
        SPACE_GROUPING = new DecimalFormat("#,###", symbols);
    }

    public static String format(long number) {
        if(0 == number) return ZERO_FORMAT;
        return SPACE_GROUPING.format(number);
    }
}
