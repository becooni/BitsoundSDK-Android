package io.bitsound.android.sample.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Locale;

import io.bitsound.android.sample.SampleActivity;
import io.bitsound.receiver.BitsoundContents;
import io.bitsound.receiver.common.Stringify;
import io.bitsound.smarton.BitsoundSmartOn;
import timber.log.Timber;


public class BitsoundSmartOnReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Timber.d("---> Received %s", intent);
        final int code = intent.getIntExtra(BitsoundSmartOn.EXTRA_BITSOUND_SMARTON_CONTENTS_RESULT_CODE, BitsoundContents.Result.SIGNAL_NOT_FOUND);
        final BitsoundContents contents = BitsoundContents.fromBytes(intent.getByteArrayExtra(BitsoundSmartOn.EXTRA_BITSOUND_SMARTON_CONTENTS_BYTES));
        Timber.d("---> %s %s", Stringify.contents(code), contents);

        Toast.makeText(context, String.format(Locale.getDefault(), "%s : %d\n%s", intent, code, contents), Toast.LENGTH_SHORT).show();

        // For SampleActivity TextLogger
        Intent iBitsoundContents = new Intent(SampleActivity.ACTION_RECEIVE_BITSOUND_CONTENTS).putExtras(intent.getExtras());
        context.sendBroadcast(iBitsoundContents);
    }
}
