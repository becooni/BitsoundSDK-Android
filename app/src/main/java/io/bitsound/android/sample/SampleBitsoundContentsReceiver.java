package io.bitsound.android.sample;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Locale;

import io.bitsound.BitsoundContents;
import io.bitsound.BitsoundContentsReceiver;
import io.bitsound.Stringify;
import timber.log.Timber;


public class SampleBitsoundContentsReceiver extends BitsoundContentsReceiver {

    public static final String ACTION_BITSOUND_CONTENTS = "io.bitsound.intent.action.RECEIVE_BITSOUND_CONTENTS";
    public static final String EXTRA_BITSOUND_CODE = "io.bitsound.intent.extra.EXTRA_BITSOUND_CODE";
    public static final String EXTRA_BITSOUND_CONTENTS = "io.bitsound.intent.extra.EXTRA_BITSOUND_CONTENTS";

    @Override
    public void onContentsReceive(@Nullable Context context, int code, @Nullable BitsoundContents contents) {
        Timber.d("---> onContentsReceive(%s, %s(0x%x), %s)", context, Stringify.result(code), code, contents);
        if (context == null) return;

        Toast.makeText(context, String.format(Locale.getDefault(), "%s\n%s", Stringify.result(code), contents), Toast.LENGTH_SHORT).show();
        final Intent intent = new Intent(ACTION_BITSOUND_CONTENTS).putExtra(EXTRA_BITSOUND_CODE, code);
        if (contents != null) intent.putExtra(EXTRA_BITSOUND_CONTENTS, contents);
        context.sendBroadcast(intent);
    }
}
