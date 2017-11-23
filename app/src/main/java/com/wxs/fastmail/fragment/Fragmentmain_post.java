package com.wxs.fastmail.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airsaid.pickerviewlibrary.OptionsPickerView;
import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.wxs.fastmail.Dialog.PostDialog;
import com.wxs.fastmail.Events.GetadsEvent;
import com.wxs.fastmail.Events.SendadsEvent;
import com.wxs.fastmail.R;
import com.wxs.fastmail.activity.PostgetActivity;
import com.wxs.fastmail.activity.PostsendActivity;
import com.wxs.fastmail.activity.ProgresspostActivity;
import com.wxs.fastmail.alipay.PayResult;
import com.wxs.fastmail.bean.OrderOnlySend;
import com.wxs.fastmail.bean.User;
import com.wxs.fastmail.tool.Tool_Url;
import com.wxs.fastmail.tool.Tool_pay;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

import static com.wxs.fastmail.tool.Tool_pay.SDK_PAY_FLAG;
import static com.wxs.fastmail.tool.Tool_pay.getSignType;

/**
 * Created by wxs on 2017/7/10.
 */

public class Fragmentmain_post extends Fragment
{
    @Bind(R.id.tv_post)
    TextView tvPost;
    @Bind(R.id.lay_post)
    LinearLayout layPost;
    @Bind(R.id.tv_get)
    TextView tvGet;
    @Bind(R.id.lay_get)
    LinearLayout layGet;
    @Bind(R.id.lay_time)
    LinearLayout layTime;
    @Bind(R.id.iv_type)
    ImageView ivType;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.lay_type)
    LinearLayout layType;
    @Bind(R.id.iv_weight)
    ImageView ivWeight;
    @Bind(R.id.tv_weight)
    TextView tvWeight;
    @Bind(R.id.lay_weight)
    LinearLayout layWeight;
    @Bind(R.id.btn_post)
    Button btnPost;
    @Bind(R.id.et_time)
    EditText etTime;
    @Bind(R.id.tv_insurance)
    TextView tvInsurance;
    @Bind(R.id.iv_insurance)
    ImageView ivInsurance;
    private String SendTel;
    private String GetTel;
    private String SendtoName;
    public static final int Msgprogress = 123;
    private static final String TAG = Fragmentmain_post.class.getSimpleName();
    @Bind(R.id.progressbar)
    CircleProgressBar progressbar;
    private OptionsPickerView<String> mOptionsPickerView;
    private OptionsPickerView<String> mOptionsPickerView1;
    private OptionsPickerView<String> mOptionsPickerView2;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();

                    String resultStatus = payResult.getResultStatus();

                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(getContext(), "支付成功",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(getContext(), "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(getContext(), "支付失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                case Msgprogress:
                    progressbar.setVisibility(View.GONE);
                    Intent intent = new Intent(getContext(), ProgresspostActivity.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                default:
                    break;
            }
        }
    };
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragmentmain_post, null);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this, view);
        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onSendadsEvent(SendadsEvent event) {
        String msg = event.getPostaddress();
        tvGet.setText(msg);
        GetTel = event.getSendphone();
        SendtoName = event.getSendname();
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetadsEvent(GetadsEvent event) {
        String msg = event.getGetaddress();
        tvPost.setText(msg);
        SendTel = event.getGephone();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }
    @OnClick(R.id.lay_post)
    public void onLayPostClicked() {
        Intent intent = new Intent(getContext(), PostgetActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.lay_type, R.id.lay_weight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lay_type:
                mOptionsPickerView1 = new OptionsPickerView<>(getActivity());
                final ArrayList<String> list1 = new ArrayList<>();
                list1.add("办公");
                list1.add("日用品");
                list1.add("食品");
                list1.add("数码产品");
                list1.add("衣物");
                list1.add("其他");
                mOptionsPickerView1.setPicker(list1);
                mOptionsPickerView1.setCancelTextColor(Color.GRAY);
                mOptionsPickerView1.setSubmitTextColor(Color.RED);
                mOptionsPickerView1.setTextSize(16f);
                mOptionsPickerView1.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int option1, int option2, int option3) {
                        String type = list1.get(option1);
                        tvType.setText(type);
                        tvType.setTextColor(Color.RED);
                        switch (type) {
                            case "办公":
                                ivType.setBackgroundResource(R.drawable.post_fileobject);
                                break;
                            case "日用品":
                                ivType.setBackgroundResource(R.drawable.post_liveobject);
                                break;
                            case "数码产品":
                                ivType.setBackgroundResource(R.drawable.post_camobject);
                                break;
                            case "衣物":
                                ivType.setBackgroundResource(R.drawable.post_clothobject);
                                break;
                            case "其他":
                                ivType.setBackgroundResource(R.drawable.post_otherobject);
                                break;
                        }
                    }
                });
                mOptionsPickerView1.show();
                break;
            case R.id.lay_weight:
                mOptionsPickerView = new OptionsPickerView<>(getActivity());
                final ArrayList<String> list = new ArrayList<>();
                for (int i = 1; i < 100; i++) {
                    list.add(i + "KG");
                }
                mOptionsPickerView.setPicker(list);
                mOptionsPickerView.setCancelTextColor(Color.GRAY);
                mOptionsPickerView.setSubmitTextColor(Color.RED);
                mOptionsPickerView.setTextSize(16f);
                mOptionsPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int option1, int option2, int option3) {
                        String weight = list.get(option1);
                        tvWeight.setText(weight);
                        tvWeight.setTextColor(Color.RED);
                        ivWeight.setBackgroundResource(R.drawable.post_weight2);
                    }
                });
                mOptionsPickerView.show();
                break;
        }
    }

    @OnClick(R.id.tv_get)
    public void onTvGetClicked() {
        Intent intent = new Intent(getContext(), PostsendActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_post)
    public void onBtnPostClicked() {
        OkhttpUtils_OrderOnlySend();
        Log.d(TAG, "SendTel==" + SendTel);
        Log.d(TAG, "tvGet==" + tvGet.getText().toString());
        Log.d(TAG, "GetTel==" + GetTel);
        Log.d(TAG, "SendtoName==" + SendtoName);
        dialog();
    }

    private void OkhttpUtils_OrderOnlySend() {
        User user = new User();
        user.setId(1);
        OrderOnlySend orderOnlySend = new OrderOnlySend();
        orderOnlySend.setUserSendTel(SendTel);
        orderOnlySend.setUserSendAddress(tvPost.getText().toString());
        orderOnlySend.setUserDeliveryTel(GetTel);
        orderOnlySend.setSendToName(SendtoName);
        orderOnlySend.setSendToAddress(tvGet.getText().toString());
        orderOnlySend.setItemType(tvType.getText().toString());
        orderOnlySend.setWeight(tvWeight.getText().toString());
        orderOnlySend.setUserGetExceptTime(Timestamp.valueOf("2015-11-15 11:23:12"));
        orderOnlySend.setUserSend(user);
        OkHttpUtils
                .postString()
                .url(Tool_Url.getUrl_post_deliverymail())
                .content(new Gson().toJson(orderOnlySend))
                .build()
                .execute(new MyStringCallback());
    }
    private void pay() {
        String orderInfo = Tool_pay.getOrderInfo("幸福西饼手工红色生日蛋糕", "江苏省无锡市果果蛋糕店", "0.01");
        // 对订单做RSA 签名
        String sign = Tool_pay.sign(orderInfo);
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 完整的符合支付宝参数规范的订单信息
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(getActivity());
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo);

                Message msg = new Message();

                msg.what = SDK_PAY_FLAG;

                msg.obj = result;

                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
    private void dialog() {
        final PostDialog dlg = new PostDialog(getContext(), R.style.PostDialog);
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.post_dialog);
        Button btn = (Button) window.findViewById(R.id.btn_post1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pay();
                dlg.dismiss();
                progressbar.setVisibility(View.VISIBLE);
                Message msg = Message.obtain(mHandler);
                msg.what = Msgprogress;
                mHandler.sendMessageDelayed(msg, 2000);
            }
        });
    }
    @OnClick(R.id.iv_insurance)
    public void onViewClicked()
    {
        mOptionsPickerView2 = new OptionsPickerView<>(getActivity());
        final ArrayList<String> list = new ArrayList<>();
         list.add("不保价");
         list.add("投保3元");
         list.add("投保5元");
        mOptionsPickerView2.setPicker(list);
        mOptionsPickerView2.setCancelTextColor(Color.GRAY);
        mOptionsPickerView2.setSubmitTextColor(Color.RED);
        mOptionsPickerView2.setTextSize(16f);
        mOptionsPickerView2.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener()
        {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
                String insurance = list.get(option1);
                tvInsurance.setText(insurance);
            }
        });
        mOptionsPickerView2.show();
    }
    private class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {

            Log.i(TAG, "onBefore");
        }

        @Override
        public void onAfter(int id)
        {
            Log.i(TAG, "onAfter");
        }
        @Override
        public void onError(Call call, Exception e, int id) {
            Log.i(TAG, "onError");
            e.printStackTrace();
        }

        @Override
        public void onResponse(String response, int id) {
            Log.i(TAG, "onResponse \t" + response);
        }
    }

}


