package io.bitsound.android.sample.logging;

import android.view.View;


public interface RecyclerViewItemObjectClickListener {
    /**
     * Callback function for item click within RecyclerView
     * @param view view of clicked item
     * @param object item in adapter
     */
    void onRecyclerViewItemObjectClick(View view, Object object);
}
