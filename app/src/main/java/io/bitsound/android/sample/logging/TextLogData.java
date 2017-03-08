package io.bitsound.android.sample.logging;

import android.graphics.Typeface;

import io.bitsound.android.sample.App;
import io.bitsound.android.sample.R;
import io.bitsound.android.sample.logging.viewholder.ViewHolderFactory;


public class TextLogData {

    private String mText;
    private String mTextIndent;
    private int mTextColor;
    private int mTextStyle;
    private int mViewType;

    private TextLogData(String text, int indent, int style, int color, int viewType) {
        mText = text;
        mTextIndent = new String(new char[indent]).replace('\0', ' ');
        mTextStyle = style;
        mTextColor = color;
        mViewType = viewType;
    }

    public TextLogData setText(String text) {
        mText = text;
        return this;
    }

    public TextLogData setTextIndent(int indent) {
        mTextIndent = new String(new char[indent]).replace('\0', ' ');
        return this;
    }

    public TextLogData setTextColor(int color) {
        mTextColor = color;
        return this;
    }

    public TextLogData setTextStyle(int style) {
        mTextStyle = style;
        return this;
    }

    public TextLogData setViewType(int viewType) {
        mViewType = viewType;
        return this;
    }

    public String getText() {
        return mTextIndent + mText;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public int getTextStyle() {
        return mTextStyle;
    }

    public int getViewType() {
        return mViewType;
    }

    public static TextLogData title(String text) {
        return new TextLogData(text, 0, Typeface.BOLD, App.color(R.color.black), ViewHolderFactory.ViewType.TEXT_LOG);
    }

    public static TextLogData content(String text) {
        return new TextLogData(text, 4, Typeface.NORMAL, App.color(R.color.black), ViewHolderFactory.ViewType.TEXT_LOG);
    }
}
