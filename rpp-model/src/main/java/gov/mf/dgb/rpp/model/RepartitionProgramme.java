package gov.mf.dgb.rpp.model;

public record RepartitionProgramme(String name, Long ae, Long cp) {
    String aeFormatted(){
        return NumberFormatter.format(ae);
    }
    String cpFormatted(){
        return NumberFormatter.format(cp);
    }
}
