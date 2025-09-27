package gov.mf.dgb.rpp.model;

public enum LanguageDirection {
    
    LTR,
    RTL;
    static final String ESCAPER = "\u061C";

    String escape(String text){
        return requireRTL(text, this) ? text + ESCAPER : text;
    }
    static boolean requireRTL(String text, LanguageDirection direction) {
        if (text == null || text.isEmpty() || (direction == LanguageDirection.LTR)) {
            return false;
        }

        // Get last code point of the string
        int cp = text.codePointBefore(text.length());
        byte dir = Character.getDirectionality(cp);

        // Check if it's neutral according to Unicode bidi
        return switch (dir) {
            case // , . / :
                    Character.DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR, // + -
                    Character.DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR, // . ,
                    Character.DIRECTIONALITY_COMMON_NUMBER_SEPARATOR, // quotes, (), …
                    Character.DIRECTIONALITY_OTHER_NEUTRALS ->
                true;
            default -> false;
        };
    }
}
