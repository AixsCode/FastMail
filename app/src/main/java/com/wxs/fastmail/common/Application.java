package com.wxs.fastmail.common;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import static com.zhy.http.okhttp.log.LoggerInterceptor.TAG;

/**
 * Created by wxs on 2017/7/14.
 */

public class Application extends android.app.Application
{
    public  android.os.Handler handler;
    public  static  Context context;
    private  static  final String token3="Tt/qGPZfuBJMSI3DEa9h1sAGqnp2FrMlhV4BFCU1BcUrXsDN8oIMEZaWUIDu4MlVck9D7V8m0AekSQ8ochK8yw==";
   // private  static  final String token4="MPEflrTW5KXa7IoKTiGfvgnHNNT+dVKtg/OF2gYN1a6t0lUr/uF6V84f/BODtsb4t+4W2BVeiK/JexB9o5vBIg==";

    @Override
    public void onCreate()
    {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplicationContext());
        SDKInitializer.initialize(getApplicationContext());
        handler=new Handler();
        context = this.getApplicationContext();
        RongIM.init(getApplicationContext());
        RongIM.connect(token3, new RongIMClient.ConnectCallback()
        {
            @Override
            public void onTokenIncorrect()
            {
                Log.d(TAG,"onTokenIncorrect");
            }
            @Override
            public void onSuccess(String s)
            {
                Log.d(TAG,"onSuccess  Usersid："+s);
            }
            @Override
            public void onError(RongIMClient.ErrorCode errorCode)
            {
                Log.d(TAG,"onError"+errorCode.getValue());
            }
        });
    }
    public Handler getHandler() {
        return handler;
    }
    public static Context getContextObject()
    {
        return context;
    }
    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
