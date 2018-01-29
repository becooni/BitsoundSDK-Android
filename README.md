# Bitsound SDK for Android

[![version](https://img.shields.io/badge/Bitsound-4.0.0-green.svg?style=flat-square)](#)
[![version](https://img.shields.io/badge/SoundllyPlayer-1.0.4-green.svg?style=flat-square)](#)
[![dependency](https://img.shields.io/badge/support--annotations-27.0.2-red.svg?style=flat-square)](#)

BitsoundAndroidSDK Archive. We will distribute through **jCenter** or **mavenCentral** in near future! You can make your own campaign with corresponding appkey from [Portal](https://portal.soundl.ly/login.html#/). **Dependencies** are stated above with red [shield](http://shields.io/)

### Latest Release

#### v4.0.0 (Updated at 2018/01/29)

**SDK Changes**

- Redesign API with Android Oreo Support
- Merge `bitsoundreceiver` `bitsoundshaking` `bitsoundsmarton` into `bitsound`
- Provide Preload Application Information for Samsung App Permission Monitor
- Fix Reported Crashes

**Sample Application Changes**

- Follow-up API & Behavior Changes


#### API Changes from `v3.6.0`

| v3.6.0 API                               | v4.0.0 API                               |
| :--------------------------------------- | :--------------------------------------- |
| Bitsound.init(Context, BitsoundContentsListener) | **Deleted**                              |
| Bitsound.release()                       | **Deleted**                              |
| Bitsound.initialized()                   | **Deleted**                              |
| Bitsound.startDetection()                | Bitsound.with(Context).startSingleDetection(class<? extends BroadcastReceiver>) |
| Bitsound.stopDetection()                 | Bitsound.with(Context).stopSingleDetection() |
| Bitsound.getScheduledContents()          | **Deleted**                              |
| Bitsound.sendCustomLog(JSONObject\|Map)  | Bitsound.with(Context).sendCustomLog(JSONObject\|Map) |
| Bitsound.getUUID(Context)                | **Deleted**                              |
| Bitsound.setUUID(Context, String)        | **Deleted**                              |
| Bitsound.getUserID(Context)              | Bitsound.with(Context).getUserID()       |
| Bitsound.setUserID(Context, String)      | Bitsound.with(Context).setUserID(String) |
| Bitsound.Result.SUCCESS                  | Bitsound.SUCCESS                         |
| Bitsound.Result.MIC_PERMISSION_DENIED    | Bitsound.MIC_PERMISSION_REQUIRED         |
| Bitsound.Result.NOT_INITIALIZED          | Bitsound.INITIALIZATION_REQUIRED         |
| Bitsound.Result.INVALID_ARGUMENTS        | Bitsound.CONTEXT_REQUIRED                |
| Bitsound.Result.INVALID_ARGUMENTS        | Bitsound.APPKEY_REQUIRED                 |
| Bitsound.Result.INVALID_ARGUMENTS        | Bitsound.INVALID_ARGUMENTS               |
| Bitsound.Result.SKIP_DETECTION           | Bitsound.DETECTION_SKIPPED               |
| Bitsound.Result.ALREADY_STARTED          | Bitsound.DETECTION_RUNNING               |
| Bitsound.Result.ALREADY_STARTED          | Bitsound.SHAKE_ALREADY_STARTED           |
| Bitsound.Result.NOT_INITIALIZED          | Bitsound.INITIALIZATION_FAILURE          |
| Bitsound.Result.OUT_OF_MEMORY            | Bitsound.OUT_OF_MEMORY_FAILURE           |
| Bitsound.Result.ERROR_UNKNOWN            | Bitsound.UNEXPECTED_FAILURE              |
| -                                        | Bitsound.SHAKE_ALREADY_DISABLED          |
| BitsoundContentsListener                 | **Replaced** by BitsoundContentsReceiver |
| BitsoundShaking.enable(Context, OnShakeListener) | Bitsound.with(Context).enableShake(vibrate, duration) |
| BitsoundShaking.disable(Context)         | Bitsound.with(Context).disableShake()    |
| BitsoundShaking.OnShakeListener          | Bitsound.OnShakeListener                 |
| BitsoundSmartOn.startScheduledDetection(Context, duration) | Bitsound.with(Context).startPeriodicDetection(class<? extends BroadcastReceiver>) |
| BitsoundSmartOn.stopScheduledDetection(Context) | Bitsound.with(Context).stopPeriodicDetection() |
| BitsoundSmartOn.sendCustomLog(Context, JSONObject) | Bitsound.with(Context).sendCustomLog(JSONObject\|Map) |
| BitsoundSmartOn.allowDetection(Context, boolean) | Bitsound.with(Context).allowDetection(boolean) |
| BitsoundSmartOn.allowNightDetection(Context, boolean) | Bitsound.with(Context).allowNightDetection(boolean) |
| BitsoundSmartOn.allowBackgroundDetection(Context, boolean) | Bitsound.with(Context).allowBackgroundDetection(boolean) |
| BitsoundContents.Result.SUCCESS          | Bitsound.BEACON_RECEIVED                 |
| BitsoundContents.Result.INVALID_BEACON   | Bitsound.BEACON_NOT_VALID                |
| BitsoundContents.Result.SIGNAL_NOT_FOUND | Bitsound.BEACON_NOT_FOUND                |
| BitsoundContents.State.STARTED           | Bitsound.DETECTION_STARTED               |
| BitsoundContents.State.STOPPED           | Bitsound.DETECTION_STOPPED               |
| BitsoundContents.Error.NETWORK           | Bitsound.NETWORK_CONNECTION_FAILURE      |
| BitsoundContents.Error.MIC_FAILURE       | Bitsound.MIC_OCCUPATION_FAILURE          |
| BitsoundContents.Error.NOT_AUTHORIZED    | Bitsound.AUTHORIZATION_FAILURE           |
| BitsoundContents.Error.NOT_INITIALIZED   | Bitsound.INITIALIZATION_FAILURE          |
| BitsoundContents.Error.NATIVE_LIBRARY    | Bitsound.NATIVE_LIBRARY_LOAD_FAILURE     |

---

### Support Criteria

|                   Type | Criteria                       |
| ---------------------: | :----------------------------- |
|                Version | v4.0.0                         |
|            SDK Modules | `bitsound`                     |
|       targetSdkVersion | 27 (8.1, Oreo)                 |
|      compileSdkVersion | 27 (8.1, Oreo)                 |
| Maximum Guaranteed API | 27 (8.1, Oreo)                 |
| Minimum Guaranteed API | 19 (4.4 KitKat)                |
|          minSdkVersion | 14 (4.0.3, ICS)                |
|                 Locale | KR                             |
|               Language | ko                             |
|              Platforms | SmartPhone                     |
|                   ABIs | `armeabi-v7a` `arm64-v8a`      |
|         Library Format | .aar (Android Archive Library) |
|            Device List | Stated Below                   |

### Tested Devices

|           Series | Models                                   |
| ---------------: | :--------------------------------------- |
| Samsung Flagship | `Note8` `S8+` `S8` `Note FE` `S7Edge` `S7` `S6Edge` `S6` `Note5` `S5` `Note4Edge` `Note4` `S4 LTE-A` `S4` `Note3` |
| Samsung MidRange | `A8` `A7` `A5`  `J7` `J5`                |
|               LG | `V30` `V20` `G6` `G5` `G4` `X Power`     |
|           Google | `Pixel 2 XL` `Nexus 5X`                  |

---

### Documents

- [Introduction](https://docs.bitsound.io/docs/introduction)
- [Getting Started](https://docs.bitsound.io/docs/getting-started)
- [Android SDK Setup](https://docs.bitsound.io/docs/android-setup)
- [Android SDK API Reference](https://docs.bitsound.io/docs/android)
