package com.wxs.fastmail.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

public class TeamBuyActivity extends AppCompatActivity
{
    @Bind(R.id.back)
    ImageView back;
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
    @Bind(R.id.tv_num)
    TextView tvNum;
    @Bind(R.id.lay_num)
    LinearLayout layNum;
    @Bind(R.id.et_money)
    EditText etMoney;
    @Bind(R.id.tv_buyaddress)
    TextView tvBuyaddress;
    @Bind(R.id.lay_buyaddress)
    LinearLayout layBuyaddress;
    @Bind(R.id.btn_teambuy)
    Button btnTeambuy;
    private  String etPublish_content;
    private  String etMoney_content;
    private OptionsPickerView<String> mOptionsPickerView;
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
        setContentView(R.layout.activity_team_buy);
        ButterKnife.bind(this);
        initializePhoto();
        geteditcontent();
    }
    @OnClick({R.id.back, R.id.lay_photo, R.id.lay_num, R.id.lay_buyaddress, R.id.btn_teambuy})
    public void onViewClicked(View view) {
          switch (view.getId())
          {
            case R.id.back:
                TeamBuyActivity.this.finish();
                break;
            case R.id.lay_photo:
                takephoto();
                break;
            case R.id.lay_num:
                teamnumshow();
                break;
            case R.id.lay_buyaddress:
                Intent intent =new Intent(TeamBuyActivity.this,MapPurchaseActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_teambuy:
                Toasty.success(TeamBuyActivity.this,"团购发起成功", Toast.LENGTH_SHORT).show();
                TeamBuyActivity.this.finish();
            default:
                break;
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        tvBuyaddress.setText("江苏省无锡市五爱人家面包");
    }
    //显示参团人数
    private void teamnumshow() {
        mOptionsPickerView = new OptionsPickerView<>(this);
        final ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < 15; i++)
        {
           list.add("参团人数"+i+"人");
        }
        mOptionsPickerView.setPicker(list);
        mOptionsPickerView.setCancelTextColor(Color.GRAY);
        mOptionsPickerView.setSubmitTextColor(Color.RED);
        mOptionsPickerView.setTextSize(16f);
        mOptionsPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
                String num = list.get(option1);
                tvNum.setText(num);
            }
        });
        mOptionsPickerView.show();
    }
    //获取edit的内容
    private void geteditcontent() {
        etPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPublish_content=etPublish.getText().toString().trim();
            }
        });
        etMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                etMoney_content=etMoney.getText().toString().trim();
            }
        });
    }
    //初始化照片
    private void initializePhoto() {
        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;
        gridView.setNumColumns(cols);
    }
    //构造多图片适配器
    private class GridAdapter extends BaseAdapter {
        private ArrayList<String> listUrls;
        private LayoutInflater inflater;

        public GridAdapter(ArrayList<String> listUrls) {
            this.listUrls = listUrls;
            if (listUrls.size() == 7) {
                listUrls.remove(listUrls.size() - 1);
            }
            inflater = LayoutInflater.from(TeamBuyActivity.this);
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
                Glide.with(TeamBuyActivity.this)
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
    private void takephoto() {
        String imgs = "paizhao";
        if ("paizhao".equals(imgs)) {
            PhotoPickerIntent intent = new PhotoPickerIntent(TeamBuyActivity.this);
            intent.setSelectModel(SelectModel.MULTI);
            intent.setShowCarema(true); // 是否显示拍照
            intent.setMaxTotal(6); // 最多选择照片数量，默认为6
            intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
            startActivityForResult(intent, REQUEST_CAMERA_CODE);
        } else {
            PhotoPreviewIntent intent = new PhotoPreviewIntent(TeamBuyActivity.this);
            intent.setPhotoPaths(imagePaths);
            startActivityForResult(intent, REQUEST_PREVIEW_CODE);
        }
        gridAdapter = new GridAdapter(imagePaths);
        gridView.setAdapter(gridAdapter);
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
