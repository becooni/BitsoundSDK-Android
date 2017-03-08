package io.bitsound.android.sample.logging.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.bitsound.android.sample.R;
import io.bitsound.android.sample.logging.TextLogData;


public class TextLogViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.viewholder_text_log_text) protected TextView mTextLog;

    TextLogViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(TextLogData data) {
        if (mTextLog == null) return;
        mTextLog.setText(data.getText());
        mTextLog.setTextColor(data.getTextColor());
        mTextLog.setTypeface(null, data.getTextStyle());
    }
}
