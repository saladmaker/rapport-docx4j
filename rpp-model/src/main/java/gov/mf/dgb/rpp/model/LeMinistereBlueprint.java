package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Prototype;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

@Prototype.Blueprint(createEmptyPublic = false)
@Prototype.CustomMethods(LeMinistereBlueprint.CustomMethods.class)
interface LeMinistereBlueprint extends Writable{

    String MNSTR_1_TITLE_KEY = "section1.leministere.title.text";
    String MNSTR_2_ORG_TITLE_KEY = "section1.leministere.organigramme.title.text";

    byte[] image();

    @Override
    default void write(WordprocessingMLPackage document, GenerationContext context){
        context.applyLayout(PageLayout.LANDSCAPE);
        context.addStaticContent(HEADING_2_STYLE, MNSTR_1_TITLE_KEY);
        context.addStaticContent(PARAGRAPH_STYLE, MNSTR_2_ORG_TITLE_KEY);
        context.insertScaledImage(image(), IMG_STYLE, 0.8, 0.2);
    }


    final class CustomMethods{
        @Prototype.FactoryMethod
        static LeMinistere create(byte[] image){
            return LeMinistere.builder()
                    .image(image)
                    .build();
        }
    }

}
