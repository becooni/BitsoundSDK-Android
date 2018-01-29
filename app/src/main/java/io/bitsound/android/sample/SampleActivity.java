package io.bitsound.android.sample;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.soundlly.soundllyplayer.SoundllyPlayer;
import com.soundlly.soundllyplayer.SoundllyPlayerResultListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.bitsound.Bitsound;
import io.bitsound.BitsoundContents;
import io.bitsound.Stringify;
import io.bitsound.android.sample.logging.TextLogAdapter;
import io.bitsound.android.sample.logging.TextLogData;
import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import timber.log.Timber;


public class SampleActivity extends AppCompatActivity {

    /* For Requesting Mic Permission */
    private static final int REQUEST_CODE_RECORD_AUDIO = 3;

    @BindView(R.id.bitsound_appkey) protected TextView mBitsoundAppKeyText;
    @BindView(R.id.bitsound_shake_enable) protected ImageView mBitsoundEnableShake;
    @BindView(R.id.bitsound_shake_disable) protected ImageView mBitsoundDisableShake;
    @BindView(R.id.bitsound_detection_single_start) protected ImageView mBitsoundStartSingleDetection;
    @BindView(R.id.bitsound_detection_single_stop) protected ImageView mBitsoundStopSingleDetection;
    @BindView(R.id.bitsound_detection_periodic_start) protected ImageView mBitsoundStartPeriodicDetection;
    @BindView(R.id.bitsound_detection_periodic_stop) protected ImageView mBitsoundStopPeriodicDetection;
    @BindView(R.id.soundlly_player_beacon) protected EditText mSoundllyPlayerBeacon;
    @BindView(R.id.soundlly_player_start) protected ImageView mSoundllyPlayerStart;
    @BindView(R.id.soundlly_player_stop) protected ImageView mSoundllyPlayerStop;

    @BindView(R.id.text_logger) protected RecyclerView mTextLogger;

    @BindColor(R.color.grey) protected int GREY;
    @BindColor(R.color.green) protected int GREEN;
    @BindColor(R.color.red) protected int RED;


    private ActionBar mActionBar;
    private TextLogAdapter mTextLogAdapter;

