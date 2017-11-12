package com.openlab.amazonia.data.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by katherine on 12/11/17.
 */

public class ResponseVisited implements Serializable{
    private ArrayList<VisitedEntity> list_anp;

    public ArrayList<VisitedEntity> getList_anp() {
        return list_anp;
    }

    public void setList_anp(ArrayList<VisitedEntity> list_anp) {
        this.list_anp = list_anp;
    }
}
