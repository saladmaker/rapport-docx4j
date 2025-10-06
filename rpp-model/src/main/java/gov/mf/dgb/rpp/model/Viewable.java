package gov.mf.dgb.rpp.model;

interface Viewable {
    GenerationContext context();
    default String escaped(String text){
        return context().direction().escape(text);
    }
}
