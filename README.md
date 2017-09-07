# BitsoundSDK-Android

[![version](https://img.shields.io/badge/Bitsound-3.3.1-green.svg?style=flat-square)](#)
[![version](https://img.shields.io/badge/SoundllyPlayer-1.0.4-green.svg?style=flat-square)](#)
[![dependency](https://img.shields.io/badge/support--annotations-26.0.1-red.svg?style=flat-square)](#)

BitsoundAndroidSDK Archive. We will distribute through **jCenter** or **mavenCentral** in near future! You can make your campaign with related appkey in [Portal](https://portal.soundl.ly/login.html#/). **Dependencies** are stated above with red [shield](http://shields.io/)

### Latest Release

#### v3.3.1 (Updated at 2017/08/28)

- Fix Rare Crashes happened on some particular devices

---

### Previous Release

#### v3.3.0 (Updated at 2017/08/21)

Changes

- Improved Alarm Stability
- Improved Detection Accuracy
- Supports `SAMSUNG Galaxy S8` `SAMSUNG Galaxy S8+` `LG G6` and more...
- Following Features has been deprecated with `@Deprecated` Annotation :
  - `Bitsound.getTags(Context)`
  - `Bitsound.setTags(Context, JSONObject)`
  - `Bitsound.hasTag(Context, String)`
  - `Bitsound.removeTag(Context, String)`
  - `Bitsound.addTag(Context, String, boolean)`
  - `Bitsound.addTag(Context, String, double)`
  - `Bitsound.addTag(Context, String, int)`
  - `Bitsound.addTag(Context, String, long)`
  - `Bitsound.addTag(Context, String, Object)`
  - `Bitsound.setUUID(Context, String)`
- `Bitsound.checkAndInit` is obsolete since `v3.3.0`

BugFix

  - Resolve Potential Exception in Threading
  - Resolve Potential NPE

Sample App

- Update play-services to `11.0.4`
- Include SoundllyPlayer Test Code in Sample Application

#### v3.2.2 (Updated at 2017/07/12)

Changes

- **More Controllable AlarmScheduler** with `offset` and `poolsize`

BugFix

- Fixed `NullPointException` rarely occured in some Lollipop Devices

Sample App

- Fixed Default BroadcastReceiver `android:name`
- Update buildToolsVersion to `25.0.3`
- Update play-services to `11.0.2`

#### v3.2.0 (Updated at 2017/06/12)

Changes

- Decreased Logging Network Usage **down to 10%**
- **Initialization Fails for Network Error** without Force Trigger Parameter
- Always turn on PassiveMic Feature **(will be slowly deprecated)**
- Use Explicit Intent to prepare **Android O**

#### v3.1.2 (Updated at 2017/05/17)

BugFix

- Fixed Potential `NullPointException`

#### v3.1.1 (Updated at 2017/05/17)

BugFix

- Fixed AlarmPoolSize was decreased rarely on **SAMSUNG Devices**
- Fixed AlarmPoolSize was operating in one less number
- Fixed AlarmScheduler's rare Exceptions with synchronization
- Fixed Dispatching statistics delivered in proper time

#### v3.1.0 (Updated at 2017/05/08)

Changes

- Dependency Reduction (removed following dependencies)  
  [![dependency](https://img.shields.io/badge/appcompat--v7-25.1.0-red.svg?style=flat-square)](#)
  [![dependency](https://img.shields.io/badge/play--services--base-10.0.1-red.svg?style=flat-square)](#)
  [![dependency](https://img.shields.io/badge/play--services--ads-10.0.1-red.svg?style=flat-square)](#)
- Stable Alarm Scheduling
- Stable Network Access including less Data Usage
- Stable Log for Statistics
- Stable Audio Recording free from AudioEffect native crash
- Support Log Tagging API instead of repeated customlog calls
- Support FrameTypeFour Sound Beacon
- Support BitsoundContents from Bytes in BitsoundSmartOn Receiver
- Support Stringify class for code values in string

#### v3.0.11 (Updated at 2017/03/28)

- Core Parameter Update for FrameTypeZero Signal

#### v3.0.10 (Updated at 2017/03/20)

- Synchronized **Local ScheduleFile Version** against Server
- Synchronized **Initialization Process**


#### v3.0.9 (Updated at 2017/03/16)

- **Bitsound.init** verification runs **Every 10 minutes**
- Updates Schedule on **Every 10 minutes**

---

### Documents

- [Introduction](https://docs.bitsound.io/docs/introduction)
- [Getting Started](https://docs.bitsound.io/docs/getting-started)
- [Android SDK Setup](https://docs.bitsound.io/docs/android-setup)
- [Android SDK API Reference](https://docs.bitsound.io/docs/android)

### Supported Features

**Type**|**Criteria**
:-----:|:-----:
Devices|Support Device List (Link will be updated)
Locale|KR (as default)
Language|ko (as default)
minSdkVersion|14 (4.0.3, ICS)
Module Coverage|receiver / shaking / smarton
Library Format|.aar (Android Archive Library)
CPU Architecture|arm64-v8a / armeabi / armeabi-v7a / x86 / x86\_64
Platforms|Phone and Tablet / TV
