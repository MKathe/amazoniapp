package com.openlab.amazonia.presentation.datalist;

import com.openlab.amazonia.data.entities.ListEntity;

/**
 * Created by katherine on 24/04/17.
 */

public interface ListItem {

    void clickItem(ListEntity listEntity);

    void deleteItem(ListEntity listEntity, int position);
}