    private static final View.OnTouchListener ALPHA_EFFECT = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    view.setAlpha(0.5f);
                    break;
                case MotionEvent.ACTION_UP:
                    view.setAlpha(1.0f);
                    break;
            }
            return false;
        }
    };

    private BroadcastReceiver mBitsoundContentsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Timber.d("---> Received %s", intent);
            if (!SampleBitsoundContentsReceiver.ACTION_BITSOUND_CONTENTS.equals(intent.getAction())) return;

            final int code = intent.getIntExtra(SampleBitsoundContentsReceiver.EXTRA_BITSOUND_CODE, Bitsound.UNEXPECTED_FAILURE);
            final BitsoundContents contents = intent.getParcelableExtra(SampleBitsoundContentsReceiver.EXTRA_BITSOUND_CONTENTS);
            Timber.d("---> %s %s", Stringify.result(code), contents);
            SampleActivity.this.textLogging("code : %s(0x%x)\n contents : %s)", Stringify.result(code), code, contents);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(SampleActivity.this);

        mBitsoundAppKeyText.setText("AppKey : " + getBitsoundAppKey(this));

        /* Setup ActionBar */
        mActionBar = this.getSupportActionBar();
        if (mActionBar != null) mActionBar.setTitle(Build.MODEL);

        /* Setup Text Logger */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        List<TextLogData> mTextLogDataList = new ArrayList<>();
        mTextLogAdapter = new TextLogAdapter(mTextLogDataList);
        mTextLogger.setAdapter(mTextLogAdapter);
        mTextLogger.setHasFixedSize(true);
        mTextLogger.setLayoutManager(layoutManager);

        /* Initialize Dashboard */
        initializeBitsoundShakeDashboard();
        initializeBitsoundSingleDetectionDashboard();
        initializeBitsoundPeriodicDetectionDashboard();
        initializeSoundllyPlayerDashboard();

        /* Register Broadcast Receiver */
        this.registerReceiver(mBitsoundContentsReceiver, new IntentFilter(SampleBitsoundContentsReceiver.ACTION_BITSOUND_CONTENTS));
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(mBitsoundContentsReceiver);
        super.onDestroy();
    }

    /* Bitsound Shake Dashboard Listeners */
    private void initializeBitsoundShakeDashboard() {
        mBitsoundEnableShake.setOnTouchListener(ALPHA_EFFECT);
        mBitsoundDisableShake.setOnTouchListener(ALPHA_EFFECT);
        Picasso.with(this).load(R.drawable.ic_play_arrow_white_24dp).transform(new ColorFilterTransformation(GREEN)).into(mBitsoundEnableShake);
        Picasso.with(this).load(R.drawable.ic_pause_white_24dp).transform(new ColorFilterTransformation(RED)).into(mBitsoundDisableShake);
    }

    @OnClick(R.id.bitsound_shake_enable)
    protected void onBitsoundEnableShake() {
        this.textLoggingBold(R.string.bitsound_shake_enable);
        int result = Bitsound.with(this).enableShake(true, 1_000L);
        this.textLogging(Stringify.result(result));
    }

    @OnClick(R.id.bitsound_shake_disable)
    protected void onBitsoundDisableShake() {
        this.textLoggingBold(R.string.bitsound_shake_disable);
        int result = Bitsound.with(this).disableShake();
        this.textLogging(Stringify.result(result));
    }

    /* Bitsound Single Detection Dashboard Listeners */
    private void initializeBitsoundSingleDetectionDashboard() {
        mBitsoundStartSingleDetection.setOnTouchListener(ALPHA_EFFECT);
        mBitsoundStopSingleDetection.setOnTouchListener(ALPHA_EFFECT);
        Picasso.with(this).load(R.drawable.ic_play_arrow_white_24dp).transform(new ColorFilterTransformation(GREEN)).into(mBitsoundStartSingleDetection);
        Picasso.with(this).load(R.drawable.ic_pause_white_24dp).transform(new ColorFilterTransformation(RED)).into(mBitsoundStopSingleDetection);
    }

    @OnClick(R.id.bitsound_detection_single_start)
    public void onBitsoundStartSingleDetection() {
        this.textLoggingBold(R.string.bitsound_detection_single_start);
        int result = Bitsound.with(this).startSingleDetection(SampleBitsoundContentsReceiver.class);
        this.textLogging(Stringify.result(result));
    }

    @OnClick(R.id.bitsound_detection_single_stop)
    public void onBitsoundStopSingleDetection() {
        this.textLoggingBold(R.string.bitsound_detection_single_stop);
        int result = Bitsound.with(this).stopSingleDetection();
        this.textLogging(Stringify.result(result));
    }

    /* Bitsound Periodic Detection Dashboard Listeners */
    private void initializeBitsoundPeriodicDetectionDashboard() {
        mBitsoundStartPeriodicDetection.setOnTouchListener(ALPHA_EFFECT);
        mBitsoundStopPeriodicDetection.setOnTouchListener(ALPHA_EFFECT);
        Picasso.with(this).load(R.drawable.ic_play_arrow_white_24dp).transform(new ColorFilterTransformation(GREEN)).into(mBitsoundStartPeriodicDetection);
        Picasso.with(this).load(R.drawable.ic_pause_white_24dp).transform(new ColorFilterTransformation(RED)).into(mBitsoundStopPeriodicDetection);
    }
    @OnClick(R.id.bitsound_detection_periodic_start)
    protected void onBitsoundStartPeriodicDetection() {
        this.textLoggingBold(R.string.bitsound_detection_periodic_start);
        int result = Bitsound.with(this).startPeriodicDetection(SampleBitsoundContentsReceiver.class);
        this.textLogging(Stringify.result(result));
    }
    @OnClick(R.id.bitsound_detection_periodic_stop)
    protected void onBitsoundStopPeriodicDetection() {
        this.textLoggingBold(R.string.bitsound_detection_periodic_stop);
        int result = Bitsound.with(this).stopPeriodicDetection();
        this.textLogging(Stringify.result(result));
    }

    /* SoundllyPlayer Dashboard Listeners */
    private static final String SOUNDLLY_APP_KEY = "75d97ae5-0e2d-4322-82cd-9770d129cd04"; // Same with Bitsound AppKey in AndroidManifest.xml
    private SoundllyPlayerResultListener mPlayerListener;
    private void initializeSoundllyPlayerDashboard() {
        mSoundllyPlayerStart.setOnTouchListener(ALPHA_EFFECT);
        mSoundllyPlayerStop.setOnTouchListener(ALPHA_EFFECT);
        Picasso.with(this).load(R.drawable.ic_play_arrow_white_24dp).transform(new ColorFilterTransformation(GREEN)).into(mSoundllyPlayerStart);
        Picasso.with(this).load(R.drawable.ic_pause_white_24dp).transform(new ColorFilterTransformation(RED)).into(mSoundllyPlayerStop);
    }
    @OnClick(R.id.soundlly_player_start)
    protected void onSoundllyPlayerStart() {
        try {
            final Long beacon = Long.valueOf(mSoundllyPlayerBeacon.getText().toString());
            this.textLoggingBold(R.string.soundlly_player_start);
            if (beacon >= 1_000_000L) {
                this.textLogging("Beacon ID(#%d) must be smaller than 1,000,000", beacon);
                return;
            }
            if (mPlayerListener != null) {
                SampleActivity.this.textLogging("Playing Beacon #%d with Existing Listener", beacon);
                SoundllyPlayer.play(beacon);
            } else SoundllyPlayer.init(this, SOUNDLLY_APP_KEY, mPlayerListener = new SoundllyPlayerResultListener() {

                @Override
                public void onInitialized() {
                    SampleActivity.this.textLogging("Playing Beacon #%d in onInitialized()", beacon);
                    SoundllyPlayer.play(beacon);
                }

                @Override
                public void onPrepared(long beacon) {
                    SampleActivity.this.textLogging("SoundllyPlayer Prepared with Beacon #%d", beacon);
                    SoundllyPlayer.play(beacon);
                }

                @Override
                public void onError(int errorCode) {
                    switch (errorCode) {
                        case SoundllyPlayerResultListener.ERROR_AUTHENTICATION:
                            SampleActivity.this.textLogging("[Error] Authentication Failure : Invalid AppKey");
                            break;
                        case SoundllyPlayerResultListener.ERROR_INVALID_SOUNDLLY_ID:
                            SampleActivity.this.textLogging("[Error] Invalid Soundlly Beacon ID");
                            break;
                        case SoundllyPlayerResultListener.ERROR_NETWORK:
                            SampleActivity.this.textLogging("[Error] Network Error");
                            break;
                        case SoundllyPlayerResultListener.ERROR_PLAYBACK:
                            SampleActivity.this.textLogging("[Error] Playback Error");
                            break;
                        default:
                            SampleActivity.this.textLogging("[Error] Unexpected Code : %d", errorCode);
                            break;
                    }
                }

                @Override
                public void onStateChanged(int stateCode) {
                    switch (stateCode) {
                        case SoundllyPlayerResultListener.PLAY_STARTED: // Playback Started
                            SampleActivity.this.textLogging("[State] Sound Beacon Play Started");
                            break;
                        case SoundllyPlayerResultListener.PLAY_STOPPING: // .stop() is called
                            SampleActivity.this.textLogging("[State] Sound Beacon Play Stopping");
                            break;
                        case SoundllyPlayerResultListener.PLAY_DONE: // Playback Finished
                            SampleActivity.this.textLogging("[State] Sound Beacon Play Done");
                            break;
                        default:
                            SampleActivity.this.textLogging("[State] Unexpected Code : %d", stateCode);
                            break;
                    }
                }
            });
        } catch (NumberFormatException e) {
            Timber.e(e);
        }
    }
    @OnClick(R.id.soundlly_player_stop)
    protected void onSoundllyPlayerStop() {
        this.textLoggingBold(R.string.soundlly_player_stop);
        SoundllyPlayer.stop();
        this.textLogging("SoundllyPlayer Stopped");
    }

    /* Menu */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_clear_text_log:
                mTextLogAdapter.clear();
                this.textLoggingBold(R.string.menu_clear_text_log);
                return true;
            case R.id.menu_mic_permission:
                this.textLoggingBold(R.string.menu_mic_permission);
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                    this.textLogging(R.string.permission_mic_granted);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_RECORD_AUDIO);
                }
            default: return super.onOptionsItemSelected(item);
        }
    }

    /* Android Permission for API>23 */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        boolean hasRecordPermission = false;
        for (String permission : permissions) {
            if (Manifest.permission.RECORD_AUDIO.equals(permission) && PackageManager.PERMISSION_GRANTED == grantResults[0]) {
                hasRecordPermission = true;
            }
        }

        this.textLogging(hasRecordPermission? R.string.permission_mic_granted : R.string.permission_mic_denied);
    }

    /* TextLogging */
    private void textLogging(int resid) {
        this.textLogging(App.string(resid));
    }
    private void textLogging(String format, Object... args) {
        String message = String.format(Locale.getDefault(), format, args);
        Timber.d(message);
        TextLogData model = TextLogData.content(message);
        mTextLogAdapter.add(model);
        mTextLogger.smoothScrollToPosition(mTextLogAdapter.getItemCount());
    }

    private void textLoggingBold(int resid) {
        this.textLoggingBold(App.string(resid));
    }
    private void textLoggingBold(String format, Object... args) {
        String message = String.format(Locale.getDefault(), format, args);
        Timber.d(message);
        TextLogData model = TextLogData.title(message);
        mTextLogAdapter.add(model);
        mTextLogger.smoothScrollToPosition(mTextLogAdapter.getItemCount());
    }

    /* Bitsound AppKey */
    @Nullable
    public static String getBitsoundAppKey(@NonNull Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle metaData = appInfo.metaData;
            return metaData.getString("io.bitsound.AppKey");
        } catch (PackageManager.NameNotFoundException exception) {
            Timber.e(exception);
            return "APPKEY NOT FOUND";
        }
    }
}
