package com.wxs.fastmail.activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxs.fastmail.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InsurancefinishActivity extends AppCompatActivity
{
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.id_recyclerview)
    RecyclerView idRecyclerview;
    private List<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurancefinish);
        ButterKnife.bind(this);
        initData();
        initlize();
    }
    private void initData()
    {
        mDatas= new ArrayList<String>();
        for (int i=30;i>0;i--)
        {
            mDatas.add("2017-"+"07"+"-"+i);
        }
    }
    private void initlize()
    {
        idRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        idRecyclerview.setAdapter(new HomeAdapter());
    }
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(InsurancefinishActivity.this).inflate(R.layout.item_insurance, parent,
                    false));
            return holder;
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
             holder.tvTime.setText(mDatas.get(position));

        }
        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder
        {
            @Bind(R.id.tv_time)
            TextView tvTime;
            public MyViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
    @OnClick(R.id.back)
    public void onViewClicked()
    {
        InsurancefinishActivity.this.finish();
    }
}
