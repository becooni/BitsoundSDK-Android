# BitsoundSDK-Android

[![version](https://img.shields.io/badge/Bitsound-3.5.5-green.svg?style=flat-square)](#)
[![version](https://img.shields.io/badge/SoundllyPlayer-1.0.4-green.svg?style=flat-square)](#)
[![dependency](https://img.shields.io/badge/support--annotations-27.0.2-red.svg?style=flat-square)](#)

BitsoundAndroidSDK Archive. We will distribute through **jCenter** or **mavenCentral** in near future! You can make your own campaign with corresponding appkey from [Portal](https://portal.soundl.ly/login.html#/). **Dependencies** are stated above with red [shield](http://shields.io/)

### Latest Release

#### v3.5.5 (Updated at 2017/12/04)

**Changes**

- ADID is enabled by default. Clients can opt-out if necessary
- Remove Force Initialization Parameter from `Bitsound.init` API
- Shrink SDK Size by 35% by dropping `x86` `x86_64` ABI in production build
- Resolve Reported Crashes

**Sample Project**
- targetSdkVersion `27`
- compileSdkVersioin `27`
- buildToolsVersion `27.0.1`
- Android Support Library `v27.0.2`
- Play Services Library `v11.6.2`

---

### Support Criteria

|                   Type | Criteria                       |
| ---------------------: | :----------------------------- |
|                Version | v3.5.5                         |
|            SDK Modules | `receiver` `shaking` `smarton` |
|      compileSdkVersion | 27 (8.1, Oreo)                 |
| Maximum Guaranteed API | 25 (7.1.2, Nougat)             |
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
| Samsung MidRange | `A8` `A7` `A5` `A3` `J7` `J5`            |
|      Samsung ETC | `On7(2016)` `GrandMAX` `Grand2`          |
|      LG Flagship | `V30` `V20` `V10` `G6` `G5` `G4` `G3` `G2` `G Stylo` `GPro` |
|      LG MidRange | `X Power` `X Screen` `K10` `Class` `Optimus LTE` |
|           Google | `Nexus 6` `Nexus 5X`                     |
|              ETC | `VEGA Secret UP` `VEGA LTE-A` `VEGA Iron` `VEGA NO.6` `VEGA Racer` `SKY IM-100` |

---

### Documents

- [Introduction](https://docs.bitsound.io/docs/introduction)
- [Getting Started](https://docs.bitsound.io/docs/getting-started)
- [Android SDK Setup](https://docs.bitsound.io/docs/android-setup)
- [Android SDK API Reference](https://docs.bitsound.io/docs/android)
