# Bitsound SDK for Android

[![version](https://img.shields.io/badge/Bitsound-4.1.0-green.svg?style=flat-square)](#)
[![version](https://img.shields.io/badge/SoundllyPlayer-1.0.4-green.svg?style=flat-square)](#)
[![dependency](https://img.shields.io/badge/support--annotations-27.0.2-red.svg?style=flat-square)](#)

BitsoundAndroidSDK Archive. We will distribute through **jCenter** or **mavenCentral** in near future! You can make your own campaign with corresponding appkey from [Portal](https://portal.soundl.ly/login.html#/). **Dependencies** are stated above with red [shield](http://shields.io/)

### Latest Release

#### v4.1.0 (Updated at 2018/03/12)

**Changes**

- Use `HTTPS` for all network connection
- Provide SDK for projects with targetSdkVersion under 26 with a suffix `-nougat`
- Support service blocking against brand, model, and OS version
- Bugfix reported crashes including `SharedPreferences` type casting issue

**변경점**

- 모든 네트워크 통신 방식을 `HTTPS`로 변경
- targetSdkVersion 오레오 미만인 프로젝트를 위해, `-nougat` 접미어가 포함된 SDK 제공
- 브랜드, 모델, OS버전 대상으로의 서비스 블로킹 지원
- `SharedPreferences` 타입 캐스팅 이슈를 포함하여 보고된 크래시 수정

---

### Support Criteria

|                   Type | Criteria                       |
| ---------------------: | :----------------------------- |
|                Version | v4.1.0                         |
|            SDK Modules | `bitsound`                     |
|       targetSdkVersion | 26 (8.0, Oreo)                 |
|      compileSdkVersion | 27 (8.1, Oreo)                 |
| Maximum Guaranteed API | 27 (8.1, Oreo)                 |
| Minimum Guaranteed API | 19 (4.4 KitKat)                |
|          minSdkVersion | 14 (4.0.3, ICS)                |
|                 Locale | KR                             |
|               Language | ko                             |
|              Platforms | Phone                          |
|                   ABIs | `armeabi-v7a` `arm64-v8a`      |
|         Library Format | .aar (Android Archive Library) |
|            Device List | Stated Below                   |

### Tested Devices

|           Series | Models                                   |
| ---------------: | :--------------------------------------- |
| Samsung S Series | `S8+` `S8` `S7Edge` `S7` `S6Edge` `S6` `S5` `S4 LTE-A` `S4` |
| Samsung Note Series | `Note8` `Note FE` `Note5` `Note4Edge` `Note4` `Note3` |
| Samsung MidRange | `A8` `A7` `A5` `A3` `J7` `J5`            |
|      Samsung ETC | `On7(2016)` `GrandMAX` `Grand2`          |
|      LG Flagship | `V30` `V20` `V10(Unsupported)` `G6` `G5` `G4` `G3` `G2` `G Stylo` `GPro` |
|      LG MidRange | `X Power` `X Screen` `K10` `Class` `Optimus LTE` |
|           Google | `Nexus 6` `Nexus 5X`              |
|              ETC | `VEGA Secret UP` `VEGA LTE-A` `VEGA Iron` `VEGA NO.6` `VEGA Racer` `SKY IM-100` |

---

### Documents

- [Introduction](https://docs.bitsound.io/docs/introduction)
- [Getting Started](https://docs.bitsound.io/docs/getting-started)
- [Android SDK Setup](https://docs.bitsound.io/docs/android-setup)
- [Android SDK API Reference](https://docs.bitsound.io/docs/android)
