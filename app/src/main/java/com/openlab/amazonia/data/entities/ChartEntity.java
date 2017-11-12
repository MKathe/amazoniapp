package com.openlab.amazonia.data.entities;

import java.io.Serializable;

/**
 * Created by katherine on 12/11/17.
 */

public class ChartEntity implements Serializable {

    private int total;
    private int foreign;
    private int national;
    private int exonerated;
    private int foreign_percent;
    private int national_percent;
    private int exonerated_percent;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getForeign() {
        return foreign;
    }

    public void setForeign(int foreign) {
        this.foreign = foreign;
    }

    public int getNational() {
        return national;
    }

    public void setNational(int national) {
        this.national = national;
    }

    public int getExonerated() {
        return exonerated;
    }

    public void setExonerated(int exonerated) {
        this.exonerated = exonerated;
    }

    public int getForeign_percent() {
        return foreign_percent;
    }

    public void setForeign_percent(int foreign_percent) {
        this.foreign_percent = foreign_percent;
    }

    public int getNational_percent() {
        return national_percent;
    }

    public void setNational_percent(int national_percent) {
        this.national_percent = national_percent;
    }

    public int getExonerated_percent() {
        return exonerated_percent;
    }

    public void setExonerated_percent(int exonerated_percent) {
        this.exonerated_percent = exonerated_percent;
    }
}
