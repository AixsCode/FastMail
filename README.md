FastMail
=====

[![Travis](https://img.shields.io/travis/rust-lang/rust.svg)]() 
[![GitHub release](https://img.shields.io/github/release/qubyte/rubidium.svg)]() 
[![bitHound](https://img.shields.io/bithound/code/github/rexxars/sse-channel.svg)]()
[![gradle](https://img.shields.io/badge/gradle-4.0.1-blue.svg)]()  
# Introuduce

This is for  studies to learn basic android konwledge ,its contain BaiduMap AliYun Automatic Recognition
and Rongyun SDK to communicate Now its conncats its backstage management system

# Configuration enviroment
Version Gadle version 4.1
Android Plugin Version 3.0.1
Android Plugin Repository jcenter
Default Library Repository jcenter

This is gradle code
build.gradle 
```
apply plugin: 'com.android.application'

android {

    compileSdkVersion 25
    buildToolsVersion "26.0.2"
    defaultConfig {
        applicationId "com.wxs.fastmail"
        minSdkVersion 21
        targetSdkVersion 25
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    sourceSets {
        main {
            jni.srcDirs = []
            jniLibs.srcDirs = ['src/main/jniLibs']

        }
    }
    repositories {
        jcenter()
        flatDir {
            dirs 'libs' //就是你放aar的目录地址
        }
        maven {
            url "https://jitpack.io"
        }
    }
}
dependencies {
    compile fileTree(include: ['*.jar'], dir: 'libs')
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile project(':library')
    compile name: 'SMSSDK-3.0.0', ext: 'aar'
    compile name: 'SMSSDKGUI-3.0.0', ext: 'aar'
    compile files('libs/MobTools-2017.0607.1736.jar')
    compile files('libs/MobCommons-2017.0607.1736.jar')
    compile project(':IMKit')
    compile project(':IMLib')
    compile files('libs/BaiduLBS_Android.jar')
    compile files('libs/httpmime-4.1.2.jar')
    compile files('libs/IndoorscapeAlbumPlugin.jar')
    compile project(':pickerview')
    compile project(':pictureselector')
    compile files('libs/alipaysdk.jar')
    compile files('libs/alipaysecsdk.jar')
    compile files('libs/alipayutdid.jar')
    compile project(':stepview')
    compile project(':OkhttpUtils')
    compile project(':toasty')
    compile files('libs/jcore-android-1.1.6.jar')
    compile files('libs/jpush-android-3.0.8.jar')
    compile 'com.squareup.picasso:picasso:2.5.2'
    compile files('libs/NlsClientSdk.jar')
    compile 'com.squareup.picasso:picasso:2.5.2'
    compile 'com.android.support:cardview-v7:25.3.1'
    compile 'com.android.support:appcompat-v7:25.3.1'
    compile 'com.android.support.constraint:constraint-layout:1.0.0-alpha7'
    compile 'com.android.support:design:25.3.1'
    compile 'cn.bingoogolapple:bga-banner:2.1.7@aar'
    compile 'com.android.support:support-v4:25.1.0'
    compile 'com.lsjwzh:materialloadingprogressbar:0.5.8-RELEASE'
    compile 'com.android.support:recyclerview-v7:25.1.0'
    compile 'com.wuxiaolong.pullloadmorerecyclerview:library:1.1.2'
    compile 'com.jakewharton:butterknife:7.0.1'
    compile 'org.greenrobot:eventbus:3.0.0'
    compile 'de.hdodenhof:circleimageview:2.1.0'
    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'com.github.clans:fab:1.6.4'
    compile 'com.a520wcf.yllistview:YLListView:1.0.1'
    compile 'com.lrq.menu:addpopmenu:1.0.2'
    compile 'com.alibaba:fastjson:1.2.35'
    compile 'com.bigkoo:alertview:1.0.3'
    compile 'com.zhy:flowlayout-lib:1.0.3'
    compile 'com.tapadoo.android:alerter:2.0.0'
    compile 'com.google.code.gson:gson:2.6.2'
    compile 'com.google.zxing:core:3.2.1'
    compile 'cn.bingoogolapple:bga-qrcodecore:1.1.7@aar'
    compile 'cn.bingoogolapple:bga-zxing:1.1.7@aar'
    compile 'com.github.Cutta:GifView:1.1'
    testCompile 'junit:junit:4.12'
}
```
# App screenshot
![screenshot1](http://img.blog.csdn.net/20171123204652464?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd194X3NfaF9o/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![screenshot2](http://img.blog.csdn.net/20171123204721260?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd194X3NfaF9o/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/g)
![screenshot3](http://img.blog.csdn.net/20171123204749621?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd194X3NfaF9o/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)ravity/SouthEast)


