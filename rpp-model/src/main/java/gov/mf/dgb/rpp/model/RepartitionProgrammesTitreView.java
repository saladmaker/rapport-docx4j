package gov.mf.dgb.rpp.model;

import java.util.List;

public interface RepartitionProgrammesTitreView {
    List<RepartitionTitreView> repartitions();

    default String default

    record RepartitionTitreView(RepartitionTitre delegate) implements RepartitionTitre{

        @Override
        public String name() {
            return delegate.name();
        }

        @Override
        public Long titre1() {
            return delegate.titre1();
        }

        @Override
        public Long titre2() {
            return delegate.titre2();
        }

        @Override
        public Long titre3() {
            return delegate.titre3();
        }

        @Override
        public Long titre4() {
            return delegate.titre4();
        }

        @Override
        public Long titre5() {
            return delegate.titre5();
        }

        @Override
        public Long titre6() {
            return delegate.titre6();
        }

        @Override
        public Long titre7() {
            return delegate.titre7();
        }

        String total(){
            return NumberFormatter.format(totalNumber());
        }

        long totalNumber(){
            return titre1() +
                    titre2() +
                    titre3() +
                    titre4() +
                    titre5() +
                    titre6() +
                    titre7();

        }

    }
}
