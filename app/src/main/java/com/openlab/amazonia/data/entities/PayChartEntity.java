package com.openlab.amazonia.data.entities;

import java.io.Serializable;

/**
 * Created by katherine on 12/11/17.
 */

public class PayChartEntity implements Serializable {

    private int total;
    private int payers;
    private int non_paying;
    private int payers_percent;
    private int non_paying_percent;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPayers() {
        return payers;
    }

    public void setPayers(int payers) {
        this.payers = payers;
    }

    public int getNon_paying() {
        return non_paying;
    }

    public void setNon_paying(int non_paying) {
        this.non_paying = non_paying;
    }

    public int getPayers_percent() {
        return payers_percent;
    }

    public void setPayers_percent(int payers_percent) {
        this.payers_percent = payers_percent;
    }

    public int getNon_paying_percent() {
        return non_paying_percent;
    }

    public void setNon_paying_percent(int non_paying_percent) {
        this.non_paying_percent = non_paying_percent;
    }
}
