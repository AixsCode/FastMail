package com.wxs.fastmail.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.MobSDK;
import com.wxs.fastmail.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static com.wxs.fastmail.R.id.get_identify_code;

public class SignActivity extends AppCompatActivity
{
    public  static final  String TAG=SignActivity.class.getSimpleName();
    @Bind(R.id.input_mobile)
    EditText inputMobile;
    @Bind(R.id.input_identiycode)
    EditText inputIdentiycode;
    @Bind(get_identify_code)
    AppCompatButton getIdentifyCode;
    @Bind(R.id.input_password)
    EditText inputPassword;
    @Bind(R.id.btn_signup)
    AppCompatButton btnSignup;
    @Bind(R.id.link_login)
    TextView linkLogin;
    private  int i=60;
    private android.os.Handler handler1=new android.os.Handler(new android.os.Handler.Callback() {
        public boolean handleMessage(Message msg) {
            if (msg.what == -1) {
                //修改控件文本进行倒计时  i 以60秒倒计时为例
                getIdentifyCode.setText( i+" s");
            }
            else if (msg.what == -2)
            {
                //修改控件文本，进行重新发送验证码
                getIdentifyCode.setText("重新发送");
                getIdentifyCode.setClickable(true);
                i = 60;
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                // 短信注册成功后，返回MainActivity,然后提示
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    // 提交验证码成功,调用注册接口，之后直接登录
                    //当号码来自短信注册页面时调用登录注册接口
                    //当号码来自绑定页面时调用绑定手机号码接口

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    Toast.makeText(getApplication(), "验证码已经发送",
                            Toast.LENGTH_SHORT).show();
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
            return  false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        MobSDK.init(getApplication(), "1f4450211e544", "e6e9eed1473757c09639ea5b2c7e220b");

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        getIdentifyCode.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String mobile1 = inputMobile.getText().toString();
                if (mobile1.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"手机号码不能为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                SMSSDK.getVerificationCode("86", mobile1);
                getIdentifyCode.setClickable(false);
                //开始倒计时
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for (; i > 0; i--) {
                            handler1.sendEmptyMessage(-1);
                            if (i <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        //倒计时结束执行
                        handler1.sendEmptyMessage(-2);
                    }
                }).start();
            }
        });
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler1.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eventHandler);
    }

    protected void onDestroy()
    {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }
        btnSignup.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(SignActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("注册中..");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        onSignupSuccess();

                        progressDialog.dismiss();
                    }
                }, 3000);
    }
    public void onSignupSuccess()
    {
        btnSignup.setEnabled(true);
        Intent intent=new Intent(SignActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        btnSignup.setEnabled(true);
    }
    public boolean validate()
    {
        boolean valid = true;

        String mobile = inputMobile.getText().toString();
        String password = inputPassword.getText().toString();

        if (mobile.isEmpty() || mobile.length()<=10) {
            inputMobile.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            inputMobile.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            inputPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        }
        else
            {
            inputPassword.setError(null);
        }
        return valid;
    }

}
