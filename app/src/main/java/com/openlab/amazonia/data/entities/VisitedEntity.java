package com.openlab.amazonia.data.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by katherine on 12/11/17.
 */

public class VisitedEntity implements Serializable {
    private int this_year_foreign;
    private int this_year_national;
    private int this_year_exonerated;

    private int last_year_foreign;
    private int last_year_exonerated;
    private int last_year_national;

    private int exonerated_percent;
    private boolean exonerated_negative;

    private int foreign_percent;
    private boolean foreing_negative;


    private int national_percent;
    private boolean national_negative;

    private String anp;

    public int getThis_year_foreign() {
        return this_year_foreign;
    }

    public void setThis_year_foreign(int this_year_foreign) {
        this.this_year_foreign = this_year_foreign;
    }

    public int getThis_year_national() {
        return this_year_national;
    }

    public void setThis_year_national(int this_year_national) {
        this.this_year_national = this_year_national;
    }

    public int getThis_year_exonerated() {
        return this_year_exonerated;
    }

    public void setThis_year_exonerated(int this_year_exonerated) {
        this.this_year_exonerated = this_year_exonerated;
    }

    public int getLast_year_foreign() {
        return last_year_foreign;
    }

    public void setLast_year_foreign(int last_year_foreign) {
        this.last_year_foreign = last_year_foreign;
    }

    public int getLast_year_exonerated() {
        return last_year_exonerated;
    }

    public void setLast_year_exonerated(int last_year_exonerated) {
        this.last_year_exonerated = last_year_exonerated;
    }

    public int getLast_year_national() {
        return last_year_national;
    }

    public void setLast_year_national(int last_year_national) {
        this.last_year_national = last_year_national;
    }

    public int getExonerated_percent() {
        return exonerated_percent;
    }

    public void setExonerated_percent(int exonerated_percent) {
        this.exonerated_percent = exonerated_percent;
    }

    public boolean isExonerated_negative() {
        return exonerated_negative;
    }

    public void setExonerated_negative(boolean exonerated_negative) {
        this.exonerated_negative = exonerated_negative;
    }

    public int getForeign_percent() {
        return foreign_percent;
    }

    public void setForeign_percent(int foreign_percent) {
        this.foreign_percent = foreign_percent;
    }

    public boolean isForeing_negative() {
        return foreing_negative;
    }

    public void setForeing_negative(boolean foreing_negative) {
        this.foreing_negative = foreing_negative;
    }

    public int getNational_percent() {
        return national_percent;
    }

    public void setNational_percent(int national_percent) {
        this.national_percent = national_percent;
    }

    public boolean isNational_negative() {
        return national_negative;
    }

    public void setNational_negative(boolean national_negative) {
        this.national_negative = national_negative;
    }

    public String getAnp() {
        return anp;
    }

    public void setAnp(String anp) {
        this.anp = anp;
    }
}
