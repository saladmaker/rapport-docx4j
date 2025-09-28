package rpp.test.generators;

import gov.mf.dgb.rpp.model.*;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.io.IOException;
import java.util.Map;

public class ArabicModel implements DocumentGenerator {
    static String intro = """
            يتمثل قطاع التكوين والتعليم المهنيين في تلبية الطلب الاجتماعي على التكوين المعبر عنه من طرف الشباب المتخرجين من التعليم الوطني، 
            وكذلك في تلبية حاجيات المؤسسات وسوق العمل من التأهيل المهني.
            
            يشمل التكوين المقدم 23 شعبة مهنية، وكل شعبة تنقسم بدورها إلى تخصصات (495).
            
            يستند تنظيم وعمل القطاع إلى القانون الإطار رقم 08-07 المؤرخ في 23 فبراير 2008، 
            والمتعلق بالقانون التوجيهي للتكوين والتعليم المهنيين.
            """;

    @Override
    public void generate(WordprocessingMLPackage document) {
        byte[] image = null;
        try {
            image = ArabicModel.class.getClassLoader().getResourceAsStream("ORGANIGRAME.jpg").readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var au = AuSujet.builder()
                .laMission(LaMission.builder()
                        .intro(intro)
                        .addMission(Mission.builder()
                                .mission("المالية العمومية")
                                .addSubMission("الجباية")
                                .addSubMission("الجمارك")
                                .addSubMission("الأملاك الوطنية والشؤون العقارية")
                                .addSubMission("النفقات العمومية، الميزانية والمحاسبة العمومية")
                                .build()
                        )
                        .addMission(Mission.create("النقد"))
                        .addMission(Mission.create("الادخار، القرض والتأمينات الاقتصادية"))
                        .addMission(Mission.create("التدخلات المالية للدولة"))
                        .addMission(Mission.create("السياسة الوطنية في مجال الاستدانة الخارجية"))
                        .addMission(Mission.create("مراقبة الصرف"))
                        .addMission(Mission.create("الرقابة المالية المتعلقة باستعمال اعتمادات ميزانية الدولة وموارد الخزينة العمومية"))
                        .addMission(Mission.create("العلاقات الاقتصادية والمالية الخارجية"))
                        .build()
                )
                .leMinistere(LeMinistere.builder()
                        .image(image)
                        .build())
                .fichePortefeuille(FichePortefeuille.builder()
                        .addRepartitionProgramme(new RepartitionProgramme("برنامج أ", 12332423L, 343423423L))
                        .addRepartitionProgramme(new RepartitionProgramme("برنامج ب", 33423423L, 3434234234L))
                        .build())
                .build();
        au.write(document, GenerationContext.of(LanguageDirection.RTL, document, Map.of()));
    }
}
