package com.wxs.fastmail.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wxs.fastmail.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublishActivity extends AppCompatActivity {

    @Bind(R.id.idea_cancal)
    TextView ideaCancal;
    @Bind(R.id.idea_send)
    TextView ideaSend;
    @Bind(R.id.idea_content)
    EditText ideaContent;
    @Bind(R.id.inputType)
    TextView inputType;
    @Bind(R.id.idea_username)
    TextView ideaUsername;
    @Bind(R.id.ImgList)
    RecyclerView ImgList;
    @Bind(R.id.idea_linearLayout)
    LinearLayout ideaLinearLayout;
    @Bind(R.id.blankspace)
    ImageView blankspace;
    @Bind(R.id.publicbutton)
    TextView publicbutton;
    @Bind(R.id.limitTextView)
    TextView limitTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.idea_cancal, R.id.idea_send})
    public void onViewClicked(View view)
    {
        switch (view.getId()) {
            case R.id.idea_cancal:
                PublishActivity.this.finish();
                break;
            case R.id.idea_send:
//
//                Alerter.create(this)
//                        .setTitle("Excity")
//                        .setBackgroundColorRes(R.color.colorAccent)
//                        .setText("动态发布成功")
//                        .enableSwipeToDismiss()
//                        .show();

                PublishActivity.this.finish();
                Toast.makeText(PublishActivity.this, "动态发布成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @OnClick(R.id.idea_content)
    public void onViewClicked() {
        ideaSend.setTextColor(Color.RED);
    }
}