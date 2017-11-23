package com.wxs.fastmail.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airsaid.pickerviewlibrary.OptionsPickerView;
import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.wxs.fastmail.Dialog.CueDialog;
import com.wxs.fastmail.Events.EstimateEvent;
import com.wxs.fastmail.Events.Purchaseaddress;
import com.wxs.fastmail.Events.Receiveaddress;
import com.wxs.fastmail.Events.VoiceEvent;
import com.wxs.fastmail.R;
import com.wxs.fastmail.alipay.PayResult;
import com.wxs.fastmail.bean.OrderUserThree;
import com.wxs.fastmail.bean.User;
import com.wxs.fastmail.tool.Tool_Url;
import com.wxs.fastmail.tool.Tool_pay;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

import static com.wxs.fastmail.R.id.et_put;
import static com.wxs.fastmail.tool.Tool_pay.SDK_PAY_FLAG;
import static com.wxs.fastmail.tool.Tool_pay.getSignType;

public class MadeOrderActivity extends AppCompatActivity
{
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_put)
    EditText etPut;
    @Bind(R.id.gridView)
    GridView gridView;
    @Bind(R.id.tv_takeaddress)
    TextView tvTakeaddress;
    @Bind(R.id.tv_getaddress)
    TextView tvGetaddress;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.iv_takephoto)
    ImageView ivTakephoto;
    @Bind(R.id.et_money)
    EditText etMoney;
    @Bind(R.id.tv_finalmoney)
    TextView tvFinalmoney;
    @Bind(R.id.btn_pay)
    Button btnPay;
    @Bind(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_introduce)
    TextView tvIntroduce;
    @Bind(R.id.tv_voice)
    TextView tvVoice;
    @Bind(R.id.tv_estimate)
    TextView tvEstimate;
    @Bind(R.id.ll_estimate)
    LinearLayout llEstimate;
    private OptionsPickerView<String> mOptionsPickerView;

    private OptionsPickerView<String> mOptionsPickerView1;

    private String etMoney_content;

    private String etPut_content;

    private String etPhone_content;

    private String voiceflag = "";
    private String voiceresult = "";
    private static final String TAG = MadeOrderActivity.class.getSimpleName();

    private String[] mVals = new String[]{"随意购", "买咖啡", "买早餐", "买宵夜", "买水果", "买酒", "买蛋糕", "买鲜花", "买衣服"};
    //CAMER_CODE
    private static final int REQUEST_CAMERA_CODE = 10;

    //PRIVATE_CODE
    private static final int REQUEST_PREVIEW_CODE = 20;
    //相片路径
    private ArrayList<String> imagePaths = new ArrayList<>();

    //GirdAdapter
    private GridAdapter gridAdapter;
    private static final int getaddress = 123;
    private static final int buyaddress = 124;
    private static final int ordertrail = 125;
    private String finaltv = "";

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void ReceiveaddressEvent(Receiveaddress event) {
        tvTakeaddress.setText(event.getReceiveaddress());
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void PurchaseaddressEvent(Purchaseaddress event) {
        tvGetaddress.setText(event.getPurchaseaddress());
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void VoiceEvent(VoiceEvent event) {
        voiceflag = event.getVoiceflag();
        voiceresult = event.getVoiceresult();
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void EstimateEvent(EstimateEvent event) {
        tvEstimate.setText("20元");
        tvFinalmoney.setText("50");
    }

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
                        Toast.makeText(MadeOrderActivity.this, "支付成功",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MadeOrderActivity.this, ProgressActivity.class);
                        startActivity(intent);
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(MadeOrderActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(MadeOrderActivity.this, "支付失败",
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
    @Override
    protected void onStart() {
        if (voiceflag.equals("Voiceorder")) {
            tvVoice.setVisibility(View.VISIBLE);
            etPut.setHint("");
        }
        super.onStart();
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
                PayTask alipay = new PayTask(MadeOrderActivity.this);
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

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_made_order);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initializePhoto();
        initialize();
        tvVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (voiceresult.equals("")) {
                    etPut.setText("目前无需要转化的语音");
                } else {
                    tvVoice.setVisibility(View.INVISIBLE);
                    etPut.setText("");

                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MadeOrderActivity.this.finish();
            }
        });
        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged==" + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged==" + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged==" + s);

                finaltv = Integer.toString(Integer.parseInt(s.toString()) + 50);

                tvFinalmoney.setText(finaltv);

                Log.d(TAG, "afterTextChanged==============" + finaltv);

            }
        });

        tvPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MadeOrderActivity.this, PriceActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initialize() {
        idFlowlayout.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                final LayoutInflater mInflater = LayoutInflater.from(getApplicationContext());
                TextView tv = (TextView) mInflater.inflate(R.layout.tag_item_tv, idFlowlayout, false);
                tv.setText(s);
                return tv;
            }
        });
    }

    private void initializePhoto() {
        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;
        gridView.setNumColumns(cols);
    }

    @OnClick(et_put)
    public void onEtPutClicked() {
    }

    @OnClick(R.id.et_phone)
    public void onEtPhoneClicked() {

    }

    @OnClick(R.id.tv_takeaddress)
    public void onTvTakeaddressClicked() {
        Intent intent = new Intent(this, MapReceiveActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.tv_getaddress)
    public void onTvGetaddressClicked() {
        mOptionsPickerView1 = new OptionsPickerView<>(this);
        final ArrayList<String> list = new ArrayList<>();
        list.add("就近购买");
        list.add("指定地点购买");
        mOptionsPickerView1.setPicker(list);
        mOptionsPickerView1.setCancelTextColor(Color.GRAY);
        mOptionsPickerView1.setSubmitTextColor(Color.RED);
        mOptionsPickerView1.setTextSize(16f);
        mOptionsPickerView1.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
                String address = list.get(option1);
                tvGetaddress.setText(address);
                if (address == "指定地点购买") {
                    Intent intent = new Intent(MadeOrderActivity.this, MapPurchaseActivity.class);
                    startActivity(intent);
                }
                if (address == "就近购买") {
                    dialog();
                }
            }
        });
        mOptionsPickerView1.show();
    }

    private void dialog()
    {
        final CueDialog dlg = new CueDialog(MadeOrderActivity.this, R.style.PostDialog);
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.cuedialog);
        Button btn = (Button) window.findViewById(R.id.btn_yes);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });
        Button btn1 = (Button) window.findViewById(R.id.btn_deny);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });
    }

    @OnClick(R.id.tv_time)
    public void onTvTimeClicked() {
        mOptionsPickerView = new OptionsPickerView<>(this);
        final ArrayList<String> list = new ArrayList<>();
        list.add("半小时内送达");
        list.add("一小时内送达");
        list.add("一个半小时内送达");
        list.add("两小时内送达");
        list.add("聊天商谈");
        mOptionsPickerView.setPicker(list);
        mOptionsPickerView.setCancelTextColor(Color.GRAY);
        mOptionsPickerView.setSubmitTextColor(Color.RED);
        mOptionsPickerView.setTextSize(16f);
        mOptionsPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
                String time = list.get(option1);
                tvTime.setText(time);
            }
        });
        mOptionsPickerView.show();
    }

    @OnClick(R.id.tv_finalmoney)
    public void onTvFinalmoneyClicked() {
    }

    @OnClick(R.id.btn_pay)
    public void onBtnPayClicked() {
        get__editValue();
        Log.d(TAG, "etPut_content" + etPut_content);
        Log.d(TAG, "etMoney_content" + etMoney_content);
        Log.d(TAG, "etPhone_content" + etPhone_content);
        OkhttpUtils_orderThree_publish();
        pay();
    }

    public void get__editValue() {
        etPut_content = etPut.getText().toString().trim();
        etMoney_content = etMoney.getText().toString().trim();
        etPhone_content = etPhone.getText().toString().trim();
    }

    private void OkhttpUtils_orderThree_publish() {
        User user = new User();
        user.setId(1);
        OrderUserThree orderUserThree = new OrderUserThree();
        orderUserThree.setUserGet(user);
        orderUserThree.setUserGetNotes(etPut_content);
        orderUserThree.setUserGetTel(etPhone_content);
        orderUserThree.setUserGetAdress(tvTakeaddress.getText().toString());
        orderUserThree.setPosition(tvGetaddress.getText().toString());
        orderUserThree.setUserGetExceptTime(Timestamp.valueOf("2015-11-15 11:23:12"));
        orderUserThree.setUserDeliveryReward(Float.parseFloat(etMoney_content));
        orderUserThree.setUserSendMoney(Float.parseFloat("30"));
        OkHttpUtils
                .postString()
                .url(Tool_Url.getUrl_orderUserThree_publish())
                .content(new Gson().toJson(orderUserThree))
                .build()
                .execute(new MyStringCallback());
    }

