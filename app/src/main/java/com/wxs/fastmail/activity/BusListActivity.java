package com.wxs.fastmail.activity;

import android.content.Intent;
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
import com.wxs.fastmail.databean.BusinessData;
import com.wxs.fastmail.tool.Tool_GetData;
import butterknife.Bind;
import butterknife.ButterKnife;
public class BusListActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);
        ButterKnife.bind(this);
        initialize();
    }
    private void initialize() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter());
    }
    class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            return new ViewHolder(inflater.inflate(R.layout.business_item_home, parent, false));
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position)
        {
            BusinessData businessData = Tool_GetData.getBusinessDataList().get(position);
            holder.tvAddress.setText(businessData.getStore_name());
            holder.tvTime.setText(businessData.getStore_time());
            holder.tvDistance.setText(businessData.getStore_distance());
            holder.ivImageView.setImageBitmap(businessData.getBitmap());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BusListActivity.this, BusinessActivity.class);
                    startActivity(intent);
                }
            });
        }
        @Override
        public int getItemCount() {
            return Tool_GetData.getBusinessDataList().size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.iv_imageView)
            ImageView ivImageView;
            @Bind(R.id.tv_address)
            TextView tvAddress;
            @Bind(R.id.tv_distance)
            TextView tvDistance;
            @Bind(R.id.tv_time)
            TextView tvTime;
            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
