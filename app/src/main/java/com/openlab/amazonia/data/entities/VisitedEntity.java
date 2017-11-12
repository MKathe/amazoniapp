package com.openlab.amazonia.data.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by katherine on 12/11/17.
 */

public class VisitedEntity implements Serializable {
    private int year_2016;
    private int year_2017;
    private String anp;
    private int percent;
    private boolean negative;

    public int getYear_2016() {
        return year_2016;
    }

    public void setYear_2016(int year_2016) {
        this.year_2016 = year_2016;
    }

    public int getYear_2017() {
        return year_2017;
    }

    public void setYear_2017(int year_2017) {
        this.year_2017 = year_2017;
    }

    public String getAnp() {
        return anp;
    }

    public void setAnp(String anp) {
        this.anp = anp;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public boolean isNegative() {
        return negative;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
    }
}
