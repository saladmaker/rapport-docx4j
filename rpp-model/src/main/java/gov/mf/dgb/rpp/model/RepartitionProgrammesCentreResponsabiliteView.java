package gov.mf.dgb.rpp.model;

interface RepartitionProgrammesCentreResponsabiliteView {

    record RepartitionCentreResponsabiliteView(RepartitionCentreResponsabilite delegate)
            implements RepartitionCentreResponsabilite{

        @Override
        public String name() {
            return delegate().name();
        }

        @Override
        public Long servicesCentraux() {
            return delegate.servicesCentraux();
        }

        @Override
        public Long servicesDeconcentres() {
            return delegate.servicesDeconcentres();
        }

        @Override
        public Long organismesSousTutelles() {
            return delegate.organismesSousTutelles();
        }

        @Override
        public Long organesTerritoriaux() {
            return delegate.organesTerritoriaux();
        }

        @Override
        public Long autreOrganismesSousTutelles() {
            return delegate.autreOrganismesSousTutelles();
        }
        String total(){
            var total = servicesCentraux() +
                    servicesDeconcentres() +
                    organismesSousTutelles() +
                    organesTerritoriaux() +
                    autreOrganismesSousTutelles();
            return NumberFormatter.format(total);
        }
    }
}
