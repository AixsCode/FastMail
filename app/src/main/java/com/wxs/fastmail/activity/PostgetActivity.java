package com.wxs.fastmail.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airsaid.pickerviewlibrary.CityPickerView;
import com.airsaid.pickerviewlibrary.OptionsPickerView;
import com.airsaid.pickerviewlibrary.listener.OnSimpleCitySelectListener;
import com.wxs.fastmail.Events.GetadsEvent;
import com.wxs.fastmail.R;
import com.wxs.fastmail.fragment.Fragmentmain_post;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wxs.fastmail.R.id.et_phone;


public class PostgetActivity extends AppCompatActivity{
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.btn_sure)
    TextView btnSure;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.lay_name)
    LinearLayout layName;
    @Bind(R.id.lay_phone)
    LinearLayout layPhone;
    @Bind(R.id.tv_area)
    TextView tvArea;
    @Bind(R.id.lay_area)
    LinearLayout layArea;
    @Bind(R.id.tv_street)
    TextView tvStreet;
    @Bind(R.id.lay_street)
    LinearLayout layStreet;
    @Bind(R.id.et_put)
    EditText etPut;
    @Bind(et_phone)
    EditText etPhone;
    private CityPickerView mCityPickerView;
    private static final String TAG = PostgetActivity.class.getSimpleName();
    private OptionsPickerView<String> mOptionsPickerView;

    private Fragmentmain_post fr_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postget);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.back, R.id.btn_sure, R.id.lay_area, R.id.lay_street})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                PostgetActivity.this.finish();
                break;
            case R.id.btn_sure:
                String area=tvArea.getText().toString().trim();
                String street=tvStreet.getText().toString().trim();
                String phone=etPhone.getText().toString().trim();
                String name=etName.getText().toString().trim();
                GetadsEvent getadsEvent=new GetadsEvent(area+street,name,phone);

                EventBus.getDefault().postSticky(getadsEvent);
                PostgetActivity.this.finish();
                break;
            case R.id.lay_area:
                mCityPickerView = new CityPickerView(this);
                mCityPickerView.setCancelTextColor(Color.GRAY);
                mCityPickerView.setSubmitTextColor(Color.RED);
                mCityPickerView.setTextSize(16f);
                mCityPickerView.setOnCitySelectListener(new OnSimpleCitySelectListener() {
                    @Override
                    public void onCitySelect(String prov, String city, String area) {
                        // 省、市、区 分开获取
                        Log.e(TAG, "省: " + prov + " 市: " + city + " 区: " + area);
                    }
                    @Override
                    public void onCitySelect(String str) {
                        tvArea.setText(str);
                    }
                });
                mCityPickerView.show();
                break;
            case R.id.lay_street:
                mOptionsPickerView = new OptionsPickerView<>(this);
                final ArrayList<String> list = new ArrayList<>();
                list.add("硕放街道");
                list.add("华庄街道");
                list.add("新安街道");
                list.add("坊前街道");
                list.add("荣巷街道");
                list.add("梅村街道");
                list.add("旺庄街道");
                list.add("江溪街道");
                list.add("河埒街道");
                mOptionsPickerView.setPicker(list);
                mOptionsPickerView.setCancelTextColor(Color.GRAY);
                mOptionsPickerView.setSubmitTextColor(Color.RED);
                mOptionsPickerView.setTextSize(16f);
                mOptionsPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int option1, int option2, int option3) {
                        String street = list.get(option1);
                        tvStreet.setText(street);
                    }
                });
                mOptionsPickerView.show();
                break;
            default:
                break;
        }
    }
}
