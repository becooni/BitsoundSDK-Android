package io.bitsound.android.sample.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import java.util.Locale;

import io.bitsound.android.sample.MainActivity;
import io.bitsound.receiver.BitsoundContents;
import io.bitsound.smarton.BitsoundSmartOn;
import timber.log.Timber;


public class BitsoundSmartOnReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final int resultCode = intent.getIntExtra(BitsoundSmartOn.EXTRA_BITSOUND_SMARTON_CONTENTS_RESULT_CODE, BitsoundContents.Result.SIGNAL_NOT_FOUND);
        final int sequence = intent.getIntExtra(BitsoundSmartOn.EXTRA_BITSOUND_SMARTON_CONTENTS_SEQUENCE, 0);
        final String name = intent.getStringExtra(BitsoundSmartOn.EXTRA_BITSOUND_SMARTON_CONTENTS_NAME);
        final String comment = intent.getStringExtra(BitsoundSmartOn.EXTRA_BITSOUND_SMARTON_CONTENTS_COMMENT);
        final String url = intent.getStringExtra(BitsoundSmartOn.EXTRA_BITSOUND_SMARTON_CONTENTS_URL);

        Timber.d("[onReceive] (intent.getAction():%s) (resultCode:%d)", intent.getAction(), resultCode);
        Timber.d("[onReceive] BitsoundContents Result :");
        Timber.d("                sequence:%d", sequence);
        Timber.d("                name:%s", name);
        Timber.d("                comment:%s", comment);
        Timber.d("                url:%s", url);

        String message = String.format(Locale.getDefault(), "[onReceive] (intent.getAction():%s) (resultCode:%d)", intent.getAction(), resultCode);
        message = String.format(
            Locale.getDefault(),
            "%s\n[onReceive] BitsoundContents Received:\n    sequence:%d\n    name:%s\n    comment:%s\n    url:%s",
            message, sequence, name, comment, url
        );
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        // For MainActivity TextLogger
        Intent iBitsoundContents = new Intent(MainActivity.ACTION_RECEIVE_BITSOUND_CONTENTS).putExtras(intent.getExtras());
        LocalBroadcastManager.getInstance(context).sendBroadcast(iBitsoundContents);
    }
}
