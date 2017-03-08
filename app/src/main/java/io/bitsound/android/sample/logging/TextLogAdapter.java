package io.bitsound.android.sample.logging;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import io.bitsound.android.sample.R;
import io.bitsound.android.sample.logging.viewholder.HeaderViewHolder;
import io.bitsound.android.sample.logging.viewholder.TextLogViewHolder;
import io.bitsound.android.sample.logging.viewholder.ViewHolderFactory;


public class TextLogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int MAX_TEXT_LOGS = 500;

    private List<TextLogData> mTextLogDataList;
    private RecyclerViewItemObjectClickListener mRecyclerViewItemObjectClickListener;
    private int mIndexHeader;  // Always at 0-index position
    private int mIndexContent; // Comes right after Header
    private int mIndexFooter;  // May come after Content

    public TextLogAdapter(@NonNull List<TextLogData> initial) {
        this(initial, null);
    }
    public TextLogAdapter(@NonNull List<TextLogData> initial, @Nullable RecyclerViewItemObjectClickListener listener) {
        mTextLogDataList = initial;
        mRecyclerViewItemObjectClickListener = listener;
        mIndexHeader = 0;
        mIndexContent = mIndexHeader + 1;
        mIndexFooter = mTextLogDataList.size() + mIndexContent;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolderFactory.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position <= mIndexHeader) ((HeaderViewHolder) holder).bind(R.color.white);
        else switch (mTextLogDataList.get(position - mIndexContent).getViewType()) {
            case ViewHolderFactory.ViewType.TEXT_LOG:
                ((TextLogViewHolder) holder).bind(mTextLogDataList.get(position - mIndexContent));
                break;
            default: break;
        }
    }

    @Override
    public int getItemCount() {
        if (mTextLogDataList == null) return mIndexContent;
        else return mTextLogDataList.size() + mIndexContent;
    }

    @Override
    public int getItemViewType(int position) {
        if (position <= mIndexHeader) return ViewHolderFactory.ViewType.HEADER;
        else return mTextLogDataList.get(position - 1).getViewType();
    }

    private void reconfigure() {
        mIndexHeader = 0;
        mIndexContent = mIndexHeader + 1;
        mIndexFooter = mTextLogDataList.size() + mIndexContent;
        notifyDataSetChanged();
    }

    public void add(TextLogData data) {
        synchronized (TextLogAdapter.class) {
            mTextLogDataList.add(data);
            while (mTextLogDataList.size() > MAX_TEXT_LOGS) {
                mTextLogDataList.remove(0); // Drop Old TextLog
            }
            reconfigure();
        }
    }

    public void clear() {
        synchronized (TextLogAdapter.class) {
            mTextLogDataList.clear();
            reconfigure();
        }
    }
}
