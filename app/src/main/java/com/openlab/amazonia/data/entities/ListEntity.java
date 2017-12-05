package com.openlab.amazonia.data.entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kath on 3/12/17.
 */

public class ListEntity implements Serializable {
    private int id;
    private boolean approved;
    private String date;
    private int exonerated;
    private int foreign;
    private int national;
    private int protected_natural_area;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getExonerated() {
        return exonerated;
    }

    public void setExonerated(int exonerated) {
        this.exonerated = exonerated;
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

    public int getProtected_natural_area() {
        return protected_natural_area;
    }

    public void setProtected_natural_area(int protected_natural_area) {
        this.protected_natural_area = protected_natural_area;
    }

    public String getDay(){
        if (getDate() == null ){
            return "";
        }
        Date tempDate = null;
        SimpleDateFormat parseDateFromServer= new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat parseDateForShowDetail =  new SimpleDateFormat("dd' de 'MMMM' del 'yyyy", new Locale("es","ES"));

        try {
            tempDate = parseDateFromServer.parse(getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDateForShowDetail.format(tempDate);
    }
}
