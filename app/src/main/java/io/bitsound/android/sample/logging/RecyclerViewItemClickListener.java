package io.bitsound.android.sample.logging;

import android.view.View;


public interface RecyclerViewItemClickListener {
    /**
     * Callback function for item click within RecyclerView
     * @param view view of clicked item
     * @param position position in adapter
     */
    void onRecyclerViewItemClick(View view, int position);
}
