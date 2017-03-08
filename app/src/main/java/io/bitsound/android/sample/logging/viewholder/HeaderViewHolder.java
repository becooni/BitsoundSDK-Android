package io.bitsound.android.sample.logging.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.bitsound.android.sample.App;


public class HeaderViewHolder extends RecyclerView.ViewHolder {

    public HeaderViewHolder(View view) {
        super(view);
    }

    public void bind(int resid) {
        itemView.setBackgroundColor(App.color(resid));
    }
}
