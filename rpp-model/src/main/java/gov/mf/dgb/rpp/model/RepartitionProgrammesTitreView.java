package gov.mf.dgb.rpp.model;

import java.util.List;
import java.util.Objects;

//todo add mf version neat solution for orientation switch
//todo refactor all views should start with ViewXXX
public interface RepartitionProgrammesTitreView {
    List<RepartitionTitre> delegates();

    default List<RepartitionTitreView> repartitions(){
        return delegates().stream()
                .map(RepartitionTitreView::new)
                .toList();
    }

    default boolean requireLandscape(){
        return delegates().stream()
                .anyMatch(RepartitionTitre::isMF);
    }

    interface RepartitionProgrammesTitreViewFR extends RepartitionProgrammesTitreView{
        static RepartitionProgrammesTitreViewFR of(List<RepartitionTitre> delegates){
            return () -> delegates;
        }

    }

    interface RepartitionProgrammesTitreViewAR extends RepartitionProgrammesTitreView{
        static RepartitionProgrammesTitreViewAR of(List<RepartitionTitre> delegates){
            return () -> delegates;
        }
    }

    static RepartitionProgrammesTitreView of(List<RepartitionTitre> delegates, LanguageDirection direction){
        Objects.requireNonNull(delegates);
        Objects.requireNonNull(direction);

        return switch (direction){
            case LTR -> RepartitionProgrammesTitreView.RepartitionProgrammesTitreViewFR.of(delegates);
            case RTL -> RepartitionProgrammesTitreView.RepartitionProgrammesTitreViewAR.of(delegates);
        };
    }
    record RepartitionTitreView(RepartitionTitre delegate){

        public String name() {
            return delegate.name();
        }

        public String titre1() {
            return NumberFormatter.format(delegate.titre1());
        }

        public String titre2() {
            return NumberFormatter.format(delegate.titre2());
        }

        public String titre3() {
            return NumberFormatter.format(delegate.titre3());
        }

        public String titre4() {
            return NumberFormatter.format(delegate.titre4());
        }

        public String titre5() {
            return NumberFormatter.format(delegate.titre5());
        }

        public String titre6() {
            return NumberFormatter.format(delegate.titre6());
        }

        public String titre7() {
            return NumberFormatter.format(delegate.titre7());
        }

        String total(){
            return NumberFormatter.format(delegate.total());
        }

    }

    default String totalTitre1(){
        var total= delegates().stream()
                .mapToLong(RepartitionTitre::titre1)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalTitre2(){
        var total = delegates().stream()
                .mapToLong(RepartitionTitre::titre2)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalTitre3(){
        var total = delegates().stream()
                .mapToLong(RepartitionTitre::titre3)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalTitre4(){
        var total = delegates().stream()
                .mapToLong(RepartitionTitre::titre4)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalTitre5(){
        var total = delegates().stream()
                .mapToLong(RepartitionTitre::titre5)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalTitre6(){
        var total = delegates().stream()
                .mapToLong(RepartitionTitre::titre6)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalTitre7(){
        var total = delegates().stream()
                .mapToLong(RepartitionTitre::titre7)
                .sum();
        return NumberFormatter.format(total);
    }
    default String globalTotal(){
        var total = delegates().stream()
                .mapToLong(RepartitionTitre::total)
                .sum();
        return NumberFormatter.format(total);
    }

}
