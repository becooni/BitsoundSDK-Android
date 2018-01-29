package io.bitsound.android.sample.logging.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Locale;

import io.bitsound.android.sample.R;
import io.bitsound.android.sample.App;
import io.bitsound.android.sample.logging.RecyclerViewItemClickListener;


public class ViewHolderFactory {

    public static class ViewType {
        public static final int HEADER = 0x00;
        public static final int TEXT_LOG = 0x10;
    }

    public static RecyclerView.ViewHolder create(ViewGroup parent, int viewType) {
        return ViewHolderFactory.create(parent, viewType, null, null);
    }
    public static RecyclerView.ViewHolder create(ViewGroup parent, int viewType, RecyclerViewItemClickListener listener) {
        return ViewHolderFactory.create(parent, viewType, listener, null);
    }
    public static RecyclerView.ViewHolder create(ViewGroup parent, int viewType, RecyclerViewItemClickListener listener, Integer layoutResId) {
        final LayoutInflater inflater = LayoutInflater.from(App.getContext());
        switch(viewType) {
            case ViewType.HEADER : return new HeaderViewHolder(inflater.inflate(layoutResId != null ? layoutResId : R.layout.viewholder_header, parent, false));
            case ViewType.TEXT_LOG : return new TextLogViewHolder(inflater.inflate(layoutResId != null ? layoutResId : R.layout.viewholder_text_log, parent, false));
            default : throw new RuntimeException(String.format(Locale.getDefault(), "Cannot find any ViewHolder with ViewType#%d. Check if you put correct ViewType.", viewType));
        }
    }
}
