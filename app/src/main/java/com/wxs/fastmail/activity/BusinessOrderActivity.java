package com.wxs.fastmail.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.wxs.fastmail.R;
import com.wxs.fastmail.alipay.PayResult;
import com.wxs.fastmail.tool.Tool_pay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wxs.fastmail.tool.Tool_pay.SDK_PAY_FLAG;
import static com.wxs.fastmail.tool.Tool_pay.getSignType;

public class BusinessOrderActivity extends AppCompatActivity
{
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_takeaddress)
    TextView tvTakeaddress;
    @Bind(R.id.tv_getaddress)
    TextView tvGetaddress;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.et_money)
    TextView etMoney;
    @Bind(R.id.tv_sendmoney)
    TextView tvSendmoney;
    @Bind(R.id.tv_finalmoney)
    TextView tvFinalmoney;
    @Bind(R.id.btn_pay)
    Button btnPay;
    private  static  final String  TAG=BusinessOrderActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_order);
        ButterKnife.bind(this);
        tvFinalmoney.setText("88");
    }
    @OnClick({R.id.back, R.id.btn_pay})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.back:
                BusinessOrderActivity.this.finish();
                break;
            case R.id.btn_pay:
                pay();
                break;
        }
    }
    private Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                {
                    PayResult payResult = new PayResult((String) msg.obj);

                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();

                    String resultStatus = payResult.getResultStatus();

                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(BusinessOrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        BusinessOrderActivity.this.finish();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(BusinessOrderActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(BusinessOrderActivity.this, "支付失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
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
                PayTask alipay = new PayTask(BusinessOrderActivity.this);
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
}
