package rpp.test;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public interface DocumentGenerator {
    void generate(WordprocessingMLPackage document);
}
