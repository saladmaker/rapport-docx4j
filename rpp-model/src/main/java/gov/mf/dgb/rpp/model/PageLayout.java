package gov.mf.dgb.rpp.model;

import java.math.BigInteger;

import org.docx4j.wml.SectPr.PgMar;
import org.docx4j.wml.SectPr.PgSz;
import org.docx4j.wml.STPageOrientation;

public enum PageLayout {
    PORTRAIT(BigInteger.valueOf(11906), BigInteger.valueOf(16838), STPageOrientation.PORTRAIT,
            BigInteger.valueOf(1440)),
    LANDSCAPE(BigInteger.valueOf(16838), BigInteger.valueOf(11906), STPageOrientation.LANDSCAPE,
            BigInteger.valueOf(1440));

    public final BigInteger width;
    public final BigInteger height;
    public final STPageOrientation orientation;
    public final BigInteger margin;

    PageLayout(BigInteger width, BigInteger height, STPageOrientation orientation, BigInteger margin) {
        this.width = width;
        this.height = height;
        this.orientation = orientation;
        this.margin = margin;
    }

    public BigInteger usableWidth() {
        return width.subtract(margin.multiply(BigInteger.valueOf(2)));
    }

    public BigInteger usableHeight() {
        return height.subtract(margin.multiply(BigInteger.valueOf(2)));
    }

    public PgSz toPgSz() {
        PgSz sz = new PgSz();
        sz.setW(width);
        sz.setH(height);
        sz.setOrient(orientation);
        return sz;
    }

    public PgMar toPgMar() {
        PgMar mar = new PgMar();
        mar.setTop(margin);
        mar.setBottom(margin);
        mar.setLeft(margin);
        mar.setRight(margin);
        return mar;
    }
}