package com.wxs.fastmail.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wxs.fastmail.R;
import com.wxs.fastmail.bean.OrderUserThree;
import com.wxs.fastmail.common.Application;
import com.wxs.fastmail.tool.Tool_Random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wxs on 2017/7/11.
 */
public class RecyclerViewBuyAadpter extends RecyclerView.Adapter<RecyclerViewBuyAadpter.ViewHolder> implements View.OnClickListener {

    private Context mContext;

    private  List<OrderUserThree> orderUserThreeList =new ArrayList<>();

    public void addAllData(List<OrderUserThree> orderUserThreeList)
    {
        this.orderUserThreeList.addAll(orderUserThreeList);
        notifyDataSetChanged();

    }
    //定义变量
    private OnItemClickListener mOnItemClickListener =null;
    @Override
    public void onClick(View v)
    {
        if(mOnItemClickListener!=null) {
            //使用getTag获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
    //定义接口
    public  static interface OnItemClickListener
    {
        void  onItemClick(View view ,int position);

    }
    public void  setOnItemClickListener(OnItemClickListener  listener)
    {
         this.mOnItemClickListener=listener;
    }
    public void clearData() {
        this.orderUserThreeList.clear();
    }
    public RecyclerViewBuyAadpter(Context context)
    {
        mContext = context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.order_time)
        TextView orderTime;
        @Bind(R.id.order_money)
        TextView orderMoney;
        @Bind(R.id.order_things)
        TextView orderThings;
        @Bind(R.id.order_address)
        TextView orderAddress;
        @Bind(R.id.card_view)
        CardView cardView;
        @Bind(R.id.order_head)
        ImageView orderHead;
        private SparseArray<View> mViews;
        private View mRootView;
        public ViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mViews = new SparseArray<>();
            mRootView = itemView;
        }
         public <T extends ImageView> ViewHolder setRandomIconByGlide(Context context, int viewId) {
            T imageView = getView(viewId);
            Glide.with(Application.getContextObject()).load(Tool_Random.getRandomIcon()).centerCrop().into(imageView);
            return this;
         }
         public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mRootView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_buy_item, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        holder.itemView.setTag(position);
        Collections.reverse(orderUserThreeList);
        OrderUserThree orderUserThree =orderUserThreeList.get(position);

        holder.orderAddress.setText(orderUserThree.getPosition());

        if(orderUserThree.getUserDeliveryReward()==null) {
               holder.orderMoney.setText("30");
        }
        else
        {
            holder.orderMoney.setText((orderUserThree.getUserDeliveryReward()).toString());
        }
        holder.orderTime.setText(Tool_Random.getRandomTime());

        holder.orderThings.setText(orderUserThree.getUserGetNotes());

        holder.setRandomIconByGlide(mContext,R.id.order_head);

    }
    @Override
    public int getItemCount()
    {
          return orderUserThreeList.size();
    }
}
