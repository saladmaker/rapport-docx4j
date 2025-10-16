package gov.mf.dgb.rpp.model;

public record RepartitionProgramme(String name, Long ae, Long cp) {
    public String aeFormatted(){
        return NumberFormatter.format(ae);
    }
    public String cpFormatted(){
        return NumberFormatter.format(cp);
    }
}