//    @OnClick(R.id.iv_takephoto)
//    public void onViewClicked() {
//        String imgs = "paizhao";
//        if ("paizhao".equals(imgs)) {
//            PhotoPickerIntent intent = new PhotoPickerIntent(MadeOrderActivity.this);
//            intent.setSelectModel(SelectModel.MULTI);
//            intent.setShowCarema(true); // 是否显示拍照
//            intent.setMaxTotal(6); // 最多选择照片数量，默认为6
//            intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
//            startActivityForResult(intent, REQUEST_CAMERA_CODE);
//        } else {
//            PhotoPreviewIntent intent = new PhotoPreviewIntent(MadeOrderActivity.this);
//            intent.setPhotoPaths(imagePaths);
//            startActivityForResult(intent, REQUEST_PREVIEW_CODE);
//        }
//        gridAdapter = new GridAdapter(imagePaths);
//        gridView.setAdapter(gridAdapter);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //选择照片
                case REQUEST_CAMERA_CODE:
                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    Log.d(TAG, "数量：" + list.size());
                    loadAdpater(list);
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    loadAdpater(ListExtra);
                    break;
            }
        }
    }

    private void loadAdpater(ArrayList<String> paths) {
        if (imagePaths != null && imagePaths.size() > 0) {
            imagePaths.clear();
        }
        if (paths.contains("paizhao")) {
            paths.remove("paizhao");
        }
        paths.add("paizhao");
        imagePaths.addAll(paths);
        gridAdapter = new GridAdapter(imagePaths);
        gridView.setAdapter(gridAdapter);
        try {
            JSONArray obj = new JSONArray(imagePaths);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.iv_takephoto, R.id.tv_estimate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_takephoto:
                String imgs = "paizhao";
                if ("paizhao".equals(imgs)) {
                    PhotoPickerIntent intent = new PhotoPickerIntent(MadeOrderActivity.this);
                    intent.setSelectModel(SelectModel.MULTI);
                    intent.setShowCarema(true); // 是否显示拍照
                    intent.setMaxTotal(6); // 最多选择照片数量，默认为6
                    intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                    startActivityForResult(intent, REQUEST_CAMERA_CODE);
                } else {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(MadeOrderActivity.this);
                    intent.setPhotoPaths(imagePaths);
                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }
                gridAdapter = new GridAdapter(imagePaths);
                gridView.setAdapter(gridAdapter);
                break;
            case R.id.tv_estimate:
                Intent intent=new Intent(MadeOrderActivity.this,PriceestimateActivity.class);
                startActivity(intent);
                break;
        }
    }

    private class GridAdapter extends BaseAdapter {
        private ArrayList<String> listUrls;
        private LayoutInflater inflater;

        public GridAdapter(ArrayList<String> listUrls) {
            this.listUrls = listUrls;
            if (listUrls.size() == 7) {
                listUrls.remove(listUrls.size() - 1);
            }
            inflater = LayoutInflater.from(MadeOrderActivity.this);
        }

        public int getCount() {
            return listUrls.size();
        }

        @Override
        public String getItem(int position) {
            return listUrls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_order_photo, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final String path = listUrls.get(position);
            if (path.equals("paizhao")) {
                // holder.image.setImageResource(R.mipmap.find_add_img);
            } else {
                Glide.with(MadeOrderActivity.this)
                        .load(path)
                        .centerCrop()
                        .crossFade()
                        .into(holder.image);
            }
            return convertView;
        }

        class ViewHolder {
            ImageView image;
        }
    }

    private class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            Log.i(TAG, "onBefore");
        }

        @Override
        public void onAfter(int id) {
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
