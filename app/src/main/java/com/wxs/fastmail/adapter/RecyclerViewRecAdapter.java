package com.wxs.fastmail.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wxs.fastmail.R;
import com.wxs.fastmail.bean.Recruit;
import com.wxs.fastmail.common.Application;
import com.wxs.fastmail.tool.Tool_Random;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wxs on 2017/7/18.
 */

public class RecyclerViewRecAdapter extends RecyclerView.Adapter<RecyclerViewRecAdapter.ViewHolder>
{
    private Context mcontext;
    private List<Recruit> recruitList = new ArrayList<>();
    public void addAllData(List<Recruit> recruitList) {
        this.recruitList.addAll(recruitList);
        notifyDataSetChanged();
    }
    public RecyclerViewRecAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }
    public void clearData() {
        this.recruitList.clear();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_rec_item, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Recruit  recruit =recruitList.get(position);
        holder.recDemand.setText(recruit.getContent());
        holder.recMoney.setText(recruit.getPrice().toString());
        holder.recTime.setText(Tool_Random.getRandomTime());
        holder.recHeadtitle.setText(Tool_Random.getRandomName());
        String Urlimage=recruit.getImageUrl();
        Picasso.with(Application.getContextObject()).load(Urlimage).into(holder.recPhoto);
        String Urlimage1=recruit.getUser().getImageUrl();
        Picasso.with(Application.getContextObject()).load(Urlimage1).into(holder.recHeadphoto);
    }
    @Override
    public int getItemCount()
    {
        return recruitList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.rec_time)
        TextView recTime;
        @Bind(R.id.rec_money)
        TextView recMoney;
        @Bind(R.id.rec_headphoto)
        ImageView recHeadphoto;
        @Bind(R.id.rec_headtitle)
        TextView recHeadtitle;
        @Bind(R.id.rec_demand)
        TextView recDemand;
        @Bind(R.id.rec_photo)
        ImageView recPhoto;
        @Bind(R.id.card_view)
        CardView cardView;
        public  ViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
