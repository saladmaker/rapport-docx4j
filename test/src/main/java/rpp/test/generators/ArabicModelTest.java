package rpp.test.generators;

import gov.mf.dgb.rpp.model.GenerationContext;
import gov.mf.dgb.rpp.model.LaMission;
import gov.mf.dgb.rpp.model.LanguageDirection;
import gov.mf.dgb.rpp.model.Mission;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.util.Map;

public class ArabicModelTest implements DocumentGenerator {
    @Override
    public void generate(WordprocessingMLPackage document) {
        var laMission = LaMission.builder()
                .intro("--")
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
                .build();
        laMission.write(document, GenerationContext.of(LanguageDirection.RTL, document, Map.of()));
    }
}
