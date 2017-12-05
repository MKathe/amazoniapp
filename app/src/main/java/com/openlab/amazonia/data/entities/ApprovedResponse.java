package com.openlab.amazonia.data.entities;

import java.io.Serializable;

/**
 * Created by kath on 5/12/17.
 */

public class ApprovedResponse implements Serializable{
    private boolean approved;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
