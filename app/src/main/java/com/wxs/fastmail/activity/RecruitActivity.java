package com.wxs.fastmail.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airsaid.pickerviewlibrary.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.wxs.fastmail.R;

import org.json.JSONArray;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class RecruitActivity extends AppCompatActivity
{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_publish)
    EditText etPublish;
    @Bind(R.id.gridView)
    GridView gridView;
    @Bind(R.id.iv_takephoto)
    ImageView ivTakephoto;
    @Bind(R.id.lay_photo)
    LinearLayout layPhoto;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.lay_time)
    LinearLayout layTime;
    @Bind(R.id.et_rec)
    EditText etRec;
    @Bind(R.id.lay_rec)
    LinearLayout layRec;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.btn_rec)
    Button btnRec;
    @Bind(R.id.iv_help)
    ImageView ivHelp;
    @Bind(R.id.tv_help)
    TextView tvHelp;
    @Bind(R.id.recruit_help)
    LinearLayout recruitHelp;
    @Bind(R.id.iv_time)
    ImageView ivTime;
    @Bind(R.id.tv_time1)
    TextView tvTime1;
    @Bind(R.id.recruit_time)
    LinearLayout recruitTime;
    @Bind(R.id.iv_line)
    ImageView ivLine;
    @Bind(R.id.tv_line)
    TextView tvLine;
    @Bind(R.id.recruit_line)
    LinearLayout recruitLine;
    @Bind(R.id.iv_dog)
    ImageView ivDog;
    @Bind(R.id.tv_dog)
    TextView tvDog;
    @Bind(R.id.recruit_dog)
    LinearLayout recruitDog;
    @Bind(R.id.iv_move)
    ImageView ivMove;
    @Bind(R.id.tv_move)
    TextView tvMove;
    @Bind(R.id.recruit_move)
    LinearLayout recruitMove;
    private OptionsPickerView<String> mOptionsPickerView;
    private String edittext;
    private static final String TAG = RecruitActivity.class.getSimpleName();
    //CAMER_CODE
    private static final int REQUEST_CAMERA_CODE = 10;
    //PRIVATE_CODE
    private static final int REQUEST_PREVIEW_CODE = 20;
    //相片路径
    private ArrayList<String> imagePaths = new ArrayList<>();
    //GirdAdapter
    private GridAdapter gridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruit);
        ButterKnife.bind(this);
        initializePhoto();
        initialize();
    }
    private void initialize()
    {
        recruitHelp.setOnClickListener(new IconOnClickListener());
        recruitMove.setOnClickListener(new IconOnClickListener());
        recruitDog.setOnClickListener(new IconOnClickListener());
        recruitLine.setOnClickListener(new IconOnClickListener());
        recruitTime.setOnClickListener(new IconOnClickListener());
    }
    private class IconOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            resetOtherTabs();
            switch (v.getId()) {
                case R.id.recruit_move:
                    tvMove.setTextColor(ContextCompat.getColor(RecruitActivity.this, R.color.colorAccent));
                    ivMove.setBackgroundResource(R.drawable.recruit_move_click);
                    etPublish.setText("搬家");
                    break;
                case R.id.recruit_dog:
                    tvDog.setTextColor(ContextCompat.getColor(RecruitActivity.this, R.color.colorAccent));
                    ivDog.setBackgroundResource(R.drawable.recruit_dog_clic);
                    etPublish.setText("运送宠物");
                    break;
                case R.id.recruit_line:
                    tvLine.setTextColor(ContextCompat.getColor(RecruitActivity.this, R.color.colorAccent));
                    ivLine.setBackgroundResource(R.drawable.recruit_line_click);
                    etPublish.setText("代排队");
                    break;
                case R.id.recruit_time:
                    tvTime.setTextColor(ContextCompat.getColor(RecruitActivity.this, R.color.colorAccent));
                    ivTime.setBackgroundResource(R.drawable.recruit_time_click);
                    etPublish.setText("小时工");
                    break;
                case R.id.recruit_help:
                    tvHelp.setTextColor(ContextCompat.getColor(RecruitActivity.this, R.color.colorAccent));
                    ivHelp.setBackgroundResource(R.drawable.recruit_help_click);
                    etPublish.setText("帮忙");
                    break;
                default:
                    break;
            }
    }
        private void resetOtherTabs()
        {
            tvMove.setTextColor(ContextCompat.getColor(RecruitActivity.this, R.color.text1));
            ivMove.setBackgroundResource(R.drawable.recruit_move_unclick);

            tvDog.setTextColor(ContextCompat.getColor(RecruitActivity.this, R.color.text1));
            ivDog.setBackgroundResource(R.drawable.recruit_dog_unclic);

            tvLine.setTextColor(ContextCompat.getColor(RecruitActivity.this, R.color.text1));
            ivLine.setBackgroundResource(R.drawable.recruit_line_unclick);

            tvTime.setTextColor(ContextCompat.getColor(RecruitActivity.this, R.color.text1));
            ivTime.setBackgroundResource(R.drawable.recruit_time_unclick);

            tvHelp.setTextColor(ContextCompat.getColor(RecruitActivity.this, R.color.text1));
            ivHelp.setBackgroundResource(R.drawable.recruit_help_unclick);
        }
    }
    @OnClick({R.id.lay_photo, R.id.lay_time})
    public void onViewClicked(View view)
    {
        switch (view.getId()) {
            case R.id.lay_photo:
                takephoto();
                break;
            case R.id.lay_time:
                timeshow();
                break;
        }
    }
    private void initializePhoto() {
        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;
        gridView.setNumColumns(cols);
    }

    private void takephoto() {
        String imgs = "paizhao";
        if ("paizhao".equals(imgs)) {
            PhotoPickerIntent intent = new PhotoPickerIntent(RecruitActivity.this);
            intent.setSelectModel(SelectModel.MULTI);
            intent.setShowCarema(true); // 是否显示拍照
            intent.setMaxTotal(6); // 最多选择照片数量，默认为6
            intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
            startActivityForResult(intent, REQUEST_CAMERA_CODE);
        } else {
            PhotoPreviewIntent intent = new PhotoPreviewIntent(RecruitActivity.this);
            intent.setPhotoPaths(imagePaths);
            startActivityForResult(intent, REQUEST_PREVIEW_CODE);
        }
        gridAdapter = new GridAdapter(imagePaths);
        gridView.setAdapter(gridAdapter);
    }

    public void timeshow() {
        mOptionsPickerView = new OptionsPickerView<>(this);
        final ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            for (int j = 1; j < 24; j++)
                list.add(i + "天" + j + "小时");
        }
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

    @OnClick(R.id.back)
    public void onBackClicked() {
        RecruitActivity.this.finish();
    }

    @OnClick(R.id.btn_rec)
    public void onBtnRecClicked() {
        edittext = etPublish.getText().toString().trim();
        Log.i(TAG, "edittext==" + edittext);
        if (!edittext.equals("")) {
            Toasty.success(RecruitActivity.this, "招募发布成功", Toast.LENGTH_SHORT).show();
            RecruitActivity.this.finish();
        } else {
            Toasty.normal(RecruitActivity.this, "请填写招募信息", Toast.LENGTH_SHORT).show();
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
            inflater = LayoutInflater.from(RecruitActivity.this);
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
                Glide.with(RecruitActivity.this)
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
}
