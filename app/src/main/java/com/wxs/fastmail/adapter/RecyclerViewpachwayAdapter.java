package com.wxs.fastmail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wxs.fastmail.R;
import com.wxs.fastmail.common.Application;
import com.wxs.fastmail.databean.PatchData;
import com.wxs.fastmail.tool.Tool_GetData;
import com.wxs.fastmail.tool.Tool_Random;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wxs on 2017/7/28.
 */

public class RecyclerViewpachwayAdapter extends RecyclerView.Adapter<RecyclerViewpachwayAdapter.ViewHolder> implements View.OnClickListener{

    private Context mContext;
    //数据集
    private List<PatchData> patchDataList = new ArrayList<>();

    public RecyclerViewpachwayAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addAllData(List<PatchData> patchDataList) {
        this.patchDataList.addAll(patchDataList);
        notifyDataSetChanged();
    }
    public void clearData() {
        this.patchDataList.clear();
    }

    //定义变量
    private OnItemClickListener mOnItemClickListener = null;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //使用getTag获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.order_label)
        ImageView orderLabel;
        @Bind(R.id.order_time)
        TextView orderTime;
        @Bind(R.id.order_money)
        TextView orderMoney;
        @Bind(R.id.order_name)
        TextView orderName;
        @Bind(R.id.order_phone)
        TextView orderPhone;
        @Bind(R.id.order_reqtime)
        TextView orderReqtime;
        @Bind(R.id.order_things)
        TextView orderThings;
        @Bind(R.id.order_dis1)
        TextView orderDis1;
        @Bind(R.id.order_dis2)
        TextView orderDis2;
        @Bind(R.id.iv_me_position)
        ImageView ivMePosition;
        @Bind(R.id.btn_getOrder)
        Button btnGetOrder;
        private SparseArray<View> mViews;
        private View mRootView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mViews = new SparseArray<>();
            mRootView = itemView;
        }

        public <T extends ImageView> ViewHolder setRandomIconByGlide(Context context, int viewId) {
            T imageView = getView(viewId);
            Glide.with(context).load(Tool_Random.getRandomLabel()).centerCrop()
                    .into(imageView);
            return this;
        }

        public <T extends View> T getView(int viewId)
        {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mRootView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }
    }

    //定义接口
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_patchway_item, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        PatchData patchData = Tool_GetData.getPatchDataList().get(position);
        holder.orderTime.setText(patchData.getOrder_time());
        holder.orderMoney.setText(patchData.getOrder_money());
        holder.orderThings.setText(patchData.getOrder_things());
        holder.orderName.setText(patchData.getOrder_name());
        holder.orderPhone.setText(patchData.getOrder_phone());
        holder.orderReqtime.setText(patchData.getOrder_reqtime());
        holder.orderDis1.setText(patchData.getOrder_distance1());
        holder.orderDis2.setText(patchData.getOrder_distance2());
        holder.setRandomIconByGlide(Application.getContextObject(), R.id.order_label);
    }
    @Override
    public int getItemCount() {
        return patchDataList.size();
    }
}
