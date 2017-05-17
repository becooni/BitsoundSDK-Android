# BitsoundSDK-Android

[![version](https://img.shields.io/badge/version-3.0.11-green.svg?style=flat-square)](#)
[![dependency](https://img.shields.io/badge/support--annotations-25.3.1-red.svg?style=flat-square)](#)

BitsoundAndroidSDK Archive. We will distribute through **jCenter** in near future! You can make your campaign with related appkey in [Portal](https://portal.soundl.ly/login.html#/). **Dependencies** are stated above with red [shield](http://shields.io/)

### Latest Release

#### v3.1.2 (Updated at 2017/05/17)

BugFix

- Fixed Potential `NullPointException`

---

### Previous Release

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

- [Introduction](https://docs.bitsound.io/v1.0/docs/introduction)
- [Getting Started](https://docs.bitsound.io/v1.0/docs/getting-started)
- [Android SDK Setup](https://docs.bitsound.io/v1.0/docs/android-sdk-setup)
- [Android SDK API Reference](https://docs.bitsound.io/v1.0/docs/android-sdk)

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
