package gov.mf.dgb.rpp.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

class NumberFormatter {
    private static final DecimalFormat SPACE_GROUPING;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator(' ');
        SPACE_GROUPING = new DecimalFormat("#,###", symbols);
    }

    public static String format(long number) {
        return SPACE_GROUPING.format(number);
    }
}
