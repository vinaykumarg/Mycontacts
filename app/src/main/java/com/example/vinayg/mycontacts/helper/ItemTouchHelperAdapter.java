package com.example.vinayg.mycontacts.helper;

/**
 * Created by vinay.g.
 */
public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
