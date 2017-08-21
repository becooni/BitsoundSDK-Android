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
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

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
import io.bitsound.android.sample.logging.TextLogAdapter;
import io.bitsound.android.sample.logging.TextLogData;
import io.bitsound.receiver.Bitsound;
import io.bitsound.receiver.BitsoundContents;
import io.bitsound.receiver.BitsoundContentsListener;
import io.bitsound.receiver.common.Constant;
import io.bitsound.receiver.common.Stringify;
import io.bitsound.shaking.BitsoundShaking;
import io.bitsound.smarton.BitsoundSmartOn;
import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import timber.log.Timber;


public class SampleActivity extends AppCompatActivity {

    /* For Local Broadcasting */
    public static final String ACTION_RECEIVE_BITSOUND_CONTENTS = "io.bitsound.intent.action.RECEIVE_BITSOUND_CONTENTS";

    /* For Requesting Mic Permission */
    private static final int REQUEST_CODE_RECORD_AUDIO = 3;

    @BindView(R.id.bitsound_init) protected ImageView mBitsoundInit;
    @BindView(R.id.bitsound_release) protected ImageView mBitsoundRelease;
    @BindView(R.id.bitsound_receiver_start) protected ImageView mBitsoundStartDetection;
    @BindView(R.id.bitsound_receiver_stop) protected ImageView mBitsoundStopDetection;
    @BindView(R.id.bitsound_shaking_enable) protected ImageView mBitsoundShakingEnable;
    @BindView(R.id.bitsound_shaking_disable) protected ImageView mBitsoundShakingDisable;
    @BindView(R.id.bitsound_smarton_start) protected ImageView mBitsoundSmartOnStartScheduledDetection;
    @BindView(R.id.bitsound_smarton_stop) protected ImageView mBitsoundSmartOnStopScheduledDetection;
    @BindView(R.id.soundlly_player_beacon) protected EditText mSoundllyPlayerBeacon;
    @BindView(R.id.soundlly_player_start) protected ImageView mSoundllyPlayerStart;
    @BindView(R.id.soundlly_player_stop) protected ImageView mSoundllyPlayerStop;

    @BindView(R.id.text_logger) protected RecyclerView mTextLogger;

    @BindColor(R.color.grey) protected int GREY;
    @BindColor(R.color.green) protected int GREEN;
    @BindColor(R.color.red) protected int RED;


    private ActionBar mActionBar;
    private TextLogAdapter mTextLogAdapter;

    private boolean mContentsAssistant = false;
    private long mStartTime;

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
            if (!ACTION_RECEIVE_BITSOUND_CONTENTS.equals(intent.getAction())) return;

            final int code = intent.getIntExtra(BitsoundSmartOn.EXTRA_BITSOUND_SMARTON_CONTENTS_RESULT_CODE, BitsoundContents.Result.SIGNAL_NOT_FOUND);
            final BitsoundContents contents = BitsoundContents.fromBytes(intent.getByteArrayExtra(BitsoundSmartOn.EXTRA_BITSOUND_SMARTON_CONTENTS_BYTES));
            Timber.d("---> %s %s", Stringify.contents(code), contents);

