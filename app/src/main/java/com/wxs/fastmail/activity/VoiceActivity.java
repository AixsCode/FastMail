package com.wxs.fastmail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.idst.nls.NlsClient;
import com.alibaba.idst.nls.NlsListener;
import com.alibaba.idst.nls.StageListener;
import com.alibaba.idst.nls.internal.protocol.NlsRequest;
import com.alibaba.idst.nls.internal.protocol.NlsRequestProto;
import com.cunoraz.gifview.library.GifView;
import com.wxs.fastmail.Events.VoiceEvent;
import com.wxs.fastmail.R;
import com.wxs.fastmail.bean.Voice;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

import static com.wxs.fastmail.common.Application.context;
public class VoiceActivity extends AppCompatActivity
{
    @Bind(R.id.tv_1)
    TextView tv1;
    @Bind(R.id.tv_2)
    TextView tv2;
    @Bind(R.id.tv_3)
    TextView tv3;
    @Bind(R.id.tv_4)
    TextView tv4;
    @Bind(R.id.tv_5)
    TextView tv5;
    @Bind(R.id.tv_6)
    TextView tv6;
    @Bind(R.id.order_gif)
    GifView orderGif;
    @Bind(R.id.tv_7)
    TextView tv7;
    @Bind(R.id.btn_voicemade)
    ImageButton btnVoicemade;
    @Bind(R.id.tv_time)
    TextView tvTime;
    private NlsClient mNlsClient;
    private NlsRequest mNlsRequest;
    private boolean longClicked = false;
    private boolean isRecognizing = false;
    private  static  final  int time=123;
    private boolean flag = true;
    private int i = 0;
    private int DELYED= 1000;
    private  String VoiceResult;
    private static String TAG = VoiceActivity.class.getSimpleName();
    private Handler mHandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        ButterKnife.bind(this);
        initlize();
        initnls();
    }
    /*
        nls初始化
     */
    private void initnls()
    {
        String appkey = "nls-service"; //请设置申请到的Appkey
        mNlsRequest = initNlsRequest();
        mNlsRequest.setApp_key(appkey);    //appkey请从 "快速开始" 帮助页面的appkey列表中获取
        mNlsRequest.setAsr_sc("opu");      //设置语音格式
        mNlsRequest.enableCloudNLUResult(); //初始化NLU请求
        NlsClient.openLog(true);
        NlsClient.configure(getApplicationContext()); //全局配置
        mNlsClient = NlsClient.newInstance(this, mRecognizeListener, mStageListener,mNlsRequest);                          //实例化NlsClient
        mNlsClient.setMaxRecordTime(60000);  //设置最长语音
        mNlsClient.setMaxStallTime(1000);    //设置最短语音
        mNlsClient.setMinRecordTime(500);    //设置最大录音中断时间
        mNlsClient.setRecordAutoStop(false);  //设置VAD
        mNlsClient.setMinVoiceValueInterval(200); //设置音量回调时长
    }
    private NlsRequest initNlsRequest()
    {
        NlsRequestProto proto = new NlsRequestProto(context);
        //proto.setApp_user_id("user_id"); //设置用户名
        return new NlsRequest(proto);
    }
    private  void  initStartRecognizing() {
        isRecognizing = true;
        mNlsRequest.authorize("LTAITAh6u2NgMSJc", "ccRFgo5yVvDO8WP9PoH5EM2JwlSb9y"); //请替换为用户申请到的数加认证key和密钥
        mNlsClient.start();
    }
    private  void initStopRecognizing()
    {
        isRecognizing=false;
        mNlsClient.stop();
    }
    Runnable runnable = new Runnable()
    {
        @Override
        public void run() {
          // handler自带方法实现定时器
            try
            {
                mHandler.postDelayed(this, DELYED);
                tvTime.setText(Integer.toString(i++)+"S");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };
    private void initlize()
    {
        btnVoicemade.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longClicked = true;
                Log.d(TAG, "onLongClick=============" + longClicked);
                change();
                return longClicked;
            }
        });
        btnVoicemade.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    Log.d(TAG, "ACTION_DOWN=============" + longClicked);
                    if (longClicked == false) {
                        change();
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    if (longClicked == true) {
                        change_init();
                        initStopRecognizing();
                        vibrate();
                        Intent intent = new Intent(VoiceActivity.this, MadeOrderActivity.class);
                        startActivity(intent);
                        EventBus.getDefault().postSticky(new VoiceEvent("Voiceorder",VoiceResult));
                        longClicked = false;
                        flag = false;
                        Log.d(TAG, "ACTION_UP=============1" + longClicked);
                    }
                    Log.d(TAG, "ACTION_UP=============2" + longClicked);
                    if (longClicked == false && flag == true)
                    {
                        change_init();
                        vibrate();
                        Toasty.normal(VoiceActivity.this, "输入时间太短哦,请重新输入", Toast.LENGTH_SHORT).show();
                    }
                    flag = true;
                }
                return false;
            }
        });
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        VoiceActivity.this.finish();
    }
    private void change()
    {
        orderGif.setVisibility(View.VISIBLE);
        orderGif.play();
        initStartRecognizing();
        tv1.setText("请尽量标准描述您的需求哦");
        tv2.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);
        tv4.setVisibility(View.INVISIBLE);
        tv5.setVisibility(View.INVISIBLE);
        tv6.setVisibility(View.INVISIBLE);
        tvTime.setVisibility(View.VISIBLE);
        mHandler.postDelayed(runnable, DELYED);
        tv7.setText("正在识别····");
    }
    private void change_init()
    {
        orderGif.setVisibility(View.INVISIBLE);
        orderGif.pause();
        tv1.setText("说出需要购买的商品以及描述");
        tv2.setVisibility(View.VISIBLE);
        tv3.setVisibility(View.VISIBLE);
        tv4.setVisibility(View.VISIBLE);
        tv5.setVisibility(View.VISIBLE);
        tvTime.setVisibility(View.INVISIBLE);
        tv6.setVisibility(View.VISIBLE);
        tv7.setText("按下说话");
    }
    private void vibrate()
    {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
    private NlsListener mRecognizeListener = new NlsListener()
    {
        @Override
        public void onRecognizingResult(int status, RecognizedResult result)
        {
            switch (status)
            {
                case NlsClient.ErrorCode.SUCCESS:
                    Log.d(TAG,"result.asr_out=="+result.asr_out);
                    Voice voice=JSON.parseObject(result.asr_out,Voice.class);
                    VoiceResult=voice.getResult();
                    break;
                case NlsClient.ErrorCode.RECOGNIZE_ERROR:
                    Toast.makeText(VoiceActivity.this, "recognizer error", Toast.LENGTH_LONG).show();
                    break;
                case NlsClient.ErrorCode.RECORDING_ERROR:
                    Toast.makeText(VoiceActivity.this,"recording error",Toast.LENGTH_LONG).show();
                    break;
                case NlsClient.ErrorCode.NOTHING:
                    Toast.makeText(VoiceActivity.this,"nothing",Toast.LENGTH_LONG).show();
                    break;
            }
            isRecognizing = false;
        }
    };
    private StageListener mStageListener = new StageListener()
    {
        @Override
        public void onStartRecognizing(NlsClient recognizer) {
            super.onStartRecognizing(recognizer);    //To change body of overridden methods use File | Settings | File Templates.
        }
        @Override
        public void onStopRecognizing(NlsClient recognizer)
        {
            super.onStopRecognizing(recognizer);    //To change body of overridden methods use File | Settings | File Templates.
        }
        @Override
        public void onStartRecording(NlsClient recognizer) {
            super.onStartRecording(recognizer);    //To change body of overridden methods use File | Settings | File Templates.
        }
        @Override
        public void onStopRecording(NlsClient recognizer) {
            super.onStopRecording(recognizer);    //To change body of overridden methods use File | Settings | File Templates.
        }
        @Override
        public void onVoiceVolume(int volume)
        {
            super.onVoiceVolume(volume);
        }
    };
}
