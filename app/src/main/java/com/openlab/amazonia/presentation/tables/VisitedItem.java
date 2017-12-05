package com.openlab.amazonia.presentation.tables;


import com.openlab.amazonia.data.entities.ProductEntity;
import com.openlab.amazonia.data.entities.VisitedEntity;

/**
 * Created by katherine on 31/05/17.
 */

public interface VisitedItem {
    void clickItem(VisitedEntity visitedEntity);

   // void deleteItem(ReservationEntity ticketEntity, int position);
}