            // Reuse BitsoundContentsListener::onResult
            mBitsoundContentsListener.onResult(code, contents);
        }
    };

    private BitsoundContentsListener mBitsoundContentsListener = new BitsoundContentsListener() {
        @Override
        public void onInitialized() {
            SampleActivity.this.textLogging("[onInitialized] BitsoundInit Success!!");
            if (mActionBar!= null) mActionBar.setTitle(String.format("%s : %s", Build.MODEL, App.string(R.string.toolbar_bitsound_initialized)));
        }
        @Override
        public void onError(int errorCode) {
            SampleActivity.this.textLogging("[onError] %s", Stringify.contents(errorCode));
        }
        @Override
        public void onStateChanged(int stateCode) {
            if (stateCode == BitsoundContents.State.STOPPED) {
                SampleActivity.this.textLogging("[onStateChanged][%dms] %s", (System.currentTimeMillis() - mStartTime), Stringify.contents(stateCode));
            } else SampleActivity.this.textLogging("[onStateChanged] %s", Stringify.contents(stateCode));
        }
        @Override
        public void onResult(int resultCode, BitsoundContents contents) {
            String message = String.format(Locale.getDefault(), "[%dms][%s]", System.currentTimeMillis() - mStartTime, Stringify.contents(resultCode));
            if (resultCode == BitsoundContents.Result.SUCCESS && contents != null) message += "\n" + contents;
            SampleActivity.this.textLogging("[onResult] %s", message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(SampleActivity.this);

        /* Setup ActionBar */
        mActionBar = this.getSupportActionBar();
        if (mActionBar != null) mActionBar.setElevation(0);
        if (mActionBar != null) mActionBar.setTitle(String.format("%s : %s", Build.MODEL, App.string(R.string.toolbar_bitsound_not_initialized)));
        if (mActionBar != null) mActionBar.setSubtitle(getBitsoundAppKey(this));

        /* Setup Text Logger */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        List<TextLogData> mTextLogDataList = new ArrayList<>();
        mTextLogAdapter = new TextLogAdapter(mTextLogDataList);
        mTextLogger.setAdapter(mTextLogAdapter);
        mTextLogger.setHasFixedSize(true);
        mTextLogger.setLayoutManager(layoutManager);

        /* Initialize Dashboard */
        initializeBitsoundDashboard();
        initializeBitsoundReceiverDashboard();
        initializeBitsoundShakingDashboard();
        initializeBitsoundSmartOnDashboard();
        initializeSoundllyPlayerDashboard();

        /* Register Broadcast Receiver */
        this.registerReceiver(mBitsoundContentsReceiver, new IntentFilter(ACTION_RECEIVE_BITSOUND_CONTENTS));
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(mBitsoundContentsReceiver);
        super.onDestroy();
    }

    /* Bitsound Dashboard Listeners */
    private void initializeBitsoundDashboard() {
        mBitsoundInit.setOnTouchListener(ALPHA_EFFECT);
        mBitsoundRelease.setOnTouchListener(ALPHA_EFFECT);
        Picasso.with(this).load(R.drawable.ic_play_arrow_white_24dp).transform(new ColorFilterTransformation(GREEN)).into(mBitsoundInit);
        Picasso.with(this).load(R.drawable.ic_pause_white_24dp).transform(new ColorFilterTransformation(RED)).into(mBitsoundRelease);
    }
    @OnClick(R.id.bitsound_init)
    protected void onBitsoundInit() {
        this.textLoggingBold(R.string.bitsound_init);
        int result = Bitsound.init(SampleActivity.this.getApplication(), mBitsoundContentsListener);
        this.textLogging(Stringify.result(result));
    }
    @OnClick(R.id.bitsound_release)
    protected void onBitsoundRelease() {
        this.textLoggingBold(R.string.bitsound_release);
        Bitsound.release();
        BitsoundShaking.disable(this);
        this.textLogging("Bitsound Released");
        if (mActionBar!= null) mActionBar.setTitle(String.format("%s : %s", Build.MODEL, App.string(R.string.toolbar_bitsound_released)));
    }

    /* BitsoundReceiver Dashboard Listeners */
    private void initializeBitsoundReceiverDashboard() {
        mBitsoundStartDetection.setOnTouchListener(ALPHA_EFFECT);
        mBitsoundStopDetection.setOnTouchListener(ALPHA_EFFECT);
        Picasso.with(this).load(R.drawable.ic_play_arrow_white_24dp).transform(new ColorFilterTransformation(GREEN)).into(mBitsoundStartDetection);
        Picasso.with(this).load(R.drawable.ic_pause_white_24dp).transform(new ColorFilterTransformation(RED)).into(mBitsoundStopDetection);
    }
    @OnClick(R.id.bitsound_receiver_start)
    public void onBitsoundStartDetection() {
        this.textLoggingBold(R.string.bitsound_receiver_start);
        this.handleStartDetect(false);
    }
    @OnClick(R.id.bitsound_receiver_stop)
    public void onBitsoundStopDetection() {
        this.textLoggingBold(R.string.bitsound_receiver_stop);
        this.handleStopDetect();
    }
    private int handleStartDetect(boolean shake) {
        mStartTime = System.currentTimeMillis();
        int result = Bitsound.startDetection(mContentsAssistant);
        if (!shake) this.textLogging(Stringify.result(result));
        return result;
    }
    private void handleStopDetect() {
        Bitsound.stopDetection();
        this.textLogging("Stopped Listening");
    }

    /* BitsoundShaking Dashboard Listeners */
    private static final int VIBRATE_IN_MILLISECONDS = 500;
    private BitsoundShaking.OnShakeListener mBitsoundShakingListener = new BitsoundShaking.OnShakeListener() {
        @Override
        public void onShake() {
            Vibrator vibrator = (Vibrator) SampleActivity.this.getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_IN_MILLISECONDS);
            SampleActivity.this.textLogging("onShake Callback : Shake Detected");

            int detectResult = handleStartDetect(true);
            SampleActivity.this.textLogging("onShake Callback : Start Listening %s", Stringify.result(detectResult));
        }
    };
    private void initializeBitsoundShakingDashboard() {
        mBitsoundShakingEnable.setOnTouchListener(ALPHA_EFFECT);
        mBitsoundShakingDisable.setOnTouchListener(ALPHA_EFFECT);
        Picasso.with(this).load(R.drawable.ic_play_arrow_white_24dp).transform(new ColorFilterTransformation(GREEN)).into(mBitsoundShakingEnable);
        Picasso.with(this).load(R.drawable.ic_pause_white_24dp).transform(new ColorFilterTransformation(RED)).into(mBitsoundShakingDisable);
    }
    @OnClick(R.id.bitsound_shaking_enable)
    protected void onBitsoundEnableShaking() {
        this.textLoggingBold(R.string.bitsound_shaking_enable);
        int result = BitsoundShaking.enable(this, mBitsoundShakingListener);
        this.textLogging(Stringify.result(result));
    }
    @OnClick(R.id.bitsound_shaking_disable)
    protected void onBitsoundDisableShaking() {
        this.textLoggingBold(R.string.bitsound_shaking_disable);
        BitsoundShaking.disable(this);
        this.textLogging("Disabled Bitsound Shaking");
    }

    /* BitsoundSmartOn Dashboard Listeners */
    private void initializeBitsoundSmartOnDashboard() {
        mBitsoundSmartOnStartScheduledDetection.setOnTouchListener(ALPHA_EFFECT);
        mBitsoundSmartOnStopScheduledDetection.setOnTouchListener(ALPHA_EFFECT);
        Picasso.with(this).load(R.drawable.ic_play_arrow_white_24dp).transform(new ColorFilterTransformation(GREEN)).into(mBitsoundSmartOnStartScheduledDetection);
        Picasso.with(this).load(R.drawable.ic_pause_white_24dp).transform(new ColorFilterTransformation(RED)).into(mBitsoundSmartOnStopScheduledDetection);
    }
    @OnClick(R.id.bitsound_smarton_start)
    protected void onBitsoundStartSmartOn() {
        this.textLoggingBold(R.string.bitsound_smarton_start);
        int result = BitsoundSmartOn.startScheduledDetection(this, 3 * DateUtils.MINUTE_IN_MILLIS);
        this.textLogging("Result : %s", Stringify.result(result));
    }
    @OnClick(R.id.bitsound_smarton_stop)
    protected void onBitsoundStopSmartOn() {
        this.textLoggingBold(R.string.bitsound_smarton_stop);
        int result = BitsoundSmartOn.stopScheduledDetection(this);
        this.textLogging("Result : %s", Stringify.result(result));
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
            return metaData.getString(Constant.METADATA_STR_APP_KEY);
        } catch (PackageManager.NameNotFoundException exception) {
            Timber.e(exception);
            return "APPKEY NOT FOUND";
        }
    }
}
